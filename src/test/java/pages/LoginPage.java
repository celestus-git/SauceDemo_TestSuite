package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends  BasePage{


    @FindBy(xpath = "//input[@data-test='username']")
    private WebElement usernameField;

    @FindBy(xpath= "//input[@data-test='password']")
    private WebElement passwordField;

    @FindBy(xpath= "//input[@data-test='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement loginErrorMessage;

    public LoginPage(WebDriver driver){

        super(driver);


        PageFactory.initElements(driver,this);
    }

    public void enterUsername(String username){
        sendKeys(usernameField,username);

    }
    public void enterPassword(String password){
        sendKeys(passwordField,password);

    }
    public void clearCredentials(){
        clearText(usernameField);
        clearText(passwordField);
    }
    public void clearPassword(){
        clearText(passwordField);
    }


    public void clickLoginButton(){
        click(loginButton);
    }

    public void login(String username, String password){
       enterUsername(username);
       enterPassword(password);
       clickLoginButton();



    }


    public boolean isLoginPageDisplayed(){
        return isDisplayed(loginButton);
    }
    public boolean isLoginErrorMessageDisplayed(){
        return  isDisplayed(loginErrorMessage);
    }
    public String getErrorMessage(){
        wait.waitForElementToBeVisible(loginErrorMessage);
        return  getText(loginErrorMessage);
    }



}
