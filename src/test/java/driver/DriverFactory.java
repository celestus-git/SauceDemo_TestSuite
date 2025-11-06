package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

public static WebDriver getDriver(BrowserType browserType){
    WebDriver driver;


    switch (browserType){
        case CHROME -> {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable notifications");
            chromeOptions.addArguments("--disable-popup-blocking");
            driver= new ChromeDriver();
        }
        case EDGE -> {
            WebDriverManager.edgedriver().setup();
            driver= new EdgeDriver();
        }
        case FIREFOX -> {
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
        }
        default -> throw new UnsupportedOperationException("The browser chosen "+ browserType +" is not supported");
    }
    driver.manage().window().maximize();
    return driver;
}
}
