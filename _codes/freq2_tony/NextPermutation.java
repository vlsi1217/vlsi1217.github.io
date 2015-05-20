package freq2_tony;

/**
 * ������Ҫ�����ʲô��next permutation. ����Ҫ�ҵ�����. ����ο�"ˮ�е���"��ͼʾ.
 * 
 * @author tzhang
 *
 */
public class NextPermutation {

  public static void main(String[] args) {
    int[] A = {1, 12, 6, 8, 7, 4, 3, 2};
    System.out.println("heelo");
    nextPermutation(A);
    for (int i : A)
      System.out.print(i + " ");
  }

  /**
   * ��һ��д, �������. ����reverse���Ե����ó���.
   * 
   * @param num
   */
  public static void nextPerm(int[] num) {
    if (num == null || num.length == 0)
      return;
    int partitionIdx = num.length - 2;
    int partitionNum = 0, changeNum = 0;
    while (partitionIdx >= 0 && num[partitionIdx] > num[partitionIdx + 1])
      partitionIdx--;
    partitionNum = num[partitionIdx];
    int changeIdx;
    for (changeIdx = num.length - 1; changeIdx > partitionIdx; changeIdx--) {
      if (num[changeIdx] > partitionNum) {
        changeNum = num[changeIdx];
        break;
      }
    }
    System.out.println(partitionNum + " " + partitionIdx);
    // swap
    // int tmp = changeNum;
    // changeNum = partitionNum;
    // partitionNum = tmp;
    int tmp = num[changeIdx];
    num[changeIdx] = num[partitionIdx];
    num[partitionIdx] = tmp;
    // reverse
    int i = partitionIdx + 1, j = num.length - 1;
    while (i < j) {
      tmp = num[i];
      num[i] = num[j];
      num[j] = tmp;
      i++;
      j--;
    }
  }

  /**
   * Ganker��д������.
   * 
   * @param num
   */
  public static void nextPermutation(int[] num) {
    if (num == null || num.length == 0)
      return;
    int i = num.length - 2;
    while (i >= 0 && num[i] > num[i + 1]) {
      i--;
    }
    if (i >= 0) {
      int j = num.length - 1; // �����һ����õ�ˮ�е����д��. Ganker������һ���Ƕ�:
                              // ��changeNum�Ǵ�partitionNum��ʼ����ҵ�һ������С��, Ȼ�����Լ�.
      while (j > i && num[j] < num[i])
        j--;
      int tmp = num[j];
      num[j] = num[i];
      num[i] = tmp;
    }
    reverse(num, i + 1);
  }

  /**
   * helper method: reverse the substring: A.substring(idx).
   * 
   * @param A
   * @param idx
   */
  private static void reverse(int[] A, int idx) {
    // ����check��, ��Ϊֻ���ǿ����õ�
    int i = idx;
    int j = A.length - 1;
    while (i < j) {
      int tmp = A[i];
      A[i] = A[j];
      A[j] = tmp;
      i++;
      j--;
    }
  }
}
