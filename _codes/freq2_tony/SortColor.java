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
    int[] B = new int[len]; // ���ս��
    int[] C = new int[3]; // ��¼��aС���м�����helper array. ��Ϊ0,1,2, ����ֻ��3��.

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
   * ͬ��, ��Ϊֻ��1,2. ����ֻ��1��ָ��: p1ָ��1�����һ���±�. ͬʱloop i����.
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
   * ����������: ��Ϊֻ��0,1,2. ���Կ���ֻ��2��ָ��: p0, p1,�ֱ�ָ��0�����һ���±�, 1�����һ���±�
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
   * �ȸ�ϰһ�¼򵥵�counting sort, �õ�wiki��java
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
