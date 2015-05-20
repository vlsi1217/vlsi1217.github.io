package freq2_tony;
/**
 * 终于理解了N00t的解法. 分析的过程在黑色本子里面. 很有意思. 不过我也只是用了分情况讨论的老方法而已. 
 * 要学着用Stanford Algs里面的多种分析方法. 证明的过程真漂亮. 喜欢random walk的E[Tn] = 0.5
 * 
 * key在于那个作为返回值的boundary[0]最后赋值为newEnd. 使得backward的getVolumn可以剪枝. 不过O()不变.
 * @author tzhang
 *
 */
public class TrappingRainBrute {

//  private final int[] A; // This is the trap array.
  private int getVolume(int[] A, boolean isForward, int end, int[] boundary) {
    // skip first zero. 因为左起点是0的都没有用, 挡不住水. 所以要跳过他们.找到第一个非零的trap
    int cur = 0;
    if (!isForward)  cur = A.length-1;
    while (cur != end && A[cur] == 0) 
      cur += (isForward?1 : -1);
    
    // 从边界撸到另一个边界
    int vol = 0, newEnd = cur, tempSum = 0;
    for (int i = cur; i != end; i += (isForward?1:-1) ) {
      if (A[i] >= A[newEnd]) {  // 正常ending
        vol += tempSum;
        // reset? 因为下一个开始到来. 注意其实一共最多只有2段. 只是下一个开始可能会合并到前一个.
        tempSum = 0;
        newEnd = i;
      } else {
        tempSum += (A[newEnd] - A[i]);  // 推进cur的过程.
      }
    }
    
    boundary[0] = newEnd;
    return vol;
  }
  
  public int trap(int[] A) {
    int[] boundary = new int[1];
    int vol = getVolume(A, true, A.length-1, boundary);
    vol += getVolume(A, false, boundary[0]-1, boundary);  // 其实backward的时候的4th param没用.
    return vol;
  }
  
  public TrappingRainBrute(){
//    int[] A = new int[] {2, 1, 0, 1};
    int[] A = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
    int res = trap(A);
    System.out.println("result: " + res);
  }
  
  public static void main(String[] args) {
    TrappingRainBrute trb = new TrappingRainBrute();
  }
}
