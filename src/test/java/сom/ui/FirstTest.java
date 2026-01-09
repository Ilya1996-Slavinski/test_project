package сom.ui;

import org.testng.annotations.Test;
import ui.pages.MainPage;

public class FirstTest extends  BaseUiTest{

    @Test
    public void first(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnButton();
    }
}