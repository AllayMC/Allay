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
import org.allaymc.server.block.component.button.BlockButtonBaseComponentImpl;
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

import java.time.Duration;
import java.util.function.Function;

/**
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class BlockTypeInitializer {
    public static void initLeaves() {
        BlockTypes.ACACIA_LEAVES = AllayBlockType
                .builder(BlockAcaciaLeavesBehavior.class)
                .vanillaBlock(BlockId.ACACIA_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.AZALEA_LEAVES = AllayBlockType
                .builder(BlockAzaleaLeavesBehavior.class)
                .vanillaBlock(BlockId.AZALEA_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.BIRCH_LEAVES = AllayBlockType
                .builder(BlockBirchLeavesBehavior.class)
                .vanillaBlock(BlockId.BIRCH_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.CHERRY_LEAVES = AllayBlockType
                .builder(BlockCherryLeavesBehavior.class)
                .vanillaBlock(BlockId.CHERRY_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_OAK_LEAVES = AllayBlockType
                .builder(BlockDarkOakLeavesBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.JUNGLE_LEAVES = AllayBlockType
                .builder(BlockJungleLeavesBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.MANGROVE_LEAVES = AllayBlockType
                .builder(BlockMangroveLeavesBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.OAK_LEAVES = AllayBlockType
                .builder(BlockOakLeavesBehavior.class)
                .vanillaBlock(BlockId.OAK_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.SPRUCE_LEAVES = AllayBlockType
                .builder(BlockSpruceLeavesBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_LEAVES)
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

    public static void initShulkerBox() {
        BlockTypes.YELLOW_SHULKER_BOX = AllayBlockType
                .builder(BlockYellowShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.YELLOW_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.WHITE_SHULKER_BOX = AllayBlockType
                .builder(BlockWhiteShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.WHITE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.UNDYED_SHULKER_BOX = AllayBlockType
                .builder(BlockUndyedShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.UNDYED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SHULKER_BOX = AllayBlockType
                .builder(BlockRedShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.RED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PURPLE_SHULKER_BOX = AllayBlockType
                .builder(BlockPurpleShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.PURPLE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PINK_SHULKER_BOX = AllayBlockType
                .builder(BlockPinkShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.PINK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.ORANGE_SHULKER_BOX = AllayBlockType
                .builder(BlockOrangeShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.ORANGE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.MAGENTA_SHULKER_BOX = AllayBlockType
                .builder(BlockMagentaShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIME_SHULKER_BOX = AllayBlockType
                .builder(BlockLimeShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.LIME_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockLightGrayShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockLightBlueShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GREEN_SHULKER_BOX = AllayBlockType
                .builder(BlockGreenShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.GREEN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockGrayShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.CYAN_SHULKER_BOX = AllayBlockType
                .builder(BlockCyanShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.CYAN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BROWN_SHULKER_BOX = AllayBlockType
                .builder(BlockBrownShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.BROWN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockBlueShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLACK_SHULKER_BOX = AllayBlockType
                .builder(BlockBlackShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.BLACK_SHULKER_BOX)
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
        BlockTypes.ACACIA_STAIRS = AllayBlockType
                .builder(BlockAcaciaStairsBehavior.class)
                .vanillaBlock(BlockId.ACACIA_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.ANDESITE_STAIRS = AllayBlockType
                .builder(BlockAndesiteStairsBehavior.class)
                .vanillaBlock(BlockId.ANDESITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BAMBOO_MOSAIC_STAIRS = AllayBlockType
                .builder(BlockBambooMosaicStairsBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BAMBOO_STAIRS = AllayBlockType
                .builder(BlockBambooStairsBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BIRCH_STAIRS = AllayBlockType
                .builder(BlockBirchStairsBehavior.class)
                .vanillaBlock(BlockId.BIRCH_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BLACKSTONE_STAIRS = AllayBlockType
                .builder(BlockBlackstoneStairsBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BRICK_STAIRS = AllayBlockType
                .builder(BlockBrickStairsBehavior.class)
                .vanillaBlock(BlockId.BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CHERRY_STAIRS = AllayBlockType
                .builder(BlockCherryStairsBehavior.class)
                .vanillaBlock(BlockId.CHERRY_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.COBBLED_DEEPSLATE_STAIRS = AllayBlockType
                .builder(BlockCobbledDeepslateStairsBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CRIMSON_STAIRS = AllayBlockType
                .builder(BlockCrimsonStairsBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_OAK_STAIRS = AllayBlockType
                .builder(BlockDarkOakStairsBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_PRISMARINE_STAIRS = AllayBlockType
                .builder(BlockDarkPrismarineStairsBehavior.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DEEPSLATE_BRICK_STAIRS = AllayBlockType
                .builder(BlockDeepslateBrickStairsBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DEEPSLATE_TILE_STAIRS = AllayBlockType
                .builder(BlockDeepslateTileStairsBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DIORITE_STAIRS = AllayBlockType
                .builder(BlockDioriteStairsBehavior.class)
                .vanillaBlock(BlockId.DIORITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.END_BRICK_STAIRS = AllayBlockType
                .builder(BlockEndBrickStairsBehavior.class)
                .vanillaBlock(BlockId.END_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.EXPOSED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockExposedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.GRANITE_STAIRS = AllayBlockType
                .builder(BlockGraniteStairsBehavior.class)
                .vanillaBlock(BlockId.GRANITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.JUNGLE_STAIRS = AllayBlockType
                .builder(BlockJungleStairsBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MANGROVE_STAIRS = AllayBlockType
                .builder(BlockMangroveStairsBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MOSSY_COBBLESTONE_STAIRS = AllayBlockType
                .builder(BlockMossyCobblestoneStairsBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MOSSY_STONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockMossyStoneBrickStairsBehavior.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MUD_BRICK_STAIRS = AllayBlockType
                .builder(BlockMudBrickStairsBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.NETHER_BRICK_STAIRS = AllayBlockType
                .builder(BlockNetherBrickStairsBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.NORMAL_STONE_STAIRS = AllayBlockType
                .builder(BlockNormalStoneStairsBehavior.class)
                .vanillaBlock(BlockId.NORMAL_STONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.OAK_STAIRS = AllayBlockType
                .builder(BlockOakStairsBehavior.class)
                .vanillaBlock(BlockId.OAK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockOxidizedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_ANDESITE_STAIRS = AllayBlockType
                .builder(BlockPolishedAndesiteStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockPolishedBlackstoneBrickStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_BLACKSTONE_STAIRS = AllayBlockType
                .builder(BlockPolishedBlackstoneStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_DEEPSLATE_STAIRS = AllayBlockType
                .builder(BlockPolishedDeepslateStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_DIORITE_STAIRS = AllayBlockType
                .builder(BlockPolishedDioriteStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_GRANITE_STAIRS = AllayBlockType
                .builder(BlockPolishedGraniteStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_TUFF_STAIRS = AllayBlockType
                .builder(BlockPolishedTuffStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PRISMARINE_BRICKS_STAIRS = AllayBlockType
                .builder(BlockPrismarineBricksStairsBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICKS_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PRISMARINE_STAIRS = AllayBlockType
                .builder(BlockPrismarineStairsBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PURPUR_STAIRS = AllayBlockType
                .builder(BlockPurpurStairsBehavior.class)
                .vanillaBlock(BlockId.PURPUR_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.QUARTZ_STAIRS = AllayBlockType
                .builder(BlockQuartzStairsBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.RED_NETHER_BRICK_STAIRS = AllayBlockType
                .builder(BlockRedNetherBrickStairsBehavior.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockRedSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_QUARTZ_STAIRS = AllayBlockType
                .builder(BlockSmoothQuartzStairsBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockSmoothRedSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockSmoothSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SPRUCE_STAIRS = AllayBlockType
                .builder(BlockSpruceStairsBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.STONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockStoneBrickStairsBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.STONE_STAIRS = AllayBlockType
                .builder(BlockStoneStairsBehavior.class)
                .vanillaBlock(BlockId.STONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.TUFF_BRICK_STAIRS = AllayBlockType
                .builder(BlockTuffBrickStairsBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.TUFF_STAIRS = AllayBlockType
                .builder(BlockTuffStairsBehavior.class)
                .vanillaBlock(BlockId.TUFF_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WARPED_STAIRS = AllayBlockType
                .builder(BlockWarpedStairsBehavior.class)
                .vanillaBlock(BlockId.WARPED_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockWaxedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockWaxedExposedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockWaxedOxidizedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockWaxedWeatheredCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WEATHERED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockWeatheredCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
    }

    public static void initColoredTorch() {
        BlockTypes.COLORED_TORCH_RED = buildColoredTorch(BlockColoredTorchRedBehavior.class, BlockId.COLORED_TORCH_RED);
        BlockTypes.COLORED_TORCH_BLUE = buildColoredTorch(BlockColoredTorchBlueBehavior.class, BlockId.COLORED_TORCH_BLUE);
        BlockTypes.COLORED_TORCH_GREEN = buildColoredTorch(BlockColoredTorchGreenBehavior.class, BlockId.COLORED_TORCH_GREEN);
        BlockTypes.COLORED_TORCH_PURPLE = buildColoredTorch(BlockColoredTorchPurpleBehavior.class, BlockId.COLORED_TORCH_PURPLE);
    }

    private static <T extends BlockBehavior> BlockType<T> buildColoredTorch(Class<T> clazz, BlockId blockId) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
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
        BlockTypes.ACACIA_BUTTON = buildWoodenButton(BlockAcaciaButtonBehavior.class, BlockId.ACACIA_BUTTON);
        BlockTypes.BAMBOO_BUTTON = buildWoodenButton(BlockBambooButtonBehavior.class, BlockId.BAMBOO_BUTTON);
        BlockTypes.BIRCH_BUTTON = buildWoodenButton(BlockBirchButtonBehavior.class, BlockId.BIRCH_BUTTON);
        BlockTypes.CHERRY_BUTTON = buildWoodenButton(BlockCherryButtonBehavior.class, BlockId.CHERRY_BUTTON);
        BlockTypes.CRIMSON_BUTTON = buildWoodenButton(BlockCrimsonButtonBehavior.class, BlockId.CRIMSON_BUTTON);
        BlockTypes.DARK_OAK_BUTTON = buildWoodenButton(BlockDarkOakButtonBehavior.class, BlockId.DARK_OAK_BUTTON);
        BlockTypes.JUNGLE_BUTTON = buildWoodenButton(BlockJungleButtonBehavior.class, BlockId.JUNGLE_BUTTON);
        BlockTypes.MANGROVE_BUTTON = buildWoodenButton(BlockMangroveButtonBehavior.class, BlockId.MANGROVE_BUTTON);
        BlockTypes.SPRUCE_BUTTON = buildWoodenButton(BlockSpruceButtonBehavior.class, BlockId.SPRUCE_BUTTON);
        BlockTypes.WARPED_BUTTON = buildWoodenButton(BlockWarpedButtonBehavior.class, BlockId.WARPED_BUTTON);
        BlockTypes.WOODEN_BUTTON = buildWoodenButton(BlockWoodenButtonBehavior.class, BlockId.WOODEN_BUTTON);

        BlockTypes.POLISHED_BLACKSTONE_BUTTON = buildButton(BlockPolishedBlackstoneButtonBehavior.class, BlockId.POLISHED_BLACKSTONE_BUTTON, blockType -> new BlockButtonBaseComponentImpl(blockType, Duration.ofSeconds(1)));
        BlockTypes.STONE_BUTTON = buildButton(BlockStoneButtonBehavior.class, BlockId.STONE_BUTTON, blockType -> new BlockButtonBaseComponentImpl(blockType, Duration.ofSeconds(1)));
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
                .build();
    }

    public static void initWater() {
        BlockTypes.WATER = AllayBlockType
                .builder(BlockWaterBehavior.class)
                .vanillaBlock(BlockId.WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .addComponent(new BlockLiquidComponentImpl())
                .build();
        BlockTypes.FLOWING_WATER = AllayBlockType
                .builder(BlockFlowingWaterBehavior.class)
                .vanillaBlock(BlockId.FLOWING_WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .addComponent(new BlockLiquidComponentImpl())
                .build();
    }

    public static void initLava() {
        BlockTypes.LAVA = AllayBlockType
                .builder(BlockLavaBehavior.class)
                .vanillaBlock(BlockId.LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .addComponent(new BlockLiquidComponentImpl())
                .build();
        BlockTypes.FLOWING_LAVA = AllayBlockType
                .builder(BlockFlowingLavaBehavior.class)
                .vanillaBlock(BlockId.FLOWING_LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .addComponent(new BlockLiquidComponentImpl())
                .build();
    }
}
