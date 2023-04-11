import java.util.Comparator;

public class NameComparator implements Comparator<Human> {

    private boolean reverse = false;
    public NameComparator(boolean reverse){
        this.reverse = reverse;
    }
    @Override
    public int compare(Human o1, Human o2) {
        int rev;
        if (reverse){
            rev = -1;
        }
        else{
            rev = 1;
        }
        int comp1 = o1.getSurname().compareTo(o2.getSurname())*rev;
        if (comp1 != 0)
            return comp1;
        else{
            int comp2 = o1.getName().compareTo(o2.getName())*rev;
            if (comp2 != 0)
                return comp2;
            else{
                return o1.getPatronym().compareTo(o2.getPatronym())*rev;
            }
        }
    }
}
