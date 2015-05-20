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
   * path sum I: 判断是否存在path sum.
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
   * Path sum II: 求所有等于sum的path. 使用的还是通用的递归模版. by Ganker
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
   * Path sum II的辅助函数. 即dfs() method. 注意if(root.value == sum) 里面不应该加上 return; // 如果有return则会得到: [[5,
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
        // return; // 如果有return则会得到: [[5, 4, 11, 2], [5, 4, 8, 9, 4, 5]]
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
      res.add(new ArrayList<Integer>(item)); // 注意这里Ganker并没有copy一个path, 再加. 为什么N00t不copy就是错的呢?
      return; // 加不加return都不会出错. 为什么呐?
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
