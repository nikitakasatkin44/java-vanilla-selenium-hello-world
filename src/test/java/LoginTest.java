import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();
    }

    @Test
    public void loginTest() {
        driver.get("https://test-online.sbis.ru/auth/");

        String title = driver.getTitle();
        assertEquals("Вход в личный кабинет", title);

        WebElement loginInput = driver.findElement(By.name("Login"));
        WebElement passInput = driver.findElement(By.name("Password"));
        WebElement submitButton1 = driver.findElement(By.cssSelector("[data-qa=\"auth-AdaptiveLoginForm__checkSignInTypeButton\"]"));
//        WebElement submitButton2 = driver.findElement(By.cssSelector("[data-qa=\"auth-AdaptiveLoginForm__signInButton\"]"));

        loginInput.sendKeys("salary_smoke");
        submitButton1.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitButton2 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-qa=\"auth-AdaptiveLoginForm__signInButton\"]")));

        passInput.sendKeys("salary_smoke123");
        submitButton2.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}
