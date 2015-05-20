package freq2_tony;

import java.util.*;

/**
 * 按照N00t的思想. 还是很好理解的. 只是怎么想到那个加入[]空集的呢? 那个是recursion的base case. 不然就死循环了.
 * 
 * @author tzhang
 *
 */
public class SubsetIIN00t {
  public static void main(String[] args) {
    SubsetIIN00t ssii = new SubsetIIN00t();
    System.out.println("testing N00t subset I");
    int[] num = new int[] {1, 2, 3};
    List<List<Integer>> res = ssii.subsetI(num);
    for (List<Integer> r : res) {
      System.out.println(r);
    }
  }

  public List<List<Integer>> subsetI(int[] num) {
    Arrays.sort(num);
    return helperN00t(num, 0, new ArrayList<List<Integer>>());
  }

  private List<List<Integer>> helperN00t(int[] num, int cur,
      List<List<Integer>> res) {
    // recursion的base case. 即cur 超过num范围, 就加入[].
    if (cur >= num.length) {//(cur < 0) {
      res.add(new ArrayList<Integer>());
      return res;
    }
    // step 1: populate subsets made of elements after the cur one.
    res = helperN00t(num, cur + 1, res);
    // step 2: append element to each populated subsets
    int cursize = res.size();
    for (int i = 0; i < cursize; ++i) {
      List<Integer> elem = new ArrayList<Integer>();
      elem.add(num[cur]);
      elem.addAll(res.get(i));
      res.add(elem);
    }
    return res;
  }
}
