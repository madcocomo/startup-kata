package vic.kata.hangman;

public class Game {
    private int chance;
    private String tried;
    private String secret;

    public Game(int chance, String tried, String secret) {
        this.chance = chance;
        this.tried = tried;
        this.secret = secret;
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
            mask = "[^"+tried+"]";
        }
        return secret.replaceAll(mask, "_");
    }
}
