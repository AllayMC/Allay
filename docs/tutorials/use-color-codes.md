---
comments: true
---

Color codes are used to customize the color and formatting of text in the game, such as on signs, in chat, in titles, in
forms and more. These color codes are represented by the section sign (`§`) followed by a character. Each character
represents a different color or formatting option. Allay wants to make it easy to use them, that is why an easy to
use api is provided.

## Usage

This code shows how easy it is to use colored text in allay:

```java
import org.allaymc.api.utils.TextFormat;

var myBeautifulText = "This is " + TextFormat.DARK_GREEN + "dark green." + TextFormat.RESET;
```

!!! tip

    Don't forget to end the colored text with `TextFormat.RESET`,
    it ends all extra color format options and resets them. 


## All color and format codes

<div class="center-table" markdown>

| Code |        Name        |
|:----:|:------------------:|
| `§0` |       black        |
| `§1` |     dark_blue      |
| `§2` |     dark_green     |
| `§3` |     dark_aqua      |
| `§4` |      dark_red      |
| `§5` |    dark_purple     |
| `§6` |        gold        |
| `§7` |        gray        |
| `§8` |     dark_gray      |
| `§9` |        blue        |
| `§a` |       green        |
| `§b` |        aqua        |
| `§c` |        red         |
| `§d` |    light_purple    |
| `§e` |       yellow       |
| `§f` |       white        |
| `§g` |   minecoin_gold    |
| `§h` |  material_quartz   |
| `§i` |   material_iron    |
| `§j` | material_netherite |
| `§k` |     obfuscated     |
| `§l` |        bold        |
| `§m` | material_redstone  |
| `§n` |  material_copper   |
| `§o` |       italic       |
| `§p` |   material_gold    |
| `§q` |  material_emerald  |
| `§r` |       reset        |
| `§s` |  material_diamond  |
| `§t` |   material_lapis   |
| `§u` | material_amethyst  |

</div>
