package context;

import data.User;
import data.UserCreator;
import drivers.WebDriverSupplier;
import org.openqa.selenium.WebDriver;
import pages.InventoryPage;
import pages.LoginPage;
import pages.PageObjectManager;
import javax.inject.Inject;
import java.util.List;

public class TestContext {

    private final List<User> validUsers;
    private final WebDriverSupplier webDriverSupplier;

    private WebDriver driver;
    public InventoryPage inventoryPage;
    public User currentUser;
    private PageObjectManager pageObjectManager;

    @Inject
    public TestContext(WebDriverSupplier webDriverSupplier) {
        this.validUsers= UserCreator.getValidUserList();
        this.webDriverSupplier = webDriverSupplier;

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

    public WebDriver getDriver() {
        if (driver==null){
            driver = webDriverSupplier.get();
        }
        return driver;
    }

    public InventoryPage getInventoryPage() {
        return inventoryPage;
    }

    public void setInventoryPage(InventoryPage inventoryPage) {
        this.inventoryPage = inventoryPage;
    }

    public PageObjectManager getPageObjectManager() {
       if (pageObjectManager ==null){
           pageObjectManager = new PageObjectManager(getDriver());
       }

        return pageObjectManager;
    }

    public void cleanUp(){
        if (driver!= null){
            driver.quit();
            driver=null;
            pageObjectManager=null;
        }
    }
}
