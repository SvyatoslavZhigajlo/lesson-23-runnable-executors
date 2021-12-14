import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Collection<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Main main = new Main();
        main.runPrintCollection(integers);

        /*ДЗ 25 Задание 2 - Выполните подсчет минимального, максимального, среднего
        значения и суммы элементов коллекции.
         */
        main.runCountingValues(integers);

        /*ДЗ 25 Задание 3 - Исследуйте загрузку процессора при исполþзовании Future. Вýполните Thread.sleep() в
        отделþной задаче и ожидайте завершениā с исполþзованием:
        a. Метода Future.get()
        b. Цикла while и метода Future.isDone()
         */
        System.out.println("Запуск useFutureGet с ожиданием 20 сек");
        try {
            Thread.sleep(20_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main.useFutureGet(integers);
        System.out.println("Запуск useFutureIsDone с ожиданием 10 сек");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main.useFutureIsDone(integers);
    }

    private void runPrintCollection(Collection integer) {
        Collection<Integer> integers = new ArrayList<>(integer);
        MyPrintIntegerCollectionTask task = new MyPrintIntegerCollectionTask(integers);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> result = executor.submit(task);

        System.out.println("Awaiting 'MyPrintIntegerCollectionTask'...");
        try {
            result.get();
            System.out.println("Task 'MyPrintIntegerCollectionTask' is completed");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Task 'MyPrintIntegerCollectionTask' has failed");
            e.printStackTrace();
        }
        executor.shutdown();
    }

    private void runCountingValues(Collection integer) {
        Collection<Integer> integers = new ArrayList<>(integer);
        ExecutorService executor = Executors.newScheduledThreadPool(5);

        MinValue taskMinValue = new MinValue(integers);
        Future<Integer> minValueResult = executor.submit(taskMinValue);

        MaxValue taskMaxValue = new MaxValue(integers);
        Future<Integer> maxValueResult = executor.submit(taskMaxValue);

        SumValue taskSumValue = new SumValue(integers);
        Future<Integer> sumValueResult = executor.submit(taskSumValue);

        AverageValue taskAverageValue = new AverageValue(integers);
        Future<Float> averageValueResult = executor.submit(taskAverageValue);


        try {
            Integer minValue = minValueResult.get();
            System.out.println("Min value from collection: " + minValue);

            Integer maxValue = maxValueResult.get();
            System.out.println("Max value from collection: " + maxValue);

            Integer sumValue = sumValueResult.get();
            System.out.println();
            System.out.println("Sum value from collection: " + sumValue);

            Float averageValue = averageValueResult.get();
            System.out.println();
            System.out.println("Average value from collection: " + averageValue);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    private void useFutureGet(Collection collection){
        Collection<Integer> integerCollection = new ArrayList<>(collection);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        MyPrintIntegerCollectionTask printCollection = new MyPrintIntegerCollectionTask(integerCollection);
        Future<?> future = executor.submit(printCollection);

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    private void useFutureIsDone(Collection collection){
        Collection<Integer> integerCollection = new ArrayList<>(collection);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        MyPrintIntegerCollectionTask printCollection = new MyPrintIntegerCollectionTask(integerCollection);
        Future<?> future = executor.submit(printCollection);

        while (!future.isDone()){

        }
    }
}
