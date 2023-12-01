package edu.hw8.task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import static java.util.Map.entry;

public class InsultDictionary {
    private static final HashMap<String, List<String>> INSULTS = new HashMap<>(Map.ofEntries(
        entry("личности", List.of(
            "Не переходи на личности там, где их нет"
        )),
        entry("оскорбления", List.of(
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
            "рандомное оскорбление"
        )),
        entry("глупый", List.of(
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        )),
        entry("интеллект", List.of(
            "Чем ниже интеллект, тем громче оскорбления"
        ))
    ));
    private static final String UNSUPPORTED_THEME_ERROR_MESSAGE = "This theme unsupported";

    private InsultDictionary() {
    }

    /**
     * Return random insult from dictionary by given theme
     *
     * @param theme - insult theme
     * @return random insult
     * @throws IllegalArgumentException if theme unsupported
     */
    public static String getInsultByTheme(String theme) throws IllegalArgumentException {
        List<String> insults = INSULTS.get(theme.toLowerCase());

        if (insults == null) {
            throw new IllegalArgumentException(UNSUPPORTED_THEME_ERROR_MESSAGE);
        }

        return insults.get(ThreadLocalRandom.current().nextInt(insults.size()));
    }

    public static List<String> getListOfInsultsByTheme(String theme) {
        List<String> result = INSULTS.get(theme.toLowerCase());

        if (result == null) {
            throw new IllegalArgumentException(UNSUPPORTED_THEME_ERROR_MESSAGE);
        }

        return new ArrayList<>(result);
    }
}
