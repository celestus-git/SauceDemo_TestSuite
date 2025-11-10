package tests;


import Logger.LogManager;
import driver.BrowserType;
import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import wait.WaitHelper;


public class BaseTest{

    protected static WebDriver driver;
    protected final Logger logger = LogManager.getLogger(getClass());
    protected WaitHelper wait;
    private final String BASE_URL = "https://www.saucedemo.com/";




    public void setUp(BrowserType browserType){

        logger.info("Iniciando configuracion de prueba en navegador: {}", browserType.name());


        driver = DriverFactory.getDriver(browserType);
        this.wait= new WaitHelper(driver);
        driver.get(BASE_URL);
        wait.waitPageToBeLoaded();

        logger.info("Navegación completada a la URL: {}", BASE_URL);


    }

    public void tearDown(){
       if(driver!=null){
           logger.info("Closing webdriver and cleaning session");
           driver.manage().deleteAllCookies();
           driver.quit();
       }
    }

}
