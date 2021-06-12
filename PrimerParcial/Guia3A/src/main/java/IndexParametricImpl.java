import java.util.Arrays;

public class IndexParametricImpl<T extends Comparable<? super T>> implements IndexParametricService<T> {

    private final int CHUNK = 20;
    @SuppressWarnings("unchecked")
    private T[] array = (T[]) new Comparable[CHUNK];
    private int dim = 0;

    @Override
    public void initialize(T[] elements) {
        if(elements == null) {
            throw new IllegalArgumentException("Arreglo inválido");
        }
        Arrays.sort(elements);
        array = elements;
        dim = elements.length;
    }

    @Override
    public boolean search(T key) {
        if(dim == 0) {
            return false;
        }
        return array[getClosestPosition(key)] == key;
    }

    @Override
    public void insert(T key) {
        if(dim == array.length) {
            resize();
        }
        int i = getClosestPosition(key);
        System.arraycopy(array, i, array, i + 1, dim - i - 1);
        array[i] = key;
        dim++;
    }

    @Override
    public void delete(T key) {
        int i = getClosestPosition(key);
        if(array[i].compareTo(key) == 0) {
            System.arraycopy(array, i + 1, array, i, dim - i - 1);
            dim--;
        }
    }

    @Override
    public int occurrences(T key) {
        int occurrences = 0;
        if(dim == 0) {
            return 0;
        }
        int pos = getClosestPosition(key);
        for(int i  = pos; i < dim && array[i].compareTo(key) == 0; i++) {
            occurrences++;
        }
        for(int i  = pos - 1; i >= 0 && array[i].compareTo(key) == 0; i--) {
            occurrences++;
        }
        return occurrences;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] range(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded) {
        int left = getClosestPosition(leftKey);
        int right = getClosestPosition(rightKey);
        if(right >= dim){
            right = dim - 1;
            rightIncluded = true;
        }
        if(!leftIncluded) {
            while(left < dim && array[left].compareTo(leftKey) == 0) {
                left++;
            }
        } else {
            while(left > 0 && array[left - 1].compareTo(leftKey) == 0) {
                left--;
            }
        }
        if(!rightIncluded) {
            while(right >= 0 && array[right].compareTo(rightKey) == 0) {
                right--;
            }
        } else {
            while(right < dim - 1 && array[right + 1].compareTo(rightKey) == 0) {
                right++;
            }
        }

        if(left > right) {
            return (T[]) new Comparable[0];
        }
        T[] aux = (T[]) new Comparable[right - left + 1];
        System.arraycopy(array, left, aux, 0, right - left + 1);
        return aux;
    }

    @Override
    public void sortedPrint() {
        for(T elem : array) {
            System.out.println(elem);
        }
    }

    @Override
    public T getMax() {
        return array[dim - 1];
    }

    @Override
    public T getMin() {
        return array[0];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for(int i = 0; i < array.length - 1; i++) {
            stringBuilder.append(array[i]).append(", ");
        }
        if(array.length > 0) {
            stringBuilder.append(array[array.length - 1]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void resize() {
        array = Arrays.copyOf(array, array.length + CHUNK);
    }

    public int getClosestPosition(T key) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int i = left + (right-left) / 2;
            int cmp = array[i].compareTo(key);
            if(cmp < 0) {
                left = i + 1;
            }
            else if(cmp > 0) {
                right = i - 1;
            } else {
                return i;
            }
        }
        return left;
    }
}
