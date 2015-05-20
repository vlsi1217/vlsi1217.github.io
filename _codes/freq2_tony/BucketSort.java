package freq2_tony;

/**
 * �ο���UW��sorting�ϼ��е�Bucket sort. ���wiki�ܺö�. ��Ҫ������˼���Լ�2��loop��д����i++�ķ�ʽ.
 * https://courses.cs.washington.edu/courses/cse373/13wi/lectures/03-01/Sorting.java
 * 
 * �ص�: �������sorting��ʵ�����˺ܶ��������˼��. recursion, DP, D&C, �Լ���̻���.
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
    // step 1: �ҵ���Χ, ����������counter[]
    for (int e : num) {
      min = Math.min(e, min);
      max = Math.max(e, max);
    }
    System.out.println(min + " " + max);
    int[] counter = new int[max - min + 1];
    // step 2: ��������¼ÿ��bucket collision�Ĵ���. �����Ǽ򵥵�rangeΪ1��bucket.
    for (int e : num) {
      counter[e - min]++;
    }
    // step 3: one-passage ������inplace�Żص�ԭ����num[]. ע����������: i,j,k��Ҫ�����. 
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
