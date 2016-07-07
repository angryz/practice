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

/**
 * Created by zzp on 7/6/16.
 */
public class DoubleCheckedLockingTest {

    @Test
    public void testGetInstance() {
        DoubleCheckedLocking inst = DoubleCheckedLocking.getInstance();
        System.out.println(inst);
        Assert.assertTrue(inst instanceof DoubleCheckedLocking);
    }

    @Test
    public void testGetInstanceConcurrently() throws Exception {
        Map<String, DoubleCheckedLocking> results = new HashMap<>(50);
        ConcurrentTestHelper.AbstractTask task = new ConcurrentTestHelper.AbstractTask() {
            @Override
            void doTask() {
                DoubleCheckedLocking inst = DoubleCheckedLocking.getInstance();
                System.out.println(Thread.currentThread().getId() + " : " + inst.hashCode());
                results.put(Thread.currentThread().getName(), inst);
            }
        };
        ConcurrentTestHelper.runTaskConcurrently(task, 50);
        ConcurrentTestHelper.assertAllValuesSame(results);
    }
}