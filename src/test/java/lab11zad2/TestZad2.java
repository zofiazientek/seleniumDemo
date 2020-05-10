package lab11zad2;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestZad2 {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("http://automationpractice.com/index.php");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    private boolean isElementPresent(By by)
    {
        try
        {
            driver.findElement(by);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Test
    public void isSearchFieldPresent() {
        Assertions.assertTrue(isElementPresent(By.id("search_query_top"))); }

    @Test
    public void isBestsellersButtonPresent() {
        Assertions.assertTrue(isElementPresent(By.xpath("//a[@class='blockbestsellers']"))); }

    @Test
    public void isBlockTopPresent() {
        Assertions.assertFalse(isElementPresent(By.id("topblock")));}

    @Test
    public void isFacebookButtonPresent() {
        Assertions.assertTrue(isElementPresent(By.cssSelector("#social_block > ul > li.facebook > a")));
    }
}