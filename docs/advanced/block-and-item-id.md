---
comments: true
---

# Block and Item Id

This page will explain the id system of block and item.

## Overview

In Minecraft: Bedrock Edition, all blocks must have a correspond `hard block item`. But not every item have a correspond block.
If an item does not have a correspond block, then we call it `pure item`. The texture of `hard block item` is decided by the block's
texture and cannot be changed. To solve this problem, the vanilla also registered extra block item for some blocks (e.g. cake,
cauldron, grass, kelp, bed, sugar cane, etc.). We called these extra block items `actual block item`.

When a block only have one correspond block item, `hard block item` is equal to `actual block item`. Most of the blocks in vanilla
are like this.

## How Blocks & Items are Registered and How the Order Affect the Id

The register order of blocks and items in vanilla:

1. Register all `pure item` and `actual block item`.
2. Register all blocks, and try to register `hard block item` with the same id of block for each block, and there are two cases:
    - There is no `actual block item` that already registered with the same id, which means that this block only have one correspond block item.
      We can say that the `hard block item` is equal to the `actual block item` of this block.
    - There is an `actual block item` that already registered with the same id, which means that the `hard block item` and `actual block item`
      of this block is different. **However, the `hard block item` will still be registered, and to solve the conflict, a perfix `item.` will
      be added to the path of the id**.

Here, we will take cake (whose `hard block item` is different from its `actual block item`) as an example:

1. Register the `actual block item`: `minecraft:cake`.
2. Register the block: `minecarft:cake`.
3. Try to register the `hard block item` with the same id of block: `minecraft:cake`.
   But there is already an `actual block item` with the same id, so the `hard block item` will be registered with the id `minecraft:item.cake`.

## Special Cases

Normally, for a block, you can find an item of the same block ID. But there are special cases here (for historical reasons):

| Block Id        | Actual Block Item Id                     | Hard Block Item Id   |
|-----------------|------------------------------------------|----------------------|
| minecraft:reeds | ~~minecraft:reeds~~ minecraft:sugar_cane | minecraft:item.reeds |

## How Allay Handles Block and Item Id

To unify naming and reduce confusion. In Allay, we ensure that the `hard block item`'s id of the registered block is the same as the block's id
without an id conflict. If you want to register extra block item for your block, please make sure that it has the same id of the blcok you want
to register extra block item for. Later when registering the block, Allay will consider the item you registered as the `actual block item`, and
register the `hard block item` with a `item.` prefix.

The return value of `BlockType#getItemType()` will be the `actual block item` instead of the `hard block item` if the block has extra block item.
