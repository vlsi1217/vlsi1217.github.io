package freq2_tony;

import java.util.*;

/**
 * Google面试常见LC题目
 * 
 * @author tzhang
 *
 */
public class MinWindowStr {

  public static void main(String[] args) {
    MinWindowStr mws = new MinWindowStr();
    String s = "ADBAADCDAB", t = "ABC";
    String res = mws.minwindow(s, t);
    System.out.println(res);
  }

  public String minwindow(String S, String T) {
    if (S == null || S.length() == 0)
      return "";
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    for (int i = 0; i < T.length(); ++i) {
      if (!map.containsKey(T.charAt(i)))
        map.put(T.charAt(i), 1);
      else
        map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
    }

    int left = 0;
    int count = 0;
    int minLen = S.length() + 1;
    int minStart = 0;
    for (int right = 0; right < S.length(); ++right) {
      if (map.containsKey(S.charAt(right))) {
        map.put(S.charAt(right), map.get(S.charAt(right)) - 1);
        if (map.get(S.charAt(right)) >= 0)
          count++;
        while (count == T.length()) {
          if (right-left+1 < minLen) {
            minLen = right-left+1;
            minStart = left;
          }
          if (map.containsKey(S.charAt(left))) {
            map.put(S.charAt(left), map.get(S.charAt(left))+1);
            if (map.get(S.charAt(left)) > 0) {
              count--;
            }
          }
          left++;
        }
      }
    }
    if (minLen > S.length()) 
      return "";
    return S.substring(minStart, minLen + minStart);
  }

  private static void reviewHash() {
    Map<String, Integer> hashmap = new HashMap<>();
    hashmap.put("A", 1);
    hashmap.put("B", 1);
    hashmap.put("A", 1);
    hashmap.put("B", 1);
    hashmap.put("B", 1);
    hashmap.put("C", 0);
    System.out.println(hashmap.containsKey("C"));
    int res = hashmap.get("C");
    System.out.println(res);
  }
}
