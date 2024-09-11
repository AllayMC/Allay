package org.allaymc.server.block.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.interfaces.*;
import org.allaymc.api.block.interfaces.button.*;
import org.allaymc.api.block.interfaces.door.*;
import org.allaymc.api.block.interfaces.hangingsign.*;
import org.allaymc.api.block.interfaces.leaves.*;
import org.allaymc.api.block.interfaces.liquid.BlockFlowingLavaBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockFlowingWaterBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockLavaBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockWaterBehavior;
import org.allaymc.api.block.interfaces.log.*;
import org.allaymc.api.block.interfaces.sand.BlockRedSandBehavior;
import org.allaymc.api.block.interfaces.sand.BlockSandBehavior;
import org.allaymc.api.block.interfaces.shulkerbox.*;
import org.allaymc.api.block.interfaces.stairs.*;
import org.allaymc.api.block.interfaces.standingsign.*;
import org.allaymc.api.block.interfaces.torch.*;
import org.allaymc.api.block.interfaces.wallsign.*;
import org.allaymc.api.block.interfaces.wood.*;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.voxelshape.VoxelShapes;
import org.allaymc.server.block.component.*;
import org.allaymc.server.block.component.button.BlockWoodenButtonBaseComponentImpl;
import org.allaymc.server.block.component.door.BlockDoorBaseComponentImpl;
import org.allaymc.server.block.component.grass.BlockShortGrassBaseComponentImpl;
import org.allaymc.server.block.component.grass.BlockTallGrassBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockHangingSignBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockStandingSignBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockWallSignBaseComponentImpl;
import org.allaymc.server.block.component.torch.BlockColoredTorchBaseComponentImpl;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;
import org.allaymc.server.block.component.wood.BlockOldWoodBaseComponentImpl;
import org.allaymc.server.block.component.wood.BlockWoodBaseComponentImpl;

import java.util.function.Function;

/**
 * Allay Project 2024/3/9
 *
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class BlockTypeInitializer {
    public static void initLeaves() {
        BlockTypes.ACACIA_LEAVES = buildLeaves(BlockAcaciaLeavesBehavior.class, BlockId.ACACIA_LEAVES);
        BlockTypes.AZALEA_LEAVES = buildLeaves(BlockAzaleaLeavesBehavior.class, BlockId.AZALEA_LEAVES);
        BlockTypes.AZALEA_LEAVES_FLOWERED = buildLeaves(BlockAzaleaLeavesFloweredBehavior.class, BlockId.AZALEA_LEAVES_FLOWERED);
        BlockTypes.BIRCH_LEAVES = buildLeaves(BlockBirchLeavesBehavior.class, BlockId.BIRCH_LEAVES);
        BlockTypes.CHERRY_LEAVES = buildLeaves(BlockCherryLeavesBehavior.class, BlockId.CHERRY_LEAVES);
        BlockTypes.DARK_OAK_LEAVES = buildLeaves(BlockDarkOakLeavesBehavior.class, BlockId.DARK_OAK_LEAVES);
        BlockTypes.JUNGLE_LEAVES = buildLeaves(BlockJungleLeavesBehavior.class, BlockId.JUNGLE_LEAVES);
        BlockTypes.MANGROVE_LEAVES = buildLeaves(BlockMangroveLeavesBehavior.class, BlockId.MANGROVE_LEAVES);
        BlockTypes.OAK_LEAVES = buildLeaves(BlockOakLeavesBehavior.class, BlockId.OAK_LEAVES);
        BlockTypes.SPRUCE_LEAVES = buildLeaves(BlockSpruceLeavesBehavior.class, BlockId.SPRUCE_LEAVES);
    }

    private static <T extends BlockBehavior> BlockType<T> buildLeaves(Class<T> clazz, BlockId blockId) {
        return AllayBlockType.builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
    }

    public static void initFallable() {
        BlockTypes.GRAVEL = AllayBlockType
                .builder(BlockGravelBehavior.class)
                .vanillaBlock(BlockId.GRAVEL)
                .setBlockBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
        BlockTypes.SAND = AllayBlockType
                .builder(BlockSandBehavior.class)
                .vanillaBlock(BlockId.SAND)
                .setBlockBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SAND = AllayBlockType
                .builder(BlockRedSandBehavior.class)
                .vanillaBlock(BlockId.RED_SAND)
                .setBlockBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
    }

    public static void initShulkerBoxes() {
        BlockTypes.BLACK_SHULKER_BOX = buildShulkerBox(BlockBlackShulkerBoxBehavior.class, BlockId.BLACK_SHULKER_BOX);
        BlockTypes.BLUE_SHULKER_BOX = buildShulkerBox(BlockBlueShulkerBoxBehavior.class, BlockId.BLUE_SHULKER_BOX);
        BlockTypes.BROWN_SHULKER_BOX = buildShulkerBox(BlockBrownShulkerBoxBehavior.class, BlockId.BROWN_SHULKER_BOX);
        BlockTypes.CYAN_SHULKER_BOX = buildShulkerBox(BlockCyanShulkerBoxBehavior.class, BlockId.CYAN_SHULKER_BOX);
        BlockTypes.GRAY_SHULKER_BOX = buildShulkerBox(BlockGrayShulkerBoxBehavior.class, BlockId.GRAY_SHULKER_BOX);
        BlockTypes.GREEN_SHULKER_BOX = buildShulkerBox(BlockGreenShulkerBoxBehavior.class, BlockId.GREEN_SHULKER_BOX);
        BlockTypes.LIGHT_BLUE_SHULKER_BOX = buildShulkerBox(BlockLightBlueShulkerBoxBehavior.class, BlockId.LIGHT_BLUE_SHULKER_BOX);
        BlockTypes.LIGHT_GRAY_SHULKER_BOX = buildShulkerBox(BlockLightGrayShulkerBoxBehavior.class, BlockId.LIGHT_GRAY_SHULKER_BOX);
        BlockTypes.LIME_SHULKER_BOX = buildShulkerBox(BlockLimeShulkerBoxBehavior.class, BlockId.LIME_SHULKER_BOX);
        BlockTypes.MAGENTA_SHULKER_BOX = buildShulkerBox(BlockMagentaShulkerBoxBehavior.class, BlockId.MAGENTA_SHULKER_BOX);
        BlockTypes.ORANGE_SHULKER_BOX = buildShulkerBox(BlockOrangeShulkerBoxBehavior.class, BlockId.ORANGE_SHULKER_BOX);
        BlockTypes.PINK_SHULKER_BOX = buildShulkerBox(BlockPinkShulkerBoxBehavior.class, BlockId.PINK_SHULKER_BOX);
        BlockTypes.PURPLE_SHULKER_BOX = buildShulkerBox(BlockPurpleShulkerBoxBehavior.class, BlockId.PURPLE_SHULKER_BOX);
        BlockTypes.RED_SHULKER_BOX = buildShulkerBox(BlockRedShulkerBoxBehavior.class, BlockId.RED_SHULKER_BOX);
        BlockTypes.UNDYED_SHULKER_BOX = buildShulkerBox(BlockUndyedShulkerBoxBehavior.class, BlockId.UNDYED_SHULKER_BOX);
        BlockTypes.WHITE_SHULKER_BOX = buildShulkerBox(BlockWhiteShulkerBoxBehavior.class, BlockId.WHITE_SHULKER_BOX);
        BlockTypes.YELLOW_SHULKER_BOX = buildShulkerBox(BlockYellowShulkerBoxBehavior.class, BlockId.YELLOW_SHULKER_BOX);
    }

    private static <T extends BlockBehavior> BlockType<T> buildShulkerBox(Class<T> clazz, BlockId blockId) {
        return AllayBlockType.builder(clazz)
                .vanillaBlock(blockId)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
    }

    public static void initGrassBlock() {
        BlockTypes.GRASS_BLOCK = AllayBlockType
                .builder(BlockGrassBlockBehavior.class)
                .vanillaBlock(BlockId.GRASS_BLOCK)
                .setBlockBaseComponentSupplier(BlockGrassBlockBaseComponentImpl::new)
                .build();
    }

    public static void initShortGrass() {
        BlockTypes.SHORT_GRASS = AllayBlockType
                .builder(BlockShortGrassBehavior.class)
                .vanillaBlock(BlockId.SHORT_GRASS)
                .setBlockBaseComponentSupplier(BlockShortGrassBaseComponentImpl::new)
                .build();
        BlockTypes.FERN = AllayBlockType
                .builder(BlockFernBehavior.class)
                .vanillaBlock(BlockId.FERN)
                .setBlockBaseComponentSupplier(BlockShortGrassBaseComponentImpl::new)
                .build();
    }

    public static void initTallGrass() {
        BlockTypes.TALL_GRASS = AllayBlockType
                .builder(BlockTallGrassBehavior.class)
                .vanillaBlock(BlockId.TALL_GRASS)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(BlockTallGrassBaseComponentImpl::new)
                .build();
        BlockTypes.LARGE_FERN = AllayBlockType
                .builder(BlockLargeFernBehavior.class)
                .vanillaBlock(BlockId.LARGE_FERN)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(BlockTallGrassBaseComponentImpl::new)
                .build();
    }

    public static void initBarrel() {
        BlockTypes.BARREL = AllayBlockType
                .builder(BlockBarrelBehavior.class)
                .vanillaBlock(BlockId.BARREL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .bindBlockEntity(BlockEntityTypes.BARREL)
                .build();
    }

    public static void initChest() {
        BlockTypes.CHEST = AllayBlockType
                .builder(BlockChestBehavior.class)
                .vanillaBlock(BlockId.CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.CHEST)
                .build();
    }

    public static void initCraftingTable() {
        BlockTypes.CRAFTING_TABLE = AllayBlockType
                .builder(BlockCraftingTableBehavior.class)
                .vanillaBlock(BlockId.CRAFTING_TABLE)
                .setBlockBaseComponentSupplier(BlockCraftingTableBaseComponentImpl::new)
                .build();
    }

    public static void initStairs() {
        BlockTypes.ACACIA_STAIRS = buildStairs(BlockAcaciaStairsBehavior.class, BlockId.ACACIA_STAIRS);
        BlockTypes.ANDESITE_STAIRS = buildStairs(BlockAndesiteStairsBehavior.class, BlockId.ANDESITE_STAIRS);
        BlockTypes.BAMBOO_MOSAIC_STAIRS = buildStairs(BlockBambooMosaicStairsBehavior.class, BlockId.BAMBOO_MOSAIC_STAIRS);
        BlockTypes.BAMBOO_STAIRS = buildStairs(BlockBambooStairsBehavior.class, BlockId.BAMBOO_STAIRS);
        BlockTypes.BIRCH_STAIRS = buildStairs(BlockBirchStairsBehavior.class, BlockId.BIRCH_STAIRS);
        BlockTypes.BLACKSTONE_STAIRS = buildStairs(BlockBlackstoneStairsBehavior.class, BlockId.BLACKSTONE_STAIRS);
        BlockTypes.BRICK_STAIRS = buildStairs(BlockBrickStairsBehavior.class, BlockId.BRICK_STAIRS);
        BlockTypes.CHERRY_STAIRS = buildStairs(BlockCherryStairsBehavior.class, BlockId.CHERRY_STAIRS);
        BlockTypes.COBBLED_DEEPSLATE_STAIRS = buildStairs(BlockCobbledDeepslateStairsBehavior.class, BlockId.COBBLED_DEEPSLATE_STAIRS);
        BlockTypes.CRIMSON_STAIRS = buildStairs(BlockCrimsonStairsBehavior.class, BlockId.CRIMSON_STAIRS);
        BlockTypes.CUT_COPPER_STAIRS = buildStairs(BlockCutCopperStairsBehavior.class, BlockId.CUT_COPPER_STAIRS);
        BlockTypes.DARK_OAK_STAIRS = buildStairs(BlockDarkOakStairsBehavior.class, BlockId.DARK_OAK_STAIRS);
        BlockTypes.DARK_PRISMARINE_STAIRS = buildStairs(BlockDarkPrismarineStairsBehavior.class, BlockId.DARK_PRISMARINE_STAIRS);
        BlockTypes.DEEPSLATE_BRICK_STAIRS = buildStairs(BlockDeepslateBrickStairsBehavior.class, BlockId.DEEPSLATE_BRICK_STAIRS);
        BlockTypes.DEEPSLATE_TILE_STAIRS = buildStairs(BlockDeepslateTileStairsBehavior.class, BlockId.DEEPSLATE_TILE_STAIRS);
        BlockTypes.DIORITE_STAIRS = buildStairs(BlockDioriteStairsBehavior.class, BlockId.DIORITE_STAIRS);
        BlockTypes.END_BRICK_STAIRS = buildStairs(BlockEndBrickStairsBehavior.class, BlockId.END_BRICK_STAIRS);
        BlockTypes.EXPOSED_CUT_COPPER_STAIRS = buildStairs(BlockExposedCutCopperStairsBehavior.class, BlockId.EXPOSED_CUT_COPPER_STAIRS);
        BlockTypes.GRANITE_STAIRS = buildStairs(BlockGraniteStairsBehavior.class, BlockId.GRANITE_STAIRS);
        BlockTypes.JUNGLE_STAIRS = buildStairs(BlockJungleStairsBehavior.class, BlockId.JUNGLE_STAIRS);
        BlockTypes.MANGROVE_STAIRS = buildStairs(BlockMangroveStairsBehavior.class, BlockId.MANGROVE_STAIRS);
        BlockTypes.MOSSY_COBBLESTONE_STAIRS = buildStairs(BlockMossyCobblestoneStairsBehavior.class, BlockId.MOSSY_COBBLESTONE_STAIRS);
        BlockTypes.MOSSY_STONE_BRICK_STAIRS = buildStairs(BlockMossyStoneBrickStairsBehavior.class, BlockId.MOSSY_STONE_BRICK_STAIRS);
        BlockTypes.MUD_BRICK_STAIRS = buildStairs(BlockMudBrickStairsBehavior.class, BlockId.MUD_BRICK_STAIRS);
        BlockTypes.NETHER_BRICK_STAIRS = buildStairs(BlockNetherBrickStairsBehavior.class, BlockId.NETHER_BRICK_STAIRS);
        BlockTypes.NORMAL_STONE_STAIRS = buildStairs(BlockNormalStoneStairsBehavior.class, BlockId.NORMAL_STONE_STAIRS);
        BlockTypes.OAK_STAIRS = buildStairs(BlockOakStairsBehavior.class, BlockId.OAK_STAIRS);
        BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = buildStairs(BlockOxidizedCutCopperStairsBehavior.class, BlockId.OXIDIZED_CUT_COPPER_STAIRS);
        BlockTypes.POLISHED_ANDESITE_STAIRS = buildStairs(BlockPolishedAndesiteStairsBehavior.class, BlockId.POLISHED_ANDESITE_STAIRS);
        BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = buildStairs(BlockPolishedBlackstoneBrickStairsBehavior.class, BlockId.POLISHED_BLACKSTONE_BRICK_STAIRS);
        BlockTypes.POLISHED_BLACKSTONE_STAIRS = buildStairs(BlockPolishedBlackstoneStairsBehavior.class, BlockId.POLISHED_BLACKSTONE_STAIRS);
        BlockTypes.POLISHED_DEEPSLATE_STAIRS = buildStairs(BlockPolishedDeepslateStairsBehavior.class, BlockId.POLISHED_DEEPSLATE_STAIRS);
        BlockTypes.POLISHED_DIORITE_STAIRS = buildStairs(BlockPolishedDioriteStairsBehavior.class, BlockId.POLISHED_DIORITE_STAIRS);
        BlockTypes.POLISHED_GRANITE_STAIRS = buildStairs(BlockPolishedGraniteStairsBehavior.class, BlockId.POLISHED_GRANITE_STAIRS);
        BlockTypes.POLISHED_TUFF_STAIRS = buildStairs(BlockPolishedTuffStairsBehavior.class, BlockId.POLISHED_TUFF_STAIRS);
        BlockTypes.PRISMARINE_BRICKS_STAIRS = buildStairs(BlockPrismarineBricksStairsBehavior.class, BlockId.PRISMARINE_BRICKS_STAIRS);
        BlockTypes.PRISMARINE_STAIRS = buildStairs(BlockPrismarineStairsBehavior.class, BlockId.PRISMARINE_STAIRS);
        BlockTypes.PURPUR_STAIRS = buildStairs(BlockPurpurStairsBehavior.class, BlockId.PURPUR_STAIRS);
        BlockTypes.QUARTZ_STAIRS = buildStairs(BlockQuartzStairsBehavior.class, BlockId.QUARTZ_STAIRS);
        BlockTypes.RED_NETHER_BRICK_STAIRS = buildStairs(BlockRedNetherBrickStairsBehavior.class, BlockId.RED_NETHER_BRICK_STAIRS);
        BlockTypes.RED_SANDSTONE_STAIRS = buildStairs(BlockRedSandstoneStairsBehavior.class, BlockId.RED_SANDSTONE_STAIRS);
        BlockTypes.SANDSTONE_STAIRS = buildStairs(BlockSandstoneStairsBehavior.class, BlockId.SANDSTONE_STAIRS);
        BlockTypes.SMOOTH_QUARTZ_STAIRS = buildStairs(BlockSmoothQuartzStairsBehavior.class, BlockId.SMOOTH_QUARTZ_STAIRS);
        BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = buildStairs(BlockSmoothRedSandstoneStairsBehavior.class, BlockId.SMOOTH_RED_SANDSTONE_STAIRS);
        BlockTypes.SMOOTH_SANDSTONE_STAIRS = buildStairs(BlockSmoothSandstoneStairsBehavior.class, BlockId.SMOOTH_SANDSTONE_STAIRS);
        BlockTypes.SPRUCE_STAIRS = buildStairs(BlockSpruceStairsBehavior.class, BlockId.SPRUCE_STAIRS);
        BlockTypes.STONE_BRICK_STAIRS = buildStairs(BlockStoneBrickStairsBehavior.class, BlockId.STONE_BRICK_STAIRS);
        BlockTypes.STONE_STAIRS = buildStairs(BlockStoneStairsBehavior.class, BlockId.STONE_STAIRS);
        BlockTypes.TUFF_BRICK_STAIRS = buildStairs(BlockTuffBrickStairsBehavior.class, BlockId.TUFF_BRICK_STAIRS);
        BlockTypes.TUFF_STAIRS = buildStairs(BlockTuffStairsBehavior.class, BlockId.TUFF_STAIRS);
        BlockTypes.WARPED_STAIRS = buildStairs(BlockWarpedStairsBehavior.class, BlockId.WARPED_STAIRS);
        BlockTypes.WAXED_CUT_COPPER_STAIRS = buildStairs(BlockWaxedCutCopperStairsBehavior.class, BlockId.WAXED_CUT_COPPER_STAIRS);
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = buildStairs(BlockWaxedExposedCutCopperStairsBehavior.class, BlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS);
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = buildStairs(BlockWaxedOxidizedCutCopperStairsBehavior.class, BlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS);
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = buildStairs(BlockWaxedWeatheredCutCopperStairsBehavior.class, BlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS);
        BlockTypes.WEATHERED_CUT_COPPER_STAIRS = buildStairs(BlockWeatheredCutCopperStairsBehavior.class, BlockId.WEATHERED_CUT_COPPER_STAIRS);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStairs(Class<T> clazz, BlockId blockId) {
        return AllayBlockType.builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
    }

    public static void initColoredTorch() {
        BlockTypes.COLORED_TORCH_BP = AllayBlockType
                .builder(BlockColoredTorchBpBehavior.class)
                .vanillaBlock(BlockId.COLORED_TORCH_BP)
                .setProperties(BlockPropertyTypes.COLOR_BIT, BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockColoredTorchBaseComponentImpl::new)
                .build();
        BlockTypes.COLORED_TORCH_RG = AllayBlockType
                .builder(BlockColoredTorchRgBehavior.class)
                .vanillaBlock(BlockId.COLORED_TORCH_RG)
                .setProperties(BlockPropertyTypes.COLOR_BIT, BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockColoredTorchBaseComponentImpl::new)
                .build();
    }

    public static void initTorch() {
        BlockTypes.REDSTONE_TORCH = AllayBlockType
                .builder(BlockRedstoneTorchBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.SOUL_TORCH = AllayBlockType
                .builder(BlockSoulTorchBehavior.class)
                .vanillaBlock(BlockId.SOUL_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.TORCH = AllayBlockType
                .builder(BlockTorchBehavior.class)
                .vanillaBlock(BlockId.TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();

        BlockTypes.UNDERWATER_TORCH = AllayBlockType
                .builder(BlockUnderwaterTorchBehavior.class)
                .vanillaBlock(BlockId.UNDERWATER_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.UNLIT_REDSTONE_TORCH = AllayBlockType
                .builder(BlockUnlitRedstoneTorchBehavior.class)
                .vanillaBlock(BlockId.UNLIT_REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
    }

    public static void initFurnace() {
        BlockTypes.FURNACE = AllayBlockType
                .builder(BlockFurnaceBehavior.class)
                .vanillaBlock(BlockId.FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.FURNACE)
                .build();
        BlockTypes.LIT_FURNACE = AllayBlockType
                .builder(BlockLitFurnaceBehavior.class)
                .vanillaBlock(BlockId.LIT_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.FURNACE)
                .build();
        BlockTypes.BLAST_FURNACE = AllayBlockType
                .builder(BlockBlastFurnaceBehavior.class)
                .vanillaBlock(BlockId.BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.BLAST_FURNACE)
                .build();
        BlockTypes.LIT_BLAST_FURNACE = AllayBlockType
                .builder(BlockLitBlastFurnaceBehavior.class)
                .vanillaBlock(BlockId.LIT_BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.BLAST_FURNACE)
                .build();
        BlockTypes.SMOKER = AllayBlockType
                .builder(BlockSmokerBehavior.class)
                .vanillaBlock(BlockId.SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.SMOKER)
                .build();
        BlockTypes.LIT_SMOKER = AllayBlockType
                .builder(BlockLitSmokerBehavior.class)
                .vanillaBlock(BlockId.LIT_SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .bindBlockEntity(BlockEntityTypes.SMOKER)
                .build();
    }

    public static void initWoods() {
        // Log
        BlockTypes.ACACIA_LOG = buildWood(BlockAcaciaLogBehavior.class, BlockId.ACACIA_LOG, BlockId.STRIPPED_ACACIA_LOG);
        BlockTypes.BAMBOO_BLOCK = buildWood(BlockBambooBlockBehavior.class, BlockId.BAMBOO_BLOCK, BlockId.STRIPPED_BAMBOO_BLOCK);
        BlockTypes.BIRCH_LOG = buildWood(BlockBirchLogBehavior.class, BlockId.BIRCH_LOG, BlockId.STRIPPED_BIRCH_LOG);
        BlockTypes.CHERRY_LOG = buildWood(BlockCherryLogBehavior.class, BlockId.CHERRY_LOG, BlockId.STRIPPED_CHERRY_LOG);
        BlockTypes.CRIMSON_STEM = buildWood(BlockCrimsonStemBehavior.class, BlockId.CRIMSON_STEM, BlockId.STRIPPED_CRIMSON_STEM);
        BlockTypes.DARK_OAK_LOG = buildWood(BlockDarkOakLogBehavior.class, BlockId.DARK_OAK_LOG, BlockId.STRIPPED_OAK_LOG);
        BlockTypes.JUNGLE_LOG = buildWood(BlockJungleLogBehavior.class, BlockId.JUNGLE_LOG, BlockId.STRIPPED_JUNGLE_LOG);
        BlockTypes.MANGROVE_LOG = buildWood(BlockMangroveLogBehavior.class, BlockId.MANGROVE_LOG, BlockId.STRIPPED_MANGROVE_LOG);
        BlockTypes.OAK_LOG = buildWood(BlockOakLogBehavior.class, BlockId.OAK_LOG, BlockId.STRIPPED_OAK_LOG);
        BlockTypes.SPRUCE_LOG = buildWood(BlockSpruceLogBehavior.class, BlockId.SPRUCE_LOG, BlockId.STRIPPED_SPRUCE_LOG);
        BlockTypes.WARPED_STEM = buildWood(BlockWarpedStemBehavior.class, BlockId.WARPED_STEM, BlockId.STRIPPED_WARPED_STEM);
        // Stripped Log
        BlockTypes.STRIPPED_ACACIA_LOG = buildStrippedWood(BlockStrippedAcaciaLogBehavior.class, BlockId.STRIPPED_ACACIA_LOG);
        BlockTypes.STRIPPED_BAMBOO_BLOCK = buildStrippedWood(BlockStrippedBambooBlockBehavior.class, BlockId.STRIPPED_BAMBOO_BLOCK);
        BlockTypes.STRIPPED_BIRCH_LOG = buildStrippedWood(BlockStrippedBirchLogBehavior.class, BlockId.STRIPPED_BIRCH_LOG);
        BlockTypes.STRIPPED_CHERRY_LOG = buildStrippedWood(BlockStrippedCherryLogBehavior.class, BlockId.STRIPPED_CHERRY_LOG);
        BlockTypes.STRIPPED_CRIMSON_STEM = buildStrippedWood(BlockStrippedCrimsonStemBehavior.class, BlockId.STRIPPED_CRIMSON_STEM);
        BlockTypes.STRIPPED_DARK_OAK_LOG = buildStrippedWood(BlockStrippedDarkOakLogBehavior.class, BlockId.STRIPPED_DARK_OAK_LOG);
        BlockTypes.STRIPPED_JUNGLE_LOG = buildStrippedWood(BlockStrippedJungleLogBehavior.class, BlockId.STRIPPED_JUNGLE_LOG);
        BlockTypes.STRIPPED_MANGROVE_LOG = buildStrippedWood(BlockStrippedMangroveLogBehavior.class, BlockId.STRIPPED_MANGROVE_LOG);
        BlockTypes.STRIPPED_OAK_LOG = buildStrippedWood(BlockStrippedOakLogBehavior.class, BlockId.STRIPPED_OAK_LOG);
        BlockTypes.STRIPPED_SPRUCE_LOG = buildStrippedWood(BlockStrippedSpruceLogBehavior.class, BlockId.STRIPPED_SPRUCE_LOG);
        BlockTypes.STRIPPED_WARPED_STEM = buildStrippedWood(BlockStrippedWarpedStemBehavior.class, BlockId.STRIPPED_WARPED_STEM);

        // Wood
        BlockTypes.ACACIA_WOOD = buildWood(BlockAcaciaWoodBehavior.class, BlockId.ACACIA_WOOD, BlockId.STRIPPED_ACACIA_WOOD);
        BlockTypes.BIRCH_WOOD = buildWood(BlockBirchWoodBehavior.class, BlockId.BIRCH_WOOD, BlockId.STRIPPED_BIRCH_WOOD);
        BlockTypes.CRIMSON_HYPHAE = buildWood(BlockCrimsonHyphaeBehavior.class, BlockId.CRIMSON_HYPHAE, BlockId.STRIPPED_CRIMSON_HYPHAE);
        BlockTypes.DARK_OAK_WOOD = buildWood(BlockDarkOakWoodBehavior.class, BlockId.DARK_OAK_WOOD, BlockId.STRIPPED_DARK_OAK_WOOD);
        BlockTypes.JUNGLE_WOOD = buildWood(BlockJungleWoodBehavior.class, BlockId.JUNGLE_WOOD, BlockId.STRIPPED_JUNGLE_WOOD);
        BlockTypes.OAK_WOOD = buildWood(BlockOakWoodBehavior.class, BlockId.OAK_WOOD, BlockId.STRIPPED_OAK_WOOD);
        BlockTypes.SPRUCE_WOOD = buildWood(BlockSpruceWoodBehavior.class, BlockId.SPRUCE_WOOD, BlockId.STRIPPED_SPRUCE_WOOD);
        BlockTypes.WARPED_HYPHAE = buildWood(BlockWarpedHyphaeBehavior.class, BlockId.WARPED_HYPHAE, BlockId.STRIPPED_WARPED_HYPHAE);
        // Stripped Wood
        BlockTypes.STRIPPED_ACACIA_WOOD = buildStrippedWood(BlockStrippedAcaciaWoodBehavior.class, BlockId.STRIPPED_ACACIA_WOOD);
        BlockTypes.STRIPPED_BIRCH_WOOD = buildStrippedWood(BlockStrippedBirchWoodBehavior.class, BlockId.STRIPPED_BIRCH_WOOD);
        BlockTypes.STRIPPED_CRIMSON_HYPHAE = buildStrippedWood(BlockStrippedCrimsonHyphaeBehavior.class, BlockId.STRIPPED_CRIMSON_HYPHAE);
        BlockTypes.STRIPPED_DARK_OAK_WOOD = buildStrippedWood(BlockStrippedDarkOakWoodBehavior.class, BlockId.STRIPPED_DARK_OAK_WOOD);
        BlockTypes.STRIPPED_JUNGLE_WOOD = buildStrippedWood(BlockStrippedJungleWoodBehavior.class, BlockId.STRIPPED_JUNGLE_WOOD);
        BlockTypes.STRIPPED_OAK_WOOD = buildStrippedWood(BlockStrippedOakWoodBehavior.class, BlockId.STRIPPED_OAK_WOOD);
        BlockTypes.STRIPPED_SPRUCE_WOOD = buildStrippedWood(BlockStrippedSpruceWoodBehavior.class, BlockId.STRIPPED_SPRUCE_WOOD);
        BlockTypes.STRIPPED_WARPED_HYPHAE = buildStrippedWood(BlockStrippedWarpedHyphaeBehavior.class, BlockId.STRIPPED_WARPED_HYPHAE);
        BlockTypes.STRIPPED_CHERRY_WOOD = buildStrippedWood(BlockStrippedCherryWoodBehavior.class, BlockId.STRIPPED_CHERRY_WOOD);
        BlockTypes.STRIPPED_MANGROVE_WOOD = buildStrippedWood(BlockStrippedMangroveWoodBehavior.class, BlockId.STRIPPED_MANGROVE_WOOD);

        // Old Wood
        BlockTypes.CHERRY_WOOD = buildOldWood(BlockCherryWoodBehavior.class, BlockId.CHERRY_WOOD, BlockId.STRIPPED_CHERRY_WOOD);
        BlockTypes.MANGROVE_WOOD = buildOldWood(BlockMangroveWoodBehavior.class, BlockId.MANGROVE_WOOD, BlockId.STRIPPED_MANGROVE_WOOD);
    }

    public static void initButtons() {
        BlockTypes.ACACIA_BUTTON = buildButton(BlockAcaciaButtonBehavior.class, BlockId.ACACIA_BUTTON);
        BlockTypes.BAMBOO_BUTTON = buildButton(BlockBambooButtonBehavior.class, BlockId.BAMBOO_BUTTON);
        BlockTypes.BIRCH_BUTTON = buildButton(BlockBirchButtonBehavior.class, BlockId.BIRCH_BUTTON);
        BlockTypes.CHERRY_BUTTON = buildButton(BlockCherryButtonBehavior.class, BlockId.CHERRY_BUTTON);
        BlockTypes.CRIMSON_BUTTON = buildButton(BlockCrimsonButtonBehavior.class, BlockId.CRIMSON_BUTTON);
        BlockTypes.DARK_OAK_BUTTON = buildButton(BlockDarkOakButtonBehavior.class, BlockId.DARK_OAK_BUTTON);
        BlockTypes.JUNGLE_BUTTON = buildButton(BlockJungleButtonBehavior.class, BlockId.JUNGLE_BUTTON);
        BlockTypes.MANGROVE_BUTTON = buildButton(BlockMangroveButtonBehavior.class, BlockId.MANGROVE_BUTTON);
        BlockTypes.POLISHED_BLACKSTONE_BUTTON = buildButton(BlockPolishedBlackstoneButtonBehavior.class, BlockId.POLISHED_BLACKSTONE_BUTTON);
        BlockTypes.SPRUCE_BUTTON = buildButton(BlockSpruceButtonBehavior.class, BlockId.SPRUCE_BUTTON);
        BlockTypes.STONE_BUTTON = buildButton(BlockStoneButtonBehavior.class, BlockId.STONE_BUTTON);
        BlockTypes.WARPED_BUTTON = buildButton(BlockWarpedButtonBehavior.class, BlockId.WARPED_BUTTON);
        BlockTypes.WOODEN_BUTTON = buildButton(BlockWoodenButtonBehavior.class, BlockId.WOODEN_BUTTON);
    }

    private static <T extends BlockBehavior> BlockType<T> buildButton(Class<T> clazz, BlockId blockId) {
        return AllayBlockType.builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.BUTTON_PRESSED_BIT)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildWoodenButton(Class<T> clazz, BlockId blockId) {
        return buildButton(clazz, blockId, BlockWoodenButtonBaseComponentImpl::new);
    }

    private static <T extends BlockBehavior> BlockType<T> buildButton(Class<T> clazz, BlockId blockId, Function<BlockType<T>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    public static void initDoors() {
        BlockTypes.ACACIA_DOOR = buildWoodenDoor(BlockAcaciaDoorBehavior.class, BlockId.ACACIA_DOOR);
        BlockTypes.BAMBOO_DOOR = buildWoodenDoor(BlockBambooDoorBehavior.class, BlockId.BAMBOO_DOOR);
        BlockTypes.BIRCH_DOOR = buildWoodenDoor(BlockBirchDoorBehavior.class, BlockId.BIRCH_DOOR);
        BlockTypes.CHERRY_DOOR = buildWoodenDoor(BlockCherryDoorBehavior.class, BlockId.CHERRY_DOOR);
        BlockTypes.CRIMSON_DOOR = buildWoodenDoor(BlockCrimsonDoorBehavior.class, BlockId.CRIMSON_DOOR);
        BlockTypes.DARK_OAK_DOOR = buildWoodenDoor(BlockDarkOakDoorBehavior.class, BlockId.DARK_OAK_DOOR);
        BlockTypes.JUNGLE_DOOR = buildWoodenDoor(BlockJungleDoorBehavior.class, BlockId.JUNGLE_DOOR);
        BlockTypes.MANGROVE_DOOR = buildWoodenDoor(BlockMangroveDoorBehavior.class, BlockId.MANGROVE_DOOR);
        BlockTypes.WOODEN_DOOR = buildWoodenDoor(BlockWoodenDoorBehavior.class, BlockId.WOODEN_DOOR);
        BlockTypes.SPRUCE_DOOR = buildWoodenDoor(BlockSpruceDoorBehavior.class, BlockId.SPRUCE_DOOR);
        BlockTypes.WARPED_DOOR = buildWoodenDoor(BlockWarpedDoorBehavior.class, BlockId.WARPED_DOOR);
    }

    public static void initRods() {
        BlockTypes.END_ROD = AllayBlockType
                .builder(BlockEndRodBehavior.class)
                .vanillaBlock(BlockId.END_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockRodBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHTNING_ROD = AllayBlockType
                .builder(BlockLightningRodBehavior.class)
                .vanillaBlock(BlockId.LIGHTNING_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockRodBaseComponentImpl::new)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildWood(Class<T> clazz, BlockId blockId, BlockId strippedBlockId) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(blockType -> new BlockWoodBaseComponentImpl(blockType, strippedBlockId))
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildStrippedWood(Class<T> clazz, BlockId blockId) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(blockType -> new BlockWoodBaseComponentImpl(blockType, blockId))
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildOldWood(Class<T> clazz, BlockId blockId, BlockId strippedBlockId) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                // NOTICE: STRIPPED_BIT is deprecated and shouldn't be used!
                .setProperties(BlockPropertyTypes.PILLAR_AXIS, BlockPropertyTypes.STRIPPED_BIT)
                .setBlockBaseComponentSupplier(blockType -> new BlockOldWoodBaseComponentImpl(blockType, strippedBlockId))
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildWoodenDoor(Class<T> clazz, BlockId blockId) {
        return buildDoor(clazz, blockId, BlockDoorBaseComponentImpl::new);
    }

    private static <T extends BlockBehavior> BlockType<T> buildDoor(Class<T> clazz, BlockId blockId, Function<BlockType<T>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    public static void initWallSigns() {
        BlockTypes.WALL_SIGN = buildWallSign(BlockWallSignBehavior.class, BlockId.WALL_SIGN, ItemTypes.OAK_SIGN);
        BlockTypes.ACACIA_WALL_SIGN = buildWallSign(BlockAcaciaWallSignBehavior.class, BlockId.ACACIA_WALL_SIGN, ItemTypes.ACACIA_SIGN);
        BlockTypes.BAMBOO_WALL_SIGN = buildWallSign(BlockBambooWallSignBehavior.class, BlockId.BAMBOO_WALL_SIGN, ItemTypes.BAMBOO_SIGN);
        BlockTypes.BIRCH_WALL_SIGN = buildWallSign(BlockBirchWallSignBehavior.class, BlockId.BIRCH_WALL_SIGN, ItemTypes.BIRCH_SIGN);
        BlockTypes.CHERRY_WALL_SIGN = buildWallSign(BlockCherryWallSignBehavior.class, BlockId.CHERRY_WALL_SIGN, ItemTypes.CHERRY_SIGN);
        BlockTypes.CRIMSON_WALL_SIGN = buildWallSign(BlockCrimsonWallSignBehavior.class, BlockId.CRIMSON_WALL_SIGN, ItemTypes.CRIMSON_SIGN);
        BlockTypes.DARKOAK_WALL_SIGN = buildWallSign(BlockDarkoakWallSignBehavior.class, BlockId.DARKOAK_WALL_SIGN, ItemTypes.DARK_OAK_SIGN);
        BlockTypes.JUNGLE_WALL_SIGN = buildWallSign(BlockJungleWallSignBehavior.class, BlockId.JUNGLE_WALL_SIGN, ItemTypes.JUNGLE_SIGN);
        BlockTypes.MANGROVE_WALL_SIGN = buildWallSign(BlockMangroveWallSignBehavior.class, BlockId.MANGROVE_WALL_SIGN, ItemTypes.MANGROVE_SIGN);
        BlockTypes.SPRUCE_WALL_SIGN = buildWallSign(BlockSpruceWallSignBehavior.class, BlockId.SPRUCE_WALL_SIGN, ItemTypes.SPRUCE_SIGN);
        BlockTypes.WARPED_WALL_SIGN = buildWallSign(BlockWarpedWallSignBehavior.class, BlockId.WARPED_WALL_SIGN, ItemTypes.WARPED_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildWallSign(Class<T> clazz, BlockId blockId, ItemType<?> dropItemType) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(blockType -> new BlockWallSignBaseComponentImpl(blockType, dropItemType))
                .bindBlockEntity(BlockEntityTypes.SIGN)
                .build();
    }

    public static void initStandingSigns() {
        BlockTypes.STANDING_SIGN = buildStandingSign(BlockStandingSignBehavior.class, BlockId.STANDING_SIGN, ItemTypes.OAK_SIGN);
        BlockTypes.ACACIA_STANDING_SIGN = buildStandingSign(BlockAcaciaStandingSignBehavior.class, BlockId.ACACIA_STANDING_SIGN, ItemTypes.ACACIA_SIGN);
        BlockTypes.BAMBOO_STANDING_SIGN = buildStandingSign(BlockBambooStandingSignBehavior.class, BlockId.BAMBOO_STANDING_SIGN, ItemTypes.BAMBOO_SIGN);
        BlockTypes.BIRCH_STANDING_SIGN = buildStandingSign(BlockBirchStandingSignBehavior.class, BlockId.BIRCH_STANDING_SIGN, ItemTypes.BIRCH_SIGN);
        BlockTypes.CHERRY_STANDING_SIGN = buildStandingSign(BlockCherryStandingSignBehavior.class, BlockId.CHERRY_STANDING_SIGN, ItemTypes.CHERRY_SIGN);
        BlockTypes.CRIMSON_STANDING_SIGN = buildStandingSign(BlockCrimsonStandingSignBehavior.class, BlockId.CRIMSON_STANDING_SIGN, ItemTypes.CRIMSON_SIGN);
        BlockTypes.DARKOAK_STANDING_SIGN = buildStandingSign(BlockDarkoakStandingSignBehavior.class, BlockId.DARKOAK_STANDING_SIGN, ItemTypes.DARK_OAK_SIGN);
        BlockTypes.JUNGLE_STANDING_SIGN = buildStandingSign(BlockJungleStandingSignBehavior.class, BlockId.JUNGLE_STANDING_SIGN, ItemTypes.JUNGLE_SIGN);
        BlockTypes.MANGROVE_STANDING_SIGN = buildStandingSign(BlockMangroveStandingSignBehavior.class, BlockId.MANGROVE_STANDING_SIGN, ItemTypes.MANGROVE_SIGN);
        BlockTypes.SPRUCE_STANDING_SIGN = buildStandingSign(BlockSpruceStandingSignBehavior.class, BlockId.SPRUCE_STANDING_SIGN, ItemTypes.SPRUCE_SIGN);
        BlockTypes.WARPED_STANDING_SIGN = buildStandingSign(BlockWarpedStandingSignBehavior.class, BlockId.WARPED_STANDING_SIGN, ItemTypes.WARPED_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStandingSign(Class<T> clazz, BlockId blockId, ItemType<?> dropItemType) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .setBlockBaseComponentSupplier(blockType -> new BlockStandingSignBaseComponentImpl(blockType, dropItemType))
                .bindBlockEntity(BlockEntityTypes.SIGN)
                .build();
    }

    public static void initHangingSigns() {
        BlockTypes.ACACIA_HANGING_SIGN = buildHangingSign(BlockAcaciaHangingSignBehavior.class, BlockId.ACACIA_HANGING_SIGN);
        BlockTypes.BAMBOO_HANGING_SIGN = buildHangingSign(BlockBambooHangingSignBehavior.class, BlockId.BAMBOO_HANGING_SIGN);
        BlockTypes.BIRCH_HANGING_SIGN = buildHangingSign(BlockBirchHangingSignBehavior.class, BlockId.BIRCH_HANGING_SIGN);
        BlockTypes.CHERRY_HANGING_SIGN = buildHangingSign(BlockCherryHangingSignBehavior.class, BlockId.CHERRY_HANGING_SIGN);
        BlockTypes.CRIMSON_HANGING_SIGN = buildHangingSign(BlockCrimsonHangingSignBehavior.class, BlockId.CRIMSON_HANGING_SIGN);
        BlockTypes.DARK_OAK_HANGING_SIGN = buildHangingSign(BlockDarkOakHangingSignBehavior.class, BlockId.DARK_OAK_HANGING_SIGN);
        BlockTypes.JUNGLE_HANGING_SIGN = buildHangingSign(BlockJungleHangingSignBehavior.class, BlockId.JUNGLE_HANGING_SIGN);
        BlockTypes.MANGROVE_HANGING_SIGN = buildHangingSign(BlockMangroveHangingSignBehavior.class, BlockId.MANGROVE_HANGING_SIGN);
        BlockTypes.OAK_HANGING_SIGN = buildHangingSign(BlockOakHangingSignBehavior.class, BlockId.OAK_HANGING_SIGN);
        BlockTypes.SPRUCE_HANGING_SIGN = buildHangingSign(BlockSpruceHangingSignBehavior.class, BlockId.SPRUCE_HANGING_SIGN);
        BlockTypes.WARPED_HANGING_SIGN = buildHangingSign(BlockWarpedHangingSignBehavior.class, BlockId.WARPED_HANGING_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildHangingSign(Class<T> clazz, BlockId blockId) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .setBlockBaseComponentSupplier(BlockHangingSignBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.HANGING_SIGN)
                .build();
    }

    public static void initSlimeBlock() {
        BlockTypes.SLIME = AllayBlockType
                .builder(BlockSlimeBehavior.class)
                .vanillaBlock(BlockId.SLIME)
                .setBlockBaseComponentSupplier(BlockSlimeBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedDamageReductionFactor(blockState -> 1.0f))
                .build();
    }

    public static void initHayBlock() {
        BlockTypes.HAY_BLOCK = AllayBlockType
                .builder(BlockHayBlockBehavior.class)
                .vanillaBlock(BlockId.HAY_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedDamageReductionFactor(blockState -> 0.8f))
                .build();
    }

    public static void initWater() {
        BlockTypes.WATER = AllayBlockType
                .builder(BlockWaterBehavior.class)
                .vanillaBlock(BlockId.WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCanResetFallDistance(blockState -> true))
                .build();
        BlockTypes.FLOWING_WATER = AllayBlockType
                .builder(BlockFlowingWaterBehavior.class)
                .vanillaBlock(BlockId.FLOWING_WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCanResetFallDistance(blockState -> true))
                .build();
    }

    public static void initLava() {
        BlockTypes.LAVA = AllayBlockType
                .builder(BlockLavaBehavior.class)
                .vanillaBlock(BlockId.LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCanResetFallDistance(blockState -> true))
                .build();
        BlockTypes.FLOWING_LAVA = AllayBlockType
                .builder(BlockFlowingLavaBehavior.class)
                .vanillaBlock(BlockId.FLOWING_LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCanResetFallDistance(blockState -> true))
                .build();
    }
}
