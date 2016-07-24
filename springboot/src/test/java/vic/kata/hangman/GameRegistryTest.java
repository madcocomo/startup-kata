package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class GameRegistryTest {
    GameRegistry registry = new GameRegistry();
    @Mock
    private Game game;

    @Test
    public void should_not_inprogress_before_register() throws Exception {
        assertFalse(registry.hasGameInProgress("sessionX"));
    }

    @Test
    public void should_has_gameInProgress_after_register() throws Exception {
        registry.register("sessionX", game);
        assertTrue("game in progress", registry.hasGameInProgress("sessionX"));
        assertSame("find registered game", game, registry.find("sessionX"));
    }
}