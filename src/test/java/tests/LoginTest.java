package tests;

import data.User;
import org.testng.annotations.DataProvider;
import pages.LoginPage;
import service.UserCreator;

import java.util.List;

public class LoginTest extends BaseTest {

LoginPage loginPage;

    @DataProvider(name="validUsers")
    public Object[][] getValidUserCredentials(){

        List<User> userList = UserCreator.getValidUserList();
        //Conversion de List a Object[][] para TestNG

        Object[][] data = new Object[userList.size()][1];
        for(int i=0; i<userList.size();i++){
            data[i][0]= userList.get(i);

        }
        return data;

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
