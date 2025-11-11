package hooks;

import context.TestContext;
import drivers.BrowserType;
import Logger.LogManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import io.cucumber.java.Scenario;
import drivers.BrowserManager;


public class Hook  {

    private final Logger logger = LogManager.getLogger(Hook.class);
    private final TestContext testContext;


    public Hook(TestContext testContext ){

        this.testContext=testContext;

    }

    @Before(order = 0)
    public void SetUpScenario(Scenario scenario){

        // 1. Obtener los parámetros del navegador
        String browserName = scenario.getSourceTagNames().stream()
                .filter(tag -> tag.startsWith("@browser="))
                .map(tag -> tag.split("=")[1].toUpperCase())
                .findFirst()
                .orElse(BrowserType.CHROME.name());

        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless","false"));

        logger.info("===========================");
        logger.info("START SCENARIO: {} | Browser: {} | Headless: {}", scenario.getName(), browserName, isHeadless);
        logger.info("===========================");


        try {
            // Convierte el nombre del String a tu enum BrowserType
            BrowserType browserType = BrowserType.valueOf(browserName);

            // LLAMA al método que crea e inicializa el driver en TestContext
            this.testContext.setupDriver(browserType, isHeadless);

            // 3. Obtener el driver inicializado del TestContext
            WebDriver driver = this.testContext.getDriver();

            // 4. Navegación (Solo si el driver se creó con éxito)
            if (driver != null) {
                driver.get("https://www.saucedemo.com/");
                logger.info("Navigating to base URL");
            } else {
                logger.error("ERROR. Webdriver could not be created/injected correctly by DriverFactory.");
            }

        } catch (IllegalArgumentException e) {
            logger.error("Browser name {} is invalid. Using default Chrome.", browserName);
            // Manejar un caso de fallback si el tag es incorrecto, y re-intentar la inicialización
            this.testContext.setupDriver(BrowserType.CHROME, isHeadless);
            WebDriver driver = this.testContext.getDriver();
            if (driver != null) { driver.get("https://www.saucedemo.com/"); }
        }
    }

    @After(order = 0)
    public void tearDownScenario(Scenario scenario){

        // 1. Obtener el driver a través del contexto
        WebDriver driver = this.testContext.getDriver();

        if (driver != null && scenario.isFailed()){
            try {
                // Aquí iría tu lógica de captura de pantalla (si la tienes)
                logger.error("Scenario '{}' is failed. Check log",scenario.getName());
            }catch (Exception e) {
                logger.warn("Screenshot didn't indexed: {}", e.getMessage());
            }
        }

       BrowserManager.closeDriver(driver);

        logger.info("ENDED SCENARIO: {}| STATUS: {}", scenario.getName(), (scenario.isFailed()) ? "FAILED" : "SUCCESSFUL");
        logger.info("===========================");
    }
}
