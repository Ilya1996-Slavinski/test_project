package сom;

import org.testng.annotations.DataProvider;
import сom.ui.driverInitialization.BrowserTypes;

public class DataProviders {
    @DataProvider(name = "browserProviders", parallel = true)
    public static Object[][] browserProviders(){
        return new Object[][]{
                {BrowserTypes.EDGE},
                {BrowserTypes.CHROME},
                {BrowserTypes.FIREFOX}
        };
    }
}
