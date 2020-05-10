package lab11zad4;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


@ExtendWith(SeleniumExtension.class)
public class TestZad4 {
    private static WebDriver driver;

    public TestZad4(ChromeDriver driver)
    {
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);
    }


    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }


//pageLoadTimeout() - ograniczenie czasu na załadowanie strony
//setScriptTimeout() - ograniczenie czasu na wykonanie asynchronicznego skryptu zanim wyrzucony będzie error;
// jeśli czas będzie ujemny, skrypt będzie mógł działać w nieskończoność

    @Test
    public void longPageLoad(){
        Assertions.assertThrows(TimeoutException.class,
                () -> driver.navigate().to("https://worlds-highest-website.com/"));
    }

    @Test
    public void longScript() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        Assertions.assertThrows(ScriptTimeoutException.class,
                () -> executor.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);"));
    }
}