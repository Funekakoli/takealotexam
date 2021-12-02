package frameWorkClasses;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel extends BasePage{
	public Object[][] excelDP(String pProperty,String pfilename,String pSheet) throws IOException{

        //We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
		// Get the directory where the excel file is placed
		String excelDirectory = getDataConfigPropeties(pProperty);
        Object[][] arrObj = getExcelData(excelDirectory+pfilename,pSheet);

        return arrObj;

}

 
 public String[][] getExcelData(String fileName, String sheetName){
         String[][] data = null;     
try
     {
	   /*Java FileInputStream class obtains input bytes from a file. 
	    * It is used for reading byte-oriented data (streams of raw bytes) such as image data, 
	    * audio, video etc. You can also read character-stream data. But, f
	    * or reading streams of characters, it is recommended to use FileReader class.
	    */
     FileInputStream fis = new FileInputStream(fileName);
     /*High level representation of a SpreadsheetML workbook. 
      * This is the first object most users will construct whether 
      * they are reading or writing a workbook. 
      * It is also the top level object for creating new sheets/etc.
      */
     XSSFWorkbook wb = new XSSFWorkbook(fis);
     XSSFSheet sh = wb.getSheet(sheetName);
     DataFormatter formatter = new DataFormatter();
     XSSFRow row = sh.getRow(0);
     int noOfRows = sh.getPhysicalNumberOfRows();
     int noOfCols = row.getLastCellNum();
     //High level representation of a cell in a row of a spreadsheet.
 

     data = new String[noOfRows-1][noOfCols];
     for(int i =1; i<noOfRows ;i++){
    	 
          for(int j=0;j<noOfCols;j++){
                data[i-1][j] = formatter.formatCellValue( sh.getRow(i).getCell(j));
                System.out.println(data[i-1][j]);
        	  
              

            }

     }

     }

     catch (Exception e) {

            System.out.println("The exception is: " +e.getMessage());

         }

         return data;

 }
}
