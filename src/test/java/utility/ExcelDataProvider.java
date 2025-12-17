package utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

	@DataProvider(name = "loginData")
	public Object[][] dataProvider() throws IOException {

		String path = System.getProperty("user.dir") + "\\testData\\loginTestData.xlsx";

//		System.out.println("Excel path = " + path);
//		File f = new File(path);
//		System.out.println("Exists = " + f.exists());

		ExcelUtility util = new ExcelUtility(path);
		System.out.println("hello 1");
		int totalRows = util.getRowCount("Sheet1"); // last row index
		System.out.println("hello 2");
		int totalColumns = util.getCellCount("Sheet1", totalRows); // header row

		Object[][] data = new Object[totalRows][totalColumns];

		for (int r = 1; r <= totalRows; r++) { // start from row 1 (skip header)
			for (int c = 0; c < totalColumns; c++) {
				data[r - 1][c] = util.getCellData("Sheet1", r, c);
			}
		}

		return data;

	}

}
