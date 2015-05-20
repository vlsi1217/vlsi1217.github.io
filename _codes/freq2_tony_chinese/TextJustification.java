package freq2_tony;

import java.util.*;

public class TextJustification {

  public static void main(String[] args) {
    String[] words =
        {"This", "is", "an", "example", "of", "txt", "a", "justification."};
    List<String> result = (new TextJustification().txtjustify(words, 16));
    for (String r : result) {
      System.out.println(r);
    }
  }

  /**
   * 参考的Ganker的做法. 基本上是看到的最好的解法了. 这道题考的主要是写代码的基本功. 很多细节和index.
   * 
   * @param words
   * @param L
   * @return
   */
  public List<String> txtjustify(String[] words, int L) {
    List<String> res = new ArrayList<>();
    if (words == null || words.length == 0)
      return res;
    int count = 0;
    int last = 0;
    for (int i = 0; i < words.length; ++i) {
      if ((count + words[i].length() + i - last) > L) { // i-last是计算当前行至少每个单词之间要有一个空格的空间.
        int spacenum = 0;
        int extranum = 0;
        if (i - last - 1 > 0) { // 这里的i-last-1是什么东西? i-1相当于end, last相当于start, 所以求该行间隔
          spacenum = (L - count) / (i - last - 1);
          extranum = (L - count) % (i - last - 1);
        }
        StringBuilder sb = new StringBuilder();
        for (int j = last; j < i; ++j) {
          sb.append(words[j]);
          if (j < i - 1) {
            for (int k = 0; k < spacenum; ++k) {
              sb.append(" ");
            }
            if (extranum > 0) {
              sb.append(" ");
            }
            extranum--; // 题目要求是尽量平分空格, 但是左边的间隔要更多机会.
          }
        }
        // 这里又一个边界条件: 就是如果该行只能放一个词, 那就必须要分配空格来pad了.
        for (int l = 0; l < L-sb.length(); ++l) {//(int l = sb.length(); l < L; ++l) {
          sb.append(" ");
        }
        res.add(sb.toString());
        count = 0;
        last = i; // 更新last作为循环下一行时, 的开头.
      }
      count += words[i].length();
    }
    // 处理到最后一行了. 注意最后一行是很有可能是多个单词的. 但是简单很多, 最后一行的每个单词间隔一个空就行.
    StringBuilder st = new StringBuilder();
    for (int i = last; i < words.length; ++i) {
      st.append(words[i]);
      if (st.length() < L) {
        st.append(" ");
      }
    }
    for (int j = st.length(); j < L; ++j) {
      st.append(" ");
    }
    res.add(st.toString());
    return res;
  }

}
