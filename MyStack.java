

public class MyStack<T>  {

    private int size;
    private Node<T> head;

    public MyStack() {
        head = null;
        size = 0;
    }

    public void push(T element) {
        if(head == null) {
            head = new Node(element);

        } else {
            Node<T> newNode = new Node(element);
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public T pop() throws NullPointerException {
        T topData = head.data;
        if(head == null) {
            System.out.println("the top is now null, list is now empty");
        }
        else {
            head = head.next;
            size--;
            return topData;
        }
        try {
            pop();
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println(ex.getMessage());
        }
        return topData;
    }

    public T top() {
        if(head != null)
            return head.data;
        else
            return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "size=" + size +
                ", head=" + head +
                '}';
    }

    public void print() throws NullPointerException {
        if (this.head == null){
            throw new NullPointerException("The Stack is now empty.");
        }
        System.out.println( "MyStack{" +
                "size=" + size +
                ", head=" + head.data +
                '}');


    }



    private class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

    }

}