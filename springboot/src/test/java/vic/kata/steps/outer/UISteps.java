package vic.kata.steps.outer;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import vic.kata.hangman.HangmanApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = HangmanApplication.class, loader = SpringApplicationContextLoader.class)
@WebIntegrationTest(randomPort = true)
public class UISteps {
    WebDriver driver;
    @Value("http://localhost:${local.server.port}")
    private String url;

    @cucumber.api.java.Before("@UI")
    public void setup() {
        //TODO: set in test propriety file
        //System.setProperty("webdriver.chrome.driver", "C:\\ProgramData\\chromedriver.exe");
        //driver = new ChromeDriver();
        driver = new HtmlUnitDriver();
    }

    @cucumber.api.java.After
    public void tearDown() {
        driver.close();
    }

    @When("^player open home page$")
    public void openHomePage() throws Exception {
        driver.get(url);
    }

    @Then("^player can see the game name$")
    public void gameNameDisplay() throws Exception {
        WebElement title = driver.findElement(By.name("title"));
        assertEquals("home page title", "Hangman", title.getText());
    }

    @Then("^player can start a new game$")
    public void playerCanOpenGame() throws Exception {
        WebElement form = driver.findElement(By.id("start-game"));
        assertEquals("Form action", "game", form.getAttribute("action"));
        String buttonText = form.findElement(By.tagName("input")).getAttribute("value");
        assertEquals("button text", "Start Game", buttonText);
    }

    @When("^player open game page$")
    public void playerOpenGamePage() throws Exception {
        driver.get(url);
        WebElement form = driver.findElement(By.id("start-game"));
        form.submit();
    }

    @Then("^player could guess the word$")
    public void playerCouldGuessWord() throws Exception {
        WebElement form = driver.findElement(By.id("guess"));
        assertEquals("Form action", "guess", form.getAttribute("action"));
        WebElement input = form.findElement(By.name("letter"));
        assertNotNull("input", input);
        assertEquals("input single letter", "1", input.getAttribute("maxlength"));
        assertNotNull("auto focused, in HTML5", input.getAttribute("autofocus"));
    }
}
