package freq2_tony;
import java.util.*;

import testingAns.ThreeSumGanker;

/**
 * ��4sum֮ǰ��ϰһ��3sum, ����Ganker�Ĵ�������һЩ,�ȽϺ�. �ȸ�ϰһ��2sum.
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
        continue;  // ��twoSum�����whileһ������ȥ�ظ�.
      List<List<Integer>> curRes = twoSumGanker(num, i-1, -num[i]);
      for (List<Integer> cur2res : curRes) {
        cur2res.add(num[i]);
      }
      res.addAll(curRes);
    }
    return res;
  }
  /**
   * Code Ganker��3sum����ʹ����2sum����. ��Ϊ���2sum������չ��4sum������. ����������Է������н�. 
   * @param num : ��input ����, ��Ϊparam����. Զ��global var���. ���������num�Ѿ�sort����.
   * @param end : ��num���ұ߽�������2sum���㷶Χ. ��Ϊtargetÿ�������ǰ�ƶ�, ������Ѿ����ǹ���. 
   * @param target : ��һ�������ϵ�target.
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
        res.add(path); // ��Ϊÿ�ν�������һ����path, ���Բ��õ���obj������ĸĶ���.
        l++; // ���ƶ�����pointer, ��������2��while loop���������ܿ��ظ����.
        r--;
        while (l<r && num[l] == num[l-1])
          l++; // �ҵ�����������֮���Ҫ�ܿ��ظ��Ľ��!
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
   * ����Ganker��4sum�����˵�. ����N00t�ıȽϺ���.
   */
  
  /**
   * N00t��3sum��ʵ�Ǳ�Ganker�����, ����Ganker�ֳ�2����, �������׶�Щ. �����Ganker������Noot�Ͳ�������.
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
        continue;  // ��֤����һ�鲻���ظ���.
      int l = 0, r = i-1;
      while (l < r) {
        int sum = num[i] + num[l] + num[r];
        if (sum < 0) 
          l++;
        else if (sum > 0) 
          r--;
        else { // �ҵ�һ��. 
          List<Integer> cur = new ArrayList<Integer>();
          cur.add(num[l]);
          cur.add(num[r]);
          cur.add(num[i]); // �����˳����Ҫ.
          res.add(cur);
          do {
            l++;
          } while (l < r && num[l] == num[l-1]); // �ܿ�������ظ���.
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
