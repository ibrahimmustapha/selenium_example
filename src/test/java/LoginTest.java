import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @Test
    public void Login() {
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
        WebDriver driver = new ChromeDriver();

        // Run this url
        driver.get("https://twitterxclone.netlify.app/");

        // find element with text 'Continue with Google'
        WebElement googleSignIn = driver.findElement(By.partialLinkText("Continue with Google"));
        googleSignIn.click();

        // find username and password input
        WebElement username = driver.findElement(By.id("identifierId"));
        username.sendKeys("musib581@gmail.com");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Mustapha03");

        // click next button
        WebElement nextButton = driver.findElement(By.className("VfPpkd-RLmnJb"));
        nextButton.click();

        // check for login success or failure
        WebElement loginStatus = driver.findElement(By.linkText("Home"));
        if (loginStatus.isDisplayed()) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login Failed!");
        }


    }
}
