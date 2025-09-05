---
comments: true
---

# Work with Blocks

This tutorial introduces the core concepts of Allay’s Blocks API and shows practical ways to look up block types and states, and to read/write blocks in the world.

## Core Concepts

- Block
  - A block placed in the world at a specific coordinate, conceptually “a block at (x, y, z) in a given dimension.”
  - In the API, placed blocks are interacted with primarily through the world (Dimension) and block states at positions.

- BlockType
  - The definition of a kind of block (e.g., stone, sand, furnace). It defines behavior, available properties, and all valid states of that type.
  - Key methods include: getting its behavior, enumerating properties and states, and creating states from property values.

- BlockTypes
  - A generated convenience class that exposes constants for every known BlockType (e.g., BlockTypes.STONE). It lets you access block types without performing a registry lookup and benefits from IDE auto-completion.

- BlockState
  - A concrete, specific configuration of a BlockType (i.e., a unique combination of property values).
  - BlockState is a singleton: you can use == to compare two states for equality.
  - It exposes helpers to read and set property values (returning another BlockState instance).

- BlockPropertyType
  - The descriptor of a property on a block type. It carries the property name, type (BOOLEAN/INT/ENUM), default value, valid values and bit size used in special value encoding.
  - You construct property values through it or set them via BlockState helpers.

- BlockPropertyTypes
  - A generated catalog of BlockPropertyType descriptors for common block properties (e.g., BlockPropertyTypes.PILLAR_AXIS). Use these to create property values or update properties on a BlockState in a type-safe, discoverable way.

- BlockBehavior
  - The behavior interface associated with a BlockType. Concrete block types provide a behavior that implements block-specific logic and data components.

## Getting a BlockType

There are two common ways to obtain a BlockType.

### 1) From the global registry

```java linenums="1"
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.block.type.BlockType;

BlockType<?> stone = Registries.BLOCKS.get(new Identifier("minecraft:stone"));
if (stone == null) {
    // Unknown identifier
}
```

### 2) From the generated BlockTypes class

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.type.BlockType;

BlockType<?> stone = BlockTypes.STONE; // Convenience access
```

## Getting a BlockState

### 1) From a BlockType

- Default state:

```java linenums="1"
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.type.BlockState;

BlockState defaultStone = BlockTypes.STONE.getDefaultState();
```

- Set a property on the default state (returns a new state):

```java linenums="1"
import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.type.BlockState;

BlockState oakLogY = BlockTypes.OAK_LOG
        .getDefaultState()
        .setPropertyValue(BlockPropertyTypes.PILLAR_AXIS, PillarAxis.Y);
```

- Create a state from explicit property values:

```java linenums="1"
import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.type.BlockState;

BlockState oakLogX = BlockTypes.OAK_LOG.ofState(
        BlockPropertyTypes.PILLAR_AXIS.createValue(PillarAxis.X)
);
```

### 2) Using BlockStateSafeGetter (version-safe)

BlockStateSafeGetter helps you build or parse states in a way that remains compatible across protocol versions.

- Build by name + properties:

```java linenums="1"
import org.allaymc.api.block.type.BlockStateSafeGetter;
import org.allaymc.api.block.type.BlockState;

BlockState oakLogZ = BlockStateSafeGetter
        .name("minecraft:oak_log")
        .property("pillar_axis", "z") // property names/values follow the block state NBT representation
        .blockState();
```

- Parse from NBT:

```java linenums="1"
import org.allaymc.api.block.type.BlockStateSafeGetter;
import org.allaymc.api.network.ProtocolInfo;
import org.cloudburstmc.nbt.NbtMap;
import org.allaymc.api.block.type.BlockState;

NbtMap stateNbt = NbtMap.builder()
        .putString("name", "minecraft:oak_log")
        .putInt("version", ProtocolInfo.BLOCK_STATE_VERSION_NUM)
        .putCompound("states", NbtMap.builder()
                .putString("pillar_axis", "y")
                .build())
        .build();
BlockState oakLogY = BlockStateSafeGetter.fromNBT(stateNbt);
```

### 3) From the BlockState palette (by hash)

If you already know the block state hash, you can get the instance from the global palette:

```java linenums="1"
import org.allaymc.api.registry.Registries;
import org.allaymc.api.block.type.BlockState;

int hash = /* a known block state hash */;
BlockState state = Registries.BLOCK_STATE_PALETTE.get(hash);
```

## Read/Write blocks in a Dimension

Use Dimension to query and modify blocks at world positions.

### Reading the block state at a position

```java linenums="1"
import org.allaymc.api.world.Dimension;
import org.allaymc.api.block.type.BlockState;

Dimension dimension = /* obtain your dimension */;
int x = 100, y = 64, z = 100;
BlockState state = dimension.getBlockState(x, y, z);
```

### Setting the block state at a position

```java linenums="1"
import org.allaymc.api.world.Dimension;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.block.type.BlockState;

Dimension dimension = /* obtain your dimension */;
BlockState stone = BlockTypes.STONE.getDefaultState();
boolean ok = dimension.setBlockState(100, 64, 100, stone);
```

There are overloads to specify the layer and flags (send updates, trigger behavior updates, etc.).

### Updating a single property in place

Dimension provides a helper to update one property of the block at a position:

```java linenums="1"
import org.allaymc.api.world.Dimension;
import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.property.type.BlockPropertyTypes;

Dimension dimension = /* obtain your dimension */;
dimension.updateBlockProperty(BlockPropertyTypes.PILLAR_AXIS, PillarAxis.Z, 100, 64, 100);
```

This reads the current state at the position, applies the property change, and writes the updated state back.

!!! tip

    - BlockState is a singleton; you can compare states with `==` reliably.
    - `setPropertyValue` returns another BlockState instance; the original state remains unchanged.
    - When using BlockStateSafeGetter, prefer name + properties to avoid breakage across protocol updates.