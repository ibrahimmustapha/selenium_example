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
import java.util.Random;

public class ContactDatabaseTest {

    private WebDriver driver;

    @Before
    public void startBrowser() {
        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.LINUX).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://thinking-tester-contact-list.herokuapp.com/");
    }

    @Test
    public void addContact() {
        // login user
        WebElement username = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        username.sendKeys("kev12@gmail.com");
        password.sendKeys("00000000");
        submitButton.click();

        for (int i = 0; i < 10; i++) {

            // add contact
            WebElement addContactButton = driver.findElement(By.id("add-contact"));
            addContactButton.click();

            // add contact details
            WebElement firstName = driver.findElement(By.id("firstName"));
            WebElement lastName = driver.findElement(By.id("lastName"));
            WebElement dateOfBirth = driver.findElement(By.id("birthdate"));
            WebElement email = driver.findElement(By.id("email"));
            WebElement phone = driver.findElement(By.id("phone"));
            WebElement firstAddress = driver.findElement(By.id("street1"));
            WebElement secondAddress = driver.findElement(By.id("street2"));
            WebElement city = driver.findElement(By.id("city"));
            WebElement state = driver.findElement(By.id("stateProvince"));
            WebElement postalCode = driver.findElement(By.id("postalCode"));
            WebElement country = driver.findElement(By.id("country"));

            // Random number 0 - 100
            Random rand = new Random();
            int randomNumber = rand.nextInt(100);

            firstName.sendKeys("Ayisha" + randomNumber);
            lastName.sendKeys("Mustapha" + randomNumber);
            dateOfBirth.sendKeys("1997-12-22");
            email.sendKeys("ayishamustapha" + randomNumber + "@gmail.com");
            phone.sendKeys("0249385444" + randomNumber);
            firstAddress.sendKeys("Kasoa" + randomNumber);
            secondAddress.sendKeys("Ashonmang" + randomNumber);
            city.sendKeys("Accra");
            state.sendKeys("Greater Accra" + randomNumber);
            postalCode.sendKeys("33442345" + randomNumber);
            country.sendKeys("Ghana" + randomNumber);

            // submit contact form
            WebElement submit = driver.findElement(By.id("submit"));
            submit.click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        List<WebElement> contact = driver.findElements(By.className("contactTableBodyRow"));
        contact.forEach((c) -> {
            System.out.println(c.getText());
        });

        Assert.assertTrue("contact should be added to table", contact != null);
    }

    @After
    public void closeBrowser() {
        if (driver != null)
            driver.close();
    }
}
