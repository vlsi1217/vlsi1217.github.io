package freq2_tony;

/**
 * Lintcode的DP经典题目Minimum Adjustment Cost: Given an integer array, adjust each integers so that the
 * difference of every adjcent integers are not greater than a given number target. If the array
 * before adjustment is A, the array after adjustment is B, you should minimize the sum of
 * |A[i]-B[i]|
 * 
 * 注意 You can assume each number in the array is a positive integer and not greater than 100
 * 
 * 样例 Given [1,4,2,3] and target=1, one of the solutions is [2,3,2,3], the adjustment cost is 2 and
 * it's minimal. Return 2.
 * 
 * @reference: http://www.cnblogs.com/yuzhangcmu/p/4153927.html
 * @author tzhang
 *
 */

import java.util.*;

public class LintMinAdjustmentCost {
  public static void main(String[] args) {
    int[] A = new int[] {1, 4};
    LintMinAdjustmentCost lmac = new LintMinAdjustmentCost();
    List<Integer> AA = new ArrayList<Integer>();
    for (int i : A)
      AA.add(i);

    System.out.println("input: " + AA);
    int res = lmac.MinAdjustmentCost4(AA, 1);
    System.out.println(res);
  }

  /**
   * Client
   * 
   * @param A
   * @param target
   * @return
   */
  public int MinAdjustmentCost1(List<Integer> A, int target) {
    if (A == null)
      return 0;
    List<Integer> result = new ArrayList<Integer>(A);
    List<List<Integer>> path = new ArrayList<List<Integer>>();
    int res = rec1(A, result, path, target, 0);
    System.out.println("result: " + path);
    return res;
  }

  /**
   * 但是怎么返回最优解? 例如[1,4,2,3]应该2. [2,3,2,3], [2,4,3,3], etc?
   * 
   * @param ori
   * @param res
   * @param path
   * @param target
   * @param index
   * @return
   */
  private static int rec1(List<Integer> ori, List<Integer> res,
      List<List<Integer>> path, int target, int index) {
    int len = ori.size();
    if (index >= len) {
      path.add(new ArrayList<Integer>(res));
      return 0; // the index is out of len, base case return.
    }
    int dif = 0;
    int min = Integer.MAX_VALUE;
    // NP问题的经典backtracking做法.
    for (int i = 1; i <= 4; ++i) {
      if (index != 0 && Math.abs(i - res.get(index - 1)) > target) {
        continue; // 剪枝: 一旦发现res不符合要求就不继续rec了
      }
      res.set(index, i);
      dif = Math.abs(i - ori.get(index));
      dif += rec1(ori, res, path, target, index + 1);
      // min = Math.min(min, dif);
      if (dif <= min) {
        min = dif;
        // path.set(index, res.get(index));
        System.out.println(res);
      }
      // else
      // path.remove(path.size()-1);
      res.set(index, ori.get(index));
    }

    // System.out.println(res);
    return min;
  }


  /*
   * SOLUTION 4： DP
   */
  /**
   * @param A: An integer array.
   * @param target: An integer.
   */
  public int MinAdjustmentCost4(List<Integer> A, int target) {
    // write your code here
    if (A == null || A.size() == 0) {
      return 0;
    }

    // D[i][v]: 把index = i的值修改为v，所需要的最小花费
    int[][] D = new int[A.size()][101];

    int size = A.size();

    for (int i = 0; i < size; i++) {
      for (int j = 1; j <= 100; j++) {
        D[i][j] = Integer.MAX_VALUE;
        if (i == 0) {
          // The first element.
          D[i][j] = Math.abs(j - A.get(i));
        } else {
          for (int k = 1; k <= 100; k++) {
            // 不符合条件
            if (Math.abs(j - k) > target) {
              continue;
            }

            int dif = Math.abs(j - A.get(i)) + D[i - 1][k];
            D[i][j] = Math.min(D[i][j], dif);
          }
        }
      }
    }

    int ret = Integer.MAX_VALUE;
    for (int i = 1; i <= 100; i++) {
      ret = Math.min(ret, D[size - 1][i]);
    }

    return ret;
  }
}
