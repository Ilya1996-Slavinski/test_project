package ui.driverInitialization;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import ui.driverInitialization.WebDriverManagers;

public class EdgeDriverManager implements WebDriverManagers {


    @Override
    public WebDriver createDriver() {

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--remote-allow-origins=*");
        // edgeOptions.addArguments("--headless=new"); for CI/CD
        return new EdgeDriver(edgeOptions);

    }

}