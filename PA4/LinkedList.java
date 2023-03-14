public class LinkedList {
    private class Node {
        private int data;
        private Node next;

        Node(int data) {
            this.data = data;
            this.next = null;

        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void addLast(int data){
        Node newNode = new Node(data);
        if (head == null){
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            tail = newNode;
        }
    }

    public int getLast(){
        return tail.data;
    }
}