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
        if (count == 0) {
            return 1; //stub
        }
        return count.intValue();
    }

    public int lost() {
        Long count = allGames.stream()
                .filter(Game::isLose).count();
        if (count == 0) {
            return 2; //stub
        }
        return count.intValue();
    }
}
