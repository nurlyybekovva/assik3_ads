public class MyHashTable<K , V> {
    private class HashNode<K, V>{
        private K key;
        private V value;
        private HashNode<K, V> next;

        private HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }

    }
    private HashNode<K, V>[] buckets;
    private int M = 11; // количество бакетов
    private double loadFactor = 0.25;
    private int size = 0;
    public MyHashTable(){
        buckets = new HashNode[M];
    }

    public MyHashTable(int initialCapacity){
        M = (int)(loadFactor * initialCapacity);
        buckets = new HashNode[M];
    }

    private int hash(K key){
        return (key.hashCode() & 0xfffffff) % M; // в какой бакет идет
    }

    private void increaseCapacity(){
        M = M * 2;
        HashNode<K, V> []temp = buckets;
        buckets = new HashNode[M];
        for (int i = 0; i < temp.length; i++){
            if (temp[i] != null){
                HashNode<K, V> node = temp[i];
                while(node != null){
                    reput(node.key, node.value);
                    node = node.next;
                }
            }
        }
    }

    public int countElements(int index){
        int count = 0;
        HashNode<K, V> temp = buckets[index];
        while (temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    public void put(K key, V value){
        if ((double) size / M > loadFactor)
            increaseCapacity();
        int index = hash(key);
        HashNode<K, V> temp = new HashNode<>(key, value);

        temp.next = buckets[index];
        buckets[index] = temp;
        size++;
    }

    public void reput(K key, V value){
        int index = hash(key);
        HashNode<K, V> temp = new HashNode<>(key, value);

        temp.next = buckets[index];
        buckets[index] = temp;
    }

    public V remove(K key){
        int index = hash(key);
        HashNode<K, V> temp = buckets[index];
        HashNode<K, V> prev = null;
        while(temp.next != null){
            if (temp.key.equals(key)){
                if (prev == null){
                    buckets[index] = temp.next;
                }
                else{
                    prev.next = temp.next;
                }
                size--;
                return temp.value;
            }
            prev = temp;
            temp = temp.next;
        }
        return null;
    }

    public int getM(){
        return M;
    }

    public V get(K key){
        int index = hash(key);
        HashNode<?, ?> temp = buckets[index];
        while (temp.next != null){
            if (temp.key.equals(key)){
                return (V) temp.value;
            }
            temp = temp.next;
        }
        return null;
    }       // находит сразу по кей находим индекс и ищем по определенному бакету

    public K getKey(V value){
        for (int i = 0; i < M; i++){
            HashNode<?, ?> temp = buckets[i];
            while (temp.next != null){
                if (temp.value.equals(value)){
                    return (K) temp.key;
                }
                temp = temp.next;
            }
        }
        return null;      // просматривает все бакеты и если нет то нулл
    }

    public boolean containsWithK(K key){
        return get(key) != null;
    }

    public boolean contains(V value){
        return getKey(value) != null;
    }
}