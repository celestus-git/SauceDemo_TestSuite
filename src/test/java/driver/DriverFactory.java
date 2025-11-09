package driver;

import Logger.LogManager;
import org.slf4j.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    protected static final Logger logger= LogManager.getLogger(DriverFactory.class);

public static WebDriver getDriver(BrowserType browserType){
logger.debug("Inicializando Webdriver para el navegador: {}",browserType.name());

    WebDriver driver;


    switch (browserType){
        case CHROME -> {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();

            chromeOptions.addArguments("--disable notifications");
            chromeOptions.addArguments("--disable-popup-blocking");

            driver= new ChromeDriver();

            logger.info("Chrome driver creado con éxito");
        }
        case EDGE -> {
            WebDriverManager.edgedriver().setup();
            driver= new EdgeDriver();
            logger.info("Edge driver creado con éxito");
        }
        case FIREFOX -> {
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
            logger.info("Firefox driver creado con éxito");
        }
        default -> throw new UnsupportedOperationException("The browser chosen "+ browserType +" is not supported");
    }
    driver.manage().window().maximize();
    return driver;
}
}
