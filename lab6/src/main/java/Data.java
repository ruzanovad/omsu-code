import java.util.Iterator;
import java.util.NoSuchElementException;

public class Data implements Iterable<Integer> {
    private String name;
    private Group[] groups;

    public Data() {
        name = "";
        groups = new Group[0];
    }

    public Data(String name, Group... arr) {
        this.name = name;
        this.groups = arr;
    }

    public Data(Data other) {
        this(other.name, other.groups);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group[] getGroups() {
        return groups;
    }

    public void setGroups(Group[] groups) {
        this.groups = groups;
    }

    public int getLength() {
        return groups.length;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int curGroup = 0;
            private int curInt = 0;

            @Override
            public boolean hasNext() {
                while (curGroup < groups.length) {
                    if (groups[curGroup] != null && groups[curGroup].getLength() != 0) {
                        if (curInt < groups[curGroup].getLength()) {
                            return true;
                        }
                    } else {
                        curInt++;
                        resetIndex();
                    }
                }
                return false;
            }

            @Override
            public Integer next() {
                return getNext();
            }

            private void resetIndex() {
                if (curInt >= groups[curGroup].getLength()) {
                    curInt = 0;
                    curGroup++;
                }
            }

            private int getNext() {
                if (!hasNext())
                    throw new NoSuchElementException();
                int ret = groups[curGroup].getArray()[curInt];
                curInt++;
                resetIndex();
                return ret;
            }
        };
    }
}
