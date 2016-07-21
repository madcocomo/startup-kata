package vic.kata.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vic.kata.hangman.HangmanApplication;
import vic.kata.hangman.SecretProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HangmanApplication.class, loader = SpringApplicationContextLoader.class)
@WebIntegrationTest(randomPort = true)
public class UISteps {
    WebDriver driver;
    @Value("http://localhost:${local.server.port}")
    private String url;

    //TODO UI feature and functional feature should run in separated context
    //provide mock value as workaround
    @Autowired
    private SecretProvider provider;

    @cucumber.api.java.Before
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

    @Test
    public void suppress_junit_complain() throws Exception { }


    @When("^player open home page$")
    public void openHomePage() throws Exception {
        driver.get(url);
    }

    @Then("^the game name is display$")
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
        given(provider.getSecret()).willReturn("whatever");
        driver.get(url);
        WebElement form = driver.findElement(By.id("start-game"));
        form.submit();
    }

    @Then("^player could guess the word$")
    public void playerCouldGuessWord() throws Exception {
        WebElement form = driver.findElement(By.id("guess"));
        assertEquals("Form action", "guess", form.getAttribute("action"));
        WebElement input = form.findElement(By.xpath("input[@type='text']"));
        assertNotNull("input", input);
    }

}
