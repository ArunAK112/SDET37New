package assignment;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToFetchTheDataStoredInExcelSheetTest {

	public static void main(String[] args) throws Throwable {

		// getting data from excelsheet
		FileInputStream fileInputStream = new FileInputStream("./src/test/resources/data/InputData.xlsx");
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet("newLink");

//		for(int i=0; i<sheet.getLastRowNum(); i++)
//		{
//		Row row = sheet.getRow(i);
//		
//		for(int j=0;j<row.getLastCellNum();j++)
//		{
//			Cell cell = row.getCell(j);
//			DataFormatter dataFormatter = new DataFormatter();
//		    String data = dataFormatter.formatCellValue(cell);
//		    System.out.println(data);
//		}
//		}
		
		for(int i=0;i<sheet.getLastRowNum();i++)
		{
			String value = sheet.getRow(i).getCell(0).getStringCellValue();
			System.out.println(value);
		}
		
	}

}
