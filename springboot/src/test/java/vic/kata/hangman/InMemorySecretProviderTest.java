package vic.kata.hangman;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class InMemorySecretProviderTest {

    private InMemorySecretProvider provider;
    private List<String> secretList;

    @Before
    public void setUp() throws Exception {
        provider = new InMemorySecretProvider();
        secretList = Arrays.asList(
                "APPLE", "ORANGE", "WATERMELON", "CHERRY");
        provider.getSecrets().addAll(secretList);
    }

    @Test
    public void should_get_secret_from_list() throws Exception {
        String actual = provider.getSecret();
        assertTrue("should in list", secretList.contains(actual));
    }

    @Test
    public void should_equally_select_secrets() throws Exception {
        Map<String, Integer> counts = new HashMap<>();
        int sampleTime = 1000;
        for (int i = 0; i < sampleTime; i++) {
            String actual = provider.getSecret();
            Integer previous_count = 0;
            if (counts.containsKey(actual)) {
                previous_count = counts.get(actual);
            }
            counts.put(actual, previous_count+1);
        }

        int ideallyTime = sampleTime / secretList.size();
        int varance = sampleTime / 20;
        for (String word : secretList) {
            assertTrue("should almost equal times, but is " + counts.get(word),
                counts.get(word) - ideallyTime < varance);
        }
    }
}