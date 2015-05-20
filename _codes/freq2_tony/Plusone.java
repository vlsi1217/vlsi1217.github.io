package freq2_tony;
/**
 * Given a number represented as an array of digits, plus one to the number.
 * @author tzhang
 *
 */
public class Plusone {
  /**
   * ��Ȼ��һ������Ŀ, ����Ganker����Google��ʱ������.
   * @param num
   * @return
   */
  private int[] plusOneN00t(int[] num) {
    int len = num.length;
    for (int i = len-1; i>=0; --i) {
      if (num[i] != 9) {
        ++num[i];
        return num;
      }
      else {
        num[i] = 0;
      }
    }
    // if finished for but not return, it means all 9999.
    int[] result = new int[len+1];
    result[0] = 1;
    return result;
  }
  
  /**
   * plus one����չ��: ����ӵ���һ��int. ��Ҫ�������н�λ.
   * @param digits
   * @param x
   * @return
   */
  public int[] plusInt(int[] digits, int x) {  
    int n = digits.length;  
    for (int i=n-1; i>=0; --i) {  
      if (x == 0) return digits;  
      int d = x % 10; // get last digit of x
      x /= 10; // remove last digit from x
      digits[i] += d;
      if (digits[i] >= 10) { // has carry
        digits[i] -= 10;
        ++x; // pass on carry
      }
    }
    // ����ߵ�����, ˵��carryû����. calculate length of x
    int m = 0, y = x;
    while (y > 0) {
      ++m;  y /= 10;
    }
    // create a longer array
    int[] result = new int[n+m];  
    System.arraycopy(digits, 0, result, m, n);
    for (int i=m-1; i>=0; --i) {  
      result[i] = x % 10;  
      x /= 10;
    }  
    return result;  
  }  
  
  public Plusone(){
    int[] a = {3};
    for(int i : plusInt(a, 17)){
      System.out.print(i);
    }
  }
  public static void main(String[] args) {
    Plusone po = new Plusone();
  }
}
