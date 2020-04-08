![示意图](sc1.jpg)

## WeChat For PAD
WeChat For PAD是为解决Android平板上没有独立的微信而编写的

以往在Android平板上使用微信有两条路，一是使用手机版微信，这样会造成手机上的微信下线，手机和平板无法同时在线; 二是使用网页版，不过网页版默认是不支持手机浏览器的。即使通过修改UA标识，在浏览器上使用网页版，体验也不尽如人意。。。

WeChat For PAD使用Android原生WebView和微信网页版作为基础，通过注入JS和CSS代码对网页版进行优化和编写新功能，从而提供更适合Android平板的微信。

## 项目进度
- 2020/4/08  
    创建项目，实现了CSS和JS的注入功能，并且提供了一个基本的，适合平板的UI，今天可能懒得再写了...并且给此项目想了个Slogan：Wechat For PAD —— 一切因穷而起

## 期望的功能
- **!!!1st. 最期望的是我不要弃坑**
- JS和CSS注入插件化，提供插件市场
- 发送消息通知
- 多种自带主题、夜间模式
- 防撤回
- 比网页版更多的表情，和手机版同步
- 优化平板操作手感

## 灵感来源
[electronic-wechat](https://github.com/geeeeeeeeek/electronic-wechat/)