package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecordsServiceTest {
    @Mock
    private GameRepository repository;
    @InjectMocks
    private RecordsService service = new RecordsService();

    private Game loseGame() {
        Game game = mock(Game.class);
        when(game.isLose()).thenReturn(true);
        return game;
    }

    private Game winGame() {
        Game game = mock(Game.class);
        when(game.isWin()).thenReturn(true);
        return game;
    }

    @Test
    public void should_count_game_from_repository() throws Exception {
        //Given
        List<Game> playedGames = Arrays.asList(
                winGame(), winGame(), loseGame());
        when(repository.findAll()).thenReturn(playedGames);
        //When
        //Then
        assertEquals("played", 3, service.played());
        assertEquals("won", 2, service.won());
        assertEquals("lost", 1, service.lost());
    }
}