In Allay, permissions are represented as a tree structure.

### Representation of a Permission Node

Strings represent permissions, with dots indicating the parent-child relationship between nodes.

Examples:

- `allay.cmd.tell`
- `allay.cmd.help`
- `allay.text.color`

### Permission Matching (Test Code in test/perm/PermTreeTest.java)

#### Example 1:

Let's say the player "cool_loong" has the permission `allay.cmd.tell`.

Then the player "cool_loong" has permissions for

- `allay`
- `allay.cmd`
- `allay.cmd.tell`

But does not have permissions for `allay.cmd.help` etc.

#### Example 2:

Let's say the player "cool_loong" has the permission `allay.cmd`.

Then the player "cool_loong" has permissions for

- `allay`
- `allay.cmd`

But does not have permissions for `allay.cmd.tell`, `allay.cmd.help`, etc.