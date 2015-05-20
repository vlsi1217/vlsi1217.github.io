package freq2_tony;

import java.util.*;

public class PathSum {
  private static class TreeNode {
    int value;
    TreeNode left, right;

    TreeNode(int v) {
      this.value = v;
      left = right = null;
    }
  }

  /**
   * path sum I: �ж��Ƿ����path sum.
   * 
   * @param root
   * @param sum
   * @return
   */
  private boolean hasPathSumI(TreeNode root, int sum) {
    if (root == null)
      return false;
    if (root.left == null && root.right == null)
      return (sum == root.value);
    return hasPathSumI(root.left, sum - root.value)
        || hasPathSumI(root.right, sum - root.value);
  }

  /**
   * Path sum II: �����е���sum��path. ʹ�õĻ���ͨ�õĵݹ�ģ��. by Ganker
   * 
   * @param root
   * @param sum
   * @return
   */
  private List<List<Integer>> pathSumII(TreeNode root, int sum) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if (root == null)
      return result;
    dfs(root, sum, new ArrayList<Integer>(), result);
    return result;
  }

  /**
   * Path sum II�ĸ�������. ��dfs() method. ע��if(root.value == sum) ���治Ӧ�ü��� return; // �����return���õ�: [[5,
   * 4, 11, 2], [5, 4, 8, 9, 4, 5]]
   * 
   * @param root
   * @param sum
   * @param path
   * @param res
   */
  private void dfs(TreeNode root, int sum, List<Integer> path,
      List<List<Integer>> res) {
    if (root == null)
      return;

    path.add(root.value);
    if (root.left == null && root.right == null) {
      if (root.value == sum) {
        res.add(new ArrayList<Integer>(path));
        // return; // �����return���õ�: [[5, 4, 11, 2], [5, 4, 8, 9, 4, 5]]
      }
    }
    dfs(root.left, sum - root.value, path, res);
    dfs(root.right, sum - root.value, path, res);
    path.remove(path.size() - 1);
  }

  // ----------------------------------------------------------->
  public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    if (root == null)
      return res;
    ArrayList<Integer> item = new ArrayList<Integer>();
    item.add(root.value);
    helper(root, sum - root.value, item, res);
    return res;
  }

  private void helper(TreeNode root, int sum, ArrayList<Integer> item,
      ArrayList<ArrayList<Integer>> res) {
    if (root == null)
      return;
    if (root.left == null && root.right == null && sum == 0) {
      res.add(new ArrayList<Integer>(item)); // ע������Ganker��û��copyһ��path, �ټ�. ΪʲôN00t��copy���Ǵ����?
      return; // �Ӳ���return���������. Ϊʲô��?
    }
    if (root.left != null) {
      item.add(root.left.value);
      helper(root.left, sum - root.left.value, item, res);
      item.remove(item.size() - 1);
    }
    if (root.right != null) {
      item.add(root.right.value);
      helper(root.right, sum - root.right.value, item, res);
      item.remove(item.size() - 1);
    }
  }

  public PathSum() {
    TreeNode a = new TreeNode(5);
    TreeNode b1 = new TreeNode(4);
    TreeNode b2 = new TreeNode(8);
    TreeNode c1 = new TreeNode(11);
    TreeNode c2 = new TreeNode(13); // (9);
    TreeNode c3 = new TreeNode(4);
    TreeNode d1 = new TreeNode(7);
    TreeNode d2 = new TreeNode(2);
    TreeNode d3 = new TreeNode(5); // (1);
    a.left = b1;
    a.right = b2;
    b1.left = c1;
    b2.left = c2;
    b2.right = c3;
    c1.left = d1;
    c1.right = d2;
    c3.right = d3;

    // boolean res = hasPathSumI(a, 22);
    // System.out.println(res);
    // List<ArrayList<Integer>> allpath = pathSum(a, 22);
    List<List<Integer>> allpath = pathSumII(a, 22);
    System.out.println(allpath);
  }

  public static void main(String[] args) {
    PathSum ps = new PathSum();
  }
}
