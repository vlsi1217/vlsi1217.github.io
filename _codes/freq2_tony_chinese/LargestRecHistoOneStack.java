package freq2_tony;

import java.util.Stack;

/**
 * 这里用的abcbc的解法. 相对容易懂一些. 最主要是理解. 不要想着光靠调试出来!
 * http://blog.csdn.net/abcbc/article/details/8943485
 * @author tzhang
 *
 */
public class LargestRecHistoOneStack {
  public int largestRecOneStack(int[] height) {
    int area = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < height.length; i++) {
      if (stack.empty() || height[stack.peek()] < height[i]) {
        stack.push(i);
      } else {
        int start = stack.pop();
        int width = stack.isEmpty()? 0 : i - stack.peek() -1;
        area = Math.max(area, height[start] * width);
        i--;       // 太帅了!
      }
    }
    while (!stack.isEmpty()) {
      int start = stack.pop();
      int width = stack.isEmpty()? height.length : height.length - stack.peek() - 1;
      area = Math.max(area, height[start] * width);
    }
    return area;
  }
  
//algs client
 public int largestRectangleArea(int[] height) {  
   return largestRecOneStack(height);  
 }  
 
 public LargestRecHistoOneStack(){
   int[] A = new int[] {2, 7, 5, 6, 4};
   int res = largestRectangleArea(A);
   System.out.println("I got " + res);
 }
 
 public static void main(String[] args) {
   LargestRecHistoOneStack lros = new LargestRecHistoOneStack();
   lros.getClass();
 }
}
