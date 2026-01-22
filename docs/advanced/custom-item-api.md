---
comments: true
---

# Custom Item API

This guide covers the advanced Custom Item API for creating items with custom client-side properties
such as custom textures, display names, render offsets, and special flags.

**What you will learn:**

- How to create custom items with CustomItemDefinitionGenerator
- Configuring textures, display names, and render offsets
- Special item flags (foil, off-hand, cooldown)
- Auto-detection for armor, tools, and food items

!!! warning "Server Module Required"
    The Custom Item API requires access to the **server module**. In your `build.gradle.kts`, you must set
    `apiOnly = false` in the `allay` block:

    ```kotlin
    allay {
        apiOnly = false  // Required for Custom Item API
        // ... other settings
    }
    ```

    Note that internal APIs may change between versions. See [AllayGradle documentation](https://github.com/AllayMC/AllayGradle)
    for more details.

!!! tip "Prerequisites"
    Before diving into custom items, make sure you understand the basics of the Item API covered in
    [Item API Tutorial](../tutorials/item-api.md).

## Overview

The Custom Item API uses a **builder pattern** through `CustomItemDefinitionGenerator`. You configure
properties like texture, display name, render offsets, and various flags. The system automatically
detects and configures special item types (armor, tools, food).

## Quick Start

### Simple Textured Item

The simplest way to create a custom item with a texture:

```java linenums="1"
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.item.type.CustomItemDefinitionGenerator;

AllayItemType.builder(MyItemStackImpl.class)
    .identifier("myplugin:ruby")
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("ruby")
            .build())
    .build();
```

### Item with Display Name

```java linenums="1"
AllayItemType.builder(MyItemStackImpl.class)
    .identifier("myplugin:magic_gem")
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("magic_gem")
            .displayName("Magical Gem")
            .build())
    .build();
```

### Item with Enchantment Glint

```java linenums="1"
AllayItemType.builder(MyItemStackImpl.class)
    .identifier("myplugin:enchanted_crystal")
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("enchanted_crystal")
            .displayName("Enchanted Crystal")
            .foil(true)  // Always show enchantment glint
            .build())
    .build();
```

## CustomItemDefinitionGenerator

The main builder for custom item definitions.

### Builder Properties

| Property               | Type                  | Description                                         |
|------------------------|-----------------------|-----------------------------------------------------|
| `texture`              | `String`              | **Required.** Texture name from resource pack       |
| `displayName`          | `String`              | Display name in inventory (null uses identifier)    |
| `renderOffsets`        | `RenderOffsets`       | Item position/rotation when held                    |
| `foil`                 | `boolean`             | Show enchantment glint even without enchantments    |
| `canDestroyInCreative` | `boolean`             | Can destroy blocks in creative mode (default: true) |
| `allowOffHand`         | `boolean`             | Can be placed in off-hand slot                      |
| `cooldown`             | `Integer`             | Item cooldown in ticks                              |
| `customProperties`     | `Map<String, NbtMap>` | Additional NBT properties                           |
| `customComponents`     | `Map<String, NbtMap>` | Custom Bedrock components                           |

### Basic Example

```java linenums="1"
CustomItemDefinitionGenerator.builder()
    .texture("my_item")
    .displayName("My Custom Item")
    .allowOffHand(true)
    .build()
```

### Full Example

```java linenums="1"
CustomItemDefinitionGenerator.builder()
    .texture("special_item")
    .displayName("Special Item")
    .foil(true)
    .canDestroyInCreative(false)
    .allowOffHand(true)
    .cooldown(20)  // 1 second cooldown
    .build()
```

## Render Offsets

Configure how items appear when held in first-person and third-person views.

### Using Scale Helper

The simplest way to adjust item size:

```java linenums="1"
import org.allaymc.server.item.type.RenderOffsets;

CustomItemDefinitionGenerator.builder()
    .texture("large_item")
    .renderOffsets(RenderOffsets.scale(2.0f))  // 2x larger
    .build()
```

### Using Texture Size Helper

Automatically scale based on texture dimensions:

```java linenums="1"
// For a 32x32 texture (2x the normal 16x16)
CustomItemDefinitionGenerator.builder()
    .texture("big_texture_item")
    .renderOffsets(RenderOffsets.textureSize(32))
    .build()

// For a 48x48 texture
CustomItemDefinitionGenerator.builder()
    .texture("huge_texture_item")
    .renderOffsets(RenderOffsets.textureSize(48))
    .build()
```

!!! tip "Texture Size"
    The texture size must be a multiple of 16. Common values: 16 (default), 32, 48, 64.

### Full Customization

For complete control over item positioning:

```java linenums="1"
import org.allaymc.server.item.type.RenderOffsets;
import org.allaymc.server.item.type.RenderOffsets.Hand;
import org.allaymc.server.item.type.RenderOffsets.Offset;
import org.joml.Vector3f;

RenderOffsets offsets = RenderOffsets.builder()
    .mainHand(Hand.builder()
        .firstPerson(Offset.builder()
            .position(new Vector3f(0, 0, 0))
            .rotation(new Vector3f(0, 45, 0))
            .scale(new Vector3f(1.5f, 1.5f, 1.5f))
            .build())
        .thirdPerson(Offset.builder()
            .scale(new Vector3f(1.2f, 1.2f, 1.2f))
            .build())
        .build())
    .offHand(Hand.builder()
        .firstPerson(Offset.builder()
            .scale(new Vector3f(1.5f, 1.5f, 1.5f))
            .build())
        .build())
    .build();

CustomItemDefinitionGenerator.builder()
    .texture("my_item")
    .renderOffsets(offsets)
    .build()
```

### Offset Properties

| Property   | Type       | Description                      |
|------------|------------|----------------------------------|
| `position` | `Vector3f` | Position offset (x, y, z)        |
| `rotation` | `Vector3f` | Rotation in degrees (rx, ry, rz) |
| `scale`    | `Vector3f` | Scale factors (sx, sy, sz)       |

## Special Flags

### Foil (Enchantment Glint)

Make items always display the enchantment shimmer effect, like enchanted golden apples:

```java linenums="1"
CustomItemDefinitionGenerator.builder()
    .texture("legendary_artifact")
    .displayName("Legendary Artifact")
    .foil(true)
    .build()
```

### Off-Hand Support

Allow items to be placed in the off-hand slot:

```java linenums="1"
CustomItemDefinitionGenerator.builder()
    .texture("shield_charm")
    .allowOffHand(true)
    .build()
```

### Destroy in Creative Mode

For items like swords that shouldn't instantly break blocks in creative:

```java linenums="1"
CustomItemDefinitionGenerator.builder()
    .texture("magic_wand")
    .canDestroyInCreative(false)
    .build()
```

### Item Cooldown

Add a cooldown after using the item:

```java linenums="1"
// 2 second cooldown (40 ticks)
CustomItemDefinitionGenerator.builder()
    .texture("teleport_crystal")
    .cooldown(40)
    .build()
```

## Auto-Detection

The generator automatically detects and configures special item types based on their components:

### Armor Items

If your item has `ItemArmorBaseComponent`, the generator automatically:

- Sets the wearable slot (helmet, chestplate, leggings, boots)
- Applies protection value

```java linenums="1"
AllayItemType.builder(CustomHelmetImpl.class)
    .identifier("myplugin:ruby_helmet")
    .addComponent(ItemArmorBaseComponentImpl.class)  // Auto-detected
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("ruby_helmet")
            .build())
    .build();
```

### Tool Items

If your item has `ItemToolComponent`, the generator automatically:

- Sets `hand_equipped` flag (item held sideways)
- Configures attack damage

```java linenums="1"
AllayItemType.builder(CustomPickaxeImpl.class)
    .identifier("myplugin:ruby_pickaxe")
    .addComponent(ItemToolComponentImpl.class)  // Auto-detected
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("ruby_pickaxe")
            .build())
    .build();
```

### Food Items

If your item has `ItemEdibleComponent`, the generator automatically:

- Sets `use_duration` (eating time)
- Sets `use_animation` (eating animation)

```java linenums="1"
AllayItemType.builder(CustomFoodImpl.class)
    .identifier("myplugin:magic_apple")
    .addComponent(ItemEdibleComponentImpl.class)  // Auto-detected
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("magic_apple")
            .displayName("Magic Apple")
            .foil(true)  // Like enchanted golden apple
            .build())
    .build();
```

### Damageable Items

If your item has durability (`ItemData.isDamageable()`), the generator automatically:

- Applies max durability

## Custom NBT Components

For advanced use cases, add custom Bedrock protocol components:

```java linenums="1"
import org.cloudburstmc.nbt.NbtMap;
import java.util.Map;

Map<String, NbtMap> customComponents = Map.of(
    "minecraft:durability", NbtMap.builder()
        .putInt("max_durability", 500)
        .build()
);

CustomItemDefinitionGenerator.builder()
    .texture("durable_item")
    .customComponents(customComponents)
    .build()
```

## Complete Examples

### Custom Gem Item

```java linenums="1"
AllayItemType.builder(GemItemStackImpl.class)
    .identifier("myplugin:sapphire")
    .itemData(ItemData.builder()
        .maxStackSize(64)
        .build())
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("sapphire")
            .displayName("Sapphire")
            .build())
    .build();
```

### Custom Tool with Large Texture

```java linenums="1"
AllayItemType.builder(GreatSwordImpl.class)
    .identifier("myplugin:greatsword")
    .itemData(ItemData.builder()
        .maxStackSize(1)
        .maxDamage(2000)
        .attackDamage(12)
        .build())
    .addComponent(ItemToolComponentImpl.class)
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("greatsword")
            .displayName("Greatsword")
            .renderOffsets(RenderOffsets.textureSize(32))  // 32x32 texture
            .canDestroyInCreative(false)
            .build())
    .build();
```

### Custom Food with Glint

```java linenums="1"
AllayItemType.builder(EnchantedFruitImpl.class)
    .identifier("myplugin:enchanted_fruit")
    .itemData(ItemData.builder()
        .maxStackSize(64)
        .build())
    .addComponent(ItemEdibleComponentImpl.class)
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("enchanted_fruit")
            .displayName("Enchanted Fruit")
            .foil(true)
            .build())
    .build();
```

### Custom Throwable Item

```java linenums="1"
AllayItemType.builder(ThrowableItemImpl.class)
    .identifier("myplugin:magic_orb")
    .itemData(ItemData.builder()
        .maxStackSize(16)
        .build())
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("magic_orb")
            .displayName("Magic Orb")
            .allowOffHand(true)
            .cooldown(20)  // 1 second cooldown
            .build())
    .build();
```

### Custom Shield-Like Item

```java linenums="1"
AllayItemType.builder(MagicShieldImpl.class)
    .identifier("myplugin:magic_shield")
    .itemData(ItemData.builder()
        .maxStackSize(1)
        .maxDamage(336)
        .build())
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("magic_shield")
            .displayName("Magic Shield")
            .allowOffHand(true)
            .renderOffsets(RenderOffsets.builder()
                .mainHand(Hand.builder()
                    .firstPerson(Offset.builder()
                        .scale(new Vector3f(1.8f, 1.8f, 1.8f))
                        .build())
                    .build())
                .build())
            .build())
    .build();
```

## Item Tags

Item tags are automatically added from your item type's configured tags:

```java linenums="1"
AllayItemType.builder(CustomSwordImpl.class)
    .identifier("myplugin:ruby_sword")
    .addItemTag(ItemTags.IS_SWORD)
    .addItemTag(ItemTags.IS_TOOL)
    .itemDefinitionGenerator(
        CustomItemDefinitionGenerator.builder()
            .texture("ruby_sword")
            .build())
    .build();
```

## Tips and Best Practices

!!! tip "Texture Requirements"
    Texture names must match entries in your resource pack's `textures/item_texture.json`.

!!! tip "Use textureSize() for Large Items"
    For items with textures larger than 16x16, use `RenderOffsets.textureSize()` for correct scaling.

!!! tip "Let Auto-Detection Work"
    Don't manually configure armor slots or tool properties - add the appropriate components
    and let the generator detect them automatically.

!!! warning "Cooldown Values"
    Cooldown is in game ticks (20 ticks = 1 second). Very short cooldowns may not be noticeable.

!!! warning "Display Name Localization"
    For translatable display names, use translation keys that start with the appropriate prefix.
    The display name supports the `@MayContainTrKey` annotation pattern.

!!! warning "Resource Pack Required"
    Custom items require a resource pack with the appropriate textures. Without it,
    items will appear with missing texture placeholders.
