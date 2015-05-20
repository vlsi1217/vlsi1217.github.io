title: "Room plan"
date: "2014-01-15 20:17:16"
tags: misc
categories: Thinking
description: "A collection of Hello World applications from helloworld.org."
toc: true
---

# 加入Github代码:
{% codeblock _.compact http://underscorejs.org/#compact Underscore.js %}
_.compact([0, 1, false, 2, '', 3]);
=> [1, 2, 3]
{% endcodeblock %}

# 书房布置
* 最近看了中国的Golang/Docker推广人: 马道长的博客, 发现他的书桌真是很赞. 其实很简单, 就是macbook+LCD+机械键盘. 感觉很舒适.
![马道长](http://7xj2zx.com1.z0.glb.clouddn.com/desk.jpg)

# 测试gist:
<script src="https://gist.github.com/4505639.js?file=macroBuild.scala" type="text/javascript"></script>

# 2
<script src="http://gist-it.appspot.com/https://github.com/vlsi1217/LeetCode-1/blob/master/bfs/Solve_SurroundedRegions.java">
</script>

# 测试Data Files:
{% for link in site.data.menu %}
  <a href="{{ link }}">{{ loop.key }}</a>
{% endfor %}
