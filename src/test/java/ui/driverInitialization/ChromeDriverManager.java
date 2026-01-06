package ui.driverInitialization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class ChromeDriverManager implements WebDriverManagers {
    WebDriver driver;

    @Override
    public WebDriver createDriver() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--remote-allow-origins=*");
        // chromeOptions.addArguments("--headless=new"); for CI/CD
        driver = new ChromeDriver(chromeOptions);
        return driver;   }
}
