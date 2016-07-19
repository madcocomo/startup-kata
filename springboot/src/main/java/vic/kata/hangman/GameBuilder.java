package vic.kata.hangman;

import org.springframework.stereotype.Component;

@Component
public class GameBuilder {
    public Game createGame(int chance, String tried, String secret) {
        return new Game(chance, tried, secret);
    }
}
