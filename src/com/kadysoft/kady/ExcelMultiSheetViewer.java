
package com.kadysoft.kady;

/*
 * مثال كامل لـ JavaFX Application باستخدام SpreadsheetView من ControlsFX.
 * البرنامج: زر لاختيار ملف Excel (.xlsx)، قراءته بـ Apache POI، عرض البيانات في SpreadsheetView
 * مثل Excel، دعم تعديل الخلايا، ومعالجة المعادلات (formulas) تلقائيًا عند التحميل.
 * 
 * ملاحظات مهمة:
 * - SpreadsheetView لا يدعم formulas تلقائيًا مثل Excel، لذا نستخدم POI لحسابها قبل العرض.
 * - لدعم formulas ديناميكي أثناء التعديل: أضفنا listener بسيط لإعادة حساب عمود معين (مثال: عمود "Total").
 *   يمكن توسيعها لمعادلات أكثر تعقيدًا.
 * - Dependencies (في pom.xml لـ Maven):
 *   <dependency>
 *     <groupId>org.controlsfx</groupId>
 *     <artifactId>controlsfx</artifactId>
 *     <version>11.2.1</version>
 *   </dependency>
 *   <dependency>
 *     <groupId>org.apache.poi</groupId>
 *     <artifactId>poi-ooxml</artifactId>
 *     <version>5.2.5</version>
 *   </dependency>
 * 
 * - افترض أن الملف Excel له هيكل بسيط: عمود A (Item), B (Quantity), C (Price), D (Total = B*C أو formula).
 * 
 * جرب مع ملف Excel بسيط للاختبار.
 */



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.spreadsheet.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelMultiSheetViewer extends Application {

    private TabPane tabPane;
    private Workbook workbook;
    private FormulaEvaluator evaluator;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Excel Multi-Sheet Viewer");

        Button loadButton = new Button("تحميل ملف Excel");

        loadButton.setOnAction(e -> loadWorkbook(primaryStage));

        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        VBox root = new VBox(15, loadButton, tabPane);
        root.setStyle("-fx-padding: 20; -fx-background-color: #f5f5f5;");

        Scene scene = new Scene(root, 1100, 750);

        // تطبيق ثيم cupertino-light (غيّر المسار حسب مكان الملف عندك)
//        String cssPath = getClass().getResource("/cupertino-light.css").toExternalForm();
//        if (cssPath != null) {
//            scene.getStylesheets().add(cssPath);
//        } else {
//            System.err.println("لم يتم العثور على cupertino-light.css");
//        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadWorkbook(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx", "*.xlsm", "*.xls")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile == null) return;

        try (FileInputStream fis = new FileInputStream(selectedFile)) {
            workbook = WorkbookFactory.create(fis);  // يدعم xls و xlsx
            evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            evaluator.evaluateAll();

            tabPane.getTabs().clear();

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                String sheetName = workbook.getSheetName(i);

                Tab tab = new Tab(sheetName.isEmpty() ? "Sheet" + (i+1) : sheetName);
                tab.setContent(createSpreadsheetView(sheet));
                tabPane.getTabs().add(tab);
            }

            if (!tabPane.getTabs().isEmpty()) {
                tabPane.getSelectionModel().select(0);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            showError("خطأ في تحميل الملف", ex.getMessage());
        }
    }

    private SpreadsheetView createSpreadsheetView(Sheet sheet) {
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = 0;
        for (Row row : sheet) {
            if (row != null) {
                colCount = Math.max(colCount, row.getLastCellNum());
            }
        }
        if (colCount == 0) colCount = 26;

        GridBase grid = new GridBase(rowCount, colCount);
        ObservableList<ObservableList<SpreadsheetCell>> rows = grid.getRows();

        for (int r = 0; r < rowCount; r++) {
            Row excelRow = sheet.getRow(r);
            ObservableList<SpreadsheetCell> rowCells = FXCollections.observableArrayList();

            for (int c = 0; c < colCount; c++) {
                org.apache.poi.ss.usermodel.Cell cell = (excelRow != null) ? excelRow.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK) : null;
                String value = getCellValue(cell);
                SpreadsheetCell spCell = SpreadsheetCellType.STRING.createCell(r, c, 1, 1, value);
                rowCells.add(spCell);
            }
            rows.add(rowCells);
        }

        SpreadsheetView view = new SpreadsheetView(grid);
        view.setEditable(true);

        // إعادة حساب بسيط عند التعديل (مثال: عمود D = B × C)
        grid.getRows().forEach(rowList ->
                rowList.forEach(cell ->
                        cell.itemProperty().addListener((obs, oldVal, newVal) -> {
                            if (newVal != null) {
                                recalculateExampleFormulas(grid);
                            }
                        })
                )
        );

        return view;
    }

    private String getCellValue(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null) return "";

        int cellType = cell.getCellType();

        if (cellType == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA) {
            try {
                CellValue cv = evaluator.evaluate(cell);
                if (cv == null) return "";
                switch (cv.getCellType()) {
                    case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC: return formatNumber(cv.getNumberValue());
                    case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:  return cv.getStringValue();
                    case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN: return String.valueOf(cv.getBooleanValue());
                    case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:   return "#ERROR!";
                    default: return "";
                }
            } catch (Exception e) {
                return cell.getCellFormula();  // عرض الصيغة لو فشل الحساب
            }
        } else {
            switch (cellType) {
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
                    return cell.getStringCellValue().trim();
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
                    return formatNumber(cell.getNumericCellValue());
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
                case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:
                default:
                    return "";
            }
        }
    }

    // مساعد لتنسيق الأرقام بشكل أفضل
    private String formatNumber(double num) {
        if (num == (long) num) {
            return String.valueOf((long) num);
        }
        return String.format("%.4f", num).replaceAll("\\.?0+$", "");
    }

    // مثال بسيط لإعادة حساب (يمكن توسيعه)
    private void recalculateExampleFormulas(GridBase grid) {
        for (int r = 1; r < grid.getRowCount(); r++) {
            try {
                String qty = grid.getRows().get(r).get(1).getItem().toString().trim();
                String price = grid.getRows().get(r).get(2).getItem().toString().trim();

                double q = Double.parseDouble(qty.replaceAll("[^0-9.]", ""));
                double p = Double.parseDouble(price.replaceAll("[^0-9.]", ""));
                double total = q * p;

                grid.getRows().get(r).get(3).setItem(formatNumber(total));
            } catch (Exception ignored) {}
        }
    }

    private void showError(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}