import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.logging.Logger;

public class SecondTestExample {

    @Test
    public void JijiExample() {
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ebay.com/");
        driver.findElement(By.id("gh-ac")).sendKeys("Macbook");
        driver.findElement(By.id("gh-btn")).click();
        List<WebElement> link = driver.findElements(By.partialLinkText("Macbook Pro"));
        for (WebElement l : link) {
            System.out.println(l.getText());
        }
        if (link != null)
            driver.close();
    }

    @Test
    public void selectorExample() {
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.com");
        driver.findElement(By.cssSelector("input#twotabsearchtextbox")).sendKeys("Macbook");
        driver.findElement(By.id("nav-search-submit-button")).click();
        List<WebElement> consoles = driver.findElements(By.partialLinkText("MacBook"));
        for (WebElement console : consoles) {
            System.out.println(console.getText());
        }
    }

    @Test
    public void searchBlog() {
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        WebElement searchInput = driver.findElement(By.id("APjFqb"));
        searchInput.sendKeys("Junit for beginners");
        searchInput.submit();
        WebElement link = driver.findElement(By.partialLinkText("JUnit Tutorial"));
        link.click();
        driver.getTitle();
    }
}
