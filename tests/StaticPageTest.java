package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StaticPageTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @DataProvider(name = "staticPages")
    public Iterator<Object[]> staticPages() {
        List<String> pages = Arrays.asList(
                "https://picoctf.org/resources.html",
                "https://picoctf.org/index.html",
                "https://picoctf.org/get_started.html"
        );
        return pages.stream().map(url -> new Object[]{url}).iterator();
    }

    @Test(dataProvider = "staticPages")
    public void testStaticPages(String url) {
        driver.get(url);
        
        // Perform different checks based on the URL
        if (url.contains("resources.html")) {
            WebElement someElement = driver.findElement(By.cssSelector(".text-bluish"));
            Assert.assertTrue(someElement.isDisplayed());
        } else if (url.contains("index.html")) {
            WebElement someElement = driver.findElement(By.cssSelector(".category-sponsors"));
            Assert.assertTrue(someElement.isDisplayed());
        } else if (url.contains("get_started.html")) {
            WebElement someElement = driver.findElement(By.cssSelector(".step-trigger"));
            Assert.assertTrue(someElement.isDisplayed());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
