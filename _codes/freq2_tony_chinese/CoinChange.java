package freq2_tony;

/**
 * We must organize the computation so that all entries that we need are filled in prior to using
 * them in subsequent computations.
 */
import java.util.*;

/**
 * 今天听了段公子的DP, 里面经典的2D->1D DP优化的第一题就是coin change. 这对于理解distinct subsequence帮助很大.
 * http://www.acmerblog.com/dp6-coin-change-4973.html
 * 
 * @author tzhang
 *
 */
public class CoinChange {

  /**
   * 终于学会怎么用Recursion了, 用的是N00t的combinations recursion模版. 一开始得到4个[]的list<list>是因为我直接add(path),
   * 而不是path的copy. 因为Java是call by reference, 所以后面改了, 前面add的是reference, 自然也是没用的.
   * 
   * @param coins : 给出一组硬币
   * @param m : m种不同面值的硬币
   * @param value : 要找到N元钱
   * @param path : 记录现在的硬币组合
   * @param res : 所有解
   * @return
   */
  private int coinRecur(int[] coins, int m, int value, List<Integer> path,
      List<List<Integer>> res) {
    if (value == 0) {
      List<Integer> pathCpy = new ArrayList<Integer>(path); // 关键步骤!
      res.add(pathCpy);
      return 1;
    }
    if (value < 0 || m <= 0) {
      // path.remove(path.size()-1);
      // res.remove(res.size()-1);
      return 0;
    }
    // 开始分类: 如果不包含第m种硬币
    int notInclu = coinRecur(coins, m - 1, value, path, res);
    // 如果包含第m种硬币, 则down tree中加入该硬币的value.
    path.add(coins[m - 1]);
    int inclu = coinRecur(coins, m, value - coins[m - 1], path, res); // 为什么是coins[m-1]呢? 因为index
                                                                      // m-1就是第m个硬币的面值.
    // 最后记得up tree的时候要吐出来之前加入的第m种硬币.
    path.remove(path.size() - 1);
    // return 2种组合的解
    return notInclu + inclu;
  }

  /**
   * ACM之家的2D的自下而上的打表. 先理解这个, 再去看优化成1D的DP.
   * 
   * @param coins
   * @param m
   * @param n
   * @return
   */
  private int countBU2D(int[] coins, int m, int n) {
    int opt[][] = new int[n + 1][m];
    // 现初始化边界
    for (int i = 0; i < m; ++i) {
      opt[0][i] = 1;
    }
    //
    for (int i = 1; i < n + 1; ++i) {
      for (int j = 0; j < m; ++j) {
        // 包含m硬币
        int x = (coins[j] <= i) ? opt[i - coins[j]][j] : 0;
        // 不包含m硬币
        int y = (j >= 1) ? opt[i][j - 1] : 0;
        opt[i][j] = x + y;
      }
    }
    return opt[n][m - 1];
  }

  /**
   * ACM之家的1D的自下而上的DP, youtube这个视频分析的很详细. 自己想了好久. 例如distinct subsequences的1D. Topcoder的DP
   * https://www.youtube.com/watch?v=za2bgJLHmxI
   * 
   * @param S
   * @param m
   * @param n
   * @return
   */
  private int countBU1D(int S[], int m, int n) {
    int[] opt = new int[n + 1];
    opt[0] = 1;
    // 是01背包问题. 即每一行都只和上一行的值有关. 但还是没理解.
    for (int i = 0; i < m; ++i) {
      for (int j = S[i]; j <= n; ++j) {
        opt[j] += opt[j - S[i]];
      }
    }
    return opt[n];
  }

  public CoinChange() {
    int[] coins = {1, 2, 3};
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    int resDFS =
        coinRecur(coins, coins.length, 5, new ArrayList<Integer>(), res);
    // int resDFS = countDFS(coins, 3, 4);
    // System.out.println(resDFS);
    // System.out.println(res);
    int result = (countBU2D(coins, 3, 4));
    System.out.println(result);
  }

  public static void main(String[] args) {
    CoinChange ccdp = new CoinChange();
  }
}
