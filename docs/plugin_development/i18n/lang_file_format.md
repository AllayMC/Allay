---
comments: true
---

Consider the following multi-language keys:

### Example 1

```properties
translation.test.complex=Prefix, %s%2$s, then %s and %1$s, finally %s, also %1$s!
```

If the parameters are [1,2,3,4,5], the output will be `Prefix, 15, then 2 and 4, finally 3, also 4!`

Placeholders without explicit parameter ordering will be replaced first (`%s`), in the order they appear.
Then, the remaining placeholders (`%1$s` and `%2$s`) will be replaced according to the declared parameter order.

In the example above, there are two `%1$s` placeholders, both of which become 4 after applying the parameters.

Parameter types can be declared as `s` (string) or `d` (number); in reality, this type is not important,
and even string parameters can replace number placeholders.

### Example 2

```properties
world_recovery.error.insufficient_space=Insufficient space, unable to recover. Needed: %1. Available: %2.	#
```

If the parameters are [1,2], the output will be `Insufficient space, unable to recover. Needed: 1. Available: 2.`

Thus, placeholders can be declared without a type.

### Regarding the Use of '%'

The percent sign `%` indicates the beginning of a multi-language key.

For example, you can write:

```
aaa%multiplayer.player.joined
```

Where `%multiplayer.player.joined` will be replaced, while the beginning `aaa` will be retained.

Of course, you can also write it like this (although it makes little sense): `%multiplayer.player.joined`

When determining the right boundary of a multi-language key, traversal starts from the beginning of the key
and moves right.

If an illegal character (one that cannot be used in a multi-language key) is encountered, traversal stops,
and the previous character is taken as the right boundary.
For a list of valid characters, refer to the method `org.allaymc.api.i18n.I18n.isValidKeyCharacter`.

If during traversal two colons (`:`) appear consecutively, this key will not be considered a valid multi-language key.