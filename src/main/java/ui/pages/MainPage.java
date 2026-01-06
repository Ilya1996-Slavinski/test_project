package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BasePage {

    @FindBy(xpath = "//a[text()='Location']")
    WebElement locationButton;


    public MainPage(WebDriver driver){
        super(driver);

    }

    public MainPage clickOnButton() {
        clickOnButton(locationButton);
        return this;
    }
}