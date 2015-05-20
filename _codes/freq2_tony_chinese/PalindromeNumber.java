package freq2_tony;

/**
 * 本来一看到palindrome就本能的想用PalindromeDPDP的建立tbl的那个用双指针并且用2个while循环来判断odd/even的palindrome.
 * 但是那个是找出一个list中的palindrome的subset. 这里就是判断一个Int是不是就是一个palindrome. 不用那么麻烦. 想到了2个方法: 一个就是DPDP那个想法的简化,
 * 用2个pointer从2头开始扫到中间. 或者就是利用palindrome number的性质: reverse=自己.
 * 
 * 主要参考1337的解法: http://articles.leetcode.com/2012/01/palindrome-number.html 其中最后一个recursion解法不错.
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
