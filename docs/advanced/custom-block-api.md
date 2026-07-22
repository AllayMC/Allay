---
comments: true
---

# Custom Block API

This guide explains how to describe custom block rendering with protocol-independent Java objects. Allay stores the
definition on the block type and encodes it for each supported Bedrock protocol when networking is initialized.

!!! warning "Server Module Required"
    The Custom Block API belongs to the server module. Set `apiOnly = false` in the AllayGradle configuration:

    ```kotlin
    allay {
        apiOnly = false
    }
    ```

    Server APIs may change between versions. See the
    [AllayGradle documentation](https://github.com/AllayMC/AllayGradle) for dependency setup.

Before using this API, become familiar with the [Block API tutorial](../tutorials/block-api.md).

## Overview

A custom block definition has two levels:

- `CustomBlockDefinition` is created by `AllayBlockType.Builder`. It contains type-wide configuration and an immutable
  state-definition table.
- `CustomBlockStateDefinition` describes the geometry, materials, transformation, and display name for one state.

You normally construct only `CustomBlockStateDefinition`. Pass either one constant definition or a state function to
`customBlockDefinition(...)`. The function runs once for each state while the block type is built; it is not retained
or called while packets are sent.

## Quick Start

### Simple Textured Block

```java linenums="1"
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.block.type.CustomBlockStateDefinition;

AllayBlockType.builder(MyBlockImpl.class)
    .identifier("myplugin:ruby_block")
    .customBlockDefinition(CustomBlockStateDefinition.ofTexture("ruby_block"))
    .build();
```

The texture name is a short name from `terrain_texture.json`.

### Block with Custom Geometry

```java linenums="1"
import org.allaymc.server.block.type.CustomBlockStateDefinition.Geometry;
import org.allaymc.server.block.type.CustomBlockStateDefinition.Materials;

var lampDefinition = CustomBlockStateDefinition.builder()
    .geometry(Geometry.of("geometry.myplugin.lamp"))
    .materials(Materials.builder().any("lamp").build())
    .displayName("tile.myplugin.lamp.name")
    .build();

AllayBlockType.builder(MyLampImpl.class)
    .identifier("myplugin:lamp")
    .customBlockDefinition(lampDefinition)
    .build();
```

### State-Dependent Rendering

Use the function overload only when appearance changes with a block property:

```java linenums="1"
import org.allaymc.api.block.property.type.BlockPropertyTypes;

AllayBlockType.builder(MyDoorImpl.class)
    .identifier("myplugin:brass_door")
    .setProperties(BlockPropertyTypes.OPEN_BIT)
    .customBlockDefinition(state -> {
        boolean open = state.getPropertyValue(BlockPropertyTypes.OPEN_BIT);
        return CustomBlockStateDefinition.builder()
            .geometry(open ? "geometry.myplugin.door_open" : "geometry.myplugin.door_closed")
            .materials(Materials.builder().any("brass_door").build())
            .build();
    })
    .build();
```

Every returned definition must be non-null. Prefer the constant overload when all states look alike.

## State Definition

`CustomBlockStateDefinition` accepts these fields:

| Field | Purpose |
| --- | --- |
| `geometry` | Model identifier plus optional bone visibility, culling, and UV locking |
| `materials` | Textures and render methods assigned by face or material name |
| `transformation` | Model rotation, scale, and translation |
| `displayName` | Literal text or translation key; defaults to the block identifier |
| `rawComponents` | Advanced raw Bedrock components for this state |

The object is immutable after construction. Collection arguments are copied, so builders and source maps may be reused
without changing a registered block.

## Geometry

For a simple model, provide only its resource-pack identifier:

```java
Geometry geometry = Geometry.of("geometry.myplugin.machine");
```

The advanced builder adds conditional bones, culling metadata, and UV locks:

```java linenums="1"
Geometry geometry = Geometry.builder()
    .identifier("geometry.myplugin.machine")
    .boneVisibility("indicator", BlockPropertyTypes.POWERED_BIT)
    .boneVisibility("cover", BlockPropertyTypes.OPEN_BIT, false)
    .culling("myplugin:culling.machine")
    .cullingLayer("myplugin:machine_layer")
    .uvLock("base", "frame")
    .build();
```

### Bone Visibility

Bone visibility can be expressed in four ways:

```java
.boneVisibility("always_visible", true)
.boneVisibility("powered_part", BlockPropertyTypes.POWERED_BIT)
.boneVisibility("age_three", BlockPropertyTypes.AGE_4, 3)
.boneVisibilityMolang("advanced", "q.block_state('myplugin:mode') == 2")
```

Property overloads are preferred because Allay formats the Molang condition consistently. Raw Molang remains available
for expressions that cannot be represented by one property comparison.

### Culling

`culling(...)` selects a culling rules resource. `cullingLayer(...)` assigns the geometry to a culling layer. These are
independent settings: use either or both when the resource pack defines them.

### UV Lock

Use `uvLockAll()` to lock every bone, or `uvLock(String...)` / `uvLock(List<String>)` for selected bones.
UV locking keeps textures aligned when transformed geometry rotates.

## Materials

Material names may be face names, the wildcard `*`, or names referenced by custom geometry. Build the collection before
passing it to the state definition:

```java linenums="1"
Materials materials = Materials.builder()
    .any("machine_side")
    .face(BlockFace.UP, "machine_top")
    .face(BlockFace.DOWN, MaterialInstance.opaque("machine_bottom"))
    .sides(MaterialInstance.alphaTest("machine_grille"))
    .build();
```

`any(...)` supplies the fallback material. `face(...)` overrides one face, and `sides(...)` assigns north, south, east,
and west together.

### Material Instances

Factory methods cover the standard render methods:

| Factory | Render method | Typical use |
| --- | --- | --- |
| `opaque(texture)` | `OPAQUE` | Solid blocks |
| `blend(texture)` | `BLEND` | Translucent glass or ice |
| `doubleSided(texture)` | `DOUBLE_SIDED` | Surfaces visible from both sides |
| `alphaTest(texture)` | `ALPHA_TEST` | Cutout vegetation and crossed planes |
| `alphaTestSingleSided(texture)` | `ALPHA_TEST_SINGLE_SIDED` | Doors and other one-sided cutouts |
| `alphaTestToOpaque(texture)` | `ALPHA_TEST_TO_OPAQUE` | Cutouts that become opaque at distance |
| `alphaTestSingleSidedToOpaque(texture)` | `ALPHA_TEST_SINGLE_SIDED_TO_OPAQUE` | One-sided distance optimization |
| `blendToOpaque(texture)` | `BLEND_TO_OPAQUE` | Translucency that becomes opaque at distance |

The builder exposes the same options plus lighting flags:

```java linenums="1"
MaterialInstance leaves = MaterialInstance.builder()
    .texture("silver_leaves")
    .renderMethod(RenderMethod.ALPHA_TEST)
    .faceDimming(true)
    .ambientOcclusionIntensity(0.8f)
    .randomUVRotation(true)
    .textureVariation(true)
    .build();
```

Allay encodes these fields directly in the format understood by each client version.

## Transformation

Transformations use degrees for rotation, factors for scale, and block units for translation:

```java linenums="1"
Transformation transformation = Transformation.builder()
    .ry(90)
    .sx(0.75f)
    .sy(1.25f)
    .sz(0.75f)
    .ty(0.125f)
    .build();
```

Rotations must be multiples of 90 degrees. Negative and values above 360 are accepted when they remain multiples of 90.
An omitted or zero scale axis uses `1.0`.

State-dependent transformations can rotate one geometry without duplicating resource-pack models:

```java linenums="1"
AllayBlockType.builder(MyDirectionalBlockImpl.class)
    .identifier("myplugin:directional_console")
    .setProperties(BlockPropertyTypes.DIRECTION_4)
    .customBlockDefinition(state -> {
        int direction = state.getPropertyValue(BlockPropertyTypes.DIRECTION_4);
        return CustomBlockStateDefinition.builder()
            .geometry("geometry.myplugin.directional_console")
            .materials(Materials.builder().any("directional_console").build())
            .transformation(Transformation.builder().ry(direction * 90).build())
            .build();
    })
    .build();
```

## Placement Rotation

Placement rotation belongs to the block type, not an individual state:

```java
AllayBlockType.builder(MyMachineImpl.class)
    .identifier("myplugin:machine")
    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
    .customBlockDefinition(machineDefinition)
    .customBlockRotationOffset(90)
    .build();
```

Valid offsets are `0`, `90`, `180`, and `270`. The option affects the placement-direction trait and is useful with the
cardinal-direction or facing-direction properties.

## Physical Properties

Do not duplicate collision, selection, light, or friction settings in the client definition. Allay derives these for
each state from `BlockStateData`:

- collision shape and selection shape;
- light emission and dampening;
- friction;
- server-authoritative mining behavior.

This keeps rendering metadata aligned with server behavior. Blocks tagged `REPLACEABLE` or `POTTABLE_PLANT` also receive
the matching client components automatically. Every block tag is included in the state components.

```java linenums="1"
AllayBlockType.builder(MyFlowerImpl.class)
    .identifier("myplugin:moon_flower")
    .setBlockTags(Set.of(BlockTags.POTTABLE_PLANT, BlockTags.REPLACEABLE))
    .customBlockDefinition(CustomBlockStateDefinition.builder()
        .geometry("geometry.myplugin.cross")
        .materials(Materials.builder()
            .any(MaterialInstance.alphaTest("moon_flower"))
            .build())
        .build())
    .build();
```

## Raw Components

Raw NBT is an escape hatch for components that do not yet have a structured API. Global components apply to the entire
type; state components apply only to the corresponding permutation:

```java linenums="1"
var stateDefinition = CustomBlockStateDefinition.builder()
    .materials(Materials.builder().any("signal_lamp").build())
    .rawComponents(Map.of(
        "minecraft:random_offset", NbtMap.builder().putString("type", "xyz").build()))
    .build();

AllayBlockType.builder(MySignalLampImpl.class)
    .identifier("myplugin:signal_lamp")
    .customBlockDefinition(stateDefinition)
    .customBlockRawComponent("myplugin:global_marker", NbtMap.EMPTY)
    .build();
```

Use `customBlockRawComponents(Map)` to replace the complete global raw-component map. Raw payloads are merged as supplied
and are not adapted for older protocols; compatibility is the plugin's responsibility. Server-derived physical
components take precedence over state-level raw entries with the same name.

## Complete Examples

### Growth Stage Crop

```java linenums="1"
AllayBlockType.builder(MyCropImpl.class)
    .identifier("myplugin:moon_crop")
    .setProperties(BlockPropertyTypes.AGE_16)
    .customBlockDefinition(state -> {
        int age = state.getPropertyValue(BlockPropertyTypes.AGE_16);
        return CustomBlockStateDefinition.builder()
            .geometry(Geometry.builder()
                .identifier("geometry.myplugin.crop")
                .boneVisibilityMolang("flowers", "q.block_state('age') >= 5")
                .uvLockAll()
                .build())
            .materials(Materials.builder()
                .any(MaterialInstance.alphaTest("moon_crop_" + age))
                .build())
            .displayName("tile.myplugin.moon_crop.name")
            .build();
    })
    .build();
```

### Multi-State Machine

```java linenums="1"
enum MachineMode { OFF, IDLE, RUNNING }

var POWERED = BooleanPropertyType.of("myplugin:powered", false);
var MODE = EnumPropertyType.of("myplugin:mode", MachineMode.class, MachineMode.OFF);

AllayBlockType.builder(MyMachineImpl.class)
    .identifier("myplugin:processing_machine")
    .setProperties(POWERED, MODE)
    .customBlockDefinition(state -> {
        boolean powered = state.getPropertyValue(POWERED);
        MachineMode mode = state.getPropertyValue(MODE);
        String texture = switch (mode) {
            case OFF -> "machine_off";
            case IDLE -> "machine_idle";
            case RUNNING -> "machine_running";
        };
        return CustomBlockStateDefinition.builder()
            .geometry(powered ? "geometry.myplugin.machine_on" : "geometry.myplugin.machine_off")
            .materials(Materials.builder().any(texture).build())
            .displayName("tile.myplugin.processing_machine.name")
            .build();
    })
    .build();
```

### Rotatable Reinforced Block with a Raw Component

```java linenums="1"
AllayBlockType.builder(MyReinforcedBlockImpl.class)
    .identifier("myplugin:reinforced_block")
    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
    .customBlockDefinition(CustomBlockStateDefinition.builder()
        .geometry("geometry.myplugin.reinforced")
        .materials(Materials.builder().any("reinforced_block").build())
        .build())
    .customBlockRotationOffset(90)
    .customBlockRawComponent(
        "minecraft:destructible_by_explosion",
        NbtMap.builder().putFloat("explosion_resistance", 100.0f).build())
    .build();
```

Keep state functions deterministic and inexpensive. They run only during registration, but their results become the
cached definitions used by every supported protocol.

## Best Practices

- Start with `CustomBlockStateDefinition.ofTexture(...)` or the constant overload and introduce a state function only
  when appearance actually varies.
- Use descriptive resource names such as `geometry.myplugin.machine_open` and keep texture short names synchronized with
  `terrain_texture.json`.
- Verify that every geometry and culling identifier exists in the resource pack; missing assets render as placeholders.
- Keep raw components small and document the client versions they target.
