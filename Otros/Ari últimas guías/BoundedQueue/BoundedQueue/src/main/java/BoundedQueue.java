public class BoundedQueue<T> {
    private int dimMax;
    private T[] queue;
    private int dimActual = 0;
    private int indFirst;
    private int indLast;
    @SuppressWarnings("unchecked")
    public BoundedQueue(int dimMax) {
        this.dimMax = dimMax;
        queue = (T[]) new Object[dimMax];
        indFirst = 0;
        indLast = -1;
    }

    public void enqueue(T elem){
        if(isFull())
            throw new RuntimeException("no hay mas lugar en el queue");
        indLast = ++ indLast % dimMax;
        queue[indLast] = elem;
        dimActual++;
    }

    public T dequeue(){
        if(isEmpty())
            throw new RuntimeException("el queue esta vacio");
        T toReturn = queue[indFirst];
        indFirst = ++indFirst % dimMax;
        dimActual--;
        return toReturn;
    }

    private boolean isFull(){
        return dimActual == dimMax;
    }

    private boolean isEmpty(){
        return dimActual == 0;
    }

    public void dump(){
        StringBuilder string = new StringBuilder();
        for (int i = indFirst; i%dimMax != indLast; i++) {
            string.append(queue[i%dimMax]).append(" ");
        }
        string.append(queue[indLast]);
        System.out.println(string.toString());
    }

    public static void main(String[] args) {
        BoundedQueue<Integer> myQueue = new BoundedQueue<>(5);
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(30);
        myQueue.enqueue(40);
        System.out.println("Solo hice enqueues");
        myQueue.dump();
        System.out.println("----------------------");
        System.out.println("hago dos dequeues");
        System.out.println(myQueue.dequeue() );
        System.out.println(myQueue.dequeue() );
        System.out.println("----------------------");
        System.out.println("Despues de dos dequeues");
        myQueue.dump();
        myQueue.enqueue(50);
        myQueue.enqueue(60);
        myQueue.enqueue(70);
        System.out.println("----------------------");
        System.out.println("Hice tres enquques");
        myQueue.dump();
        System.out.println("----------------------");
        System.out.println("hago dos dequeues");
        System.out.println(myQueue.dequeue() );
        System.out.println(myQueue.dequeue() );
        System.out.println("----------------------");
        System.out.println("hice dos dequques");
        myQueue.dump();
    }

}
