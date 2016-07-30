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
public class FunctionalSteps {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private MockHttpSession session = new MockHttpSession();
    private ResultActions page;

    @Mock
    private SecretProvider provider;
    @Mock
    private GameConfiguration config;
    @InjectMocks @Autowired
    private GameService service;

    @cucumber.api.java.Before("@functional")
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private void assertAtPage(String toMatch) throws Exception {
        page.andExpect(status().isOk())
            .andExpect(content().node(hasXPath(toMatch)));
    }

    private void assertNotAtPage(String toMatch) throws Exception {
        page.andExpect(status().isOk())
            .andExpect(content().node(not(hasXPath(toMatch))));
    }

    private void assertAtPageOnly(boolean isTrue, String xpath) throws Exception {
        if (isTrue) {
            assertAtPage(xpath);
        } else {
            assertNotAtPage(xpath);
        }
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
        page = mvc.perform(post("/game").session(session));
    }

    @Then("^the question is: (.*)$")
    public void verifyQuestion(String question) throws Exception {
        assertAtPage("//li[string(.)='Question: "+ question +"']");
    }

    @Then("^the tried is: (.*)$")
    public void verifyTried(String tried) throws Exception {
        assertAtPage("//li[string(.)='Tried: "+ tried +"']");
    }

    @Then("^chance is: (.*)$")
    public void verifyChance(int chance) throws Exception {
        assertAtPage("//li[string(.)='Chance: "+ chance +"']");
    }

    @When("^player input: (.)$")
    public void inputLetter(String l) throws Exception {
        page = mvc.perform(post("/guess").session(session).param("letter", l));
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
        assertAtPageOnly(state == GameState.Playing, "//form[@action='guess']");
        assertAtPageOnly(state != GameState.Playing, "//form[@action='game']");
        assertAtPageOnly(state == GameState.Win, "//h1[text()='You Win']");
        assertAtPageOnly(state == GameState.Lose, "//h1[text()='No Luck']");
    }

}
