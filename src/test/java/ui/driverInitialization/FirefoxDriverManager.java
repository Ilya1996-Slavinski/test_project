package ui.driverInitialization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ui.driverInitialization.WebDriverManagers;

public class FirefoxDriverManager implements WebDriverManagers {

    @Override
    public WebDriver createDriver() {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-notifications");

        // firefoxOptions.addArguments("--headless=new"); for CI/CD
        return new FirefoxDriver(firefoxOptions);

    }


}