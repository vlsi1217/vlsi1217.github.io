package freq2_tony;
/**
 * 实际上没必要像N00t那样写, 很容易错. 实际上用ganker的方法就很直观简单. 还是使用双指针法.
 * @author tzhang
 *
 */
public class RemoveDupSorted {
  public static void main(String[] args) {
    int[] array = {1, 1, 1, 2, 2, 3, 4, 5};
//    int resI = removeDupSortI(array);
//    System.out.println(resI);
//    for (int i = 0; i < resI; ++i) {
//      System.out.print(array[i] + " ");
//    }
    
    int resII = removeDupSortII(array);
    System.out.println(resII);
    for (int i = 0; i < resII; ++i) {
      System.out.print(array[i] + " ");
    }
  }
  
  /**
   * Ganker的双指针大法好. 即在一遍loop中维护2个指针: idx记录当前有效元素的长度, i就负责扫下去.
   * @param arr
   * @return
   */
  private static int removeDupSortI(int[] arr) {
    if (arr == null || arr.length==0) {
      return 0;
    }
    int idx = 1;
    for (int i = 1; i < arr.length; ++i) {
      if (arr[i] != arr[i-1]) {  // 当发现有效元素, 才update idx, 并inplace赋值.
        arr[idx] = arr[i];
        idx++;
      }
    }
    return idx;
  }
  
  private static int removeDupSortII(int[] arr) {
    if (arr == null || arr.length == 0)
      return 0;
    int idx = 1;
    int cnt = 1;
    for (int i = 1; i < arr.length; ++i) {
      if (arr[i] == arr[i-1]) {
        cnt++;
        if (cnt > 2) {
          continue;
        }
      }
      else if (arr[i] != arr[i-1]) {
        cnt = 1;
      }
      arr[idx++] = arr[i];
    }
    return idx;
  }
}
