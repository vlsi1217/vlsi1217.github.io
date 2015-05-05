# Leetcode Freq 4-5

标签（空格分隔）： leetcode
---

## 目录
[TOC]

## 20141111
###Remove Element
###Roman to Integer
###Swap Nodes in Pairs
* List的经典操作: 交换nodes. 注意这里什么不变, 什么变. 以及如何处理边界条件. 头脑要清晰. 意乱就麻烦.
* follow up就是freq 2的reverse Nodes K-group 
###Add Binary
###Sum Root to Leaf Numbers
###Add Two Numbers
###Integer to Roman


## 20141108
###Word Search
###Decode Ways
###Binary Tree Level Order Traversal
###Palindrome Partitioning
###Sqrt(x)
###Generate Parentheses
###Merge k Sorted Lists


## 20141105：
### 1. word ladder
* 为什么wordladder用linkedlist来保存distanct？为什么要在for loop加distance并enqueue？为什么`!isEmpty`的时候pop出来的distance就是对的。
    * a. ANS: 可以参考Algs Princeton的BFS正确性证明。每次wordQ 在enqueue的时候都是同样深度的。而且queue的pop（remove and return）保证了distance的正确性。
eg：ab->aa的最短距离。虽然az的话。loop会把distanceQ+1. 但是不同的word话要pop distance。如果不同的话又返回到aa了。
  * b. 不过还是觉得不太对劲。如果运气不好一直网az,dz,zz方向走不就成了DFS了？而不是BFS（一层一层的走吗？）.
ANS：BFS是搜完距离1的继续搜距离2的。当全部搜完之后call distTo来输出start到end的最近距离。

```
  ab
 /   \
aa   az
         \
         dz
           \
           zz
```
  

## 20141103:
### 0. BFS
* 联系看pseudocode来写java。比如Wiki page的BFS (link).
* 还是学习一下regex方便些。因为Java的replaceall支持regex。而且今天在删除eclipse里面的空行也是用的`regex: ^\s*\n`

### 1. Word Ladder
* 怎么想到本质一个graph的shortest path呢？
    * a. 先是看了yutianzuijin的csdn博客（link）他先是想到2d matrix 然后再想到graph的2种表示方法：
        * 一. adjacent matrix 
        * 二.adjacent list. 然后就可以用graph的BFS来解决。但是时间复杂度又太大了，是O(m*dict_size). 其实可以做到O(26*m)
    * b. 鱼的（link）讲了可以用`bi-directional BFS`，也可以看作是夹逼方法和k-Sum问题类似。
    * c. n00tc0d3r比较详细的分析并给出不同解法。而且说了hashset/hashmap在Java和C++里面的区别：在Java中map快，在C++中set快。


## 20141102:
> finish freq 5

### 1.  set Matrix Zeros.
* 这又是一道：达到目的可以改变方式。为了in place. 
* 必须要有2组set来记录行列为0的info。既然有matrix给我们。就可以改变它。为什么不用第一行列作为这个row/col set. 当然如果第一行列有zero怎么回忆起来呢？虽说inplace不能用additional set. 但是flag还是可以的。因为O(1)=in place。

### 2. Merge sorted array/list.
复习一下java里面的lnkedlist和指针了。

## 20141101:
### 1. Pow(x,n):
我看了n00tc的答案，他是用n>>1 和 n&1来代替除法和取余数。但是我直接把pow2的计算换成bit后答案就错了。为什么呐？我测试了一下>>1 跟 &1. 发现如果是正数当然没问题。但如果是负数则除法跟取模都会得到意外的答案。
1. I got yu shu: 1 vs 1
1. I got remainder: 1 vs 1
2. I got yu shu: -1 vs -2
2. I got remainder: -1 vs 1
注意到N00tc0d3r一开始就把指数n取了绝对值。

### 2. Validate BST
很好的题目。可以复习到recursion跟iteration的转化。其实traverse里面的迭代版本不好想。邓俊辉的教材分析得很详细。而且recursion里面的每个return只是结束当前的stack，从而可以继续之前保存的stack。觉得CMU-ebiz的答案最好懂。这里面的lastVisit = root.val放到if(root.val<=lastVisit)之后。这个顺序也是一个高潮。要真正理解recursion才能想出来这点。

### 3.  kd-Tree. 
+ 参见Coursera Algo4th 的作业五：在一堆点里面找能用一个unit square抱住的点群。这就需要用2d tree来search了。link

### 4.  Palindrome
+ 做palindrome之前先看一下java的replaceall用的regex: [link]()
+ 这里要了解的就是[^a-z], [a-z0-3]的意思。前者是除了a到z以外。后者表示a到z和0到3.所以这里就是先把输入的string的非字母数字的字符都换成”“。

### 5. Valid Number
+ 居然说Valid Number是难度2的题目。。。跪了。第一次面试做compiler的parser就是一个automa。然后不会写Java的状态机。这次一定要搞懂。记得moore和mooley FSM最难的就是设计states。就连最简单的判断100101都容易写错。可以参考这个link看看这个状态图是怎么画的。N00tc0d3r只给了图....
+ 注意switch case的格式：要在每个case最后加break或者return。而且case只能是每一个值而不能给范围。所以a-zA-Z0-9就呆了。还是if else好用些。

### 6. setMatrixZeros
+ 做setMatrixZeros看到用的hashset，为什么呢？hashmap不行吗？为什么用`Set<Integer> rows = new HashSet<Integer>();`
然后看到了一片总结：link。
可以看看我在CS108里面贴的那个java collection framework关系图。
注意在jdk里面set.class和hashset.class. 前者是interface后者是implementation。


## 20141030:
### 0. climb chairs
这题和数硬币组合类似。不过前者是permutation后者为combination。

### 1. 2 sum:
首先把题意搞清楚: 
输入是一组未排序的可重复数组，以及一个目标数。且一组数组只有一组解。求这两个数的index。

ANS: 
一开始想是：
```
for i: array
     for: j: rest of array
          if (i+j==target)
               return.
```
但是这样就会得到O(n^2)!

记得quick sort之类的排序算法是O(nlgn)。可以得到吗。
唉。想不出来。
看网上答案也就n00tc03r和lexi女的博客有思路分析。还有CMU-ebiz的java整个打包但是答案质量一般。不过也可以看看。programcreek的也很好。

但最主要还是像段公子说的那样要总结分析出pattern而不是一题一题的解决。这样永远会有不会做“新题”的可能。

比如这里面一般C++的都会用头尾指针，如CMU的做法。这里有个分析点很重要：首位指针如果大于target则可以把大的元素永远排除！也很容易想：因为这个最大的数加上最小的那个数都超了，那他就不可能和任意其他元素相加得到target。但是小的那个数却可以保留。因为他还有机会。这里和merge 2个排好序的数组要从最后排起。以及删除一个linked list的node却只给了前一个pointer一样。要脑子拐过弯！

所以先写第二种解法：头尾指针往中间靠。这样的时间复杂度是多少呢？
仔细看一下。CC里面的2sum是return 满足条件的2个element的值。所以可以用java的sort。
但因为leetcode是要求index。所以要多一个O(N)空间复杂度来保存index。
九章和CMU_ebiz都给了这个答案。

第三种解法：用hashmap。则可以O(1)+O(n)=O(n)
一个for loop, 搜索map里面有没满足的数，途中把target-input[i] 放到map里面。
这个最好。用到了java的library。又快又方便啊！而且HashTable解决所有搜索问题啊。

### 2. 3 sum：
还是要把题目理解：
找到一组未排序的可重复数组里面的所有 不重复的满足sum为0的triplets。
艹，搞了一上午，为什么Java ArrayList<ArrayList<Integer>> remains empty when I add an ArrayList<Integer>. 原来是ret.add(each)的不是copy而是reference。在each.clear()后，前一句的2D list add就是add了一个空的ArrayList。正如Core Java里面讲的：“parameters to java methods are always passed by value. The value of any object variable is a reference to an object that stored elsewhere.
中饭回来查了一发现stack overflow上有这个解答：link
所以有2个方法解决clear就把之前added的删去的问题：
     a. 用add new（each） 也就是copy过来。
     b. 每次在inner loop里面new一个each。

 
### 3. 3 sum closest
### 4. 4 sum
### 5. k-sum.
### 6.pow(x,n)：
先是naive做法。直接循环。但是傻了，忘了负指数是取倒数了。
到了用naive的recursion做法，Programcreek是说：'a recursive solution can easily be written.'。。。好容易弄混。要好好分类。其实就3类，先按N的奇偶正负分，同时和x的正负。而且记住是recursion，所以三个case都是互相调用，每次调用都是`pow*pow*pow`！而不是pow一次。
但是naive的recursion还是不够快，而且recursion的重复计算总是不好的。


---
|  ID   |                                   |   |   | 
|-----|-----------------------------------|---|---| 
| 27  | Remove Element                    | 1 | 4 | 
| 13  | Roman to Integer                  | 2 | 4 | 
| 24  | ~~Swap Nodes in Pairs~~               | 2 | 4 | 
| 67  | Add Binary                        | 2 | 4 | 
| 129 | Sum Root to Leaf Numbers          | 2 | 4 | 
| 2   | ~~Add Two Numbers~~                   | 3 | 4 | 
| 12  | Integer to Roman                  | 3 | 4 | 
| 22  | Generate Parentheses              | 3 | 4 | 
| 23  | Merge k Sorted Lists              | 3 | 4 | 
| 46  | ~~Permutations~~                      | 3 | 4 | 
| 49  | ~~Anagrams~~                          | 3 | 4 | 
| 77  | ~~Combinations~~                      | 3 | 4 | 
| 78  | ~~Subsets~~                           | 3 | 4 | 
| 79  | Word Search                       | 3 | 4 | 
| 91  | Decode Ways                       | 3 | 4 | 
| 102 | Binary Tree Level Order Traversal | 3 | 4 | 
| 131 | Palindrome Partitioning           | 3 | 4 | 
| 69  | Sqrt(x)                           | 4 | 4 | 
| 1   | ~~Two Sum~~                           | 2 | 5 | 
| 8   | ~~String to Integer (atoi)~~          | 2 | 5 | 
| 20  | ~~Valid Parentheses~~                 | 2 | 5 | 
| 21  | ~~Merge Two Sorted Lists~~            | 2 | 5 | 
| 65  | ~~Valid Number~~                      | 2 | 5 | 
| 70  | ~~Climbing Stairs~~                   | 2 | 5 | 
| 88  | ~~Merge Sorted Array~~                | 2 | 5 | 
| 125 | ~~Valid Palindrome~~                  | 2 | 5 | 
| 15  | ~~3Sum~~                              | 3 | 5 | 
| 50  | ~~Pow(x, n)~~                         | 3 | 5 | 
| 73  | ~~Set Matrix Zeroes~~                 | 3 | 5 | 
| 98  | ~~Validate Binary Search Tree~~       | 3 | 5 | 
| 127 | ~~Word Ladder~~                       | 3 | 5 | 
| 28  | ~~Implement strStr()~~                | 4 | 5 | 
| 56  | ~~Merge Intervals~~                   | 4 | 5 | 
| 57  | ~~Insert Interval~~                   | 4 | 5 | 
|  ID   | 念念不忘, 必有回响    |   |   | 


