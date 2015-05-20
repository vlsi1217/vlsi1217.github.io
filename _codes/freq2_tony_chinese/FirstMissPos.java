package freq2_tony;

/**
 * 其实就是Bucket sort的思想. 数字放到index对应的位置. 从而实现排序. 然后再过一遍, 利用题目本身暗含的性质: 正数连续来找第一个missing positive.
 * 不过这里结合了Ganker和水中的鱼的解释. 其实这个解法并没有真的完全排序, 而只是将范围内的数字按照index顺序放. 所以可见负数/大数并没有排序.
 * 
 * @author tzhang
 *
 */
public class FirstMissPos {

  public static void main(String[] args) {
    int[] num = new int[] {3, 1, 2};
    FirstMissPos fmp = new FirstMissPos();
    System.out.println("Ans: " + fmp.firstMissingPositive(num));
    for (int i : num)
      System.out.print(i + " ");
  }

  /**
   * 跟Counting sort一样，利用数组的index来作为数字本身的索引，把正数按照递增顺序依次放到数组中。
   * 
   * @param A
   * @return
   */
  public int firstMissingPositive(int[] A) {
    if (A == null || A.length == 0) {
      return 1;
    }
    for (int i = 0; i < A.length; i++) {
      if (A[i] <= A.length && A[i] > 0 && A[A[i] - 1] != A[i]) { // 因为空间O(1). 所以必须交换elem in-place
        int temp = A[A[i] - 1];
        A[A[i] - 1] = A[i];
        A[i] = temp;
        i--; // 这里体现了Ganker的细致: 被迫交换A[i]后并不能保证就结束了, 还要重新判断! 所以i--.
      }
    }
    for (int i = 0; i < A.length; i++) {
      if (A[i] != i + 1)
        return i + 1;
    }
    return A.length + 1;
  }
}
