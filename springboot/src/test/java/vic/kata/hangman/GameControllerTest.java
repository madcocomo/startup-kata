package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

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
    @Mock
    private HttpSession session;
    @InjectMocks
    private GameController controller = new GameController();

    @Test
    public void testNewGame() throws Exception {
        //Given
        when(session.getId()).thenReturn("newSessionId");
        when(mockService.gameInstance("newSessionId")).thenReturn(game);
        //When
        String actual = controller.newGame(model, session);
        //Then
        assertEquals("Not very useful again", "game", actual);
        verify(mockService).gameInstance("newSessionId");
        verify(model).addAttribute("game", game);
    }

    @Test
    public void testGuessLetter() throws Exception {
        //Given
        when(session.getId()).thenReturn("sessionId");
        when(mockService.gameInstance("sessionId")).thenReturn(game);
        //When
        //TODO validate the letter
        String actual = controller.guessLetter("X", model, session);
        //Then
        assertEquals("forward to game page when playing", "game", actual);
        verify(mockService).gameInstance("sessionId");
        verify(game).guess("X");
        verify(model).addAttribute("game", game);
    }
}