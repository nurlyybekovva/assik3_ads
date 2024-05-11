import java.util.Iterator;
public class BST<K extends Comparable<K>, V> implements Iterable<K>{
    private class Node{
        private K key;
        private V value;
        private int length = 1;
        private Node right;
        private Node left;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }


    private Node root;
    public BST(){
        root = null;
    }


    public void put(K key, V value){
        if (root == null){
            root = new Node(key, value);
            root.length = 1;
            return;
        }
        Node current = root;
        while(true){
            if (key.compareTo(current.key) < 0){
                if (current.left != null){
                    current = current.left;
                }
                else{
                    current.left = new Node(key, value);
                    current.length = 1 + (current.left == null ? 0 : current.left.length) + (current.right == null ? 0 : current.right.length);
                    return;
                }
            }
            else if(key.compareTo(current.key) > 0){
                if (current.right != null){
                    current = current.right;
                }
                else{
                    current.right = new Node(key, value);
                    current.length = 1 + (current.left == null ? 0 : current.left.length) + (current.right == null ? 0 : current.right.length);
                    return;
                }
            }
            else{
                current.value = value;
                return;
            }
        }
    }


    public V get(K key){
        Node current = root;

        if (current == null){
            return null;
        }
        while (current != null){
            if (key.compareTo(current.key) < 0){
                current = current.left;
            }
            else if (key.compareTo(current.key) > 0){
                current = current.right;
            }
            else{
                return root.value;
            }
        }
        return null;
    }

    public void delete(K key){
        Node current = root;
        Node parent = null;
        boolean left_ch = false;


        if (root == null){
            return;
        }
        if (current == null){
            return;
        }
        while (current != null && current.key.equals(key) != true){
            parent = current;
            if (key.compareTo(current.key) < 0){
                current = current.left;
                left_ch = true;
            }
            else {
                current = current.left;
                left_ch = false;
            }
        }

        if (current.left == null){
            if (current == root){
                root = current.right;
            }
            else if (left_ch == true){
                parent.left = current.right;
            }
            else{
                parent.right = current.right;
            }
        }

        else if (current.right == null){
            if (current == root){
                root = current.left;
            }
            else if (left_ch == true){
                parent.left = current.left;
            }
            else{
                parent.right = current.left;
            }
        }
        else{
            Node cr = current.right;
            Node c = current;
            while(cr.left != null){
                c = cr;
                cr = cr.left;
            }
            if (c != current){
                c.left = cr.right;
                cr.right = current.right;
            }
            if (current == root){
                root = cr;
            }
            else if (left_ch == true){
                parent.left = cr;
            }
            else{
                parent.right = cr;
            }
            cr.left = current.left;
        }
        while (parent != null){
            parent.length = 1 + parent.left.length + parent.right.length;
            parent = findParent(root, parent.key);
        }
    }


    private Node findParent(Node node, K key) {
        Node n = null;
        while (node != null && node.key.equals(key) != true) {
            n = node;
            if (key.compareTo(node.key) < 0)
                node = node.left;

            else
                node = node.right;

        }
        return n;
    }


    public boolean isEmpty() {
        return root.length == 1;
    }
    public boolean contains(K key) {
        return get(key) != null;
    }


    @Override
    public Iterator<K> iterator(){
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        private MyQueue<K> queue = new MyQueue<>();
        public BSTIterator() {
            inOrder(root);
        }
        private void inOrder(Node node) {
            if(node == null) return;
            inOrder(node.left);
            queue.enqueue(node.key);
            inOrder(node.right);
        }
        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }
        @Override
        public K next() {
            return queue.dequeue();
        }
    }
}
