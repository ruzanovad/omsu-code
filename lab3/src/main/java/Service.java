import javax.management.InstanceNotFoundException;
import java.util.Arrays;

public class Service {
    static final int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final static String[] monthsString = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private static int findMonth(String m) throws InstanceNotFoundException {
        for (int i = 0; i < 12; ++i) {
            if (monthsString[i].equals(m))
                return i + 1;
        }
        throw new InstanceNotFoundException();
    }

    public static Human[] findAdults(Human[] humans, Date date){
        int count = 0;
        Human[] adults = new Human[humans.length];
        for (Human human : humans) {
            Date birthDate = new Date(
                    human.getBirthDate().getDay(),
                    human.getBirthDate().getMonth(),
                    human.getBirthDate().getYear());
            if (birthDate.getDay() == 29 && birthDate.getMonth().equals("February")) {
                birthDate.setDay(1);
                birthDate.setMonth(3);
                birthDate.setYear(birthDate.getYear() + 18);
            } else {
                birthDate.setYear(birthDate.getYear() + 18);
            }
            if (date.after(birthDate)) {
                adults[count] = human;
                count++;
            }

        }
        return Arrays.copyOfRange(adults, 0, count);
    }
    public static int[] getAges(Human[] humans, Date date) throws InstanceNotFoundException {
        int[] dates = new int[humans.length];
        for (int i = 0; i < humans.length; ++i){
            Date curr = humans[i].getBirthDate();
            if (curr.after(date)){
                throw new IllegalArgumentException("Person is not born yet");
            }
            if (curr.equals(date)){
                dates[i] = 0;
                continue;
            }
            int days = date.getDay(), month = findMonth(date.getMonth()), years = date.getYear();
            days-= curr.getDay();
            if (curr.getDay() > date.getDay()){
                month = (month-1+12)%12;
                if (month==1){
                    days+=31;
                    years-=1;
                }
                else if (month==3 && date.isLeapYear()){
                    days+=29;
                }
                else{
                    days+=months[month-1];
                }
            }
            if (findMonth(curr.getMonth())> month){
                years-=1;
                month+=12;
            }
            month-= findMonth(curr.getMonth());
            years-= curr.getYear();
            dates[i] = years;
        }
        return dates;
    }
}
