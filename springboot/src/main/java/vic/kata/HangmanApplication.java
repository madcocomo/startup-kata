package vic.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HangmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HangmanApplication.class, args);
	}

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
				"<h2>Question: A___E</h2>" +
				"</body></html>";
	}

}
