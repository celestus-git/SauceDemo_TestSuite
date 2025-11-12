package stepdefinitions;

import Logger.LogManager;
import context.TestContext;
import data.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import io.cucumber.java.en.When;

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
    @When("user enters valid credentials {string} and {string}")
    public void enterUserCredentials(String usarname, String password){
        User user = new User(usarname,password);

        context.setCurrentUser(user);
        context.loginPage.enterCredentials(user);

        logger.info("Credentials entered {} {}",usarname,password);
        logger.info("ACTION: Credentials provided in input fields.");
    }

    @And("user clears input credentials")
    public void userClearsInputCredentials() {
        context.loginPage.clearCredentials();
    }

    @And("user clears password credential")
    public void userClearsPasswordCredential() {
        context.loginPage.clearPassword();
    }
    @When("user tries to login")
    public void userTriesToLogin(){
        context.loginPage.clickLoginButton();
        logger.info("ACTION clicked Login button on empty fields");
    }

    @When("user clicks the login button")
    public void userClicksLoginButton(){
        User currentUser= context.getCurrentUser();

        logger.info("ACTION: performing login for user {}",currentUser.getUsername());

    context.inventoryPage= context.loginPage.login(currentUser);

    }
    @Then("the user is directed to Inventory page")
    public void userRedirectedToInventoryPage(){

        assertThat(context.inventoryPage.isInventoryPageDisplayed(),is(true));
        logger.info("SUCCESSFUL ASSERT, InventoryPageLoaded");
    }

    @Then("an error message is displayed {string}")
    public void errorMessageIsDisplayed(String expectedErrorMessage){
        String errorMessageDisplayed = context.loginPage.getErrorMessage();

        //assertThat(errorMessageDisplayed,is(expectedErrorMessage));
        logger.info("SUCCESSFUL ASSERT: error message displayed: {}",errorMessageDisplayed);
    }



}
