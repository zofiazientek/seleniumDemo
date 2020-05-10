package lab10zad2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestZad2 {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver() {
        System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
        driver = new FirefoxDriver();
        // Implicity wait -> max czas na znalezienie elementu na stronie
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://google.com/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void firstAndThirdLink() {
        driver.findElement(By.name("q")).sendKeys("java");
        driver.findElement(By.name("q")).submit();
        //driver.findElement(By.cssSelector("div div.rc div.r a")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("div div.rc div.r a"));
        WebElement firstPage = elements.get(0);
        firstPage.click();
        driver.navigate().back();
        driver.findElement(By.cssSelector("#rso > div:nth-child(3) > div > div.r > a"));
        driver.navigate().back();
    }

    @Test
    public void logoClick() {
        driver.findElement(By.id("hplogo")).click();
    }

    @Test
    public void noElementTest() {
        driver.findElement(By.linkText("costam")).click();
        // org.openqa.selenium.NoSuchElementException: Unable to locate element: *[name='costam']
    }

}