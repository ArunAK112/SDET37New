package com.lexnod.genericLib;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author ARUN
 *
 */

public class ExcelFileLibrary {
	/**
	 * To get the data from the excel file
	 * @param excelPath
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws Throwable
	 */
	
	public String getExcelData(String sheetName, int row, int cell) throws Throwable 
	{
		FileInputStream fileInputStream = new FileInputStream(IAutoConstants.EXCEL_PATH);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		DataFormatter format = new DataFormatter();
		Cell result = workbook.getSheet(sheetName).getRow(row).getCell(cell);
		return format.formatCellValue(result);
	}
	
	/**
	 * To insert a data into excel
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @param data
	 * @throws Throwable
	 */
	
	public void writeExcelData(String sheetName, int row, int cell,String data) throws Throwable
	{
		FileInputStream fileInputStream = new FileInputStream(IAutoConstants.EXCEL_PATH);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		workbook.getSheet(sheetName).getRow(row).createCell(cell).setCellValue(data);
		
		FileOutputStream fileOutputStream = new FileOutputStream(IAutoConstants.EXCEL_PATH);
		workbook.write(fileOutputStream);
	}
	
	/**
	 * to get the last row number
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public int getLastRowNumFromExcel(String sheetName) throws Throwable
	{
		Workbook workbook = WorkbookFactory.create(new FileInputStream(IAutoConstants.EXCEL_PATH));
		return workbook.getSheet(sheetName).getLastRowNum();
	}

}
