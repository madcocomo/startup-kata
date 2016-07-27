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

    @Test
    public void game_should_take_care_the_case_because_it_knows() throws Exception {
        Game game = new Game(5, "e", "HEllo");
        game.guess("h");
        assertEquals("Upper case secret", "HE___", game.getQuestion());
        assertEquals("tried", "EH", game.getTried());
        assertEquals("no change on chance", 5, game.getChance());
    }

    //TODO: A Rule object to decide the chance and tired recording?
    @Test
    public void should_not_remember_duplicate_tries_but_reduce_chance_each_wrong() throws Exception {
        Game game = new Game(5, "E", "HELLO");
        game.guess("X");
        game.guess("x");
        assertEquals("tried", "EX", game.getTried());
        assertEquals("always chance-- even has tried", 3, game.getChance());
    }

    @Test
    public void telerate_to_invalidate_input() throws Exception {
        Game game = new Game(5, "E", "HELLO");
        game.guess("*");
        assertEquals("tried not changed", "E", game.getTried());
        assertEquals("chance not changed", 5, game.getChance());
    }
}