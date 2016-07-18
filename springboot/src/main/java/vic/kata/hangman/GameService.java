package vic.kata.hangman;

import org.springframework.stereotype.Service;

@Service
public class GameService {
    public GameInfo startGame() {
        return new GameInfo(12, "AEIOU");
    }
}
