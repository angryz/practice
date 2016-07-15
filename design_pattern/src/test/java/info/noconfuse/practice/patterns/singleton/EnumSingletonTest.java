package info.noconfuse.practice.patterns.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzp on 7/7/16.
 */
public class EnumSingletonTest {

    @Test
    public void testGetInstance() {
        EnumSingleton inst = EnumSingleton.INSTANCE;
        System.out.println(inst);
        Assert.assertTrue(inst instanceof EnumSingleton);
    }

    @Test
    public void testGetInstanceConcurrently() {
        Map<String, EnumSingleton> results = new HashMap<>(50);
        ConcurrentTestHelper.AbstractTask task = new ConcurrentTestHelper.AbstractTask() {
            @Override
            void doTask() {
                EnumSingleton inst = EnumSingleton.INSTANCE;
                System.out.println(Thread.currentThread().getId() + " > " + inst.hashCode());
                results.put(Thread.currentThread().getName(), inst);
            }
        };
        ConcurrentTestHelper.runTaskConcurrently(task, 50);
        ConcurrentTestHelper.assertAllValuesSame(results);
    }
}
