package vic.kata.steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vic.kata.hangman.HangmanApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HangmanApplication.class, loader = SpringApplicationContextLoader.class)
abstract public class WithSpringSteps {
    @Test
    public void suppress_junit_complain() throws Exception { }
}
