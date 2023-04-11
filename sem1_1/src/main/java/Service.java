import java.util.*;

import static java.lang.Math.abs;

public class Service {
    public static Collection<Data> getElemsByName(List<Data> list, String name){
        Collection<Data> ret = new ArrayList<>();
        for (Data d: list){
            if (d!=null && d.getName().equals(name)){
                ret.add(d);
            }
        }
        return ret;
    }
    public static Collection<Data> getElemsByLevel(List<Data> list, double level){
        Collection<Data> ret = new ArrayList<>();
        for (Data d: list){
            if (d != null && abs(d.getValue())<= level){
                ret.add(d);
            }
        }
        return ret;
    }

    public static Set<Double> getValuesFromSet(List<Data> list, Set<String> names){
        Set<Double> ret = new HashSet<>();
        for (Data d: list){
            if (d!= null && names.contains(d.getName())){
                ret.add(d.getValue());
            }
        }
        return ret;
    }

    public static String[] getNamesFromPositive(List<Data> list){
        Set<String> stringSet = new TreeSet<>();
        for (Data d: list){
            if (d != null && d.getValue()>0){
                stringSet.add(d.getName());
            }
        }
        return stringSet.toArray(new String[]{});
    }

    public static <T> Set<T> union(List<Set<T>> sets){
        Set<T> ret = new HashSet<>();
        for (Set<T> x: sets){
            if (x!=null){
                ret.addAll(x);
            }
        }
        return ret;
    }

    public static <T> Set<T> intersection(List<Set<T>> sets) throws NullPointerException{
        Set<T> ret = new HashSet<>();
        Iterator<Set<T>> it = sets.iterator();
        if(it.hasNext())
            ret.addAll(it.next());
        while(it.hasNext()){
            ret.retainAll(it.next());
        }
        return ret;
    }

    public static <T> List<Set<T>> getMaxSets(List<Set<T>> sets){
        List<Set<T>> ret = new ArrayList<>();
        DataSet d = new DataSet();
        int maxSize = 0;
        for(Set<T> x: sets){
            if (x!= null && x.size()>maxSize){
                maxSize = x.size();
            }
        }
        for(Set<T> x: sets){
            if (x != null && x.size()==maxSize){
                ret.add(x);
            }
        }
        return ret;
    }

}
