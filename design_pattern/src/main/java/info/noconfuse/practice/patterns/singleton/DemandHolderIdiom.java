package info.noconfuse.practice.patterns.singleton;

/**
 * <p>
 * JVM在类的初始化阶段（即在Class被加载后，且被线程使用之前），会执行类的初始化。在执行类的初始化期间，JVM会去获取一个锁。
 * 这个锁可以同步多个线程对同一个类的初始化。<br>
 * 基于这个特性，可以实现另一种线程安全的延迟初始化方案（这个方案被称之为Initialization On Demand Holder idiom）
 * </p>
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
 *   初始化一个类，包括执行这个类的静态初始化和初始化在这个类中声明的静态字段。
 *   根据java语言规范，在首次发生下列任意一种情况时，一个类或接口类型T将被立即初始化：
 *   - T是一个类，而且一个T类型的实例被创建；
 *   - T是一个类，且T中声明的一个静态方法被调用；
 *   - T中声明的一个静态字段被赋值；
 *   - T中声明的一个静态字段被使用，而且这个字段不是一个常量字段；
 *   - T是一个顶级类（top level class，见java语言规范的§7.6），而且一个断言语句嵌套在T内部被执行。
 *   </pre>
 * </p>
 *
 * <p></p>
 * Created by zzp on 7/7/16.
 */
public class DemandHolderIdiom {

    private DemandHolderIdiom() {
    }

    public static DemandHolderIdiom getInstance() {
        return InstanceHolder.inst;
    }

    private static class InstanceHolder {
        static DemandHolderIdiom inst = new DemandHolderIdiom();
    }
}
