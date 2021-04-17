public interface IndexParametricService <T extends Comparable<? super T>>{

    /**
     * lanza excepction si elements is null o si alguno de los elementos del
     * arreglo proporcionado son null
     * @param elements valores del índice, los anteriores se descartan
     */
    void initialize(T [] elements);

    /**
     * busca una key en el índice, O(log2 N)
     * @param key clave a buscar en el indice
     * @return True si existe la clave
     */
    boolean search(T key);

    /**
     * inserta el key en pos correcta. Crece automáticamente de a chunks.
     * si el valor proporcionado es null, ignora el pedido.
     * @param key clave a insertar
     */
    void insert(T key);

    /**
     * borra el key si lo hay, sino lo ignora.
     * decrece automáticamente de a chunks
     * @param key clave a eliminar
     */
    void delete(T key);

    /**
     * @param key clave a buscar ocurrencias
     * @return cantidad de apariciones de la clave especificada
     */
    int occurrences(T key);

    /**
     * devuelve un nuevo arreglo ordenado con los elementos que pertenecen
     * al intervalo dado por leftkey y rightkey.  Si el mismo es abierto/cerrado depende
     * de las variables leftIncluded  y rightIncluded. True indica que es cerrado. El valor
     * devuelto será un arrego de length 0 si no hay elementos que satisfagan al condicion
    **/
     T[] range(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded);


    /**
     * imprime el contenido del índice ordenado por su key
     */
    void sortedPrint();


    /**
     * @return  máximo elemento del índice o null si no hay elementos
     */
    T getMax();

    /**
     * @return mínimo elemento del índice o null si no hay elementos
     */
    T getMin();

}
