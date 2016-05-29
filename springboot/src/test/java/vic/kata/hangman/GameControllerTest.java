package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HangmanApplication.class)
public class GameControllerTest {
    @Autowired
    private GameController game;

    @Test
    public void testHome() throws Exception {
        String actual = game.home();
        assertTrue(actual, actual.contains("<h1>Hangman</h1>"));
    }
}