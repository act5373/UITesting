package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class TigerCenterTest2
{
	private WebDriver driver;
	private String baseUrl;

	// .classSearchBasicResultsMargin

	@BeforeEach
	void setUp() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver"); // can be replaced with Firefox driver
		driver = new ChromeDriver(); // can be replaced with Firefox
		baseUrl = "https://"; // TARGET URL
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterEach
	void tearDown()
	{
		driver.quit();
	}

	@Test
	public void testClassSearchButton() throws Exception
	{
		driver.get(baseUrl + "tigercenter.rit.edu/tigerCenterApp/landing");
		WebElement classButton = driver.findElement(By.xpath(
			"//*[@id=\"angularApp\"]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/landing-page/div/div/div/div/div[4]/a[1]"));
		assertEquals("Class Search", classButton.getText());
		classButton.click();

		WebElement searchBar = driver.findElement(By.xpath(
			"/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/form/div/ng2-completer/div/input"));
		searchBar.sendKeys("SWEN 352");

		Thread.sleep(1000);
		WebElement elem = driver.findElement(By.xpath(
			"/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/form/div/div[3]/div/select"));
		
		elem.click();
		
		System.out.println(elem);
		System.out.println(elem.getText());
		
		File f = elem.getScreenshotAs(OutputType.FILE);
		
//		.selectByValue("1: 0");

		driver.findElement(By.xpath(
			"/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/form/div/button"))
			.click();

//		File f = driver.findElement(By.name("body")).getScreenshotAs(OutputType.FILE);
//
//		Files.copy(f, new File("xd.png"));

		// /html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/div[4]/div[5]

	}

}
