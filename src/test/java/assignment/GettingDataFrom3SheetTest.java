package assignment;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GettingDataFrom3SheetTest {

	public static void main(String[] args) throws Throwable {

		FileInputStream fis = new FileInputStream("./src/test/resources/data/new.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		for(int page=1;page<=3;page++)
		{
			String temp = "sheet"+page;
			Sheet sheet = workbook.getSheet(temp);
			for(int row=0;row<=sheet.getLastRowNum();row++)
			{
				for(int column=0;column<2;column++)
				{
					String value = sheet.getRow(row).getCell(column).getStringCellValue();
					System.out.print(value+"  ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

}
