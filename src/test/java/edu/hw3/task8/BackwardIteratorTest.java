package edu.hw3.task8;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BackwardIteratorTest {
    @Test
    void testEmptySequence() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of());
        assertThrows(NoSuchElementException.class, backwardIterator::next);
    }

    @Test
    void testNonEmptySequence() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1, 2, 3, 4));

        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(4);
        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(3);
        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(2);
        assertThat(backwardIterator.hasNext()).isTrue();
        assertThat(backwardIterator.next()).isEqualTo(1);

        assertThat(backwardIterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, backwardIterator::next);
    }
}
