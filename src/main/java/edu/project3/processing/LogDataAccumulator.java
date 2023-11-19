package edu.project3.processing;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.jetbrains.annotations.Range;

public class LogDataAccumulator {
    private final List<String> files = new ArrayList<>();
    private final Map<String, Integer> requestedResources = new HashMap<>();
    private final Map<Integer, Integer> statusCodeCounter = new HashMap<>();
    private Integer totalSizeOfResponses = 0;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Integer numberOfRequests = 0;

    public void increaseNumberOfRequests() {
        this.numberOfRequests++;
    }

    public void setFiles(List<String> files) {
        this.files.clear();
        this.files.addAll(files);
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        if (startDate == null) {
            this.startDate = OffsetDateTime.MIN;
        } else {
            this.startDate = OffsetDateTime.from(startDate);
        }
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        if (endDate == null) {
            this.endDate = OffsetDateTime.MAX;
        } else {
            this.endDate = OffsetDateTime.from(endDate);
        }
    }

    /**
     * Increase counter of requests, having certain resource
     */
    public void addRequestedResource(String resourcePath) {
        if (this.requestedResources.containsKey(resourcePath)) {
            this.requestedResources.put(resourcePath, this.requestedResources.get(resourcePath) + 1);
        } else {
            this.requestedResources.put(resourcePath, 1);
        }
    }

    /**
     * Add status code to counter in report
     *
     * @param statusCode - status code to add
     */
    public void addStatusCode(@Range(from = 0, to = 599) Integer statusCode) {
        if (this.statusCodeCounter.containsKey(statusCode)) {
            this.statusCodeCounter.put(statusCode, this.statusCodeCounter.get(statusCode) + 1);
        } else {
            this.statusCodeCounter.put(statusCode, 1);
        }
    }

    public void addResponseSize(Integer responseSize) {
        this.totalSizeOfResponses += responseSize;
    }

    /**
     * Get requested resources and number of their requests in descending order
     *
     * @return priority queue of map entries with resource name and number of requests
     */
    public PriorityQueue<Map.Entry<String, Integer>> getRequestedResources() {
        // PQ sort pairs by number of requests in descending order
        PriorityQueue<Map.Entry<String, Integer>> result =
            new PriorityQueue<>((first, second) -> Integer.compare(second.getValue(), first.getValue()));
        result.addAll(this.requestedResources.entrySet());
        return result;
    }

    /**
     * Get all status codes, sorted by number of requests, having this codes, in descending order
     *
     * @return priority queue of map entries with status codes and number of requests
     */
    public PriorityQueue<Map.Entry<Integer, Integer>> getStatusCodes() {
        PriorityQueue<Map.Entry<Integer, Integer>> result =
            new PriorityQueue<>((first, second) -> Integer.compare(second.getValue(), first.getValue()));
        result.addAll(this.statusCodeCounter.entrySet());
        return result;
    }

    public Integer getAverageResponseSize() {
        if (this.numberOfRequests != 0) {
            return this.totalSizeOfResponses / this.numberOfRequests;
        }
        return 0;
    }

    /**
     * Copy data from this accumulator to LogReport
     *
     * @return LogReport with copied data
     */
    public LogReport toLogReport() {
        return new LogReport(
            List.copyOf(this.files),
            OffsetDateTime.from(startDate),
            OffsetDateTime.from(endDate),
            numberOfRequests,
            getAverageResponseSize(),
            new PriorityQueue<>(this.getRequestedResources()),
            new PriorityQueue<>(this.getStatusCodes())
        );
    }
}
