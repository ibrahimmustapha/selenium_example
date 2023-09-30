import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginFailureTest {

    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void startBrowser () {
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    @Test
    public void loginFailure() {
        // find email input and enter Admin as email
        WebElement email = driver.findElement(By.name("username"));
        email.sendKeys("Admin");

        // find password input and enter 12345 as password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("12345");

        // click on login button
        WebElement loginButton = driver.findElement(By.className("oxd-button"));
        loginButton.click();

        // check for auth error message
        WebElement errorMessage = driver.findElement(By.className("oxd-alert-content--error"));
        System.out.println(errorMessage.getText());

        Assert.assertTrue( "error message be displayed", errorMessage.isDisplayed());
    }

    @After
    public void closeBrowser() {
        if (driver != null)
            driver.close();
    }
}
