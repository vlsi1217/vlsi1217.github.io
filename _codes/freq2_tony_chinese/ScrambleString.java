package freq2_tony;

import java.util.*;

/**
 * When you see string problem that is about subsequence or matching, dynamic programming method
 * should come to your mind naturally. ---by link
 * 
 * N00t的3种解法. 不过剪枝那里有很多种不同方法: 主页君的sort. 水中的鱼的sort. 还有武大的hash.
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
   * N00t的最原始的recursion解法, 这里的剪枝太原始, 就只是比较了长度. 其实一般都是像水中的鱼那样比较sort之后是否相等. 即"abc", "acd"肯定要剪枝的.
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
   * 这个方法也是Recursion(helper)里面call recursion(client->helper)
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
    if (s1.equals(s2) || s2.equals(pair.get(s1)) || s1.equals(pair.get(s2))) // 为什么N00t这里不判断第三种情况?
      return true;
    for (int i = 1; i < len; ++i) {
      String s1_left = s1.substring(0, i), s1_right = s1.substring(i, len);
      // without swap
      String s2_left = s2.substring(0, i), s2_right = s2.substring(i, len);
      if (isScrambleBacktrackN00t(s1_left, s2_left)
          && isScrambleBacktrackN00t(s1_right, s2_right)) {
        pair.put(s1_left, s2_left);
        pair.put(s1_right, s2_right); // 想想怎么用的: 这里的map是当前切法的map
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
   * client of isScrambleBacktrackHelperN00t, 而且也在helper里面call.
   * 
   * @param s1
   * @param s2
   * @return
   */
  public static boolean isScrambleBacktrackN00t(String s1, String s2) {
    return isScrambleBacktrackHelperN00t(s1, s2, new HashMap<String, String>());
  }


  // ---------------------------------N00t的Buttom up的DP解法. 关键在于state的定义和state transition
  /**
   * 一开始没理解N00t的opt{i][j][len]的定义: 即s1.substring(i,i+len+1) 是 s2.substring(j,j+len+1)的Scramble
   * String. 注意到substring的第一个参数是inclusive, 第二个是exclusive的. 不过i...i+len的长度也是len+1. 但是这样的好处是len可以从0开始.
   * 即3D表格base是0.
   * 
   * 照着N00t的解释, 这个DP是bottom-up的. 一般BU是iteration的. 不过也可以recursion: LCA1337.
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

    // 3D DP的表. 目的是不用重新计算已经算过的子问题. 即每次计算前查一下表. O(1)时间.
    boolean[][][] scrambled = new boolean[len][len][len]; // Scrambled[i][j][k]. 注意k的含义.
                                                          // 以及substring(st,end)的开闭.
    // 初始化边界, 在LCS中要找规律, 这里很简单, 通过含义就能发现
    for (int i = 0; i < len; ++i) {
      for (int j = 0; j < len; ++j) {
        scrambled[i][j][0] =
            s1.substring(i, i + 1).equals(s2.substring(j, j + 1));
      }
    }

    // 开始Bottom-up的打表了
    for (int k = 1; k < len; ++k) { // Ganker: 因为长度小于n的所有情况我们都在前面求解过了（也就是长度是最外层循环）
      for (int i = 0; i < len - k; ++i) {
        for (int j = 0; j < len - k; ++j) {
          scrambled[i][j][k] = false; // 这是干什么?
          for (int p = 0; p < k; ++p) {
            boolean llrr =
                scrambled[i][j][p]
                    && scrambled[i + p + 1][j + p + 1][k - p - 1];
            boolean lrrl = scrambled[i][j + k - p][p]
            // scrambled[i][j + p - 1][p] // 这种角标的选择还是得像linkedlist reverse那样, 要搞熟练, 错了不容易debug.
                && scrambled[i + p + 1][j][k - p - 1];
            scrambled[i][j][k] = llrr || lrrl; // 注意: 只要k中有一个p的切法是Scramble就OK了.(为什么?) 所以这里是K.
            if (scrambled[i][j][k] == true)
              break;  // 找到一个满足的p就可以break了, 然后到...?
          }
        }
      }
    }
    return scrambled[0][0][len - 1];
  }

  // -------------------------------------------------------------> Ganker的DP解法. 复杂度分析很好
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

  // --------------------------------------------------------> 主页君的DP解法:
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
      // 注意这里的边界选取。 如果选的不对，就会发生越界的情况.. orz..
      // attention: should use "<="
      for (int i = 0; i <= len - k; i++) {
        for (int j = 0; j <= len - k; j++) {
          if (k == 1) {
            OPT[i][j][k] = s1.charAt(i) == s2.charAt(j);
            continue; // 用的好, 这里是想进入k=1的情况后, 跑完全部j. 但是不可避免的i=i+1的情况要在下一次loop的时候才能update
          }

          OPT[i][j][k] = false; // 为什么?
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
