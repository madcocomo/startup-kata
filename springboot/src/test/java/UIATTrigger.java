import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict=true, monochrome=true,
    tags = "@UI", glue = "vic.kata.steps.outer",
    plugin="junit:output", snippets= SnippetType.CAMELCASE)
public class UIATTrigger {
}
