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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof LogReport)) {
            return false;
        }

        LogReport temp = (LogReport) obj;

        boolean result = true;

        // Compare PriorityQueues
        PriorityQueue<Map.Entry<String, Integer>> tempRequestedSourcesOriginal =
            new PriorityQueue<>(this.requestedSources);
        PriorityQueue<Map.Entry<String, Integer>> tempRequestedSourcesOther =
            new PriorityQueue<>(temp.requestedSources);
        while (!tempRequestedSourcesOriginal.isEmpty()) {
            if (!tempRequestedSourcesOriginal.poll().equals(tempRequestedSourcesOther.poll())) {
                return false;
            }
        }
        result = result && tempRequestedSourcesOther.isEmpty();

        PriorityQueue<Map.Entry<String, Integer>> tempStatusCodeOriginal = new PriorityQueue<>(this.requestedSources);
        PriorityQueue<Map.Entry<String, Integer>> tempStatusCodeOther = new PriorityQueue<>(temp.requestedSources);
        while (!tempStatusCodeOriginal.isEmpty()) {
            if (!tempStatusCodeOriginal.poll().equals(tempStatusCodeOther.poll())) {
                return false;
            }
        }
        result = result && tempStatusCodeOther.isEmpty();

        return this.files.equals(temp.files)
            && this.startDate.equals(temp.startDate)
            && this.endDate.equals(temp.endDate)
            && this.numberOfRequests.equals(temp.numberOfRequests)
            && this.averageResponseSize.equals(temp.averageResponseSize)
            && result;
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
}
