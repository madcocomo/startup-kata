package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
        GameRecords gameRecords = service.statistic();
        //Then
        assertEquals("played", 3, gameRecords.played());
        assertEquals("won", 2, gameRecords.won());
        assertEquals("lost", 1, gameRecords.lost());
        verify(repository, times(1)).findAll();
    }
}