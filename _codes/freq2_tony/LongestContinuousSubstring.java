package freq2_tony;
/**
 * GoPro����һ����Ŀ: abccdeeeeef->�ҵ����������ĸ, �Լ�����. �������Ӧ����e, 5.
 * @author tzhang
 *
 */
public class LongestContinuousSubstring {
  public static void main(String[] args) {
    String temp = "abccdeeeeef";
//    int res = recurse('a', 0, 1, 0, temp);
    char res = recursettt('a', '0', 0, 1, 0, temp);
    System.out.println(res);
  }

  /**
   * http://stackoverflow.com/questions/13489623/how-to-find-the-longest-continuous-sub-string-in-a-string
   * @param last
   * @param seqLength
   * @param currentIndex
   * @param largestSeqLength
   * @param source
   * @return
   */
  public static int recurse(Character last, int seqLength, int currentIndex, int largestSeqLength, String source) {
    if (currentIndex >= source.length()) {
      return largestSeqLength;
    }

    if (source.charAt(currentIndex) == last) {
      seqLength++;
      if (seqLength > largestSeqLength) {
        largestSeqLength = seqLength;
      }
    } else {
      seqLength = 1;
    }

    return recurse(source.charAt(currentIndex), seqLength, currentIndex+1, largestSeqLength, source);
  }
  
  /**
   * �ı���һ��, ��Ҫ������ظ���char�Լ����ĳ���.
   * @param last
   * @param largestChar
   * @param seqLength
   * @param currentIndex
   * @param largestSeqLength
   * @param source
   * @return
   */
  public static char recursettt(Character last, Character largestChar, int seqLength, int currentIndex, int largestSeqLength, String source) {
    if (currentIndex >= source.length()) {
      return largestChar;
    }

    if (source.charAt(currentIndex) == last) {
      seqLength++;
      if (seqLength > largestSeqLength) {
        largestSeqLength = seqLength;
        largestChar = last;
      }
    } else {
      seqLength = 1;
    }

    return recursettt(source.charAt(currentIndex), largestChar, seqLength, currentIndex+1, largestSeqLength, source);
  }
}
