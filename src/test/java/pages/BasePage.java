package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import wait.WaitHelper;

import java.util.Objects;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitHelper wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver);

    }

    protected void safeClick(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void click(By locator) {
        WebElement element = wait.waitForElementToBeClickable(locator);
        safeClick(element);
    }

    protected void sendKeys(By locator, String text) {
        WebElement element = wait.waitForElementToBeVisible(locator);
        fullClearText(element);
        if (text != null && !text.isEmpty()) {
            element.sendKeys(text);
        } else {
            throw new IllegalArgumentException("text cannot be null or empty");
        }
//        if (element.getAttribute("value") == null || element.getAttribute("value").isBlank()) {
//            element.click();
//            element.sendKeys(text);
//        }
    }

    protected String getText(By locator) {
        return wait.waitForElementToBeVisible(locator).getText();

    }

    protected WebElement visible(By locator) {
        return wait.waitForElementToBeVisible(locator);

    }

    protected String getAttribute(By locator, String name) {
        return wait.waitForElementToBeVisible(locator).getAttribute(name);
    }

    protected void clearText(By locator) {
        WebElement element = wait.waitForElementToBeVisible(locator);
        fullClearText(element);

    }

    protected void fullClearText(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        if (element.getAttribute("value") != null && !Objects.requireNonNull(element.getAttribute("value")).isEmpty()) {
            element.clear();
        }
    }

    protected void waitThatUrlContains(String urlPart) {
        wait.waitForUrlContains(urlPart);
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return wait.waitForElementToBeVisible(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}
