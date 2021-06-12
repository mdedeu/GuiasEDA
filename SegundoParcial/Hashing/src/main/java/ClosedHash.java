import java.util.function.Function;
//Open Addressing o ClosedHashing .Baja logica
public class ClosedHash<K,V> {
    private final int initiallookUpSize= 10;

    // estática. No crece. Espacio suficiente...
    @SuppressWarnings("unchecked")
    private Node<K,V>[] LookUp= new Node[initiallookUpSize];
    private final Function<? super K, Integer> prehash;
    private int lookupSize = initiallookUpSize;
    private int uses;
    private final double MAXIMUM_LOAD_FACTOR= 0.75;
@SuppressWarnings("unchecked")
    private void rehash(){
        lookupSize = lookupSize * 2;
        Node<K, V>[] aux = LookUp;

        LookUp = new Node[lookupSize];
        for (Node<K, V> kvNode : aux) {
            if (kvNode != null && kvNode.occupied) {
                uses = 0;
                insert(kvNode.key, kvNode.value);
            }
        }
    }
    public V getValue(K key) {
        Node<K, V> entry = get(key);
        if (entry == null)
            return null;

        return entry.value;
    }

    public ClosedHash( Function<? super K, Integer> mappingFn)
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
    private Node<K,V> get(K key)
    {
        int pos = hash(key);
        while (LookUp[pos] != null) {
            if (LookUp[pos].key == key && LookUp[pos].occupied)
                return LookUp[pos];
            pos = (pos + 1) % lookupSize;
        }
        return null;

    }

    // insert = update
    public void insert(K key, V value)
    {
        int pos = hash(key);
        int firstEmpty = 0;
        boolean wasEmpty = false;
        while (LookUp[pos] != null) {
            if (LookUp[pos].key == key && LookUp[pos].occupied) {
                LookUp[pos].value = value;
                return;
            }
            if (!LookUp[pos].occupied && !wasEmpty) {
                wasEmpty = true;
                firstEmpty = pos;
            }
            pos = (pos + 1) % lookupSize;
        }
        if (wasEmpty) {
            LookUp[firstEmpty] = new Node<>(key, value);
        } else {
            LookUp[pos] = new Node<>(key, value);
        }
        uses++;
        if (Double.compare((double) uses / lookupSize, MAXIMUM_LOAD_FACTOR) >= 0) {
            rehash();
        }

    }

    public void delete(K key)
    {
        int pos = hash(key);
        while (LookUp[pos] != null) {
            if (LookUp[pos].key == key && LookUp[pos].occupied) {
                if (LookUp[(pos + 1) % lookupSize] != null) {
                    LookUp[pos].occupied = false;
                } else {
                    LookUp[pos] = null;
                    uses--;
                }
                return;
            }
            pos = (pos + 1) % lookupSize;
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
        boolean occupied;
        Node(K theKey, V theValue)
        {
            key= theKey;
            value= theValue;
            occupied=true;
        }
        public String toString()
        {
            return String.format("key=%s, value=%s", key, value );
        }
    }
}
