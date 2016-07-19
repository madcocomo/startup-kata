package vic.kata.hangman;

import org.springframework.stereotype.Component;

@Component
public class GameConfiguration {
    public int getInitChance() {
        return 12;
    }

    public String getInitTried() {
        return "AEIOU";
    }
}
