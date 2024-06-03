import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;  

import java.net.URL;
import java.net.MalformedURLException;


public class FirstSeleniumTest {
    public WebDriver driver;
    
    @Before
    public void setup()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);

        driver.get("https://play.picoctf.org/login");
    }

    @Test
    public void testLogin() {
            MainPage mainPage = new MainPage(driver);
            mainPage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("r2k", "?*N-7$z2*WZk9Hh");

             // Add assertion to verify email verification status
        WebElement verificationElement = driver.findElement(By.className("ml-2 text-green"));
        Assert.assertTrue(verificationElement.isDisplayed(), "Successfull login.");
        }
    

    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
