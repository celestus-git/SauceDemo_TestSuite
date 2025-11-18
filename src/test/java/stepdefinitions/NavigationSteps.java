package stepdefinitions;

import context.TestContext;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;


    public class NavigationSteps {
        private static final String BASE_URL = "https://www.saucedemo.com/";
        private final TestContext testContext;

        public NavigationSteps(TestContext testContext) {
            this.testContext = testContext;
        }

        @Given("user is on the LoginPage on {string} browser")
        public void userIsOnTheLoginPageInBrowser(String browserName) {
            String normalized = browserName.toLowerCase();
            System.setProperty("browser", normalized);

            WebDriver driver = testContext.getDriver();
            driver.get(BASE_URL);
        }
    }

