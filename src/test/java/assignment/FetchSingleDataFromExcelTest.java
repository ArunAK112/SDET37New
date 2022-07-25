package assignment;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchSingleDataFromExcelTest {

	public static void main(String[] args) throws Throwable {

		//getting data from excelsheet
		FileInputStream fileInputStream = new FileInputStream("./src/test/resources/data/InputData.xlsx");
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		String value = workbook.getSheet("Assignment").getRow(0).getCell(0).getStringCellValue();
		System.out.println(value);
	}

}
