
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
//Closed Addressing o OpenHashing . Zona de Overflow
public class OpenHash<K,V>{
    private int initiallookUpSize= 10;

    // estática. No crece. Espacio suficiente...
    @SuppressWarnings("unchecked")
    private LinkedList<Node<K,V>>[] LookUp= new LinkedList[initiallookUpSize];


    private Function<? super K, Integer> prehash;

    private int usedKeys;
    private final double maximumLoadFactor= 0.75;

    public double getLoadFactor(){
        return (double)usedKeys / LookUp.length;
    }
    @SuppressWarnings("unchecked")
    private void rehash(){
        LinkedList<Node<K, V>>[] prevLookUp = LookUp;
        LookUp = new LinkedList[LookUp.length * 2];
        int prevUsedKey = usedKeys;

        for (List<Node<K, V>> list : prevLookUp) {
            if (list != null) {
                usedKeys = 0; //Evita el rehash
                for (Node<K, V> node : list) {
                    insert(node.key, node.value);
                }
            }
        }

        usedKeys = prevUsedKey;
    }

    public OpenHash( Function<? super K, Integer> mappingFn)
    {
        prehash= mappingFn;
    }

    // ajuste al tamaño de la tabla
    private int hash(K key)
    {
        if (key == null)
            throw new RuntimeException("No key provided");

        return prehash.apply(key) % LookUp.length;
    }

    public V getValue(K key)
    {
        Node<K, V> entry = get(key);
        if (entry == null)
            return null;

        return entry.value;
    }
    private Node<K,V> get(K key)
    {
        LinkedList<Node<K, V>> list = LookUp[hash(key)];
        if (list != null) {
            for (Node<K, V> node : list) {
                if (node.key.equals(key)) {
                    return node;
                }
            }
        }
        return null;

    }

    // insert = update
    public void insert(K key, V value)
    {
        int hashKey = hash(key);
        if (LookUp[hashKey] == null) {
            LookUp[hashKey] = new LinkedList<>();
        }

        Node<K, V> aux = get(key);
        if (aux != null) {
            aux.value = value;
        } else {
            LookUp[hashKey].add(new Node<>(key, value));
            usedKeys++;
        }

        if (getLoadFactor() > maximumLoadFactor) {
            rehash();
        }

    }

    public void delete(K key)
    {
        int hashKey = hash(key);
        Node<K, V> node = get(key);
        if (node != null) {
            LookUp[hashKey].remove(node);
            usedKeys--;
        }
        if (LookUp[hashKey].isEmpty()) {
            LookUp[hashKey] = null;
        }
    }

    public void dump(){
        for(int rec= 0; rec < LookUp.length; rec++)
            if (LookUp[rec] == null)
                System.out.println(String.format("slot %d is empty", rec));
            else
                System.out.println(String.format("slot %d contains %s",
                        rec, LookUp[rec]));
    }

    static class Node<K,V>
    {
        final K key;
        V value;
        Node(K theKey, V theValue)
        {
            key= theKey;
            value= theValue;
        }
        public String toString()
        {
            return String.format("key=%s, value=%s", key, value );
        }
    }
}
