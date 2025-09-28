---
comments: true
---

# Blocks API

This tutorial introduces the core concepts of the Allay Blocks API and shows practical ways to find block types, work
with block states and properties, and read/write blocks in the dimension.

What you will learn:

- What are Block, BlockType, BlockTypes, BlockState, BlockPropertyType, BlockPropertyTypes, BlockBehavior
- Ways to get a BlockType/BlockState
- How to read and set a BlockState at a dimension position

## Key Concepts

- Block
    - An immutable data object that represents a block at a specific position (and layer) in a dimension.
    - It wraps a BlockState and adds dimension position/layer utilities.
    - Useful for “at this location, what is the block and what can I do with it?”

- BlockType
    - Describes a kind of block (e.g., Stone, Dirt, Chest).
    - Owns the set of possible states and the rules/properties those states can take.
    - Provides the default state and all known states. Each BlockType is tied to a BlockBehavior.

- BlockTypes
    - A convenience class with static fields for all known vanilla block types (e.g., BlockTypes.STONE, BlockTypes.AIR, …).
    - Great for quick access without looking up identifiers manually.

- BlockState
    - A concrete, immutable state of a BlockType (e.g., “oak door, open=false, facing=north”).
    - States are singletons: compare with “==” safely.
    - Carries a map of BlockPropertyType -> value, supports set/get to produce new states.

- BlockPropertyType
    - Describes a configurable property for a BlockType (e.g., “open”, “facing”, “age”).
    - Each property has a name, a value type, and valid values defined by the BlockType.

- BlockPropertyTypes
    - A central collection of known property definitions (e.g., commonly used flags or enums).
    - Use these when changing or reading a property on a BlockState.

- BlockBehavior
    - the logic/behavior associated with a BlockType (drops, interactions, ticking, etc.).
    - Accessed via BlockType.getBlockBehavior() or BlockState.getBehavior().

Difference at a glance:

- BlockType: the category/kind (e.g., Stone)
- BlockState: the exact configured instance of that kind (e.g., Stone has no extra properties; Door has open/facing/etc.)
- Block: BlockState at a position in the dimension
- BlockPropertyType/BlockPropertyTypes: the schema and catalog for state properties
- BlockBehavior: the code that defines how a BlockType acts
- BlockTypes: ready-to-use static references for common BlockType objects

## Getting a BlockType

You generally have two ways:

### From BlockTypes class

Use fields from BlockTypes for quick access:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.behavior.BlockBehavior;

public class Example {
    public void getFromBlockTypes() {
        BlockType<?> air = BlockTypes.AIR;
        BlockType<?> stone = BlockTypes.STONE;
    }
}
```

### From the Registry

Look up by Identifier via the registries when you have dynamic keys or custom content:

```java linenums="1"
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;

public class Example {
    public void getFromRegistry() {
        Identifier id = new Identifier("minecraft:stone");
        // stone may be null if not found
        BlockType<?> stone = Registries.BLOCK_TYPE.get(id);
    }
}
```

## Getting a BlockState

### Getting the Default BlockState:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;

public class Example {
    public void getDefaultState() {
        BlockState stone = BlockTypes.STONE.getDefaultState();
    }
}
```

### From Property Values:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;
import org.allaymc.api.block.property.BlockPropertyType;
import org.allaymc.api.block.property.BlockPropertyTypes;
import org.allaymc.api.block.property.enums.*;

public class Example {
    public void getWithProperties() {
        // Create from property values
        BlockState door = BlockTypes.OAK_DOOR.ofState(
                BlockPropertyTypes.DOOR_HINGE_BIT.createValue(false),
                BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION.createValue(MinecraftCardinalDirection.NORTH),
                BlockPropertyTypes.OPEN_BIT.createValue(false),
                BlockPropertyTypes.UPPER_BLOCK_BIT.createValue(false)
        );

        // Or start from default and set properties immutably
        BlockState closedNorthDoor = BlockTypes.OAK_DOOR
                .getDefaultState()
                .setPropertyValue(BlockPropertyTypes.OPEN_BIT, false)
                .setPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, MinecraftCardinalDirection.NORTH);
    }
}
```

### Copying Similar BlockState's Property Values:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;

public class Example {
    public void copy() {
        BlockState door = BlockTypes.OAK_DOOR.getDefaultState();
        // Copy property values from another similar block state (if they have the same property types)
        BlockState darkDoor = BlockTypes.DARK_OAK_DOOR.copyPropertyValuesFrom(door);
    }
}
```

### From Old Format (Using BlockStateGetter)

```java linenums="1"
import org.allaymc.api.block.state.BlockState;
import org.allaymc.api.block.type.BlockStateGetter;

public class Example {
    public void fromGetter() {
        // Legacy id+val format is supported since it will be updated to the latest state
        BlockState stone = BlockStateGetter.name("minecraft:stone").val(1).blockState();
        // Similarly, old properties are supported
        BlockState anvil = BlockStateGetter.name("minecraft:anvil").property("damage", "very_damaged").blockState();
        // This would return the default state of the block type
        BlockState pinkPetals = BlockStateGetter.name("minecraft:pink_petals").blockState();
    }
}
```

## Reading and Writing BlockState in a Dimension

Dimension provides methods to get or update blocks by coordinates (int x, y, z) and layer (optional).

### Read a BlockState at a Location

```java linenums="1"
import org.allaymc.api.world.Dimension;
import org.joml.Vector3i;

public class Example {
    public BlockState readAt(Dimension dimension, int x, int y, int z) {
        return dimension.getBlockState(x, y, z);
    }

    public BlockState readAtLayer1(Dimension dimension, Vector3i pos) {
        return dimension.getBlockState(pos, 1);
    }
}
```

### Create a Block Instance at a Location

```java linenums="1"
import org.allaymc.api.block.Block;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3i;

public class Example {
    public Block createBlockInstance(Dimension dimension, Vector3i pos) {
        // Captures state + position
        return new Block(dimension, pos);
    }
}
```

### Set or Update BlockState at a location:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;
import org.allaymc.api.world.Dimension;

public class Example {
    public void placeStone(Dimension dimension, int x, int y, int z) {
        BlockState stone = BlockTypes.STONE.getDefaultState();
        dimension.setBlockState(x, y, z, stone);
    }
}
```

### Update a Single Property in-place:

```java linenums="1"
import org.allaymc.api.block.Block;
import org.allaymc.api.block.property.BlockPropertyTypes;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3i;

public class Example {
    public void toggleOpen(Dimension dimension, Vector3i doorPos) {
        Block door = new Block(dimension, doorPos);
        boolean open = door.getPropertyValue(BlockPropertyTypes.OPEN_BIT);
        door.updateBlockProperty(BlockPropertyTypes.OPEN_BIT, !open);
    }
}
```

### Read/Modify/Write with a New BlockState:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;
import org.allaymc.api.block.property.BlockPropertyTypes;
import org.allaymc.api.world.Dimension;

public class Example {
    public void setFacingToNorth(Dimension dimension, int x, int y, int z) {
        BlockState current = dimension.getBlockState(x, y, z, 0);
        if (current.getBlockType() == BlockTypes.OAK_DOOR) {
            BlockState updated = current.setPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, MinecraftCardinalDirection.NORTH);
            dimension.setBlockState(x, y, z, updated);
        }
    }
}
```

## Tips

- Compare BlockState with “==” because states are singletons.
- Use BlockTypes when you need fast access to common vanilla blocks.
- Use the registry for dynamic lookups by Identifier.
- Always verify that the property you set exists on the BlockType (use hasProperty or handle IllegalArgumentException).
