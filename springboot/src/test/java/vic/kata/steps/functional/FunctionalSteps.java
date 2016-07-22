package vic.kata.steps.functional;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import vic.kata.hangman.GameConfiguration;
import vic.kata.hangman.GameService;
import vic.kata.hangman.SecretProvider;
import vic.kata.steps.WithSpringSteps;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
public class FunctionalSteps extends WithSpringSteps {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private ResultActions page;

    @Mock
    private SecretProvider provider;
    @Mock
    private GameConfiguration config;
    @InjectMocks @Autowired
    private GameService service;

    @cucumber.api.java.Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private void assertAtPage(String toMatch) throws Exception {
        page.andExpect(status().isOk())
            .andExpect(content().string(containsString(toMatch)));
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
        page = mvc.perform(post("/game"));
    }

    @Then("^the question is: (.*)$")
    public void verifyQuestion(String question) throws Exception {
        assertAtPage("Question: <span>" + question +"</span>");
    }

    @Then("^the tried is: (.*)$")
    public void verifyTried(String tried) throws Exception {
        assertAtPage("Tired: <span>" + tried +"</span>");
    }

    @Then("^chance is: (.*)$")
    public void verifyChance(int chance) throws Exception {
        assertAtPage("Chance: <span>" + chance +"</span>");
    }
}
