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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
			"/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/form/div/ng2-completer/div/input")));
		searchBar.sendKeys("SWEN 352");

		Select formChange = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
			"#hideTerm > div:nth-child(1) > select:nth-child(1)"))));

		formChange.selectByValue("1: 0");

		driver.findElement(By.xpath(
			"/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/form/div/button"))
			.click();
		
		WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/div[4]/div[5]")));
		
		File f = new File("screenshot.png");
		f.createNewFile();
		Files.copy(table.getScreenshotAs(OutputType.FILE), f);
		// /html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/div[4]/div[5]
	}

}
