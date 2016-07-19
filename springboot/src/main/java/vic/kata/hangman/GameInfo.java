package vic.kata.hangman;

public class GameInfo {
    private int chance;
    private String tried;
    private String question;

    public GameInfo(int chance, String tried, String question) {
        this.chance = chance;
        this.tried = tried;
        this.question = question;
    }

    public int getChance() {
        return chance;
    }

    public String getTried() {
        return tried;
    }

    public String getQuestion() {
        return question;
    }
}
