public interface IBuffer<T> {
    void add(T x);

    T get();

    int size();

    void clear();

    boolean empty();
}
