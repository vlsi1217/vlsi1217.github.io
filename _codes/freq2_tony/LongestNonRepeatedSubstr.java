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
   * N00t�Ľⷨ: ��һ��hashmap����¼���е�char���ֵ�λ��. �����ǰ��char���ֹ������ڵ�ǰstring��. ��update length. ��ΪJava��pass by
   * value, ����primitive�Ƕ����޸�. ����Ҫ����object, �������Զ����޸�reference, һ�θı�String.
   * 
   * @param s
   * @return
   */
  private static int lengthofLongestSubstr(String s, List<String> resultStr) {
    int maxlen = 0;
    int start = 0, end = 0;
    HashMap<Character, Integer> map = new HashMap<>(); // map��¼��char�ϴγ��ֵ�position

    while (end < s.length()) {
      char cur = s.charAt(end);
      if (map.containsKey(cur) && map.get(cur) >= start) {
        if (maxlen <= end - start) {
          System.out.println(map.get(cur) + " " + (end - start) + " " + cur);
          // String path = s.substring(map.get(cur), end);
          String path = s.substring(start, end);
          resultStr.add(path);
        }
        maxlen = Math.max(maxlen, end - start); // �����ظ���, ��update length.
        start = map.get(cur) + 1; // ����ʼ��һ��string.
      }
      map.put(cur, end++);
      // end++;
    }
    maxlen = Math.max(maxlen, end - start); // �ǵ�Ҫloop��������updateһ��.
    return maxlen;
  }

  /**
   * Ganker�Ĵ��ڷ���O(n)�Ľ���������. ����һ��ͨ�÷���(�Ҿ��������ǰ�澭��������˫ָ�뷨)
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
        while (s.charAt(walker) != s.charAt(runner)) { // һ��ʼû���. ����"xyb12b02"��֪����
          set.remove(s.charAt(walker));
          walker++;
        }
        walker++; // ����walker== runner֮���ټ�1, ������ʼ�µ�start.
      } else {
        set.add(s.charAt(runner));
        // runner++; // runnerӦ��һֱ��ǰ.
      }
      runner++;
    }
    max = Math.max(max, runner - walker); // ��Ҫ����. ����һ��: "xyb12b07". �����յ�ʱ��û���ظ���7. ����Ҫupdate.
    return max;
  }
}
