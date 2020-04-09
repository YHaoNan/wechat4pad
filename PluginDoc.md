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
            "name": "设置名称",
            "desc": "描述",
            "type": "设置类型，支持Int,Float,Bool,String",
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

## JQuery支持
JQuery已经作为内建插件内置在WeChat4PAD中，并保证在所有插件注入之前注入，所以插件开发者无需自行注入

## 关于
目前只是粗略的定义了这些内容，然后一遍修改一遍完善文档
