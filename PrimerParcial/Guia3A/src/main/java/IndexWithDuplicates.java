import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndexWithDuplicates implements IndexService {

    private int[] array;
    private int dim;
    private final int CHUNK = 20;

    public IndexWithDuplicates() {
        this.array = new int[CHUNK];
        dim = 0;
    }

    @Override
    public void initialize(int[] elements) {
        if(elements == null) {
            throw new IllegalArgumentException("Arreglo inválido\n");
        }
        Arrays.sort(elements);
        array = elements;
        dim = array.length;
    }

    @Override
    public boolean search(int key) {
        if(dim == 0) {
            return false;
        }
        return array[getClosestPosition(key)] == key;
    }

    @Override
    public void insert(int key) {
        if(dim == array.length) {
            resize();
        }
        int i = getClosestPosition(key);
        System.arraycopy(array, i, array, i + 1, dim - i - 1);
        array[i] = key;
        dim++;
    }

    @Override
    public void delete(int key) {
        int i = getClosestPosition(key);
        if(array[i] == key) {
            System.arraycopy(array, i + 1, array, i, dim - i - 1);
            dim--;
        }
    }

    @Override
    public int occurrences(int key) {
        int occurrences = 0;
        if(dim == 0) {
            return 0;
        }
        int pos = getClosestPosition(key);
        for(int i  = pos; i < dim && array[i] == key; i++) {
            occurrences++;
        }
        for(int i  = pos - 1; i >= 0 && array[i] == key; i--) {
            occurrences++;
        }
        return occurrences;
    }

    @Override
    public int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded) {
        int left = getClosestPosition(leftKey);
        int right = getClosestPosition(rightKey);
        if(!leftIncluded) {
            while(left < dim && array[left] == leftKey) {
                left++;
            }
        }
        if(!rightIncluded) {
            while(right >= 0 && array[right] == rightKey) {
                right--;
            }
        }

        if(left >= right) {
            return new int[0];
        }
        int[] aux = new int[right - left + 1];
        System.arraycopy(array, left, aux, 0, right - left + 1);
        return aux;
    }

    @Override
    public void sortedPrint() {

    }

    @Override
    public int getMax() {
        return array[dim - 1];
    }

    @Override
    public int getMin() {
        return array[0];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for(int i = 0; i < array.length - 1; i++) {
            stringBuilder.append(array[i] + ", ");
        }
        if(array.length > 0) {
            stringBuilder.append(array[array.length - 1]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void resize(int delta) {
        array = Arrays.copyOf(array, array.length + delta);
    }

    private void resize() {
        array = Arrays.copyOf(array, array.length + CHUNK);
    }

    public int getClosestPosition(int key) {
        int left = 0;
        int right = dim - 1;
        while(left <= right) {
            int i = left + (right-left) / 2;
            if(array[i] < key) {
                left = i + 1;
            }
            else if(array[i] > key) {
                right = i - 1;
            } else {
                return i;
            }
        }
        return left;
    }

}
