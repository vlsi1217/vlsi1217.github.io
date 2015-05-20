title: "Leetcode freq 2"
date: "2015-02-16 20:17:16"
tags: Leetcode
categories: Interview
description: "A collection of Hello World applications from helloworld.org."
toc: true
---

## 20141228
### Populating Next Right Pointers in Each Node II
* 

### Lowest Common Ancestors I/II:
#### I: 即没有parent的pointer怎么做?
* 这题不仅在九章算法课第二课里面讲了, 而且在[1337文章link](http://articles.leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-i.html)也详细的解释了Bottom-up的意义. 我认为那个是设计思路. 
* 先复习一下: top-down/bottom-up approach: [TD/BU的区别-米群讨论](http://www.meetqun.com/thread-2732-1-1.html), 但是里面大牛的回答也不准确, DP的top-down/bottom-up和recursion没什么关系. 例如这里的LCA的Bottom-up就是recursion. 但是Scramble String的3D Bottom-up DP就是iteration. 那么在Bottom-up有什么好处呢? 在Binary Tree的BU解法中, 相对于Top-Down是
> avoiding traversing the same nodes over and over again.      --- 1337

    * 就**树**来说: 最准确的解释就是: [自底向上=post order traversal](https://xiangcaohello.wordpress.com/2014/06/22/%E8%87%AA%E5%BA%95%E5%90%91%E4%B8%8A%E9%81%8D%E5%8E%86%E6%A0%91%E9%81%BF%E5%85%8D%E9%87%8D%E5%A4%8D%E8%AE%A1%E7%AE%97/), 以及这篇[Bottom-up解题报告](http://www.cnblogs.com/airwindow/p/4285203.html)
    * 其次[javayu的解释](http://wp.javayu.me/2014/02/lowest-common-ancestor-of-a-binary-tree/): 树的bottom-up方式和top-down方式的主要差别就在于：先处理当前节点还是先处理子树
+ 而且这里很有意思的是Top down的LCA是recursion, 里面还调用countMatches这个小recursion.
    + 这还是见得比较少的, 一般就是client调用helper recursion.
    + 照[Tree总结](http://www.faceye.net/search/136311.html)所总结的万金油方法, 不过这个表述没看明白, 原来是在[cnblog symetric Tree](http://www.cnblogs.com/leetcode/p/4003795.html)里面的分析的思路: 
> 1. top-down 还是 bottom-up.    选择top-down.        O(n^2)
> 2. 用递归. 是否需要内嵌小递归.   需要. 其实就是判定root的是否一样的小递归.
> 3. 逻辑运用小递归.  

#### 这道题的follow up: parent pointer
* 如果每个node有parent pointer的话呢? 可以怎么改进: [LCA II](http://articles.leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-ii.html)
* 在九章算法的第二课就讲了, 90% Tree的题目可以用Divide & Conquer做. 注意D&C和Traverse的recursion区别在于前者有return value. 因为在**治**的阶段需要对**分**的结果进行合并处理.

### Minimum Adjustment Cost: Lintcode动态规划题目
* 参考的是[主页君的cnblog和github](http://www.cnblogs.com/yuzhangcmu/p/4153927.html)分析的很详细, 有4种解法:
    > 其实就是NP问题的backtracking解法. 但还是老问题, 怎么找到解集合?

![九章算法黄老师课上的课件](http://7xj2zx.com1.z0.glb.clouddn.com/9chap_minAdjCost.png)

### Binary Tree Maximum Path Sum
* 这题是diameter/height的扩展题. 因为题目还包含了不经过root的情况. 其实都是dfs recursion. 只是要同时保存single path和sum. 即
    * single path是经过当前node的一边的max: Math.max(l.path, r.path)+root.v -> 经过当前node的边的最大值. 0->即经过此node的边为负.
    * sum则是左右sum的最大值, 或者左右边最大值加上当前node.
* 由[嘻唰唰blog](http://bangbingsyb.blogspot.com/2014/11/leetcode-binary-tree-maximum-path-sum.html)题解里面分析的: 对于每一个node的Maximum Path Sum分2类: 
    > 1. single path是指由该node出发向leaf的第一类path中最大的path sum
    > 2. 以x为LCA的第二类path中的最大path sum

* 正是因为看到嘻唰唰提到的LCA, 所以去看了1337的LCA文章, 受益良多. 加深对top-down/bottom-up的理解.

### Diameter and Height of Binary Tree
* 做Binary Tree Maximum Path Sum之前先做一下类似理解的题目: Diameter of Binary Tree: [N00t帖子](http://n00tc0d3r.blogspot.com/2013/07/diameter-of-binary-tree.html)
> 树没有被看成有向图，而是被当成无向图来寻找路径 -- [Ganker](http://blog.csdn.net/linhuanmars/article/details/22969069)
* 标准的DFS. 注意base case一定要return来终止.
* 注意Height必须是进过root, 但是Diameter并不一定. 参见N00t的配图. 而N00t的公式则按照(SOF解释](http://stackoverflow.com/a/11897490). 很好理解了.

### Next Permutation
* 先理解什么意思: 见[Nayuyi.io的解释](http://www.nayuki.io/page/next-lexicographical-permutation-algorithm). 正如水中鱼所说: 这道题目就是一个观察题, 以及基本的array操作. 比如交换, index, loop的break. 复杂度只能是O(3*n)
* 我还是参考的水中的鱼的思路. 找到partitionNum, partitionIdx, changeNum. 然后交换partionNum, changeNum. 接着再reverse num.substring(partitionIdx+1).
* Ganker和水中的鱼对于changeNum的找法有一点微小区别.

### First Missing Positive
1. 其实这就是bucket sorting. 是最快的排序法, 比qsort还快. 是O(n), 但是是用空间换时间.

 可以generalize为更实用的radix sort.
> A type of bucket sort called the counting sort --- [IBM link](https://www-927.ibm.com/ibm/cas/hspc/student/algorithms/BucketSort.html)
* bucket sort, radix sort, bubble sort, counting sort --- [University of Washington CSE 373](https://courses.cs.washington.edu/courses/cse373/13wi/lectures.shtml#today)
    * 注意这里的最后一步就是Concatenation bucket到原array的时候有3个var, 不要搞混了.
* 注意ganker的内循环判定的条件是`A[i] != A[A[i]-1]`. 而且最后要`i--`. 为什么不能换成`A[i] != i+1`. 
    * 因为若是换成直接判断A[i]!=i+1的话, 则会导致在重复字符的情况下出现死循环.
    * 之所以i--. 是因为交换完之后不一定是正确的. 例如{3,1,2}一开始的3,2交换为2,3后, 2并不是正确位置. 所以再判断2,1并交换才对.
    * 所以Ganker说这道题目很简单, 但其中包含的算法思想和编程基础很适合面试!

### Largest Rectangle in Histogram

### Scramble String: 3D 动态规划经典
* Google考过, 见[MITBBS 2012年帖子](http://www.mitbbs.com/article_t/JobHunting/32107851.html)
* 先理解这个Scramble是什么意思: `s1 = "great", s2 = "rtgae";`就不是有效的. 注意要求是
* 这道题目可以用recursion, 更可以用DP. 是一道经典的3D DP. 但正如段公子所说: DP table可以用array也可以用HashMap, 可见武大csdn的hashmap解法. 但是觉得这个和N00t的backtracking差不多? 看看九章算法主页君的就搞懂了, 其实不一样.
* 参考的: 
    * [fightforyourdream的recursion+DP](http://blog.csdn.net/fightforyourdream/article/details/17707187).
    * [武大CSDN的Hashmap动态规划](http://blog.csdn.net/whuwangyi/article/details/14105063), 
    * [Unieagele解题博客的3D动归](http://blog.unieagle.net/2012/10/23/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Ascramble-string%EF%BC%8C%E4%B8%89%E7%BB%B4%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/)
    * [九章算法主页君--CMUyu](http://www.cnblogs.com/yuzhangcmu/p/4189152.html)
        * 分析的答案很适合面试的顺序: recursion->剪枝->Top-down DP(memorize)->Bottom-up DP(iteration).
        * 而且时间空间复杂度分析的很详细.

    * Ganker的3D动归, 和N00t的意思差不多. 不过Ganker的分析不错.
        + 难点在于for loop的顺序. 最外层应该是len. 因为是bottom-up. 从小问题推大问题. 或者说大问题的解可以使用小问题的解得到, 而不用反复求解同一个小问题.
        > 对于判断这些左右部分是不是scramble我们是有历史信息的，因为长度小于n的所有情况我们都在前面求解过了（也就是长度是最外层循环）。
        
        + 有点没搞明白: 为什么Ganker说:
        >如果以上两种情况有一种成立，说明s1[i...i+len-1]和s2[j...j+len-1]是scramble的。
    * 水中的鱼的recursion, 他的剪枝很好, 可以AC. 而N00t的原始的recursion(即brute-force的剪枝仅仅比较s1,s2的长度, 是很低效率的, 所以过不了).
    * N00t的递归, DP (bottom-up) 就是iteration, 而LCA的DP (bottom-up)则是recursion. 那么这个是怎么想出来的呢: 
> + 先理解递归是怎么做的: 从大到小substr判断. 即切割成substructure/subproblem.
  + For each pair of (n-1)-char-long substrings of the two strings, are they scramble to each other?
  + For each pair of (n-2)-char-long substrings, are they scramble?
  + ... ...
  + For each pair of 2-char-long substrings, are they scramble?
  + For each pair of char in the two strings, are they scramble (i.e. do they equal)?
  + That is saying, we can build up a table and solve the problem in a bottom-up fashion.
  + 然后还有就是这里的递推式一开始没有理解. 看了`fight for your dream`懂了: 还是recursion的想法,2种情况: 前前&&后后||前后&&后前. **注意: 这种var(i,j,k,p)变多了之后不要搞混了. 最好的方法就是写一个例子出来.

### Interleaving String: 2D/1D 动态规划
> 凡是substring的题目, 都是自动脑补动态规划, 正如Tree的题目自动脑补分治法.

#### 方法1: recursion
* 当然, 最好先从简单的做法入手, 先有个做对的方法: recursion是DP的前提. 或者说有了递归的思路就好想出来DP了.
    * 对于在recursion call里面处理下一个substring的话, 即修改index, 有2种方法. 
        1. 例如remove duplicate from string, 因为简单的赋值. 所以res[j++] = s[i++];
        2. 在N00t的recursive call里面, 因为一句话里面有2个地方用到i,k. eg: `s1[i] == s3[k] && isInterleave(s1, s2, s3, i+1, j, k+1)`. 我一开始是用++i, j++. 这样很容易弄混. 直接干脆用`i+1`. 
        3. 总结一下: 如果是简单的method call, 可以使用`++i; j++`. 不过长一点的statement还是不要弄晕自己, 代码最好不要依赖于statement的顺序, 所以还是原始的`i+1`.
        
![2D打表图示](http://7xj2zx.com1.z0.glb.clouddn.com/freq2_interleave.jpg)
#### 方法2: 2D 递归
* 既然recursion已经想出来了, 也就弄明白了? (not yet! 想法出来还要写代码实现, 里面很多门道和细节的设计)
* 首先是边界条件, 或者说base case的设计: 有个trick, 每次遇到string的DP, 都要增加一个空位. 表示s1, s2都不取的情况. 所以Opt[0][0] = true. 这里参考的[popfish的算法路blog画的2D 打表图示](http://blog.csdn.net/u011095253/article/details/9248073). 我一开始做的时候, 在打1st row和1st col的时候直接就是比较substring了. 但是结果不对. 为什么呢: 因为substring没用对.
    * 有2点关于substring要注意
        1. substring上界是开区间. 所以`substring(0,0)`是`""`, (0,1)才相当于`charAt(0)`.
        2. 刚发现: `"n"=="n"`是true, 但是`"ni".substring(0,1)=="no".substring(0,1)`居然是**false**! 刚查了[SOF: how to compare string](http://stackoverflow.com/a/513839), 以及[SOF: string object vs string literal](http://stackoverflow.com/questions/3297867/difference-between-string-object-and-string-literal), 才知道: 
        > ~~**Java的`==`比较*string*的时候比较的是reference, 而不是value!**.~~ 应该说: Java 的 "=="比较object的时候, 是看是否为同一个reference.  
        所以2个substring的reference不同, 当然就是false!

#### 方法3: 1D 递归. 参考Ganker的解法.
* 注意到2D的递推式只和上一层和左边的有关: `opt[i][j] ~ {opt[i-1][j], opt[i][j-1]}`. 所以可以只用一个1D 的 dp表就可以了. 当然这个时候就要小心了. 有几点:
    1. 长度多少? 意义已经改变了.
        * 对于处理2个string的问题, 有个trick: 一般都最好选择短的那个处理, 可以节省空间或者时间.当然`opt[minWord.length()+1]`, 而且因为是需要update 短的opt长度. 所以将opt update放在内层短循环可以更加优化. 因为opt[0]~~的意义~~, 有2个含义:
            * 一个意义是到外层循环到i的时候, opt[0]表示第一列的第i个时候, 表示原本2d中的opt[0][j]. 即看s3[0...j]是否可以用MaxWord[0...j], 而没有MinWord. 来interleave.
            * 第二个意义是: 内层循环到j的时候, s3[0...i+j+1]是否可以由maxWord[0...i]minWord[0...j]来interleave.
    2. 因为外层循环maxWord, 内层循环minWord. 所以1st row可以单独初始化. 而1st col, 即maxWord的interleave的base caseze'y则要在外层循环中初始化. 而将minWord放在内层循环upodate可以节省DP表空间.
    3. 最关键的在于递推式现在怎么样勒?
        * 我一开始就搞错了. 一定要注意: 1D其实就是2D压缩而来的. 所以就是2D的简化版. 所以要和2D的递推式紧密联合起来想. 
        * 2D的递推式: `opt[i+1][j+1] = opt[i][j+1] && s1[i] 或者 opt[i+1][j] && s2[j]`. 所以`opt[j+1]`和上一层或者左边有关: `opt[j+1] = opt[j+1] && maxWord[i] 或者 opt[j] && minWord[j]`. 关键在于搞清楚现在上一层和左边需要的char是哪一个. 注意这里的j是横轴啊! 
            * 所以2d里面的左边, 即opt[i][j+1]则相当于1d里面的(opt[j])就是由2d里面的s1(横着的string). 
            * 而j+1, 就是相当于2D里面的上一层, 即相当于opt[i+1][j]. 所以是和2d里面的s2(竖着的string), 所以是maxWord的char.

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
    * 这很重要. 因为recursion就是要一步步进入子问题, 解决, 跳出递归, 到保存到stack的以前的现场. 所以ganker也强调了这里要在helper()后面恢复现场, 即list.remove(list.size()-1), 以及初始化used[i]. 即used[i] = false.
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
* Hello WOrld

### Recover Binary Search Tree
* 再次加深了对Recursion的理解. 2个不连续的recursion. 而且param还不对称, N00t的recursion写的真好. N00t的方法也很好. 
### Distinct Subsequences
 > +When you see string problem that is about subsequence or matching, dynamic programming method should come to your mind naturally. ---by [link](http://www.cnblogs.com/springfor/p/3896152.html)

 *  DP的经典题目. N00t的做法也是可以的. 但我倾向于Ganker/SophieJ. 但是要理解DP公式的含义. 不然连边界条件或者初始条件都搞不出来. Ganker的2D分析, 然后code则是优化为1D dp的解法一开始没看懂, 然后Ganker在回复里面解释了为什么j是从T的**从后向前扫**. 这是因为这里的**DP是想使用update之前的值**, 所以这样. 如果以后有个DP问题是要使用更新后的1D dp, 则是从头往后扫.
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





