package freq2_tony;

import java.util.*;

/**
 * Ganker的recursion解法来做Subset II, 还是用的Subset I的模版. 但是要加入判断何时加入dup. 这里N00t的解释很详细. 举的例子是: {1,2,2}.
 * 那res = [[], [1], [2], [1,2], [1,2,2], [2,2,2]]. 然后第二个2只加在[2], [1,2]中.
 * 所以要细致的使用res.get(i)和num[index], 以及update lastsize[].
 * 
 * 而且subset II和Permutation II一样, 是general的解法. 所以也可以解决I的问题. 所以熟悉的话, 最好直接这样写.
 * 
 * @author tzhang
 *
 */
public class SubsetGankerII {
  public static void main(String[] args) {
    SubsetGankerII ssii = new SubsetGankerII();
    int[] num = new int[] {1, 2, 3};
    System.out.println("testing Ganker subset I");
    List<List<Integer>> res = ssii.subsetII(num);
    for (List<Integer> r : res) {
      System.out.println(r);
    }
  }

  public List<List<Integer>> subsetII(int[] num) {
    if (num == null)
      return null;
    Arrays.sort(num);
    return helperGankerII(num, num.length - 1, new int[1]); // 因为array也是一个object, 是pass by value.
                                                        // 即array的handle
  }

  /**
   * Ganker 的recursion解法. 主要是先排序, 然后recursion中只取不重复的数. 其实思想和N00t的解释一样. 都是看好dup要加在哪个subsets里面.
   * 这里通过前一次的res.size()判断.
   * 
   * @param num
   * @param index
   * @param lastsize :
   * @return
   */
  private List<List<Integer>> helperGankerII(int[] num, int index, int[] lastsize) {
    if (index == -1) {
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      List<Integer> item = new ArrayList<Integer>();
      res.add(item);
      return res;
    }
    List<List<Integer>> res = helperGankerII(num, index - 1, lastsize);
    int size = res.size();
    int start = 0;
    if (index > 0 && num[index] == num[index - 1]) {
      start = lastsize[0];
    }
    for (int i = start; i < size; ++i) {
      List<Integer> item = new ArrayList<Integer>(res.get(i));
      item.add(num[index]);
//      item.addAll(res.get(i)); // 为什么N00t要这样, 而Ganker不用呢?
      res.add(item);
    }
    lastsize[0] = size;
    return res;
  }
}
