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


    public int won() {
        Long count = allGames.stream()
                .filter(Game::isWin).count();
        return count.intValue();
    }

    public int lost() {
        Long count = allGames.stream()
                .filter(Game::isLose).count();
        return count.intValue();
    }
}
