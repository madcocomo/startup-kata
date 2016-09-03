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
    @Test
    public void should_count_game_from_repository() throws Exception {
        //Given
        List<Game> playedGames = Arrays.asList(
            mock(Game.class), mock(Game.class), mock(Game.class));
        when(repository.findAll()).thenReturn(playedGames);
        //When
        int actual = service.played();
        //Then
        assertEquals(3, actual);

    }
}