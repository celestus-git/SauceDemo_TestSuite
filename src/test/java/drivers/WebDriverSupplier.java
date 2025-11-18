package drivers;

//import io.cucumber.java.Scenario;
import javax.inject.Inject;
import javax.inject.Provider;
import org.openqa.selenium.WebDriver;


public class WebDriverSupplier implements Provider<WebDriver> {

//    private final Scenario scenario;
    private final DriverFactory driverFactory;

    @Inject
    public WebDriverSupplier(DriverFactory driverFactory){
        this.driverFactory = driverFactory;
    }

    @Override
    public WebDriver get() {
        String browserName= System.getProperty("browser", "chrome").toUpperCase();
        boolean isHeadless=Boolean.parseBoolean(System.getProperty("headless","true"));
        BrowserType browserType;
        try {
            browserType = BrowserType.valueOf(browserName);

        }catch (IllegalArgumentException e){
            browserType=BrowserType.CHROME;
        }
        return this.driverFactory.createDriver(browserType,isHeadless);
    }
}
