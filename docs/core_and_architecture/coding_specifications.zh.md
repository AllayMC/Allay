---
comments: true
---

[//]: # (PS: 需要进一步讨论)
[//]: # (项目编码规范大体上基于[Google的Java编码规范]&#40;https://google.github.io/styleguide/javaguide.html&#41;，但在一些方面上可能有差异)

## 禁止使用@NotNull和@Nullable注解

在之前，我们的项目使用了大量的此类注解，
但是后来我们注意到这几个注释带来的好处十分有限，而坏处却十分明显

具体来说，这几个注释只能起到标识作用，并不能提供任何编译期的检查。

另外，我们注意到IDEA的误报率非常高: 编辑器并不能执行高级语义分析，导致在一些不可能出问题的点位报黄，引起极大混乱

鉴于此，我们规定，在任何情况下禁止使用@NotNull和@Nullable注解

## @Range的使用

@Range同样也是一个纯标识作用的注解，它可以使ide提供数值参数的范围检查。
但是请注意，@Range本身不带有任何真正的检查，你仍需要加上检查代码。

```java
public void setBlockInChunk(@Range(min = 0, max = 15) int x, @Range(min = 0, max = 15) int y, int z, BlockState block) {
    // 假设这边x和z的值应该在[0, 15]范围内
    if (x < 0 || x > 15) error();
    if (z < 0 || z > 15) error();
    // ...
} 
```

我们规定：
- @Range注解必须与显式代码检查一起使用，即@Range不得单独使用（避免给调用者一种“有@Range注解保驾护航”的错觉）
- 对于参数存在范围或其他限制的方法，方法体内必须进行显式检查。对于数值参数，建议联合@Range注解一起使用

## 类注释

Allay要求在每个类文件上按照一定格式标明信息，这是一个例子：

```java
/**
 * Allay Project 2024/6/1
 *
 * @author daoge_cmd
 */
class XXX {
    
}
```

## 减少嵌套层数

这是一个老生常谈的问题了，嵌套过度的代码可读性特别差，而减少嵌套层数可以带来很大的好处。
以下有一些常用的减少嵌套层数的方法：

### 卫语句

卫语句是一种编程技巧，它将复杂的条件表达式拆分成多个条件表达式，减少嵌套。
如果条件语句极其复杂，就应该将条件语句拆解开，然后逐个检查，并在条件为真时立刻从函数中返回，这样的单独检查通常被称之为“卫语句”。
卫语句的效果就是将原来需要仔细阅读代码、细心整理逻辑的条件判断整理成一眼能看透的逻辑关系。

举个例子：

原始代码
```java
public static void main(String[] args) {
    for (int i = 1; i <= 100; i++) {
        if (i % 3 == 0) {
            if (i % 4 == 0) {
                if (i % 5 == 0) {
                    System.out.println(i);
                }
            }
        }
    }
}
```

重构后
```java
public static void main(String[] args) {
    for (int i = 1; i <= 100; i++) {
        if (i % 3 != 0) {
            continue;
        }
        // or
        if (i % 4 != 0) continue;
        if (i % 5 != 0) continue;
        System.out.println(i);
    }
}
```

只有在上面三个条件都不成立的情况下，才会走到最后一步输出的语句。也就是排除那些不符合条件的情况，剩下的自然就是符合条件的了。

## 提取方法

部分方法承担的功能重，逻辑复杂，往往代码嵌套也非常严重。
这时我们可以考虑提取方法，将一个方法拆分为多个方法调用的组合

## 善用return（和卫语句类似）

在C时代，单一出口原则被广泛认同，即“一个方法内只能有一个return”。
我们认为这过分严格了，至少在某些情况下，提前return可以降低嵌套层数：

原始代码
```java
public static void main(String[] args) {
    if (args.length == 0) {
        return;
    } else {
        // Do something else
    }
}
```

重构后
```java
public static void main(String[] args) {
    if (args.length == 0) {
        return;
    }
    // Do something else
}

```

这是一个十分简单却十分有效的技巧