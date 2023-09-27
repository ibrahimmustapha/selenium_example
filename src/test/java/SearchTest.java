import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SearchTest {

    private WebDriver driver;

    @Before
    public void startBrowser() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
        driver.manage().window().maximize();
    }

    @Test
    public void searchItem() {
        driver.get("https://www.ebay.com/");

        // find search input and search for nike 'Airforce'
        WebElement searchInput = driver.findElement(By.id("gh-ac"));
        searchInput.sendKeys("Airforce");

        WebElement searchButton = driver.findElement(By.id("gh-btn"));
        searchButton.click();

        List<WebElement> sneakers = driver.findElements(By.partialLinkText("Air Force"));
        for (WebElement sneaker: sneakers) {
            System.out.println(sneaker.getText());
        }

    }
//
//    @After
//    public void closeBrowser() {
//        if (driver != null)
//            driver.close();
//    }
}
