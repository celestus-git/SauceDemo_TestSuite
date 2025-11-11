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
    public boolean isInventoryPageDisplayed(){
        return isDisplayed(titleProductPage) && getCurrentUrl().contains("inventory");
    }
    public String getTitle(){
        return getText(titleProductPage);
    }
}
