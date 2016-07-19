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

    public GameInfo startGame() {
        return builder.createGame(configuration.getInitChance(),
                configuration.getInitTried(),
                provider.getSecret());
    }
}
