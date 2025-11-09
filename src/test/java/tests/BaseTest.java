package tests;


import Logger.LogManager;
import driver.BrowserType;
import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import wait.WaitHelper;

import java.sql.Driver;


public class BaseTest{

    protected final Logger logger = LogManager.getLogger(getClass());

    protected WebDriver driver;
    protected WaitHelper wait;
    private final String BASE_URL = "https://www.saucedemo.com/";

    @BeforeMethod

    @Parameters("browserType")
    public void setUp(String browserName){

        logger.info("Iniciando configuracion de prueba en navegador: {}", browserName);

        BrowserType browser = BrowserType.valueOf(browserName.toUpperCase());

        driver = DriverFactory.getDriver(browser);
        this.wait= new WaitHelper(driver);
        driver.get(BASE_URL);
        wait.waitPageToBeLoaded();

        logger.info("Navegación completada a la URL: {}", BASE_URL);


    }
    @AfterMethod
    public void tearDown(){
       if(driver!=null){
           driver.manage().deleteAllCookies();
           driver.quit();
       }
    }

}
