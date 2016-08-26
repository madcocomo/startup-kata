package vic.kata.steps.functional;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import vic.kata.hangman.GameConfiguration;
import vic.kata.hangman.GameService;
import vic.kata.hangman.HangmanApplication;
import vic.kata.hangman.SecretProvider;

import java.util.List;

import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = HangmanApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
public class RecordsFunctionalSteps {
    @When("^admin view the game records$")
    public void adminViewGameRecords() {

    }

    @Then("^the played game counts is: (\\d*)$")
    public void playedGameCountsIs(int count) {

    }
}
