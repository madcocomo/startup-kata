package vic.kata.hangman;

import java.util.List;


public class GameRecords {
    private final List<Game> allGames;

    public GameRecords(List<Game> allGames) {
        this.allGames = allGames;
    }

    public int played() {
        return allGames.size();
    }


    public long won() {
        return allGames.stream().filter(Game::isWin).count();
    }

    public long lost() {
        return allGames.stream().filter(Game::isLose).count();
    }
}
