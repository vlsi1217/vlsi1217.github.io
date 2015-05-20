package freq2_tony;

/**
 * N00t��Ganker�ķ������ܺ�. һ���㷨˼��: DP, recursion���õ�. ���ҳ���������: �Ӵ������е�����. �Ӵ�������������.
 * ����SophieJ�Ľ�������׶����DP��ʽ����ô������:
 * http://jane4532.blogspot.com/2013/09/distinct-subsequenceleetcode.html
 * 
 * @author tzhang
 *
 */
public class NumDistinctSubstr {
  /**
   * http://jane4532.blogspot.com/2013/09/distinct-subsequenceleetcode.html SophieJ�ĵ�һ��DP, Ҳ������Ľⷨ.
   * http://www.cnblogs.com/springfor/p/3896152.html ����ʹ��2D DP, ����O(n^2)��ʱ�ո��Ӷ�. ע��base condition�Ľ�.
   * 
   * ע���������index�ǰ���[s][t]. ����[0][0]�ǿռ���t��Ȼ�ǿռ�s������. ͬ��, �ռ���t����s�ĵ�����, �κ�t�����ǿռ�s������. ע��i,j��for
   * loop�����ǰ�����Ȼ˳���, ���Դ�1��size. �����ڱȽϵ�ʱ��, ���ûص�array�����index, ����Ҫi-1/j-1.
   * 
   * @param s
   * @param t
   */
  private static int numDisctinct2DP(String s, String t) {
    int[][] opt = new int[s.length() + 1][t.length() + 1];
    opt[0][0] = 1; // 2����Ϊ�յ�ʱ��Ҳ��ƥ���.
    for (int i = 1; i <= s.length(); ++i) {
      opt[i][0] = 1;
    }
    for (int j = 1; j <= t.length(); ++j) {
      opt[0][j] = 0;
    }
    for (int i = 1; i <= s.length(); ++i) {
      for (int j = 1; j <= t.length(); ++j) {
        opt[i][j] = opt[i - 1][j]; // ���ܴ˿̵�i,j��s,t��ĸ�Ƿ���ͬ, ������inherit��һ����opt. ע��:
                                   // �������һ����ָopt[i-1][j]. ����: match s��ǰһ���Ӵ��ĸ���.
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          opt[i][j] += opt[i - 1][j - 1]; // �����match��ǰ��ĸ�Ļ�, �ͼ���match ����ǰһ�����Ӵ�.
        }
      }
    }
    return opt[s.length()][t.length()];
  }

  /**
   * http://blog.csdn.net/linhuanmars/article/details/23589057 Ganker�ķ���. �������º��������DPʲôʱ��Ӻ���ǰ,
   * http://jane4532.blogspot.com/2013/09/distinct-subsequenceleetcode.html ʲôʱ���ǰ����ɨ. ������.
   * http://stackoverflow.com/questions/20459262/distinct-subsequences-dp-explanation
   * �򵥵�˵��֮�����������2D-->1D����Ϊopt[i][j] ֻ�� opt[i-1][j] / opt[i-1][j-1]�й�. �����Կ���opt[i]��opt[i-1].
   * ���Ծͳ���1D��. ���������N00t���͵ĺ����.
   * 
   * @param s
   * @param t
   * @return
   */
  private static int numdistinct1DP(String s, String t) {
    if (t.length() == 0)
      return 1; // ��Ϊ�ռ����κηǿռ��ϵ��Ӽ�
    if (s.length() == 0)
      return 0; // ��Ϊ�κηǿռ��϶����ǿռ����Ӽ�.
    int[] res = new int[t.length() + 1];
    res[0] = 1;
    for (int i = 1; i <= s.length(); ++i) {
      // before i-th iteration, res[j] = # of distinct of T[0...j] in S[0...i-1]
      for (int j = t.length(); j > 0; --j) {      // ����j�ǴӺ���ǰɨ. ���ǹؼ�. ѧϰ��introCS�����binomial���������.
        // res[j] = res[j]; // ���ܵ�ǰ��char�Ƿ����, ������inherit�ɵ�ֵ. ע��, ������Ϊt�ǴӺ���ǰɨ��, �����Ǹ��º��ֵ.
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          res[j] += res[j - 1];
        }
      }
      // after i-th iteration, res[j] = # of distinct of T[0...j] in S[0...i]
    }
    return res[t.length()];
  }

  public static void main(String[] args) {
    String s = "rabbbit", t = "rabbit", t2 = "tibbar";
    System.out.println(numdistinct1DP(s, t));
  }
}
