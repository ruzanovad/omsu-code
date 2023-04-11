import java.util.Arrays;

public class Task implements ITask{
    private int[] data;

    public Task(int ... arr){
        data = arr;
    }

    @Override
    public int[] getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Arrays.equals(data, task.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}
