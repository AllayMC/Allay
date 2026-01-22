---
comments: true
---

# Custom Block API

This guide covers the advanced Custom Block API for creating blocks with custom client-side rendering properties
such as custom geometry, textures, transformations, and state-dependent appearances.

**What you will learn:**

- How to create custom blocks with the function-based API
- Configuring geometry, materials, and transformations
- Creating state-dependent rendering (different appearances per block state)
- Understanding automatic optimization

!!! warning "Server Module Required"
    The Custom Block API requires access to the **server module**. In your `build.gradle.kts`, you must set
    `apiOnly = false` in the `allay` block:

    ```kotlin
    allay {
        apiOnly = false  // Required for Custom Block API
        // ... other settings
    }
    ```

    Note that internal APIs may change between versions. See [AllayGradle documentation](https://github.com/AllayMC/AllayGradle)
    for more details.

!!! tip "Prerequisites"
    Before diving into custom blocks, make sure you understand the basics of the Block API covered in
    [Block API Tutorial](../tutorials/block-api.md).

## Overview

The Custom Block API uses a **function-based approach** where you provide a function that maps each `BlockState`
to its rendering properties via `BlockStateDefinition`. The system automatically:

- Iterates all possible block state combinations
- Collects rendering properties for each state
- Optimizes by extracting common properties to global components
- Merges states with identical properties into single permutations

## Quick Start

### Simple Textured Block

The simplest way to create a custom block with a texture:

```java linenums="1"
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.block.type.CustomBlockDefinitionGenerator;

AllayBlockType.builder(MyBlockImpl.class)
    .identifier("myplugin:ruby_block")
    .blockDefinitionGenerator(
        CustomBlockDefinitionGenerator.ofTexture("ruby_block"))
    .build();
```

### Block with Custom Geometry

```java linenums="1"
import org.allaymc.server.block.type.BlockStateDefinition;
import org.allaymc.server.block.type.BlockStateDefinition.Geometry;
import org.allaymc.server.block.type.BlockStateDefinition.Materials;

AllayBlockType.builder(MyBlockImpl.class)
    .identifier("myplugin:custom_lamp")
    .blockDefinitionGenerator(
        CustomBlockDefinitionGenerator.ofConstant(
            BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.custom_lamp"))
                .materials(Materials.builder().any("lamp_texture"))
                .displayName("Custom Lamp")
                .build()))
    .build();
```

### State-Dependent Rendering

The most powerful feature - different appearances based on block state:

```java linenums="1"
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.server.block.type.BlockStateDefinition.Geometry;

AllayBlockType.builder(MyDoorImpl.class)
    .identifier("myplugin:custom_door")
    .setProperties(BlockPropertyTypes.OPEN_BIT)
    .blockDefinitionGenerator(
        CustomBlockDefinitionGenerator.of(state -> {
            boolean isOpen = state.getPropertyValue(BlockPropertyTypes.OPEN_BIT);
            return BlockStateDefinition.builder()
                .geometry(Geometry.of(isOpen ? "geometry.door_open" : "geometry.door_closed"))
                .materials(Materials.builder().any("door_texture"))
                .build();
        }))
    .build();
```

## CustomBlockDefinitionGenerator

The main entry point for custom block definitions.

### Factory Methods

| Method                                           | Description                                      |
|--------------------------------------------------|--------------------------------------------------|
| `of(Function<BlockState, BlockStateDefinition>)` | Full control - provide a function for each state |
| `ofConstant(BlockStateDefinition)`               | All states have the same appearance              |
| `ofTexture(String texture)`                      | Simple texture-only block                        |

### Using `of()` - Full Control

```java linenums="1"
CustomBlockDefinitionGenerator.of(state -> {
    // Access block state properties
    int age = state.getPropertyValue(BlockPropertyTypes.AGE);
    boolean powered = state.getPropertyValue(BlockPropertyTypes.POWERED_BIT);

    // Return different definitions based on state
    return BlockStateDefinition.builder()
        .geometry(Geometry.of("geometry.crop_stage_" + age))
        .materials(Materials.builder().any(powered ? "crop_powered" : "crop_normal"))
        .build();
});
```

### Using `ofConstant()` - Same Appearance

```java linenums="1"
CustomBlockDefinitionGenerator.ofConstant(
    BlockStateDefinition.builder()
        .geometry(Geometry.of("geometry.my_block"))
        .materials(Materials.builder().any("my_texture"))
        .transformation(Transformation.builder().ry(45).build())
        .build()
);
```

### Using `ofTexture()` - Simple Texture

```java linenums="1"
// All faces use the same texture with default opaque rendering
CustomBlockDefinitionGenerator.ofTexture("ruby_block");
```

## BlockStateDefinition

Holds the client-side rendering properties for a block state.

```java linenums="1"
BlockStateDefinition.builder()
    .geometry(Geometry)         // Geometry configuration
    .materials(Materials)       // Material instances
    .transformation(Transformation)  // Model transformation
    .displayName(String)        // Display name in inventory
    .build()
```

### Properties

| Property         | Type             | Description                                                                           |
|------------------|------------------|---------------------------------------------------------------------------------------|
| `geometry`       | `Geometry`       | Geometry configuration with identifier and advanced properties, null for default cube |
| `materials`      | `Materials`      | Material/texture configuration for block faces                                        |
| `transformation` | `Transformation` | Rotation, scale, and translation of the model                                         |
| `displayName`    | `String`         | Name shown in inventory, null uses block identifier                                   |

## Geometry

Configure the 3D model (geometry) for block rendering. Supports both simple identifier-only form and
advanced object form with bone visibility, culling, and UV lock settings.

### Simple Form

For basic geometry with just an identifier:

```java linenums="1"
import org.allaymc.server.block.type.BlockStateDefinition.Geometry;

// Simple string form - most common usage
Geometry.of("geometry.custom_block")
```

### Advanced Form (Object)

For advanced features like bone visibility control and culling optimization:

```java linenums="1"
import org.allaymc.api.block.property.type.BlockPropertyTypes;

// Object form with bone visibility based on block properties
Geometry.builder()
    .identifier("geometry.door")
    .boneVisibility("hinge", false)                                    // Always hidden
    .boneVisibility("handle", BlockPropertyTypes.OPEN_BIT, true)       // Visible when open
    .build()

// With culling optimization
Geometry.builder()
    .identifier("geometry.leaves")
    .culling("custom:culling.leaves")
    .build()

// With UV lock
Geometry.builder()
    .identifier("geometry.rotatable")
    .uvLockAll()  // Lock UVs for all bones when rotating
    .build()
```

### Properties

| Property         | Type                          | Description                                                         |
|------------------|-------------------------------|---------------------------------------------------------------------|
| `identifier`     | `String`                      | **Required.** Geometry identifier (e.g., `"geometry.custom_block"`) |
| `boneVisibility` | `Map<String, BoneVisibility>` | Map of bone names to visibility conditions                          |
| `culling`        | `String`                      | Culling rules identifier (format: `namespace:culling.name`)         |
| `cullingLayer`   | `String`                      | Culling layer for optimization                                      |
| `uvLockBones`    | `List<String>`                | List of specific bone names to lock UVs                             |
| `uvLockAll`      | `boolean`                     | Whether to lock UVs for all bones                                   |

### Bone Visibility

Control which bones of a geometry are visible. Use property-based conditions for
dynamic visibility based on block state:

```java linenums="1"
Geometry.builder()
    .identifier("geometry.complex_model")
    // Static visibility - always hidden
    .boneVisibility("decoration", false)
    // Property-based visibility - visible when powered
    .boneVisibility("indicator", BlockPropertyTypes.POWERED_BIT)
    // Property-based visibility with specific value
    .boneVisibility("stage_2", BlockPropertyTypes.AGE, 2)
    .build()
```

#### Bone Visibility Methods

| Method                                               | Description                               |
|------------------------------------------------------|-------------------------------------------|
| `boneVisibility(String, boolean)`                    | Static visibility (always visible/hidden) |
| `boneVisibility(String, BlockPropertyType<Boolean>)` | Visible when boolean property is true     |
| `boneVisibility(String, BlockPropertyType<T>, T)`    | Visible when property equals value        |
| `boneVisibilityMolang(String, String)`               | Raw Molang expression (advanced)          |

### Culling

Configure block culling for performance optimization:

```java linenums="1"
Geometry.builder()
    .identifier("geometry.leaves")
    .culling("myplugin:culling.leaves")      // Custom culling rules
    .cullingLayer("minecraft:culling_layer.leaves")
    .build()
```

### UV Lock

Prevent UV coordinates from rotating when the block model is transformed:

```java linenums="1"
// Lock all bones
Geometry.builder()
    .identifier("geometry.log")
    .uvLockAll()
    .build()

// Lock specific bones only
Geometry.builder()
    .identifier("geometry.complex")
    .uvLock("top", "bottom")  // Only lock these bones
    .build()
```

## Materials

Configure textures for each block face.

### Builder Pattern

```java linenums="1"
import org.allaymc.server.block.type.BlockStateDefinition.Materials;
import org.allaymc.api.block.data.BlockFace;

Materials.builder()
    .any("default_texture")                    // All unspecified faces
    .face(BlockFace.UP, "top_texture")        // Override top face
    .face(BlockFace.DOWN, "bottom_texture")   // Override bottom face
    .sides("side_texture")                    // All horizontal faces
```

### Methods

| Method                              | Description                                      |
|-------------------------------------|--------------------------------------------------|
| `any(String texture)`               | Set texture for all unspecified faces (wildcard) |
| `any(MaterialInstance)`             | Set material for all unspecified faces           |
| `face(BlockFace, String)`           | Set texture for a specific face                  |
| `face(BlockFace, MaterialInstance)` | Set material for a specific face                 |
| `sides(String texture)`             | Set texture for all horizontal faces (N/S/E/W)   |
| `sides(MaterialInstance)`           | Set material for all horizontal faces            |

### Practical Examples

**Simple single texture:**

```java linenums="1"
Materials.builder()
    .any("stone_texture")
```

**Different top and bottom:**

```java linenums="1"
Materials.builder()
    .any("log_side")                    // Default for all faces
    .face(BlockFace.UP, "log_top")      // Override top
    .face(BlockFace.DOWN, "log_top")    // Override bottom
```

**Furnace-style (front face different):**

```java linenums="1"
Materials.builder()
    .any("furnace_side")
    .face(BlockFace.UP, "furnace_top")
    .face(BlockFace.NORTH, "furnace_front")
```

## MaterialInstance

Configure advanced rendering properties for a face.

```java linenums="1"
import org.allaymc.server.block.type.BlockStateDefinition.MaterialInstance;
import org.allaymc.server.block.type.BlockStateDefinition.RenderMethod;

MaterialInstance.builder()
    .texture("my_texture")
    .renderMethod(RenderMethod.ALPHA_TEST)
    .faceDimming(true)
    .ambientOcclusion(true)
    .randomUVRotation(false)
    .textureVariation(false)
    .build()
```

### Builder Properties

| Property           | Type           | Default  | Description                                              |
|--------------------|----------------|----------|----------------------------------------------------------|
| `texture`          | `String`       | Required | Texture name from resource pack                          |
| `renderMethod`     | `RenderMethod` | `OPAQUE` | How the texture is rendered                              |
| `faceDimming`      | `boolean`      | `true`   | Apply directional light dimming                          |
| `ambientOcclusion` | `boolean`      | `true`   | Apply ambient occlusion shadows                          |
| `randomUVRotation` | `boolean`      | `false`  | Randomly rotate texture to avoid tiling patterns         |
| `textureVariation` | `boolean`      | `false`  | Enable texture variation                                 |

### Factory Methods

| Method                                          | Description                             |
|-------------------------------------------------|-----------------------------------------|
| `MaterialInstance.of(String texture)`           | Opaque texture with defaults            |
| `MaterialInstance.opaque(String)`               | Explicit opaque rendering               |
| `MaterialInstance.alphaTest(String)`            | Binary transparency (solid/transparent) |
| `MaterialInstance.alphaTestSingleSided(String)` | Binary transparency, single-sided       |
| `MaterialInstance.blend(String)`                | Smooth transparency blending            |
| `MaterialInstance.doubleSided(String)`          | Visible from both sides                 |

### Render Methods

| RenderMethod                        | Description                                      | Use Case                      |
|-------------------------------------|--------------------------------------------------|-------------------------------|
| `OPAQUE`                            | Fully opaque                                     | Solid blocks like stone       |
| `ALPHA_TEST`                        | Binary transparency                              | Leaves, flowers, crops        |
| `ALPHA_TEST_SINGLE_SIDED`           | Binary, one-sided                                | Glass panes                   |
| `ALPHA_TEST_TO_OPAQUE`              | Binary transparency, opaque at distance          | Performance-optimized leaves  |
| `ALPHA_TEST_SINGLE_SIDED_TO_OPAQUE` | Single-sided, opaque at distance                 | Performance-optimized panes   |
| `BLEND`                             | Smooth transparency                              | Stained glass, water          |
| `BLEND_TO_OPAQUE`                   | Smooth transparency, opaque at distance          | Performance-optimized glass   |
| `DOUBLE_SIDED`                      | Both sides visible                               | Vines, banners                |

### Example with Transparency

```java linenums="1"
Materials.builder().any(MaterialInstance.alphaTest("leaves_texture"))
```

## Transformation

Apply rotation, scale, and translation to the block model.

```java linenums="1"
import org.allaymc.server.block.type.BlockStateDefinition.Transformation;

Transformation.builder()
    .rx(0).ry(90).rz(0)      // Rotation in degrees (0, 90, 180, 270)
    .sx(1).sy(1).sz(1)       // Scale (default 1.0)
    .tx(0).ty(0).tz(0)       // Translation
    .build()
```

### Properties

| Property         | Type    | Description                                      |
|------------------|---------|--------------------------------------------------|
| `rx`, `ry`, `rz` | `int`   | Rotation in degrees (must be 0, 90, 180, or 270) |
| `sx`, `sy`, `sz` | `float` | Scale factors (default 1.0)                      |
| `tx`, `ty`, `tz` | `float` | Translation offsets                              |

!!! warning "Rotation Constraints"
    Rotation values must be 0, 90, 180, or 270 degrees. Other values will cause rendering issues.

### Rotation Example

Create a block that rotates based on facing direction:

```java linenums="1"
CustomBlockDefinitionGenerator.of(state -> {
    var facing = state.getPropertyValue(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION);
    int rotation = switch (facing) {
        case NORTH -> 0;
        case EAST -> 90;
        case SOUTH -> 180;
        case WEST -> 270;
    };

    return BlockStateDefinition.builder()
        .geometry(Geometry.of("geometry.directional_block"))
        .transformation(Transformation.builder().ry(rotation).build())
        .build();
});
```

## Complete Examples

### Growth Stage Crop

```java linenums="1"
import org.allaymc.api.block.property.type.IntPropertyType;

// Define growth property (0-7)
var AGE = IntPropertyType.of("age", 0, 7, 0);

AllayBlockType.builder(CustomCropImpl.class)
    .identifier("myplugin:magic_crop")
    .setProperties(AGE)
    .blockDefinitionGenerator(
        CustomBlockDefinitionGenerator.of(state -> {
            int age = state.getPropertyValue(AGE);
            return BlockStateDefinition.builder()
                .geometry(Geometry.of("geometry.crop"))
                .materials(Materials.builder().any(MaterialInstance.alphaTest("magic_crop_stage_" + age)))
                .build();
        }))
    .build();
```

### Multi-State Machine Block

```java linenums="1"
import org.allaymc.api.block.property.type.BooleanPropertyType;
import org.allaymc.api.block.property.type.EnumPropertyType;

enum MachineState { OFF, IDLE, RUNNING }

var POWERED = BooleanPropertyType.of("powered", false);
var STATE = EnumPropertyType.of("state", MachineState.class, MachineState.OFF);

AllayBlockType.builder(MachineBlockImpl.class)
    .identifier("myplugin:processing_machine")
    .setProperties(POWERED, STATE)
    .blockDefinitionGenerator(
        CustomBlockDefinitionGenerator.of(state -> {
            boolean powered = state.getPropertyValue(POWERED);
            MachineState machineState = state.getPropertyValue(STATE);

            String geometry = powered ? "geometry.machine_on" : "geometry.machine_off";
            String texture = switch (machineState) {
                case OFF -> "machine_off";
                case IDLE -> "machine_idle";
                case RUNNING -> "machine_running";
            };

            return BlockStateDefinition.builder()
                .geometry(Geometry.of(geometry))
                .materials(Materials.builder().any(texture))
                .displayName("Processing Machine")
                .build();
        }))
    .build();
```

### Rotatable Block with Custom NBT Components

```java linenums="1"
import org.cloudburstmc.nbt.NbtMap;
import java.util.Map;

// Custom components (advanced usage)
Map<String, NbtMap> customComponents = Map.of(
    "minecraft:destructible_by_explosion", NbtMap.builder()
        .putFloat("explosion_resistance", 100.0f)
        .build()
);

AllayBlockType.builder(ReinforcedBlockImpl.class)
    .identifier("myplugin:reinforced_block")
    .blockDefinitionGenerator(new CustomBlockDefinitionGenerator(
        state -> BlockStateDefinition.builder()
            .geometry(Geometry.of("geometry.reinforced"))
            .materials(Materials.builder().any("reinforced_texture"))
            .build(),
        customComponents))
    .build();
```

## Automatic Optimization

The system automatically optimizes your definitions:

1. **Global Component Extraction**: Properties shared by all states become global components
2. **Permutation Merging**: States with identical rendering are merged into single permutations
3. **Molang Condition Generation**: Efficient conditions for state-based switching

### Example of Optimization

If you define:

```java linenums="1"
// Only geometry changes based on OPEN_BIT, materials are the same
CustomBlockDefinitionGenerator.of(state -> {
    boolean open = state.getPropertyValue(OPEN_BIT);
    return BlockStateDefinition.builder()
        .geometry(Geometry.of(open ? "geometry.open" : "geometry.closed"))
        .materials(Materials.builder().any("door_texture"))  // Same for all
        .build();
});
```

The system will:

- Extract `materials` as a global component (same for all states)
- Create only 2 permutations for the geometry (open vs closed)
- Generate efficient Molang conditions: `q.block_state('open_bit') == 1`

## Physical Properties

Physical properties like collision shape, light emission, and friction are **automatically read** from
`BlockStateData` and don't need to be specified in `BlockStateDefinition`:

- Collision shape
- Selection box
- Light emission
- Light dampening
- Friction coefficient

These are configured through block components, not the definition generator.

## Automatic Components from Block Tags

Certain block tags automatically add components to your custom block:

| Block Tag        | Effect                                    |
|------------------|-------------------------------------------|
| `REPLACEABLE`    | Block can be replaced when placing others |
| `POTTABLE_PLANT` | Block can be placed in a flower pot       |

```java linenums="1"
import org.allaymc.api.block.tag.BlockTags;

AllayBlockType.builder(CustomFlowerImpl.class)
    .identifier("myplugin:custom_flower")
    .addBlockTag(BlockTags.POTTABLE_PLANT)  // Can be placed in flower pots
    .blockDefinitionGenerator(
        CustomBlockDefinitionGenerator.ofTexture("custom_flower"))
    .build();
```

## Tips and Best Practices

!!! tip "Start Simple"
    Begin with `ofTexture()` or `ofConstant()` and only use `of()` when you need state-dependent rendering.

!!! tip "Reuse Definitions"
    If multiple states share the same appearance, the optimizer will merge them automatically.
    You don't need to optimize manually.

!!! tip "Use Descriptive Geometry Names"
    Name your geometry files clearly: `geometry.my_block_open`, `geometry.my_block_closed`.

!!! warning "Geometry Must Exist"
    The geometry identifier must reference an actual geometry file in your resource pack.
    Missing geometries will cause client-side rendering issues.

!!! warning "Texture Names"
    Texture names must match entries in your resource pack's `terrain_texture.json` or `textures/item_texture.json`.
