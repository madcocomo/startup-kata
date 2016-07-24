package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
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
    @Mock
    private GameRegistry gameRegistry;
    @Mock
    private Game game;

    @InjectMocks
    private GameService service = new GameService();

    @Test
    public void should_create_game_if_not_registered() throws Exception {
        //Given
        when(config.getInitChance()).thenReturn(10);
        when(config.getInitTried()).thenReturn("AEO");
        when(secretProvider.getSecret()).thenReturn("hello");
        when(gameBuilder.createGame(10, "AEO", "HELLO")).thenReturn(game);

        when(gameRegistry.hasGameInProgress("sessionId")).thenReturn(false);
        //When
        service.gameInstance("sessionId");
        //Then
        verify(gameBuilder).createGame(10, "AEO", "HELLO");
        verify(gameRegistry).register("sessionId", game);
    }

    @Test
    public void should_retrieve_inprogress_game_if_registered() throws Exception {
        //Given
        when(gameRegistry.hasGameInProgress("sessionId")).thenReturn(true);
        when(gameRegistry.find("sessionId")).thenReturn(game);
        //When
        service.gameInstance("sessionId");
        verify(gameBuilder, never()).createGame(anyInt(), anyString(), anyString());
    }

}