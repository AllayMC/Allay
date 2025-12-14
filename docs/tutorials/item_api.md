---
comments: true
---

# Items API

This tutorial introduces the core concepts of the Allay Items API and shows practical ways to find item types, work
with item stacks, enchantments, and item data.

**What you will learn:**

- Key concepts: ItemStack, ItemType, ItemTypes, ItemData, ItemTag
- How to create and manipulate item stacks
- Working with enchantments
- Item properties and metadata

## Key Concepts

### ItemStack

Represents an actual item instance that can be held, stored, or dropped. An ItemStack has:

- A type (ItemType)
- A count (how many items in the stack)
- Meta/damage value
- Optional enchantments, custom name, lore, etc.

```java linenums="1"
// Create a stack of 64 diamonds
ItemStack diamonds = ItemTypes.DIAMOND.createItemStack(64);

// Get item information
int count = diamonds.getCount();
ItemType<?> type = diamonds.getItemType();
```

### ItemType

Describes a kind of item (e.g., Diamond, Sword, Apple). Each ItemType:

- Has a unique identifier
- Can create ItemStack instances
- Has associated ItemData (max stack size, durability, etc.)
- May have ItemTags for categorization

```java linenums="1"
ItemType<?> diamondType = ItemTypes.DIAMOND;
ItemType<?> swordType = ItemTypes.DIAMOND_SWORD;

// Create an item stack from the type
ItemStack stack = diamondType.createItemStack(32);
```

### ItemTypes

A convenience class with static fields for all known vanilla item types.
Great for quick access without looking up identifiers manually.

```java linenums="1"
ItemType<?> diamond = ItemTypes.DIAMOND;
ItemType<?> ironSword = ItemTypes.IRON_SWORD;
ItemType<?> apple = ItemTypes.APPLE;
ItemType<?> air = ItemTypes.AIR;
```

### ItemData

Contains metadata about an item type such as:

- Max stack size
- Durability (max damage)
- Attack damage
- Armor value
- Enchantability

```java linenums="1"
ItemData data = ItemTypes.DIAMOND_SWORD.getItemData();
int maxDamage = data.maxDamage();        // Durability
int attackDamage = data.attackDamage();  // Base attack damage
int maxStack = data.maxStackSize();      // Usually 1 for tools
```

### ItemTag

Tags categorize items into groups for easier querying (e.g., "is_sword", "is_food", "diamond_tier").

```java linenums="1"
// Check if an item type has a specific tag
boolean isSword = ItemTypes.DIAMOND_SWORD.hasItemTag(ItemTags.IS_SWORD);
boolean isFood = ItemTypes.APPLE.hasItemTag(ItemTags.IS_FOOD);
boolean isDiamondTier = ItemTypes.DIAMOND_PICKAXE.hasItemTag(ItemTags.DIAMOND_TIER);
```

### Summary Table

| Concept   | Description                                                    |
|-----------|----------------------------------------------------------------|
| ItemStack | An actual item instance with count, damage, enchantments, etc. |
| ItemType  | The category/kind of item (e.g., Diamond, Iron Sword)          |
| ItemTypes | Static references for common vanilla ItemType objects          |
| ItemData  | Metadata about an item (durability, damage, stack size)        |
| ItemTag   | Categorization tags for item types                             |

## Getting an ItemType

### From ItemTypes Class

Use fields from `ItemTypes` for quick access to vanilla items:

```java linenums="1"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.type.ItemType;

ItemType<?> diamond = ItemTypes.DIAMOND;
ItemType<?> sword = ItemTypes.DIAMOND_SWORD;
ItemType<?> apple = ItemTypes.APPLE;
ItemType<?> bow = ItemTypes.BOW;
```

### From the Registry

Look up by Identifier when you have dynamic keys or custom content:

```java linenums="1"
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;

// Look up by identifier
Identifier id = new Identifier("minecraft:diamond");
ItemType<?> diamond = Registries.ITEM_TYPE.get(id); // may be null if not found

// Or use string directly
ItemType<?> sword = Registries.ITEM_TYPE.get(new Identifier("minecraft:diamond_sword"));
```

## Creating Item Stacks

### Basic Creation

```java linenums="1"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.ItemStack;

// Create a single item
ItemStack oneApple = ItemTypes.APPLE.createItemStack();

// Create a stack with specific count
ItemStack diamonds = ItemTypes.DIAMOND.createItemStack(64);

// Create with count and meta (damage value)
ItemStack coloredWool = ItemTypes.WOOL.createItemStack(32, 14); // Red wool
```

### Using ItemStackInitInfo

For more control over item creation:

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.type.ItemTypes;

ItemStack customStack = ItemTypes.DIAMOND_SWORD.createItemStack(
        ItemStackInitInfo.builder()
                .count(1)
                .meta(0)
                .build()
);
```

## Working with Item Stacks

### Basic Properties

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

ItemStack stack = ItemTypes.DIAMOND.createItemStack(32);

// Get count
int count = stack.getCount();

// Set count
stack.setCount(64);

// Reduce count
stack.reduceCount(10); // Now 54

// Increase count
stack.increaseCount(5); // Now 59

// Check if stack is full
boolean isFull = stack.isFull();

// Check if empty or air
boolean isEmpty = stack.isEmptyOrAir();
```

### Custom Name and Lore

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import java.util.List;

ItemStack sword = ItemTypes.DIAMOND_SWORD.createItemStack();

// Set custom name
sword.setCustomName("§6Excalibur");

// Get custom name
String name = sword.getCustomName();

// Set lore (description lines)
sword.setLore(List.of(
        "§7A legendary sword",
        "§7forged in ancient times",
        "§c+10 Attack Damage"
));

// Get lore
List<String> lore = sword.getLore();
```

### Durability and Damage

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

ItemStack pickaxe = ItemTypes.DIAMOND_PICKAXE.createItemStack();

// Get max durability
int maxDurability = pickaxe.getMaxDamage();

// Get current damage (higher = more damaged)
int currentDamage = pickaxe.getDamage();

// Set damage
pickaxe.setDamage(100);

// Check if broken
boolean isBroken = pickaxe.isBroken();

// Try to increase damage (respects Unbreaking enchantment)
boolean damaged = pickaxe.tryIncreaseDamage(1);
```

### Copying Items

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

ItemStack original = ItemTypes.DIAMOND_SWORD.createItemStack();
original.setCustomName("Original");

// Copy the item (with new network ID)
ItemStack copy = original.copy();

// Copy without new network ID
ItemStack exactCopy = original.copy(false);
```

### Checking if Items Can Merge

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

ItemStack stack1 = ItemTypes.DIAMOND.createItemStack(32);
ItemStack stack2 = ItemTypes.DIAMOND.createItemStack(32);
ItemStack stack3 = ItemTypes.GOLD_INGOT.createItemStack(32);

// Check if can merge (considers count limits)
boolean canMerge = stack1.canMerge(stack2); // true

// Check ignoring count limits
boolean canMergeIgnoreCount = stack1.canMerge(stack2, true);

// Different types cannot merge
boolean cannotMerge = stack1.canMerge(stack3); // false
```

## Working with Enchantments

### Adding Enchantments

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.enchantment.EnchantmentTypes;

ItemStack sword = ItemTypes.DIAMOND_SWORD.createItemStack();

// Add a single enchantment
sword.addEnchantment(EnchantmentTypes.SHARPNESS, 5);
sword.addEnchantment(EnchantmentTypes.UNBREAKING, 3);
sword.addEnchantment(EnchantmentTypes.FIRE_ASPECT, 2);
```

### Checking Enchantments

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.enchantment.EnchantmentInstance;

// Check if item has any enchantments
boolean hasEnchants = sword.hasEnchantments();

// Check for specific enchantment
boolean hasSharpness = sword.hasEnchantment(EnchantmentTypes.SHARPNESS);

// Get enchantment level (0 if not present)
int sharpnessLevel = sword.getEnchantmentLevel(EnchantmentTypes.SHARPNESS);

// Get all enchantments
Collection<EnchantmentInstance> enchants = sword.getEnchantments();
for (EnchantmentInstance enchant : enchants) {
    System.out.println(enchant.getType().getIdentifier() + " Level " + enchant.getLevel());
}
```

### Removing Enchantments

```java linenums="1"
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.enchantment.EnchantmentInstance;

// Remove specific enchantment
EnchantmentInstance removed = sword.removeEnchantment(EnchantmentTypes.FIRE_ASPECT);

// Remove all enchantments
sword.removeAllEnchantments();
```

### Enchantment Compatibility

```java linenums="1"
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.enchantment.EnchantmentType;
import java.util.Set;

// Check if an enchantment is compatible with existing enchantments
boolean compatible = sword.checkEnchantmentCompatibility(EnchantmentTypes.SMITE);

// Get incompatible enchantments
Set<EnchantmentType> incompatible = sword.getIncompatibleEnchantmentTypes(EnchantmentTypes.SMITE);
// Smite is incompatible with Sharpness and Bane of Arthropods
```

### Available Enchantment Types

| Enchantment             | Description                  |
|-------------------------|------------------------------|
| `SHARPNESS`             | Increases melee damage       |
| `SMITE`                 | Extra damage to undead       |
| `BANE_OF_ARTHROPODS`    | Extra damage to arthropods   |
| `KNOCKBACK`             | Increases knockback          |
| `FIRE_ASPECT`           | Sets target on fire          |
| `LOOTING`               | Increases mob drops          |
| `EFFICIENCY`            | Faster mining                |
| `SILK_TOUCH`            | Blocks drop themselves       |
| `FORTUNE`               | Increases certain drops      |
| `UNBREAKING`            | Increases durability         |
| `MENDING`               | Repairs with XP              |
| `PROTECTION`            | Reduces damage               |
| `FIRE_PROTECTION`       | Reduces fire damage          |
| `BLAST_PROTECTION`      | Reduces explosion damage     |
| `PROJECTILE_PROTECTION` | Reduces projectile damage    |
| `FEATHER_FALLING`       | Reduces fall damage          |
| `RESPIRATION`           | Extends underwater breathing |
| `AQUA_AFFINITY`         | Faster underwater mining     |
| `DEPTH_STRIDER`         | Faster underwater movement   |
| `FROST_WALKER`          | Freezes water                |
| ... and more            |                              |

## Working with Item Tags

Item tags help categorize items for gameplay mechanics:

```java linenums="1"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.data.ItemTags;

// Check item categories
boolean isTool = ItemTypes.DIAMOND_PICKAXE.hasItemTag(ItemTags.IS_TOOL);
boolean isPickaxe = ItemTypes.DIAMOND_PICKAXE.hasItemTag(ItemTags.IS_PICKAXE);
boolean isSword = ItemTypes.DIAMOND_SWORD.hasItemTag(ItemTags.IS_SWORD);
boolean isFood = ItemTypes.APPLE.hasItemTag(ItemTags.IS_FOOD);
boolean isMeat = ItemTypes.COOKED_BEEF.hasItemTag(ItemTags.IS_MEAT);

// Check tool tiers
boolean isDiamondTier = ItemTypes.DIAMOND_PICKAXE.hasItemTag(ItemTags.DIAMOND_TIER);
boolean isIronTier = ItemTypes.IRON_SWORD.hasItemTag(ItemTags.IRON_TIER);
boolean isGoldenTier = ItemTypes.GOLDEN_AXE.hasItemTag(ItemTags.GOLDEN_TIER);
```

### Common Item Tags

| Tag                | Description               |
|--------------------|---------------------------|
| `IS_TOOL`          | Item is a tool            |
| `IS_SWORD`         | Item is a sword           |
| `IS_PICKAXE`       | Item is a pickaxe         |
| `IS_AXE`           | Item is an axe            |
| `IS_SHOVEL`        | Item is a shovel          |
| `IS_HOE`           | Item is a hoe             |
| `IS_FOOD`          | Item is edible            |
| `IS_MEAT`          | Item is meat              |
| `IS_FISH`          | Item is fish              |
| `DIAMOND_TIER`     | Diamond tier tool/armor   |
| `IRON_TIER`        | Iron tier tool/armor      |
| `GOLDEN_TIER`      | Gold tier tool/armor      |
| `STONE_TIER`       | Stone tier tool           |
| `WOODEN_TIER`      | Wood tier tool            |
| `NETHERITE_TIER`   | Netherite tier tool/armor |
| `TRIMMABLE_ARMORS` | Armor that can be trimmed |
| `SPAWN_EGG`        | Spawn egg item            |

## Converting Between Items and Blocks

Some items can be placed as blocks:

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.state.BlockState;

// Get the block type associated with an item
BlockType<?> blockType = ItemTypes.STONE.getBlockType();

// Convert an item stack to a block state
ItemStack stoneItem = ItemTypes.STONE.createItemStack();
BlockState stoneBlock = stoneItem.toBlockState();
```

## Practical Examples

### Give Items to a Player

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

public void giveEnchantedSword(EntityPlayer player) {
    // Create a diamond sword
    ItemStack sword = ItemTypes.DIAMOND_SWORD.createItemStack();

    // Customize it
    sword.setCustomName("§6Dragon Slayer");
    sword.setLore(List.of("§7A blade forged to slay dragons"));
    sword.addEnchantment(EnchantmentTypes.SHARPNESS, 5);
    sword.addEnchantment(EnchantmentTypes.UNBREAKING, 3);
    sword.addEnchantment(EnchantmentTypes.FIRE_ASPECT, 2);

    // Give to player (if inventory is full, item will be dropped)
    player.tryAddItem(sword);
}
```

### Check Player's Held Item

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.entity.interfaces.EntityPlayer;

public boolean isHoldingSword(EntityPlayer player) {
    ItemStack heldItem = player.getItemInHand();

    if (heldItem.isEmptyOrAir()) {
        return false;
    }

    return heldItem.getItemType().hasItemTag(ItemTags.IS_SWORD);
}
```

### Create a Reward Kit

```java linenums="1"
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import java.util.List;

public void giveStarterKit(EntityPlayer player) {
    List<ItemStack> kit = List.of(
            createItem(ItemTypes.IRON_SWORD, 1),
            createItem(ItemTypes.IRON_PICKAXE, 1),
            createItem(ItemTypes.IRON_AXE, 1),
            createItem(ItemTypes.BREAD, 32),
            createItem(ItemTypes.TORCH, 64),
            createItem(ItemTypes.OAK_PLANKS, 64)
    );

    for (ItemStack item : kit) {
        player.tryAddItem(item);
    }
}

private ItemStack createItem(ItemType<?> type, int count) {
    return type.createItemStack(count);
}
```

## Tips and Best Practices

!!! tip "Check for Air/Empty"
    Always check if an item stack is empty before processing:
    ```java linenums="1"
    if (!itemStack.isEmptyOrAir()) {
        // Safe to process
    }
    ```

!!! tip "Use ItemTypes for Vanilla Items"
    Use `ItemTypes.DIAMOND` instead of registry lookup for better performance and type safety.

!!! tip "ItemStack is Mutable"
    Unlike BlockState, ItemStack is mutable. Modifications affect the original instance:
    ```java linenums="1"
    ItemStack stack = ItemTypes.DIAMOND.createItemStack(32);
    stack.setCount(64); // Modifies the original stack
    ```

!!! tip "Copy Before Modifying Shared Items"
    If you need to modify an item that might be shared, copy it first:
    ```java linenums="1"
    ItemStack copy = originalStack.copy();
    copy.setCustomName("Modified");
    ```

!!! warning "Enchantment Compatibility"
    Some enchantments are mutually exclusive (e.g., Sharpness/Smite/Bane of Arthropods).
    Check compatibility before adding:
    ```java linenums="1"
    if (sword.checkEnchantmentCompatibility(EnchantmentTypes.SMITE)) {
        sword.addEnchantment(EnchantmentTypes.SMITE, 5);
    }
    ```

!!! warning "Stack Size Limits"
    Different items have different max stack sizes. Tools stack to 1, most items to 64:
    ```java linenums="1"
    int maxStack = itemType.getItemData().maxStackSize();
    ```
