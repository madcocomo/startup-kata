package vic.kata.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class GameController {
    @Autowired
    private GameService service;

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String newGame(Model model, HttpSession session) {
        String sessionId = session.getId();
        Game game = service.gameInstance(sessionId);
        model.addAttribute("game", game);
        return "game";
    }

    @RequestMapping(value = "/guess", method = RequestMethod.POST)
    public String guessLetter(String letter, Model model, HttpSession session) {
        String sessionId = session.getId();
        Game game = service.gameInstance(sessionId);
        game.guess(letter);
        model.addAttribute("game", game);
        return "game";
    }
}
