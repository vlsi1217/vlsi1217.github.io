package freq2_tony;
import java.util.*;

import testingAns.ThreeSumGanker;

/**
 * 在4sum之前复习一下3sum, 觉得Ganker的代码清晰一些,比较好. 先复习一下2sum.
 * @author tzhang
 *
 */
public class ThreeSum {
  public List<List<Integer>> threeSumGanker(int[] num) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (num == null || num.length < 3)
      return res;
    Arrays.sort(num);
    for (int i = num.length-1; i >= 2; --i) {
      if (i < num.length-1 && num[i] == num[i+1])
        continue;  // 和twoSum里面的while一样用于去重复.
      List<List<Integer>> curRes = twoSumGanker(num, i-1, -num[i]);
      for (List<Integer> cur2res : curRes) {
        cur2res.add(num[i]);
      }
      res.addAll(curRes);
    }
    return res;
  }
  /**
   * Code Ganker在3sum里面使用了2sum来做. 因为这个2sum可以扩展到4sum及以上. 而且这个可以返回所有解. 
   * @param num : 即input 数组, 作为param传入. 远比global var灵活. 而且这里的num已经sort过了.
   * @param end : 即num的右边界来控制2sum计算范围. 因为target每次向此向前移动, 后面的已经考虑过了. 
   * @param target : 即一般意义上的target.
   * @return
   */
  private List<List<Integer>> twoSumGanker(int[] num, int end, int target) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (num == null || num.length < 2)
      return res;
    int l = 0;
    int r = end;
    while (l < r) {
      if (num[l] + num[r] == target) {
        List<Integer> path = new ArrayList<Integer>();
        path.add(num[l]);
        path.add(num[r]);
        res.add(path); // 因为每次进来都是一个新path, 所以不用担心obj给后面的改动了.
        l++; // 先移动左右pointer, 这样下面2个while loop可以用来避开重复结果.
        r--;
        while (l<r && num[l] == num[l-1])
          l++; // 找到符合条件的之后就要避开重复的结果!
        while (l < r && num[r] == num[r+1]) 
          r--;
      }
      if (num[l] + num[r] > target) {
        r--;
      }
      else 
        l++;
    }
    return res;
  }
  
  /**
   * 但是Ganker的4sum复杂了点. 觉得N00t的比较好用.
   */
  
  /**
   * N00t的3sum其实是比Ganker更简洁, 不过Ganker分成2部分, 所以容易懂些. 理解了Ganker再来看Noot就不是事了.
   * @param num
   * @return
   */
  private List<List<Integer>> threeSumN00t(int[] num) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (num == null || num.length < 3) 
      return res;
    Arrays.sort(num);
    for (int i = num.length-1; i >= 2 && num[i] >= 0; --i) {
      if (i < num.length-1 && num[i] == num[i+1])
        continue;  // 保证了这一组不是重复的.
      int l = 0, r = i-1;
      while (l < r) {
        int sum = num[i] + num[l] + num[r];
        if (sum < 0) 
          l++;
        else if (sum > 0) 
          r--;
        else { // 找到一组. 
          List<Integer> cur = new ArrayList<Integer>();
          cur.add(num[l]);
          cur.add(num[r]);
          cur.add(num[i]); // 这里的顺序重要.
          res.add(cur);
          do {
            l++;
          } while (l < r && num[l] == num[l-1]); // 避开后面的重复的.
          do {
            r--;
          } while (l < r && num[r] == num[r+1]);
        }
      }
    }
    return res;
  }
  
  public ThreeSum() {
    int[] input = {-8, -5, 0, 3, 0, 1, 4, 4, 4};
    List<List<Integer>> res = threeSumN00t(input);
    // ArrayList<ArrayList<Integer>> res = twoSum(input, input.length-1, 4);
    System.out.println(res);
  }

  public static void main(String[] args) {
    ThreeSum ts = new ThreeSum();
  }
}
