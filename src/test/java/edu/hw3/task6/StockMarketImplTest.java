package edu.hw3.task6;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

class StockMarketImplTest {

    private static Stream<Arguments> stocks() {
        Stock first = new Stock("first", 1);
        Stock second = new Stock("second", 2);
        Stock third = new Stock("third", 3);
        return Stream.of(
            Arguments.of(List.of(first, second, third)),
            Arguments.of(List.of(first, third, second)),
            Arguments.of(List.of(second, first, third)),
            Arguments.of(List.of(second, third, first)),
            Arguments.of(List.of(third, first, second)),
            Arguments.of(List.of(third, second, first))
        );
    }

    /**
     * Test checks that order is not important for stock market implementation
     */
    @ParameterizedTest
    @MethodSource("stocks")
    void testStockMarketImplementation(List<Stock> stocks) {
        StockMarketImpl stockMarket = new StockMarketImpl();
        for (Stock stock : stocks) {
            stockMarket.add(stock);
        }

        assertThat(stockMarket.mostValuableStock().cost()).isEqualTo(3);
        stockMarket.remove(new Stock("third", 3));

        assertThat(stockMarket.mostValuableStock().cost()).isEqualTo(2);
        stockMarket.remove(new Stock("second", 2));

        assertThat(stockMarket.mostValuableStock().cost()).isEqualTo(1);
        stockMarket.remove(new Stock("first", 1));

        assertThat(stockMarket.mostValuableStock()).isNull();
    }
}
