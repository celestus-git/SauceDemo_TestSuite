package wait;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import java.time.Duration;

public class WaitHelper {

    private final WebDriver driver;
    private final Duration timeout;
    private final Duration poll;
/* Constructor aplica dependency injection. Recibe el driver como parámetro, no lo crea
 * @param driver WebDriver sobre el cual aplica las esperas
 */
public WaitHelper(WebDriver driver){
    this(driver,
            Duration.ofSeconds(Long.getLong("wait.timeout.sec",10)),
            Duration.ofMillis(Long.getLong("wait.poll.ms",300)));
}
public WaitHelper(WebDriver driver,Duration timeout, Duration poll){
    this.driver=driver;
    this.timeout=timeout;
    this.poll=poll;
}

private FluentWait<WebDriver> fluentWait(String message){
    return new FluentWait<>(driver)
            .withTimeout(timeout)
            .pollingEvery(poll)
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class)
            .ignoring(ElementClickInterceptedException.class)
            .ignoring(ElementNotInteractableException.class)
            .withMessage(message);
}

public void waitPageToBeLoaded(){
    fluentWait("Waiting document.readyState=complete")
                    .until(w->"complete".equals(((JavascriptExecutor) w)
                    .executeScript("return document.readyState")));
}

public void waitForUrlContains(String urlPart){
    fluentWait("Waiting that URL contains "+urlPart)
            .until(ExpectedConditions.urlContains(urlPart));
}
public WebElement waitForElementToBeClickable(By locator){
    return fluentWait("Waiting clickable"+locator)
            .until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));

}
public WebElement waitForElementToBeVisible(By locator){
    return fluentWait("Waiting visibility"+ locator)
            .until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
}




}
