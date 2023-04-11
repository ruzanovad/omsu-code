import java.util.LinkedList;
import java.util.Queue;

public class SimpleBuffer<T> implements IBuffer<T>{
    private final Queue<T> q;
    private int maxsize = -1;

    public SimpleBuffer(){
        q = new LinkedList<>();
    }

    public SimpleBuffer(int maxsize) {
        q = new LinkedList<>();
        this.maxsize = maxsize;
    }

    @Override
    public void add(T x) {
        if (q.size() == maxsize)
            throw new IllegalArgumentException("is full");
        q.add(x);
    }

    @Override
    public T get() {
        return q.poll();
    }

    @Override
    public int size() {
        return q.size();
    }

    @Override
    public void clear() {
        q.clear();
    }

    @Override
    public boolean empty() {
        return q.isEmpty();
    }
}
