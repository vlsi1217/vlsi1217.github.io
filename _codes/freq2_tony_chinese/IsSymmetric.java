package freq2_tony;

public class IsSymmetric {
  private class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int value) {
      val = value;
      left = right = null;
    }
  }

  // ---------------------------------------> recursion解法
  private boolean isSymmetric(TreeNode root) {
    if (root == null)
      return false;
    return helper(root.left, root.right);
  }

  /**
   * 判断是否对称就是看左右子树对应的节点: 都为Null就可以, 如果一个null, 一个不是就不对称了. 或者是2个值不等. 如果值相当呢? 并不能return,
   * 因为return就结束recursion了 所以值相等的时候是继续比较下去的.
   * 
   * @param root1
   * @param root2
   * @return
   */
  private boolean helper(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) 
      return true;
    if (root1 == null || root2 == null) 
      return false;
    if (root1.val != root2.val)
      return false;
    return helper(root1.left, root2.right) && helper(root1.right, root2.left);
  }
  
  public IsSymmetric(){
    TreeNode root = new TreeNode(3);
    TreeNode l1 = new TreeNode(1);
    TreeNode r1 = new TreeNode(1);
    root.left = l1;
    root.right = r1;
    System.out.println(isSymmetric(root));
  }
  
  public static void main(String[] args) {
    IsSymmetric is = new IsSymmetric();
  }
}
