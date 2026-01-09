package сom.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import сom.ui.driverInitialization.BrowserTypes;
import сom.ui.driverInitialization.DriverFactory;
import сom.ui.driverInitialization.RunMode;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import static сom.ui.driverInitialization.DriverFactory.quitWebDriver;


public abstract class BaseUiTest {
    WebDriver driver;
    WebDriverWait wait;
    static Properties config;

    static {
        loadConfiguration();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = DriverFactory.getInstanceOfDriver(BrowserTypes.EDGE, RunMode.GRID);
        driver.manage().window().maximize();
      driver.get("https://automationintesting.online");
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//        String baseUrl = config.getProperty("ui.base.url");
//        if (baseUrl == null || baseUrl.isBlank()) {
//            throw new IllegalStateException("Configuration of URL is empty or null");
//        }
//        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        quitWebDriver();    }


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
