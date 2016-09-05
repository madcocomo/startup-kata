package vic.kata.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameBuilder builder;
    @Autowired
    private GameConfiguration configuration;
    @Autowired
    private SecretProvider provider;
    @Autowired
    private GameRegistry inProgressGames;

    public Game inProgressGame(String sessionId) {
        if (inProgressGames.hasGameInProgress(sessionId)) {
            return inProgressGames.find(sessionId);
        }
        return null;
    }

    public Game startGame(String sessionId) {
        Game game = builder.createGame(configuration.getInitChance(),
                configuration.getInitTried(),
                provider.getSecret());
        inProgressGames.register(sessionId, game);
        return game;
    }
}
