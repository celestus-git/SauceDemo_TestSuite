package stepdefinitions;

import driver.BrowserType;
import Logger.LogManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import io.cucumber.java.Scenario;
import tests.BaseTest;


public class Hook extends BaseTest {


    @Before
    public void SetUpScenario(){
        String browserName = System.getProperty("browserName","CHROME");

        BrowserType browserType;

        try {
            browserType = BrowserType.valueOf(browserName.toUpperCase());
        }catch (IllegalArgumentException e){
            logger.error("Type of browserName '{}' is not valid. Using CHROME as default", browserName);
            browserType=BrowserType.CHROME;

        }
        logger.info("===========================");
        logger.info("START SCENARIO:  | Browser: {}",browserType.name());
    setUp(browserType);


    }
    @After
    public void tearDownScenario(Scenario scenario){

       if (driver!=null&&scenario.isFailed()){
           logger.error("Scenario '{}' is failed. Check log",scenario.getName());
       }
    //Add code of block to take screenshots
        tearDown();
       logger.info("ENDED SCENARIO: {}| STATUS: {}",scenario.getName(),(scenario.isFailed())?"FAILED":"SUCCESSFUL");
    }


    }

