title: "Hexo搭建的经历和问题"
date: "2015-01-15 20:17:16"
tags: webApp
categories: Web
description: "Nice Hexo and Freemind"
toc: true
---

#这里记录Hexo搭建的经历和问题

---
## 20150519
### 怎么更好的铁贴码?
* 我想在每一个解题报告里面加入github, 有什么好的办法呢?
	* gist-it.
	* <script url-to-gist /script>
	* data-file-folder

## 20150513
* 因为主要还是在zybuluo里面写markdown. 写完之后放到Hexo并deploy. 所以就直接用img的链接. 一开始试了一下google drive, 分享链接不太好用(要选择是public的程度). 所以还是使用了七牛. 发现还挺方便的. 而且1G空间很够用了. 而且读取很快. 不错. 
* 所以以后就是图片本地还是放在img文件夹里面. 不过同时上传到七牛. 最好还是压缩一下. 有的图片大于1M了.

## 20150508
* 今天主要的任务是把我的github.io从wiki-in-box搬到了Hexo-Freemind. 感受到了强大的Hexo.
* 不过其中遇到了不少问题. 所以也用了挺长时间做:
	1. disque怎么弄? 原来是名字是'abc123', 而不是'<abc213>'. 所以造成了无法和我在disqus里面注册的.
	2. RSS/SiteMap怎么弄? 我本来是按照教程直接npm install这2个plugin. 但是hexo g之后并没有产生atom.xml/sitemap.xml. 然后才发现这个plugin应该安装在hexo/tonyhexo里面, 而不是安装到`C:\MOOC\NodeJS_Intro\`下面. 所以又倒来倒去. 
* 主要参考的: 
	* [如何搭建一个独立博客](http://cnfeat.com/2014/05/10/2014-05-11-how-to-build-a-blog/#). 这里还讲了如何DNS,
	* [Hexo博客优化](http://baoxiehao.com/2014/05/17/Hexo%E5%8D%9A%E5%AE%A2%E4%BC%98%E5%8C%96/). 这个讲了如何使用google analytics和sitemap的用法.
* 但是发现Hexo还是有些缺陷:
	+ 例如N00t的博客, 她的search可以实时先是match的blog, 而且还能多种显示博客的形式.
	+ 这个markdown和作业部落的不太一样, 不过差别不大就是了.
* 方式: 
	+ 主要是在作业部落上面写好, 然后再放到这个Hexo上面. 因为作业部落确实是markdown很友好. 不过Hexo是我的个人博客. 更大自由.
