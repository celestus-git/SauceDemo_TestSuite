package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class InventoryPage extends BasePage{

    private final By titleProductPage = By.xpath( "//div[@class='app_logo']");
    private final By appLogo = By.xpath("//*[contains(@class,'app_logo') and normalize-space()='Swag Labs']");


    public InventoryPage(WebDriver driver){
        super(driver);

    }
    public InventoryPage ensureReady(){
        waitThatUrlContains("inventory");
        isDisplayed(appLogo);
        return this;
    }
    public boolean isInventoryPageDisplayed(){
        return isDisplayed(appLogo) && getCurrentUrl().contains("inventory");
    }
    public String getTitle(){
        return getText(appLogo);
    }
}
