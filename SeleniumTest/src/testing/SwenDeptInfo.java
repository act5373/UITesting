package testing;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Name
Email
Title
Department
College
Address for the Golisano College of Computing and Information Sciences
 */
public class SwenDeptInfo
{
	private WebDriver driver;

	@BeforeEach
	void setUp() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver"); // can be replaced with Firefox driver
		driver = new ChromeDriver(); // can be replaced with Firefox
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://www.rit.edu/");
		driver.findElement(By.xpath("//*[@id=\"main-nav--link--academics\"]")).click();
		driver.findElement(By.xpath("/html/body/div[4]/header[2]/section[3]/nav/div/ul/li[2]/div/div/ul[1]/li[5]/a")).click();
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div[3]/div[3]/div/div/div/div/div/div/div/div/ul/li[3]/a")).click();
		driver.findElement(By.xpath("/html/body/div[4]/header[2]/section[3]/nav/div/ul/li[7]/a")).click();
	}

	@AfterEach
	void tearDown()
	{
		driver.quit();
	}

	private void testID(int id)
	{
		driver.findElement(By.xpath("/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div[1]/div/div[" + id + "]/div[1]/p/a")).click();
		
		print("Name", "/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div[1]/div/div[" + id + "]/div[2]/div/div[1]/article/div/div[2]/div[1]/a");
		
		print("Position", "/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div[1]/div/div[" + id + "]/div[2]/div/div[1]/article/div/div[2]/div[2]");

		print("Department", "/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div[1]/div/div[" + id + "]/div[2]/div/div[1]/article/div/div[2]/div[3]");
		
		print("College", "/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div[1]/div/div[" + id + "]/div[2]/div/div[1]/article/div/div[2]/div[4]");
		
		print("Contact", "/html/body/div[3]/main/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div/div/div[2]/div/div[1]/div/div[" + id + "]/div[2]/div/div[1]/article/div/div[3]/div[1]/a");
		
		print("Address", "/html/body/div[3]/footer/section[1]/div/section[1]/div[2]/div/div[1]/p[2]");
		
		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("\n\n");
	}
	
	@Test
	public void testSE()
	{
		testID(7);
	}
	
	@Test
	public void testCS()
	{
		testID(2);
	}
	
	@Test
	public void testInfo()
	{
		testID(5);
	}
	
	private void print(String desc, String xpath)
	{
		try
		{
			System.out.println(desc + ": " + driver.findElement(By.xpath(xpath)).getText());
		}
		catch(NoSuchElementException e)
		{
			System.out.println(desc + " information not found!");
		}
	}
}
