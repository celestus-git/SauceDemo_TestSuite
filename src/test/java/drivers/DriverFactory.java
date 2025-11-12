package drivers;

import Logger.LogManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {

    private WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static final String BASE_URL = "https://www.saucedemo.com/";

    public DriverFactory() {
    }

    public WebDriver createDriver(BrowserType browserType, boolean isHeadless) {
        this.driver = initializeDriver(browserType, isHeadless);
        logger.debug("Inicializando Webdriver para el navegador: {}", browserType.name());

        return this.driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    private WebDriver initializeDriver(BrowserType browserType, boolean isHeadless) {
        switch (browserType) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                if (isHeadless) chromeOptions.addArguments("--headless=new",
                                                            "--window-size=1366,768",
                                                            "--no-sanbox",
                                                            "--disable-dev-shm-usage",
                                                            "--disable-gpu");

                chromeOptions.addArguments("--disable-notifications", "--disable-popup-blocking", "--disable-extensions");

                driver = new ChromeDriver(chromeOptions);

                logger.info("Chrome driver creado con éxito");

            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) edgeOptions.addArguments("--headless=new", "--window-size=1366,768");
                driver = new EdgeDriver(edgeOptions);

                logger.info("Edge driver creado con éxito");
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) firefoxOptions.addArguments("--headless", "--window-size=1366,768");
                driver = new FirefoxDriver(firefoxOptions);
                logger.info("Firefox driver creado con éxito");
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
        }

        return driver;
    }

}

