package pages;

import org.openqa.selenium.*;
import wait.WaitHelper;

public abstract class BasePage {

    protected WebDriver driver;
    protected WaitHelper wait;

    public BasePage(WebDriver driver){
        this.driver=driver;
        this.wait= new WaitHelper(driver);
    }
    protected void clickButton(WebElement element){
        wait.waitForElementToBeClickable(element);
        element.click();
    }
    protected void sendText(WebElement element,String text){
        wait.waitForElementToBeVisible(element);
        element.clear();
        if (text!=null &&!text.isEmpty()){
            element.sendKeys(text);
        }
    }
    protected void clearText(WebElement element){
        wait.waitForElementToBeVisible(element);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);

    }

    protected boolean isDisplayed(WebElement element){
        try {
            wait.waitForElementToBeVisible(element);
            return element.isDisplayed();
        }
        catch (TimeoutException| NoSuchElementException e){
            return false;
        }
    }

    protected String getText(WebElement element){
        wait.waitForElementToBeVisible(element);
        return element.getText() ;
    }

    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

}
