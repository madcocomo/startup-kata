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

    public Game startGame() {
        return builder.createGame(configuration.getInitChance(),
                configuration.getInitTried(),
                toUpper(provider.getSecret()));
    }

    private String toUpper(String secret) {
        return secret.toUpperCase();
    }

    public Game retriveGame(String sessionId) {
        Game game = new Game(10,"AE"+"L","APPLE");
        return game;
    }
}
