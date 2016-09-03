package vic.kata.hangman;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Game {
    public static final String VALIDATE_PATTERN = "[A-Z]";

    @Id
    @GeneratedValue
    private Long id;

    private int chance;
    private String tried;
    private String secret;

    public Game() {}

    public Game(int chance, String tried, String secret) {
        this.chance = chance;
        this.tried = standardize(tried);
        this.secret = standardize(secret);
    }

    public int getChance() {
        return chance;
    }

    public String getTried() {
        return tried;
    }

    public String getQuestion() {
        String mask = ".";
        if (!tried.isEmpty()) {
            mask = String.format("[^%s]", tried);
        }
        return secret.replaceAll(mask, "_");
    }

    public void guess(String letter) {
        String standardized = standardize(letter);
        //Smell ...
        if (skipInvalidateLetter(standardized)) {
            return;
        }
        if (neverTriedBefore(standardized)) {
            tried += standardized;
        }
        if (guessWrong(standardized)) {
            chance -= 1;
        }
    }

    private boolean guessWrong(String standardized) {
        return !secret.contains(standardized);
    }

    private boolean neverTriedBefore(String standardized) {
        return !tried.contains(standardized);
    }

    private boolean skipInvalidateLetter(String standardized) {
        return !standardized.matches(VALIDATE_PATTERN);
    }

    private String standardize(String letter) {
        return letter.toUpperCase();
    }

    public boolean isEnd() {
        return isWin() || isLose();
    }

    public boolean isWin() {
        return getQuestion().equals(secret);
    }

    public boolean isLose() {
        return chance == 0;
    }
}
