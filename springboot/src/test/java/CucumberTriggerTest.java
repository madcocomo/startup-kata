import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vic.kata.hangman.HangmanApplication;

@RunWith(Cucumber.class)
@CucumberOptions(strict=true, monochrome=true, format="junit:output", snippets= SnippetType.CAMELCASE)
public class CucumberTriggerTest {

}
