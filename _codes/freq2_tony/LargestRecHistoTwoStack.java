package freq2_tony;

import java.util.Stack;

/**
 * recursion������ת��stack����, �����������, ��Ȼ���ǻ�SOF��.
 * ��1:
 * �����õ�N00t��2��stack�ķ���. ��ϸ����ʲô��˼.
 * ���� "if(operator || ...)"��evaluate�ǵ���һ�������˾ͽ����ignore �����param
 * http://stackoverflow.com/questions/7925479/if-argument-evaluation-order
 * 
 * ��2:
 * 2��stack�Ľⷨ����abcbc������, �ҿ������Ҳ������д��. ������ͼ��׶�.
 * http://blog.csdn.net/abcbc/article/details/8943485
 * @author tzhang
 *
 */
public class LargestRecHistoTwoStack {
  public int largestRect(int[] height) {  
    int area = 0;  
    // stack to store the indices of left boundary  
    // left boundary is the last height that is not lower than the current one  
    Stack<Integer> left = new Stack<Integer>();  
    Stack<Integer> index = new Stack<Integer>();  
    int cur = 0;  
    while (cur < height.length) {  
      if (cur == 0 || height[cur] > height[index.peek()]) {  
        left.push(cur);  
        index.push(cur);  
      } else if (height[cur] < height[index.peek()]) {  
        int last;  
        do {  
          last = left.pop();  
          System.out.println(area);
          area = Math.max(area, height[index.pop()] * (cur - last));  
        } while (!left.isEmpty() && height[cur] < height[index.peek()]);  
        left.push(last);  
        index.push(cur);  
      }  
      cur++;  
    }  
    // pop out values in index and left and calculate their areas  
    while (!index.isEmpty() && !left.isEmpty()) {  
//      System.out.println(index.peek() + " " + left.peek());
      int areaTemp = height[index.pop()] * (height.length - left.pop());
      
      area = Math.max(area, areaTemp );  
//      System.out.println(areaTemp);
    }  
    
    return area;  
  }  
  
  
  // O(n) using two stacks
  // http://blog.csdn.net/abcbc/article/details/8943485 ������ͱ�N00t�ĺö�, ������ͼ����.
  public int largestRectabcbc(int[] height) {
    int area = 0;
    Stack<Integer> heightStack = new Stack<>();
    Stack<Integer> indexStack = new Stack<>();
    for (int i = 0; i < height.length; i++) {
      if (heightStack.empty() || heightStack.peek() <= height[i]) {
        heightStack.push(height[i]);
        indexStack.push(i);
      } else if (heightStack.peek() > height[i]) {
        int j;
        do {
          j = indexStack.pop();
          int currArea = (i - j) * heightStack.pop();
          area = Math.max(currArea, area);
        } while (!heightStack.empty() && (heightStack.peek() > height[i])); 
        heightStack.push(height[i]);
        indexStack.push(j);
      }
    }
    while (!heightStack.empty()) {
      int currArea = (height.length - indexStack.pop()) * heightStack.pop();
      if (currArea > area) {
        area = currArea;
      }
    }
    return area;
  }

  
//algs client
 public int largestRectangleArea(int[] height) {  
   return largestRectabcbc(height);  
 }  
 
 public LargestRecHistoTwoStack(){
   int[] A = new int[] {2, 1, 5, 6, 2, 3};
   int res = largestRectangleArea(A);
   System.out.println("I got " + res);
 }
 
 public static void main(String[] args) {
   LargestRecHistoTwoStack lrhs = new LargestRecHistoTwoStack();
   lrhs.getClass();
 }
}