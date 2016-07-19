package vic.kata.hangman;

import org.springframework.stereotype.Component;

@Component
public class GameConfiguration {
    public int getInitChance() {
        return 0;
    }

    public String getInitTried() {
        return "";
    }
}
