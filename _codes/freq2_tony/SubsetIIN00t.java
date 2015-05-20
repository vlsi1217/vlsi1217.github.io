package freq2_tony;

import java.util.*;

/**
 * ����N00t��˼��. ���Ǻܺ�����. ֻ����ô�뵽�Ǹ�����[]�ռ�����? �Ǹ���recursion��base case. ��Ȼ����ѭ����.
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
    // recursion��base case. ��cur ����num��Χ, �ͼ���[].
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
