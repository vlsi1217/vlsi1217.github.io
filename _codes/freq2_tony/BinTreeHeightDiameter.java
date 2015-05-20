package freq2_tony;

/**
 * http://n00tc0d3r.blogspot.com/2013/07/diameter-of-binary-tree.html
 * 
 * @author tzhang
 *
 */
public class BinTreeHeightDiameter {
  public static void main(String[] args) {
    BinTreeHeightDiameter bthd = new BinTreeHeightDiameter();

  }

  // height of this BST (one-node tree has height 0)
  private int algsheight(TreeNode x) {
    if (x == null)
      return -1;
    return 1 + Math.max(algsheight(x.left), algsheight(x.right)); // 这样写的好处是避免了下面这种写法里面要保存一个中间变量:
                                                                  // hl.
  }

  public void height(TreeNode root, int[] h) {
    // base case必须return!!!强调很多遍了.
    if (root == null) {
      h[0] = 0;
      return;
    }
    // 求左子树
    height(root.left, h);
    // 保存当前的解
    int hl = h[0];
    // 求右子树
    height(root.right, h);
    // 根据左右子树的解, 得到当前node的height.
    h[0] = Math.max(hl, h[0]) + 1;
  }

  /**
   * N00t的解法: dh保存[height, diameter]. 注意diameter有3种情况: 经过root的话就是左右高+1(root), 或者是左右子树的diameter的最大值.
   * 
   * @param root
   * @param dh
   */
  public void diameter(TreeNode root, int[] dh) {
    if (root == null) {
      dh[0] = 0;
      dh[0] = 0;
      return;
    }
    // left subtree
    diameter(root.left, dh);
    int hleft = dh[0];
    int dleft = dh[1];
    // right subtree
    diameter(root.right, dh);
    // 结合左右子树的解
    dh[1] = Math.max(Math.max(dleft, dh[1]), dh[0] + hleft + 1);
    dh[0] = Math.max(hleft, dh[0]) + 1; // 这里不能调换dh[1], dh[0]求解的顺序, 因为diameter和当前node的left,
                                        // right的高有关. 如果先求了dh, 则dh此时是该node的高了.
  }

  static class TreeNode {
    int value;
    TreeNode left, right;

    TreeNode(int v) {
      this.value = v;
    }
  }

  public BinTreeHeightDiameter() {
    TreeNode root = new TreeNode(0);
    TreeNode yi = new TreeNode(1);
    TreeNode er = new TreeNode(2);
    TreeNode sa = new TreeNode(3);
    TreeNode si = new TreeNode(4);
    root.left = yi;
    root.right = er;
    er.left = sa;
    er.right = si;
    int[] hei = new int[2];
    diameter(root, hei);
    // height(root, hei);
    // int hei = algsheight(root);
    System.out.println(hei[0] + " " + hei[1]);
  }
}
