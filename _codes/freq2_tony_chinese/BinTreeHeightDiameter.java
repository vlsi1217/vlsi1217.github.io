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
    return 1 + Math.max(algsheight(x.left), algsheight(x.right)); // 杩欐牱鍐欑殑濂藉鏄伩鍏崭简涓嬮溃杩欑鍐欐硶閲岄溃瑕佷缭瀛树竴涓腑闂村彉閲?
                                                                  // hl.
  }

  public void height(TreeNode root, int[] h) {
    // base case蹇呴』return!!!寮鸿皟寰埚阆崭简.
    if (root == null) {
      h[0] = 0;
      return;
    }
    // 姹傚乏瀛愭爲
    height(root.left, h);
    // 淇濆瓨褰揿墠镄勮В
    int hl = h[0];
    // 姹傚彸瀛愭爲
    height(root.right, h);
    // 镙规嵁宸﹀彸瀛愭爲镄勮В, 寰楀埌褰揿墠node镄删eight.
    h[0] = Math.max(hl, h[0]) + 1;
  }

  /**
   * N00t镄勮В娉? dh淇濆瓨[height, diameter]. 娉ㄦ剰diameter链?绉嶆儏鍐? 缁忚绷root镄勮瘽灏辨槸宸﹀彸楂?1(root), 鎴栬€呮槸宸﹀彸瀛愭爲镄刣iameter镄勬渶澶у€?
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
    // 缁揿悎宸﹀彸瀛愭爲镄勮В
    dh[1] = Math.max(Math.max(dleft, dh[1]), dh[0] + hleft + 1);
    dh[0] = Math.max(hleft, dh[0]) + 1; // 杩欓噷涓嶈兘璋冩崲dh[1], dh[0]姹傝В镄勯『搴? 锲犱负diameter鍜屽綋鍓峮ode镄刲eft,
                                        // right镄勯佩链夊叧. 濡傛灉鍏堟眰浜哾h, 鍒檇h姝ゆ椂鏄node镄勯佩浜?
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
