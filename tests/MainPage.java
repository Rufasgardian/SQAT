package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageBase {
    @FindBy(linkText = "Login")
    private WebElement loginLink;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickLogin() {
        loginLink.click();
    }
}
