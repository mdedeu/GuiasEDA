import java.util.function.Function;
public class Hash<K, V>
{
    private final int initiallookUpSize= 10;

    // estática. No crece. Espacio suficiente...
    @SuppressWarnings("unchecked")
    private Node<K,V>[] LookUp= new Node[initiallookUpSize];

    private final Function<? super K, Integer> prehash;

    private int usedKeys;
    private final double maximumLoadFactor= 0.75;

    public double getLoadFactor(){
        return (double)usedKeys / LookUp.length;
    }
   @SuppressWarnings("unchecked")
    private void rehash(){
        Node<K, V>[] newLookUp = new Node[LookUp.length * 2];
        for(int i = 0; i < LookUp.length; i++) {
            if(LookUp[i] != null) {
                newLookUp[hash(LookUp[i].key)] = LookUp[i];
            }
        }
        LookUp = newLookUp;
    }

    public Hash( Function<? super K, Integer> mappingFn)
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
        return LookUp[ hash( key) ];
    }
    // insert = update
    public void insert(K key, V value)
    {
        if(getLoadFactor()>= maximumLoadFactor)
            rehash();
        LookUp[ hash(key)] = new Node<K,V>(key, value);
        usedKeys++;
    }

    public void delete(K key)
    {
        LookUp[ hash( key) ] = null;
        usedKeys--;
    }
    public void dump(){
        for(int rec= 0; rec < LookUp.length; rec++)
            if (LookUp[rec] == null)
                System.out.printf("slot %d is empty%n", rec);
            else
                System.out.printf("slot %d contains %s%n",
                        rec, LookUp[rec]);
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
