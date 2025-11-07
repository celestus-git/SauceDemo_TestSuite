package tests;

import driver.BrowserType;
import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import wait.WaitHelper;

import java.sql.Driver;

public class BaseTest{

    protected WebDriver driver;
    protected WaitHelper wait;
    private final String BASE_URL = "https://www.saucedemo.com/";

    @BeforeMethod

    @Parameters("browserType")
    public void setUp(String browserName){

        BrowserType browser = BrowserType.valueOf(browserName.toUpperCase());

        driver = DriverFactory.getDriver(browser);
        this.wait= new WaitHelper(driver);
        driver.get(BASE_URL);
        wait.waitPageToBeLoaded();

    }
    @AfterMethod
    public void tearDown(){
       if(driver!=null){
           driver.quit();
       }
    }

}
