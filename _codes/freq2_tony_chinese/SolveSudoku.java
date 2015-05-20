package freq2_tony;

/*
 * Ganker的解答比较简洁. 也是延续NP问题的常见brute force解法: 循环recursion.
 */
public class SolveSudoku {

  /**
   * 循环递归写得好! 比Combination的还要好. 因为递归调用用于判断里. 想清楚helper()后的return true.
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
      return true;  // 先判断j>=9. 就是保证该行的所有列都填满之后, 若此时i也为9. 说明全满.
    }
    if (board[i][j] == '.') {
      for (int k = 1; k <= 9; ++k) {
        board[i][j] = (char)(k+'0');
        if (isValid(board, i, j)) {
          if (helper(board, i, j+1)) {
            return true;  // ??? 相当于going up the tree. 即合并子解树的时候.
          }
        }
        board[i][j] = '.'; // ??? 相当于N-Queens里面的path.remove(path.size()-1);
      }
    }
    else {
      return helper(board, i, j+1);
    }
    return false;
  }

  /**
   * 写的真简洁漂亮.
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
      for (int col = j / 3 * 3; col < j / 3 * 3 + 3; ++j) { // 注意这里并不是%.
        if ((row != i || col != j) && board[row][col] == board[i][j]) // 注意这里并不是&&.
          return false;
      }
    }
    return true;
  }
}
