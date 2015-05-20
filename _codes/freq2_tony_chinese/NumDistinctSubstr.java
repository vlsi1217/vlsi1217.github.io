package freq2_tony;

/**
 * N00t和Ganker的分析都很好. 一般算法思想: DP, recursion都用到. 而且常见的题型: 子串和序列的区别. 子串是连续的序列.
 * 发现SophieJ的解答最容易懂这个DP公式是怎么出来的:
 * http://jane4532.blogspot.com/2013/09/distinct-subsequenceleetcode.html
 * 
 * @author tzhang
 *
 */
public class NumDistinctSubstr {
  /**
   * http://jane4532.blogspot.com/2013/09/distinct-subsequenceleetcode.html SophieJ的第一个DP, 也是最常见的解法.
   * http://www.cnblogs.com/springfor/p/3896152.html 但是使用2D DP, 所以O(n^2)的时空复杂度. 注意base condition的解.
   * 
   * 注意我这里的index是按照[s][t]. 所以[0][0]是空集的t自然是空集s的序列. 同理, 空集的t都是s的的序列, 任何t都不是空集s的序列. 注意i,j在for
   * loop里面是按照自然顺序的, 所以从1到size. 但是在比较的时候, 还得回到array里面的index, 所以要i-1/j-1.
   * 
   * @param s
   * @param t
   */
  private static int numDisctinct2DP(String s, String t) {
    int[][] opt = new int[s.length() + 1][t.length() + 1];
    opt[0][0] = 1; // 2个都为空的时候也是匹配的.
    for (int i = 1; i <= s.length(); ++i) {
      opt[i][0] = 1;
    }
    for (int j = 1; j <= t.length(); ++j) {
      opt[0][j] = 0;
    }
    for (int i = 1; i <= s.length(); ++i) {
      for (int j = 1; j <= t.length(); ++j) {
        opt[i][j] = opt[i - 1][j]; // 不管此刻的i,j的s,t字母是否相同, 都可以inherit上一处的opt. 注意:
                                   // 这里的上一处是指opt[i-1][j]. 代表: match s的前一串子串的个数.
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          opt[i][j] += opt[i - 1][j - 1]; // 如果还match当前字母的话, 就加上match 各自前一处的子串.
        }
      }
    }
    return opt[s.length()][t.length()];
  }

  /**
   * http://blog.csdn.net/linhuanmars/article/details/23589057 Ganker的方法. 他在文章后面分析了DP什么时候从后向前,
   * http://jane4532.blogspot.com/2013/09/distinct-subsequenceleetcode.html 什么时候从前往后扫. 很清晰.
   * http://stackoverflow.com/questions/20459262/distinct-subsequences-dp-explanation
   * 简单的说来之所以这题可以2D-->1D是因为opt[i][j] 只和 opt[i-1][j] / opt[i-1][j-1]有关. 即可以看作opt[i]和opt[i-1].
   * 所以就成了1D了. 觉得这里的N00t解释的很清楚.
   * 
   * @param s
   * @param t
   * @return
   */
  private static int numdistinct1DP(String s, String t) {
    if (t.length() == 0)
      return 1; // 因为空集是任何非空集合的子集
    if (s.length() == 0)
      return 0; // 因为任何非空集合都不是空集的子集.
    int[] res = new int[t.length() + 1];
    res[0] = 1;
    for (int i = 1; i <= s.length(); ++i) {
      // before i-th iteration, res[j] = # of distinct of T[0...j] in S[0...i-1]
      for (int j = t.length(); j > 0; --j) {      // 这里j是从后向前扫. 这是关键. 学习了introCS里面的binomial更好理解了.
        // res[j] = res[j]; // 不管当前的char是否相等, 都可以inherit旧的值. 注意, 这里因为t是从后往前扫的, 所以是更新后的值.
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
