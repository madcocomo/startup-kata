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
        GameRecords records = service.statistic();
        model.addAttribute("played", records.played());
        model.addAttribute("won", records.won());
        model.addAttribute("lost", records.lost());
        return "records";
    }
}
