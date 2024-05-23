import java.util.concurrent.CyclicBarrier;

public class Main {
    public static String winner = "";

    public static void main(String[] args) {
        System.out.println("Эстафета началась");
        CyclicBarrier barrier = new CyclicBarrier(2);

        Task athlete1Team1 = new Task("Атлет 1 команды 1", 9, barrier);
        Task athlete2Team1 = new Task("Атлет 2 команды 1", 4, barrier);
        Task athlete1Team2 = new Task("Атлет 1 команды 2", 8, barrier);
        Task athlete2Team2 = new Task("Атлет 2 команды 2", 6, barrier);

        Thread thread1 = new Thread(() -> {
            try {
                barrier.await();
                athlete1Team1.run();
                barrier.await();
                athlete2Team1.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                barrier.await();
                athlete1Team2.run();
                barrier.await();
                athlete2Team2.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Победитель: " + winner);
    }
}