# Leetcode freq 3

标签（空格分隔）： leetcode

---

# Frequency 3题解
[TOC]

## 20141218
### Combination - N00t
+ 标准的recursion DFS模版. 牢记于心.
+ 而且有topdown和buttomup2个写法. 其中BU更简练.
+ 还有iteration的写法.

### Letter Combinations of a Phone Number
+ 然后再来看这道题, 可以用Simple&Stupid的做法, 和N00t的Combination差不多. 
+ 或者N00t的iteration解法. 

### Combination Sum I/II
+ N00t和Ganker结合. 注意2个的区别: I是可以reuse自己. 即可以用2(0), 2(0). 这里的2(0)表示在位置0的2. eg: {2, 2, 3, 7} 求sum=7. 而II是不能reuse自己. 所以答案是2(0), 2(1).

### Maximum Subarray I/II
+ N00t的I是用的简单的一次过. 里面的sum初始化的条件是sum<0. 但是看了Ganker的方法才知道原来这道题目是典型的DP. 
+ 其实LC的要求是不用DP做来达到O(n). 而是用Divide and Conquer来做. 看水中的鱼(C++). 或者一天一学(Java): [链接](http://joycelearning.blogspot.com/2013/10/leetcode-maximum-subarray.html)
+ 其实II并**不能**用N00t的做法. 因为有可能出现有多个subarray的值都一样! 可以看LintCode的解法: [链接](http://codeanytime.blogspot.com/2014/12/lintcode-maximum-subarray-ii.html)

### Jump Game
+ Ganker在Maximum Subarray里面提到了DP里面常用的一个方法: 称为"局部最优和全局最优解法". 在Jump game里面也用到了. 


## 20141217
### Trapping Rain Water
+ [Ganker CSDN大神的DP解法](http://blog.csdn.net/linhuanmars/article/details/20888505)
+ 当然还是N00t的解法作为入口开始理解.

### Largest Rectangle in Histogram
+ N00t: 双Stack, 一个stack
+ CSDN/abcbc: [双Stack](http://blog.csdn.net/abcbc/article/details/8943485)
+ GeekForGeek: sliding window, segment tree. 这2个做法很重要!要认真理解.

### Search a 2D Matrix
+ 简单题: 用2次binary search. 或者当作1个sorted array只要一次binary search就行了.

### Partition List
+ 参考的Ganker的答案, 但是有点混乱了. Java里面不是copy的handle吗? 那walk, runner一直改变, 不是也同时改变了helper吗? 
+ ANS: NONONONONO. 双指针大法: walker/runner只是copy了pointer. 而每次walker = walker.next. 就assign了walker到下一个pointer. 这和C/C++一样.

### Number of Islands
+ 简单题. ProgramCreek使用的recursion解法. 

## 20141208
### Populating Next Right Pointers in Each Node
### Divide Two Integers                        
### Search in Rotated Sorted Array I/II
### Deep Iterator : LinkedIn老题目
+ C++的解法: [fgdsd的解法](http://www.fgdsb.com/2015/01/19/nested-iterator/) 以及[Iterator pattern](http://www.oodesign.com/iterator-pattern.html)
+ ~~Rosetta算法大全里面也有解法~~ 这只是print Flatten, 不是iterator: [Java解法link](http://rosettacode.org/wiki/Flatten_a_list#Java)
+ 还是包子铺好, 有Java的解法(是iteration, 使用Stack来替代recursion, 上面讲recursion简单些): [iteration解法](http://blog.baozitraining.org/2014/08/linkedin-twitter-and-hulu-onsite-how-to.html)
+ 其实这是一个常见的模式: **Iterator and Composite Pattern**. 见Head First Design Pattern. 这是最好的解法. 设计+代码

## 20141206
### Search In Sorted Array      
+ 同类题目: Search Insert Position
+ 同类题目: Search for a range

### Multiply Strings     
+ 初看之下, 就是一道简单题目啊, str2num然后乘起来不就行了? 其实不行, 因为会太大(??)
+ 第一种解法初看之下也是平淡无奇, 但其实分析的时候发现居然把array和charAt的顺序搞混了. 45->num1 = [4,5]. 这里的num1[0] = 4! 所以45*123的res要做成5位, 并将第一位留为0. 因为最终可能进位.

### N-Queens I/II
+ Matrix67/N00t用的是位运算解决问题.
+ Ganker用的是传统的**循环递归**解决这种NP问题. 
+ 又听了段公子的DP+Recursion讲座. 讲了用遍历+backtracking这种都是NP, 即是NP问题. 里面也提到了像Word Break这种可以Top-Down(memorize)和Button-Up(DP)的区别. 以及求CSP的个数和所有解空间的区别. 
+ Quora上面讲了top-down跟button-up的DP都不一定需要用recursion. 

## 20141205
### Restore IP Addresses     
+ 太好了, 又深入的理解了recursion. 这里虽然只有一次显式的recursion, 但他是位于loop里面, 后面还有个results.add(). 以及共用(?和谁)一个results. 所以这种是一个今天学到的recursion: loop内的recursion用于循环遍历一层. 
+ N00t果然是recursion很厉害. 佩服. 这里是DFS还是BFS? 是不是子解树? 之前看的那个CSDN 链接在哪?

### Triangle Path Sum
+ 很好的DP问题. 简单, 也分为top-down, 和button-up的2种思维方式.
+ 初始化List<List<>>费了些心思, 觉得还是anonymous inner class比较简洁. [link](http://mangstacular.blogspot.com/2013/04/4-ways-to-initialize-list-in-java.html). 其中[programinterview的讲解很清晰](http://www.programmerinterview.com/index.php/java-questions/java-anonymous-class-example/)
+ N00t的top-down又让我深入理解了recursion中的param是primitive/object的区别. 以及call stack的new和clr的过程(Eclipse). 
+ 暂时还没看N00t的top-down的BFS解法........................................

### Surrounded Regions    
+ 极度经典的图形学算法题目: flood fill. 同时关于DFS/BFS的取舍. 以及Queue的API: offer/poll. (这和stack的push/pull对应) 
+ 参考Ganker/N00t的解法. 
+ Palindrome Partitioning I/II    
+ N00t大神的DP+DP/DP+, 还有美丽的**partitionHelper()**
```
private static void recur(String s, int left, List<List<String>> result) {
    if (left == s.length()) {
      result.add(new ArrayList<String>());
    }
    for (int i = left; i < s.length(); ++i) {
      List<List<String>> temp = new ArrayList<List<String>>();
      recur(s, i + 1, temp);
      for (List<String> partitions : temp) {
        partitions.add(0, s.substring(left, i + 1));
      }
      result.addAll(temp);  // add()和addAll()的区别.
    }
  }
```

+ 想到了word segment, word spell out, Matrix Chain multiplication
    + 也是一道经典recursion题目. 而且还看到了在for each里面的recursion call. 怎么展开呢? 太美了的recursion!
    + [word spell out 链接](http://www.geeksforgeeks.org/print-possible-strings-can-made-placing-spaces/)
    + [Matrix Chain Multiplication链接](http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/)
    + [Word Split: SOF链接](http://stackoverflow.com/questions/5275002/recursively-spell-out-a-word)
    + 20150423: subsequence+matching的题目首先想到的应该是DP: 见[sophieJ的解法](http://jane4532.blogspot.com/2013/11/palindrome-partitioning-iileetcode.html)

### Word Segment I/II
+ Naive做法是N00t的好
+ DP做法有2D和1D, 可以是aray或者arrayList, N00t的做法和ProgramCreek的不同. 
+ Word Break II的主要参照programCreek的做法. 很完美经典的DFS+DP解法. 漂亮!!! ProgramCreek还赔了图, 其中的words即dp[].显而可见index为什么是s.length()+1了. ![ProgramCreek的配图][1]
        
## 20141129
### Find the k-th Smallest Element in the Union of Two Sorted Arrays
+ 先理解这道题再做median of two array.
+ 这里关键的问题可以转化为: 找B[j-1] <A[i] < B[j] 或者 A[i-1] < B[j] < A[i]. 如果不成立就recursion. 注意这里因为Java不能用指针, 所以要稍微站换一下1337的写法.

### Median of Two Sorted Arrays    
+ 这题有很多思考, 值得说道说道. 这题是源自于CLRS. 而且里面还简化了: m=n. 而LC这道题是generic的. 
+ 要做这题先要理解这道题: Find the k-th Smallest Element in the Union of Two Sorted Arrays. 见上.
+ 

### Regular Expression Matching        
+ 如何保证考虑详细?
+ N00t的解法很好
    + 不知道怎样才能像他那样那么有逻辑. 不会有错漏. 这个if/else设计的太美了
    + redundant分析我觉得不对. 因为到了2nd iteration中. "b" vs "a*a*b"是不会有3个substructure的. 因为不match.
    + backtracking做法中, 一个用substring, 一个就是用2个指针i,j. 避免了substring()来copy浪费空间. 不过之所以这里可以用"char array"是因为这里只是比对. 而不需要修改. 所以直接reference即可. 但是像之前的partitionHelper里面可以用吗??? 或者是更之前的: 

### Wildcard Matching 
    +    

## 20141118
### Construct Binary Tree from Inorder and Postorder traversal
+ 首先通过pre, post, in加深了对recursion的理解. 
+ 还是老话, 思考问题的方式. 先是找规律. 发现pre order的头的root, 然后可以在In order里面找到左右子树的大小, 再回到pre order里面找到左右子树的root. 如此recursion.

### construct binary tree from preorder and inorder traversal
+ 举一反三. 找规律看到了post-order反着读的话就正好是pre order的mirror. 所以这次从post order的尾巴开始读. 发现尾巴就是root, 然后回到inorder里面找左右子树的大小. 从而回到post-order里面找左右子树的root. 要注意的是不能点到mid < end和mid > start! 一开始就这样, 发现有错误, 进行到一半就停了.
+ Flatten Binary Tree to Linked List  
+ [太好了, 终于完全理解复杂recursion了](http://n00tc0d3r.blogspot.com/2013/03/flatten-binary-tree-to-linked-list-in.html)

### Path Sum
+ root-to-leaf Path sum: [ProgramCreek的解法](http://www.programcreek.com/2013/01/leetcode-path-sum/)   
+ Binary Tree Maximum Path Sum
+ Find ALL Path Sum

### Reverse Integer
+ 要注意overflow, 跟负号

### Unique Paths I/II
+ 经典的DP问题    
+ 也是path问题, 可以找规律, 变成排列组合题.
+ 如果有obstacle呢? 
+ 觉得还是N00t的解法比较好. 相对于1337的2D opt, 这个只要1D. 而且可以解出来.

### Longest Consecutive Sequence  
+ 还是觉得N00t的方法. 还是一样, 学会抽象, 不要以为tree型就一定要用tree实现, 或者range就必须要知道左右和长度. 要有了整体把握才设计API. HashMap的关键在于key/value的设计. 
+ 还是N00t的"simple"(不觉得simple, 然而很多技巧在里面). 好聪明的做法. 果然是精通API和recursion的N00t.

##20141115
### Edit Distance 
* 这个是个经典的DP问题.  是Stanford Algs 2 DP问题DNA Sequence Alignment的扩展, 因为不只是ACGT, 也不只是Gap, 而是有3种操作. 注意n00tc0d3r的DP的优化! (用的空间是O(min(l1, l2) 而不是通常的O(l1*l2)) 我觉得这个是一个通用的optimize方法. 是不是four Russian(不是)?

### One Edit Distance
* 这个看起来就是直接用Edit Distance就行了. 其实会超时间! 注意是one distance. 所以直接1 pass. 

### Binary Tree Inorder Traversal
+ 很喜欢N00tc03的post-order=pre-order的mirror.

### Binary Tree ZigZag level order Traversal
+ 这个属于BFS. 注意看题意: 输出是要按照parent来加上括号. 而不是只要按照顺序就行的. 所以有2中方法:
+ 法1: 用flag
+ 法2: SRAM的双buffer. 这里使我真正的理解了**github_addtion.bellmanFord()**! 原来reachableNode, 和nextreachableNodes就和queue, queueBuffer的关系一样. 都是用来**update到下一个layer**的.
+ 注意要用2个loop, out-loop创建reslist来保存currLevel的值, 创建childLevel来保存下一层. in-loop用来填充res和childLevel

## 20141112		
### Convert Sorted list to BST (in-order, Button-up)
+ 这个和array的区别是array可以O(1) search, LinkedList要O(n). 所以不行. 但是想到BST是in-order. 可以直接从leaf开始. 也就是**Buttom-up recursive**. 但是要注意的是recursion的写法. 真正理解: 位于recursive call之前和之后的code的意义是什么. 这在Algs4的BST讲过. 在Stanford的SCC的DFS_loop讲过.

### Convert Sorted Array to BST
+ 简单. 因为BST是按顺序的, 所以recursive拿middle并左右路.

### Remove duplicates from sorted Array
+ 这个可以看N00tc0d3r的解法. 先学会remove element from array
+ 而且用Nth-to-end里面的双指针, 距离n+1个node! 

### Median of Two Sorted Arrays
+ 比较难. 而且大部分答案都不对. 还是得看1337c0d3r的答案分析. 正统. 他让先看: Find the k-th Smallest Element in the Union of Two Sorted Arrays. 他说这个是这道题的基础.

### Remove duplicates from sorted List
+ 关键在于list的删除要给出这个node的前一个node, 然后用pre.next = pre.next.next.

----

## 题目
| #  | Leetcode problem                                  | freq |
|-----|----------------------------------------------------|---|
| 26  | ~~Remove Duplicates from Sorted Array~~                | 3 |
| 83  | ~~Remove Duplicates from Sorted List~~                 | 3 |
| 112 | ~~Path Sum~~                                           | 3 |
| 7   | ~~Reverse Integer~~                                    | 3 |
| 19  | ~~Remove Nth Node From End of List~~                   | 3 |
| 62  | ~~Unique Paths~~                                       | 3 |
| 108 | ~~Convert Sorted Array to Binary Search Tree~~         | 3 |
| 17  | ~~Letter Combinations of a Phone Number~~              | 3 |
| 39  | ~~Combination Sum~~                                    | 3 |
| 53  | ~~Maximum Subarray~~                                   | 3 |
| 63  | ~~Unique Paths II~~                                    | 3 |
| 64  | ~~Minimum Path Sum~~                                 | 3 |
| 74  | ~~Search a 2D Matrix~~                                 | 3 |
| 82  | ~~Remove Duplicates from Sorted List II~~              | 3 |
| 86  | ~~Partition List~~                                     | 3 |
| 93  | ~~Restore IP Addresses~~                              | 3 |
| 105 | ~~Construct Binary Tree from Preorder and Inorder~~ | 3 |
| 106 | ~~Construct Binary Tree from Inorder and Postorder~~ | 3 |
| 114 | ~~Flatten Binary Tree to Linked List~~                | 3 |
| 116 | ~~Populating Next Right Pointers in Each Node        | 3 |
| 29  | ~~Divide Two Integers                                | 3 |
| 33  | ~~Search in Rotated Sorted Array                     | 3 |
| 34  | ~~Search for a Range~~                                 | 3 |
| 43  | ~~Multiply Strings~~                                   | 3 |
| 51  | ~~N-Queens~~                                           | 3 |
| 52  | ~~N-Queens II~~                                        | 3 |
| 72  | ~~Edit Distance~~                                      | 3 |
| 94  | ~~Binary Tree Inorder Traversal~~                      | 3 |
| 103 | ~~Binary Tree Zigzag Level Order Traversal~~           | 3 |
| 109 | ~~Convert Sorted List to Binary Search Tree~~          | 3 |
| 128 | ~~Longest Consecutive Sequence~~                  | 3 |
| 130 | ~~Surrounded Regions~~                  | 3 |
| 132 | ~~Palindrome Partitioning II~~                  | 3 |
| 4   | ~~Median of Two Sorted Arrays~~                  | 3 |
| 10  | ~~Regular Expression Matching~~                  | 3 |
| 44  | ~~Wildcard Matching~~                  | 3 |
| 81  | ~~Search in Rotated Sorted Array II~~                  | 3 |
| end  | 念念不忘必有回响                | 3 |
 


  [1]: http://www.programcreek.com/wp-content/uploads/2014/03/word-break-II-java-298x400.png