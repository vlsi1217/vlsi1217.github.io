package freq2_tony;

/**
 * �򵥵��������. bruteforce����. ����N00t��2������������API����ƺ��㷨�ĸĽ�. �����Ganker��Ҫ��.
 * 
 * @author tzhang
 *
 */
public class ValidSudoku {
  /**
   * �����API���
   * 
   * @param c
   * @param numbers
   * @return
   */
  private boolean isValid(char c, boolean[] numbers) {
    if (c == '.')
      return true;
    if (!(c >= '1' || c <= '9' || numbers[c - '1']))
      return false;
    numbers[c - '1'] = true;
    return true;
  }

  /**
   * ������ʹ��/��%���ҵ���Ӧ��Сboard��row/col.
   * 
   * @param board
   * @return
   */
  public boolean isValidSudoku(char[][] board) {
    if (board.length != 9)
      return false;
    if (board[0].length != 9)
      return false;
    for (int i = 0; i < 9; ++i) {
      boolean[] block = new boolean[9];
      for (int j = 0; j < 9; ++j) {
        if (!isValid(board[i][j], block))
          return false;
      }
    }
    for (int j = 0; j < 9; ++j) {
      boolean[] block = new boolean[9];
      for (int i = 0; i < 9; ++i) {
        if (!isValid(board[j][i], block))
          return false;
      }
    }
    for (int smallboard = 0; smallboard < 9; ++smallboard) {
      boolean[] block = new boolean[9];
      for (int i = smallboard / 3 * 3; i < smallboard / 3 * 3 + 3; ++i) {
        for (int j = smallboard % 3 * 3; j < smallboard % 3 * 3 + 3; ++j) {
          if (!isValid(board[i][j], block))
            return false;
        }
      }
    }
    return true;
  }

  
}
