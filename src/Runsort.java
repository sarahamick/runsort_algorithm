
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Runsort {

    public static void main(String[] args) {
        Comparable[] a = In.readStrings();
        sort(a);
        show(a);
    }

    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        int nextStart = run(a, 0);

        while(true){
            if(nextStart == -1) break;
            nextStart = run(a, nextStart);
        }
    }

    public static int run(Comparable[] a, int startIndex){

        int endOfFirstRun = nextRun(a, startIndex);

        if(endOfFirstRun == a.length-1) {
            if(startIndex==0) return -1;
            else return 0;
        }

        int endOfSecondRun = nextRun(a, endOfFirstRun+1);

        if((endOfSecondRun-startIndex)<8) {
            FancyRunsort.useInsertionSort(a, endOfFirstRun, endOfSecondRun);
        }

        else merge(a, startIndex, endOfFirstRun, endOfSecondRun);

        if(endOfSecondRun == a.length-1) return 0;

        return endOfSecondRun + 1;
    }

    //returns the index of the end of the next run
    public static int nextRun(Comparable[] a, int start){
        if(start==a.length-1) return start;
        int l = start+1;
        while(less(a[start], a[l])){
            if(l==a.length-1) return l;
            start++; l++;
        }
        return start;
    }

    //following methods taken from Sedgewick and Wayne, Section 2.2 and 2.3.
    public static void merge(Comparable[] a, int lo, int mid, int hi)
    {  // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi )              a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) <= 0;
    }


    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void show(Comparable[] a)
    {  // Print the array, on a single line.
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

}
