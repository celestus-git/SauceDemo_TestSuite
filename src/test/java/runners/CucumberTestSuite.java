package runners;


import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.Test_Suite_Sauce.stepdefinitions")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value ="pretty,html:target/cucumber-reports.html")
public class CucumberTestSuite {
}
