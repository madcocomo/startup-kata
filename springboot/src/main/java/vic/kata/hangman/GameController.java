package vic.kata.hangman;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
public class GameController {
    @RequestMapping("/")
    public String home() {
        return "<html><head/><body>" +
                "<h1>Hangman</h1>" +
                "<form action='game' method='post'><input type='submit'value='Start Game'/></form>" +
                "</body></html>";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String newGame() {
        return "<html><head/><body>" +
                "<h1>Hangman</h1>" +
                "<ul>" +
                "<li>Question: A___E</li>" +
                "<li>Tired: AEIOU</li>" +
                "<li>Chance: 12</li>" +
                "</ul>" +
                "</body></html>";
    }
}
