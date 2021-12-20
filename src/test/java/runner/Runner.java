package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:reports/html-reports/default-cucumber-reports.html",
                "json:reports/json-reports/default-cucumber-reports.json"
        },
        features = "./src/test/resources/features",
        glue = "stepdef",
        tags = "@end2end"
)
public class Runner {
}
