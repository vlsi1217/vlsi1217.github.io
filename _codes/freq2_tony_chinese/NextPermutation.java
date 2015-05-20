package freq2_tony;

/**
 * 这题主要是理解什么是next permutation. 还是要找到规律. 这里参考"水中的鱼"的图示.
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
   * 第一次写, 不够简洁. 还有reverse可以单独拿出来.
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
   * Ganker的写法更好.
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
      int j = num.length - 1; // 这里我还是用的水中的鱼的写法. Ganker则是另一个角度:
                              // 即changeNum是从partitionNum开始向后找第一个比他小的, 然后再自减.
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
    // 不用check了, 因为只到是可以用的
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
