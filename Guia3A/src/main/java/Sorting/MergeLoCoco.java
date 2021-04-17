package Sorting;

public class MergeLoCoco {

    public static void mergesort(int[] unsorted) {
        mergesort(unsorted, unsorted.length - 1);
    }

    public static void mergesort(int[] unsorted, int cantElements) {
        mergesortHelper(unsorted, 0, cantElements);
    }

    private static void mergesortHelper(int[] unsorted, int leftPos, int rightPos) {
        if (rightPos <= leftPos)
            return;

        int mid = (leftPos + rightPos) / 2;
        mergesortHelper(unsorted, leftPos, mid);
        mergesortHelper(unsorted, mid + 1, rightPos);

        merge(unsorted, leftPos, mid, rightPos);
    }

    private static void merge(int[] unsorted, int left, int mid, int right) {
        int[] aux = new int[right - left + 1];

        if (right - left + 1 >= 0)
            System.arraycopy(unsorted, left, aux, 0, right - left + 1);

        //        int i = left, j = mid + 1, k = left;
        int i = 0, j = mid - left + 1, k = left;
        mid = mid - left;
        right = right - left;

        while (i <= mid && j <= right) {
            if (aux[i] <= aux[j]) {
                unsorted[k++] = aux[i++];
            } else
                unsorted[k++] = aux[j++];
        }

        // Copio el resto del array pues solo copié los valores chicos
            while (i <= mid) {
                unsorted[k++] = aux[i++];
        }
}

    public static void main(String[] args) {
        int[] array1 = {52,43,25,12,85,1,9,74,51,25,12,35,1,4,8,94,6,56,4515,21,56,564,651,651,6,854913,1,84,91,61,65,498,2};

        SortingMain.printArray(array1);
        MergeLoCoco.mergesort(array1);
        SortingMain.printArray(array1);


    }
}
