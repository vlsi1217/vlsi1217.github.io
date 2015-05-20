package freq2_tony;

import java.util.*;

/**
 * When you see string problem that is about subsequence or matching, dynamic programming method
 * should come to your mind naturally. ---by link
 * 
 * N00t��3�ֽⷨ. ������֦�����кܶ��ֲ�ͬ����: ��ҳ����sort. ˮ�е����sort. ��������hash.
 * 
 * @author tzhang
 *
 */
public class ScrambleString {
  public static void main(String[] args) {
    String s1 = "tiger", s2 = "itreg"; // "great", s2 = "rgtae"; // s2 = "rtgae"; //
    // boolean ans = isScrambledRecN00t(s1, s2);
    // boolean ans = isScrambleBacktrackN00t(s1, s2);
    boolean ans = isScrambleCMU(s1, s2);
    System.out.println(ans);
  }

  /**
   * N00t����ԭʼ��recursion�ⷨ, ����ļ�֦̫ԭʼ, ��ֻ�ǱȽ��˳���. ��ʵһ�㶼����ˮ�е��������Ƚ�sort֮���Ƿ����. ��"abc", "acd"�϶�Ҫ��֦��.
   * 
   * @param s1
   * @param s2
   * @return
   */
  public static boolean isScrambledRecN00t(String s1, String s2) {
    int len = s1.length();
    if (len != s2.length())
      return false;
    if (s1.equals(s2))
      return true;
    for (int i = 1; i < len; ++i) {
      String s1_left = s1.substring(0, i), s1_right = s1.substring(i, len);
      // without swap
      String s2_left = s2.substring(0, i), s2_right = s2.substring(i, len);
      if (isScrambledRecN00t(s1_left, s2_left)
          && isScrambledRecN00t(s1_right, s2_right))
        return true;
      // with swap
      s2_left = s2.substring(0, len - i);
      s2_right = s2.substring(len - i, len);
      if (isScrambledRecN00t(s1_left, s2_right)
          && isScrambledRecN00t(s1_right, s2_left))
        return true;
    }
    return false;
  }

  // ----------------------------------------------N00t's Recursion+backtracking
  /**
   * �������Ҳ��Recursion(helper)����call recursion(client->helper)
   * 
   * @param s1
   * @param s2
   * @param pair
   * @return
   */
  private static boolean isScrambleBacktrackHelperN00t(String s1, String s2,
      Map<String, String> pair) {
    int len = s1.length();
    if (len != s2.length())
      return false;
    if (s1.equals(s2) || s2.equals(pair.get(s1)) || s1.equals(pair.get(s2))) // ΪʲôN00t���ﲻ�жϵ��������?
      return true;
    for (int i = 1; i < len; ++i) {
      String s1_left = s1.substring(0, i), s1_right = s1.substring(i, len);
      // without swap
      String s2_left = s2.substring(0, i), s2_right = s2.substring(i, len);
      if (isScrambleBacktrackN00t(s1_left, s2_left)
          && isScrambleBacktrackN00t(s1_right, s2_right)) {
        pair.put(s1_left, s2_left);
        pair.put(s1_right, s2_right); // ������ô�õ�: �����map�ǵ�ǰ�з���map
        return true;
      }
      // with swap
      s2_left = s2.substring(0, len - i);
      s2_right = s2.substring(len - i, len);
      if (isScrambleBacktrackN00t(s1_left, s2_right)
          && isScrambleBacktrackN00t(s1_right, s2_left)) {
        pair.put(s1_left, s2_right);
        pair.put(s1_right, s1_left);
        return true;
      }
    }
    return false;
  }

  /**
   * client of isScrambleBacktrackHelperN00t, ����Ҳ��helper����call.
   * 
   * @param s1
   * @param s2
   * @return
   */
  public static boolean isScrambleBacktrackN00t(String s1, String s2) {
    return isScrambleBacktrackHelperN00t(s1, s2, new HashMap<String, String>());
  }


  // ---------------------------------N00t��Buttom up��DP�ⷨ. �ؼ�����state�Ķ����state transition
  /**
   * һ��ʼû���N00t��opt{i][j][len]�Ķ���: ��s1.substring(i,i+len+1) �� s2.substring(j,j+len+1)��Scramble
   * String. ע�⵽substring�ĵ�һ��������inclusive, �ڶ�����exclusive��. ����i...i+len�ĳ���Ҳ��len+1. ���������ĺô���len���Դ�0��ʼ.
   * ��3D���base��0.
   * 
   * ����N00t�Ľ���, ���DP��bottom-up��. һ��BU��iteration��. ����Ҳ����recursion: LCA1337.
   * 
   * @param s1
   * @param s2
   * @return
   */
  public static boolean isScrambleDPbuN00t(String s1, String s2) {
    int len = s1.length();
    if (len != s2.length())
      return false;
    if (s1.equals(s2))
      return true;

    // 3D DP�ı�. Ŀ���ǲ������¼����Ѿ������������. ��ÿ�μ���ǰ��һ�±�. O(1)ʱ��.
    boolean[][][] scrambled = new boolean[len][len][len]; // Scrambled[i][j][k]. ע��k�ĺ���.
                                                          // �Լ�substring(st,end)�Ŀ���.
    // ��ʼ���߽�, ��LCS��Ҫ�ҹ���, ����ܼ�, ͨ��������ܷ���
    for (int i = 0; i < len; ++i) {
      for (int j = 0; j < len; ++j) {
        scrambled[i][j][0] =
            s1.substring(i, i + 1).equals(s2.substring(j, j + 1));
      }
    }

    // ��ʼBottom-up�Ĵ����
    for (int k = 1; k < len; ++k) { // Ganker: ��Ϊ����С��n������������Ƕ���ǰ�������ˣ�Ҳ���ǳ����������ѭ����
      for (int i = 0; i < len - k; ++i) {
        for (int j = 0; j < len - k; ++j) {
          scrambled[i][j][k] = false; // ���Ǹ�ʲô?
          for (int p = 0; p < k; ++p) {
            boolean llrr =
                scrambled[i][j][p]
                    && scrambled[i + p + 1][j + p + 1][k - p - 1];
            boolean lrrl = scrambled[i][j + k - p][p]
            // scrambled[i][j + p - 1][p] // ���ֽǱ��ѡ���ǵ���linkedlist reverse����, Ҫ������, ���˲�����debug.
                && scrambled[i + p + 1][j][k - p - 1];
            scrambled[i][j][k] = llrr || lrrl; // ע��: ֻҪk����һ��p���з���Scramble��OK��.(Ϊʲô?) ����������K.
            if (scrambled[i][j][k] == true)
              break;  // �ҵ�һ�������p�Ϳ���break��, Ȼ��...?
          }
        }
      }
    }
    return scrambled[0][0][len - 1];
  }

  // -------------------------------------------------------------> Ganker��DP�ⷨ. ���Ӷȷ����ܺ�
  public static boolean isScrambleGanker(String s1, String s2) {
    if (s1 == null || s2 == null || s1.length() != s2.length())
      return false;
    int lenS1 = s1.length();
    if (lenS1 == 0)
      return true;
    boolean[][][] res = new boolean[lenS1][lenS1][lenS1 + 1];
    for (int i = 0; i < lenS1; ++i) {
      for (int j = 0; j < lenS1; ++j) {
        res[i][j][1] = s1.charAt(i) == s2.charAt(i);
      }
    }

    for (int len = 2; len <= lenS1; ++len) {
      for (int i = 0; i < lenS1; ++i) {
        for (int j = 0; j < lenS1; ++j) {
          for (int k = 1; k < len; ++k) {
            res[i][j][len] |=
                res[i][j][k] && res[i + k][j + k][len - k]
                    || res[i][j + len - k][k] && res[i + k][j][len - k];
          }
        }
      }
    }
    return res[0][0][lenS1];
  }

  // --------------------------------------------------------> ��ҳ����DP�ⷨ:
  /*
   * Solution 4: The DP Version. REDO
   */
  public static boolean isScrambleCMU(String s1, String s2) {
    if (s1 == null || s2 == null) {
      return false;
    }

    int len = s1.length();

    if (s2.length() != len) {
      return false;
    }

    boolean[][][] OPT = new boolean[len][len][len + 1];

    // D[i][j][k] = D[i][]
    for (int k = 1; k <= len; k++) {
      // ע������ı߽�ѡȡ�� ���ѡ�Ĳ��ԣ��ͻᷢ��Խ������.. orz..
      // attention: should use "<="
      for (int i = 0; i <= len - k; i++) {
        for (int j = 0; j <= len - k; j++) {
          if (k == 1) {
            OPT[i][j][k] = s1.charAt(i) == s2.charAt(j);
            continue; // �õĺ�, �����������k=1�������, ����ȫ��j. ���ǲ��ɱ����i=i+1�����Ҫ����һ��loop��ʱ�����update
          }

          OPT[i][j][k] = false; // Ϊʲô?
          for (int l = 1; l <= k - 1; l++) {
            if (OPT[i][j][l] && OPT[i + l][j + l][k - l]
                || OPT[i][j + k - l][l] && OPT[i + l][j][k - l]) {
              OPT[i][j][k] = true;
              break;
            }
          }
        }
      }
    }

    return OPT[0][0][len];
  }
}
