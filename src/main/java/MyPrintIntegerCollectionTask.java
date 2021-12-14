import java.util.Collection;

public class MyPrintIntegerCollectionTask implements Runnable{

    private Collection<Integer> collection;

    public MyPrintIntegerCollectionTask(Collection<Integer> collection) {
        this.collection = collection;
    }


    @Override
    public void run() {
        for (Integer value : collection) {
            System.out.println(value);
        }
    }
}
