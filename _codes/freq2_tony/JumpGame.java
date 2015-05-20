package freq2_tony;

import java.util.*;

public class JumpGame {

  // -------------------------------------------> N00t JumpGameI
  /**
   * Ganker��DP�ⷨ...
   * 
   * @param A
   * @return
   */
  private static boolean canJump(int[] A) {
    if (A == null || A.length == 0)
      return false;
    int reach = 0;
    for (int i = 0; i <= reach && i < A.length; i++) {
      reach = Math.max(A[i] + i, reach);
    }
    if (reach < A.length - 1)
      return false;
    return true;
  }

  // -------------------------------------------> N00t JumpGameII
  /**
   * Jump Game II�����������Ե���, ��Сjump����. ������Сstep����.
   * 
   * @param arr
   * @return
   */
  private static int jumpN00t(int[] arr) {
    if (arr.length <= 1)
      return 0;
    int[] steps = new int[arr.length - 1];
    for (int i = arr.length - 2; i >= 0; --i) {
      if (arr[i] >= (arr.length - 1 - i)) {
        steps[i] = 1; // ��˵������һ��jump���ɵ���.
      } else {
        int min = arr.length;
        for (int j = 1; j <= arr[i]; ++j) { // ��i����ʹ�õ�̤������Щ
          min = Math.min(min, steps[i + j]); // �ҵ�����̤��
        }
        steps[i] = min + 1; // �Լ�����̤��Ҳ��1��jump
      }
    }
    return steps[0];
  }

  /**
   * �������ķ���: Greedy Algorithm. ע�������next��max������. ��N00t�ķ���. ע��Greedy��ʵ��������, ���õ�ʱ��Ҫ��֤��Ϊʲô������Greedy.
   * 
   * @param A
   * @return
   */
  public static int jumpN00tGreedy(int[] A) {
    int steps = 0, max = 0, next = 0;  // �ֱ��Ǹ�ʲô��?
    for (int i = 0; i < A.length - 1 && next < A.length - 1; ++i) {
      max = Math.max(max, i + A[i]);
      if (i == next) { // ready to jump
        if (max == next)
          return -1; // unreachable
        next = max;
        ++steps;
      }
    }
    return steps;
  }

  /**
   * Ganker��DP�ⷨ.
   * 
   * @param A
   * @return
   */
  public static int jump(int[] A) {
    if (A == null || A.length == 0)
      return 0;
    int lastReach = 0;
    int reach = 0;
    int step = 0;
    for (int i = 0; i <= reach && i < A.length; i++) {
      if (i > lastReach) {
        step++;
        lastReach = reach;
      }
      reach = Math.max(reach, A[i] + i);
    }
    if (reach < A.length - 1)
      return 0;
    return step;
  }

  /**
   * ����Ganker��һ����������ķ���, ������¼path.
   * 
   * @param A
   * @return
   */
  public static int jumpGankerComment(int[] A) {
    int last = 0, reach = 0, best = 0;
    List<Integer> steps = new ArrayList<Integer>();

    for (int i = 0; i < A.length; i++) {
      if (i > last) {
        last = reach;
        steps.add(best);
      }
      if (i + A[i] > reach) {
        reach = i + A[i];
        best = i;
      }
    }

    System.out.println("best jump point" + steps);
    return steps.size();
  }


  public static void main(String[] args) {
    int[] arr = {2, 3, 1, 0, 4}; // {2, 3, 1, 1, 4}; //{3, 2, 1, 0, 4}; //
    // System.out.println(canJump(arr));
    System.out.println(jumpN00tGreedy(arr));
  }
}
