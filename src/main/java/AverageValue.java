import java.util.Collection;
import java.util.concurrent.Callable;

public class AverageValue implements Callable <Float>{

    Collection <Integer> collection;

    public AverageValue(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public Float call() {

        float sumCollection = 0;

        for (Integer integer : collection) {
            sumCollection = sumCollection + integer.intValue();
        }
        float result = sumCollection/collection.size();

        return result;
    }
}
