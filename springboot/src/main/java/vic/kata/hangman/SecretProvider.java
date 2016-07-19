package vic.kata.hangman;

import org.springframework.stereotype.Component;

@Component
public class SecretProvider {
    public String getSecret() {
        return "BANANA";
    }
}
