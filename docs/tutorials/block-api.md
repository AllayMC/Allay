---
comments: true
---

# Blocks API

This tutorial introduces the core concepts of the Allay Blocks API and shows practical ways to find block types, work
with block states and properties, and read/write blocks in the dimension.

**What you will learn:**

- Key concepts: Block, BlockType, BlockState, BlockPropertyType, BlockBehavior
- How to get a BlockType and BlockState
- How to read and set blocks in a dimension
- Working with block properties and layers

## Key Concepts

### Block

An immutable data object that represents a block at a specific position (and layer) in a dimension.
It wraps a BlockState and adds dimension position/layer utilities.

```java linenums="1"
// "At this location, what is the block and what can I do with it?"
Block block = new Block(dimension, x, y, z);
BlockState state = block.getBlockState();
boolean isOpen = block.getPropertyValue(BlockPropertyTypes.OPEN_BIT);
```

### BlockType

Describes a kind of block (e.g., Stone, Dirt, Chest). Each BlockType:

- Owns the set of possible states and properties
- Provides the default state and all known states
- Is tied to a BlockBehavior that defines its logic

```java linenums="1"
BlockType<?> stoneType = BlockTypes.STONE;
BlockState defaultStone = stoneType.getDefaultState();
BlockBehavior behavior = stoneType.getBlockBehavior();
```

### BlockTypes

A convenience class with static fields for all known vanilla block types.
Great for quick access without looking up identifiers manually.

```java linenums="1"
BlockType<?> air = BlockTypes.AIR;
BlockType<?> stone = BlockTypes.STONE;
BlockType<?> oakDoor = BlockTypes.OAK_DOOR;
```

### BlockState

A concrete, immutable state of a BlockType (e.g., "oak door, open=false, facing=north").

- States are **singletons**: compare with `==` safely
- Carries a map of BlockPropertyType -> value
- Supports `setPropertyValue()` to produce new states (immutably)

```java linenums="1"
BlockState closedDoor = BlockTypes.OAK_DOOR.getDefaultState();
BlockState openDoor = closedDoor.setPropertyValue(BlockPropertyTypes.OPEN_BIT, true);
// closedDoor != openDoor (different states)
```

### BlockPropertyType & BlockPropertyTypes

`BlockPropertyType` describes a configurable property for a BlockType (e.g., "open", "facing", "age").
`BlockPropertyTypes` is a central collection of known property definitions.

```java linenums="1"
// Check if a block type has a property
boolean hasFacing = BlockTypes.OAK_DOOR.hasProperty(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);

// Get property value from a state
MinecraftCardinalDirection facing = doorState.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
```

### BlockBehavior

The logic/behavior associated with a BlockType (drops, interactions, ticking, etc.).

```java linenums="1"
BlockBehavior behavior = BlockTypes.CHEST.getBlockBehavior();
// or from a state
BlockBehavior behavior2 = someBlockState.getBehavior();
```

### Summary Table

| Concept           | Description                                                             |
|-------------------|-------------------------------------------------------------------------|
| BlockType         | The category/kind (e.g., Stone, Oak Door)                               |
| BlockState        | The exact configured instance (e.g., Door with open=true, facing=north) |
| Block             | BlockState at a specific position in a dimension                        |
| BlockPropertyType | The schema for a state property (e.g., "open_bit")                      |
| BlockBehavior     | The code that defines how a BlockType acts                              |
| BlockTypes        | Static references for common vanilla BlockType objects                  |

## Getting a BlockType

### From BlockTypes Class

Use fields from `BlockTypes` for quick access to vanilla blocks:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.type.BlockType;

BlockType<?> air = BlockTypes.AIR;
BlockType<?> stone = BlockTypes.STONE;
BlockType<?> chest = BlockTypes.CHEST;
BlockType<?> oakDoor = BlockTypes.OAK_DOOR;
```

### From the Registry

Look up by Identifier when you have dynamic keys or custom content:

```java linenums="1"
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;

// Look up by identifier
Identifier id = new Identifier("minecraft:stone");
BlockType<?> stone = Registries.BLOCK_TYPE.get(id); // may be null if not found

// Or use string directly
BlockType<?> dirt = Registries.BLOCK_TYPE.get(new Identifier("minecraft:dirt"));
```

## Getting a BlockState

### Default BlockState

Every BlockType has a default state:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;

BlockState stone = BlockTypes.STONE.getDefaultState();
BlockState air = BlockTypes.AIR.getDefaultState();
```

### From Property Values

Create a state with specific property values:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;
import org.allaymc.api.block.property.BlockPropertyTypes;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;

// Method 1: Create from property values directly
BlockState door = BlockTypes.OAK_DOOR.ofState(
        BlockPropertyTypes.DOOR_HINGE_BIT.createValue(false),
        BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION.createValue(MinecraftCardinalDirection.NORTH),
        BlockPropertyTypes.OPEN_BIT.createValue(false),
        BlockPropertyTypes.UPPER_BLOCK_BIT.createValue(false)
);

// Method 2: Start from default and set properties (immutably)
BlockState closedNorthDoor = BlockTypes.OAK_DOOR
        .getDefaultState()
        .setPropertyValue(BlockPropertyTypes.OPEN_BIT, false)
        .setPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, MinecraftCardinalDirection.NORTH);
```

### Copy Property Values from Another State

Copy property values between similar block types:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;

BlockState oakDoor = BlockTypes.OAK_DOOR.getDefaultState()
        .setPropertyValue(BlockPropertyTypes.OPEN_BIT, true);

// Copy property values to another door type (same properties)
BlockState darkOakDoor = BlockTypes.DARK_OAK_DOOR.copyPropertyValuesFrom(oakDoor);
// darkOakDoor now has open_bit=true
```

### From Legacy Format (BlockStateGetter)

For compatibility with old block ID/data formats:

```java linenums="1"
import org.allaymc.api.block.state.BlockState;
import org.allaymc.api.block.type.BlockStateGetter;

// Legacy id + data value format (automatically updated to latest state)
BlockState stone = BlockStateGetter.name("minecraft:stone").val(1).blockState();

// Old property format
BlockState anvil = BlockStateGetter.name("minecraft:anvil")
        .property("damage", "very_damaged")
        .blockState();

// Just get default state by name
BlockState pinkPetals = BlockStateGetter.name("minecraft:pink_petals").blockState();
```

## Reading and Writing Blocks in a Dimension

Dimension provides methods to get or update blocks by coordinates and layer.

### Understanding Block Layers

Minecraft Bedrock Edition supports multiple block layers at each position:

- **Layer 0**: The main block layer (solid blocks, most blocks)
- **Layer 1**: The liquid/waterlogged layer (water, lava inside other blocks)

```java linenums="1"
// Get block at layer 0 (default)
BlockState mainBlock = dimension.getBlockState(x, y, z);

// Get block at layer 1 (e.g., water inside a waterlogged block)
BlockState liquidBlock = dimension.getBlockState(x, y, z, 1);
```

### Read a BlockState

```java linenums="1"
import org.allaymc.api.world.Dimension;
import org.allaymc.api.block.state.BlockState;

public BlockState getBlock(Dimension dimension, int x, int y, int z) {
    return dimension.getBlockState(x, y, z);
}

public BlockState getBlockAtLayer(Dimension dimension, int x, int y, int z, int layer) {
    return dimension.getBlockState(x, y, z, layer);
}
```

### Create a Block Instance

The `Block` class wraps a BlockState with its position:

```java linenums="1"
import org.allaymc.api.block.Block;
import org.allaymc.api.world.Dimension;

// Create a Block instance (captures state + position)
Block block = new Block(dimension, x, y, z);

// Access block information
BlockState state = block.getBlockState();
BlockType<?> type = block.getBlockType();
int posX = block.getX();
```

### Set a BlockState

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;
import org.allaymc.api.world.Dimension;

public void placeStone(Dimension dimension, int x, int y, int z) {
    BlockState stone = BlockTypes.STONE.getDefaultState();
    dimension.setBlockState(x, y, z, stone);
}

public void placeWaterInBlock(Dimension dimension, int x, int y, int z) {
    // Set water at layer 1 (waterlogged)
    BlockState water = BlockTypes.WATER.getDefaultState();
    dimension.setBlockState(x, y, z, water, 1);
}
```

### Update a Block Property In-Place

```java linenums="1"
import org.allaymc.api.block.Block;
import org.allaymc.api.block.property.BlockPropertyTypes;
import org.allaymc.api.world.Dimension;

public void toggleDoor(Dimension dimension, int x, int y, int z) {
    Block door = new Block(dimension, x, y, z);

    // Read current value
    boolean isOpen = door.getPropertyValue(BlockPropertyTypes.OPEN_BIT);

    // Update the property (modifies the block in the dimension)
    door.updateBlockProperty(BlockPropertyTypes.OPEN_BIT, !isOpen);
}
```

### Read, Modify, and Write a BlockState

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;
import org.allaymc.api.block.property.BlockPropertyTypes;
import org.allaymc.api.block.property.enums.MinecraftCardinalDirection;
import org.allaymc.api.world.Dimension;

public void rotateDoorToNorth(Dimension dimension, int x, int y, int z) {
    BlockState current = dimension.getBlockState(x, y, z);

    // Check if it's a door
    if (current.getBlockType() == BlockTypes.OAK_DOOR) {
        // Create new state with updated property
        BlockState rotated = current.setPropertyValue(
                BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION,
                MinecraftCardinalDirection.NORTH
        );
        // Write back to dimension
        dimension.setBlockState(x, y, z, rotated);
    }
}
```

## Working with Block Properties

### Check if a Property Exists

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.property.BlockPropertyTypes;

// Check on BlockType
boolean canOpen = BlockTypes.OAK_DOOR.hasProperty(BlockPropertyTypes.OPEN_BIT);
boolean hasAge = BlockTypes.WHEAT.hasProperty(BlockPropertyTypes.AGE);

// Check on BlockState
BlockState state = BlockTypes.OAK_DOOR.getDefaultState();
boolean hasOpenBit = state.hasProperty(BlockPropertyTypes.OPEN_BIT);
```

### Get All Properties of a Block

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.property.type.BlockPropertyType;

// Get all property types for a block
var properties = BlockTypes.OAK_DOOR.getProperties();
for (BlockPropertyType<?> prop : properties.values()) {
    System.out.println("Property: " + prop.getName());
}
```

### Common Block Properties

| Property | Description | Example Blocks |
|----------|-------------|----------------|
| `OPEN_BIT` | Whether a door/trapdoor/fence gate is open | Doors, Trapdoors, Fence Gates |
| `MINECRAFT_CARDINAL_DIRECTION` | Facing direction (north/south/east/west) | Doors, Signs, Chests |
| `AGE` | Growth stage | Crops, Saplings |
| `POWERED_BIT` | Whether redstone-powered | Repeaters, Comparators |
| `LIT` | Whether lit | Furnace, Campfire |
| `UPPER_BLOCK_BIT` | Upper half of a tall block | Doors, Tall Flowers |

## Practical Examples

### Check if Player is Standing on a Specific Block

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

public boolean isStandingOnGold(EntityPlayer player) {
    var pos = player.getLocation();
    var dimension = player.getDimension();

    // Get block below player
    BlockState below = dimension.getBlockState(
            (int) pos.x(),
            (int) pos.y() - 1,
            (int) pos.z()
    );

    return below.getBlockType() == BlockTypes.GOLD_BLOCK;
}
```

### Replace All Blocks in an Area

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;
import org.allaymc.api.world.Dimension;

public void replaceStoneWithDirt(Dimension dimension, int x1, int y1, int z1, int x2, int y2, int z2) {
    BlockState dirt = BlockTypes.DIRT.getDefaultState();

    for (int x = x1; x <= x2; x++) {
        for (int y = y1; y <= y2; y++) {
            for (int z = z1; z <= z2; z++) {
                BlockState current = dimension.getBlockState(x, y, z);
                if (current.getBlockType() == BlockTypes.STONE) {
                    dimension.setBlockState(x, y, z, dirt);
                }
            }
        }
    }
}
```

### Create a Custom Structure

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.state.BlockState;
import org.allaymc.api.world.Dimension;

public void buildPillar(Dimension dimension, int x, int y, int z, int height) {
    BlockState stone = BlockTypes.STONE.getDefaultState();
    BlockState stoneBrick = BlockTypes.STONEBRICK.getDefaultState();

    for (int i = 0; i < height; i++) {
        // Alternate between stone and stone brick
        BlockState block = (i % 2 == 0) ? stone : stoneBrick;
        dimension.setBlockState(x, y + i, z, block);
    }
}
```

## Tips and Best Practices

!!! tip "Compare BlockState with =="
    BlockStates are singletons, so you can safely compare them using `==` instead of `.equals()`:
    ```java linenums="1"
    if (state1 == state2) { /* same state */ }
    ```

!!! tip "Use BlockTypes for Vanilla Blocks"
    Use `BlockTypes.STONE` instead of looking up by identifier for better performance and type safety.

!!! tip "Validate Properties Before Setting"
    Always verify that a property exists on a BlockType before setting it:
    ```java linenums="1"
    if (blockType.hasProperty(BlockPropertyTypes.OPEN_BIT)) {
        // Safe to use OPEN_BIT
    }
    ```

!!! tip "BlockState is Immutable"
    `setPropertyValue()` returns a **new** BlockState; it doesn't modify the original:
    ```java linenums="1"
    BlockState original = BlockTypes.OAK_DOOR.getDefaultState();
    BlockState modified = original.setPropertyValue(BlockPropertyTypes.OPEN_BIT, true);
    // original still has open_bit=false
    ```

!!! warning "Don't Forget Block Layers"
    When working with waterlogged blocks, remember to check layer 1 for water:
    ```java linenums="1"
    BlockState water = dimension.getBlockState(x, y, z, 1);
    boolean isWaterlogged = water.getBlockType() == BlockTypes.WATER;
    ```
