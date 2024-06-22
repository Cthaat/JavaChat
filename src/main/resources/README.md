# Java Chat
### 一个基于**JavaFX**的聊天客户端 

<p align="center">
  简体中文
  ｜
  <a herf="https://github.com/Cthaat/JavaChat/blob/master/README.md"> English </a>
</p>

---

## 简介

Java Chat 这是我大一下半学期的期末大作业，界面采用比起swing较为现代的JavaFX，数据库是使用Mysql，
实现了基本的聊天功能，包括发送消息、接收消息、查看历史消息、用户注册、登录等。
同时使用Redis缓存用户信息，提高了用户体验。
具备完整的图形界面，UI设计方面使用SceneBuilder，创建fxml文件，并使用CSS样式化界面。
这是我的第一份好好写的大作业了，各位不妨点个星星支持一下qwq。🤣

## UI设计

 - 登陆界面 <br/>
 
![登录界面](https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/runTimePic/logIn.png)

 - 注册界面 <br/>

![注册界面](https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/runTimePic/logUp.png)

 - 错误界面 <br/>

 ![错误界面](https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/runTimePic/logInErroe.png)

 - 用户列表 <br/>

![注册界面](https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/runTimePic/main.png)

 - 聊天房间 <br/>

 ![聊天房间](https://github.com/Cthaat/JavaChat/blob/master/src/main/resources/runTimePic/chatRoom.png)

> UI设计的比较丑陋，后续会优化
> 如有建议欢迎提出issue
> 下边就是实现功能了 😊

---

## 需求和实现

#### 登录
 
 - [x] 使用账户密码登录
 - [x] 错误提示
 - [ ] 记住密码
 - [ ] 自动登录
 - [ ] 管理员和普通用户分开登录
 - [ ] 验证码

#### 注册

 - [ ] 输入框验证
 - [ ] 验证码
 - [ ] 注册成功提示
 - [ ] 头像设置
 - [ ] 邮件验证激活

#### 用户列表

 - [x] 自己的好友列表
 - [x] 加好友
 - [x] 删除好友
 - [x] 聊天
 - [ ] 历史消息
 - [ ] 群聊
 - [ ] 黑名单
 - [ ] 同意添加好友

#### 聊天房间

 - [x] 发送消息
 - [x] 接收消息
 - [x] 历史消息
 - [ ] 实时消息
 - [ ] 图片、文件发送
 - [ ] 表情发送
 - [ ] 语音发送
 - [ ] 视频通话
 - [ ] 踢人
 - [ ] 禁言
 - [ ] 管理员功能

> 以上就是Java Chat的全部功能，后续会继续完善 <br/>
> 没有完成的功能会慢慢学 <br/>
> 如果你觉得功能足够当作大作业的话 <br/>
> 下面就是运行教程 <br/>
> 几步就可以快速开始运行 🥰 <br/>

---

## 运行教程

 - 目前作者没有自己的服务器🙁，所以需要自己搭建服务器
 - 服务器端在 <a href="https://github.com/Cthaat/JavaChatSer" target="_blank">JavaChatSer</a>
 - 后续会把他部署到服务器上，方便大家使用

--- 

## 服务器部署


 - 将服务器端代码clone到本地
 - 导入Mysql数据库，创建数据库和表
 - 使用`src/main/resources/javaChat.sql`来创建数据库表
 - 下载redis作为缓存数据库
 - 启动redis服务器
 - 修改配置文件中的数据库连接信息
 - 配置信息都在`src/main/resources`资源文件夹下
 - 启动客户端，连接服务器
































