package dataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lexnod.genericLib.ExcelFileLibrary;

public class FetchDataFromExcelTest extends ExcelFileLibrary{
	@Test(dataProvider = "fetchDataFromExcel")
	public void fetchDataFromExcel(String src, String dest, String Price)
	{
		System.out.println("From - "+src+" To - "+dest+" TicketPrice - "+Price);
	}

	@DataProvider
	public Object[][] fetchDataFromExcel() throws Throwable
	{
			Object[][] objArr=new Object[3][3];
			
			objArr[0][0]=getExcelData("DataProvider", 0, 0);
			objArr[0][1]=getExcelData("DataProvider", 0, 1);
			objArr[0][2]=getExcelData("DataProvider", 0, 2);
			objArr[1][0]=getExcelData("DataProvider", 1, 0);
			objArr[1][1]=getExcelData("DataProvider", 1, 1);
			objArr[1][2]=getExcelData("DataProvider", 1, 2);
			objArr[2][0]=getExcelData("DataProvider", 2, 0);
			objArr[2][1]=getExcelData("DataProvider", 2, 1);
			objArr[2][2]=getExcelData("DataProvider", 2, 2);
			return objArr;
			
	}
}
