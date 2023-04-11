import java.util.*;

public class PhoneBook {
    private final Map<Human, List<String>> book;

    public PhoneBook() {
        book = new HashMap<>();
    }

    public PhoneBook(Map<Human, List<String>> map) {
        book = new HashMap<>(map);
    }

    public PhoneBook(PhoneBook other) {
        book = new HashMap<>(other.book);
    }

    public void addNumber(Human human, String number) {
        if (book.containsKey(human)) {
            book.get(human).add(number);
        } else {
            List<String> s = new ArrayList<>();
            s.add(number);
            book.put(human, s);
        }
    }

    public boolean deleteNumber(Human human, String number) {
        if (book.containsKey(human)) {
            int index = book.get(human).indexOf(number);
            if (index == -1)
                return false;
            book.get(human).remove(index);
            //book.get(human).remove(number);
            return true;
        }
        return false;
    }

    public List<String> getListOfNumbers(Human human) throws NoSuchElementException {
        if (book.containsKey(human)) {
            return book.get(human);
        } else {
            throw new NoSuchElementException();
        }
    }

    public Human findByNumber(String number) {
        for (var entry : book.entrySet()) {
            if (entry.getValue().contains(number)) {
                return entry.getKey();
            }

        }
        throw new NoSuchElementException();
    }

    public Map<Human, List<String>> getAllBeginsWith(String begin) {
        Map<Human, List<String>> newBook = new HashMap<>();
        for (var entry : book.entrySet()) {
            if (entry.getKey().getSurname().startsWith(begin)) {
                newBook.put(entry.getKey(), entry.getValue());
            }
        }
        return newBook;
    }
}
