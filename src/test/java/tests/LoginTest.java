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

    LoginPage loginPage;
    InventoryPage inventoryPage;
    private final String usernameErrorMessage = "Epic sadface: Username is required";
    private final String passwordErrorMessage = "Epic sadface: Password is required";
    private final String inventorySwagLabsTitle = "Swag Labs";

    @DataProvider(name = "validUsers")
    public Object[][] getValidUserCredentials() {

        List<User> userList = UserCreator.getValidUserList();
        //Conversion de List a Object[][] para TestNG

        Object[][] data = new Object[userList.size()][1];

        for (int i = 0; i < userList.size(); i++) {
            data[i][0] = userList.get(i);

        }
        return data;

    }

    @Test(priority = 2, dataProvider = "validUsers", description = "Given the user is on the Login page" +
            "when the user enter valid username and password" +
            "And click loginButton" +
            "then the user should be redirected to the dashboard page"
    )
    public void successfulLogin(User user) {
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
        loginPage.login(user.getUsername(), user.getPassword());
        loginPage.waitForSuccessfulLogin();

        inventoryPage = new InventoryPage(driver);


        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed());
        Assert.assertEquals(inventoryPage.getTitle(), inventorySwagLabsTitle);

    }

    @Test(priority = 1, dataProvider = "validUsers", description = "Given the user is on the Login page" +
            "when the user enter any credentials" +
            "and clear the credentials" +
            "and hit Login button" +
            "then the user should remain in LoginPage" +
            "and see error message: Username is required")
    public void failedLoginUsernameEmpty(User user) {
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
        loginPage.enterUsername(user.getUsername());
        loginPage.enterPassword(user.getPassword());
        loginPage.clearCredentials();
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), usernameErrorMessage);

    }

    @Test(priority = 3, dataProvider = "validUsers", description = "Given the user is on the Login page"
            + "when the user enter any valid credentials"
            + "and user clear password credential"
            + "and hit login button"
            + "then the user should remain in LoginPage"
            + "and see error message: Password is required")
    public void failedLoginPasswordEmpty(User user) {
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
        loginPage.enterUsername(user.getUsername());
        loginPage.enterPassword(user.getPassword());
        loginPage.clearPassword();
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(),passwordErrorMessage);


    }


}
