package org.allaymc.server.block.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.BlockBaseComponent;
import org.allaymc.api.block.interfaces.*;
import org.allaymc.api.block.interfaces.button.*;
import org.allaymc.api.block.interfaces.door.*;
import org.allaymc.api.block.interfaces.hangingsign.*;
import org.allaymc.api.block.interfaces.leaves.*;
import org.allaymc.api.block.interfaces.log.*;
import org.allaymc.api.block.interfaces.shulkerbox.*;
import org.allaymc.api.block.interfaces.stairs.*;
import org.allaymc.api.block.interfaces.standingsign.*;
import org.allaymc.api.block.interfaces.terracotta.*;
import org.allaymc.api.block.interfaces.torch.*;
import org.allaymc.api.block.interfaces.wallsign.*;
import org.allaymc.api.block.interfaces.wood.*;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.voxelshape.CommonShapes;
import org.allaymc.server.block.component.*;
import org.allaymc.server.block.component.button.BlockButtonBaseComponentImpl;
import org.allaymc.server.block.component.button.BlockWoodenButtonBaseComponentImpl;
import org.allaymc.server.block.component.common.BlockFallableBaseComponentImpl;
import org.allaymc.server.block.component.common.BlockShortGrassBaseComponentImpl;
import org.allaymc.server.block.component.common.BlockStateDataComponentImpl;
import org.allaymc.server.block.component.common.BlockTallGrassBaseComponentImpl;
import org.allaymc.server.block.component.door.BlockDoorBaseComponentImpl;
import org.allaymc.server.block.component.facing.BlockCardinalDirectionComponentImpl;
import org.allaymc.server.block.component.facing.BlockPillarAxisLikeComponentImpl;
import org.allaymc.server.block.component.sign.BlockHangingSignBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockStandingSignBaseComponentImpl;
import org.allaymc.server.block.component.sign.BlockWallSignBaseComponentImpl;
import org.allaymc.server.block.component.torch.BlockColoredTorchBaseComponentImpl;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;

import java.time.Duration;
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
        BlockTypes.ACACIA_LEAVES = BlockTypeBuilder
                .builder(BlockAcaciaLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.ACACIA_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.AZALEA_LEAVES = BlockTypeBuilder
                .builder(BlockAzaleaLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.AZALEA_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.BIRCH_LEAVES = BlockTypeBuilder
                .builder(BlockBirchLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.BIRCH_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.CHERRY_LEAVES = BlockTypeBuilder
                .builder(BlockCherryLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.CHERRY_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_OAK_LEAVES = BlockTypeBuilder
                .builder(BlockDarkOakLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.DARK_OAK_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.JUNGLE_LEAVES = BlockTypeBuilder
                .builder(BlockJungleLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.JUNGLE_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.MANGROVE_LEAVES = BlockTypeBuilder
                .builder(BlockMangroveLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.MANGROVE_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.OAK_LEAVES = BlockTypeBuilder
                .builder(BlockOakLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.OAK_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.SPRUCE_LEAVES = BlockTypeBuilder
                .builder(BlockSpruceLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.SPRUCE_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
    }

    public static void initGravel() {
        BlockTypes.GRAVEL = BlockTypeBuilder
                .builder(BlockGravelBehavior.class)
                .vanillaBlock(VanillaBlockId.GRAVEL)
                .setBlockBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
    }

    public static void initSand() {
        BlockTypes.SAND = BlockTypeBuilder
                .builder(BlockSandBehavior.class)
                .vanillaBlock(VanillaBlockId.SAND)
                .setProperties(VanillaBlockPropertyTypes.SAND_TYPE)
                .setBlockBaseComponentSupplier(BlockSandBaseComponentImpl::new)
                .build();
    }

    public static void initShulkerBox() {
        BlockTypes.YELLOW_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockYellowShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.YELLOW_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.WHITE_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockWhiteShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.WHITE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.UNDYED_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockUndyedShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.UNDYED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockRedShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.RED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PURPLE_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockPurpleShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.PURPLE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PINK_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockPinkShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.PINK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.ORANGE_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockOrangeShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.ORANGE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.MAGENTA_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockMagentaShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.MAGENTA_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIME_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockLimeShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.LIME_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_GRAY_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockLightGrayShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.LIGHT_GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_BLUE_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockLightBlueShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.LIGHT_BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GREEN_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockGreenShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.GREEN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GRAY_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockGrayShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.CYAN_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockCyanShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.CYAN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BROWN_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockBrownShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.BROWN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLUE_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockBlueShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLACK_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockBlackShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.BLACK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
    }

    public static void initGrassBlock() {
        BlockTypes.GRASS_BLOCK = BlockTypeBuilder
                .builder(BlockGrassBlockBehavior.class)
                .vanillaBlock(VanillaBlockId.GRASS_BLOCK)
                .setBlockBaseComponentSupplier(BlockGrassBlockBaseComponentImpl::new)
                .build();
    }

    public static void initShortGrass() {
        BlockTypes.SHORT_GRASS = BlockTypeBuilder
                .builder(BlockShortGrassBehavior.class)
                .vanillaBlock(VanillaBlockId.SHORT_GRASS)
                .setBlockBaseComponentSupplier(BlockShortGrassBaseComponentImpl::new)
                .build();
        BlockTypes.FERN = BlockTypeBuilder
                .builder(BlockFernBehavior.class)
                .vanillaBlock(VanillaBlockId.FERN)
                .setBlockBaseComponentSupplier(BlockShortGrassBaseComponentImpl::new)
                .build();
    }

    public static void initTallGrass() {
        BlockTypes.TALL_GRASS = BlockTypeBuilder
                .builder(BlockTallGrassBehavior.class)
                .vanillaBlock(VanillaBlockId.TALL_GRASS)
                .setProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(BlockTallGrassBaseComponentImpl::new)
                .build();
        BlockTypes.LARGE_FERN = BlockTypeBuilder
                .builder(BlockLargeFernBehavior.class)
                .vanillaBlock(VanillaBlockId.LARGE_FERN)
                .setProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(BlockTallGrassBaseComponentImpl::new)
                .build();
    }

    public static void initBarrel() {
        BlockTypes.BARREL = BlockTypeBuilder
                .builder(BlockBarrelBehavior.class)
                .vanillaBlock(VanillaBlockId.BARREL)
                .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
                .setBlockBaseComponentSupplier(BlockBarrelBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.BARREL)
                .build();
    }

    public static void initChest() {
        BlockTypes.CHEST = BlockTypeBuilder
                .builder(BlockChestBehavior.class)
                .vanillaBlock(VanillaBlockId.CHEST)
                .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBlockBaseComponentSupplier(BlockCardinalDirectionComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.CHEST)
                .build();
    }

    public static void initCraftingTable() {
        BlockTypes.CRAFTING_TABLE = BlockTypeBuilder
                .builder(BlockCraftingTableBehavior.class)
                .vanillaBlock(VanillaBlockId.CRAFTING_TABLE)
                .setBlockBaseComponentSupplier(BlockCraftingTableBaseComponentImpl::new)
                .build();
    }

    public static void initStairs() {
        BlockTypes.ACACIA_STAIRS = BlockTypeBuilder
                .builder(BlockAcaciaStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.ACACIA_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.ANDESITE_STAIRS = BlockTypeBuilder
                .builder(BlockAndesiteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.ANDESITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BAMBOO_MOSAIC_STAIRS = BlockTypeBuilder
                .builder(BlockBambooMosaicStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BAMBOO_STAIRS = BlockTypeBuilder
                .builder(BlockBambooStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.BAMBOO_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BIRCH_STAIRS = BlockTypeBuilder
                .builder(BlockBirchStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.BIRCH_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BLACKSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockBlackstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.BLACKSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CHERRY_STAIRS = BlockTypeBuilder
                .builder(BlockCherryStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.CHERRY_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.COBBLED_DEEPSLATE_STAIRS = BlockTypeBuilder
                .builder(BlockCobbledDeepslateStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CRIMSON_STAIRS = BlockTypeBuilder
                .builder(BlockCrimsonStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.CRIMSON_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_OAK_STAIRS = BlockTypeBuilder
                .builder(BlockDarkOakStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.DARK_OAK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_PRISMARINE_STAIRS = BlockTypeBuilder
                .builder(BlockDarkPrismarineStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.DARK_PRISMARINE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DEEPSLATE_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockDeepslateBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DEEPSLATE_TILE_STAIRS = BlockTypeBuilder
                .builder(BlockDeepslateTileStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DIORITE_STAIRS = BlockTypeBuilder
                .builder(BlockDioriteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.DIORITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.END_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockEndBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.END_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.EXPOSED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockExposedCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.GRANITE_STAIRS = BlockTypeBuilder
                .builder(BlockGraniteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.GRANITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.JUNGLE_STAIRS = BlockTypeBuilder
                .builder(BlockJungleStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.JUNGLE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MANGROVE_STAIRS = BlockTypeBuilder
                .builder(BlockMangroveStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.MANGROVE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MOSSY_COBBLESTONE_STAIRS = BlockTypeBuilder
                .builder(BlockMossyCobblestoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MOSSY_STONE_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockMossyStoneBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.MOSSY_STONE_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MUD_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockMudBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.MUD_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.NETHER_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockNetherBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.NETHER_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.NORMAL_STONE_STAIRS = BlockTypeBuilder
                .builder(BlockNormalStoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.NORMAL_STONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.OAK_STAIRS = BlockTypeBuilder
                .builder(BlockOakStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.OAK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockOxidizedCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_ANDESITE_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedAndesiteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_ANDESITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_BLACKSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_DEEPSLATE_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedDeepslateStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_DIORITE_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedDioriteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_DIORITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_GRANITE_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedGraniteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_GRANITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_TUFF_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedTuffStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_TUFF_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PRISMARINE_BRICKS_STAIRS = BlockTypeBuilder
                .builder(BlockPrismarineBricksStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.PRISMARINE_BRICKS_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PRISMARINE_STAIRS = BlockTypeBuilder
                .builder(BlockPrismarineStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.PRISMARINE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PURPUR_STAIRS = BlockTypeBuilder
                .builder(BlockPurpurStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.PURPUR_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.QUARTZ_STAIRS = BlockTypeBuilder
                .builder(BlockQuartzStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.QUARTZ_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.RED_NETHER_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockRedNetherBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SANDSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockRedSandstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.RED_SANDSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SANDSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockSandstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.SANDSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_QUARTZ_STAIRS = BlockTypeBuilder
                .builder(BlockSmoothQuartzStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.SMOOTH_QUARTZ_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockSmoothRedSandstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.SMOOTH_RED_SANDSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_SANDSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockSmoothSandstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.SMOOTH_SANDSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SPRUCE_STAIRS = BlockTypeBuilder
                .builder(BlockSpruceStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.SPRUCE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.STONE_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockStoneBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.STONE_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.STONE_STAIRS = BlockTypeBuilder
                .builder(BlockStoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.STONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.TUFF_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockTuffBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.TUFF_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.TUFF_STAIRS = BlockTypeBuilder
                .builder(BlockTuffStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.TUFF_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WARPED_STAIRS = BlockTypeBuilder
                .builder(BlockWarpedStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WARPED_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockWaxedCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockWaxedExposedCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockWaxedOxidizedCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockWaxedWeatheredCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WEATHERED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockWeatheredCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
    }

    public static void initColoredTorch() {
        BlockTypes.COLORED_TORCH_BP = BlockTypeBuilder
                .builder(BlockColoredTorchBpBehavior.class)
                .vanillaBlock(VanillaBlockId.COLORED_TORCH_BP)
                .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockColoredTorchBaseComponentImpl::new)
                .build();
        BlockTypes.COLORED_TORCH_RG = BlockTypeBuilder
                .builder(BlockColoredTorchRgBehavior.class)
                .vanillaBlock(VanillaBlockId.COLORED_TORCH_RG)
                .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockColoredTorchBaseComponentImpl::new)
                .build();
    }

    public static void initTorch() {
        BlockTypes.REDSTONE_TORCH = BlockTypeBuilder
                .builder(BlockRedstoneTorchBehavior.class)
                .vanillaBlock(VanillaBlockId.REDSTONE_TORCH)
                .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.SOUL_TORCH = BlockTypeBuilder
                .builder(BlockSoulTorchBehavior.class)
                .vanillaBlock(VanillaBlockId.SOUL_TORCH)
                .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.TORCH = BlockTypeBuilder
                .builder(BlockTorchBehavior.class)
                .vanillaBlock(VanillaBlockId.TORCH)
                .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();

        BlockTypes.UNDERWATER_TORCH = BlockTypeBuilder
                .builder(BlockUnderwaterTorchBehavior.class)
                .vanillaBlock(VanillaBlockId.UNDERWATER_TORCH)
                .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.UNLIT_REDSTONE_TORCH = BlockTypeBuilder
                .builder(BlockUnlitRedstoneTorchBehavior.class)
                .vanillaBlock(VanillaBlockId.UNLIT_REDSTONE_TORCH)
                .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
    }

    public static void initFurnace() {
        BlockTypes.FURNACE = BlockTypeBuilder
                .builder(BlockFurnaceBehavior.class)
                .vanillaBlock(VanillaBlockId.FURNACE)
                .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBlockBaseComponentSupplier(BlockCardinalDirectionComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.FURNACE)
                .build();
        BlockTypes.LIT_FURNACE = BlockTypeBuilder
                .builder(BlockLitFurnaceBehavior.class)
                .vanillaBlock(VanillaBlockId.LIT_FURNACE)
                .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBlockBaseComponentSupplier(BlockCardinalDirectionComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.FURNACE)
                .build();
        BlockTypes.BLAST_FURNACE = BlockTypeBuilder
                .builder(BlockBlastFurnaceBehavior.class)
                .vanillaBlock(VanillaBlockId.BLAST_FURNACE)
                .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBlockBaseComponentSupplier(BlockCardinalDirectionComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.BLAST_FURNACE)
                .build();
        BlockTypes.LIT_BLAST_FURNACE = BlockTypeBuilder
                .builder(BlockLitBlastFurnaceBehavior.class)
                .vanillaBlock(VanillaBlockId.LIT_BLAST_FURNACE)
                .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBlockBaseComponentSupplier(BlockCardinalDirectionComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.BLAST_FURNACE)
                .build();
        BlockTypes.SMOKER = BlockTypeBuilder
                .builder(BlockSmokerBehavior.class)
                .vanillaBlock(VanillaBlockId.SMOKER)
                .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBlockBaseComponentSupplier(BlockCardinalDirectionComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.SMOKER)
                .build();
        BlockTypes.LIT_SMOKER = BlockTypeBuilder
                .builder(BlockLitSmokerBehavior.class)
                .vanillaBlock(VanillaBlockId.LIT_SMOKER)
                .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBlockBaseComponentSupplier(BlockCardinalDirectionComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.SMOKER)
                .build();
    }

    public static void initPillars() {
        // Log
        BlockTypes.ACACIA_LOG = buildWood(BlockAcaciaLogBehavior.class, VanillaBlockId.ACACIA_LOG, VanillaBlockId.STRIPPED_ACACIA_LOG);
        BlockTypes.BAMBOO_BLOCK = buildWood(BlockBambooBlockBehavior.class, VanillaBlockId.BAMBOO_BLOCK, VanillaBlockId.STRIPPED_BAMBOO_BLOCK);
        BlockTypes.BIRCH_LOG = buildWood(BlockBirchLogBehavior.class, VanillaBlockId.BIRCH_LOG, VanillaBlockId.STRIPPED_BIRCH_LOG);
        BlockTypes.CHERRY_LOG = buildWood(BlockCherryLogBehavior.class, VanillaBlockId.CHERRY_LOG, VanillaBlockId.STRIPPED_CHERRY_LOG);
        BlockTypes.CRIMSON_STEM = buildWood(BlockCrimsonStemBehavior.class, VanillaBlockId.CRIMSON_STEM, VanillaBlockId.STRIPPED_CRIMSON_STEM);
        BlockTypes.DARK_OAK_LOG = buildWood(BlockDarkOakLogBehavior.class, VanillaBlockId.DARK_OAK_LOG, VanillaBlockId.STRIPPED_OAK_LOG);
        BlockTypes.JUNGLE_LOG = buildWood(BlockJungleLogBehavior.class, VanillaBlockId.JUNGLE_LOG, VanillaBlockId.STRIPPED_JUNGLE_LOG);
        BlockTypes.MANGROVE_LOG = buildWood(BlockMangroveLogBehavior.class, VanillaBlockId.MANGROVE_LOG, VanillaBlockId.STRIPPED_MANGROVE_LOG);
        BlockTypes.OAK_LOG = buildWood(BlockOakLogBehavior.class, VanillaBlockId.OAK_LOG, VanillaBlockId.STRIPPED_OAK_LOG);
        BlockTypes.SPRUCE_LOG = buildWood(BlockSpruceLogBehavior.class, VanillaBlockId.SPRUCE_LOG, VanillaBlockId.STRIPPED_SPRUCE_LOG);
        BlockTypes.WARPED_STEM = buildWood(BlockWarpedStemBehavior.class, VanillaBlockId.WARPED_STEM, VanillaBlockId.STRIPPED_WARPED_STEM);

        // Stripped Log
        BlockTypes.STRIPPED_ACACIA_LOG = buildPillarAxis(BlockStrippedAcaciaLogBehavior.class, VanillaBlockId.STRIPPED_ACACIA_LOG);
        BlockTypes.STRIPPED_BAMBOO_BLOCK = buildPillarAxis(BlockStrippedBambooBlockBehavior.class, VanillaBlockId.STRIPPED_BAMBOO_BLOCK);
        BlockTypes.STRIPPED_BIRCH_LOG = buildPillarAxis(BlockStrippedBirchLogBehavior.class, VanillaBlockId.STRIPPED_BIRCH_LOG);
        BlockTypes.STRIPPED_CHERRY_LOG = buildPillarAxis(BlockStrippedCherryLogBehavior.class, VanillaBlockId.STRIPPED_CHERRY_LOG);
        BlockTypes.STRIPPED_CRIMSON_STEM = buildPillarAxis(BlockStrippedCrimsonStemBehavior.class, VanillaBlockId.STRIPPED_CRIMSON_STEM);
        BlockTypes.STRIPPED_DARK_OAK_LOG = buildPillarAxis(BlockStrippedDarkOakLogBehavior.class, VanillaBlockId.STRIPPED_DARK_OAK_LOG);
        BlockTypes.STRIPPED_JUNGLE_LOG = buildPillarAxis(BlockStrippedJungleLogBehavior.class, VanillaBlockId.STRIPPED_JUNGLE_LOG);
        BlockTypes.STRIPPED_OAK_LOG = buildPillarAxis(BlockStrippedOakLogBehavior.class, VanillaBlockId.STRIPPED_OAK_LOG);
        BlockTypes.STRIPPED_SPRUCE_LOG = buildPillarAxis(BlockStrippedSpruceLogBehavior.class, VanillaBlockId.STRIPPED_SPRUCE_LOG);
        BlockTypes.STRIPPED_WARPED_STEM = buildPillarAxis(BlockStrippedWarpedStemBehavior.class, VanillaBlockId.STRIPPED_WARPED_STEM);

        // Wood
        BlockTypes.ACACIA_WOOD = buildWood(BlockAcaciaWoodBehavior.class, VanillaBlockId.ACACIA_WOOD, VanillaBlockId.STRIPPED_ACACIA_WOOD);
        BlockTypes.BIRCH_WOOD = buildWood(BlockBirchWoodBehavior.class, VanillaBlockId.BIRCH_WOOD, VanillaBlockId.STRIPPED_BIRCH_WOOD);
        BlockTypes.CRIMSON_HYPHAE = buildWood(BlockCrimsonHyphaeBehavior.class, VanillaBlockId.CRIMSON_HYPHAE, VanillaBlockId.STRIPPED_CRIMSON_HYPHAE);
        BlockTypes.DARK_OAK_WOOD = buildWood(BlockDarkOakWoodBehavior.class, VanillaBlockId.DARK_OAK_WOOD, VanillaBlockId.STRIPPED_DARK_OAK_WOOD);
        BlockTypes.JUNGLE_WOOD = buildWood(BlockJungleWoodBehavior.class, VanillaBlockId.JUNGLE_WOOD, VanillaBlockId.STRIPPED_JUNGLE_WOOD);
        BlockTypes.OAK_WOOD = buildWood(BlockOakWoodBehavior.class, VanillaBlockId.OAK_WOOD, VanillaBlockId.STRIPPED_OAK_WOOD);
        BlockTypes.SPRUCE_WOOD = buildWood(BlockSpruceWoodBehavior.class, VanillaBlockId.SPRUCE_WOOD, VanillaBlockId.STRIPPED_SPRUCE_WOOD);
        BlockTypes.WARPED_HYPHAE = buildWood(BlockWarpedHyphaeBehavior.class, VanillaBlockId.WARPED_HYPHAE, VanillaBlockId.STRIPPED_WARPED_HYPHAE);

        // New Wood
        BlockTypes.CHERRY_WOOD = buildNewWood(BlockCherryWoodBehavior.class, VanillaBlockId.CHERRY_WOOD);
        BlockTypes.MANGROVE_WOOD = buildNewWood(BlockMangroveWoodBehavior.class, VanillaBlockId.MANGROVE_WOOD);

        // Stripped Wood
        BlockTypes.STRIPPED_ACACIA_WOOD = buildPillarAxis(BlockStrippedAcaciaWoodBehavior.class, VanillaBlockId.STRIPPED_ACACIA_WOOD);
        BlockTypes.STRIPPED_BIRCH_WOOD = buildPillarAxis(BlockStrippedBirchWoodBehavior.class, VanillaBlockId.STRIPPED_BIRCH_WOOD);
        BlockTypes.STRIPPED_CHERRY_WOOD = buildPillarAxis(BlockStrippedCherryWoodBehavior.class, VanillaBlockId.STRIPPED_CHERRY_WOOD);
        BlockTypes.STRIPPED_CRIMSON_HYPHAE = buildPillarAxis(BlockStrippedCrimsonHyphaeBehavior.class, VanillaBlockId.STRIPPED_CRIMSON_HYPHAE);
        BlockTypes.STRIPPED_DARK_OAK_WOOD = buildPillarAxis(BlockStrippedDarkOakWoodBehavior.class, VanillaBlockId.STRIPPED_DARK_OAK_WOOD);
        BlockTypes.STRIPPED_JUNGLE_WOOD = buildPillarAxis(BlockStrippedJungleWoodBehavior.class, VanillaBlockId.STRIPPED_JUNGLE_WOOD);
        BlockTypes.STRIPPED_MANGROVE_WOOD = buildPillarAxis(BlockStrippedMangroveWoodBehavior.class, VanillaBlockId.STRIPPED_MANGROVE_WOOD);
        BlockTypes.STRIPPED_OAK_WOOD = buildPillarAxis(BlockStrippedOakWoodBehavior.class, VanillaBlockId.STRIPPED_OAK_WOOD);
        BlockTypes.STRIPPED_SPRUCE_WOOD = buildPillarAxis(BlockStrippedSpruceWoodBehavior.class, VanillaBlockId.STRIPPED_SPRUCE_WOOD);
        BlockTypes.STRIPPED_WARPED_HYPHAE = buildPillarAxis(BlockStrippedWarpedHyphaeBehavior.class, VanillaBlockId.STRIPPED_WARPED_HYPHAE);

        BlockTypes.MUDDY_MANGROVE_ROOTS = buildPillarAxis(BlockMuddyMangroveRootsBehavior.class, VanillaBlockId.MUDDY_MANGROVE_ROOTS);

        BlockTypes.BASALT = buildPillarAxis(BlockBasaltBehavior.class, VanillaBlockId.BASALT);
        BlockTypes.POLISHED_BASALT = buildPillarAxis(BlockPolishedBasaltBehavior.class, VanillaBlockId.POLISHED_BASALT);

        BlockTypes.OCHRE_FROGLIGHT = buildPillarAxis(BlockOchreFroglightBehavior.class, VanillaBlockId.OCHRE_FROGLIGHT);
        BlockTypes.PEARLESCENT_FROGLIGHT = buildPillarAxis(BlockPearlescentFroglightBehavior.class, VanillaBlockId.PEARLESCENT_FROGLIGHT);
        BlockTypes.VERDANT_FROGLIGHT = buildPillarAxis(BlockVerdantFroglightBehavior.class, VanillaBlockId.VERDANT_FROGLIGHT);

        BlockTypes.BONE_BLOCK = BlockTypeBuilder
                .builder(BlockBoneBlockBehavior.class)
                .vanillaBlock(VanillaBlockId.BONE_BLOCK)
                .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(BlockPillarAxisLikeComponentImpl::new)
                .build();
        BlockTypes.HAY_BLOCK = BlockTypeBuilder
                .builder(BlockHayBlockBehavior.class)
                .vanillaBlock(VanillaBlockId.HAY_BLOCK)
                .setProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(BlockPillarAxisLikeComponentImpl::new)
                .build();

        BlockTypes.PURPUR_BLOCK = BlockTypeBuilder
                .builder(BlockPurpurBlockBehavior.class)
                .vanillaBlock(VanillaBlockId.PURPUR_BLOCK)
                .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(BlockPillarAxisLikeComponentImpl::new)
                .build();
        BlockTypes.QUARTZ_BLOCK = BlockTypeBuilder
                .builder(BlockQuartzBlockBehavior.class)
                .vanillaBlock(VanillaBlockId.QUARTZ_BLOCK)
                .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(BlockPillarAxisLikeComponentImpl::new)
                .build();

        BlockTypes.CHAIN = buildPillarAxis(BlockChainBehavior.class, VanillaBlockId.CHAIN);
        BlockTypes.DEEPSLATE = buildPillarAxis(BlockDeepslateBehavior.class, VanillaBlockId.DEEPSLATE);
    }

    public static void initCardinalDirection() {
        BlockTypes.ANVIL = BlockTypeBuilder
                .builder(BlockAnvilBehavior.class)
                .vanillaBlock(VanillaBlockId.ANVIL)
                .setProperties(VanillaBlockPropertyTypes.DAMAGE, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBlockBaseComponentSupplier(BlockCardinalDirectionComponentImpl::new)
                .build();

        //TODO: BlockTypes.BIG_DRIPLEAF, BIG_DRIPLEAF
        BlockTypes.CARVED_PUMPKIN = buildCardinalDirection(BlockCarvedPumpkinBehavior.class, VanillaBlockId.CARVED_PUMPKIN);

        //TODO: CALIBRATED_SCULK_SENSOR
    }

    public static void initButtons() {
        BlockTypes.ACACIA_BUTTON = buildWoodenButton(BlockAcaciaButtonBehavior.class, VanillaBlockId.ACACIA_BUTTON);
        BlockTypes.BAMBOO_BUTTON = buildWoodenButton(BlockBambooButtonBehavior.class, VanillaBlockId.BAMBOO_BUTTON);
        BlockTypes.BIRCH_BUTTON = buildWoodenButton(BlockBirchButtonBehavior.class, VanillaBlockId.BIRCH_BUTTON);
        BlockTypes.CHERRY_BUTTON = buildWoodenButton(BlockCherryButtonBehavior.class, VanillaBlockId.CHERRY_BUTTON);
        BlockTypes.CRIMSON_BUTTON = buildWoodenButton(BlockCrimsonButtonBehavior.class, VanillaBlockId.CRIMSON_BUTTON);
        BlockTypes.DARK_OAK_BUTTON = buildWoodenButton(BlockDarkOakButtonBehavior.class, VanillaBlockId.DARK_OAK_BUTTON);
        BlockTypes.JUNGLE_BUTTON = buildWoodenButton(BlockJungleButtonBehavior.class, VanillaBlockId.JUNGLE_BUTTON);
        BlockTypes.MANGROVE_BUTTON = buildWoodenButton(BlockMangroveButtonBehavior.class, VanillaBlockId.MANGROVE_BUTTON);
        BlockTypes.SPRUCE_BUTTON = buildWoodenButton(BlockSpruceButtonBehavior.class, VanillaBlockId.SPRUCE_BUTTON);
        BlockTypes.WARPED_BUTTON = buildWoodenButton(BlockWarpedButtonBehavior.class, VanillaBlockId.WARPED_BUTTON);
        BlockTypes.WOODEN_BUTTON = buildWoodenButton(BlockWoodenButtonBehavior.class, VanillaBlockId.WOODEN_BUTTON);

        BlockTypes.POLISHED_BLACKSTONE_BUTTON = buildButton(BlockPolishedBlackstoneButtonBehavior.class, VanillaBlockId.POLISHED_BLACKSTONE_BUTTON, blockType -> new BlockButtonBaseComponentImpl(blockType, Duration.ofSeconds(1)));
        BlockTypes.STONE_BUTTON = buildButton(BlockStoneButtonBehavior.class, VanillaBlockId.STONE_BUTTON, blockType -> new BlockButtonBaseComponentImpl(blockType, Duration.ofSeconds(1)));
    }

    public static void initDoors() {
        BlockTypes.ACACIA_DOOR = buildWoodenDoor(BlockAcaciaDoorBehavior.class, VanillaBlockId.ACACIA_DOOR);
        BlockTypes.BAMBOO_DOOR = buildWoodenDoor(BlockBambooDoorBehavior.class, VanillaBlockId.BAMBOO_DOOR);
        BlockTypes.BIRCH_DOOR = buildWoodenDoor(BlockBirchDoorBehavior.class, VanillaBlockId.BIRCH_DOOR);
        BlockTypes.CHERRY_DOOR = buildWoodenDoor(BlockCherryDoorBehavior.class, VanillaBlockId.CHERRY_DOOR);
        BlockTypes.CRIMSON_DOOR = buildWoodenDoor(BlockCrimsonDoorBehavior.class, VanillaBlockId.CRIMSON_DOOR);
        BlockTypes.DARK_OAK_DOOR = buildWoodenDoor(BlockDarkOakDoorBehavior.class, VanillaBlockId.DARK_OAK_DOOR);
        BlockTypes.JUNGLE_DOOR = buildWoodenDoor(BlockJungleDoorBehavior.class, VanillaBlockId.JUNGLE_DOOR);
        BlockTypes.MANGROVE_DOOR = buildWoodenDoor(BlockMangroveDoorBehavior.class, VanillaBlockId.MANGROVE_DOOR);
        BlockTypes.WOODEN_DOOR = buildWoodenDoor(BlockWoodenDoorBehavior.class, VanillaBlockId.WOODEN_DOOR);
        BlockTypes.SPRUCE_DOOR = buildWoodenDoor(BlockSpruceDoorBehavior.class, VanillaBlockId.SPRUCE_DOOR);
        BlockTypes.WARPED_DOOR = buildWoodenDoor(BlockWarpedDoorBehavior.class, VanillaBlockId.WARPED_DOOR);
    }

    public static void initGlazedTerracotta() {
        BlockTypes.BLACK_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockBlackGlazedTerracottaBehavior.class, VanillaBlockId.BLACK_GLAZED_TERRACOTTA);
        BlockTypes.BLUE_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockBlueGlazedTerracottaBehavior.class, VanillaBlockId.BLUE_GLAZED_TERRACOTTA);
        BlockTypes.BROWN_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockBrownGlazedTerracottaBehavior.class, VanillaBlockId.BROWN_GLAZED_TERRACOTTA);
        BlockTypes.CYAN_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockCyanGlazedTerracottaBehavior.class, VanillaBlockId.CYAN_GLAZED_TERRACOTTA);
        BlockTypes.GRAY_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockGrayGlazedTerracottaBehavior.class, VanillaBlockId.GRAY_GLAZED_TERRACOTTA);
        BlockTypes.GREEN_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockGreenGlazedTerracottaBehavior.class, VanillaBlockId.GREEN_GLAZED_TERRACOTTA);
        BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockLightBlueGlazedTerracottaBehavior.class, VanillaBlockId.LIGHT_BLUE_GLAZED_TERRACOTTA);
        BlockTypes.LIME_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockLimeGlazedTerracottaBehavior.class, VanillaBlockId.LIME_GLAZED_TERRACOTTA);
        BlockTypes.MAGENTA_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockMagentaGlazedTerracottaBehavior.class, VanillaBlockId.MAGENTA_GLAZED_TERRACOTTA);
        BlockTypes.ORANGE_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockOrangeGlazedTerracottaBehavior.class, VanillaBlockId.ORANGE_GLAZED_TERRACOTTA);
        BlockTypes.PINK_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockPinkGlazedTerracottaBehavior.class, VanillaBlockId.PINK_GLAZED_TERRACOTTA);
        BlockTypes.PURPLE_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockPurpleGlazedTerracottaBehavior.class, VanillaBlockId.PURPLE_GLAZED_TERRACOTTA);
        BlockTypes.RED_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockRedGlazedTerracottaBehavior.class, VanillaBlockId.RED_GLAZED_TERRACOTTA);
        BlockTypes.SILVER_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockSilverGlazedTerracottaBehavior.class, VanillaBlockId.SILVER_GLAZED_TERRACOTTA);
        BlockTypes.PURPLE_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockPurpleGlazedTerracottaBehavior.class, VanillaBlockId.PURPLE_GLAZED_TERRACOTTA);
        BlockTypes.WHITE_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockWhiteGlazedTerracottaBehavior.class, VanillaBlockId.WHITE_GLAZED_TERRACOTTA);
        BlockTypes.YELLOW_GLAZED_TERRACOTTA = buildGlazedTerracootta(BlockYellowGlazedTerracottaBehavior.class, VanillaBlockId.YELLOW_GLAZED_TERRACOTTA);
    }

    public static void initRods() {
        BlockTypes.END_ROD = BlockTypeBuilder
                .builder(BlockEndRodBehavior.class)
                .vanillaBlock(VanillaBlockId.END_ROD)
                .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockRodBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHTNING_ROD = BlockTypeBuilder
                .builder(BlockLightningRodBehavior.class)
                .vanillaBlock(VanillaBlockId.LIGHTNING_ROD)
                .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockRodBaseComponentImpl::new)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildGlazedTerracootta(Class<T> clazz, VanillaBlockId vanillaBlockId) {
        return BlockTypeBuilder
                .builder(clazz)
                .vanillaBlock(vanillaBlockId)
                .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockGlazedTerracottaBaseComponentImpl::new)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildWood(Class<T> clazz, VanillaBlockId vanillaBlockId, VanillaBlockId strippedBlockId) {
        return BlockTypeBuilder
                .builder(clazz)
                .vanillaBlock(vanillaBlockId)
                .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(blockType -> new BlockWoodBaseComponentImpl(blockType, strippedBlockId))
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildNewWood(Class<T> clazz, VanillaBlockId vanillaBlockId) {
        return BlockTypeBuilder
                .builder(clazz)
                .vanillaBlock(vanillaBlockId)
                .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
                .setBlockBaseComponentSupplier(BlockNewWoodBaseComponentImpl::new)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildPillarAxis(Class<T> clazz, VanillaBlockId vanillaBlockId) {
        return BlockTypeBuilder
                .builder(clazz)
                .vanillaBlock(vanillaBlockId)
                .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
                .setBlockBaseComponentSupplier(BlockPillarAxisLikeComponentImpl::new)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildCardinalDirection(Class<T> clazz, VanillaBlockId vanillaBlockId) {
        return BlockTypeBuilder
                .builder(clazz)
                .vanillaBlock(vanillaBlockId)
                .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBlockBaseComponentSupplier(BlockCardinalDirectionComponentImpl::new)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildWoodenButton(Class<T> clazz, VanillaBlockId vanillaBlockId) {
        return buildButton(clazz, vanillaBlockId, BlockWoodenButtonBaseComponentImpl::new);
    }

    private static <T extends BlockBehavior> BlockType<T> buildButton(Class<T> clazz, VanillaBlockId vanillaBlockId, Function<BlockType<T>, BlockBaseComponent> blockBaseComponentSupplier) {
        return BlockTypeBuilder
                .builder(clazz)
                .vanillaBlock(vanillaBlockId)
                .setProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    private static <T extends BlockBehavior> BlockType<T> buildWoodenDoor(Class<T> clazz, VanillaBlockId vanillaBlockId) {
        return buildDoor(clazz, vanillaBlockId, BlockDoorBaseComponentImpl::new);
    }

    private static <T extends BlockBehavior> BlockType<T> buildDoor(Class<T> clazz, VanillaBlockId vanillaBlockId, Function<BlockType<T>, BlockBaseComponent> blockBaseComponentSupplier) {
        return BlockTypeBuilder
                .builder(clazz)
                .vanillaBlock(vanillaBlockId)
                .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(blockBaseComponentSupplier)
                .build();
    }

    public static void initWallSigns() {
        BlockTypes.WALL_SIGN = buildWallSign(BlockWallSignBehavior.class, VanillaBlockId.WALL_SIGN, ItemTypes.OAK_SIGN);
        BlockTypes.ACACIA_WALL_SIGN = buildWallSign(BlockAcaciaWallSignBehavior.class, VanillaBlockId.ACACIA_WALL_SIGN, ItemTypes.ACACIA_SIGN);
        BlockTypes.BAMBOO_WALL_SIGN = buildWallSign(BlockBambooWallSignBehavior.class, VanillaBlockId.BAMBOO_WALL_SIGN, ItemTypes.BAMBOO_SIGN);
        BlockTypes.BIRCH_WALL_SIGN = buildWallSign(BlockBirchWallSignBehavior.class, VanillaBlockId.BIRCH_WALL_SIGN, ItemTypes.BIRCH_SIGN);
        BlockTypes.CHERRY_WALL_SIGN = buildWallSign(BlockCherryWallSignBehavior.class, VanillaBlockId.CHERRY_WALL_SIGN, ItemTypes.CHERRY_SIGN);
        BlockTypes.CRIMSON_WALL_SIGN = buildWallSign(BlockCrimsonWallSignBehavior.class, VanillaBlockId.CRIMSON_WALL_SIGN, ItemTypes.CRIMSON_SIGN);
        BlockTypes.DARKOAK_WALL_SIGN = buildWallSign(BlockDarkoakWallSignBehavior.class, VanillaBlockId.DARKOAK_WALL_SIGN, ItemTypes.DARK_OAK_SIGN);
        BlockTypes.JUNGLE_WALL_SIGN = buildWallSign(BlockJungleWallSignBehavior.class, VanillaBlockId.JUNGLE_WALL_SIGN, ItemTypes.JUNGLE_SIGN);
        BlockTypes.MANGROVE_WALL_SIGN = buildWallSign(BlockMangroveWallSignBehavior.class, VanillaBlockId.MANGROVE_WALL_SIGN, ItemTypes.MANGROVE_SIGN);
        BlockTypes.SPRUCE_WALL_SIGN = buildWallSign(BlockSpruceWallSignBehavior.class, VanillaBlockId.SPRUCE_WALL_SIGN, ItemTypes.SPRUCE_SIGN);
        BlockTypes.WARPED_WALL_SIGN = buildWallSign(BlockWarpedWallSignBehavior.class, VanillaBlockId.WARPED_WALL_SIGN, ItemTypes.WARPED_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildWallSign(Class<T> clazz, VanillaBlockId vanillaBlockId, ItemType<?> dropItemType) {
        return BlockTypeBuilder
                .builder(clazz)
                .vanillaBlock(vanillaBlockId)
                .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
                .setBlockBaseComponentSupplier(blockType -> new BlockWallSignBaseComponentImpl(blockType, dropItemType))
                .bindBlockEntity(BlockEntityTypes.SIGN)
                .build();
    }

    public static void initStandingSigns() {
        BlockTypes.STANDING_SIGN = buildStandingSign(BlockStandingSignBehavior.class, VanillaBlockId.STANDING_SIGN, ItemTypes.OAK_SIGN);
        BlockTypes.ACACIA_STANDING_SIGN = buildStandingSign(BlockAcaciaStandingSignBehavior.class, VanillaBlockId.ACACIA_STANDING_SIGN, ItemTypes.ACACIA_SIGN);
        BlockTypes.BAMBOO_STANDING_SIGN = buildStandingSign(BlockBambooStandingSignBehavior.class, VanillaBlockId.BAMBOO_STANDING_SIGN, ItemTypes.BAMBOO_SIGN);
        BlockTypes.BIRCH_STANDING_SIGN = buildStandingSign(BlockBirchStandingSignBehavior.class, VanillaBlockId.BIRCH_STANDING_SIGN, ItemTypes.BIRCH_SIGN);
        BlockTypes.CHERRY_STANDING_SIGN = buildStandingSign(BlockCherryStandingSignBehavior.class, VanillaBlockId.CHERRY_STANDING_SIGN, ItemTypes.CHERRY_SIGN);
        BlockTypes.CRIMSON_STANDING_SIGN = buildStandingSign(BlockCrimsonStandingSignBehavior.class, VanillaBlockId.CRIMSON_STANDING_SIGN, ItemTypes.CRIMSON_SIGN);
        BlockTypes.DARKOAK_STANDING_SIGN = buildStandingSign(BlockDarkoakStandingSignBehavior.class, VanillaBlockId.DARKOAK_STANDING_SIGN, ItemTypes.DARK_OAK_SIGN);
        BlockTypes.JUNGLE_STANDING_SIGN = buildStandingSign(BlockJungleStandingSignBehavior.class, VanillaBlockId.JUNGLE_STANDING_SIGN, ItemTypes.JUNGLE_SIGN);
        BlockTypes.MANGROVE_STANDING_SIGN = buildStandingSign(BlockMangroveStandingSignBehavior.class, VanillaBlockId.MANGROVE_STANDING_SIGN, ItemTypes.MANGROVE_SIGN);
        BlockTypes.SPRUCE_STANDING_SIGN = buildStandingSign(BlockSpruceStandingSignBehavior.class, VanillaBlockId.SPRUCE_STANDING_SIGN, ItemTypes.SPRUCE_SIGN);
        BlockTypes.WARPED_STANDING_SIGN = buildStandingSign(BlockWarpedStandingSignBehavior.class, VanillaBlockId.WARPED_STANDING_SIGN, ItemTypes.WARPED_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildStandingSign(Class<T> clazz, VanillaBlockId vanillaBlockId, ItemType<?> dropItemType) {
        return BlockTypeBuilder
                .builder(clazz)
                .vanillaBlock(vanillaBlockId)
                .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .setBlockBaseComponentSupplier(blockType -> new BlockStandingSignBaseComponentImpl(blockType, dropItemType))
                .bindBlockEntity(BlockEntityTypes.SIGN)
                .build();
    }

    public static void initHangingSigns() {
        BlockTypes.ACACIA_HANGING_SIGN = buildHangingSign(BlockAcaciaHangingSignBehavior.class, VanillaBlockId.ACACIA_HANGING_SIGN);
        BlockTypes.BAMBOO_HANGING_SIGN = buildHangingSign(BlockBambooHangingSignBehavior.class, VanillaBlockId.BAMBOO_HANGING_SIGN);
        BlockTypes.BIRCH_HANGING_SIGN = buildHangingSign(BlockBirchHangingSignBehavior.class, VanillaBlockId.BIRCH_HANGING_SIGN);
        BlockTypes.CHERRY_HANGING_SIGN = buildHangingSign(BlockCherryHangingSignBehavior.class, VanillaBlockId.CHERRY_HANGING_SIGN);
        BlockTypes.CRIMSON_HANGING_SIGN = buildHangingSign(BlockCrimsonHangingSignBehavior.class, VanillaBlockId.CRIMSON_HANGING_SIGN);
        BlockTypes.DARK_OAK_HANGING_SIGN = buildHangingSign(BlockDarkOakHangingSignBehavior.class, VanillaBlockId.DARK_OAK_HANGING_SIGN);
        BlockTypes.JUNGLE_HANGING_SIGN = buildHangingSign(BlockJungleHangingSignBehavior.class, VanillaBlockId.JUNGLE_HANGING_SIGN);
        BlockTypes.MANGROVE_HANGING_SIGN = buildHangingSign(BlockMangroveHangingSignBehavior.class, VanillaBlockId.MANGROVE_HANGING_SIGN);
        BlockTypes.OAK_HANGING_SIGN = buildHangingSign(BlockOakHangingSignBehavior.class, VanillaBlockId.OAK_HANGING_SIGN);
        BlockTypes.SPRUCE_HANGING_SIGN = buildHangingSign(BlockSpruceHangingSignBehavior.class, VanillaBlockId.SPRUCE_HANGING_SIGN);
        BlockTypes.WARPED_HANGING_SIGN = buildHangingSign(BlockWarpedHangingSignBehavior.class, VanillaBlockId.WARPED_HANGING_SIGN);
    }

    private static <T extends BlockBehavior> BlockType<T> buildHangingSign(Class<T> clazz, VanillaBlockId vanillaBlockId) {
        return BlockTypeBuilder
                .builder(clazz)
                .vanillaBlock(vanillaBlockId)
                .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
                .setBlockBaseComponentSupplier(BlockHangingSignBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.HANGING_SIGN)
                .build();
    }
}
