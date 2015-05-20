package freq2_tony;

import java.util.*;

/**
 * Ganker��recursion�ⷨ����Subset II, �����õ�Subset I��ģ��. ����Ҫ�����жϺ�ʱ����dup. ����N00t�Ľ��ͺ���ϸ. �ٵ�������: {1,2,2}.
 * ��res = [[], [1], [2], [1,2], [1,2,2], [2,2,2]]. Ȼ��ڶ���2ֻ����[2], [1,2]��.
 * ����Ҫϸ�µ�ʹ��res.get(i)��num[index], �Լ�update lastsize[].
 * 
 * ����subset II��Permutation IIһ��, ��general�Ľⷨ. ����Ҳ���Խ��I������. ������Ϥ�Ļ�, ���ֱ������д.
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
    return helperGankerII(num, num.length - 1, new int[1]); // ��ΪarrayҲ��һ��object, ��pass by value.
                                                        // ��array��handle
  }

  /**
   * Ganker ��recursion�ⷨ. ��Ҫ��������, Ȼ��recursion��ֻȡ���ظ�����. ��ʵ˼���N00t�Ľ���һ��. ���ǿ���dupҪ�����ĸ�subsets����.
   * ����ͨ��ǰһ�ε�res.size()�ж�.
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
//      item.addAll(res.get(i)); // ΪʲôN00tҪ����, ��Ganker������?
      res.add(item);
    }
    lastsize[0] = size;
    return res;
  }
}
