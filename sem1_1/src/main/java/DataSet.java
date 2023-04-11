import java.util.*;

public class DataSet implements Iterable<Data>{
    private final List<Data> list;

    public DataSet(){
        list = new ArrayList<>();
    }

    public void add(Data d){
        list.add(d);
    }

    @Override
    public Iterator<Data> iterator() {
        return new Iterator<Data>() {
            private int cur;

            @Override
            public boolean hasNext() {
                return cur!=list.size();
            }

            @Override
            public Data next() {
                if (!hasNext())
                    throw new NoSuchElementException();
//                while (list.get(cur) == null)
//                    cur++;
//                if (!hasNext())
//                    throw new NoSuchElementException();
                int lastReturned = cur;
                cur++;
                return list.get(lastReturned);
            }
        };
    }

    public void sort(){
        // Collections.sort(list, Comparator.comparingDouble(Data::getValue));
        list.sort(Comparator.comparing(Data::getName));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSet data = (DataSet) o;
        return Objects.equals(list, data.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
