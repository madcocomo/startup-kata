package vic.kata.hangman;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="game")
public class GameConfiguration {

    private int initChance;
    private String initTried;

    public int getInitChance() {
        return initChance;
    }

    public void setInitChance(int initChance) {
        this.initChance = initChance;
    }

    public String getInitTried() {
        return initTried;
    }

    public void setInitTried(String initTried) {
        this.initTried = initTried;
    }
}
