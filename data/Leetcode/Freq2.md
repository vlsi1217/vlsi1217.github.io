# Leetcode Freq 2


标签（空格分隔）： leetcode

---
[TOC]
## 20141228
### Populating Next Right Pointers in Each Node II
* 
### Binary Tree Maximum Path Sum
### Next Permutation
### First Missing Positive
### Largest Rectangle in Histogram
### Scramble String
### Interleaving String

## 20141226
### Reverse Nodes in k-Group
* 先做Swap Nodes in Pairs. 注意Ganker提到的, list常用的技巧就是遇到需要修改root的题目一般都是接一个空的node到root, 这样就把root退化为一般的中间node.
* 这题主要参考[N00t的解法](http://n00tc0d3r.blogspot.com/2013/05/reverse-linked-list.html). 即Invariant是2个node, keep track这2个node. 头脑要清晰. 在写之前想好算法.
* N00t里面这个reverse list的时候的思路超级清晰. 想想为什么是这样写. **关键**在于
> pre, cur是不变的[以N00t的例子为例, 到结束的时候, 实际上pre还是指向1, cur还是指向2], 只改变pre.next, cur.next. 以及这2个next之间的link.
```
// 定位好之后就可以开始交换了. 这里是这个method的关键.
    while (pos < end && cur != null) {
      ListNode tmp = cur.next.next; // N00t用的是nt: node temp.
      cur.next.next = pre.next; // cur;
      pre.next = cur.next;
      cur.next = tmp; // pre.next.next = tmp;
      pos++;
    }
```

### Trapping Rain Water
### Permutations I/II
* NP问题的做法基本都是用Backtracking. 想象这里和Combination有什么区别呢? 
    * Ans: 这里子问题的条件变化了. Combination里面是helper(,i+1,). 这里是每次在loop里面使能used[i] = true, helper(used[i]). 这样就能缩小到下一个子问题. 
    * 这很重要. 因为recursion就是要一步步进入子问题, 解决, 跳出递归, 到保存到stack的以前的现场. 所以ganker也强调了这里要在helper()后面ehui'fu恢复现场, 即list.remove(list.size()-1), 以及初始化used[i]. 即used[i] = false.
* II就是加入了一个条件: 如果是{1,2,1} 呢? 要避免112, 112出现2次的情况, 就要在dfs之前先判断是否和上一个num重复(**因为num已经sort过了). 
* [评论的一个follow up: link](http://blog.csdn.net/linhuanmars/article/details/21569031). 其实很简单, 也是加入一个判断是否和上一个item里面最后一个数相等, 若是就continue.

### Rotate Image

### Text Justification
* 考察基本功的好题目, 注意题目的各个细节. 参考的ganker的解. 
    1. 什么是last呢? 即本行的开头, 什么是i呢?下行的开头. 什么是i-last-1呢? 就是该行的间隔个数. 例如该行如果可以放5个单词, 开头的last是4, 下一行开头是9. 那么9-1就是该行的结尾, 9-1-4既是该行有4个间隔. 
    2. 为什么下一步循环的时候判断`if(j<i-1)`呢? 这很好理解, 因为分配`\b`只跟间隔有关, end的词在一行只有一个词的时候才要pad空格. 
    3. 上面2这个if语句里面最后的extraNum--是什么意思呢? 注意题目要求是尽量平均分配. 所以多余的就从start开始的间隔每个+1. 注意这里的extraNum是%间隔数. 所以最多的情况是每一个间隔的空格都加1个. 否则就是靠左的间隔s分到一个extra的空格.
    >"Extra spaces between words should be distributed as evenly as possible."

### Sort Colors
* 其实就是counting sort. 拓展题就是如何压缩, 如何返回最长连续char及其个数.
* follow up: 如何使用O(1)的空间来做? 而且O(n)的time? N00t和Ganker都是用的双指针. 好聪明, 怎么想到的?
    * 最主要还是和reverse Nodes k-group的思想一样, 想好invarient. 可以这样想. 如果sort的colors只有0,1. 那么就只要一个pointer0, 然后i一个一个往下走, 如果遇到了0, 就赋值, pionter0++. 相当于swap的操作. 
    * 现在可以看看有3个colors: 0,1,2的话怎么用pointers呢? ans: 就用2个pointers: p0, p1. 遇到0就赋值, p0++, p1++. 遇到1就赋值, p1++. 为什么这个时候p0不改变呢? 因为??????

### Minimum Window Substring
 - 先去复习longest non-repeated substring和Concatenation (freq1), 因为方法都一样: 
    >建立一个字典，然后维护一个窗口
 - 都是substring match 字典. 这一点类似的还有Word break(Ganker的DP解)
 - 看了N00t的Concatenation里面讲了原来双指针窗口法~~原来就是KMP~~(并不是KMP).不过KMP, BM, KR都很重要.
 -  
 
### Gray Code
* 使用的方法就是ASIC里面介绍的方法. 有意思. 感觉KMP也是FSM.
* 原来Gray code就是汉诺塔的解... N00t的一行解法太cool, 没看明白啥意思.

### Subsets I/II
* 还是Permutation/Combination这种NP问题的解法: backtracking. 只是有一点点区别: 在处理加入的元素时要去掉一些不符合条件的.
* 很好的题目, 再次加深理解Recursion, 不仅仅是像我在[LC中学习Recursion](https://www.zybuluo.com/mdeditor#80599)那样还停留在Recursion代码之前, 之后的代码的意义. 而是要深入理解Recursion思维的想法和设计. 
    * recursion里面, subset加入的顺序是怎么样的? 如何保证 non-descending order? Ans: 其实就是对自己设计的recursion要理解, 只到recursion的顺序就ok了. 因为Ganker的rec是从3,2,1,0,-1 递减的call. 而N00t则是0,1,2,4递增的call. 所以顺序不同. 具体的trace可以参考P533-Algs4-DFS.
    * 还有就是为什么N00t的输出是从[3], [2]开始, 而Ganker的则是从[1], [1,2]开始. 这是因为base case 的判断的区别, 以及recur进入下一层子问题的顺序是递增还是递减. 这很有意思. 要thinking in recursion. 


## 20141225
### Path Sum I/II
* 树的题目很常见, 但是方法都类似: recursion.
* 问题也很常见: I一般就是问是否存在解. II一般是求所有解的集合.
* 正是这道题目, N00t讲了Java的call by value对于object来说是copy他的handler, 即reference, 所以将来的改变会对之前add的reference改变, 也就改变了最终的结果: 
    > + Notice that we make a copy of the path before we added it to our result set. The reason is that when we add an Object (here, an ArrayList), Java add a copy of the pointer (i.e. a reference) of the Object, rather than a deep copy. So, **any changes to content** of the original Object will reflect into our final result set.

+ 同样的Ganker也是`res.add(new ArrayList<Integer>(item));`. 否则必然是错误的.
+ 为什么N00t的if (root.value==sum)里面放path到res之后不能return, 否则会出现错误的path? 因为这个里面加上return是完全错误的! 这里并不是base case, 不应该return. 但是Ganker的为什么又应该加上return呢? 因为这个是base case. 注意Ganker的顺序.

### Longest Substring Without Repeating Characters
* N00t的方法是hashmap+start/end2个pointer的移动, 注意N00t只要判断当前的char是不是在当前substring中, 才update. 考虑到返回所有这种substring的问题. 我的思路是记录longest的start, 这样因为maxlength是返回值. 所以substring就可以解决. 但是有点小问题.
* Ganker则是使用的walker/runner 双指针法. 或者说: 窗口法来处理这一类的string问题. 还归了类. 一开始没理解为什么是while(charAt(w)!=charAt(r)). 想到了一个好例子: "xyb12b02". 走了一遍理解了. 本来给的例子: "abcabcbb"并不好. 因为W=R都是连续的, 没看到while不等的情况. 所以有时候要多举几个case. 但不是随便举. 而是想上面那个case那样第一个重复的'b'并不是第一个字母. 
    * 扩展的题目有Substring with Concatenation of All Words，Minimum Window Substring，思路是非常接近的，只是操作上会更加繁琐一些。
* case很重要, 一是在开始做题之前用来理解, 考虑算法之用. 二来是作为test case. 但是case也要设计得好才行, 不然还是会错. 所以要建立在完全理解题意, 想好算法之后写**有用**的case, 来改正算法. 有点鸡生蛋, 但实际上是要懂了再下笔. 面试不是调代码.
    
### Longest Consecutive int string
* 这道题在GoPro onsite面到. 其实很简单的recursion. 题目意思是给一个排好序的string: "abccdeeeeef", 则'e'是重复次数最多, 有5次. 所以返回: e和5. 其实这个和compression很相同, 之前见过: 将这个string输出为"a1b1c2d1e5f1"
* 思路参考的[SOF上面的recursion]().

### Container With Most Water

### Jump Game
* 经典的DP和Greedy问题. 段公子在DP/recursion里面讲了. 
* 第二题的想法在N00t和Ganker之间有区别. 可以加深理解. 也确实想了好久才明白他们的code.
* Ganker的评论里面又一个记录path的方法, 这样可以返回最少jump的path. 总而言之, 也是一个DP的方法. DP还是Greedy? 傻傻分不清. 还要回头**复习一下贪新算法**: MST.

### 3Sum/4Sum
* 主要参考N00t和Ganker的解法. 但其实可以结合HashSet来做4Sum. 更简单.
* 注意这里面的设计, 如何避免重复. 所以要sort. 通常的解法是夹逼法则. for loop循环3-2或者4-2个头, 然后剩下2个index可以用left/right来夹逼.
* 注意这里正确的使用了do...while. 
* Binary search的合理使用. 一般不会直接写一个binary search. 但是这里夹逼自然是binary search好用. O(n)->O(lgn)

### Spiral Matrix I/II
* N00t的这张图真是经典, 也确实是复杂题目想好psuedo code才写!
* 

### Recover Binary Search Tree
* 再次加深了对Recursion的理解. 2个不连续的recursion. 而且param还不对称, N00t的recursion写的真好. N00t的方法也很好. 
### Distinct Subsequences
 > +When you see string problem that is about subsequence or matching, dynamic programming method should come to your mind naturally. ---by [link](http://www.cnblogs.com/springfor/p/3896152.html)

 *  DP的经典题目. N00t的做法也是可以的. 但我倾向于Ganker/SophieJ. 但是要理解DP公式的含义. 不然连边界条件或者初始条件都搞不出来. Ganker的2D分析, 然后code则是优化为1D dp的解法一开始没看懂, 然后Ganker在回复里面解释了为什么j是从T的后面往前扫. 这是因为这里的**DP是想使用update之前的值**, 所以这样. 如果以后有个DP问题是要使用更新后的1D dp, 则是从头往后扫.
 * 还是的加深理解为什么1D的逆向填的DP是对的? 会不会导致比较出来的subsequence没按顺序? 或者反过来了? 见[csdn link](http://blog.csdn.net/kenden23/article/details/19332545) 有点乱: 下标, 如何保证是in-order? 看Coin Change---段公子.


## 20141223
### Plus One
* 这是一道简单题. N00t给出了他的扩展题: plus int. 
* Ganker说他在Google店面的时候就问了这题. 因为适合扩展和OOD的设计.
### Symmetric Tree
* Ganker是简单的recursion和iteration. 注意这里判断是否对称的条件: 空的情况, 有值的情况. 注意不用判断值相等. 为什么? 因为helper是recursion, 要找到结束得点. 而值相等可以继续走下去. 而都为空就可以直接返回true. 因为走不下去了. 这里也明显的看出iteration为什么会繁琐. 
* N00t使用的一种stl数据结构: ArrayDeque. 其他思路和Ganker是一样的.

### Balanced Binary Tree

### Palindrome Number
* 和Freq3的PalindromeDPDP类似. 
* 这里的比较2end不能用pointer了. 因为不是string. 这里有2个方法. 用一个div(不断变化). 本质还是loop里面update为指向对称的end points.
* 或者是1337里面的第三个方法: 用一个stack.

### Search Insert Position
* 在freq3里面已经做过 

### Valid Sudoku
* 巧妙使用`i/3*3`和`i%3*3`. 以及API的设计

### SOlve Sudoku
* 加深理解循环递归. 觉得比NP的N-Queens更重要. 这里的设计是很重要的模版. 

### Count and Say
* String的小题目. 一定要bug free. 
* 题目居然没看懂. 还是看了[CSDN上面FightForDream的帖子](http://blog.csdn.net/fightforyourdream/article/details/12901505)才搞明白什么意思.
### Remove Duplicates from Sorted Array I/II
* 好在我回顾了这道题目. 发现N00t的思路太不好了, 很容易错, 而且扩展不了. 实际上有string, 有比较的时候就用pointer就好了. 而且这里还是in-place. 就算是II, 也只要多加一个变量cnt来看当前有几次重复元素.
* 所以Ganker的方法远胜于N00t. 也保证了这种简单题目的清晰思路和bug-free. 就是通过
    
---

## 题目
| #  | Leetcode problem                                  | freq |
|-----|------------------------------------------------|---| 
| 66  | ~Plus One                                       | 2 | 
| 101 | ~Symmetric Tree                                 | 2 | 
| 110 | ~Balanced Binary Tree                           | 2 | 
| 9   | ~Palindrome Number                              | 2 | 
| 35  | ~Search Insert Position                         | 2 | 
| 36  | ~Valid Sudoku                                   | 2 | 
| 38  | ~Count and Say                                  | 2 | 
| 80  | ~Remove Duplicates from Sorted Array II         | 2 | 
| 113 | ~Path Sum II                                    | 2 | 
| 3   | ~Longest Substring Without Repeating Characters | 2 | 
| 11  | ~Container With Most Water                      | 2 | 
| 18  | ~4Sum                                           | 2 | 
| 55  | ~Jump Game                                      | 2 | 
| 59  | ~Spiral Matrix II                               | 2 | 
| 61  | ~Rotate List                                    | 2 | 
| 92  | ~Reverse Linked List II                         | 2 | 
| 5   | ~Longest Palindromic Substring                  | 2 | 
| 25  | Reverse Nodes in k-Group                       | 2 | 
| 37  | Sudoku Solver                                  | 2 | 
| 40  | Combination Sum II                             | 2 | 
| 42  | Trapping Rain Water                            | 2 | 
| 45  | Jump Game II                                   | 2 | 
| 47  | Permutations II                                | 2 | 
| 48  | Rotate Image                                   | 2 | 
| 54  | Spiral Matrix                                  | 2 | 
| 68  | Text Justification                             | 2 | 
| 75  | Sort Colors                                    | 2 | 
| 76  | Minimum Window Substring                       | 2 | 
| 89  | Gray Code                                      | 2 | 
| 90  | Subsets II                                     | 2 | 
| 99  | Recover Binary Search Tree                     | 2 | 
| 115 | Distinct Subsequences                          | 2 | 
| 117 | Populating Next Right Pointers in Each Node II | 2 | 
| 124 | Binary Tree Maximum Path Sum                   | 2 | 
| 31  | Next Permutation                               | 2 | 
| 41  | First Missing Positive                         | 2 | 
| 84  | Largest Rectangle in Histogram                 | 2 | 
| 87  | Scramble String                                | 2 | 
| 97  | Interleaving String                            | 2 | 






