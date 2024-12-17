package org.allaymc.server.block.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.data.OxidationLevel;
import org.allaymc.api.block.interfaces.*;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.voxelshape.VoxelShapes;
import org.allaymc.server.block.component.*;
import org.allaymc.server.block.component.button.BlockButtonBaseComponentImpl;
import org.allaymc.server.block.component.button.BlockWoodenButtonBaseComponentImpl;
import org.allaymc.server.block.component.door.BlockDoorBaseComponentImpl;
import org.allaymc.server.block.component.door.BlockIronDoorBaseComponentImpl;
import org.allaymc.server.block.component.grass.BlockShortGrassBaseComponentImpl;
import org.allaymc.server.block.component.grass.BlockTallGrassBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockHangingSignBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockStandingSignBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockWallSignBaseComponentImpl;
import org.allaymc.server.block.component.torch.BlockColoredTorchBaseComponentImpl;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;
import org.allaymc.server.block.impl.*;

import java.time.Duration;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class BlockTypeInitializer {
    public static void initCopper() {
        BiFunction<OxidationLevel, Boolean, BlockType<?>> copper = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_COPPER : BlockTypes.COPPER_BLOCK;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_COPPER : BlockTypes.EXPOSED_COPPER;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_COPPER : BlockTypes.WEATHERED_COPPER;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_COPPER : BlockTypes.OXIDIZED_COPPER;
        };
        BlockTypes.COPPER_BLOCK = buildCopper(BlockId.COPPER_BLOCK, OxidationLevel.UNAFFECTED, copper);
        BlockTypes.EXPOSED_COPPER = buildCopper(BlockId.EXPOSED_COPPER, OxidationLevel.EXPOSED, copper);
        BlockTypes.WEATHERED_COPPER = buildCopper(BlockId.WEATHERED_COPPER, OxidationLevel.WEATHERED, copper);
        BlockTypes.OXIDIZED_COPPER = buildCopper(BlockId.OXIDIZED_COPPER, OxidationLevel.OXIDIZED, copper);
        BlockTypes.WAXED_COPPER = buildCopper(BlockId.WAXED_COPPER, OxidationLevel.UNAFFECTED, copper);
        BlockTypes.WAXED_EXPOSED_COPPER = buildCopper(BlockId.WAXED_EXPOSED_COPPER, OxidationLevel.EXPOSED, copper);
        BlockTypes.WAXED_WEATHERED_COPPER = buildCopper(BlockId.WAXED_WEATHERED_COPPER, OxidationLevel.WEATHERED, copper);
        BlockTypes.WAXED_OXIDIZED_COPPER = buildCopper(BlockId.WAXED_OXIDIZED_COPPER, OxidationLevel.OXIDIZED, copper);

        BiFunction<OxidationLevel, Boolean, BlockType<?>> chiseled_copper = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_CHISELED_COPPER : BlockTypes.CHISELED_COPPER;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_CHISELED_COPPER : BlockTypes.EXPOSED_CHISELED_COPPER;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_CHISELED_COPPER : BlockTypes.WEATHERED_CHISELED_COPPER;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER : BlockTypes.OXIDIZED_CHISELED_COPPER;
        };
        BlockTypes.CHISELED_COPPER = buildCopper(BlockId.CHISELED_COPPER, OxidationLevel.UNAFFECTED, chiseled_copper);
        BlockTypes.EXPOSED_CHISELED_COPPER = buildCopper(BlockId.EXPOSED_CHISELED_COPPER, OxidationLevel.EXPOSED, chiseled_copper);
        BlockTypes.WEATHERED_CHISELED_COPPER = buildCopper(BlockId.WEATHERED_CHISELED_COPPER, OxidationLevel.WEATHERED, chiseled_copper);
        BlockTypes.OXIDIZED_CHISELED_COPPER = buildCopper(BlockId.OXIDIZED_CHISELED_COPPER, OxidationLevel.OXIDIZED, chiseled_copper);
        BlockTypes.WAXED_CHISELED_COPPER = buildCopper(BlockId.WAXED_CHISELED_COPPER, OxidationLevel.UNAFFECTED, chiseled_copper);
        BlockTypes.WAXED_EXPOSED_CHISELED_COPPER = buildCopper(BlockId.WAXED_EXPOSED_CHISELED_COPPER, OxidationLevel.EXPOSED, chiseled_copper);
        BlockTypes.WAXED_WEATHERED_CHISELED_COPPER = buildCopper(BlockId.WAXED_WEATHERED_CHISELED_COPPER, OxidationLevel.WEATHERED, chiseled_copper);
        BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER = buildCopper(BlockId.WAXED_OXIDIZED_CHISELED_COPPER, OxidationLevel.OXIDIZED, chiseled_copper);

        BiFunction<OxidationLevel, Boolean, BlockType<?>> cut_copper = (level, waxed) -> switch (level) {
            case UNAFFECTED -> waxed ? BlockTypes.WAXED_CUT_COPPER : BlockTypes.CUT_COPPER;
            case EXPOSED -> waxed ? BlockTypes.WAXED_EXPOSED_CUT_COPPER : BlockTypes.EXPOSED_CUT_COPPER;
            case WEATHERED -> waxed ? BlockTypes.WAXED_WEATHERED_CUT_COPPER : BlockTypes.WEATHERED_CUT_COPPER;
            case OXIDIZED -> waxed ? BlockTypes.WAXED_OXIDIZED_CUT_COPPER : BlockTypes.OXIDIZED_CUT_COPPER;
        };
        BlockTypes.CUT_COPPER = buildCopper(BlockId.CUT_COPPER, OxidationLevel.UNAFFECTED, cut_copper);
        BlockTypes.EXPOSED_CUT_COPPER = buildCopper(BlockId.EXPOSED_CUT_COPPER, OxidationLevel.EXPOSED, cut_copper);
        BlockTypes.WEATHERED_CUT_COPPER = buildCopper(BlockId.WEATHERED_CUT_COPPER, OxidationLevel.WEATHERED, cut_copper);
        BlockTypes.OXIDIZED_CUT_COPPER = buildCopper(BlockId.OXIDIZED_CUT_COPPER, OxidationLevel.OXIDIZED, cut_copper);
        BlockTypes.WAXED_CUT_COPPER = buildCopper(BlockId.WAXED_CUT_COPPER, OxidationLevel.UNAFFECTED, cut_copper);
        BlockTypes.WAXED_EXPOSED_CUT_COPPER = buildCopper(BlockId.WAXED_EXPOSED_CUT_COPPER, OxidationLevel.EXPOSED, cut_copper);
        BlockTypes.WAXED_WEATHERED_CUT_COPPER = buildCopper(BlockId.WAXED_WEATHERED_CUT_COPPER, OxidationLevel.WEATHERED, cut_copper);
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER = buildCopper(BlockId.WAXED_OXIDIZED_CUT_COPPER, OxidationLevel.OXIDIZED, cut_copper);
    }

    private static BlockType<BlockCopperBehavior> buildCopper(BlockId blockId, OxidationLevel oxidationLevel, BiFunction<OxidationLevel, Boolean, BlockType<?>> blockTypeFunction) {
        return AllayBlockType
                .builder(BlockCopperBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockCopperBaseComponentImpl::new)
                .addComponent(new BlockOxidationComponentImpl(oxidationLevel, blockTypeFunction))
                .build();
    }

    public static void initClay() {
        BlockTypes.CLAY = AllayBlockType
                .builder(BlockClayBehaviorImpl.class)
                .vanillaBlock(BlockId.CLAY)
                .setBaseComponentSupplier(BlockClayBaseComponentImpl::new)
                .build();
    }

    public static void initCarpets() {
        BlockTypes.WHITE_CARPET = buildCarpet(BlockId.WHITE_CARPET);
        BlockTypes.ORANGE_CARPET = buildCarpet(BlockId.ORANGE_CARPET);
        BlockTypes.MAGENTA_CARPET = buildCarpet(BlockId.MAGENTA_CARPET);
        BlockTypes.LIGHT_BLUE_CARPET = buildCarpet(BlockId.LIGHT_BLUE_CARPET);
        BlockTypes.YELLOW_CARPET = buildCarpet(BlockId.YELLOW_CARPET);
        BlockTypes.LIME_CARPET = buildCarpet(BlockId.LIME_CARPET);
        BlockTypes.PINK_CARPET = buildCarpet(BlockId.PINK_CARPET);
        BlockTypes.GRAY_CARPET = buildCarpet(BlockId.GRAY_CARPET);
        BlockTypes.LIGHT_GRAY_CARPET = buildCarpet(BlockId.LIGHT_GRAY_CARPET);
        BlockTypes.CYAN_CARPET = buildCarpet(BlockId.CYAN_CARPET);
        BlockTypes.PURPLE_CARPET = buildCarpet(BlockId.PURPLE_CARPET);
        BlockTypes.BLUE_CARPET = buildCarpet(BlockId.BLUE_CARPET);
        BlockTypes.BROWN_CARPET = buildCarpet(BlockId.BROWN_CARPET);
        BlockTypes.GREEN_CARPET = buildCarpet(BlockId.GREEN_CARPET);
        BlockTypes.RED_CARPET = buildCarpet(BlockId.RED_CARPET);
        BlockTypes.BLACK_CARPET = buildCarpet(BlockId.BLACK_CARPET);
    }

    private static BlockType<BlockCarpetBehavior> buildCarpet(BlockId blockId) {
        return AllayBlockType
                .builder(BlockCarpetBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockCarpetBaseComponentImpl::new)
                .build();
    }

    public static void initJukebox() {
        BlockTypes.JUKEBOX = AllayBlockType
                .builder(BlockJukeboxBehaviorImpl.class)
                .vanillaBlock(BlockId.JUKEBOX)
                .bindBlockEntity(BlockEntityTypes.JUKEBOX)
                .setBaseComponentSupplier(BlockJukeboxBaseComponentImpl::new)
                .build();
    }

    public static void initLeaves() {
        BlockTypes.ACACIA_LEAVES = buildLeaves(BlockId.ACACIA_LEAVES, ItemTypes.ACACIA_SAPLING);
        BlockTypes.AZALEA_LEAVES = buildLeaves(BlockId.AZALEA_LEAVES, null);
        BlockTypes.AZALEA_LEAVES_FLOWERED = buildLeaves(BlockId.AZALEA_LEAVES_FLOWERED, null);
        BlockTypes.BIRCH_LEAVES = buildLeaves(BlockId.BIRCH_LEAVES, ItemTypes.BIRCH_SAPLING);
        BlockTypes.CHERRY_LEAVES = buildLeaves(BlockId.CHERRY_LEAVES, ItemTypes.CHERRY_SAPLING);
        BlockTypes.DARK_OAK_LEAVES = buildLeaves(BlockId.DARK_OAK_LEAVES, ItemTypes.DARK_OAK_SAPLING);
        BlockTypes.JUNGLE_LEAVES = buildLeaves(BlockId.JUNGLE_LEAVES, ItemTypes.JUNGLE_SAPLING, false, true);
        BlockTypes.MANGROVE_LEAVES = buildLeaves(BlockId.MANGROVE_LEAVES, null);
        BlockTypes.OAK_LEAVES = buildLeaves(BlockId.OAK_LEAVES, ItemTypes.OAK_SAPLING, true, false);
        BlockTypes.SPRUCE_LEAVES = buildLeaves(BlockId.SPRUCE_LEAVES, ItemTypes.SPRUCE_SAPLING);
        BlockTypes.PALE_OAK_LEAVES = buildLeaves(BlockId.PALE_OAK_LEAVES, ItemTypes.PALE_OAK_SAPLING);
    }

    private static BlockType<BlockLeavesBehavior> buildLeaves(BlockId id, ItemType<?> saplingType) {
        return buildLeaves(id, saplingType, false, false);
    }

    private static BlockType<BlockLeavesBehavior> buildLeaves(BlockId id, ItemType<?> saplingType, boolean canDropApple, boolean dropMoreSaplings) {
        return AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(id)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBaseComponentSupplier(blockType -> new BlockLeavesBaseComponentImpl(blockType, saplingType, canDropApple, dropMoreSaplings))
                .build();
    }

    public static void initFallable() {
        BlockTypes.GRAVEL = AllayBlockType
                .builder(BlockGravelBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAVEL)
                .setBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
        BlockTypes.SAND = AllayBlockType
                .builder(BlockSandBehaviorImpl.class)
                .vanillaBlock(BlockId.SAND)
                .setBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SAND = AllayBlockType
                .builder(BlockRedSandBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SAND)
                .setBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
    }

    public static void initShulkerBox() {
        BlockTypes.YELLOW_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.WHITE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.UNDYED_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.UNDYED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PURPLE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PINK_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.ORANGE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.MAGENTA_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIME_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GREEN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.CYAN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BROWN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLACK_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
    }

    public static void initGrassBlock() {
        BlockTypes.GRASS_BLOCK = AllayBlockType
                .builder(BlockGrassBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.GRASS_BLOCK)
                .setBaseComponentSupplier(BlockGrassBlockBaseComponentImpl::new)
                .build();
    }

    public static void initShortGrass() {
        BlockTypes.SHORT_GRASS = AllayBlockType
                .builder(BlockShortGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.SHORT_GRASS)
                .setBaseComponentSupplier(BlockShortGrassBaseComponentImpl::new)
                .build();
        BlockTypes.FERN = AllayBlockType
                .builder(BlockFernBehaviorImpl.class)
                .vanillaBlock(BlockId.FERN)
                .setBaseComponentSupplier(BlockShortGrassBaseComponentImpl::new)
                .build();
    }

    public static void initTallGrass() {
        BlockTypes.TALL_GRASS = AllayBlockType
                .builder(BlockTallGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.TALL_GRASS)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(blockType -> new BlockTallGrassBaseComponentImpl(blockType, ItemId.SHORT_GRASS))
                .build();
        BlockTypes.LARGE_FERN = AllayBlockType
                .builder(BlockLargeFernBehaviorImpl.class)
                .vanillaBlock(BlockId.LARGE_FERN)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(blockType -> new BlockTallGrassBaseComponentImpl(blockType, ItemId.FERN))
                .build();
    }

    public static void initBarrel() {
        BlockTypes.BARREL = AllayBlockType
                .builder(BlockBarrelBehaviorImpl.class)
                .vanillaBlock(BlockId.BARREL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .bindBlockEntity(BlockEntityTypes.BARREL)
                .build();
    }

    public static void initChest() {
        BlockTypes.CHEST = AllayBlockType
                .builder(BlockChestBehaviorImpl.class)
                .vanillaBlock(BlockId.CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.CHEST)
                .build();
    }

    public static void initCraftingTable() {
        BlockTypes.CRAFTING_TABLE = AllayBlockType
                .builder(BlockCraftingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.CRAFTING_TABLE)
                .setBaseComponentSupplier(BlockCraftingTableBaseComponentImpl::new)
                .build();
    }

    public static void initStairs() {
        BlockTypes.ACACIA_STAIRS = buildStairs(BlockId.ACACIA_STAIRS);
        BlockTypes.ANDESITE_STAIRS = buildStairs(BlockId.ANDESITE_STAIRS);
        BlockTypes.BAMBOO_MOSAIC_STAIRS = buildStairs(BlockId.BAMBOO_MOSAIC_STAIRS);
        BlockTypes.BAMBOO_STAIRS = buildStairs(BlockId.BAMBOO_STAIRS);
        BlockTypes.BIRCH_STAIRS = buildStairs(BlockId.BIRCH_STAIRS);
        BlockTypes.BLACKSTONE_STAIRS = buildStairs(BlockId.BLACKSTONE_STAIRS);
        BlockTypes.BRICK_STAIRS = buildStairs(BlockId.BRICK_STAIRS);
        BlockTypes.CHERRY_STAIRS = buildStairs(BlockId.CHERRY_STAIRS);
        BlockTypes.COBBLED_DEEPSLATE_STAIRS = buildStairs(BlockId.COBBLED_DEEPSLATE_STAIRS);
        BlockTypes.CRIMSON_STAIRS = buildStairs(BlockId.CRIMSON_STAIRS);
        BlockTypes.CUT_COPPER_STAIRS = buildStairs(BlockId.CUT_COPPER_STAIRS);
        BlockTypes.DARK_OAK_STAIRS = buildStairs(BlockId.DARK_OAK_STAIRS);
        BlockTypes.DARK_PRISMARINE_STAIRS = buildStairs(BlockId.DARK_PRISMARINE_STAIRS);
        BlockTypes.DEEPSLATE_BRICK_STAIRS = buildStairs(BlockId.DEEPSLATE_BRICK_STAIRS);
        BlockTypes.DEEPSLATE_TILE_STAIRS = buildStairs(BlockId.DEEPSLATE_TILE_STAIRS);
        BlockTypes.DIORITE_STAIRS = buildStairs(BlockId.DIORITE_STAIRS);
        BlockTypes.END_BRICK_STAIRS = buildStairs(BlockId.END_BRICK_STAIRS);
        BlockTypes.EXPOSED_CUT_COPPER_STAIRS = buildStairs(BlockId.EXPOSED_CUT_COPPER_STAIRS);
        BlockTypes.GRANITE_STAIRS = buildStairs(BlockId.GRANITE_STAIRS);
        BlockTypes.JUNGLE_STAIRS = buildStairs(BlockId.JUNGLE_STAIRS);
        BlockTypes.MANGROVE_STAIRS = buildStairs(BlockId.MANGROVE_STAIRS);
        BlockTypes.MOSSY_COBBLESTONE_STAIRS = buildStairs(BlockId.MOSSY_COBBLESTONE_STAIRS);
        BlockTypes.MOSSY_STONE_BRICK_STAIRS = buildStairs(BlockId.MOSSY_STONE_BRICK_STAIRS);
        BlockTypes.MUD_BRICK_STAIRS = buildStairs(BlockId.MUD_BRICK_STAIRS);
        BlockTypes.NETHER_BRICK_STAIRS = buildStairs(BlockId.NETHER_BRICK_STAIRS);
        BlockTypes.NORMAL_STONE_STAIRS = buildStairs(BlockId.NORMAL_STONE_STAIRS);
        BlockTypes.OAK_STAIRS = buildStairs(BlockId.OAK_STAIRS);
        BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = buildStairs(BlockId.OXIDIZED_CUT_COPPER_STAIRS);
        BlockTypes.POLISHED_ANDESITE_STAIRS = buildStairs(BlockId.POLISHED_ANDESITE_STAIRS);
        BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = buildStairs(BlockId.POLISHED_BLACKSTONE_BRICK_STAIRS);
        BlockTypes.POLISHED_BLACKSTONE_STAIRS = buildStairs(BlockId.POLISHED_BLACKSTONE_STAIRS);
        BlockTypes.POLISHED_DEEPSLATE_STAIRS = buildStairs(BlockId.POLISHED_DEEPSLATE_STAIRS);
        BlockTypes.POLISHED_DIORITE_STAIRS = buildStairs(BlockId.POLISHED_DIORITE_STAIRS);
        BlockTypes.POLISHED_GRANITE_STAIRS = buildStairs(BlockId.POLISHED_GRANITE_STAIRS);
        BlockTypes.POLISHED_TUFF_STAIRS = buildStairs(BlockId.POLISHED_TUFF_STAIRS);
        BlockTypes.PRISMARINE_BRICKS_STAIRS = buildStairs(BlockId.PRISMARINE_BRICKS_STAIRS);
        BlockTypes.PRISMARINE_STAIRS = buildStairs(BlockId.PRISMARINE_STAIRS);
        BlockTypes.PURPUR_STAIRS = buildStairs(BlockId.PURPUR_STAIRS);
        BlockTypes.QUARTZ_STAIRS = buildStairs(BlockId.QUARTZ_STAIRS);
        BlockTypes.RED_NETHER_BRICK_STAIRS = buildStairs(BlockId.RED_NETHER_BRICK_STAIRS);
        BlockTypes.RED_SANDSTONE_STAIRS = buildStairs(BlockId.RED_SANDSTONE_STAIRS);
        BlockTypes.SANDSTONE_STAIRS = buildStairs(BlockId.SANDSTONE_STAIRS);
        BlockTypes.SMOOTH_QUARTZ_STAIRS = buildStairs(BlockId.SMOOTH_QUARTZ_STAIRS);
        BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = buildStairs(BlockId.SMOOTH_RED_SANDSTONE_STAIRS);
        BlockTypes.SMOOTH_SANDSTONE_STAIRS = buildStairs(BlockId.SMOOTH_SANDSTONE_STAIRS);
        BlockTypes.SPRUCE_STAIRS = buildStairs(BlockId.SPRUCE_STAIRS);
        BlockTypes.STONE_BRICK_STAIRS = buildStairs(BlockId.STONE_BRICK_STAIRS);
        BlockTypes.STONE_STAIRS = buildStairs(BlockId.STONE_STAIRS);
        BlockTypes.TUFF_BRICK_STAIRS = buildStairs(BlockId.TUFF_BRICK_STAIRS);
        BlockTypes.TUFF_STAIRS = buildStairs(BlockId.TUFF_STAIRS);
        BlockTypes.WARPED_STAIRS = buildStairs(BlockId.WARPED_STAIRS);
        BlockTypes.WAXED_CUT_COPPER_STAIRS = buildStairs(BlockId.WAXED_CUT_COPPER_STAIRS);
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = buildStairs(BlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS);
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = buildStairs(BlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = buildStairs(BlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS);
        BlockTypes.WEATHERED_CUT_COPPER_STAIRS = buildStairs(BlockId.WEATHERED_CUT_COPPER_STAIRS);
        BlockTypes.PALE_OAK_STAIRS = buildStairs(BlockId.PALE_OAK_STAIRS);
    }

    private static BlockType<BlockStairsBehavior> buildStairs(BlockId id) {
        return AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(id)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .setBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .build();
    }

    public static void initColoredTorch() {
        BlockTypes.COLORED_TORCH_RED = buildColoredTorch(BlockId.COLORED_TORCH_RED);
        BlockTypes.COLORED_TORCH_BLUE = buildColoredTorch(BlockId.COLORED_TORCH_BLUE);
        BlockTypes.COLORED_TORCH_GREEN = buildColoredTorch(BlockId.COLORED_TORCH_GREEN);
        BlockTypes.COLORED_TORCH_PURPLE = buildColoredTorch(BlockId.COLORED_TORCH_PURPLE);
    }

    private static BlockType<BlockColoredTorchBehavior> buildColoredTorch(BlockId blockId) {
        return AllayBlockType
                .builder(BlockColoredTorchBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockColoredTorchBaseComponentImpl::new)
                .build();
    }

    public static void initTorch() {
        BlockTypes.REDSTONE_TORCH = AllayBlockType
                .builder(BlockRedstoneTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.UNLIT_REDSTONE_TORCH = AllayBlockType
                .builder(BlockRedstoneTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.UNLIT_REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();

        BlockTypes.SOUL_TORCH = AllayBlockType
                .builder(BlockSoulTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.TORCH = AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();

        BlockTypes.UNDERWATER_TORCH = AllayBlockType
                .builder(BlockUnderwaterTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.UNDERWATER_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
    }

    public static void initFurnace() {
        BlockTypes.FURNACE = AllayBlockType
                .builder(BlockFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.FURNACE)
                .build();
        BlockTypes.LIT_FURNACE = AllayBlockType
                .builder(BlockFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.FURNACE)
                .build();
        BlockTypes.BLAST_FURNACE = AllayBlockType
                .builder(BlockBlastFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.BLAST_FURNACE)
                .build();
        BlockTypes.LIT_BLAST_FURNACE = AllayBlockType
                .builder(BlockBlastFurnaceBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.BLAST_FURNACE)
                .build();
        BlockTypes.SMOKER = AllayBlockType
                .builder(BlockSmokerBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.SMOKER)
                .build();
        BlockTypes.LIT_SMOKER = AllayBlockType
                .builder(BlockSmokerBehaviorImpl.class)
                .vanillaBlock(BlockId.LIT_SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.SMOKER)
                .build();
    }

    public static void initWoods() {
        // Log
        BlockTypes.ACACIA_LOG = buildWood(BlockId.ACACIA_LOG, BlockId.STRIPPED_ACACIA_LOG);
        BlockTypes.BAMBOO_BLOCK = buildWood(BlockId.BAMBOO_BLOCK, BlockId.STRIPPED_BAMBOO_BLOCK);
        BlockTypes.BIRCH_LOG = buildWood(BlockId.BIRCH_LOG, BlockId.STRIPPED_BIRCH_LOG);
        BlockTypes.CHERRY_LOG = buildWood(BlockId.CHERRY_LOG, BlockId.STRIPPED_CHERRY_LOG);
        BlockTypes.CRIMSON_STEM = buildWood(BlockId.CRIMSON_STEM, BlockId.STRIPPED_CRIMSON_STEM);
        BlockTypes.DARK_OAK_LOG = buildWood(BlockId.DARK_OAK_LOG, BlockId.STRIPPED_OAK_LOG);
        BlockTypes.JUNGLE_LOG = buildWood(BlockId.JUNGLE_LOG, BlockId.STRIPPED_JUNGLE_LOG);
        BlockTypes.MANGROVE_LOG = buildWood(BlockId.MANGROVE_LOG, BlockId.STRIPPED_MANGROVE_LOG);
        BlockTypes.OAK_LOG = buildWood(BlockId.OAK_LOG, BlockId.STRIPPED_OAK_LOG);
        BlockTypes.SPRUCE_LOG = buildWood(BlockId.SPRUCE_LOG, BlockId.STRIPPED_SPRUCE_LOG);
        BlockTypes.WARPED_STEM = buildWood(BlockId.WARPED_STEM, BlockId.STRIPPED_WARPED_STEM);
        BlockTypes.PALE_OAK_LOG = buildWood(BlockId.PALE_OAK_LOG, BlockId.STRIPPED_PALE_OAK_LOG);
        // Stripped Log
        BlockTypes.STRIPPED_ACACIA_LOG = buildStrippedWood(BlockId.STRIPPED_ACACIA_LOG);
        BlockTypes.STRIPPED_BAMBOO_BLOCK = buildStrippedWood(BlockId.STRIPPED_BAMBOO_BLOCK);
        BlockTypes.STRIPPED_BIRCH_LOG = buildStrippedWood(BlockId.STRIPPED_BIRCH_LOG);
        BlockTypes.STRIPPED_CHERRY_LOG = buildStrippedWood(BlockId.STRIPPED_CHERRY_LOG);
        BlockTypes.STRIPPED_CRIMSON_STEM = buildStrippedWood(BlockId.STRIPPED_CRIMSON_STEM);
        BlockTypes.STRIPPED_DARK_OAK_LOG = buildStrippedWood(BlockId.STRIPPED_DARK_OAK_LOG);
        BlockTypes.STRIPPED_JUNGLE_LOG = buildStrippedWood(BlockId.STRIPPED_JUNGLE_LOG);
        BlockTypes.STRIPPED_MANGROVE_LOG = buildStrippedWood(BlockId.STRIPPED_MANGROVE_LOG);
        BlockTypes.STRIPPED_OAK_LOG = buildStrippedWood(BlockId.STRIPPED_OAK_LOG);
        BlockTypes.STRIPPED_SPRUCE_LOG = buildStrippedWood(BlockId.STRIPPED_SPRUCE_LOG);
        BlockTypes.STRIPPED_WARPED_STEM = buildStrippedWood(BlockId.STRIPPED_WARPED_STEM);
        BlockTypes.STRIPPED_PALE_OAK_LOG = buildStrippedWood(BlockId.STRIPPED_PALE_OAK_LOG);

        // Wood
        BlockTypes.ACACIA_WOOD = buildWood(BlockId.ACACIA_WOOD, BlockId.STRIPPED_ACACIA_WOOD);
        BlockTypes.BIRCH_WOOD = buildWood(BlockId.BIRCH_WOOD, BlockId.STRIPPED_BIRCH_WOOD);
        BlockTypes.CRIMSON_HYPHAE = buildWood(BlockId.CRIMSON_HYPHAE, BlockId.STRIPPED_CRIMSON_HYPHAE);
        BlockTypes.DARK_OAK_WOOD = buildWood(BlockId.DARK_OAK_WOOD, BlockId.STRIPPED_DARK_OAK_WOOD);
        BlockTypes.JUNGLE_WOOD = buildWood(BlockId.JUNGLE_WOOD, BlockId.STRIPPED_JUNGLE_WOOD);
        BlockTypes.OAK_WOOD = buildWood(BlockId.OAK_WOOD, BlockId.STRIPPED_OAK_WOOD);
        BlockTypes.SPRUCE_WOOD = buildWood(BlockId.SPRUCE_WOOD, BlockId.STRIPPED_SPRUCE_WOOD);
        BlockTypes.WARPED_HYPHAE = buildWood(BlockId.WARPED_HYPHAE, BlockId.STRIPPED_WARPED_HYPHAE);
        BlockTypes.CHERRY_WOOD = buildWood(BlockId.CHERRY_WOOD, BlockId.STRIPPED_CHERRY_WOOD);
        BlockTypes.MANGROVE_WOOD = buildWood(BlockId.MANGROVE_WOOD, BlockId.STRIPPED_MANGROVE_WOOD);
        BlockTypes.PALE_OAK_WOOD = buildWood(BlockId.PALE_OAK_WOOD, BlockId.STRIPPED_PALE_OAK_WOOD);
        // Stripped Wood
        BlockTypes.STRIPPED_ACACIA_WOOD = buildStrippedWood(BlockId.STRIPPED_ACACIA_WOOD);
        BlockTypes.STRIPPED_BIRCH_WOOD = buildStrippedWood(BlockId.STRIPPED_BIRCH_WOOD);
        BlockTypes.STRIPPED_CRIMSON_HYPHAE = buildStrippedWood(BlockId.STRIPPED_CRIMSON_HYPHAE);
        BlockTypes.STRIPPED_DARK_OAK_WOOD = buildStrippedWood(BlockId.STRIPPED_DARK_OAK_WOOD);
        BlockTypes.STRIPPED_JUNGLE_WOOD = buildStrippedWood(BlockId.STRIPPED_JUNGLE_WOOD);
        BlockTypes.STRIPPED_OAK_WOOD = buildStrippedWood(BlockId.STRIPPED_OAK_WOOD);
        BlockTypes.STRIPPED_SPRUCE_WOOD = buildStrippedWood(BlockId.STRIPPED_SPRUCE_WOOD);
        BlockTypes.STRIPPED_WARPED_HYPHAE = buildStrippedWood(BlockId.STRIPPED_WARPED_HYPHAE);
        BlockTypes.STRIPPED_CHERRY_WOOD = buildStrippedWood(BlockId.STRIPPED_CHERRY_WOOD);
        BlockTypes.STRIPPED_MANGROVE_WOOD = buildStrippedWood(BlockId.STRIPPED_MANGROVE_WOOD);
        BlockTypes.STRIPPED_PALE_OAK_WOOD = buildStrippedWood(BlockId.STRIPPED_PALE_OAK_WOOD);
    }

    public static void initButtons() {
        BlockTypes.ACACIA_BUTTON = buildWoodenButton(BlockId.ACACIA_BUTTON);
        BlockTypes.BAMBOO_BUTTON = buildWoodenButton(BlockId.BAMBOO_BUTTON);
        BlockTypes.BIRCH_BUTTON = buildWoodenButton(BlockId.BIRCH_BUTTON);
        BlockTypes.CHERRY_BUTTON = buildWoodenButton(BlockId.CHERRY_BUTTON);
        BlockTypes.CRIMSON_BUTTON = buildWoodenButton(BlockId.CRIMSON_BUTTON);
        BlockTypes.DARK_OAK_BUTTON = buildWoodenButton(BlockId.DARK_OAK_BUTTON);
        BlockTypes.JUNGLE_BUTTON = buildWoodenButton(BlockId.JUNGLE_BUTTON);
        BlockTypes.MANGROVE_BUTTON = buildWoodenButton(BlockId.MANGROVE_BUTTON);
        BlockTypes.SPRUCE_BUTTON = buildWoodenButton(BlockId.SPRUCE_BUTTON);
        BlockTypes.WARPED_BUTTON = buildWoodenButton(BlockId.WARPED_BUTTON);
        BlockTypes.WOODEN_BUTTON = buildWoodenButton(BlockId.WOODEN_BUTTON);
        BlockTypes.PALE_OAK_BUTTON = buildWoodenButton(BlockId.PALE_OAK_BUTTON);

        BlockTypes.POLISHED_BLACKSTONE_BUTTON = buildButton(BlockId.POLISHED_BLACKSTONE_BUTTON, blockType -> new BlockButtonBaseComponentImpl(blockType, Duration.ofSeconds(1)));
        BlockTypes.STONE_BUTTON = buildButton(BlockId.STONE_BUTTON, blockType -> new BlockButtonBaseComponentImpl(blockType, Duration.ofSeconds(1)));
    }

    public static void initDoors() {
        BlockTypes.ACACIA_DOOR = buildDoor(BlockId.ACACIA_DOOR);
        BlockTypes.BAMBOO_DOOR = buildDoor(BlockId.BAMBOO_DOOR);
        BlockTypes.BIRCH_DOOR = buildDoor(BlockId.BIRCH_DOOR);
        BlockTypes.CHERRY_DOOR = buildDoor(BlockId.CHERRY_DOOR);
        BlockTypes.CRIMSON_DOOR = buildDoor(BlockId.CRIMSON_DOOR);
        BlockTypes.DARK_OAK_DOOR = buildDoor(BlockId.DARK_OAK_DOOR);
        BlockTypes.JUNGLE_DOOR = buildDoor(BlockId.JUNGLE_DOOR);
        BlockTypes.MANGROVE_DOOR = buildDoor(BlockId.MANGROVE_DOOR);
        BlockTypes.WOODEN_DOOR = buildDoor(BlockId.WOODEN_DOOR);
        BlockTypes.SPRUCE_DOOR = buildDoor(BlockId.SPRUCE_DOOR);
        BlockTypes.WARPED_DOOR = buildDoor(BlockId.WARPED_DOOR);
        BlockTypes.PALE_OAK_DOOR = buildDoor(BlockId.PALE_OAK_DOOR);

        BlockTypes.IRON_DOOR = buildDoor0(BlockId.IRON_DOOR, BlockIronDoorBehaviorImpl.class, BlockIronDoorBaseComponentImpl::new);

//        BlockTypes.COPPER_DOOR = buildCopperDoor(BlockId.COPPER_DOOR);
//        BlockTypes.EXPOSED_COPPER_DOOR = buildCopperDoor(BlockId.EXPOSED_COPPER_DOOR);
//        BlockTypes.OXIDIZED_COPPER_DOOR = buildCopperDoor(BlockId.OXIDIZED_COPPER_DOOR);
//        BlockTypes.WAXED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_COPPER_DOOR);
//        BlockTypes.WAXED_EXPOSED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_EXPOSED_COPPER_DOOR);
//        BlockTypes.WAXED_OXIDIZED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_OXIDIZED_COPPER_DOOR);
//        BlockTypes.WAXED_WEATHERED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_WEATHERED_COPPER_DOOR);
//        BlockTypes.WEATHERED_COPPER_DOOR = buildCopperDoor(BlockId.WEATHERED_COPPER_DOOR);
    }

    private static <T extends BlockBehavior> BlockType<T> buildDoor(BlockId blockId) {
        return buildDoor0(blockId, BlockDoorBehaviorImpl.class, BlockDoorBaseComponentImpl::new);
    }

//    private static <T extends BlockBehavior> BlockType<T> buildCopperDoor(BlockId blockId) {
//        // TODO: replace BlockDoorBaseComponentImpl::new
//        return buildDoor0(blockId, BlockCopperDoorBehaviorImpl.class, BlockDoorBaseComponentImpl::new);
//    }

    private static <T extends BlockBehavior> BlockType<T> buildDoor0(BlockId blockId, Class<? extends BlockBehavior> clazz, Function<BlockType<?>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    public static void initRods() {
        BlockTypes.END_ROD = AllayBlockType
                .builder(BlockEndRodBehaviorImpl.class)
                .vanillaBlock(BlockId.END_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockRodBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHTNING_ROD = AllayBlockType
                .builder(BlockLightningRodBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHTNING_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(BlockRodBaseComponentImpl::new)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildWood(BlockId blockId, BlockId strippedBlockId) {
        return AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(blockType -> new BlockWoodBaseComponentImpl(blockType, strippedBlockId))
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildStrippedWood(BlockId blockId) {
        return AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(blockType -> new BlockWoodBaseComponentImpl(blockType, blockId))
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildWoodenButton(BlockId blockId) {
        return buildButton(blockId, BlockWoodenButtonBaseComponentImpl::new);
    }

    private static <T extends BlockBehavior> BlockType<T> buildButton(BlockId blockId, Function<BlockType<?>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(BlockButtonBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    public static void initWallSigns() {
        BlockTypes.WALL_SIGN = buildWallSign(BlockId.WALL_SIGN, ItemTypes.OAK_SIGN);
        BlockTypes.ACACIA_WALL_SIGN = buildWallSign(BlockId.ACACIA_WALL_SIGN, ItemTypes.ACACIA_SIGN);
        BlockTypes.BAMBOO_WALL_SIGN = buildWallSign(BlockId.BAMBOO_WALL_SIGN, ItemTypes.BAMBOO_SIGN);
        BlockTypes.BIRCH_WALL_SIGN = buildWallSign(BlockId.BIRCH_WALL_SIGN, ItemTypes.BIRCH_SIGN);
        BlockTypes.CHERRY_WALL_SIGN = buildWallSign(BlockId.CHERRY_WALL_SIGN, ItemTypes.CHERRY_SIGN);
        BlockTypes.CRIMSON_WALL_SIGN = buildWallSign(BlockId.CRIMSON_WALL_SIGN, ItemTypes.CRIMSON_SIGN);
        BlockTypes.DARKOAK_WALL_SIGN = buildWallSign(BlockId.DARKOAK_WALL_SIGN, ItemTypes.DARK_OAK_SIGN);
        BlockTypes.JUNGLE_WALL_SIGN = buildWallSign(BlockId.JUNGLE_WALL_SIGN, ItemTypes.JUNGLE_SIGN);
        BlockTypes.MANGROVE_WALL_SIGN = buildWallSign(BlockId.MANGROVE_WALL_SIGN, ItemTypes.MANGROVE_SIGN);
        BlockTypes.SPRUCE_WALL_SIGN = buildWallSign(BlockId.SPRUCE_WALL_SIGN, ItemTypes.SPRUCE_SIGN);
        BlockTypes.WARPED_WALL_SIGN = buildWallSign(BlockId.WARPED_WALL_SIGN, ItemTypes.WARPED_SIGN);
        BlockTypes.PALE_OAK_WALL_SIGN = buildWallSign(BlockId.PALE_OAK_WALL_SIGN, ItemTypes.PALE_OAK_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildWallSign(BlockId blockId, ItemType<?> dropItemType) {
        return AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBaseComponentSupplier(blockType -> new BlockWallSignBaseComponentImpl(blockType, dropItemType))
                .bindBlockEntity(BlockEntityTypes.SIGN)
                .build();
    }

    public static void initStandingSigns() {
        BlockTypes.STANDING_SIGN = buildStandingSign(BlockId.STANDING_SIGN, ItemTypes.OAK_SIGN);
        BlockTypes.ACACIA_STANDING_SIGN = buildStandingSign(BlockId.ACACIA_STANDING_SIGN, ItemTypes.ACACIA_SIGN);
        BlockTypes.BAMBOO_STANDING_SIGN = buildStandingSign(BlockId.BAMBOO_STANDING_SIGN, ItemTypes.BAMBOO_SIGN);
        BlockTypes.BIRCH_STANDING_SIGN = buildStandingSign(BlockId.BIRCH_STANDING_SIGN, ItemTypes.BIRCH_SIGN);
        BlockTypes.CHERRY_STANDING_SIGN = buildStandingSign(BlockId.CHERRY_STANDING_SIGN, ItemTypes.CHERRY_SIGN);
        BlockTypes.CRIMSON_STANDING_SIGN = buildStandingSign(BlockId.CRIMSON_STANDING_SIGN, ItemTypes.CRIMSON_SIGN);
        BlockTypes.DARKOAK_STANDING_SIGN = buildStandingSign(BlockId.DARKOAK_STANDING_SIGN, ItemTypes.DARK_OAK_SIGN);
        BlockTypes.JUNGLE_STANDING_SIGN = buildStandingSign(BlockId.JUNGLE_STANDING_SIGN, ItemTypes.JUNGLE_SIGN);
        BlockTypes.MANGROVE_STANDING_SIGN = buildStandingSign(BlockId.MANGROVE_STANDING_SIGN, ItemTypes.MANGROVE_SIGN);
        BlockTypes.SPRUCE_STANDING_SIGN = buildStandingSign(BlockId.SPRUCE_STANDING_SIGN, ItemTypes.SPRUCE_SIGN);
        BlockTypes.WARPED_STANDING_SIGN = buildStandingSign(BlockId.WARPED_STANDING_SIGN, ItemTypes.WARPED_SIGN);
        BlockTypes.PALE_OAK_STANDING_SIGN = buildStandingSign(BlockId.PALE_OAK_STANDING_SIGN, ItemTypes.PALE_OAK_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStandingSign(BlockId blockId, ItemType<?> dropItemType) {
        return AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .setBaseComponentSupplier(blockType -> new BlockStandingSignBaseComponentImpl(blockType, dropItemType))
                .bindBlockEntity(BlockEntityTypes.SIGN)
                .build();
    }

    public static void initHangingSigns() {
        BlockTypes.ACACIA_HANGING_SIGN = buildHangingSign(BlockId.ACACIA_HANGING_SIGN);
        BlockTypes.BAMBOO_HANGING_SIGN = buildHangingSign(BlockId.BAMBOO_HANGING_SIGN);
        BlockTypes.BIRCH_HANGING_SIGN = buildHangingSign(BlockId.BIRCH_HANGING_SIGN);
        BlockTypes.CHERRY_HANGING_SIGN = buildHangingSign(BlockId.CHERRY_HANGING_SIGN);
        BlockTypes.CRIMSON_HANGING_SIGN = buildHangingSign(BlockId.CRIMSON_HANGING_SIGN);
        BlockTypes.DARK_OAK_HANGING_SIGN = buildHangingSign(BlockId.DARK_OAK_HANGING_SIGN);
        BlockTypes.JUNGLE_HANGING_SIGN = buildHangingSign(BlockId.JUNGLE_HANGING_SIGN);
        BlockTypes.MANGROVE_HANGING_SIGN = buildHangingSign(BlockId.MANGROVE_HANGING_SIGN);
        BlockTypes.OAK_HANGING_SIGN = buildHangingSign(BlockId.OAK_HANGING_SIGN);
        BlockTypes.SPRUCE_HANGING_SIGN = buildHangingSign(BlockId.SPRUCE_HANGING_SIGN);
        BlockTypes.WARPED_HANGING_SIGN = buildHangingSign(BlockId.WARPED_HANGING_SIGN);
        BlockTypes.PALE_OAK_HANGING_SIGN = buildHangingSign(BlockId.PALE_OAK_HANGING_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildHangingSign(BlockId blockId) {
        return AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .setBaseComponentSupplier(BlockHangingSignBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.HANGING_SIGN)
                .build();
    }

    public static void initSlimeBlock() {
        BlockTypes.SLIME = AllayBlockType
                .builder(BlockSlimeBehaviorImpl.class)
                .vanillaBlock(BlockId.SLIME)
                .setBaseComponentSupplier(BlockSlimeBaseComponentImpl::new)
                .build();
    }

    public static void initHayBlock() {
        BlockTypes.HAY_BLOCK = AllayBlockType
                .builder(BlockHayBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.HAY_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .setBaseComponentSupplier(BlockHayBlockBaseComponentImpl::new)
                .build();
    }

    public static void initWater() {
        BlockTypes.WATER = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBaseComponentSupplier(BlockWaterBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(VoxelShapes::buildLiquidShape))
                .build();
        BlockTypes.FLOWING_WATER = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWING_WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBaseComponentSupplier(BlockLiquidBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(VoxelShapes::buildLiquidShape))
                .build();
    }

    public static void initLava() {
        BlockTypes.LAVA = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBaseComponentSupplier(BlockLiquidBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(VoxelShapes::buildLiquidShape))
                .build();
        BlockTypes.FLOWING_LAVA = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWING_LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBaseComponentSupplier(BlockLiquidBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(VoxelShapes::buildLiquidShape))
                .build();
    }

    public static void initEnchantingTable() {
        BlockTypes.ENCHANTING_TABLE = AllayBlockType
                .builder(BlockEnchantingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.ENCHANTING_TABLE)
                .bindBlockEntity(BlockEntityTypes.ENCHANT_TABLE)
                .setBaseComponentSupplier(BlockEnchantingTableBaseComponentImpl::new)
                .build();
    }

    public static void initStone() {
        BlockTypes.STONE = AllayBlockType
                .builder(BlockStoneBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE)
                .setBaseComponentSupplier(BlockStoneBaseComponentImpl::new)
                .build();
    }

    public static void initGlass() {
        // Normal
        BlockTypes.BLACK_STAINED_GLASS = buildGlass(BlockId.BLACK_STAINED_GLASS);
        BlockTypes.BLUE_STAINED_GLASS = buildGlass(BlockId.BLUE_STAINED_GLASS);
        BlockTypes.BROWN_STAINED_GLASS = buildGlass(BlockId.BROWN_STAINED_GLASS);
        BlockTypes.CYAN_STAINED_GLASS = buildGlass(BlockId.CYAN_STAINED_GLASS);
        BlockTypes.GLASS = buildGlass(BlockId.GLASS);
        BlockTypes.GRAY_STAINED_GLASS = buildGlass(BlockId.GRAY_STAINED_GLASS);
        BlockTypes.GREEN_STAINED_GLASS = buildGlass(BlockId.GREEN_STAINED_GLASS);
        BlockTypes.LIGHT_BLUE_STAINED_GLASS = buildGlass(BlockId.LIGHT_BLUE_STAINED_GLASS);
        BlockTypes.LIGHT_GRAY_STAINED_GLASS = buildGlass(BlockId.LIGHT_GRAY_STAINED_GLASS);
        BlockTypes.LIME_STAINED_GLASS = buildGlass(BlockId.LIME_STAINED_GLASS);
        BlockTypes.MAGENTA_STAINED_GLASS = buildGlass(BlockId.MAGENTA_STAINED_GLASS);
        BlockTypes.ORANGE_STAINED_GLASS = buildGlass(BlockId.ORANGE_STAINED_GLASS);
        BlockTypes.PINK_STAINED_GLASS = buildGlass(BlockId.PINK_STAINED_GLASS);
        BlockTypes.PURPLE_STAINED_GLASS = buildGlass(BlockId.PURPLE_STAINED_GLASS);
        BlockTypes.RED_STAINED_GLASS = buildGlass(BlockId.RED_STAINED_GLASS);
        BlockTypes.WHITE_STAINED_GLASS = buildGlass(BlockId.WHITE_STAINED_GLASS);
        BlockTypes.YELLOW_STAINED_GLASS = buildGlass(BlockId.YELLOW_STAINED_GLASS);
        // Special
        BlockTypes.TINTED_GLASS = buildGlass(BlockId.TINTED_GLASS);

        // Hard
        BlockTypes.HARD_BLACK_STAINED_GLASS = buildGlass(BlockId.HARD_BLACK_STAINED_GLASS);
        BlockTypes.HARD_BLUE_STAINED_GLASS = buildGlass(BlockId.HARD_BLUE_STAINED_GLASS);
        BlockTypes.HARD_BROWN_STAINED_GLASS = buildGlass(BlockId.HARD_BROWN_STAINED_GLASS);
        BlockTypes.HARD_CYAN_STAINED_GLASS = buildGlass(BlockId.HARD_CYAN_STAINED_GLASS);
        BlockTypes.HARD_GLASS = buildGlass(BlockId.HARD_GLASS);
        BlockTypes.HARD_GRAY_STAINED_GLASS = buildGlass(BlockId.HARD_GRAY_STAINED_GLASS);
        BlockTypes.HARD_GREEN_STAINED_GLASS = buildGlass(BlockId.HARD_GREEN_STAINED_GLASS);
        BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS = buildGlass(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS);
        BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS = buildGlass(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS);
        BlockTypes.HARD_LIME_STAINED_GLASS = buildGlass(BlockId.HARD_LIME_STAINED_GLASS);
        BlockTypes.HARD_MAGENTA_STAINED_GLASS = buildGlass(BlockId.HARD_MAGENTA_STAINED_GLASS);
        BlockTypes.HARD_ORANGE_STAINED_GLASS = buildGlass(BlockId.HARD_ORANGE_STAINED_GLASS);
        BlockTypes.HARD_PINK_STAINED_GLASS = buildGlass(BlockId.HARD_PINK_STAINED_GLASS);
        BlockTypes.HARD_PURPLE_STAINED_GLASS = buildGlass(BlockId.HARD_PURPLE_STAINED_GLASS);
        BlockTypes.HARD_RED_STAINED_GLASS = buildGlass(BlockId.HARD_RED_STAINED_GLASS);
        BlockTypes.HARD_WHITE_STAINED_GLASS = buildGlass(BlockId.HARD_WHITE_STAINED_GLASS);
        BlockTypes.HARD_YELLOW_STAINED_GLASS = buildGlass(BlockId.HARD_YELLOW_STAINED_GLASS);
    }

    public static void initGlassPane() {
        // Normal
        BlockTypes.BLACK_STAINED_GLASS_PANE = buildGlassPane(BlockId.BLACK_STAINED_GLASS_PANE);
        BlockTypes.BLUE_STAINED_GLASS_PANE = buildGlassPane(BlockId.BLUE_STAINED_GLASS_PANE);
        BlockTypes.BROWN_STAINED_GLASS_PANE = buildGlassPane(BlockId.BROWN_STAINED_GLASS_PANE);
        BlockTypes.CYAN_STAINED_GLASS_PANE = buildGlassPane(BlockId.CYAN_STAINED_GLASS_PANE);
        BlockTypes.GLASS_PANE = buildGlassPane(BlockId.GLASS_PANE);
        BlockTypes.GRAY_STAINED_GLASS_PANE = buildGlassPane(BlockId.GRAY_STAINED_GLASS_PANE);
        BlockTypes.GREEN_STAINED_GLASS_PANE = buildGlassPane(BlockId.GREEN_STAINED_GLASS_PANE);
        BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE = buildGlassPane(BlockId.LIGHT_BLUE_STAINED_GLASS_PANE);
        BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE = buildGlassPane(BlockId.LIGHT_GRAY_STAINED_GLASS_PANE);
        BlockTypes.LIME_STAINED_GLASS_PANE = buildGlassPane(BlockId.LIME_STAINED_GLASS_PANE);
        BlockTypes.MAGENTA_STAINED_GLASS_PANE = buildGlassPane(BlockId.MAGENTA_STAINED_GLASS_PANE);
        BlockTypes.ORANGE_STAINED_GLASS_PANE = buildGlassPane(BlockId.ORANGE_STAINED_GLASS_PANE);
        BlockTypes.PINK_STAINED_GLASS_PANE = buildGlassPane(BlockId.PINK_STAINED_GLASS_PANE);
        BlockTypes.PURPLE_STAINED_GLASS_PANE = buildGlassPane(BlockId.PURPLE_STAINED_GLASS_PANE);
        BlockTypes.RED_STAINED_GLASS_PANE = buildGlassPane(BlockId.RED_STAINED_GLASS_PANE);
        BlockTypes.WHITE_STAINED_GLASS_PANE = buildGlassPane(BlockId.WHITE_STAINED_GLASS_PANE);
        BlockTypes.YELLOW_STAINED_GLASS_PANE = buildGlassPane(BlockId.YELLOW_STAINED_GLASS_PANE);

        // Hard
        BlockTypes.HARD_BLACK_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_BLACK_STAINED_GLASS_PANE);
        BlockTypes.HARD_BLUE_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_BLUE_STAINED_GLASS_PANE);
        BlockTypes.HARD_BROWN_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_BROWN_STAINED_GLASS_PANE);
        BlockTypes.HARD_CYAN_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_CYAN_STAINED_GLASS_PANE);
        BlockTypes.HARD_GLASS_PANE = buildGlassPane(BlockId.HARD_GLASS_PANE);
        BlockTypes.HARD_GRAY_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_GRAY_STAINED_GLASS_PANE);
        BlockTypes.HARD_GREEN_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_GREEN_STAINED_GLASS_PANE);
        BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE);
        BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE);
        BlockTypes.HARD_LIME_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_LIME_STAINED_GLASS_PANE);
        BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_MAGENTA_STAINED_GLASS_PANE);
        BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_ORANGE_STAINED_GLASS_PANE);
        BlockTypes.HARD_PINK_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_PINK_STAINED_GLASS_PANE);
        BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_PURPLE_STAINED_GLASS_PANE);
        BlockTypes.HARD_RED_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_RED_STAINED_GLASS_PANE);
        BlockTypes.HARD_WHITE_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_WHITE_STAINED_GLASS_PANE);
        BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE = buildGlassPane(BlockId.HARD_YELLOW_STAINED_GLASS_PANE);
    }

    public static BlockType<BlockGlassBehavior> buildGlass(BlockId blockId) {
        return AllayBlockType
                .builder(BlockGlassBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockGlassBaseComponentImpl::new)
                .build();
    }

    public static BlockType<BlockGlassPaneBehavior> buildGlassPane(BlockId blockId) {
        return AllayBlockType
                .builder(BlockGlassPaneBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setBaseComponentSupplier(BlockGlassBaseComponentImpl::new)
                .build();
    }
}
