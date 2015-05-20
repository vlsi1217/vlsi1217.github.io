package freq2_tony;

/**
 * 参考的UW的sorting合集中的Bucket sort. 结合wiki很好懂. 主要是他的思想以及2个loop的写法和i++的方式.
 * https://courses.cs.washington.edu/courses/cse373/13wi/lectures/03-01/Sorting.java
 * 
 * 重点: 最基础的sorting其实包含了很多解决问题的思想. recursion, DP, D&C, 以及编程基础.
 * 
 * @author tzhang
 *
 */
public class BucketSort {
  public static void main(String[] args) {
    int[] a = new int[] {-1, 4, 1, 3};
    BucketSort bs = new BucketSort();
    bs.bucket(a);
//    for (int i : a) {
//      System.out.print(i+" ");
//    }
  }

  public static void bucket(int[] num) {
    if (num == null || num.length == 0) {
      return;
    }
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    // step 1: 找到范围, 来尽量减少counter[]
    for (int e : num) {
      min = Math.min(e, min);
      max = Math.max(e, max);
    }
    System.out.println(min + " " + max);
    int[] counter = new int[max - min + 1];
    // step 2: 计数器记录每个bucket collision的次数. 这里是简单的range为1的bucket.
    for (int e : num) {
      counter[e - min]++;
    }
    // step 3: one-passage 计数器inplace放回到原来的num[]. 注意这里的设计: i,j,k不要搞混了. 
    int k = 0;
    for (int i = 0; i < counter.length; ++i) {
      for (int j = 0; j < counter[i]; ++j) {
        num[k] = i + min;
        k++;
      }
    }
    for (int e : num) {
      System.out.print(e+" ");
    }
  }
}
