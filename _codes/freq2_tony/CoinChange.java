package freq2_tony;

/**
 * We must organize the computation so that all entries that we need are filled in prior to using
 * them in subsequent computations.
 */
import java.util.*;

/**
 * �������˶ι��ӵ�DP, ���澭���2D->1D DP�Ż��ĵ�һ�����coin change. ��������distinct subsequence�����ܴ�.
 * http://www.acmerblog.com/dp6-coin-change-4973.html
 * 
 * @author tzhang
 *
 */
public class CoinChange {

  /**
   * ����ѧ����ô��Recursion��, �õ���N00t��combinations recursionģ��. һ��ʼ�õ�4��[]��list<list>����Ϊ��ֱ��add(path),
   * ������path��copy. ��ΪJava��call by reference, ���Ժ������, ǰ��add����reference, ��ȻҲ��û�õ�.
   * 
   * @param coins : ����һ��Ӳ��
   * @param m : m�ֲ�ͬ��ֵ��Ӳ��
   * @param value : Ҫ�ҵ�NԪǮ
   * @param path : ��¼���ڵ�Ӳ�����
   * @param res : ���н�
   * @return
   */
  private int coinRecur(int[] coins, int m, int value, List<Integer> path,
      List<List<Integer>> res) {
    if (value == 0) {
      List<Integer> pathCpy = new ArrayList<Integer>(path); // �ؼ�����!
      res.add(pathCpy);
      return 1;
    }
    if (value < 0 || m <= 0) {
      // path.remove(path.size()-1);
      // res.remove(res.size()-1);
      return 0;
    }
    // ��ʼ����: �����������m��Ӳ��
    int notInclu = coinRecur(coins, m - 1, value, path, res);
    // ���������m��Ӳ��, ��down tree�м����Ӳ�ҵ�value.
    path.add(coins[m - 1]);
    int inclu = coinRecur(coins, m, value - coins[m - 1], path, res); // Ϊʲô��coins[m-1]��? ��Ϊindex
                                                                      // m-1���ǵ�m��Ӳ�ҵ���ֵ.
    // ���ǵ�up tree��ʱ��Ҫ�³���֮ǰ����ĵ�m��Ӳ��.
    path.remove(path.size() - 1);
    // return 2����ϵĽ�
    return notInclu + inclu;
  }

  /**
   * ACM֮�ҵ�2D�����¶��ϵĴ��. ��������, ��ȥ���Ż���1D��DP.
   * 
   * @param coins
   * @param m
   * @param n
   * @return
   */
  private int countBU2D(int[] coins, int m, int n) {
    int opt[][] = new int[n + 1][m];
    // �ֳ�ʼ���߽�
    for (int i = 0; i < m; ++i) {
      opt[0][i] = 1;
    }
    //
    for (int i = 1; i < n + 1; ++i) {
      for (int j = 0; j < m; ++j) {
        // ����mӲ��
        int x = (coins[j] <= i) ? opt[i - coins[j]][j] : 0;
        // ������mӲ��
        int y = (j >= 1) ? opt[i][j - 1] : 0;
        opt[i][j] = x + y;
      }
    }
    return opt[n][m - 1];
  }

  /**
   * ACM֮�ҵ�1D�����¶��ϵ�DP, youtube�����Ƶ�����ĺ���ϸ. �Լ����˺þ�. ����distinct subsequences��1D. Topcoder��DP
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
    // ��01��������. ��ÿһ�ж�ֻ����һ�е�ֵ�й�. ������û���.
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
