import java.util.*;

public class CollectionsDemo {
    public static int beginsWith(List<String> sList, char c){
        int count = 0;
        for(String s: sList){
            if (!"".equals(s) && s != null && s.charAt(0) == c)
                count++;
        }
        return count;
    }

    public static List<Human> getNamesakes(List<Human> humans, Human target){
        List<Human> h = new ArrayList<>();
        for (Human x: humans){
            if (x.getSurname().equals(target.getSurname()) && !x.equals(target)){
                h.add(x);
            }
        }
        return h;
    }

    public static List<Human> deleteHuman(List<Human> humans, Human target){
//        List<Human> newHumans = new ArrayList<Human>(humans);
        List<Human> newHumans = new ArrayList<>();
        for (Human h: humans){
            if (!h.equals(target)){
                newHumans.add(new Human(h));
            }
        }
//        newHumans.remove(target);
        return newHumans;
    }


    public static List<Set<Integer>> disjointSet(List<Set<Integer>> list, Set<Integer> set){
        List<Set<Integer>> newList = new ArrayList<>();
        for (Set<Integer> curr: list){
            Set<Integer> intersection = new HashSet<>(curr);
            intersection.retainAll(set);
            if (intersection.size()==0){
                newList.add(curr);
            }
        }
        return newList;
    }

    public static <T extends Human> Set<T> getMaxAgeSet(List<T> humans){
        Set<T> h = new HashSet<>();
        int age = 0;
        for (T x: humans){
            if (x.getAge()> age)
                age = x.getAge();
        }
        for (T x: humans){
            if (x.getAge() == age)
                h.add(x);
        }
        return h;
    }
    public static List<Human> getSortedByName(Set<Human> humans){
        List<Human> ret = new ArrayList<>(humans);

        ret.sort(new NameComparator(false));
        return ret;
    }

    public static Set<Human> getHumansFromSet(Map<Integer , Human> humanMap, Set<Integer> integers){
        Set<Human> newSet = new HashSet<>();
        for (int x: integers){
            if (humanMap.containsKey(x)){
                newSet.add(humanMap.get(x));
            }
        }
        return newSet;
    }
    public static List<Integer> getAdults(Map<Integer , Human> humanMap){
        List<Integer> newList = new ArrayList<>();
        for (Map.Entry<Integer, Human> entry: humanMap.entrySet()){
            if (entry.getValue().getAge()>= 18)
                newList.add(entry.getKey());
        }
        return newList;
    }
    public static Map<Integer, Integer> getIntegerIntegerMap(Map<Integer , Human> humanMap){
        Map<Integer, Integer> newMap = new HashMap<>(humanMap.size());
        for (Map.Entry<Integer, Human> entry: humanMap.entrySet()){
                newMap.put(entry.getKey(), entry.getValue().getAge());
        }
        return newMap;
    }
    public static Map<Integer, List<Human>> getIntegerListMap(Set<Human> humans){
        Map<Integer, List<Human>> newMap = new HashMap<>();
        for (Human x: humans){
            if (!newMap.containsKey(x.getAge())){
                List<Human> humanList = new ArrayList<>();
                humanList.add(x);
                newMap.put(x.getAge(), humanList);
            }
            else{
                newMap.get(x.getAge()).add(x);
            }
        }
        return newMap;
    }
    public static Map<Integer, Map<Character, List<Human>>> getMapByAge(Set<Human> humans){
        Map<Integer, Map<Character, List<Human>>> ret = new HashMap<>();
        Map<Integer, List<Human>> newMap = getIntegerListMap(humans);
        for (int x: newMap.keySet()){
            List<Human> current = newMap.get(x);
            current.sort(new NameComparator(true));
            Map<Character, List<Human>> charList = new HashMap<>();
            for (Human y: current){
                if (charList.containsKey(y.getSurname().charAt(0))){
                    charList.get(y.getSurname().charAt(0)).add(y);
                }
                else{
                    List<Human> toAdd = new ArrayList<>();
                    toAdd.add(y);
                    charList.put(y.getSurname().charAt(0), toAdd);
                }
            }
            ret.put(x, charList);
        }
        return ret;
    }

}
