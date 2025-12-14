---
comments: true
---

# Container API

Containers are objects that hold items in Allay. This includes player inventories, chests, furnaces,
and many other blocks that can store items. Allay also provides a powerful "fake container" system
for creating custom GUIs.

## Key Concepts

### Container

`Container` is the base interface for all containers. It provides methods for managing items and viewers.

**Key Methods:**

| Method                          | Description                                      |
|---------------------------------|--------------------------------------------------|
| `getContainerType()`            | Get the container type                           |
| `getItemStack(int slot)`        | Get the item at a specific slot                  |
| `setItemStack(int slot, item)`  | Set an item at a specific slot                   |
| `clearSlot(int slot)`           | Clear a specific slot                            |
| `clearAllSlots()`               | Clear all slots                                  |
| `isEmpty()`                     | Check if the container is empty                  |
| `tryAddItem(ItemStack)`         | Try to add an item, merging with existing stacks |
| `addViewer(ContainerViewer)`    | Add a viewer to the container                    |
| `removeViewer(ContainerViewer)` | Remove a viewer from the container               |
| `getViewers()`                  | Get all current viewers                          |

### ContainerType

`ContainerType` represents the type of a container and defines its size. Container types are used
to identify and retrieve containers from a `ContainerHolder`.

### ContainerTypes

`ContainerTypes` contains all available container types:

| Type                | Size | Description                    |
|---------------------|------|--------------------------------|
| `INVENTORY`         | 36   | Player inventory               |
| `ARMOR`             | 4    | Player armor slots             |
| `OFFHAND`           | 1    | Player offhand slot            |
| `CHEST`             | 27   | Single chest                   |
| `DOUBLE_CHEST`      | 54   | Double chest                   |
| `BARREL`            | 27   | Barrel                         |
| `SHULKER_BOX`       | 27   | Shulker box                    |
| `ENDER_CHEST`       | 27   | Ender chest                    |
| `FURNACE`           | 3    | Furnace                        |
| `BLAST_FURNACE`     | 3    | Blast furnace                  |
| `SMOKER`            | 3    | Smoker                         |
| `BREWING_STAND`     | 5    | Brewing stand                  |
| `ANVIL`             | 3    | Anvil                          |
| `ENCHANT_TABLE`     | 2    | Enchanting table               |
| `CRAFTING_TABLE`    | 9    | Crafting table                 |
| `CRAFTING_GRID`     | 4    | Player 2x2 crafting grid       |
| `STONECUTTER`       | 2    | Stonecutter                    |
| `GRINDSTONE`        | 3    | Grindstone                     |
| `LOOM`              | 4    | Loom                           |
| `SMITHING_TABLE`    | 4    | Smithing table                 |
| `CARTOGRAPHY_TABLE` | 3    | Cartography table              |
| `BEACON`            | 1    | Beacon                         |
| `FAKE_CHEST`        | 27   | Fake chest (virtual container) |
| `FAKE_DOUBLE_CHEST` | 54   | Fake double chest (virtual)    |
| ... and more        |      |                                |

### ContainerHolder

`ContainerHolder` is an interface for objects that hold multiple containers. `EntityPlayer` implements
this interface to hold inventory, armor, and offhand containers.

```java linenums="1"
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;

public void accessPlayerContainers(EntityPlayer player) {
    // Get player's inventory container
    var inventory = player.getContainer(ContainerTypes.INVENTORY);

    // Get player's armor container
    var armor = player.getContainer(ContainerTypes.ARMOR);

    // Get player's offhand container
    var offhand = player.getContainer(ContainerTypes.OFFHAND);
}
```

### ContainerViewer

`ContainerViewer` is an interface for entities that can view and interact with containers.
`Player` implements this interface.

## Working with Player Inventory

### Getting Items

```java linenums="1"
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

public void getPlayerItems(EntityPlayer player) {
    // Get the item in the player's hand
    ItemStack itemInHand = player.getItemInHand();

    // Get the current hand slot index (0-8)
    int handSlot = player.getHandSlot();

    // Get an item from a specific inventory slot
    var inventory = player.getContainer(ContainerTypes.INVENTORY);
    ItemStack item = inventory.getItemStack(0);

    // Get all items in the inventory
    var allItems = inventory.getItemStacks();
}
```

### Setting Items

```java linenums="1"
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.type.ItemTypes;

public void setPlayerItems(EntityPlayer player) {
    // Set item in hand
    player.setItemInHand(ItemTypes.DIAMOND_SWORD.createItemStack());

    // Clear item in hand
    player.clearItemInHand();

    // Set item at a specific slot
    var inventory = player.getContainer(ContainerTypes.INVENTORY);
    inventory.setItemStack(0, ItemTypes.DIAMOND.createItemStack(64));

    // Clear a specific slot
    inventory.clearSlot(0);

    // Clear the entire inventory
    inventory.clearAllSlots();
}
```

### Adding Items

```java linenums="1"
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.type.ItemTypes;

public void addItemsToPlayer(EntityPlayer player) {
    var diamond = ItemTypes.DIAMOND.createItemStack(32);

    // Try to add item - will merge with existing stacks or find empty slot
    // If inventory is full, the item will be dropped at the player's position
    player.tryAddItem(diamond);
}
```

## Working with Block Containers

Block containers (chests, furnaces, etc.) can be accessed when players interact with them:

```java linenums="1"
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.type.ItemTypes;

public void workWithBlockContainer(EntityPlayer player) {
    // Get the container the player has opened (e.g., a chest)
    var controller = player.getController();
    if (controller != null) {
        var openedChest = controller.getOpenedContainer(ContainerTypes.CHEST);
        if (openedChest != null) {
            // Add an item to the chest
            openedChest.tryAddItem(ItemTypes.GOLD_INGOT.createItemStack(16));

            // Get an item from the chest
            var item = openedChest.getItemStack(0);
        }
    }
}
```

## Container Listeners

Containers support various listeners for monitoring changes:

```java linenums="1"
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;

public void setupContainerListeners(Container container) {
    // Listen for container open events
    container.addOpenListener((ContainerViewer viewer) -> {
        System.out.println("Container opened by a viewer");
    });

    // Listen for container close events
    container.addCloseListener((ContainerViewer viewer) -> {
        System.out.println("Container closed by a viewer");
    });

    // Listen for changes to a specific slot
    container.addSlotChangeListener(0, (itemStack) -> {
        System.out.println("Slot 0 changed to: " + itemStack.getItemType().getIdentifier());
    });
}
```

## Fake Containers (Virtual GUIs)

Fake containers are virtual containers that don't correspond to any real block in the world.
They are useful for creating custom GUIs like menus, shops, or interactive interfaces.

### Creating Fake Containers

Use `FakeContainerFactory` to create fake containers:

```java linenums="1"
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.container.interfaces.FakeContainer;

// Create a fake chest (27 slots)
FakeContainer fakeChest = FakeContainerFactory.getFactory().createFakeChestContainer();

// Create a fake double chest (54 slots)
FakeContainer fakeDoubleChest = FakeContainerFactory.getFactory().createFakeDoubleChestContainer();
```

### Configuring Fake Containers

```java linenums="1"
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.item.type.ItemTypes;

public void configureFakeContainer() {
    var fakeChest = FakeContainerFactory.getFactory().createFakeChestContainer();

    // Set a custom name (displayed in the container header)
    fakeChest.setCustomName("My Custom Menu");

    // Add items to the container
    fakeChest.setItemStack(0, ItemTypes.DIAMOND.createItemStack());
    fakeChest.setItemStack(4, ItemTypes.EMERALD.createItemStack());
    fakeChest.setItemStack(8, ItemTypes.GOLD_INGOT.createItemStack());
}
```

### Adding Click Listeners

Fake containers support click listeners that are triggered when players interact with items:

```java linenums="1"
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.Player;

public void createInteractiveMenu(Player player) {
    var menu = FakeContainerFactory.getFactory().createFakeChestContainer();
    menu.setCustomName("Shop Menu");

    // Add item with click listener
    menu.setItemStackWithListener(0, ItemTypes.DIAMOND_SWORD.createItemStack(), () -> {
        player.sendText("You clicked on the Diamond Sword!");
        // Add your shop logic here
    });

    // Or add listener separately
    menu.setItemStack(4, ItemTypes.GOLDEN_APPLE.createItemStack());
    menu.addClickListener(4, () -> {
        player.sendText("You clicked on the Golden Apple!");
    });

    // Show the menu to the player
    menu.addPlayer(player);
}
```

### Showing Fake Containers to Players

!!! warning "Important"
    Use `addPlayer()` instead of `addViewer()` for fake containers. The `addPlayer()` method
    handles sending fake blocks to the client before opening the container.

```java linenums="1"
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.player.Player;

public void showFakeContainer(Player player) {
    var fakeChest = FakeContainerFactory.getFactory().createFakeChestContainer();
    fakeChest.setCustomName("My Menu");

    // Show to player (with optional callback)
    fakeChest.addPlayer(player, success -> {
        if (success) {
            System.out.println("Container opened successfully");
        } else {
            System.out.println("Failed to open container");
        }
    });

    // Or without callback
    fakeChest.addPlayer(player);
}
```

### Closing Fake Containers

```java linenums="1"
import org.allaymc.api.container.interfaces.FakeContainer;
import org.allaymc.api.player.Player;

public void closeFakeContainer(FakeContainer container, Player player) {
    // Remove a specific player
    container.removeViewer(player);

    // Remove all viewers
    container.removeAllViewers();
}
```

## Practical Examples

### Simple Shop Menu

```java linenums="1"
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.container.interfaces.FakeContainer;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.Player;

public class ShopMenu {
    private final FakeContainer menu;
    private final Player player;

    public ShopMenu(Player player) {
        this.player = player;
        this.menu = FakeContainerFactory.getFactory().createFakeChestContainer();
        setupMenu();
    }

    private void setupMenu() {
        menu.setCustomName("Server Shop");

        // Diamond Sword - 10 emeralds
        menu.setItemStackWithListener(10, ItemTypes.DIAMOND_SWORD.createItemStack(), () -> {
            buyItem("Diamond Sword", 10);
        });

        // Golden Apple - 5 emeralds
        menu.setItemStackWithListener(12, ItemTypes.GOLDEN_APPLE.createItemStack(), () -> {
            buyItem("Golden Apple", 5);
        });

        // Diamond Armor Set - 40 emeralds
        menu.setItemStackWithListener(14, ItemTypes.DIAMOND_CHESTPLATE.createItemStack(), () -> {
            buyItem("Diamond Armor Set", 40);
        });

        // Close button
        menu.setItemStackWithListener(26, ItemTypes.BARRIER.createItemStack(), () -> {
            close();
        });
    }

    private void buyItem(String itemName, int price) {
        player.sendText("You purchased " + itemName + " for " + price + " emeralds!");
        // Add your purchase logic here
    }

    public void open() {
        menu.addPlayer(player);
    }

    public void close() {
        menu.removeViewer(player);
    }
}
```

### Paginated Menu

```java linenums="1"
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.container.interfaces.FakeContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.Player;

import java.util.List;

public class PaginatedMenu {
    private final Player player;
    private final List<ItemStack> items;
    private final int itemsPerPage = 45; // Leave bottom row for navigation
    private int currentPage = 0;
    private FakeContainer menu;

    public PaginatedMenu(Player player, List<ItemStack> items) {
        this.player = player;
        this.items = items;
    }

    public void open() {
        showPage(0);
    }

    private void showPage(int page) {
        this.currentPage = page;

        // Close previous menu if open
        if (menu != null) {
            menu.removeViewer(player);
        }

        menu = FakeContainerFactory.getFactory().createFakeDoubleChestContainer();
        menu.setCustomName("Items - Page " + (page + 1));

        // Calculate items for this page
        int startIndex = page * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, items.size());

        // Add items
        for (int i = startIndex; i < endIndex; i++) {
            int slot = i - startIndex;
            ItemStack item = items.get(i);
            menu.setItemStackWithListener(slot, item, () -> {
                player.sendText("You selected: " + item.getItemType().getIdentifier());
            });
        }

        // Previous page button (slot 45)
        if (page > 0) {
            menu.setItemStackWithListener(45, ItemTypes.ARROW.createItemStack(), () -> {
                showPage(currentPage - 1);
            });
        }

        // Next page button (slot 53)
        if (endIndex < items.size()) {
            menu.setItemStackWithListener(53, ItemTypes.ARROW.createItemStack(), () -> {
                showPage(currentPage + 1);
            });
        }

        // Close button (slot 49)
        menu.setItemStackWithListener(49, ItemTypes.BARRIER.createItemStack(), () -> {
            menu.removeViewer(player);
        });

        menu.addPlayer(player);
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) items.size() / itemsPerPage);
    }
}
```

### Confirmation Dialog

```java linenums="1"
import org.allaymc.api.container.FakeContainerFactory;
import org.allaymc.api.container.interfaces.FakeContainer;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.Player;

public class ConfirmationDialog {

    public static void show(Player player, String title, Runnable onConfirm, Runnable onCancel) {
        var dialog = FakeContainerFactory.getFactory().createFakeChestContainer();
        dialog.setCustomName(title);

        // Confirm button (green wool)
        dialog.setItemStackWithListener(11, ItemTypes.GREEN_WOOL.createItemStack(), () -> {
            dialog.removeViewer(player);
            onConfirm.run();
        });

        // Cancel button (red wool)
        dialog.setItemStackWithListener(15, ItemTypes.RED_WOOL.createItemStack(), () -> {
            dialog.removeViewer(player);
            onCancel.run();
        });

        dialog.addPlayer(player);
    }
}

// Usage:
// ConfirmationDialog.show(player, "Delete all items?",
//     () -> player.sendText("Confirmed!"),
//     () -> player.sendText("Cancelled!")
// );
```

## Tips

!!! tip "Fake Container Lifecycle"
    Remember to remove viewers from fake containers when they are no longer needed. Fake
    containers send fake blocks to the client, which are cleaned up when the viewer is removed.

!!! tip "Click Listener Cleanup"
    If you're reusing fake containers, call `removeAllClickListeners()` before setting up new
    listeners to avoid duplicate callbacks.

!!! tip "Player.getController()"
    When working with fake containers, use `player.getController()` to get the `Player` interface
    from an `EntityPlayer`. The controller handles the network communication for containers.

!!! tip "Container Updates"
    When you modify items in a container using `setItemStack()`, viewers are automatically
    notified. If you modify an `ItemStack` directly (e.g., changing count), call
    `notifySlotChange(slot)` to update viewers.
