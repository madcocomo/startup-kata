package vic.kata.steps.functional;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import vic.kata.hangman.GameRepository;
import vic.kata.hangman.HangmanApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ContextConfiguration(classes = {HangmanApplication.class, FunctionalTestHelper.class}, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
public class RecordsFunctionalSteps {
    @Autowired
    FunctionalTestHelper helper;
    private ResultActions page;

    @Autowired
    private GameRepository repository;

    @Given("^an empty game history$")
    public void cleanGameHistory() throws Exception {
        repository.deleteAllInBatch();
    }

    @When("^admin view the game records$")
    public void adminViewGameRecords() throws Exception {
        page = helper.getMvc().perform(post("/records"));
    }

    @Then("^the played game counts is: (\\d*)$")
    public void playedGameCountsIs(int count) throws Exception {
        helper.assertAtPage(page, "//li[string(.)='Played: " + count + "']");
    }
}
