package сom.ui.driverInitialization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;


public class DriverFactory {

   public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void getInstanceOfDriver(BrowserTypes browser, RunMode mode) throws Exception {
        WebDriver rawDriver;
        URL gridUrl = new URL("http://localhost:4444");
        rawDriver = switch (browser) {
            case CHROME -> {
                ChromeOptions options = ChromeDriverManager.getOptions();
                yield (mode == RunMode.GRID) ? new RemoteWebDriver(gridUrl, options) : new ChromeDriverManager().createDriver();
            }
            case EDGE -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                yield (mode == RunMode.GRID) ? new RemoteWebDriver(gridUrl, edgeOptions) : new EdgeDriverManager().createDriver();
            }
            case FIREFOX -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                yield (mode == RunMode.GRID) ? new RemoteWebDriver(gridUrl, firefoxOptions) : new FirefoxDriverManager().createDriver();
            }
        };
        driver.set(rawDriver);
        driver.get();

    }

    public static void quitWebDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}