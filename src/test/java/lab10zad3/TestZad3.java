package lab10zad3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestZad3 {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
        driver = new FirefoxDriver();
        // Implicity wait -> max czas na znalezienie elementu na stronie
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("http://demo.guru99.com/test/guru99home/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void numberOfLinks() {
        List<WebElement> elements = driver.findElements(By.xpath("//a"));
        System.out.println("Links: "+elements.size());
    }

    @Test
    public void numberOfImages() {
        List<WebElement> images = driver.findElements(By.xpath("//img"));
        System.out.println("Images: "+images.size());
    }

    @Test
    public void enterLinks() throws Exception {
        Thread.sleep(5000);


        List<WebElement> links = driver.findElements((By.xpath("//a")));
        System.out.println(links.size());
        List linksHref = new ArrayList();
        for(int i=0;i<links.size();i++)
        {

            linksHref.add(links.get(i).getAttribute("href"));
            //System.out.println(links.get(i).getAttribute("href") saved on list);
        }
        for(int j=0;j<links.size();j++)
        {
            String page = linksHref.get(j).toString();
            driver.get(page);
            driver.navigate().back();
            Thread.sleep(3000);
            //System.out.println(page + " visited! going back");
        }
    }

    @Test
    public void numberOfFields() {
        List<WebElement> fields = driver.findElements(By.xpath("//form/input"));
        System.out.println(fields.size());
    }
}
