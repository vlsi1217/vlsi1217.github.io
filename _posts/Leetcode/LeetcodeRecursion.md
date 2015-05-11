title: "leetcode中学习recursion"
date: "2015-02-16 20:17:16"
tags: Leetcode
categories: Interview
description: "Leetcode总结题型."
toc: true
---

>+ N00t大神的recursion出神入化
+ LC中大量的recursion解法
+ recursion的关键在于代码结构, 以及param/return.

## recursion的种类
### 1. 简单的按照结构分的有
#### 1个rec. 以及简单三明治关系.
```
void rec(){
    codeTD;
    rec();
    codeBU;
    }
```
* 这里面的codeTD可以看作是walk DOWN the tree, codeBU理解为walk UP the tree.

#### 1个rec. 但是在for loop里面的话, 会更有意思.
```
void rec(){
    if (lastRow)
        res.add(path + s);
    for(i = 1; i < path.size(); i++){
        rec(path.append, res);
        path.delete;
    }
}
```
* 这在restore IP的N00t的第一个解法中遇到.

#### 1个recursion, 这次是在for each() 括号里面. 更有意思啦
```
List<List<?> segmentRec(String s) {
    // do something
    for ( i ) {
        rest = s.substring(i)
        for (List<?> seg : segmentRec(rest)) {
            // do something
        }
    }
    return result;
}
```
* 这个是在word segment里面用到.

####  2个连续rec call. 有param, 有return
##### 在triangle Path Sum中的N00t的第一个解法.
* 注意这里的minSum既是recursion method的param, 又是param的返回值. 这在recursion中很常用. 可以记录在**触底反弹**的时候不断update这个值. 
* 因为rec有返回值, 而且复制给同一个var(minSum). 因为2个recursion的话, 第二个rec中的param会在第一个rec算完后update. 所以这一套组合拳就能在Traversal tree中不断update. 也就相当于brute force的找所有组合的极值. 
+ 这就体现出recursion的意义: 有目的, 有结构的遍历符合规则的结构的所有path. 例如triangle Path Sum就是想要找DFS path的最小路径. 而不是随便一个乱序的路径. 
```
int DFS(a, sum, minSum) {
  sum += a;  // walking down the tree.
  if (lastRow && sum < minSum)  return sum;
  else {
    minSum = DFS(a, sum, minSum);  // 注意这里的赋值是minSum, 而不是其他var name. 所以跟着的第二条recursion中的minSum才会update.
    minSum = DFS(a, sum, minSum);  // 这里的minSum用于DFS的return.
  }
  return minSum; // walking up the tree.
}
```

##### 还有个很好的例子就是flatten Binary Tree的九章算法模版.
+ recursion中有param不一定必须return. 主要修改就OK了. 
+ 关键在于2个recursion的时候, 第二个recursion计算的root

##### 还有很经典的是Maximum Subarray的divid and conquer解法.
+ 2个连续的rec. 一个左子树, 一个右子树. 然后下面的code是向上走. 即合并的时候做的. 干什么呢? 自然是用来合并左右子树计算得到的结果. [一天一学的Java](http://joycelearning.blogspot.com/2013/10/leetcode-maximum-subarray.html)分析很到位.
    
#### 2个不连续的rec call. 中间有code. 注意N00t和programCreek的一点区别
```Java
Node rec(l, h) {
  mid = lo + (hi-lo)/2;
  leftNode = rec(lo, mid-1);
  middleCode;
  rightNode = rec(mid+1, hi);
  return root;
}
```
* 这在Sorted list to Binary Tree里面用到了.
#### 2个不连续的recur call. 中间有code, 而且使用的return.
* 经典案例: Recover Binary Search Tree --- N00t的方法. 这才是真正的使用in-order. 注意每一次的rec call里面的param都变化. 而不是像dummy in-order那样对称.
```
private TreeNode inorder(TreeNode root, TreeNode[] nodes, TreeNode pre) {  
   if (root == null) return pre;  
   // left subtree  
   TreeNode last = inorder(root.left, nodes, pre);  
   // visit  
   if (last != null && root.val < last.val) {  
     // some code
   }  
   // right subtree  
   return inorder(root.right, nodes, root);  
 }  
```

#### 3个rec call, 中间有code, 有param, 有返回.
```Java
DFS(tri, row, col, HashMap){
    // base condition
    return min;
    if(Map.contains())
        min+= Math.min(DFS(Hashmap), DFS(Hashmap));
    else 
        min+= Math.min(map.get(), DFS(HashMap));
    map.put(row, min);
    return min;
}
```
* 在Triangle Path Sum的DFS recursion解法里面用到. 注意这里的Math.min()里面的第二个DFS的map已经在在第一个DFS中改变了.
* 记住: java永远是call by value. 
    * 所以primitive 的话不会改变. 于是必须return. 所以在Triangle Path Sum中的第一个解法必须return, 然后第二个recursion的minSum才是改动过的.
    * 如果是object, call-by-value的是地址. 所以可以改动. 于是不需要return. 就如Triangle Path Sum里面的DFS解法的map. 这里的map是重复使用(update)的.
    * 其实这里就是段公子说的用HashMap来代替array保存DP结果更有通用性. array其实是最简单的HashMap, 即key是index, value则是a[i].

---

### 2. 按照DFS中使用的分
> + 根据[leetcode summary的博客](http://leetcodesummary.blogspot.com/2013/10/leetcode-treerecursion.html)
+ 还有就是CS的关键: **抽象抽象再抽象**. 并不一定要真的是一个Tree放在那里让你traverse. 而是题目可以按照tree的结构分析分解和理解. 比如说word segment, word ladder, Graph, 乃至2-SAT->SCC. 等等.

#### 例如NP问题: Subsets I/II

#### NP问题: Permutation I/II


    