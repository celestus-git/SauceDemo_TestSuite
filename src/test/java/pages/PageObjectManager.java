package pages;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private LoginPage loginPage;
    private final WebDriver driver;

    public PageObjectManager(WebDriver driver){
        this.driver=driver;

    }


    public LoginPage getLoginPage() {
        if (loginPage==null){
            loginPage = new LoginPage(driver);
        }

        return loginPage;
    }
}
