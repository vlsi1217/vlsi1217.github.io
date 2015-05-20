package freq2_tony;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same
 * color are adjacent, with the colors in the order red, white and blue. Here, we will use the
 * integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * 
 * Note:
 * 
 * @author tzhang
 *
 */
public class SortColor {
  /**
   * 
   * @param A
   */
  private static void sortColors(int[] A) {
    int len = A.length;
    int[] B = new int[len]; // 最终结果
    int[] C = new int[3]; // 记录比a小的有几个的helper array. 因为0,1,2, 所以只用3格.

    for (int i = 0; i < len; ++i) {
      C[A[i]]++;
    }
    for (int i = 1; i < 3; ++i) {
      C[i] += C[i - 1];
    }
    for (int i = len - 1; i >= 0; i--) {
      int a = A[i];
      B[C[a] - 1] = a;
      C[a]--;
    }
    for (int i : B) {
      System.out.print(i);
    }
  }
  
  /**
   * 同理, 因为只有1,2. 所以只用1个指针: p1指向1的最后一个下标. 同时loop i即可.
   * @param A
   */
  public static void sortOneTwo(int[] A) {
    if(A==null || A.length ==0)  return;
    int p1 = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] == 1) {
        A[i] = 2;
        A[p1++] = 1;
      }
    }
    for (int i : A) {
      System.out.print(i);
    }
  }
  
  /**
   * 聪明的做法: 因为只有0,1,2. 所以可以只用2个指针: p0, p1,分别指向0的最后一个下标, 1的最后一个下标
   * @param A
   */
  public static void sortColorsGanker(int[] A) {
    if(A==null || A.length==0)
        return;
    int idx0 = 0;
    int idx1 = 0;
    for(int i=0;i<A.length;i++)
    {
        if(A[i]==0)
        {
            A[i] = 2;
            A[idx1++] = 1;
            A[idx0++] = 0;
        }
        else if(A[i]==1)
        {
            A[i] = 2;
            A[idx1++] = 1;
        }
    }
    for (int i : A) {
      System.out.print(i);
    }
}


  /**
   * 先复习一下简单的counting sort, 用的wiki的java
   */
  private static void countSort() {
    int[] A = {1, 9, 8, 3, 8, 1};
    int k = 10;
    int[] C = new int[10];
    int[] B = new int[A.length];
    for (int i = 0; i < A.length; ++i) {
      int a = A[i];
      C[a]++;
    }
    for (int x : C)
      System.out.print(x);
    System.out.println();
    for (int i = 1; i < k; i++) {
      C[i] = C[i] + C[i - 1];
    }
    for (int x : C)
      System.out.print(x);

    for (int j = A.length - 1; j >= 0; j--) {
      int a = A[j];
      B[C[a] - 1] = a;
      C[a]--;
    }

    System.out.println();
    for (int x : B)
      System.out.print(x);
  }

  public static void main(String[] args) {
    // countSort();
    int[] A = {1,1,0,1};  //{2, 1, 1, 2, 0, 2, 1};
    sortColorsGanker(A);
//    int[] A = {2,1,1, 2, 1};
//    sortOneTwo(A);
  }
}
