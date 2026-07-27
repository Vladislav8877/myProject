package projectMultithreading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private final AtomicInteger taskCounter = new AtomicInteger(0);
    private final AtomicInteger activeTaskCount = new AtomicInteger(0);
    private final Map<String, Integer> results = new HashMap<>();


    public int getActiveTaskCount() {
        return activeTaskCount.get();
    }

    public Optional<Integer> getResult(String taskName) {
        synchronized (results) {
            return Optional.ofNullable(results.get(taskName));
        }
    }

    public void calculateSumTask(List<Integer> numbers) {
        String taskName = "task" + taskCounter.incrementAndGet();
        activeTaskCount.incrementAndGet();
        CalculateSumTask task = new CalculateSumTask(taskName, numbers);
        CompletableFuture.supplyAsync(() -> {
            try {
                return task.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, executor).whenComplete((result, exception) -> {
            if (exception == null) {
                synchronized (results) {
                    results.put(taskName, result);
                }
            } else {
                System.out.println("Ошибка в задаче: " + taskName + ": " + exception.getMessage());
            }
            activeTaskCount.decrementAndGet();
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}
