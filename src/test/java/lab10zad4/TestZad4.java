package lab10zad4;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestZad4 {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        // Implicity wait -> max czas na znalezienie elementu na stronie
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/login");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void successfulLogin() {
        driver.findElement(By.name("txtUsername")).sendKeys("opensourcecms");
        driver.findElement(By.name("txtPassword")).sendKeys("opensourcecms");
        driver.findElement(By.name("Submit")).click();
        String expectedUrl="https://s2.demo.opensourcecms.com/orangehrm/index.php";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }

    @Test void wrongPassword() {
        driver.findElement(By.name("txtUsername")).sendKeys("opensourcecms");
        driver.findElement(By.name("txtPassword")).sendKeys("cms");
        driver.findElement(By.name("Submit")).click();
        String expectedUrl="https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/validateCredentials";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }

    @Test void wrongUser() {
        driver.findElement(By.name("txtUsername")).sendKeys("open");
        driver.findElement(By.name("txtPassword")).sendKeys("opensourcecms");
        driver.findElement(By.name("Submit")).click();
        String actualMessage = driver.findElement(By.id("spanMessage")).getText();
        String expectedMessage="Invalid credentials";
        Assert.assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void emptyPassword() {
        driver.findElement(By.name("txtUsername")).sendKeys("opensourcecms");
        driver.findElement(By.name("txtPassword")).sendKeys("");
        driver.findElement(By.name("Submit")).click();
        String actualMessage = driver.findElement(By.id("spanMessage")).getText();
        String expectedMessage="Password cannot be empty";
        Assert.assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void emptyUser() {
        driver.findElement(By.name("txtUsername")).sendKeys("");
        driver.findElement(By.name("txtPassword")).sendKeys("opensourcecms");
        driver.findElement(By.name("Submit")).click();
        String actualMessage = driver.findElement(By.id("spanMessage")).getText();
        String expectedMessage="Username cannot be empty";
        Assert.assertEquals(expectedMessage,actualMessage);
    }
}