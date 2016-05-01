package vic.kata.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import vic.kata.HangmanApplication;

import static org.junit.Assert.assertTrue;

public class Hangman {

    private String pageContent;
    //@Autowired
    private HangmanApplication application = new HangmanApplication();

    @When("^player open home page$")
    public void openHomePage() {
        pageContent = application.home();
    }

    @Then("^the game name is display$")
    public void gameNameDisplay() {
        assertTrue(pageContent, pageContent.contains("Hangman"));
    }

    @Then("^player can start a new game$")
    public void playerCanOpenGame() {
        assertTrue(pageContent, pageContent.matches(".*<form action='game' method='post'>.*"));
    }

    @Given("^the secret is: (.*)$")
    public void initSecret(String secret) {
    }

    @Given("^the tried at start is: (.*)$")
    public void initTried(String tried) {
    }

    @When("^player start a new game$")
    public void startGame() {
        pageContent = application.newGame();
    }

    @Then("^the question is: (.*)$")
    public void verifyQuestion(String question) {
        assertTrue(pageContent, pageContent.matches(
            ".*"+question+".*"));
    }

    @Then("^the tried is: (.*)$")
    public void verifyTried(String tried) {

    }

    @Then("^chance is: (.*)$")
    public void verifyChance(int chance) {

    }
}
