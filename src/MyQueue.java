public class MyQueue<T extends Comparable<T>> {
    MyArrayList<T> queue = new MyArrayList<>(); // Based on MyArrayList
    public MyQueue(){
    } // Constructor

    public T front (){
        return queue.getFirst();
    } // Returns first element

    public T back(){
        return queue.getLast();
    } // Returns last element

    public T dequeue(){
        T item = queue.getFirst();
        queue.remove(0);
        return item;
    } // Gets first element and removes it

    public void enqueue(T item){
        queue.add(item);
    }

    public boolean isEmpty(){
        return queue.size() == 0;
    } // Returns true if the queue is empty

    public int size(){
        return queue.size();
    } // Returns the number of elements in the queue

    public void clear(){
        queue.clear();
    } // Removes all elements from the queue
}