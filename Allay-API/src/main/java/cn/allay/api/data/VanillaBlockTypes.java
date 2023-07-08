package cn.allay.api.data;

import cn.allay.api.block.impl.*;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;

/**
 * Allay Project <p>
 * @author daoge_cmd
 */
public interface VanillaBlockTypes {
  BlockType<BlockAcaciaButton> ACACIA_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaButton.class)
          .vanillaBlock(VanillaBlockId.ACACIA_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaDoor> ACACIA_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaDoor.class)
          .vanillaBlock(VanillaBlockId.ACACIA_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaFence> ACACIA_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaFence.class)
          .vanillaBlock(VanillaBlockId.ACACIA_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaFenceGate> ACACIA_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaFenceGate.class)
          .vanillaBlock(VanillaBlockId.ACACIA_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaHangingSign> ACACIA_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaHangingSign.class)
          .vanillaBlock(VanillaBlockId.ACACIA_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaLog> ACACIA_LOG_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaLog.class)
          .vanillaBlock(VanillaBlockId.ACACIA_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaPressurePlate> ACACIA_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaPressurePlate.class)
          .vanillaBlock(VanillaBlockId.ACACIA_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaStairs> ACACIA_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaStairs.class)
          .vanillaBlock(VanillaBlockId.ACACIA_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaStandingSign> ACACIA_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaStandingSign.class)
          .vanillaBlock(VanillaBlockId.ACACIA_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaTrapdoor> ACACIA_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaTrapdoor.class)
          .vanillaBlock(VanillaBlockId.ACACIA_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAcaciaWallSign> ACACIA_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaWallSign.class)
          .vanillaBlock(VanillaBlockId.ACACIA_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockActivatorRail> ACTIVATOR_RAIL_TYPE = BlockTypeBuilder
          .builder(BlockActivatorRail.class)
          .vanillaBlock(VanillaBlockId.ACTIVATOR_RAIL, true)
          .withProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION_6, VanillaBlockPropertyTypes.RAIL_DATA_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockAir> AIR_TYPE = BlockTypeBuilder
          .builder(BlockAir.class)
          .vanillaBlock(VanillaBlockId.AIR, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAllow> ALLOW_TYPE = BlockTypeBuilder
          .builder(BlockAllow.class)
          .vanillaBlock(VanillaBlockId.ALLOW, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAmethystBlock> AMETHYST_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockAmethystBlock.class)
          .vanillaBlock(VanillaBlockId.AMETHYST_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAmethystCluster> AMETHYST_CLUSTER_TYPE = BlockTypeBuilder
          .builder(BlockAmethystCluster.class)
          .vanillaBlock(VanillaBlockId.AMETHYST_CLUSTER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAncientDebris> ANCIENT_DEBRIS_TYPE = BlockTypeBuilder
          .builder(BlockAncientDebris.class)
          .vanillaBlock(VanillaBlockId.ANCIENT_DEBRIS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAndesiteStairs> ANDESITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockAndesiteStairs.class)
          .vanillaBlock(VanillaBlockId.ANDESITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAnvil> ANVIL_TYPE = BlockTypeBuilder
          .builder(BlockAnvil.class)
          .vanillaBlock(VanillaBlockId.ANVIL, true)
          .withProperties(VanillaBlockPropertyTypes.DAMAGE, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockAzalea> AZALEA_TYPE = BlockTypeBuilder
          .builder(BlockAzalea.class)
          .vanillaBlock(VanillaBlockId.AZALEA, true)
          .addBasicComponents()
          .build();

  BlockType<BlockAzaleaLeaves> AZALEA_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockAzaleaLeaves.class)
          .vanillaBlock(VanillaBlockId.AZALEA_LEAVES, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockAzaleaLeavesFlowered> AZALEA_LEAVES_FLOWERED_TYPE = BlockTypeBuilder
          .builder(BlockAzaleaLeavesFlowered.class)
          .vanillaBlock(VanillaBlockId.AZALEA_LEAVES_FLOWERED, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBamboo> BAMBOO_TYPE = BlockTypeBuilder
          .builder(BlockBamboo.class)
          .vanillaBlock(VanillaBlockId.BAMBOO, true)
          .withProperties(VanillaBlockPropertyTypes.BAMBOO_LEAF_SIZE, VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.BAMBOO_STALK_THICKNESS)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooBlock> BAMBOO_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBambooBlock.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooButton> BAMBOO_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockBambooButton.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooDoor> BAMBOO_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockBambooDoor.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooDoubleSlab> BAMBOO_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooFence> BAMBOO_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockBambooFence.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooFenceGate> BAMBOO_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockBambooFenceGate.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooHangingSign> BAMBOO_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBambooHangingSign.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaic> BAMBOO_MOSAIC_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaic.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicDoubleSlab> BAMBOO_MOSAIC_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicSlab> BAMBOO_MOSAIC_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicSlab.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooMosaicStairs> BAMBOO_MOSAIC_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBambooMosaicStairs.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_MOSAIC_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooPlanks> BAMBOO_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockBambooPlanks.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_PLANKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooPressurePlate> BAMBOO_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockBambooPressurePlate.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooSapling> BAMBOO_SAPLING_TYPE = BlockTypeBuilder
          .builder(BlockBambooSapling.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_SAPLING, true)
          .withProperties(VanillaBlockPropertyTypes.SAPLING_TYPE, VanillaBlockPropertyTypes.AGE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooSlab> BAMBOO_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBambooSlab.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooStairs> BAMBOO_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBambooStairs.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooStandingSign> BAMBOO_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBambooStandingSign.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooTrapdoor> BAMBOO_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockBambooTrapdoor.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBambooWallSign> BAMBOO_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBambooWallSign.class)
          .vanillaBlock(VanillaBlockId.BAMBOO_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBarrel> BARREL_TYPE = BlockTypeBuilder
          .builder(BlockBarrel.class)
          .vanillaBlock(VanillaBlockId.BARREL, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.OPEN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBarrier> BARRIER_TYPE = BlockTypeBuilder
          .builder(BlockBarrier.class)
          .vanillaBlock(VanillaBlockId.BARRIER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBasalt> BASALT_TYPE = BlockTypeBuilder
          .builder(BlockBasalt.class)
          .vanillaBlock(VanillaBlockId.BASALT, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBeacon> BEACON_TYPE = BlockTypeBuilder
          .builder(BlockBeacon.class)
          .vanillaBlock(VanillaBlockId.BEACON, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBed> BED_TYPE = BlockTypeBuilder
          .builder(BlockBed.class)
          .vanillaBlock(VanillaBlockId.BED, true)
          .withProperties(VanillaBlockPropertyTypes.HEAD_PIECE_BIT, VanillaBlockPropertyTypes.OCCUPIED_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBedrock> BEDROCK_TYPE = BlockTypeBuilder
          .builder(BlockBedrock.class)
          .vanillaBlock(VanillaBlockId.BEDROCK, true)
          .withProperties(VanillaBlockPropertyTypes.INFINIBURN_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBeeNest> BEE_NEST_TYPE = BlockTypeBuilder
          .builder(BlockBeeNest.class)
          .vanillaBlock(VanillaBlockId.BEE_NEST, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockBeehive> BEEHIVE_TYPE = BlockTypeBuilder
          .builder(BlockBeehive.class)
          .vanillaBlock(VanillaBlockId.BEEHIVE, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.HONEY_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockBeetroot> BEETROOT_TYPE = BlockTypeBuilder
          .builder(BlockBeetroot.class)
          .vanillaBlock(VanillaBlockId.BEETROOT, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockBell> BELL_TYPE = BlockTypeBuilder
          .builder(BlockBell.class)
          .vanillaBlock(VanillaBlockId.BELL, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.TOGGLE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBigDripleaf> BIG_DRIPLEAF_TYPE = BlockTypeBuilder
          .builder(BlockBigDripleaf.class)
          .vanillaBlock(VanillaBlockId.BIG_DRIPLEAF, true)
          .withProperties(VanillaBlockPropertyTypes.BIG_DRIPLEAF_TILT, VanillaBlockPropertyTypes.BIG_DRIPLEAF_HEAD, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchButton> BIRCH_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockBirchButton.class)
          .vanillaBlock(VanillaBlockId.BIRCH_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchDoor> BIRCH_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockBirchDoor.class)
          .vanillaBlock(VanillaBlockId.BIRCH_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchFence> BIRCH_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockBirchFence.class)
          .vanillaBlock(VanillaBlockId.BIRCH_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchFenceGate> BIRCH_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockBirchFenceGate.class)
          .vanillaBlock(VanillaBlockId.BIRCH_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchHangingSign> BIRCH_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBirchHangingSign.class)
          .vanillaBlock(VanillaBlockId.BIRCH_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchLog> BIRCH_LOG_TYPE = BlockTypeBuilder
          .builder(BlockBirchLog.class)
          .vanillaBlock(VanillaBlockId.BIRCH_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchPressurePlate> BIRCH_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockBirchPressurePlate.class)
          .vanillaBlock(VanillaBlockId.BIRCH_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchStairs> BIRCH_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBirchStairs.class)
          .vanillaBlock(VanillaBlockId.BIRCH_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchStandingSign> BIRCH_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBirchStandingSign.class)
          .vanillaBlock(VanillaBlockId.BIRCH_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchTrapdoor> BIRCH_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockBirchTrapdoor.class)
          .vanillaBlock(VanillaBlockId.BIRCH_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBirchWallSign> BIRCH_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockBirchWallSign.class)
          .vanillaBlock(VanillaBlockId.BIRCH_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackCandle> BLACK_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockBlackCandle.class)
          .vanillaBlock(VanillaBlockId.BLACK_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackCandleCake> BLACK_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockBlackCandleCake.class)
          .vanillaBlock(VanillaBlockId.BLACK_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackCarpet> BLACK_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBlackCarpet.class)
          .vanillaBlock(VanillaBlockId.BLACK_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackGlazedTerracotta> BLACK_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBlackGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.BLACK_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackWool> BLACK_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockBlackWool.class)
          .vanillaBlock(VanillaBlockId.BLACK_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstone> BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockBlackstone.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneDoubleSlab> BLACKSTONE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneSlab> BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneSlab.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneStairs> BLACKSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneStairs.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlackstoneWall> BLACKSTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockBlackstoneWall.class)
          .vanillaBlock(VanillaBlockId.BLACKSTONE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH)
          .addBasicComponents()
          .build();

  BlockType<BlockBlastFurnace> BLAST_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockBlastFurnace.class)
          .vanillaBlock(VanillaBlockId.BLAST_FURNACE, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueCandle> BLUE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockBlueCandle.class)
          .vanillaBlock(VanillaBlockId.BLUE_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueCandleCake> BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockBlueCandleCake.class)
          .vanillaBlock(VanillaBlockId.BLUE_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueCarpet> BLUE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBlueCarpet.class)
          .vanillaBlock(VanillaBlockId.BLUE_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueGlazedTerracotta> BLUE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBlueGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.BLUE_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueIce> BLUE_ICE_TYPE = BlockTypeBuilder
          .builder(BlockBlueIce.class)
          .vanillaBlock(VanillaBlockId.BLUE_ICE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBlueWool> BLUE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockBlueWool.class)
          .vanillaBlock(VanillaBlockId.BLUE_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBoneBlock> BONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBoneBlock.class)
          .vanillaBlock(VanillaBlockId.BONE_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockBookshelf> BOOKSHELF_TYPE = BlockTypeBuilder
          .builder(BlockBookshelf.class)
          .vanillaBlock(VanillaBlockId.BOOKSHELF, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBorderBlock> BORDER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBorderBlock.class)
          .vanillaBlock(VanillaBlockId.BORDER_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH)
          .addBasicComponents()
          .build();

  BlockType<BlockBrainCoral> BRAIN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockBrainCoral.class)
          .vanillaBlock(VanillaBlockId.BRAIN_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBrewingStand> BREWING_STAND_TYPE = BlockTypeBuilder
          .builder(BlockBrewingStand.class)
          .vanillaBlock(VanillaBlockId.BREWING_STAND, true)
          .withProperties(VanillaBlockPropertyTypes.BREWING_STAND_SLOT_C_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, VanillaBlockPropertyTypes.BREWING_STAND_SLOT_B_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBrickBlock> BRICK_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBrickBlock.class)
          .vanillaBlock(VanillaBlockId.BRICK_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBrickStairs> BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockBrickStairs.class)
          .vanillaBlock(VanillaBlockId.BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownCandle> BROWN_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockBrownCandle.class)
          .vanillaBlock(VanillaBlockId.BROWN_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownCandleCake> BROWN_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockBrownCandleCake.class)
          .vanillaBlock(VanillaBlockId.BROWN_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownCarpet> BROWN_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockBrownCarpet.class)
          .vanillaBlock(VanillaBlockId.BROWN_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownGlazedTerracotta> BROWN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockBrownGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.BROWN_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownMushroom> BROWN_MUSHROOM_TYPE = BlockTypeBuilder
          .builder(BlockBrownMushroom.class)
          .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownMushroomBlock> BROWN_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockBrownMushroomBlock.class)
          .vanillaBlock(VanillaBlockId.BROWN_MUSHROOM_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockBrownWool> BROWN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockBrownWool.class)
          .vanillaBlock(VanillaBlockId.BROWN_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBubbleColumn> BUBBLE_COLUMN_TYPE = BlockTypeBuilder
          .builder(BlockBubbleColumn.class)
          .vanillaBlock(VanillaBlockId.BUBBLE_COLUMN, true)
          .withProperties(VanillaBlockPropertyTypes.DRAG_DOWN)
          .addBasicComponents()
          .build();

  BlockType<BlockBubbleCoral> BUBBLE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockBubbleCoral.class)
          .vanillaBlock(VanillaBlockId.BUBBLE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockBuddingAmethyst> BUDDING_AMETHYST_TYPE = BlockTypeBuilder
          .builder(BlockBuddingAmethyst.class)
          .vanillaBlock(VanillaBlockId.BUDDING_AMETHYST, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCactus> CACTUS_TYPE = BlockTypeBuilder
          .builder(BlockCactus.class)
          .vanillaBlock(VanillaBlockId.CACTUS, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockCake> CAKE_TYPE = BlockTypeBuilder
          .builder(BlockCake.class)
          .vanillaBlock(VanillaBlockId.CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.BITE_COUNTER)
          .addBasicComponents()
          .build();

  BlockType<BlockCalcite> CALCITE_TYPE = BlockTypeBuilder
          .builder(BlockCalcite.class)
          .vanillaBlock(VanillaBlockId.CALCITE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCalibratedSculkSensor> CALIBRATED_SCULK_SENSOR_TYPE = BlockTypeBuilder
          .builder(BlockCalibratedSculkSensor.class)
          .vanillaBlock(VanillaBlockId.CALIBRATED_SCULK_SENSOR, true)
          .withProperties(VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCamera> CAMERA_TYPE = BlockTypeBuilder
          .builder(BlockCamera.class)
          .vanillaBlock(VanillaBlockId.CAMERA, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCampfire> CAMPFIRE_TYPE = BlockTypeBuilder
          .builder(BlockCampfire.class)
          .vanillaBlock(VanillaBlockId.CAMPFIRE, true)
          .withProperties(VanillaBlockPropertyTypes.EXTINGUISHED, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCandle> CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockCandle.class)
          .vanillaBlock(VanillaBlockId.CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockCandleCake> CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockCandleCake.class)
          .vanillaBlock(VanillaBlockId.CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCarrots> CARROTS_TYPE = BlockTypeBuilder
          .builder(BlockCarrots.class)
          .vanillaBlock(VanillaBlockId.CARROTS, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockCartographyTable> CARTOGRAPHY_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockCartographyTable.class)
          .vanillaBlock(VanillaBlockId.CARTOGRAPHY_TABLE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCarvedPumpkin> CARVED_PUMPKIN_TYPE = BlockTypeBuilder
          .builder(BlockCarvedPumpkin.class)
          .vanillaBlock(VanillaBlockId.CARVED_PUMPKIN, true)
          .withProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCauldron> CAULDRON_TYPE = BlockTypeBuilder
          .builder(BlockCauldron.class)
          .vanillaBlock(VanillaBlockId.CAULDRON, true)
          .withProperties(VanillaBlockPropertyTypes.FILL_LEVEL, VanillaBlockPropertyTypes.CAULDRON_LIQUID)
          .addBasicComponents()
          .build();

  BlockType<BlockCaveVines> CAVE_VINES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVines.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES, true)
          .withProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockCaveVinesBodyWithBerries> CAVE_VINES_BODY_WITH_BERRIES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVinesBodyWithBerries.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES_BODY_WITH_BERRIES, true)
          .withProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockCaveVinesHeadWithBerries> CAVE_VINES_HEAD_WITH_BERRIES_TYPE = BlockTypeBuilder
          .builder(BlockCaveVinesHeadWithBerries.class)
          .vanillaBlock(VanillaBlockId.CAVE_VINES_HEAD_WITH_BERRIES, true)
          .withProperties(VanillaBlockPropertyTypes.GROWING_PLANT_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockChain> CHAIN_TYPE = BlockTypeBuilder
          .builder(BlockChain.class)
          .vanillaBlock(VanillaBlockId.CHAIN, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockChainCommandBlock> CHAIN_COMMAND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockChainCommandBlock.class)
          .vanillaBlock(VanillaBlockId.CHAIN_COMMAND_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockChemicalHeat> CHEMICAL_HEAT_TYPE = BlockTypeBuilder
          .builder(BlockChemicalHeat.class)
          .vanillaBlock(VanillaBlockId.CHEMICAL_HEAT, true)
          .addBasicComponents()
          .build();

  BlockType<BlockChemistryTable> CHEMISTRY_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockChemistryTable.class)
          .vanillaBlock(VanillaBlockId.CHEMISTRY_TABLE, true)
          .withProperties(VanillaBlockPropertyTypes.CHEMISTRY_TABLE_TYPE, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryButton> CHERRY_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockCherryButton.class)
          .vanillaBlock(VanillaBlockId.CHERRY_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryDoor> CHERRY_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockCherryDoor.class)
          .vanillaBlock(VanillaBlockId.CHERRY_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryDoubleSlab> CHERRY_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCherryDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.CHERRY_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryFence> CHERRY_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockCherryFence.class)
          .vanillaBlock(VanillaBlockId.CHERRY_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryFenceGate> CHERRY_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockCherryFenceGate.class)
          .vanillaBlock(VanillaBlockId.CHERRY_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryHangingSign> CHERRY_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCherryHangingSign.class)
          .vanillaBlock(VanillaBlockId.CHERRY_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryLeaves> CHERRY_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockCherryLeaves.class)
          .vanillaBlock(VanillaBlockId.CHERRY_LEAVES, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryLog> CHERRY_LOG_TYPE = BlockTypeBuilder
          .builder(BlockCherryLog.class)
          .vanillaBlock(VanillaBlockId.CHERRY_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryPlanks> CHERRY_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockCherryPlanks.class)
          .vanillaBlock(VanillaBlockId.CHERRY_PLANKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryPressurePlate> CHERRY_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockCherryPressurePlate.class)
          .vanillaBlock(VanillaBlockId.CHERRY_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockCherrySapling> CHERRY_SAPLING_TYPE = BlockTypeBuilder
          .builder(BlockCherrySapling.class)
          .vanillaBlock(VanillaBlockId.CHERRY_SAPLING, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherrySlab> CHERRY_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCherrySlab.class)
          .vanillaBlock(VanillaBlockId.CHERRY_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryStairs> CHERRY_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCherryStairs.class)
          .vanillaBlock(VanillaBlockId.CHERRY_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryStandingSign> CHERRY_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCherryStandingSign.class)
          .vanillaBlock(VanillaBlockId.CHERRY_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryTrapdoor> CHERRY_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockCherryTrapdoor.class)
          .vanillaBlock(VanillaBlockId.CHERRY_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryWallSign> CHERRY_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCherryWallSign.class)
          .vanillaBlock(VanillaBlockId.CHERRY_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCherryWood> CHERRY_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockCherryWood.class)
          .vanillaBlock(VanillaBlockId.CHERRY_WOOD, true)
          .withProperties(VanillaBlockPropertyTypes.STRIPPED_BIT, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockChest> CHEST_TYPE = BlockTypeBuilder
          .builder(BlockChest.class)
          .vanillaBlock(VanillaBlockId.CHEST, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledBookshelf> CHISELED_BOOKSHELF_TYPE = BlockTypeBuilder
          .builder(BlockChiseledBookshelf.class)
          .vanillaBlock(VanillaBlockId.CHISELED_BOOKSHELF, true)
          .withProperties(VanillaBlockPropertyTypes.BOOKS_STORED, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledDeepslate> CHISELED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockChiseledDeepslate.class)
          .vanillaBlock(VanillaBlockId.CHISELED_DEEPSLATE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledNetherBricks> CHISELED_NETHER_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockChiseledNetherBricks.class)
          .vanillaBlock(VanillaBlockId.CHISELED_NETHER_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockChiseledPolishedBlackstone> CHISELED_POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockChiseledPolishedBlackstone.class)
          .vanillaBlock(VanillaBlockId.CHISELED_POLISHED_BLACKSTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockChorusFlower> CHORUS_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockChorusFlower.class)
          .vanillaBlock(VanillaBlockId.CHORUS_FLOWER, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_6)
          .addBasicComponents()
          .build();

  BlockType<BlockChorusPlant> CHORUS_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockChorusPlant.class)
          .vanillaBlock(VanillaBlockId.CHORUS_PLANT, true)
          .addBasicComponents()
          .build();

  BlockType<BlockClay> CLAY_TYPE = BlockTypeBuilder
          .builder(BlockClay.class)
          .vanillaBlock(VanillaBlockId.CLAY, true)
          .addBasicComponents()
          .build();

  BlockType<BlockClientRequestPlaceholderBlock> CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockClientRequestPlaceholderBlock.class)
          .vanillaBlock(VanillaBlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCoalBlock> COAL_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCoalBlock.class)
          .vanillaBlock(VanillaBlockId.COAL_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCoalOre> COAL_ORE_TYPE = BlockTypeBuilder
          .builder(BlockCoalOre.class)
          .vanillaBlock(VanillaBlockId.COAL_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslate> COBBLED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslate.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateDoubleSlab> COBBLED_DEEPSLATE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateSlab> COBBLED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateSlab.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateStairs> COBBLED_DEEPSLATE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateStairs.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCobbledDeepslateWall> COBBLED_DEEPSLATE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockCobbledDeepslateWall.class)
          .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH)
          .addBasicComponents()
          .build();

  BlockType<BlockCobblestone> COBBLESTONE_TYPE = BlockTypeBuilder
          .builder(BlockCobblestone.class)
          .vanillaBlock(VanillaBlockId.COBBLESTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCobblestoneWall> COBBLESTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockCobblestoneWall.class)
          .vanillaBlock(VanillaBlockId.COBBLESTONE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, VanillaBlockPropertyTypes.WALL_BLOCK_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockCocoa> COCOA_TYPE = BlockTypeBuilder
          .builder(BlockCocoa.class)
          .vanillaBlock(VanillaBlockId.COCOA, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_3, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockColoredTorchBp> COLORED_TORCH_BP_TYPE = BlockTypeBuilder
          .builder(BlockColoredTorchBp.class)
          .vanillaBlock(VanillaBlockId.COLORED_TORCH_BP, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockColoredTorchRg> COLORED_TORCH_RG_TYPE = BlockTypeBuilder
          .builder(BlockColoredTorchRg.class)
          .vanillaBlock(VanillaBlockId.COLORED_TORCH_RG, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCommandBlock> COMMAND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCommandBlock.class)
          .vanillaBlock(VanillaBlockId.COMMAND_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockComposter> COMPOSTER_TYPE = BlockTypeBuilder
          .builder(BlockComposter.class)
          .vanillaBlock(VanillaBlockId.COMPOSTER, true)
          .withProperties(VanillaBlockPropertyTypes.COMPOSTER_FILL_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockConcrete> CONCRETE_TYPE = BlockTypeBuilder
          .builder(BlockConcrete.class)
          .vanillaBlock(VanillaBlockId.CONCRETE, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockConcretePowder> CONCRETE_POWDER_TYPE = BlockTypeBuilder
          .builder(BlockConcretePowder.class)
          .vanillaBlock(VanillaBlockId.CONCRETE_POWDER, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockConduit> CONDUIT_TYPE = BlockTypeBuilder
          .builder(BlockConduit.class)
          .vanillaBlock(VanillaBlockId.CONDUIT, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCopperBlock> COPPER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCopperBlock.class)
          .vanillaBlock(VanillaBlockId.COPPER_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCopperOre> COPPER_ORE_TYPE = BlockTypeBuilder
          .builder(BlockCopperOre.class)
          .vanillaBlock(VanillaBlockId.COPPER_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralBlock> CORAL_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockCoralBlock.class)
          .vanillaBlock(VanillaBlockId.CORAL_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_COLOR, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFan> CORAL_FAN_TYPE = BlockTypeBuilder
          .builder(BlockCoralFan.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION, VanillaBlockPropertyTypes.CORAL_COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanDead> CORAL_FAN_DEAD_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanDead.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_DEAD, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_FAN_DIRECTION, VanillaBlockPropertyTypes.CORAL_COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanHang> CORAL_FAN_HANG_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHang.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanHang2> CORAL_FAN_HANG2_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHang2.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG2, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCoralFanHang3> CORAL_FAN_HANG3_TYPE = BlockTypeBuilder
          .builder(BlockCoralFanHang3.class)
          .vanillaBlock(VanillaBlockId.CORAL_FAN_HANG3, true)
          .withProperties(VanillaBlockPropertyTypes.CORAL_HANG_TYPE_BIT, VanillaBlockPropertyTypes.CORAL_DIRECTION, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedDeepslateBricks> CRACKED_DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedDeepslateBricks.class)
          .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedDeepslateTiles> CRACKED_DEEPSLATE_TILES_TYPE = BlockTypeBuilder
          .builder(BlockCrackedDeepslateTiles.class)
          .vanillaBlock(VanillaBlockId.CRACKED_DEEPSLATE_TILES, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedNetherBricks> CRACKED_NETHER_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedNetherBricks.class)
          .vanillaBlock(VanillaBlockId.CRACKED_NETHER_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrackedPolishedBlackstoneBricks> CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockCrackedPolishedBlackstoneBricks.class)
          .vanillaBlock(VanillaBlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCraftingTable> CRAFTING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockCraftingTable.class)
          .vanillaBlock(VanillaBlockId.CRAFTING_TABLE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonButton> CRIMSON_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonButton.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonDoor> CRIMSON_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonDoor.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonDoubleSlab> CRIMSON_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonFence> CRIMSON_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFence.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonFenceGate> CRIMSON_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFenceGate.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonFungus> CRIMSON_FUNGUS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonFungus.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_FUNGUS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonHangingSign> CRIMSON_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonHangingSign.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonHyphae> CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonHyphae.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_HYPHAE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonNylium> CRIMSON_NYLIUM_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonNylium.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_NYLIUM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonPlanks> CRIMSON_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonPlanks.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_PLANKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonPressurePlate> CRIMSON_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonPressurePlate.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonRoots> CRIMSON_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonRoots.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_ROOTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonSlab> CRIMSON_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonSlab.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonStairs> CRIMSON_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonStairs.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonStandingSign> CRIMSON_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonStandingSign.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonStem> CRIMSON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonStem.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonTrapdoor> CRIMSON_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonTrapdoor.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCrimsonWallSign> CRIMSON_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockCrimsonWallSign.class)
          .vanillaBlock(VanillaBlockId.CRIMSON_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCryingObsidian> CRYING_OBSIDIAN_TYPE = BlockTypeBuilder
          .builder(BlockCryingObsidian.class)
          .vanillaBlock(VanillaBlockId.CRYING_OBSIDIAN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCutCopper> CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockCutCopper.class)
          .vanillaBlock(VanillaBlockId.CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCutCopperSlab> CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCutCopperStairs> CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockCutCopperStairs.class)
          .vanillaBlock(VanillaBlockId.CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanCandle> CYAN_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockCyanCandle.class)
          .vanillaBlock(VanillaBlockId.CYAN_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanCandleCake> CYAN_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockCyanCandleCake.class)
          .vanillaBlock(VanillaBlockId.CYAN_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanCarpet> CYAN_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockCyanCarpet.class)
          .vanillaBlock(VanillaBlockId.CYAN_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanGlazedTerracotta> CYAN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockCyanGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.CYAN_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockCyanWool> CYAN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockCyanWool.class)
          .vanillaBlock(VanillaBlockId.CYAN_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakButton> DARK_OAK_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakButton.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakDoor> DARK_OAK_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakDoor.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakFence> DARK_OAK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakFence.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakFenceGate> DARK_OAK_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakFenceGate.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakHangingSign> DARK_OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakHangingSign.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakLog> DARK_OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakLog.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakPressurePlate> DARK_OAK_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakPressurePlate.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakStairs> DARK_OAK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakStairs.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkOakTrapdoor> DARK_OAK_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakTrapdoor.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkPrismarineStairs> DARK_PRISMARINE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDarkPrismarineStairs.class)
          .vanillaBlock(VanillaBlockId.DARK_PRISMARINE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkoakStandingSign> DARKOAK_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkoakStandingSign.class)
          .vanillaBlock(VanillaBlockId.DARKOAK_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDarkoakWallSign> DARKOAK_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockDarkoakWallSign.class)
          .vanillaBlock(VanillaBlockId.DARKOAK_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDaylightDetector> DAYLIGHT_DETECTOR_TYPE = BlockTypeBuilder
          .builder(BlockDaylightDetector.class)
          .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDaylightDetectorInverted> DAYLIGHT_DETECTOR_INVERTED_TYPE = BlockTypeBuilder
          .builder(BlockDaylightDetectorInverted.class)
          .vanillaBlock(VanillaBlockId.DAYLIGHT_DETECTOR_INVERTED, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadBrainCoral> DEAD_BRAIN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadBrainCoral.class)
          .vanillaBlock(VanillaBlockId.DEAD_BRAIN_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadBubbleCoral> DEAD_BUBBLE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadBubbleCoral.class)
          .vanillaBlock(VanillaBlockId.DEAD_BUBBLE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadFireCoral> DEAD_FIRE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadFireCoral.class)
          .vanillaBlock(VanillaBlockId.DEAD_FIRE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadHornCoral> DEAD_HORN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadHornCoral.class)
          .vanillaBlock(VanillaBlockId.DEAD_HORN_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadTubeCoral> DEAD_TUBE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadTubeCoral.class)
          .vanillaBlock(VanillaBlockId.DEAD_TUBE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeadbush> DEADBUSH_TYPE = BlockTypeBuilder
          .builder(BlockDeadbush.class)
          .vanillaBlock(VanillaBlockId.DEADBUSH, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDecoratedPot> DECORATED_POT_TYPE = BlockTypeBuilder
          .builder(BlockDecoratedPot.class)
          .vanillaBlock(VanillaBlockId.DECORATED_POT, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslate> DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslate.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickDoubleSlab> DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickSlab> DEEPSLATE_BRICK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickSlab.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickStairs> DEEPSLATE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickStairs.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBrickWall> DEEPSLATE_BRICK_WALL_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBrickWall.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICK_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateBricks> DEEPSLATE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateBricks.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateCoalOre> DEEPSLATE_COAL_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateCoalOre.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_COAL_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateCopperOre> DEEPSLATE_COPPER_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateCopperOre.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_COPPER_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateDiamondOre> DEEPSLATE_DIAMOND_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateDiamondOre.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_DIAMOND_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateEmeraldOre> DEEPSLATE_EMERALD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateEmeraldOre.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_EMERALD_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateGoldOre> DEEPSLATE_GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateGoldOre.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_GOLD_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateIronOre> DEEPSLATE_IRON_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateIronOre.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_IRON_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateLapisOre> DEEPSLATE_LAPIS_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateLapisOre.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_LAPIS_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateRedstoneOre> DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateRedstoneOre.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_REDSTONE_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileDoubleSlab> DEEPSLATE_TILE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileSlab> DEEPSLATE_TILE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileSlab.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileStairs> DEEPSLATE_TILE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileStairs.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTileWall> DEEPSLATE_TILE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTileWall.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH)
          .addBasicComponents()
          .build();

  BlockType<BlockDeepslateTiles> DEEPSLATE_TILES_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateTiles.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_TILES, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDeny> DENY_TYPE = BlockTypeBuilder
          .builder(BlockDeny.class)
          .vanillaBlock(VanillaBlockId.DENY, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDetectorRail> DETECTOR_RAIL_TYPE = BlockTypeBuilder
          .builder(BlockDetectorRail.class)
          .vanillaBlock(VanillaBlockId.DETECTOR_RAIL, true)
          .withProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION_6, VanillaBlockPropertyTypes.RAIL_DATA_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDiamondBlock> DIAMOND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDiamondBlock.class)
          .vanillaBlock(VanillaBlockId.DIAMOND_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDiamondOre> DIAMOND_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDiamondOre.class)
          .vanillaBlock(VanillaBlockId.DIAMOND_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDioriteStairs> DIORITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockDioriteStairs.class)
          .vanillaBlock(VanillaBlockId.DIORITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockDirt> DIRT_TYPE = BlockTypeBuilder
          .builder(BlockDirt.class)
          .vanillaBlock(VanillaBlockId.DIRT, true)
          .withProperties(VanillaBlockPropertyTypes.DIRT_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockDirtWithRoots> DIRT_WITH_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockDirtWithRoots.class)
          .vanillaBlock(VanillaBlockId.DIRT_WITH_ROOTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDispenser> DISPENSER_TYPE = BlockTypeBuilder
          .builder(BlockDispenser.class)
          .vanillaBlock(VanillaBlockId.DISPENSER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleCutCopperSlab> DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDoubleCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoublePlant> DOUBLE_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockDoublePlant.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_PLANT, true)
          .withProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOUBLE_PLANT_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlab> DOUBLE_STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlab2> DOUBLE_STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab2.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB2, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlab3> DOUBLE_STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab3.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB3, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleStoneBlockSlab4> DOUBLE_STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
          .builder(BlockDoubleStoneBlockSlab4.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_STONE_BLOCK_SLAB4, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4)
          .addBasicComponents()
          .build();

  BlockType<BlockDoubleWoodenSlab> DOUBLE_WOODEN_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockDoubleWoodenSlab.class)
          .vanillaBlock(VanillaBlockId.DOUBLE_WOODEN_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockDragonEgg> DRAGON_EGG_TYPE = BlockTypeBuilder
          .builder(BlockDragonEgg.class)
          .vanillaBlock(VanillaBlockId.DRAGON_EGG, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDriedKelpBlock> DRIED_KELP_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDriedKelpBlock.class)
          .vanillaBlock(VanillaBlockId.DRIED_KELP_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDripstoneBlock> DRIPSTONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockDripstoneBlock.class)
          .vanillaBlock(VanillaBlockId.DRIPSTONE_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockDropper> DROPPER_TYPE = BlockTypeBuilder
          .builder(BlockDropper.class)
          .vanillaBlock(VanillaBlockId.DROPPER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockElement0> ELEMENT_0_TYPE = BlockTypeBuilder
          .builder(BlockElement0.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_0, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement1> ELEMENT_1_TYPE = BlockTypeBuilder
          .builder(BlockElement1.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_1, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement10> ELEMENT_10_TYPE = BlockTypeBuilder
          .builder(BlockElement10.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_10, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement100> ELEMENT_100_TYPE = BlockTypeBuilder
          .builder(BlockElement100.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_100, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement101> ELEMENT_101_TYPE = BlockTypeBuilder
          .builder(BlockElement101.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_101, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement102> ELEMENT_102_TYPE = BlockTypeBuilder
          .builder(BlockElement102.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_102, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement103> ELEMENT_103_TYPE = BlockTypeBuilder
          .builder(BlockElement103.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_103, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement104> ELEMENT_104_TYPE = BlockTypeBuilder
          .builder(BlockElement104.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_104, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement105> ELEMENT_105_TYPE = BlockTypeBuilder
          .builder(BlockElement105.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_105, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement106> ELEMENT_106_TYPE = BlockTypeBuilder
          .builder(BlockElement106.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_106, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement107> ELEMENT_107_TYPE = BlockTypeBuilder
          .builder(BlockElement107.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_107, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement108> ELEMENT_108_TYPE = BlockTypeBuilder
          .builder(BlockElement108.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_108, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement109> ELEMENT_109_TYPE = BlockTypeBuilder
          .builder(BlockElement109.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_109, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement11> ELEMENT_11_TYPE = BlockTypeBuilder
          .builder(BlockElement11.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_11, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement110> ELEMENT_110_TYPE = BlockTypeBuilder
          .builder(BlockElement110.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_110, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement111> ELEMENT_111_TYPE = BlockTypeBuilder
          .builder(BlockElement111.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_111, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement112> ELEMENT_112_TYPE = BlockTypeBuilder
          .builder(BlockElement112.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_112, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement113> ELEMENT_113_TYPE = BlockTypeBuilder
          .builder(BlockElement113.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_113, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement114> ELEMENT_114_TYPE = BlockTypeBuilder
          .builder(BlockElement114.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_114, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement115> ELEMENT_115_TYPE = BlockTypeBuilder
          .builder(BlockElement115.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_115, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement116> ELEMENT_116_TYPE = BlockTypeBuilder
          .builder(BlockElement116.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_116, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement117> ELEMENT_117_TYPE = BlockTypeBuilder
          .builder(BlockElement117.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_117, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement118> ELEMENT_118_TYPE = BlockTypeBuilder
          .builder(BlockElement118.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_118, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement12> ELEMENT_12_TYPE = BlockTypeBuilder
          .builder(BlockElement12.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_12, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement13> ELEMENT_13_TYPE = BlockTypeBuilder
          .builder(BlockElement13.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_13, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement14> ELEMENT_14_TYPE = BlockTypeBuilder
          .builder(BlockElement14.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_14, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement15> ELEMENT_15_TYPE = BlockTypeBuilder
          .builder(BlockElement15.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_15, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement16> ELEMENT_16_TYPE = BlockTypeBuilder
          .builder(BlockElement16.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_16, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement17> ELEMENT_17_TYPE = BlockTypeBuilder
          .builder(BlockElement17.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_17, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement18> ELEMENT_18_TYPE = BlockTypeBuilder
          .builder(BlockElement18.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_18, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement19> ELEMENT_19_TYPE = BlockTypeBuilder
          .builder(BlockElement19.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_19, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement2> ELEMENT_2_TYPE = BlockTypeBuilder
          .builder(BlockElement2.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_2, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement20> ELEMENT_20_TYPE = BlockTypeBuilder
          .builder(BlockElement20.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_20, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement21> ELEMENT_21_TYPE = BlockTypeBuilder
          .builder(BlockElement21.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_21, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement22> ELEMENT_22_TYPE = BlockTypeBuilder
          .builder(BlockElement22.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_22, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement23> ELEMENT_23_TYPE = BlockTypeBuilder
          .builder(BlockElement23.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_23, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement24> ELEMENT_24_TYPE = BlockTypeBuilder
          .builder(BlockElement24.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_24, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement25> ELEMENT_25_TYPE = BlockTypeBuilder
          .builder(BlockElement25.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_25, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement26> ELEMENT_26_TYPE = BlockTypeBuilder
          .builder(BlockElement26.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_26, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement27> ELEMENT_27_TYPE = BlockTypeBuilder
          .builder(BlockElement27.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_27, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement28> ELEMENT_28_TYPE = BlockTypeBuilder
          .builder(BlockElement28.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_28, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement29> ELEMENT_29_TYPE = BlockTypeBuilder
          .builder(BlockElement29.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_29, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement3> ELEMENT_3_TYPE = BlockTypeBuilder
          .builder(BlockElement3.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_3, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement30> ELEMENT_30_TYPE = BlockTypeBuilder
          .builder(BlockElement30.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_30, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement31> ELEMENT_31_TYPE = BlockTypeBuilder
          .builder(BlockElement31.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_31, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement32> ELEMENT_32_TYPE = BlockTypeBuilder
          .builder(BlockElement32.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_32, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement33> ELEMENT_33_TYPE = BlockTypeBuilder
          .builder(BlockElement33.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_33, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement34> ELEMENT_34_TYPE = BlockTypeBuilder
          .builder(BlockElement34.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_34, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement35> ELEMENT_35_TYPE = BlockTypeBuilder
          .builder(BlockElement35.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_35, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement36> ELEMENT_36_TYPE = BlockTypeBuilder
          .builder(BlockElement36.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_36, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement37> ELEMENT_37_TYPE = BlockTypeBuilder
          .builder(BlockElement37.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_37, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement38> ELEMENT_38_TYPE = BlockTypeBuilder
          .builder(BlockElement38.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_38, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement39> ELEMENT_39_TYPE = BlockTypeBuilder
          .builder(BlockElement39.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_39, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement4> ELEMENT_4_TYPE = BlockTypeBuilder
          .builder(BlockElement4.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_4, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement40> ELEMENT_40_TYPE = BlockTypeBuilder
          .builder(BlockElement40.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_40, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement41> ELEMENT_41_TYPE = BlockTypeBuilder
          .builder(BlockElement41.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_41, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement42> ELEMENT_42_TYPE = BlockTypeBuilder
          .builder(BlockElement42.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_42, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement43> ELEMENT_43_TYPE = BlockTypeBuilder
          .builder(BlockElement43.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_43, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement44> ELEMENT_44_TYPE = BlockTypeBuilder
          .builder(BlockElement44.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_44, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement45> ELEMENT_45_TYPE = BlockTypeBuilder
          .builder(BlockElement45.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_45, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement46> ELEMENT_46_TYPE = BlockTypeBuilder
          .builder(BlockElement46.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_46, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement47> ELEMENT_47_TYPE = BlockTypeBuilder
          .builder(BlockElement47.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_47, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement48> ELEMENT_48_TYPE = BlockTypeBuilder
          .builder(BlockElement48.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_48, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement49> ELEMENT_49_TYPE = BlockTypeBuilder
          .builder(BlockElement49.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_49, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement5> ELEMENT_5_TYPE = BlockTypeBuilder
          .builder(BlockElement5.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_5, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement50> ELEMENT_50_TYPE = BlockTypeBuilder
          .builder(BlockElement50.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_50, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement51> ELEMENT_51_TYPE = BlockTypeBuilder
          .builder(BlockElement51.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_51, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement52> ELEMENT_52_TYPE = BlockTypeBuilder
          .builder(BlockElement52.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_52, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement53> ELEMENT_53_TYPE = BlockTypeBuilder
          .builder(BlockElement53.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_53, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement54> ELEMENT_54_TYPE = BlockTypeBuilder
          .builder(BlockElement54.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_54, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement55> ELEMENT_55_TYPE = BlockTypeBuilder
          .builder(BlockElement55.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_55, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement56> ELEMENT_56_TYPE = BlockTypeBuilder
          .builder(BlockElement56.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_56, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement57> ELEMENT_57_TYPE = BlockTypeBuilder
          .builder(BlockElement57.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_57, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement58> ELEMENT_58_TYPE = BlockTypeBuilder
          .builder(BlockElement58.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_58, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement59> ELEMENT_59_TYPE = BlockTypeBuilder
          .builder(BlockElement59.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_59, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement6> ELEMENT_6_TYPE = BlockTypeBuilder
          .builder(BlockElement6.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_6, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement60> ELEMENT_60_TYPE = BlockTypeBuilder
          .builder(BlockElement60.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_60, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement61> ELEMENT_61_TYPE = BlockTypeBuilder
          .builder(BlockElement61.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_61, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement62> ELEMENT_62_TYPE = BlockTypeBuilder
          .builder(BlockElement62.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_62, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement63> ELEMENT_63_TYPE = BlockTypeBuilder
          .builder(BlockElement63.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_63, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement64> ELEMENT_64_TYPE = BlockTypeBuilder
          .builder(BlockElement64.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_64, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement65> ELEMENT_65_TYPE = BlockTypeBuilder
          .builder(BlockElement65.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_65, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement66> ELEMENT_66_TYPE = BlockTypeBuilder
          .builder(BlockElement66.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_66, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement67> ELEMENT_67_TYPE = BlockTypeBuilder
          .builder(BlockElement67.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_67, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement68> ELEMENT_68_TYPE = BlockTypeBuilder
          .builder(BlockElement68.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_68, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement69> ELEMENT_69_TYPE = BlockTypeBuilder
          .builder(BlockElement69.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_69, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement7> ELEMENT_7_TYPE = BlockTypeBuilder
          .builder(BlockElement7.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_7, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement70> ELEMENT_70_TYPE = BlockTypeBuilder
          .builder(BlockElement70.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_70, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement71> ELEMENT_71_TYPE = BlockTypeBuilder
          .builder(BlockElement71.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_71, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement72> ELEMENT_72_TYPE = BlockTypeBuilder
          .builder(BlockElement72.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_72, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement73> ELEMENT_73_TYPE = BlockTypeBuilder
          .builder(BlockElement73.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_73, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement74> ELEMENT_74_TYPE = BlockTypeBuilder
          .builder(BlockElement74.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_74, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement75> ELEMENT_75_TYPE = BlockTypeBuilder
          .builder(BlockElement75.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_75, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement76> ELEMENT_76_TYPE = BlockTypeBuilder
          .builder(BlockElement76.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_76, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement77> ELEMENT_77_TYPE = BlockTypeBuilder
          .builder(BlockElement77.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_77, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement78> ELEMENT_78_TYPE = BlockTypeBuilder
          .builder(BlockElement78.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_78, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement79> ELEMENT_79_TYPE = BlockTypeBuilder
          .builder(BlockElement79.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_79, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement8> ELEMENT_8_TYPE = BlockTypeBuilder
          .builder(BlockElement8.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_8, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement80> ELEMENT_80_TYPE = BlockTypeBuilder
          .builder(BlockElement80.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_80, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement81> ELEMENT_81_TYPE = BlockTypeBuilder
          .builder(BlockElement81.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_81, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement82> ELEMENT_82_TYPE = BlockTypeBuilder
          .builder(BlockElement82.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_82, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement83> ELEMENT_83_TYPE = BlockTypeBuilder
          .builder(BlockElement83.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_83, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement84> ELEMENT_84_TYPE = BlockTypeBuilder
          .builder(BlockElement84.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_84, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement85> ELEMENT_85_TYPE = BlockTypeBuilder
          .builder(BlockElement85.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_85, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement86> ELEMENT_86_TYPE = BlockTypeBuilder
          .builder(BlockElement86.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_86, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement87> ELEMENT_87_TYPE = BlockTypeBuilder
          .builder(BlockElement87.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_87, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement88> ELEMENT_88_TYPE = BlockTypeBuilder
          .builder(BlockElement88.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_88, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement89> ELEMENT_89_TYPE = BlockTypeBuilder
          .builder(BlockElement89.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_89, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement9> ELEMENT_9_TYPE = BlockTypeBuilder
          .builder(BlockElement9.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_9, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement90> ELEMENT_90_TYPE = BlockTypeBuilder
          .builder(BlockElement90.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_90, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement91> ELEMENT_91_TYPE = BlockTypeBuilder
          .builder(BlockElement91.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_91, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement92> ELEMENT_92_TYPE = BlockTypeBuilder
          .builder(BlockElement92.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_92, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement93> ELEMENT_93_TYPE = BlockTypeBuilder
          .builder(BlockElement93.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_93, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement94> ELEMENT_94_TYPE = BlockTypeBuilder
          .builder(BlockElement94.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_94, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement95> ELEMENT_95_TYPE = BlockTypeBuilder
          .builder(BlockElement95.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_95, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement96> ELEMENT_96_TYPE = BlockTypeBuilder
          .builder(BlockElement96.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_96, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement97> ELEMENT_97_TYPE = BlockTypeBuilder
          .builder(BlockElement97.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_97, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement98> ELEMENT_98_TYPE = BlockTypeBuilder
          .builder(BlockElement98.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_98, true)
          .addBasicComponents()
          .build();

  BlockType<BlockElement99> ELEMENT_99_TYPE = BlockTypeBuilder
          .builder(BlockElement99.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_99, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEmeraldBlock> EMERALD_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockEmeraldBlock.class)
          .vanillaBlock(VanillaBlockId.EMERALD_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEmeraldOre> EMERALD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockEmeraldOre.class)
          .vanillaBlock(VanillaBlockId.EMERALD_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEnchantingTable> ENCHANTING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockEnchantingTable.class)
          .vanillaBlock(VanillaBlockId.ENCHANTING_TABLE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEndBrickStairs> END_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockEndBrickStairs.class)
          .vanillaBlock(VanillaBlockId.END_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockEndBricks> END_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockEndBricks.class)
          .vanillaBlock(VanillaBlockId.END_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEndGateway> END_GATEWAY_TYPE = BlockTypeBuilder
          .builder(BlockEndGateway.class)
          .vanillaBlock(VanillaBlockId.END_GATEWAY, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEndPortal> END_PORTAL_TYPE = BlockTypeBuilder
          .builder(BlockEndPortal.class)
          .vanillaBlock(VanillaBlockId.END_PORTAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEndPortalFrame> END_PORTAL_FRAME_TYPE = BlockTypeBuilder
          .builder(BlockEndPortalFrame.class)
          .vanillaBlock(VanillaBlockId.END_PORTAL_FRAME, true)
          .withProperties(VanillaBlockPropertyTypes.END_PORTAL_EYE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockEndRod> END_ROD_TYPE = BlockTypeBuilder
          .builder(BlockEndRod.class)
          .vanillaBlock(VanillaBlockId.END_ROD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockEndStone> END_STONE_TYPE = BlockTypeBuilder
          .builder(BlockEndStone.class)
          .vanillaBlock(VanillaBlockId.END_STONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockEnderChest> ENDER_CHEST_TYPE = BlockTypeBuilder
          .builder(BlockEnderChest.class)
          .vanillaBlock(VanillaBlockId.ENDER_CHEST, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCopper> EXPOSED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockExposedCopper.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCutCopper> EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopper.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCutCopperSlab> EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedCutCopperStairs> EXPOSED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopperStairs.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockExposedDoubleCutCopperSlab> EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockExposedDoubleCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFarmland> FARMLAND_TYPE = BlockTypeBuilder
          .builder(BlockFarmland.class)
          .vanillaBlock(VanillaBlockId.FARMLAND, true)
          .withProperties(VanillaBlockPropertyTypes.MOISTURIZED_AMOUNT)
          .addBasicComponents()
          .build();

  BlockType<BlockFenceGate> FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockFenceGate.class)
          .vanillaBlock(VanillaBlockId.FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockFire> FIRE_TYPE = BlockTypeBuilder
          .builder(BlockFire.class)
          .vanillaBlock(VanillaBlockId.FIRE, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockFireCoral> FIRE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockFireCoral.class)
          .vanillaBlock(VanillaBlockId.FIRE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockFletchingTable> FLETCHING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockFletchingTable.class)
          .vanillaBlock(VanillaBlockId.FLETCHING_TABLE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockFlowerPot> FLOWER_POT_TYPE = BlockTypeBuilder
          .builder(BlockFlowerPot.class)
          .vanillaBlock(VanillaBlockId.FLOWER_POT, true)
          .withProperties(VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFloweringAzalea> FLOWERING_AZALEA_TYPE = BlockTypeBuilder
          .builder(BlockFloweringAzalea.class)
          .vanillaBlock(VanillaBlockId.FLOWERING_AZALEA, true)
          .addBasicComponents()
          .build();

  BlockType<BlockFlowingLava> FLOWING_LAVA_TYPE = BlockTypeBuilder
          .builder(BlockFlowingLava.class)
          .vanillaBlock(VanillaBlockId.FLOWING_LAVA, true)
          .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockFlowingWater> FLOWING_WATER_TYPE = BlockTypeBuilder
          .builder(BlockFlowingWater.class)
          .vanillaBlock(VanillaBlockId.FLOWING_WATER, true)
          .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockFrame> FRAME_TYPE = BlockTypeBuilder
          .builder(BlockFrame.class)
          .vanillaBlock(VanillaBlockId.FRAME, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockFrogSpawn> FROG_SPAWN_TYPE = BlockTypeBuilder
          .builder(BlockFrogSpawn.class)
          .vanillaBlock(VanillaBlockId.FROG_SPAWN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockFrostedIce> FROSTED_ICE_TYPE = BlockTypeBuilder
          .builder(BlockFrostedIce.class)
          .vanillaBlock(VanillaBlockId.FROSTED_ICE, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_4)
          .addBasicComponents()
          .build();

  BlockType<BlockFurnace> FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockFurnace.class)
          .vanillaBlock(VanillaBlockId.FURNACE, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGildedBlackstone> GILDED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockGildedBlackstone.class)
          .vanillaBlock(VanillaBlockId.GILDED_BLACKSTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGlass> GLASS_TYPE = BlockTypeBuilder
          .builder(BlockGlass.class)
          .vanillaBlock(VanillaBlockId.GLASS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGlassPane> GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockGlassPane.class)
          .vanillaBlock(VanillaBlockId.GLASS_PANE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowFrame> GLOW_FRAME_TYPE = BlockTypeBuilder
          .builder(BlockGlowFrame.class)
          .vanillaBlock(VanillaBlockId.GLOW_FRAME, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ITEM_FRAME_PHOTO_BIT, VanillaBlockPropertyTypes.ITEM_FRAME_MAP_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowLichen> GLOW_LICHEN_TYPE = BlockTypeBuilder
          .builder(BlockGlowLichen.class)
          .vanillaBlock(VanillaBlockId.GLOW_LICHEN, true)
          .withProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowingobsidian> GLOWINGOBSIDIAN_TYPE = BlockTypeBuilder
          .builder(BlockGlowingobsidian.class)
          .vanillaBlock(VanillaBlockId.GLOWINGOBSIDIAN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGlowstone> GLOWSTONE_TYPE = BlockTypeBuilder
          .builder(BlockGlowstone.class)
          .vanillaBlock(VanillaBlockId.GLOWSTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGoldBlock> GOLD_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockGoldBlock.class)
          .vanillaBlock(VanillaBlockId.GOLD_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGoldOre> GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockGoldOre.class)
          .vanillaBlock(VanillaBlockId.GOLD_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGoldenRail> GOLDEN_RAIL_TYPE = BlockTypeBuilder
          .builder(BlockGoldenRail.class)
          .vanillaBlock(VanillaBlockId.GOLDEN_RAIL, true)
          .withProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION_6, VanillaBlockPropertyTypes.RAIL_DATA_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGraniteStairs> GRANITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockGraniteStairs.class)
          .vanillaBlock(VanillaBlockId.GRANITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGrass> GRASS_TYPE = BlockTypeBuilder
          .builder(BlockGrass.class)
          .vanillaBlock(VanillaBlockId.GRASS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGrassPath> GRASS_PATH_TYPE = BlockTypeBuilder
          .builder(BlockGrassPath.class)
          .vanillaBlock(VanillaBlockId.GRASS_PATH, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGravel> GRAVEL_TYPE = BlockTypeBuilder
          .builder(BlockGravel.class)
          .vanillaBlock(VanillaBlockId.GRAVEL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayCandle> GRAY_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockGrayCandle.class)
          .vanillaBlock(VanillaBlockId.GRAY_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayCandleCake> GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockGrayCandleCake.class)
          .vanillaBlock(VanillaBlockId.GRAY_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayCarpet> GRAY_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockGrayCarpet.class)
          .vanillaBlock(VanillaBlockId.GRAY_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayGlazedTerracotta> GRAY_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockGrayGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.GRAY_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGrayWool> GRAY_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockGrayWool.class)
          .vanillaBlock(VanillaBlockId.GRAY_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenCandle> GREEN_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockGreenCandle.class)
          .vanillaBlock(VanillaBlockId.GREEN_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenCandleCake> GREEN_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockGreenCandleCake.class)
          .vanillaBlock(VanillaBlockId.GREEN_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenCarpet> GREEN_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockGreenCarpet.class)
          .vanillaBlock(VanillaBlockId.GREEN_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenGlazedTerracotta> GREEN_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockGreenGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.GREEN_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockGreenWool> GREEN_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockGreenWool.class)
          .vanillaBlock(VanillaBlockId.GREEN_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockGrindstone> GRINDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockGrindstone.class)
          .vanillaBlock(VanillaBlockId.GRINDSTONE, true)
          .withProperties(VanillaBlockPropertyTypes.ATTACHMENT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockHangingRoots> HANGING_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockHangingRoots.class)
          .vanillaBlock(VanillaBlockId.HANGING_ROOTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHardGlass> HARD_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardGlass.class)
          .vanillaBlock(VanillaBlockId.HARD_GLASS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHardGlassPane> HARD_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockHardGlassPane.class)
          .vanillaBlock(VanillaBlockId.HARD_GLASS_PANE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHardStainedGlass> HARD_STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockHardStainedGlass.class)
          .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockHardStainedGlassPane> HARD_STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockHardStainedGlassPane.class)
          .vanillaBlock(VanillaBlockId.HARD_STAINED_GLASS_PANE, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockHardenedClay> HARDENED_CLAY_TYPE = BlockTypeBuilder
          .builder(BlockHardenedClay.class)
          .vanillaBlock(VanillaBlockId.HARDENED_CLAY, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHayBlock> HAY_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockHayBlock.class)
          .vanillaBlock(VanillaBlockId.HAY_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.DEPRECATED, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockHeavyWeightedPressurePlate> HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockHeavyWeightedPressurePlate.class)
          .vanillaBlock(VanillaBlockId.HEAVY_WEIGHTED_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockHoneyBlock> HONEY_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockHoneyBlock.class)
          .vanillaBlock(VanillaBlockId.HONEY_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHoneycombBlock> HONEYCOMB_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockHoneycombBlock.class)
          .vanillaBlock(VanillaBlockId.HONEYCOMB_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockHopper> HOPPER_TYPE = BlockTypeBuilder
          .builder(BlockHopper.class)
          .vanillaBlock(VanillaBlockId.HOPPER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TOGGLE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockHornCoral> HORN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockHornCoral.class)
          .vanillaBlock(VanillaBlockId.HORN_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockIce> ICE_TYPE = BlockTypeBuilder
          .builder(BlockIce.class)
          .vanillaBlock(VanillaBlockId.ICE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockInfestedDeepslate> INFESTED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockInfestedDeepslate.class)
          .vanillaBlock(VanillaBlockId.INFESTED_DEEPSLATE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockInfoUpdate> INFO_UPDATE_TYPE = BlockTypeBuilder
          .builder(BlockInfoUpdate.class)
          .vanillaBlock(VanillaBlockId.INFO_UPDATE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockInfoUpdate2> INFO_UPDATE2_TYPE = BlockTypeBuilder
          .builder(BlockInfoUpdate2.class)
          .vanillaBlock(VanillaBlockId.INFO_UPDATE2, true)
          .addBasicComponents()
          .build();

  BlockType<BlockInvisibleBedrock> INVISIBLE_BEDROCK_TYPE = BlockTypeBuilder
          .builder(BlockInvisibleBedrock.class)
          .vanillaBlock(VanillaBlockId.INVISIBLE_BEDROCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockIronBars> IRON_BARS_TYPE = BlockTypeBuilder
          .builder(BlockIronBars.class)
          .vanillaBlock(VanillaBlockId.IRON_BARS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockIronBlock> IRON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockIronBlock.class)
          .vanillaBlock(VanillaBlockId.IRON_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockIronDoor> IRON_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockIronDoor.class)
          .vanillaBlock(VanillaBlockId.IRON_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockIronOre> IRON_ORE_TYPE = BlockTypeBuilder
          .builder(BlockIronOre.class)
          .vanillaBlock(VanillaBlockId.IRON_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockIronTrapdoor> IRON_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockIronTrapdoor.class)
          .vanillaBlock(VanillaBlockId.IRON_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJigsaw> JIGSAW_TYPE = BlockTypeBuilder
          .builder(BlockJigsaw.class)
          .vanillaBlock(VanillaBlockId.JIGSAW, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ROTATION)
          .addBasicComponents()
          .build();

  BlockType<BlockJukebox> JUKEBOX_TYPE = BlockTypeBuilder
          .builder(BlockJukebox.class)
          .vanillaBlock(VanillaBlockId.JUKEBOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleButton> JUNGLE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockJungleButton.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleDoor> JUNGLE_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockJungleDoor.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleFence> JUNGLE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockJungleFence.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleFenceGate> JUNGLE_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockJungleFenceGate.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleHangingSign> JUNGLE_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockJungleHangingSign.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleLog> JUNGLE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockJungleLog.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockJunglePressurePlate> JUNGLE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockJunglePressurePlate.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleStairs> JUNGLE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockJungleStairs.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleStandingSign> JUNGLE_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockJungleStandingSign.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleTrapdoor> JUNGLE_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockJungleTrapdoor.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockJungleWallSign> JUNGLE_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockJungleWallSign.class)
          .vanillaBlock(VanillaBlockId.JUNGLE_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockKelp> KELP_TYPE = BlockTypeBuilder
          .builder(BlockKelp.class)
          .vanillaBlock(VanillaBlockId.KELP, true)
          .withProperties(VanillaBlockPropertyTypes.KELP_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockLadder> LADDER_TYPE = BlockTypeBuilder
          .builder(BlockLadder.class)
          .vanillaBlock(VanillaBlockId.LADDER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLantern> LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockLantern.class)
          .vanillaBlock(VanillaBlockId.LANTERN, true)
          .withProperties(VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockLapisBlock> LAPIS_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockLapisBlock.class)
          .vanillaBlock(VanillaBlockId.LAPIS_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLapisOre> LAPIS_ORE_TYPE = BlockTypeBuilder
          .builder(BlockLapisOre.class)
          .vanillaBlock(VanillaBlockId.LAPIS_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLargeAmethystBud> LARGE_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockLargeAmethystBud.class)
          .vanillaBlock(VanillaBlockId.LARGE_AMETHYST_BUD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLava> LAVA_TYPE = BlockTypeBuilder
          .builder(BlockLava.class)
          .vanillaBlock(VanillaBlockId.LAVA, true)
          .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockLeaves> LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockLeaves.class)
          .vanillaBlock(VanillaBlockId.LEAVES, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT, VanillaBlockPropertyTypes.OLD_LEAF_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockLeaves2> LEAVES2_TYPE = BlockTypeBuilder
          .builder(BlockLeaves2.class)
          .vanillaBlock(VanillaBlockId.LEAVES2, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT, VanillaBlockPropertyTypes.NEW_LEAF_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockLectern> LECTERN_TYPE = BlockTypeBuilder
          .builder(BlockLectern.class)
          .vanillaBlock(VanillaBlockId.LECTERN, true)
          .withProperties(VanillaBlockPropertyTypes.POWERED_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLever> LEVER_TYPE = BlockTypeBuilder
          .builder(BlockLever.class)
          .vanillaBlock(VanillaBlockId.LEVER, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.LEVER_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlock> LIGHT_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockLightBlock.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.BLOCK_LIGHT_LEVEL)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueCandle> LIGHT_BLUE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCandle.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueCandleCake> LIGHT_BLUE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCandleCake.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueCarpet> LIGHT_BLUE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueCarpet.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueGlazedTerracotta> LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLightBlueWool> LIGHT_BLUE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLightBlueWool.class)
          .vanillaBlock(VanillaBlockId.LIGHT_BLUE_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayCandle> LIGHT_GRAY_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCandle.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayCandleCake> LIGHT_GRAY_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCandleCake.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayCarpet> LIGHT_GRAY_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayCarpet.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightGrayWool> LIGHT_GRAY_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLightGrayWool.class)
          .vanillaBlock(VanillaBlockId.LIGHT_GRAY_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLightWeightedPressurePlate> LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockLightWeightedPressurePlate.class)
          .vanillaBlock(VanillaBlockId.LIGHT_WEIGHTED_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockLightningRod> LIGHTNING_ROD_TYPE = BlockTypeBuilder
          .builder(BlockLightningRod.class)
          .vanillaBlock(VanillaBlockId.LIGHTNING_ROD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeCandle> LIME_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockLimeCandle.class)
          .vanillaBlock(VanillaBlockId.LIME_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeCandleCake> LIME_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockLimeCandleCake.class)
          .vanillaBlock(VanillaBlockId.LIME_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeCarpet> LIME_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockLimeCarpet.class)
          .vanillaBlock(VanillaBlockId.LIME_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeGlazedTerracotta> LIME_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockLimeGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.LIME_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLimeWool> LIME_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockLimeWool.class)
          .vanillaBlock(VanillaBlockId.LIME_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLitBlastFurnace> LIT_BLAST_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockLitBlastFurnace.class)
          .vanillaBlock(VanillaBlockId.LIT_BLAST_FURNACE, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLitDeepslateRedstoneOre> LIT_DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockLitDeepslateRedstoneOre.class)
          .vanillaBlock(VanillaBlockId.LIT_DEEPSLATE_REDSTONE_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLitFurnace> LIT_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockLitFurnace.class)
          .vanillaBlock(VanillaBlockId.LIT_FURNACE, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLitPumpkin> LIT_PUMPKIN_TYPE = BlockTypeBuilder
          .builder(BlockLitPumpkin.class)
          .vanillaBlock(VanillaBlockId.LIT_PUMPKIN, true)
          .withProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLitRedstoneLamp> LIT_REDSTONE_LAMP_TYPE = BlockTypeBuilder
          .builder(BlockLitRedstoneLamp.class)
          .vanillaBlock(VanillaBlockId.LIT_REDSTONE_LAMP, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLitRedstoneOre> LIT_REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockLitRedstoneOre.class)
          .vanillaBlock(VanillaBlockId.LIT_REDSTONE_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLitSmoker> LIT_SMOKER_TYPE = BlockTypeBuilder
          .builder(BlockLitSmoker.class)
          .vanillaBlock(VanillaBlockId.LIT_SMOKER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockLodestone> LODESTONE_TYPE = BlockTypeBuilder
          .builder(BlockLodestone.class)
          .vanillaBlock(VanillaBlockId.LODESTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockLoom> LOOM_TYPE = BlockTypeBuilder
          .builder(BlockLoom.class)
          .vanillaBlock(VanillaBlockId.LOOM, true)
          .withProperties(VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaCandle> MAGENTA_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCandle.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaCandleCake> MAGENTA_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCandleCake.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaCarpet> MAGENTA_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockMagentaCarpet.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaGlazedTerracotta> MAGENTA_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockMagentaGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMagentaWool> MAGENTA_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockMagentaWool.class)
          .vanillaBlock(VanillaBlockId.MAGENTA_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMagma> MAGMA_TYPE = BlockTypeBuilder
          .builder(BlockMagma.class)
          .vanillaBlock(VanillaBlockId.MAGMA, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveButton> MANGROVE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockMangroveButton.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveDoor> MANGROVE_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockMangroveDoor.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveDoubleSlab> MANGROVE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMangroveDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveFence> MANGROVE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockMangroveFence.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveFenceGate> MANGROVE_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockMangroveFenceGate.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveHangingSign> MANGROVE_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockMangroveHangingSign.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveLeaves> MANGROVE_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockMangroveLeaves.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_LEAVES, true)
          .withProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveLog> MANGROVE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockMangroveLog.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockMangrovePlanks> MANGROVE_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePlanks.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PLANKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMangrovePressurePlate> MANGROVE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePressurePlate.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockMangrovePropagule> MANGROVE_PROPAGULE_TYPE = BlockTypeBuilder
          .builder(BlockMangrovePropagule.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_PROPAGULE, true)
          .withProperties(VanillaBlockPropertyTypes.PROPAGULE_STAGE, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveRoots> MANGROVE_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockMangroveRoots.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_ROOTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveSlab> MANGROVE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMangroveSlab.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveStairs> MANGROVE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMangroveStairs.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveStandingSign> MANGROVE_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockMangroveStandingSign.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveTrapdoor> MANGROVE_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockMangroveTrapdoor.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveWallSign> MANGROVE_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockMangroveWallSign.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMangroveWood> MANGROVE_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockMangroveWood.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_WOOD, true)
          .withProperties(VanillaBlockPropertyTypes.STRIPPED_BIT, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockMediumAmethystBud> MEDIUM_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockMediumAmethystBud.class)
          .vanillaBlock(VanillaBlockId.MEDIUM_AMETHYST_BUD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMelonBlock> MELON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMelonBlock.class)
          .vanillaBlock(VanillaBlockId.MELON_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMelonStem> MELON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockMelonStem.class)
          .vanillaBlock(VanillaBlockId.MELON_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockMobSpawner> MOB_SPAWNER_TYPE = BlockTypeBuilder
          .builder(BlockMobSpawner.class)
          .vanillaBlock(VanillaBlockId.MOB_SPAWNER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMonsterEgg> MONSTER_EGG_TYPE = BlockTypeBuilder
          .builder(BlockMonsterEgg.class)
          .vanillaBlock(VanillaBlockId.MONSTER_EGG, true)
          .withProperties(VanillaBlockPropertyTypes.MONSTER_EGG_STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockMossBlock> MOSS_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMossBlock.class)
          .vanillaBlock(VanillaBlockId.MOSS_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMossCarpet> MOSS_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockMossCarpet.class)
          .vanillaBlock(VanillaBlockId.MOSS_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMossyCobblestone> MOSSY_COBBLESTONE_TYPE = BlockTypeBuilder
          .builder(BlockMossyCobblestone.class)
          .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMossyCobblestoneStairs> MOSSY_COBBLESTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMossyCobblestoneStairs.class)
          .vanillaBlock(VanillaBlockId.MOSSY_COBBLESTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMossyStoneBrickStairs> MOSSY_STONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMossyStoneBrickStairs.class)
          .vanillaBlock(VanillaBlockId.MOSSY_STONE_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMovingBlock> MOVING_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockMovingBlock.class)
          .vanillaBlock(VanillaBlockId.MOVING_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMud> MUD_TYPE = BlockTypeBuilder
          .builder(BlockMud.class)
          .vanillaBlock(VanillaBlockId.MUD, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickDoubleSlab> MUD_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickSlab> MUD_BRICK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickSlab.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickStairs> MUD_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickStairs.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBrickWall> MUD_BRICK_WALL_TYPE = BlockTypeBuilder
          .builder(BlockMudBrickWall.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICK_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH)
          .addBasicComponents()
          .build();

  BlockType<BlockMudBricks> MUD_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockMudBricks.class)
          .vanillaBlock(VanillaBlockId.MUD_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockMuddyMangroveRoots> MUDDY_MANGROVE_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockMuddyMangroveRoots.class)
          .vanillaBlock(VanillaBlockId.MUDDY_MANGROVE_ROOTS, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockMycelium> MYCELIUM_TYPE = BlockTypeBuilder
          .builder(BlockMycelium.class)
          .vanillaBlock(VanillaBlockId.MYCELIUM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherBrick> NETHER_BRICK_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrick.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherBrickFence> NETHER_BRICK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrickFence.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherBrickStairs> NETHER_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockNetherBrickStairs.class)
          .vanillaBlock(VanillaBlockId.NETHER_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherGoldOre> NETHER_GOLD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockNetherGoldOre.class)
          .vanillaBlock(VanillaBlockId.NETHER_GOLD_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherSprouts> NETHER_SPROUTS_TYPE = BlockTypeBuilder
          .builder(BlockNetherSprouts.class)
          .vanillaBlock(VanillaBlockId.NETHER_SPROUTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherWart> NETHER_WART_TYPE = BlockTypeBuilder
          .builder(BlockNetherWart.class)
          .vanillaBlock(VanillaBlockId.NETHER_WART, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_4)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherWartBlock> NETHER_WART_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockNetherWartBlock.class)
          .vanillaBlock(VanillaBlockId.NETHER_WART_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetheriteBlock> NETHERITE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockNetheriteBlock.class)
          .vanillaBlock(VanillaBlockId.NETHERITE_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherrack> NETHERRACK_TYPE = BlockTypeBuilder
          .builder(BlockNetherrack.class)
          .vanillaBlock(VanillaBlockId.NETHERRACK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNetherreactor> NETHERREACTOR_TYPE = BlockTypeBuilder
          .builder(BlockNetherreactor.class)
          .vanillaBlock(VanillaBlockId.NETHERREACTOR, true)
          .addBasicComponents()
          .build();

  BlockType<BlockNormalStoneStairs> NORMAL_STONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockNormalStoneStairs.class)
          .vanillaBlock(VanillaBlockId.NORMAL_STONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockNoteblock> NOTEBLOCK_TYPE = BlockTypeBuilder
          .builder(BlockNoteblock.class)
          .vanillaBlock(VanillaBlockId.NOTEBLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOakFence> OAK_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockOakFence.class)
          .vanillaBlock(VanillaBlockId.OAK_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOakHangingSign> OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockOakHangingSign.class)
          .vanillaBlock(VanillaBlockId.OAK_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockOakLog> OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockOakLog.class)
          .vanillaBlock(VanillaBlockId.OAK_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockOakStairs> OAK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockOakStairs.class)
          .vanillaBlock(VanillaBlockId.OAK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockObserver> OBSERVER_TYPE = BlockTypeBuilder
          .builder(BlockObserver.class)
          .vanillaBlock(VanillaBlockId.OBSERVER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockObsidian> OBSIDIAN_TYPE = BlockTypeBuilder
          .builder(BlockObsidian.class)
          .vanillaBlock(VanillaBlockId.OBSIDIAN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOchreFroglight> OCHRE_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockOchreFroglight.class)
          .vanillaBlock(VanillaBlockId.OCHRE_FROGLIGHT, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeCandle> ORANGE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCandle.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeCandleCake> ORANGE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCandleCake.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeCarpet> ORANGE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockOrangeCarpet.class)
          .vanillaBlock(VanillaBlockId.ORANGE_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeGlazedTerracotta> ORANGE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockOrangeGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.ORANGE_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockOrangeWool> ORANGE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockOrangeWool.class)
          .vanillaBlock(VanillaBlockId.ORANGE_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCopper> OXIDIZED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCopper.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCutCopper> OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCutCopper.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCutCopperSlab> OXIDIZED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedCutCopperStairs> OXIDIZED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCutCopperStairs.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockOxidizedDoubleCutCopperSlab> OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedDoubleCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPackedIce> PACKED_ICE_TYPE = BlockTypeBuilder
          .builder(BlockPackedIce.class)
          .vanillaBlock(VanillaBlockId.PACKED_ICE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPackedMud> PACKED_MUD_TYPE = BlockTypeBuilder
          .builder(BlockPackedMud.class)
          .vanillaBlock(VanillaBlockId.PACKED_MUD, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPearlescentFroglight> PEARLESCENT_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockPearlescentFroglight.class)
          .vanillaBlock(VanillaBlockId.PEARLESCENT_FROGLIGHT, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkCandle> PINK_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockPinkCandle.class)
          .vanillaBlock(VanillaBlockId.PINK_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkCandleCake> PINK_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockPinkCandleCake.class)
          .vanillaBlock(VanillaBlockId.PINK_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkCarpet> PINK_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockPinkCarpet.class)
          .vanillaBlock(VanillaBlockId.PINK_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkGlazedTerracotta> PINK_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockPinkGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.PINK_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkPetals> PINK_PETALS_TYPE = BlockTypeBuilder
          .builder(BlockPinkPetals.class)
          .vanillaBlock(VanillaBlockId.PINK_PETALS, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPinkWool> PINK_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockPinkWool.class)
          .vanillaBlock(VanillaBlockId.PINK_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPiston> PISTON_TYPE = BlockTypeBuilder
          .builder(BlockPiston.class)
          .vanillaBlock(VanillaBlockId.PISTON, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPistonArmCollision> PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
          .builder(BlockPistonArmCollision.class)
          .vanillaBlock(VanillaBlockId.PISTON_ARM_COLLISION, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPitcherCrop> PITCHER_CROP_TYPE = BlockTypeBuilder
          .builder(BlockPitcherCrop.class)
          .vanillaBlock(VanillaBlockId.PITCHER_CROP, true)
          .withProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPitcherPlant> PITCHER_PLANT_TYPE = BlockTypeBuilder
          .builder(BlockPitcherPlant.class)
          .vanillaBlock(VanillaBlockId.PITCHER_PLANT, true)
          .withProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPlanks> PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockPlanks.class)
          .vanillaBlock(VanillaBlockId.PLANKS, true)
          .withProperties(VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockPodzol> PODZOL_TYPE = BlockTypeBuilder
          .builder(BlockPodzol.class)
          .vanillaBlock(VanillaBlockId.PODZOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPointedDripstone> POINTED_DRIPSTONE_TYPE = BlockTypeBuilder
          .builder(BlockPointedDripstone.class)
          .vanillaBlock(VanillaBlockId.POINTED_DRIPSTONE, true)
          .withProperties(VanillaBlockPropertyTypes.DRIPSTONE_THICKNESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedAndesiteStairs> POLISHED_ANDESITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedAndesiteStairs.class)
          .vanillaBlock(VanillaBlockId.POLISHED_ANDESITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBasalt> POLISHED_BASALT_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBasalt.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BASALT, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstone> POLISHED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstone.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickDoubleSlab> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickSlab> POLISHED_BLACKSTONE_BRICK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickSlab.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickStairs> POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickStairs.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBrickWall> POLISHED_BLACKSTONE_BRICK_WALL_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBrickWall.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICK_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneBricks> POLISHED_BLACKSTONE_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneBricks.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneButton> POLISHED_BLACKSTONE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneButton.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneDoubleSlab> POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstonePressurePlate> POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstonePressurePlate.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneSlab> POLISHED_BLACKSTONE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneSlab.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneStairs> POLISHED_BLACKSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneStairs.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedBlackstoneWall> POLISHED_BLACKSTONE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockPolishedBlackstoneWall.class)
          .vanillaBlock(VanillaBlockId.POLISHED_BLACKSTONE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslate> POLISHED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslate.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateDoubleSlab> POLISHED_DEEPSLATE_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateSlab> POLISHED_DEEPSLATE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateSlab.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateStairs> POLISHED_DEEPSLATE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateStairs.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDeepslateWall> POLISHED_DEEPSLATE_WALL_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDeepslateWall.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DEEPSLATE_WALL, true)
          .withProperties(VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, VanillaBlockPropertyTypes.WALL_POST_BIT, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, VanillaBlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedDioriteStairs> POLISHED_DIORITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedDioriteStairs.class)
          .vanillaBlock(VanillaBlockId.POLISHED_DIORITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPolishedGraniteStairs> POLISHED_GRANITE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPolishedGraniteStairs.class)
          .vanillaBlock(VanillaBlockId.POLISHED_GRANITE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPortal> PORTAL_TYPE = BlockTypeBuilder
          .builder(BlockPortal.class)
          .vanillaBlock(VanillaBlockId.PORTAL, true)
          .withProperties(VanillaBlockPropertyTypes.PORTAL_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPotatoes> POTATOES_TYPE = BlockTypeBuilder
          .builder(BlockPotatoes.class)
          .vanillaBlock(VanillaBlockId.POTATOES, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPowderSnow> POWDER_SNOW_TYPE = BlockTypeBuilder
          .builder(BlockPowderSnow.class)
          .vanillaBlock(VanillaBlockId.POWDER_SNOW, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPoweredComparator> POWERED_COMPARATOR_TYPE = BlockTypeBuilder
          .builder(BlockPoweredComparator.class)
          .vanillaBlock(VanillaBlockId.POWERED_COMPARATOR, true)
          .withProperties(VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPoweredRepeater> POWERED_REPEATER_TYPE = BlockTypeBuilder
          .builder(BlockPoweredRepeater.class)
          .vanillaBlock(VanillaBlockId.POWERED_REPEATER, true)
          .withProperties(VanillaBlockPropertyTypes.REPEATER_DELAY, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPrismarine> PRISMARINE_TYPE = BlockTypeBuilder
          .builder(BlockPrismarine.class)
          .vanillaBlock(VanillaBlockId.PRISMARINE, true)
          .withProperties(VanillaBlockPropertyTypes.PRISMARINE_BLOCK_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockPrismarineBricksStairs> PRISMARINE_BRICKS_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPrismarineBricksStairs.class)
          .vanillaBlock(VanillaBlockId.PRISMARINE_BRICKS_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPrismarineStairs> PRISMARINE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPrismarineStairs.class)
          .vanillaBlock(VanillaBlockId.PRISMARINE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPumpkin> PUMPKIN_TYPE = BlockTypeBuilder
          .builder(BlockPumpkin.class)
          .vanillaBlock(VanillaBlockId.PUMPKIN, true)
          .withProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPumpkinStem> PUMPKIN_STEM_TYPE = BlockTypeBuilder
          .builder(BlockPumpkinStem.class)
          .vanillaBlock(VanillaBlockId.PUMPKIN_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleCandle> PURPLE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockPurpleCandle.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleCandleCake> PURPLE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockPurpleCandleCake.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleCarpet> PURPLE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockPurpleCarpet.class)
          .vanillaBlock(VanillaBlockId.PURPLE_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleGlazedTerracotta> PURPLE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockPurpleGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.PURPLE_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpleWool> PURPLE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockPurpleWool.class)
          .vanillaBlock(VanillaBlockId.PURPLE_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpurBlock> PURPUR_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockPurpurBlock.class)
          .vanillaBlock(VanillaBlockId.PURPUR_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockPurpurStairs> PURPUR_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockPurpurStairs.class)
          .vanillaBlock(VanillaBlockId.PURPUR_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzBlock> QUARTZ_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockQuartzBlock.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzBricks> QUARTZ_BRICKS_TYPE = BlockTypeBuilder
          .builder(BlockQuartzBricks.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_BRICKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzOre> QUARTZ_ORE_TYPE = BlockTypeBuilder
          .builder(BlockQuartzOre.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockQuartzStairs> QUARTZ_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockQuartzStairs.class)
          .vanillaBlock(VanillaBlockId.QUARTZ_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRail> RAIL_TYPE = BlockTypeBuilder
          .builder(BlockRail.class)
          .vanillaBlock(VanillaBlockId.RAIL, true)
          .withProperties(VanillaBlockPropertyTypes.RAIL_DIRECTION_10)
          .addBasicComponents()
          .build();

  BlockType<BlockRawCopperBlock> RAW_COPPER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawCopperBlock.class)
          .vanillaBlock(VanillaBlockId.RAW_COPPER_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRawGoldBlock> RAW_GOLD_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawGoldBlock.class)
          .vanillaBlock(VanillaBlockId.RAW_GOLD_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRawIronBlock> RAW_IRON_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRawIronBlock.class)
          .vanillaBlock(VanillaBlockId.RAW_IRON_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedCandle> RED_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockRedCandle.class)
          .vanillaBlock(VanillaBlockId.RED_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockRedCandleCake> RED_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockRedCandleCake.class)
          .vanillaBlock(VanillaBlockId.RED_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockRedCarpet> RED_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockRedCarpet.class)
          .vanillaBlock(VanillaBlockId.RED_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedFlower> RED_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockRedFlower.class)
          .vanillaBlock(VanillaBlockId.RED_FLOWER, true)
          .withProperties(VanillaBlockPropertyTypes.FLOWER_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockRedGlazedTerracotta> RED_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockRedGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.RED_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedMushroom> RED_MUSHROOM_TYPE = BlockTypeBuilder
          .builder(BlockRedMushroom.class)
          .vanillaBlock(VanillaBlockId.RED_MUSHROOM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedMushroomBlock> RED_MUSHROOM_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRedMushroomBlock.class)
          .vanillaBlock(VanillaBlockId.RED_MUSHROOM_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.HUGE_MUSHROOM_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockRedNetherBrick> RED_NETHER_BRICK_TYPE = BlockTypeBuilder
          .builder(BlockRedNetherBrick.class)
          .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedNetherBrickStairs> RED_NETHER_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockRedNetherBrickStairs.class)
          .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedSandstone> RED_SANDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockRedSandstone.class)
          .vanillaBlock(VanillaBlockId.RED_SANDSTONE, true)
          .withProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockRedSandstoneStairs> RED_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockRedSandstoneStairs.class)
          .vanillaBlock(VanillaBlockId.RED_SANDSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedWool> RED_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockRedWool.class)
          .vanillaBlock(VanillaBlockId.RED_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneBlock> REDSTONE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneBlock.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneLamp> REDSTONE_LAMP_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneLamp.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_LAMP, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneOre> REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneOre.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_ORE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneTorch> REDSTONE_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneTorch.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_TORCH, true)
          .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockRedstoneWire> REDSTONE_WIRE_TYPE = BlockTypeBuilder
          .builder(BlockRedstoneWire.class)
          .vanillaBlock(VanillaBlockId.REDSTONE_WIRE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockReeds> REEDS_TYPE = BlockTypeBuilder
          .builder(BlockReeds.class)
          .vanillaBlock(VanillaBlockId.REEDS, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockReinforcedDeepslate> REINFORCED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockReinforcedDeepslate.class)
          .vanillaBlock(VanillaBlockId.REINFORCED_DEEPSLATE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRepeatingCommandBlock> REPEATING_COMMAND_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockRepeatingCommandBlock.class)
          .vanillaBlock(VanillaBlockId.REPEATING_COMMAND_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.CONDITIONAL_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockReserved6> RESERVED6_TYPE = BlockTypeBuilder
          .builder(BlockReserved6.class)
          .vanillaBlock(VanillaBlockId.RESERVED6, true)
          .addBasicComponents()
          .build();

  BlockType<BlockRespawnAnchor> RESPAWN_ANCHOR_TYPE = BlockTypeBuilder
          .builder(BlockRespawnAnchor.class)
          .vanillaBlock(VanillaBlockId.RESPAWN_ANCHOR, true)
          .withProperties(VanillaBlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
          .addBasicComponents()
          .build();

  BlockType<BlockSand> SAND_TYPE = BlockTypeBuilder
          .builder(BlockSand.class)
          .vanillaBlock(VanillaBlockId.SAND, true)
          .withProperties(VanillaBlockPropertyTypes.SAND_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSandstone> SANDSTONE_TYPE = BlockTypeBuilder
          .builder(BlockSandstone.class)
          .vanillaBlock(VanillaBlockId.SANDSTONE, true)
          .withProperties(VanillaBlockPropertyTypes.SAND_STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSandstoneStairs> SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSandstoneStairs.class)
          .vanillaBlock(VanillaBlockId.SANDSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSapling> SAPLING_TYPE = BlockTypeBuilder
          .builder(BlockSapling.class)
          .vanillaBlock(VanillaBlockId.SAPLING, true)
          .withProperties(VanillaBlockPropertyTypes.SAPLING_TYPE, VanillaBlockPropertyTypes.AGE_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockScaffolding> SCAFFOLDING_TYPE = BlockTypeBuilder
          .builder(BlockScaffolding.class)
          .vanillaBlock(VanillaBlockId.SCAFFOLDING, true)
          .withProperties(VanillaBlockPropertyTypes.STABILITY, VanillaBlockPropertyTypes.STABILITY_CHECK)
          .addBasicComponents()
          .build();

  BlockType<BlockSculk> SCULK_TYPE = BlockTypeBuilder
          .builder(BlockSculk.class)
          .vanillaBlock(VanillaBlockId.SCULK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkCatalyst> SCULK_CATALYST_TYPE = BlockTypeBuilder
          .builder(BlockSculkCatalyst.class)
          .vanillaBlock(VanillaBlockId.SCULK_CATALYST, true)
          .withProperties(VanillaBlockPropertyTypes.BLOOM)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkSensor> SCULK_SENSOR_TYPE = BlockTypeBuilder
          .builder(BlockSculkSensor.class)
          .vanillaBlock(VanillaBlockId.SCULK_SENSOR, true)
          .withProperties(VanillaBlockPropertyTypes.SCULK_SENSOR_PHASE)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkShrieker> SCULK_SHRIEKER_TYPE = BlockTypeBuilder
          .builder(BlockSculkShrieker.class)
          .vanillaBlock(VanillaBlockId.SCULK_SHRIEKER, true)
          .withProperties(VanillaBlockPropertyTypes.CAN_SUMMON, VanillaBlockPropertyTypes.ACTIVE)
          .addBasicComponents()
          .build();

  BlockType<BlockSculkVein> SCULK_VEIN_TYPE = BlockTypeBuilder
          .builder(BlockSculkVein.class)
          .vanillaBlock(VanillaBlockId.SCULK_VEIN, true)
          .withProperties(VanillaBlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockSeaLantern> SEA_LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockSeaLantern.class)
          .vanillaBlock(VanillaBlockId.SEA_LANTERN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSeaPickle> SEA_PICKLE_TYPE = BlockTypeBuilder
          .builder(BlockSeaPickle.class)
          .vanillaBlock(VanillaBlockId.SEA_PICKLE, true)
          .withProperties(VanillaBlockPropertyTypes.CLUSTER_COUNT, VanillaBlockPropertyTypes.DEAD_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockSeagrass> SEAGRASS_TYPE = BlockTypeBuilder
          .builder(BlockSeagrass.class)
          .vanillaBlock(VanillaBlockId.SEAGRASS, true)
          .withProperties(VanillaBlockPropertyTypes.SEA_GRASS_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockShroomlight> SHROOMLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockShroomlight.class)
          .vanillaBlock(VanillaBlockId.SHROOMLIGHT, true)
          .addBasicComponents()
          .build();

  BlockType<BlockShulkerBox> SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockShulkerBox.class)
          .vanillaBlock(VanillaBlockId.SHULKER_BOX, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockSilverGlazedTerracotta> SILVER_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockSilverGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.SILVER_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSkull> SKULL_TYPE = BlockTypeBuilder
          .builder(BlockSkull.class)
          .vanillaBlock(VanillaBlockId.SKULL, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSlime> SLIME_TYPE = BlockTypeBuilder
          .builder(BlockSlime.class)
          .vanillaBlock(VanillaBlockId.SLIME, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSmallAmethystBud> SMALL_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockSmallAmethystBud.class)
          .vanillaBlock(VanillaBlockId.SMALL_AMETHYST_BUD, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmallDripleafBlock> SMALL_DRIPLEAF_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockSmallDripleafBlock.class)
          .vanillaBlock(VanillaBlockId.SMALL_DRIPLEAF_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmithingTable> SMITHING_TABLE_TYPE = BlockTypeBuilder
          .builder(BlockSmithingTable.class)
          .vanillaBlock(VanillaBlockId.SMITHING_TABLE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoker> SMOKER_TYPE = BlockTypeBuilder
          .builder(BlockSmoker.class)
          .vanillaBlock(VanillaBlockId.SMOKER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothBasalt> SMOOTH_BASALT_TYPE = BlockTypeBuilder
          .builder(BlockSmoothBasalt.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_BASALT, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothQuartzStairs> SMOOTH_QUARTZ_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSmoothQuartzStairs.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_QUARTZ_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothRedSandstoneStairs> SMOOTH_RED_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSmoothRedSandstoneStairs.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_RED_SANDSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothSandstoneStairs> SMOOTH_SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSmoothSandstoneStairs.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_SANDSTONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSmoothStone> SMOOTH_STONE_TYPE = BlockTypeBuilder
          .builder(BlockSmoothStone.class)
          .vanillaBlock(VanillaBlockId.SMOOTH_STONE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSnifferEgg> SNIFFER_EGG_TYPE = BlockTypeBuilder
          .builder(BlockSnifferEgg.class)
          .vanillaBlock(VanillaBlockId.SNIFFER_EGG, true)
          .withProperties(VanillaBlockPropertyTypes.CRACKED_STATE)
          .addBasicComponents()
          .build();

  BlockType<BlockSnow> SNOW_TYPE = BlockTypeBuilder
          .builder(BlockSnow.class)
          .vanillaBlock(VanillaBlockId.SNOW, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSnowLayer> SNOW_LAYER_TYPE = BlockTypeBuilder
          .builder(BlockSnowLayer.class)
          .vanillaBlock(VanillaBlockId.SNOW_LAYER, true)
          .withProperties(VanillaBlockPropertyTypes.COVERED_BIT, VanillaBlockPropertyTypes.HEIGHT)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulCampfire> SOUL_CAMPFIRE_TYPE = BlockTypeBuilder
          .builder(BlockSoulCampfire.class)
          .vanillaBlock(VanillaBlockId.SOUL_CAMPFIRE, true)
          .withProperties(VanillaBlockPropertyTypes.EXTINGUISHED, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulFire> SOUL_FIRE_TYPE = BlockTypeBuilder
          .builder(BlockSoulFire.class)
          .vanillaBlock(VanillaBlockId.SOUL_FIRE, true)
          .withProperties(VanillaBlockPropertyTypes.AGE_16)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulLantern> SOUL_LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockSoulLantern.class)
          .vanillaBlock(VanillaBlockId.SOUL_LANTERN, true)
          .withProperties(VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulSand> SOUL_SAND_TYPE = BlockTypeBuilder
          .builder(BlockSoulSand.class)
          .vanillaBlock(VanillaBlockId.SOUL_SAND, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulSoil> SOUL_SOIL_TYPE = BlockTypeBuilder
          .builder(BlockSoulSoil.class)
          .vanillaBlock(VanillaBlockId.SOUL_SOIL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSoulTorch> SOUL_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockSoulTorch.class)
          .vanillaBlock(VanillaBlockId.SOUL_TORCH, true)
          .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSponge> SPONGE_TYPE = BlockTypeBuilder
          .builder(BlockSponge.class)
          .vanillaBlock(VanillaBlockId.SPONGE, true)
          .withProperties(VanillaBlockPropertyTypes.SPONGE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSporeBlossom> SPORE_BLOSSOM_TYPE = BlockTypeBuilder
          .builder(BlockSporeBlossom.class)
          .vanillaBlock(VanillaBlockId.SPORE_BLOSSOM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceButton> SPRUCE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockSpruceButton.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceDoor> SPRUCE_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockSpruceDoor.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceFence> SPRUCE_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockSpruceFence.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceFenceGate> SPRUCE_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockSpruceFenceGate.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceHangingSign> SPRUCE_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockSpruceHangingSign.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceLog> SPRUCE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockSpruceLog.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockSprucePressurePlate> SPRUCE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockSprucePressurePlate.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceStairs> SPRUCE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockSpruceStairs.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceStandingSign> SPRUCE_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockSpruceStandingSign.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceTrapdoor> SPRUCE_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockSpruceTrapdoor.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockSpruceWallSign> SPRUCE_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockSpruceWallSign.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStainedGlass> STAINED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockStainedGlass.class)
          .vanillaBlock(VanillaBlockId.STAINED_GLASS, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockStainedGlassPane> STAINED_GLASS_PANE_TYPE = BlockTypeBuilder
          .builder(BlockStainedGlassPane.class)
          .vanillaBlock(VanillaBlockId.STAINED_GLASS_PANE, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockStainedHardenedClay> STAINED_HARDENED_CLAY_TYPE = BlockTypeBuilder
          .builder(BlockStainedHardenedClay.class)
          .vanillaBlock(VanillaBlockId.STAINED_HARDENED_CLAY, true)
          .withProperties(VanillaBlockPropertyTypes.COLOR)
          .addBasicComponents()
          .build();

  BlockType<BlockStandingBanner> STANDING_BANNER_TYPE = BlockTypeBuilder
          .builder(BlockStandingBanner.class)
          .vanillaBlock(VanillaBlockId.STANDING_BANNER, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStandingSign> STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockStandingSign.class)
          .vanillaBlock(VanillaBlockId.STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStickyPiston> STICKY_PISTON_TYPE = BlockTypeBuilder
          .builder(BlockStickyPiston.class)
          .vanillaBlock(VanillaBlockId.STICKY_PISTON, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStickyPistonArmCollision> STICKY_PISTON_ARM_COLLISION_TYPE = BlockTypeBuilder
          .builder(BlockStickyPistonArmCollision.class)
          .vanillaBlock(VanillaBlockId.STICKY_PISTON_ARM_COLLISION, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStone> STONE_TYPE = BlockTypeBuilder
          .builder(BlockStone.class)
          .vanillaBlock(VanillaBlockId.STONE, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlab> STONE_BLOCK_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlab2> STONE_BLOCK_SLAB2_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab2.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB2, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_2, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlab3> STONE_BLOCK_SLAB3_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab3.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB3, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_SLAB_TYPE_3, VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBlockSlab4> STONE_BLOCK_SLAB4_TYPE = BlockTypeBuilder
          .builder(BlockStoneBlockSlab4.class)
          .vanillaBlock(VanillaBlockId.STONE_BLOCK_SLAB4, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT, VanillaBlockPropertyTypes.STONE_SLAB_TYPE_4)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneBrickStairs> STONE_BRICK_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockStoneBrickStairs.class)
          .vanillaBlock(VanillaBlockId.STONE_BRICK_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneButton> STONE_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockStoneButton.class)
          .vanillaBlock(VanillaBlockId.STONE_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStonePressurePlate> STONE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockStonePressurePlate.class)
          .vanillaBlock(VanillaBlockId.STONE_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockStoneStairs> STONE_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockStoneStairs.class)
          .vanillaBlock(VanillaBlockId.STONE_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStonebrick> STONEBRICK_TYPE = BlockTypeBuilder
          .builder(BlockStonebrick.class)
          .vanillaBlock(VanillaBlockId.STONEBRICK, true)
          .withProperties(VanillaBlockPropertyTypes.STONE_BRICK_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockStonecutter> STONECUTTER_TYPE = BlockTypeBuilder
          .builder(BlockStonecutter.class)
          .vanillaBlock(VanillaBlockId.STONECUTTER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockStonecutterBlock> STONECUTTER_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockStonecutterBlock.class)
          .vanillaBlock(VanillaBlockId.STONECUTTER_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedAcaciaLog> STRIPPED_ACACIA_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedAcaciaLog.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_ACACIA_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedBambooBlock> STRIPPED_BAMBOO_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockStrippedBambooBlock.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_BAMBOO_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedBirchLog> STRIPPED_BIRCH_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedBirchLog.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_BIRCH_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCherryLog> STRIPPED_CHERRY_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCherryLog.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCherryWood> STRIPPED_CHERRY_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCherryWood.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_WOOD, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCrimsonHyphae> STRIPPED_CRIMSON_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCrimsonHyphae.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_HYPHAE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedCrimsonStem> STRIPPED_CRIMSON_STEM_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCrimsonStem.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CRIMSON_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedDarkOakLog> STRIPPED_DARK_OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedDarkOakLog.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_DARK_OAK_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedJungleLog> STRIPPED_JUNGLE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedJungleLog.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_JUNGLE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedMangroveLog> STRIPPED_MANGROVE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedMangroveLog.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedMangroveWood> STRIPPED_MANGROVE_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockStrippedMangroveWood.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_MANGROVE_WOOD, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedOakLog> STRIPPED_OAK_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedOakLog.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_OAK_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedSpruceLog> STRIPPED_SPRUCE_LOG_TYPE = BlockTypeBuilder
          .builder(BlockStrippedSpruceLog.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_SPRUCE_LOG, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedWarpedHyphae> STRIPPED_WARPED_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockStrippedWarpedHyphae.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_HYPHAE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStrippedWarpedStem> STRIPPED_WARPED_STEM_TYPE = BlockTypeBuilder
          .builder(BlockStrippedWarpedStem.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_WARPED_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockStructureBlock> STRUCTURE_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockStructureBlock.class)
          .vanillaBlock(VanillaBlockId.STRUCTURE_BLOCK, true)
          .withProperties(VanillaBlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockStructureVoid> STRUCTURE_VOID_TYPE = BlockTypeBuilder
          .builder(BlockStructureVoid.class)
          .vanillaBlock(VanillaBlockId.STRUCTURE_VOID, true)
          .withProperties(VanillaBlockPropertyTypes.STRUCTURE_VOID_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockSuspiciousGravel> SUSPICIOUS_GRAVEL_TYPE = BlockTypeBuilder
          .builder(BlockSuspiciousGravel.class)
          .vanillaBlock(VanillaBlockId.SUSPICIOUS_GRAVEL, true)
          .withProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSuspiciousSand> SUSPICIOUS_SAND_TYPE = BlockTypeBuilder
          .builder(BlockSuspiciousSand.class)
          .vanillaBlock(VanillaBlockId.SUSPICIOUS_SAND, true)
          .withProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockSweetBerryBush> SWEET_BERRY_BUSH_TYPE = BlockTypeBuilder
          .builder(BlockSweetBerryBush.class)
          .vanillaBlock(VanillaBlockId.SWEET_BERRY_BUSH, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockTallgrass> TALLGRASS_TYPE = BlockTypeBuilder
          .builder(BlockTallgrass.class)
          .vanillaBlock(VanillaBlockId.TALLGRASS, true)
          .withProperties(VanillaBlockPropertyTypes.TALL_GRASS_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockTarget> TARGET_TYPE = BlockTypeBuilder
          .builder(BlockTarget.class)
          .vanillaBlock(VanillaBlockId.TARGET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockTintedGlass> TINTED_GLASS_TYPE = BlockTypeBuilder
          .builder(BlockTintedGlass.class)
          .vanillaBlock(VanillaBlockId.TINTED_GLASS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockTnt> TNT_TYPE = BlockTypeBuilder
          .builder(BlockTnt.class)
          .vanillaBlock(VanillaBlockId.TNT, true)
          .withProperties(VanillaBlockPropertyTypes.EXPLODE_BIT, VanillaBlockPropertyTypes.ALLOW_UNDERWATER_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockTorch> TORCH_TYPE = BlockTypeBuilder
          .builder(BlockTorch.class)
          .vanillaBlock(VanillaBlockId.TORCH, true)
          .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockTorchflower> TORCHFLOWER_TYPE = BlockTypeBuilder
          .builder(BlockTorchflower.class)
          .vanillaBlock(VanillaBlockId.TORCHFLOWER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockTorchflowerCrop> TORCHFLOWER_CROP_TYPE = BlockTypeBuilder
          .builder(BlockTorchflowerCrop.class)
          .vanillaBlock(VanillaBlockId.TORCHFLOWER_CROP, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockTrapdoor> TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockTrapdoor.class)
          .vanillaBlock(VanillaBlockId.TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockTrappedChest> TRAPPED_CHEST_TYPE = BlockTypeBuilder
          .builder(BlockTrappedChest.class)
          .vanillaBlock(VanillaBlockId.TRAPPED_CHEST, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockTripWire> TRIP_WIRE_TYPE = BlockTypeBuilder
          .builder(BlockTripWire.class)
          .vanillaBlock(VanillaBlockId.TRIP_WIRE, true)
          .withProperties(VanillaBlockPropertyTypes.POWERED_BIT, VanillaBlockPropertyTypes.SUSPENDED_BIT, VanillaBlockPropertyTypes.DISARMED_BIT, VanillaBlockPropertyTypes.ATTACHED_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockTripwireHook> TRIPWIRE_HOOK_TYPE = BlockTypeBuilder
          .builder(BlockTripwireHook.class)
          .vanillaBlock(VanillaBlockId.TRIPWIRE_HOOK, true)
          .withProperties(VanillaBlockPropertyTypes.POWERED_BIT, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockTubeCoral> TUBE_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockTubeCoral.class)
          .vanillaBlock(VanillaBlockId.TUBE_CORAL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockTuff> TUFF_TYPE = BlockTypeBuilder
          .builder(BlockTuff.class)
          .vanillaBlock(VanillaBlockId.TUFF, true)
          .addBasicComponents()
          .build();

  BlockType<BlockTurtleEgg> TURTLE_EGG_TYPE = BlockTypeBuilder
          .builder(BlockTurtleEgg.class)
          .vanillaBlock(VanillaBlockId.TURTLE_EGG, true)
          .withProperties(VanillaBlockPropertyTypes.CRACKED_STATE, VanillaBlockPropertyTypes.TURTLE_EGG_COUNT)
          .addBasicComponents()
          .build();

  BlockType<BlockTwistingVines> TWISTING_VINES_TYPE = BlockTypeBuilder
          .builder(BlockTwistingVines.class)
          .vanillaBlock(VanillaBlockId.TWISTING_VINES, true)
          .withProperties(VanillaBlockPropertyTypes.TWISTING_VINES_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockUnderwaterTorch> UNDERWATER_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockUnderwaterTorch.class)
          .vanillaBlock(VanillaBlockId.UNDERWATER_TORCH, true)
          .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockUndyedShulkerBox> UNDYED_SHULKER_BOX_TYPE = BlockTypeBuilder
          .builder(BlockUndyedShulkerBox.class)
          .vanillaBlock(VanillaBlockId.UNDYED_SHULKER_BOX, true)
          .addBasicComponents()
          .build();

  BlockType<BlockUnknown> UNKNOWN_TYPE = BlockTypeBuilder
          .builder(BlockUnknown.class)
          .vanillaBlock(VanillaBlockId.UNKNOWN, true)
          .addBasicComponents()
          .build();

  BlockType<BlockUnlitRedstoneTorch> UNLIT_REDSTONE_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockUnlitRedstoneTorch.class)
          .vanillaBlock(VanillaBlockId.UNLIT_REDSTONE_TORCH, true)
          .withProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockUnpoweredComparator> UNPOWERED_COMPARATOR_TYPE = BlockTypeBuilder
          .builder(BlockUnpoweredComparator.class)
          .vanillaBlock(VanillaBlockId.UNPOWERED_COMPARATOR, true)
          .withProperties(VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockUnpoweredRepeater> UNPOWERED_REPEATER_TYPE = BlockTypeBuilder
          .builder(BlockUnpoweredRepeater.class)
          .vanillaBlock(VanillaBlockId.UNPOWERED_REPEATER, true)
          .withProperties(VanillaBlockPropertyTypes.REPEATER_DELAY, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockVerdantFroglight> VERDANT_FROGLIGHT_TYPE = BlockTypeBuilder
          .builder(BlockVerdantFroglight.class)
          .vanillaBlock(VanillaBlockId.VERDANT_FROGLIGHT, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockVine> VINE_TYPE = BlockTypeBuilder
          .builder(BlockVine.class)
          .vanillaBlock(VanillaBlockId.VINE, true)
          .withProperties(VanillaBlockPropertyTypes.VINE_DIRECTION_BITS)
          .addBasicComponents()
          .build();

  BlockType<BlockWallBanner> WALL_BANNER_TYPE = BlockTypeBuilder
          .builder(BlockWallBanner.class)
          .vanillaBlock(VanillaBlockId.WALL_BANNER, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWallSign> WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWallSign.class)
          .vanillaBlock(VanillaBlockId.WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedButton> WARPED_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockWarpedButton.class)
          .vanillaBlock(VanillaBlockId.WARPED_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedDoor> WARPED_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockWarpedDoor.class)
          .vanillaBlock(VanillaBlockId.WARPED_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedDoubleSlab> WARPED_DOUBLE_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWarpedDoubleSlab.class)
          .vanillaBlock(VanillaBlockId.WARPED_DOUBLE_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedFence> WARPED_FENCE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFence.class)
          .vanillaBlock(VanillaBlockId.WARPED_FENCE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedFenceGate> WARPED_FENCE_GATE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFenceGate.class)
          .vanillaBlock(VanillaBlockId.WARPED_FENCE_GATE, true)
          .withProperties(VanillaBlockPropertyTypes.IN_WALL_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedFungus> WARPED_FUNGUS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedFungus.class)
          .vanillaBlock(VanillaBlockId.WARPED_FUNGUS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedHangingSign> WARPED_HANGING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWarpedHangingSign.class)
          .vanillaBlock(VanillaBlockId.WARPED_HANGING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.HANGING)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedHyphae> WARPED_HYPHAE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedHyphae.class)
          .vanillaBlock(VanillaBlockId.WARPED_HYPHAE, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedNylium> WARPED_NYLIUM_TYPE = BlockTypeBuilder
          .builder(BlockWarpedNylium.class)
          .vanillaBlock(VanillaBlockId.WARPED_NYLIUM, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedPlanks> WARPED_PLANKS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedPlanks.class)
          .vanillaBlock(VanillaBlockId.WARPED_PLANKS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedPressurePlate> WARPED_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockWarpedPressurePlate.class)
          .vanillaBlock(VanillaBlockId.WARPED_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedRoots> WARPED_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedRoots.class)
          .vanillaBlock(VanillaBlockId.WARPED_ROOTS, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedSlab> WARPED_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWarpedSlab.class)
          .vanillaBlock(VanillaBlockId.WARPED_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedStairs> WARPED_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWarpedStairs.class)
          .vanillaBlock(VanillaBlockId.WARPED_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedStandingSign> WARPED_STANDING_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWarpedStandingSign.class)
          .vanillaBlock(VanillaBlockId.WARPED_STANDING_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedStem> WARPED_STEM_TYPE = BlockTypeBuilder
          .builder(BlockWarpedStem.class)
          .vanillaBlock(VanillaBlockId.WARPED_STEM, true)
          .withProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedTrapdoor> WARPED_TRAPDOOR_TYPE = BlockTypeBuilder
          .builder(BlockWarpedTrapdoor.class)
          .vanillaBlock(VanillaBlockId.WARPED_TRAPDOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedWallSign> WARPED_WALL_SIGN_TYPE = BlockTypeBuilder
          .builder(BlockWarpedWallSign.class)
          .vanillaBlock(VanillaBlockId.WARPED_WALL_SIGN, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWarpedWartBlock> WARPED_WART_BLOCK_TYPE = BlockTypeBuilder
          .builder(BlockWarpedWartBlock.class)
          .vanillaBlock(VanillaBlockId.WARPED_WART_BLOCK, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWater> WATER_TYPE = BlockTypeBuilder
          .builder(BlockWater.class)
          .vanillaBlock(VanillaBlockId.WATER, true)
          .withProperties(VanillaBlockPropertyTypes.LIQUID_DEPTH)
          .addBasicComponents()
          .build();

  BlockType<BlockWaterlily> WATERLILY_TYPE = BlockTypeBuilder
          .builder(BlockWaterlily.class)
          .vanillaBlock(VanillaBlockId.WATERLILY, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCopper> WAXED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCopper.class)
          .vanillaBlock(VanillaBlockId.WAXED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCutCopper> WAXED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCutCopper.class)
          .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCutCopperSlab> WAXED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedCutCopperStairs> WAXED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCutCopperStairs.class)
          .vanillaBlock(VanillaBlockId.WAXED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedDoubleCutCopperSlab> WAXED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedDoubleCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.WAXED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCopper> WAXED_EXPOSED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCopper.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCutCopper> WAXED_EXPOSED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopper.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCutCopperSlab> WAXED_EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedCutCopperStairs> WAXED_EXPOSED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedCutCopperStairs.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedExposedDoubleCutCopperSlab> WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedExposedDoubleCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCopper> WAXED_OXIDIZED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCopper.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCutCopper> WAXED_OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCutCopper.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCutCopperSlab> WAXED_OXIDIZED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedCutCopperStairs> WAXED_OXIDIZED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedCutCopperStairs.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedOxidizedDoubleCutCopperSlab> WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedOxidizedDoubleCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCopper> WAXED_WEATHERED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCopper.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCutCopper> WAXED_WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCutCopper.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCutCopperSlab> WAXED_WEATHERED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredCutCopperStairs> WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredCutCopperStairs.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWaxedWeatheredDoubleCutCopperSlab> WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWaxedWeatheredDoubleCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCopper> WEATHERED_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCopper.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCutCopper> WEATHERED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCutCopper.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCutCopperSlab> WEATHERED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredCutCopperStairs> WEATHERED_CUT_COPPER_STAIRS_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredCutCopperStairs.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_CUT_COPPER_STAIRS, true)
          .withProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWeatheredDoubleCutCopperSlab> WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWeatheredDoubleCutCopperSlab.class)
          .vanillaBlock(VanillaBlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWeb> WEB_TYPE = BlockTypeBuilder
          .builder(BlockWeb.class)
          .vanillaBlock(VanillaBlockId.WEB, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWeepingVines> WEEPING_VINES_TYPE = BlockTypeBuilder
          .builder(BlockWeepingVines.class)
          .vanillaBlock(VanillaBlockId.WEEPING_VINES, true)
          .withProperties(VanillaBlockPropertyTypes.WEEPING_VINES_AGE)
          .addBasicComponents()
          .build();

  BlockType<BlockWheat> WHEAT_TYPE = BlockTypeBuilder
          .builder(BlockWheat.class)
          .vanillaBlock(VanillaBlockId.WHEAT, true)
          .withProperties(VanillaBlockPropertyTypes.GROWTH)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteCandle> WHITE_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockWhiteCandle.class)
          .vanillaBlock(VanillaBlockId.WHITE_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteCandleCake> WHITE_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockWhiteCandleCake.class)
          .vanillaBlock(VanillaBlockId.WHITE_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteCarpet> WHITE_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockWhiteCarpet.class)
          .vanillaBlock(VanillaBlockId.WHITE_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteGlazedTerracotta> WHITE_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockWhiteGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.WHITE_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWhiteWool> WHITE_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockWhiteWool.class)
          .vanillaBlock(VanillaBlockId.WHITE_WOOL, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWitherRose> WITHER_ROSE_TYPE = BlockTypeBuilder
          .builder(BlockWitherRose.class)
          .vanillaBlock(VanillaBlockId.WITHER_ROSE, true)
          .addBasicComponents()
          .build();

  BlockType<BlockWood> WOOD_TYPE = BlockTypeBuilder
          .builder(BlockWood.class)
          .vanillaBlock(VanillaBlockId.WOOD, true)
          .withProperties(VanillaBlockPropertyTypes.WOOD_TYPE, VanillaBlockPropertyTypes.STRIPPED_BIT, VanillaBlockPropertyTypes.PILLAR_AXIS)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenButton> WOODEN_BUTTON_TYPE = BlockTypeBuilder
          .builder(BlockWoodenButton.class)
          .vanillaBlock(VanillaBlockId.WOODEN_BUTTON, true)
          .withProperties(VanillaBlockPropertyTypes.BUTTON_PRESSED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenDoor> WOODEN_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockWoodenDoor.class)
          .vanillaBlock(VanillaBlockId.WOODEN_DOOR, true)
          .withProperties(VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenPressurePlate> WOODEN_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockWoodenPressurePlate.class)
          .vanillaBlock(VanillaBlockId.WOODEN_PRESSURE_PLATE, true)
          .withProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .addBasicComponents()
          .build();

  BlockType<BlockWoodenSlab> WOODEN_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockWoodenSlab.class)
          .vanillaBlock(VanillaBlockId.WOODEN_SLAB, true)
          .withProperties(VanillaBlockPropertyTypes.TOP_SLOT_BIT, VanillaBlockPropertyTypes.WOOD_TYPE)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowCandle> YELLOW_CANDLE_TYPE = BlockTypeBuilder
          .builder(BlockYellowCandle.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CANDLE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT, VanillaBlockPropertyTypes.CANDLES)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowCandleCake> YELLOW_CANDLE_CAKE_TYPE = BlockTypeBuilder
          .builder(BlockYellowCandleCake.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CANDLE_CAKE, true)
          .withProperties(VanillaBlockPropertyTypes.LIT)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowCarpet> YELLOW_CARPET_TYPE = BlockTypeBuilder
          .builder(BlockYellowCarpet.class)
          .vanillaBlock(VanillaBlockId.YELLOW_CARPET, true)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowFlower> YELLOW_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockYellowFlower.class)
          .vanillaBlock(VanillaBlockId.YELLOW_FLOWER, true)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowGlazedTerracotta> YELLOW_GLAZED_TERRACOTTA_TYPE = BlockTypeBuilder
          .builder(BlockYellowGlazedTerracotta.class)
          .vanillaBlock(VanillaBlockId.YELLOW_GLAZED_TERRACOTTA, true)
          .withProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .addBasicComponents()
          .build();

  BlockType<BlockYellowWool> YELLOW_WOOL_TYPE = BlockTypeBuilder
          .builder(BlockYellowWool.class)
          .vanillaBlock(VanillaBlockId.YELLOW_WOOL, true)
          .addBasicComponents()
          .build();
}
