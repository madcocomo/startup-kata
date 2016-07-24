package vic.kata.hangman;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameRegistry {
    Map<String, Game> gameMap = new HashMap<>();

    public Game find(String sessionId) {
        return gameMap.get(sessionId);
    }

    public boolean hasGameInProgress(String sessionId) {
        return gameMap.containsKey(sessionId);
    }

    public void register(String sessionId, Game game) {
        gameMap.put(sessionId, game);

    }
}
