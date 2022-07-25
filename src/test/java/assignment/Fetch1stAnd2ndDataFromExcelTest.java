package assignment;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Fetch1stAnd2ndDataFromExcelTest {

	public static void main(String[] args) throws Throwable {

		FileInputStream fileInputStream = new FileInputStream("./src/test/resources/data/InputData.xlsx");
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet("CreateProject");
		for(int row=0;row<sheet.getLastRowNum();row++)
		{
			for(int column=0;column<2;column++)
			{
				String value = sheet.getRow(row).getCell(column).getStringCellValue();
				System.out.print(value+"   ");
			}
			System.out.println();
			//modification
		}
		
	}

}
