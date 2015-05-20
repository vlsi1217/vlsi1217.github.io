package freq2_tony;

// CSE 373, Winter 2013
// This program compares the runtime of various sorting algorithms.
// https://courses.cs.washington.edu/courses/cse373/13wi/lectures/03-01/Sorting.java
// Today's version contains:
// bogo, bubble, selection, insertion, shell, merge,
// quick, heap, bucket, radix, and stooge sort.
//
// (We did not code heap, bucket, radix, or stooge sort, but they
// are included here at the bottom so that you can look at them or
// test them out if you like.)

import java.util.*; // for Random

public class WashingtonSort {
  private static final Random RAND = new Random(42); // random number generator

  public static void main(String[] args) {
    int LENGTH = 1000; // initial length of array to sort
    int RUNS = 10; // how many times to grow by 2?

    for (int i = 0; i < RUNS; i++) {
      int[] a = createRandomArray(LENGTH);

      // perform a sort and time how long it takes
      long startTime1 = System.currentTimeMillis();
      bucketSort(a);
      long endTime1 = System.currentTimeMillis();

      if (!isSorted(a)) {
        throw new RuntimeException("not sorted afterward: "
            + Arrays.toString(a));
      }

      System.out.printf("%10d elements  =>  %6d ms \n", LENGTH, endTime1
          - startTime1);
      LENGTH *= 2; // double size of array for next time
    }
  }


  // Sorts the elements of the given array using the "quick sort"
  // algorithm, which divides the input up into two partitions
  // based on which elements are less-than and greater-than a given
  // chosen "pivot" value.
  // This implementation chooses the first element as the pivot.
  public static void quickSort(int[] a) {
    quickSort(a, 0, a.length - 1);
  }

  // Recursive helper for quickSort that sorts the given range of the array,
  // from index [min .. max], inclusive.
  private static void quickSort(int[] a, int min, int max) {
    if (min >= max) {
      return; // base case; EZ mode
    }

    // pick a "pivot" value (first element)
    int pivot = a[min];

    // move the pivot to the end
    swap(a, min, max);

    // partition: left: < pivot, right: > pivot
    int i = min;
    int j = max - 1;
    while (i <= j) {
      while (i <= j && a[i] < pivot) {
        i++;
      }
      while (i <= j && a[j] > pivot) {
        j--;
      }

      if (i <= j) {
        swap(a, i, j);
        i++;
        j--;
      }
    }

    // move pivot back to middle
    swap(a, max, i);

    // sort the two partitions
    quickSort(a, min, i - 1);
    quickSort(a, i + 1, max);
  }


  // Arranges the elements of the given array into sorted order
  // using the "merge sort" algorithm, which splits the array in half,
  // recursively sorts the halves, then merges the sorted halves.
  // It is O(N log N) for all inputs.
  public static void mergeSort(int[] a) {
    if (a.length >= 2) {
      // split array in half
      int[] left = Arrays.copyOfRange(a, 0, a.length / 2);
      int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

      // sort the halves
      mergeSort(left);
      mergeSort(right);

      // merge them back together
      int i1 = 0;
      int i2 = 0;
      for (int i = 0; i < a.length; i++) {
        if (i2 >= right.length || (i1 < left.length && left[i1] < right[i2])) {
          a[i] = left[i1];
          i1++;
        } else {
          a[i] = right[i2];
          i2++;
        }
      }
    }
  }


  // Arranges the elements of the given array into sorted order
  // using the "shell sort" algorithm, which creates slices of
  // the array separated by a given gap and insertion-sorts by
  // that gap, eventually ending in a normal insertion sort.
  // It is O(N^1.25) on average.
  public static void shellSort(int[] a) {
    for (int gap = a.length / 2; gap >= 1; gap = gap / 2) {
      // insertion sort on every gap'th element
      for (int i = gap; i < a.length; i++) {
        int temp = a[i];
        int j = i;
        while (j >= gap && a[j - gap] > temp) {
          a[j] = a[j - gap];
          j -= gap;
        }
        a[j] = temp;
      }
    }
  }



  // Arranges the elements of the given array into sorted order
  // using the "insertion sort" algorithm, which manages a sorted
  // prefix of the array, and one at a time, shifts each next element
  // to be in the right ordered place within that sorted prefix
  // until the entire array is sorted.
  // It is O(N^2) but faster (O(N)) for sorted inputs.
  public static void insertionSort(int[] a) {
    for (int i = 1; i < a.length; i++) {
      // move a[pass] into its proper place,
      // assuming that a[0]..a[pass-1] are sorted
      int temp = a[i];
      int j = i;
      while (j >= 1 && a[j - 1] > temp) {
        a[j] = a[j - 1];
        j--;
      }
      a[j] = temp;
    }
  }

  // Arranges the elements of the given array into sorted order
  // using the "selection sort" algorithm, which makes sweeps over
  // the array, finding the i'th smallest element each time,
  // and swapping it to be stored at index i.
  // It is O(N^2) for all inputs.
  public static void selectionSort(int[] a) {
    for (int pass = 0; pass < a.length; pass++) {
      // figure out what should go into a[pass]
      int min = pass;
      for (int j = pass + 1; j < a.length; j++) {
        if (a[j] < a[min]) {
          min = j;
        }
      }

      swap(a, pass, min);
    }
  }

  // Arranges the elements of the given array into sorted order
  // using the "bubble sort" algorithm, which makes sweeps over
  // the array and swaps neighbors that are out of order.
  // It is O(N^2) for all inputs.
  public static void bubbleSort(int[] a) {
    for (int pass = 0; pass < a.length; pass++) {
      // make a sweep
      boolean changed = false;
      for (int i = 0; i < a.length - 1 - pass; i++) {
        if (a[i] > a[i + 1]) {
          swap(a, i, i + 1);
          changed = true;
        }
      }
      if (!changed) {
        return;
      }
    }
  }

  // Arranges the elements of the given array into sorted order
  // using the "bogo sort" algorithm.
  // It is O(N!) on average. (very bad!)
  public static void bogoSort(int[] a) {
    while (!isSorted(a)) {
      shuffle(a);
    }
  }

  // Sorts the contents of a using heap sort algorithm.
  public static void heapSort(int[] a) {
    // turn a into a max-heap
    for (int i = a.length / 2; i >= 0; i--) {
      bubbleDown(a, i, a.length - 1);
    }
    for (int i = a.length - 1; i > 0; i--) {
      swap(a, 0, i); // remove-max, move to end
      bubbleDown(a, 0, i - 1);
    }
  }

  // Swaps a[hole] down with its larger child until in place.
  private static void bubbleDown(int[] a, int hole, int max) {
    int temp = a[hole];
    while (hole * 2 <= max) {
      // pick larger child
      int child = hole * 2;
      if (child != max && a[child + 1] > a[child]) {
        child++;
      }
      if (a[child] > temp) {
        a[hole] = a[child];
      } else {
        break;
      }
      hole = child;
    }
    a[hole] = temp;
  }

  // Sorts the contents of a using bucket sort algorithm.
  // Works for any range of integers in a.
  public static void bucketSort(int[] a) {
    int min = Integer.MAX_VALUE; // find range of values
    int max = Integer.MIN_VALUE; // stored in a
    for (int k : a) {
      max = Math.max(max, k);
      min = Math.min(min, k);
    }
    int[] counters = new int[max - min + 1];
    for (int k : a) {
      counters[k - min]++;
    }
    int i = 0;
    for (int j = 0; j < counters.length; j++) {
      for (int k = 0; k < counters[j]; k++) {
        a[i] = j + min;
        i++;
      }
    }
  }

  // Arranges the elements in the array into ascending order
  // using the "radix sort" algorithm, which sorts into ten
  // queues by ones digit, then tens, ... until sorted.
  @SuppressWarnings("unchecked")
  public static void radixSort(int[] a) {
    // initialize array of 10 queues for digit value 0-9
    Queue<Integer>[] buckets = (Queue<Integer>[]) new Queue[10];
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new ArrayDeque<Integer>();
    }

    // copy to/from buckets repeatedly until sorted
    int digit = 1;
    while (toBuckets(a, digit, buckets)) {
      fromBuckets(a, buckets);
      digit++;
    }
  }

  // Organizes the integers in the array into the given array
  // of queues based on their digit at the given place.
  // For example, if digit == 2, uses the tens digit, so array
  // {343, 219, 841, 295} would be put in queues #4, 1, 4, 9.
  // Returns true if any elements have a non-zero digit value.
  private static boolean toBuckets(int[] a, int digit, Queue<Integer>[] buckets) {
    boolean nonZero = false;
    for (int element : a) {
      int which = kthDigit(element, digit);
      buckets[which].add(element);
      if (which != 0) {
        nonZero = true;
      }
    }
    return nonZero;
  }

  // Returns the k'th least significant digit from the given integer.
  // For example, kthDigit(9814728, 3) returns 7.
  // If the given integer does not have a kth digit, returns 0.
  private static final int kthDigit(int element, int k) {
    for (int i = 1; i <= k - 1; i++) {
      element = element / 10;
    }
    return element % 10;
  }

  // Moves the data in the given array of queues back into the
  // given array, in ascending order by bucket.
  // Postcondition: all queues in the array are empty.
  private static void fromBuckets(int[] a, Queue<Integer>[] buckets) {
    int i = 0;
    for (int j = 0; j < buckets.length; j++) {
      while (!buckets[j].isEmpty()) {
        a[i] = buckets[j].remove();
        i++;
      }
    }
  }

  public static void stoogeSort(int[] a) {
    stoogeSort(a, 0, a.length - 1);
  }

  private static void stoogeSort(int[] a, int min, int max) {
    if (min < max) {
      if (a[min] > a[max]) {
        swap(a, min, max);
      }
      int oneThird = (max - min + 1) / 3;
      if (oneThird >= 1) {
        stoogeSort(a, min, max - oneThird);
        stoogeSort(a, min + oneThird, max);
        stoogeSort(a, min, max - oneThird);
      }
    }
  }

  // Swaps the values at the two given indexes in the given array.
  private static final void swap(int[] a, int i, int j) {
    if (i != j) {
      int temp = a[i];
      a[i] = a[j];
      a[j] = temp;
    }
  }

  // Randomly rearranges the elements of the given array.
  private static void shuffle(int[] a) {
    for (int i = 0; i < a.length; i++) {
      // move element i to a random index in [i .. length-1]
      int randomIndex = (int) (Math.random() * a.length - i);
      swap(a, i, i + randomIndex);
    }
  }

  // Returns true if the given array is in sorted ascending order.
  private static boolean isSorted(int[] a) {
    for (int i = 0; i < a.length - 1; i++) {
      if (a[i] > a[i + 1]) {
        return false;
      }
    }
    return true;
  }



  // Creates an array of the given length, fills it with random
  // non-negative integers, and returns it.
  public static int[] createRandomArray(int length) {
    int[] a = new int[length];
    for (int i = 0; i < a.length; i++) {
      a[i] = RAND.nextInt(1000000);
      // a[i] = RAND.nextInt(40);
    }
    return a;
  }

  // Creates an array of the given length, fills it with ordered
  // non-negative integers, and returns it.
  public static int[] createAscendingArray(int length) {
    int[] a = new int[length];
    for (int i = 0; i < a.length; i++) {
      a[i] = i;
    }
    return a;
  }

  // Creates an array of the given length, fills it with reverse-ordered
  // non-negative integers, and returns it.
  public static int[] createDescendingArray(int length) {
    int[] a = new int[length];
    for (int i = 0; i < a.length; i++) {
      a[i] = a.length - 1 - i;
    }
    return a;
  }
}
