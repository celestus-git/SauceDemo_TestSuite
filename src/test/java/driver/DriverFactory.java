package driver;

import Logger.LogManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;




public class DriverFactory {

    protected static final Logger logger= LogManager.getLogger(DriverFactory.class);

    private DriverFactory(){}

public static WebDriver newDriver(BrowserType browserType, boolean headless){
logger.debug("Inicializando Webdriver para el navegador: {} headless={}",browserType.name(),headless);

    WebDriver driver;


    switch (browserType){
        case CHROME -> {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();

            if (headless) chromeOptions.addArguments("--headless=new","--window-size=1366,768");

            chromeOptions.addArguments("--remote-debugging-port=0",
                                        "--disable-dev-shm-usage",
                                        "--disable-notifications",
                                        "--disable-popup-blocking"
                                        );
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);


            driver= new ChromeDriver(chromeOptions);

            logger.info("Chrome driver creado con éxito");
        }
        case EDGE -> {
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            if (headless) edgeOptions.addArguments("--headless=new");
            driver= new EdgeDriver(edgeOptions);
            logger.info("Edge driver creado con éxito");
        }
        case FIREFOX -> {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            if (headless) firefoxOptions.addArguments("--headless");
            driver= new FirefoxDriver(firefoxOptions);
            logger.info("Firefox driver creado con éxito");
        }
        default -> throw new UnsupportedOperationException("The browser chosen "+ browserType +" is not supported");
    }
    driver.manage().window().maximize();
    return driver;
}
}
