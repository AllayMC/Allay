在allay中，权限是一个树结构

### 表示一个权限节点

权限由字符串表示，点号表明了节点间的父子关系

例子：

```allay.cmd.tell```

```allay.cmd.help```

```allay.text.color```

### 表示一组权限节点

可用通配符“*”表示此权限节点下可能的所有子节点

带通配符的例子：

```allay.cmd.*```

```*```

请注意，上述两个例子不指代一个具体的权限节点，而是一组权限节点

这意味着你不能在调用方法```hasPerm(String perm)```时使用通配符！

### 不带通配符时权限的匹配（测试代码见test/perm/PermTreeTest.java)

令玩家cool_loong拥有权限```allay.cmd.tell```

则玩家cool_loong拥有权限:

```allay```
```allay.cmd.```
```allay.cmd.tell```

但不拥有权限```allay.cmd.help```

### 带通配符时权限的匹配

令玩家cool_loong拥有权限```allay.cmd.*```


则玩家cool_loong拥有权限（只列举部分，由于存在通配符）:

```allay```
```allay.cmd.```
```allay.cmd.tell```
```allay.cmd.a```
```allay.cmd.b```
```allay.cmd.a.b```
```allay.cmd.a.b.c```
etc...

但不拥有权限```allay.eat.daoge```