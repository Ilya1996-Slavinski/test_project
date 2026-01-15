package сom.ui;
import org.testng.annotations.Test;
import ui.pages.MainPage;
import сom.DataProviders;
import сom.ui.driverInitialization.BrowserTypes;
import сom.ui.driverInitialization.DriverFactory;

public class FirstTest extends BaseUiTest {

    @Test(dataProvider = "browserProviders", dataProviderClass = DataProviders.class, groups = "grid")
    public void seleniumGridTest(BrowserTypes browserTypes) {
        MainPage mainPage = new MainPage(DriverFactory.driver.get());
        mainPage.clickOnButton();
    }

    @Test
    public void chromeTest() {
        MainPage mainPage = new MainPage(DriverFactory.driver.get());
        mainPage.clickOnButton();
    }
}


