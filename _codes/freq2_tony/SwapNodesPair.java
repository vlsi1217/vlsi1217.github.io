package freq2_tony;
/**
 * �������N00t�ķ�����Ganker�ĺ�.
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
   * N00t�ķ���. ��ʵLinkedList�������Ǽ���, �����и�trick, ����Ҫ�ı�root��method, ����һ��dummy root�ӵ�root.
   * �Ϳ��Խ�root����Ϊһ����м�node.
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
