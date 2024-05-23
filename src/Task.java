import java.util.Date;
import java.util.concurrent.CyclicBarrier;

class Task implements Runnable{
    private String teamName;
    private int speed;
    private CyclicBarrier barrier;


    public Task(String teamName,int speed, CyclicBarrier barrier){
        this.speed = speed;
        this.teamName = teamName;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(teamName + " стартует в " + new Date());
            Thread.sleep((long) (200/speed*1000));
            System.out.println(teamName + " финишировал в " + new Date());

            if (Main.winner.isEmpty()) {
                Main.winner = teamName;
            }

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}