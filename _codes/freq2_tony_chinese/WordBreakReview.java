package freq2_tony;

import java.util.*;

/**
 * Word break I: 找到一个string是否可以segment为Dict中的string.
 * 
 * @author tzhang
 *
 */
public class WordBreakReview {
  // client to test
  public static void main(String[] args) {
    String s = "codeleet";
    List<String> dict = new ArrayList<>();
    String[] dictstring = new String[] {"leet", "code", "co"};
    for (String ds : dictstring) {
      dict.add(ds);
    }

    System.out.println("GOT: " + wordBreakIGank(s, dict));
  }

  /**
   * 参考的Ganker的DP解法. 关键在于递推式(recurrence)的建立.
   * 同时注意之所以不用substring(j,i+1)是因为这个不是O(1),所以在for(j)里面一直用deleteCharAt(0)
   * 
   * @param s
   * @param dict
   * @return
   */
  private static boolean wordBreakIGank(String s, List<String> dict) {
    if (s == null || s.length() == 0)
      return true;
    int len = s.length();
    boolean[] dp = new boolean[len + 1];  // 注意最基本的东西: index要正确
    dp[0] = true; // dp初始条件
    for (int i = 0; i < len; ++i) {
      StringBuilder sb = new StringBuilder(s.substring(0, i + 1));
      for (int j = 0; j < i + 1; ++j) {
        if (dp[j] == true && dict.contains(sb.toString())) {
          dp[i + 1] = true; // 注意是递推式是U(并集), 所以只要dp[j]&&sb[j...i+1]成立就可以结束这次循环.
          break;
        }
        sb.deleteCharAt(0); // 这样就是O(1)操作, 避免反复的substring. 本质相当于是s.substring(j,i+1)
      }
    }
    return dp[len];
  }
}
