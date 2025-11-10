package pages;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{


    private final By usernameField =By.xpath( "//input[@data-test='username']");

    private final By passwordField = By.xpath("//input[@data-test='password']");

    private final By loginButton = By.xpath("//input[@data-test='login-button']");

    private final By loginErrorMessage= By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public LoginPage enterUsername(String username){
        sendKeys(usernameField,username);
        return this;

    }
    public LoginPage enterPassword(String password){
        sendKeys(passwordField,password);
        return this;
    }
    public LoginPage clearCredentials(){
        clearText(usernameField);
        clearText(passwordField);
    return this;
    }
    public LoginPage clearPassword(){
        clearText(passwordField);
        return this;
    }

    public LoginPage clickLoginButton(){
        click(loginButton);
        return this;
    }

    public LoginPage login(String username, String password){
       return enterUsername(username)
               .enterPassword(password)
               .clickLoginButton();
    }
public String currentUsernameValue(){
        return visible(usernameField).getAttribute("value");
}
    public void waitForSuccessfulLogin(){
        waitThatUrlContains("inventory.html");
    }
    public boolean isLoginPageDisplayed(){
        return isDisplayed(loginButton);
    }
    public boolean isLoginErrorMessageDisplayed(){
        return  isDisplayed(loginErrorMessage);
    }
    public String getErrorMessage(){
        return  getText(loginErrorMessage);
    }


}
