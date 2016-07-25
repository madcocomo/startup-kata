package vic.kata.hangman;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void new_game_should_present_init_settings() throws Exception {
        Game game = new Game(5, "DIY", "IDONTCARE");
        assertEquals("init chance", 5, game.getChance());
        assertEquals("init tried", "DIY", game.getTried());
    }

    @Test
    public void should_show_underline_if_not_tried() throws Exception {
        Game game = new Game(5, "", "HELLO");
        assertEquals("question", "_____", game.getQuestion());
    }

    @Test
    public void should_show_underline_excepts_tried() throws Exception {
        Game game = new Game(5, "O", "HELLO");
        assertEquals("question", "____O", game.getQuestion());
    }

    @Test
    public void should_change_tried_after_guess() throws Exception {
        Game game = new Game(5, "O", "HELLO");
        game.guess("E");
        assertEquals("tried", "OE", game.getTried());
        assertEquals("changed by tried", "_E__O", game.getQuestion());
        assertEquals("no change on chance", 5, game.getChance());
    }

    @Test
    public void should_reduce_chance_if_guess_wrong() throws Exception {
        Game game = new Game(5, "O", "HELLO");
        game.guess("F");
        assertEquals("tried", "OF", game.getTried());
        assertEquals("changed by tried", "____O", game.getQuestion());
        assertEquals("chance--", 4, game.getChance());
    }
}