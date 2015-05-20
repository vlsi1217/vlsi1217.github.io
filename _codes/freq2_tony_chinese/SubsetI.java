package freq2_tony;

import java.util.*;

/**
 * NP问题的常用解法: backtracking. 当然分递归和非递归2种做法. 这里是简单的题: subset. 即假设input是不重复的.
 * 而且Ganker并不是8queen那种常见的backtracking模版. 还是要仔细品品他的思路. 其实本质是一样的. 考虑当前的rec返回, 然后看到新的书就加到所有子解中,
 * 再放到res里面. 还有N00t的解法, 也是分为递归和非递归.
 * 
 * @author tzhang
 *
 */
public class SubsetI {
  public static void main(String[] args) {
    int[] num = new int[] {1, 2};
    SubsetI ssi = new SubsetI();
    List<List<Integer>> res = ssi.subsetsI(num);
    for (List<Integer> r : res) {
      System.out.println(r);
    }
  }

  public List<List<Integer>> subsetsI(int[] num) {
    if (num == null)
      return null;
    Arrays.sort(num);
    return helperGankerI(num, num.length - 1);
  }

  /**
   * Code ganker的递归解法. 对于初学时的我, 先理解好recur之前的code是walk down Tree, 之后的code是walk up tree. 现在已经理解,
   * 最主要是怎么运用/设计递归.
   * 
   * @param num
   * @param index
   * @return
   */
  private List<List<Integer>> helperGankerI(int[] num, int index) {
    if (index == -1) {
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      List<Integer> elem = new ArrayList<Integer>();
      res.add(elem);
      return res;
    }
    List<List<Integer>> res = helperGankerI(num, index - 1);
    int size = res.size();
    for (int i = 0; i < size; ++i) {
      List<Integer> ele = new ArrayList<Integer>(res.get(i));
      ele.add(num[index]);
      res.add(ele);
    }
    return res;
  }
}
