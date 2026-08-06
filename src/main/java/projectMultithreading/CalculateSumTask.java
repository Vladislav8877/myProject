package projectMultithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CalculateSumTask implements Callable<Integer> {
    private String taskName;
    private List<Integer> numbers;

    public CalculateSumTask(String taskName, List<Integer> numbers) {
        this.taskName = taskName;
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Task Name: " + this.taskName + " || " + Thread.currentThread().getName());
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        Thread.sleep(200);
        return sum;
    }
}