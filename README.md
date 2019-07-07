
提示：我们十分欢迎您的参与！如果您有任何反馈，可以再[CSDN](https://blog.csdn.net/weixin_43125410)给我留言。
# StockSpark（股票信息网）
StockSpark是股票信息查询和展示网站，后端通过springboot、Jsoup、mybaits来实现股票信息的爬取、加入数据库、job定时器等功能。前端采用[layui](https://www.layui.com/)结合Thmeleaf模板引擎开发界面，数据图表展示选用的[百度echarts](https://echarts.baidu.com/)
主要功能包含：
  股票信息的爬取、查看股票、查看股票历史行情、查看股票近一月的情况、查看所有股票30天内涨幅超过5%的次数
## 1.工具信息介绍
* 开发工具：Idea
* 编码集:utf-8
* 数据库：MySql
* 爬虫工具：Jsoup
## 2.实现功能
* 获取所有股票的代码
* 自动爬取十年内的每天的股票信息
* 展示三十天内涨幅超过5%的次数
* 每日四点更新今日的股票详情
* 前端展示股票信息
* 用echarts 实现历史详情、涨幅次数和近一个月的数据图表
## 3.
