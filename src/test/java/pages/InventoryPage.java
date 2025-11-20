package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage{


    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement titleProductPage;

    public InventoryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);

    }
    public boolean isInventoryPageDisplayed() {
        try {
            // wait es heredado de BasePage (instancia de WaitHelper)
            wait.waitForElementToBeVisible(titleProductPage);
            return titleProductPage.isDisplayed();
        } catch (Exception e) {
            // Capturamos TimeoutException, NoSuchElementException, StaleElementReference, etc.
            return false;
        }
    }
    public void waitUntilLoaded() {
        // Espera que el DOM esté listo
        wait.waitPageToBeLoaded();

        // Luego, espera que el elemento principal de la página sea visible
        wait.waitForElementToBeVisible(titleProductPage);
    }

}
