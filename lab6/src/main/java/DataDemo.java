import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataDemo {
    public static List<Integer> getAll(Data data){
        List<Integer> ret = new ArrayList<>();
        Iterator<Integer> it = data.iterator();
        while (it.hasNext()){
            ret.add(it.next());
        }
        return ret;
    }
}
