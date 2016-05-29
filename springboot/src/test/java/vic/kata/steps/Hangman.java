package vic.kata.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.WebUtils;
import vic.kata.hangman.GameController;
import vic.kata.hangman.HangmanApplication;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class Hangman {

    private GameController gameController = new GameController();
    private MockMvc mvc = MockMvcBuilders.standaloneSetup(gameController).build();
    private ResultActions page;

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
        assertAtPage("<form action='game' method='post'>");
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
        //pageContent = gameController.newGame();
        page = mvc.perform(post("/game"));
    }

    @Then("^the question is: (.*)$")
    public void verifyQuestion(String question) throws Exception {
        assertAtPage(question);
    }

    @Then("^the tried is: (.*)$")
    public void verifyTried(String tried) throws Exception {
        assertAtPage("Tired: " + tried);
    }

    @Then("^chance is: (.*)$")
    public void verifyChance(int chance) throws Exception {
        assertAtPage("Chance: " + chance);
    }
}
