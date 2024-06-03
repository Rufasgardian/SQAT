import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;  

import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;


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
        LoginPage.login("r2k", "?*N-7$z2*WZk9Hh");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement verificationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ml-2.text-green")));

        Assert.assertTrue(verificationElement.isDisplayed());

    }
    
    @Test(dependsOnMethods = "testLogin")
    public void testLogoutAndDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement userDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".dropdown-toggle")));
        userDropdown.click();

        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nav-item.dropdown-item")));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button should be displayed.");

        logoutButton.click();

        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));
        Assert.assertTrue(loginLink.isDisplayed(), "Login link should be displayed after logout.");
    }



    

    @Test(dependsOnMethods = "testLogin")
    public void testFillTextarea() {
        driver.get("https://play.picoctf.org/practice");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name-search")));
        textArea.sendKeys("Test search content");

        Assert.assertEquals(textArea.getAttribute("value"), "Test search content", "Textarea content should match the input.");
    }

    @Test(dependsOnMethods = "testLogin")
    public void testComplexXPath() {

        driver.get("https://play.picoctf.org/playlists");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement complexElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//a[@href='/playlists/2']")));
        Assert.assertTrue(complexElement.isDisplayed());

        complexElement.click();

        wait.until(ExpectedConditions.urlToBe("https://play.picoctf.org/playlists/2"));
        String expectedUrl = "https://play.picoctf.org/playlists/2";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "The URL should match after clicking the complex XPath element.");
    }



     @Test
    public void testPageTitle() {
        driver.get("https://picoctf.org");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("picoCTF - CMU Cybersecurity Competition"));

        String pageTitle = driver.getTitle();

        Assert.assertEquals(pageTitle, "picoCTF", "The page title should match the expected title.");
    }

    @Test
    public void testHoverOverAboutPicoCTFButton() {
        driver.get("https://picoctf.org");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement aboutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.btn.btn-primary.mt-2[href='about.html']")));

        Actions actions = new Actions(driver);
        actions.moveToElement(aboutButton).perform();

        String buttonColor = aboutButton.getCssValue("color");
        System.out.println("Button color on hover: " + buttonColor); 

    }


     @Test
    public void testBrowserBackButton() {

        driver.get("https://picoctf.org");

        WebElement aboutButton = driver.findElement(By.cssSelector("a.btn.btn-primary.mt-2[href='about.html']"));
        aboutButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("picoCTF - About picoCTF"));

        String aboutPageTitle = driver.getTitle();
        Assert.assertEquals(aboutPageTitle, "picoCTF - About picoCTF");

        driver.navigate().back();

        wait.until(ExpectedConditions.titleIs("picoCTF - CMU Cybersecurity Competition"));

        String homePageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle, "picoCTF - CMU Cybersecurity Competition");
    }


    @Test
    public void testManipulateCookieToAvoidConsentPopup() {
        driver.get("https://picoctf.org");

        Cookie consentCookie = new Cookie("consent_given", "true");
        driver.manage().addCookie(consentCookie);

        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isConsentPopupPresent = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("consent-popup")));
        Assert.assertTrue(isConsentPopupPresent);
    }


    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
