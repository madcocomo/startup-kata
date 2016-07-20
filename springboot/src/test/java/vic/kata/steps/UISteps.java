package vic.kata.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vic.kata.hangman.HangmanApplication;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HangmanApplication.class, loader = SpringApplicationContextLoader.class)
@WebIntegrationTest(randomPort = true)
public class UISteps {
    WebDriver driver;

    @cucumber.api.java.Before
    public void setup() {
        //TODO: set in test propriety file
        System.setProperty("webdriver.chrome.driver", "C:\\ProgramData\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @cucumber.api.java.After
    public void tearDown() {
        driver.close();
    }

    @Value("http://localhost:${local.server.port}")
    private String url;

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
        assertEquals("Form action", url+"/game", form.getAttribute("action"));
        String buttonText = form.findElement(By.tagName("input")).getAttribute("value");
        assertEquals("button text", "Start Game", buttonText);

    }


}
