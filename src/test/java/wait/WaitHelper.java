package wait;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

private WebDriver driver;
private WebDriverWait wait;

/* Constructor aplica dependency injection. Recibe el driver como parámetro, no lo crea
 * @param driver WebDriver sobre el cual aplica las esperas
 */
public WaitHelper(WebDriver driver){
    this.driver=driver;
}

private Wait<WebDriver> getFluentWait(){
    return new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofSeconds(2))
            .ignoring(NoSuchElementException.class);
}

public void waitPageToBeLoaded(){
    Wait<WebDriver> wait=getFluentWait();
    wait.until(w->{
        String readyState =(String) ((JavascriptExecutor) driver)
        .executeScript("return document.readyState");
        return "complete".equals(readyState);
    });
}

public WebElement waitForElementToBeClickable(WebElement element){
    return getFluentWait().until(ExpectedConditions.elementToBeClickable(element));

}
public WebElement waitForElementToBeVisible(WebElement element){
    return getFluentWait().until(ExpectedConditions.visibilityOf(element));
}
public void waitForSuccessfullLogin(String urlPart){
    getFluentWait().until(ExpectedConditions.urlContains(urlPart));
}



}
