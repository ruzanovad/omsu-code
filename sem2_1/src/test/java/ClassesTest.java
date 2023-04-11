import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ClassesTest {

    @Test
    void buffer(){
        IBuffer<Integer> iBuffer = new SimpleBuffer<>();
        int[] arr = new int[]{1, 2, 3, 4};
        for (int x: arr){
            iBuffer.add(x);
        }
        assertEquals(4, iBuffer.size());
        for (int x: arr){
            assertEquals(x, iBuffer.get());
        }
        assertTrue(iBuffer.empty());
    }
    @Test
    void generator(){
        IBuffer<ITask> iTaskIBuffer= new SimpleBuffer<>();
        iTaskIBuffer.add(new Task(1));
        iTaskIBuffer.add(new Task(1, 2));
        iTaskIBuffer.add(new Task(1, 2, 3));

        {
            List<ITask> tasks = new ArrayList<>();
            Collections.addAll(tasks, new Task(1), new Task(1, 2));
            List<ITask> expected  = new ArrayList<>();
            int size = iTaskIBuffer.size();
            for (int i = 0;i < size-1; ++i){
                expected.add(iTaskIBuffer.get());
            }
            assertEquals(expected, tasks);
        }


        SimpleTaskGenerator iGenerator = new SimpleTaskGenerator(iTaskIBuffer,0, 5);
        iGenerator.generate();
        iGenerator.withStartValue(-3).withAmount(3).generate();
        {
            List<ITask> tasks = new ArrayList<>();
            Collections.addAll(tasks, new Task(1, 2, 3), new Task(0, 1, 2, 3, 4),
                    new Task(-3, -2, -1));
            List<ITask> expected  = new ArrayList<>();
            IBuffer<ITask> g = iGenerator.getBuffer();
            int size = g.size();
            for (int i = 0;i < size; ++i){
                expected.add(g.get());
            }
            assertEquals(expected, tasks);
        }

    }
    @Test
    void processor(){
        IBuffer<ITask> iTaskIBuffer= new SimpleBuffer<>();
        iTaskIBuffer.add(new Task(1));
        iTaskIBuffer.add(new Task(1, 2));
        iTaskIBuffer.add(new Task(1, 2, 3));

        ITaskProcessor iTaskProcessor = new SimpleTaskProcessor(iTaskIBuffer);
        assertNull(iTaskProcessor.process(new Task(-1)));
        assertEquals(6, iTaskProcessor.process(new Task(1, 2, 3)));
        assertEquals(1, iTaskProcessor.process(new Task(1)));
        assertEquals(3, iTaskProcessor.process(new Task(1, 2)));
        assertNull(iTaskProcessor.process(new Task(1, 2)));
    }
}