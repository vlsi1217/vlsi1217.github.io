package freq2_tony;

/**
 * Reverse Linked List in k-Groups. ��Swap Nodes Pair��һ�����. For example: Given this linked list:
 * 1->2->3->4->5, For k = 2, you should return: 2->1->4->3->5 For k = 3, you should return:
 * 3->2->1->4->5
 * 
 * ��������Ŀ, ����N00t�ķ�����Ganker����ϸ. ������Ҫ�ο���N00t�Ľⷨ
 * http://n00tc0d3r.blogspot.com/2013/05/reverse-linked-list.html
 * 
 * @author tzhang
 *
 */
public class ReverseNodesKGroup {
  static class ListNode {
    int value;
    ListNode next;

    ListNode(int v) {
      this.value = v;
      this.next = null;
    }
  }

  /**
   * ���Ǵ���reverse Nodes K group��subroutine. ������֪: 1-2-3-4-5, start = 2, end = 4, ����1-4-3-2-5.
   * ��reverse [2,3,4] ���range��list. ���start=1�Ļ���ô����root��? ��: ��һ��dummy node�ӵ�root. �����ͽ�root�˻�Ϊһ��node
   * ������Ŀ����˫ָ�뷨.
   * 
   * @param root
   * @param start
   * @param end
   * @return
   */
  public ListNode reverseRange(ListNode root, int start, int end) {
    ListNode dummy = new ListNode(0);
    dummy.next = root;

    // ʹ��˫ָ�뷨, һ��ǰ���ڵ�, һ����ǰ�ڵ�. ����һ����־int
    ListNode pre = dummy, cur = root;
    int pos = 1;

    // ��ʼ֮ǰ�ȶ�λ��start, ����һֱloopֱ��pos == start, ��cur����Ч��.
    while (pos < start && cur != null) {
      pre = cur;
      cur = cur.next;
      pos++;
    }

    // ��λ��֮��Ϳ��Կ�ʼ������. ���������method�Ĺؼ�.
    while (pos < end && cur != null) {
      ListNode tmp = cur.next.next; // N00t�õ���nt: node temp.
      cur.next.next = pre.next; // cur;
      pre.next = cur.next;
      cur.next = tmp; // pre.next.next = tmp;
      pos++;
    }
    
    return dummy.next;
  }
}
