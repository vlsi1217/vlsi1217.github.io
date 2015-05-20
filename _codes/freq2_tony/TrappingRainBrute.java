package freq2_tony;
/**
 * ���������N00t�Ľⷨ. �����Ĺ����ں�ɫ��������. ������˼. ������Ҳֻ�����˷�������۵��Ϸ�������. 
 * Ҫѧ����Stanford Algs����Ķ��ַ�������. ֤���Ĺ�����Ư��. ϲ��random walk��E[Tn] = 0.5
 * 
 * key�����Ǹ���Ϊ����ֵ��boundary[0]���ֵΪnewEnd. ʹ��backward��getVolumn���Լ�֦. ����O()����.
 * @author tzhang
 *
 */
public class TrappingRainBrute {

//  private final int[] A; // This is the trap array.
  private int getVolume(int[] A, boolean isForward, int end, int[] boundary) {
    // skip first zero. ��Ϊ�������0�Ķ�û����, ����סˮ. ����Ҫ��������.�ҵ���һ�������trap
    int cur = 0;
    if (!isForward)  cur = A.length-1;
    while (cur != end && A[cur] == 0) 
      cur += (isForward?1 : -1);
    
    // �ӱ߽�ߣ����һ���߽�
    int vol = 0, newEnd = cur, tempSum = 0;
    for (int i = cur; i != end; i += (isForward?1:-1) ) {
      if (A[i] >= A[newEnd]) {  // ����ending
        vol += tempSum;
        // reset? ��Ϊ��һ����ʼ����. ע����ʵһ�����ֻ��2��. ֻ����һ����ʼ���ܻ�ϲ���ǰһ��.
        tempSum = 0;
        newEnd = i;
      } else {
        tempSum += (A[newEnd] - A[i]);  // �ƽ�cur�Ĺ���.
      }
    }
    
    boundary[0] = newEnd;
    return vol;
  }
  
  public int trap(int[] A) {
    int[] boundary = new int[1];
    int vol = getVolume(A, true, A.length-1, boundary);
    vol += getVolume(A, false, boundary[0]-1, boundary);  // ��ʵbackward��ʱ���4th paramû��.
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
