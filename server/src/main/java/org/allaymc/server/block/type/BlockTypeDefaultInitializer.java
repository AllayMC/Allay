package org.allaymc.server.block.type;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.block.impl.*;

public final class BlockTypeDefaultInitializer {
    public static void init() {
        if (BlockTypes.ACACIA_BUTTON == null) {
            BlockTypes.ACACIA_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.ACACIA_DOOR == null) {
            BlockTypes.ACACIA_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.ACACIA_DOUBLE_SLAB == null) {
            BlockTypes.ACACIA_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.ACACIA_FENCE == null) {
            BlockTypes.ACACIA_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_FENCE)
                    .build();
        }
        if (BlockTypes.ACACIA_FENCE_GATE == null) {
            BlockTypes.ACACIA_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.ACACIA_HANGING_SIGN == null) {
            BlockTypes.ACACIA_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.ACACIA_LEAVES == null) {
            BlockTypes.ACACIA_LEAVES = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_LEAVES)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.ACACIA_LOG == null) {
            BlockTypes.ACACIA_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.ACACIA_PLANKS == null) {
            BlockTypes.ACACIA_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_PLANKS)
                    .build();
        }
        if (BlockTypes.ACACIA_PRESSURE_PLATE == null) {
            BlockTypes.ACACIA_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.ACACIA_SAPLING == null) {
            BlockTypes.ACACIA_SAPLING = AllayBlockType
                    .builder(BlockSaplingBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_SAPLING)
                    .setProperties(BlockPropertyTypes.AGE_BIT)
                    .build();
        }
        if (BlockTypes.ACACIA_SHELF == null) {
            BlockTypes.ACACIA_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.ACACIA_SLAB == null) {
            BlockTypes.ACACIA_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.ACACIA_STAIRS == null) {
            BlockTypes.ACACIA_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.ACACIA_STANDING_SIGN == null) {
            BlockTypes.ACACIA_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.ACACIA_TRAPDOOR == null) {
            BlockTypes.ACACIA_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.ACACIA_WALL_SIGN == null) {
            BlockTypes.ACACIA_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.ACACIA_WOOD == null) {
            BlockTypes.ACACIA_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACACIA_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.ACTIVATOR_RAIL == null) {
            BlockTypes.ACTIVATOR_RAIL = AllayBlockType
                    .builder(BlockActivatorRailBehaviorImpl.class)
                    .vanillaBlock(BlockId.ACTIVATOR_RAIL)
                    .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                    .build();
        }
        if (BlockTypes.AIR == null) {
            BlockTypes.AIR = AllayBlockType
                    .builder(BlockAirBehaviorImpl.class)
                    .vanillaBlock(BlockId.AIR)
                    .build();
        }
        if (BlockTypes.ALLIUM == null) {
            BlockTypes.ALLIUM = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.ALLIUM)
                    .build();
        }
        if (BlockTypes.ALLOW == null) {
            BlockTypes.ALLOW = AllayBlockType
                    .builder(BlockAllowBehaviorImpl.class)
                    .vanillaBlock(BlockId.ALLOW)
                    .build();
        }
        if (BlockTypes.AMETHYST_BLOCK == null) {
            BlockTypes.AMETHYST_BLOCK = AllayBlockType
                    .builder(BlockAmethystBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.AMETHYST_BLOCK)
                    .build();
        }
        if (BlockTypes.AMETHYST_CLUSTER == null) {
            BlockTypes.AMETHYST_CLUSTER = AllayBlockType
                    .builder(BlockAmethystClusterBehaviorImpl.class)
                    .vanillaBlock(BlockId.AMETHYST_CLUSTER)
                    .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                    .build();
        }
        if (BlockTypes.ANCIENT_DEBRIS == null) {
            BlockTypes.ANCIENT_DEBRIS = AllayBlockType
                    .builder(BlockAncientDebrisBehaviorImpl.class)
                    .vanillaBlock(BlockId.ANCIENT_DEBRIS)
                    .build();
        }
        if (BlockTypes.ANDESITE == null) {
            BlockTypes.ANDESITE = AllayBlockType
                    .builder(BlockAndesiteBehaviorImpl.class)
                    .vanillaBlock(BlockId.ANDESITE)
                    .build();
        }
        if (BlockTypes.ANDESITE_DOUBLE_SLAB == null) {
            BlockTypes.ANDESITE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.ANDESITE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.ANDESITE_SLAB == null) {
            BlockTypes.ANDESITE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.ANDESITE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.ANDESITE_STAIRS == null) {
            BlockTypes.ANDESITE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.ANDESITE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.ANDESITE_WALL == null) {
            BlockTypes.ANDESITE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.ANDESITE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.ANVIL == null) {
            BlockTypes.ANVIL = AllayBlockType
                    .builder(BlockAnvilBehaviorImpl.class)
                    .vanillaBlock(BlockId.ANVIL)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.AZALEA == null) {
            BlockTypes.AZALEA = AllayBlockType
                    .builder(BlockAzaleaBehaviorImpl.class)
                    .vanillaBlock(BlockId.AZALEA)
                    .build();
        }
        if (BlockTypes.AZALEA_LEAVES == null) {
            BlockTypes.AZALEA_LEAVES = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.AZALEA_LEAVES)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.AZALEA_LEAVES_FLOWERED == null) {
            BlockTypes.AZALEA_LEAVES_FLOWERED = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.AZALEA_LEAVES_FLOWERED)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.AZURE_BLUET == null) {
            BlockTypes.AZURE_BLUET = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.AZURE_BLUET)
                    .build();
        }
        if (BlockTypes.BAMBOO == null) {
            BlockTypes.BAMBOO = AllayBlockType
                    .builder(BlockBambooBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO)
                    .setProperties(BlockPropertyTypes.AGE_BIT, BlockPropertyTypes.BAMBOO_LEAF_SIZE, BlockPropertyTypes.BAMBOO_STALK_THICKNESS)
                    .build();
        }
        if (BlockTypes.BAMBOO_BLOCK == null) {
            BlockTypes.BAMBOO_BLOCK = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_BLOCK)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.BAMBOO_BUTTON == null) {
            BlockTypes.BAMBOO_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.BAMBOO_DOOR == null) {
            BlockTypes.BAMBOO_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.BAMBOO_DOUBLE_SLAB == null) {
            BlockTypes.BAMBOO_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.BAMBOO_FENCE == null) {
            BlockTypes.BAMBOO_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_FENCE)
                    .build();
        }
        if (BlockTypes.BAMBOO_FENCE_GATE == null) {
            BlockTypes.BAMBOO_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.BAMBOO_HANGING_SIGN == null) {
            BlockTypes.BAMBOO_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.BAMBOO_MOSAIC == null) {
            BlockTypes.BAMBOO_MOSAIC = AllayBlockType
                    .builder(BlockBambooMosaicBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_MOSAIC)
                    .build();
        }
        if (BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB == null) {
            BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_MOSAIC_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.BAMBOO_MOSAIC_SLAB == null) {
            BlockTypes.BAMBOO_MOSAIC_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_MOSAIC_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.BAMBOO_MOSAIC_STAIRS == null) {
            BlockTypes.BAMBOO_MOSAIC_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_MOSAIC_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.BAMBOO_PLANKS == null) {
            BlockTypes.BAMBOO_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_PLANKS)
                    .build();
        }
        if (BlockTypes.BAMBOO_PRESSURE_PLATE == null) {
            BlockTypes.BAMBOO_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.BAMBOO_SAPLING == null) {
            BlockTypes.BAMBOO_SAPLING = AllayBlockType
                    .builder(BlockSaplingBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_SAPLING)
                    .setProperties(BlockPropertyTypes.AGE_BIT)
                    .build();
        }
        if (BlockTypes.BAMBOO_SHELF == null) {
            BlockTypes.BAMBOO_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.BAMBOO_SLAB == null) {
            BlockTypes.BAMBOO_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.BAMBOO_STAIRS == null) {
            BlockTypes.BAMBOO_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.BAMBOO_STANDING_SIGN == null) {
            BlockTypes.BAMBOO_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.BAMBOO_TRAPDOOR == null) {
            BlockTypes.BAMBOO_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.BAMBOO_WALL_SIGN == null) {
            BlockTypes.BAMBOO_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.BAMBOO_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.BARREL == null) {
            BlockTypes.BARREL = AllayBlockType
                    .builder(BlockBarrelBehaviorImpl.class)
                    .vanillaBlock(BlockId.BARREL)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.BARRIER == null) {
            BlockTypes.BARRIER = AllayBlockType
                    .builder(BlockBarrierBehaviorImpl.class)
                    .vanillaBlock(BlockId.BARRIER)
                    .build();
        }
        if (BlockTypes.BASALT == null) {
            BlockTypes.BASALT = AllayBlockType
                    .builder(BlockBasaltBehaviorImpl.class)
                    .vanillaBlock(BlockId.BASALT)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.BEACON == null) {
            BlockTypes.BEACON = AllayBlockType
                    .builder(BlockBeaconBehaviorImpl.class)
                    .vanillaBlock(BlockId.BEACON)
                    .build();
        }
        if (BlockTypes.BED == null) {
            BlockTypes.BED = AllayBlockType
                    .builder(BlockBedBehaviorImpl.class)
                    .vanillaBlock(BlockId.BED)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.HEAD_PIECE_BIT, BlockPropertyTypes.OCCUPIED_BIT)
                    .build();
        }
        if (BlockTypes.BEDROCK == null) {
            BlockTypes.BEDROCK = AllayBlockType
                    .builder(BlockBedrockBehaviorImpl.class)
                    .vanillaBlock(BlockId.BEDROCK)
                    .setProperties(BlockPropertyTypes.INFINIBURN_BIT)
                    .build();
        }
        if (BlockTypes.BEE_NEST == null) {
            BlockTypes.BEE_NEST = AllayBlockType
                    .builder(BlockBeeNestBehaviorImpl.class)
                    .vanillaBlock(BlockId.BEE_NEST)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.HONEY_LEVEL)
                    .build();
        }
        if (BlockTypes.BEEHIVE == null) {
            BlockTypes.BEEHIVE = AllayBlockType
                    .builder(BlockBeehiveBehaviorImpl.class)
                    .vanillaBlock(BlockId.BEEHIVE)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.HONEY_LEVEL)
                    .build();
        }
        if (BlockTypes.BEETROOT == null) {
            BlockTypes.BEETROOT = AllayBlockType
                    .builder(BlockBeetrootBehaviorImpl.class)
                    .vanillaBlock(BlockId.BEETROOT)
                    .setProperties(BlockPropertyTypes.GROWTH)
                    .build();
        }
        if (BlockTypes.BELL == null) {
            BlockTypes.BELL = AllayBlockType
                    .builder(BlockBellBehaviorImpl.class)
                    .vanillaBlock(BlockId.BELL)
                    .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.TOGGLE_BIT)
                    .build();
        }
        if (BlockTypes.BIG_DRIPLEAF == null) {
            BlockTypes.BIG_DRIPLEAF = AllayBlockType
                    .builder(BlockBigDripleafBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIG_DRIPLEAF)
                    .setProperties(BlockPropertyTypes.BIG_DRIPLEAF_HEAD, BlockPropertyTypes.BIG_DRIPLEAF_TILT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.BIRCH_BUTTON == null) {
            BlockTypes.BIRCH_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.BIRCH_DOOR == null) {
            BlockTypes.BIRCH_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.BIRCH_DOUBLE_SLAB == null) {
            BlockTypes.BIRCH_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.BIRCH_FENCE == null) {
            BlockTypes.BIRCH_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_FENCE)
                    .build();
        }
        if (BlockTypes.BIRCH_FENCE_GATE == null) {
            BlockTypes.BIRCH_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.BIRCH_HANGING_SIGN == null) {
            BlockTypes.BIRCH_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.BIRCH_LEAVES == null) {
            BlockTypes.BIRCH_LEAVES = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_LEAVES)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.BIRCH_LOG == null) {
            BlockTypes.BIRCH_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.BIRCH_PLANKS == null) {
            BlockTypes.BIRCH_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_PLANKS)
                    .build();
        }
        if (BlockTypes.BIRCH_PRESSURE_PLATE == null) {
            BlockTypes.BIRCH_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.BIRCH_SAPLING == null) {
            BlockTypes.BIRCH_SAPLING = AllayBlockType
                    .builder(BlockSaplingBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_SAPLING)
                    .setProperties(BlockPropertyTypes.AGE_BIT)
                    .build();
        }
        if (BlockTypes.BIRCH_SHELF == null) {
            BlockTypes.BIRCH_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.BIRCH_SLAB == null) {
            BlockTypes.BIRCH_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.BIRCH_STAIRS == null) {
            BlockTypes.BIRCH_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.BIRCH_STANDING_SIGN == null) {
            BlockTypes.BIRCH_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.BIRCH_TRAPDOOR == null) {
            BlockTypes.BIRCH_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.BIRCH_WALL_SIGN == null) {
            BlockTypes.BIRCH_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.BIRCH_WOOD == null) {
            BlockTypes.BIRCH_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.BIRCH_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.BLACK_CANDLE == null) {
            BlockTypes.BLACK_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.BLACK_CANDLE_CAKE == null) {
            BlockTypes.BLACK_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.BLACK_CARPET == null) {
            BlockTypes.BLACK_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_CARPET)
                    .build();
        }
        if (BlockTypes.BLACK_CONCRETE == null) {
            BlockTypes.BLACK_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_CONCRETE)
                    .build();
        }
        if (BlockTypes.BLACK_CONCRETE_POWDER == null) {
            BlockTypes.BLACK_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.BLACK_GLAZED_TERRACOTTA == null) {
            BlockTypes.BLACK_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.BLACK_SHULKER_BOX == null) {
            BlockTypes.BLACK_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.BLACK_STAINED_GLASS == null) {
            BlockTypes.BLACK_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.BLACK_STAINED_GLASS_PANE == null) {
            BlockTypes.BLACK_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.BLACK_TERRACOTTA == null) {
            BlockTypes.BLACK_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.BLACK_WOOL == null) {
            BlockTypes.BLACK_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACK_WOOL)
                    .build();
        }
        if (BlockTypes.BLACKSTONE == null) {
            BlockTypes.BLACKSTONE = AllayBlockType
                    .builder(BlockBlackstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACKSTONE)
                    .build();
        }
        if (BlockTypes.BLACKSTONE_DOUBLE_SLAB == null) {
            BlockTypes.BLACKSTONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACKSTONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.BLACKSTONE_SLAB == null) {
            BlockTypes.BLACKSTONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACKSTONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.BLACKSTONE_STAIRS == null) {
            BlockTypes.BLACKSTONE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACKSTONE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.BLACKSTONE_WALL == null) {
            BlockTypes.BLACKSTONE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLACKSTONE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.BLAST_FURNACE == null) {
            BlockTypes.BLAST_FURNACE = AllayBlockType
                    .builder(BlockBlastFurnaceBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLAST_FURNACE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.BLUE_CANDLE == null) {
            BlockTypes.BLUE_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.BLUE_CANDLE_CAKE == null) {
            BlockTypes.BLUE_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.BLUE_CARPET == null) {
            BlockTypes.BLUE_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_CARPET)
                    .build();
        }
        if (BlockTypes.BLUE_CONCRETE == null) {
            BlockTypes.BLUE_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_CONCRETE)
                    .build();
        }
        if (BlockTypes.BLUE_CONCRETE_POWDER == null) {
            BlockTypes.BLUE_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.BLUE_GLAZED_TERRACOTTA == null) {
            BlockTypes.BLUE_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.BLUE_ICE == null) {
            BlockTypes.BLUE_ICE = AllayBlockType
                    .builder(BlockIceBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_ICE)
                    .build();
        }
        if (BlockTypes.BLUE_ORCHID == null) {
            BlockTypes.BLUE_ORCHID = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.BLUE_ORCHID)
                    .build();
        }
        if (BlockTypes.BLUE_SHULKER_BOX == null) {
            BlockTypes.BLUE_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.BLUE_STAINED_GLASS == null) {
            BlockTypes.BLUE_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.BLUE_STAINED_GLASS_PANE == null) {
            BlockTypes.BLUE_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.BLUE_TERRACOTTA == null) {
            BlockTypes.BLUE_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.BLUE_WOOL == null) {
            BlockTypes.BLUE_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.BLUE_WOOL)
                    .build();
        }
        if (BlockTypes.BONE_BLOCK == null) {
            BlockTypes.BONE_BLOCK = AllayBlockType
                    .builder(BlockBoneBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.BONE_BLOCK)
                    .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.BOOKSHELF == null) {
            BlockTypes.BOOKSHELF = AllayBlockType
                    .builder(BlockBookshelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.BOOKSHELF)
                    .build();
        }
        if (BlockTypes.BORDER_BLOCK == null) {
            BlockTypes.BORDER_BLOCK = AllayBlockType
                    .builder(BlockBorderBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.BORDER_BLOCK)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.BRAIN_CORAL == null) {
            BlockTypes.BRAIN_CORAL = AllayBlockType
                    .builder(BlockCoralBehaviorImpl.class)
                    .vanillaBlock(BlockId.BRAIN_CORAL)
                    .build();
        }
        if (BlockTypes.BRAIN_CORAL_BLOCK == null) {
            BlockTypes.BRAIN_CORAL_BLOCK = AllayBlockType
                    .builder(BlockCoralBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.BRAIN_CORAL_BLOCK)
                    .build();
        }
        if (BlockTypes.BRAIN_CORAL_FAN == null) {
            BlockTypes.BRAIN_CORAL_FAN = AllayBlockType
                    .builder(BlockCoralFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.BRAIN_CORAL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                    .build();
        }
        if (BlockTypes.BRAIN_CORAL_WALL_FAN == null) {
            BlockTypes.BRAIN_CORAL_WALL_FAN = AllayBlockType
                    .builder(BlockCoralWallFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.BRAIN_CORAL_WALL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.BREWING_STAND == null) {
            BlockTypes.BREWING_STAND = AllayBlockType
                    .builder(BlockBrewingStandBehaviorImpl.class)
                    .vanillaBlock(BlockId.BREWING_STAND)
                    .setProperties(BlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
                    .build();
        }
        if (BlockTypes.BRICK_BLOCK == null) {
            BlockTypes.BRICK_BLOCK = AllayBlockType
                    .builder(BlockBrickBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.BRICK_BLOCK)
                    .build();
        }
        if (BlockTypes.BRICK_DOUBLE_SLAB == null) {
            BlockTypes.BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.BRICK_SLAB == null) {
            BlockTypes.BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.BRICK_STAIRS == null) {
            BlockTypes.BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.BRICK_WALL == null) {
            BlockTypes.BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.BROWN_CANDLE == null) {
            BlockTypes.BROWN_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.BROWN_CANDLE_CAKE == null) {
            BlockTypes.BROWN_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.BROWN_CARPET == null) {
            BlockTypes.BROWN_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_CARPET)
                    .build();
        }
        if (BlockTypes.BROWN_CONCRETE == null) {
            BlockTypes.BROWN_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_CONCRETE)
                    .build();
        }
        if (BlockTypes.BROWN_CONCRETE_POWDER == null) {
            BlockTypes.BROWN_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.BROWN_GLAZED_TERRACOTTA == null) {
            BlockTypes.BROWN_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.BROWN_MUSHROOM == null) {
            BlockTypes.BROWN_MUSHROOM = AllayBlockType
                    .builder(BlockBrownMushroomBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_MUSHROOM)
                    .build();
        }
        if (BlockTypes.BROWN_MUSHROOM_BLOCK == null) {
            BlockTypes.BROWN_MUSHROOM_BLOCK = AllayBlockType
                    .builder(BlockBrownMushroomBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_MUSHROOM_BLOCK)
                    .setProperties(BlockPropertyTypes.HUGE_MUSHROOM_BITS)
                    .build();
        }
        if (BlockTypes.BROWN_SHULKER_BOX == null) {
            BlockTypes.BROWN_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.BROWN_STAINED_GLASS == null) {
            BlockTypes.BROWN_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.BROWN_STAINED_GLASS_PANE == null) {
            BlockTypes.BROWN_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.BROWN_TERRACOTTA == null) {
            BlockTypes.BROWN_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.BROWN_WOOL == null) {
            BlockTypes.BROWN_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.BROWN_WOOL)
                    .build();
        }
        if (BlockTypes.BUBBLE_COLUMN == null) {
            BlockTypes.BUBBLE_COLUMN = AllayBlockType
                    .builder(BlockBubbleColumnBehaviorImpl.class)
                    .vanillaBlock(BlockId.BUBBLE_COLUMN)
                    .setProperties(BlockPropertyTypes.DRAG_DOWN)
                    .build();
        }
        if (BlockTypes.BUBBLE_CORAL == null) {
            BlockTypes.BUBBLE_CORAL = AllayBlockType
                    .builder(BlockCoralBehaviorImpl.class)
                    .vanillaBlock(BlockId.BUBBLE_CORAL)
                    .build();
        }
        if (BlockTypes.BUBBLE_CORAL_BLOCK == null) {
            BlockTypes.BUBBLE_CORAL_BLOCK = AllayBlockType
                    .builder(BlockCoralBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.BUBBLE_CORAL_BLOCK)
                    .build();
        }
        if (BlockTypes.BUBBLE_CORAL_FAN == null) {
            BlockTypes.BUBBLE_CORAL_FAN = AllayBlockType
                    .builder(BlockCoralFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.BUBBLE_CORAL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                    .build();
        }
        if (BlockTypes.BUBBLE_CORAL_WALL_FAN == null) {
            BlockTypes.BUBBLE_CORAL_WALL_FAN = AllayBlockType
                    .builder(BlockCoralWallFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.BUBBLE_CORAL_WALL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.BUDDING_AMETHYST == null) {
            BlockTypes.BUDDING_AMETHYST = AllayBlockType
                    .builder(BlockBuddingAmethystBehaviorImpl.class)
                    .vanillaBlock(BlockId.BUDDING_AMETHYST)
                    .build();
        }
        if (BlockTypes.BUSH == null) {
            BlockTypes.BUSH = AllayBlockType
                    .builder(BlockBushBehaviorImpl.class)
                    .vanillaBlock(BlockId.BUSH)
                    .build();
        }
        if (BlockTypes.CACTUS == null) {
            BlockTypes.CACTUS = AllayBlockType
                    .builder(BlockCactusBehaviorImpl.class)
                    .vanillaBlock(BlockId.CACTUS)
                    .setProperties(BlockPropertyTypes.AGE_16)
                    .build();
        }
        if (BlockTypes.CACTUS_FLOWER == null) {
            BlockTypes.CACTUS_FLOWER = AllayBlockType
                    .builder(BlockCactusFlowerBehaviorImpl.class)
                    .vanillaBlock(BlockId.CACTUS_FLOWER)
                    .build();
        }
        if (BlockTypes.CAKE == null) {
            BlockTypes.CAKE = AllayBlockType
                    .builder(BlockCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.CAKE)
                    .setProperties(BlockPropertyTypes.BITE_COUNTER)
                    .build();
        }
        if (BlockTypes.CALCITE == null) {
            BlockTypes.CALCITE = AllayBlockType
                    .builder(BlockCalciteBehaviorImpl.class)
                    .vanillaBlock(BlockId.CALCITE)
                    .build();
        }
        if (BlockTypes.CALIBRATED_SCULK_SENSOR == null) {
            BlockTypes.CALIBRATED_SCULK_SENSOR = AllayBlockType
                    .builder(BlockCalibratedSculkSensorBehaviorImpl.class)
                    .vanillaBlock(BlockId.CALIBRATED_SCULK_SENSOR)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.SCULK_SENSOR_PHASE)
                    .build();
        }
        if (BlockTypes.CAMERA == null) {
            BlockTypes.CAMERA = AllayBlockType
                    .builder(BlockCameraBehaviorImpl.class)
                    .vanillaBlock(BlockId.CAMERA)
                    .build();
        }
        if (BlockTypes.CAMPFIRE == null) {
            BlockTypes.CAMPFIRE = AllayBlockType
                    .builder(BlockCampfireBehaviorImpl.class)
                    .vanillaBlock(BlockId.CAMPFIRE)
                    .setProperties(BlockPropertyTypes.EXTINGUISHED, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.CANDLE == null) {
            BlockTypes.CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.CANDLE_CAKE == null) {
            BlockTypes.CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.CARROTS == null) {
            BlockTypes.CARROTS = AllayBlockType
                    .builder(BlockCarrotsBehaviorImpl.class)
                    .vanillaBlock(BlockId.CARROTS)
                    .setProperties(BlockPropertyTypes.GROWTH)
                    .build();
        }
        if (BlockTypes.CARTOGRAPHY_TABLE == null) {
            BlockTypes.CARTOGRAPHY_TABLE = AllayBlockType
                    .builder(BlockCartographyTableBehaviorImpl.class)
                    .vanillaBlock(BlockId.CARTOGRAPHY_TABLE)
                    .build();
        }
        if (BlockTypes.CARVED_PUMPKIN == null) {
            BlockTypes.CARVED_PUMPKIN = AllayBlockType
                    .builder(BlockCarvedPumpkinBehaviorImpl.class)
                    .vanillaBlock(BlockId.CARVED_PUMPKIN)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.CAULDRON == null) {
            BlockTypes.CAULDRON = AllayBlockType
                    .builder(BlockCauldronBehaviorImpl.class)
                    .vanillaBlock(BlockId.CAULDRON)
                    .setProperties(BlockPropertyTypes.CAULDRON_LIQUID, BlockPropertyTypes.FILL_LEVEL)
                    .build();
        }
        if (BlockTypes.CAVE_VINES == null) {
            BlockTypes.CAVE_VINES = AllayBlockType
                    .builder(BlockCaveVinesBehaviorImpl.class)
                    .vanillaBlock(BlockId.CAVE_VINES)
                    .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                    .build();
        }
        if (BlockTypes.CAVE_VINES_BODY_WITH_BERRIES == null) {
            BlockTypes.CAVE_VINES_BODY_WITH_BERRIES = AllayBlockType
                    .builder(BlockCaveVinesBodyWithBerriesBehaviorImpl.class)
                    .vanillaBlock(BlockId.CAVE_VINES_BODY_WITH_BERRIES)
                    .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                    .build();
        }
        if (BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES == null) {
            BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES = AllayBlockType
                    .builder(BlockCaveVinesHeadWithBerriesBehaviorImpl.class)
                    .vanillaBlock(BlockId.CAVE_VINES_HEAD_WITH_BERRIES)
                    .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                    .build();
        }
        if (BlockTypes.CHAIN_COMMAND_BLOCK == null) {
            BlockTypes.CHAIN_COMMAND_BLOCK = AllayBlockType
                    .builder(BlockChainCommandBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHAIN_COMMAND_BLOCK)
                    .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.CHALKBOARD == null) {
            BlockTypes.CHALKBOARD = AllayBlockType
                    .builder(BlockChalkboardBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHALKBOARD)
                    .setProperties(BlockPropertyTypes.DIRECTION_16)
                    .build();
        }
        if (BlockTypes.CHEMICAL_HEAT == null) {
            BlockTypes.CHEMICAL_HEAT = AllayBlockType
                    .builder(BlockChemicalHeatBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHEMICAL_HEAT)
                    .build();
        }
        if (BlockTypes.CHERRY_BUTTON == null) {
            BlockTypes.CHERRY_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.CHERRY_DOOR == null) {
            BlockTypes.CHERRY_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.CHERRY_DOUBLE_SLAB == null) {
            BlockTypes.CHERRY_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.CHERRY_FENCE == null) {
            BlockTypes.CHERRY_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_FENCE)
                    .build();
        }
        if (BlockTypes.CHERRY_FENCE_GATE == null) {
            BlockTypes.CHERRY_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.CHERRY_HANGING_SIGN == null) {
            BlockTypes.CHERRY_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.CHERRY_LEAVES == null) {
            BlockTypes.CHERRY_LEAVES = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_LEAVES)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.CHERRY_LOG == null) {
            BlockTypes.CHERRY_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.CHERRY_PLANKS == null) {
            BlockTypes.CHERRY_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_PLANKS)
                    .build();
        }
        if (BlockTypes.CHERRY_PRESSURE_PLATE == null) {
            BlockTypes.CHERRY_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.CHERRY_SAPLING == null) {
            BlockTypes.CHERRY_SAPLING = AllayBlockType
                    .builder(BlockSaplingBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_SAPLING)
                    .setProperties(BlockPropertyTypes.AGE_BIT)
                    .build();
        }
        if (BlockTypes.CHERRY_SHELF == null) {
            BlockTypes.CHERRY_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.CHERRY_SLAB == null) {
            BlockTypes.CHERRY_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.CHERRY_STAIRS == null) {
            BlockTypes.CHERRY_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.CHERRY_STANDING_SIGN == null) {
            BlockTypes.CHERRY_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.CHERRY_TRAPDOOR == null) {
            BlockTypes.CHERRY_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.CHERRY_WALL_SIGN == null) {
            BlockTypes.CHERRY_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.CHERRY_WOOD == null) {
            BlockTypes.CHERRY_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHERRY_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.CHEST == null) {
            BlockTypes.CHEST = AllayBlockType
                    .builder(BlockChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.CHIPPED_ANVIL == null) {
            BlockTypes.CHIPPED_ANVIL = AllayBlockType
                    .builder(BlockAnvilBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHIPPED_ANVIL)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.CHISELED_BOOKSHELF == null) {
            BlockTypes.CHISELED_BOOKSHELF = AllayBlockType
                    .builder(BlockChiseledBookshelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_BOOKSHELF)
                    .setProperties(BlockPropertyTypes.BOOKS_STORED, BlockPropertyTypes.DIRECTION_4)
                    .build();
        }
        if (BlockTypes.CHISELED_COPPER == null) {
            BlockTypes.CHISELED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_COPPER)
                    .build();
        }
        if (BlockTypes.CHISELED_DEEPSLATE == null) {
            BlockTypes.CHISELED_DEEPSLATE = AllayBlockType
                    .builder(BlockChiseledDeepslateBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_DEEPSLATE)
                    .build();
        }
        if (BlockTypes.CHISELED_NETHER_BRICKS == null) {
            BlockTypes.CHISELED_NETHER_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_NETHER_BRICKS)
                    .build();
        }
        if (BlockTypes.CHISELED_POLISHED_BLACKSTONE == null) {
            BlockTypes.CHISELED_POLISHED_BLACKSTONE = AllayBlockType
                    .builder(BlockChiseledPolishedBlackstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_POLISHED_BLACKSTONE)
                    .build();
        }
        if (BlockTypes.CHISELED_QUARTZ_BLOCK == null) {
            BlockTypes.CHISELED_QUARTZ_BLOCK = AllayBlockType
                    .builder(BlockChiseledQuartzBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_QUARTZ_BLOCK)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.CHISELED_RED_SANDSTONE == null) {
            BlockTypes.CHISELED_RED_SANDSTONE = AllayBlockType
                    .builder(BlockSandstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_RED_SANDSTONE)
                    .build();
        }
        if (BlockTypes.CHISELED_RESIN_BRICKS == null) {
            BlockTypes.CHISELED_RESIN_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_RESIN_BRICKS)
                    .build();
        }
        if (BlockTypes.CHISELED_SANDSTONE == null) {
            BlockTypes.CHISELED_SANDSTONE = AllayBlockType
                    .builder(BlockSandstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_SANDSTONE)
                    .build();
        }
        if (BlockTypes.CHISELED_STONE_BRICKS == null) {
            BlockTypes.CHISELED_STONE_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_STONE_BRICKS)
                    .build();
        }
        if (BlockTypes.CHISELED_TUFF == null) {
            BlockTypes.CHISELED_TUFF = AllayBlockType
                    .builder(BlockChiseledTuffBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_TUFF)
                    .build();
        }
        if (BlockTypes.CHISELED_TUFF_BRICKS == null) {
            BlockTypes.CHISELED_TUFF_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHISELED_TUFF_BRICKS)
                    .build();
        }
        if (BlockTypes.CHORUS_FLOWER == null) {
            BlockTypes.CHORUS_FLOWER = AllayBlockType
                    .builder(BlockChorusFlowerBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHORUS_FLOWER)
                    .setProperties(BlockPropertyTypes.AGE_6)
                    .build();
        }
        if (BlockTypes.CHORUS_PLANT == null) {
            BlockTypes.CHORUS_PLANT = AllayBlockType
                    .builder(BlockChorusPlantBehaviorImpl.class)
                    .vanillaBlock(BlockId.CHORUS_PLANT)
                    .build();
        }
        if (BlockTypes.CLAY == null) {
            BlockTypes.CLAY = AllayBlockType
                    .builder(BlockClayBehaviorImpl.class)
                    .vanillaBlock(BlockId.CLAY)
                    .build();
        }
        if (BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK == null) {
            BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK = AllayBlockType
                    .builder(BlockClientRequestPlaceholderBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
                    .build();
        }
        if (BlockTypes.CLOSED_EYEBLOSSOM == null) {
            BlockTypes.CLOSED_EYEBLOSSOM = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.CLOSED_EYEBLOSSOM)
                    .build();
        }
        if (BlockTypes.COAL_BLOCK == null) {
            BlockTypes.COAL_BLOCK = AllayBlockType
                    .builder(BlockCoalBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.COAL_BLOCK)
                    .build();
        }
        if (BlockTypes.COAL_ORE == null) {
            BlockTypes.COAL_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.COAL_ORE)
                    .build();
        }
        if (BlockTypes.COARSE_DIRT == null) {
            BlockTypes.COARSE_DIRT = AllayBlockType
                    .builder(BlockCoarseDirtBehaviorImpl.class)
                    .vanillaBlock(BlockId.COARSE_DIRT)
                    .build();
        }
        if (BlockTypes.COBBLED_DEEPSLATE == null) {
            BlockTypes.COBBLED_DEEPSLATE = AllayBlockType
                    .builder(BlockCobbledDeepslateBehaviorImpl.class)
                    .vanillaBlock(BlockId.COBBLED_DEEPSLATE)
                    .build();
        }
        if (BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB == null) {
            BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.COBBLED_DEEPSLATE_SLAB == null) {
            BlockTypes.COBBLED_DEEPSLATE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.COBBLED_DEEPSLATE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.COBBLED_DEEPSLATE_STAIRS == null) {
            BlockTypes.COBBLED_DEEPSLATE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.COBBLED_DEEPSLATE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.COBBLED_DEEPSLATE_WALL == null) {
            BlockTypes.COBBLED_DEEPSLATE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.COBBLED_DEEPSLATE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.COBBLESTONE == null) {
            BlockTypes.COBBLESTONE = AllayBlockType
                    .builder(BlockCobblestoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.COBBLESTONE)
                    .build();
        }
        if (BlockTypes.COBBLESTONE_DOUBLE_SLAB == null) {
            BlockTypes.COBBLESTONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.COBBLESTONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.COBBLESTONE_SLAB == null) {
            BlockTypes.COBBLESTONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.COBBLESTONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.COBBLESTONE_WALL == null) {
            BlockTypes.COBBLESTONE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.COBBLESTONE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.COCOA == null) {
            BlockTypes.COCOA = AllayBlockType
                    .builder(BlockCocoaBehaviorImpl.class)
                    .vanillaBlock(BlockId.COCOA)
                    .setProperties(BlockPropertyTypes.AGE_3, BlockPropertyTypes.DIRECTION_4)
                    .build();
        }
        if (BlockTypes.COLORED_TORCH_BLUE == null) {
            BlockTypes.COLORED_TORCH_BLUE = AllayBlockType
                    .builder(BlockTorchBehaviorImpl.class)
                    .vanillaBlock(BlockId.COLORED_TORCH_BLUE)
                    .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.COLORED_TORCH_GREEN == null) {
            BlockTypes.COLORED_TORCH_GREEN = AllayBlockType
                    .builder(BlockTorchBehaviorImpl.class)
                    .vanillaBlock(BlockId.COLORED_TORCH_GREEN)
                    .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.COLORED_TORCH_PURPLE == null) {
            BlockTypes.COLORED_TORCH_PURPLE = AllayBlockType
                    .builder(BlockTorchBehaviorImpl.class)
                    .vanillaBlock(BlockId.COLORED_TORCH_PURPLE)
                    .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.COLORED_TORCH_RED == null) {
            BlockTypes.COLORED_TORCH_RED = AllayBlockType
                    .builder(BlockTorchBehaviorImpl.class)
                    .vanillaBlock(BlockId.COLORED_TORCH_RED)
                    .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.COMMAND_BLOCK == null) {
            BlockTypes.COMMAND_BLOCK = AllayBlockType
                    .builder(BlockCommandBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.COMMAND_BLOCK)
                    .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.COMPOSTER == null) {
            BlockTypes.COMPOSTER = AllayBlockType
                    .builder(BlockComposterBehaviorImpl.class)
                    .vanillaBlock(BlockId.COMPOSTER)
                    .setProperties(BlockPropertyTypes.COMPOSTER_FILL_LEVEL)
                    .build();
        }
        if (BlockTypes.COMPOUND_CREATOR == null) {
            BlockTypes.COMPOUND_CREATOR = AllayBlockType
                    .builder(BlockCompoundCreatorBehaviorImpl.class)
                    .vanillaBlock(BlockId.COMPOUND_CREATOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4)
                    .build();
        }
        if (BlockTypes.CONDUIT == null) {
            BlockTypes.CONDUIT = AllayBlockType
                    .builder(BlockConduitBehaviorImpl.class)
                    .vanillaBlock(BlockId.CONDUIT)
                    .build();
        }
        if (BlockTypes.COPPER_BARS == null) {
            BlockTypes.COPPER_BARS = AllayBlockType
                    .builder(BlockCopperBarsBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_BARS)
                    .build();
        }
        if (BlockTypes.COPPER_BLOCK == null) {
            BlockTypes.COPPER_BLOCK = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_BLOCK)
                    .build();
        }
        if (BlockTypes.COPPER_BULB == null) {
            BlockTypes.COPPER_BULB = AllayBlockType
                    .builder(BlockCopperBulbBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_BULB)
                    .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.COPPER_CHAIN == null) {
            BlockTypes.COPPER_CHAIN = AllayBlockType
                    .builder(BlockCopperChainBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_CHAIN)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.COPPER_CHEST == null) {
            BlockTypes.COPPER_CHEST = AllayBlockType
                    .builder(BlockCopperChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.COPPER_DOOR == null) {
            BlockTypes.COPPER_DOOR = AllayBlockType
                    .builder(BlockCopperDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.COPPER_GOLEM_STATUE == null) {
            BlockTypes.COPPER_GOLEM_STATUE = AllayBlockType
                    .builder(BlockCopperGolemStatueBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_GOLEM_STATUE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.COPPER_GRATE == null) {
            BlockTypes.COPPER_GRATE = AllayBlockType
                    .builder(BlockCopperGrateBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_GRATE)
                    .build();
        }
        if (BlockTypes.COPPER_LANTERN == null) {
            BlockTypes.COPPER_LANTERN = AllayBlockType
                    .builder(BlockCopperLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_LANTERN)
                    .setProperties(BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.COPPER_ORE == null) {
            BlockTypes.COPPER_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_ORE)
                    .build();
        }
        if (BlockTypes.COPPER_TORCH == null) {
            BlockTypes.COPPER_TORCH = AllayBlockType
                    .builder(BlockTorchBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_TORCH)
                    .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.COPPER_TRAPDOOR == null) {
            BlockTypes.COPPER_TRAPDOOR = AllayBlockType
                    .builder(BlockCopperTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.COPPER_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.CORNFLOWER == null) {
            BlockTypes.CORNFLOWER = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.CORNFLOWER)
                    .build();
        }
        if (BlockTypes.CRACKED_DEEPSLATE_BRICKS == null) {
            BlockTypes.CRACKED_DEEPSLATE_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRACKED_DEEPSLATE_BRICKS)
                    .build();
        }
        if (BlockTypes.CRACKED_DEEPSLATE_TILES == null) {
            BlockTypes.CRACKED_DEEPSLATE_TILES = AllayBlockType
                    .builder(BlockCrackedDeepslateTilesBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRACKED_DEEPSLATE_TILES)
                    .build();
        }
        if (BlockTypes.CRACKED_NETHER_BRICKS == null) {
            BlockTypes.CRACKED_NETHER_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRACKED_NETHER_BRICKS)
                    .build();
        }
        if (BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS == null) {
            BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
                    .build();
        }
        if (BlockTypes.CRACKED_STONE_BRICKS == null) {
            BlockTypes.CRACKED_STONE_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRACKED_STONE_BRICKS)
                    .build();
        }
        if (BlockTypes.CRAFTER == null) {
            BlockTypes.CRAFTER = AllayBlockType
                    .builder(BlockCrafterBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRAFTER)
                    .setProperties(BlockPropertyTypes.CRAFTING, BlockPropertyTypes.ORIENTATION, BlockPropertyTypes.TRIGGERED_BIT)
                    .build();
        }
        if (BlockTypes.CRAFTING_TABLE == null) {
            BlockTypes.CRAFTING_TABLE = AllayBlockType
                    .builder(BlockCraftingTableBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRAFTING_TABLE)
                    .build();
        }
        if (BlockTypes.CREAKING_HEART == null) {
            BlockTypes.CREAKING_HEART = AllayBlockType
                    .builder(BlockCreakingHeartBehaviorImpl.class)
                    .vanillaBlock(BlockId.CREAKING_HEART)
                    .setProperties(BlockPropertyTypes.CREAKING_HEART_STATE, BlockPropertyTypes.NATURAL, BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.CREEPER_HEAD == null) {
            BlockTypes.CREEPER_HEAD = AllayBlockType
                    .builder(BlockHeadBehaviorImpl.class)
                    .vanillaBlock(BlockId.CREEPER_HEAD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.CRIMSON_BUTTON == null) {
            BlockTypes.CRIMSON_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.CRIMSON_DOOR == null) {
            BlockTypes.CRIMSON_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.CRIMSON_DOUBLE_SLAB == null) {
            BlockTypes.CRIMSON_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.CRIMSON_FENCE == null) {
            BlockTypes.CRIMSON_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_FENCE)
                    .build();
        }
        if (BlockTypes.CRIMSON_FENCE_GATE == null) {
            BlockTypes.CRIMSON_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.CRIMSON_FUNGUS == null) {
            BlockTypes.CRIMSON_FUNGUS = AllayBlockType
                    .builder(BlockCrimsonFungusBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_FUNGUS)
                    .build();
        }
        if (BlockTypes.CRIMSON_HANGING_SIGN == null) {
            BlockTypes.CRIMSON_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.CRIMSON_HYPHAE == null) {
            BlockTypes.CRIMSON_HYPHAE = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_HYPHAE)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.CRIMSON_NYLIUM == null) {
            BlockTypes.CRIMSON_NYLIUM = AllayBlockType
                    .builder(BlockCrimsonNyliumBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_NYLIUM)
                    .build();
        }
        if (BlockTypes.CRIMSON_PLANKS == null) {
            BlockTypes.CRIMSON_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_PLANKS)
                    .build();
        }
        if (BlockTypes.CRIMSON_PRESSURE_PLATE == null) {
            BlockTypes.CRIMSON_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.CRIMSON_ROOTS == null) {
            BlockTypes.CRIMSON_ROOTS = AllayBlockType
                    .builder(BlockCrimsonRootsBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_ROOTS)
                    .build();
        }
        if (BlockTypes.CRIMSON_SHELF == null) {
            BlockTypes.CRIMSON_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.CRIMSON_SLAB == null) {
            BlockTypes.CRIMSON_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.CRIMSON_STAIRS == null) {
            BlockTypes.CRIMSON_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.CRIMSON_STANDING_SIGN == null) {
            BlockTypes.CRIMSON_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.CRIMSON_STEM == null) {
            BlockTypes.CRIMSON_STEM = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_STEM)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.CRIMSON_TRAPDOOR == null) {
            BlockTypes.CRIMSON_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.CRIMSON_WALL_SIGN == null) {
            BlockTypes.CRIMSON_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRIMSON_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.CRYING_OBSIDIAN == null) {
            BlockTypes.CRYING_OBSIDIAN = AllayBlockType
                    .builder(BlockCryingObsidianBehaviorImpl.class)
                    .vanillaBlock(BlockId.CRYING_OBSIDIAN)
                    .build();
        }
        if (BlockTypes.CUT_COPPER == null) {
            BlockTypes.CUT_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.CUT_COPPER)
                    .build();
        }
        if (BlockTypes.CUT_COPPER_SLAB == null) {
            BlockTypes.CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.CUT_COPPER_STAIRS == null) {
            BlockTypes.CUT_COPPER_STAIRS = AllayBlockType
                    .builder(BlockCopperStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.CUT_COPPER_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.CUT_RED_SANDSTONE == null) {
            BlockTypes.CUT_RED_SANDSTONE = AllayBlockType
                    .builder(BlockSandstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.CUT_RED_SANDSTONE)
                    .build();
        }
        if (BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB == null) {
            BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.CUT_RED_SANDSTONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.CUT_RED_SANDSTONE_SLAB == null) {
            BlockTypes.CUT_RED_SANDSTONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.CUT_RED_SANDSTONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.CUT_SANDSTONE == null) {
            BlockTypes.CUT_SANDSTONE = AllayBlockType
                    .builder(BlockSandstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.CUT_SANDSTONE)
                    .build();
        }
        if (BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB == null) {
            BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.CUT_SANDSTONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.CUT_SANDSTONE_SLAB == null) {
            BlockTypes.CUT_SANDSTONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.CUT_SANDSTONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.CYAN_CANDLE == null) {
            BlockTypes.CYAN_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.CYAN_CANDLE_CAKE == null) {
            BlockTypes.CYAN_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.CYAN_CARPET == null) {
            BlockTypes.CYAN_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_CARPET)
                    .build();
        }
        if (BlockTypes.CYAN_CONCRETE == null) {
            BlockTypes.CYAN_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_CONCRETE)
                    .build();
        }
        if (BlockTypes.CYAN_CONCRETE_POWDER == null) {
            BlockTypes.CYAN_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.CYAN_GLAZED_TERRACOTTA == null) {
            BlockTypes.CYAN_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.CYAN_SHULKER_BOX == null) {
            BlockTypes.CYAN_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.CYAN_STAINED_GLASS == null) {
            BlockTypes.CYAN_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.CYAN_STAINED_GLASS_PANE == null) {
            BlockTypes.CYAN_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.CYAN_TERRACOTTA == null) {
            BlockTypes.CYAN_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.CYAN_WOOL == null) {
            BlockTypes.CYAN_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.CYAN_WOOL)
                    .build();
        }
        if (BlockTypes.DAMAGED_ANVIL == null) {
            BlockTypes.DAMAGED_ANVIL = AllayBlockType
                    .builder(BlockAnvilBehaviorImpl.class)
                    .vanillaBlock(BlockId.DAMAGED_ANVIL)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.DANDELION == null) {
            BlockTypes.DANDELION = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.DANDELION)
                    .build();
        }
        if (BlockTypes.DARK_OAK_BUTTON == null) {
            BlockTypes.DARK_OAK_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.DARK_OAK_DOOR == null) {
            BlockTypes.DARK_OAK_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.DARK_OAK_DOUBLE_SLAB == null) {
            BlockTypes.DARK_OAK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DARK_OAK_FENCE == null) {
            BlockTypes.DARK_OAK_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_FENCE)
                    .build();
        }
        if (BlockTypes.DARK_OAK_FENCE_GATE == null) {
            BlockTypes.DARK_OAK_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.DARK_OAK_HANGING_SIGN == null) {
            BlockTypes.DARK_OAK_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.DARK_OAK_LEAVES == null) {
            BlockTypes.DARK_OAK_LEAVES = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_LEAVES)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.DARK_OAK_LOG == null) {
            BlockTypes.DARK_OAK_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.DARK_OAK_PLANKS == null) {
            BlockTypes.DARK_OAK_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_PLANKS)
                    .build();
        }
        if (BlockTypes.DARK_OAK_PRESSURE_PLATE == null) {
            BlockTypes.DARK_OAK_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.DARK_OAK_SAPLING == null) {
            BlockTypes.DARK_OAK_SAPLING = AllayBlockType
                    .builder(BlockSaplingBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_SAPLING)
                    .setProperties(BlockPropertyTypes.AGE_BIT)
                    .build();
        }
        if (BlockTypes.DARK_OAK_SHELF == null) {
            BlockTypes.DARK_OAK_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.DARK_OAK_SLAB == null) {
            BlockTypes.DARK_OAK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DARK_OAK_STAIRS == null) {
            BlockTypes.DARK_OAK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.DARK_OAK_TRAPDOOR == null) {
            BlockTypes.DARK_OAK_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.DARK_OAK_WOOD == null) {
            BlockTypes.DARK_OAK_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_OAK_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.DARK_PRISMARINE == null) {
            BlockTypes.DARK_PRISMARINE = AllayBlockType
                    .builder(BlockDarkPrismarineBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_PRISMARINE)
                    .build();
        }
        if (BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB == null) {
            BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_PRISMARINE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DARK_PRISMARINE_SLAB == null) {
            BlockTypes.DARK_PRISMARINE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_PRISMARINE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DARK_PRISMARINE_STAIRS == null) {
            BlockTypes.DARK_PRISMARINE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARK_PRISMARINE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.DARKOAK_STANDING_SIGN == null) {
            BlockTypes.DARKOAK_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARKOAK_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.DARKOAK_WALL_SIGN == null) {
            BlockTypes.DARKOAK_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.DARKOAK_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.DAYLIGHT_DETECTOR == null) {
            BlockTypes.DAYLIGHT_DETECTOR = AllayBlockType
                    .builder(BlockDaylightDetectorBehaviorImpl.class)
                    .vanillaBlock(BlockId.DAYLIGHT_DETECTOR)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.DAYLIGHT_DETECTOR_INVERTED == null) {
            BlockTypes.DAYLIGHT_DETECTOR_INVERTED = AllayBlockType
                    .builder(BlockDaylightDetectorBehaviorImpl.class)
                    .vanillaBlock(BlockId.DAYLIGHT_DETECTOR_INVERTED)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.DEAD_BRAIN_CORAL == null) {
            BlockTypes.DEAD_BRAIN_CORAL = AllayBlockType
                    .builder(BlockCoralBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_BRAIN_CORAL)
                    .build();
        }
        if (BlockTypes.DEAD_BRAIN_CORAL_BLOCK == null) {
            BlockTypes.DEAD_BRAIN_CORAL_BLOCK = AllayBlockType
                    .builder(BlockCoralBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_BLOCK)
                    .build();
        }
        if (BlockTypes.DEAD_BRAIN_CORAL_FAN == null) {
            BlockTypes.DEAD_BRAIN_CORAL_FAN = AllayBlockType
                    .builder(BlockCoralFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEAD_BRAIN_CORAL_WALL_FAN == null) {
            BlockTypes.DEAD_BRAIN_CORAL_WALL_FAN = AllayBlockType
                    .builder(BlockCoralWallFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_WALL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEAD_BUBBLE_CORAL == null) {
            BlockTypes.DEAD_BUBBLE_CORAL = AllayBlockType
                    .builder(BlockCoralBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL)
                    .build();
        }
        if (BlockTypes.DEAD_BUBBLE_CORAL_BLOCK == null) {
            BlockTypes.DEAD_BUBBLE_CORAL_BLOCK = AllayBlockType
                    .builder(BlockCoralBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_BLOCK)
                    .build();
        }
        if (BlockTypes.DEAD_BUBBLE_CORAL_FAN == null) {
            BlockTypes.DEAD_BUBBLE_CORAL_FAN = AllayBlockType
                    .builder(BlockCoralFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEAD_BUBBLE_CORAL_WALL_FAN == null) {
            BlockTypes.DEAD_BUBBLE_CORAL_WALL_FAN = AllayBlockType
                    .builder(BlockCoralWallFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_WALL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEAD_FIRE_CORAL == null) {
            BlockTypes.DEAD_FIRE_CORAL = AllayBlockType
                    .builder(BlockCoralBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_FIRE_CORAL)
                    .build();
        }
        if (BlockTypes.DEAD_FIRE_CORAL_BLOCK == null) {
            BlockTypes.DEAD_FIRE_CORAL_BLOCK = AllayBlockType
                    .builder(BlockCoralBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_FIRE_CORAL_BLOCK)
                    .build();
        }
        if (BlockTypes.DEAD_FIRE_CORAL_FAN == null) {
            BlockTypes.DEAD_FIRE_CORAL_FAN = AllayBlockType
                    .builder(BlockCoralFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_FIRE_CORAL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEAD_FIRE_CORAL_WALL_FAN == null) {
            BlockTypes.DEAD_FIRE_CORAL_WALL_FAN = AllayBlockType
                    .builder(BlockCoralWallFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_FIRE_CORAL_WALL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEAD_HORN_CORAL == null) {
            BlockTypes.DEAD_HORN_CORAL = AllayBlockType
                    .builder(BlockCoralBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_HORN_CORAL)
                    .build();
        }
        if (BlockTypes.DEAD_HORN_CORAL_BLOCK == null) {
            BlockTypes.DEAD_HORN_CORAL_BLOCK = AllayBlockType
                    .builder(BlockCoralBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_HORN_CORAL_BLOCK)
                    .build();
        }
        if (BlockTypes.DEAD_HORN_CORAL_FAN == null) {
            BlockTypes.DEAD_HORN_CORAL_FAN = AllayBlockType
                    .builder(BlockCoralFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_HORN_CORAL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEAD_HORN_CORAL_WALL_FAN == null) {
            BlockTypes.DEAD_HORN_CORAL_WALL_FAN = AllayBlockType
                    .builder(BlockCoralWallFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_HORN_CORAL_WALL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEAD_TUBE_CORAL == null) {
            BlockTypes.DEAD_TUBE_CORAL = AllayBlockType
                    .builder(BlockCoralBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_TUBE_CORAL)
                    .build();
        }
        if (BlockTypes.DEAD_TUBE_CORAL_BLOCK == null) {
            BlockTypes.DEAD_TUBE_CORAL_BLOCK = AllayBlockType
                    .builder(BlockCoralBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_TUBE_CORAL_BLOCK)
                    .build();
        }
        if (BlockTypes.DEAD_TUBE_CORAL_FAN == null) {
            BlockTypes.DEAD_TUBE_CORAL_FAN = AllayBlockType
                    .builder(BlockCoralFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_TUBE_CORAL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEAD_TUBE_CORAL_WALL_FAN == null) {
            BlockTypes.DEAD_TUBE_CORAL_WALL_FAN = AllayBlockType
                    .builder(BlockCoralWallFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEAD_TUBE_CORAL_WALL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEADBUSH == null) {
            BlockTypes.DEADBUSH = AllayBlockType
                    .builder(BlockDeadbushBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEADBUSH)
                    .build();
        }
        if (BlockTypes.DECORATED_POT == null) {
            BlockTypes.DECORATED_POT = AllayBlockType
                    .builder(BlockDecoratedPotBehaviorImpl.class)
                    .vanillaBlock(BlockId.DECORATED_POT)
                    .setProperties(BlockPropertyTypes.DIRECTION_4)
                    .build();
        }
        if (BlockTypes.DEEPSLATE == null) {
            BlockTypes.DEEPSLATE = AllayBlockType
                    .builder(BlockDeepslateBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_BRICK_SLAB == null) {
            BlockTypes.DEEPSLATE_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_BRICK_STAIRS == null) {
            BlockTypes.DEEPSLATE_BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_BRICK_WALL == null) {
            BlockTypes.DEEPSLATE_BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_BRICKS == null) {
            BlockTypes.DEEPSLATE_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_BRICKS)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_COAL_ORE == null) {
            BlockTypes.DEEPSLATE_COAL_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_COAL_ORE)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_COPPER_ORE == null) {
            BlockTypes.DEEPSLATE_COPPER_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_COPPER_ORE)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_DIAMOND_ORE == null) {
            BlockTypes.DEEPSLATE_DIAMOND_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_DIAMOND_ORE)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_EMERALD_ORE == null) {
            BlockTypes.DEEPSLATE_EMERALD_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_EMERALD_ORE)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_GOLD_ORE == null) {
            BlockTypes.DEEPSLATE_GOLD_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_GOLD_ORE)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_IRON_ORE == null) {
            BlockTypes.DEEPSLATE_IRON_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_IRON_ORE)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_LAPIS_ORE == null) {
            BlockTypes.DEEPSLATE_LAPIS_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_LAPIS_ORE)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_REDSTONE_ORE == null) {
            BlockTypes.DEEPSLATE_REDSTONE_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_REDSTONE_ORE)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB == null) {
            BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_TILE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_TILE_SLAB == null) {
            BlockTypes.DEEPSLATE_TILE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_TILE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_TILE_STAIRS == null) {
            BlockTypes.DEEPSLATE_TILE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_TILE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_TILE_WALL == null) {
            BlockTypes.DEEPSLATE_TILE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_TILE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.DEEPSLATE_TILES == null) {
            BlockTypes.DEEPSLATE_TILES = AllayBlockType
                    .builder(BlockDeepslateTilesBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEEPSLATE_TILES)
                    .build();
        }
        if (BlockTypes.DENY == null) {
            BlockTypes.DENY = AllayBlockType
                    .builder(BlockDenyBehaviorImpl.class)
                    .vanillaBlock(BlockId.DENY)
                    .build();
        }
        if (BlockTypes.DEPRECATED_ANVIL == null) {
            BlockTypes.DEPRECATED_ANVIL = AllayBlockType
                    .builder(BlockDeprecatedAnvilBehaviorImpl.class)
                    .vanillaBlock(BlockId.DEPRECATED_ANVIL)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.DEPRECATED_PURPUR_BLOCK_1 == null) {
            BlockTypes.DEPRECATED_PURPUR_BLOCK_1 = AllayBlockType
                    .builder(BlockDeprecatedPurpurBlock1BehaviorImpl.class)
                    .vanillaBlock(BlockId.DEPRECATED_PURPUR_BLOCK_1)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.DEPRECATED_PURPUR_BLOCK_2 == null) {
            BlockTypes.DEPRECATED_PURPUR_BLOCK_2 = AllayBlockType
                    .builder(BlockDeprecatedPurpurBlock2BehaviorImpl.class)
                    .vanillaBlock(BlockId.DEPRECATED_PURPUR_BLOCK_2)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.DETECTOR_RAIL == null) {
            BlockTypes.DETECTOR_RAIL = AllayBlockType
                    .builder(BlockDetectorRailBehaviorImpl.class)
                    .vanillaBlock(BlockId.DETECTOR_RAIL)
                    .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                    .build();
        }
        if (BlockTypes.DIAMOND_BLOCK == null) {
            BlockTypes.DIAMOND_BLOCK = AllayBlockType
                    .builder(BlockDiamondBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.DIAMOND_BLOCK)
                    .build();
        }
        if (BlockTypes.DIAMOND_ORE == null) {
            BlockTypes.DIAMOND_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.DIAMOND_ORE)
                    .build();
        }
        if (BlockTypes.DIORITE == null) {
            BlockTypes.DIORITE = AllayBlockType
                    .builder(BlockDioriteBehaviorImpl.class)
                    .vanillaBlock(BlockId.DIORITE)
                    .build();
        }
        if (BlockTypes.DIORITE_DOUBLE_SLAB == null) {
            BlockTypes.DIORITE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DIORITE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DIORITE_SLAB == null) {
            BlockTypes.DIORITE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DIORITE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DIORITE_STAIRS == null) {
            BlockTypes.DIORITE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.DIORITE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.DIORITE_WALL == null) {
            BlockTypes.DIORITE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.DIORITE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.DIRT == null) {
            BlockTypes.DIRT = AllayBlockType
                    .builder(BlockDirtBehaviorImpl.class)
                    .vanillaBlock(BlockId.DIRT)
                    .build();
        }
        if (BlockTypes.DIRT_WITH_ROOTS == null) {
            BlockTypes.DIRT_WITH_ROOTS = AllayBlockType
                    .builder(BlockDirtWithRootsBehaviorImpl.class)
                    .vanillaBlock(BlockId.DIRT_WITH_ROOTS)
                    .build();
        }
        if (BlockTypes.DISPENSER == null) {
            BlockTypes.DISPENSER = AllayBlockType
                    .builder(BlockDispenserBehaviorImpl.class)
                    .vanillaBlock(BlockId.DISPENSER)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                    .build();
        }
        if (BlockTypes.DOUBLE_CUT_COPPER_SLAB == null) {
            BlockTypes.DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockDoubleCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.DOUBLE_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.DRAGON_EGG == null) {
            BlockTypes.DRAGON_EGG = AllayBlockType
                    .builder(BlockDragonEggBehaviorImpl.class)
                    .vanillaBlock(BlockId.DRAGON_EGG)
                    .build();
        }
        if (BlockTypes.DRAGON_HEAD == null) {
            BlockTypes.DRAGON_HEAD = AllayBlockType
                    .builder(BlockHeadBehaviorImpl.class)
                    .vanillaBlock(BlockId.DRAGON_HEAD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.DRIED_GHAST == null) {
            BlockTypes.DRIED_GHAST = AllayBlockType
                    .builder(BlockDriedGhastBehaviorImpl.class)
                    .vanillaBlock(BlockId.DRIED_GHAST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REHYDRATION_LEVEL)
                    .build();
        }
        if (BlockTypes.DRIED_KELP_BLOCK == null) {
            BlockTypes.DRIED_KELP_BLOCK = AllayBlockType
                    .builder(BlockDriedKelpBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.DRIED_KELP_BLOCK)
                    .build();
        }
        if (BlockTypes.DRIPSTONE_BLOCK == null) {
            BlockTypes.DRIPSTONE_BLOCK = AllayBlockType
                    .builder(BlockDripstoneBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.DRIPSTONE_BLOCK)
                    .build();
        }
        if (BlockTypes.DROPPER == null) {
            BlockTypes.DROPPER = AllayBlockType
                    .builder(BlockDropperBehaviorImpl.class)
                    .vanillaBlock(BlockId.DROPPER)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                    .build();
        }
        if (BlockTypes.ELEMENT_0 == null) {
            BlockTypes.ELEMENT_0 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_0)
                    .build();
        }
        if (BlockTypes.ELEMENT_1 == null) {
            BlockTypes.ELEMENT_1 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_1)
                    .build();
        }
        if (BlockTypes.ELEMENT_10 == null) {
            BlockTypes.ELEMENT_10 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_10)
                    .build();
        }
        if (BlockTypes.ELEMENT_100 == null) {
            BlockTypes.ELEMENT_100 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_100)
                    .build();
        }
        if (BlockTypes.ELEMENT_101 == null) {
            BlockTypes.ELEMENT_101 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_101)
                    .build();
        }
        if (BlockTypes.ELEMENT_102 == null) {
            BlockTypes.ELEMENT_102 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_102)
                    .build();
        }
        if (BlockTypes.ELEMENT_103 == null) {
            BlockTypes.ELEMENT_103 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_103)
                    .build();
        }
        if (BlockTypes.ELEMENT_104 == null) {
            BlockTypes.ELEMENT_104 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_104)
                    .build();
        }
        if (BlockTypes.ELEMENT_105 == null) {
            BlockTypes.ELEMENT_105 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_105)
                    .build();
        }
        if (BlockTypes.ELEMENT_106 == null) {
            BlockTypes.ELEMENT_106 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_106)
                    .build();
        }
        if (BlockTypes.ELEMENT_107 == null) {
            BlockTypes.ELEMENT_107 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_107)
                    .build();
        }
        if (BlockTypes.ELEMENT_108 == null) {
            BlockTypes.ELEMENT_108 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_108)
                    .build();
        }
        if (BlockTypes.ELEMENT_109 == null) {
            BlockTypes.ELEMENT_109 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_109)
                    .build();
        }
        if (BlockTypes.ELEMENT_11 == null) {
            BlockTypes.ELEMENT_11 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_11)
                    .build();
        }
        if (BlockTypes.ELEMENT_110 == null) {
            BlockTypes.ELEMENT_110 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_110)
                    .build();
        }
        if (BlockTypes.ELEMENT_111 == null) {
            BlockTypes.ELEMENT_111 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_111)
                    .build();
        }
        if (BlockTypes.ELEMENT_112 == null) {
            BlockTypes.ELEMENT_112 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_112)
                    .build();
        }
        if (BlockTypes.ELEMENT_113 == null) {
            BlockTypes.ELEMENT_113 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_113)
                    .build();
        }
        if (BlockTypes.ELEMENT_114 == null) {
            BlockTypes.ELEMENT_114 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_114)
                    .build();
        }
        if (BlockTypes.ELEMENT_115 == null) {
            BlockTypes.ELEMENT_115 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_115)
                    .build();
        }
        if (BlockTypes.ELEMENT_116 == null) {
            BlockTypes.ELEMENT_116 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_116)
                    .build();
        }
        if (BlockTypes.ELEMENT_117 == null) {
            BlockTypes.ELEMENT_117 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_117)
                    .build();
        }
        if (BlockTypes.ELEMENT_118 == null) {
            BlockTypes.ELEMENT_118 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_118)
                    .build();
        }
        if (BlockTypes.ELEMENT_12 == null) {
            BlockTypes.ELEMENT_12 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_12)
                    .build();
        }
        if (BlockTypes.ELEMENT_13 == null) {
            BlockTypes.ELEMENT_13 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_13)
                    .build();
        }
        if (BlockTypes.ELEMENT_14 == null) {
            BlockTypes.ELEMENT_14 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_14)
                    .build();
        }
        if (BlockTypes.ELEMENT_15 == null) {
            BlockTypes.ELEMENT_15 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_15)
                    .build();
        }
        if (BlockTypes.ELEMENT_16 == null) {
            BlockTypes.ELEMENT_16 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_16)
                    .build();
        }
        if (BlockTypes.ELEMENT_17 == null) {
            BlockTypes.ELEMENT_17 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_17)
                    .build();
        }
        if (BlockTypes.ELEMENT_18 == null) {
            BlockTypes.ELEMENT_18 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_18)
                    .build();
        }
        if (BlockTypes.ELEMENT_19 == null) {
            BlockTypes.ELEMENT_19 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_19)
                    .build();
        }
        if (BlockTypes.ELEMENT_2 == null) {
            BlockTypes.ELEMENT_2 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_2)
                    .build();
        }
        if (BlockTypes.ELEMENT_20 == null) {
            BlockTypes.ELEMENT_20 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_20)
                    .build();
        }
        if (BlockTypes.ELEMENT_21 == null) {
            BlockTypes.ELEMENT_21 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_21)
                    .build();
        }
        if (BlockTypes.ELEMENT_22 == null) {
            BlockTypes.ELEMENT_22 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_22)
                    .build();
        }
        if (BlockTypes.ELEMENT_23 == null) {
            BlockTypes.ELEMENT_23 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_23)
                    .build();
        }
        if (BlockTypes.ELEMENT_24 == null) {
            BlockTypes.ELEMENT_24 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_24)
                    .build();
        }
        if (BlockTypes.ELEMENT_25 == null) {
            BlockTypes.ELEMENT_25 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_25)
                    .build();
        }
        if (BlockTypes.ELEMENT_26 == null) {
            BlockTypes.ELEMENT_26 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_26)
                    .build();
        }
        if (BlockTypes.ELEMENT_27 == null) {
            BlockTypes.ELEMENT_27 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_27)
                    .build();
        }
        if (BlockTypes.ELEMENT_28 == null) {
            BlockTypes.ELEMENT_28 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_28)
                    .build();
        }
        if (BlockTypes.ELEMENT_29 == null) {
            BlockTypes.ELEMENT_29 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_29)
                    .build();
        }
        if (BlockTypes.ELEMENT_3 == null) {
            BlockTypes.ELEMENT_3 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_3)
                    .build();
        }
        if (BlockTypes.ELEMENT_30 == null) {
            BlockTypes.ELEMENT_30 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_30)
                    .build();
        }
        if (BlockTypes.ELEMENT_31 == null) {
            BlockTypes.ELEMENT_31 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_31)
                    .build();
        }
        if (BlockTypes.ELEMENT_32 == null) {
            BlockTypes.ELEMENT_32 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_32)
                    .build();
        }
        if (BlockTypes.ELEMENT_33 == null) {
            BlockTypes.ELEMENT_33 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_33)
                    .build();
        }
        if (BlockTypes.ELEMENT_34 == null) {
            BlockTypes.ELEMENT_34 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_34)
                    .build();
        }
        if (BlockTypes.ELEMENT_35 == null) {
            BlockTypes.ELEMENT_35 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_35)
                    .build();
        }
        if (BlockTypes.ELEMENT_36 == null) {
            BlockTypes.ELEMENT_36 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_36)
                    .build();
        }
        if (BlockTypes.ELEMENT_37 == null) {
            BlockTypes.ELEMENT_37 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_37)
                    .build();
        }
        if (BlockTypes.ELEMENT_38 == null) {
            BlockTypes.ELEMENT_38 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_38)
                    .build();
        }
        if (BlockTypes.ELEMENT_39 == null) {
            BlockTypes.ELEMENT_39 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_39)
                    .build();
        }
        if (BlockTypes.ELEMENT_4 == null) {
            BlockTypes.ELEMENT_4 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_4)
                    .build();
        }
        if (BlockTypes.ELEMENT_40 == null) {
            BlockTypes.ELEMENT_40 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_40)
                    .build();
        }
        if (BlockTypes.ELEMENT_41 == null) {
            BlockTypes.ELEMENT_41 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_41)
                    .build();
        }
        if (BlockTypes.ELEMENT_42 == null) {
            BlockTypes.ELEMENT_42 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_42)
                    .build();
        }
        if (BlockTypes.ELEMENT_43 == null) {
            BlockTypes.ELEMENT_43 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_43)
                    .build();
        }
        if (BlockTypes.ELEMENT_44 == null) {
            BlockTypes.ELEMENT_44 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_44)
                    .build();
        }
        if (BlockTypes.ELEMENT_45 == null) {
            BlockTypes.ELEMENT_45 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_45)
                    .build();
        }
        if (BlockTypes.ELEMENT_46 == null) {
            BlockTypes.ELEMENT_46 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_46)
                    .build();
        }
        if (BlockTypes.ELEMENT_47 == null) {
            BlockTypes.ELEMENT_47 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_47)
                    .build();
        }
        if (BlockTypes.ELEMENT_48 == null) {
            BlockTypes.ELEMENT_48 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_48)
                    .build();
        }
        if (BlockTypes.ELEMENT_49 == null) {
            BlockTypes.ELEMENT_49 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_49)
                    .build();
        }
        if (BlockTypes.ELEMENT_5 == null) {
            BlockTypes.ELEMENT_5 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_5)
                    .build();
        }
        if (BlockTypes.ELEMENT_50 == null) {
            BlockTypes.ELEMENT_50 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_50)
                    .build();
        }
        if (BlockTypes.ELEMENT_51 == null) {
            BlockTypes.ELEMENT_51 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_51)
                    .build();
        }
        if (BlockTypes.ELEMENT_52 == null) {
            BlockTypes.ELEMENT_52 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_52)
                    .build();
        }
        if (BlockTypes.ELEMENT_53 == null) {
            BlockTypes.ELEMENT_53 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_53)
                    .build();
        }
        if (BlockTypes.ELEMENT_54 == null) {
            BlockTypes.ELEMENT_54 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_54)
                    .build();
        }
        if (BlockTypes.ELEMENT_55 == null) {
            BlockTypes.ELEMENT_55 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_55)
                    .build();
        }
        if (BlockTypes.ELEMENT_56 == null) {
            BlockTypes.ELEMENT_56 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_56)
                    .build();
        }
        if (BlockTypes.ELEMENT_57 == null) {
            BlockTypes.ELEMENT_57 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_57)
                    .build();
        }
        if (BlockTypes.ELEMENT_58 == null) {
            BlockTypes.ELEMENT_58 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_58)
                    .build();
        }
        if (BlockTypes.ELEMENT_59 == null) {
            BlockTypes.ELEMENT_59 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_59)
                    .build();
        }
        if (BlockTypes.ELEMENT_6 == null) {
            BlockTypes.ELEMENT_6 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_6)
                    .build();
        }
        if (BlockTypes.ELEMENT_60 == null) {
            BlockTypes.ELEMENT_60 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_60)
                    .build();
        }
        if (BlockTypes.ELEMENT_61 == null) {
            BlockTypes.ELEMENT_61 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_61)
                    .build();
        }
        if (BlockTypes.ELEMENT_62 == null) {
            BlockTypes.ELEMENT_62 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_62)
                    .build();
        }
        if (BlockTypes.ELEMENT_63 == null) {
            BlockTypes.ELEMENT_63 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_63)
                    .build();
        }
        if (BlockTypes.ELEMENT_64 == null) {
            BlockTypes.ELEMENT_64 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_64)
                    .build();
        }
        if (BlockTypes.ELEMENT_65 == null) {
            BlockTypes.ELEMENT_65 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_65)
                    .build();
        }
        if (BlockTypes.ELEMENT_66 == null) {
            BlockTypes.ELEMENT_66 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_66)
                    .build();
        }
        if (BlockTypes.ELEMENT_67 == null) {
            BlockTypes.ELEMENT_67 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_67)
                    .build();
        }
        if (BlockTypes.ELEMENT_68 == null) {
            BlockTypes.ELEMENT_68 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_68)
                    .build();
        }
        if (BlockTypes.ELEMENT_69 == null) {
            BlockTypes.ELEMENT_69 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_69)
                    .build();
        }
        if (BlockTypes.ELEMENT_7 == null) {
            BlockTypes.ELEMENT_7 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_7)
                    .build();
        }
        if (BlockTypes.ELEMENT_70 == null) {
            BlockTypes.ELEMENT_70 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_70)
                    .build();
        }
        if (BlockTypes.ELEMENT_71 == null) {
            BlockTypes.ELEMENT_71 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_71)
                    .build();
        }
        if (BlockTypes.ELEMENT_72 == null) {
            BlockTypes.ELEMENT_72 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_72)
                    .build();
        }
        if (BlockTypes.ELEMENT_73 == null) {
            BlockTypes.ELEMENT_73 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_73)
                    .build();
        }
        if (BlockTypes.ELEMENT_74 == null) {
            BlockTypes.ELEMENT_74 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_74)
                    .build();
        }
        if (BlockTypes.ELEMENT_75 == null) {
            BlockTypes.ELEMENT_75 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_75)
                    .build();
        }
        if (BlockTypes.ELEMENT_76 == null) {
            BlockTypes.ELEMENT_76 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_76)
                    .build();
        }
        if (BlockTypes.ELEMENT_77 == null) {
            BlockTypes.ELEMENT_77 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_77)
                    .build();
        }
        if (BlockTypes.ELEMENT_78 == null) {
            BlockTypes.ELEMENT_78 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_78)
                    .build();
        }
        if (BlockTypes.ELEMENT_79 == null) {
            BlockTypes.ELEMENT_79 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_79)
                    .build();
        }
        if (BlockTypes.ELEMENT_8 == null) {
            BlockTypes.ELEMENT_8 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_8)
                    .build();
        }
        if (BlockTypes.ELEMENT_80 == null) {
            BlockTypes.ELEMENT_80 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_80)
                    .build();
        }
        if (BlockTypes.ELEMENT_81 == null) {
            BlockTypes.ELEMENT_81 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_81)
                    .build();
        }
        if (BlockTypes.ELEMENT_82 == null) {
            BlockTypes.ELEMENT_82 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_82)
                    .build();
        }
        if (BlockTypes.ELEMENT_83 == null) {
            BlockTypes.ELEMENT_83 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_83)
                    .build();
        }
        if (BlockTypes.ELEMENT_84 == null) {
            BlockTypes.ELEMENT_84 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_84)
                    .build();
        }
        if (BlockTypes.ELEMENT_85 == null) {
            BlockTypes.ELEMENT_85 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_85)
                    .build();
        }
        if (BlockTypes.ELEMENT_86 == null) {
            BlockTypes.ELEMENT_86 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_86)
                    .build();
        }
        if (BlockTypes.ELEMENT_87 == null) {
            BlockTypes.ELEMENT_87 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_87)
                    .build();
        }
        if (BlockTypes.ELEMENT_88 == null) {
            BlockTypes.ELEMENT_88 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_88)
                    .build();
        }
        if (BlockTypes.ELEMENT_89 == null) {
            BlockTypes.ELEMENT_89 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_89)
                    .build();
        }
        if (BlockTypes.ELEMENT_9 == null) {
            BlockTypes.ELEMENT_9 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_9)
                    .build();
        }
        if (BlockTypes.ELEMENT_90 == null) {
            BlockTypes.ELEMENT_90 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_90)
                    .build();
        }
        if (BlockTypes.ELEMENT_91 == null) {
            BlockTypes.ELEMENT_91 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_91)
                    .build();
        }
        if (BlockTypes.ELEMENT_92 == null) {
            BlockTypes.ELEMENT_92 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_92)
                    .build();
        }
        if (BlockTypes.ELEMENT_93 == null) {
            BlockTypes.ELEMENT_93 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_93)
                    .build();
        }
        if (BlockTypes.ELEMENT_94 == null) {
            BlockTypes.ELEMENT_94 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_94)
                    .build();
        }
        if (BlockTypes.ELEMENT_95 == null) {
            BlockTypes.ELEMENT_95 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_95)
                    .build();
        }
        if (BlockTypes.ELEMENT_96 == null) {
            BlockTypes.ELEMENT_96 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_96)
                    .build();
        }
        if (BlockTypes.ELEMENT_97 == null) {
            BlockTypes.ELEMENT_97 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_97)
                    .build();
        }
        if (BlockTypes.ELEMENT_98 == null) {
            BlockTypes.ELEMENT_98 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_98)
                    .build();
        }
        if (BlockTypes.ELEMENT_99 == null) {
            BlockTypes.ELEMENT_99 = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_99)
                    .build();
        }
        if (BlockTypes.ELEMENT_CONSTRUCTOR == null) {
            BlockTypes.ELEMENT_CONSTRUCTOR = AllayBlockType
                    .builder(BlockElementBehaviorImpl.class)
                    .vanillaBlock(BlockId.ELEMENT_CONSTRUCTOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4)
                    .build();
        }
        if (BlockTypes.EMERALD_BLOCK == null) {
            BlockTypes.EMERALD_BLOCK = AllayBlockType
                    .builder(BlockEmeraldBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.EMERALD_BLOCK)
                    .build();
        }
        if (BlockTypes.EMERALD_ORE == null) {
            BlockTypes.EMERALD_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.EMERALD_ORE)
                    .build();
        }
        if (BlockTypes.ENCHANTING_TABLE == null) {
            BlockTypes.ENCHANTING_TABLE = AllayBlockType
                    .builder(BlockEnchantingTableBehaviorImpl.class)
                    .vanillaBlock(BlockId.ENCHANTING_TABLE)
                    .build();
        }
        if (BlockTypes.END_BRICK_STAIRS == null) {
            BlockTypes.END_BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.END_BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.END_BRICKS == null) {
            BlockTypes.END_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.END_BRICKS)
                    .build();
        }
        if (BlockTypes.END_GATEWAY == null) {
            BlockTypes.END_GATEWAY = AllayBlockType
                    .builder(BlockEndGatewayBehaviorImpl.class)
                    .vanillaBlock(BlockId.END_GATEWAY)
                    .build();
        }
        if (BlockTypes.END_PORTAL == null) {
            BlockTypes.END_PORTAL = AllayBlockType
                    .builder(BlockEndPortalBehaviorImpl.class)
                    .vanillaBlock(BlockId.END_PORTAL)
                    .build();
        }
        if (BlockTypes.END_PORTAL_FRAME == null) {
            BlockTypes.END_PORTAL_FRAME = AllayBlockType
                    .builder(BlockEndPortalFrameBehaviorImpl.class)
                    .vanillaBlock(BlockId.END_PORTAL_FRAME)
                    .setProperties(BlockPropertyTypes.END_PORTAL_EYE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.END_ROD == null) {
            BlockTypes.END_ROD = AllayBlockType
                    .builder(BlockEndRodBehaviorImpl.class)
                    .vanillaBlock(BlockId.END_ROD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.END_STONE == null) {
            BlockTypes.END_STONE = AllayBlockType
                    .builder(BlockEndStoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.END_STONE)
                    .build();
        }
        if (BlockTypes.END_STONE_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.END_STONE_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.END_STONE_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.END_STONE_BRICK_SLAB == null) {
            BlockTypes.END_STONE_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.END_STONE_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.END_STONE_BRICK_WALL == null) {
            BlockTypes.END_STONE_BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.END_STONE_BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.ENDER_CHEST == null) {
            BlockTypes.ENDER_CHEST = AllayBlockType
                    .builder(BlockEnderChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.ENDER_CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.EXPOSED_CHISELED_COPPER == null) {
            BlockTypes.EXPOSED_CHISELED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_CHISELED_COPPER)
                    .build();
        }
        if (BlockTypes.EXPOSED_COPPER == null) {
            BlockTypes.EXPOSED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_COPPER)
                    .build();
        }
        if (BlockTypes.EXPOSED_COPPER_BARS == null) {
            BlockTypes.EXPOSED_COPPER_BARS = AllayBlockType
                    .builder(BlockCopperBarsBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_COPPER_BARS)
                    .build();
        }
        if (BlockTypes.EXPOSED_COPPER_BULB == null) {
            BlockTypes.EXPOSED_COPPER_BULB = AllayBlockType
                    .builder(BlockCopperBulbBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_COPPER_BULB)
                    .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.EXPOSED_COPPER_CHAIN == null) {
            BlockTypes.EXPOSED_COPPER_CHAIN = AllayBlockType
                    .builder(BlockCopperChainBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_COPPER_CHAIN)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.EXPOSED_COPPER_CHEST == null) {
            BlockTypes.EXPOSED_COPPER_CHEST = AllayBlockType
                    .builder(BlockExposedCopperChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_COPPER_CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.EXPOSED_COPPER_DOOR == null) {
            BlockTypes.EXPOSED_COPPER_DOOR = AllayBlockType
                    .builder(BlockCopperDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_COPPER_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.EXPOSED_COPPER_GOLEM_STATUE == null) {
            BlockTypes.EXPOSED_COPPER_GOLEM_STATUE = AllayBlockType
                    .builder(BlockCopperGolemStatueBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_COPPER_GOLEM_STATUE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.EXPOSED_COPPER_GRATE == null) {
            BlockTypes.EXPOSED_COPPER_GRATE = AllayBlockType
                    .builder(BlockCopperGrateBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_COPPER_GRATE)
                    .build();
        }
        if (BlockTypes.EXPOSED_COPPER_LANTERN == null) {
            BlockTypes.EXPOSED_COPPER_LANTERN = AllayBlockType
                    .builder(BlockCopperLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_COPPER_LANTERN)
                    .setProperties(BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.EXPOSED_COPPER_TRAPDOOR == null) {
            BlockTypes.EXPOSED_COPPER_TRAPDOOR = AllayBlockType
                    .builder(BlockCopperTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_COPPER_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.EXPOSED_CUT_COPPER == null) {
            BlockTypes.EXPOSED_CUT_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_CUT_COPPER)
                    .build();
        }
        if (BlockTypes.EXPOSED_CUT_COPPER_SLAB == null) {
            BlockTypes.EXPOSED_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.EXPOSED_CUT_COPPER_STAIRS == null) {
            BlockTypes.EXPOSED_CUT_COPPER_STAIRS = AllayBlockType
                    .builder(BlockCopperStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_CUT_COPPER_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB == null) {
            BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockDoubleCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.EXPOSED_LIGHTNING_ROD == null) {
            BlockTypes.EXPOSED_LIGHTNING_ROD = AllayBlockType
                    .builder(BlockLightningRodBehaviorImpl.class)
                    .vanillaBlock(BlockId.EXPOSED_LIGHTNING_ROD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.FARMLAND == null) {
            BlockTypes.FARMLAND = AllayBlockType
                    .builder(BlockFarmlandBehaviorImpl.class)
                    .vanillaBlock(BlockId.FARMLAND)
                    .setProperties(BlockPropertyTypes.MOISTURIZED_AMOUNT)
                    .build();
        }
        if (BlockTypes.FENCE_GATE == null) {
            BlockTypes.FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.FERN == null) {
            BlockTypes.FERN = AllayBlockType
                    .builder(BlockFernBehaviorImpl.class)
                    .vanillaBlock(BlockId.FERN)
                    .build();
        }
        if (BlockTypes.FIRE == null) {
            BlockTypes.FIRE = AllayBlockType
                    .builder(BlockFireBehaviorImpl.class)
                    .vanillaBlock(BlockId.FIRE)
                    .setProperties(BlockPropertyTypes.AGE_16)
                    .build();
        }
        if (BlockTypes.FIRE_CORAL == null) {
            BlockTypes.FIRE_CORAL = AllayBlockType
                    .builder(BlockCoralBehaviorImpl.class)
                    .vanillaBlock(BlockId.FIRE_CORAL)
                    .build();
        }
        if (BlockTypes.FIRE_CORAL_BLOCK == null) {
            BlockTypes.FIRE_CORAL_BLOCK = AllayBlockType
                    .builder(BlockCoralBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.FIRE_CORAL_BLOCK)
                    .build();
        }
        if (BlockTypes.FIRE_CORAL_FAN == null) {
            BlockTypes.FIRE_CORAL_FAN = AllayBlockType
                    .builder(BlockCoralFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.FIRE_CORAL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                    .build();
        }
        if (BlockTypes.FIRE_CORAL_WALL_FAN == null) {
            BlockTypes.FIRE_CORAL_WALL_FAN = AllayBlockType
                    .builder(BlockCoralWallFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.FIRE_CORAL_WALL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.FIREFLY_BUSH == null) {
            BlockTypes.FIREFLY_BUSH = AllayBlockType
                    .builder(BlockFireflyBushBehaviorImpl.class)
                    .vanillaBlock(BlockId.FIREFLY_BUSH)
                    .build();
        }
        if (BlockTypes.FLETCHING_TABLE == null) {
            BlockTypes.FLETCHING_TABLE = AllayBlockType
                    .builder(BlockFletchingTableBehaviorImpl.class)
                    .vanillaBlock(BlockId.FLETCHING_TABLE)
                    .build();
        }
        if (BlockTypes.FLOWER_POT == null) {
            BlockTypes.FLOWER_POT = AllayBlockType
                    .builder(BlockFlowerPotBehaviorImpl.class)
                    .vanillaBlock(BlockId.FLOWER_POT)
                    .setProperties(BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.FLOWERING_AZALEA == null) {
            BlockTypes.FLOWERING_AZALEA = AllayBlockType
                    .builder(BlockFloweringAzaleaBehaviorImpl.class)
                    .vanillaBlock(BlockId.FLOWERING_AZALEA)
                    .build();
        }
        if (BlockTypes.FLOWING_LAVA == null) {
            BlockTypes.FLOWING_LAVA = AllayBlockType
                    .builder(BlockLiquidBehaviorImpl.class)
                    .vanillaBlock(BlockId.FLOWING_LAVA)
                    .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                    .build();
        }
        if (BlockTypes.FLOWING_WATER == null) {
            BlockTypes.FLOWING_WATER = AllayBlockType
                    .builder(BlockLiquidBehaviorImpl.class)
                    .vanillaBlock(BlockId.FLOWING_WATER)
                    .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                    .build();
        }
        if (BlockTypes.FRAME == null) {
            BlockTypes.FRAME = AllayBlockType
                    .builder(BlockFrameBehaviorImpl.class)
                    .vanillaBlock(BlockId.FRAME)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ITEM_FRAME_MAP_BIT, BlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
                    .build();
        }
        if (BlockTypes.FROG_SPAWN == null) {
            BlockTypes.FROG_SPAWN = AllayBlockType
                    .builder(BlockFrogSpawnBehaviorImpl.class)
                    .vanillaBlock(BlockId.FROG_SPAWN)
                    .build();
        }
        if (BlockTypes.FROSTED_ICE == null) {
            BlockTypes.FROSTED_ICE = AllayBlockType
                    .builder(BlockIceBehaviorImpl.class)
                    .vanillaBlock(BlockId.FROSTED_ICE)
                    .setProperties(BlockPropertyTypes.AGE_4)
                    .build();
        }
        if (BlockTypes.FURNACE == null) {
            BlockTypes.FURNACE = AllayBlockType
                    .builder(BlockFurnaceBehaviorImpl.class)
                    .vanillaBlock(BlockId.FURNACE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.GILDED_BLACKSTONE == null) {
            BlockTypes.GILDED_BLACKSTONE = AllayBlockType
                    .builder(BlockGildedBlackstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.GILDED_BLACKSTONE)
                    .build();
        }
        if (BlockTypes.GLASS == null) {
            BlockTypes.GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.GLASS)
                    .build();
        }
        if (BlockTypes.GLASS_PANE == null) {
            BlockTypes.GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.GLASS_PANE)
                    .build();
        }
        if (BlockTypes.GLOW_FRAME == null) {
            BlockTypes.GLOW_FRAME = AllayBlockType
                    .builder(BlockGlowFrameBehaviorImpl.class)
                    .vanillaBlock(BlockId.GLOW_FRAME)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ITEM_FRAME_MAP_BIT, BlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
                    .build();
        }
        if (BlockTypes.GLOW_LICHEN == null) {
            BlockTypes.GLOW_LICHEN = AllayBlockType
                    .builder(BlockGlowLichenBehaviorImpl.class)
                    .vanillaBlock(BlockId.GLOW_LICHEN)
                    .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                    .build();
        }
        if (BlockTypes.GLOWINGOBSIDIAN == null) {
            BlockTypes.GLOWINGOBSIDIAN = AllayBlockType
                    .builder(BlockGlowingobsidianBehaviorImpl.class)
                    .vanillaBlock(BlockId.GLOWINGOBSIDIAN)
                    .build();
        }
        if (BlockTypes.GLOWSTONE == null) {
            BlockTypes.GLOWSTONE = AllayBlockType
                    .builder(BlockGlowstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.GLOWSTONE)
                    .build();
        }
        if (BlockTypes.GOLD_BLOCK == null) {
            BlockTypes.GOLD_BLOCK = AllayBlockType
                    .builder(BlockGoldBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.GOLD_BLOCK)
                    .build();
        }
        if (BlockTypes.GOLD_ORE == null) {
            BlockTypes.GOLD_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.GOLD_ORE)
                    .build();
        }
        if (BlockTypes.GOLDEN_RAIL == null) {
            BlockTypes.GOLDEN_RAIL = AllayBlockType
                    .builder(BlockGoldenRailBehaviorImpl.class)
                    .vanillaBlock(BlockId.GOLDEN_RAIL)
                    .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                    .build();
        }
        if (BlockTypes.GRANITE == null) {
            BlockTypes.GRANITE = AllayBlockType
                    .builder(BlockGraniteBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRANITE)
                    .build();
        }
        if (BlockTypes.GRANITE_DOUBLE_SLAB == null) {
            BlockTypes.GRANITE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRANITE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.GRANITE_SLAB == null) {
            BlockTypes.GRANITE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRANITE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.GRANITE_STAIRS == null) {
            BlockTypes.GRANITE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRANITE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.GRANITE_WALL == null) {
            BlockTypes.GRANITE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRANITE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.GRASS_BLOCK == null) {
            BlockTypes.GRASS_BLOCK = AllayBlockType
                    .builder(BlockGrassBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRASS_BLOCK)
                    .build();
        }
        if (BlockTypes.GRASS_PATH == null) {
            BlockTypes.GRASS_PATH = AllayBlockType
                    .builder(BlockGrassPathBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRASS_PATH)
                    .build();
        }
        if (BlockTypes.GRAVEL == null) {
            BlockTypes.GRAVEL = AllayBlockType
                    .builder(BlockGravelBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAVEL)
                    .build();
        }
        if (BlockTypes.GRAY_CANDLE == null) {
            BlockTypes.GRAY_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.GRAY_CANDLE_CAKE == null) {
            BlockTypes.GRAY_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.GRAY_CARPET == null) {
            BlockTypes.GRAY_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_CARPET)
                    .build();
        }
        if (BlockTypes.GRAY_CONCRETE == null) {
            BlockTypes.GRAY_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_CONCRETE)
                    .build();
        }
        if (BlockTypes.GRAY_CONCRETE_POWDER == null) {
            BlockTypes.GRAY_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.GRAY_GLAZED_TERRACOTTA == null) {
            BlockTypes.GRAY_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.GRAY_SHULKER_BOX == null) {
            BlockTypes.GRAY_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.GRAY_STAINED_GLASS == null) {
            BlockTypes.GRAY_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.GRAY_STAINED_GLASS_PANE == null) {
            BlockTypes.GRAY_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.GRAY_TERRACOTTA == null) {
            BlockTypes.GRAY_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.GRAY_WOOL == null) {
            BlockTypes.GRAY_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRAY_WOOL)
                    .build();
        }
        if (BlockTypes.GREEN_CANDLE == null) {
            BlockTypes.GREEN_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.GREEN_CANDLE_CAKE == null) {
            BlockTypes.GREEN_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.GREEN_CARPET == null) {
            BlockTypes.GREEN_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_CARPET)
                    .build();
        }
        if (BlockTypes.GREEN_CONCRETE == null) {
            BlockTypes.GREEN_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_CONCRETE)
                    .build();
        }
        if (BlockTypes.GREEN_CONCRETE_POWDER == null) {
            BlockTypes.GREEN_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.GREEN_GLAZED_TERRACOTTA == null) {
            BlockTypes.GREEN_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.GREEN_SHULKER_BOX == null) {
            BlockTypes.GREEN_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.GREEN_STAINED_GLASS == null) {
            BlockTypes.GREEN_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.GREEN_STAINED_GLASS_PANE == null) {
            BlockTypes.GREEN_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.GREEN_TERRACOTTA == null) {
            BlockTypes.GREEN_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.GREEN_WOOL == null) {
            BlockTypes.GREEN_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.GREEN_WOOL)
                    .build();
        }
        if (BlockTypes.GRINDSTONE == null) {
            BlockTypes.GRINDSTONE = AllayBlockType
                    .builder(BlockGrindstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.GRINDSTONE)
                    .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION_4)
                    .build();
        }
        if (BlockTypes.HANGING_ROOTS == null) {
            BlockTypes.HANGING_ROOTS = AllayBlockType
                    .builder(BlockHangingRootsBehaviorImpl.class)
                    .vanillaBlock(BlockId.HANGING_ROOTS)
                    .build();
        }
        if (BlockTypes.HARD_BLACK_STAINED_GLASS == null) {
            BlockTypes.HARD_BLACK_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_BLACK_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_BLACK_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_BLACK_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_BLACK_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_BLUE_STAINED_GLASS == null) {
            BlockTypes.HARD_BLUE_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_BLUE_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_BLUE_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_BLUE_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_BLUE_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_BROWN_STAINED_GLASS == null) {
            BlockTypes.HARD_BROWN_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_BROWN_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_BROWN_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_BROWN_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_BROWN_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_CYAN_STAINED_GLASS == null) {
            BlockTypes.HARD_CYAN_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_CYAN_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_CYAN_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_CYAN_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_CYAN_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_GLASS == null) {
            BlockTypes.HARD_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_GLASS_PANE == null) {
            BlockTypes.HARD_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_GRAY_STAINED_GLASS == null) {
            BlockTypes.HARD_GRAY_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_GRAY_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_GRAY_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_GRAY_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_GRAY_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_GREEN_STAINED_GLASS == null) {
            BlockTypes.HARD_GREEN_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_GREEN_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_GREEN_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_GREEN_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_GREEN_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS == null) {
            BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS == null) {
            BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_LIME_STAINED_GLASS == null) {
            BlockTypes.HARD_LIME_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_LIME_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_LIME_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_LIME_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_LIME_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_MAGENTA_STAINED_GLASS == null) {
            BlockTypes.HARD_MAGENTA_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_MAGENTA_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_MAGENTA_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_ORANGE_STAINED_GLASS == null) {
            BlockTypes.HARD_ORANGE_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_ORANGE_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_ORANGE_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_PINK_STAINED_GLASS == null) {
            BlockTypes.HARD_PINK_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_PINK_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_PINK_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_PINK_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_PINK_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_PURPLE_STAINED_GLASS == null) {
            BlockTypes.HARD_PURPLE_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_PURPLE_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_PURPLE_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_RED_STAINED_GLASS == null) {
            BlockTypes.HARD_RED_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_RED_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_RED_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_RED_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_RED_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_WHITE_STAINED_GLASS == null) {
            BlockTypes.HARD_WHITE_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_WHITE_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_WHITE_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_WHITE_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_WHITE_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARD_YELLOW_STAINED_GLASS == null) {
            BlockTypes.HARD_YELLOW_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_YELLOW_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE == null) {
            BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARD_YELLOW_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.HARDENED_CLAY == null) {
            BlockTypes.HARDENED_CLAY = AllayBlockType
                    .builder(BlockHardenedClayBehaviorImpl.class)
                    .vanillaBlock(BlockId.HARDENED_CLAY)
                    .build();
        }
        if (BlockTypes.HAY_BLOCK == null) {
            BlockTypes.HAY_BLOCK = AllayBlockType
                    .builder(BlockHayBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.HAY_BLOCK)
                    .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.HEAVY_CORE == null) {
            BlockTypes.HEAVY_CORE = AllayBlockType
                    .builder(BlockHeavyCoreBehaviorImpl.class)
                    .vanillaBlock(BlockId.HEAVY_CORE)
                    .build();
        }
        if (BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE == null) {
            BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.HEAVY_WEIGHTED_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.HONEY_BLOCK == null) {
            BlockTypes.HONEY_BLOCK = AllayBlockType
                    .builder(BlockHoneyBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.HONEY_BLOCK)
                    .build();
        }
        if (BlockTypes.HONEYCOMB_BLOCK == null) {
            BlockTypes.HONEYCOMB_BLOCK = AllayBlockType
                    .builder(BlockHoneycombBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.HONEYCOMB_BLOCK)
                    .build();
        }
        if (BlockTypes.HOPPER == null) {
            BlockTypes.HOPPER = AllayBlockType
                    .builder(BlockHopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.HOPPER)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TOGGLE_BIT)
                    .build();
        }
        if (BlockTypes.HORN_CORAL == null) {
            BlockTypes.HORN_CORAL = AllayBlockType
                    .builder(BlockCoralBehaviorImpl.class)
                    .vanillaBlock(BlockId.HORN_CORAL)
                    .build();
        }
        if (BlockTypes.HORN_CORAL_BLOCK == null) {
            BlockTypes.HORN_CORAL_BLOCK = AllayBlockType
                    .builder(BlockCoralBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.HORN_CORAL_BLOCK)
                    .build();
        }
        if (BlockTypes.HORN_CORAL_FAN == null) {
            BlockTypes.HORN_CORAL_FAN = AllayBlockType
                    .builder(BlockCoralFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.HORN_CORAL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                    .build();
        }
        if (BlockTypes.HORN_CORAL_WALL_FAN == null) {
            BlockTypes.HORN_CORAL_WALL_FAN = AllayBlockType
                    .builder(BlockCoralWallFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.HORN_CORAL_WALL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.ICE == null) {
            BlockTypes.ICE = AllayBlockType
                    .builder(BlockIceBehaviorImpl.class)
                    .vanillaBlock(BlockId.ICE)
                    .build();
        }
        if (BlockTypes.INFESTED_CHISELED_STONE_BRICKS == null) {
            BlockTypes.INFESTED_CHISELED_STONE_BRICKS = AllayBlockType
                    .builder(BlockInfestedBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.INFESTED_CHISELED_STONE_BRICKS)
                    .build();
        }
        if (BlockTypes.INFESTED_COBBLESTONE == null) {
            BlockTypes.INFESTED_COBBLESTONE = AllayBlockType
                    .builder(BlockInfestedBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.INFESTED_COBBLESTONE)
                    .build();
        }
        if (BlockTypes.INFESTED_CRACKED_STONE_BRICKS == null) {
            BlockTypes.INFESTED_CRACKED_STONE_BRICKS = AllayBlockType
                    .builder(BlockInfestedBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.INFESTED_CRACKED_STONE_BRICKS)
                    .build();
        }
        if (BlockTypes.INFESTED_DEEPSLATE == null) {
            BlockTypes.INFESTED_DEEPSLATE = AllayBlockType
                    .builder(BlockInfestedBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.INFESTED_DEEPSLATE)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.INFESTED_MOSSY_STONE_BRICKS == null) {
            BlockTypes.INFESTED_MOSSY_STONE_BRICKS = AllayBlockType
                    .builder(BlockInfestedBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.INFESTED_MOSSY_STONE_BRICKS)
                    .build();
        }
        if (BlockTypes.INFESTED_STONE == null) {
            BlockTypes.INFESTED_STONE = AllayBlockType
                    .builder(BlockInfestedBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.INFESTED_STONE)
                    .build();
        }
        if (BlockTypes.INFESTED_STONE_BRICKS == null) {
            BlockTypes.INFESTED_STONE_BRICKS = AllayBlockType
                    .builder(BlockInfestedBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.INFESTED_STONE_BRICKS)
                    .build();
        }
        if (BlockTypes.INFO_UPDATE == null) {
            BlockTypes.INFO_UPDATE = AllayBlockType
                    .builder(BlockInfoUpdateBehaviorImpl.class)
                    .vanillaBlock(BlockId.INFO_UPDATE)
                    .build();
        }
        if (BlockTypes.INFO_UPDATE2 == null) {
            BlockTypes.INFO_UPDATE2 = AllayBlockType
                    .builder(BlockInfoUpdate2BehaviorImpl.class)
                    .vanillaBlock(BlockId.INFO_UPDATE2)
                    .build();
        }
        if (BlockTypes.INVISIBLE_BEDROCK == null) {
            BlockTypes.INVISIBLE_BEDROCK = AllayBlockType
                    .builder(BlockInvisibleBedrockBehaviorImpl.class)
                    .vanillaBlock(BlockId.INVISIBLE_BEDROCK)
                    .build();
        }
        if (BlockTypes.IRON_BARS == null) {
            BlockTypes.IRON_BARS = AllayBlockType
                    .builder(BlockIronBarsBehaviorImpl.class)
                    .vanillaBlock(BlockId.IRON_BARS)
                    .build();
        }
        if (BlockTypes.IRON_BLOCK == null) {
            BlockTypes.IRON_BLOCK = AllayBlockType
                    .builder(BlockIronBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.IRON_BLOCK)
                    .build();
        }
        if (BlockTypes.IRON_CHAIN == null) {
            BlockTypes.IRON_CHAIN = AllayBlockType
                    .builder(BlockIronChainBehaviorImpl.class)
                    .vanillaBlock(BlockId.IRON_CHAIN)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.IRON_DOOR == null) {
            BlockTypes.IRON_DOOR = AllayBlockType
                    .builder(BlockIronDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.IRON_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.IRON_ORE == null) {
            BlockTypes.IRON_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.IRON_ORE)
                    .build();
        }
        if (BlockTypes.IRON_TRAPDOOR == null) {
            BlockTypes.IRON_TRAPDOOR = AllayBlockType
                    .builder(BlockIronTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.IRON_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.JIGSAW == null) {
            BlockTypes.JIGSAW = AllayBlockType
                    .builder(BlockJigsawBehaviorImpl.class)
                    .vanillaBlock(BlockId.JIGSAW)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ROTATION)
                    .build();
        }
        if (BlockTypes.JUKEBOX == null) {
            BlockTypes.JUKEBOX = AllayBlockType
                    .builder(BlockJukeboxBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUKEBOX)
                    .build();
        }
        if (BlockTypes.JUNGLE_BUTTON == null) {
            BlockTypes.JUNGLE_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.JUNGLE_DOOR == null) {
            BlockTypes.JUNGLE_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.JUNGLE_DOUBLE_SLAB == null) {
            BlockTypes.JUNGLE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.JUNGLE_FENCE == null) {
            BlockTypes.JUNGLE_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_FENCE)
                    .build();
        }
        if (BlockTypes.JUNGLE_FENCE_GATE == null) {
            BlockTypes.JUNGLE_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.JUNGLE_HANGING_SIGN == null) {
            BlockTypes.JUNGLE_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.JUNGLE_LEAVES == null) {
            BlockTypes.JUNGLE_LEAVES = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_LEAVES)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.JUNGLE_LOG == null) {
            BlockTypes.JUNGLE_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.JUNGLE_PLANKS == null) {
            BlockTypes.JUNGLE_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_PLANKS)
                    .build();
        }
        if (BlockTypes.JUNGLE_PRESSURE_PLATE == null) {
            BlockTypes.JUNGLE_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.JUNGLE_SAPLING == null) {
            BlockTypes.JUNGLE_SAPLING = AllayBlockType
                    .builder(BlockSaplingBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_SAPLING)
                    .setProperties(BlockPropertyTypes.AGE_BIT)
                    .build();
        }
        if (BlockTypes.JUNGLE_SHELF == null) {
            BlockTypes.JUNGLE_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.JUNGLE_SLAB == null) {
            BlockTypes.JUNGLE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.JUNGLE_STAIRS == null) {
            BlockTypes.JUNGLE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.JUNGLE_STANDING_SIGN == null) {
            BlockTypes.JUNGLE_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.JUNGLE_TRAPDOOR == null) {
            BlockTypes.JUNGLE_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.JUNGLE_WALL_SIGN == null) {
            BlockTypes.JUNGLE_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.JUNGLE_WOOD == null) {
            BlockTypes.JUNGLE_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.JUNGLE_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.KELP == null) {
            BlockTypes.KELP = AllayBlockType
                    .builder(BlockKelpBehaviorImpl.class)
                    .vanillaBlock(BlockId.KELP)
                    .setProperties(BlockPropertyTypes.KELP_AGE)
                    .build();
        }
        if (BlockTypes.LAB_TABLE == null) {
            BlockTypes.LAB_TABLE = AllayBlockType
                    .builder(BlockLabTableBehaviorImpl.class)
                    .vanillaBlock(BlockId.LAB_TABLE)
                    .setProperties(BlockPropertyTypes.DIRECTION_4)
                    .build();
        }
        if (BlockTypes.LADDER == null) {
            BlockTypes.LADDER = AllayBlockType
                    .builder(BlockLadderBehaviorImpl.class)
                    .vanillaBlock(BlockId.LADDER)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.LANTERN == null) {
            BlockTypes.LANTERN = AllayBlockType
                    .builder(BlockLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.LANTERN)
                    .setProperties(BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.LAPIS_BLOCK == null) {
            BlockTypes.LAPIS_BLOCK = AllayBlockType
                    .builder(BlockLapisBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LAPIS_BLOCK)
                    .build();
        }
        if (BlockTypes.LAPIS_ORE == null) {
            BlockTypes.LAPIS_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.LAPIS_ORE)
                    .build();
        }
        if (BlockTypes.LARGE_AMETHYST_BUD == null) {
            BlockTypes.LARGE_AMETHYST_BUD = AllayBlockType
                    .builder(BlockLargeAmethystBudBehaviorImpl.class)
                    .vanillaBlock(BlockId.LARGE_AMETHYST_BUD)
                    .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                    .build();
        }
        if (BlockTypes.LARGE_FERN == null) {
            BlockTypes.LARGE_FERN = AllayBlockType
                    .builder(BlockLargeFernBehaviorImpl.class)
                    .vanillaBlock(BlockId.LARGE_FERN)
                    .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.LAVA == null) {
            BlockTypes.LAVA = AllayBlockType
                    .builder(BlockLiquidBehaviorImpl.class)
                    .vanillaBlock(BlockId.LAVA)
                    .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                    .build();
        }
        if (BlockTypes.LEAF_LITTER == null) {
            BlockTypes.LEAF_LITTER = AllayBlockType
                    .builder(BlockPlantPileImpl.class)
                    .vanillaBlock(BlockId.LEAF_LITTER)
                    .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.LECTERN == null) {
            BlockTypes.LECTERN = AllayBlockType
                    .builder(BlockLecternBehaviorImpl.class)
                    .vanillaBlock(BlockId.LECTERN)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.LEVER == null) {
            BlockTypes.LEVER = AllayBlockType
                    .builder(BlockLeverBehaviorImpl.class)
                    .vanillaBlock(BlockId.LEVER)
                    .setProperties(BlockPropertyTypes.LEVER_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_0 == null) {
            BlockTypes.LIGHT_BLOCK_0 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_0)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_1 == null) {
            BlockTypes.LIGHT_BLOCK_1 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_1)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_10 == null) {
            BlockTypes.LIGHT_BLOCK_10 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_10)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_11 == null) {
            BlockTypes.LIGHT_BLOCK_11 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_11)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_12 == null) {
            BlockTypes.LIGHT_BLOCK_12 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_12)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_13 == null) {
            BlockTypes.LIGHT_BLOCK_13 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_13)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_14 == null) {
            BlockTypes.LIGHT_BLOCK_14 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_14)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_15 == null) {
            BlockTypes.LIGHT_BLOCK_15 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_15)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_2 == null) {
            BlockTypes.LIGHT_BLOCK_2 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_2)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_3 == null) {
            BlockTypes.LIGHT_BLOCK_3 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_3)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_4 == null) {
            BlockTypes.LIGHT_BLOCK_4 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_4)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_5 == null) {
            BlockTypes.LIGHT_BLOCK_5 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_5)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_6 == null) {
            BlockTypes.LIGHT_BLOCK_6 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_6)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_7 == null) {
            BlockTypes.LIGHT_BLOCK_7 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_7)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_8 == null) {
            BlockTypes.LIGHT_BLOCK_8 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_8)
                    .build();
        }
        if (BlockTypes.LIGHT_BLOCK_9 == null) {
            BlockTypes.LIGHT_BLOCK_9 = AllayBlockType
                    .builder(BlockLightBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLOCK_9)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_CANDLE == null) {
            BlockTypes.LIGHT_BLUE_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_CANDLE_CAKE == null) {
            BlockTypes.LIGHT_BLUE_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_CARPET == null) {
            BlockTypes.LIGHT_BLUE_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_CARPET)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_CONCRETE == null) {
            BlockTypes.LIGHT_BLUE_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_CONCRETE)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_CONCRETE_POWDER == null) {
            BlockTypes.LIGHT_BLUE_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA == null) {
            BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_SHULKER_BOX == null) {
            BlockTypes.LIGHT_BLUE_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_STAINED_GLASS == null) {
            BlockTypes.LIGHT_BLUE_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE == null) {
            BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_TERRACOTTA == null) {
            BlockTypes.LIGHT_BLUE_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.LIGHT_BLUE_WOOL == null) {
            BlockTypes.LIGHT_BLUE_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_BLUE_WOOL)
                    .build();
        }
        if (BlockTypes.LIGHT_GRAY_CANDLE == null) {
            BlockTypes.LIGHT_GRAY_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_GRAY_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.LIGHT_GRAY_CANDLE_CAKE == null) {
            BlockTypes.LIGHT_GRAY_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_GRAY_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.LIGHT_GRAY_CARPET == null) {
            BlockTypes.LIGHT_GRAY_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_GRAY_CARPET)
                    .build();
        }
        if (BlockTypes.LIGHT_GRAY_CONCRETE == null) {
            BlockTypes.LIGHT_GRAY_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_GRAY_CONCRETE)
                    .build();
        }
        if (BlockTypes.LIGHT_GRAY_CONCRETE_POWDER == null) {
            BlockTypes.LIGHT_GRAY_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_GRAY_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.LIGHT_GRAY_SHULKER_BOX == null) {
            BlockTypes.LIGHT_GRAY_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_GRAY_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.LIGHT_GRAY_STAINED_GLASS == null) {
            BlockTypes.LIGHT_GRAY_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_GRAY_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE == null) {
            BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_GRAY_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.LIGHT_GRAY_TERRACOTTA == null) {
            BlockTypes.LIGHT_GRAY_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_GRAY_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.LIGHT_GRAY_WOOL == null) {
            BlockTypes.LIGHT_GRAY_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_GRAY_WOOL)
                    .build();
        }
        if (BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE == null) {
            BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHT_WEIGHTED_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.LIGHTNING_ROD == null) {
            BlockTypes.LIGHTNING_ROD = AllayBlockType
                    .builder(BlockLightningRodBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIGHTNING_ROD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.LILAC == null) {
            BlockTypes.LILAC = AllayBlockType
                    .builder(BlockBigFlowerImpl.class)
                    .vanillaBlock(BlockId.LILAC)
                    .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.LILY_OF_THE_VALLEY == null) {
            BlockTypes.LILY_OF_THE_VALLEY = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.LILY_OF_THE_VALLEY)
                    .build();
        }
        if (BlockTypes.LIME_CANDLE == null) {
            BlockTypes.LIME_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.LIME_CANDLE_CAKE == null) {
            BlockTypes.LIME_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.LIME_CARPET == null) {
            BlockTypes.LIME_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_CARPET)
                    .build();
        }
        if (BlockTypes.LIME_CONCRETE == null) {
            BlockTypes.LIME_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_CONCRETE)
                    .build();
        }
        if (BlockTypes.LIME_CONCRETE_POWDER == null) {
            BlockTypes.LIME_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.LIME_GLAZED_TERRACOTTA == null) {
            BlockTypes.LIME_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.LIME_SHULKER_BOX == null) {
            BlockTypes.LIME_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.LIME_STAINED_GLASS == null) {
            BlockTypes.LIME_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.LIME_STAINED_GLASS_PANE == null) {
            BlockTypes.LIME_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.LIME_TERRACOTTA == null) {
            BlockTypes.LIME_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.LIME_WOOL == null) {
            BlockTypes.LIME_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIME_WOOL)
                    .build();
        }
        if (BlockTypes.LIT_BLAST_FURNACE == null) {
            BlockTypes.LIT_BLAST_FURNACE = AllayBlockType
                    .builder(BlockBlastFurnaceBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIT_BLAST_FURNACE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE == null) {
            BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIT_DEEPSLATE_REDSTONE_ORE)
                    .build();
        }
        if (BlockTypes.LIT_FURNACE == null) {
            BlockTypes.LIT_FURNACE = AllayBlockType
                    .builder(BlockFurnaceBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIT_FURNACE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.LIT_PUMPKIN == null) {
            BlockTypes.LIT_PUMPKIN = AllayBlockType
                    .builder(BlockLitPumpkinBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIT_PUMPKIN)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.LIT_REDSTONE_LAMP == null) {
            BlockTypes.LIT_REDSTONE_LAMP = AllayBlockType
                    .builder(BlockRedstoneLampBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIT_REDSTONE_LAMP)
                    .build();
        }
        if (BlockTypes.LIT_REDSTONE_ORE == null) {
            BlockTypes.LIT_REDSTONE_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIT_REDSTONE_ORE)
                    .build();
        }
        if (BlockTypes.LIT_SMOKER == null) {
            BlockTypes.LIT_SMOKER = AllayBlockType
                    .builder(BlockSmokerBehaviorImpl.class)
                    .vanillaBlock(BlockId.LIT_SMOKER)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.LODESTONE == null) {
            BlockTypes.LODESTONE = AllayBlockType
                    .builder(BlockLodestoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.LODESTONE)
                    .build();
        }
        if (BlockTypes.LOOM == null) {
            BlockTypes.LOOM = AllayBlockType
                    .builder(BlockLoomBehaviorImpl.class)
                    .vanillaBlock(BlockId.LOOM)
                    .setProperties(BlockPropertyTypes.DIRECTION_4)
                    .build();
        }
        if (BlockTypes.MAGENTA_CANDLE == null) {
            BlockTypes.MAGENTA_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.MAGENTA_CANDLE_CAKE == null) {
            BlockTypes.MAGENTA_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.MAGENTA_CARPET == null) {
            BlockTypes.MAGENTA_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_CARPET)
                    .build();
        }
        if (BlockTypes.MAGENTA_CONCRETE == null) {
            BlockTypes.MAGENTA_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_CONCRETE)
                    .build();
        }
        if (BlockTypes.MAGENTA_CONCRETE_POWDER == null) {
            BlockTypes.MAGENTA_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.MAGENTA_GLAZED_TERRACOTTA == null) {
            BlockTypes.MAGENTA_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.MAGENTA_SHULKER_BOX == null) {
            BlockTypes.MAGENTA_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.MAGENTA_STAINED_GLASS == null) {
            BlockTypes.MAGENTA_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.MAGENTA_STAINED_GLASS_PANE == null) {
            BlockTypes.MAGENTA_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.MAGENTA_TERRACOTTA == null) {
            BlockTypes.MAGENTA_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.MAGENTA_WOOL == null) {
            BlockTypes.MAGENTA_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGENTA_WOOL)
                    .build();
        }
        if (BlockTypes.MAGMA == null) {
            BlockTypes.MAGMA = AllayBlockType
                    .builder(BlockMagmaBehaviorImpl.class)
                    .vanillaBlock(BlockId.MAGMA)
                    .build();
        }
        if (BlockTypes.MANGROVE_BUTTON == null) {
            BlockTypes.MANGROVE_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.MANGROVE_DOOR == null) {
            BlockTypes.MANGROVE_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.MANGROVE_DOUBLE_SLAB == null) {
            BlockTypes.MANGROVE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.MANGROVE_FENCE == null) {
            BlockTypes.MANGROVE_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_FENCE)
                    .build();
        }
        if (BlockTypes.MANGROVE_FENCE_GATE == null) {
            BlockTypes.MANGROVE_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.MANGROVE_HANGING_SIGN == null) {
            BlockTypes.MANGROVE_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.MANGROVE_LEAVES == null) {
            BlockTypes.MANGROVE_LEAVES = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_LEAVES)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.MANGROVE_LOG == null) {
            BlockTypes.MANGROVE_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.MANGROVE_PLANKS == null) {
            BlockTypes.MANGROVE_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_PLANKS)
                    .build();
        }
        if (BlockTypes.MANGROVE_PRESSURE_PLATE == null) {
            BlockTypes.MANGROVE_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.MANGROVE_PROPAGULE == null) {
            BlockTypes.MANGROVE_PROPAGULE = AllayBlockType
                    .builder(BlockMangrovePropaguleBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_PROPAGULE)
                    .setProperties(BlockPropertyTypes.HANGING, BlockPropertyTypes.PROPAGULE_STAGE)
                    .build();
        }
        if (BlockTypes.MANGROVE_ROOTS == null) {
            BlockTypes.MANGROVE_ROOTS = AllayBlockType
                    .builder(BlockMangroveRootsBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_ROOTS)
                    .build();
        }
        if (BlockTypes.MANGROVE_SHELF == null) {
            BlockTypes.MANGROVE_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.MANGROVE_SLAB == null) {
            BlockTypes.MANGROVE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.MANGROVE_STAIRS == null) {
            BlockTypes.MANGROVE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.MANGROVE_STANDING_SIGN == null) {
            BlockTypes.MANGROVE_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.MANGROVE_TRAPDOOR == null) {
            BlockTypes.MANGROVE_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.MANGROVE_WALL_SIGN == null) {
            BlockTypes.MANGROVE_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.MANGROVE_WOOD == null) {
            BlockTypes.MANGROVE_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.MANGROVE_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.MATERIAL_REDUCER == null) {
            BlockTypes.MATERIAL_REDUCER = AllayBlockType
                    .builder(BlockMaterialReducerBehaviorImpl.class)
                    .vanillaBlock(BlockId.MATERIAL_REDUCER)
                    .setProperties(BlockPropertyTypes.DIRECTION_4)
                    .build();
        }
        if (BlockTypes.MEDIUM_AMETHYST_BUD == null) {
            BlockTypes.MEDIUM_AMETHYST_BUD = AllayBlockType
                    .builder(BlockMediumAmethystBudBehaviorImpl.class)
                    .vanillaBlock(BlockId.MEDIUM_AMETHYST_BUD)
                    .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                    .build();
        }
        if (BlockTypes.MELON_BLOCK == null) {
            BlockTypes.MELON_BLOCK = AllayBlockType
                    .builder(BlockMelonBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.MELON_BLOCK)
                    .build();
        }
        if (BlockTypes.MELON_STEM == null) {
            BlockTypes.MELON_STEM = AllayBlockType
                    .builder(BlockMelonStemBehaviorImpl.class)
                    .vanillaBlock(BlockId.MELON_STEM)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROWTH)
                    .build();
        }
        if (BlockTypes.MOB_SPAWNER == null) {
            BlockTypes.MOB_SPAWNER = AllayBlockType
                    .builder(BlockMobSpawnerBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOB_SPAWNER)
                    .build();
        }
        if (BlockTypes.MOSS_BLOCK == null) {
            BlockTypes.MOSS_BLOCK = AllayBlockType
                    .builder(BlockMossBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSS_BLOCK)
                    .build();
        }
        if (BlockTypes.MOSS_CARPET == null) {
            BlockTypes.MOSS_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSS_CARPET)
                    .build();
        }
        if (BlockTypes.MOSSY_COBBLESTONE == null) {
            BlockTypes.MOSSY_COBBLESTONE = AllayBlockType
                    .builder(BlockMossyCobblestoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSSY_COBBLESTONE)
                    .build();
        }
        if (BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB == null) {
            BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSSY_COBBLESTONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.MOSSY_COBBLESTONE_SLAB == null) {
            BlockTypes.MOSSY_COBBLESTONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSSY_COBBLESTONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.MOSSY_COBBLESTONE_STAIRS == null) {
            BlockTypes.MOSSY_COBBLESTONE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSSY_COBBLESTONE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.MOSSY_COBBLESTONE_WALL == null) {
            BlockTypes.MOSSY_COBBLESTONE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSSY_COBBLESTONE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSSY_STONE_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.MOSSY_STONE_BRICK_SLAB == null) {
            BlockTypes.MOSSY_STONE_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSSY_STONE_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.MOSSY_STONE_BRICK_STAIRS == null) {
            BlockTypes.MOSSY_STONE_BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSSY_STONE_BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.MOSSY_STONE_BRICK_WALL == null) {
            BlockTypes.MOSSY_STONE_BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSSY_STONE_BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.MOSSY_STONE_BRICKS == null) {
            BlockTypes.MOSSY_STONE_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOSSY_STONE_BRICKS)
                    .build();
        }
        if (BlockTypes.MOVING_BLOCK == null) {
            BlockTypes.MOVING_BLOCK = AllayBlockType
                    .builder(BlockMovingBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.MOVING_BLOCK)
                    .build();
        }
        if (BlockTypes.MUD == null) {
            BlockTypes.MUD = AllayBlockType
                    .builder(BlockMudBehaviorImpl.class)
                    .vanillaBlock(BlockId.MUD)
                    .build();
        }
        if (BlockTypes.MUD_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.MUD_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.MUD_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.MUD_BRICK_SLAB == null) {
            BlockTypes.MUD_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.MUD_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.MUD_BRICK_STAIRS == null) {
            BlockTypes.MUD_BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.MUD_BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.MUD_BRICK_WALL == null) {
            BlockTypes.MUD_BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.MUD_BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.MUD_BRICKS == null) {
            BlockTypes.MUD_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.MUD_BRICKS)
                    .build();
        }
        if (BlockTypes.MUDDY_MANGROVE_ROOTS == null) {
            BlockTypes.MUDDY_MANGROVE_ROOTS = AllayBlockType
                    .builder(BlockMuddyMangroveRootsBehaviorImpl.class)
                    .vanillaBlock(BlockId.MUDDY_MANGROVE_ROOTS)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.MUSHROOM_STEM == null) {
            BlockTypes.MUSHROOM_STEM = AllayBlockType
                    .builder(BlockMushroomStemBehaviorImpl.class)
                    .vanillaBlock(BlockId.MUSHROOM_STEM)
                    .setProperties(BlockPropertyTypes.HUGE_MUSHROOM_BITS)
                    .build();
        }
        if (BlockTypes.MYCELIUM == null) {
            BlockTypes.MYCELIUM = AllayBlockType
                    .builder(BlockMyceliumBehaviorImpl.class)
                    .vanillaBlock(BlockId.MYCELIUM)
                    .build();
        }
        if (BlockTypes.NETHER_BRICK == null) {
            BlockTypes.NETHER_BRICK = AllayBlockType
                    .builder(BlockNetherBrickBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHER_BRICK)
                    .build();
        }
        if (BlockTypes.NETHER_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.NETHER_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHER_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.NETHER_BRICK_FENCE == null) {
            BlockTypes.NETHER_BRICK_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHER_BRICK_FENCE)
                    .build();
        }
        if (BlockTypes.NETHER_BRICK_SLAB == null) {
            BlockTypes.NETHER_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHER_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.NETHER_BRICK_STAIRS == null) {
            BlockTypes.NETHER_BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHER_BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.NETHER_BRICK_WALL == null) {
            BlockTypes.NETHER_BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHER_BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.NETHER_GOLD_ORE == null) {
            BlockTypes.NETHER_GOLD_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHER_GOLD_ORE)
                    .build();
        }
        if (BlockTypes.NETHER_SPROUTS == null) {
            BlockTypes.NETHER_SPROUTS = AllayBlockType
                    .builder(BlockNetherSproutsBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHER_SPROUTS)
                    .build();
        }
        if (BlockTypes.NETHER_WART == null) {
            BlockTypes.NETHER_WART = AllayBlockType
                    .builder(BlockNetherWartBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHER_WART)
                    .setProperties(BlockPropertyTypes.AGE_4)
                    .build();
        }
        if (BlockTypes.NETHER_WART_BLOCK == null) {
            BlockTypes.NETHER_WART_BLOCK = AllayBlockType
                    .builder(BlockNetherWartBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHER_WART_BLOCK)
                    .build();
        }
        if (BlockTypes.NETHERITE_BLOCK == null) {
            BlockTypes.NETHERITE_BLOCK = AllayBlockType
                    .builder(BlockNetheriteBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHERITE_BLOCK)
                    .build();
        }
        if (BlockTypes.NETHERRACK == null) {
            BlockTypes.NETHERRACK = AllayBlockType
                    .builder(BlockNetherrackBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHERRACK)
                    .build();
        }
        if (BlockTypes.NETHERREACTOR == null) {
            BlockTypes.NETHERREACTOR = AllayBlockType
                    .builder(BlockNetherreactorBehaviorImpl.class)
                    .vanillaBlock(BlockId.NETHERREACTOR)
                    .build();
        }
        if (BlockTypes.NORMAL_STONE_DOUBLE_SLAB == null) {
            BlockTypes.NORMAL_STONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.NORMAL_STONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.NORMAL_STONE_SLAB == null) {
            BlockTypes.NORMAL_STONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.NORMAL_STONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.NORMAL_STONE_STAIRS == null) {
            BlockTypes.NORMAL_STONE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.NORMAL_STONE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.NOTEBLOCK == null) {
            BlockTypes.NOTEBLOCK = AllayBlockType
                    .builder(BlockNoteblockBehaviorImpl.class)
                    .vanillaBlock(BlockId.NOTEBLOCK)
                    .build();
        }
        if (BlockTypes.OAK_DOUBLE_SLAB == null) {
            BlockTypes.OAK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.OAK_FENCE == null) {
            BlockTypes.OAK_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_FENCE)
                    .build();
        }
        if (BlockTypes.OAK_HANGING_SIGN == null) {
            BlockTypes.OAK_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.OAK_LEAVES == null) {
            BlockTypes.OAK_LEAVES = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_LEAVES)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.OAK_LOG == null) {
            BlockTypes.OAK_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.OAK_PLANKS == null) {
            BlockTypes.OAK_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_PLANKS)
                    .build();
        }
        if (BlockTypes.OAK_SAPLING == null) {
            BlockTypes.OAK_SAPLING = AllayBlockType
                    .builder(BlockSaplingBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_SAPLING)
                    .setProperties(BlockPropertyTypes.AGE_BIT)
                    .build();
        }
        if (BlockTypes.OAK_SHELF == null) {
            BlockTypes.OAK_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.OAK_SLAB == null) {
            BlockTypes.OAK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.OAK_STAIRS == null) {
            BlockTypes.OAK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.OAK_WOOD == null) {
            BlockTypes.OAK_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.OAK_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.OBSERVER == null) {
            BlockTypes.OBSERVER = AllayBlockType
                    .builder(BlockObserverBehaviorImpl.class)
                    .vanillaBlock(BlockId.OBSERVER)
                    .setProperties(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.OBSIDIAN == null) {
            BlockTypes.OBSIDIAN = AllayBlockType
                    .builder(BlockObsidianBehaviorImpl.class)
                    .vanillaBlock(BlockId.OBSIDIAN)
                    .build();
        }
        if (BlockTypes.OCHRE_FROGLIGHT == null) {
            BlockTypes.OCHRE_FROGLIGHT = AllayBlockType
                    .builder(BlockOchreFroglightBehaviorImpl.class)
                    .vanillaBlock(BlockId.OCHRE_FROGLIGHT)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.OPEN_EYEBLOSSOM == null) {
            BlockTypes.OPEN_EYEBLOSSOM = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.OPEN_EYEBLOSSOM)
                    .build();
        }
        if (BlockTypes.ORANGE_CANDLE == null) {
            BlockTypes.ORANGE_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.ORANGE_CANDLE_CAKE == null) {
            BlockTypes.ORANGE_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.ORANGE_CARPET == null) {
            BlockTypes.ORANGE_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_CARPET)
                    .build();
        }
        if (BlockTypes.ORANGE_CONCRETE == null) {
            BlockTypes.ORANGE_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_CONCRETE)
                    .build();
        }
        if (BlockTypes.ORANGE_CONCRETE_POWDER == null) {
            BlockTypes.ORANGE_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.ORANGE_GLAZED_TERRACOTTA == null) {
            BlockTypes.ORANGE_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.ORANGE_SHULKER_BOX == null) {
            BlockTypes.ORANGE_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.ORANGE_STAINED_GLASS == null) {
            BlockTypes.ORANGE_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.ORANGE_STAINED_GLASS_PANE == null) {
            BlockTypes.ORANGE_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.ORANGE_TERRACOTTA == null) {
            BlockTypes.ORANGE_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.ORANGE_TULIP == null) {
            BlockTypes.ORANGE_TULIP = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.ORANGE_TULIP)
                    .build();
        }
        if (BlockTypes.ORANGE_WOOL == null) {
            BlockTypes.ORANGE_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.ORANGE_WOOL)
                    .build();
        }
        if (BlockTypes.OXEYE_DAISY == null) {
            BlockTypes.OXEYE_DAISY = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.OXEYE_DAISY)
                    .build();
        }
        if (BlockTypes.OXIDIZED_CHISELED_COPPER == null) {
            BlockTypes.OXIDIZED_CHISELED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_CHISELED_COPPER)
                    .build();
        }
        if (BlockTypes.OXIDIZED_COPPER == null) {
            BlockTypes.OXIDIZED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_COPPER)
                    .build();
        }
        if (BlockTypes.OXIDIZED_COPPER_BARS == null) {
            BlockTypes.OXIDIZED_COPPER_BARS = AllayBlockType
                    .builder(BlockCopperBarsBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_COPPER_BARS)
                    .build();
        }
        if (BlockTypes.OXIDIZED_COPPER_BULB == null) {
            BlockTypes.OXIDIZED_COPPER_BULB = AllayBlockType
                    .builder(BlockCopperBulbBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_COPPER_BULB)
                    .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.OXIDIZED_COPPER_CHAIN == null) {
            BlockTypes.OXIDIZED_COPPER_CHAIN = AllayBlockType
                    .builder(BlockCopperChainBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_COPPER_CHAIN)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.OXIDIZED_COPPER_CHEST == null) {
            BlockTypes.OXIDIZED_COPPER_CHEST = AllayBlockType
                    .builder(BlockOxidizedCopperChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_COPPER_CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.OXIDIZED_COPPER_DOOR == null) {
            BlockTypes.OXIDIZED_COPPER_DOOR = AllayBlockType
                    .builder(BlockCopperDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_COPPER_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.OXIDIZED_COPPER_GOLEM_STATUE == null) {
            BlockTypes.OXIDIZED_COPPER_GOLEM_STATUE = AllayBlockType
                    .builder(BlockCopperGolemStatueBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_COPPER_GOLEM_STATUE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.OXIDIZED_COPPER_GRATE == null) {
            BlockTypes.OXIDIZED_COPPER_GRATE = AllayBlockType
                    .builder(BlockCopperGrateBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_COPPER_GRATE)
                    .build();
        }
        if (BlockTypes.OXIDIZED_COPPER_LANTERN == null) {
            BlockTypes.OXIDIZED_COPPER_LANTERN = AllayBlockType
                    .builder(BlockCopperLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_COPPER_LANTERN)
                    .setProperties(BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.OXIDIZED_COPPER_TRAPDOOR == null) {
            BlockTypes.OXIDIZED_COPPER_TRAPDOOR = AllayBlockType
                    .builder(BlockCopperTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_COPPER_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.OXIDIZED_CUT_COPPER == null) {
            BlockTypes.OXIDIZED_CUT_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER)
                    .build();
        }
        if (BlockTypes.OXIDIZED_CUT_COPPER_SLAB == null) {
            BlockTypes.OXIDIZED_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.OXIDIZED_CUT_COPPER_STAIRS == null) {
            BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = AllayBlockType
                    .builder(BlockCopperStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB == null) {
            BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockDoubleCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.OXIDIZED_LIGHTNING_ROD == null) {
            BlockTypes.OXIDIZED_LIGHTNING_ROD = AllayBlockType
                    .builder(BlockLightningRodBehaviorImpl.class)
                    .vanillaBlock(BlockId.OXIDIZED_LIGHTNING_ROD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.PACKED_ICE == null) {
            BlockTypes.PACKED_ICE = AllayBlockType
                    .builder(BlockIceBehaviorImpl.class)
                    .vanillaBlock(BlockId.PACKED_ICE)
                    .build();
        }
        if (BlockTypes.PACKED_MUD == null) {
            BlockTypes.PACKED_MUD = AllayBlockType
                    .builder(BlockPackedMudBehaviorImpl.class)
                    .vanillaBlock(BlockId.PACKED_MUD)
                    .build();
        }
        if (BlockTypes.PALE_HANGING_MOSS == null) {
            BlockTypes.PALE_HANGING_MOSS = AllayBlockType
                    .builder(BlockPaleHangingMossBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_HANGING_MOSS)
                    .setProperties(BlockPropertyTypes.TIP)
                    .build();
        }
        if (BlockTypes.PALE_MOSS_BLOCK == null) {
            BlockTypes.PALE_MOSS_BLOCK = AllayBlockType
                    .builder(BlockPaleMossBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_MOSS_BLOCK)
                    .build();
        }
        if (BlockTypes.PALE_MOSS_CARPET == null) {
            BlockTypes.PALE_MOSS_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_MOSS_CARPET)
                    .setProperties(BlockPropertyTypes.PALE_MOSS_CARPET_SIDE_EAST, BlockPropertyTypes.PALE_MOSS_CARPET_SIDE_NORTH, BlockPropertyTypes.PALE_MOSS_CARPET_SIDE_SOUTH, BlockPropertyTypes.PALE_MOSS_CARPET_SIDE_WEST, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.PALE_OAK_BUTTON == null) {
            BlockTypes.PALE_OAK_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.PALE_OAK_DOOR == null) {
            BlockTypes.PALE_OAK_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.PALE_OAK_DOUBLE_SLAB == null) {
            BlockTypes.PALE_OAK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.PALE_OAK_FENCE == null) {
            BlockTypes.PALE_OAK_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_FENCE)
                    .build();
        }
        if (BlockTypes.PALE_OAK_FENCE_GATE == null) {
            BlockTypes.PALE_OAK_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.PALE_OAK_HANGING_SIGN == null) {
            BlockTypes.PALE_OAK_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.PALE_OAK_LEAVES == null) {
            BlockTypes.PALE_OAK_LEAVES = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_LEAVES)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.PALE_OAK_LOG == null) {
            BlockTypes.PALE_OAK_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.PALE_OAK_PLANKS == null) {
            BlockTypes.PALE_OAK_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_PLANKS)
                    .build();
        }
        if (BlockTypes.PALE_OAK_PRESSURE_PLATE == null) {
            BlockTypes.PALE_OAK_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.PALE_OAK_SAPLING == null) {
            BlockTypes.PALE_OAK_SAPLING = AllayBlockType
                    .builder(BlockSaplingBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_SAPLING)
                    .setProperties(BlockPropertyTypes.AGE_BIT)
                    .build();
        }
        if (BlockTypes.PALE_OAK_SHELF == null) {
            BlockTypes.PALE_OAK_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.PALE_OAK_SLAB == null) {
            BlockTypes.PALE_OAK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.PALE_OAK_STAIRS == null) {
            BlockTypes.PALE_OAK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.PALE_OAK_STANDING_SIGN == null) {
            BlockTypes.PALE_OAK_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.PALE_OAK_TRAPDOOR == null) {
            BlockTypes.PALE_OAK_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.PALE_OAK_WALL_SIGN == null) {
            BlockTypes.PALE_OAK_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.PALE_OAK_WOOD == null) {
            BlockTypes.PALE_OAK_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.PALE_OAK_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.PEARLESCENT_FROGLIGHT == null) {
            BlockTypes.PEARLESCENT_FROGLIGHT = AllayBlockType
                    .builder(BlockPearlescentFroglightBehaviorImpl.class)
                    .vanillaBlock(BlockId.PEARLESCENT_FROGLIGHT)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.PEONY == null) {
            BlockTypes.PEONY = AllayBlockType
                    .builder(BlockBigFlowerImpl.class)
                    .vanillaBlock(BlockId.PEONY)
                    .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB == null) {
            BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.PETRIFIED_OAK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.PETRIFIED_OAK_SLAB == null) {
            BlockTypes.PETRIFIED_OAK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.PETRIFIED_OAK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.PIGLIN_HEAD == null) {
            BlockTypes.PIGLIN_HEAD = AllayBlockType
                    .builder(BlockHeadBehaviorImpl.class)
                    .vanillaBlock(BlockId.PIGLIN_HEAD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.PINK_CANDLE == null) {
            BlockTypes.PINK_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.PINK_CANDLE_CAKE == null) {
            BlockTypes.PINK_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.PINK_CARPET == null) {
            BlockTypes.PINK_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_CARPET)
                    .build();
        }
        if (BlockTypes.PINK_CONCRETE == null) {
            BlockTypes.PINK_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_CONCRETE)
                    .build();
        }
        if (BlockTypes.PINK_CONCRETE_POWDER == null) {
            BlockTypes.PINK_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.PINK_GLAZED_TERRACOTTA == null) {
            BlockTypes.PINK_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.PINK_PETALS == null) {
            BlockTypes.PINK_PETALS = AllayBlockType
                    .builder(BlockPlantPileImpl.class)
                    .vanillaBlock(BlockId.PINK_PETALS)
                    .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.PINK_SHULKER_BOX == null) {
            BlockTypes.PINK_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.PINK_STAINED_GLASS == null) {
            BlockTypes.PINK_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.PINK_STAINED_GLASS_PANE == null) {
            BlockTypes.PINK_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.PINK_TERRACOTTA == null) {
            BlockTypes.PINK_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.PINK_TULIP == null) {
            BlockTypes.PINK_TULIP = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.PINK_TULIP)
                    .build();
        }
        if (BlockTypes.PINK_WOOL == null) {
            BlockTypes.PINK_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.PINK_WOOL)
                    .build();
        }
        if (BlockTypes.PISTON == null) {
            BlockTypes.PISTON = AllayBlockType
                    .builder(BlockPistonBehaviorImpl.class)
                    .vanillaBlock(BlockId.PISTON)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.PISTON_ARM_COLLISION == null) {
            BlockTypes.PISTON_ARM_COLLISION = AllayBlockType
                    .builder(BlockPistonArmCollisionBehaviorImpl.class)
                    .vanillaBlock(BlockId.PISTON_ARM_COLLISION)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.PITCHER_CROP == null) {
            BlockTypes.PITCHER_CROP = AllayBlockType
                    .builder(BlockPitcherCropBehaviorImpl.class)
                    .vanillaBlock(BlockId.PITCHER_CROP)
                    .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.PITCHER_PLANT == null) {
            BlockTypes.PITCHER_PLANT = AllayBlockType
                    .builder(BlockBigFlowerImpl.class)
                    .vanillaBlock(BlockId.PITCHER_PLANT)
                    .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.PLAYER_HEAD == null) {
            BlockTypes.PLAYER_HEAD = AllayBlockType
                    .builder(BlockHeadBehaviorImpl.class)
                    .vanillaBlock(BlockId.PLAYER_HEAD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.PODZOL == null) {
            BlockTypes.PODZOL = AllayBlockType
                    .builder(BlockPodzolBehaviorImpl.class)
                    .vanillaBlock(BlockId.PODZOL)
                    .build();
        }
        if (BlockTypes.POINTED_DRIPSTONE == null) {
            BlockTypes.POINTED_DRIPSTONE = AllayBlockType
                    .builder(BlockPointedDripstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.POINTED_DRIPSTONE)
                    .setProperties(BlockPropertyTypes.DRIPSTONE_THICKNESS, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.POLISHED_ANDESITE == null) {
            BlockTypes.POLISHED_ANDESITE = AllayBlockType
                    .builder(BlockPolishedAndesiteBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_ANDESITE)
                    .build();
        }
        if (BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB == null) {
            BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_ANDESITE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_ANDESITE_SLAB == null) {
            BlockTypes.POLISHED_ANDESITE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_ANDESITE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_ANDESITE_STAIRS == null) {
            BlockTypes.POLISHED_ANDESITE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_ANDESITE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.POLISHED_BASALT == null) {
            BlockTypes.POLISHED_BASALT = AllayBlockType
                    .builder(BlockPolishedBasaltBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BASALT)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE == null) {
            BlockTypes.POLISHED_BLACKSTONE = AllayBlockType
                    .builder(BlockPolishedBlackstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB == null) {
            BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS == null) {
            BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL == null) {
            BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_BRICKS == null) {
            BlockTypes.POLISHED_BLACKSTONE_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICKS)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_BUTTON == null) {
            BlockTypes.POLISHED_BLACKSTONE_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB == null) {
            BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE == null) {
            BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_SLAB == null) {
            BlockTypes.POLISHED_BLACKSTONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_STAIRS == null) {
            BlockTypes.POLISHED_BLACKSTONE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.POLISHED_BLACKSTONE_WALL == null) {
            BlockTypes.POLISHED_BLACKSTONE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_BLACKSTONE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.POLISHED_DEEPSLATE == null) {
            BlockTypes.POLISHED_DEEPSLATE = AllayBlockType
                    .builder(BlockPolishedDeepslateBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_DEEPSLATE)
                    .build();
        }
        if (BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB == null) {
            BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_DEEPSLATE_SLAB == null) {
            BlockTypes.POLISHED_DEEPSLATE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_DEEPSLATE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_DEEPSLATE_STAIRS == null) {
            BlockTypes.POLISHED_DEEPSLATE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_DEEPSLATE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.POLISHED_DEEPSLATE_WALL == null) {
            BlockTypes.POLISHED_DEEPSLATE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_DEEPSLATE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.POLISHED_DIORITE == null) {
            BlockTypes.POLISHED_DIORITE = AllayBlockType
                    .builder(BlockPolishedDioriteBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_DIORITE)
                    .build();
        }
        if (BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB == null) {
            BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_DIORITE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_DIORITE_SLAB == null) {
            BlockTypes.POLISHED_DIORITE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_DIORITE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_DIORITE_STAIRS == null) {
            BlockTypes.POLISHED_DIORITE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_DIORITE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.POLISHED_GRANITE == null) {
            BlockTypes.POLISHED_GRANITE = AllayBlockType
                    .builder(BlockPolishedGraniteBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_GRANITE)
                    .build();
        }
        if (BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB == null) {
            BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_GRANITE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_GRANITE_SLAB == null) {
            BlockTypes.POLISHED_GRANITE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_GRANITE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_GRANITE_STAIRS == null) {
            BlockTypes.POLISHED_GRANITE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_GRANITE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.POLISHED_TUFF == null) {
            BlockTypes.POLISHED_TUFF = AllayBlockType
                    .builder(BlockPolishedTuffBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_TUFF)
                    .build();
        }
        if (BlockTypes.POLISHED_TUFF_DOUBLE_SLAB == null) {
            BlockTypes.POLISHED_TUFF_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_TUFF_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_TUFF_SLAB == null) {
            BlockTypes.POLISHED_TUFF_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_TUFF_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.POLISHED_TUFF_STAIRS == null) {
            BlockTypes.POLISHED_TUFF_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_TUFF_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.POLISHED_TUFF_WALL == null) {
            BlockTypes.POLISHED_TUFF_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.POLISHED_TUFF_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.POPPY == null) {
            BlockTypes.POPPY = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.POPPY)
                    .build();
        }
        if (BlockTypes.PORTAL == null) {
            BlockTypes.PORTAL = AllayBlockType
                    .builder(BlockPortalBehaviorImpl.class)
                    .vanillaBlock(BlockId.PORTAL)
                    .setProperties(BlockPropertyTypes.PORTAL_AXIS)
                    .build();
        }
        if (BlockTypes.POTATOES == null) {
            BlockTypes.POTATOES = AllayBlockType
                    .builder(BlockPotatoesBehaviorImpl.class)
                    .vanillaBlock(BlockId.POTATOES)
                    .setProperties(BlockPropertyTypes.GROWTH)
                    .build();
        }
        if (BlockTypes.POWDER_SNOW == null) {
            BlockTypes.POWDER_SNOW = AllayBlockType
                    .builder(BlockPowderSnowBehaviorImpl.class)
                    .vanillaBlock(BlockId.POWDER_SNOW)
                    .build();
        }
        if (BlockTypes.POWERED_COMPARATOR == null) {
            BlockTypes.POWERED_COMPARATOR = AllayBlockType
                    .builder(BlockComparatorBehaviorImpl.class)
                    .vanillaBlock(BlockId.POWERED_COMPARATOR)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                    .build();
        }
        if (BlockTypes.POWERED_REPEATER == null) {
            BlockTypes.POWERED_REPEATER = AllayBlockType
                    .builder(BlockRepeaterBehaviorImpl.class)
                    .vanillaBlock(BlockId.POWERED_REPEATER)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                    .build();
        }
        if (BlockTypes.PRISMARINE == null) {
            BlockTypes.PRISMARINE = AllayBlockType
                    .builder(BlockPrismarineBehaviorImpl.class)
                    .vanillaBlock(BlockId.PRISMARINE)
                    .build();
        }
        if (BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.PRISMARINE_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.PRISMARINE_BRICK_SLAB == null) {
            BlockTypes.PRISMARINE_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.PRISMARINE_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.PRISMARINE_BRICKS == null) {
            BlockTypes.PRISMARINE_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.PRISMARINE_BRICKS)
                    .build();
        }
        if (BlockTypes.PRISMARINE_BRICKS_STAIRS == null) {
            BlockTypes.PRISMARINE_BRICKS_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.PRISMARINE_BRICKS_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.PRISMARINE_DOUBLE_SLAB == null) {
            BlockTypes.PRISMARINE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.PRISMARINE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.PRISMARINE_SLAB == null) {
            BlockTypes.PRISMARINE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.PRISMARINE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.PRISMARINE_STAIRS == null) {
            BlockTypes.PRISMARINE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.PRISMARINE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.PRISMARINE_WALL == null) {
            BlockTypes.PRISMARINE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.PRISMARINE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.PUMPKIN == null) {
            BlockTypes.PUMPKIN = AllayBlockType
                    .builder(BlockPumpkinBehaviorImpl.class)
                    .vanillaBlock(BlockId.PUMPKIN)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.PUMPKIN_STEM == null) {
            BlockTypes.PUMPKIN_STEM = AllayBlockType
                    .builder(BlockPumpkinStemBehaviorImpl.class)
                    .vanillaBlock(BlockId.PUMPKIN_STEM)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROWTH)
                    .build();
        }
        if (BlockTypes.PURPLE_CANDLE == null) {
            BlockTypes.PURPLE_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.PURPLE_CANDLE_CAKE == null) {
            BlockTypes.PURPLE_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.PURPLE_CARPET == null) {
            BlockTypes.PURPLE_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_CARPET)
                    .build();
        }
        if (BlockTypes.PURPLE_CONCRETE == null) {
            BlockTypes.PURPLE_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_CONCRETE)
                    .build();
        }
        if (BlockTypes.PURPLE_CONCRETE_POWDER == null) {
            BlockTypes.PURPLE_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.PURPLE_GLAZED_TERRACOTTA == null) {
            BlockTypes.PURPLE_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.PURPLE_SHULKER_BOX == null) {
            BlockTypes.PURPLE_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.PURPLE_STAINED_GLASS == null) {
            BlockTypes.PURPLE_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.PURPLE_STAINED_GLASS_PANE == null) {
            BlockTypes.PURPLE_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.PURPLE_TERRACOTTA == null) {
            BlockTypes.PURPLE_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.PURPLE_WOOL == null) {
            BlockTypes.PURPLE_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPLE_WOOL)
                    .build();
        }
        if (BlockTypes.PURPUR_BLOCK == null) {
            BlockTypes.PURPUR_BLOCK = AllayBlockType
                    .builder(BlockPurpurBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPUR_BLOCK)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.PURPUR_DOUBLE_SLAB == null) {
            BlockTypes.PURPUR_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPUR_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.PURPUR_PILLAR == null) {
            BlockTypes.PURPUR_PILLAR = AllayBlockType
                    .builder(BlockPurpurPillarBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPUR_PILLAR)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.PURPUR_SLAB == null) {
            BlockTypes.PURPUR_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPUR_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.PURPUR_STAIRS == null) {
            BlockTypes.PURPUR_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.PURPUR_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.QUARTZ_BLOCK == null) {
            BlockTypes.QUARTZ_BLOCK = AllayBlockType
                    .builder(BlockQuartzBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.QUARTZ_BLOCK)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.QUARTZ_BRICKS == null) {
            BlockTypes.QUARTZ_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.QUARTZ_BRICKS)
                    .build();
        }
        if (BlockTypes.QUARTZ_DOUBLE_SLAB == null) {
            BlockTypes.QUARTZ_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.QUARTZ_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.QUARTZ_ORE == null) {
            BlockTypes.QUARTZ_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.QUARTZ_ORE)
                    .build();
        }
        if (BlockTypes.QUARTZ_PILLAR == null) {
            BlockTypes.QUARTZ_PILLAR = AllayBlockType
                    .builder(BlockQuartzPillarBehaviorImpl.class)
                    .vanillaBlock(BlockId.QUARTZ_PILLAR)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.QUARTZ_SLAB == null) {
            BlockTypes.QUARTZ_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.QUARTZ_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.QUARTZ_STAIRS == null) {
            BlockTypes.QUARTZ_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.QUARTZ_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.RAIL == null) {
            BlockTypes.RAIL = AllayBlockType
                    .builder(BlockRailBehaviorImpl.class)
                    .vanillaBlock(BlockId.RAIL)
                    .setProperties(BlockPropertyTypes.RAIL_DIRECTION_10)
                    .build();
        }
        if (BlockTypes.RAW_COPPER_BLOCK == null) {
            BlockTypes.RAW_COPPER_BLOCK = AllayBlockType
                    .builder(BlockRawCopperBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.RAW_COPPER_BLOCK)
                    .build();
        }
        if (BlockTypes.RAW_GOLD_BLOCK == null) {
            BlockTypes.RAW_GOLD_BLOCK = AllayBlockType
                    .builder(BlockRawGoldBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.RAW_GOLD_BLOCK)
                    .build();
        }
        if (BlockTypes.RAW_IRON_BLOCK == null) {
            BlockTypes.RAW_IRON_BLOCK = AllayBlockType
                    .builder(BlockRawIronBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.RAW_IRON_BLOCK)
                    .build();
        }
        if (BlockTypes.RED_CANDLE == null) {
            BlockTypes.RED_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.RED_CANDLE_CAKE == null) {
            BlockTypes.RED_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.RED_CARPET == null) {
            BlockTypes.RED_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_CARPET)
                    .build();
        }
        if (BlockTypes.RED_CONCRETE == null) {
            BlockTypes.RED_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_CONCRETE)
                    .build();
        }
        if (BlockTypes.RED_CONCRETE_POWDER == null) {
            BlockTypes.RED_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.RED_GLAZED_TERRACOTTA == null) {
            BlockTypes.RED_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.RED_MUSHROOM == null) {
            BlockTypes.RED_MUSHROOM = AllayBlockType
                    .builder(BlockRedMushroomBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_MUSHROOM)
                    .build();
        }
        if (BlockTypes.RED_MUSHROOM_BLOCK == null) {
            BlockTypes.RED_MUSHROOM_BLOCK = AllayBlockType
                    .builder(BlockRedMushroomBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_MUSHROOM_BLOCK)
                    .setProperties(BlockPropertyTypes.HUGE_MUSHROOM_BITS)
                    .build();
        }
        if (BlockTypes.RED_NETHER_BRICK == null) {
            BlockTypes.RED_NETHER_BRICK = AllayBlockType
                    .builder(BlockRedNetherBrickBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_NETHER_BRICK)
                    .build();
        }
        if (BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_NETHER_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.RED_NETHER_BRICK_SLAB == null) {
            BlockTypes.RED_NETHER_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_NETHER_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.RED_NETHER_BRICK_STAIRS == null) {
            BlockTypes.RED_NETHER_BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_NETHER_BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.RED_NETHER_BRICK_WALL == null) {
            BlockTypes.RED_NETHER_BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_NETHER_BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.RED_SAND == null) {
            BlockTypes.RED_SAND = AllayBlockType
                    .builder(BlockRedSandBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_SAND)
                    .build();
        }
        if (BlockTypes.RED_SANDSTONE == null) {
            BlockTypes.RED_SANDSTONE = AllayBlockType
                    .builder(BlockSandstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_SANDSTONE)
                    .build();
        }
        if (BlockTypes.RED_SANDSTONE_DOUBLE_SLAB == null) {
            BlockTypes.RED_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_SANDSTONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.RED_SANDSTONE_SLAB == null) {
            BlockTypes.RED_SANDSTONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_SANDSTONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.RED_SANDSTONE_STAIRS == null) {
            BlockTypes.RED_SANDSTONE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_SANDSTONE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.RED_SANDSTONE_WALL == null) {
            BlockTypes.RED_SANDSTONE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_SANDSTONE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.RED_SHULKER_BOX == null) {
            BlockTypes.RED_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.RED_STAINED_GLASS == null) {
            BlockTypes.RED_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.RED_STAINED_GLASS_PANE == null) {
            BlockTypes.RED_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.RED_TERRACOTTA == null) {
            BlockTypes.RED_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.RED_TULIP == null) {
            BlockTypes.RED_TULIP = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.RED_TULIP)
                    .build();
        }
        if (BlockTypes.RED_WOOL == null) {
            BlockTypes.RED_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.RED_WOOL)
                    .build();
        }
        if (BlockTypes.REDSTONE_BLOCK == null) {
            BlockTypes.REDSTONE_BLOCK = AllayBlockType
                    .builder(BlockRedstoneBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.REDSTONE_BLOCK)
                    .build();
        }
        if (BlockTypes.REDSTONE_LAMP == null) {
            BlockTypes.REDSTONE_LAMP = AllayBlockType
                    .builder(BlockRedstoneLampBehaviorImpl.class)
                    .vanillaBlock(BlockId.REDSTONE_LAMP)
                    .build();
        }
        if (BlockTypes.REDSTONE_ORE == null) {
            BlockTypes.REDSTONE_ORE = AllayBlockType
                    .builder(BlockOreBehaviorImpl.class)
                    .vanillaBlock(BlockId.REDSTONE_ORE)
                    .build();
        }
        if (BlockTypes.REDSTONE_TORCH == null) {
            BlockTypes.REDSTONE_TORCH = AllayBlockType
                    .builder(BlockTorchBehaviorImpl.class)
                    .vanillaBlock(BlockId.REDSTONE_TORCH)
                    .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.REDSTONE_WIRE == null) {
            BlockTypes.REDSTONE_WIRE = AllayBlockType
                    .builder(BlockRedstoneWireBehaviorImpl.class)
                    .vanillaBlock(BlockId.REDSTONE_WIRE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.REEDS == null) {
            BlockTypes.REEDS = AllayBlockType
                    .builder(BlockReedsBehaviorImpl.class)
                    .vanillaBlock(BlockId.REEDS)
                    .setProperties(BlockPropertyTypes.AGE_16)
                    .build();
        }
        if (BlockTypes.REINFORCED_DEEPSLATE == null) {
            BlockTypes.REINFORCED_DEEPSLATE = AllayBlockType
                    .builder(BlockReinforcedDeepslateBehaviorImpl.class)
                    .vanillaBlock(BlockId.REINFORCED_DEEPSLATE)
                    .build();
        }
        if (BlockTypes.REPEATING_COMMAND_BLOCK == null) {
            BlockTypes.REPEATING_COMMAND_BLOCK = AllayBlockType
                    .builder(BlockRepeatingCommandBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.REPEATING_COMMAND_BLOCK)
                    .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.RESERVED6 == null) {
            BlockTypes.RESERVED6 = AllayBlockType
                    .builder(BlockReserved6BehaviorImpl.class)
                    .vanillaBlock(BlockId.RESERVED6)
                    .build();
        }
        if (BlockTypes.RESIN_BLOCK == null) {
            BlockTypes.RESIN_BLOCK = AllayBlockType
                    .builder(BlockResinBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.RESIN_BLOCK)
                    .build();
        }
        if (BlockTypes.RESIN_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.RESIN_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.RESIN_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.RESIN_BRICK_SLAB == null) {
            BlockTypes.RESIN_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.RESIN_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.RESIN_BRICK_STAIRS == null) {
            BlockTypes.RESIN_BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.RESIN_BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.RESIN_BRICK_WALL == null) {
            BlockTypes.RESIN_BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.RESIN_BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.RESIN_BRICKS == null) {
            BlockTypes.RESIN_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.RESIN_BRICKS)
                    .build();
        }
        if (BlockTypes.RESIN_CLUMP == null) {
            BlockTypes.RESIN_CLUMP = AllayBlockType
                    .builder(BlockResinClumpBehaviorImpl.class)
                    .vanillaBlock(BlockId.RESIN_CLUMP)
                    .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                    .build();
        }
        if (BlockTypes.RESPAWN_ANCHOR == null) {
            BlockTypes.RESPAWN_ANCHOR = AllayBlockType
                    .builder(BlockRespawnAnchorBehaviorImpl.class)
                    .vanillaBlock(BlockId.RESPAWN_ANCHOR)
                    .setProperties(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
                    .build();
        }
        if (BlockTypes.ROSE_BUSH == null) {
            BlockTypes.ROSE_BUSH = AllayBlockType
                    .builder(BlockBigFlowerImpl.class)
                    .vanillaBlock(BlockId.ROSE_BUSH)
                    .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.SAND == null) {
            BlockTypes.SAND = AllayBlockType
                    .builder(BlockSandBehaviorImpl.class)
                    .vanillaBlock(BlockId.SAND)
                    .build();
        }
        if (BlockTypes.SANDSTONE == null) {
            BlockTypes.SANDSTONE = AllayBlockType
                    .builder(BlockSandstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.SANDSTONE)
                    .build();
        }
        if (BlockTypes.SANDSTONE_DOUBLE_SLAB == null) {
            BlockTypes.SANDSTONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SANDSTONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SANDSTONE_SLAB == null) {
            BlockTypes.SANDSTONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SANDSTONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SANDSTONE_STAIRS == null) {
            BlockTypes.SANDSTONE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.SANDSTONE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.SANDSTONE_WALL == null) {
            BlockTypes.SANDSTONE_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.SANDSTONE_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.SCAFFOLDING == null) {
            BlockTypes.SCAFFOLDING = AllayBlockType
                    .builder(BlockScaffoldingBehaviorImpl.class)
                    .vanillaBlock(BlockId.SCAFFOLDING)
                    .setProperties(BlockPropertyTypes.STABILITY, BlockPropertyTypes.STABILITY_CHECK)
                    .build();
        }
        if (BlockTypes.SCULK == null) {
            BlockTypes.SCULK = AllayBlockType
                    .builder(BlockSculkBehaviorImpl.class)
                    .vanillaBlock(BlockId.SCULK)
                    .build();
        }
        if (BlockTypes.SCULK_CATALYST == null) {
            BlockTypes.SCULK_CATALYST = AllayBlockType
                    .builder(BlockSculkCatalystBehaviorImpl.class)
                    .vanillaBlock(BlockId.SCULK_CATALYST)
                    .setProperties(BlockPropertyTypes.BLOOM)
                    .build();
        }
        if (BlockTypes.SCULK_SENSOR == null) {
            BlockTypes.SCULK_SENSOR = AllayBlockType
                    .builder(BlockSculkSensorBehaviorImpl.class)
                    .vanillaBlock(BlockId.SCULK_SENSOR)
                    .setProperties(BlockPropertyTypes.SCULK_SENSOR_PHASE)
                    .build();
        }
        if (BlockTypes.SCULK_SHRIEKER == null) {
            BlockTypes.SCULK_SHRIEKER = AllayBlockType
                    .builder(BlockSculkShriekerBehaviorImpl.class)
                    .vanillaBlock(BlockId.SCULK_SHRIEKER)
                    .setProperties(BlockPropertyTypes.ACTIVE, BlockPropertyTypes.CAN_SUMMON)
                    .build();
        }
        if (BlockTypes.SCULK_VEIN == null) {
            BlockTypes.SCULK_VEIN = AllayBlockType
                    .builder(BlockSculkVeinBehaviorImpl.class)
                    .vanillaBlock(BlockId.SCULK_VEIN)
                    .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                    .build();
        }
        if (BlockTypes.SEA_LANTERN == null) {
            BlockTypes.SEA_LANTERN = AllayBlockType
                    .builder(BlockSeaLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.SEA_LANTERN)
                    .build();
        }
        if (BlockTypes.SEA_PICKLE == null) {
            BlockTypes.SEA_PICKLE = AllayBlockType
                    .builder(BlockSeaPickleBehaviorImpl.class)
                    .vanillaBlock(BlockId.SEA_PICKLE)
                    .setProperties(BlockPropertyTypes.CLUSTER_COUNT, BlockPropertyTypes.DEAD_BIT)
                    .build();
        }
        if (BlockTypes.SEAGRASS == null) {
            BlockTypes.SEAGRASS = AllayBlockType
                    .builder(BlockSeagrassBehaviorImpl.class)
                    .vanillaBlock(BlockId.SEAGRASS)
                    .setProperties(BlockPropertyTypes.SEA_GRASS_TYPE)
                    .build();
        }
        if (BlockTypes.SHORT_DRY_GRASS == null) {
            BlockTypes.SHORT_DRY_GRASS = AllayBlockType
                    .builder(BlockShortDryGrassBehaviorImpl.class)
                    .vanillaBlock(BlockId.SHORT_DRY_GRASS)
                    .build();
        }
        if (BlockTypes.SHORT_GRASS == null) {
            BlockTypes.SHORT_GRASS = AllayBlockType
                    .builder(BlockShortGrassBehaviorImpl.class)
                    .vanillaBlock(BlockId.SHORT_GRASS)
                    .build();
        }
        if (BlockTypes.SHROOMLIGHT == null) {
            BlockTypes.SHROOMLIGHT = AllayBlockType
                    .builder(BlockShroomlightBehaviorImpl.class)
                    .vanillaBlock(BlockId.SHROOMLIGHT)
                    .build();
        }
        if (BlockTypes.SILVER_GLAZED_TERRACOTTA == null) {
            BlockTypes.SILVER_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.SILVER_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.SKELETON_SKULL == null) {
            BlockTypes.SKELETON_SKULL = AllayBlockType
                    .builder(BlockHeadBehaviorImpl.class)
                    .vanillaBlock(BlockId.SKELETON_SKULL)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.SLIME == null) {
            BlockTypes.SLIME = AllayBlockType
                    .builder(BlockSlimeBehaviorImpl.class)
                    .vanillaBlock(BlockId.SLIME)
                    .build();
        }
        if (BlockTypes.SMALL_AMETHYST_BUD == null) {
            BlockTypes.SMALL_AMETHYST_BUD = AllayBlockType
                    .builder(BlockSmallAmethystBudBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMALL_AMETHYST_BUD)
                    .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                    .build();
        }
        if (BlockTypes.SMALL_DRIPLEAF_BLOCK == null) {
            BlockTypes.SMALL_DRIPLEAF_BLOCK = AllayBlockType
                    .builder(BlockSmallDripleafBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMALL_DRIPLEAF_BLOCK)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.SMITHING_TABLE == null) {
            BlockTypes.SMITHING_TABLE = AllayBlockType
                    .builder(BlockSmithingTableBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMITHING_TABLE)
                    .build();
        }
        if (BlockTypes.SMOKER == null) {
            BlockTypes.SMOKER = AllayBlockType
                    .builder(BlockSmokerBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOKER)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.SMOOTH_BASALT == null) {
            BlockTypes.SMOOTH_BASALT = AllayBlockType
                    .builder(BlockSmoothBasaltBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_BASALT)
                    .build();
        }
        if (BlockTypes.SMOOTH_QUARTZ == null) {
            BlockTypes.SMOOTH_QUARTZ = AllayBlockType
                    .builder(BlockSmoothQuartzBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_QUARTZ)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB == null) {
            BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_QUARTZ_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SMOOTH_QUARTZ_SLAB == null) {
            BlockTypes.SMOOTH_QUARTZ_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_QUARTZ_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SMOOTH_QUARTZ_STAIRS == null) {
            BlockTypes.SMOOTH_QUARTZ_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_QUARTZ_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.SMOOTH_RED_SANDSTONE == null) {
            BlockTypes.SMOOTH_RED_SANDSTONE = AllayBlockType
                    .builder(BlockSandstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE)
                    .build();
        }
        if (BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB == null) {
            BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SMOOTH_RED_SANDSTONE_SLAB == null) {
            BlockTypes.SMOOTH_RED_SANDSTONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS == null) {
            BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.SMOOTH_SANDSTONE == null) {
            BlockTypes.SMOOTH_SANDSTONE = AllayBlockType
                    .builder(BlockSandstoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_SANDSTONE)
                    .build();
        }
        if (BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB == null) {
            BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_SANDSTONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SMOOTH_SANDSTONE_SLAB == null) {
            BlockTypes.SMOOTH_SANDSTONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_SANDSTONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SMOOTH_SANDSTONE_STAIRS == null) {
            BlockTypes.SMOOTH_SANDSTONE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_SANDSTONE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.SMOOTH_STONE == null) {
            BlockTypes.SMOOTH_STONE = AllayBlockType
                    .builder(BlockSmoothStoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_STONE)
                    .build();
        }
        if (BlockTypes.SMOOTH_STONE_DOUBLE_SLAB == null) {
            BlockTypes.SMOOTH_STONE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_STONE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SMOOTH_STONE_SLAB == null) {
            BlockTypes.SMOOTH_STONE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SMOOTH_STONE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SNIFFER_EGG == null) {
            BlockTypes.SNIFFER_EGG = AllayBlockType
                    .builder(BlockSnifferEggBehaviorImpl.class)
                    .vanillaBlock(BlockId.SNIFFER_EGG)
                    .setProperties(BlockPropertyTypes.CRACKED_STATE)
                    .build();
        }
        if (BlockTypes.SNOW == null) {
            BlockTypes.SNOW = AllayBlockType
                    .builder(BlockSnowBehaviorImpl.class)
                    .vanillaBlock(BlockId.SNOW)
                    .build();
        }
        if (BlockTypes.SNOW_LAYER == null) {
            BlockTypes.SNOW_LAYER = AllayBlockType
                    .builder(BlockSnowLayerBehaviorImpl.class)
                    .vanillaBlock(BlockId.SNOW_LAYER)
                    .setProperties(BlockPropertyTypes.COVERED_BIT, BlockPropertyTypes.HEIGHT)
                    .build();
        }
        if (BlockTypes.SOUL_CAMPFIRE == null) {
            BlockTypes.SOUL_CAMPFIRE = AllayBlockType
                    .builder(BlockCampfireBehaviorImpl.class)
                    .vanillaBlock(BlockId.SOUL_CAMPFIRE)
                    .setProperties(BlockPropertyTypes.EXTINGUISHED, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.SOUL_FIRE == null) {
            BlockTypes.SOUL_FIRE = AllayBlockType
                    .builder(BlockFireBehaviorImpl.class)
                    .vanillaBlock(BlockId.SOUL_FIRE)
                    .setProperties(BlockPropertyTypes.AGE_16)
                    .build();
        }
        if (BlockTypes.SOUL_LANTERN == null) {
            BlockTypes.SOUL_LANTERN = AllayBlockType
                    .builder(BlockLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.SOUL_LANTERN)
                    .setProperties(BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.SOUL_SAND == null) {
            BlockTypes.SOUL_SAND = AllayBlockType
                    .builder(BlockSoulSandBehaviorImpl.class)
                    .vanillaBlock(BlockId.SOUL_SAND)
                    .build();
        }
        if (BlockTypes.SOUL_SOIL == null) {
            BlockTypes.SOUL_SOIL = AllayBlockType
                    .builder(BlockSoulSoilBehaviorImpl.class)
                    .vanillaBlock(BlockId.SOUL_SOIL)
                    .build();
        }
        if (BlockTypes.SOUL_TORCH == null) {
            BlockTypes.SOUL_TORCH = AllayBlockType
                    .builder(BlockTorchBehaviorImpl.class)
                    .vanillaBlock(BlockId.SOUL_TORCH)
                    .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.SPONGE == null) {
            BlockTypes.SPONGE = AllayBlockType
                    .builder(BlockSpongeBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPONGE)
                    .build();
        }
        if (BlockTypes.SPORE_BLOSSOM == null) {
            BlockTypes.SPORE_BLOSSOM = AllayBlockType
                    .builder(BlockSporeBlossomBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPORE_BLOSSOM)
                    .build();
        }
        if (BlockTypes.SPRUCE_BUTTON == null) {
            BlockTypes.SPRUCE_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.SPRUCE_DOOR == null) {
            BlockTypes.SPRUCE_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.SPRUCE_DOUBLE_SLAB == null) {
            BlockTypes.SPRUCE_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SPRUCE_FENCE == null) {
            BlockTypes.SPRUCE_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_FENCE)
                    .build();
        }
        if (BlockTypes.SPRUCE_FENCE_GATE == null) {
            BlockTypes.SPRUCE_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.SPRUCE_HANGING_SIGN == null) {
            BlockTypes.SPRUCE_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.SPRUCE_LEAVES == null) {
            BlockTypes.SPRUCE_LEAVES = AllayBlockType
                    .builder(BlockLeavesBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_LEAVES)
                    .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                    .build();
        }
        if (BlockTypes.SPRUCE_LOG == null) {
            BlockTypes.SPRUCE_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.SPRUCE_PLANKS == null) {
            BlockTypes.SPRUCE_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_PLANKS)
                    .build();
        }
        if (BlockTypes.SPRUCE_PRESSURE_PLATE == null) {
            BlockTypes.SPRUCE_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.SPRUCE_SAPLING == null) {
            BlockTypes.SPRUCE_SAPLING = AllayBlockType
                    .builder(BlockSaplingBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_SAPLING)
                    .setProperties(BlockPropertyTypes.AGE_BIT)
                    .build();
        }
        if (BlockTypes.SPRUCE_SHELF == null) {
            BlockTypes.SPRUCE_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.SPRUCE_SLAB == null) {
            BlockTypes.SPRUCE_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.SPRUCE_STAIRS == null) {
            BlockTypes.SPRUCE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.SPRUCE_STANDING_SIGN == null) {
            BlockTypes.SPRUCE_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.SPRUCE_TRAPDOOR == null) {
            BlockTypes.SPRUCE_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.SPRUCE_WALL_SIGN == null) {
            BlockTypes.SPRUCE_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.SPRUCE_WOOD == null) {
            BlockTypes.SPRUCE_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.SPRUCE_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STANDING_BANNER == null) {
            BlockTypes.STANDING_BANNER = AllayBlockType
                    .builder(BlockStandingBannerBehaviorImpl.class)
                    .vanillaBlock(BlockId.STANDING_BANNER)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.STANDING_SIGN == null) {
            BlockTypes.STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.STICKY_PISTON == null) {
            BlockTypes.STICKY_PISTON = AllayBlockType
                    .builder(BlockStickyPistonBehaviorImpl.class)
                    .vanillaBlock(BlockId.STICKY_PISTON)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.STICKY_PISTON_ARM_COLLISION == null) {
            BlockTypes.STICKY_PISTON_ARM_COLLISION = AllayBlockType
                    .builder(BlockStickyPistonArmCollisionBehaviorImpl.class)
                    .vanillaBlock(BlockId.STICKY_PISTON_ARM_COLLISION)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.STONE == null) {
            BlockTypes.STONE = AllayBlockType
                    .builder(BlockStoneBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONE)
                    .build();
        }
        if (BlockTypes.STONE_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.STONE_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONE_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.STONE_BRICK_SLAB == null) {
            BlockTypes.STONE_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONE_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.STONE_BRICK_STAIRS == null) {
            BlockTypes.STONE_BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONE_BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.STONE_BRICK_WALL == null) {
            BlockTypes.STONE_BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONE_BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.STONE_BRICKS == null) {
            BlockTypes.STONE_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONE_BRICKS)
                    .build();
        }
        if (BlockTypes.STONE_BUTTON == null) {
            BlockTypes.STONE_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONE_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.STONE_PRESSURE_PLATE == null) {
            BlockTypes.STONE_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONE_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.STONE_STAIRS == null) {
            BlockTypes.STONE_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONE_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.STONECUTTER == null) {
            BlockTypes.STONECUTTER = AllayBlockType
                    .builder(BlockStonecutterBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONECUTTER)
                    .build();
        }
        if (BlockTypes.STONECUTTER_BLOCK == null) {
            BlockTypes.STONECUTTER_BLOCK = AllayBlockType
                    .builder(BlockStonecutterBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.STONECUTTER_BLOCK)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.STRIPPED_ACACIA_LOG == null) {
            BlockTypes.STRIPPED_ACACIA_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_ACACIA_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_ACACIA_WOOD == null) {
            BlockTypes.STRIPPED_ACACIA_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_ACACIA_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_BAMBOO_BLOCK == null) {
            BlockTypes.STRIPPED_BAMBOO_BLOCK = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_BAMBOO_BLOCK)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_BIRCH_LOG == null) {
            BlockTypes.STRIPPED_BIRCH_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_BIRCH_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_BIRCH_WOOD == null) {
            BlockTypes.STRIPPED_BIRCH_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_BIRCH_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_CHERRY_LOG == null) {
            BlockTypes.STRIPPED_CHERRY_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_CHERRY_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_CHERRY_WOOD == null) {
            BlockTypes.STRIPPED_CHERRY_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_CHERRY_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_CRIMSON_HYPHAE == null) {
            BlockTypes.STRIPPED_CRIMSON_HYPHAE = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_CRIMSON_HYPHAE)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_CRIMSON_STEM == null) {
            BlockTypes.STRIPPED_CRIMSON_STEM = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_CRIMSON_STEM)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_DARK_OAK_LOG == null) {
            BlockTypes.STRIPPED_DARK_OAK_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_DARK_OAK_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_DARK_OAK_WOOD == null) {
            BlockTypes.STRIPPED_DARK_OAK_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_DARK_OAK_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_JUNGLE_LOG == null) {
            BlockTypes.STRIPPED_JUNGLE_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_JUNGLE_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_JUNGLE_WOOD == null) {
            BlockTypes.STRIPPED_JUNGLE_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_JUNGLE_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_MANGROVE_LOG == null) {
            BlockTypes.STRIPPED_MANGROVE_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_MANGROVE_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_MANGROVE_WOOD == null) {
            BlockTypes.STRIPPED_MANGROVE_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_MANGROVE_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_OAK_LOG == null) {
            BlockTypes.STRIPPED_OAK_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_OAK_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_OAK_WOOD == null) {
            BlockTypes.STRIPPED_OAK_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_OAK_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_PALE_OAK_LOG == null) {
            BlockTypes.STRIPPED_PALE_OAK_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_PALE_OAK_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_PALE_OAK_WOOD == null) {
            BlockTypes.STRIPPED_PALE_OAK_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_PALE_OAK_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_SPRUCE_LOG == null) {
            BlockTypes.STRIPPED_SPRUCE_LOG = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_SPRUCE_LOG)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_SPRUCE_WOOD == null) {
            BlockTypes.STRIPPED_SPRUCE_WOOD = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_SPRUCE_WOOD)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_WARPED_HYPHAE == null) {
            BlockTypes.STRIPPED_WARPED_HYPHAE = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_WARPED_HYPHAE)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRIPPED_WARPED_STEM == null) {
            BlockTypes.STRIPPED_WARPED_STEM = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRIPPED_WARPED_STEM)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.STRUCTURE_BLOCK == null) {
            BlockTypes.STRUCTURE_BLOCK = AllayBlockType
                    .builder(BlockStructureBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRUCTURE_BLOCK)
                    .setProperties(BlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
                    .build();
        }
        if (BlockTypes.STRUCTURE_VOID == null) {
            BlockTypes.STRUCTURE_VOID = AllayBlockType
                    .builder(BlockStructureVoidBehaviorImpl.class)
                    .vanillaBlock(BlockId.STRUCTURE_VOID)
                    .build();
        }
        if (BlockTypes.SUNFLOWER == null) {
            BlockTypes.SUNFLOWER = AllayBlockType
                    .builder(BlockBigFlowerImpl.class)
                    .vanillaBlock(BlockId.SUNFLOWER)
                    .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.SUSPICIOUS_GRAVEL == null) {
            BlockTypes.SUSPICIOUS_GRAVEL = AllayBlockType
                    .builder(BlockSuspiciousGravelBehaviorImpl.class)
                    .vanillaBlock(BlockId.SUSPICIOUS_GRAVEL)
                    .setProperties(BlockPropertyTypes.BRUSHED_PROGRESS, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.SUSPICIOUS_SAND == null) {
            BlockTypes.SUSPICIOUS_SAND = AllayBlockType
                    .builder(BlockSuspiciousSandBehaviorImpl.class)
                    .vanillaBlock(BlockId.SUSPICIOUS_SAND)
                    .setProperties(BlockPropertyTypes.BRUSHED_PROGRESS, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.SWEET_BERRY_BUSH == null) {
            BlockTypes.SWEET_BERRY_BUSH = AllayBlockType
                    .builder(BlockSweetBerryBushBehaviorImpl.class)
                    .vanillaBlock(BlockId.SWEET_BERRY_BUSH)
                    .setProperties(BlockPropertyTypes.GROWTH)
                    .build();
        }
        if (BlockTypes.TALL_DRY_GRASS == null) {
            BlockTypes.TALL_DRY_GRASS = AllayBlockType
                    .builder(BlockTallDryGrassBehaviorImpl.class)
                    .vanillaBlock(BlockId.TALL_DRY_GRASS)
                    .build();
        }
        if (BlockTypes.TALL_GRASS == null) {
            BlockTypes.TALL_GRASS = AllayBlockType
                    .builder(BlockTallGrassBehaviorImpl.class)
                    .vanillaBlock(BlockId.TALL_GRASS)
                    .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.TARGET == null) {
            BlockTypes.TARGET = AllayBlockType
                    .builder(BlockTargetBehaviorImpl.class)
                    .vanillaBlock(BlockId.TARGET)
                    .build();
        }
        if (BlockTypes.TINTED_GLASS == null) {
            BlockTypes.TINTED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.TINTED_GLASS)
                    .build();
        }
        if (BlockTypes.TNT == null) {
            BlockTypes.TNT = AllayBlockType
                    .builder(BlockTntBehaviorImpl.class)
                    .vanillaBlock(BlockId.TNT)
                    .setProperties(BlockPropertyTypes.EXPLODE_BIT)
                    .build();
        }
        if (BlockTypes.TORCH == null) {
            BlockTypes.TORCH = AllayBlockType
                    .builder(BlockTorchBehaviorImpl.class)
                    .vanillaBlock(BlockId.TORCH)
                    .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.TORCHFLOWER == null) {
            BlockTypes.TORCHFLOWER = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.TORCHFLOWER)
                    .build();
        }
        if (BlockTypes.TORCHFLOWER_CROP == null) {
            BlockTypes.TORCHFLOWER_CROP = AllayBlockType
                    .builder(BlockTorchflowerCropBehaviorImpl.class)
                    .vanillaBlock(BlockId.TORCHFLOWER_CROP)
                    .setProperties(BlockPropertyTypes.GROWTH)
                    .build();
        }
        if (BlockTypes.TRAPDOOR == null) {
            BlockTypes.TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.TRAPPED_CHEST == null) {
            BlockTypes.TRAPPED_CHEST = AllayBlockType
                    .builder(BlockTrappedChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.TRAPPED_CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.TRIAL_SPAWNER == null) {
            BlockTypes.TRIAL_SPAWNER = AllayBlockType
                    .builder(BlockTrialSpawnerBehaviorImpl.class)
                    .vanillaBlock(BlockId.TRIAL_SPAWNER)
                    .setProperties(BlockPropertyTypes.OMINOUS, BlockPropertyTypes.TRIAL_SPAWNER_STATE)
                    .build();
        }
        if (BlockTypes.TRIP_WIRE == null) {
            BlockTypes.TRIP_WIRE = AllayBlockType
                    .builder(BlockTripWireBehaviorImpl.class)
                    .vanillaBlock(BlockId.TRIP_WIRE)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.DISARMED_BIT, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.SUSPENDED_BIT)
                    .build();
        }
        if (BlockTypes.TRIPWIRE_HOOK == null) {
            BlockTypes.TRIPWIRE_HOOK = AllayBlockType
                    .builder(BlockTripwireHookBehaviorImpl.class)
                    .vanillaBlock(BlockId.TRIPWIRE_HOOK)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.TUBE_CORAL == null) {
            BlockTypes.TUBE_CORAL = AllayBlockType
                    .builder(BlockCoralBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUBE_CORAL)
                    .build();
        }
        if (BlockTypes.TUBE_CORAL_BLOCK == null) {
            BlockTypes.TUBE_CORAL_BLOCK = AllayBlockType
                    .builder(BlockCoralBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUBE_CORAL_BLOCK)
                    .build();
        }
        if (BlockTypes.TUBE_CORAL_FAN == null) {
            BlockTypes.TUBE_CORAL_FAN = AllayBlockType
                    .builder(BlockCoralFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUBE_CORAL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                    .build();
        }
        if (BlockTypes.TUBE_CORAL_WALL_FAN == null) {
            BlockTypes.TUBE_CORAL_WALL_FAN = AllayBlockType
                    .builder(BlockCoralWallFanBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUBE_CORAL_WALL_FAN)
                    .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.TUFF == null) {
            BlockTypes.TUFF = AllayBlockType
                    .builder(BlockTuffBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUFF)
                    .build();
        }
        if (BlockTypes.TUFF_BRICK_DOUBLE_SLAB == null) {
            BlockTypes.TUFF_BRICK_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUFF_BRICK_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.TUFF_BRICK_SLAB == null) {
            BlockTypes.TUFF_BRICK_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUFF_BRICK_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.TUFF_BRICK_STAIRS == null) {
            BlockTypes.TUFF_BRICK_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUFF_BRICK_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.TUFF_BRICK_WALL == null) {
            BlockTypes.TUFF_BRICK_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUFF_BRICK_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.TUFF_BRICKS == null) {
            BlockTypes.TUFF_BRICKS = AllayBlockType
                    .builder(BlockBricksBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUFF_BRICKS)
                    .build();
        }
        if (BlockTypes.TUFF_DOUBLE_SLAB == null) {
            BlockTypes.TUFF_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUFF_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.TUFF_SLAB == null) {
            BlockTypes.TUFF_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUFF_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.TUFF_STAIRS == null) {
            BlockTypes.TUFF_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUFF_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.TUFF_WALL == null) {
            BlockTypes.TUFF_WALL = AllayBlockType
                    .builder(BlockWallBehaviorImpl.class)
                    .vanillaBlock(BlockId.TUFF_WALL)
                    .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                    .build();
        }
        if (BlockTypes.TURTLE_EGG == null) {
            BlockTypes.TURTLE_EGG = AllayBlockType
                    .builder(BlockTurtleEggBehaviorImpl.class)
                    .vanillaBlock(BlockId.TURTLE_EGG)
                    .setProperties(BlockPropertyTypes.CRACKED_STATE, BlockPropertyTypes.TURTLE_EGG_COUNT)
                    .build();
        }
        if (BlockTypes.TWISTING_VINES == null) {
            BlockTypes.TWISTING_VINES = AllayBlockType
                    .builder(BlockTwistingVinesBehaviorImpl.class)
                    .vanillaBlock(BlockId.TWISTING_VINES)
                    .setProperties(BlockPropertyTypes.TWISTING_VINES_AGE)
                    .build();
        }
        if (BlockTypes.UNDERWATER_TNT == null) {
            BlockTypes.UNDERWATER_TNT = AllayBlockType
                    .builder(BlockUnderwaterTntBehaviorImpl.class)
                    .vanillaBlock(BlockId.UNDERWATER_TNT)
                    .setProperties(BlockPropertyTypes.EXPLODE_BIT)
                    .build();
        }
        if (BlockTypes.UNDERWATER_TORCH == null) {
            BlockTypes.UNDERWATER_TORCH = AllayBlockType
                    .builder(BlockTorchBehaviorImpl.class)
                    .vanillaBlock(BlockId.UNDERWATER_TORCH)
                    .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.UNDYED_SHULKER_BOX == null) {
            BlockTypes.UNDYED_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.UNDYED_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.UNKNOWN == null) {
            BlockTypes.UNKNOWN = AllayBlockType
                    .builder(BlockUnknownBehaviorImpl.class)
                    .vanillaBlock(BlockId.UNKNOWN)
                    .build();
        }
        if (BlockTypes.UNLIT_REDSTONE_TORCH == null) {
            BlockTypes.UNLIT_REDSTONE_TORCH = AllayBlockType
                    .builder(BlockTorchBehaviorImpl.class)
                    .vanillaBlock(BlockId.UNLIT_REDSTONE_TORCH)
                    .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.UNPOWERED_COMPARATOR == null) {
            BlockTypes.UNPOWERED_COMPARATOR = AllayBlockType
                    .builder(BlockComparatorBehaviorImpl.class)
                    .vanillaBlock(BlockId.UNPOWERED_COMPARATOR)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                    .build();
        }
        if (BlockTypes.UNPOWERED_REPEATER == null) {
            BlockTypes.UNPOWERED_REPEATER = AllayBlockType
                    .builder(BlockRepeaterBehaviorImpl.class)
                    .vanillaBlock(BlockId.UNPOWERED_REPEATER)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                    .build();
        }
        if (BlockTypes.VAULT == null) {
            BlockTypes.VAULT = AllayBlockType
                    .builder(BlockVaultBehaviorImpl.class)
                    .vanillaBlock(BlockId.VAULT)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OMINOUS, BlockPropertyTypes.VAULT_STATE)
                    .build();
        }
        if (BlockTypes.VERDANT_FROGLIGHT == null) {
            BlockTypes.VERDANT_FROGLIGHT = AllayBlockType
                    .builder(BlockVerdantFroglightBehaviorImpl.class)
                    .vanillaBlock(BlockId.VERDANT_FROGLIGHT)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.VINE == null) {
            BlockTypes.VINE = AllayBlockType
                    .builder(BlockVineBehaviorImpl.class)
                    .vanillaBlock(BlockId.VINE)
                    .setProperties(BlockPropertyTypes.VINE_DIRECTION_BITS)
                    .build();
        }
        if (BlockTypes.WALL_BANNER == null) {
            BlockTypes.WALL_BANNER = AllayBlockType
                    .builder(BlockWallBannerBehaviorImpl.class)
                    .vanillaBlock(BlockId.WALL_BANNER)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.WALL_SIGN == null) {
            BlockTypes.WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.WARPED_BUTTON == null) {
            BlockTypes.WARPED_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.WARPED_DOOR == null) {
            BlockTypes.WARPED_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.WARPED_DOUBLE_SLAB == null) {
            BlockTypes.WARPED_DOUBLE_SLAB = AllayBlockType
                    .builder(BlockDoubleSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_DOUBLE_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WARPED_FENCE == null) {
            BlockTypes.WARPED_FENCE = AllayBlockType
                    .builder(BlockFenceBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_FENCE)
                    .build();
        }
        if (BlockTypes.WARPED_FENCE_GATE == null) {
            BlockTypes.WARPED_FENCE_GATE = AllayBlockType
                    .builder(BlockFenceGateBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_FENCE_GATE)
                    .setProperties(BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                    .build();
        }
        if (BlockTypes.WARPED_FUNGUS == null) {
            BlockTypes.WARPED_FUNGUS = AllayBlockType
                    .builder(BlockWarpedFungusBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_FUNGUS)
                    .build();
        }
        if (BlockTypes.WARPED_HANGING_SIGN == null) {
            BlockTypes.WARPED_HANGING_SIGN = AllayBlockType
                    .builder(BlockHangingSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_HANGING_SIGN)
                    .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.WARPED_HYPHAE == null) {
            BlockTypes.WARPED_HYPHAE = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_HYPHAE)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.WARPED_NYLIUM == null) {
            BlockTypes.WARPED_NYLIUM = AllayBlockType
                    .builder(BlockWarpedNyliumBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_NYLIUM)
                    .build();
        }
        if (BlockTypes.WARPED_PLANKS == null) {
            BlockTypes.WARPED_PLANKS = AllayBlockType
                    .builder(BlockPlanksBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_PLANKS)
                    .build();
        }
        if (BlockTypes.WARPED_PRESSURE_PLATE == null) {
            BlockTypes.WARPED_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.WARPED_ROOTS == null) {
            BlockTypes.WARPED_ROOTS = AllayBlockType
                    .builder(BlockWarpedRootsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_ROOTS)
                    .build();
        }
        if (BlockTypes.WARPED_SHELF == null) {
            BlockTypes.WARPED_SHELF = AllayBlockType
                    .builder(BlockShelfBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_SHELF)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.POWERED_SHELF_TYPE)
                    .build();
        }
        if (BlockTypes.WARPED_SLAB == null) {
            BlockTypes.WARPED_SLAB = AllayBlockType
                    .builder(BlockSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WARPED_STAIRS == null) {
            BlockTypes.WARPED_STAIRS = AllayBlockType
                    .builder(BlockStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.WARPED_STANDING_SIGN == null) {
            BlockTypes.WARPED_STANDING_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_STANDING_SIGN)
                    .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                    .build();
        }
        if (BlockTypes.WARPED_STEM == null) {
            BlockTypes.WARPED_STEM = AllayBlockType
                    .builder(BlockWoodBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_STEM)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.WARPED_TRAPDOOR == null) {
            BlockTypes.WARPED_TRAPDOOR = AllayBlockType
                    .builder(BlockTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.WARPED_WALL_SIGN == null) {
            BlockTypes.WARPED_WALL_SIGN = AllayBlockType
                    .builder(BlockSignBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_WALL_SIGN)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.WARPED_WART_BLOCK == null) {
            BlockTypes.WARPED_WART_BLOCK = AllayBlockType
                    .builder(BlockWarpedWartBlockBehaviorImpl.class)
                    .vanillaBlock(BlockId.WARPED_WART_BLOCK)
                    .build();
        }
        if (BlockTypes.WATER == null) {
            BlockTypes.WATER = AllayBlockType
                    .builder(BlockLiquidBehaviorImpl.class)
                    .vanillaBlock(BlockId.WATER)
                    .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                    .build();
        }
        if (BlockTypes.WATERLILY == null) {
            BlockTypes.WATERLILY = AllayBlockType
                    .builder(BlockWaterlilyBehaviorImpl.class)
                    .vanillaBlock(BlockId.WATERLILY)
                    .build();
        }
        if (BlockTypes.WAXED_CHISELED_COPPER == null) {
            BlockTypes.WAXED_CHISELED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_CHISELED_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_COPPER == null) {
            BlockTypes.WAXED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_COPPER_BARS == null) {
            BlockTypes.WAXED_COPPER_BARS = AllayBlockType
                    .builder(BlockCopperBarsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_COPPER_BARS)
                    .build();
        }
        if (BlockTypes.WAXED_COPPER_BULB == null) {
            BlockTypes.WAXED_COPPER_BULB = AllayBlockType
                    .builder(BlockCopperBulbBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_COPPER_BULB)
                    .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_COPPER_CHAIN == null) {
            BlockTypes.WAXED_COPPER_CHAIN = AllayBlockType
                    .builder(BlockCopperChainBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_COPPER_CHAIN)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.WAXED_COPPER_CHEST == null) {
            BlockTypes.WAXED_COPPER_CHEST = AllayBlockType
                    .builder(BlockWaxedCopperChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_COPPER_CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_COPPER_DOOR == null) {
            BlockTypes.WAXED_COPPER_DOOR = AllayBlockType
                    .builder(BlockCopperDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_COPPER_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_COPPER_GOLEM_STATUE == null) {
            BlockTypes.WAXED_COPPER_GOLEM_STATUE = AllayBlockType
                    .builder(BlockCopperGolemStatueBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_COPPER_GOLEM_STATUE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_COPPER_GRATE == null) {
            BlockTypes.WAXED_COPPER_GRATE = AllayBlockType
                    .builder(BlockCopperGrateBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_COPPER_GRATE)
                    .build();
        }
        if (BlockTypes.WAXED_COPPER_LANTERN == null) {
            BlockTypes.WAXED_COPPER_LANTERN = AllayBlockType
                    .builder(BlockCopperLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_COPPER_LANTERN)
                    .setProperties(BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.WAXED_COPPER_TRAPDOOR == null) {
            BlockTypes.WAXED_COPPER_TRAPDOOR = AllayBlockType
                    .builder(BlockCopperTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_COPPER_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_CUT_COPPER == null) {
            BlockTypes.WAXED_CUT_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_CUT_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_CUT_COPPER_SLAB == null) {
            BlockTypes.WAXED_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WAXED_CUT_COPPER_STAIRS == null) {
            BlockTypes.WAXED_CUT_COPPER_STAIRS = AllayBlockType
                    .builder(BlockCopperStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_CUT_COPPER_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB == null) {
            BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockDoubleCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_DOUBLE_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_CHISELED_COPPER == null) {
            BlockTypes.WAXED_EXPOSED_CHISELED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_CHISELED_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_COPPER == null) {
            BlockTypes.WAXED_EXPOSED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_COPPER_BARS == null) {
            BlockTypes.WAXED_EXPOSED_COPPER_BARS = AllayBlockType
                    .builder(BlockCopperBarsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_BARS)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_COPPER_BULB == null) {
            BlockTypes.WAXED_EXPOSED_COPPER_BULB = AllayBlockType
                    .builder(BlockCopperBulbBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_BULB)
                    .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_COPPER_CHAIN == null) {
            BlockTypes.WAXED_EXPOSED_COPPER_CHAIN = AllayBlockType
                    .builder(BlockCopperChainBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_CHAIN)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_COPPER_CHEST == null) {
            BlockTypes.WAXED_EXPOSED_COPPER_CHEST = AllayBlockType
                    .builder(BlockWaxedExposedCopperChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_COPPER_DOOR == null) {
            BlockTypes.WAXED_EXPOSED_COPPER_DOOR = AllayBlockType
                    .builder(BlockCopperDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_COPPER_GOLEM_STATUE == null) {
            BlockTypes.WAXED_EXPOSED_COPPER_GOLEM_STATUE = AllayBlockType
                    .builder(BlockCopperGolemStatueBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_GOLEM_STATUE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_COPPER_GRATE == null) {
            BlockTypes.WAXED_EXPOSED_COPPER_GRATE = AllayBlockType
                    .builder(BlockCopperGrateBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_GRATE)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_COPPER_LANTERN == null) {
            BlockTypes.WAXED_EXPOSED_COPPER_LANTERN = AllayBlockType
                    .builder(BlockCopperLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_LANTERN)
                    .setProperties(BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR == null) {
            BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR = AllayBlockType
                    .builder(BlockCopperTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER == null) {
            BlockTypes.WAXED_EXPOSED_CUT_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB == null) {
            BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS == null) {
            BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = AllayBlockType
                    .builder(BlockCopperStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB == null) {
            BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockDoubleCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WAXED_EXPOSED_LIGHTNING_ROD == null) {
            BlockTypes.WAXED_EXPOSED_LIGHTNING_ROD = AllayBlockType
                    .builder(BlockLightningRodBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_EXPOSED_LIGHTNING_ROD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_LIGHTNING_ROD == null) {
            BlockTypes.WAXED_LIGHTNING_ROD = AllayBlockType
                    .builder(BlockLightningRodBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_LIGHTNING_ROD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER == null) {
            BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_CHISELED_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_COPPER == null) {
            BlockTypes.WAXED_OXIDIZED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_COPPER_BARS == null) {
            BlockTypes.WAXED_OXIDIZED_COPPER_BARS = AllayBlockType
                    .builder(BlockCopperBarsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_BARS)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_COPPER_BULB == null) {
            BlockTypes.WAXED_OXIDIZED_COPPER_BULB = AllayBlockType
                    .builder(BlockCopperBulbBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_BULB)
                    .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_COPPER_CHAIN == null) {
            BlockTypes.WAXED_OXIDIZED_COPPER_CHAIN = AllayBlockType
                    .builder(BlockCopperChainBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_CHAIN)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_COPPER_CHEST == null) {
            BlockTypes.WAXED_OXIDIZED_COPPER_CHEST = AllayBlockType
                    .builder(BlockWaxedOxidizedCopperChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_COPPER_DOOR == null) {
            BlockTypes.WAXED_OXIDIZED_COPPER_DOOR = AllayBlockType
                    .builder(BlockCopperDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_COPPER_GOLEM_STATUE == null) {
            BlockTypes.WAXED_OXIDIZED_COPPER_GOLEM_STATUE = AllayBlockType
                    .builder(BlockCopperGolemStatueBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_GOLEM_STATUE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_COPPER_GRATE == null) {
            BlockTypes.WAXED_OXIDIZED_COPPER_GRATE = AllayBlockType
                    .builder(BlockCopperGrateBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_GRATE)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_COPPER_LANTERN == null) {
            BlockTypes.WAXED_OXIDIZED_COPPER_LANTERN = AllayBlockType
                    .builder(BlockCopperLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_LANTERN)
                    .setProperties(BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR == null) {
            BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR = AllayBlockType
                    .builder(BlockCopperTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER == null) {
            BlockTypes.WAXED_OXIDIZED_CUT_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB == null) {
            BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS == null) {
            BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = AllayBlockType
                    .builder(BlockCopperStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB == null) {
            BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockDoubleCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WAXED_OXIDIZED_LIGHTNING_ROD == null) {
            BlockTypes.WAXED_OXIDIZED_LIGHTNING_ROD = AllayBlockType
                    .builder(BlockLightningRodBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_OXIDIZED_LIGHTNING_ROD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_CHISELED_COPPER == null) {
            BlockTypes.WAXED_WEATHERED_CHISELED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_CHISELED_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_COPPER == null) {
            BlockTypes.WAXED_WEATHERED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_COPPER_BARS == null) {
            BlockTypes.WAXED_WEATHERED_COPPER_BARS = AllayBlockType
                    .builder(BlockCopperBarsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_BARS)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_COPPER_BULB == null) {
            BlockTypes.WAXED_WEATHERED_COPPER_BULB = AllayBlockType
                    .builder(BlockCopperBulbBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_BULB)
                    .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_COPPER_CHAIN == null) {
            BlockTypes.WAXED_WEATHERED_COPPER_CHAIN = AllayBlockType
                    .builder(BlockCopperChainBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_CHAIN)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_COPPER_CHEST == null) {
            BlockTypes.WAXED_WEATHERED_COPPER_CHEST = AllayBlockType
                    .builder(BlockWaxedWeatheredCopperChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_COPPER_DOOR == null) {
            BlockTypes.WAXED_WEATHERED_COPPER_DOOR = AllayBlockType
                    .builder(BlockCopperDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_COPPER_GOLEM_STATUE == null) {
            BlockTypes.WAXED_WEATHERED_COPPER_GOLEM_STATUE = AllayBlockType
                    .builder(BlockCopperGolemStatueBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_GOLEM_STATUE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_COPPER_GRATE == null) {
            BlockTypes.WAXED_WEATHERED_COPPER_GRATE = AllayBlockType
                    .builder(BlockCopperGrateBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_GRATE)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_COPPER_LANTERN == null) {
            BlockTypes.WAXED_WEATHERED_COPPER_LANTERN = AllayBlockType
                    .builder(BlockCopperLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_LANTERN)
                    .setProperties(BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR == null) {
            BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR = AllayBlockType
                    .builder(BlockCopperTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER == null) {
            BlockTypes.WAXED_WEATHERED_CUT_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB == null) {
            BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS == null) {
            BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = AllayBlockType
                    .builder(BlockCopperStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB == null) {
            BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockDoubleCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WAXED_WEATHERED_LIGHTNING_ROD == null) {
            BlockTypes.WAXED_WEATHERED_LIGHTNING_ROD = AllayBlockType
                    .builder(BlockLightningRodBehaviorImpl.class)
                    .vanillaBlock(BlockId.WAXED_WEATHERED_LIGHTNING_ROD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.WEATHERED_CHISELED_COPPER == null) {
            BlockTypes.WEATHERED_CHISELED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_CHISELED_COPPER)
                    .build();
        }
        if (BlockTypes.WEATHERED_COPPER == null) {
            BlockTypes.WEATHERED_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_COPPER)
                    .build();
        }
        if (BlockTypes.WEATHERED_COPPER_BARS == null) {
            BlockTypes.WEATHERED_COPPER_BARS = AllayBlockType
                    .builder(BlockCopperBarsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_COPPER_BARS)
                    .build();
        }
        if (BlockTypes.WEATHERED_COPPER_BULB == null) {
            BlockTypes.WEATHERED_COPPER_BULB = AllayBlockType
                    .builder(BlockCopperBulbBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_COPPER_BULB)
                    .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.WEATHERED_COPPER_CHAIN == null) {
            BlockTypes.WEATHERED_COPPER_CHAIN = AllayBlockType
                    .builder(BlockCopperChainBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_COPPER_CHAIN)
                    .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                    .build();
        }
        if (BlockTypes.WEATHERED_COPPER_CHEST == null) {
            BlockTypes.WEATHERED_COPPER_CHEST = AllayBlockType
                    .builder(BlockWeatheredCopperChestBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_COPPER_CHEST)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WEATHERED_COPPER_DOOR == null) {
            BlockTypes.WEATHERED_COPPER_DOOR = AllayBlockType
                    .builder(BlockCopperDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_COPPER_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.WEATHERED_COPPER_GOLEM_STATUE == null) {
            BlockTypes.WEATHERED_COPPER_GOLEM_STATUE = AllayBlockType
                    .builder(BlockCopperGolemStatueBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_COPPER_GOLEM_STATUE)
                    .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WEATHERED_COPPER_GRATE == null) {
            BlockTypes.WEATHERED_COPPER_GRATE = AllayBlockType
                    .builder(BlockCopperGrateBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_COPPER_GRATE)
                    .build();
        }
        if (BlockTypes.WEATHERED_COPPER_LANTERN == null) {
            BlockTypes.WEATHERED_COPPER_LANTERN = AllayBlockType
                    .builder(BlockCopperLanternBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_COPPER_LANTERN)
                    .setProperties(BlockPropertyTypes.HANGING)
                    .build();
        }
        if (BlockTypes.WEATHERED_COPPER_TRAPDOOR == null) {
            BlockTypes.WEATHERED_COPPER_TRAPDOOR = AllayBlockType
                    .builder(BlockCopperTrapdoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_COPPER_TRAPDOOR)
                    .setProperties(BlockPropertyTypes.DIRECTION_4, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                    .build();
        }
        if (BlockTypes.WEATHERED_CUT_COPPER == null) {
            BlockTypes.WEATHERED_CUT_COPPER = AllayBlockType
                    .builder(BlockCopperBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_CUT_COPPER)
                    .build();
        }
        if (BlockTypes.WEATHERED_CUT_COPPER_SLAB == null) {
            BlockTypes.WEATHERED_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WEATHERED_CUT_COPPER_STAIRS == null) {
            BlockTypes.WEATHERED_CUT_COPPER_STAIRS = AllayBlockType
                    .builder(BlockCopperStairsBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_CUT_COPPER_STAIRS)
                    .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                    .build();
        }
        if (BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB == null) {
            BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB = AllayBlockType
                    .builder(BlockDoubleCopperSlabBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                    .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                    .build();
        }
        if (BlockTypes.WEATHERED_LIGHTNING_ROD == null) {
            BlockTypes.WEATHERED_LIGHTNING_ROD = AllayBlockType
                    .builder(BlockLightningRodBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEATHERED_LIGHTNING_ROD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                    .build();
        }
        if (BlockTypes.WEB == null) {
            BlockTypes.WEB = AllayBlockType
                    .builder(BlockWebBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEB)
                    .build();
        }
        if (BlockTypes.WEEPING_VINES == null) {
            BlockTypes.WEEPING_VINES = AllayBlockType
                    .builder(BlockWeepingVinesBehaviorImpl.class)
                    .vanillaBlock(BlockId.WEEPING_VINES)
                    .setProperties(BlockPropertyTypes.WEEPING_VINES_AGE)
                    .build();
        }
        if (BlockTypes.WET_SPONGE == null) {
            BlockTypes.WET_SPONGE = AllayBlockType
                    .builder(BlockWetSpongeBehaviorImpl.class)
                    .vanillaBlock(BlockId.WET_SPONGE)
                    .build();
        }
        if (BlockTypes.WHEAT == null) {
            BlockTypes.WHEAT = AllayBlockType
                    .builder(BlockWheatBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHEAT)
                    .setProperties(BlockPropertyTypes.GROWTH)
                    .build();
        }
        if (BlockTypes.WHITE_CANDLE == null) {
            BlockTypes.WHITE_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.WHITE_CANDLE_CAKE == null) {
            BlockTypes.WHITE_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.WHITE_CARPET == null) {
            BlockTypes.WHITE_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_CARPET)
                    .build();
        }
        if (BlockTypes.WHITE_CONCRETE == null) {
            BlockTypes.WHITE_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_CONCRETE)
                    .build();
        }
        if (BlockTypes.WHITE_CONCRETE_POWDER == null) {
            BlockTypes.WHITE_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.WHITE_GLAZED_TERRACOTTA == null) {
            BlockTypes.WHITE_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.WHITE_SHULKER_BOX == null) {
            BlockTypes.WHITE_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.WHITE_STAINED_GLASS == null) {
            BlockTypes.WHITE_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.WHITE_STAINED_GLASS_PANE == null) {
            BlockTypes.WHITE_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.WHITE_TERRACOTTA == null) {
            BlockTypes.WHITE_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.WHITE_TULIP == null) {
            BlockTypes.WHITE_TULIP = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.WHITE_TULIP)
                    .build();
        }
        if (BlockTypes.WHITE_WOOL == null) {
            BlockTypes.WHITE_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.WHITE_WOOL)
                    .build();
        }
        if (BlockTypes.WILDFLOWERS == null) {
            BlockTypes.WILDFLOWERS = AllayBlockType
                    .builder(BlockPlantPileImpl.class)
                    .vanillaBlock(BlockId.WILDFLOWERS)
                    .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                    .build();
        }
        if (BlockTypes.WITHER_ROSE == null) {
            BlockTypes.WITHER_ROSE = AllayBlockType
                    .builder(BlockSmallFlowerImpl.class)
                    .vanillaBlock(BlockId.WITHER_ROSE)
                    .build();
        }
        if (BlockTypes.WITHER_SKELETON_SKULL == null) {
            BlockTypes.WITHER_SKELETON_SKULL = AllayBlockType
                    .builder(BlockHeadBehaviorImpl.class)
                    .vanillaBlock(BlockId.WITHER_SKELETON_SKULL)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.WOODEN_BUTTON == null) {
            BlockTypes.WOODEN_BUTTON = AllayBlockType
                    .builder(BlockButtonBehaviorImpl.class)
                    .vanillaBlock(BlockId.WOODEN_BUTTON)
                    .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.WOODEN_DOOR == null) {
            BlockTypes.WOODEN_DOOR = AllayBlockType
                    .builder(BlockDoorBehaviorImpl.class)
                    .vanillaBlock(BlockId.WOODEN_DOOR)
                    .setProperties(BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                    .build();
        }
        if (BlockTypes.WOODEN_PRESSURE_PLATE == null) {
            BlockTypes.WOODEN_PRESSURE_PLATE = AllayBlockType
                    .builder(BlockPressurePlateBehaviorImpl.class)
                    .vanillaBlock(BlockId.WOODEN_PRESSURE_PLATE)
                    .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                    .build();
        }
        if (BlockTypes.YELLOW_CANDLE == null) {
            BlockTypes.YELLOW_CANDLE = AllayBlockType
                    .builder(BlockCandleBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_CANDLE)
                    .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.YELLOW_CANDLE_CAKE == null) {
            BlockTypes.YELLOW_CANDLE_CAKE = AllayBlockType
                    .builder(BlockCandleCakeBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_CANDLE_CAKE)
                    .setProperties(BlockPropertyTypes.LIT)
                    .build();
        }
        if (BlockTypes.YELLOW_CARPET == null) {
            BlockTypes.YELLOW_CARPET = AllayBlockType
                    .builder(BlockCarpetBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_CARPET)
                    .build();
        }
        if (BlockTypes.YELLOW_CONCRETE == null) {
            BlockTypes.YELLOW_CONCRETE = AllayBlockType
                    .builder(BlockConcreteBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_CONCRETE)
                    .build();
        }
        if (BlockTypes.YELLOW_CONCRETE_POWDER == null) {
            BlockTypes.YELLOW_CONCRETE_POWDER = AllayBlockType
                    .builder(BlockConcretePowderBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_CONCRETE_POWDER)
                    .build();
        }
        if (BlockTypes.YELLOW_GLAZED_TERRACOTTA == null) {
            BlockTypes.YELLOW_GLAZED_TERRACOTTA = AllayBlockType
                    .builder(BlockGlazedTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_GLAZED_TERRACOTTA)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
        if (BlockTypes.YELLOW_SHULKER_BOX == null) {
            BlockTypes.YELLOW_SHULKER_BOX = AllayBlockType
                    .builder(BlockShulkerBoxBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_SHULKER_BOX)
                    .build();
        }
        if (BlockTypes.YELLOW_STAINED_GLASS == null) {
            BlockTypes.YELLOW_STAINED_GLASS = AllayBlockType
                    .builder(BlockGlassBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_STAINED_GLASS)
                    .build();
        }
        if (BlockTypes.YELLOW_STAINED_GLASS_PANE == null) {
            BlockTypes.YELLOW_STAINED_GLASS_PANE = AllayBlockType
                    .builder(BlockGlassPaneBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_STAINED_GLASS_PANE)
                    .build();
        }
        if (BlockTypes.YELLOW_TERRACOTTA == null) {
            BlockTypes.YELLOW_TERRACOTTA = AllayBlockType
                    .builder(BlockTerracottaBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_TERRACOTTA)
                    .build();
        }
        if (BlockTypes.YELLOW_WOOL == null) {
            BlockTypes.YELLOW_WOOL = AllayBlockType
                    .builder(BlockWoolBehaviorImpl.class)
                    .vanillaBlock(BlockId.YELLOW_WOOL)
                    .build();
        }
        if (BlockTypes.ZOMBIE_HEAD == null) {
            BlockTypes.ZOMBIE_HEAD = AllayBlockType
                    .builder(BlockHeadBehaviorImpl.class)
                    .vanillaBlock(BlockId.ZOMBIE_HEAD)
                    .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                    .build();
        }
    }
}
