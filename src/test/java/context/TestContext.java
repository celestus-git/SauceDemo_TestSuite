package context;

import data.User;
import data.UserCreator;
import drivers.BrowserType;
import drivers.WebDriverSupplier;
import org.openqa.selenium.WebDriver;
import pages.InventoryPage;
import pages.LoginPage;
import pages.PageObjectManager;

import java.util.List;

public class TestContext {

    private final WebDriverSupplier webDriverSupplier;
    private final List<User> validUsers;
    private WebDriver driver;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public User currentUser;
    private final PageObjectManager pageObjectManager;

    public TestContext(WebDriverSupplier webDriverSupplier,PageObjectManager pageObjectManager) {
        this.webDriverSupplier = webDriverSupplier;
        this.pageObjectManager=pageObjectManager;
        this.validUsers= UserCreator.getValidUserList();
    }
    public void setupDriver(BrowserType browserType, boolean isHeadless) {
        if (this.driver==null) {
            System.setProperty("browser", browserType.name());
            System.setProperty("headless", String.valueOf(isHeadless));

            this.driver = webDriverSupplier.get();
        }
    }
    public WebDriver getDriver() {
        return this.driver;
    }
    public List<User>getValidUsers(){
        return validUsers;
    }
    public User getCurrentUser() {
        // Retorna el campo privado que almacena el usuario
        return this.currentUser;
    }
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }
}
