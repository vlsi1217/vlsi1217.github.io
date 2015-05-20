package freq2_tony;
/**
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 这道题很有意思, 思想就是ASIC上面学的那个倒过来再补上最高的0/1. 但是还没想过怎么用代码写出来, 就像KMP的DFA居然可以这么简单的写出来. 这道题还是N00t做的漂亮,
 * 还引申到hanoi tower!
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
   * N00t, 什么意思?
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
