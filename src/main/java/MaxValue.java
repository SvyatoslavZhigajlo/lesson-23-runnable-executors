import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;

public class MaxValue implements Callable<Integer> {

    Collection <Integer> collection;

    public MaxValue(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public Integer call(){
        return Collections.max(collection);
    }
}
