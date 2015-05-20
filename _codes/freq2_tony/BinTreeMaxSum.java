package freq2_tony;

import freq2_tony.BinTreeHeightDiameter.TreeNode;

/**
 * 树的题目一般都是recursion. 不过这道题目要注意: 可以是任何path, 有点类似Tree diameter. 即**不是树, 而是图. 或者像Ganker所说: 不是看作有向图,
 * 而是看作无向图**
 * 
 * @author tzhang
 *
 */
public class BinTreeMaxSum {

  public static void main(String[] args) {
    BinTreeMaxSum btms = new BinTreeMaxSum();
  }

  public Data maxsum(TreeNode root) {
    Data d = new Data();
    if (root == null) {
      return d;
    }
    Data l = maxsum(root.left);
    Data r = maxsum(root.right);
    d.path = Math.max(0, Math.max(l.path, r.path) + root.value);  // 当前node 的partial sum: 包含node的话就是加上子树的partial sum. 不包括的话就是0! 这样sum就相当于不经过这个node了.
    d.sum = Math.max(Math.max(l.sum, r.sum), l.path + r.path + root.value); // sum就是一般的写法, 注意什么时候是path, 什么时候是sum.
    return d;
  }

  static class Data {
    int path; // max partial path sum
    int sum; // current max path sum

    Data() {
      path = 0;
      sum = Integer.MIN_VALUE;
    }
  }

  public BinTreeMaxSum() {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    root.left = yi;
    root.right = er;
    er.left = sa;
    er.right = si;
    Data res = maxsum(root);
    System.out.println(res.sum);
  }
}
