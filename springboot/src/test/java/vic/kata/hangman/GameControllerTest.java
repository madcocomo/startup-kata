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
    public void testHome() throws Exception {
        //TODO is Home really a method of game controller?
        String actual = controller.home();
        assertEquals("Not very useful test", "home", actual);
    }

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

}