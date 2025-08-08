package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Utility {

	public static WebDriver driver;  // remove static while performing parallel execution 
	public static Properties prop = new Properties();
	public static FileOutputStream output;
	public static SoftAssert assertObj = new SoftAssert();
	public String sheetname;
	public static ExtentReports extent;
	public String testName, testDescription, testAuthor, testCategory;
	public static ExtentTest test;
	public static Alert alert;
	
	public void launchBrowserAndLoadUrl(String browser, String url) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			
			driver= new ChromeDriver();
			
		} else if(browser.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();
			
		} else {
			
			driver = new ChromeDriver();
		}
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
	}
	
	public void browserClose() {
		
		driver.close();
	}
	
	public static void readAndWritePropFile() throws IOException {
		
		String filePath="F:\\JAVA PROGRAM\\DemoBlaze\\src\\test\\resources\\data\\testData.properties";
		FileInputStream file = new FileInputStream(filePath);
		prop.load(file);
		file.close();
		
	}
	
	public static Object[][] readExcel(String sheetname) throws IOException {
		// Open the workbook
				XSSFWorkbook book = new XSSFWorkbook("F:\\JAVA PROGRAM\\DemoBlaze\\src\\test\\resources\\data\\Entries.xlsx");
				
				// Open the sheet
				
				XSSFSheet sheet = book.getSheet(sheetname);
				
				int rowCount = sheet.getPhysicalNumberOfRows();
				int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
				
				Object[][] data = new Object[rowCount - 1][columnCount];
				
				for(int i=1; i<rowCount; i++) {
					
					XSSFRow row = sheet.getRow(i);
					
					for(int j=0; j<columnCount; j++) {
						
						 XSSFCell cell = row.getCell(j, XSSFRow.MissingCellPolicy.RETURN_BLANK_AS_NULL);

			                if (cell == null) {
			                    data[i - 1][j] = "";
			                } else {
			                    switch (cell.getCellType()) {
			                        case STRING:
			                            String value = cell.getStringCellValue().trim();
			                            data[i - 1][j] = value.equalsIgnoreCase("null") ? null : value;
			                            System.out.print(cell.getStringCellValue()+"|");
			                            break;
			                        case NUMERIC:
			                            data[i - 1][j] = cell.getNumericCellValue();
			                            System.out.print(cell.getNumericCellValue()+"|");
			                            break;
			                        case BOOLEAN:
			                            data[i - 1][j] = cell.getBooleanCellValue();
			                            System.out.print(cell.getBooleanCellValue()+"|");
			                            break;
			                        default:
			                            data[i - 1][j] = null;
			                            System.out.print(cell.getStringCellValue()+"|");
			                    }
						}	
			                
					}System.out.println();
				}
				book.close();
				return data;
			}

	public static void alter() {
		alert = driver.switchTo().alert();
	}
	public static String screenShot(String name) throws IOException {
		
		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		String path="F:\\JAVA PROGRAM\\DemoBlaze\\src\\test\\resources\\TestOutput\\snaps\\"+name+timestamp+".png";
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
		return path;
	}
	
	
}