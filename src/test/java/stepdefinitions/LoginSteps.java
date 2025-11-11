package stepdefinitions;

import Logger.LogManager;
import context.TestContext;
import data.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import io.cucumber.java.en.When;

import pages.InventoryPage;
import pages.LoginPage;


public class LoginSteps  {
private final Logger logger = LogManager.getLogger(LoginSteps.class);

//Dependencies injection
    private final TestContext context;


    public LoginSteps(TestContext context){
        this.context=context;

    }


    @Given("user is on the LoginPage")
    public void userIsOnTheLoginPage(){
        WebDriver driver = context.getDriver();
        context.loginPage = new LoginPage(driver);
        logger.info("VERIFICATION: the Login page is fully loaded");
        assertThat(context.loginPage.isLoginPageDisplayed(),is(true));
        logger.info("VERIFICATION: The page is successfully loaded");

    }
    @When("user enters valid credentials {string}")
    public void userEnterValidCredentials(String userType){
        User validUser = context.getValidUsers().stream()
                .filter(user -> user.getUsername().equals(userType))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Usuario '" + userType + "' no encontrado en la lista de usuarios válidos."));

        // 2. Almacenar este usuario como el actual en el TestContext
        context.setCurrentUser(validUser);
    }
    @Then("the user is directed to Inventory page")
    public void userRedirectedToInventoryPage(){
        WebDriver driver = context.getDriver();
        context.inventoryPage = new InventoryPage(driver);

        assertThat(context.inventoryPage.isInventoryPageDisplayed(),is(true));
        logger.info("SUCCESSFUL ASSERT, InventoryPageLoaded");
    }

}
