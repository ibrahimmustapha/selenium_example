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

import java.time.Duration;

public class EcommercePurchase {
    private WebDriver driver;

    @Before
    public void startBrowser() {
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void purchaseItem() {
        // find username input and enter username
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        // find username input and enter username
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        // find login button and click
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // click on item
        WebElement backpack = driver.findElement(By.linkText("Sauce Labs Backpack"));
        backpack.click();

        // add to cart
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        // open cart
        WebElement cartLink = driver.findElement(By.className("shopping_cart_link"));
        cartLink.click();

        // checkout item
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

        // fill checkout form firstname, lastname  & zipcode
        WebElement checkoutFirstName = driver.findElement(By.id("first-name"));
        WebElement checkoutLastName = driver.findElement(By.id("last-name"));
        WebElement checkoutZipCode = driver.findElement(By.id("postal-code"));

        checkoutFirstName.sendKeys("John");
        checkoutLastName.sendKeys("Cina");
        checkoutZipCode.sendKeys("12345");

        // click continue
        WebElement checkoutContinue = driver.findElement(By.id("continue"));
        checkoutContinue.click();

        // click finish to complete payment
        WebElement finishPayment = driver.findElement(By.id("finish"));
        finishPayment.click();

        // find thank you header
        WebElement completeHeader = driver.findElement(By.className("complete-header"));

        Assert.assertTrue("Complete header must be displayed", completeHeader.isDisplayed());
    }

    @After
    public void closeBrowser() {
        if (driver != null)
            driver.close();
    }

}
