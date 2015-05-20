package freq2_tony;

/**
 * 做法都差不多. 题目意思却是一开始没看懂. 看了CSDN上面的: http://blog.csdn.net/fightforyourdream/article/details/12901505
 * 才知道是干嘛的. 当然, 正如N00t分析的: String处理的时候推荐使用Stringbuilder
 * 
 * @author tzhang
 *
 */
public class CountAndSay {
  public static void main(String[] args) {
    System.out.println(countAndSay(1));
  }

  /**
   * N00t使用的和Bellman-Ford一样的方法: 双buffer法. 注意while的写法. 括号里面n-->1就行了.
   * 
   * @param n
   * @return
   */
  private static String countAndSay(int n) {
    if (n <= 0)
      return null;
    StringBuilder res = new StringBuilder("1");
    while (n-- > 1) {
      StringBuilder tmp = new StringBuilder();
      int cnt = 1;
      for (int i = 1; i < res.length(); ++i) {
        if (res.charAt(i) == res.charAt(i - 1)) {
          cnt++;
        } else {
          tmp.append(cnt).append(res.charAt(i));
          cnt = 1;
        }
      }
      tmp.append(cnt);
      tmp.append(res.charAt(res.length() - 1));
      res = tmp;
    }
    return res.toString();
  }
}
