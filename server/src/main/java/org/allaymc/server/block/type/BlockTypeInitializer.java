package org.allaymc.server.block.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.data.BlockId;
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
import org.allaymc.server.block.impl.*;

import java.time.Duration;
import java.util.function.Function;

/**
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class BlockTypeInitializer {
    public static void initJukebox() {
        BlockTypes.JUKEBOX = AllayBlockType
                .builder(BlockJukeboxBehaviorImpl.class)
                .vanillaBlock(BlockId.JUKEBOX)
                .bindBlockEntity(BlockEntityTypes.JUKEBOX)
                .setBlockBaseComponentSupplier(BlockJukeboxBaseComponentImpl::new)
                .build();
    }

    public static void initLeaves() {
        BlockTypes.ACACIA_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.AZALEA_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.AZALEA_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.AZALEA_LEAVES_FLOWERED = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.AZALEA_LEAVES_FLOWERED)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.BIRCH_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.CHERRY_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_OAK_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.JUNGLE_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.MANGROVE_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.OAK_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.SPRUCE_LEAVES = AllayBlockType
                .builder(BlockLeavesBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
    }

    public static void initFallable() {
        BlockTypes.GRAVEL = AllayBlockType
                .builder(BlockGravelBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAVEL)
                .setBlockBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
        BlockTypes.SAND = AllayBlockType
                .builder(BlockSandBehaviorImpl.class)
                .vanillaBlock(BlockId.SAND)
                .setBlockBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SAND = AllayBlockType
                .builder(BlockRedSandBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SAND)
                .setBlockBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
    }

    public static void initShulkerBox() {
        BlockTypes.YELLOW_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.YELLOW_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.WHITE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.WHITE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.UNDYED_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.UNDYED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PURPLE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPLE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PINK_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.PINK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.ORANGE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.ORANGE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.MAGENTA_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.MAGENTA_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIME_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIME_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GREEN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.GREEN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GRAY_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.CYAN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.CYAN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BROWN_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BROWN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLUE_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLACK_SHULKER_BOX = AllayBlockType
                .builder(BlockShulkerBoxBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
    }

    public static void initGrassBlock() {
        BlockTypes.GRASS_BLOCK = AllayBlockType
                .builder(BlockGrassBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.GRASS_BLOCK)
                .setBlockBaseComponentSupplier(BlockGrassBlockBaseComponentImpl::new)
                .build();
    }

    public static void initShortGrass() {
        BlockTypes.SHORT_GRASS = AllayBlockType
                .builder(BlockShortGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.SHORT_GRASS)
                .setBlockBaseComponentSupplier(BlockShortGrassBaseComponentImpl::new)
                .build();
        BlockTypes.FERN = AllayBlockType
                .builder(BlockFernBehaviorImpl.class)
                .vanillaBlock(BlockId.FERN)
                .setBlockBaseComponentSupplier(BlockShortGrassBaseComponentImpl::new)
                .build();
    }

    public static void initTallGrass() {
        BlockTypes.TALL_GRASS = AllayBlockType
                .builder(BlockTallGrassBehaviorImpl.class)
                .vanillaBlock(BlockId.TALL_GRASS)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(BlockTallGrassBaseComponentImpl::new)
                .build();
        BlockTypes.LARGE_FERN = AllayBlockType
                .builder(BlockLargeFernBehaviorImpl.class)
                .vanillaBlock(BlockId.LARGE_FERN)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(BlockTallGrassBaseComponentImpl::new)
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
                .setBlockBaseComponentSupplier(BlockCraftingTableBaseComponentImpl::new)
                .build();
    }

    public static void initStairs() {
        BlockTypes.ACACIA_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.ACACIA_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.ANDESITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.ANDESITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BAMBOO_MOSAIC_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BAMBOO_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.BAMBOO_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BIRCH_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.BIRCH_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BLACKSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.BLACKSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CHERRY_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.CHERRY_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.COBBLED_DEEPSLATE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CRIMSON_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.CRIMSON_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_OAK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_OAK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_PRISMARINE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DEEPSLATE_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DEEPSLATE_TILE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DIORITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.DIORITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.END_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.END_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.EXPOSED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.GRANITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.GRANITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.JUNGLE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.JUNGLE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MANGROVE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.MANGROVE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MOSSY_COBBLESTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MOSSY_STONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MUD_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.MUD_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.NETHER_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.NETHER_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.NORMAL_STONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.NORMAL_STONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.OAK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.OAK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_ANDESITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_BLACKSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_DEEPSLATE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_DIORITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_GRANITE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_TUFF_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PRISMARINE_BRICKS_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICKS_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PRISMARINE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.PRISMARINE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PURPUR_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.PURPUR_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.QUARTZ_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.QUARTZ_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.RED_NETHER_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_QUARTZ_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_SANDSTONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SPRUCE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.SPRUCE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.STONE_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.STONE_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.STONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.TUFF_BRICK_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.TUFF_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.TUFF_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WARPED_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WARPED_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WEATHERED_CUT_COPPER_STAIRS = AllayBlockType
                .builder(BlockStairsBehaviorImpl.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedCollisionShape(VoxelShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
    }

    public static void initColoredTorch() {
        BlockTypes.COLORED_TORCH_RED = buildColoredTorch(BlockId.COLORED_TORCH_RED);
        BlockTypes.COLORED_TORCH_BLUE = buildColoredTorch(BlockId.COLORED_TORCH_BLUE);
        BlockTypes.COLORED_TORCH_GREEN = buildColoredTorch(BlockId.COLORED_TORCH_GREEN);
        BlockTypes.COLORED_TORCH_PURPLE = buildColoredTorch(BlockId.COLORED_TORCH_PURPLE);
    }

    private static <T extends BlockBehavior> BlockType<T> buildColoredTorch(BlockId blockId) {
        return AllayBlockType
                .builder((Class<? extends BlockBehaviorImpl>) BlockColoredTorchBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockColoredTorchBaseComponentImpl::new)
                .build();
    }

    public static void initTorch() {
        BlockTypes.REDSTONE_TORCH = AllayBlockType
                .builder(BlockRedstoneTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.UNLIT_REDSTONE_TORCH = AllayBlockType
                .builder(BlockRedstoneTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.UNLIT_REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();

        BlockTypes.SOUL_TORCH = AllayBlockType
                .builder(BlockSoulTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.SOUL_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.TORCH = AllayBlockType
                .builder(BlockTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();

        BlockTypes.UNDERWATER_TORCH = AllayBlockType
                .builder(BlockUnderwaterTorchBehaviorImpl.class)
                .vanillaBlock(BlockId.UNDERWATER_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
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
        // TODO: Replace BlockDoorBaseComponentImpl::new
        BlockTypes.IRON_DOOR = buildIronDoor(BlockId.IRON_DOOR);
        BlockTypes.COPPER_DOOR = buildCopperDoor(BlockId.COPPER_DOOR);
        BlockTypes.EXPOSED_COPPER_DOOR = buildCopperDoor(BlockId.EXPOSED_COPPER_DOOR);
        BlockTypes.OXIDIZED_COPPER_DOOR = buildCopperDoor(BlockId.OXIDIZED_COPPER_DOOR);
        BlockTypes.WAXED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_COPPER_DOOR);
        BlockTypes.WAXED_EXPOSED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_EXPOSED_COPPER_DOOR);
        BlockTypes.WAXED_OXIDIZED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_OXIDIZED_COPPER_DOOR);
        BlockTypes.WAXED_WEATHERED_COPPER_DOOR = buildCopperDoor(BlockId.WAXED_WEATHERED_COPPER_DOOR);
        BlockTypes.WEATHERED_COPPER_DOOR = buildCopperDoor(BlockId.WEATHERED_COPPER_DOOR);
    }

    public static void initRods() {
        BlockTypes.END_ROD = AllayBlockType
                .builder(BlockEndRodBehaviorImpl.class)
                .vanillaBlock(BlockId.END_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockRodBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHTNING_ROD = AllayBlockType
                .builder(BlockLightningRodBehaviorImpl.class)
                .vanillaBlock(BlockId.LIGHTNING_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockRodBaseComponentImpl::new)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildWood(BlockId blockId, BlockId strippedBlockId) {
        return AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(blockType -> new BlockWoodBaseComponentImpl(blockType, strippedBlockId))
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildStrippedWood(BlockId blockId) {
        return AllayBlockType
                .builder(BlockWoodBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(blockType -> new BlockWoodBaseComponentImpl(blockType, blockId))
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
                .setBlockBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildDoor(BlockId blockId) {
        return buildDoor0(blockId, BlockDoorBehaviorImpl.class, BlockDoorBaseComponentImpl::new);
    }

    private static <T extends BlockBehavior> BlockType<T> buildIronDoor(BlockId blockId) {
        // TODO: replace BlockDoorBaseComponentImpl::new
        return buildDoor0(blockId, BlockIronDoorBehaviorImpl.class, BlockDoorBaseComponentImpl::new);
    }

    private static <T extends BlockBehavior> BlockType<T> buildCopperDoor(BlockId blockId) {
        // TODO: replace BlockDoorBaseComponentImpl::new
        return buildDoor0(blockId, BlockCopperDoorBehaviorImpl.class, BlockDoorBaseComponentImpl::new);
    }

    private static <T extends BlockBehavior> BlockType<T> buildDoor0(BlockId blockId, Class<? extends BlockBehavior> clazz, Function<BlockType<?>, BlockBaseComponent> blockBaseComponentSupplier) {
        return AllayBlockType
                .builder(clazz)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(blockBaseComponentSupplier)
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
    }

    private static <T extends BlockBehavior> BlockType<T> buildWallSign(BlockId blockId, ItemType<?> dropItemType) {
        return AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(blockType -> new BlockWallSignBaseComponentImpl(blockType, dropItemType))
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
    }

    private static <T extends BlockBehavior> BlockType<T> buildStandingSign(BlockId blockId, ItemType<?> dropItemType) {
        return AllayBlockType
                .builder(BlockSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .setBlockBaseComponentSupplier(blockType -> new BlockStandingSignBaseComponentImpl(blockType, dropItemType))
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
    }

    private static <T extends BlockBehavior> BlockType<T> buildHangingSign(BlockId blockId) {
        return AllayBlockType
                .builder(BlockHangingSignBehaviorImpl.class)
                .vanillaBlock(blockId)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .setBlockBaseComponentSupplier(BlockHangingSignBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.HANGING_SIGN)
                .build();
    }

    public static void initSlimeBlock() {
        BlockTypes.SLIME = AllayBlockType
                .builder(BlockSlimeBehaviorImpl.class)
                .vanillaBlock(BlockId.SLIME)
                .setBlockBaseComponentSupplier(BlockSlimeBaseComponentImpl::new)
                .build();
    }

    public static void initHayBlock() {
        BlockTypes.HAY_BLOCK = AllayBlockType
                .builder(BlockHayBlockBehaviorImpl.class)
                .vanillaBlock(BlockId.HAY_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(BlockHayBlockBaseComponentImpl::new)
                .build();
    }

    public static void initWater() {
        BlockTypes.WATER = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBlockBaseComponentSupplier(BlockWaterBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(VoxelShapes::buildLiquidShape))
                .build();
        BlockTypes.FLOWING_WATER = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWING_WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBlockBaseComponentSupplier(BlockLiquidBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(VoxelShapes::buildLiquidShape))
                .build();
    }

    public static void initLava() {
        BlockTypes.LAVA = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBlockBaseComponentSupplier(BlockLiquidBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(VoxelShapes::buildLiquidShape))
                .build();
        BlockTypes.FLOWING_LAVA = AllayBlockType
                .builder(BlockLiquidBehaviorImpl.class)
                .vanillaBlock(BlockId.FLOWING_LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .setBlockBaseComponentSupplier(BlockLiquidBaseComponentImpl::new)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedShape(VoxelShapes::buildLiquidShape))
                .build();
    }

    public static void initEnchantingTable() {
        BlockTypes.ENCHANTING_TABLE = AllayBlockType
                .builder(BlockEnchantingTableBehaviorImpl.class)
                .vanillaBlock(BlockId.ENCHANTING_TABLE)
                .bindBlockEntity(BlockEntityTypes.ENCHANT_TABLE)
                .setBlockBaseComponentSupplier(BlockEnchantingTableBaseComponentImpl::new)
                .build();
    }
}
