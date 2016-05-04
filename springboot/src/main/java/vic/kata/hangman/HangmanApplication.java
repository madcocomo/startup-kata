package vic.kata.hangman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HangmanApplication {
	public static void main(String[] args) {
		SpringApplication.run(HangmanApplication.class, args);
	}

}
