package pages;

import data.User;
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

    public void clearCredentials(){
       isDisplayed(usernameField);

        clearText(usernameField);
        clearText(passwordField);
    }
    public void clearPassword(){
        clearText(passwordField);
    }


    public void clickLoginButton(){

        clickButton(loginButton);
    }
    public void enterCredentials(User user){
        sendText(usernameField,user.getUsername());
        sendText(passwordField,user.getPassword());


    }
    public InventoryPage login(User user){

        clickLoginButton();

       return new InventoryPage(driver);

    }


    public boolean isLoginPageDisplayed(){

        return wait.waitForElementToBeVisible(loginButton).isDisplayed();
    }

    public String getErrorMessage(){
        isDisplayed(loginErrorMessage);
       return loginErrorMessage.getText();

    }



}
