import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict=true, monochrome=true,
    tags = "@functional", glue = "vic.kata.steps.functional",
    plugin="junit:output", snippets= SnippetType.CAMELCASE)
public class FunctionalATTrigger {
}
