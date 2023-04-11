public class SimpleTaskGenerator implements ITaskGenerator{
    private IBuffer<ITask> buffer;
    private int startValue;
    private int amount;

    public SimpleTaskGenerator(IBuffer<ITask> buffer, int startValue, int amount){
        this.buffer = buffer;
        this.startValue = startValue;
        this.amount = amount;
    }
    @Override
    public void generate() {
        int[] t = new int[amount];
        for (int i = 0; i < amount; ++i){
            t[i] = startValue+i;
        }
        buffer.add(new Task(t));

    }

    public IBuffer<ITask> getBuffer(){
        return buffer;
    }
    public SimpleTaskGenerator withStartValue(int value){
        this.startValue = value;
        return this;
    }

    public SimpleTaskGenerator withAmount(int amount){
        this.amount = amount;
        return this;
    }
}
