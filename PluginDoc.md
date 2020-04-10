## 文件结构
除了一个config.json是必须存在的其余都无所谓。

一个最简单的插件即只有config.json描述文件的插件，它什么都不做。

## config.json结构
```json
{
    "name": "插件名",
    "desc": "插件描述",
    "author": "作者名",
    "icon": "插件图标",
    "settings": [
        {
            "id": "设置的唯一id",
            "name": "设置名称",
            "desc": "描述",
            "type": "设置类型，支持int,float,bool,string",
            "default": "默认值"
        },
        {
            ...
        }
    ],
    "inject": [
        "xxx.css","xxx.js"
    ]
}
```
如上是插件的所有字段，`settings`中的字段是插件的设置项，可以由用户设置，`inject`中的是需要向页面注入的文件，只支持css和js并且必须明确的写出小写扩展名，在页面加载完成后，`inject`中的文件将会按顺序注入到页面中。如上所有字段必须存在于config.json中并严格按照格式编写。

## 插件设置
`settings`字段中的所有设置项可以由用户进行自定义设置，插件开发者可以在js代码中通过`__env__`这个全局变量获取用户设置。

### 第一种方法
```js
__env__.getSetting("设置id")
```
将返回一个JSON字符串，包含
```js
{
    "类型": "值"
}
```
这个类型即你在config中编写的`type`字段，如果你定义了一个`bool`类型的设置，就要取返回值中的`bool`字段，其他字段为null。

以布尔类型设置为例，第一种方法完整的写法是
```js
JSON.parse(__env__.getSetting("xxx.xxx")).bool
```
### 捷径
上面显然很麻烦，得自己转成JSON再取对应的类型，但是由于Android WebView API的限制，只能返回原生类型，我貌似只能这样实现。但是我提供了几个其他方法，隐式的自动帮你完成那些操作，更方便的同时代码的可读性也会提升。

```js
__env__.getBoolSetting(id)
__env__.getFloatSetting(id)
__env__.getStringSetting(id)
__env__.getIntSetting(id)
```

如上四个方法可以直接返回对应类型的值

### 关于类型
目前还不会检测你获取的类型和预先在`config.json`中定义的类型是否匹配，若不匹配将造成类型转换错误，可能出现闪退

### 设置ID
如果你开发过VSCode插件，那么你可能对这种使用一个全局唯一的id来定义一个设置项并且使用id获取它的方法格外熟悉。VSCode就是采用了这种方法。

如果你没有开发VSCode插件的经验，那你一定要注意，这个id是全局唯一的，全局惟一的！全局惟一的！如果重复会抛出`SettingIdAlreadyUsedException`。所以定义id的时候请使用完全限定名降低重复的风险。

```
site.lilpig.wx4padplugin.show_deleted_msg.is_open
```

如上是一个完全限定名作为id的示例，它由`根域名.开发者网站域名.开发者网站二级域名.插件名.设置选项名`组成

## JQuery支持
JQuery已经作为内建插件内置在WeChat4PAD中，并保证在所有插件注入之前注入，所以插件开发者无需自行注入

## 关于
目前只是粗略的定义了这些内容，然后一遍修改一遍完善文档
