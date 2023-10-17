package edu.hw3.task7;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    void testCustomComparator() {
        TreeMap<String, String> tree = new TreeMap<>(new NullTreeComparator());
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }
}
