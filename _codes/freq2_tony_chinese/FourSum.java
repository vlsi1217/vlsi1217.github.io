package freq2_tony;

import java.util.*;

public class FourSum {
  /**
   * 
   * @param num
   * @param target
   * @return
   */
  public List<List<Integer>> fourSumN00t(int[] num, int target) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if  (num == null || num.length < 4) 
      return res;
    Arrays.sort(num);
    for (int i = 0 ; i < num.length-3 && num[i] <= target; ++i) {
      if (i > 0 && num[i] == num[i-1])
        continue;
      for (int j = i+1; j < num.length-2; ++j) {
        if (j > i+1 && num[j] == num[j-1]) 
          continue;
        int l = j+1, r= num.length-1;
        while (l < r)  {
          int sum = num[i] + num[j] + num[l] + num[r];
          if (sum < target) {
            l++;
          }
          else if (sum > target) {
            r--;
          }
          else {
            List<Integer> cur = new ArrayList<Integer>();
            cur.add(num[i]);
            cur.add(num[j]);
            cur.add(num[l]);
            cur.add(num[r]);
            res.add(cur);
            do {
              l++;
            } while (l < r && num[l] == num[l-1]);
            do {
              r--;
            } while (l < r && num[r] == num[r+1]);
          }
        }
      }
    }
    return res;
  }
  
  public FourSum() {
    int[] input = {-8, -5, 0, 3, 0, 1, 4, 4, 4};
    List<List<Integer>> res = fourSumN00t(input, 4);
    // ArrayList<ArrayList<Integer>> res = twoSum(input, input.length-1, 4);
    System.out.println(res);
  }

  public static void main(String[] args) {
    FourSum fs = new FourSum();
  }
}
