package vic.kata.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecordsController {
    @Autowired
    private RecordsService service;

    @RequestMapping("/records")
    public String showRecords(Model model) {
        model.addAttribute("played", service.played());
        model.addAttribute("won", service.won());
        model.addAttribute("lost", service.lost());
        return "records";
    }
}
