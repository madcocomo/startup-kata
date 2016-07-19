package vic.kata.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import vic.kata.hangman.HangmanApplication;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HangmanApplication.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
public class Hangman {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    private ResultActions page;

    @cucumber.api.java.Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void suppress_junit_complain() throws Exception { }

    @When("^player open home page$")
    public void openHomePage() throws Exception {
        page = mvc.perform(get("/"));
    }

    @Then("^the game name is display$")
    public void gameNameDisplay() throws Exception {
        assertAtPage("Hangman");
    }

    @Then("^player can start a new game$")
    public void playerCanOpenGame() throws Exception {
        assertAtPage("<form action=\"game\" method=\"post\">");
    }

    private void assertAtPage(String toMatch) throws Exception {
        page.andExpect(status().isOk())
            .andExpect(content().string(containsString(toMatch)));
    }

    @Given("^the secret is: (.*)$")
    public void initSecret(String secret) {
    }

    @Given("^the tried at start is: (.*)$")
    public void initTried(String tried) {
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
