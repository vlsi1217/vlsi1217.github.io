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
   * NP���ⶼ���õ�Backtracking. ����������4��param
   * 
   * @param num : ����
   * @param used : ����������������������. ����Combination���õ�i+1
   * @param item : ÿһ��������Ľ�
   * @param res : ����������Ľ�ļ���
   */
  private void dfs(int[] num, boolean[] used, ArrayList<Integer> item,
      ArrayList<ArrayList<Integer>> res) {
    if (item.size() == num.length) {
      res.add(new ArrayList<Integer>(item)); // �����Ѿ�����Ϥ, ��Ϊpass-by-value, ���Ա���cloneһ������ֵ.
      return;
    }
    for (int i = 0; i < num.length; i++) {
      if (i > 0 && !used[i - 1] && num[i] == num[i - 1]) // �ؼ���: ��Ϊclient�����input���ź����,
                                                         // ���Ծ��ж�ǰһ����ͬ���Ƿ��ù�.
        continue;
//      if (item.size() > 0 && item.get(item.size() - 1) == num[i]) // follow up: ������������ڵ�num��ȵĻ�.
//        continue;  //http://blog.csdn.net/linhuanmars/article/details/21569031 �ش����������һ������
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
