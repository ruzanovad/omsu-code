public class PriorityTask implements ITask, Comparable<PriorityTask>{

    private int priority;
    @Override
    public int[] getData() {
        return new int[0];
    }

    @Override
    public int compareTo(PriorityTask o) {
        return 0;
    }
}
