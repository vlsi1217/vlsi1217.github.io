package freq2_tony;

import java.util.*;

public class JumpGame {

  // -------------------------------------------> N00t JumpGameI
  /**
   * Ganker的DP解法...
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
   * Jump Game II求的是如果可以到达, 最小jump次数. 不是最小step次数.
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
        steps[i] = 1; // 即说明可以一次jump即可到达.
      } else {
        int min = arr.length;
        for (int j = 1; j <= arr[i]; ++j) { // 即i可以使用的踏板有哪些
          min = Math.min(min, steps[i + j]); // 找到最优踏板
        }
        steps[i] = min + 1; // 自己跳到踏板也有1个jump
      }
    }
    return steps[0];
  }

  /**
   * 最厉害的方法: Greedy Algorithm. 注意这里的next和max的意义. 见N00t的分析. 注意Greedy其实很少能用, 能用的时候要先证明为什么可以用Greedy.
   * 
   * @param A
   * @return
   */
  public static int jumpN00tGreedy(int[] A) {
    int steps = 0, max = 0, next = 0;  // 分别是干什么的?
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
   * Ganker的DP解法.
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
   * 这是Ganker的一个评论里面的方法, 用来记录path.
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
