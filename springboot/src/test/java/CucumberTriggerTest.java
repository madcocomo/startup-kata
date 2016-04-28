import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict=true, monochrome=true, format="junit:output", snippets= SnippetType.CAMELCASE)
public class CucumberTriggerTest {

}
