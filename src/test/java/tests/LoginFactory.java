package tests;

import data.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import service.UserCreator;

public class LoginFactory {
    @DataProvider(name = "validUsers")
    public static Object[][] users() {

        var userList = UserCreator.getValidUserList();
        System.out.println("[DP] users=" + userList.size());
        //Conversion de List a Object[][] para TestNG

        Object[][] data = new Object[userList.size()][1];

        for (int i = 0; i < userList.size(); i++) {
            data[i][0] = userList.get(i);

        }
        return data;

    }
    @Factory(dataProvider = "validUsers")
    public Object[] factory(User user){
        System.out.println("[FACTORY] building for user=" + user.getUsername());
        return new Object[]{new LoginSuccessTest(user)
        ,new LoginEmptyCredentials(user)
        ,new LoginPasswordEmpty(user)};
    }
}
