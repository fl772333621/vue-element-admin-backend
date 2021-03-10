# vue-element-admin 专用后端项目Demo

https://github.com/PanJiaChen/vue-element-admin 该前端项目专用java后台实现。（基于提交的commit为 docs: add coupon @PanJiaChen committed on 8 Sep 2020）
> 集成 security 和 jwt，没有使用数据库去校验用户是否合法，仅靠代码判断。<br />
> 如下步骤将引导你让前后端项目配合跑起来~~

## 第一步、vue-element-admin 项目启动

### .env.development 文件调整（必须）

目标：转发后台请求到 vue-element-admin-backend
> VUE_APP_BASE_API 路径调整为 vue-element-admin-backend 启动后的ip及端口 <br />
> 示例 VUE_APP_BASE_API = 'http://localhost:8080/'

### index.vue 文件调整（非必须）

目标：停止登陆校验
> loginRules 中 删除 validator: validateUsername 和 validator: validatePassword

### npm install 、npm run dev

## 第二步、vue-element-admin-backend 项目启动

### 数据库配置

虽然用到了数据库配置，但是暂时没有添加任何查数据库的操作。

## 第三步、登陆 http://localhost:9528 尝试新版后端支持吧~

注意：后端支持已经实现的功能如下。<br />
登陆功能（JSON、POST、有参）UserController.login <br />
登出功能（JSON、POST、无参）UserController.logout <br />
用户信息（JSON、GET、有参） UserController.info <br />
交易列表（JSON、GET、无参）TransactionController.list
> 功能已经全覆盖支持，更多功能复制Controller接口即可

token方面<br />
支持从header的X-Token字段获取为token <br />
支持从url的路径参数token内获取为token <br />
兼容GET和POST请求，并对OPTIONS请求不做token校验