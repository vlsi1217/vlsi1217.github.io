package freq2_tony;

import java.util.*;

/**
 * Word break I: �ҵ�һ��string�Ƿ����segmentΪDict�е�string.
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
   * �ο���Ganker��DP�ⷨ. �ؼ����ڵ���ʽ(recurrence)�Ľ���.
   * ͬʱע��֮���Բ���substring(j,i+1)����Ϊ�������O(1),������for(j)����һֱ��deleteCharAt(0)
   * 
   * @param s
   * @param dict
   * @return
   */
  private static boolean wordBreakIGank(String s, List<String> dict) {
    if (s == null || s.length() == 0)
      return true;
    int len = s.length();
    boolean[] dp = new boolean[len + 1];  // ע��������Ķ���: indexҪ��ȷ
    dp[0] = true; // dp��ʼ����
    for (int i = 0; i < len; ++i) {
      StringBuilder sb = new StringBuilder(s.substring(0, i + 1));
      for (int j = 0; j < i + 1; ++j) {
        if (dp[j] == true && dict.contains(sb.toString())) {
          dp[i + 1] = true; // ע���ǵ���ʽ��U(����), ����ֻҪdp[j]&&sb[j...i+1]�����Ϳ��Խ������ѭ��.
          break;
        }
        sb.deleteCharAt(0); // ��������O(1)����, ���ⷴ����substring. �����൱����s.substring(j,i+1)
      }
    }
    return dp[len];
  }
}
