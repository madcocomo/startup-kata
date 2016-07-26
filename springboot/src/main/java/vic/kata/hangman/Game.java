package vic.kata.hangman;

public class Game {
    private int chance;
    private String tried;
    private String secret;

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
        tried += standardized;
        if (!secret.contains(standardized)) {
            chance -= 1;
        }
    }

    private String standardize(String letter) {
        return letter.toUpperCase();
    }
}
