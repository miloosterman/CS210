public class DynamicArray {
    private int[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 3;

    DynamicArray() {
        this.array = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    void add(int value) {
        if (this.size >= this.array.length)
            resize(2 * this.array.length);
        this.array[size] = value;
        this.size++;
    }

    int get(int index) {
        return this.array[index];
    }

    void remove(int index) {
        int[] temp = new int[this.array.length - 1];
        for (int i = 0, j = 0; i < size; i++){
            if(i != index){
                temp[j++] = this.array[i];
            }
        }

        this.array = temp;
    }

    void resize(int capacity) {
        int[] temp = new int[capacity];
        size = capacity < this.size ? capacity : this.size;
        for (int i = 0; i < size; i++)
            temp[i] = this.array[i];
        this.array = temp;
    }

    int size() {
        return this.size;
    }

    public String toString(){
        String res = "";
        for (int i : this.array){
            res += Integer.toString(i);
        }

        return res;
    }

}