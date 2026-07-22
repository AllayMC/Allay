---
comments: true
---

# Custom Item API

This guide explains how to register the client-facing parameters of a custom item. `CustomItemDefinition` is an
immutable parameter object: it contains texture and rendering choices, but no encoded protocol NBT. Allay combines it
with the finished item type and encodes the appropriate definition for each supported client.

!!! warning "Server Module Required"
    The Custom Item API belongs to the server module. Set `apiOnly = false` in the AllayGradle configuration:

    ```kotlin
    allay {
        apiOnly = false
    }
    ```

    Server APIs may change between versions. See the
    [AllayGradle documentation](https://github.com/AllayMC/AllayGradle) for dependency setup.

Read the [Item API tutorial](../tutorials/item-api.md) first if you have not created an item type before.

## Quick Start

Pass the definition object directly to the item builder:

```java linenums="1"
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.item.type.CustomItemDefinition;

AllayItemType.builder(MyRubyItemStack.class)
    .identifier("myplugin:ruby")
    .customItemDefinition(CustomItemDefinition.builder()
        .texture("ruby")
        .displayName("item.myplugin.ruby.name")
        .build())
    .build();
```

There is no generator callback. The definition stores only the values supplied here; the completed `ItemType` remains
the source of gameplay data such as stack size, durability, armor value, tags, and components.

## Definition Fields

| Field | Purpose |
| --- | --- |
| `texture` | Required short name from `item_texture.json` |
| `displayName` | Literal text or translation key; defaults to the item identifier |
| `renderOffsets` | Main-hand and off-hand transforms for first- and third-person views |
| `foil` | Always display the enchantment glint |
| `canDestroyInCreative` | Permit creative-mode block destruction; defaults to `true` |
| `cooldown` | Cooldown duration in seconds |
| `rawProperties` | Advanced raw entries merged into `item_properties` |
| `rawComponents` | Advanced raw entries merged into the component table |

The texture must not be blank and cooldown cannot be negative. Input maps and vectors are copied, so later mutations do
not alter a registered definition.

```java linenums="1"
CustomItemDefinition definition = CustomItemDefinition.builder()
    .texture("charged_crystal")
    .displayName("item.myplugin.charged_crystal.name")
    .foil(true)
    .canDestroyInCreative(false)
    .cooldown(2)
    .build();
```

## Render Offsets

Render offsets control item position, rotation, and scale in each hand and camera view. The two convenience factories
cover common texture-based models.

### Scale Helper

```java
import org.allaymc.server.item.type.CustomItemDefinition.RenderOffsets;

var definition = CustomItemDefinition.builder()
    .texture("large_hammer")
    .renderOffsets(RenderOffsets.scale(2.0f))
    .build();
```

### Texture Size Helper

```java
var definition32 = CustomItemDefinition.builder()
    .texture("ruby_32")
    .renderOffsets(RenderOffsets.textureSize(32))
    .build();

var definition64 = CustomItemDefinition.builder()
    .texture("ruby_64")
    .renderOffsets(RenderOffsets.textureSize(64))
    .build();
```

Texture sizes must be positive multiples of 16. `16`, `32`, `48`, and `64` are common values.

### Full Customization

An `Offset` accepts three optional JOML vectors:

- `position`: translation in block units;
- `rotation`: rotation in degrees;
- `scale`: scale factors for the three axes.

```java linenums="1"
import org.allaymc.server.item.type.CustomItemDefinition.RenderOffsets;
import org.allaymc.server.item.type.CustomItemDefinition.RenderOffsets.Hand;
import org.allaymc.server.item.type.CustomItemDefinition.RenderOffsets.Offset;
import org.joml.Vector3f;

RenderOffsets offsets = RenderOffsets.builder()
    .mainHand(Hand.builder()
        .firstPerson(Offset.builder()
            .position(new Vector3f(0.0f, 0.05f, -0.1f))
            .rotation(new Vector3f(0, 45, 0))
            .scale(new Vector3f(0.12f, 0.12f, 0.12f))
            .build())
        .thirdPerson(Offset.builder()
            .position(new Vector3f(0, 0.15f, 0))
            .rotation(new Vector3f(0, 90, 0))
            .scale(new Vector3f(0.08f, 0.08f, 0.08f))
            .build())
        .build())
    .offHand(Hand.builder()
        .firstPerson(Offset.builder()
            .scale(new Vector3f(0.1f, 0.1f, 0.1f))
            .build())
        .build())
    .build();
```

Omitted hands and views use client defaults.

## Special Flags

### Enchantment Glint

Set `foil(true)` to show the glint even when the stack has no enchantments:

```java
CustomItemDefinition.builder()
    .texture("enchanted_crystal")
    .foil(true)
    .build();
```

### Off-Hand Support

Off-hand support is a gameplay tag, not a client-definition flag. Add `ItemTags.ALLOW_OFFHAND` to the item type:

```java linenums="1"
AllayItemType.builder(MyCharmItemStack.class)
    .identifier("myplugin:charm")
    .setItemTags(Set.of(ItemTags.ALLOW_OFFHAND))
    .customItemDefinition(CustomItemDefinition.builder()
        .texture("charm")
        .build())
    .build();
```

Allay reads the tag while encoding the definition, so the server and client use the same capability source.

### Creative Destruction

`canDestroyInCreative` defaults to `true`. Set it to `false` for sword-like or utility items that should not instantly
break targeted blocks in creative mode.

### Cooldown

`cooldown(seconds)` adds a cooldown component whose category is the item identifier:

```java
CustomItemDefinition.builder()
    .texture("teleport_orb")
    .cooldown(3)
    .build();
```

Stacks of the same item share that category and therefore the cooldown timer.

## Derived Components

Allay examines the finished item type during protocol initialization and derives client metadata from the same gameplay
configuration used by the server.

### Armor Items

If the item stack implements `ItemWearableComponent`, Allay adds armor protection and the correct wearable slot for a
helmet, chestplate, leggings, or boots. The protection value comes from `ItemData.armorValue()`.

### Tools

If the stack implements `ItemToolComponent`, Allay marks it as hand-equipped and uses `ItemData.attackDamage()` for the
client damage property.

### Food and Drinks

If the stack implements `ItemEdibleComponent`, Allay derives use duration, eat/drink animation, and whether it may
always be consumed from the component implementation.

### Damageable Items

When `ItemData.isDamageable()` is true, the client durability component uses `ItemData.maxDamage()`.

### Stack Size and Tags

Maximum stack size comes from `ItemData.maxStackSize()`. All item tags are included in the encoded component data, and
`ItemTags.ALLOW_OFFHAND` also controls the off-hand property.

## Raw Extensions

Structured fields should be preferred because Allay can encode them consistently. Raw maps remain available for new or
specialized Bedrock components:

```java linenums="1"
var definition = CustomItemDefinition.builder()
    .texture("scanner")
    .rawProperties(Map.of(
        "myplugin:property", NbtMap.builder().putBoolean("enabled", true).build()))
    .rawComponents(Map.of(
        "minecraft:interact_button", NbtMap.builder()
            .putString("value", "action.interact.scan")
            .build()))
    .build();
```

Raw properties are applied after derived properties, and raw components are applied after built-in components. They may
therefore intentionally replace an entry with the same key. Allay does not translate raw payloads between protocol
versions; the plugin is responsible for compatibility.

## Complete Examples

### Custom Gem

```java linenums="1"
AllayItemType.builder(MyGemItemStack.class)
    .identifier("myplugin:moonstone")
    .customItemDefinition(CustomItemDefinition.builder()
        .texture("moonstone")
        .displayName("item.myplugin.moonstone.name")
        .foil(true)
        .renderOffsets(CustomItemDefinition.RenderOffsets.textureSize(32))
        .build())
    .build();
```

### Large Tool

```java linenums="1"
AllayItemType.builder(MyHammerItemStack.class)
    .identifier("myplugin:war_hammer")
    .itemData(ItemData.builder()
        .maxStackSize(1)
        .isDamageable(true)
        .maxDamage(800)
        .attackDamage(12)
        .build())
    .customItemDefinition(CustomItemDefinition.builder()
        .texture("war_hammer")
        .displayName("item.myplugin.war_hammer.name")
        .renderOffsets(CustomItemDefinition.RenderOffsets.textureSize(64))
        .canDestroyInCreative(false)
        .build())
    .build();
```

### Food with a Cooldown

```java linenums="1"
AllayItemType.builder(MyFruitItemStack.class)
    .identifier("myplugin:star_fruit")
    .customItemDefinition(CustomItemDefinition.builder()
        .texture("star_fruit")
        .displayName("item.myplugin.star_fruit.name")
        .foil(true)
        .cooldown(1)
        .build())
    .build();
```

The edible behavior still belongs in the item stack's components. The definition only supplies client presentation and
explicit client options.

### Throwable Item

```java linenums="1"
AllayItemType.builder(MyOrbItemStack.class)
    .identifier("myplugin:magic_orb")
    .itemData(ItemData.builder().maxStackSize(16).build())
    .setItemTags(Set.of(ItemTags.ALLOW_OFFHAND))
    .customItemDefinition(CustomItemDefinition.builder()
        .texture("magic_orb")
        .displayName("item.myplugin.magic_orb.name")
        .cooldown(1)
        .build())
    .build();
```

The projectile-spawning behavior belongs in the item stack implementation; the definition supplies its presentation,
off-hand declaration, and client cooldown.

### Shield-Like Item

```java linenums="1"
var shieldOffsets = CustomItemDefinition.RenderOffsets.builder()
    .mainHand(CustomItemDefinition.RenderOffsets.Hand.builder()
        .firstPerson(CustomItemDefinition.RenderOffsets.Offset.builder()
            .scale(new Vector3f(1.8f, 1.8f, 1.8f))
            .build())
        .build())
    .offHand(CustomItemDefinition.RenderOffsets.Hand.builder()
        .firstPerson(CustomItemDefinition.RenderOffsets.Offset.builder()
            .scale(new Vector3f(1.8f, 1.8f, 1.8f))
            .build())
        .build())
    .build();

AllayItemType.builder(MyShieldItemStack.class)
    .identifier("myplugin:magic_shield")
    .itemData(ItemData.builder()
        .maxStackSize(1)
        .isDamageable(true)
        .maxDamage(336)
        .build())
    .setItemTags(Set.of(ItemTags.ALLOW_OFFHAND))
    .customItemDefinition(CustomItemDefinition.builder()
        .texture("magic_shield")
        .displayName("item.myplugin.magic_shield.name")
        .renderOffsets(shieldOffsets)
        .build())
    .build();
```

## Item Tags

Tags remain part of `ItemType` configuration and are copied into the client definition automatically:

```java linenums="1"
AllayItemType.builder(MySwordItemStack.class)
    .identifier("myplugin:ruby_sword")
    .setItemTags(Set.of(ItemTags.IS_SWORD, ItemTags.IS_TOOL))
    .customItemDefinition(CustomItemDefinition.builder()
        .texture("ruby_sword")
        .canDestroyInCreative(false)
        .build())
    .build();
```

## Best Practices

- Match texture short names to `textures/item_texture.json` and ship the corresponding resource pack.
- Use `RenderOffsets.textureSize(...)` for square textures larger than 16 pixels before tuning individual vectors.
- Put gameplay behavior in item components and let Allay derive armor, tool, food, durability, stack, and tag metadata.
- Use translation keys for localized display names.
- Keep cooldowns user-visible and reserve raw extensions for fields not represented by the structured model.
