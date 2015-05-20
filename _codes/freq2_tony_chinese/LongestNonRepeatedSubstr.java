package freq2_tony;

import java.util.*;

/**
 * Freq 2: Longest Substring Without Repeating Characters -- LeetCode
 * 
 * @author tzhang
 *
 */
public class LongestNonRepeatedSubstr {
  public static void main(String[] args) {
    String s = "xyb12b02";// "abcabcbb";
    List<String> res = new ArrayList<>();
    // int length = lengthofLongestSubstr(s, res);
    int length = lengthOfLongestSubstring(s, res);
    System.out.println(res);
    System.out.print(length);
  }

  /**
   * N00t的解法: 用一个hashmap来记录所有的char出现的位置. 如果当前的char出现过而且在当前string内. 则update length. 因为Java是pass by
   * value, 所以primitive是对内修改. 所以要传入object, 这样可以对内修改reference, 一次改变String.
   * 
   * @param s
   * @return
   */
  private static int lengthofLongestSubstr(String s, List<String> resultStr) {
    int maxlen = 0;
    int start = 0, end = 0;
    HashMap<Character, Integer> map = new HashMap<>(); // map记录该char上次出现的position

    while (end < s.length()) {
      char cur = s.charAt(end);
      if (map.containsKey(cur) && map.get(cur) >= start) {
        if (maxlen <= end - start) {
          System.out.println(map.get(cur) + " " + (end - start) + " " + cur);
          // String path = s.substring(map.get(cur), end);
          String path = s.substring(start, end);
          resultStr.add(path);
        }
        maxlen = Math.max(maxlen, end - start); // 发现重复了, 则update length.
        start = map.get(cur) + 1; // 即开始下一段string.
      }
      map.put(cur, end++);
      // end++;
    }
    maxlen = Math.max(maxlen, end - start); // 记得要loop结束后再update一次.
    return maxlen;
  }

  /**
   * Ganker的窗口法是O(n)的解决这个问题. 这是一个通用方法(我觉得这就是前面经常看到的双指针法)
   * 
   * @param s
   * @return
   */
  public static int lengthOfLongestSubstring(String s, List<String> resultStr) {
    if (s == null || s.length() == 0)
      return 0;
    HashSet<Character> set = new HashSet<Character>();
    int max = 0;
    int walker = 0;
    int runner = 0;
    while (runner < s.length()) {
      if (set.contains(s.charAt(runner))) {
        if (max < runner - walker) {
          max = runner - walker;
          resultStr.add(s.substring(walker, runner));
        }
        while (s.charAt(walker) != s.charAt(runner)) { // 一开始没理解. 看看"xyb12b02"就知道了
          set.remove(s.charAt(walker));
          walker++;
        }
        walker++; // 即当walker== runner之后再加1, 这样开始新的start.
      } else {
        set.add(s.charAt(runner));
        // runner++; // runner应该一直向前.
      }
      runner++;
    }
    max = Math.max(max, runner - walker); // 不要忘了. 考虑一下: "xyb12b07". 即最终的时候并没有重复到7. 但是要update.
    return max;
  }
}
