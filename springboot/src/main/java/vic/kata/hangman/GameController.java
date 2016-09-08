package vic.kata.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class GameController {
    @Autowired
    private GameService service;
    @Autowired
    private GameRepository repository;

    @ModelAttribute
    public Game getInProgressGame(HttpSession session) {
        return service.inProgressGame(session.getId());
    }

    @RequestMapping(value = "/game")
    public String showGame(Game game) {
        return "game";
    }

    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public String newGame(HttpSession session) {
        Game game = service.startGame(session.getId());
        repository.save(game);
        return "redirect:/game";
    }

    @RequestMapping(value = "/guess", method = RequestMethod.POST)
    public String guessLetter(String letter, Game game) {
        if (game == null || game.isEnd()) {
            return "redirect:/";
        }
        game.guess(letter);
        repository.save(game);
        return "redirect:/game";
    }
}
