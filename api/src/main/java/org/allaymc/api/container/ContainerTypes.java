package org.allaymc.api.container;

import org.allaymc.api.container.interfaces.*;

/**
 * {@code ContainerTypes} defines the shared type identities and fixed slot counts for
 * built-in containers. Holders and network metadata use these exact instances as keys,
 * so a separately constructed type with the same size does not identify the same layout.
 *
 * @author daoge_cmd
 */
public interface ContainerTypes {
    /**
     * The built-in type constants supply shared identities for container allocation,
     * holder lookup, and network layout registration. Identity for the 3-slot anvil
     * container layout.
     */
    ContainerType<AnvilContainer> ANVIL = new ContainerType<>(3);
    /**
     * Identity for the 4-slot armor container layout.
     */
    ContainerType<ArmorContainer> ARMOR = new ContainerType<>(4);
    /**
     * Identity for the 1-slot entity hand container layout.
     */
    ContainerType<EntityHandContainer> ENTITY_HAND = new ContainerType<>(1);
    /**
     * Identity for the 27-slot barrel container layout.
     */
    ContainerType<BlockContainer> BARREL = new ContainerType<>(27);
    /**
     * Identity for the 1-slot beacon container layout.
     */
    ContainerType<BeaconContainer> BEACON = new ContainerType<>(1);
    /**
     * Identity for the 3-slot blast furnace container layout.
     */
    ContainerType<FurnaceContainer> BLAST_FURNACE = new ContainerType<>(3);
    /**
     * Identity for the 5-slot brewing stand container layout.
     */
    ContainerType<BrewingStandContainer> BREWING_STAND = new ContainerType<>(5);
    /**
     * Identity for the 3-slot cartography table container layout.
     */
    ContainerType<CartographyTableContainer> CARTOGRAPHY_TABLE = new ContainerType<>(3);
    /**
     * Identity for the 27-slot chest container layout.
     */
    ContainerType<BlockContainer> CHEST = new ContainerType<>(27);
    /**
     * Identity for the 5-slot hopper container layout.
     */
    ContainerType<BlockContainer> HOPPER = new ContainerType<>(5);
    /**
     * Identity for the 9-slot dispenser container layout.
     */
    ContainerType<BlockContainer> DISPENSER = new ContainerType<>(9);
    /**
     * Identity for the 9-slot dropper container layout.
     */
    ContainerType<BlockContainer> DROPPER = new ContainerType<>(9);
    /**
     * Identity for the 6-slot chiseled bookshelf container layout.
     */
    ContainerType<Container> CHISELED_BOOKSHELF = new ContainerType<>(6);
    /**
     * Identity for the 4-slot crafting grid container layout.
     */
    ContainerType<RecipeContainer> CRAFTING_GRID = new ContainerType<>(4);
    /**
     * Identity for the 9-slot crafting table container layout.
     */
    ContainerType<CraftingTableContainer> CRAFTING_TABLE = new ContainerType<>(9);
    /**
     * Identity for the 1-slot created output container layout.
     */
    ContainerType<Container> CREATED_OUTPUT = new ContainerType<>(1);
    /**
     * Identity for the 1-slot cursor container layout.
     */
    ContainerType<Container> CURSOR = new ContainerType<>(1);
    /**
     * Identity for the 54-slot double chest container layout.
     */
    ContainerType<BlockContainer> DOUBLE_CHEST = new ContainerType<>(54);
    /**
     * Identity for the 2-slot enchant table container layout.
     */
    ContainerType<EnchantTableContainer> ENCHANT_TABLE = new ContainerType<>(2);
    /**
     * Identity for the 27-slot ender chest container layout.
     */
    ContainerType<BlockContainer> ENDER_CHEST = new ContainerType<>(27);
    /**
     * Identity for the 3-slot furnace container layout.
     */
    ContainerType<FurnaceContainer> FURNACE = new ContainerType<>(3);
    /**
     * Identity for the 3-slot grindstone container layout.
     */
    ContainerType<GrindstoneContainer> GRINDSTONE = new ContainerType<>(3);
    /**
     * Identity for the 4-slot loom container layout.
     */
    ContainerType<LoomContainer> LOOM = new ContainerType<>(4);
    /**
     * Identity for the 1-slot offhand container layout.
     */
    ContainerType<OffhandContainer> OFFHAND = new ContainerType<>(1);
    /**
     * Identity for the 36-slot inventory container layout.
     */
    ContainerType<InventoryContainer> INVENTORY = new ContainerType<>(36);
    /**
     * Identity for the 27-slot shulker box container layout.
     */
    ContainerType<BlockContainer> SHULKER_BOX = new ContainerType<>(27);
    /**
     * Identity for the 4-slot smithing table container layout.
     */
    ContainerType<SmithingTableContainer> SMITHING_TABLE = new ContainerType<>(4);
    /**
     * Identity for the 3-slot smoker container layout.
     */
    ContainerType<FurnaceContainer> SMOKER = new ContainerType<>(3);
    /**
     * Identity for the 3-slot shelf container layout.
     */
    ContainerType<Container> SHELF = new ContainerType<>(3);
    /**
     * Identity for the 2-slot stonecutter container layout.
     */
    ContainerType<StonecutterContainer> STONECUTTER = new ContainerType<>(2);

    /**
     * The fake type constants identify synthetic chest layouts used by the
     * fake-container factory and network window registration. Identity for the 27-slot
     * fake chest container layout.
     */
    ContainerType<FakeContainer> FAKE_CHEST = new ContainerType<>(27);
    /**
     * Identity for the 54-slot fake double chest container layout.
     */
    ContainerType<FakeContainer> FAKE_DOUBLE_CHEST = new ContainerType<>(54);
}
