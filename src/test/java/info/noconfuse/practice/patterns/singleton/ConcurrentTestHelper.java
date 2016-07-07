package info.noconfuse.practice.patterns.singleton;

import org.junit.Assert;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzp on 7/7/16.
 */
public class ConcurrentTestHelper {

    public static void runTaskConcurrently(AbstractTask task, int threadsNum) {
        CyclicBarrier beginingBarrier = new CyclicBarrier(threadsNum, new Runnable() {
            @Override
            public void run() {
                System.out.println("All threads are ready");
            }
        });
        CyclicBarrier endingBarrier = new CyclicBarrier(threadsNum, new Runnable() {
            @Override
            public void run() {
                System.out.println("All threads are done");
                synchronized (task) {
                    task.notify();
                }
            }
        });
        task.setBeginingBarrier(beginingBarrier);
        task.setEndingBarrier(endingBarrier);

        ExecutorService executorService = Executors.newFixedThreadPool(threadsNum);
        for (int i = 0; i < threadsNum; i++) {
            executorService.submit(task);
        }
        executorService.shutdown();
        synchronized (task) {
            try {
                task.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> void assertAllValuesSame(Map<String, T> results) {
        Iterator<T> it = results.values().iterator();
        T a, b;
        while (it.hasNext()) {
            a = it.next();
            if (!it.hasNext()) break;
            b = it.next();
            //System.out.println("a >>> " + a);
            //System.out.println("b >>> " + b);
            Assert.assertSame(a, b);
        }
    }

    static abstract class AbstractTask implements Runnable {
        private CyclicBarrier beginingBarrier;
        private CyclicBarrier endingBarrier;

        abstract void doTask();

        @Override
        public void run() {
            try {
                beginingBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            doTask();
            try {
                endingBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        public void setBeginingBarrier(CyclicBarrier beginingBarrier) {
            this.beginingBarrier = beginingBarrier;
        }

        public void setEndingBarrier(CyclicBarrier endingBarrier) {
            this.endingBarrier = endingBarrier;
        }
    }
}
