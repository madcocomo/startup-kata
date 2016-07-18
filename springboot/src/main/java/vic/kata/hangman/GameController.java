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

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String newGame(Model model) {
        GameInfo gameInfo = service.startGame();
        model.addAttribute("chance", gameInfo.getChance());
        model.addAttribute("tried", gameInfo.getTried());
        return "game";
    }
}
