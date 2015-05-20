package freq2_tony;

import freq2_tony.BinTreeHeightDiameter.TreeNode;

/**
 * http://articles.leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-i.html
 * Top-down/Bottom-up的区别还是Javayu的解释最清晰, 其实就是recursion的walk down/up处理的顺序:
 * http://wp.javayu.me/2014/02/lowest-common-ancestor-of-a-binary-tree/
 * 
 * @author tzhang
 *
 */
public class LCA1337 {
  public static void main(String[] args) {
    LCA1337 lcai = new LCA1337();
  }

  static class TreeNode {
    int value;
    TreeNode left, right;

    TreeNode(int v) {
      this.value = v;
    }
  }

  /**
   * LCA Top down的helper method
   * 
   * @param root
   * @param p
   * @param q
   * @return
   */
  private static int countMatchesPQ(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null)
      return 0;
    int matches =
        countMatchesPQ(root.left, p, q) + countMatchesPQ(root.right, p, q);
    if (root == p || root == q)
      return 1 + matches;
    else
      return matches;
  }

  /**
   * 1337所讲的Top-Down approach来找LCA
   * 
   * @param root
   * @param p
   * @param q
   * @return
   */
  public static TreeNode lcaTD(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || p == null || q == null)
      return null;
    if (root == p || root == q)
      return root;
    int totalMatches = countMatchesPQ(root.left, p, q);
    if (totalMatches == 1)
      return root;
    else if (totalMatches == 2)
      return lcaTD(root.left, p, q);
    else
      return lcaTD(root.right, p, q);
  }

  /**
   * 加深理解什么是Bottom-up!
   * 
   * @param root
   * @param p
   * @param q
   * @return
   */
  public static TreeNode lcaBU(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null)
      return null;
    if (root == p || root == q)
      return root;
    TreeNode l = lcaBU(root.left, p, q);
    TreeNode r = lcaBU(root.right, p, q);
    if (l != null && r != null) {
      return root; // nodes are each on a separate branch
    }

    // either one node is on one branch,
    // or none was found in any of the branches
    return l != null ? l : r;
  }

  public LCA1337() {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    root.left = yi;
    root.right = er;
    er.left = sa;
    er.right = si;
    TreeNode res = lcaBU(root, sa, si);
    System.out.println(res.value);
  }
}
