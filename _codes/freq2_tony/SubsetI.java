package freq2_tony;

import java.util.*;

/**
 * NP����ĳ��ýⷨ: backtracking. ��Ȼ�ֵݹ�ͷǵݹ�2������. �����Ǽ򵥵���: subset. ������input�ǲ��ظ���.
 * ����Ganker������8queen���ֳ�����backtrackingģ��. ����Ҫ��ϸƷƷ����˼·. ��ʵ������һ����. ���ǵ�ǰ��rec����, Ȼ�󿴵��µ���ͼӵ������ӽ���,
 * �ٷŵ�res����. ����N00t�Ľⷨ, Ҳ�Ƿ�Ϊ�ݹ�ͷǵݹ�.
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
   * Code ganker�ĵݹ�ⷨ. ���ڳ�ѧʱ����, ������recur֮ǰ��code��walk down Tree, ֮���code��walk up tree. �����Ѿ����,
   * ����Ҫ����ô����/��Ƶݹ�.
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
