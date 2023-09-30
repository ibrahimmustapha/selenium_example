import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class LaunchBrowserTest {
    @Test
    public void simpleTest() {
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();

        // initiate a new object for chromedriver
        WebDriver driver = new ChromeDriver();

        // open the browser full max on the screen
        driver.manage().window().maximize();

        // run the url in the browser
        driver.get("https://www.ebay.com/");

        driver.findElement(By.id("gh-ac")).sendKeys("Hoodie men XL");
        driver.findElement(By.id("gh-btn")).click();

        System.out.println(driver.getCurrentUrl());
        driver.quit();
    }
}
