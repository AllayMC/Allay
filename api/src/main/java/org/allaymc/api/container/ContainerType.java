package org.allaymc.api.container;

import lombok.Getter;
import org.allaymc.api.container.interfaces.*;

/**
 * ContainerType represents a type of container, which can be used to get container
 * instance from a {@link ContainerHolder}. The instances of this class are compared
 * by reference.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("ALL")
public class ContainerType<T extends Container> {

    public static final ContainerType<AnvilContainer> ANVIL = new ContainerType<>(3);
    public static final ContainerType<ArmorContainer> ARMOR = new ContainerType<>(4);
    public static final ContainerType<BlockContainer> BARREL = new ContainerType<>(27);
    public static final ContainerType<BeaconContainer> BEACON = new ContainerType<>(1);
    public static final ContainerType<FurnaceContainer> BLAST_FURNACE = new ContainerType<>(3);
    public static final ContainerType<BrewingStandContainer> BREWING_STAND = new ContainerType<>(5);
    public static final ContainerType<CartographyTableContainer> CARTOGRAPHY_TABLE = new ContainerType<>(3);
    public static final ContainerType<BlockContainer> CHEST = new ContainerType<>(27);
    public static final ContainerType<RecipeContainer> CRAFTING_GRID = new ContainerType<>(4);
    public static final ContainerType<CraftingTableContainer> CRAFTING_TABLE = new ContainerType<>(9);
    public static final ContainerType<Container> CREATED_OUTPUT = new ContainerType<>(1);
    public static final ContainerType<Container> CURSOR = new ContainerType<>(1);
    public static final ContainerType<BlockContainer> DOUBLE_CHEST = new ContainerType<>(54);
    public static final ContainerType<EnchantTableContainer> ENCHANT_TABLE = new ContainerType<>(2);
    public static final ContainerType<BlockContainer> ENDER_CHEST = new ContainerType<>(27);
    public static final ContainerType<FurnaceContainer> FURNACE = new ContainerType<>(3);
    public static final ContainerType<GrindstoneContainer> GRINDSTONE = new ContainerType<>(3);
    public static final ContainerType<LoomContainer> LOOM = new ContainerType<>(4);
    public static final ContainerType<OffhandContainer> OFFHAND = new ContainerType<>(1);
    public static final ContainerType<InventoryContainer> INVENTORY = new ContainerType<>(36);
    public static final ContainerType<BlockContainer> SHULKER_BOX = new ContainerType<>(27);
    public static final ContainerType<SmithingTableContainer> SMITHING_TABLE = new ContainerType<>(4);
    public static final ContainerType<FurnaceContainer> SMOKER = new ContainerType<>(3);
    public static final ContainerType<StonecutterContainer> STONECUTTER = new ContainerType<>(2);

    @Getter
    private final int size;

    public ContainerType(int size) {
        this.size = size;
    }
}
