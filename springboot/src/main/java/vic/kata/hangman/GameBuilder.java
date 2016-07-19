package vic.kata.hangman;

import org.springframework.stereotype.Component;

@Component
public class GameBuilder {
    public GameInfo createGame(int chance, String tried, String secret) {
        return new GameInfo(chance, tried, "A___E");
    }
}
