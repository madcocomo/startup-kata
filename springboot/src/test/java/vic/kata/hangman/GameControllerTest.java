package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {
    @Mock
    private GameService service;
    @Mock
    private GameRepository repository;
    @Mock
    private Model model;
    @Mock
    private Game game;
    @Mock
    private HttpSession session;
    @InjectMocks
    private GameController controller = new GameController();

    @Test
    public void should_create_game_and_save() throws Exception {
        //Given
        when(session.getId()).thenReturn("newSessionId");
        when(service.inProgressGame("newSessionId")).thenReturn(null);
        when(service.startGame("newSessionId")).thenReturn(game);
        //When
        String actual = controller.newGame(model, session);
        //Then
        assertEquals("show game page", "redirect:/game", actual);
        verify(service).startGame("newSessionId");
        verify(repository).save(game);
    }

    @Test
    public void should_call_game_guess_and_save() throws Exception {
        //Given
        when(session.getId()).thenReturn("sessionId");
        when(service.inProgressGame("sessionId")).thenReturn(game);
        //When
        String actual = controller.guessLetter("X", model, session);
        //Then
        assertEquals("forward to game page when playing", "redirect:/game", actual);
        verify(service).inProgressGame("sessionId");
        InOrder inOrder = inOrder(repository, game);
        inOrder.verify(game).guess("X");
        inOrder.verify(repository).save(game);
    }

    @Test
    public void should_redirected_to_home_if_playing_nonexists_game() throws Exception {
        //Given
        when(session.getId()).thenReturn("newSessionId");
        when(service.inProgressGame("newSessionId")).thenReturn(null);
        //When
        String actual = controller.guessLetter("X", model, session);
        //Then
        assertEquals("back to home page", "redirect:/", actual);
    }

    @Test
    public void should_redirected_to_home_if_playing_ended_game() throws Exception {
        //Given
        when(session.getId()).thenReturn("sessionId");
        when(service.inProgressGame("sessionId")).thenReturn(game);
        when(game.isEnd()).thenReturn(true);
        //When
        String actual = controller.guessLetter("X", model, session);
        //Then
        assertEquals("back to home page", "redirect:/", actual);
    }
}