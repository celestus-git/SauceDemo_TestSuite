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


    @DataProvider(name="validUsers")
    public Object[][] getValidUserCredentials() {

        List<User> userList = UserCreator.getValidUserList();
        //Conversion de List a Object[][] para TestNG

        Object[][] data = new Object[userList.size()][1];

        for (int i = 0; i < userList.size(); i++) {
            data[i][0] = userList.get(i);

        }
        return data;

    }

    @Test(priority = 1,dataProvider ="validUsers" , description = "Given the user is on the Login page" +
            "when the user enter valid username and password"+
            "And click loginButton"+
            "then the user should be redirected to the dashboard page"
    )
            public void successfulLogin(User user){
                loginPage = new LoginPage(driver);
                Assert.assertTrue(loginPage.isLoginPageDisplayed());
                loginPage.login(user.getUsername(), user.getPassword());
                loginPage.waitForSuccessfulLogin();

                inventoryPage = new InventoryPage(driver);

                Assert.assertTrue(inventoryPage.isInventoryPageDisplayed());
                //Assert.assertTrue(inventoryPage);

    }

//    return new Object[][]{
//            {"standard_user","secret_sauce"},
//            {"locked_out_user","secret_sauce"},
//            {"problem_user","secret_sauce"},
//            {"performance_glitch_user","secret_sauce"},
//            {"error_user","secret_sauce"},
//            {"visual_user","secret_sauce"}
//    } ;
//
//}
}
