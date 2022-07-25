package assignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataInExcelSheetTest {

	public static void main(String[] args) throws Throwable {

		// writing data in excel sheet
		FileInputStream fileInputStream = new FileInputStream("./src/test/resources/data/InputData.xlsx");
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		workbook.getSheet("Assignment").createRow(2).createCell(5).setCellValue("878776");
		FileOutputStream fileOutputStream = new FileOutputStream("./src/test/resources/data/InputData.xlsx");
		workbook.write(fileOutputStream);
		System.out.println("Data stored in excel sheet");

	}

}
