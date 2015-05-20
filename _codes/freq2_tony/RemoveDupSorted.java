package freq2_tony;
/**
 * ʵ����û��Ҫ��N00t����д, �����״�. ʵ������ganker�ķ����ͺ�ֱ�ۼ�. ����ʹ��˫ָ�뷨.
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
   * Ganker��˫ָ��󷨺�. ����һ��loop��ά��2��ָ��: idx��¼��ǰ��ЧԪ�صĳ���, i�͸���ɨ��ȥ.
   * @param arr
   * @return
   */
  private static int removeDupSortI(int[] arr) {
    if (arr == null || arr.length==0) {
      return 0;
    }
    int idx = 1;
    for (int i = 1; i < arr.length; ++i) {
      if (arr[i] != arr[i-1]) {  // ��������ЧԪ��, ��update idx, ��inplace��ֵ.
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
