package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage{

    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement titleProductPage;

    public InventoryPage(WebDriver driver){
        super(driver);

    }
    public boolean isInventoryPageDisplayed(){
        return isDisplayed(titleProductPage) && getCurrentUrl().contains("inventory");
    }
}
