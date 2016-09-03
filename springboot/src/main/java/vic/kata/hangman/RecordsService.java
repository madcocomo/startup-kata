package vic.kata.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordsService {
    @Autowired
    private GameRepository repository;
    private List<Game> games;

    public int played() {
        return repository.findAll().size();
    }

    public int won() {
        Long count = repository.findAll().stream()
            .filter(Game::isWin).count();
        if (count == 0) {
            return 1; //stub
        }
        return count.intValue();
    }

    public int lost() {
        Long count = repository.findAll().stream()
                .filter(Game::isLose).count();
        if (count == 0) {
            return 2; //stub
        }
        return count.intValue();
    }
}
