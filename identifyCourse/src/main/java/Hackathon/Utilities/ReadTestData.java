package Hackathon.Utilities;

import java.util.Hashtable;

public class ReadTestData {
	
	//public static void main(String args[]) throws Exception{
	public static Object[][] getTestData(String DataFileName, String SheetName, String TestName) {
		ReadExcelDataFile readdata = new ReadExcelDataFile(System.getProperty("user.dir")+"\\src\\main\\java\\testData\\courseTestData.xlsx");
		String sheetName = SheetName;
		String testName = TestName;

		//Find Start Row of TestCase
		int startRowNum = 0;
		while (!readdata.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
			startRowNum++;
		}
		//System.out.println("Test Starts from Row Number : " + startRowNum);
		
		int startTestColumn = startRowNum+1;
		int startTestRow = startRowNum+2;
		
		//Find Number of Rows of TestCase
		int rows = 0;
		while (!readdata.getCellData(sheetName, 0, startTestRow+rows).equals("")) {
			//System.out.println(readdata.getCellData(sheetName, 0, startTestRow++));
			rows++;
		}
		
		//System.out.println("Total Numbe of Rows in Test : " +testName + " is - " +rows);
		
		//Find Number of Columns in Test
		int colmns=0;
		while (!readdata.getCellData(sheetName, colmns, startTestColumn).equals("")) {
			colmns++;
		}
		//System.out.println("Total Number of Columns in Test : " +testName + " is - " +colmns);
		
		/*for (int rowNumber=startTestRow; rowNumber<startTestRow+rows; rowNumber++) {
			for (int colNumber=0; colNumber<colmns; colNumber++) {
				System.out.println(readdata.getCellData(sheetName, colNumber, rowNumber));
			}*/
		Object[][] dataSet = new Object[rows][1];
		Hashtable<String, String> dataTable = null;
		int dataRowNumber=0;
		for (int rowNumber = startTestRow; rowNumber < startTestRow + rows; rowNumber++) {
			dataTable = new Hashtable<String, String>();
			for (int colNumber = 0; colNumber < colmns; colNumber++) {
				String key = readdata.getCellData(sheetName, colNumber, startTestColumn);
				//System.out.println(key);
				String value = readdata.getCellData(sheetName, colNumber, rowNumber);
				//System.out.println(value);
				dataTable.put(key, value);
			
			}
			dataSet[dataRowNumber][0]=dataTable;
			System.out.println(dataSet[dataRowNumber][0]);
			dataRowNumber++;
		}

	 return dataSet;
			
				
	}
}
