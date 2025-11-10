package tests;

import data.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import service.UserCreator;

import java.util.List;

public class LoginTest extends BaseTest {



    private final String usernameErrorMessage = "Epic sadface: Username is required";
    private final String passwordErrorMessage = "Epic sadface: Password is required";
    private final String inventorySwagLabsTitle = "Swag Labs";

    private final User user;
    public LoginTest(User user){this.user=user;}

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

        Assert.assertFalse(loginPage.currentUsernameValue().isBlank());
        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(),passwordErrorMessage);


    }

    @Test( description = "UC-3 Given the user is on the Login page" +
            "when the user enter valid username and password" +
            "And click loginButton" +
            "then the user should be redirected to the dashboard page"
    )
    public void successfulLogin() {

        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.isLoginPageDisplayed());

        loginPage.login(user.getUsername(), user.getPassword());;

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed());
        Assert.assertEquals(inventoryPage.getTitle(), inventorySwagLabsTitle);

    }






}
