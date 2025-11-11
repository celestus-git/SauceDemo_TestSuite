package pages;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private LoginPage loginPage;
    private WebDriver driver;
    public PageObjectManager(){

    }

    public void initializeDriver(WebDriver driver) {
        this.driver = driver;
        this.loginPage=new LoginPage(driver);
    }

    public LoginPage getLoginPage() {
        if (loginPage==null){
            throw new IllegalStateException("LoginPage not initialized");
        }

        return loginPage;
    }
}
