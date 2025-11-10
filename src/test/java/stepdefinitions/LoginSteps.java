package stepdefinitions;

import io.cucumber.java.en.Given;

import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import tests.BaseTest;

public class LoginSteps extends BaseTest {

    @Given("user is on the LoginPage")
    public void userIsOnTheLoginPage(){
        loginPage = new LoginPage(driver);
        logger.info("VERIFICATION: the Login page is fully loaded");
        Assertions.assertTrue(loginPage.isLoginPageDisplayed());

    }
}
