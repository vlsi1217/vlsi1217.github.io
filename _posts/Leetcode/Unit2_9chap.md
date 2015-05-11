title: "Unit 2 九章算法课: Binary Search"
date: "2015-05-06 20:17:16"
tags: [Leetcode, 九章]
categories: Interview
description: "A collection of Hello World applications from helloworld.org."
toc: true
---

## 2分法模版
```
while (start + 1 < end) {
    mid = start + (end - start) / 2;
    if (A[mid] == target) {
        end = mid;  // key
    } else if (A[mid] < target) {
        start = mid;
    } else if (A[mid] > target) {
        end = mid;
    }
}
if (A[start] >= target) {
    return start;
} else if (A[end] >= target) {
    return end;
} else {
    return end + 1;
}
```
* while退出条件: lo+1<hi
* 中间处理通用: 都是lo=mid, hi=mid. 不用mid+1/mid-1.
* while循环结束后, 只要处理2个数就可以了: start和end. 可以很容易分析.
    * 这里的start/end的意义是什么呢? 并不一定是target的范围就已经确定了, 例如有dup的时候, 就要看你是求first, any, 还是last了.
    * 或者说target不再A[]里面的情况呢? 那么target < A[lo] 或者 A[hi] < target.

###从复杂度->算法
* 一般的复杂度有O(n), 比他好的就是O(lgn). 那就只有二分了. 所以看到这种复杂度要求的题目, 就是用二分模版.

### p1. search insert position
* 其实根据题意了解到, 就是找first position i, that A[i] >= target. 所以对于这种sorted array的search first/last position就是用二分法. 
* 模版是要灵活运用的, 只是说这样写不太容易出错. 但是也要在适当范围内根据问题来做. 譬如insert的target不再A[]内会怎么样呢? 实际还是只要根据lo/hi这2个数分析就好, 但是这里学到了有了range, 有了lo/hi, 并**不一定**就是分3段: 左中右. 这里的分法有意思. `<= low, <= hi, 和大于hi.`

## Sorted Array 问题
### 重要的2题: Search in rotated sorted array
* binary search
    + 重要做题概念: 做题先画图, 容易直观分析.
    * 如果有duplicate, 就没得2分. 因为lo=mid=hi = 1的话, 所以2分无意义.
* 不用二分做
    * find minimum in rotated sorted array. 也是2分.
    * recover rotatted sorted array.
    * 然后用min来search, 或者recover在search.

#### p1. Find minimum in Rotated sorted array I/II

### 重要的2题: Median of two sorted arrays
* 这题的算法思想很重要. 能提升自己的能力.
* 先做: find kth int of two sorted arrays
    + 其实就等于做出来median这题了.
* 注意 k-k/2!=k/2. 因为有奇数偶数的情况. 所以统一用k-k/2.

#### kth of two sorted arrays

## 其他简单
* merge sorted array/list.
    + 小技巧: 从末尾开始插入.
    + 
* 三步反转法

