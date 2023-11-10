package edu.hw6.task5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HackerNewsTest {

    private static Stream<Arguments> newsId() {
        return Stream.of(
            Arguments.of(37570037, "JDK 21 Release Notes"),
            Arguments.of(38216588, "Flight Finder GPT"),
            Arguments.of(38216498, "Tesla shares drop 5% after HSBC calls it a 'expensive auto company'")
        );
    }

    @Test
    void hackerNewsTopStories() {
        long[] topStoryCodes = HackerNews.hackerNewsTopStories();
        assertThat(topStoryCodes.length).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(500);
        assertThat(topStoryCodes[0]).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("newsId")
    void testNews(long id, String expectedTitle) {
        String actualTitle = HackerNews.news(id);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }
}
