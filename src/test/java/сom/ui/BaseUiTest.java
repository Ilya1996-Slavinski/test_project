package сom.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import сom.ui.driverInitialization.BrowserTypes;
import сom.ui.driverInitialization.DriverFactory;
import сom.ui.driverInitialization.RunMode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;


public abstract class BaseUiTest {
    WebDriverWait wait;
    static Properties config;
    Boolean isGridTest;

    static {
        loadConfiguration();
    }

    @BeforeMethod(alwaysRun = true)
    public void baseSetUp(Method method, Object[] data) throws Exception {
        Test gridAnnotation = method.getAnnotation(Test.class);
        isGridTest = false;
        if (gridAnnotation != null) {
            for (String group : gridAnnotation.groups()) {
                if (group.equals("grid")) {
                    isGridTest = true;
                    break;
                }
            }
        }
        if (isGridTest) {
            BrowserTypes firstBrowser = (BrowserTypes) data[0];
            DriverFactory.getInstanceOfDriver(firstBrowser, RunMode.GRID);
        } else {
            DriverFactory.getInstanceOfDriver(BrowserTypes.CHROME, RunMode.LOCAL);
        }
        commonBrowserConfig();
    }


    public void commonBrowserConfig() {
        WebDriver currentDriver = DriverFactory.driver.get();
        currentDriver.manage().window().maximize();
        wait = new WebDriverWait(currentDriver, Duration.ofSeconds(10));
        currentDriver.get(config.getProperty("ui.base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method) {
        DriverFactory.quitWebDriver();
    }


    public static void loadConfiguration() {
        config = new Properties();
        try (InputStream input = BaseUiTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                config.load(input);
            } else {
                throw new IOException("Config file has not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("Loading of config is failed");
        }
    }
}

