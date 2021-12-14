import java.util.Collection;
import java.util.concurrent.Callable;

public class SumValue implements Callable <Integer> {

    Collection <Integer> collection;

    public SumValue(Collection<Integer> collection) {
        this.collection = collection;
    }


    @Override
    public Integer call(){
        int resultSum = 0;

        for (Integer calcSumInteger : collection) {
            resultSum += calcSumInteger;
        }
        return resultSum;
    }
}
