package org.allaymc.server.block.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.interfaces.*;
import org.allaymc.api.block.interfaces.glass.BlockGlassBehavior;
import org.allaymc.api.block.interfaces.leaves.*;
import org.allaymc.api.block.interfaces.shulkerbox.*;
import org.allaymc.api.block.interfaces.stairs.*;
import org.allaymc.api.block.interfaces.torch.*;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.math.voxelshape.CommonShapes;
import org.allaymc.server.block.component.barrel.BlockBarrelBaseComponentImpl;
import org.allaymc.server.block.component.chest.BlockChestBaseComponentImpl;
import org.allaymc.server.block.component.common.BlockStateDataComponentImpl;
import org.allaymc.server.block.component.common.BlockFallableBaseComponentImpl;
import org.allaymc.server.block.component.common.BlockTallWheatSeedDropableBaseComponentImpl;
import org.allaymc.server.block.component.common.BlockWheatSeedDropableBaseComponentImpl;
import org.allaymc.server.block.component.craftingtable.BlockCraftingTableBaseComponentImpl;
import org.allaymc.server.block.component.glass.BlockGlassBaseComponentImpl;
import org.allaymc.server.block.component.grassblock.BlockGrassBlockBaseComponentImpl;
import org.allaymc.server.block.component.leaves.BlockLeavesBaseComponentImpl;
import org.allaymc.server.block.component.sand.BlockSandBaseComponentImpl;
import org.allaymc.server.block.component.shulkerbox.BlockShulkerBoxBaseComponentImpl;
import org.allaymc.server.block.component.stairs.BlockStairsBaseComponentImpl;
import org.allaymc.server.block.component.torch.BlockColoredTorchBaseComponentImpl;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;

/**
 * Allay Project 2024/3/9
 *
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class BlockTypeInitializer {
    public static void initLeaves() {
        BlockTypes.ACACIA_LEAVES_TYPE = BlockTypeBuilder
                .builder(BlockAcaciaLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.ACACIA_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.AZALEA_LEAVES_TYPE = BlockTypeBuilder
                .builder(BlockAzaleaLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.AZALEA_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.BIRCH_LEAVES_TYPE = BlockTypeBuilder
                .builder(BlockBirchLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.BIRCH_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.CHERRY_LEAVES_TYPE = BlockTypeBuilder
                .builder(BlockCherryLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.CHERRY_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_OAK_LEAVES_TYPE = BlockTypeBuilder
                .builder(BlockDarkOakLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.DARK_OAK_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.JUNGLE_LEAVES_TYPE = BlockTypeBuilder
                .builder(BlockJungleLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.JUNGLE_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.MANGROVE_LEAVES_TYPE = BlockTypeBuilder
                .builder(BlockMangroveLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.MANGROVE_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.OAK_LEAVES_TYPE = BlockTypeBuilder
                .builder(BlockOakLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.OAK_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
        BlockTypes.SPRUCE_LEAVES_TYPE = BlockTypeBuilder
                .builder(BlockSpruceLeavesBehavior.class)
                .vanillaBlock(VanillaBlockId.SPRUCE_LEAVES)
                .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
                .setBlockBaseComponentSupplier(BlockLeavesBaseComponentImpl::new)
                .build();
    }

    public static void initGravel() {
        BlockTypes.GRAVEL_TYPE = BlockTypeBuilder
                .builder(BlockGravelBehavior.class)
                .vanillaBlock(VanillaBlockId.GRAVEL)
                .setBlockBaseComponentSupplier(BlockFallableBaseComponentImpl::new)
                .build();
    }

    public static void initSand() {
        BlockTypes.SAND_TYPE = BlockTypeBuilder
                .builder(BlockSandBehavior.class)
                .vanillaBlock(VanillaBlockId.SAND)
                .setProperties(VanillaBlockPropertyTypes.SAND_TYPE)
                .setBlockBaseComponentSupplier(BlockSandBaseComponentImpl::new)
                .build();
    }

    public static void initShulkerBox() {
        BlockTypes.YELLOW_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockYellowShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.YELLOW_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.WHITE_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockWhiteShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.WHITE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.UNDYED_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockUndyedShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.UNDYED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockRedShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.RED_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PURPLE_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockPurpleShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.PURPLE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.PINK_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockPinkShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.PINK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.ORANGE_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockOrangeShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.ORANGE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.MAGENTA_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockMagentaShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.MAGENTA_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIME_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockLimeShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.LIME_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockLightGrayShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.LIGHT_GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.LIGHT_BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockLightBlueShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.LIGHT_BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GREEN_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockGreenShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.GREEN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.GRAY_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockGrayShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.GRAY_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.CYAN_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockCyanShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.CYAN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BROWN_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockBrownShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.BROWN_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLUE_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockBlueShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.BLUE_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();
        BlockTypes.BLACK_SHULKER_BOX_TYPE = BlockTypeBuilder
                .builder(BlockBlackShulkerBoxBehavior.class)
                .vanillaBlock(VanillaBlockId.BLACK_SHULKER_BOX)
                .bindBlockEntity(BlockEntityTypes.SHULKER_BOX_TYPE)
                .setBlockBaseComponentSupplier(BlockShulkerBoxBaseComponentImpl::new)
                .build();

    }

    public static void initGrassBlock() {
        BlockTypes.GRASS_BLOCK_TYPE = BlockTypeBuilder
                .builder(BlockGrassBlockBehavior.class)
                .vanillaBlock(VanillaBlockId.GRASS_BLOCK)
                .setBlockBaseComponentSupplier(BlockGrassBlockBaseComponentImpl::new)
                .build();
    }

    public static void initShortGrass() {
        BlockTypes.SHORT_GRASS_TYPE = BlockTypeBuilder
                .builder(BlockShortGrassBehavior.class)
                .vanillaBlock(VanillaBlockId.SHORT_GRASS)
                .setBlockBaseComponentSupplier(BlockWheatSeedDropableBaseComponentImpl::new)
                .build();
    }

    public static void initTallGrass() {
        BlockTypes.TALL_GRASS_TYPE = BlockTypeBuilder
                .builder(BlockTallGrassBehavior.class)
                .vanillaBlock(VanillaBlockId.TALL_GRASS)
                .setProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(BlockTallWheatSeedDropableBaseComponentImpl::new)
                .build();
    }

    public static void initLargeFern() {
        // TODO: Needs adaptation
        BlockTypes.LARGE_FERN_TYPE = BlockTypeBuilder
                .builder(BlockLargeFernBehavior.class)
                .vanillaBlock(VanillaBlockId.LARGE_FERN)
                .setProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
                .setBlockBaseComponentSupplier(BlockTallWheatSeedDropableBaseComponentImpl::new)
                .build();
    }

    public static void initGlass() {
        BlockTypes.GLASS_TYPE = BlockTypeBuilder
                .builder(BlockGlassBehavior.class)
                .vanillaBlock(VanillaBlockId.GLASS)
                .setBlockBaseComponentSupplier(BlockGlassBaseComponentImpl::new)
                .build();
    }

    public static void initBarrel() {
        BlockTypes.BARREL_TYPE = BlockTypeBuilder
                .builder(BlockBarrelBehavior.class)
                .vanillaBlock(VanillaBlockId.BARREL)
                .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
                .setBlockBaseComponentSupplier(BlockBarrelBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.BARREL_TYPE)
                .build();
    }

    public static void initChest() {
        BlockTypes.CHEST_TYPE = BlockTypeBuilder
                .builder(BlockChestBehavior.class)
                .vanillaBlock(VanillaBlockId.CHEST)
                .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .setBlockBaseComponentSupplier(BlockChestBaseComponentImpl::new)
                .bindBlockEntity(BlockEntityTypes.CHEST_TYPE)
                .build();
    }

    public static void initCraftingTable() {
        BlockTypes.CRAFTING_TABLE_TYPE = BlockTypeBuilder
                .builder(BlockCraftingTableBehavior.class)
                .vanillaBlock(VanillaBlockId.CRAFTING_TABLE)
                .setBlockBaseComponentSupplier(BlockCraftingTableBaseComponentImpl::new)
                .build();
    }

    public static void initStairs() {
        BlockTypes.ACACIA_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockAcaciaStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.ACACIA_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.ANDESITE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockAndesiteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.ANDESITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BAMBOO_MOSAIC_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockBambooMosaicStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BAMBOO_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockBambooStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.BAMBOO_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BIRCH_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockBirchStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.BIRCH_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BLACKSTONE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockBlackstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.BLACKSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.BRICK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CHERRY_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockCherryStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.CHERRY_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.COBBLED_DEEPSLATE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockCobbledDeepslateStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CRIMSON_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockCrimsonStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.CRIMSON_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_OAK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockDarkOakStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.DARK_OAK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DARK_PRISMARINE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockDarkPrismarineStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.DARK_PRISMARINE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DEEPSLATE_BRICK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockDeepslateBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DEEPSLATE_TILE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockDeepslateTileStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.DIORITE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockDioriteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.DIORITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.END_BRICK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockEndBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.END_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.EXPOSED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockExposedCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.GRANITE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockGraniteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.GRANITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.JUNGLE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockJungleStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.JUNGLE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MANGROVE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockMangroveStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.MANGROVE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MOSSY_COBBLESTONE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockMossyCobblestoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MOSSY_STONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockMossyStoneBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.MOSSY_STONE_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.MUD_BRICK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockMudBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.MUD_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.NETHER_BRICK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockNetherBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.NETHER_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.NORMAL_STONE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockNormalStoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.NORMAL_STONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.OAK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockOakStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.OAK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.OXIDIZED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockOxidizedCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_ANDESITE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockPolishedAndesiteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_ANDESITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_BLACKSTONE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_DEEPSLATE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockPolishedDeepslateStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_DIORITE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockPolishedDioriteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_DIORITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_GRANITE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockPolishedGraniteStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_GRANITE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.POLISHED_TUFF_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockPolishedTuffStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.POLISHED_TUFF_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PRISMARINE_BRICKS_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockPrismarineBricksStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.PRISMARINE_BRICKS_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PRISMARINE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockPrismarineStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.PRISMARINE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.PURPUR_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockPurpurStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.PURPUR_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.QUARTZ_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockQuartzStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.QUARTZ_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.RED_NETHER_BRICK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockRedNetherBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.RED_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockRedSandstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.RED_SANDSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockSandstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.SANDSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_QUARTZ_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockSmoothQuartzStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.SMOOTH_QUARTZ_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockSmoothRedSandstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.SMOOTH_RED_SANDSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SMOOTH_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockSmoothSandstoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.SMOOTH_SANDSTONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.SPRUCE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockSpruceStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.SPRUCE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.STONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockStoneBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.STONE_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.STONE_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockStoneStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.STONE_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.TUFF_BRICK_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockTuffBrickStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.TUFF_BRICK_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.TUFF_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockTuffStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.TUFF_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WARPED_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockWarpedStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WARPED_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockWaxedCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockWaxedExposedCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockWaxedOxidizedCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockWaxedWeatheredCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
        BlockTypes.WEATHERED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
                .builder(BlockWeatheredCutCopperStairsBehavior.class)
                .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
                .addComponent(BlockStateDataComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
                .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
                .build();
    }

    public static void initColoredTorch() {
        BlockTypes.COLORED_TORCH_BP_TYPE = BlockTypeBuilder
                .builder(BlockColoredTorchBpBehavior.class)
                .vanillaBlock(VanillaBlockId.COLORED_TORCH_BP)
                .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockColoredTorchBaseComponentImpl::new)
                .build();
        BlockTypes.COLORED_TORCH_RG_TYPE = BlockTypeBuilder
                .builder(BlockColoredTorchRgBehavior.class)
                .vanillaBlock(VanillaBlockId.COLORED_TORCH_RG)
                .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockColoredTorchBaseComponentImpl::new)
                .build();
    }

    public static void initTorch() {
        BlockTypes.REDSTONE_TORCH_TYPE = BlockTypeBuilder
                .builder(BlockRedstoneTorchBehavior.class)
                .vanillaBlock(VanillaBlockId.REDSTONE_TORCH)
                .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.SOUL_TORCH_TYPE = BlockTypeBuilder
                .builder(BlockSoulTorchBehavior.class)
                .vanillaBlock(VanillaBlockId.SOUL_TORCH)
                .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.TORCH_TYPE = BlockTypeBuilder
                .builder(BlockTorchBehavior.class)
                .vanillaBlock(VanillaBlockId.TORCH)
                .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();

        BlockTypes.UNDERWATER_TORCH_TYPE = BlockTypeBuilder
                .builder(BlockUnderwaterTorchBehavior.class)
                .vanillaBlock(VanillaBlockId.UNDERWATER_TORCH)
                .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
        BlockTypes.UNLIT_REDSTONE_TORCH_TYPE = BlockTypeBuilder
                .builder(BlockUnlitRedstoneTorchBehavior.class)
                .vanillaBlock(VanillaBlockId.UNLIT_REDSTONE_TORCH)
                .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
                .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
                .build();
    }
}
