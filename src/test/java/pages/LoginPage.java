package pages;

import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Optional;

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
    public Optional<InventoryPage> login(User user){
        // Rellenar credenciales si fueron pasadas
        if (user != null) {
            enterCredentials(user);
        }

        // Realiza el click que debería navegar a Inventory
        clickLoginButton();

        // Crea el Page Object destino y valida su presencia
        InventoryPage inventory = new InventoryPage(driver);
        try {
            inventory.waitUntilLoaded();
            return Optional.of(inventory);
        }catch (Exception e) {

            return Optional.empty();
        }
    }


    public boolean isLoginPageDisplayed(){

        return wait.waitForElementToBeVisible(loginButton).isDisplayed();
    }

    public String getErrorMessage(){
        isDisplayed(loginErrorMessage);
       return loginErrorMessage.getText();

    }



}
