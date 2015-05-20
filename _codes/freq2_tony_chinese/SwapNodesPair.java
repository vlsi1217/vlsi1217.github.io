package freq2_tony;
/**
 * 这里觉得N00t的方法比Ganker的好.
 * @author tzhang
 *
 */
public class SwapNodesPair {
  static class ListNode {
    int value;
    ListNode next;

    ListNode(int v) {
      this.value = v;
      this.next = null;
    }
  }
  /**
   * N00t的方法. 其实LinkedList操作就那几个, 但是有个trick, 凡是要改变root的method, 都见一个dummy root接到root.
   * 就可以将root化解为一般的中间node.
   * 
   * @param root
   * @return
   */
  private ListNode swapPairs(ListNode root) {
    ListNode dummy = new ListNode(0);
    dummy.next = root;
    ListNode cur = dummy;

    while (cur.next != null && cur.next.next != null) {
      ListNode tmp = cur.next;
      cur.next = cur.next.next;
      cur = cur.next;
      tmp.next = cur.next;
      cur.next = tmp;
      cur = cur.next;
    }

    return dummy.next;
  }
}
