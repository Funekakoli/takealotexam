package takealotproject;

	import java.io.IOException;

	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import frameWorkClasses.ReadExcel;

	public class ReadDataFromExcel {
		
		ReadExcel rExcel = new ReadExcel();
		
		@Test(dataProvider="Brand and Quantity")
		public void BrandQuantity(String brand, String quantity) {
			System.out.println(brand +  " " + quantity);
			
		}
		@DataProvider(name = "Brand and Quantity")
		public Object[][] getDataFromExcel() throws IOException{
			//retun rExcel.excellDP("excelDataDir", "ExcelData.xlsx", "Sheet");
			String excelDirectory = rExcel.getDataConfigPropeties("excelDataDir");
			Object[][] errObj = rExcel.getExcelData(excelDirectory +"BrandANDQuantity.xlsx", "Sheet1");
			return errObj;
		}

	}



