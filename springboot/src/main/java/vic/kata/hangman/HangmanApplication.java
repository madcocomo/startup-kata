package vic.kata.hangman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class HangmanApplication {
	public static void main(String[] args) {
		SpringApplication.run(HangmanApplication.class, args);
	}

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/records")
    public String records() {
        return "records";
    }
}
