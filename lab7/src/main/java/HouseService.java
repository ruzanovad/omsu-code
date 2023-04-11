import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.Objects;


public class HouseService {
    /**
     * Напишите сервисный класс с методами, которые сериализуют и десериализуют объект типа
     * House в заданный поток средствами Java.
     */
    public static boolean serialize(House house, OutputStream path) {
        try (ObjectOutputStream os = new ObjectOutputStream(path)) {
            os.writeObject(house);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Напишите сервисный класс с методами, которые сериализуют и десериализуют объект типа
     * House в заданный поток средствами Java.
     */
    public static House deserialize(InputStream path) {
        try (ObjectInputStream is = new ObjectInputStream(path)) {
            return (House) is.readObject();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Напишите метод сохранения объекта типа House в csv-файл
     */
    public static boolean writeToCSV(House house) {
        String s = "house" + house.getNumber() + ".csv";
        try (FileWriter csvWriter = new FileWriter(s)) {
            csvWriter.append("Данные о доме\n");
            csvWriter.append("Кадастровый номер:;");
            csvWriter.append(house.getNumber());
            csvWriter.append('\n');
            csvWriter.append("Адрес:;");
            csvWriter.append(house.getAddress());
            csvWriter.append('\n');
            csvWriter.append("Старший по дому:;");
            csvWriter.append(house.getBoss().toString());
            csvWriter.append('\n');
            csvWriter.append("Данные о квартирах\n");
            csvWriter.append("№;Площадь, кв.м;Владельцы\n");
            List<Flat> flats = house.getFlats();
            for (int i = 1; i <= flats.size(); ++i) {
                csvWriter.append(Integer.toString(i));
                csvWriter.append(';');
                csvWriter.append(Double.toString(flats.get(i-1).getArea()));
                csvWriter.append(';');
                var it = flats.get(i-1).getPeople().iterator();
                if (it.hasNext()) {
                    csvWriter.append(it.next().toString());
                }
                while (it.hasNext()) {
                    csvWriter.append(", ");
                    csvWriter.append(it.next().toString());
                }
                csvWriter.append('\n');
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public static String jacksonSerialize(House house) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(house);
    }

    public static House jacksonDeserialize(String house) throws JsonProcessingException {
        House ret = new ObjectMapper().readValue(house, House.class);
        return ret;
    }

    public static boolean compareJson(String o1, String o2) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(o1).equals(mapper.readTree(o2));
    }
}
