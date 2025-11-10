package tests;

import data.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import service.UserCreator;

public class LoginFactory {
    @DataProvider(name = "validUsers")
    public static Object[][] getValidUserCredentials() {

        var userList = UserCreator.getValidUserList();
        //Conversion de List a Object[][] para TestNG

        Object[][] data = new Object[userList.size()][1];

        for (int i = 0; i < userList.size(); i++) {
            data[i][0] = userList.get(i);

        }
        return data;

    }
    @Factory(dataProvider = "validUsers")
    public static Object[] factory(User user){
        return new Object[]{new LoginTest(user)};
    }
}
