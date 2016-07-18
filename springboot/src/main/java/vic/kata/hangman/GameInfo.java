package vic.kata.hangman;

public class GameInfo {
    private int chance;
    private String tried;

    public GameInfo(int chance, String tried) {
        this.chance = chance;
        this.tried = tried;
    }

    public int getChance() {
        return chance;
    }

    public String getTried() {
        return tried;
    }
}
