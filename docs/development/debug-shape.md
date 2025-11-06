---
comments: true
---

# Debug Shape

The **Debug Shape** feature, introduced in **1.21.90**, allows the server to draw various client-side shapes.
This is a powerful tool for visual debugging and development.

## Supported Parameters by Shape Type

|               | position  | scale | color | text | boxBounds | lineEndPosition | arrowHeadLength | arrowHeadRadius | segments                      |
|---------------|-----------|-------|-------|------|-----------|-----------------|-----------------|-----------------|-------------------------------|
| Line          | √         |       | √     |      |           | √               |                 |                 |                               |
| Box           | √         | √     | √     |      | √         |                 |                 |                 |                               |
| Sphere        | √         | √     | √     |      |           |                 |                 |                 | √                             |
| Circle        | √         | √     | √     |      |           |                 |                 |                 | √                             |
| Text          | √         | √     | √     | √    |           |                 |                 |                 |                               |
| Arrow         | √         | √     | √     |      |           | √               | √               | √               | √                             |
| Default value | (0, 0, 0) | 1     | white |      | (1, 1, 1) | (0, 0, 0)       | 1               | 0.5             | 20 (circle/sphere), 4 (arrow) |

## Additional Notes

- `rotation` currently has **no effect** on any debug shape.
- Setting `totalTimeLeft` to a value greater than zero **does not automatically remove** the shape after that time. The
  server must explicitly remove it by sending a removal request.
- Setting `text` to `null` will cause **nothing to render**, including the black background.
- You **can update** the properties of an existing shape (except its type) by resending it with the same ID.
- To **remove** a shape, resend it with the same ID and set all other fields to `null`.
- The `color` parameter supports **RGBA** components: red, green, blue, and alpha.