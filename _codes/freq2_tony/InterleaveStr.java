package freq2_tony;

public class InterleaveStr {
  public static void main(String[] args) {
    String s1 = "aabcc";
    String s2 = "dbbca";
    String s3 = "aadbbcbcac";
    boolean ans =
    // isInterleave(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0,
    // 0, 0);
        isInterleave1D(s1, s2, s3);
    System.out.println(ans);

    // int i = 5;
    // while (i < 10)
    // System.out.println(i++);
  }

  /**
   * Recursive�ⷨ, ��ֱ���׶�, ���ǵݹ���product code������Ҫ�����.
   * 
   * @param s1
   * @param s2
   * @param s3
   * @param i
   * @param j
   * @param k
   * @return
   */
  public static boolean isInterleave(char[] s1, char[] s2, char[] s3, int i,
      int j, int k) {
    if (k == s3.length)
      return true;
    if (i == s1.length)
      return (s2[j] == s3[k] && isInterleave(s1, s2, s3, i, j + 1, k + 1));
    if (j == s2.length)
      return (s1[i] == s3[k] && isInterleave(s1, s2, s3, i + 1, j, k + 1));
    return (s1[i] == s3[k] && isInterleave(s1, s2, s3, i + 1, j, k + 1) || s2[j] == s3[k]
        && isInterleave(s1, s2, s3, i, j + 1, k + 1));
  }

  /**
   * 2D DP�Ľⷨ.
   * 
   * @param s1
   * @param s2
   * @param s3
   * @return
   */
  public static boolean isInterleave2D(String s1, String s2, String s3) {
    int L1 = s1.length(), L2 = s2.length(), L3 = s3.length();
    if (L1 + L2 != L3)
      return false;
    boolean[][] opt = new boolean[L1 + 1][L2 + 1];
    opt[0][0] = true;
    // ��������substring. �ܻ�ʱ��. ����û�����õ�table
    // for (int i = 0; i < L1; ++i) {
    // opt[i + 1][0] = s1.substring(0, i+1).equals(s3.substring(0, i+1));
    // }
    // for (int j = 0; j < L2; ++j) {
    // opt[0][j + 1] = s2.substring(0, j+1).equals(s3.substring(0, j+1));
    // }
    for (int i = 0; i < L1; ++i) {
      opt[i + 1][0] = (s1.charAt(i) == s3.charAt(i)) && opt[i][0];
    }
    for (int i = 0; i < L2; ++i) {
      opt[0][i + 1] = (s2.charAt(i) == s3.charAt(i)) && opt[0][i];
    }
    for (int i = 0; i < L1; ++i) {
      for (int j = 0; j < L2; ++j) {
        opt[i + 1][j + 1] =
            opt[i][j + 1] && s1.charAt(i) == s3.charAt(i + j + 1)
                || opt[i + 1][j] && s2.charAt(j) == s3.charAt(i + j + 1);
      }
    }
    return opt[L1][L2];
  }

  /**
   * Ganker�Ĵ�д�ıȽϺ�. ����2��string��ʱ����÷ֳ�һ��һ��. ֻ�ö̵�. ����while������һЩ.
   * @param s1
   * @param s2
   * @param s3
   * @return
   */
  public static boolean isInterleave1D(String s1, String s2, String s3) {
    int L1 = s1.length(), L2 = s2.length(), L3 = s3.length();
    if (L1 + L2 != L3)
      return false;
    String minWord = L1 > L2 ? s2 : s1;
    String maxWord = L1 > L2 ? s1 : s2;
    boolean[] opt = new boolean[minWord.length() + 1];
    opt[0] = true;
    // init��һ��. �����̵ķ��ں���, ���ķ�������. ���Խ�ʡ�ռ�. ʱ����i*j
    for (int i = 0; i < minWord.length(); ++i) {
      opt[i + 1] = opt[i] && minWord.charAt(i) == s3.charAt(i);
    }
    for (int i = 0; i < maxWord.length(); ++i) {
      opt[0] = opt[0] && maxWord.charAt(i) == s3.charAt(i);
      for (int j = 0; j < minWord.length(); ++j) {
        opt[j + 1] = // �����е����. ����Ǽ̳���һ��, �����жϳ��Ķ�Ӧchar. ��������, �����ж϶εĶ�Ӧchar.
            opt[j] && minWord.charAt(j) == s3.charAt(i + j + 1) || opt[j + 1]
                && maxWord.charAt(i) == s3.charAt(i + j + 1);
      }
    }
    return opt[minWord.length()];
  }
}
