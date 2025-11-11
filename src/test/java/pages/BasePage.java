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
    protected void click(WebElement element){
        wait.waitForElementToBeClickable(element);
        element.click();
    }
    protected void sendKeys(WebElement element, String text){
        wait.waitForElementToBeVisible(element);
        element.clear();
        if (text!=null &&!text.isEmpty()){
            element.sendKeys(text);
        } else {
          //  throw new IllegalArgumentException("text cannot be null or empty");
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
    protected String getPageTitle(){
        return driver.getTitle();
    }


    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

}
