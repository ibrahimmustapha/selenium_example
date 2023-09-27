import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void startBrowser() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
        driver.manage().window().maximize();
    }

    @Test
    public void login() {
        // Run this url
        driver.get("https://opensource-demo.orangehrmlive.com");

        // wait for the username element to become visible
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("Admin");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");

        // click next button
        WebElement nextButton = driver.findElement(By.className("oxd-button"));
        nextButton.click();

        // check for login success or failure
        WebElement loginStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Admin")));
        System.out.println("Login Status:: " + loginStatus);

        assertTrue(loginStatus.isDisplayed() == true, "Login status must be displayed");
    }

    @After
    public void closeBrowser() {
        if (driver != null)
            driver.close();
    }
}
