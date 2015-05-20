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

  // ---------------------------------------> recursion�ⷨ
  private boolean isSymmetric(TreeNode root) {
    if (root == null)
      return false;
    return helper(root.left, root.right);
  }

  /**
   * �ж��Ƿ�Գƾ��ǿ�����������Ӧ�Ľڵ�: ��ΪNull�Ϳ���, ���һ��null, һ�����ǾͲ��Գ���. ������2��ֵ����. ���ֵ�൱��? ������return,
   * ��Ϊreturn�ͽ���recursion�� ����ֵ��ȵ�ʱ���Ǽ����Ƚ���ȥ��.
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
