

public class FancyRunsort {
    public static void useInsertionSort(Comparable[] a, int startIndex, int endIndex){

        if (endIndex >= a.length-1) endIndex = a.length-1;  // adjust endIndex to end of array

        sort(a, startIndex, endIndex);
    }

    //insertion sort method used from Sedgewick and Wayne.
    public static void sort(Comparable[] a, int lo, int hi) {

        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && Runsort.less(a[j], a[j-1]); j--) {

                Runsort.exch(a, j, j-1);

            }
        }
    }
}
