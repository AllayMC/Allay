---
comments: true
---

[//]: # (PS: Further discussion needed)

[//]: # (The project coding standard is generally based on [Google's Java coding standard]&#40;https://google.github.io/styleguide/javaguide.html&#41;, but there may be differences in some aspects)

## Prohibition of using @NotNull and @Nullable annotations

Previously, our project used a large number of such annotations, but later we noticed that the benefits of these
annotations were very limited, while the disadvantages were very obvious.

Specifically, these annotations can only serve as identifiers and cannot provide any compile-time checks.

In addition, we noticed that the false positive rate of IntelliJ IDEA is very high: the editor cannot perform advanced
semantic analysis, resulting in yellow warnings in some points where problems are impossible, causing great confusion.

Therefore, we stipulate that the use of @NotNull and @Nullable annotations is prohibited under any circumstances.

## Use of @Range

@Range is also a purely identifying annotation, which can provide range checks for numerical parameters in the IDE.
However, please note that @Range itself does not provide any real checks, and you still need to add checking code.

```java
public void setBlockInChunk(@Range(min = 0, max = 15) int x, @Range(min = 0, max = 15) int y, int z, BlockState block) {
    // Assuming that the values of x and z here should be in the range [0, 15]
    if (x < 0 || x > 15) error();
    if (z < 0 || z > 15) error();
    // ...
}
```

We stipulate:

- @Range annotation must be used together with explicit code checking, that is, @Range must not be used alone (to avoid
  giving callers the misconception that "the @Range annotation ensures everything").
- For methods with parameter ranges or other restrictions, explicit checks must be performed inside the method body. For
  numerical parameters, it is recommended to use @Range annotation together.

## Class comments

Allay requires certain information to be marked on each class file in a certain format, here is an example:

```java
/**
 * Allay Project 2024/6/1
 *
 * @author daoge_cmd
 */
class XXX {

}
```

## Reduce nesting levels

This is an age-old problem. Excessive nesting in code greatly reduces readability, while reducing nesting levels can
bring great benefits.
Here are some commonly used methods to reduce nesting levels:

### Guard Clauses

Guard clauses are a programming technique that splits complex conditional expressions into multiple simpler ones,
reducing nesting.
If a conditional statement is extremely complex, it should be broken down into individual checks, and the function
should immediately return from the function when the condition is true. Such individual checks are often called "guard
clauses".
The effect of guard clauses is to organize the original code that requires careful reading and careful logic arrangement
into logical relationships that can be seen through at a glance.

For example:

Original code:

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

Refactored code:

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

The statement for output will only be executed if none of the above three conditions are met. That is, excluding cases
that do not meet

Only when all three conditions above are not met will the final output statement be executed. In other words, it
excludes cases that do not meet the conditions, leaving only those that naturally do.

## Method Extraction

Some methods bear heavy functionality and have complex logic, often resulting in severe code nesting. In such cases, we
can consider extracting methods, splitting one method into a combination of multiple method calls.

## Efficient Use of return Statements (Similar to Guard Clauses)

In the era of C programming, the single exit principle was widely accepted, meaning "a method should only have one
return statement". We believe this requirement is overly strict. At least in some cases, using return early can reduce
nesting levels:

Original Code:

```java
public static void main(String[] args) {
    if (args.length == 0) {
        return;
    } else {
        // Do something else
    }
}
```

Refactored Code:

```java
public static void main(String[] args) {
    if (args.length == 0) {
        return;
    }
    // Do something else
}
```

This is a very simple yet effective technique.