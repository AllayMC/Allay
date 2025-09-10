---
comments: true
---

# Work with Items and Containers

This tutorial introduces the core concepts of Allay's Items and Containers API and shows practical ways to work with item types, item stacks, and containers.

## Core Concepts

- ItemType
  - The definition of a kind of item (e.g., stone, diamond_sword, apple). It defines behavior, properties, and all valid states of that item type.
  - Key methods include: creating ItemStack instances, getting runtime ID, and accessing item tags.

- ItemTypes
  - A generated convenience class that exposes constants for every known ItemType (e.g., ItemTypes.STONE, ItemTypes.DIAMOND_SWORD). It lets you access item types without performing a registry lookup and benefits from IDE auto-completion.

- ItemStack
  - A concrete instance of an ItemType with specific properties like count, meta, and components.
  - Represents an actual item that can be placed in containers, dropped in the world, or used by entities.

- Container
  - Represents a container that can hold ItemStack instances.
  - Provides methods to get, set, and clear items in specific slots.
  - Handles viewer management and synchronization.

- ContainerHolder
  - Represents an object that can hold multiple containers (e.g., players, chests, furnaces).
  - Provides methods to access containers by type or slot type.

- ContainerViewer
  - Represents an entity that can view and interact with containers.
  - Handles container opening/closing and slot change notifications.

- ItemAirStack.AIR_STACK
  - A special constant representing an empty/air item stack.
  - Used to clear slots in containers and represents empty slots.
  - The ItemAirStack class has only this single instance (singleton pattern).

## Getting an ItemType

There are two common ways to obtain an ItemType.

### 1. From the global registry

```java linenums="1"
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.item.type.ItemType;

ItemType<?> diamond = Registries.ITEMS.get(new Identifier("minecraft:diamond"));
if (diamond == null) {
    // Unknown identifier
}
```

### 2. From the generated ItemTypes class

```java linenums="1"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.type.ItemType;

ItemType<?> diamond = ItemTypes.DIAMOND; // Convenience access
```

## Creating an ItemStack

### 1. From an ItemType

- Default stack (count=1, meta=0):

```java linenums="1"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.ItemStack;

ItemStack diamondStack = ItemTypes.DIAMOND.createItemStack();
```

- With specific count:

```java linenums="1"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.ItemStack;

ItemStack diamondStack = ItemTypes.DIAMOND.createItemStack(5); // 5 diamonds
```

- With specific count and meta:

```java linenums="1"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.ItemStack;

ItemStack diamondStack = ItemTypes.DIAMOND.createItemStack(3, 0); // 3 diamonds with meta 0
```

## Working with Containers

### Adding an ItemStack to a container

```java linenums="1"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.container.Container;

// Get a container (e.g., from a player or block entity)
Container container = /* obtain your container */;
// Create an item stack
ItemStack diamondStack = ItemTypes.DIAMOND.createItemStack(3);
// Set it to slot 0
container.setItemStack(0, diamondStack);
```

### Getting and modifying an existing ItemStack

```java linenums="1"
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;

Container container = /* obtain your container */;
// Get the item stack from a slot
ItemStack stack = container.getItemStack(0);
// Modify the stack (e.g., change count)
stack.setCount(1);
// After modification, notify viewers of the change
container.notifySlotChange(0);
```

### Clearing a slot

```java linenums="1"
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.container.Container;

Container container = /* obtain your container */;
// Clear slot 0 (set to air)
container.clearSlot(0);
// Alternative: set directly to AIR_STACK
container.setItemStack(0, ItemAirStack.AIR_STACK);
```

## Using ItemAirStack.AIR_STACK

The `ItemAirStack.AIR_STACK` constant is a special singleton instance used to represent empty slots in containers:

```java linenums="1"
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.container.Container;

Container container = /* obtain your container */;

// Check if a slot is empty
container.isEmpty(0); // Uses ItemAirStack.AIR_STACK comparison
// Alternative: check if the item stack is temAirStack.AIR_STACK
// ItemAirStack.AIR_STACK is a singleton - you can use == for comparison
container.getItemStack(0) == ItemAirStack.AIR_STACK
```

### Characteristics of ItemAirStack.AIR_STACK:

- **Singleton pattern**: Only one instance exists in the entire application
- **Immutable**: Cannot be modified (all modification attempts should be ignored)
- **Zero count**: Represents an item stack with count = 0
- **Special creation**: Created with `null` ItemStackInitInfo (only allowed for air type)

### ContainerHolder and ContainerViewer

## Working with ContainerHolder

```java linenums="1"
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.impl.PlayerContainer;

ContainerHolder holder = /* obtain a container holder (e.g., player) */;
// Get a specific container type
PlayerContainer container = holder.getContainer(FullContainerType.PLAYER);
// Checks if the holder has a container of the specified type
boolean hasContainer = holder.hasContainer(FullContainerType.PLAYER);
```

## Working with ContainerViewer

```java linenums="1"
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.impl.PlayerContainer;

Container container = /* obtain a container */;
ContainerViewer viewer = /* obtain a container viewer */;
// Add the viewer to the container
container.addViewer(viewer);
// Remove the viewer from the container
container.removeViewer(viewer);
```

## Best Practices

1. **Always use ItemAirStack.AIR_STACK for empty slots** - Don't use null or other empty values
2. **Notify viewers after modifications** - Use `notifySlotChange()` to synchronize changes
3. **Check for AIR_STACK before modifying** - Ensure you're not trying to modify air stacks
4. **Use ContainerHolder for container management** - Don't manually manage container relationships
5. **Prefer ItemTypes constants** - They provide compile-time safety and IDE support

!!! tip
    - ItemAirStack.AIR_STACK is a singleton; you can compare stacks with `==` reliably
    - Always call method Container.onSlotChange() after modified a item stack got from container to ensure the viewers can view the change.
    - Use the appropriate FullContainerType for different container types (PLAYER, CHEST, etc.)
    - ContainerViewer handles the network communication automatically when using notifySlotChange()