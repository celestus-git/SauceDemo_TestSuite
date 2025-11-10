package tests;

import data.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;


public class LoginSuccessTest extends BaseTest {




    private final String inventorySwagLabsTitle = "Swag Labs";

    private final User user;
    public LoginSuccessTest(User user){this.user=user;}

    @Test( description = "UC-3 Given the user is on the Login page" +
            "when the user enter valid username and password" +
            "And click loginButton" +
            "then the user should be redirected to the dashboard page"
    )
    public void successfulLogin() {

        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.isLoginPageDisplayed());

        loginPage.login(user.getUsername(), user.getPassword());

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed());
        Assert.assertEquals(inventoryPage.getTitle(), inventorySwagLabsTitle);

    }






}
