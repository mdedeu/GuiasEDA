public class BinarySearch {

    public static boolean iterative(int arr[], int x) {
        int L = 0;
        int R = arr.length - 1;
        while(L <= R) {
            int i = L + (R-L) / 2;
            if(arr[i] < x) {
                L = i + 1;
            }
            else if(arr[i] > x) {
                R = i - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static boolean recursive(int arr[], int x) {
        return recursive(arr, x, 0, arr.length - 1);
    }

    private static boolean recursive(int arr[], int x, int l, int r) {
        if(l <= r) {
            int i = l + (r-l) / 2;
            if(arr[i] == x) {
                return true;
            }
            if(arr[i] > x) {
                return BinarySearch.recursive(arr, x, l, i - 1);
            }
            if(arr[i] < x) {
                return BinarySearch.recursive(arr, x, i + 1, r);
            }
        }
        return false;
    }
}
