package Sorting;


public class Sort {

    /**
     * INSERTIONSORT
     * Complejijad espacial: O(1)
     * Complejidad temporal: O(n^2)
     */
    public static void insertionSort(int[] values) {
        for (int rec = 1; rec < values.length; rec++) {
            int temp = values[rec];
            int pos = rec;
            while (pos > 0 && values[pos - 1] > temp) {
                values[pos] = values[pos - 1];
                pos--;
            }
            values[pos] = temp;
        }
    }

    /**
     * QUICKSORT
     * Complejijad espacial: O(log2n)
     * Complejidad temporal: Para el mejor caso y promedio: O(n logn). Para el peor caso: O(n^2).
     */
    public static void quickSort(int[] values) {
        quickSort(values, 0, values.length - 1);
    }

    private static void quickSort(int values[], int min, int max) {
        if (min >= max) {
            return;
        }

        //puede ser otro pivot que no sea el primer elemento
        int pivot = values[min];
        swap(values, min, max); //El pivot al final

        //particion
        int middle = partition(values, min, max - 1, pivot);
        swap(values, middle, max); //El pivot al lugar correcto

        //recursion en ambas particiones
        quickSort(values, min, middle - 1);
        quickSort(values, middle + 1, max);
    }

    private static void swap(int[] data, int pos1, int pos2) {
        int tmp = data[pos1];
        data[pos1] = data[pos2];
        data[pos2] = tmp;
    }

    /**
     * Particionar en <= pivot y > pivot
     * Devolver la posición del elemento a swapeear con el pivot
     */
    private static int partition(int values[], int left, int right, int pivot) {
        while (left <= right) {
            while (left <= right && values[left] < pivot) {
                left++;
            }
            while (left <= right && values[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(values, left++, right--);
            }
        }
        return left;
    }

    /**
     * MERGESORT
     * Complejijad espacial: O(n)
     * Complejidad temporal: O(n log n).
     */
    public static void mergeSort(int[] values) {
        mergeSort(values, 0, values.length - 1);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            //Encuentra el punto medio del vector.
            int middle = (left + right) / 2;

            //Divide la primera y segunda mitad (llamada recursiva).
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);

            //Une las mitades.
            merge(arr, left, middle, right);
        }
    }

    public static void merge(int[] arr, int left, int middle, int right) {
        //Encuentra el tamaño de los sub-vectores para unirlos.
        int n1 = middle - left + 1;
        int n2 = right - middle;

        //Vectores temporales.
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        //Copia los datos a los arrays temporales.
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[middle + j + 1];
        }
        /* Une los vectores temporales. */

        //Índices inicial del primer y segundo sub-vector.
        int i = 0, j = 0;

        //Índice inicial del sub-vector arr[].
        int k = left;

        //Ordenamiento.
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }//Fin del while.

        /* Si quedan elementos por ordenar */
        //Copiar los elementos restantes de leftArray[].
        while (i < n1) {
            arr[k++] = leftArray[i++];
        }
        //Copiar los elementos restantes de rightArray[].
        while (j < n2) {
            arr[k++] = rightArray[j++];
        }
    }

}
