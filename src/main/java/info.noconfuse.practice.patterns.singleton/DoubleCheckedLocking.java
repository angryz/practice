package info.noconfuse.practice.patterns.singleton;

/**
 * Double Checking Singleton.
 *
 * <p>
 * Features:
 *   <pre>
 *   1. lazy-loading
 *   2. thread-safe
 *   </pre>
 * </p>
 *
 * <p>
 * Important:
 *   <pre>
 *   1. volatile
 *   2. synchornized
 *   </pre>
 * </p>
 *
 * <p>
 * !为何要检查2次: 加锁避免了注释3和4两行代码的同步问题,但是如果不做第一次检查,synchronized将会增加性能开销,
 * 所以在加锁前做一次检查可以大大降低同步开销.
 * </p>
 *
 * <p>
 * !为何要用 volatile: 注释4的代码并非原子性操作,实际执行的是
 *     <pre>
 *         memory = allocate();   //1：分配对象的内存空间
 *         ctorInstance(memory);  //2：初始化对象
 *         instance = memory;     //3：设置instance指向刚分配的内存地址
 *     </pre>
 * 且由于一些JIT编译器的优化, 上面2和3可能改变顺序, 所以当A线程第一次检查实例不为null时,有可能B线程尚未完成实例的初始化,
 * 这样A线程就会拿到一个未完全初始化的实例.
 * 而 volatile (JDK5+) 强化了内存的可见性, 所以初始化保证在实例指向分配的内存地址前完成, 这样就真正的确保了线程安全的懒实例化.
 * </p>
 *
 * <p></p>
 * Created by zzp on 7/6/16.
 */
public class DoubleCheckedLocking {

    private static volatile DoubleCheckedLocking inst;

    private DoubleCheckedLocking() {
    }

    public static DoubleCheckedLocking getInstance() {
        if (inst == null) { // 1.第一次检查
            synchronized (DoubleCheckedLocking.class) { // 2. 加锁
                if (inst == null) { // 3.第二次检查
                    inst = new DoubleCheckedLocking(); // 4.创建实例
                }
            }
        }
        return inst;
    }
}
