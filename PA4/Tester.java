
public class Tester {
    public static void main(String[] args) {
        DynamicArray flesh = new DynamicArray();
        flesh.add(6);
        flesh.add(9);
        flesh.add(12);
        System.out.println(flesh.toString());
        flesh.remove(2);
        System.out.println(flesh.toString());
    }
}
