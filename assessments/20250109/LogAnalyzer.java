package exam;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class LogEntry {
    private String userId;
    private LocalDateTime timestamp;
    private String action;
    private String status;
    private long processingTimeMs;

    // Constructor and respective getters
    public LogEntry(String userId, LocalDateTime timestamp, String action, String status, long processingTimeMs) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.action = action;
        this.status = status;
        this.processingTimeMs = processingTimeMs;
    }

    public String getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public long getProcessingTimeMs() {
        return processingTimeMs;
    }

    public String getAction() {
        return action;
    }
}

//class UserActivityStats
class UserActivityStats {
    private double successRate;
    private double avgProcessingTime;
    private Set<String> distinctActions;

    // Constructor of UserActivityStats
    public UserActivityStats(double successRate, double avgProcessingTime, Set<String> distinctActions) {
        this.successRate = successRate;
        this.avgProcessingTime = avgProcessingTime;
        this.distinctActions = distinctActions;
    }
    // getters
    public double getSuccessRate() {
        return successRate;
    }

    public double getAvgProcessingTime() {
        return avgProcessingTime;
    }

    public Set<String> getDistinctActions() {
        return distinctActions;
    }


    public String toString() {
        String formattedSuccessRate = String.format("%.2f", successRate);
        String formattedAvgProcessingTime = String.format("%.2f", avgProcessingTime);

        return "User ActivityStats{" +
                "successRate=" + formattedSuccessRate +
                ", avgProcessingTime=" + formattedAvgProcessingTime +
                ", distinctActions=" + distinctActions +
                '}';
    }
}

public class LogAnalyzer {

    public static Map<String, UserActivityStats> analyzeUserActivity(List<LogEntry> logs) {
        return logs.stream()
                .collect(Collectors.groupingBy(LogEntry::getUserId))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 5)
                .map(entry -> {
                    List<LogEntry> userLogs = entry.getValue();
                    long totalEntries = userLogs.size();
                    long successCount = userLogs.stream()
                            .filter(log -> "SUCCESS".equals(log.getStatus()))
                            .count();
                    double successRate = (double) successCount / totalEntries * 100;
                    double avgProcessingTime = userLogs.stream()
                            .filter(log -> "SUCCESS".equals(log.getStatus()))
                            .mapToLong(LogEntry::getProcessingTimeMs)
                            .average()
                            .orElse(0);

                    Set<String> distinctActions = userLogs.stream()
                            .map(LogEntry::getAction)
                            .collect(Collectors.toSet());

                    return new AbstractMap.SimpleEntry<>(entry.getKey(), new UserActivityStats(successRate, avgProcessingTime, distinctActions));
                })
                .filter(entry -> entry.getValue() != null)
                .sorted(Comparator.comparingDouble(entry -> entry.getValue().getAvgProcessingTime())) // Sort by avg processing time
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1, e2) -> e1,LinkedHashMap::new
                ));
    }

    public static void main(String[] args) {


        List<LogEntry> logs = Arrays.asList(
                new LogEntry("user1", LocalDateTime.now(), "VIEW", "SUCCESS", 80),
                new LogEntry("user1", LocalDateTime.now(), "EDIT", "SUCCESS", 90),
                new LogEntry("user2", LocalDateTime.now(), "LOGIN", "SUCCESS", 93),
                new LogEntry("user1", LocalDateTime.now(), "LOGIN", "SUCCESS", 100),
                new LogEntry("user1", LocalDateTime.now(), "UPLOAD", "SUCCESS", 150),
                new LogEntry("user1", LocalDateTime.now(), "DOWNLOAD", "FAILURE", 200),
                new LogEntry("user1", LocalDateTime.now(), "LOGOUT", "SUCCESS", 120),
                new LogEntry("user2", LocalDateTime.now(), "EDIT", "SUCCESS", 185),
                new LogEntry("user3", LocalDateTime.now(), "LOGIN", "SUCCESS", 90),
                new LogEntry("user3", LocalDateTime.now(), "UPLOAD", "SUCCESS", 200),
                new LogEntry("user3", LocalDateTime.now(), "DOWNLOAD", "SUCCESS", 150),
                new LogEntry("user3", LocalDateTime.now(), "LOGOUT", "SUCCESS", 110),
                new LogEntry("user3", LocalDateTime.now(), "VIEW", "SUCCESS", 95),
                new LogEntry("user3", LocalDateTime.now(), "EDIT", "SUCCESS", 85),
                new LogEntry("user2", LocalDateTime.now(), "UPLOAD", "FAILURE", 200),
                new LogEntry("user2", LocalDateTime.now(), "DOWNLOAD", "SUCCESS", 150),
                new LogEntry("user2", LocalDateTime.now(), "LOGOUT", "SUCCESS", 110),
                new LogEntry("user2", LocalDateTime.now(), "VIEW", "SUCCESS", 95)

        );

        Map<String, UserActivityStats> userActivityStats = analyzeUserActivity(logs);
        userActivityStats.forEach((userId, stats) -> {
            System.out.println("User  ID: " + userId + ", Stats: " + stats);
        });
    }
}
