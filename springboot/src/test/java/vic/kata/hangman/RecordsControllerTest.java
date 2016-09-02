package vic.kata.hangman;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RecordsControllerTest {
    @Mock
    private Model model;
    @Mock
    private RecordsService service;
    @InjectMocks
    RecordsController controller = new RecordsController();

    @Test
    public void should_show_records() throws Exception {
        //Given
        when(service.played()).thenReturn(5);
        //When
        String actual = controller.showRecords(model);
        //Then
        verify(model).addAttribute("played", 5);
    }
}
