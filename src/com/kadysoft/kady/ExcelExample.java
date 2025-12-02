





import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExample {

    public static void main(String[] args) throws IOException {

        try {

            FileInputStream file = new FileInputStream(new File("C:\\Users\\Ahmed.ElKady\\Desktop\\KADINIO.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell = null;

            //Update the value of cell
            cell = sheet.getRow(5).getCell(1);
            cell.setCellValue("Hello"); 
           
//            cell = sheet.getRow(2).getCell(2);
//            cell.setCellValue(cell.getNumericCellValue() * 2);
//            Row row = sheet.getRow(0);
//            row.createCell(3).setCellValue("Value 2");

            file.close();
            FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\Ahmed.ElKady\\Desktop\\KADINIO.xlsx"));
            workbook.write(outFile);
            outFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

