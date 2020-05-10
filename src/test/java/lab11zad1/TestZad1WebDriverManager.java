package lab11zad1;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestZad1WebDriverManager {
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

    @Test
    public void searchForShoes() {
        driver.findElement(By.id("search_query_top")).sendKeys("shoes");
        driver.findElement(By.id("search_query_top")).submit();
        String expectedUrl="http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=shoes";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void bestsellersClick() {
        driver.findElement(By.xpath("//a[@class='blockbestsellers']")).click();
    }

    @Test
    public void goToFacebook() {
        driver.findElement(By.cssSelector("#social_block > ul > li.facebook > a")).click();
        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs .get(1));
        String expectedUrl="https://www.facebook.com/groups/525066904174158/";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }
}