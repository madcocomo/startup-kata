package vic.kata.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordsService {
    @Autowired
    private GameRepository repository;

    public GameRecords statistic() {
        return new GameRecords(repository.findAll());
    }
}
