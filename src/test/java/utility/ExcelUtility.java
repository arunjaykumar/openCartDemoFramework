package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private FileInputStream fis;
	private FileOutputStream fos;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	private void loadFile() throws IOException {

		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);// problem
		System.out.println("Hi");
	}

	private void closeFile() throws IOException {
		workbook.close();
		fis.close();
	}

	// Get Row Count
	public int getRowCount(String sheetName) throws IOException {
		loadFile();
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		closeFile();
		return rowCount;
	}

	// Get Cell Count
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		loadFile();
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);

		int cellCount = (row == null) ? 0 : row.getLastCellNum();
		closeFile();
		return cellCount;
	}

	// Get Cell Data
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		loadFile();
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = (row == null) ? null : row.getCell(colNum);

		DataFormatter formatter = new DataFormatter();
		String data = formatter.formatCellValue(cell);

		closeFile();
		return data;
	}

	// Write Cell Data
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		loadFile();
		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}

		cell = row.getCell(colNum);
		if (cell == null) {
			cell = row.createCell(colNum);
		}

		cell.setCellValue(data);

		fos = new FileOutputStream(path);
		workbook.write(fos);

		workbook.close();
		fis.close();
		fos.close();
	}
}
