import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;

public class MinValue implements Callable<Integer> {

    Collection<Integer> collection;

    public MinValue(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public Integer call(){
        int result = Collections.min(collection);
        return result;
    }
}
