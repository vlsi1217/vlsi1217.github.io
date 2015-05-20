package freq2_tony;
/**
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * ����������˼, ˼�����ASIC����ѧ���Ǹ��������ٲ�����ߵ�0/1. ���ǻ�û�����ô�ô���д����, ����KMP��DFA��Ȼ������ô�򵥵�д����. ����⻹��N00t����Ư��,
 * �����굽hanoi tower!
 * 
 * @author tzhang
 *
 */

import java.util.*;
public class Graycode {
  public static List<Integer> grayN00t(int n) {
    List<Integer> res = new ArrayList<>();
    if (n < 0)  return res;
    res.add(0);
    for (int i = 0; i < n; ++i){
      int flipper = 1 << i ;
      for (int k = res.size()-1; k >=0; k--) {
        int tmp = res.get(k) | flipper;
        res.add(tmp);
      }
    }
    return res;
  }
  
  /**
   * N00t, ʲô��˼?
   * @param k
   * @return
   */
  public static int kthGrayCode(int k) {
    return k ^ (k>>1);
  }
  
  public static void main(String[] args) {
//    List<Integer> res = new ArrayList<>();
//    res = grayN00t(3);
//    for (int i : res) {
//      String bin = Integer.toBinaryString(i);
//      
//      System.out.println(bin);
//    }
    System.out.println(kthGrayCode(3));
  }
}
