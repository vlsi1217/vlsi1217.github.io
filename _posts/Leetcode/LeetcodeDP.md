title: "leetcode中学习DP"
date: "2015-02-21 20:17:16"
tags: Leetcode
categories: Interview
description: "Leetcode总结题型."
toc: true
---

> + 这一篇想整理一下在LC中学到的DP. 一是学习如何推DP公式, 一是学如何优化.
+ 另外2篇是: 
    + Leetcode中学习Recursion.
    + Leetcode中学习贪心
    
# 学习
## CLRS
* rod cut
## Stanford
* WIS

## ACM 资料
* [DP的3个特性](http://www.douban.com/note/213102442/)
    * 这个豆瓣文章不错, 讲了基本上面试会用的所有基本数据结构和算法.
    * 其中是3个特性: 
        1. 最优子结构
        2. 无后效性
        3. 空间需求度
    
# 最简单的DP: Unique Path

# 2D DP经典: distinct subsequences

# 2D DP优化为1D DP的经典: Distinct Subsequences/Coin changes
## 分析来自[CSDN](http://blog.csdn.net/kenden23/article/details/19332545)
* 

# 2D DP经典: LCS
## Naive recursion
## 2D DP
* DP打表有个关键: 先构造好表, 理解并初始化边界, 然后找规律填几行. 
## 1D DP (真1层)
* 我昨天就是想当然的直接像coin change那样直接改成1D, 其实是不对的, 因为不是opt[i][j] ~ opt[i+1][j], 而是opt[i+1][j+1]. 因为我们的opt[i][j]的定义是x[i...M] vs y[j...N]. 所以必须从后往前/从下往上扫. 所以j+1在被j使用之前就改了. 因为这里打表是想使用修改前的值. 而且不能从反过来(前向后扫), 因为这里和[ganker distinct subsequence]()不同???
## O(1)D的DP, 也就是1,2,3, ... 的常数层
* 其实我使用的[0..1/2/3][0..N]这样一个[**滚动数组**](http://sxyckjzh.blog.163.com/blog/static/32629815201361010642951/)
* 如果是3层的滚动数组, 如POJ 1159 Palindrome的话, 可以用取mod来找到正确的行, 如[ACM女神](http://www.cnblogs.com/PJQOOO/p/3900677.html) 也就是常用的方法(见Valid sudoku). 如果是2层的滚动数组, 可以用我在LCS里面的双buffer. 或者可以直接使用2层的数组做(可以mod, 或者^1), 见[dp[k][j] = dp[k^1][j-1] +  1](http://www.ahathinking.com/archives/115.html#more-115)
* 参考资料: [这个分了DP类: 斜率, 压缩](http://blog.csdn.net/u011262722/article/details/10037855)
* [ACM分类, KMP7题](http://blog.csdn.net/lp_opai/article/details/43408081)

## LCS经典变形: POJ 1159 Palindrome
* 实际上LCS这里的subsequence是不连续的顺序子串. 所以可以利用这一点来解决Palindrome问题.
* 只要将给的String 反转, 比较LCS, 然后加入len-LCS即可.
* 如果要返回得到的Palindrome怎么弄?

## 2D vs 1D
* 关键就在于可不可以recover. 例如


