package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtilities {
	public Workbook wb;

	/**
	 * this method is used to fetch the data from excel file
	 * 
	 * @param sheetname
	 * @param rowindexing
	 * @param cellindexing
	 * @return String
	 * @throws Exception
	 * @throws IOException
	 */
	public String FetchDataFromExcelFile(String sheetname, int rowindexing, int cellindexing)
			throws Exception, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/vtiger_testData.xlsx");

		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row r = sh.getRow(rowindexing);
		Cell c = r.getCell(cellindexing);
		String data = c.toString();

		return data;

	}

	/**
	 * this method is used to write back data to excel file in new row/ new cell
	 * 
	 * @param sheetname
	 * @param rowindexing
	 * @param cellindexing
	 * @param data
	 * @throws Exception
	 */
	public void WrightBackTheDataToExcel(String sheetname, int rowindexing, int cellindexing, String data)
			throws Exception {

		FileInputStream fis = new FileInputStream("./src/test/resources/vtiger_testData.xlsx");
		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row r = sh.createRow(rowindexing);
		Cell c = r.createCell(cellindexing);
		c.setCellValue(data);

		FileOutputStream fos = new FileOutputStream("./src/test/resources/vtiger_testData.xlsx");
		wb.write(fos);

	}
/**
 * this method is used to close the workbook
 * @throws IOException
 */
	public void CloseTheWorkBook() throws IOException {
		wb.close();
	}
	/**
	 *  this method is used to fetch multipul data from excel file
	 * @param sheetname
	 * @return String
	 * @throws Exception
	 */

	public String FetchMultipleDtaFromExcelFile(String sheetname) throws Exception {
		FileInputStream fis = new FileInputStream("./src/test/resources/vtiger_testData.xlsx");
		wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);

		String data = null;
		for (int i = 0; i < sh.getFirstRowNum(); i++) {
			for (int j = 0; j < sh.getRow(i).getLastCellNum(); j++) {
				data=sh.getRow(i).getCell(j).toString();
			}

		}
		return data;
	}

}