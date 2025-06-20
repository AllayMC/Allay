---
comments: true
---

# Debug Shape

Debug shape is a feature that is introduced in 1.21.90. It allows the server to draw a set of different types of shapes client-side,
which would be very useful.

## Available Parameters for DebugShape

|               | position  | scale | color | text | boxBounds | lineEndPosition | arrowHeadLength | arrowHeadRadius | segments                             |
|---------------|-----------|-------|-------|------|-----------|-----------------|-----------------|-----------------|--------------------------------------|
| line          | √         |       | √     |      |           | √               |                 |                 |                                      |
| box           | √         | √     | √     |      | √         |                 |                 |                 |                                      |
| sphere        | √         | √     | √     |      |           |                 |                 |                 | √                                    |
| circle        | √         | √     | √     |      |           |                 |                 |                 | √                                    |
| text          | √         |       | √     | √    |           |                 |                 |                 |                                      |
| arrow         | √         |       | √     |      |           | √               | √               | √               | √                                    |
| default value | (0, 0, 0) | 1     | white |      | (1, 1, 1) | (0, 0, 0)       | 1               | 0.5             | 20 for circle/sphere and 4 for arrow |

## Notes

- `rotation` seems to have no effect on all types of debug shape.
- Setting a value greater than zero for `totalTimeLeft` does not seem to cause the client to stop displaying the debug shape after the specified time. 
In other words, the server must actively notify the client to remove the debug shape, although this value is still sent to the client.
- Setting `text` to `null` causes the client to not show anything, including the black background of the text.
- The properties of a debug shape can be updated by sending the shape data again, except its type.
- To remove a specified debug shape, send the shape data in the same id and let other parameters keep `null`.