package lab11zad3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;



public class TestZad3 {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUpDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
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
        //Tytuł:  titleContains(String title)
        wait.until(ExpectedConditions.titleContains("My Store"));
        //Obecność elementu	:  presenceOfElementLocated(By locator)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_query_top")));
        driver.findElement(By.id("search_query_top")).sendKeys("shoes");
        //Wartość elementu:  textToBePresentInElementValue(By locator, String text)
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("search_query_top"), "shoes"));
        //Element jest widoczny i aktywny:  elementToBeClickable(By locator)
        wait.until(ExpectedConditions.elementToBeClickable(By.name("submit_search")));
        driver.findElement(By.name("submit_search")).click();
        String expectedUrl="http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query=shoes&submit_search=";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
        driver.findElement(By.cssSelector("#selectProductSort")).click();
        //Element jest zaznaczony:  elementToBeSelected(WebElement element)
        wait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("#selectProductSort > option:nth-child(1)")));
    }

    @Test
    public void bestsellersClick() {
        driver.findElement(By.xpath("//a[@class='blockbestsellers']")).click();
    }

    @Test
    public void goToFacebook() {
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("#social_block > h4")),"Follow us"));
        driver.findElement(By.cssSelector("#social_block > ul > li.facebook > a")).click();
        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs .get(1));
        String expectedUrl="https://www.facebook.com/groups/525066904174158/";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }
}