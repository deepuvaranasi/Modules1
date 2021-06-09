import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadSafeDemo {

    private static int anInt = 0;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?>[] futures = new Future[10];
        for (int i = 0; i < 10; i++) {
            futures[i]=executorService.submit(() -> doSomeHeavyWork());

        }
        for(var f : futures){
            try {
                f.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(anInt);
    }

        public static void doSomeHeavyWork() {
            for (int i = 0; i < 10000; i++) {
                anInt++;
                System.out.println(Thread.currentThread().getName() + ":" + anInt);

            }

    }
}
