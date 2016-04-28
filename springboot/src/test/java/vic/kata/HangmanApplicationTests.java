package vic.kata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import vic.kata.HangmanApplication;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HangmanApplication.class)
@WebAppConfiguration
public class HangmanApplicationTests {

	@Test
	public void contextLoads() {
		assertEquals("should not fail", 1, 1);
	}

}
