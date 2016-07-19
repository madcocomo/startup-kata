package vic.kata.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GameController {
    @Autowired
    private GameService service;

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String newGame(Model model) {
        Game game = service.startGame();
        model.addAttribute("game", game);
        return "game";
    }
}
