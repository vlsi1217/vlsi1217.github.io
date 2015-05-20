package freq2_tony;

import java.util.*;

public class PermutationII {

  public static void main(String[] args) {
    PermutationII p2 = new PermutationII();
    int[] input = new int[] {1, 2, 1, 2};
    ArrayList<ArrayList<Integer>> res = p2.permute(input);
    for (List<Integer> r : res)
      System.out.println(r);
  }

  public ArrayList<ArrayList<Integer>> permute(int[] num) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if (num == null || num.length == 0)
      return res;
    Arrays.sort(num);
    dfs(num, new boolean[num.length], new ArrayList<Integer>(), res);
    return res;
  }

  /**
   * NP问题都是用的Backtracking. 都是有如下4个param
   * 
   * @param num : 输入
   * @param used : 用来控制子问题求解的限制. 例如Combination是用的i+1
   * @param item : 每一个子问题的解
   * @param res : 所有子问题的解的集合
   */
  private void dfs(int[] num, boolean[] used, ArrayList<Integer> item,
      ArrayList<ArrayList<Integer>> res) {
    if (item.size() == num.length) {
      res.add(new ArrayList<Integer>(item)); // 现在已经很熟悉, 因为pass-by-value, 所以必须clone一个来赋值.
      return;
    }
    for (int i = 0; i < num.length; i++) {
      if (i > 0 && !used[i - 1] && num[i] == num[i - 1]) // 关键处: 因为client里面的input是排好序的,
                                                         // 所以就判断前一个相同的是否用过.
        continue;
//      if (item.size() > 0 && item.get(item.size() - 1) == num[i]) // follow up: 如果不允许相邻的num相等的话.
//        continue;  //http://blog.csdn.net/linhuanmars/article/details/21569031 回答评论里面的一个问题
      if (!used[i]) {
        used[i] = true;
        // if (item.size() == 0 || item.size() > 0 && item.get(item.size() - 1) != num[i]) { //
        item.add(num[i]);
        dfs(num, used, item, res);
        item.remove(item.size() - 1);
        used[i] = false;
        // }
      }
    }
  }
}
