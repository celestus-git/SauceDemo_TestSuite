package stepdefinitions;

import Logger.LogManager;
import context.TestContext;
import org.slf4j.Logger;
import org.openqa.selenium.WebDriver;

public class InventorySteps {

private final Logger logger = LogManager.getLogger(InventorySteps.class);

private final TestContext testContext;
private final WebDriver driver;

public InventorySteps(TestContext testContext,WebDriver driver){
    this.testContext= testContext;
    this.driver= driver;
}
}

