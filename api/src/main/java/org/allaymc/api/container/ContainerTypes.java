package org.allaymc.api.container;

import org.allaymc.api.container.interfaces.*;

/**
 * ContainerTypes contains all the available container types in the game.
 *
 * @author daoge_cmd
 */
public interface ContainerTypes {
    /**
     * An anvil UI container.
     * Used when a player opens an anvil block to repair, combine, or rename items.
     */
    ContainerType<AnvilContainer> ANVIL = new ContainerType<>(3);
    /**
     * An equipment container that stores worn armor.
     * Used by players and armor-capable entities such as zombies or armor stands, and queried during combat and equipment synchronization.
     */
    ContainerType<ArmorContainer> ARMOR = new ContainerType<>(4);
    /**
     * A main-hand equipment container for non-player entities.
     * Used for entity-held item synchronization, currently in places such as armor-stand-style entity hand storage.
     */
    ContainerType<EntityHandContainer> ENTITY_HAND = new ContainerType<>(1);
    /**
     * A barrel block storage container.
     * Used when interacting with barrel blocks in the world.
     */
    ContainerType<BlockContainer> BARREL = new ContainerType<>(27);
    /**
     * A beacon payment slot container.
     * Used by the beacon UI for inserting the payment item required to apply effects.
     */
    ContainerType<BeaconContainer> BEACON = new ContainerType<>(1);
    /**
     * A blast furnace processing container.
     * Used by blast furnace blocks for input, fuel, and output slots.
     */
    ContainerType<FurnaceContainer> BLAST_FURNACE = new ContainerType<>(3);
    /**
     * A brewing stand processing container.
     * Used by brewing stand blocks for bottle slots, ingredient slot, and fuel slot.
     */
    ContainerType<BrewingStandContainer> BREWING_STAND = new ContainerType<>(5);
    /**
     * A cartography table UI container.
     * Used when a player edits or clones maps through a cartography table block.
     */
    ContainerType<CartographyTableContainer> CARTOGRAPHY_TABLE = new ContainerType<>(3);
    /**
     * A single chest block storage container.
     * Used by normal chest block entities and as the base size for double chest composition.
     */
    ContainerType<BlockContainer> CHEST = new ContainerType<>(27);
    /**
     * A hopper block storage container.
     * Used by hopper block entities for item transfer between inventories.
     */
    ContainerType<BlockContainer> HOPPER = new ContainerType<>(5);
    /**
     * A dispenser block storage container.
     * Used by dispenser block entities for storing and dispensing items.
     */
    ContainerType<BlockContainer> DISPENSER = new ContainerType<>(9);
    /**
     * A dropper block storage container.
     * Used by dropper block entities for storing and dropping items without dispenser behavior.
     */
    ContainerType<BlockContainer> DROPPER = new ContainerType<>(9);
    /**
     * A chiseled bookshelf storage container.
     * Used by chiseled bookshelf blocks for their six book slots and sided access behavior.
     */
    ContainerType<Container> CHISELED_BOOKSHELF = new ContainerType<>(6);
    /**
     * The player's built-in 2x2 crafting input container.
     * Used by the inventory crafting grid that is available without opening a crafting table.
     */
    ContainerType<RecipeContainer> CRAFTING_GRID = new ContainerType<>(4);
    /**
     * A 3x3 crafting table input container.
     * Used when a player opens a crafting table block and recipe actions target the larger crafting grid.
     */
    ContainerType<CraftingTableContainer> CRAFTING_TABLE = new ContainerType<>(9);
    /**
     * The result slot for player-side crafting output.
     * Used together with the personal crafting grid to expose the synthesized item before it is taken.
     */
    ContainerType<Container> CREATED_OUTPUT = new ContainerType<>(1);
    /**
     * The temporary cursor-held item container.
     * Used by inventory transaction handling to represent the stack currently carried by the player's cursor.
     */
    ContainerType<Container> CURSOR = new ContainerType<>(1);
    /**
     * A virtual storage container representing two linked chest halves.
     * Used when adjacent chests are combined into a single double chest view.
     */
    ContainerType<BlockContainer> DOUBLE_CHEST = new ContainerType<>(54);
    /**
     * An enchanting table UI container.
     * Used by the enchanting table interface for the item and lapis slots involved in enchant selection.
     */
    ContainerType<EnchantTableContainer> ENCHANT_TABLE = new ContainerType<>(2);
    /**
     * A player's ender chest storage container.
     * Used by the ender chest UI while remaining tied to player-owned persistent storage rather than a block inventory.
     */
    ContainerType<BlockContainer> ENDER_CHEST = new ContainerType<>(27);
    /**
     * A furnace processing container.
     * Used by furnace blocks for smelting input, fuel, and output slots.
     */
    ContainerType<FurnaceContainer> FURNACE = new ContainerType<>(3);
    /**
     * A grindstone UI container.
     * Used when disenchanting or repairing items through a grindstone block.
     */
    ContainerType<GrindstoneContainer> GRINDSTONE = new ContainerType<>(3);
    /**
     * A loom UI container.
     * Used when applying banner patterns through a loom block.
     */
    ContainerType<LoomContainer> LOOM = new ContainerType<>(4);
    /**
     * An offhand equipment container.
     * Used by players and other human-like entities for offhand item storage, combat logic, and visual synchronization.
     */
    ContainerType<OffhandContainer> OFFHAND = new ContainerType<>(1);
    /**
     * The main player inventory container.
     * Used for the standard 36-slot player inventory, including the selected hotbar slot and most item interactions.
     */
    ContainerType<InventoryContainer> INVENTORY = new ContainerType<>(36);
    /**
     * A shulker box block storage container.
     * Used by shulker box block entities and their UI.
     */
    ContainerType<BlockContainer> SHULKER_BOX = new ContainerType<>(27);
    /**
     * A smithing table UI container.
     * Used by smithing recipes such as equipment upgrading and template-based transformations.
     */
    ContainerType<SmithingTableContainer> SMITHING_TABLE = new ContainerType<>(4);
    /**
     * A smoker processing container.
     * Used by smoker blocks for food-focused furnace processing.
     */
    ContainerType<FurnaceContainer> SMOKER = new ContainerType<>(3);
    /**
     * A small shelf-style storage container.
     * Used by shelf blocks that expose a fixed set of display or storage slots.
     */
    ContainerType<Container> SHELF = new ContainerType<>(3);
    /**
     * A stonecutter UI container.
     * Used when cutting stone-related blocks through the stonecutter interface and its recipe actions.
     */
    ContainerType<StonecutterContainer> STONECUTTER = new ContainerType<>(2);

    // Fake Container Types

    /**
     * A UI-only chest container type.
     * Used by synthetic chest windows created through the fake container system rather than a real world block entity.
     */
    ContainerType<FakeContainer> FAKE_CHEST = new ContainerType<>(27);
    /**
     * A UI-only double chest container type.
     * Used by synthetic large chest windows created through the fake container system.
     */
    ContainerType<FakeContainer> FAKE_DOUBLE_CHEST = new ContainerType<>(54);
}
