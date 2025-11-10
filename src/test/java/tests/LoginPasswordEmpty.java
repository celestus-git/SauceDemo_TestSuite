package tests;

import data.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPasswordEmpty extends BaseTest{


    private final String passwordErrorMessage = "Epic sadface: Password is required";

    private final User user;

    public LoginPasswordEmpty(User user){this.user=user;}
    @Test( description = "UC-2 Given the user is on the Login page"
            + "when the user enter any valid credentials"
            + "and user clear password credential"
            + "and hit login button"
            + "then the user should remain in LoginPage"
            + "and see error message: Password is required")
    public void failedLoginPasswordEmpty() {

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed());

        loginPage.enterUsername(user.getUsername())
                .enterPassword(user.getPassword())
                .clearPassword()
                .clickLoginButton();

        //Assert.assertFalse(loginPage.currentUsernameValue().isBlank());
        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(),passwordErrorMessage);


    }
}
