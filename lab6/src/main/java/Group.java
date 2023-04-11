public class Group {
    private int id;
    private int[] array;

    //varargs (сахар)

    public Group(){
        this.array = new int[0];
    }
    public Group(int id, int ... arr){
        this.id = id;
        this.array = arr;
    }

    public Group(Group other){
        this(other.id, other.array);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }
    public int getLength(){
        return array.length;
    }

}
