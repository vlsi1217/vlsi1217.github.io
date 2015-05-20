package freq2_tony;

/**
 * ���������. ��Ŀ��˼ȴ��һ��ʼû����. ����CSDN�����: http://blog.csdn.net/fightforyourdream/article/details/12901505
 * ��֪���Ǹ����. ��Ȼ, ����N00t������: String�����ʱ���Ƽ�ʹ��Stringbuilder
 * 
 * @author tzhang
 *
 */
public class CountAndSay {
  public static void main(String[] args) {
    System.out.println(countAndSay(1));
  }

  /**
   * N00tʹ�õĺ�Bellman-Fordһ���ķ���: ˫buffer��. ע��while��д��. ��������n-->1������.
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
