package vic.kata.steps.functional;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Rule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import vic.kata.hangman.GameConfiguration;
import vic.kata.hangman.GameService;
import vic.kata.hangman.HangmanApplication;
import vic.kata.hangman.SecretProvider;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ContextConfiguration(classes = {HangmanApplication.class, FunctionalTestHelper.class}, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
public class GameFunctionalSteps {
    private MockHttpSession session = new MockHttpSession();
    private ResultActions page;

    @Mock
    private SecretProvider provider;
    @Mock
    private GameConfiguration config;
    @InjectMocks @Autowired
    private GameService service;

    @Autowired
    FunctionalTestHelper helper;

    @cucumber.api.java.Before("@functional")
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Given("^the secret is: (.*)$")
    public void initSecret(String secret) {
        given(provider.getSecret()).willReturn(secret);
    }

    @Given("^the tried at start is: (.*)$")
    public void initTried(String tried) {
        given(config.getInitTried()).willReturn(tried);

    }
    @Given("^the chance at start is: (\\d*)$")
    public void initTried(int chance) {
        given(config.getInitChance()).willReturn(chance);

    }

    @When("^player start a new game$")
    public void startGame() throws Exception {
        page = helper.getMvc().perform(post("/game").session(session));
    }

    @Then("^the question is: (.*)$")
    public void verifyQuestion(String question) throws Exception {
        helper.assertAtPage(page, "//li[string(.)='Question: "+ question +"']");
    }

    @Then("^the tried is: (.*)$")
    public void verifyTried(String tried) throws Exception {
        helper.assertAtPage(page, "//li[string(.)='Tried: "+ tried +"']");
    }

    @Then("^chance is: (.*)$")
    public void verifyChance(int chance) throws Exception {
        helper.assertAtPage(page, "//li[string(.)='Chance: "+ chance +"']");
    }

    @When("^player input: (.)$")
    public void inputLetter(String l) throws Exception {
        page = helper.getMvc().perform(post("/guess").session(session).param("letter", l));
    }
    enum GameState {
        Playing, Win, Lose
    }
    class GameStep {
        String guess;
        String question;
        String tried;
        int chance;
        GameState state;
    }

    @Then("^game in progress:$")
    public void gameInProgress(List<GameStep> progress) throws Exception {
        for (GameStep step : progress) {
            inputLetter(step.guess);
            verifyQuestion(step.question);
            verifyTried(step.tried);
            verifyChance(step.chance);
            verifyGameState(step.state);
        }
    }

    private void verifyGameState(GameState state) throws Exception {
        helper.assertAtPageOnly(page, state == GameState.Playing, "//form[@action='guess']", this);
        helper.assertAtPageOnly(page, state != GameState.Playing, "//form[@action='game']", this);
        helper.assertAtPageOnly(page, state != GameState.Playing, "//a[@href='records']", this);
        helper.assertAtPageOnly(page, state == GameState.Win, "//h1[text()='You Win']", this);
        helper.assertAtPageOnly(page, state == GameState.Lose, "//h1[text()='No Luck']", this);
    }

}
