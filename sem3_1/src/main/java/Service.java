import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {
    static List<String> getSubjects(Reference reference){
        List<String> subjects = new ArrayList<>();
        for (var x: reference.getRows()){
            subjects.add(x.getSubject());
        }
        return subjects;
    }

    static int getAllHours(Reference reference){
        int sum = 0;
        for (var x: reference.getRows())
        {
            sum+=x.getHours();
        }
        return sum;
    }

    static Double getAverageMark(Reference reference){
        double sum = 0;
//        if (reference.getRows().size() == 0)
//            return null;
        int count = 0;
        for (var x: reference.getRows()){
            Mark mark = x.getMark();
            if (mark != Mark.D && mark!= Mark.PASSED && mark!= Mark.NOT_PASSED) {
                sum += mark.toInt();
                count++;
            }
        }
        if (count == 0)
            return null;
        sum/=count;
        return sum;
    }

    static Map<String, String> getSubjectsWithMarks(Reference reference){
        Map<String, String> map = new HashMap<>();
        for (var x: reference.getRows()){
            map.put(x.getSubject(), x.getMark().toString());
        }
        return map;
    }

    static Map<String,  List<String>> getMarksWithSubjects(Reference reference){
        Map<String, List<String>> map = new HashMap<>();
        for (var x: reference.getRows()){
            if (map.containsKey(x.getMark().toString())){
                map.get(x.getMark().toString()).add(x.getSubject());
            }
            else {
                map.put(x.getMark().toString(), new ArrayList<>(List.of(x.getSubject())));
            }
        }
        return map;
    }
}
