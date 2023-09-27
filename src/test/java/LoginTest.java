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

        if (loginStatus.isDisplayed()) {
            System.out.println("Login successful");

            // click on the dropdown menu
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("oxd-userdropdown-tab")));
            dropdown.click();

            // find logout link and click logout
            WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
            logout.click();

            // check for success or failure of logout
            if (logout != null)
                System.out.println("Logout successful");
            else
                System.out.println("Logout failed");

        } else {
            System.out.println("Login Failed!");
        }
    }

    @After
    public void closeBrowser() {
        if (driver != null)
            driver.close();
    }
}
