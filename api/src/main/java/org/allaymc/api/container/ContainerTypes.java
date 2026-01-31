package org.allaymc.api.container;

import org.allaymc.api.container.interfaces.*;

/**
 * ContainerTypes contains all the available container types in the game.
 *
 * @author daoge_cmd
 */
public interface ContainerTypes {
    ContainerType<AnvilContainer> ANVIL = new ContainerType<>(3);
    ContainerType<ArmorContainer> ARMOR = new ContainerType<>(4);
    ContainerType<ArmorStandHandContainer> ARMOR_STAND_HAND = new ContainerType<>(1);
    ContainerType<BlockContainer> BARREL = new ContainerType<>(27);
    ContainerType<BeaconContainer> BEACON = new ContainerType<>(1);
    ContainerType<FurnaceContainer> BLAST_FURNACE = new ContainerType<>(3);
    ContainerType<BrewingStandContainer> BREWING_STAND = new ContainerType<>(5);
    ContainerType<CartographyTableContainer> CARTOGRAPHY_TABLE = new ContainerType<>(3);
    ContainerType<BlockContainer> CHEST = new ContainerType<>(27);
    ContainerType<BlockContainer> HOPPER = new ContainerType<>(5);
    ContainerType<BlockContainer> DISPENSER = new ContainerType<>(9);
    ContainerType<BlockContainer> DROPPER = new ContainerType<>(9);
    ContainerType<RecipeContainer> CRAFTING_GRID = new ContainerType<>(4);
    ContainerType<CraftingTableContainer> CRAFTING_TABLE = new ContainerType<>(9);
    ContainerType<Container> CREATED_OUTPUT = new ContainerType<>(1);
    ContainerType<Container> CURSOR = new ContainerType<>(1);
    ContainerType<BlockContainer> DOUBLE_CHEST = new ContainerType<>(54);
    ContainerType<EnchantTableContainer> ENCHANT_TABLE = new ContainerType<>(2);
    ContainerType<BlockContainer> ENDER_CHEST = new ContainerType<>(27);
    ContainerType<FurnaceContainer> FURNACE = new ContainerType<>(3);
    ContainerType<GrindstoneContainer> GRINDSTONE = new ContainerType<>(3);
    ContainerType<LoomContainer> LOOM = new ContainerType<>(4);
    ContainerType<OffhandContainer> OFFHAND = new ContainerType<>(1);
    ContainerType<InventoryContainer> INVENTORY = new ContainerType<>(36);
    ContainerType<BlockContainer> SHULKER_BOX = new ContainerType<>(27);
    ContainerType<SmithingTableContainer> SMITHING_TABLE = new ContainerType<>(4);
    ContainerType<FurnaceContainer> SMOKER = new ContainerType<>(3);
    ContainerType<StonecutterContainer> STONECUTTER = new ContainerType<>(2);

    // Fake Container Types
    ContainerType<FakeContainer> FAKE_CHEST = new ContainerType<>(27);
    ContainerType<FakeContainer> FAKE_DOUBLE_CHEST = new ContainerType<>(54);
}
