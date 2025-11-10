package tests;

import data.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginEmptyCredentials extends BaseTest{

    private final String usernameErrorMessage="Epic sadface: Username is required";
    private final User user;
    public LoginEmptyCredentials(User user){this.user=user;}

    @Test( description = "UC-1 Given the user is on the Login page" +
            "when the user enter any credentials" +
            "and clear the credentials" +
            "and hit Login button" +
            "then the user should remain in LoginPage" +
            "and see error message: Username is required")
    public void failedLoginCredentialsEmpty() {

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed());

        loginPage.enterUsername(user.getUsername());
        loginPage.enterPassword(user.getPassword());
        loginPage.clearCredentials();
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), usernameErrorMessage);

    }

}
