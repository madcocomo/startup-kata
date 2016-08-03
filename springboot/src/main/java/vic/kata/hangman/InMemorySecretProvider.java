package vic.kata.hangman;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@ConfigurationProperties(prefix = "local")
public class InMemorySecretProvider implements SecretProvider {
    private List<String> secrets = new ArrayList<>();
    Random random = new Random();

    @Override
    public String getSecret() {
        if (secrets.isEmpty()) return "ERROR";
        int index = random.nextInt(secrets.size());
        return secrets.get(index);
    }

    public List<String> getSecrets() {
        return secrets;
    }
}
