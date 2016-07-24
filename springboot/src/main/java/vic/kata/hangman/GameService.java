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
    private GameRegistry gameRegistry;

    public Game gameInstance(String sessionId) {
        if (gameRegistry.hasGameInProgress(sessionId)) {
            return gameRegistry.find(sessionId);
        }
        Game game = builder.createGame(configuration.getInitChance(),
                configuration.getInitTried(),
                toUpper(provider.getSecret()));
        gameRegistry.register(sessionId, game);
        return game;
    }

    private String toUpper(String secret) {
        return secret.toUpperCase();
    }
}
