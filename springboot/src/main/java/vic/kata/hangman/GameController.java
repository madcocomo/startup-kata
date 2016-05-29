package vic.kata.hangman;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class GameController {
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String newGame() {
        return "game1";
    }
}
