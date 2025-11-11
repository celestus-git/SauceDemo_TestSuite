package runners;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;


@Suite
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepdefinitions,hooks,context,drivers")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value ="pretty,html:target/cucumber-reports.html")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:features")
public class CucumberTestSuite {
}
