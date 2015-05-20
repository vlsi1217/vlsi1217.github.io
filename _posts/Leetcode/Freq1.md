title: "Leetcode freq 1"
date: "2015-01-16 20:17:16"
tags: Leetcode
categories: Interview
description: "A collection of Hello World applications from helloworld.org."
toc: true
---

## 20150122
### Length of Last Word
* 额, 简单题...

### Same Tree
### Maximum Depth of Binary Tree
* DFS或者BFS都能解决. 其中BFS的N00t的写法是queue+Null nod作为该level的结束, 并且只有while没有内层的for loop

### Minimum Depth of Binary Tree
* 还是DFS跟BFS都能解决. 但是BFS在这里效率更高, 因为只要找到第一个leaf就返回.

### Word Ladder II
### Longest Common Prefix
### Pascal's Triangle
### Pascal's Triangle II

## 20150120

### Best Time to Buy and Sell Stock II
   * 参考[CSDN的FightForDream分析DP](http://blog.csdn.net/fightforyourdream/article/details/14503469)


### Best Time to Buy and Sell Stock III

### ZigZag Conversion

### 3Sum Closest

### Longest Valid Parentheses

### Permutation Sequence
* 数学找规律题目. N00t的例子加上Ganker的解释. 很容易理解. 关键是code要写的快.

## 20150115
### Substring with Concatenation of All Words
* 这道题目和Longest non-repeated subsequence都是用Ganker大大的双pointer窗口大法.
* 看了N00t的才知道原来Ganker这种双指针法~~原来就是简化的KMP string match算法~~. 并不是. KMP的关键在于preprocess了一个DFA, 所以能够实现O(n).

### Simplify Path
* 先读懂Unix中的path的op意义. 发现[阿猫阿狗blog](http://huntfor.iteye.com/blog/2066875)的解释最清晰易懂. 我也测试了一下: 确实就是很简单的规则: `/.表示当前目录，/..表示上级目录，/表示根目录`. 如下图所示: 
![freq1SimPath](http://7xj2zx.com1.z0.glb.clouddn.com/freq1_simpPath1.PNG). `cd /MOOC/./../MOOC`. 以`/`为分界来认识就是: cd /(回到根目录); MOOC(去根目录下的MOOC文件夹); /..(返回上一级, 即回到c:); /.(表示当前目录, 所以还是在c:); /MOOC. 又去到MOOC文件夹. 所以执行完后表示还是去到了MOOC文件夹中. 而在command里的第二行则直接cd MOOC是找不到的, 因为pwd是c:\MOOC\NodeJS_Intro. 题目已经说了给的是**absolute path**, 即从根目录开始的path. 所以打头是`/`.

* 这里再复习一下Stack/Queue: 
    1. 虽然Stack在Java.util已经实现了stack class, 但最好不要用, 因为很多问题. 参考[lmu课件](http://cs.lmu.edu/~ray/notes/stacks/). 所以Ganker也是使用的LinkedList来instantiate, 而且要注意, 这时候是: `LinkedList<Integer> stack = LinkedList<Integer>()`, 只是这个list叫做stack而已. 其实看了[SOF stack/deque](http://stackoverflow.com/a/12524949), 讲了Stack在JavaDoc里面也是推荐用Deque来当作stack用: `Deque<Integer> stack = new ArrayDeque<Integer>();`
    2. Queue更彻底, 是interface. 根本不能instantiate. 所以也可以使用LinkedList来实现: `Queue<Integer> queue = new LinkedList<Integer>()`.

* 细节在于Java里面的split(regex, limit)是split pattern之前和之后的. 所以splits[0]是"". 一个空的string. 参见[SOF: java split leading empty string](http://stackoverflow.com/questions/9389503/how-to-prevent-java-lang-string-split-from-creating-a-leading-empty-string). 即: split之后会出现的值是: "", "a", ".", "..". 所以当stack不空且".."的时候pop(). 当split的值是有效的char, 换句话说: 不是无效的char: "", ".", ".."的时候就push().
    
### Unique Binary Search Trees I
* 这是一个很有意思的题, 因为在于解题思路可以有2个方向
    1. 想明白之后可以直接是公式解法. 这个是数学的解法. 而且这个数学解法的Sigma的处理也学到了:就是loop里面`res+= func(i)`.
    2. 利用[水中的鱼所说的BST的性质](http://fisherlei.blogspot.com/2013/03/leetcode-unique-binary-search-trees.html): `lchild<root<rchild`. 所以如下图所示: ![freq1_uniBST](http://7xj2zx.com1.z0.glb.clouddn.com/Freq1_uniBST1.JPG). 这里参考的[SophieJ递归解法](http://jane4532.blogspot.com/2013/07/unique-binary-search-tree.html). 这里的rec(i)让我思考了半天, 他的含义并不是: 以i为root的解, 而是有效的node为i个的解. 譬如在for loop到2, 即以2为root的情况下, lway只能是1个有效的node, rway则是有2个有效的node. 所以`lway = rec(2-1); rway = rec(4-2)`.
* 所以我觉得这个题目相当有意思. 而且思路很清晰.

### Unique Binary Search Trees II
* 目前只看到N00t是用了recursion和DP来解决.
#### recursion解法
* 和分析的一样, 遍历l->r, 让每一个都有机会做root, 然后Divide递归左右子树的所有可能性(所以返回值是`List<TreeNode>`, 即以TreeNode的有效的BST). 然后Conquer遍历所有有效左右子树, 并接上Node(i).
* 有几点要注意:
    1. 正如Effective java item 43所说: return empty list rather than null. 
        * 这是一般情况下的trick. 而且这里还有她的实际意义: 即空树的root. 这样在接上Node(i)的时候, 是接上空树. 因为for each的时候一定要能see到空树. 而如果在l>r的时候返回null, 而不是res.add(null)的话, 就直接忽略了空树的情况. 更重要的是: 根源上避免了判断Null pointer. 如果我return null的话, 会出现`Exception in thread "main" java.lang.NullPointerException`
    2. 在Conquer的时候, 要记住每一组left/right subtree要接上一个new TreeNode(i). 因为是一个新的以i为root的BST.
    3. 设计return value为List<TreeNode>. 例如n=3的话, 最终返回的结果是[1,1,2,3,3]. 实际上是5个不同的BST. 这里我call了writeBST. 果然可以看到返回了5个serialize的BST: `[1, #, 3, 2, #, #, #]`

#### DP解法
* 注意到recursion的时候, 例如n=4的情况. [1,2]就给recursion了好几次: [1,2,3], [1,2]. 所以明显是可以保存计算过的结果来DP.
* 但是具体怎么写呢? 先搞懂N00t的思路. 但实际写起来很多地方要注意:
    * 虽然说是T[i,l]. 但实际上N00t是用的list<list<list<TreeNode>>>来写. 原因很简单. 就是前2个保存i和l. 最后一个保存T[i,l], 即满足这个range的BST的root和合集. 因为有了root, 就能得到tree(所以我在main里面最后call了writeBST()来serialize每个BST).
    * 边界条件很简单, 就是l=0. 
    * 那么打表是怎么个打法. 在interleaving string的DP的设计是T[i][j]. 分别是2个string的坐标, 所以无所谓哪个是外层,哪个是内层循环. 但是这里是T[i][l]. 这个l是长度. N00t的设计是**外层是l, 内层是i**. 所以是按照starting point分类的. 为什么这样loop呢?
    
### Serialize/De-serialize Binary Tree
* 先是什么是serialize/deserialize. 可以看水中的鱼的图解: [水中的鱼花树](http://7xj2zx.com1.z0.glb.clouddn.com/freq1_serilizeBinTreeYUBlog.PNG)
* pre-order traverse的一个应用. 从而可以解决isValidBST. 而且这个在Leetcode的Binary Tree很常见.
* 为什么Deserialize也是用pre-order呢? 这个顺序不会反吗? 感觉上觉得应该使用serialize的相反的traverse-->post-order. 但这样其实是不对的. 见[ITeye的分析](http://yuanhsh.iteye.com/blog/2171113). 这里我一开始做的方法也是使用的index来判断是读取list中的第几个字符. 但这里我就搞混了: 到底对于right subtree的index该用多少. 
    * 水中的鱼使用了一个int[1]. 然后java当作object, 可以传值. 而且也就避免了return. 
    * 我则是用的int, 所以必须return. 所以在left算完之后return的cnt必须是对应left subtree的最后一个node. return之后, right recursion的时候这个index还要再加一. 并且右子树结束后还要return给他的parent(注意这就是recursion后的code的意义: 往上走)
    * 1337原帖后面又一个Java写的. 我改了一点. 使用了stringTokenizer. 也是对的, 而且他并没有使用index来定位这个char, 而是使用了boolean left. 然后每次就是nextToken(). 也是可以的.


### Binary Tree Level Order Traversal II

### Triangle


---

|  ID   | Question        | freq  | 
|-----|-------------------------------------------|---| 
| 58  | Length of Last Word                       | 1 | 
| 100 | Same Tree                                 | 1 | 
| 104 | Maximum Depth of Binary Tree              | 1 | 
| 111 | Minimum Depth of Binary Tree              | 1 | 
| 126 | Word Ladder II                            | 1 | 
| 14  | Longest Common Prefix                     | 1 | 
| 118 | Pascal's Triangle                         | 1 | 
| 119 | Pascal's Triangle II                      | 1 | 
| 121 | Best Time to Buy and Sell Stock           | 1 | 
| 6   | ZigZag Conversion                         | 1 | 
| 16  | 3Sum Closest                              | 1 | 
| 30  | ~~Substring with Concatenation of All Words | 1 | 
| 71  | ~~Simplify Path                             | 1 | 
| 96  | ~~Unique Binary Search Trees                | 1 | 
| 107 | ~~Binary Tree Level Order Traversal II      | 1 | 
| 120 | ~~Triangle                                  | 1 | 
| 122 | ~~Best Time to Buy and Sell Stock II        | 1 | 
| 32  | Longest Valid Parentheses                 | 1 | 
| 95  | Unique Binary Search Trees II             | 1 | 
| 123 | Best Time to Buy and Sell Stock III       | 1 | 
| 60  | Permutation Sequence                      | 1 | 
| 金庸 |     飞雪连天射白鹿          |  小虾米 |





