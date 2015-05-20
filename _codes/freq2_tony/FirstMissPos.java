package freq2_tony;

/**
 * ��ʵ����Bucket sort��˼��. ���ַŵ�index��Ӧ��λ��. �Ӷ�ʵ������. Ȼ���ٹ�һ��, ������Ŀ������������: �����������ҵ�һ��missing positive.
 * ������������Ganker��ˮ�е���Ľ���. ��ʵ����ⷨ��û�������ȫ����, ��ֻ�ǽ���Χ�ڵ����ְ���index˳���. ���Կɼ�����/������û������.
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
   * ��Counting sortһ�������������index����Ϊ���ֱ�������������������յ���˳�����ηŵ������С�
   * 
   * @param A
   * @return
   */
  public int firstMissingPositive(int[] A) {
    if (A == null || A.length == 0) {
      return 1;
    }
    for (int i = 0; i < A.length; i++) {
      if (A[i] <= A.length && A[i] > 0 && A[A[i] - 1] != A[i]) { // ��Ϊ�ռ�O(1). ���Ա��뽻��elem in-place
        int temp = A[A[i] - 1];
        A[A[i] - 1] = A[i];
        A[i] = temp;
        i--; // ����������Ganker��ϸ��: ���Ƚ���A[i]�󲢲��ܱ�֤�ͽ�����, ��Ҫ�����ж�! ����i--.
      }
    }
    for (int i = 0; i < A.length; i++) {
      if (A[i] != i + 1)
        return i + 1;
    }
    return A.length + 1;
  }
}
