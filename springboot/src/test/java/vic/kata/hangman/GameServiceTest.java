package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    @Mock
    private GameBuilder gameBuilder;
    @Mock
    private GameConfiguration config;
    @Mock
    private SecretProvider secretProvider;

    @InjectMocks
    private GameService service = new GameService();

    @Test
    public void should_set_init_info_when_start() throws Exception {
        //Given
        when(config.getInitChance()).thenReturn(10);
        when(config.getInitTried()).thenReturn("AEO");
        when(secretProvider.getSecret()).thenReturn("hello");
        //When
        service.startGame();
        //Then
        verify(gameBuilder).createGame(10, "AEO", "hello");
    }
}