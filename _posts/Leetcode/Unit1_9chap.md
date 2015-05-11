title: "Unit 1 九章算法课: 学习/做题思路"
date: "2015-05-06 20:17:16"
tags: [Leetcode, 九章]
categories: Interview
description: "A collection of Hello World applications from helloworld.org."
toc: true
---

> + 这节课我miss了, 所以只是放上对PPT的理解.
  + 参考的blog是Shuatiblog.com: [link](http://www.shuatiblog.com/blog/2014/06/12/NineChap-Permutation/)

## strStr说开去
* 先看到这种substring match的问题就想到是不是有现成的算法啊: 有啊:
    * KMP -> O(n)
    * RB hash
    * BM 首尾搜索
* 不过这里是很简单的情况, 最好先写个简单的看出我基本功的可以用的code
    * 为了不失一般性, 先判断base case. 即太短, sub过长, etc.
    * 然后就可以假定我们的needle是远小于haystack的长度. 然后就是Ganker在substring concatenation那种, 在Haystack里面traverse所有长度为needle.length()的substring, 然后另一个指针对比needle和substring.

## NP搜索模版: 从Permutation说开去
> + code monkey说: "Permutation problem provides you a list of items, your task is to build, validate and return a somewhat combination of these items. The template is to sort the input first, and add **qualified** item 1 by 1"
  + 一般这个helper method (或者叫dfs), 都有个控制进入下一步的控制方式, 例如Subset的helper用pos, Permutation用boolean[] used, 等等.
  + 先写一下这个模版:
  
  
```
public List<List<Integer>> Search(int[] A) {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    if (A==null || A.length()==0)
        return ans;
    Arrays.sort(A);
    dfs(A, new boolean[A.length()], new List<Integer>(), ans);
    return ans;
}

private static void dfs(int[] A, boolean[] used, List<Integer> path, List<List<Integer>>res) {
    if (path.length() == A.length()) {
        res.add(new ArrayList<Integer>(path))
    }
    for (int i = 0; i < A.length(); i++) {
        if (i > 0 && !used[i-1] && A[i]==A[i-1])  continue;
        if (used[i])  continue;
        path.add(A[i]);
        dfs(A, used, path, res);
        path.remove(path.size()-1);
        used[i] = false;  // 记得recursion一定要恢复现场. 即call stack回退之后的状态要和进入之前一样. 可以看看Ganker的解释.
    }
}
```
  

### Subset

### Permutation
* 这里通过boolean[] used, 来处理duplicate. 
* 例子：[1 2 3 4 4 4 5 6 7 8]. 这个例子via [CMUyu](http://www.cnblogs.com/yuzhangcmu/p/4141085.html)
    + 444这个的选法只能:4, 44, 444连续这三种选法. 即是说: 只对第一个4做递归调用. 然后对于后面2个4就不用递归了, 因为第一个4已经将这3种选法保存到result里面了.
* 当然, Permutation在工作中的代码时尽量要避免的, 所以follow up很有可能是要求用iteration来解. 见Code ganker. 或者下面这个Next Permutation.

### Combination
* 典型的模版套用

### Combination Sum


### Letter Combination of a Phone number

### Palindrome Partitioning

### Restore IP Address

### 8 queen
* 如何选取valid subproblem.

### Coin 