// import org.junit.*;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.openqa.selenium.support.ui.ExpectedConditions;

// import org.openqa.selenium.By;
// import org.openqa.selenium.NoSuchElementException;


// class PageBase {
//     protected WebDriver driver;
//     protected WebDriverWait wait;
    
    
//     public PageBase(WebDriver driver) {
//         this.driver = driver;
//         this.wait = new WebDriverWait(driver, 10);
//     }
    
//     protected WebElement waitAndReturnElement(By locator) {
//         this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//         return this.driver.findElement(locator);
//     } 
    
//     public String getBodyText() {
//         WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
//         return bodyElement.getText();
//     }
   
// }


package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageBase {
    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Common methods like wait, click, sendKeys, etc.
}
