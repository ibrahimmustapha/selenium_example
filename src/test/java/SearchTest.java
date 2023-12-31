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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTest {

    private WebDriver driver;

    @Before
    public void startBrowser() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.ebay.com/");
    }

    @Test
    public void searchItem() {

        // find search input and search for nike 'Airforce'
        WebElement searchInput = driver.findElement(By.id("gh-ac"));
        searchInput.sendKeys("Airforce");
        searchInput.submit();

        List<WebElement> sneakers = driver.findElements(By.partialLinkText("Air Force"));
        for (WebElement sneaker: sneakers) {
            System.out.println(sneaker.getText());
        }
        Assert.assertTrue("Found Nike Air Force ", sneakers != null);
    }

    @After
    public void closeBrowser() {
        if (driver != null)
            driver.close();
    }
}
