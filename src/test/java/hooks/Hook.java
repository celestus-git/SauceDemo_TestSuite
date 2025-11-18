package hooks;

import context.TestContext;
import drivers.BrowserType;
import Logger.LogManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import io.cucumber.java.Scenario;

import java.io.ByteArrayInputStream;


public class Hook {

    private static final String BASE_URL = "https://www.saucedemo.com/";
    private final Logger logger = LogManager.getLogger(Hook.class);
    private final TestContext testContext;


    public Hook(TestContext testContext) {

        this.testContext = testContext;

    }

    @Before(order = 0)
    public void SetUpScenario(Scenario scenario) {

        String browserNameFromSystem = System.getProperty("browser");

        logger.info("DIAGNOSTICO: Valor de 'browser' (Output Java): {}", System.getProperty("browser"));

        String finalBrowserName;


        if (browserNameFromSystem != null && !browserNameFromSystem.isEmpty()) {
            finalBrowserName = browserNameFromSystem.toUpperCase();
        } else {
            // 1. Obtener los parámetros del navegador
            finalBrowserName = scenario.getSourceTagNames().stream()
                    .filter(tag -> tag.startsWith("@browser="))
                    .map(tag -> tag.split("=")[1].toUpperCase())
                    .findFirst()
                    .orElse(BrowserType.CHROME.name());
        }
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));


        System.setProperty("browser", finalBrowserName);
        System.setProperty("headless", String.valueOf(isHeadless));

        String browserUpper = finalBrowserName.toUpperCase();

        Allure.parameter("browser", browserUpper);
        Allure.label("epic", "BROWSER: " + browserUpper);
        Allure.label("parentSuite","BROWSERS");
        Allure.label("suite",browserUpper);



        logger.info("===========================");
        logger.info("START SCENARIO: {} | Browser: {} | Headless: {}", scenario.getName(), finalBrowserName, isHeadless);
        logger.info("===========================");


        WebDriver driver = testContext.getDriver();
        driver.get(BASE_URL);
        logger.info("Navigating to BASE URL {}", BASE_URL);
    }

    @After(order = 0)
    public void tearDownScenario(Scenario scenario) {

        // 1. Obtener el driver a través del contexto
        WebDriver driver = null;
        try {
            driver = testContext.getDriver();
        } catch (Exception ignored) {

        }

        if (driver != null && scenario.isFailed()) {
            try {
                // Aquí iría tu lógica de captura de pantalla (si la tienes)
                logger.error("Scenario '{}' is failed. Check log", scenario.getName());
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
            } catch (Exception e) {
                logger.warn("Screenshot didn't indexed: {}", e.getMessage());
            }
        }
        testContext.cleanUp();


        logger.info("ENDED SCENARIO: {}| STATUS: {}", scenario.getName(), (scenario.isFailed()) ? "FAILED" : "SUCCESSFUL");
        logger.info("===========================");
    }
}
