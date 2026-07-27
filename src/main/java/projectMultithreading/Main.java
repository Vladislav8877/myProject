package projectMultithreading;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        DataProcessor processor = new DataProcessor();

        for (int i = 1; i <= 100; i++) {
            List<Integer> numbers = List.of(i, i * 2, i * 3, i * 4, i * 5);
            processor.calculateSumTask(numbers);
        }

        while (processor.getActiveTaskCount() > 0) {
            System.out.println("Активных задач: " + processor.getActiveTaskCount());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= 100; i++) {
            String taskName = "task" + i;
            Optional<Integer> result = processor.getResult(taskName);
            result.ifPresent(val -> System.out.println(taskName + " результат: " + val));
        }

        processor.shutdown();
    }
}