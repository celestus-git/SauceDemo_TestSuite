package tests;


import Logger.LogManager;
import driver.BrowserType;
import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.annotations.*;
import wait.WaitHelper;


public class BaseTest{

    protected final Logger logger = LogManager.getLogger(getClass());

    protected WebDriver driver;
    protected WaitHelper wait;
    private final String BASE_URL = "https://www.saucedemo.com/";

    @BeforeClass(alwaysRun = true)

    @Parameters({"browserType","headless"})
    public void setUp(@Optional("CHROME") String browserName,@Optional("true") String headless){

        logger.info("Iniciando configuracion de prueba en navegador: {}", browserName);

        BrowserType browser = BrowserType.valueOf(browserName.toUpperCase());

        driver = DriverFactory.newDriver(browser,Boolean.parseBoolean(headless));
        this.wait= new WaitHelper(driver);
        driver.get(BASE_URL);
        wait.waitPageToBeLoaded();

        logger.info("Navegación completada a la URL: {}", BASE_URL);


    }
    @AfterClass(alwaysRun = true)
    public void tearDown(){
       if(driver!=null){
           driver.manage().deleteAllCookies();
           driver.quit();
       }
    }

}
