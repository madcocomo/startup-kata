package vic.kata.hangman;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HangmanApplication.class)
public class GameControllerTest {
    @Mock
    private GameService mockService;
    @Mock
    private Model model;
    @Mock
    private GameInfo gameInfo;
    @Autowired @InjectMocks
    private GameController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(mockService.startGame()).thenReturn(gameInfo);
    }

    @Test
    public void testHome() throws Exception {
        //TODO is Home really a method of game controller?
        String actual = controller.home();
        assertEquals("Not very useful test", "home", actual);
    }

    @Test
    public void testNewGame() throws Exception {
        //Given
        when(gameInfo.getChance()).thenReturn(10);
        when(gameInfo.getTried()).thenReturn("IOU");
        //When
        String actual = controller.newGame(model);
        //Then
        assertEquals("Not very useful again", "game", actual);
        verify(mockService).startGame();
        verify(model).addAttribute("chance", 10);
        verify(model).addAttribute("tried", "IOU");
    }
}