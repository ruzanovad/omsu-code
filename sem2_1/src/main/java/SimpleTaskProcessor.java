public class SimpleTaskProcessor implements ITaskProcessor{
    private IBuffer<ITask> buffer;

    public SimpleTaskProcessor(IBuffer<ITask> buffer) {
        this.buffer = buffer;
    }

    @Override
    public Integer process(ITask task) {
        if (buffer.empty()){
            return null;
        }

        boolean flag = false;
        int bufSize = buffer.size();
        IBuffer<ITask> newBuffer = new SimpleBuffer<>();
        for (int i = 0; i < bufSize; ++i){
            ITask currTask = buffer.get();
            if (currTask.equals(task)){
                flag = true;
            }
            else
                newBuffer.add(currTask);
        }
        buffer = newBuffer;
        if (!flag)
            return null;
        else {
            int s = 0;
            for (int x : task.getData()) {
                s += x;
            }
            return s;
        }
    }
}
