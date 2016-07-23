package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {
    @Mock
    private GameService mockService;
    @Mock
    private Model model;
    @Mock
    private Game game;
    @InjectMocks
    private GameController controller = new GameController();

    @Test
    public void testNewGame() throws Exception {
        //Given
        when(mockService.startGame()).thenReturn(game);
        //When
        String actual = controller.newGame(model);
        //Then
        assertEquals("Not very useful again", "game", actual);
        verify(mockService).startGame();
        verify(model).addAttribute("game", game);
    }

    @Test
    public void testGuessLetter() throws Exception {
        //Given
        when(mockService.retriveGame("sessionId")).thenReturn(game);
        //When
        //TODO validate the letter
        String actual = controller.guessLetter("X", model);
        //Then
        assertEquals("forward to game page when playing", "game", actual);
        //TODO where to get session ID?
        verify(mockService).retriveGame("sessionId");
        verify(game).guess("X");
        verify(model).addAttribute("game", game);
    }
}