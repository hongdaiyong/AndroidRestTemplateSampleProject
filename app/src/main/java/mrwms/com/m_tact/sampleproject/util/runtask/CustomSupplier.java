package mrwms.com.m_tact.sampleproject.util.runtask;

/**
 * Created by NeverMore on 2018/03/12.
 * the copy of java.util.function.Supplier
 *
 * well... maybe it isn't a bad way to solve version problem
 */
@FunctionalInterface
public interface CustomSupplier<T> {

    T get();

}
