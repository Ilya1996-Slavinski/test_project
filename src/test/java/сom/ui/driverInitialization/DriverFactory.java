package сom.ui.driverInitialization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

public class DriverFactory {

    private static final Map<String, Supplier<WebDriverManagers>> mapOfDrivers = new HashMap<>();
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    static Properties config;

    static {
        mapOfDrivers.put(BrowserTypes.CHROME.name(), ChromeDriverManager::new);
        mapOfDrivers.put(BrowserTypes.EDGE.name(), EdgeDriverManager::new);
        mapOfDrivers.put(BrowserTypes.FIREFOX.name(), FirefoxDriverManager::new);
    }

    public static WebDriver createInstanceOfDriver(BrowserTypes browser) throws Exception {

        if (driver.get() == null) {
                Supplier<WebDriverManagers> driverSupplier = mapOfDrivers.get(browser.name());
                if (driverSupplier == null) {
                    throw new IllegalArgumentException("No driver implementation for: " + browser);
                }
                WebDriverManagers driverManager = driverSupplier.get();
                driver.set(driverManager.createDriver());

            }

        return driver.get();
    }

    public static WebDriver getInstanceOfDriver(BrowserTypes browser, RunMode mode) throws Exception {
        URL gridUrl = new URL("http://localhost:4444");
        return switch (browser) {
            case CHROME -> {
                ChromeOptions options = ChromeDriverManager.getOptions();

                yield (mode == RunMode.GRID) ? new RemoteWebDriver(gridUrl, options) :createInstanceOfDriver(BrowserTypes.CHROME);
            }
            case EDGE -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                yield (mode == RunMode.GRID) ? new RemoteWebDriver(gridUrl, edgeOptions) : createInstanceOfDriver(BrowserTypes.EDGE);
            }
            case FIREFOX -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                yield (mode == RunMode.GRID) ? new RemoteWebDriver(gridUrl, firefoxOptions) : createInstanceOfDriver(BrowserTypes.FIREFOX);
            }
        };
    }

    public static void quitWebDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}