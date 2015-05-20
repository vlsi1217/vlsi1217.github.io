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
   * �ο���Ganker������. �������ǿ�������õĽⷨ��. ����⿼����Ҫ��д����Ļ�����. �ܶ�ϸ�ں�index.
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
      if ((count + words[i].length() + i - last) > L) { // i-last�Ǽ��㵱ǰ������ÿ������֮��Ҫ��һ���ո�Ŀռ�.
        int spacenum = 0;
        int extranum = 0;
        if (i - last - 1 > 0) { // �����i-last-1��ʲô����? i-1�൱��end, last�൱��start, ��������м��
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
            extranum--; // ��ĿҪ���Ǿ���ƽ�ֿո�, ������ߵļ��Ҫ�������.
          }
        }
        // ������һ���߽�����: �����������ֻ�ܷ�һ����, �Ǿͱ���Ҫ����ո���pad��.
        for (int l = 0; l < L-sb.length(); ++l) {//(int l = sb.length(); l < L; ++l) {
          sb.append(" ");
        }
        res.add(sb.toString());
        count = 0;
        last = i; // ����last��Ϊѭ����һ��ʱ, �Ŀ�ͷ.
      }
      count += words[i].length();
    }
    // �������һ����. ע�����һ���Ǻ��п����Ƕ�����ʵ�. ���Ǽ򵥺ܶ�, ���һ�е�ÿ�����ʼ��һ���վ���.
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
