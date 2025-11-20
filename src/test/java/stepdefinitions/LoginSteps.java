package stepdefinitions;

import Logger.LogManager;
import context.TestContext;
import data.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.slf4j.Logger;
import io.cucumber.java.en.When;

import pages.InventoryPage;
import pages.LoginPage;

import java.util.Optional;


public class LoginSteps  {
private final Logger logger = LogManager.getLogger(LoginSteps.class);

//Dependencies injection
    private final TestContext context;
    private final LoginPage loginPage;


    public LoginSteps(TestContext context){
        this.context=context;
        this.loginPage = context.getPageObjectManager().getLoginPage();

    }

    @When("user enters valid credentials {string} and {string}")
    public void enterUserCredentials(String username, String password){

        User user = new User(username,password);
        context.setCurrentUser(user);
        loginPage.enterCredentials(user);

        logger.info("Credentials entered {} {}",username,password);
        logger.info("ACTION: Credentials provided in input fields.");
    }

    @And("user clears input credentials")
    public void userClearsInputCredentials() {
        loginPage.clearCredentials();
        logger.info("ACTION: Credentials fields cleared.");
    }

    @And("user clears password credential")
    public void userClearsPasswordCredential() {
        loginPage.clearPassword();
        logger.info("ACTION: Password field cleared.");
    }
    @When("user tries to login")
    public void userTriesToLogin(){
        loginPage.clickLoginButton();
        logger.info("ACTION clicked Login button on empty fields");
    }

    @When("user clicks the login button")
    public void userClicksLoginButton() {

        User currentUser = context.getCurrentUser();
        Optional<InventoryPage> maybeInventory = context.getPageObjectManager().getLoginPage().login(currentUser);

        if (maybeInventory.isPresent()) {
            context.setInventoryPage(maybeInventory.get());
            logger.info("Login successful, inventory page set in context");
        } else {
            throw new AssertionError("Login did not navigate to InventoryPage for user: " +
                    (currentUser != null ? currentUser.getUsername() : "null"));

        }
    }

    @Then("the user is directed to Inventory page")
    public void userRedirectedToInventoryPage(){

        assertThat(context.getInventoryPage().isInventoryPageDisplayed(),is(true));
        logger.info("SUCCESSFUL ASSERT, InventoryPageLoaded");
    }

    @Then("an error message is displayed {string}")
    public void errorMessageIsDisplayed(String expectedErrorMessage){
        String errorMessageDisplayed = loginPage.getErrorMessage();

        assertThat(errorMessageDisplayed,is(expectedErrorMessage));
        logger.info("SUCCESSFUL ASSERT: error message displayed: {}",errorMessageDisplayed);
    }



}
