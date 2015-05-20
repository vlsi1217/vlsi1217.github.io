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
    return 1 + Math.max(algsheight(x.left), algsheight(x.right)); // ����д�ĺô��Ǳ�������������д������Ҫ����һ���м����:
                                                                  // hl.
  }

  public void height(TreeNode root, int[] h) {
    // base case����return!!!ǿ���ܶ����.
    if (root == null) {
      h[0] = 0;
      return;
    }
    // ��������
    height(root.left, h);
    // ���浱ǰ�Ľ�
    int hl = h[0];
    // ��������
    height(root.right, h);
    // �������������Ľ�, �õ���ǰnode��height.
    h[0] = Math.max(hl, h[0]) + 1;
  }

  /**
   * N00t�Ľⷨ: dh����[height, diameter]. ע��diameter��3�����: ����root�Ļ��������Ҹ�+1(root), ����������������diameter�����ֵ.
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
    // ������������Ľ�
    dh[1] = Math.max(Math.max(dleft, dh[1]), dh[0] + hleft + 1);
    dh[0] = Math.max(hleft, dh[0]) + 1; // ���ﲻ�ܵ���dh[1], dh[0]����˳��, ��Ϊdiameter�͵�ǰnode��left,
                                        // right�ĸ��й�. ���������dh, ��dh��ʱ�Ǹ�node�ĸ���.
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
