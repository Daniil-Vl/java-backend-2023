package edu.project3.processing;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public record LogReport(
    List<String> files,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Integer numberOfRequests,
    Integer averageResponseSize,
    PriorityQueue<Map.Entry<String, Integer>> requestedSources,
    PriorityQueue<Map.Entry<Integer, Integer>> statusCodes
) {
    public PriorityQueue<Map.Entry<String, Integer>> requestedSources() {
        return new PriorityQueue<>(requestedSources);
    }

    public PriorityQueue<Map.Entry<Integer, Integer>> statusCodes() {
        return new PriorityQueue<>(statusCodes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof LogReport other)) {
            return false;
        }

        return this.files.equals(other.files)
            && this.startDate.equals(other.startDate)
            && this.endDate.equals(other.endDate)
            && this.numberOfRequests.equals(other.numberOfRequests)
            && this.averageResponseSize.equals(other.averageResponseSize)
            && areThePriorityQueuesEqual(this.requestedSources, other.requestedSources)
            && areThePriorityQueuesEqual(this.statusCodes, other.statusCodes);
    }

    @Override
    public int hashCode() {
        return this.files.hashCode()
            + this.startDate.hashCode()
            + this.endDate.hashCode()
            + this.numberOfRequests.hashCode()
            + this.averageResponseSize.hashCode()
            + this.requestedSources.hashCode()
            + this.statusCodes.hashCode();
    }

    /**
     * Checks that first pq contains all elements of second pq in same order and vice versa
     *
     * @param first  - first priority queue
     * @param second - second priority queue
     */
    private <K, V> boolean areThePriorityQueuesEqual(
        PriorityQueue<Map.Entry<K, V>> first,
        PriorityQueue<Map.Entry<K, V>> second
    ) {
        PriorityQueue<Map.Entry<K, V>> firstTemp = new PriorityQueue<>(first);
        PriorityQueue<Map.Entry<K, V>> secondTemp = new PriorityQueue<>(second);

        while (!firstTemp.isEmpty()) {
            if (!firstTemp.poll().equals(secondTemp.poll())) {
                return false;
            }
        }

        return secondTemp.isEmpty();
    }
}
