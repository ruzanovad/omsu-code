
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionDemo {
    public static int getHumans(List<Object> objects) {
        int i = 0;
        for (Object ob : objects) {
            if (ob instanceof Human) {
                i++;
            }
        }
        return i;
    }

    public static List<String> getMethods(Object ob) {
        return Arrays.stream(ob.getClass().getMethods()).map(Method::getName).toList();
    }

    public static List<String> getAllSuper(Object ob) {
        List<String> stringList = new ArrayList<>();
        for (Class<?> super_class = ob.getClass().getSuperclass();
             super_class != null;
             super_class = super_class.getSuperclass()) {
            stringList.add(super_class.getName());
        }
        return stringList;
    }

    public static int countExecutable(List<Object> objects) {
        int i = 0;
        for (Object ob : objects) {
            if (Executable.class.isAssignableFrom(ob.getClass())) {
                i++;
                Executable ex = (Executable) ob;
                ex.execute();
            }
        }
        return i;
    }

    public static List<String> getGettersAndSetters(Object ob) {
        List<String> stringList = new ArrayList<>();
        for (Method m : ob.getClass().getMethods()) {
            if ((m.getReturnType().equals(Void.TYPE) &&
                    m.getParameterCount() == 1 &&
                    !Modifier.isStatic(m.getModifiers()) &&
                    m.getName().startsWith("set")) ||
                    (m.getName().startsWith("get") &&
                            !Modifier.isStatic(m.getModifiers()) &&
                            m.getParameterCount() == 0 &&
                            !m.getReturnType().equals(Void.TYPE)))
                stringList.add(m.getName());
        }
        return stringList;
    }
}
