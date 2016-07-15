package info.noconfuse.practice.patterns.singleton;

/**
 * Since Singleton instance is static and final variable it initialized when class is first loaded into memeory
 * so creation of instance is inherently thread-safe.
 * <p>
 * <p>
 * Features:
 * <pre>
 *   1. early-loading
 *   2. thread-safe
 *   3. static factory method
 *   </pre>
 * </p>
 * <p>
 * Created by zzp on 7/7/16.
 */
public class EarlyLoadedSingleton {

    private static final EarlyLoadedSingleton INST = new EarlyLoadedSingleton();

    private EarlyLoadedSingleton() {
    }

    public static EarlyLoadedSingleton getInstance() {
        return INST;
    }

}
