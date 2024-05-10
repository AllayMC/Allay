---
comments: true
---

在allay中，权限是一个树结构

### 表示一个权限节点

权限由字符串表示，点号表明了节点间的父子关系

例子：

```allay.cmd.tell```

```allay.cmd.help```

```allay.text.color```

### 权限的匹配（测试代码见test/perm/PermTreeTest.java)

#### eg1:

令玩家cool_loong拥有权限```allay.cmd.tell```

则玩家cool_loong拥有权限:

```allay```
```allay.cmd```
```allay.cmd.tell```

但不拥有权限```allay.cmd.help```等

#### eg2:

令玩家cool_loong拥有权限```allay.cmd```

则玩家cool_loong拥有权限:

```allay```
```allay.cmd```

但不拥有权限```allay.cmd.tell```, ```allay.cmd.help```等