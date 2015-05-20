package freq2_tony;

import freq2_tony.BinTreeHeightDiameter.TreeNode;

/**
 * ������Ŀһ�㶼��recursion. ���������ĿҪע��: �������κ�path, �е�����Tree diameter. ��**������, ����ͼ. ������Ganker��˵: ���ǿ�������ͼ,
 * ���ǿ�������ͼ**
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
    d.path = Math.max(0, Math.max(l.path, r.path) + root.value);  // ��ǰnode ��partial sum: ����node�Ļ����Ǽ���������partial sum. �������Ļ�����0! ����sum���൱�ڲ��������node��.
    d.sum = Math.max(Math.max(l.sum, r.sum), l.path + r.path + root.value); // sum����һ���д��, ע��ʲôʱ����path, ʲôʱ����sum.
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
