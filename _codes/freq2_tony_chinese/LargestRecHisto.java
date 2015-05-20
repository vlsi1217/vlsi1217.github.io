package freq2_tony;
/**
 * n00t在开始TrappingRain的stack解法. 即递减栈做法之前先练了一下手. 就是用的Largest Rectangle in histogram
 * http://n00tc0d3r.blogspot.com/2013/03/largest-rectangle-in-histogram.html
 * 
 * 最喜欢recursion了.
 * @author tzhang
 *
 */
public class LargestRecHisto {
  // recursion algs to find the largest rectangle
  private int largestRect(int[] height, int l, int r) {  
    // base condition, 注意返回值
    if (l>=r) return 0;  
    // 初始化min和minId
    int min = height[l], minId = l;  
    // find the min and its index  
    for (int i=l+1; i<r; ++i) {  
      if (height[i] < min) {  
        min = height[i];  
        minId = i;  
      }  
    }  
    int minRL = min*(r-l);
    int maxRL = Math.max(largestRect(height, l, minId), largestRect(height, minId+1, r));
    
    return Math.max(minRL,  maxRL);
  }  
    
  // algs client
  public int largestRectangleArea(int[] height) {  
    return largestRect(height, 0, height.length);  
  }  
  
  public LargestRecHisto(){
    int[] A = new int[] {2, 1, 3, 7, 3, 1};
    int res = largestRectangleArea(A);
    System.out.println(res);
  }
  
  public static void main(String[] args) {
    LargestRecHisto lrh = new LargestRecHisto();
  }
}
