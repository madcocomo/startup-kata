package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertSame;
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

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService service = new GameService();

    @Test
    public void should_create_game_if_not_registered() throws Exception {
        //Given
        when(config.getInitChance()).thenReturn(10);
        when(config.getInitTried()).thenReturn("AEO");
        when(secretProvider.getSecret()).thenReturn("hello");
        when(gameBuilder.createGame(10, "AEO", "hello")).thenReturn(game);
        //When
        service.startGame("sessionId");
        //Then
        verify(gameBuilder).createGame(10, "AEO", "hello");
        verify(gameRegistry).register("sessionId", game);
    }

    @Test
    public void should_retrieve_inProgress_game_if_registered() throws Exception {
        //Given
        when(gameRegistry.hasGameInProgress("sessionId")).thenReturn(true);
        when(gameRegistry.find("sessionId")).thenReturn(game);
        //When
        Game actual = service.inProgressGame("sessionId");
        //Then
        assertSame(game, actual);
        verify(gameRegistry).find("sessionId");
        verify(gameBuilder, never()).createGame(anyInt(), anyString(), anyString());
    }

    @Test
    public void should_record_game_when_start() throws Exception {
        //Given
        when(gameBuilder.createGame(anyInt(), anyString(), anyString()))
            .thenReturn(game);
        //When
        service.startGame("irrelevant");
        //Then
        verify(gameRepository).save(game);
    }
}