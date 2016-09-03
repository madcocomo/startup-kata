package vic.kata.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordsService {
    @Autowired
    private GameRepository repository;

    public int played() {
        return repository.findAll().size();
    }
}
