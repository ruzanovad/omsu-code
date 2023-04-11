import java.util.Objects;

public class Date {
    private final static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private int day;
    private String month;
    private int year;

//    private boolean stringMonthIsCorrect(String month){
//        boolean f = false;
//        for (int i = 0; i < 12 && !f; ++i){
//            if (months[i].equals(month))
//                f = true;
//        }
//        return f;
//    }

    static int findMonth(String m) {
        for (int i = 0; i < 12; ++i) {
            if (months[i].equals(m))
                return i + 1;
        }
        return -1;
    }

    public Date(int day, String month, int year) {
        int index = findMonth(month) - 1;
        if (index == -2) {
            throw new IllegalArgumentException("Wrong string for month");
        }
        if (year < 0) {
            throw new IllegalArgumentException("Wrong year");
        }
        if (index == 1) {
            if (!(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && day > 28) {
                throw new IllegalArgumentException("Wrong day for February");
            }
            if ((year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && day > 29) {
                throw new IllegalArgumentException("Wrong day for February");
            }
        } else if (day <= 0 || (index % 2 == 0 && day > 31) || (index % 2 == 1 && day > 30)) {
            throw new IllegalArgumentException("Wrong day");
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(int day, int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Wrong month");
        }
        if (year < 0) {
            throw new IllegalArgumentException("Wrong year");
        }
        if (month == 2) {
            if (!(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && day > 28) {
                throw new IllegalArgumentException("Wrong day for February");
            }
            if ((year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && day > 29) {
                throw new IllegalArgumentException("Wrong day for February");
            }
        } else if (day <= 0 || ((month % 2 == 1 && month<=7 || month>7 && month % 2 == 0)&& day > 31) || ((month % 2 == 0 && month <= 7 || month % 2 == 1 && month> 7)&& day > 30)) {
            throw new IllegalArgumentException("Wrong day");
        }

        this.day = day;
        this.month = months[month - 1];
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        int index = findMonth(month);
        if (month.equals("February")) {
            if (!isLeapYear() && day > 28) {
                throw new IllegalArgumentException("Wrong day for February");
            }
            if (isLeapYear() && day > 29) {
                throw new IllegalArgumentException("Wrong day for February");
            }
        } else if (day <= 0 || ((index % 2 == 1 && index<=7 || index>7 && index % 2 == 0)&& day > 31) || ((index % 2 == 0 && index <= 7 || index % 2 == 1 && index> 7)&& day > 30)) {
            throw new IllegalArgumentException("Wrong day");
        }
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        int index = findMonth(month);
        if (index == -1)
            throw new IllegalArgumentException("Wrong string for month");
        if (day <= 0 || ((index % 2 == 1 && index<=7 || index>7 && index % 2 == 0)&& day > 31) || ((index % 2 == 0 && index <= 7 || index % 2 == 1 && index> 7)&& day > 30)) {
            throw new IllegalArgumentException("Wrong day");
        }
        if (month.equals("February")) {
            if (!isLeapYear() && day > 28) {
                throw new IllegalArgumentException("Wrong day for February");
            }
            if (isLeapYear() && day > 29) {
                throw new IllegalArgumentException("Wrong day for February");
            }
        }
        this.month = month;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException();
        if (month == 2) {
            if (!(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && day > 28) {
                throw new IllegalArgumentException("Wrong day for February");
            }
            if ((year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && day > 29) {
                throw new IllegalArgumentException("Wrong day for February");
            }
        } else if (day <= 0 || ((month % 2 == 1 && month<=7 || month>7 && month % 2 == 0)&& day > 31) || ((month % 2 == 0 && month <= 7 || month % 2 == 1 && month> 7)&& day > 30)) {
            throw new IllegalArgumentException("Wrong day");
        }
        this.month = months[month - 1];
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("Wrong year");
        }
        if (month.equals("February") && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && day > 29) {
            throw new IllegalArgumentException("Wrong day for February");
        }
        if (month.equals("February") && !(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && day > 28) {
            throw new IllegalArgumentException("Wrong day for February");
        }
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%d %s, %d", day, month, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date data = (Date) o;
        return day == data.day && Objects.equals(month, data.month) && year == data.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    //less than
    public boolean after(Date other) {

        if (other.year != year)
            return other.year < year;
        if (findMonth(other.month) != findMonth(month))
            return findMonth(other.month) < findMonth(month);
        return other.day < day;
    }

    public boolean isLeapYear() {
        return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
    }

}
