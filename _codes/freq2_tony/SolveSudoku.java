package freq2_tony;

/*
 * Ganker�Ľ��Ƚϼ��. Ҳ������NP����ĳ���brute force�ⷨ: ѭ��recursion.
 */
public class SolveSudoku {

  /**
   * ѭ���ݹ�д�ú�! ��Combination�Ļ�Ҫ��. ��Ϊ�ݹ���������ж���. �����helper()���return true.
   * @param board
   * @param i
   * @param j
   * @return
   */
  public boolean helper(char[][] board, int i, int j) {
    if (j >= 9) {
      return helper(board, i + 1, j);
    }
    if (i == 9) {
      return true;  // ���ж�j>=9. ���Ǳ�֤���е������ж�����֮��, ����ʱiҲΪ9. ˵��ȫ��.
    }
    if (board[i][j] == '.') {
      for (int k = 1; k <= 9; ++k) {
        board[i][j] = (char)(k+'0');
        if (isValid(board, i, j)) {
          if (helper(board, i, j+1)) {
            return true;  // ??? �൱��going up the tree. ���ϲ��ӽ�����ʱ��.
          }
        }
        board[i][j] = '.'; // ??? �൱��N-Queens�����path.remove(path.size()-1);
      }
    }
    else {
      return helper(board, i, j+1);
    }
    return false;
  }

  /**
   * д������Ư��.
   * 
   * @param board
   * @param i
   * @param j
   * @return
   */
  private boolean isValid(char[][] board, int i, int j) {
    for (int k = 0; k < 9; ++k) {
      if (k != j && board[i][j] == board[i][k])
        return false;
    }
    for (int k = 0; k < 9; ++k) {
      if (k != i && board[i][j] == board[k][j])
        return false;
    }
    for (int row = i / 3 * 3; row < i / 3 * 3 + 3; ++i) {
      for (int col = j / 3 * 3; col < j / 3 * 3 + 3; ++j) { // ע�����ﲢ����%.
        if ((row != i || col != j) && board[row][col] == board[i][j]) // ע�����ﲢ����&&.
          return false;
      }
    }
    return true;
  }
}
