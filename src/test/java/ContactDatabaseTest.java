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
        login("kev12@gmail.com", "00000000");

        for (int i = 0; i < 10; i++) {
            // add new contact
            addNewContact("Ayisha", "Mustapha", "1997-12-22", "ayishamustapha" + i + "@gmail.com", "0249385444" + i,
                    "Kasoa" + i, "Ashonmang" + i, "Greater Accra" + i, "2441" + i, "Ghana" + i);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // list all contact
        List<WebElement> contact = driver.findElements(By.className("contactTableBodyRow"));
        contact.forEach((c) -> {
            System.out.println(c.getText());
        });

        Assert.assertTrue("contact should be added to table", contact != null);
    }

    private void addNewContact(String firstName, String lastName, String dob, String contactEmail, String phoneNumber,
                              String street1, String street2, String state, String postalCode, String country) {
        WebElement addContactButton = driver.findElement(By.id("add-contact"));
        addContactButton.click();

        WebElement firstNameElement = driver.findElement(By.id("firstName"));
        WebElement lastNameElement = driver.findElement(By.id("lastName"));
        WebElement dateOfBirth = driver.findElement(By.id("birthdate"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement phone = driver.findElement(By.id("phone"));
        WebElement firstAddress = driver.findElement(By.id("street1"));
        WebElement secondAddress = driver.findElement(By.id("street2"));
        WebElement stateElement = driver.findElement(By.id("stateProvince"));
        WebElement postalCodeElement = driver.findElement(By.id("postalCode"));
        WebElement countryElement = driver.findElement(By.id("country"));

        firstNameElement.sendKeys(firstName);
        lastNameElement.sendKeys(lastName);
        dateOfBirth.sendKeys(dob);
        email.sendKeys(contactEmail);
        phone.sendKeys(phoneNumber);
        firstAddress.sendKeys(street1);
        secondAddress.sendKeys(street2);
        stateElement.sendKeys(state);
        postalCodeElement.sendKeys(postalCode);
        countryElement.sendKeys(country);

        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();
    }


    private void login (String userEmail, String userPassword) {
        WebElement username = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        username.sendKeys(userEmail);
        password.sendKeys(userPassword);
        submitButton.click();
    }

    @After
    public void closeBrowser() {
        if (driver != null)
            driver.close();
    }
}
