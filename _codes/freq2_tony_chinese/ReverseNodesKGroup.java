package freq2_tony;

/**
 * Reverse Linked List in k-Groups. 是Swap Nodes Pair的一般情况. For example: Given this linked list:
 * 1->2->3->4->5, For k = 2, you should return: 2->1->4->3->5 For k = 3, you should return:
 * 3->2->1->4->5
 * 
 * 像这种题目, 觉得N00t的分析比Ganker的详细. 所以主要参考的N00t的解法
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
   * 这是处理reverse Nodes K group的subroutine. 例如已知: 1-2-3-4-5, start = 2, end = 4, 则变成1-4-3-2-5.
   * 即reverse [2,3,4] 这个range的list. 如果start=1的话怎么操作root呢? 简单: 加一个dummy node接到root. 这样就将root退化为一般node
   * 这种题目还是双指针法.
   * 
   * @param root
   * @param start
   * @param end
   * @return
   */
  public ListNode reverseRange(ListNode root, int start, int end) {
    ListNode dummy = new ListNode(0);
    dummy.next = root;

    // 使用双指针法, 一个前驱节点, 一个当前节点. 还有一个标志int
    ListNode pre = dummy, cur = root;
    int pos = 1;

    // 开始之前先定位到start, 所以一直loop直到pos == start, 且cur是有效的.
    while (pos < start && cur != null) {
      pre = cur;
      cur = cur.next;
      pos++;
    }

    // 定位好之后就可以开始交换了. 这里是这个method的关键.
    while (pos < end && cur != null) {
      ListNode tmp = cur.next.next; // N00t用的是nt: node temp.
      cur.next.next = pre.next; // cur;
      pre.next = cur.next;
      cur.next = tmp; // pre.next.next = tmp;
      pos++;
    }
    
    return dummy.next;
  }
}
