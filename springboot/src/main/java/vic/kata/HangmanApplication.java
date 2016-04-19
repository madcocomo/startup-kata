package vic.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class HangmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HangmanApplication.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "hangman";
	}

	@RequestMapping("/error")
	public String error() {
		return "hangman error";
	}
}
