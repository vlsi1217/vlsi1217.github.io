package freq2_tony;

/**
 * ����һ����palindrome�ͱ��ܵ�����PalindromeDPDP�Ľ���tbl���Ǹ���˫ָ�벢����2��whileѭ�����ж�odd/even��palindrome.
 * �����Ǹ����ҳ�һ��list�е�palindrome��subset. ��������ж�һ��Int�ǲ��Ǿ���һ��palindrome. ������ô�鷳. �뵽��2������: һ������DPDP�Ǹ��뷨�ļ�,
 * ��2��pointer��2ͷ��ʼɨ���м�. ���߾�������palindrome number������: reverse=�Լ�.
 * 
 * ��Ҫ�ο�1337�Ľⷨ: http://articles.leetcode.com/2012/01/palindrome-number.html �������һ��recursion�ⷨ����.
 * 
 * @author tzhang
 *
 */
public class PalindromeNumber {
  private static boolean isPalindromeNum(int a) {
    if (a < 0)
      return false;
    long rev = 0, origin = a;
    while (a > 0) {
      rev = a % 10 + rev * 10;
      a /= 10;
    }
    return (origin == rev);
  }

  public static void main(String[] args) {
    boolean result = isPalindromeNum(132);
    System.out.println(result);
  }
}
