package service;

import data.User;

import java.util.Arrays;
import java.util.List;

public class UserCreator {

    public static User getStandardUser() {
        return new User.Builder()
                .withUsername("standard_user")
                .withPassword("secret_sauce")
                .build();
    }

    public static User getLockedUser() {
        return new User.Builder()

                .withUsername("locked_out_user")
                .withPassword("secret_sauce")
                .build();
    }

    public static User getProblemUser() {
        return new User.Builder()
                .withUsername("problem_user")
                .withPassword("secret_sauce")
                .build();
    }
    public static User getPerformanceGlitchUser(){
        return new User.Builder()
                .withUsername("performance_glitch_user")
                .withPassword("secret_sauce")
                .build();
    }
    public static User getErrorUser() {
        return new User.Builder()

                .withUsername("error_user")
                .withPassword("secret_sauce")
                .build();
    }
    public static User getVisualUser(){
        return new User.Builder()
                .withUsername("visual_user")
                .withPassword("secret_sauce")
                .build();
    }
    public static User getWrongUsernameUser(){
        return new User.Builder()
                .withUsername("hello")
                .withPassword("secret_sauce")
                .build();
    }
    public static User getWrongPasswordUser(){
        return new User.Builder()
                .withUsername("standard_user")
                .withPassword("hello")
                .build();
    }

    //Lista usuarios happy path - Login exitoso

    public static List<User> getValidUserList(){
        return Arrays.asList(getStandardUser()
                ,getProblemUser()
                //,getPerformanceGlitchUser()
                ,getErrorUser()
                ,getVisualUser());
    }
    public static List<User>getInvalidUserList(){
        return Arrays.asList(getLockedUser()
        ,getWrongUsernameUser()
        ,getWrongPasswordUser());
    }
}

