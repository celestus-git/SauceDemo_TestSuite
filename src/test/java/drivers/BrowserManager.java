package drivers;


import Logger.LogManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;


public class BrowserManager{


    protected static final Logger logger = LogManager.getLogger(BrowserManager.class);
    public BrowserManager(){}

//    public static WebDriver getNewDriver(BrowserType browserType,boolean isHeadless){
//        WebDriver driver = DriverFactory.createDriver(browserType,isHeadless);
//
//    return driver;
//    }



    public static void closeDriver(WebDriver driver){
       if(driver!=null){
           logger.info("Closing webdriver and cleaning session");
           driver.manage().deleteAllCookies();
           driver.quit();
       }
    }

}
