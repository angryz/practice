package info.noconfuse.practice.patterns.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Created by zzp on 7/6/16.
 */
public class DoubleCheckSingletonTest {

    @Test
    public void testGetInstance() {
        DoubleCheckSingleton inst = DoubleCheckSingleton.getInstance();
        System.out.println(inst.hashCode());
        Assert.assertTrue(inst instanceof DoubleCheckSingleton);
    }

    @Test
    public void testGetInstanceConcurrently() throws Exception {
        Map<String, DoubleCheckSingleton> results = new HashMap<>(20);
        CyclicBarrier beginingBarrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("All threads are ready");
            }
        });
        CyclicBarrier endingBarrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("All threads are done");
                synchronized (DoubleCheckSingletonTest.class) {
                    DoubleCheckSingletonTest.class.notify();
                }
            }
        });
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    beginingBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                DoubleCheckSingleton inst = DoubleCheckSingleton.getInstance();
                System.out.println(Thread.currentThread().getId() + " : " + inst.hashCode());
                results.put(Thread.currentThread().getName(), inst);
                try {
                    endingBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.submit(task);
        }
        executorService.shutdown();
        synchronized (DoubleCheckSingletonTest.class) {
            DoubleCheckSingletonTest.class.wait();
        }
        //System.out.println(results);
        Iterator<DoubleCheckSingleton> it = results.values().iterator();
        DoubleCheckSingleton a, b;
        while (it.hasNext()) {
            a = it.next();
            if (!it.hasNext()) break;
            b = it.next();
            //System.out.println("a >>> " + a);
            //System.out.println("b >>> " + b);
            Assert.assertSame(a, b);
        }
    }
}