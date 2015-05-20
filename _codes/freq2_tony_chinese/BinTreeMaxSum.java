package freq2_tony;

import freq2_tony.BinTreeHeightDiameter.TreeNode;

/**
 * 镙戠殑棰樼洰涓€鑸兘鏄麻ecursion. 涓嶈绷杩欓亾棰樼洰瑕佹敞镒? 鍙互鏄换浣昿ath, 链夌偣绫讳技Tree diameter. 鍗?*涓嶆槸镙? 钥屾槸锲? 鎴栬€呭儚Ganker镓€璇? 涓嶆槸鐪嬩綔链夊悜锲?
 * 钥屾槸鐪嬩綔镞犲悜锲?*
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
    d.path = Math.max(0, Math.max(l.path, r.path) + root.value);  // 褰揿墠node 镄刾artial sum: 鍖呭惈node镄勮瘽灏辨槸锷犱笂瀛愭爲镄刾artial sum. 涓嶅寘鎷殑璇濆氨鏄?! 杩欐牱sum灏辩浉褰扑簬涓岖粡杩囱繖涓猲ode浜?
    d.sum = Math.max(Math.max(l.sum, r.sum), l.path + r.path + root.value); // sum灏辨槸涓€鑸殑鍐欐硶, 娉ㄦ剰浠€涔堟椂链欐槸path, 浠€涔堟椂链欐槸sum.
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
