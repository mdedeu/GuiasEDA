public interface IndexService {


    /**
     * elements serán los valores del índice, los anteriores se descartan.
     * @param elements: indice de elementos int.
     * @throws IllegalArgumentException: Si elements es null y deja los valores anteriores.
     */
    void initialize(int[] elements);

    /**
     * Busca una key en el índice, O(log2N).
     * @param key Valor int a buscar en el índice.
     * @return True si el elemento está en elements.
     */
    boolean search(int key);

    /**
     * Inserta el key en la posición correspondiente.
     * @param key Valor int a insertar.
     */
    void insert(int key);

    /**
     * Elimina una aparición del elemento key en el índice.
     * Decrece automáticamente de a chunks.
     * @param key
     */
    void delete(int key);

    /**
     * @param key Elemento que se debe consultar en el índice
     * @return Cantidad de apariciones de key en el índice.
     */
    int occurrences(int key);

    /**
     * devuelve un nuevo arreglo ordenado con los elementos que pertenecen
     * al intervalo dado por leftkey y rightkey.  Si el mismo es abierto/cerrado depende
     * de las variables leftIncluded  y rightIncluded. True indica que es cerrado. El valor
     * devuelto será un arrego de length 0 si no hay elementos que satisfagan al condicion
     **/
    int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded);


    /**
     * imprime el contenido del índice ordenado por su key
     */
    void sortedPrint();


    /**
     * @return  máximo elemento del índice o null si no hay elementos
     */
    int getMax();

    /**
     * @return mínimo elemento del índice o null si no hay elementos
     */
    int getMin();

}
