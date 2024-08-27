package org.allaymc.server.block.type;

import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.interfaces.*;
import org.allaymc.api.block.interfaces.amethystbud.BlockLargeAmethystBudBehavior;
import org.allaymc.api.block.interfaces.amethystbud.BlockMediumAmethystBudBehavior;
import org.allaymc.api.block.interfaces.amethystbud.BlockSmallAmethystBudBehavior;
import org.allaymc.api.block.interfaces.anvil.BlockAnvilBehavior;
import org.allaymc.api.block.interfaces.anvil.BlockChippedAnvilBehavior;
import org.allaymc.api.block.interfaces.anvil.BlockDamagedAnvilBehavior;
import org.allaymc.api.block.interfaces.anvil.BlockDeprecatedAnvilBehavior;
import org.allaymc.api.block.interfaces.bricks.*;
import org.allaymc.api.block.interfaces.button.*;
import org.allaymc.api.block.interfaces.candle.*;
import org.allaymc.api.block.interfaces.candlecake.*;
import org.allaymc.api.block.interfaces.carpet.*;
import org.allaymc.api.block.interfaces.concrete.*;
import org.allaymc.api.block.interfaces.concretepowder.*;
import org.allaymc.api.block.interfaces.copper.*;
import org.allaymc.api.block.interfaces.coral.*;
import org.allaymc.api.block.interfaces.coralblock.*;
import org.allaymc.api.block.interfaces.coralfan.*;
import org.allaymc.api.block.interfaces.coralwallfan.*;
import org.allaymc.api.block.interfaces.dirt.BlockCoarseDirtBehavior;
import org.allaymc.api.block.interfaces.dirt.BlockDirtBehavior;
import org.allaymc.api.block.interfaces.door.*;
import org.allaymc.api.block.interfaces.element.*;
import org.allaymc.api.block.interfaces.fence.*;
import org.allaymc.api.block.interfaces.fencegate.*;
import org.allaymc.api.block.interfaces.glass.BlockGlassBehavior;
import org.allaymc.api.block.interfaces.glass.BlockHardGlassBehavior;
import org.allaymc.api.block.interfaces.glass.BlockTintedGlassBehavior;
import org.allaymc.api.block.interfaces.glasspane.BlockGlassPaneBehavior;
import org.allaymc.api.block.interfaces.glasspane.BlockHardGlassPaneBehavior;
import org.allaymc.api.block.interfaces.hangingsign.*;
import org.allaymc.api.block.interfaces.leaves.*;
import org.allaymc.api.block.interfaces.lightblock.*;
import org.allaymc.api.block.interfaces.liquid.BlockFlowingLavaBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockFlowingWaterBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockLavaBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockWaterBehavior;
import org.allaymc.api.block.interfaces.log.*;
import org.allaymc.api.block.interfaces.piston.BlockPistonArmCollisionBehavior;
import org.allaymc.api.block.interfaces.piston.BlockPistonBehavior;
import org.allaymc.api.block.interfaces.piston.BlockStickyPistonArmCollisionBehavior;
import org.allaymc.api.block.interfaces.piston.BlockStickyPistonBehavior;
import org.allaymc.api.block.interfaces.planks.*;
import org.allaymc.api.block.interfaces.sand.BlockRedSandBehavior;
import org.allaymc.api.block.interfaces.sand.BlockSandBehavior;
import org.allaymc.api.block.interfaces.sand.BlockSoulSandBehavior;
import org.allaymc.api.block.interfaces.sand.BlockSuspiciousSandBehavior;
import org.allaymc.api.block.interfaces.sandstone.*;
import org.allaymc.api.block.interfaces.sapling.*;
import org.allaymc.api.block.interfaces.shulkerbox.*;
import org.allaymc.api.block.interfaces.slab.*;
import org.allaymc.api.block.interfaces.stainedglass.*;
import org.allaymc.api.block.interfaces.stainedglasspane.*;
import org.allaymc.api.block.interfaces.stairs.*;
import org.allaymc.api.block.interfaces.standingsign.*;
import org.allaymc.api.block.interfaces.terracotta.*;
import org.allaymc.api.block.interfaces.torch.*;
import org.allaymc.api.block.interfaces.trapdoor.*;
import org.allaymc.api.block.interfaces.wall.*;
import org.allaymc.api.block.interfaces.wallsign.*;
import org.allaymc.api.block.interfaces.wood.*;
import org.allaymc.api.block.interfaces.wool.*;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public final class BlockTypeDefaultInitializer {
    public static void initAcaciaButton() {
        if (BlockTypes.ACACIA_BUTTON != null) return;
        BlockTypes.ACACIA_BUTTON = BlockTypeBuilder
                .builder(BlockAcaciaButtonBehavior.class)
                .vanillaBlock(BlockId.ACACIA_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initAcaciaDoor() {
        if (BlockTypes.ACACIA_DOOR != null) return;
        BlockTypes.ACACIA_DOOR = BlockTypeBuilder
                .builder(BlockAcaciaDoorBehavior.class)
                .vanillaBlock(BlockId.ACACIA_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initAcaciaDoubleSlab() {
        if (BlockTypes.ACACIA_DOUBLE_SLAB != null) return;
        BlockTypes.ACACIA_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockAcaciaDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.ACACIA_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAcaciaFence() {
        if (BlockTypes.ACACIA_FENCE != null) return;
        BlockTypes.ACACIA_FENCE = BlockTypeBuilder
                .builder(BlockAcaciaFenceBehavior.class)
                .vanillaBlock(BlockId.ACACIA_FENCE)
                .build();
    }

    public static void initAcaciaFenceGate() {
        if (BlockTypes.ACACIA_FENCE_GATE != null) return;
        BlockTypes.ACACIA_FENCE_GATE = BlockTypeBuilder
                .builder(BlockAcaciaFenceGateBehavior.class)
                .vanillaBlock(BlockId.ACACIA_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initAcaciaHangingSign() {
        if (BlockTypes.ACACIA_HANGING_SIGN != null) return;
        BlockTypes.ACACIA_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockAcaciaHangingSignBehavior.class)
                .vanillaBlock(BlockId.ACACIA_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initAcaciaLeaves() {
        if (BlockTypes.ACACIA_LEAVES != null) return;
        BlockTypes.ACACIA_LEAVES = BlockTypeBuilder
                .builder(BlockAcaciaLeavesBehavior.class)
                .vanillaBlock(BlockId.ACACIA_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initAcaciaLog() {
        if (BlockTypes.ACACIA_LOG != null) return;
        BlockTypes.ACACIA_LOG = BlockTypeBuilder
                .builder(BlockAcaciaLogBehavior.class)
                .vanillaBlock(BlockId.ACACIA_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initAcaciaPlanks() {
        if (BlockTypes.ACACIA_PLANKS != null) return;
        BlockTypes.ACACIA_PLANKS = BlockTypeBuilder
                .builder(BlockAcaciaPlanksBehavior.class)
                .vanillaBlock(BlockId.ACACIA_PLANKS)
                .build();
    }

    public static void initAcaciaPressurePlate() {
        if (BlockTypes.ACACIA_PRESSURE_PLATE != null) return;
        BlockTypes.ACACIA_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockAcaciaPressurePlateBehavior.class)
                .vanillaBlock(BlockId.ACACIA_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initAcaciaSapling() {
        if (BlockTypes.ACACIA_SAPLING != null) return;
        BlockTypes.ACACIA_SAPLING = BlockTypeBuilder
                .builder(BlockAcaciaSaplingBehavior.class)
                .vanillaBlock(BlockId.ACACIA_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initAcaciaSlab() {
        if (BlockTypes.ACACIA_SLAB != null) return;
        BlockTypes.ACACIA_SLAB = BlockTypeBuilder
                .builder(BlockAcaciaSlabBehavior.class)
                .vanillaBlock(BlockId.ACACIA_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAcaciaStairs() {
        if (BlockTypes.ACACIA_STAIRS != null) return;
        BlockTypes.ACACIA_STAIRS = BlockTypeBuilder
                .builder(BlockAcaciaStairsBehavior.class)
                .vanillaBlock(BlockId.ACACIA_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initAcaciaStandingSign() {
        if (BlockTypes.ACACIA_STANDING_SIGN != null) return;
        BlockTypes.ACACIA_STANDING_SIGN = BlockTypeBuilder
                .builder(BlockAcaciaStandingSignBehavior.class)
                .vanillaBlock(BlockId.ACACIA_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initAcaciaTrapdoor() {
        if (BlockTypes.ACACIA_TRAPDOOR != null) return;
        BlockTypes.ACACIA_TRAPDOOR = BlockTypeBuilder
                .builder(BlockAcaciaTrapdoorBehavior.class)
                .vanillaBlock(BlockId.ACACIA_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initAcaciaWallSign() {
        if (BlockTypes.ACACIA_WALL_SIGN != null) return;
        BlockTypes.ACACIA_WALL_SIGN = BlockTypeBuilder
                .builder(BlockAcaciaWallSignBehavior.class)
                .vanillaBlock(BlockId.ACACIA_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initAcaciaWood() {
        if (BlockTypes.ACACIA_WOOD != null) return;
        BlockTypes.ACACIA_WOOD = BlockTypeBuilder
                .builder(BlockAcaciaWoodBehavior.class)
                .vanillaBlock(BlockId.ACACIA_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initActivatorRail() {
        if (BlockTypes.ACTIVATOR_RAIL != null) return;
        BlockTypes.ACTIVATOR_RAIL = BlockTypeBuilder
                .builder(BlockActivatorRailBehavior.class)
                .vanillaBlock(BlockId.ACTIVATOR_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .build();
    }

    public static void initAir() {
        if (BlockTypes.AIR != null) return;
        BlockTypes.AIR = BlockTypeBuilder
                .builder(BlockAirBehavior.class)
                .vanillaBlock(BlockId.AIR)
                .build();
    }

    public static void initAllium() {
        if (BlockTypes.ALLIUM != null) return;
        BlockTypes.ALLIUM = BlockTypeBuilder
                .builder(BlockAlliumBehavior.class)
                .vanillaBlock(BlockId.ALLIUM)
                .build();
    }

    public static void initAllow() {
        if (BlockTypes.ALLOW != null) return;
        BlockTypes.ALLOW = BlockTypeBuilder
                .builder(BlockAllowBehavior.class)
                .vanillaBlock(BlockId.ALLOW)
                .build();
    }

    public static void initAmethystBlock() {
        if (BlockTypes.AMETHYST_BLOCK != null) return;
        BlockTypes.AMETHYST_BLOCK = BlockTypeBuilder
                .builder(BlockAmethystBlockBehavior.class)
                .vanillaBlock(BlockId.AMETHYST_BLOCK)
                .build();
    }

    public static void initAmethystCluster() {
        if (BlockTypes.AMETHYST_CLUSTER != null) return;
        BlockTypes.AMETHYST_CLUSTER = BlockTypeBuilder
                .builder(BlockAmethystClusterBehavior.class)
                .vanillaBlock(BlockId.AMETHYST_CLUSTER)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initAncientDebris() {
        if (BlockTypes.ANCIENT_DEBRIS != null) return;
        BlockTypes.ANCIENT_DEBRIS = BlockTypeBuilder
                .builder(BlockAncientDebrisBehavior.class)
                .vanillaBlock(BlockId.ANCIENT_DEBRIS)
                .build();
    }

    public static void initAndesite() {
        if (BlockTypes.ANDESITE != null) return;
        BlockTypes.ANDESITE = BlockTypeBuilder
                .builder(BlockAndesiteBehavior.class)
                .vanillaBlock(BlockId.ANDESITE)
                .build();
    }

    public static void initAndesiteDoubleSlab() {
        if (BlockTypes.ANDESITE_DOUBLE_SLAB != null) return;
        BlockTypes.ANDESITE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockAndesiteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.ANDESITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAndesiteSlab() {
        if (BlockTypes.ANDESITE_SLAB != null) return;
        BlockTypes.ANDESITE_SLAB = BlockTypeBuilder
                .builder(BlockAndesiteSlabBehavior.class)
                .vanillaBlock(BlockId.ANDESITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initAndesiteStairs() {
        if (BlockTypes.ANDESITE_STAIRS != null) return;
        BlockTypes.ANDESITE_STAIRS = BlockTypeBuilder
                .builder(BlockAndesiteStairsBehavior.class)
                .vanillaBlock(BlockId.ANDESITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initAnvil() {
        if (BlockTypes.ANVIL != null) return;
        BlockTypes.ANVIL = BlockTypeBuilder
                .builder(BlockAnvilBehavior.class)
                .vanillaBlock(BlockId.ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initAzalea() {
        if (BlockTypes.AZALEA != null) return;
        BlockTypes.AZALEA = BlockTypeBuilder
                .builder(BlockAzaleaBehavior.class)
                .vanillaBlock(BlockId.AZALEA)
                .build();
    }

    public static void initAzaleaLeaves() {
        if (BlockTypes.AZALEA_LEAVES != null) return;
        BlockTypes.AZALEA_LEAVES = BlockTypeBuilder
                .builder(BlockAzaleaLeavesBehavior.class)
                .vanillaBlock(BlockId.AZALEA_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initAzaleaLeavesFlowered() {
        if (BlockTypes.AZALEA_LEAVES_FLOWERED != null) return;
        BlockTypes.AZALEA_LEAVES_FLOWERED = BlockTypeBuilder
                .builder(BlockAzaleaLeavesFloweredBehavior.class)
                .vanillaBlock(BlockId.AZALEA_LEAVES_FLOWERED)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initAzureBluet() {
        if (BlockTypes.AZURE_BLUET != null) return;
        BlockTypes.AZURE_BLUET = BlockTypeBuilder
                .builder(BlockAzureBluetBehavior.class)
                .vanillaBlock(BlockId.AZURE_BLUET)
                .build();
    }

    public static void initBamboo() {
        if (BlockTypes.BAMBOO != null) return;
        BlockTypes.BAMBOO = BlockTypeBuilder
                .builder(BlockBambooBehavior.class)
                .vanillaBlock(BlockId.BAMBOO)
                .setProperties(BlockPropertyTypes.AGE_BIT, BlockPropertyTypes.BAMBOO_LEAF_SIZE, BlockPropertyTypes.BAMBOO_STALK_THICKNESS)
                .build();
    }

    public static void initBambooBlock() {
        if (BlockTypes.BAMBOO_BLOCK != null) return;
        BlockTypes.BAMBOO_BLOCK = BlockTypeBuilder
                .builder(BlockBambooBlockBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBambooButton() {
        if (BlockTypes.BAMBOO_BUTTON != null) return;
        BlockTypes.BAMBOO_BUTTON = BlockTypeBuilder
                .builder(BlockBambooButtonBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBambooDoor() {
        if (BlockTypes.BAMBOO_DOOR != null) return;
        BlockTypes.BAMBOO_DOOR = BlockTypeBuilder
                .builder(BlockBambooDoorBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initBambooDoubleSlab() {
        if (BlockTypes.BAMBOO_DOUBLE_SLAB != null) return;
        BlockTypes.BAMBOO_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockBambooDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooFence() {
        if (BlockTypes.BAMBOO_FENCE != null) return;
        BlockTypes.BAMBOO_FENCE = BlockTypeBuilder
                .builder(BlockBambooFenceBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_FENCE)
                .build();
    }

    public static void initBambooFenceGate() {
        if (BlockTypes.BAMBOO_FENCE_GATE != null) return;
        BlockTypes.BAMBOO_FENCE_GATE = BlockTypeBuilder
                .builder(BlockBambooFenceGateBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initBambooHangingSign() {
        if (BlockTypes.BAMBOO_HANGING_SIGN != null) return;
        BlockTypes.BAMBOO_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockBambooHangingSignBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initBambooMosaic() {
        if (BlockTypes.BAMBOO_MOSAIC != null) return;
        BlockTypes.BAMBOO_MOSAIC = BlockTypeBuilder
                .builder(BlockBambooMosaicBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC)
                .build();
    }

    public static void initBambooMosaicDoubleSlab() {
        if (BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB != null) return;
        BlockTypes.BAMBOO_MOSAIC_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockBambooMosaicDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooMosaicSlab() {
        if (BlockTypes.BAMBOO_MOSAIC_SLAB != null) return;
        BlockTypes.BAMBOO_MOSAIC_SLAB = BlockTypeBuilder
                .builder(BlockBambooMosaicSlabBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooMosaicStairs() {
        if (BlockTypes.BAMBOO_MOSAIC_STAIRS != null) return;
        BlockTypes.BAMBOO_MOSAIC_STAIRS = BlockTypeBuilder
                .builder(BlockBambooMosaicStairsBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_MOSAIC_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBambooPlanks() {
        if (BlockTypes.BAMBOO_PLANKS != null) return;
        BlockTypes.BAMBOO_PLANKS = BlockTypeBuilder
                .builder(BlockBambooPlanksBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_PLANKS)
                .build();
    }

    public static void initBambooPressurePlate() {
        if (BlockTypes.BAMBOO_PRESSURE_PLATE != null) return;
        BlockTypes.BAMBOO_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockBambooPressurePlateBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initBambooSapling() {
        if (BlockTypes.BAMBOO_SAPLING != null) return;
        BlockTypes.BAMBOO_SAPLING = BlockTypeBuilder
                .builder(BlockBambooSaplingBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initBambooSlab() {
        if (BlockTypes.BAMBOO_SLAB != null) return;
        BlockTypes.BAMBOO_SLAB = BlockTypeBuilder
                .builder(BlockBambooSlabBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBambooStairs() {
        if (BlockTypes.BAMBOO_STAIRS != null) return;
        BlockTypes.BAMBOO_STAIRS = BlockTypeBuilder
                .builder(BlockBambooStairsBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBambooStandingSign() {
        if (BlockTypes.BAMBOO_STANDING_SIGN != null) return;
        BlockTypes.BAMBOO_STANDING_SIGN = BlockTypeBuilder
                .builder(BlockBambooStandingSignBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initBambooTrapdoor() {
        if (BlockTypes.BAMBOO_TRAPDOOR != null) return;
        BlockTypes.BAMBOO_TRAPDOOR = BlockTypeBuilder
                .builder(BlockBambooTrapdoorBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initBambooWallSign() {
        if (BlockTypes.BAMBOO_WALL_SIGN != null) return;
        BlockTypes.BAMBOO_WALL_SIGN = BlockTypeBuilder
                .builder(BlockBambooWallSignBehavior.class)
                .vanillaBlock(BlockId.BAMBOO_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBarrel() {
        if (BlockTypes.BARREL != null) return;
        BlockTypes.BARREL = BlockTypeBuilder
                .builder(BlockBarrelBehavior.class)
                .vanillaBlock(BlockId.BARREL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initBarrier() {
        if (BlockTypes.BARRIER != null) return;
        BlockTypes.BARRIER = BlockTypeBuilder
                .builder(BlockBarrierBehavior.class)
                .vanillaBlock(BlockId.BARRIER)
                .build();
    }

    public static void initBasalt() {
        if (BlockTypes.BASALT != null) return;
        BlockTypes.BASALT = BlockTypeBuilder
                .builder(BlockBasaltBehavior.class)
                .vanillaBlock(BlockId.BASALT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBeacon() {
        if (BlockTypes.BEACON != null) return;
        BlockTypes.BEACON = BlockTypeBuilder
                .builder(BlockBeaconBehavior.class)
                .vanillaBlock(BlockId.BEACON)
                .build();
    }

    public static void initBed() {
        if (BlockTypes.BED != null) return;
        BlockTypes.BED = BlockTypeBuilder
                .builder(BlockBedBehavior.class)
                .vanillaBlock(BlockId.BED)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.HEAD_PIECE_BIT, BlockPropertyTypes.OCCUPIED_BIT)
                .build();
    }

    public static void initBedrock() {
        if (BlockTypes.BEDROCK != null) return;
        BlockTypes.BEDROCK = BlockTypeBuilder
                .builder(BlockBedrockBehavior.class)
                .vanillaBlock(BlockId.BEDROCK)
                .setProperties(BlockPropertyTypes.INFINIBURN_BIT)
                .build();
    }

    public static void initBeeNest() {
        if (BlockTypes.BEE_NEST != null) return;
        BlockTypes.BEE_NEST = BlockTypeBuilder
                .builder(BlockBeeNestBehavior.class)
                .vanillaBlock(BlockId.BEE_NEST)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.HONEY_LEVEL)
                .build();
    }

    public static void initBeehive() {
        if (BlockTypes.BEEHIVE != null) return;
        BlockTypes.BEEHIVE = BlockTypeBuilder
                .builder(BlockBeehiveBehavior.class)
                .vanillaBlock(BlockId.BEEHIVE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.HONEY_LEVEL)
                .build();
    }

    public static void initBeetroot() {
        if (BlockTypes.BEETROOT != null) return;
        BlockTypes.BEETROOT = BlockTypeBuilder
                .builder(BlockBeetrootBehavior.class)
                .vanillaBlock(BlockId.BEETROOT)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initBell() {
        if (BlockTypes.BELL != null) return;
        BlockTypes.BELL = BlockTypeBuilder
                .builder(BlockBellBehavior.class)
                .vanillaBlock(BlockId.BELL)
                .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION, BlockPropertyTypes.TOGGLE_BIT)
                .build();
    }

    public static void initBigDripleaf() {
        if (BlockTypes.BIG_DRIPLEAF != null) return;
        BlockTypes.BIG_DRIPLEAF = BlockTypeBuilder
                .builder(BlockBigDripleafBehavior.class)
                .vanillaBlock(BlockId.BIG_DRIPLEAF)
                .setProperties(BlockPropertyTypes.BIG_DRIPLEAF_HEAD, BlockPropertyTypes.BIG_DRIPLEAF_TILT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initBirchButton() {
        if (BlockTypes.BIRCH_BUTTON != null) return;
        BlockTypes.BIRCH_BUTTON = BlockTypeBuilder
                .builder(BlockBirchButtonBehavior.class)
                .vanillaBlock(BlockId.BIRCH_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBirchDoor() {
        if (BlockTypes.BIRCH_DOOR != null) return;
        BlockTypes.BIRCH_DOOR = BlockTypeBuilder
                .builder(BlockBirchDoorBehavior.class)
                .vanillaBlock(BlockId.BIRCH_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initBirchDoubleSlab() {
        if (BlockTypes.BIRCH_DOUBLE_SLAB != null) return;
        BlockTypes.BIRCH_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockBirchDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.BIRCH_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBirchFence() {
        if (BlockTypes.BIRCH_FENCE != null) return;
        BlockTypes.BIRCH_FENCE = BlockTypeBuilder
                .builder(BlockBirchFenceBehavior.class)
                .vanillaBlock(BlockId.BIRCH_FENCE)
                .build();
    }

    public static void initBirchFenceGate() {
        if (BlockTypes.BIRCH_FENCE_GATE != null) return;
        BlockTypes.BIRCH_FENCE_GATE = BlockTypeBuilder
                .builder(BlockBirchFenceGateBehavior.class)
                .vanillaBlock(BlockId.BIRCH_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initBirchHangingSign() {
        if (BlockTypes.BIRCH_HANGING_SIGN != null) return;
        BlockTypes.BIRCH_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockBirchHangingSignBehavior.class)
                .vanillaBlock(BlockId.BIRCH_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initBirchLeaves() {
        if (BlockTypes.BIRCH_LEAVES != null) return;
        BlockTypes.BIRCH_LEAVES = BlockTypeBuilder
                .builder(BlockBirchLeavesBehavior.class)
                .vanillaBlock(BlockId.BIRCH_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initBirchLog() {
        if (BlockTypes.BIRCH_LOG != null) return;
        BlockTypes.BIRCH_LOG = BlockTypeBuilder
                .builder(BlockBirchLogBehavior.class)
                .vanillaBlock(BlockId.BIRCH_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBirchPlanks() {
        if (BlockTypes.BIRCH_PLANKS != null) return;
        BlockTypes.BIRCH_PLANKS = BlockTypeBuilder
                .builder(BlockBirchPlanksBehavior.class)
                .vanillaBlock(BlockId.BIRCH_PLANKS)
                .build();
    }

    public static void initBirchPressurePlate() {
        if (BlockTypes.BIRCH_PRESSURE_PLATE != null) return;
        BlockTypes.BIRCH_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockBirchPressurePlateBehavior.class)
                .vanillaBlock(BlockId.BIRCH_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initBirchSapling() {
        if (BlockTypes.BIRCH_SAPLING != null) return;
        BlockTypes.BIRCH_SAPLING = BlockTypeBuilder
                .builder(BlockBirchSaplingBehavior.class)
                .vanillaBlock(BlockId.BIRCH_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initBirchSlab() {
        if (BlockTypes.BIRCH_SLAB != null) return;
        BlockTypes.BIRCH_SLAB = BlockTypeBuilder
                .builder(BlockBirchSlabBehavior.class)
                .vanillaBlock(BlockId.BIRCH_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBirchStairs() {
        if (BlockTypes.BIRCH_STAIRS != null) return;
        BlockTypes.BIRCH_STAIRS = BlockTypeBuilder
                .builder(BlockBirchStairsBehavior.class)
                .vanillaBlock(BlockId.BIRCH_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBirchStandingSign() {
        if (BlockTypes.BIRCH_STANDING_SIGN != null) return;
        BlockTypes.BIRCH_STANDING_SIGN = BlockTypeBuilder
                .builder(BlockBirchStandingSignBehavior.class)
                .vanillaBlock(BlockId.BIRCH_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initBirchTrapdoor() {
        if (BlockTypes.BIRCH_TRAPDOOR != null) return;
        BlockTypes.BIRCH_TRAPDOOR = BlockTypeBuilder
                .builder(BlockBirchTrapdoorBehavior.class)
                .vanillaBlock(BlockId.BIRCH_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initBirchWallSign() {
        if (BlockTypes.BIRCH_WALL_SIGN != null) return;
        BlockTypes.BIRCH_WALL_SIGN = BlockTypeBuilder
                .builder(BlockBirchWallSignBehavior.class)
                .vanillaBlock(BlockId.BIRCH_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBirchWood() {
        if (BlockTypes.BIRCH_WOOD != null) return;
        BlockTypes.BIRCH_WOOD = BlockTypeBuilder
                .builder(BlockBirchWoodBehavior.class)
                .vanillaBlock(BlockId.BIRCH_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBlackCandle() {
        if (BlockTypes.BLACK_CANDLE != null) return;
        BlockTypes.BLACK_CANDLE = BlockTypeBuilder
                .builder(BlockBlackCandleBehavior.class)
                .vanillaBlock(BlockId.BLACK_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlackCandleCake() {
        if (BlockTypes.BLACK_CANDLE_CAKE != null) return;
        BlockTypes.BLACK_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockBlackCandleCakeBehavior.class)
                .vanillaBlock(BlockId.BLACK_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlackCarpet() {
        if (BlockTypes.BLACK_CARPET != null) return;
        BlockTypes.BLACK_CARPET = BlockTypeBuilder
                .builder(BlockBlackCarpetBehavior.class)
                .vanillaBlock(BlockId.BLACK_CARPET)
                .build();
    }

    public static void initBlackConcrete() {
        if (BlockTypes.BLACK_CONCRETE != null) return;
        BlockTypes.BLACK_CONCRETE = BlockTypeBuilder
                .builder(BlockBlackConcreteBehavior.class)
                .vanillaBlock(BlockId.BLACK_CONCRETE)
                .build();
    }

    public static void initBlackConcretePowder() {
        if (BlockTypes.BLACK_CONCRETE_POWDER != null) return;
        BlockTypes.BLACK_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockBlackConcretePowderBehavior.class)
                .vanillaBlock(BlockId.BLACK_CONCRETE_POWDER)
                .build();
    }

    public static void initBlackGlazedTerracotta() {
        if (BlockTypes.BLACK_GLAZED_TERRACOTTA != null) return;
        BlockTypes.BLACK_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockBlackGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.BLACK_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBlackShulkerBox() {
        if (BlockTypes.BLACK_SHULKER_BOX != null) return;
        BlockTypes.BLACK_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockBlackShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.BLACK_SHULKER_BOX)
                .build();
    }

    public static void initBlackStainedGlass() {
        if (BlockTypes.BLACK_STAINED_GLASS != null) return;
        BlockTypes.BLACK_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockBlackStainedGlassBehavior.class)
                .vanillaBlock(BlockId.BLACK_STAINED_GLASS)
                .build();
    }

    public static void initBlackStainedGlassPane() {
        if (BlockTypes.BLACK_STAINED_GLASS_PANE != null) return;
        BlockTypes.BLACK_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockBlackStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.BLACK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBlackTerracotta() {
        if (BlockTypes.BLACK_TERRACOTTA != null) return;
        BlockTypes.BLACK_TERRACOTTA = BlockTypeBuilder
                .builder(BlockBlackTerracottaBehavior.class)
                .vanillaBlock(BlockId.BLACK_TERRACOTTA)
                .build();
    }

    public static void initBlackWool() {
        if (BlockTypes.BLACK_WOOL != null) return;
        BlockTypes.BLACK_WOOL = BlockTypeBuilder
                .builder(BlockBlackWoolBehavior.class)
                .vanillaBlock(BlockId.BLACK_WOOL)
                .build();
    }

    public static void initBlackstone() {
        if (BlockTypes.BLACKSTONE != null) return;
        BlockTypes.BLACKSTONE = BlockTypeBuilder
                .builder(BlockBlackstoneBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE)
                .build();
    }

    public static void initBlackstoneDoubleSlab() {
        if (BlockTypes.BLACKSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.BLACKSTONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockBlackstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBlackstoneSlab() {
        if (BlockTypes.BLACKSTONE_SLAB != null) return;
        BlockTypes.BLACKSTONE_SLAB = BlockTypeBuilder
                .builder(BlockBlackstoneSlabBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBlackstoneStairs() {
        if (BlockTypes.BLACKSTONE_STAIRS != null) return;
        BlockTypes.BLACKSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockBlackstoneStairsBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBlackstoneWall() {
        if (BlockTypes.BLACKSTONE_WALL != null) return;
        BlockTypes.BLACKSTONE_WALL = BlockTypeBuilder
                .builder(BlockBlackstoneWallBehavior.class)
                .vanillaBlock(BlockId.BLACKSTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initBlastFurnace() {
        if (BlockTypes.BLAST_FURNACE != null) return;
        BlockTypes.BLAST_FURNACE = BlockTypeBuilder
                .builder(BlockBlastFurnaceBehavior.class)
                .vanillaBlock(BlockId.BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initBlueCandle() {
        if (BlockTypes.BLUE_CANDLE != null) return;
        BlockTypes.BLUE_CANDLE = BlockTypeBuilder
                .builder(BlockBlueCandleBehavior.class)
                .vanillaBlock(BlockId.BLUE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlueCandleCake() {
        if (BlockTypes.BLUE_CANDLE_CAKE != null) return;
        BlockTypes.BLUE_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockBlueCandleCakeBehavior.class)
                .vanillaBlock(BlockId.BLUE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBlueCarpet() {
        if (BlockTypes.BLUE_CARPET != null) return;
        BlockTypes.BLUE_CARPET = BlockTypeBuilder
                .builder(BlockBlueCarpetBehavior.class)
                .vanillaBlock(BlockId.BLUE_CARPET)
                .build();
    }

    public static void initBlueConcrete() {
        if (BlockTypes.BLUE_CONCRETE != null) return;
        BlockTypes.BLUE_CONCRETE = BlockTypeBuilder
                .builder(BlockBlueConcreteBehavior.class)
                .vanillaBlock(BlockId.BLUE_CONCRETE)
                .build();
    }

    public static void initBlueConcretePowder() {
        if (BlockTypes.BLUE_CONCRETE_POWDER != null) return;
        BlockTypes.BLUE_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockBlueConcretePowderBehavior.class)
                .vanillaBlock(BlockId.BLUE_CONCRETE_POWDER)
                .build();
    }

    public static void initBlueGlazedTerracotta() {
        if (BlockTypes.BLUE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.BLUE_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockBlueGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.BLUE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBlueIce() {
        if (BlockTypes.BLUE_ICE != null) return;
        BlockTypes.BLUE_ICE = BlockTypeBuilder
                .builder(BlockBlueIceBehavior.class)
                .vanillaBlock(BlockId.BLUE_ICE)
                .build();
    }

    public static void initBlueOrchid() {
        if (BlockTypes.BLUE_ORCHID != null) return;
        BlockTypes.BLUE_ORCHID = BlockTypeBuilder
                .builder(BlockBlueOrchidBehavior.class)
                .vanillaBlock(BlockId.BLUE_ORCHID)
                .build();
    }

    public static void initBlueShulkerBox() {
        if (BlockTypes.BLUE_SHULKER_BOX != null) return;
        BlockTypes.BLUE_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockBlueShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.BLUE_SHULKER_BOX)
                .build();
    }

    public static void initBlueStainedGlass() {
        if (BlockTypes.BLUE_STAINED_GLASS != null) return;
        BlockTypes.BLUE_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockBlueStainedGlassBehavior.class)
                .vanillaBlock(BlockId.BLUE_STAINED_GLASS)
                .build();
    }

    public static void initBlueStainedGlassPane() {
        if (BlockTypes.BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.BLUE_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockBlueStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBlueTerracotta() {
        if (BlockTypes.BLUE_TERRACOTTA != null) return;
        BlockTypes.BLUE_TERRACOTTA = BlockTypeBuilder
                .builder(BlockBlueTerracottaBehavior.class)
                .vanillaBlock(BlockId.BLUE_TERRACOTTA)
                .build();
    }

    public static void initBlueWool() {
        if (BlockTypes.BLUE_WOOL != null) return;
        BlockTypes.BLUE_WOOL = BlockTypeBuilder
                .builder(BlockBlueWoolBehavior.class)
                .vanillaBlock(BlockId.BLUE_WOOL)
                .build();
    }

    public static void initBoneBlock() {
        if (BlockTypes.BONE_BLOCK != null) return;
        BlockTypes.BONE_BLOCK = BlockTypeBuilder
                .builder(BlockBoneBlockBehavior.class)
                .vanillaBlock(BlockId.BONE_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initBookshelf() {
        if (BlockTypes.BOOKSHELF != null) return;
        BlockTypes.BOOKSHELF = BlockTypeBuilder
                .builder(BlockBookshelfBehavior.class)
                .vanillaBlock(BlockId.BOOKSHELF)
                .build();
    }

    public static void initBorderBlock() {
        if (BlockTypes.BORDER_BLOCK != null) return;
        BlockTypes.BORDER_BLOCK = BlockTypeBuilder
                .builder(BlockBorderBlockBehavior.class)
                .vanillaBlock(BlockId.BORDER_BLOCK)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initBrainCoral() {
        if (BlockTypes.BRAIN_CORAL != null) return;
        BlockTypes.BRAIN_CORAL = BlockTypeBuilder
                .builder(BlockBrainCoralBehavior.class)
                .vanillaBlock(BlockId.BRAIN_CORAL)
                .build();
    }

    public static void initBrainCoralBlock() {
        if (BlockTypes.BRAIN_CORAL_BLOCK != null) return;
        BlockTypes.BRAIN_CORAL_BLOCK = BlockTypeBuilder
                .builder(BlockBrainCoralBlockBehavior.class)
                .vanillaBlock(BlockId.BRAIN_CORAL_BLOCK)
                .build();
    }

    public static void initBrainCoralFan() {
        if (BlockTypes.BRAIN_CORAL_FAN != null) return;
        BlockTypes.BRAIN_CORAL_FAN = BlockTypeBuilder
                .builder(BlockBrainCoralFanBehavior.class)
                .vanillaBlock(BlockId.BRAIN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initBrainCoralWallFan() {
        if (BlockTypes.BRAIN_CORAL_WALL_FAN != null) return;
        BlockTypes.BRAIN_CORAL_WALL_FAN = BlockTypeBuilder
                .builder(BlockBrainCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.BRAIN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initBrewingStand() {
        if (BlockTypes.BREWING_STAND != null) return;
        BlockTypes.BREWING_STAND = BlockTypeBuilder
                .builder(BlockBrewingStandBehavior.class)
                .vanillaBlock(BlockId.BREWING_STAND)
                .setProperties(BlockPropertyTypes.BREWING_STAND_SLOT_A_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_B_BIT, BlockPropertyTypes.BREWING_STAND_SLOT_C_BIT)
                .build();
    }

    public static void initBrickBlock() {
        if (BlockTypes.BRICK_BLOCK != null) return;
        BlockTypes.BRICK_BLOCK = BlockTypeBuilder
                .builder(BlockBrickBlockBehavior.class)
                .vanillaBlock(BlockId.BRICK_BLOCK)
                .build();
    }

    public static void initBrickDoubleSlab() {
        if (BlockTypes.BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBrickSlab() {
        if (BlockTypes.BRICK_SLAB != null) return;
        BlockTypes.BRICK_SLAB = BlockTypeBuilder
                .builder(BlockBrickSlabBehavior.class)
                .vanillaBlock(BlockId.BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initBrickStairs() {
        if (BlockTypes.BRICK_STAIRS != null) return;
        BlockTypes.BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockBrickStairsBehavior.class)
                .vanillaBlock(BlockId.BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initBrownCandle() {
        if (BlockTypes.BROWN_CANDLE != null) return;
        BlockTypes.BROWN_CANDLE = BlockTypeBuilder
                .builder(BlockBrownCandleBehavior.class)
                .vanillaBlock(BlockId.BROWN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBrownCandleCake() {
        if (BlockTypes.BROWN_CANDLE_CAKE != null) return;
        BlockTypes.BROWN_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockBrownCandleCakeBehavior.class)
                .vanillaBlock(BlockId.BROWN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initBrownCarpet() {
        if (BlockTypes.BROWN_CARPET != null) return;
        BlockTypes.BROWN_CARPET = BlockTypeBuilder
                .builder(BlockBrownCarpetBehavior.class)
                .vanillaBlock(BlockId.BROWN_CARPET)
                .build();
    }

    public static void initBrownConcrete() {
        if (BlockTypes.BROWN_CONCRETE != null) return;
        BlockTypes.BROWN_CONCRETE = BlockTypeBuilder
                .builder(BlockBrownConcreteBehavior.class)
                .vanillaBlock(BlockId.BROWN_CONCRETE)
                .build();
    }

    public static void initBrownConcretePowder() {
        if (BlockTypes.BROWN_CONCRETE_POWDER != null) return;
        BlockTypes.BROWN_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockBrownConcretePowderBehavior.class)
                .vanillaBlock(BlockId.BROWN_CONCRETE_POWDER)
                .build();
    }

    public static void initBrownGlazedTerracotta() {
        if (BlockTypes.BROWN_GLAZED_TERRACOTTA != null) return;
        BlockTypes.BROWN_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockBrownGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.BROWN_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initBrownMushroom() {
        if (BlockTypes.BROWN_MUSHROOM != null) return;
        BlockTypes.BROWN_MUSHROOM = BlockTypeBuilder
                .builder(BlockBrownMushroomBehavior.class)
                .vanillaBlock(BlockId.BROWN_MUSHROOM)
                .build();
    }

    public static void initBrownMushroomBlock() {
        if (BlockTypes.BROWN_MUSHROOM_BLOCK != null) return;
        BlockTypes.BROWN_MUSHROOM_BLOCK = BlockTypeBuilder
                .builder(BlockBrownMushroomBlockBehavior.class)
                .vanillaBlock(BlockId.BROWN_MUSHROOM_BLOCK)
                .setProperties(BlockPropertyTypes.HUGE_MUSHROOM_BITS)
                .build();
    }

    public static void initBrownShulkerBox() {
        if (BlockTypes.BROWN_SHULKER_BOX != null) return;
        BlockTypes.BROWN_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockBrownShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.BROWN_SHULKER_BOX)
                .build();
    }

    public static void initBrownStainedGlass() {
        if (BlockTypes.BROWN_STAINED_GLASS != null) return;
        BlockTypes.BROWN_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockBrownStainedGlassBehavior.class)
                .vanillaBlock(BlockId.BROWN_STAINED_GLASS)
                .build();
    }

    public static void initBrownStainedGlassPane() {
        if (BlockTypes.BROWN_STAINED_GLASS_PANE != null) return;
        BlockTypes.BROWN_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockBrownStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.BROWN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initBrownTerracotta() {
        if (BlockTypes.BROWN_TERRACOTTA != null) return;
        BlockTypes.BROWN_TERRACOTTA = BlockTypeBuilder
                .builder(BlockBrownTerracottaBehavior.class)
                .vanillaBlock(BlockId.BROWN_TERRACOTTA)
                .build();
    }

    public static void initBrownWool() {
        if (BlockTypes.BROWN_WOOL != null) return;
        BlockTypes.BROWN_WOOL = BlockTypeBuilder
                .builder(BlockBrownWoolBehavior.class)
                .vanillaBlock(BlockId.BROWN_WOOL)
                .build();
    }

    public static void initBubbleColumn() {
        if (BlockTypes.BUBBLE_COLUMN != null) return;
        BlockTypes.BUBBLE_COLUMN = BlockTypeBuilder
                .builder(BlockBubbleColumnBehavior.class)
                .vanillaBlock(BlockId.BUBBLE_COLUMN)
                .setProperties(BlockPropertyTypes.DRAG_DOWN)
                .build();
    }

    public static void initBubbleCoral() {
        if (BlockTypes.BUBBLE_CORAL != null) return;
        BlockTypes.BUBBLE_CORAL = BlockTypeBuilder
                .builder(BlockBubbleCoralBehavior.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL)
                .build();
    }

    public static void initBubbleCoralBlock() {
        if (BlockTypes.BUBBLE_CORAL_BLOCK != null) return;
        BlockTypes.BUBBLE_CORAL_BLOCK = BlockTypeBuilder
                .builder(BlockBubbleCoralBlockBehavior.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL_BLOCK)
                .build();
    }

    public static void initBubbleCoralFan() {
        if (BlockTypes.BUBBLE_CORAL_FAN != null) return;
        BlockTypes.BUBBLE_CORAL_FAN = BlockTypeBuilder
                .builder(BlockBubbleCoralFanBehavior.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initBubbleCoralWallFan() {
        if (BlockTypes.BUBBLE_CORAL_WALL_FAN != null) return;
        BlockTypes.BUBBLE_CORAL_WALL_FAN = BlockTypeBuilder
                .builder(BlockBubbleCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.BUBBLE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initBuddingAmethyst() {
        if (BlockTypes.BUDDING_AMETHYST != null) return;
        BlockTypes.BUDDING_AMETHYST = BlockTypeBuilder
                .builder(BlockBuddingAmethystBehavior.class)
                .vanillaBlock(BlockId.BUDDING_AMETHYST)
                .build();
    }

    public static void initCactus() {
        if (BlockTypes.CACTUS != null) return;
        BlockTypes.CACTUS = BlockTypeBuilder
                .builder(BlockCactusBehavior.class)
                .vanillaBlock(BlockId.CACTUS)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initCake() {
        if (BlockTypes.CAKE != null) return;
        BlockTypes.CAKE = BlockTypeBuilder
                .builder(BlockCakeBehavior.class)
                .vanillaBlock(BlockId.CAKE)
                .setProperties(BlockPropertyTypes.BITE_COUNTER)
                .build();
    }

    public static void initCalcite() {
        if (BlockTypes.CALCITE != null) return;
        BlockTypes.CALCITE = BlockTypeBuilder
                .builder(BlockCalciteBehavior.class)
                .vanillaBlock(BlockId.CALCITE)
                .build();
    }

    public static void initCalibratedSculkSensor() {
        if (BlockTypes.CALIBRATED_SCULK_SENSOR != null) return;
        BlockTypes.CALIBRATED_SCULK_SENSOR = BlockTypeBuilder
                .builder(BlockCalibratedSculkSensorBehavior.class)
                .vanillaBlock(BlockId.CALIBRATED_SCULK_SENSOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.SCULK_SENSOR_PHASE)
                .build();
    }

    public static void initCamera() {
        if (BlockTypes.CAMERA != null) return;
        BlockTypes.CAMERA = BlockTypeBuilder
                .builder(BlockCameraBehavior.class)
                .vanillaBlock(BlockId.CAMERA)
                .build();
    }

    public static void initCampfire() {
        if (BlockTypes.CAMPFIRE != null) return;
        BlockTypes.CAMPFIRE = BlockTypeBuilder
                .builder(BlockCampfireBehavior.class)
                .vanillaBlock(BlockId.CAMPFIRE)
                .setProperties(BlockPropertyTypes.EXTINGUISHED, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initCandle() {
        if (BlockTypes.CANDLE != null) return;
        BlockTypes.CANDLE = BlockTypeBuilder
                .builder(BlockCandleBehavior.class)
                .vanillaBlock(BlockId.CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCandleCake() {
        if (BlockTypes.CANDLE_CAKE != null) return;
        BlockTypes.CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockCandleCakeBehavior.class)
                .vanillaBlock(BlockId.CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCarrots() {
        if (BlockTypes.CARROTS != null) return;
        BlockTypes.CARROTS = BlockTypeBuilder
                .builder(BlockCarrotsBehavior.class)
                .vanillaBlock(BlockId.CARROTS)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initCartographyTable() {
        if (BlockTypes.CARTOGRAPHY_TABLE != null) return;
        BlockTypes.CARTOGRAPHY_TABLE = BlockTypeBuilder
                .builder(BlockCartographyTableBehavior.class)
                .vanillaBlock(BlockId.CARTOGRAPHY_TABLE)
                .build();
    }

    public static void initCarvedPumpkin() {
        if (BlockTypes.CARVED_PUMPKIN != null) return;
        BlockTypes.CARVED_PUMPKIN = BlockTypeBuilder
                .builder(BlockCarvedPumpkinBehavior.class)
                .vanillaBlock(BlockId.CARVED_PUMPKIN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initCauldron() {
        if (BlockTypes.CAULDRON != null) return;
        BlockTypes.CAULDRON = BlockTypeBuilder
                .builder(BlockCauldronBehavior.class)
                .vanillaBlock(BlockId.CAULDRON)
                .setProperties(BlockPropertyTypes.CAULDRON_LIQUID, BlockPropertyTypes.FILL_LEVEL)
                .build();
    }

    public static void initCaveVines() {
        if (BlockTypes.CAVE_VINES != null) return;
        BlockTypes.CAVE_VINES = BlockTypeBuilder
                .builder(BlockCaveVinesBehavior.class)
                .vanillaBlock(BlockId.CAVE_VINES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .build();
    }

    public static void initCaveVinesBodyWithBerries() {
        if (BlockTypes.CAVE_VINES_BODY_WITH_BERRIES != null) return;
        BlockTypes.CAVE_VINES_BODY_WITH_BERRIES = BlockTypeBuilder
                .builder(BlockCaveVinesBodyWithBerriesBehavior.class)
                .vanillaBlock(BlockId.CAVE_VINES_BODY_WITH_BERRIES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .build();
    }

    public static void initCaveVinesHeadWithBerries() {
        if (BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES != null) return;
        BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES = BlockTypeBuilder
                .builder(BlockCaveVinesHeadWithBerriesBehavior.class)
                .vanillaBlock(BlockId.CAVE_VINES_HEAD_WITH_BERRIES)
                .setProperties(BlockPropertyTypes.GROWING_PLANT_AGE)
                .build();
    }

    public static void initChain() {
        if (BlockTypes.CHAIN != null) return;
        BlockTypes.CHAIN = BlockTypeBuilder
                .builder(BlockChainBehavior.class)
                .vanillaBlock(BlockId.CHAIN)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initChainCommandBlock() {
        if (BlockTypes.CHAIN_COMMAND_BLOCK != null) return;
        BlockTypes.CHAIN_COMMAND_BLOCK = BlockTypeBuilder
                .builder(BlockChainCommandBlockBehavior.class)
                .vanillaBlock(BlockId.CHAIN_COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initChemicalHeat() {
        if (BlockTypes.CHEMICAL_HEAT != null) return;
        BlockTypes.CHEMICAL_HEAT = BlockTypeBuilder
                .builder(BlockChemicalHeatBehavior.class)
                .vanillaBlock(BlockId.CHEMICAL_HEAT)
                .build();
    }

    public static void initChemistryTable() {
        if (BlockTypes.CHEMISTRY_TABLE != null) return;
        BlockTypes.CHEMISTRY_TABLE = BlockTypeBuilder
                .builder(BlockChemistryTableBehavior.class)
                .vanillaBlock(BlockId.CHEMISTRY_TABLE)
                .setProperties(BlockPropertyTypes.CHEMISTRY_TABLE_TYPE, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initCherryButton() {
        if (BlockTypes.CHERRY_BUTTON != null) return;
        BlockTypes.CHERRY_BUTTON = BlockTypeBuilder
                .builder(BlockCherryButtonBehavior.class)
                .vanillaBlock(BlockId.CHERRY_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCherryDoor() {
        if (BlockTypes.CHERRY_DOOR != null) return;
        BlockTypes.CHERRY_DOOR = BlockTypeBuilder
                .builder(BlockCherryDoorBehavior.class)
                .vanillaBlock(BlockId.CHERRY_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initCherryDoubleSlab() {
        if (BlockTypes.CHERRY_DOUBLE_SLAB != null) return;
        BlockTypes.CHERRY_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockCherryDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.CHERRY_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCherryFence() {
        if (BlockTypes.CHERRY_FENCE != null) return;
        BlockTypes.CHERRY_FENCE = BlockTypeBuilder
                .builder(BlockCherryFenceBehavior.class)
                .vanillaBlock(BlockId.CHERRY_FENCE)
                .build();
    }

    public static void initCherryFenceGate() {
        if (BlockTypes.CHERRY_FENCE_GATE != null) return;
        BlockTypes.CHERRY_FENCE_GATE = BlockTypeBuilder
                .builder(BlockCherryFenceGateBehavior.class)
                .vanillaBlock(BlockId.CHERRY_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initCherryHangingSign() {
        if (BlockTypes.CHERRY_HANGING_SIGN != null) return;
        BlockTypes.CHERRY_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockCherryHangingSignBehavior.class)
                .vanillaBlock(BlockId.CHERRY_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initCherryLeaves() {
        if (BlockTypes.CHERRY_LEAVES != null) return;
        BlockTypes.CHERRY_LEAVES = BlockTypeBuilder
                .builder(BlockCherryLeavesBehavior.class)
                .vanillaBlock(BlockId.CHERRY_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initCherryLog() {
        if (BlockTypes.CHERRY_LOG != null) return;
        BlockTypes.CHERRY_LOG = BlockTypeBuilder
                .builder(BlockCherryLogBehavior.class)
                .vanillaBlock(BlockId.CHERRY_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initCherryPlanks() {
        if (BlockTypes.CHERRY_PLANKS != null) return;
        BlockTypes.CHERRY_PLANKS = BlockTypeBuilder
                .builder(BlockCherryPlanksBehavior.class)
                .vanillaBlock(BlockId.CHERRY_PLANKS)
                .build();
    }

    public static void initCherryPressurePlate() {
        if (BlockTypes.CHERRY_PRESSURE_PLATE != null) return;
        BlockTypes.CHERRY_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockCherryPressurePlateBehavior.class)
                .vanillaBlock(BlockId.CHERRY_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initCherrySapling() {
        if (BlockTypes.CHERRY_SAPLING != null) return;
        BlockTypes.CHERRY_SAPLING = BlockTypeBuilder
                .builder(BlockCherrySaplingBehavior.class)
                .vanillaBlock(BlockId.CHERRY_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initCherrySlab() {
        if (BlockTypes.CHERRY_SLAB != null) return;
        BlockTypes.CHERRY_SLAB = BlockTypeBuilder
                .builder(BlockCherrySlabBehavior.class)
                .vanillaBlock(BlockId.CHERRY_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCherryStairs() {
        if (BlockTypes.CHERRY_STAIRS != null) return;
        BlockTypes.CHERRY_STAIRS = BlockTypeBuilder
                .builder(BlockCherryStairsBehavior.class)
                .vanillaBlock(BlockId.CHERRY_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCherryStandingSign() {
        if (BlockTypes.CHERRY_STANDING_SIGN != null) return;
        BlockTypes.CHERRY_STANDING_SIGN = BlockTypeBuilder
                .builder(BlockCherryStandingSignBehavior.class)
                .vanillaBlock(BlockId.CHERRY_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initCherryTrapdoor() {
        if (BlockTypes.CHERRY_TRAPDOOR != null) return;
        BlockTypes.CHERRY_TRAPDOOR = BlockTypeBuilder
                .builder(BlockCherryTrapdoorBehavior.class)
                .vanillaBlock(BlockId.CHERRY_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initCherryWallSign() {
        if (BlockTypes.CHERRY_WALL_SIGN != null) return;
        BlockTypes.CHERRY_WALL_SIGN = BlockTypeBuilder
                .builder(BlockCherryWallSignBehavior.class)
                .vanillaBlock(BlockId.CHERRY_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCherryWood() {
        if (BlockTypes.CHERRY_WOOD != null) return;
        BlockTypes.CHERRY_WOOD = BlockTypeBuilder
                .builder(BlockCherryWoodBehavior.class)
                .vanillaBlock(BlockId.CHERRY_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS, BlockPropertyTypes.STRIPPED_BIT)
                .build();
    }

    public static void initChest() {
        if (BlockTypes.CHEST != null) return;
        BlockTypes.CHEST = BlockTypeBuilder
                .builder(BlockChestBehavior.class)
                .vanillaBlock(BlockId.CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initChippedAnvil() {
        if (BlockTypes.CHIPPED_ANVIL != null) return;
        BlockTypes.CHIPPED_ANVIL = BlockTypeBuilder
                .builder(BlockChippedAnvilBehavior.class)
                .vanillaBlock(BlockId.CHIPPED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initChiseledBookshelf() {
        if (BlockTypes.CHISELED_BOOKSHELF != null) return;
        BlockTypes.CHISELED_BOOKSHELF = BlockTypeBuilder
                .builder(BlockChiseledBookshelfBehavior.class)
                .vanillaBlock(BlockId.CHISELED_BOOKSHELF)
                .setProperties(BlockPropertyTypes.BOOKS_STORED, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initChiseledCopper() {
        if (BlockTypes.CHISELED_COPPER != null) return;
        BlockTypes.CHISELED_COPPER = BlockTypeBuilder
                .builder(BlockChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.CHISELED_COPPER)
                .build();
    }

    public static void initChiseledDeepslate() {
        if (BlockTypes.CHISELED_DEEPSLATE != null) return;
        BlockTypes.CHISELED_DEEPSLATE = BlockTypeBuilder
                .builder(BlockChiseledDeepslateBehavior.class)
                .vanillaBlock(BlockId.CHISELED_DEEPSLATE)
                .build();
    }

    public static void initChiseledNetherBricks() {
        if (BlockTypes.CHISELED_NETHER_BRICKS != null) return;
        BlockTypes.CHISELED_NETHER_BRICKS = BlockTypeBuilder
                .builder(BlockChiseledNetherBricksBehavior.class)
                .vanillaBlock(BlockId.CHISELED_NETHER_BRICKS)
                .build();
    }

    public static void initChiseledPolishedBlackstone() {
        if (BlockTypes.CHISELED_POLISHED_BLACKSTONE != null) return;
        BlockTypes.CHISELED_POLISHED_BLACKSTONE = BlockTypeBuilder
                .builder(BlockChiseledPolishedBlackstoneBehavior.class)
                .vanillaBlock(BlockId.CHISELED_POLISHED_BLACKSTONE)
                .build();
    }

    public static void initChiseledQuartzBlock() {
        if (BlockTypes.CHISELED_QUARTZ_BLOCK != null) return;
        BlockTypes.CHISELED_QUARTZ_BLOCK = BlockTypeBuilder
                .builder(BlockChiseledQuartzBlockBehavior.class)
                .vanillaBlock(BlockId.CHISELED_QUARTZ_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initChiseledRedSandstone() {
        if (BlockTypes.CHISELED_RED_SANDSTONE != null) return;
        BlockTypes.CHISELED_RED_SANDSTONE = BlockTypeBuilder
                .builder(BlockChiseledRedSandstoneBehavior.class)
                .vanillaBlock(BlockId.CHISELED_RED_SANDSTONE)
                .build();
    }

    public static void initChiseledSandstone() {
        if (BlockTypes.CHISELED_SANDSTONE != null) return;
        BlockTypes.CHISELED_SANDSTONE = BlockTypeBuilder
                .builder(BlockChiseledSandstoneBehavior.class)
                .vanillaBlock(BlockId.CHISELED_SANDSTONE)
                .build();
    }

    public static void initChiseledStoneBricks() {
        if (BlockTypes.CHISELED_STONE_BRICKS != null) return;
        BlockTypes.CHISELED_STONE_BRICKS = BlockTypeBuilder
                .builder(BlockChiseledStoneBricksBehavior.class)
                .vanillaBlock(BlockId.CHISELED_STONE_BRICKS)
                .build();
    }

    public static void initChiseledTuff() {
        if (BlockTypes.CHISELED_TUFF != null) return;
        BlockTypes.CHISELED_TUFF = BlockTypeBuilder
                .builder(BlockChiseledTuffBehavior.class)
                .vanillaBlock(BlockId.CHISELED_TUFF)
                .build();
    }

    public static void initChiseledTuffBricks() {
        if (BlockTypes.CHISELED_TUFF_BRICKS != null) return;
        BlockTypes.CHISELED_TUFF_BRICKS = BlockTypeBuilder
                .builder(BlockChiseledTuffBricksBehavior.class)
                .vanillaBlock(BlockId.CHISELED_TUFF_BRICKS)
                .build();
    }

    public static void initChorusFlower() {
        if (BlockTypes.CHORUS_FLOWER != null) return;
        BlockTypes.CHORUS_FLOWER = BlockTypeBuilder
                .builder(BlockChorusFlowerBehavior.class)
                .vanillaBlock(BlockId.CHORUS_FLOWER)
                .setProperties(BlockPropertyTypes.AGE_6)
                .build();
    }

    public static void initChorusPlant() {
        if (BlockTypes.CHORUS_PLANT != null) return;
        BlockTypes.CHORUS_PLANT = BlockTypeBuilder
                .builder(BlockChorusPlantBehavior.class)
                .vanillaBlock(BlockId.CHORUS_PLANT)
                .build();
    }

    public static void initClay() {
        if (BlockTypes.CLAY != null) return;
        BlockTypes.CLAY = BlockTypeBuilder
                .builder(BlockClayBehavior.class)
                .vanillaBlock(BlockId.CLAY)
                .build();
    }

    public static void initClientRequestPlaceholderBlock() {
        if (BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK != null) return;
        BlockTypes.CLIENT_REQUEST_PLACEHOLDER_BLOCK = BlockTypeBuilder
                .builder(BlockClientRequestPlaceholderBlockBehavior.class)
                .vanillaBlock(BlockId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
                .build();
    }

    public static void initCoalBlock() {
        if (BlockTypes.COAL_BLOCK != null) return;
        BlockTypes.COAL_BLOCK = BlockTypeBuilder
                .builder(BlockCoalBlockBehavior.class)
                .vanillaBlock(BlockId.COAL_BLOCK)
                .build();
    }

    public static void initCoalOre() {
        if (BlockTypes.COAL_ORE != null) return;
        BlockTypes.COAL_ORE = BlockTypeBuilder
                .builder(BlockCoalOreBehavior.class)
                .vanillaBlock(BlockId.COAL_ORE)
                .build();
    }

    public static void initCoarseDirt() {
        if (BlockTypes.COARSE_DIRT != null) return;
        BlockTypes.COARSE_DIRT = BlockTypeBuilder
                .builder(BlockCoarseDirtBehavior.class)
                .vanillaBlock(BlockId.COARSE_DIRT)
                .build();
    }

    public static void initCobbledDeepslate() {
        if (BlockTypes.COBBLED_DEEPSLATE != null) return;
        BlockTypes.COBBLED_DEEPSLATE = BlockTypeBuilder
                .builder(BlockCobbledDeepslateBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE)
                .build();
    }

    public static void initCobbledDeepslateDoubleSlab() {
        if (BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB != null) return;
        BlockTypes.COBBLED_DEEPSLATE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockCobbledDeepslateDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobbledDeepslateSlab() {
        if (BlockTypes.COBBLED_DEEPSLATE_SLAB != null) return;
        BlockTypes.COBBLED_DEEPSLATE_SLAB = BlockTypeBuilder
                .builder(BlockCobbledDeepslateSlabBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobbledDeepslateStairs() {
        if (BlockTypes.COBBLED_DEEPSLATE_STAIRS != null) return;
        BlockTypes.COBBLED_DEEPSLATE_STAIRS = BlockTypeBuilder
                .builder(BlockCobbledDeepslateStairsBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCobbledDeepslateWall() {
        if (BlockTypes.COBBLED_DEEPSLATE_WALL != null) return;
        BlockTypes.COBBLED_DEEPSLATE_WALL = BlockTypeBuilder
                .builder(BlockCobbledDeepslateWallBehavior.class)
                .vanillaBlock(BlockId.COBBLED_DEEPSLATE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initCobblestone() {
        if (BlockTypes.COBBLESTONE != null) return;
        BlockTypes.COBBLESTONE = BlockTypeBuilder
                .builder(BlockCobblestoneBehavior.class)
                .vanillaBlock(BlockId.COBBLESTONE)
                .build();
    }

    public static void initCobblestoneDoubleSlab() {
        if (BlockTypes.COBBLESTONE_DOUBLE_SLAB != null) return;
        BlockTypes.COBBLESTONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockCobblestoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.COBBLESTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobblestoneSlab() {
        if (BlockTypes.COBBLESTONE_SLAB != null) return;
        BlockTypes.COBBLESTONE_SLAB = BlockTypeBuilder
                .builder(BlockCobblestoneSlabBehavior.class)
                .vanillaBlock(BlockId.COBBLESTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCobblestoneWall() {
        if (BlockTypes.COBBLESTONE_WALL != null) return;
        BlockTypes.COBBLESTONE_WALL = BlockTypeBuilder
                .builder(BlockCobblestoneWallBehavior.class)
                .vanillaBlock(BlockId.COBBLESTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_BLOCK_TYPE, BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initCocoa() {
        if (BlockTypes.COCOA != null) return;
        BlockTypes.COCOA = BlockTypeBuilder
                .builder(BlockCocoaBehavior.class)
                .vanillaBlock(BlockId.COCOA)
                .setProperties(BlockPropertyTypes.AGE_3, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initColoredTorchBp() {
        if (BlockTypes.COLORED_TORCH_BP != null) return;
        BlockTypes.COLORED_TORCH_BP = BlockTypeBuilder
                .builder(BlockColoredTorchBpBehavior.class)
                .vanillaBlock(BlockId.COLORED_TORCH_BP)
                .setProperties(BlockPropertyTypes.COLOR_BIT, BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initColoredTorchRg() {
        if (BlockTypes.COLORED_TORCH_RG != null) return;
        BlockTypes.COLORED_TORCH_RG = BlockTypeBuilder
                .builder(BlockColoredTorchRgBehavior.class)
                .vanillaBlock(BlockId.COLORED_TORCH_RG)
                .setProperties(BlockPropertyTypes.COLOR_BIT, BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initCommandBlock() {
        if (BlockTypes.COMMAND_BLOCK != null) return;
        BlockTypes.COMMAND_BLOCK = BlockTypeBuilder
                .builder(BlockCommandBlockBehavior.class)
                .vanillaBlock(BlockId.COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initComposter() {
        if (BlockTypes.COMPOSTER != null) return;
        BlockTypes.COMPOSTER = BlockTypeBuilder
                .builder(BlockComposterBehavior.class)
                .vanillaBlock(BlockId.COMPOSTER)
                .setProperties(BlockPropertyTypes.COMPOSTER_FILL_LEVEL)
                .build();
    }

    public static void initConduit() {
        if (BlockTypes.CONDUIT != null) return;
        BlockTypes.CONDUIT = BlockTypeBuilder
                .builder(BlockConduitBehavior.class)
                .vanillaBlock(BlockId.CONDUIT)
                .build();
    }

    public static void initCopperBlock() {
        if (BlockTypes.COPPER_BLOCK != null) return;
        BlockTypes.COPPER_BLOCK = BlockTypeBuilder
                .builder(BlockCopperBlockBehavior.class)
                .vanillaBlock(BlockId.COPPER_BLOCK)
                .build();
    }

    public static void initCopperBulb() {
        if (BlockTypes.COPPER_BULB != null) return;
        BlockTypes.COPPER_BULB = BlockTypeBuilder
                .builder(BlockCopperBulbBehavior.class)
                .vanillaBlock(BlockId.COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initCopperDoor() {
        if (BlockTypes.COPPER_DOOR != null) return;
        BlockTypes.COPPER_DOOR = BlockTypeBuilder
                .builder(BlockCopperDoorBehavior.class)
                .vanillaBlock(BlockId.COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initCopperGrate() {
        if (BlockTypes.COPPER_GRATE != null) return;
        BlockTypes.COPPER_GRATE = BlockTypeBuilder
                .builder(BlockCopperGrateBehavior.class)
                .vanillaBlock(BlockId.COPPER_GRATE)
                .build();
    }

    public static void initCopperOre() {
        if (BlockTypes.COPPER_ORE != null) return;
        BlockTypes.COPPER_ORE = BlockTypeBuilder
                .builder(BlockCopperOreBehavior.class)
                .vanillaBlock(BlockId.COPPER_ORE)
                .build();
    }

    public static void initCopperTrapdoor() {
        if (BlockTypes.COPPER_TRAPDOOR != null) return;
        BlockTypes.COPPER_TRAPDOOR = BlockTypeBuilder
                .builder(BlockCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initCornflower() {
        if (BlockTypes.CORNFLOWER != null) return;
        BlockTypes.CORNFLOWER = BlockTypeBuilder
                .builder(BlockCornflowerBehavior.class)
                .vanillaBlock(BlockId.CORNFLOWER)
                .build();
    }

    public static void initCrackedDeepslateBricks() {
        if (BlockTypes.CRACKED_DEEPSLATE_BRICKS != null) return;
        BlockTypes.CRACKED_DEEPSLATE_BRICKS = BlockTypeBuilder
                .builder(BlockCrackedDeepslateBricksBehavior.class)
                .vanillaBlock(BlockId.CRACKED_DEEPSLATE_BRICKS)
                .build();
    }

    public static void initCrackedDeepslateTiles() {
        if (BlockTypes.CRACKED_DEEPSLATE_TILES != null) return;
        BlockTypes.CRACKED_DEEPSLATE_TILES = BlockTypeBuilder
                .builder(BlockCrackedDeepslateTilesBehavior.class)
                .vanillaBlock(BlockId.CRACKED_DEEPSLATE_TILES)
                .build();
    }

    public static void initCrackedNetherBricks() {
        if (BlockTypes.CRACKED_NETHER_BRICKS != null) return;
        BlockTypes.CRACKED_NETHER_BRICKS = BlockTypeBuilder
                .builder(BlockCrackedNetherBricksBehavior.class)
                .vanillaBlock(BlockId.CRACKED_NETHER_BRICKS)
                .build();
    }

    public static void initCrackedPolishedBlackstoneBricks() {
        if (BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS != null) return;
        BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS = BlockTypeBuilder
                .builder(BlockCrackedPolishedBlackstoneBricksBehavior.class)
                .vanillaBlock(BlockId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
                .build();
    }

    public static void initCrackedStoneBricks() {
        if (BlockTypes.CRACKED_STONE_BRICKS != null) return;
        BlockTypes.CRACKED_STONE_BRICKS = BlockTypeBuilder
                .builder(BlockCrackedStoneBricksBehavior.class)
                .vanillaBlock(BlockId.CRACKED_STONE_BRICKS)
                .build();
    }

    public static void initCrafter() {
        if (BlockTypes.CRAFTER != null) return;
        BlockTypes.CRAFTER = BlockTypeBuilder
                .builder(BlockCrafterBehavior.class)
                .vanillaBlock(BlockId.CRAFTER)
                .setProperties(BlockPropertyTypes.CRAFTING, BlockPropertyTypes.ORIENTATION, BlockPropertyTypes.TRIGGERED_BIT)
                .build();
    }

    public static void initCraftingTable() {
        if (BlockTypes.CRAFTING_TABLE != null) return;
        BlockTypes.CRAFTING_TABLE = BlockTypeBuilder
                .builder(BlockCraftingTableBehavior.class)
                .vanillaBlock(BlockId.CRAFTING_TABLE)
                .build();
    }

    public static void initCrimsonButton() {
        if (BlockTypes.CRIMSON_BUTTON != null) return;
        BlockTypes.CRIMSON_BUTTON = BlockTypeBuilder
                .builder(BlockCrimsonButtonBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCrimsonDoor() {
        if (BlockTypes.CRIMSON_DOOR != null) return;
        BlockTypes.CRIMSON_DOOR = BlockTypeBuilder
                .builder(BlockCrimsonDoorBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initCrimsonDoubleSlab() {
        if (BlockTypes.CRIMSON_DOUBLE_SLAB != null) return;
        BlockTypes.CRIMSON_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockCrimsonDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCrimsonFence() {
        if (BlockTypes.CRIMSON_FENCE != null) return;
        BlockTypes.CRIMSON_FENCE = BlockTypeBuilder
                .builder(BlockCrimsonFenceBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_FENCE)
                .build();
    }

    public static void initCrimsonFenceGate() {
        if (BlockTypes.CRIMSON_FENCE_GATE != null) return;
        BlockTypes.CRIMSON_FENCE_GATE = BlockTypeBuilder
                .builder(BlockCrimsonFenceGateBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initCrimsonFungus() {
        if (BlockTypes.CRIMSON_FUNGUS != null) return;
        BlockTypes.CRIMSON_FUNGUS = BlockTypeBuilder
                .builder(BlockCrimsonFungusBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_FUNGUS)
                .build();
    }

    public static void initCrimsonHangingSign() {
        if (BlockTypes.CRIMSON_HANGING_SIGN != null) return;
        BlockTypes.CRIMSON_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockCrimsonHangingSignBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initCrimsonHyphae() {
        if (BlockTypes.CRIMSON_HYPHAE != null) return;
        BlockTypes.CRIMSON_HYPHAE = BlockTypeBuilder
                .builder(BlockCrimsonHyphaeBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initCrimsonNylium() {
        if (BlockTypes.CRIMSON_NYLIUM != null) return;
        BlockTypes.CRIMSON_NYLIUM = BlockTypeBuilder
                .builder(BlockCrimsonNyliumBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_NYLIUM)
                .build();
    }

    public static void initCrimsonPlanks() {
        if (BlockTypes.CRIMSON_PLANKS != null) return;
        BlockTypes.CRIMSON_PLANKS = BlockTypeBuilder
                .builder(BlockCrimsonPlanksBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_PLANKS)
                .build();
    }

    public static void initCrimsonPressurePlate() {
        if (BlockTypes.CRIMSON_PRESSURE_PLATE != null) return;
        BlockTypes.CRIMSON_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockCrimsonPressurePlateBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initCrimsonRoots() {
        if (BlockTypes.CRIMSON_ROOTS != null) return;
        BlockTypes.CRIMSON_ROOTS = BlockTypeBuilder
                .builder(BlockCrimsonRootsBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_ROOTS)
                .build();
    }

    public static void initCrimsonSlab() {
        if (BlockTypes.CRIMSON_SLAB != null) return;
        BlockTypes.CRIMSON_SLAB = BlockTypeBuilder
                .builder(BlockCrimsonSlabBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCrimsonStairs() {
        if (BlockTypes.CRIMSON_STAIRS != null) return;
        BlockTypes.CRIMSON_STAIRS = BlockTypeBuilder
                .builder(BlockCrimsonStairsBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCrimsonStandingSign() {
        if (BlockTypes.CRIMSON_STANDING_SIGN != null) return;
        BlockTypes.CRIMSON_STANDING_SIGN = BlockTypeBuilder
                .builder(BlockCrimsonStandingSignBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initCrimsonStem() {
        if (BlockTypes.CRIMSON_STEM != null) return;
        BlockTypes.CRIMSON_STEM = BlockTypeBuilder
                .builder(BlockCrimsonStemBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initCrimsonTrapdoor() {
        if (BlockTypes.CRIMSON_TRAPDOOR != null) return;
        BlockTypes.CRIMSON_TRAPDOOR = BlockTypeBuilder
                .builder(BlockCrimsonTrapdoorBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initCrimsonWallSign() {
        if (BlockTypes.CRIMSON_WALL_SIGN != null) return;
        BlockTypes.CRIMSON_WALL_SIGN = BlockTypeBuilder
                .builder(BlockCrimsonWallSignBehavior.class)
                .vanillaBlock(BlockId.CRIMSON_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCryingObsidian() {
        if (BlockTypes.CRYING_OBSIDIAN != null) return;
        BlockTypes.CRYING_OBSIDIAN = BlockTypeBuilder
                .builder(BlockCryingObsidianBehavior.class)
                .vanillaBlock(BlockId.CRYING_OBSIDIAN)
                .build();
    }

    public static void initCutCopper() {
        if (BlockTypes.CUT_COPPER != null) return;
        BlockTypes.CUT_COPPER = BlockTypeBuilder
                .builder(BlockCutCopperBehavior.class)
                .vanillaBlock(BlockId.CUT_COPPER)
                .build();
    }

    public static void initCutCopperSlab() {
        if (BlockTypes.CUT_COPPER_SLAB != null) return;
        BlockTypes.CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutCopperStairs() {
        if (BlockTypes.CUT_COPPER_STAIRS != null) return;
        BlockTypes.CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initCutRedSandstone() {
        if (BlockTypes.CUT_RED_SANDSTONE != null) return;
        BlockTypes.CUT_RED_SANDSTONE = BlockTypeBuilder
                .builder(BlockCutRedSandstoneBehavior.class)
                .vanillaBlock(BlockId.CUT_RED_SANDSTONE)
                .build();
    }

    public static void initCutRedSandstoneDoubleSlab() {
        if (BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.CUT_RED_SANDSTONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockCutRedSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.CUT_RED_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutRedSandstoneSlab() {
        if (BlockTypes.CUT_RED_SANDSTONE_SLAB != null) return;
        BlockTypes.CUT_RED_SANDSTONE_SLAB = BlockTypeBuilder
                .builder(BlockCutRedSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.CUT_RED_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutSandstone() {
        if (BlockTypes.CUT_SANDSTONE != null) return;
        BlockTypes.CUT_SANDSTONE = BlockTypeBuilder
                .builder(BlockCutSandstoneBehavior.class)
                .vanillaBlock(BlockId.CUT_SANDSTONE)
                .build();
    }

    public static void initCutSandstoneDoubleSlab() {
        if (BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.CUT_SANDSTONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockCutSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.CUT_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCutSandstoneSlab() {
        if (BlockTypes.CUT_SANDSTONE_SLAB != null) return;
        BlockTypes.CUT_SANDSTONE_SLAB = BlockTypeBuilder
                .builder(BlockCutSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.CUT_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initCyanCandle() {
        if (BlockTypes.CYAN_CANDLE != null) return;
        BlockTypes.CYAN_CANDLE = BlockTypeBuilder
                .builder(BlockCyanCandleBehavior.class)
                .vanillaBlock(BlockId.CYAN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCyanCandleCake() {
        if (BlockTypes.CYAN_CANDLE_CAKE != null) return;
        BlockTypes.CYAN_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockCyanCandleCakeBehavior.class)
                .vanillaBlock(BlockId.CYAN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initCyanCarpet() {
        if (BlockTypes.CYAN_CARPET != null) return;
        BlockTypes.CYAN_CARPET = BlockTypeBuilder
                .builder(BlockCyanCarpetBehavior.class)
                .vanillaBlock(BlockId.CYAN_CARPET)
                .build();
    }

    public static void initCyanConcrete() {
        if (BlockTypes.CYAN_CONCRETE != null) return;
        BlockTypes.CYAN_CONCRETE = BlockTypeBuilder
                .builder(BlockCyanConcreteBehavior.class)
                .vanillaBlock(BlockId.CYAN_CONCRETE)
                .build();
    }

    public static void initCyanConcretePowder() {
        if (BlockTypes.CYAN_CONCRETE_POWDER != null) return;
        BlockTypes.CYAN_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockCyanConcretePowderBehavior.class)
                .vanillaBlock(BlockId.CYAN_CONCRETE_POWDER)
                .build();
    }

    public static void initCyanGlazedTerracotta() {
        if (BlockTypes.CYAN_GLAZED_TERRACOTTA != null) return;
        BlockTypes.CYAN_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockCyanGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.CYAN_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initCyanShulkerBox() {
        if (BlockTypes.CYAN_SHULKER_BOX != null) return;
        BlockTypes.CYAN_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockCyanShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.CYAN_SHULKER_BOX)
                .build();
    }

    public static void initCyanStainedGlass() {
        if (BlockTypes.CYAN_STAINED_GLASS != null) return;
        BlockTypes.CYAN_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockCyanStainedGlassBehavior.class)
                .vanillaBlock(BlockId.CYAN_STAINED_GLASS)
                .build();
    }

    public static void initCyanStainedGlassPane() {
        if (BlockTypes.CYAN_STAINED_GLASS_PANE != null) return;
        BlockTypes.CYAN_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockCyanStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.CYAN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initCyanTerracotta() {
        if (BlockTypes.CYAN_TERRACOTTA != null) return;
        BlockTypes.CYAN_TERRACOTTA = BlockTypeBuilder
                .builder(BlockCyanTerracottaBehavior.class)
                .vanillaBlock(BlockId.CYAN_TERRACOTTA)
                .build();
    }

    public static void initCyanWool() {
        if (BlockTypes.CYAN_WOOL != null) return;
        BlockTypes.CYAN_WOOL = BlockTypeBuilder
                .builder(BlockCyanWoolBehavior.class)
                .vanillaBlock(BlockId.CYAN_WOOL)
                .build();
    }

    public static void initDamagedAnvil() {
        if (BlockTypes.DAMAGED_ANVIL != null) return;
        BlockTypes.DAMAGED_ANVIL = BlockTypeBuilder
                .builder(BlockDamagedAnvilBehavior.class)
                .vanillaBlock(BlockId.DAMAGED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initDandelion() {
        if (BlockTypes.DANDELION != null) return;
        BlockTypes.DANDELION = BlockTypeBuilder
                .builder(BlockDandelionBehavior.class)
                .vanillaBlock(BlockId.DANDELION)
                .build();
    }

    public static void initDarkOakButton() {
        if (BlockTypes.DARK_OAK_BUTTON != null) return;
        BlockTypes.DARK_OAK_BUTTON = BlockTypeBuilder
                .builder(BlockDarkOakButtonBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initDarkOakDoor() {
        if (BlockTypes.DARK_OAK_DOOR != null) return;
        BlockTypes.DARK_OAK_DOOR = BlockTypeBuilder
                .builder(BlockDarkOakDoorBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initDarkOakDoubleSlab() {
        if (BlockTypes.DARK_OAK_DOUBLE_SLAB != null) return;
        BlockTypes.DARK_OAK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockDarkOakDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkOakFence() {
        if (BlockTypes.DARK_OAK_FENCE != null) return;
        BlockTypes.DARK_OAK_FENCE = BlockTypeBuilder
                .builder(BlockDarkOakFenceBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_FENCE)
                .build();
    }

    public static void initDarkOakFenceGate() {
        if (BlockTypes.DARK_OAK_FENCE_GATE != null) return;
        BlockTypes.DARK_OAK_FENCE_GATE = BlockTypeBuilder
                .builder(BlockDarkOakFenceGateBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initDarkOakHangingSign() {
        if (BlockTypes.DARK_OAK_HANGING_SIGN != null) return;
        BlockTypes.DARK_OAK_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockDarkOakHangingSignBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initDarkOakLeaves() {
        if (BlockTypes.DARK_OAK_LEAVES != null) return;
        BlockTypes.DARK_OAK_LEAVES = BlockTypeBuilder
                .builder(BlockDarkOakLeavesBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initDarkOakLog() {
        if (BlockTypes.DARK_OAK_LOG != null) return;
        BlockTypes.DARK_OAK_LOG = BlockTypeBuilder
                .builder(BlockDarkOakLogBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDarkOakPlanks() {
        if (BlockTypes.DARK_OAK_PLANKS != null) return;
        BlockTypes.DARK_OAK_PLANKS = BlockTypeBuilder
                .builder(BlockDarkOakPlanksBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_PLANKS)
                .build();
    }

    public static void initDarkOakPressurePlate() {
        if (BlockTypes.DARK_OAK_PRESSURE_PLATE != null) return;
        BlockTypes.DARK_OAK_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockDarkOakPressurePlateBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initDarkOakSapling() {
        if (BlockTypes.DARK_OAK_SAPLING != null) return;
        BlockTypes.DARK_OAK_SAPLING = BlockTypeBuilder
                .builder(BlockDarkOakSaplingBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initDarkOakSlab() {
        if (BlockTypes.DARK_OAK_SLAB != null) return;
        BlockTypes.DARK_OAK_SLAB = BlockTypeBuilder
                .builder(BlockDarkOakSlabBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkOakStairs() {
        if (BlockTypes.DARK_OAK_STAIRS != null) return;
        BlockTypes.DARK_OAK_STAIRS = BlockTypeBuilder
                .builder(BlockDarkOakStairsBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDarkOakTrapdoor() {
        if (BlockTypes.DARK_OAK_TRAPDOOR != null) return;
        BlockTypes.DARK_OAK_TRAPDOOR = BlockTypeBuilder
                .builder(BlockDarkOakTrapdoorBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initDarkOakWood() {
        if (BlockTypes.DARK_OAK_WOOD != null) return;
        BlockTypes.DARK_OAK_WOOD = BlockTypeBuilder
                .builder(BlockDarkOakWoodBehavior.class)
                .vanillaBlock(BlockId.DARK_OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDarkPrismarine() {
        if (BlockTypes.DARK_PRISMARINE != null) return;
        BlockTypes.DARK_PRISMARINE = BlockTypeBuilder
                .builder(BlockDarkPrismarineBehavior.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE)
                .build();
    }

    public static void initDarkPrismarineDoubleSlab() {
        if (BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB != null) return;
        BlockTypes.DARK_PRISMARINE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockDarkPrismarineDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkPrismarineSlab() {
        if (BlockTypes.DARK_PRISMARINE_SLAB != null) return;
        BlockTypes.DARK_PRISMARINE_SLAB = BlockTypeBuilder
                .builder(BlockDarkPrismarineSlabBehavior.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDarkPrismarineStairs() {
        if (BlockTypes.DARK_PRISMARINE_STAIRS != null) return;
        BlockTypes.DARK_PRISMARINE_STAIRS = BlockTypeBuilder
                .builder(BlockDarkPrismarineStairsBehavior.class)
                .vanillaBlock(BlockId.DARK_PRISMARINE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDarkoakStandingSign() {
        if (BlockTypes.DARKOAK_STANDING_SIGN != null) return;
        BlockTypes.DARKOAK_STANDING_SIGN = BlockTypeBuilder
                .builder(BlockDarkoakStandingSignBehavior.class)
                .vanillaBlock(BlockId.DARKOAK_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initDarkoakWallSign() {
        if (BlockTypes.DARKOAK_WALL_SIGN != null) return;
        BlockTypes.DARKOAK_WALL_SIGN = BlockTypeBuilder
                .builder(BlockDarkoakWallSignBehavior.class)
                .vanillaBlock(BlockId.DARKOAK_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initDaylightDetector() {
        if (BlockTypes.DAYLIGHT_DETECTOR != null) return;
        BlockTypes.DAYLIGHT_DETECTOR = BlockTypeBuilder
                .builder(BlockDaylightDetectorBehavior.class)
                .vanillaBlock(BlockId.DAYLIGHT_DETECTOR)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initDaylightDetectorInverted() {
        if (BlockTypes.DAYLIGHT_DETECTOR_INVERTED != null) return;
        BlockTypes.DAYLIGHT_DETECTOR_INVERTED = BlockTypeBuilder
                .builder(BlockDaylightDetectorInvertedBehavior.class)
                .vanillaBlock(BlockId.DAYLIGHT_DETECTOR_INVERTED)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initDeadBrainCoral() {
        if (BlockTypes.DEAD_BRAIN_CORAL != null) return;
        BlockTypes.DEAD_BRAIN_CORAL = BlockTypeBuilder
                .builder(BlockDeadBrainCoralBehavior.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL)
                .build();
    }

    public static void initDeadBrainCoralBlock() {
        if (BlockTypes.DEAD_BRAIN_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_BRAIN_CORAL_BLOCK = BlockTypeBuilder
                .builder(BlockDeadBrainCoralBlockBehavior.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_BLOCK)
                .build();
    }

    public static void initDeadBrainCoralFan() {
        if (BlockTypes.DEAD_BRAIN_CORAL_FAN != null) return;
        BlockTypes.DEAD_BRAIN_CORAL_FAN = BlockTypeBuilder
                .builder(BlockDeadBrainCoralFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadBrainCoralWallFan() {
        if (BlockTypes.DEAD_BRAIN_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_BRAIN_CORAL_WALL_FAN = BlockTypeBuilder
                .builder(BlockDeadBrainCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_BRAIN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadBubbleCoral() {
        if (BlockTypes.DEAD_BUBBLE_CORAL != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL = BlockTypeBuilder
                .builder(BlockDeadBubbleCoralBehavior.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL)
                .build();
    }

    public static void initDeadBubbleCoralBlock() {
        if (BlockTypes.DEAD_BUBBLE_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL_BLOCK = BlockTypeBuilder
                .builder(BlockDeadBubbleCoralBlockBehavior.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadBubbleCoralFan() {
        if (BlockTypes.DEAD_BUBBLE_CORAL_FAN != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL_FAN = BlockTypeBuilder
                .builder(BlockDeadBubbleCoralFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadBubbleCoralWallFan() {
        if (BlockTypes.DEAD_BUBBLE_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_BUBBLE_CORAL_WALL_FAN = BlockTypeBuilder
                .builder(BlockDeadBubbleCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_BUBBLE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadFireCoral() {
        if (BlockTypes.DEAD_FIRE_CORAL != null) return;
        BlockTypes.DEAD_FIRE_CORAL = BlockTypeBuilder
                .builder(BlockDeadFireCoralBehavior.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL)
                .build();
    }

    public static void initDeadFireCoralBlock() {
        if (BlockTypes.DEAD_FIRE_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_FIRE_CORAL_BLOCK = BlockTypeBuilder
                .builder(BlockDeadFireCoralBlockBehavior.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadFireCoralFan() {
        if (BlockTypes.DEAD_FIRE_CORAL_FAN != null) return;
        BlockTypes.DEAD_FIRE_CORAL_FAN = BlockTypeBuilder
                .builder(BlockDeadFireCoralFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadFireCoralWallFan() {
        if (BlockTypes.DEAD_FIRE_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_FIRE_CORAL_WALL_FAN = BlockTypeBuilder
                .builder(BlockDeadFireCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_FIRE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadHornCoral() {
        if (BlockTypes.DEAD_HORN_CORAL != null) return;
        BlockTypes.DEAD_HORN_CORAL = BlockTypeBuilder
                .builder(BlockDeadHornCoralBehavior.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL)
                .build();
    }

    public static void initDeadHornCoralBlock() {
        if (BlockTypes.DEAD_HORN_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_HORN_CORAL_BLOCK = BlockTypeBuilder
                .builder(BlockDeadHornCoralBlockBehavior.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL_BLOCK)
                .build();
    }

    public static void initDeadHornCoralFan() {
        if (BlockTypes.DEAD_HORN_CORAL_FAN != null) return;
        BlockTypes.DEAD_HORN_CORAL_FAN = BlockTypeBuilder
                .builder(BlockDeadHornCoralFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadHornCoralWallFan() {
        if (BlockTypes.DEAD_HORN_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_HORN_CORAL_WALL_FAN = BlockTypeBuilder
                .builder(BlockDeadHornCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_HORN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadTubeCoral() {
        if (BlockTypes.DEAD_TUBE_CORAL != null) return;
        BlockTypes.DEAD_TUBE_CORAL = BlockTypeBuilder
                .builder(BlockDeadTubeCoralBehavior.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL)
                .build();
    }

    public static void initDeadTubeCoralBlock() {
        if (BlockTypes.DEAD_TUBE_CORAL_BLOCK != null) return;
        BlockTypes.DEAD_TUBE_CORAL_BLOCK = BlockTypeBuilder
                .builder(BlockDeadTubeCoralBlockBehavior.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL_BLOCK)
                .build();
    }

    public static void initDeadTubeCoralFan() {
        if (BlockTypes.DEAD_TUBE_CORAL_FAN != null) return;
        BlockTypes.DEAD_TUBE_CORAL_FAN = BlockTypeBuilder
                .builder(BlockDeadTubeCoralFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initDeadTubeCoralWallFan() {
        if (BlockTypes.DEAD_TUBE_CORAL_WALL_FAN != null) return;
        BlockTypes.DEAD_TUBE_CORAL_WALL_FAN = BlockTypeBuilder
                .builder(BlockDeadTubeCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.DEAD_TUBE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initDeadbush() {
        if (BlockTypes.DEADBUSH != null) return;
        BlockTypes.DEADBUSH = BlockTypeBuilder
                .builder(BlockDeadbushBehavior.class)
                .vanillaBlock(BlockId.DEADBUSH)
                .build();
    }

    public static void initDecoratedPot() {
        if (BlockTypes.DECORATED_POT != null) return;
        BlockTypes.DECORATED_POT = BlockTypeBuilder
                .builder(BlockDecoratedPotBehavior.class)
                .vanillaBlock(BlockId.DECORATED_POT)
                .setProperties(BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initDeepslate() {
        if (BlockTypes.DEEPSLATE != null) return;
        BlockTypes.DEEPSLATE = BlockTypeBuilder
                .builder(BlockDeepslateBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initDeepslateBrickDoubleSlab() {
        if (BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.DEEPSLATE_BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockDeepslateBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateBrickSlab() {
        if (BlockTypes.DEEPSLATE_BRICK_SLAB != null) return;
        BlockTypes.DEEPSLATE_BRICK_SLAB = BlockTypeBuilder
                .builder(BlockDeepslateBrickSlabBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateBrickStairs() {
        if (BlockTypes.DEEPSLATE_BRICK_STAIRS != null) return;
        BlockTypes.DEEPSLATE_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockDeepslateBrickStairsBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDeepslateBrickWall() {
        if (BlockTypes.DEEPSLATE_BRICK_WALL != null) return;
        BlockTypes.DEEPSLATE_BRICK_WALL = BlockTypeBuilder
                .builder(BlockDeepslateBrickWallBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initDeepslateBricks() {
        if (BlockTypes.DEEPSLATE_BRICKS != null) return;
        BlockTypes.DEEPSLATE_BRICKS = BlockTypeBuilder
                .builder(BlockDeepslateBricksBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_BRICKS)
                .build();
    }

    public static void initDeepslateCoalOre() {
        if (BlockTypes.DEEPSLATE_COAL_ORE != null) return;
        BlockTypes.DEEPSLATE_COAL_ORE = BlockTypeBuilder
                .builder(BlockDeepslateCoalOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_COAL_ORE)
                .build();
    }

    public static void initDeepslateCopperOre() {
        if (BlockTypes.DEEPSLATE_COPPER_ORE != null) return;
        BlockTypes.DEEPSLATE_COPPER_ORE = BlockTypeBuilder
                .builder(BlockDeepslateCopperOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_COPPER_ORE)
                .build();
    }

    public static void initDeepslateDiamondOre() {
        if (BlockTypes.DEEPSLATE_DIAMOND_ORE != null) return;
        BlockTypes.DEEPSLATE_DIAMOND_ORE = BlockTypeBuilder
                .builder(BlockDeepslateDiamondOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_DIAMOND_ORE)
                .build();
    }

    public static void initDeepslateEmeraldOre() {
        if (BlockTypes.DEEPSLATE_EMERALD_ORE != null) return;
        BlockTypes.DEEPSLATE_EMERALD_ORE = BlockTypeBuilder
                .builder(BlockDeepslateEmeraldOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_EMERALD_ORE)
                .build();
    }

    public static void initDeepslateGoldOre() {
        if (BlockTypes.DEEPSLATE_GOLD_ORE != null) return;
        BlockTypes.DEEPSLATE_GOLD_ORE = BlockTypeBuilder
                .builder(BlockDeepslateGoldOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_GOLD_ORE)
                .build();
    }

    public static void initDeepslateIronOre() {
        if (BlockTypes.DEEPSLATE_IRON_ORE != null) return;
        BlockTypes.DEEPSLATE_IRON_ORE = BlockTypeBuilder
                .builder(BlockDeepslateIronOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_IRON_ORE)
                .build();
    }

    public static void initDeepslateLapisOre() {
        if (BlockTypes.DEEPSLATE_LAPIS_ORE != null) return;
        BlockTypes.DEEPSLATE_LAPIS_ORE = BlockTypeBuilder
                .builder(BlockDeepslateLapisOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_LAPIS_ORE)
                .build();
    }

    public static void initDeepslateRedstoneOre() {
        if (BlockTypes.DEEPSLATE_REDSTONE_ORE != null) return;
        BlockTypes.DEEPSLATE_REDSTONE_ORE = BlockTypeBuilder
                .builder(BlockDeepslateRedstoneOreBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_REDSTONE_ORE)
                .build();
    }

    public static void initDeepslateTileDoubleSlab() {
        if (BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB != null) return;
        BlockTypes.DEEPSLATE_TILE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockDeepslateTileDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateTileSlab() {
        if (BlockTypes.DEEPSLATE_TILE_SLAB != null) return;
        BlockTypes.DEEPSLATE_TILE_SLAB = BlockTypeBuilder
                .builder(BlockDeepslateTileSlabBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDeepslateTileStairs() {
        if (BlockTypes.DEEPSLATE_TILE_STAIRS != null) return;
        BlockTypes.DEEPSLATE_TILE_STAIRS = BlockTypeBuilder
                .builder(BlockDeepslateTileStairsBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDeepslateTileWall() {
        if (BlockTypes.DEEPSLATE_TILE_WALL != null) return;
        BlockTypes.DEEPSLATE_TILE_WALL = BlockTypeBuilder
                .builder(BlockDeepslateTileWallBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initDeepslateTiles() {
        if (BlockTypes.DEEPSLATE_TILES != null) return;
        BlockTypes.DEEPSLATE_TILES = BlockTypeBuilder
                .builder(BlockDeepslateTilesBehavior.class)
                .vanillaBlock(BlockId.DEEPSLATE_TILES)
                .build();
    }

    public static void initDeny() {
        if (BlockTypes.DENY != null) return;
        BlockTypes.DENY = BlockTypeBuilder
                .builder(BlockDenyBehavior.class)
                .vanillaBlock(BlockId.DENY)
                .build();
    }

    public static void initDeprecatedAnvil() {
        if (BlockTypes.DEPRECATED_ANVIL != null) return;
        BlockTypes.DEPRECATED_ANVIL = BlockTypeBuilder
                .builder(BlockDeprecatedAnvilBehavior.class)
                .vanillaBlock(BlockId.DEPRECATED_ANVIL)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initDetectorRail() {
        if (BlockTypes.DETECTOR_RAIL != null) return;
        BlockTypes.DETECTOR_RAIL = BlockTypeBuilder
                .builder(BlockDetectorRailBehavior.class)
                .vanillaBlock(BlockId.DETECTOR_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .build();
    }

    public static void initDiamondBlock() {
        if (BlockTypes.DIAMOND_BLOCK != null) return;
        BlockTypes.DIAMOND_BLOCK = BlockTypeBuilder
                .builder(BlockDiamondBlockBehavior.class)
                .vanillaBlock(BlockId.DIAMOND_BLOCK)
                .build();
    }

    public static void initDiamondOre() {
        if (BlockTypes.DIAMOND_ORE != null) return;
        BlockTypes.DIAMOND_ORE = BlockTypeBuilder
                .builder(BlockDiamondOreBehavior.class)
                .vanillaBlock(BlockId.DIAMOND_ORE)
                .build();
    }

    public static void initDiorite() {
        if (BlockTypes.DIORITE != null) return;
        BlockTypes.DIORITE = BlockTypeBuilder
                .builder(BlockDioriteBehavior.class)
                .vanillaBlock(BlockId.DIORITE)
                .build();
    }

    public static void initDioriteDoubleSlab() {
        if (BlockTypes.DIORITE_DOUBLE_SLAB != null) return;
        BlockTypes.DIORITE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockDioriteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.DIORITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDioriteSlab() {
        if (BlockTypes.DIORITE_SLAB != null) return;
        BlockTypes.DIORITE_SLAB = BlockTypeBuilder
                .builder(BlockDioriteSlabBehavior.class)
                .vanillaBlock(BlockId.DIORITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDioriteStairs() {
        if (BlockTypes.DIORITE_STAIRS != null) return;
        BlockTypes.DIORITE_STAIRS = BlockTypeBuilder
                .builder(BlockDioriteStairsBehavior.class)
                .vanillaBlock(BlockId.DIORITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initDirt() {
        if (BlockTypes.DIRT != null) return;
        BlockTypes.DIRT = BlockTypeBuilder
                .builder(BlockDirtBehavior.class)
                .vanillaBlock(BlockId.DIRT)
                .build();
    }

    public static void initDirtWithRoots() {
        if (BlockTypes.DIRT_WITH_ROOTS != null) return;
        BlockTypes.DIRT_WITH_ROOTS = BlockTypeBuilder
                .builder(BlockDirtWithRootsBehavior.class)
                .vanillaBlock(BlockId.DIRT_WITH_ROOTS)
                .build();
    }

    public static void initDispenser() {
        if (BlockTypes.DISPENSER != null) return;
        BlockTypes.DISPENSER = BlockTypeBuilder
                .builder(BlockDispenserBehavior.class)
                .vanillaBlock(BlockId.DISPENSER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                .build();
    }

    public static void initDoubleCutCopperSlab() {
        if (BlockTypes.DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.DOUBLE_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initDragonEgg() {
        if (BlockTypes.DRAGON_EGG != null) return;
        BlockTypes.DRAGON_EGG = BlockTypeBuilder
                .builder(BlockDragonEggBehavior.class)
                .vanillaBlock(BlockId.DRAGON_EGG)
                .build();
    }

    public static void initDriedKelpBlock() {
        if (BlockTypes.DRIED_KELP_BLOCK != null) return;
        BlockTypes.DRIED_KELP_BLOCK = BlockTypeBuilder
                .builder(BlockDriedKelpBlockBehavior.class)
                .vanillaBlock(BlockId.DRIED_KELP_BLOCK)
                .build();
    }

    public static void initDripstoneBlock() {
        if (BlockTypes.DRIPSTONE_BLOCK != null) return;
        BlockTypes.DRIPSTONE_BLOCK = BlockTypeBuilder
                .builder(BlockDripstoneBlockBehavior.class)
                .vanillaBlock(BlockId.DRIPSTONE_BLOCK)
                .build();
    }

    public static void initDropper() {
        if (BlockTypes.DROPPER != null) return;
        BlockTypes.DROPPER = BlockTypeBuilder
                .builder(BlockDropperBehavior.class)
                .vanillaBlock(BlockId.DROPPER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TRIGGERED_BIT)
                .build();
    }

    public static void initElement0() {
        if (BlockTypes.ELEMENT_0 != null) return;
        BlockTypes.ELEMENT_0 = BlockTypeBuilder
                .builder(BlockElement0Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_0)
                .build();
    }

    public static void initElement1() {
        if (BlockTypes.ELEMENT_1 != null) return;
        BlockTypes.ELEMENT_1 = BlockTypeBuilder
                .builder(BlockElement1Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_1)
                .build();
    }

    public static void initElement10() {
        if (BlockTypes.ELEMENT_10 != null) return;
        BlockTypes.ELEMENT_10 = BlockTypeBuilder
                .builder(BlockElement10Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_10)
                .build();
    }

    public static void initElement100() {
        if (BlockTypes.ELEMENT_100 != null) return;
        BlockTypes.ELEMENT_100 = BlockTypeBuilder
                .builder(BlockElement100Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_100)
                .build();
    }

    public static void initElement101() {
        if (BlockTypes.ELEMENT_101 != null) return;
        BlockTypes.ELEMENT_101 = BlockTypeBuilder
                .builder(BlockElement101Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_101)
                .build();
    }

    public static void initElement102() {
        if (BlockTypes.ELEMENT_102 != null) return;
        BlockTypes.ELEMENT_102 = BlockTypeBuilder
                .builder(BlockElement102Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_102)
                .build();
    }

    public static void initElement103() {
        if (BlockTypes.ELEMENT_103 != null) return;
        BlockTypes.ELEMENT_103 = BlockTypeBuilder
                .builder(BlockElement103Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_103)
                .build();
    }

    public static void initElement104() {
        if (BlockTypes.ELEMENT_104 != null) return;
        BlockTypes.ELEMENT_104 = BlockTypeBuilder
                .builder(BlockElement104Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_104)
                .build();
    }

    public static void initElement105() {
        if (BlockTypes.ELEMENT_105 != null) return;
        BlockTypes.ELEMENT_105 = BlockTypeBuilder
                .builder(BlockElement105Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_105)
                .build();
    }

    public static void initElement106() {
        if (BlockTypes.ELEMENT_106 != null) return;
        BlockTypes.ELEMENT_106 = BlockTypeBuilder
                .builder(BlockElement106Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_106)
                .build();
    }

    public static void initElement107() {
        if (BlockTypes.ELEMENT_107 != null) return;
        BlockTypes.ELEMENT_107 = BlockTypeBuilder
                .builder(BlockElement107Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_107)
                .build();
    }

    public static void initElement108() {
        if (BlockTypes.ELEMENT_108 != null) return;
        BlockTypes.ELEMENT_108 = BlockTypeBuilder
                .builder(BlockElement108Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_108)
                .build();
    }

    public static void initElement109() {
        if (BlockTypes.ELEMENT_109 != null) return;
        BlockTypes.ELEMENT_109 = BlockTypeBuilder
                .builder(BlockElement109Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_109)
                .build();
    }

    public static void initElement11() {
        if (BlockTypes.ELEMENT_11 != null) return;
        BlockTypes.ELEMENT_11 = BlockTypeBuilder
                .builder(BlockElement11Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_11)
                .build();
    }

    public static void initElement110() {
        if (BlockTypes.ELEMENT_110 != null) return;
        BlockTypes.ELEMENT_110 = BlockTypeBuilder
                .builder(BlockElement110Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_110)
                .build();
    }

    public static void initElement111() {
        if (BlockTypes.ELEMENT_111 != null) return;
        BlockTypes.ELEMENT_111 = BlockTypeBuilder
                .builder(BlockElement111Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_111)
                .build();
    }

    public static void initElement112() {
        if (BlockTypes.ELEMENT_112 != null) return;
        BlockTypes.ELEMENT_112 = BlockTypeBuilder
                .builder(BlockElement112Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_112)
                .build();
    }

    public static void initElement113() {
        if (BlockTypes.ELEMENT_113 != null) return;
        BlockTypes.ELEMENT_113 = BlockTypeBuilder
                .builder(BlockElement113Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_113)
                .build();
    }

    public static void initElement114() {
        if (BlockTypes.ELEMENT_114 != null) return;
        BlockTypes.ELEMENT_114 = BlockTypeBuilder
                .builder(BlockElement114Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_114)
                .build();
    }

    public static void initElement115() {
        if (BlockTypes.ELEMENT_115 != null) return;
        BlockTypes.ELEMENT_115 = BlockTypeBuilder
                .builder(BlockElement115Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_115)
                .build();
    }

    public static void initElement116() {
        if (BlockTypes.ELEMENT_116 != null) return;
        BlockTypes.ELEMENT_116 = BlockTypeBuilder
                .builder(BlockElement116Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_116)
                .build();
    }

    public static void initElement117() {
        if (BlockTypes.ELEMENT_117 != null) return;
        BlockTypes.ELEMENT_117 = BlockTypeBuilder
                .builder(BlockElement117Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_117)
                .build();
    }

    public static void initElement118() {
        if (BlockTypes.ELEMENT_118 != null) return;
        BlockTypes.ELEMENT_118 = BlockTypeBuilder
                .builder(BlockElement118Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_118)
                .build();
    }

    public static void initElement12() {
        if (BlockTypes.ELEMENT_12 != null) return;
        BlockTypes.ELEMENT_12 = BlockTypeBuilder
                .builder(BlockElement12Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_12)
                .build();
    }

    public static void initElement13() {
        if (BlockTypes.ELEMENT_13 != null) return;
        BlockTypes.ELEMENT_13 = BlockTypeBuilder
                .builder(BlockElement13Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_13)
                .build();
    }

    public static void initElement14() {
        if (BlockTypes.ELEMENT_14 != null) return;
        BlockTypes.ELEMENT_14 = BlockTypeBuilder
                .builder(BlockElement14Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_14)
                .build();
    }

    public static void initElement15() {
        if (BlockTypes.ELEMENT_15 != null) return;
        BlockTypes.ELEMENT_15 = BlockTypeBuilder
                .builder(BlockElement15Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_15)
                .build();
    }

    public static void initElement16() {
        if (BlockTypes.ELEMENT_16 != null) return;
        BlockTypes.ELEMENT_16 = BlockTypeBuilder
                .builder(BlockElement16Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_16)
                .build();
    }

    public static void initElement17() {
        if (BlockTypes.ELEMENT_17 != null) return;
        BlockTypes.ELEMENT_17 = BlockTypeBuilder
                .builder(BlockElement17Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_17)
                .build();
    }

    public static void initElement18() {
        if (BlockTypes.ELEMENT_18 != null) return;
        BlockTypes.ELEMENT_18 = BlockTypeBuilder
                .builder(BlockElement18Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_18)
                .build();
    }

    public static void initElement19() {
        if (BlockTypes.ELEMENT_19 != null) return;
        BlockTypes.ELEMENT_19 = BlockTypeBuilder
                .builder(BlockElement19Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_19)
                .build();
    }

    public static void initElement2() {
        if (BlockTypes.ELEMENT_2 != null) return;
        BlockTypes.ELEMENT_2 = BlockTypeBuilder
                .builder(BlockElement2Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_2)
                .build();
    }

    public static void initElement20() {
        if (BlockTypes.ELEMENT_20 != null) return;
        BlockTypes.ELEMENT_20 = BlockTypeBuilder
                .builder(BlockElement20Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_20)
                .build();
    }

    public static void initElement21() {
        if (BlockTypes.ELEMENT_21 != null) return;
        BlockTypes.ELEMENT_21 = BlockTypeBuilder
                .builder(BlockElement21Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_21)
                .build();
    }

    public static void initElement22() {
        if (BlockTypes.ELEMENT_22 != null) return;
        BlockTypes.ELEMENT_22 = BlockTypeBuilder
                .builder(BlockElement22Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_22)
                .build();
    }

    public static void initElement23() {
        if (BlockTypes.ELEMENT_23 != null) return;
        BlockTypes.ELEMENT_23 = BlockTypeBuilder
                .builder(BlockElement23Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_23)
                .build();
    }

    public static void initElement24() {
        if (BlockTypes.ELEMENT_24 != null) return;
        BlockTypes.ELEMENT_24 = BlockTypeBuilder
                .builder(BlockElement24Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_24)
                .build();
    }

    public static void initElement25() {
        if (BlockTypes.ELEMENT_25 != null) return;
        BlockTypes.ELEMENT_25 = BlockTypeBuilder
                .builder(BlockElement25Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_25)
                .build();
    }

    public static void initElement26() {
        if (BlockTypes.ELEMENT_26 != null) return;
        BlockTypes.ELEMENT_26 = BlockTypeBuilder
                .builder(BlockElement26Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_26)
                .build();
    }

    public static void initElement27() {
        if (BlockTypes.ELEMENT_27 != null) return;
        BlockTypes.ELEMENT_27 = BlockTypeBuilder
                .builder(BlockElement27Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_27)
                .build();
    }

    public static void initElement28() {
        if (BlockTypes.ELEMENT_28 != null) return;
        BlockTypes.ELEMENT_28 = BlockTypeBuilder
                .builder(BlockElement28Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_28)
                .build();
    }

    public static void initElement29() {
        if (BlockTypes.ELEMENT_29 != null) return;
        BlockTypes.ELEMENT_29 = BlockTypeBuilder
                .builder(BlockElement29Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_29)
                .build();
    }

    public static void initElement3() {
        if (BlockTypes.ELEMENT_3 != null) return;
        BlockTypes.ELEMENT_3 = BlockTypeBuilder
                .builder(BlockElement3Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_3)
                .build();
    }

    public static void initElement30() {
        if (BlockTypes.ELEMENT_30 != null) return;
        BlockTypes.ELEMENT_30 = BlockTypeBuilder
                .builder(BlockElement30Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_30)
                .build();
    }

    public static void initElement31() {
        if (BlockTypes.ELEMENT_31 != null) return;
        BlockTypes.ELEMENT_31 = BlockTypeBuilder
                .builder(BlockElement31Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_31)
                .build();
    }

    public static void initElement32() {
        if (BlockTypes.ELEMENT_32 != null) return;
        BlockTypes.ELEMENT_32 = BlockTypeBuilder
                .builder(BlockElement32Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_32)
                .build();
    }

    public static void initElement33() {
        if (BlockTypes.ELEMENT_33 != null) return;
        BlockTypes.ELEMENT_33 = BlockTypeBuilder
                .builder(BlockElement33Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_33)
                .build();
    }

    public static void initElement34() {
        if (BlockTypes.ELEMENT_34 != null) return;
        BlockTypes.ELEMENT_34 = BlockTypeBuilder
                .builder(BlockElement34Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_34)
                .build();
    }

    public static void initElement35() {
        if (BlockTypes.ELEMENT_35 != null) return;
        BlockTypes.ELEMENT_35 = BlockTypeBuilder
                .builder(BlockElement35Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_35)
                .build();
    }

    public static void initElement36() {
        if (BlockTypes.ELEMENT_36 != null) return;
        BlockTypes.ELEMENT_36 = BlockTypeBuilder
                .builder(BlockElement36Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_36)
                .build();
    }

    public static void initElement37() {
        if (BlockTypes.ELEMENT_37 != null) return;
        BlockTypes.ELEMENT_37 = BlockTypeBuilder
                .builder(BlockElement37Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_37)
                .build();
    }

    public static void initElement38() {
        if (BlockTypes.ELEMENT_38 != null) return;
        BlockTypes.ELEMENT_38 = BlockTypeBuilder
                .builder(BlockElement38Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_38)
                .build();
    }

    public static void initElement39() {
        if (BlockTypes.ELEMENT_39 != null) return;
        BlockTypes.ELEMENT_39 = BlockTypeBuilder
                .builder(BlockElement39Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_39)
                .build();
    }

    public static void initElement4() {
        if (BlockTypes.ELEMENT_4 != null) return;
        BlockTypes.ELEMENT_4 = BlockTypeBuilder
                .builder(BlockElement4Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_4)
                .build();
    }

    public static void initElement40() {
        if (BlockTypes.ELEMENT_40 != null) return;
        BlockTypes.ELEMENT_40 = BlockTypeBuilder
                .builder(BlockElement40Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_40)
                .build();
    }

    public static void initElement41() {
        if (BlockTypes.ELEMENT_41 != null) return;
        BlockTypes.ELEMENT_41 = BlockTypeBuilder
                .builder(BlockElement41Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_41)
                .build();
    }

    public static void initElement42() {
        if (BlockTypes.ELEMENT_42 != null) return;
        BlockTypes.ELEMENT_42 = BlockTypeBuilder
                .builder(BlockElement42Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_42)
                .build();
    }

    public static void initElement43() {
        if (BlockTypes.ELEMENT_43 != null) return;
        BlockTypes.ELEMENT_43 = BlockTypeBuilder
                .builder(BlockElement43Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_43)
                .build();
    }

    public static void initElement44() {
        if (BlockTypes.ELEMENT_44 != null) return;
        BlockTypes.ELEMENT_44 = BlockTypeBuilder
                .builder(BlockElement44Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_44)
                .build();
    }

    public static void initElement45() {
        if (BlockTypes.ELEMENT_45 != null) return;
        BlockTypes.ELEMENT_45 = BlockTypeBuilder
                .builder(BlockElement45Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_45)
                .build();
    }

    public static void initElement46() {
        if (BlockTypes.ELEMENT_46 != null) return;
        BlockTypes.ELEMENT_46 = BlockTypeBuilder
                .builder(BlockElement46Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_46)
                .build();
    }

    public static void initElement47() {
        if (BlockTypes.ELEMENT_47 != null) return;
        BlockTypes.ELEMENT_47 = BlockTypeBuilder
                .builder(BlockElement47Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_47)
                .build();
    }

    public static void initElement48() {
        if (BlockTypes.ELEMENT_48 != null) return;
        BlockTypes.ELEMENT_48 = BlockTypeBuilder
                .builder(BlockElement48Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_48)
                .build();
    }

    public static void initElement49() {
        if (BlockTypes.ELEMENT_49 != null) return;
        BlockTypes.ELEMENT_49 = BlockTypeBuilder
                .builder(BlockElement49Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_49)
                .build();
    }

    public static void initElement5() {
        if (BlockTypes.ELEMENT_5 != null) return;
        BlockTypes.ELEMENT_5 = BlockTypeBuilder
                .builder(BlockElement5Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_5)
                .build();
    }

    public static void initElement50() {
        if (BlockTypes.ELEMENT_50 != null) return;
        BlockTypes.ELEMENT_50 = BlockTypeBuilder
                .builder(BlockElement50Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_50)
                .build();
    }

    public static void initElement51() {
        if (BlockTypes.ELEMENT_51 != null) return;
        BlockTypes.ELEMENT_51 = BlockTypeBuilder
                .builder(BlockElement51Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_51)
                .build();
    }

    public static void initElement52() {
        if (BlockTypes.ELEMENT_52 != null) return;
        BlockTypes.ELEMENT_52 = BlockTypeBuilder
                .builder(BlockElement52Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_52)
                .build();
    }

    public static void initElement53() {
        if (BlockTypes.ELEMENT_53 != null) return;
        BlockTypes.ELEMENT_53 = BlockTypeBuilder
                .builder(BlockElement53Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_53)
                .build();
    }

    public static void initElement54() {
        if (BlockTypes.ELEMENT_54 != null) return;
        BlockTypes.ELEMENT_54 = BlockTypeBuilder
                .builder(BlockElement54Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_54)
                .build();
    }

    public static void initElement55() {
        if (BlockTypes.ELEMENT_55 != null) return;
        BlockTypes.ELEMENT_55 = BlockTypeBuilder
                .builder(BlockElement55Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_55)
                .build();
    }

    public static void initElement56() {
        if (BlockTypes.ELEMENT_56 != null) return;
        BlockTypes.ELEMENT_56 = BlockTypeBuilder
                .builder(BlockElement56Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_56)
                .build();
    }

    public static void initElement57() {
        if (BlockTypes.ELEMENT_57 != null) return;
        BlockTypes.ELEMENT_57 = BlockTypeBuilder
                .builder(BlockElement57Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_57)
                .build();
    }

    public static void initElement58() {
        if (BlockTypes.ELEMENT_58 != null) return;
        BlockTypes.ELEMENT_58 = BlockTypeBuilder
                .builder(BlockElement58Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_58)
                .build();
    }

    public static void initElement59() {
        if (BlockTypes.ELEMENT_59 != null) return;
        BlockTypes.ELEMENT_59 = BlockTypeBuilder
                .builder(BlockElement59Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_59)
                .build();
    }

    public static void initElement6() {
        if (BlockTypes.ELEMENT_6 != null) return;
        BlockTypes.ELEMENT_6 = BlockTypeBuilder
                .builder(BlockElement6Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_6)
                .build();
    }

    public static void initElement60() {
        if (BlockTypes.ELEMENT_60 != null) return;
        BlockTypes.ELEMENT_60 = BlockTypeBuilder
                .builder(BlockElement60Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_60)
                .build();
    }

    public static void initElement61() {
        if (BlockTypes.ELEMENT_61 != null) return;
        BlockTypes.ELEMENT_61 = BlockTypeBuilder
                .builder(BlockElement61Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_61)
                .build();
    }

    public static void initElement62() {
        if (BlockTypes.ELEMENT_62 != null) return;
        BlockTypes.ELEMENT_62 = BlockTypeBuilder
                .builder(BlockElement62Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_62)
                .build();
    }

    public static void initElement63() {
        if (BlockTypes.ELEMENT_63 != null) return;
        BlockTypes.ELEMENT_63 = BlockTypeBuilder
                .builder(BlockElement63Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_63)
                .build();
    }

    public static void initElement64() {
        if (BlockTypes.ELEMENT_64 != null) return;
        BlockTypes.ELEMENT_64 = BlockTypeBuilder
                .builder(BlockElement64Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_64)
                .build();
    }

    public static void initElement65() {
        if (BlockTypes.ELEMENT_65 != null) return;
        BlockTypes.ELEMENT_65 = BlockTypeBuilder
                .builder(BlockElement65Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_65)
                .build();
    }

    public static void initElement66() {
        if (BlockTypes.ELEMENT_66 != null) return;
        BlockTypes.ELEMENT_66 = BlockTypeBuilder
                .builder(BlockElement66Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_66)
                .build();
    }

    public static void initElement67() {
        if (BlockTypes.ELEMENT_67 != null) return;
        BlockTypes.ELEMENT_67 = BlockTypeBuilder
                .builder(BlockElement67Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_67)
                .build();
    }

    public static void initElement68() {
        if (BlockTypes.ELEMENT_68 != null) return;
        BlockTypes.ELEMENT_68 = BlockTypeBuilder
                .builder(BlockElement68Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_68)
                .build();
    }

    public static void initElement69() {
        if (BlockTypes.ELEMENT_69 != null) return;
        BlockTypes.ELEMENT_69 = BlockTypeBuilder
                .builder(BlockElement69Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_69)
                .build();
    }

    public static void initElement7() {
        if (BlockTypes.ELEMENT_7 != null) return;
        BlockTypes.ELEMENT_7 = BlockTypeBuilder
                .builder(BlockElement7Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_7)
                .build();
    }

    public static void initElement70() {
        if (BlockTypes.ELEMENT_70 != null) return;
        BlockTypes.ELEMENT_70 = BlockTypeBuilder
                .builder(BlockElement70Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_70)
                .build();
    }

    public static void initElement71() {
        if (BlockTypes.ELEMENT_71 != null) return;
        BlockTypes.ELEMENT_71 = BlockTypeBuilder
                .builder(BlockElement71Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_71)
                .build();
    }

    public static void initElement72() {
        if (BlockTypes.ELEMENT_72 != null) return;
        BlockTypes.ELEMENT_72 = BlockTypeBuilder
                .builder(BlockElement72Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_72)
                .build();
    }

    public static void initElement73() {
        if (BlockTypes.ELEMENT_73 != null) return;
        BlockTypes.ELEMENT_73 = BlockTypeBuilder
                .builder(BlockElement73Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_73)
                .build();
    }

    public static void initElement74() {
        if (BlockTypes.ELEMENT_74 != null) return;
        BlockTypes.ELEMENT_74 = BlockTypeBuilder
                .builder(BlockElement74Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_74)
                .build();
    }

    public static void initElement75() {
        if (BlockTypes.ELEMENT_75 != null) return;
        BlockTypes.ELEMENT_75 = BlockTypeBuilder
                .builder(BlockElement75Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_75)
                .build();
    }

    public static void initElement76() {
        if (BlockTypes.ELEMENT_76 != null) return;
        BlockTypes.ELEMENT_76 = BlockTypeBuilder
                .builder(BlockElement76Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_76)
                .build();
    }

    public static void initElement77() {
        if (BlockTypes.ELEMENT_77 != null) return;
        BlockTypes.ELEMENT_77 = BlockTypeBuilder
                .builder(BlockElement77Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_77)
                .build();
    }

    public static void initElement78() {
        if (BlockTypes.ELEMENT_78 != null) return;
        BlockTypes.ELEMENT_78 = BlockTypeBuilder
                .builder(BlockElement78Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_78)
                .build();
    }

    public static void initElement79() {
        if (BlockTypes.ELEMENT_79 != null) return;
        BlockTypes.ELEMENT_79 = BlockTypeBuilder
                .builder(BlockElement79Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_79)
                .build();
    }

    public static void initElement8() {
        if (BlockTypes.ELEMENT_8 != null) return;
        BlockTypes.ELEMENT_8 = BlockTypeBuilder
                .builder(BlockElement8Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_8)
                .build();
    }

    public static void initElement80() {
        if (BlockTypes.ELEMENT_80 != null) return;
        BlockTypes.ELEMENT_80 = BlockTypeBuilder
                .builder(BlockElement80Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_80)
                .build();
    }

    public static void initElement81() {
        if (BlockTypes.ELEMENT_81 != null) return;
        BlockTypes.ELEMENT_81 = BlockTypeBuilder
                .builder(BlockElement81Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_81)
                .build();
    }

    public static void initElement82() {
        if (BlockTypes.ELEMENT_82 != null) return;
        BlockTypes.ELEMENT_82 = BlockTypeBuilder
                .builder(BlockElement82Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_82)
                .build();
    }

    public static void initElement83() {
        if (BlockTypes.ELEMENT_83 != null) return;
        BlockTypes.ELEMENT_83 = BlockTypeBuilder
                .builder(BlockElement83Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_83)
                .build();
    }

    public static void initElement84() {
        if (BlockTypes.ELEMENT_84 != null) return;
        BlockTypes.ELEMENT_84 = BlockTypeBuilder
                .builder(BlockElement84Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_84)
                .build();
    }

    public static void initElement85() {
        if (BlockTypes.ELEMENT_85 != null) return;
        BlockTypes.ELEMENT_85 = BlockTypeBuilder
                .builder(BlockElement85Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_85)
                .build();
    }

    public static void initElement86() {
        if (BlockTypes.ELEMENT_86 != null) return;
        BlockTypes.ELEMENT_86 = BlockTypeBuilder
                .builder(BlockElement86Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_86)
                .build();
    }

    public static void initElement87() {
        if (BlockTypes.ELEMENT_87 != null) return;
        BlockTypes.ELEMENT_87 = BlockTypeBuilder
                .builder(BlockElement87Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_87)
                .build();
    }

    public static void initElement88() {
        if (BlockTypes.ELEMENT_88 != null) return;
        BlockTypes.ELEMENT_88 = BlockTypeBuilder
                .builder(BlockElement88Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_88)
                .build();
    }

    public static void initElement89() {
        if (BlockTypes.ELEMENT_89 != null) return;
        BlockTypes.ELEMENT_89 = BlockTypeBuilder
                .builder(BlockElement89Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_89)
                .build();
    }

    public static void initElement9() {
        if (BlockTypes.ELEMENT_9 != null) return;
        BlockTypes.ELEMENT_9 = BlockTypeBuilder
                .builder(BlockElement9Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_9)
                .build();
    }

    public static void initElement90() {
        if (BlockTypes.ELEMENT_90 != null) return;
        BlockTypes.ELEMENT_90 = BlockTypeBuilder
                .builder(BlockElement90Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_90)
                .build();
    }

    public static void initElement91() {
        if (BlockTypes.ELEMENT_91 != null) return;
        BlockTypes.ELEMENT_91 = BlockTypeBuilder
                .builder(BlockElement91Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_91)
                .build();
    }

    public static void initElement92() {
        if (BlockTypes.ELEMENT_92 != null) return;
        BlockTypes.ELEMENT_92 = BlockTypeBuilder
                .builder(BlockElement92Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_92)
                .build();
    }

    public static void initElement93() {
        if (BlockTypes.ELEMENT_93 != null) return;
        BlockTypes.ELEMENT_93 = BlockTypeBuilder
                .builder(BlockElement93Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_93)
                .build();
    }

    public static void initElement94() {
        if (BlockTypes.ELEMENT_94 != null) return;
        BlockTypes.ELEMENT_94 = BlockTypeBuilder
                .builder(BlockElement94Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_94)
                .build();
    }

    public static void initElement95() {
        if (BlockTypes.ELEMENT_95 != null) return;
        BlockTypes.ELEMENT_95 = BlockTypeBuilder
                .builder(BlockElement95Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_95)
                .build();
    }

    public static void initElement96() {
        if (BlockTypes.ELEMENT_96 != null) return;
        BlockTypes.ELEMENT_96 = BlockTypeBuilder
                .builder(BlockElement96Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_96)
                .build();
    }

    public static void initElement97() {
        if (BlockTypes.ELEMENT_97 != null) return;
        BlockTypes.ELEMENT_97 = BlockTypeBuilder
                .builder(BlockElement97Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_97)
                .build();
    }

    public static void initElement98() {
        if (BlockTypes.ELEMENT_98 != null) return;
        BlockTypes.ELEMENT_98 = BlockTypeBuilder
                .builder(BlockElement98Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_98)
                .build();
    }

    public static void initElement99() {
        if (BlockTypes.ELEMENT_99 != null) return;
        BlockTypes.ELEMENT_99 = BlockTypeBuilder
                .builder(BlockElement99Behavior.class)
                .vanillaBlock(BlockId.ELEMENT_99)
                .build();
    }

    public static void initEmeraldBlock() {
        if (BlockTypes.EMERALD_BLOCK != null) return;
        BlockTypes.EMERALD_BLOCK = BlockTypeBuilder
                .builder(BlockEmeraldBlockBehavior.class)
                .vanillaBlock(BlockId.EMERALD_BLOCK)
                .build();
    }

    public static void initEmeraldOre() {
        if (BlockTypes.EMERALD_ORE != null) return;
        BlockTypes.EMERALD_ORE = BlockTypeBuilder
                .builder(BlockEmeraldOreBehavior.class)
                .vanillaBlock(BlockId.EMERALD_ORE)
                .build();
    }

    public static void initEnchantingTable() {
        if (BlockTypes.ENCHANTING_TABLE != null) return;
        BlockTypes.ENCHANTING_TABLE = BlockTypeBuilder
                .builder(BlockEnchantingTableBehavior.class)
                .vanillaBlock(BlockId.ENCHANTING_TABLE)
                .build();
    }

    public static void initEndBrickStairs() {
        if (BlockTypes.END_BRICK_STAIRS != null) return;
        BlockTypes.END_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockEndBrickStairsBehavior.class)
                .vanillaBlock(BlockId.END_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initEndBricks() {
        if (BlockTypes.END_BRICKS != null) return;
        BlockTypes.END_BRICKS = BlockTypeBuilder
                .builder(BlockEndBricksBehavior.class)
                .vanillaBlock(BlockId.END_BRICKS)
                .build();
    }

    public static void initEndGateway() {
        if (BlockTypes.END_GATEWAY != null) return;
        BlockTypes.END_GATEWAY = BlockTypeBuilder
                .builder(BlockEndGatewayBehavior.class)
                .vanillaBlock(BlockId.END_GATEWAY)
                .build();
    }

    public static void initEndPortal() {
        if (BlockTypes.END_PORTAL != null) return;
        BlockTypes.END_PORTAL = BlockTypeBuilder
                .builder(BlockEndPortalBehavior.class)
                .vanillaBlock(BlockId.END_PORTAL)
                .build();
    }

    public static void initEndPortalFrame() {
        if (BlockTypes.END_PORTAL_FRAME != null) return;
        BlockTypes.END_PORTAL_FRAME = BlockTypeBuilder
                .builder(BlockEndPortalFrameBehavior.class)
                .vanillaBlock(BlockId.END_PORTAL_FRAME)
                .setProperties(BlockPropertyTypes.END_PORTAL_EYE_BIT, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initEndRod() {
        if (BlockTypes.END_ROD != null) return;
        BlockTypes.END_ROD = BlockTypeBuilder
                .builder(BlockEndRodBehavior.class)
                .vanillaBlock(BlockId.END_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initEndStone() {
        if (BlockTypes.END_STONE != null) return;
        BlockTypes.END_STONE = BlockTypeBuilder
                .builder(BlockEndStoneBehavior.class)
                .vanillaBlock(BlockId.END_STONE)
                .build();
    }

    public static void initEndStoneBrickDoubleSlab() {
        if (BlockTypes.END_STONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.END_STONE_BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockEndStoneBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.END_STONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initEndStoneBrickSlab() {
        if (BlockTypes.END_STONE_BRICK_SLAB != null) return;
        BlockTypes.END_STONE_BRICK_SLAB = BlockTypeBuilder
                .builder(BlockEndStoneBrickSlabBehavior.class)
                .vanillaBlock(BlockId.END_STONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initEnderChest() {
        if (BlockTypes.ENDER_CHEST != null) return;
        BlockTypes.ENDER_CHEST = BlockTypeBuilder
                .builder(BlockEnderChestBehavior.class)
                .vanillaBlock(BlockId.ENDER_CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initExposedChiseledCopper() {
        if (BlockTypes.EXPOSED_CHISELED_COPPER != null) return;
        BlockTypes.EXPOSED_CHISELED_COPPER = BlockTypeBuilder
                .builder(BlockExposedChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_CHISELED_COPPER)
                .build();
    }

    public static void initExposedCopper() {
        if (BlockTypes.EXPOSED_COPPER != null) return;
        BlockTypes.EXPOSED_COPPER = BlockTypeBuilder
                .builder(BlockExposedCopperBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER)
                .build();
    }

    public static void initExposedCopperBulb() {
        if (BlockTypes.EXPOSED_COPPER_BULB != null) return;
        BlockTypes.EXPOSED_COPPER_BULB = BlockTypeBuilder
                .builder(BlockExposedCopperBulbBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initExposedCopperDoor() {
        if (BlockTypes.EXPOSED_COPPER_DOOR != null) return;
        BlockTypes.EXPOSED_COPPER_DOOR = BlockTypeBuilder
                .builder(BlockExposedCopperDoorBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initExposedCopperGrate() {
        if (BlockTypes.EXPOSED_COPPER_GRATE != null) return;
        BlockTypes.EXPOSED_COPPER_GRATE = BlockTypeBuilder
                .builder(BlockExposedCopperGrateBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_GRATE)
                .build();
    }

    public static void initExposedCopperTrapdoor() {
        if (BlockTypes.EXPOSED_COPPER_TRAPDOOR != null) return;
        BlockTypes.EXPOSED_COPPER_TRAPDOOR = BlockTypeBuilder
                .builder(BlockExposedCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initExposedCutCopper() {
        if (BlockTypes.EXPOSED_CUT_COPPER != null) return;
        BlockTypes.EXPOSED_CUT_COPPER = BlockTypeBuilder
                .builder(BlockExposedCutCopperBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER)
                .build();
    }

    public static void initExposedCutCopperSlab() {
        if (BlockTypes.EXPOSED_CUT_COPPER_SLAB != null) return;
        BlockTypes.EXPOSED_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockExposedCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initExposedCutCopperStairs() {
        if (BlockTypes.EXPOSED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.EXPOSED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockExposedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initExposedDoubleCutCopperSlab() {
        if (BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.EXPOSED_DOUBLE_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockExposedDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initFarmland() {
        if (BlockTypes.FARMLAND != null) return;
        BlockTypes.FARMLAND = BlockTypeBuilder
                .builder(BlockFarmlandBehavior.class)
                .vanillaBlock(BlockId.FARMLAND)
                .setProperties(BlockPropertyTypes.MOISTURIZED_AMOUNT)
                .build();
    }

    public static void initFenceGate() {
        if (BlockTypes.FENCE_GATE != null) return;
        BlockTypes.FENCE_GATE = BlockTypeBuilder
                .builder(BlockFenceGateBehavior.class)
                .vanillaBlock(BlockId.FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initFern() {
        if (BlockTypes.FERN != null) return;
        BlockTypes.FERN = BlockTypeBuilder
                .builder(BlockFernBehavior.class)
                .vanillaBlock(BlockId.FERN)
                .build();
    }

    public static void initFire() {
        if (BlockTypes.FIRE != null) return;
        BlockTypes.FIRE = BlockTypeBuilder
                .builder(BlockFireBehavior.class)
                .vanillaBlock(BlockId.FIRE)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initFireCoral() {
        if (BlockTypes.FIRE_CORAL != null) return;
        BlockTypes.FIRE_CORAL = BlockTypeBuilder
                .builder(BlockFireCoralBehavior.class)
                .vanillaBlock(BlockId.FIRE_CORAL)
                .build();
    }

    public static void initFireCoralBlock() {
        if (BlockTypes.FIRE_CORAL_BLOCK != null) return;
        BlockTypes.FIRE_CORAL_BLOCK = BlockTypeBuilder
                .builder(BlockFireCoralBlockBehavior.class)
                .vanillaBlock(BlockId.FIRE_CORAL_BLOCK)
                .build();
    }

    public static void initFireCoralFan() {
        if (BlockTypes.FIRE_CORAL_FAN != null) return;
        BlockTypes.FIRE_CORAL_FAN = BlockTypeBuilder
                .builder(BlockFireCoralFanBehavior.class)
                .vanillaBlock(BlockId.FIRE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initFireCoralWallFan() {
        if (BlockTypes.FIRE_CORAL_WALL_FAN != null) return;
        BlockTypes.FIRE_CORAL_WALL_FAN = BlockTypeBuilder
                .builder(BlockFireCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.FIRE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initFletchingTable() {
        if (BlockTypes.FLETCHING_TABLE != null) return;
        BlockTypes.FLETCHING_TABLE = BlockTypeBuilder
                .builder(BlockFletchingTableBehavior.class)
                .vanillaBlock(BlockId.FLETCHING_TABLE)
                .build();
    }

    public static void initFlowerPot() {
        if (BlockTypes.FLOWER_POT != null) return;
        BlockTypes.FLOWER_POT = BlockTypeBuilder
                .builder(BlockFlowerPotBehavior.class)
                .vanillaBlock(BlockId.FLOWER_POT)
                .setProperties(BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initFloweringAzalea() {
        if (BlockTypes.FLOWERING_AZALEA != null) return;
        BlockTypes.FLOWERING_AZALEA = BlockTypeBuilder
                .builder(BlockFloweringAzaleaBehavior.class)
                .vanillaBlock(BlockId.FLOWERING_AZALEA)
                .build();
    }

    public static void initFlowingLava() {
        if (BlockTypes.FLOWING_LAVA != null) return;
        BlockTypes.FLOWING_LAVA = BlockTypeBuilder
                .builder(BlockFlowingLavaBehavior.class)
                .vanillaBlock(BlockId.FLOWING_LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initFlowingWater() {
        if (BlockTypes.FLOWING_WATER != null) return;
        BlockTypes.FLOWING_WATER = BlockTypeBuilder
                .builder(BlockFlowingWaterBehavior.class)
                .vanillaBlock(BlockId.FLOWING_WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initFrame() {
        if (BlockTypes.FRAME != null) return;
        BlockTypes.FRAME = BlockTypeBuilder
                .builder(BlockFrameBehavior.class)
                .vanillaBlock(BlockId.FRAME)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ITEM_FRAME_MAP_BIT, BlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
                .build();
    }

    public static void initFrogSpawn() {
        if (BlockTypes.FROG_SPAWN != null) return;
        BlockTypes.FROG_SPAWN = BlockTypeBuilder
                .builder(BlockFrogSpawnBehavior.class)
                .vanillaBlock(BlockId.FROG_SPAWN)
                .build();
    }

    public static void initFrostedIce() {
        if (BlockTypes.FROSTED_ICE != null) return;
        BlockTypes.FROSTED_ICE = BlockTypeBuilder
                .builder(BlockFrostedIceBehavior.class)
                .vanillaBlock(BlockId.FROSTED_ICE)
                .setProperties(BlockPropertyTypes.AGE_4)
                .build();
    }

    public static void initFurnace() {
        if (BlockTypes.FURNACE != null) return;
        BlockTypes.FURNACE = BlockTypeBuilder
                .builder(BlockFurnaceBehavior.class)
                .vanillaBlock(BlockId.FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initGildedBlackstone() {
        if (BlockTypes.GILDED_BLACKSTONE != null) return;
        BlockTypes.GILDED_BLACKSTONE = BlockTypeBuilder
                .builder(BlockGildedBlackstoneBehavior.class)
                .vanillaBlock(BlockId.GILDED_BLACKSTONE)
                .build();
    }

    public static void initGlass() {
        if (BlockTypes.GLASS != null) return;
        BlockTypes.GLASS = BlockTypeBuilder
                .builder(BlockGlassBehavior.class)
                .vanillaBlock(BlockId.GLASS)
                .build();
    }

    public static void initGlassPane() {
        if (BlockTypes.GLASS_PANE != null) return;
        BlockTypes.GLASS_PANE = BlockTypeBuilder
                .builder(BlockGlassPaneBehavior.class)
                .vanillaBlock(BlockId.GLASS_PANE)
                .build();
    }

    public static void initGlowFrame() {
        if (BlockTypes.GLOW_FRAME != null) return;
        BlockTypes.GLOW_FRAME = BlockTypeBuilder
                .builder(BlockGlowFrameBehavior.class)
                .vanillaBlock(BlockId.GLOW_FRAME)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ITEM_FRAME_MAP_BIT, BlockPropertyTypes.ITEM_FRAME_PHOTO_BIT)
                .build();
    }

    public static void initGlowLichen() {
        if (BlockTypes.GLOW_LICHEN != null) return;
        BlockTypes.GLOW_LICHEN = BlockTypeBuilder
                .builder(BlockGlowLichenBehavior.class)
                .vanillaBlock(BlockId.GLOW_LICHEN)
                .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                .build();
    }

    public static void initGlowingobsidian() {
        if (BlockTypes.GLOWINGOBSIDIAN != null) return;
        BlockTypes.GLOWINGOBSIDIAN = BlockTypeBuilder
                .builder(BlockGlowingobsidianBehavior.class)
                .vanillaBlock(BlockId.GLOWINGOBSIDIAN)
                .build();
    }

    public static void initGlowstone() {
        if (BlockTypes.GLOWSTONE != null) return;
        BlockTypes.GLOWSTONE = BlockTypeBuilder
                .builder(BlockGlowstoneBehavior.class)
                .vanillaBlock(BlockId.GLOWSTONE)
                .build();
    }

    public static void initGoldBlock() {
        if (BlockTypes.GOLD_BLOCK != null) return;
        BlockTypes.GOLD_BLOCK = BlockTypeBuilder
                .builder(BlockGoldBlockBehavior.class)
                .vanillaBlock(BlockId.GOLD_BLOCK)
                .build();
    }

    public static void initGoldOre() {
        if (BlockTypes.GOLD_ORE != null) return;
        BlockTypes.GOLD_ORE = BlockTypeBuilder
                .builder(BlockGoldOreBehavior.class)
                .vanillaBlock(BlockId.GOLD_ORE)
                .build();
    }

    public static void initGoldenRail() {
        if (BlockTypes.GOLDEN_RAIL != null) return;
        BlockTypes.GOLDEN_RAIL = BlockTypeBuilder
                .builder(BlockGoldenRailBehavior.class)
                .vanillaBlock(BlockId.GOLDEN_RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DATA_BIT, BlockPropertyTypes.RAIL_DIRECTION_6)
                .build();
    }

    public static void initGranite() {
        if (BlockTypes.GRANITE != null) return;
        BlockTypes.GRANITE = BlockTypeBuilder
                .builder(BlockGraniteBehavior.class)
                .vanillaBlock(BlockId.GRANITE)
                .build();
    }

    public static void initGraniteDoubleSlab() {
        if (BlockTypes.GRANITE_DOUBLE_SLAB != null) return;
        BlockTypes.GRANITE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockGraniteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.GRANITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initGraniteSlab() {
        if (BlockTypes.GRANITE_SLAB != null) return;
        BlockTypes.GRANITE_SLAB = BlockTypeBuilder
                .builder(BlockGraniteSlabBehavior.class)
                .vanillaBlock(BlockId.GRANITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initGraniteStairs() {
        if (BlockTypes.GRANITE_STAIRS != null) return;
        BlockTypes.GRANITE_STAIRS = BlockTypeBuilder
                .builder(BlockGraniteStairsBehavior.class)
                .vanillaBlock(BlockId.GRANITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initGrassBlock() {
        if (BlockTypes.GRASS_BLOCK != null) return;
        BlockTypes.GRASS_BLOCK = BlockTypeBuilder
                .builder(BlockGrassBlockBehavior.class)
                .vanillaBlock(BlockId.GRASS_BLOCK)
                .build();
    }

    public static void initGrassPath() {
        if (BlockTypes.GRASS_PATH != null) return;
        BlockTypes.GRASS_PATH = BlockTypeBuilder
                .builder(BlockGrassPathBehavior.class)
                .vanillaBlock(BlockId.GRASS_PATH)
                .build();
    }

    public static void initGravel() {
        if (BlockTypes.GRAVEL != null) return;
        BlockTypes.GRAVEL = BlockTypeBuilder
                .builder(BlockGravelBehavior.class)
                .vanillaBlock(BlockId.GRAVEL)
                .build();
    }

    public static void initGrayCandle() {
        if (BlockTypes.GRAY_CANDLE != null) return;
        BlockTypes.GRAY_CANDLE = BlockTypeBuilder
                .builder(BlockGrayCandleBehavior.class)
                .vanillaBlock(BlockId.GRAY_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGrayCandleCake() {
        if (BlockTypes.GRAY_CANDLE_CAKE != null) return;
        BlockTypes.GRAY_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockGrayCandleCakeBehavior.class)
                .vanillaBlock(BlockId.GRAY_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGrayCarpet() {
        if (BlockTypes.GRAY_CARPET != null) return;
        BlockTypes.GRAY_CARPET = BlockTypeBuilder
                .builder(BlockGrayCarpetBehavior.class)
                .vanillaBlock(BlockId.GRAY_CARPET)
                .build();
    }

    public static void initGrayConcrete() {
        if (BlockTypes.GRAY_CONCRETE != null) return;
        BlockTypes.GRAY_CONCRETE = BlockTypeBuilder
                .builder(BlockGrayConcreteBehavior.class)
                .vanillaBlock(BlockId.GRAY_CONCRETE)
                .build();
    }

    public static void initGrayConcretePowder() {
        if (BlockTypes.GRAY_CONCRETE_POWDER != null) return;
        BlockTypes.GRAY_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockGrayConcretePowderBehavior.class)
                .vanillaBlock(BlockId.GRAY_CONCRETE_POWDER)
                .build();
    }

    public static void initGrayGlazedTerracotta() {
        if (BlockTypes.GRAY_GLAZED_TERRACOTTA != null) return;
        BlockTypes.GRAY_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockGrayGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.GRAY_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initGrayShulkerBox() {
        if (BlockTypes.GRAY_SHULKER_BOX != null) return;
        BlockTypes.GRAY_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockGrayShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.GRAY_SHULKER_BOX)
                .build();
    }

    public static void initGrayStainedGlass() {
        if (BlockTypes.GRAY_STAINED_GLASS != null) return;
        BlockTypes.GRAY_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockGrayStainedGlassBehavior.class)
                .vanillaBlock(BlockId.GRAY_STAINED_GLASS)
                .build();
    }

    public static void initGrayStainedGlassPane() {
        if (BlockTypes.GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.GRAY_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockGrayStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initGrayTerracotta() {
        if (BlockTypes.GRAY_TERRACOTTA != null) return;
        BlockTypes.GRAY_TERRACOTTA = BlockTypeBuilder
                .builder(BlockGrayTerracottaBehavior.class)
                .vanillaBlock(BlockId.GRAY_TERRACOTTA)
                .build();
    }

    public static void initGrayWool() {
        if (BlockTypes.GRAY_WOOL != null) return;
        BlockTypes.GRAY_WOOL = BlockTypeBuilder
                .builder(BlockGrayWoolBehavior.class)
                .vanillaBlock(BlockId.GRAY_WOOL)
                .build();
    }

    public static void initGreenCandle() {
        if (BlockTypes.GREEN_CANDLE != null) return;
        BlockTypes.GREEN_CANDLE = BlockTypeBuilder
                .builder(BlockGreenCandleBehavior.class)
                .vanillaBlock(BlockId.GREEN_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGreenCandleCake() {
        if (BlockTypes.GREEN_CANDLE_CAKE != null) return;
        BlockTypes.GREEN_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockGreenCandleCakeBehavior.class)
                .vanillaBlock(BlockId.GREEN_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initGreenCarpet() {
        if (BlockTypes.GREEN_CARPET != null) return;
        BlockTypes.GREEN_CARPET = BlockTypeBuilder
                .builder(BlockGreenCarpetBehavior.class)
                .vanillaBlock(BlockId.GREEN_CARPET)
                .build();
    }

    public static void initGreenConcrete() {
        if (BlockTypes.GREEN_CONCRETE != null) return;
        BlockTypes.GREEN_CONCRETE = BlockTypeBuilder
                .builder(BlockGreenConcreteBehavior.class)
                .vanillaBlock(BlockId.GREEN_CONCRETE)
                .build();
    }

    public static void initGreenConcretePowder() {
        if (BlockTypes.GREEN_CONCRETE_POWDER != null) return;
        BlockTypes.GREEN_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockGreenConcretePowderBehavior.class)
                .vanillaBlock(BlockId.GREEN_CONCRETE_POWDER)
                .build();
    }

    public static void initGreenGlazedTerracotta() {
        if (BlockTypes.GREEN_GLAZED_TERRACOTTA != null) return;
        BlockTypes.GREEN_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockGreenGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.GREEN_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initGreenShulkerBox() {
        if (BlockTypes.GREEN_SHULKER_BOX != null) return;
        BlockTypes.GREEN_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockGreenShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.GREEN_SHULKER_BOX)
                .build();
    }

    public static void initGreenStainedGlass() {
        if (BlockTypes.GREEN_STAINED_GLASS != null) return;
        BlockTypes.GREEN_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockGreenStainedGlassBehavior.class)
                .vanillaBlock(BlockId.GREEN_STAINED_GLASS)
                .build();
    }

    public static void initGreenStainedGlassPane() {
        if (BlockTypes.GREEN_STAINED_GLASS_PANE != null) return;
        BlockTypes.GREEN_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockGreenStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.GREEN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initGreenTerracotta() {
        if (BlockTypes.GREEN_TERRACOTTA != null) return;
        BlockTypes.GREEN_TERRACOTTA = BlockTypeBuilder
                .builder(BlockGreenTerracottaBehavior.class)
                .vanillaBlock(BlockId.GREEN_TERRACOTTA)
                .build();
    }

    public static void initGreenWool() {
        if (BlockTypes.GREEN_WOOL != null) return;
        BlockTypes.GREEN_WOOL = BlockTypeBuilder
                .builder(BlockGreenWoolBehavior.class)
                .vanillaBlock(BlockId.GREEN_WOOL)
                .build();
    }

    public static void initGrindstone() {
        if (BlockTypes.GRINDSTONE != null) return;
        BlockTypes.GRINDSTONE = BlockTypeBuilder
                .builder(BlockGrindstoneBehavior.class)
                .vanillaBlock(BlockId.GRINDSTONE)
                .setProperties(BlockPropertyTypes.ATTACHMENT, BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initHangingRoots() {
        if (BlockTypes.HANGING_ROOTS != null) return;
        BlockTypes.HANGING_ROOTS = BlockTypeBuilder
                .builder(BlockHangingRootsBehavior.class)
                .vanillaBlock(BlockId.HANGING_ROOTS)
                .build();
    }

    public static void initHardBlackStainedGlass() {
        if (BlockTypes.HARD_BLACK_STAINED_GLASS != null) return;
        BlockTypes.HARD_BLACK_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardBlackStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_BLACK_STAINED_GLASS)
                .build();
    }

    public static void initHardBlackStainedGlassPane() {
        if (BlockTypes.HARD_BLACK_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_BLACK_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardBlackStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_BLACK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardBlueStainedGlass() {
        if (BlockTypes.HARD_BLUE_STAINED_GLASS != null) return;
        BlockTypes.HARD_BLUE_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardBlueStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initHardBlueStainedGlassPane() {
        if (BlockTypes.HARD_BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_BLUE_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardBlueStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardBrownStainedGlass() {
        if (BlockTypes.HARD_BROWN_STAINED_GLASS != null) return;
        BlockTypes.HARD_BROWN_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardBrownStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_BROWN_STAINED_GLASS)
                .build();
    }

    public static void initHardBrownStainedGlassPane() {
        if (BlockTypes.HARD_BROWN_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_BROWN_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardBrownStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_BROWN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardCyanStainedGlass() {
        if (BlockTypes.HARD_CYAN_STAINED_GLASS != null) return;
        BlockTypes.HARD_CYAN_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardCyanStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_CYAN_STAINED_GLASS)
                .build();
    }

    public static void initHardCyanStainedGlassPane() {
        if (BlockTypes.HARD_CYAN_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_CYAN_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardCyanStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_CYAN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardGlass() {
        if (BlockTypes.HARD_GLASS != null) return;
        BlockTypes.HARD_GLASS = BlockTypeBuilder
                .builder(BlockHardGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_GLASS)
                .build();
    }

    public static void initHardGlassPane() {
        if (BlockTypes.HARD_GLASS_PANE != null) return;
        BlockTypes.HARD_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_GLASS_PANE)
                .build();
    }

    public static void initHardGrayStainedGlass() {
        if (BlockTypes.HARD_GRAY_STAINED_GLASS != null) return;
        BlockTypes.HARD_GRAY_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardGrayStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initHardGrayStainedGlassPane() {
        if (BlockTypes.HARD_GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_GRAY_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardGrayStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardGreenStainedGlass() {
        if (BlockTypes.HARD_GREEN_STAINED_GLASS != null) return;
        BlockTypes.HARD_GREEN_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardGreenStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_GREEN_STAINED_GLASS)
                .build();
    }

    public static void initHardGreenStainedGlassPane() {
        if (BlockTypes.HARD_GREEN_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_GREEN_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardGreenStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_GREEN_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLightBlueStainedGlass() {
        if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS != null) return;
        BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardLightBlueStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initHardLightBlueStainedGlassPane() {
        if (BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_LIGHT_BLUE_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardLightBlueStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLightGrayStainedGlass() {
        if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS != null) return;
        BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardLightGrayStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initHardLightGrayStainedGlassPane() {
        if (BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardLightGrayStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardLimeStainedGlass() {
        if (BlockTypes.HARD_LIME_STAINED_GLASS != null) return;
        BlockTypes.HARD_LIME_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardLimeStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_LIME_STAINED_GLASS)
                .build();
    }

    public static void initHardLimeStainedGlassPane() {
        if (BlockTypes.HARD_LIME_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_LIME_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardLimeStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_LIME_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardMagentaStainedGlass() {
        if (BlockTypes.HARD_MAGENTA_STAINED_GLASS != null) return;
        BlockTypes.HARD_MAGENTA_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardMagentaStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_MAGENTA_STAINED_GLASS)
                .build();
    }

    public static void initHardMagentaStainedGlassPane() {
        if (BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_MAGENTA_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardMagentaStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_MAGENTA_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardOrangeStainedGlass() {
        if (BlockTypes.HARD_ORANGE_STAINED_GLASS != null) return;
        BlockTypes.HARD_ORANGE_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardOrangeStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_ORANGE_STAINED_GLASS)
                .build();
    }

    public static void initHardOrangeStainedGlassPane() {
        if (BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_ORANGE_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardOrangeStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_ORANGE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardPinkStainedGlass() {
        if (BlockTypes.HARD_PINK_STAINED_GLASS != null) return;
        BlockTypes.HARD_PINK_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardPinkStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_PINK_STAINED_GLASS)
                .build();
    }

    public static void initHardPinkStainedGlassPane() {
        if (BlockTypes.HARD_PINK_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_PINK_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardPinkStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_PINK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardPurpleStainedGlass() {
        if (BlockTypes.HARD_PURPLE_STAINED_GLASS != null) return;
        BlockTypes.HARD_PURPLE_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardPurpleStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_PURPLE_STAINED_GLASS)
                .build();
    }

    public static void initHardPurpleStainedGlassPane() {
        if (BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_PURPLE_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardPurpleStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_PURPLE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardRedStainedGlass() {
        if (BlockTypes.HARD_RED_STAINED_GLASS != null) return;
        BlockTypes.HARD_RED_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardRedStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_RED_STAINED_GLASS)
                .build();
    }

    public static void initHardRedStainedGlassPane() {
        if (BlockTypes.HARD_RED_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_RED_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardRedStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_RED_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardWhiteStainedGlass() {
        if (BlockTypes.HARD_WHITE_STAINED_GLASS != null) return;
        BlockTypes.HARD_WHITE_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardWhiteStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_WHITE_STAINED_GLASS)
                .build();
    }

    public static void initHardWhiteStainedGlassPane() {
        if (BlockTypes.HARD_WHITE_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_WHITE_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardWhiteStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_WHITE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardYellowStainedGlass() {
        if (BlockTypes.HARD_YELLOW_STAINED_GLASS != null) return;
        BlockTypes.HARD_YELLOW_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockHardYellowStainedGlassBehavior.class)
                .vanillaBlock(BlockId.HARD_YELLOW_STAINED_GLASS)
                .build();
    }

    public static void initHardYellowStainedGlassPane() {
        if (BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE != null) return;
        BlockTypes.HARD_YELLOW_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockHardYellowStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.HARD_YELLOW_STAINED_GLASS_PANE)
                .build();
    }

    public static void initHardenedClay() {
        if (BlockTypes.HARDENED_CLAY != null) return;
        BlockTypes.HARDENED_CLAY = BlockTypeBuilder
                .builder(BlockHardenedClayBehavior.class)
                .vanillaBlock(BlockId.HARDENED_CLAY)
                .build();
    }

    public static void initHayBlock() {
        if (BlockTypes.HAY_BLOCK != null) return;
        BlockTypes.HAY_BLOCK = BlockTypeBuilder
                .builder(BlockHayBlockBehavior.class)
                .vanillaBlock(BlockId.HAY_BLOCK)
                .setProperties(BlockPropertyTypes.DEPRECATED, BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initHeavyCore() {
        if (BlockTypes.HEAVY_CORE != null) return;
        BlockTypes.HEAVY_CORE = BlockTypeBuilder
                .builder(BlockHeavyCoreBehavior.class)
                .vanillaBlock(BlockId.HEAVY_CORE)
                .build();
    }

    public static void initHeavyWeightedPressurePlate() {
        if (BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE != null) return;
        BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockHeavyWeightedPressurePlateBehavior.class)
                .vanillaBlock(BlockId.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initHoneyBlock() {
        if (BlockTypes.HONEY_BLOCK != null) return;
        BlockTypes.HONEY_BLOCK = BlockTypeBuilder
                .builder(BlockHoneyBlockBehavior.class)
                .vanillaBlock(BlockId.HONEY_BLOCK)
                .build();
    }

    public static void initHoneycombBlock() {
        if (BlockTypes.HONEYCOMB_BLOCK != null) return;
        BlockTypes.HONEYCOMB_BLOCK = BlockTypeBuilder
                .builder(BlockHoneycombBlockBehavior.class)
                .vanillaBlock(BlockId.HONEYCOMB_BLOCK)
                .build();
    }

    public static void initHopper() {
        if (BlockTypes.HOPPER != null) return;
        BlockTypes.HOPPER = BlockTypeBuilder
                .builder(BlockHopperBehavior.class)
                .vanillaBlock(BlockId.HOPPER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.TOGGLE_BIT)
                .build();
    }

    public static void initHornCoral() {
        if (BlockTypes.HORN_CORAL != null) return;
        BlockTypes.HORN_CORAL = BlockTypeBuilder
                .builder(BlockHornCoralBehavior.class)
                .vanillaBlock(BlockId.HORN_CORAL)
                .build();
    }

    public static void initHornCoralBlock() {
        if (BlockTypes.HORN_CORAL_BLOCK != null) return;
        BlockTypes.HORN_CORAL_BLOCK = BlockTypeBuilder
                .builder(BlockHornCoralBlockBehavior.class)
                .vanillaBlock(BlockId.HORN_CORAL_BLOCK)
                .build();
    }

    public static void initHornCoralFan() {
        if (BlockTypes.HORN_CORAL_FAN != null) return;
        BlockTypes.HORN_CORAL_FAN = BlockTypeBuilder
                .builder(BlockHornCoralFanBehavior.class)
                .vanillaBlock(BlockId.HORN_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initHornCoralWallFan() {
        if (BlockTypes.HORN_CORAL_WALL_FAN != null) return;
        BlockTypes.HORN_CORAL_WALL_FAN = BlockTypeBuilder
                .builder(BlockHornCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.HORN_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initIce() {
        if (BlockTypes.ICE != null) return;
        BlockTypes.ICE = BlockTypeBuilder
                .builder(BlockIceBehavior.class)
                .vanillaBlock(BlockId.ICE)
                .build();
    }

    public static void initInfestedChiseledStoneBricks() {
        if (BlockTypes.INFESTED_CHISELED_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_CHISELED_STONE_BRICKS = BlockTypeBuilder
                .builder(BlockInfestedChiseledStoneBricksBehavior.class)
                .vanillaBlock(BlockId.INFESTED_CHISELED_STONE_BRICKS)
                .build();
    }

    public static void initInfestedCobblestone() {
        if (BlockTypes.INFESTED_COBBLESTONE != null) return;
        BlockTypes.INFESTED_COBBLESTONE = BlockTypeBuilder
                .builder(BlockInfestedCobblestoneBehavior.class)
                .vanillaBlock(BlockId.INFESTED_COBBLESTONE)
                .build();
    }

    public static void initInfestedCrackedStoneBricks() {
        if (BlockTypes.INFESTED_CRACKED_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_CRACKED_STONE_BRICKS = BlockTypeBuilder
                .builder(BlockInfestedCrackedStoneBricksBehavior.class)
                .vanillaBlock(BlockId.INFESTED_CRACKED_STONE_BRICKS)
                .build();
    }

    public static void initInfestedDeepslate() {
        if (BlockTypes.INFESTED_DEEPSLATE != null) return;
        BlockTypes.INFESTED_DEEPSLATE = BlockTypeBuilder
                .builder(BlockInfestedDeepslateBehavior.class)
                .vanillaBlock(BlockId.INFESTED_DEEPSLATE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initInfestedMossyStoneBricks() {
        if (BlockTypes.INFESTED_MOSSY_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_MOSSY_STONE_BRICKS = BlockTypeBuilder
                .builder(BlockInfestedMossyStoneBricksBehavior.class)
                .vanillaBlock(BlockId.INFESTED_MOSSY_STONE_BRICKS)
                .build();
    }

    public static void initInfestedStone() {
        if (BlockTypes.INFESTED_STONE != null) return;
        BlockTypes.INFESTED_STONE = BlockTypeBuilder
                .builder(BlockInfestedStoneBehavior.class)
                .vanillaBlock(BlockId.INFESTED_STONE)
                .build();
    }

    public static void initInfestedStoneBricks() {
        if (BlockTypes.INFESTED_STONE_BRICKS != null) return;
        BlockTypes.INFESTED_STONE_BRICKS = BlockTypeBuilder
                .builder(BlockInfestedStoneBricksBehavior.class)
                .vanillaBlock(BlockId.INFESTED_STONE_BRICKS)
                .build();
    }

    public static void initInfoUpdate() {
        if (BlockTypes.INFO_UPDATE != null) return;
        BlockTypes.INFO_UPDATE = BlockTypeBuilder
                .builder(BlockInfoUpdateBehavior.class)
                .vanillaBlock(BlockId.INFO_UPDATE)
                .build();
    }

    public static void initInfoUpdate2() {
        if (BlockTypes.INFO_UPDATE2 != null) return;
        BlockTypes.INFO_UPDATE2 = BlockTypeBuilder
                .builder(BlockInfoUpdate2Behavior.class)
                .vanillaBlock(BlockId.INFO_UPDATE2)
                .build();
    }

    public static void initInvisibleBedrock() {
        if (BlockTypes.INVISIBLE_BEDROCK != null) return;
        BlockTypes.INVISIBLE_BEDROCK = BlockTypeBuilder
                .builder(BlockInvisibleBedrockBehavior.class)
                .vanillaBlock(BlockId.INVISIBLE_BEDROCK)
                .build();
    }

    public static void initIronBars() {
        if (BlockTypes.IRON_BARS != null) return;
        BlockTypes.IRON_BARS = BlockTypeBuilder
                .builder(BlockIronBarsBehavior.class)
                .vanillaBlock(BlockId.IRON_BARS)
                .build();
    }

    public static void initIronBlock() {
        if (BlockTypes.IRON_BLOCK != null) return;
        BlockTypes.IRON_BLOCK = BlockTypeBuilder
                .builder(BlockIronBlockBehavior.class)
                .vanillaBlock(BlockId.IRON_BLOCK)
                .build();
    }

    public static void initIronDoor() {
        if (BlockTypes.IRON_DOOR != null) return;
        BlockTypes.IRON_DOOR = BlockTypeBuilder
                .builder(BlockIronDoorBehavior.class)
                .vanillaBlock(BlockId.IRON_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initIronOre() {
        if (BlockTypes.IRON_ORE != null) return;
        BlockTypes.IRON_ORE = BlockTypeBuilder
                .builder(BlockIronOreBehavior.class)
                .vanillaBlock(BlockId.IRON_ORE)
                .build();
    }

    public static void initIronTrapdoor() {
        if (BlockTypes.IRON_TRAPDOOR != null) return;
        BlockTypes.IRON_TRAPDOOR = BlockTypeBuilder
                .builder(BlockIronTrapdoorBehavior.class)
                .vanillaBlock(BlockId.IRON_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initJigsaw() {
        if (BlockTypes.JIGSAW != null) return;
        BlockTypes.JIGSAW = BlockTypeBuilder
                .builder(BlockJigsawBehavior.class)
                .vanillaBlock(BlockId.JIGSAW)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.ROTATION)
                .build();
    }

    public static void initJukebox() {
        if (BlockTypes.JUKEBOX != null) return;
        BlockTypes.JUKEBOX = BlockTypeBuilder
                .builder(BlockJukeboxBehavior.class)
                .vanillaBlock(BlockId.JUKEBOX)
                .build();
    }

    public static void initJungleButton() {
        if (BlockTypes.JUNGLE_BUTTON != null) return;
        BlockTypes.JUNGLE_BUTTON = BlockTypeBuilder
                .builder(BlockJungleButtonBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initJungleDoor() {
        if (BlockTypes.JUNGLE_DOOR != null) return;
        BlockTypes.JUNGLE_DOOR = BlockTypeBuilder
                .builder(BlockJungleDoorBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initJungleDoubleSlab() {
        if (BlockTypes.JUNGLE_DOUBLE_SLAB != null) return;
        BlockTypes.JUNGLE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockJungleDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initJungleFence() {
        if (BlockTypes.JUNGLE_FENCE != null) return;
        BlockTypes.JUNGLE_FENCE = BlockTypeBuilder
                .builder(BlockJungleFenceBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_FENCE)
                .build();
    }

    public static void initJungleFenceGate() {
        if (BlockTypes.JUNGLE_FENCE_GATE != null) return;
        BlockTypes.JUNGLE_FENCE_GATE = BlockTypeBuilder
                .builder(BlockJungleFenceGateBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initJungleHangingSign() {
        if (BlockTypes.JUNGLE_HANGING_SIGN != null) return;
        BlockTypes.JUNGLE_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockJungleHangingSignBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initJungleLeaves() {
        if (BlockTypes.JUNGLE_LEAVES != null) return;
        BlockTypes.JUNGLE_LEAVES = BlockTypeBuilder
                .builder(BlockJungleLeavesBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initJungleLog() {
        if (BlockTypes.JUNGLE_LOG != null) return;
        BlockTypes.JUNGLE_LOG = BlockTypeBuilder
                .builder(BlockJungleLogBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initJunglePlanks() {
        if (BlockTypes.JUNGLE_PLANKS != null) return;
        BlockTypes.JUNGLE_PLANKS = BlockTypeBuilder
                .builder(BlockJunglePlanksBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_PLANKS)
                .build();
    }

    public static void initJunglePressurePlate() {
        if (BlockTypes.JUNGLE_PRESSURE_PLATE != null) return;
        BlockTypes.JUNGLE_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockJunglePressurePlateBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initJungleSapling() {
        if (BlockTypes.JUNGLE_SAPLING != null) return;
        BlockTypes.JUNGLE_SAPLING = BlockTypeBuilder
                .builder(BlockJungleSaplingBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initJungleSlab() {
        if (BlockTypes.JUNGLE_SLAB != null) return;
        BlockTypes.JUNGLE_SLAB = BlockTypeBuilder
                .builder(BlockJungleSlabBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initJungleStairs() {
        if (BlockTypes.JUNGLE_STAIRS != null) return;
        BlockTypes.JUNGLE_STAIRS = BlockTypeBuilder
                .builder(BlockJungleStairsBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initJungleStandingSign() {
        if (BlockTypes.JUNGLE_STANDING_SIGN != null) return;
        BlockTypes.JUNGLE_STANDING_SIGN = BlockTypeBuilder
                .builder(BlockJungleStandingSignBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initJungleTrapdoor() {
        if (BlockTypes.JUNGLE_TRAPDOOR != null) return;
        BlockTypes.JUNGLE_TRAPDOOR = BlockTypeBuilder
                .builder(BlockJungleTrapdoorBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initJungleWallSign() {
        if (BlockTypes.JUNGLE_WALL_SIGN != null) return;
        BlockTypes.JUNGLE_WALL_SIGN = BlockTypeBuilder
                .builder(BlockJungleWallSignBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initJungleWood() {
        if (BlockTypes.JUNGLE_WOOD != null) return;
        BlockTypes.JUNGLE_WOOD = BlockTypeBuilder
                .builder(BlockJungleWoodBehavior.class)
                .vanillaBlock(BlockId.JUNGLE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initKelp() {
        if (BlockTypes.KELP != null) return;
        BlockTypes.KELP = BlockTypeBuilder
                .builder(BlockKelpBehavior.class)
                .vanillaBlock(BlockId.KELP)
                .setProperties(BlockPropertyTypes.KELP_AGE)
                .build();
    }

    public static void initLadder() {
        if (BlockTypes.LADDER != null) return;
        BlockTypes.LADDER = BlockTypeBuilder
                .builder(BlockLadderBehavior.class)
                .vanillaBlock(BlockId.LADDER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLantern() {
        if (BlockTypes.LANTERN != null) return;
        BlockTypes.LANTERN = BlockTypeBuilder
                .builder(BlockLanternBehavior.class)
                .vanillaBlock(BlockId.LANTERN)
                .setProperties(BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initLapisBlock() {
        if (BlockTypes.LAPIS_BLOCK != null) return;
        BlockTypes.LAPIS_BLOCK = BlockTypeBuilder
                .builder(BlockLapisBlockBehavior.class)
                .vanillaBlock(BlockId.LAPIS_BLOCK)
                .build();
    }

    public static void initLapisOre() {
        if (BlockTypes.LAPIS_ORE != null) return;
        BlockTypes.LAPIS_ORE = BlockTypeBuilder
                .builder(BlockLapisOreBehavior.class)
                .vanillaBlock(BlockId.LAPIS_ORE)
                .build();
    }

    public static void initLargeAmethystBud() {
        if (BlockTypes.LARGE_AMETHYST_BUD != null) return;
        BlockTypes.LARGE_AMETHYST_BUD = BlockTypeBuilder
                .builder(BlockLargeAmethystBudBehavior.class)
                .vanillaBlock(BlockId.LARGE_AMETHYST_BUD)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initLargeFern() {
        if (BlockTypes.LARGE_FERN != null) return;
        BlockTypes.LARGE_FERN = BlockTypeBuilder
                .builder(BlockLargeFernBehavior.class)
                .vanillaBlock(BlockId.LARGE_FERN)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initLava() {
        if (BlockTypes.LAVA != null) return;
        BlockTypes.LAVA = BlockTypeBuilder
                .builder(BlockLavaBehavior.class)
                .vanillaBlock(BlockId.LAVA)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initLectern() {
        if (BlockTypes.LECTERN != null) return;
        BlockTypes.LECTERN = BlockTypeBuilder
                .builder(BlockLecternBehavior.class)
                .vanillaBlock(BlockId.LECTERN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initLever() {
        if (BlockTypes.LEVER != null) return;
        BlockTypes.LEVER = BlockTypeBuilder
                .builder(BlockLeverBehavior.class)
                .vanillaBlock(BlockId.LEVER)
                .setProperties(BlockPropertyTypes.LEVER_DIRECTION, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initLightBlock0() {
        if (BlockTypes.LIGHT_BLOCK_0 != null) return;
        BlockTypes.LIGHT_BLOCK_0 = BlockTypeBuilder
                .builder(BlockLightBlock0Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_0)
                .build();
    }

    public static void initLightBlock1() {
        if (BlockTypes.LIGHT_BLOCK_1 != null) return;
        BlockTypes.LIGHT_BLOCK_1 = BlockTypeBuilder
                .builder(BlockLightBlock1Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_1)
                .build();
    }

    public static void initLightBlock10() {
        if (BlockTypes.LIGHT_BLOCK_10 != null) return;
        BlockTypes.LIGHT_BLOCK_10 = BlockTypeBuilder
                .builder(BlockLightBlock10Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_10)
                .build();
    }

    public static void initLightBlock11() {
        if (BlockTypes.LIGHT_BLOCK_11 != null) return;
        BlockTypes.LIGHT_BLOCK_11 = BlockTypeBuilder
                .builder(BlockLightBlock11Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_11)
                .build();
    }

    public static void initLightBlock12() {
        if (BlockTypes.LIGHT_BLOCK_12 != null) return;
        BlockTypes.LIGHT_BLOCK_12 = BlockTypeBuilder
                .builder(BlockLightBlock12Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_12)
                .build();
    }

    public static void initLightBlock13() {
        if (BlockTypes.LIGHT_BLOCK_13 != null) return;
        BlockTypes.LIGHT_BLOCK_13 = BlockTypeBuilder
                .builder(BlockLightBlock13Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_13)
                .build();
    }

    public static void initLightBlock14() {
        if (BlockTypes.LIGHT_BLOCK_14 != null) return;
        BlockTypes.LIGHT_BLOCK_14 = BlockTypeBuilder
                .builder(BlockLightBlock14Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_14)
                .build();
    }

    public static void initLightBlock15() {
        if (BlockTypes.LIGHT_BLOCK_15 != null) return;
        BlockTypes.LIGHT_BLOCK_15 = BlockTypeBuilder
                .builder(BlockLightBlock15Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_15)
                .build();
    }

    public static void initLightBlock2() {
        if (BlockTypes.LIGHT_BLOCK_2 != null) return;
        BlockTypes.LIGHT_BLOCK_2 = BlockTypeBuilder
                .builder(BlockLightBlock2Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_2)
                .build();
    }

    public static void initLightBlock3() {
        if (BlockTypes.LIGHT_BLOCK_3 != null) return;
        BlockTypes.LIGHT_BLOCK_3 = BlockTypeBuilder
                .builder(BlockLightBlock3Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_3)
                .build();
    }

    public static void initLightBlock4() {
        if (BlockTypes.LIGHT_BLOCK_4 != null) return;
        BlockTypes.LIGHT_BLOCK_4 = BlockTypeBuilder
                .builder(BlockLightBlock4Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_4)
                .build();
    }

    public static void initLightBlock5() {
        if (BlockTypes.LIGHT_BLOCK_5 != null) return;
        BlockTypes.LIGHT_BLOCK_5 = BlockTypeBuilder
                .builder(BlockLightBlock5Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_5)
                .build();
    }

    public static void initLightBlock6() {
        if (BlockTypes.LIGHT_BLOCK_6 != null) return;
        BlockTypes.LIGHT_BLOCK_6 = BlockTypeBuilder
                .builder(BlockLightBlock6Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_6)
                .build();
    }

    public static void initLightBlock7() {
        if (BlockTypes.LIGHT_BLOCK_7 != null) return;
        BlockTypes.LIGHT_BLOCK_7 = BlockTypeBuilder
                .builder(BlockLightBlock7Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_7)
                .build();
    }

    public static void initLightBlock8() {
        if (BlockTypes.LIGHT_BLOCK_8 != null) return;
        BlockTypes.LIGHT_BLOCK_8 = BlockTypeBuilder
                .builder(BlockLightBlock8Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_8)
                .build();
    }

    public static void initLightBlock9() {
        if (BlockTypes.LIGHT_BLOCK_9 != null) return;
        BlockTypes.LIGHT_BLOCK_9 = BlockTypeBuilder
                .builder(BlockLightBlock9Behavior.class)
                .vanillaBlock(BlockId.LIGHT_BLOCK_9)
                .build();
    }

    public static void initLightBlueCandle() {
        if (BlockTypes.LIGHT_BLUE_CANDLE != null) return;
        BlockTypes.LIGHT_BLUE_CANDLE = BlockTypeBuilder
                .builder(BlockLightBlueCandleBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightBlueCandleCake() {
        if (BlockTypes.LIGHT_BLUE_CANDLE_CAKE != null) return;
        BlockTypes.LIGHT_BLUE_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockLightBlueCandleCakeBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightBlueCarpet() {
        if (BlockTypes.LIGHT_BLUE_CARPET != null) return;
        BlockTypes.LIGHT_BLUE_CARPET = BlockTypeBuilder
                .builder(BlockLightBlueCarpetBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CARPET)
                .build();
    }

    public static void initLightBlueConcrete() {
        if (BlockTypes.LIGHT_BLUE_CONCRETE != null) return;
        BlockTypes.LIGHT_BLUE_CONCRETE = BlockTypeBuilder
                .builder(BlockLightBlueConcreteBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CONCRETE)
                .build();
    }

    public static void initLightBlueConcretePowder() {
        if (BlockTypes.LIGHT_BLUE_CONCRETE_POWDER != null) return;
        BlockTypes.LIGHT_BLUE_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockLightBlueConcretePowderBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_CONCRETE_POWDER)
                .build();
    }

    public static void initLightBlueGlazedTerracotta() {
        if (BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockLightBlueGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLightBlueShulkerBox() {
        if (BlockTypes.LIGHT_BLUE_SHULKER_BOX != null) return;
        BlockTypes.LIGHT_BLUE_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockLightBlueShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_SHULKER_BOX)
                .build();
    }

    public static void initLightBlueStainedGlass() {
        if (BlockTypes.LIGHT_BLUE_STAINED_GLASS != null) return;
        BlockTypes.LIGHT_BLUE_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockLightBlueStainedGlassBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_STAINED_GLASS)
                .build();
    }

    public static void initLightBlueStainedGlassPane() {
        if (BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE != null) return;
        BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockLightBlueStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLightBlueTerracotta() {
        if (BlockTypes.LIGHT_BLUE_TERRACOTTA != null) return;
        BlockTypes.LIGHT_BLUE_TERRACOTTA = BlockTypeBuilder
                .builder(BlockLightBlueTerracottaBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_TERRACOTTA)
                .build();
    }

    public static void initLightBlueWool() {
        if (BlockTypes.LIGHT_BLUE_WOOL != null) return;
        BlockTypes.LIGHT_BLUE_WOOL = BlockTypeBuilder
                .builder(BlockLightBlueWoolBehavior.class)
                .vanillaBlock(BlockId.LIGHT_BLUE_WOOL)
                .build();
    }

    public static void initLightGrayCandle() {
        if (BlockTypes.LIGHT_GRAY_CANDLE != null) return;
        BlockTypes.LIGHT_GRAY_CANDLE = BlockTypeBuilder
                .builder(BlockLightGrayCandleBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightGrayCandleCake() {
        if (BlockTypes.LIGHT_GRAY_CANDLE_CAKE != null) return;
        BlockTypes.LIGHT_GRAY_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockLightGrayCandleCakeBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLightGrayCarpet() {
        if (BlockTypes.LIGHT_GRAY_CARPET != null) return;
        BlockTypes.LIGHT_GRAY_CARPET = BlockTypeBuilder
                .builder(BlockLightGrayCarpetBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CARPET)
                .build();
    }

    public static void initLightGrayConcrete() {
        if (BlockTypes.LIGHT_GRAY_CONCRETE != null) return;
        BlockTypes.LIGHT_GRAY_CONCRETE = BlockTypeBuilder
                .builder(BlockLightGrayConcreteBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CONCRETE)
                .build();
    }

    public static void initLightGrayConcretePowder() {
        if (BlockTypes.LIGHT_GRAY_CONCRETE_POWDER != null) return;
        BlockTypes.LIGHT_GRAY_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockLightGrayConcretePowderBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_CONCRETE_POWDER)
                .build();
    }

    public static void initLightGrayShulkerBox() {
        if (BlockTypes.LIGHT_GRAY_SHULKER_BOX != null) return;
        BlockTypes.LIGHT_GRAY_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockLightGrayShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_SHULKER_BOX)
                .build();
    }

    public static void initLightGrayStainedGlass() {
        if (BlockTypes.LIGHT_GRAY_STAINED_GLASS != null) return;
        BlockTypes.LIGHT_GRAY_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockLightGrayStainedGlassBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_STAINED_GLASS)
                .build();
    }

    public static void initLightGrayStainedGlassPane() {
        if (BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE != null) return;
        BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockLightGrayStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLightGrayTerracotta() {
        if (BlockTypes.LIGHT_GRAY_TERRACOTTA != null) return;
        BlockTypes.LIGHT_GRAY_TERRACOTTA = BlockTypeBuilder
                .builder(BlockLightGrayTerracottaBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_TERRACOTTA)
                .build();
    }

    public static void initLightGrayWool() {
        if (BlockTypes.LIGHT_GRAY_WOOL != null) return;
        BlockTypes.LIGHT_GRAY_WOOL = BlockTypeBuilder
                .builder(BlockLightGrayWoolBehavior.class)
                .vanillaBlock(BlockId.LIGHT_GRAY_WOOL)
                .build();
    }

    public static void initLightWeightedPressurePlate() {
        if (BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE != null) return;
        BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockLightWeightedPressurePlateBehavior.class)
                .vanillaBlock(BlockId.LIGHT_WEIGHTED_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initLightningRod() {
        if (BlockTypes.LIGHTNING_ROD != null) return;
        BlockTypes.LIGHTNING_ROD = BlockTypeBuilder
                .builder(BlockLightningRodBehavior.class)
                .vanillaBlock(BlockId.LIGHTNING_ROD)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLilac() {
        if (BlockTypes.LILAC != null) return;
        BlockTypes.LILAC = BlockTypeBuilder
                .builder(BlockLilacBehavior.class)
                .vanillaBlock(BlockId.LILAC)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initLilyOfTheValley() {
        if (BlockTypes.LILY_OF_THE_VALLEY != null) return;
        BlockTypes.LILY_OF_THE_VALLEY = BlockTypeBuilder
                .builder(BlockLilyOfTheValleyBehavior.class)
                .vanillaBlock(BlockId.LILY_OF_THE_VALLEY)
                .build();
    }

    public static void initLimeCandle() {
        if (BlockTypes.LIME_CANDLE != null) return;
        BlockTypes.LIME_CANDLE = BlockTypeBuilder
                .builder(BlockLimeCandleBehavior.class)
                .vanillaBlock(BlockId.LIME_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLimeCandleCake() {
        if (BlockTypes.LIME_CANDLE_CAKE != null) return;
        BlockTypes.LIME_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockLimeCandleCakeBehavior.class)
                .vanillaBlock(BlockId.LIME_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initLimeCarpet() {
        if (BlockTypes.LIME_CARPET != null) return;
        BlockTypes.LIME_CARPET = BlockTypeBuilder
                .builder(BlockLimeCarpetBehavior.class)
                .vanillaBlock(BlockId.LIME_CARPET)
                .build();
    }

    public static void initLimeConcrete() {
        if (BlockTypes.LIME_CONCRETE != null) return;
        BlockTypes.LIME_CONCRETE = BlockTypeBuilder
                .builder(BlockLimeConcreteBehavior.class)
                .vanillaBlock(BlockId.LIME_CONCRETE)
                .build();
    }

    public static void initLimeConcretePowder() {
        if (BlockTypes.LIME_CONCRETE_POWDER != null) return;
        BlockTypes.LIME_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockLimeConcretePowderBehavior.class)
                .vanillaBlock(BlockId.LIME_CONCRETE_POWDER)
                .build();
    }

    public static void initLimeGlazedTerracotta() {
        if (BlockTypes.LIME_GLAZED_TERRACOTTA != null) return;
        BlockTypes.LIME_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockLimeGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.LIME_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initLimeShulkerBox() {
        if (BlockTypes.LIME_SHULKER_BOX != null) return;
        BlockTypes.LIME_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockLimeShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.LIME_SHULKER_BOX)
                .build();
    }

    public static void initLimeStainedGlass() {
        if (BlockTypes.LIME_STAINED_GLASS != null) return;
        BlockTypes.LIME_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockLimeStainedGlassBehavior.class)
                .vanillaBlock(BlockId.LIME_STAINED_GLASS)
                .build();
    }

    public static void initLimeStainedGlassPane() {
        if (BlockTypes.LIME_STAINED_GLASS_PANE != null) return;
        BlockTypes.LIME_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockLimeStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.LIME_STAINED_GLASS_PANE)
                .build();
    }

    public static void initLimeTerracotta() {
        if (BlockTypes.LIME_TERRACOTTA != null) return;
        BlockTypes.LIME_TERRACOTTA = BlockTypeBuilder
                .builder(BlockLimeTerracottaBehavior.class)
                .vanillaBlock(BlockId.LIME_TERRACOTTA)
                .build();
    }

    public static void initLimeWool() {
        if (BlockTypes.LIME_WOOL != null) return;
        BlockTypes.LIME_WOOL = BlockTypeBuilder
                .builder(BlockLimeWoolBehavior.class)
                .vanillaBlock(BlockId.LIME_WOOL)
                .build();
    }

    public static void initLitBlastFurnace() {
        if (BlockTypes.LIT_BLAST_FURNACE != null) return;
        BlockTypes.LIT_BLAST_FURNACE = BlockTypeBuilder
                .builder(BlockLitBlastFurnaceBehavior.class)
                .vanillaBlock(BlockId.LIT_BLAST_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLitDeepslateRedstoneOre() {
        if (BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE != null) return;
        BlockTypes.LIT_DEEPSLATE_REDSTONE_ORE = BlockTypeBuilder
                .builder(BlockLitDeepslateRedstoneOreBehavior.class)
                .vanillaBlock(BlockId.LIT_DEEPSLATE_REDSTONE_ORE)
                .build();
    }

    public static void initLitFurnace() {
        if (BlockTypes.LIT_FURNACE != null) return;
        BlockTypes.LIT_FURNACE = BlockTypeBuilder
                .builder(BlockLitFurnaceBehavior.class)
                .vanillaBlock(BlockId.LIT_FURNACE)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLitPumpkin() {
        if (BlockTypes.LIT_PUMPKIN != null) return;
        BlockTypes.LIT_PUMPKIN = BlockTypeBuilder
                .builder(BlockLitPumpkinBehavior.class)
                .vanillaBlock(BlockId.LIT_PUMPKIN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLitRedstoneLamp() {
        if (BlockTypes.LIT_REDSTONE_LAMP != null) return;
        BlockTypes.LIT_REDSTONE_LAMP = BlockTypeBuilder
                .builder(BlockLitRedstoneLampBehavior.class)
                .vanillaBlock(BlockId.LIT_REDSTONE_LAMP)
                .build();
    }

    public static void initLitRedstoneOre() {
        if (BlockTypes.LIT_REDSTONE_ORE != null) return;
        BlockTypes.LIT_REDSTONE_ORE = BlockTypeBuilder
                .builder(BlockLitRedstoneOreBehavior.class)
                .vanillaBlock(BlockId.LIT_REDSTONE_ORE)
                .build();
    }

    public static void initLitSmoker() {
        if (BlockTypes.LIT_SMOKER != null) return;
        BlockTypes.LIT_SMOKER = BlockTypeBuilder
                .builder(BlockLitSmokerBehavior.class)
                .vanillaBlock(BlockId.LIT_SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initLodestone() {
        if (BlockTypes.LODESTONE != null) return;
        BlockTypes.LODESTONE = BlockTypeBuilder
                .builder(BlockLodestoneBehavior.class)
                .vanillaBlock(BlockId.LODESTONE)
                .build();
    }

    public static void initLoom() {
        if (BlockTypes.LOOM != null) return;
        BlockTypes.LOOM = BlockTypeBuilder
                .builder(BlockLoomBehavior.class)
                .vanillaBlock(BlockId.LOOM)
                .setProperties(BlockPropertyTypes.DIRECTION)
                .build();
    }

    public static void initMagentaCandle() {
        if (BlockTypes.MAGENTA_CANDLE != null) return;
        BlockTypes.MAGENTA_CANDLE = BlockTypeBuilder
                .builder(BlockMagentaCandleBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initMagentaCandleCake() {
        if (BlockTypes.MAGENTA_CANDLE_CAKE != null) return;
        BlockTypes.MAGENTA_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockMagentaCandleCakeBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initMagentaCarpet() {
        if (BlockTypes.MAGENTA_CARPET != null) return;
        BlockTypes.MAGENTA_CARPET = BlockTypeBuilder
                .builder(BlockMagentaCarpetBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_CARPET)
                .build();
    }

    public static void initMagentaConcrete() {
        if (BlockTypes.MAGENTA_CONCRETE != null) return;
        BlockTypes.MAGENTA_CONCRETE = BlockTypeBuilder
                .builder(BlockMagentaConcreteBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_CONCRETE)
                .build();
    }

    public static void initMagentaConcretePowder() {
        if (BlockTypes.MAGENTA_CONCRETE_POWDER != null) return;
        BlockTypes.MAGENTA_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockMagentaConcretePowderBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_CONCRETE_POWDER)
                .build();
    }

    public static void initMagentaGlazedTerracotta() {
        if (BlockTypes.MAGENTA_GLAZED_TERRACOTTA != null) return;
        BlockTypes.MAGENTA_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockMagentaGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initMagentaShulkerBox() {
        if (BlockTypes.MAGENTA_SHULKER_BOX != null) return;
        BlockTypes.MAGENTA_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockMagentaShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_SHULKER_BOX)
                .build();
    }

    public static void initMagentaStainedGlass() {
        if (BlockTypes.MAGENTA_STAINED_GLASS != null) return;
        BlockTypes.MAGENTA_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockMagentaStainedGlassBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_STAINED_GLASS)
                .build();
    }

    public static void initMagentaStainedGlassPane() {
        if (BlockTypes.MAGENTA_STAINED_GLASS_PANE != null) return;
        BlockTypes.MAGENTA_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockMagentaStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_STAINED_GLASS_PANE)
                .build();
    }

    public static void initMagentaTerracotta() {
        if (BlockTypes.MAGENTA_TERRACOTTA != null) return;
        BlockTypes.MAGENTA_TERRACOTTA = BlockTypeBuilder
                .builder(BlockMagentaTerracottaBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_TERRACOTTA)
                .build();
    }

    public static void initMagentaWool() {
        if (BlockTypes.MAGENTA_WOOL != null) return;
        BlockTypes.MAGENTA_WOOL = BlockTypeBuilder
                .builder(BlockMagentaWoolBehavior.class)
                .vanillaBlock(BlockId.MAGENTA_WOOL)
                .build();
    }

    public static void initMagma() {
        if (BlockTypes.MAGMA != null) return;
        BlockTypes.MAGMA = BlockTypeBuilder
                .builder(BlockMagmaBehavior.class)
                .vanillaBlock(BlockId.MAGMA)
                .build();
    }

    public static void initMangroveButton() {
        if (BlockTypes.MANGROVE_BUTTON != null) return;
        BlockTypes.MANGROVE_BUTTON = BlockTypeBuilder
                .builder(BlockMangroveButtonBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initMangroveDoor() {
        if (BlockTypes.MANGROVE_DOOR != null) return;
        BlockTypes.MANGROVE_DOOR = BlockTypeBuilder
                .builder(BlockMangroveDoorBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initMangroveDoubleSlab() {
        if (BlockTypes.MANGROVE_DOUBLE_SLAB != null) return;
        BlockTypes.MANGROVE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockMangroveDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMangroveFence() {
        if (BlockTypes.MANGROVE_FENCE != null) return;
        BlockTypes.MANGROVE_FENCE = BlockTypeBuilder
                .builder(BlockMangroveFenceBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_FENCE)
                .build();
    }

    public static void initMangroveFenceGate() {
        if (BlockTypes.MANGROVE_FENCE_GATE != null) return;
        BlockTypes.MANGROVE_FENCE_GATE = BlockTypeBuilder
                .builder(BlockMangroveFenceGateBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initMangroveHangingSign() {
        if (BlockTypes.MANGROVE_HANGING_SIGN != null) return;
        BlockTypes.MANGROVE_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockMangroveHangingSignBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initMangroveLeaves() {
        if (BlockTypes.MANGROVE_LEAVES != null) return;
        BlockTypes.MANGROVE_LEAVES = BlockTypeBuilder
                .builder(BlockMangroveLeavesBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initMangroveLog() {
        if (BlockTypes.MANGROVE_LOG != null) return;
        BlockTypes.MANGROVE_LOG = BlockTypeBuilder
                .builder(BlockMangroveLogBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initMangrovePlanks() {
        if (BlockTypes.MANGROVE_PLANKS != null) return;
        BlockTypes.MANGROVE_PLANKS = BlockTypeBuilder
                .builder(BlockMangrovePlanksBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_PLANKS)
                .build();
    }

    public static void initMangrovePressurePlate() {
        if (BlockTypes.MANGROVE_PRESSURE_PLATE != null) return;
        BlockTypes.MANGROVE_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockMangrovePressurePlateBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initMangrovePropagule() {
        if (BlockTypes.MANGROVE_PROPAGULE != null) return;
        BlockTypes.MANGROVE_PROPAGULE = BlockTypeBuilder
                .builder(BlockMangrovePropaguleBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_PROPAGULE)
                .setProperties(BlockPropertyTypes.HANGING, BlockPropertyTypes.PROPAGULE_STAGE)
                .build();
    }

    public static void initMangroveRoots() {
        if (BlockTypes.MANGROVE_ROOTS != null) return;
        BlockTypes.MANGROVE_ROOTS = BlockTypeBuilder
                .builder(BlockMangroveRootsBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_ROOTS)
                .build();
    }

    public static void initMangroveSlab() {
        if (BlockTypes.MANGROVE_SLAB != null) return;
        BlockTypes.MANGROVE_SLAB = BlockTypeBuilder
                .builder(BlockMangroveSlabBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMangroveStairs() {
        if (BlockTypes.MANGROVE_STAIRS != null) return;
        BlockTypes.MANGROVE_STAIRS = BlockTypeBuilder
                .builder(BlockMangroveStairsBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMangroveStandingSign() {
        if (BlockTypes.MANGROVE_STANDING_SIGN != null) return;
        BlockTypes.MANGROVE_STANDING_SIGN = BlockTypeBuilder
                .builder(BlockMangroveStandingSignBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initMangroveTrapdoor() {
        if (BlockTypes.MANGROVE_TRAPDOOR != null) return;
        BlockTypes.MANGROVE_TRAPDOOR = BlockTypeBuilder
                .builder(BlockMangroveTrapdoorBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initMangroveWallSign() {
        if (BlockTypes.MANGROVE_WALL_SIGN != null) return;
        BlockTypes.MANGROVE_WALL_SIGN = BlockTypeBuilder
                .builder(BlockMangroveWallSignBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initMangroveWood() {
        if (BlockTypes.MANGROVE_WOOD != null) return;
        BlockTypes.MANGROVE_WOOD = BlockTypeBuilder
                .builder(BlockMangroveWoodBehavior.class)
                .vanillaBlock(BlockId.MANGROVE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS, BlockPropertyTypes.STRIPPED_BIT)
                .build();
    }

    public static void initMediumAmethystBud() {
        if (BlockTypes.MEDIUM_AMETHYST_BUD != null) return;
        BlockTypes.MEDIUM_AMETHYST_BUD = BlockTypeBuilder
                .builder(BlockMediumAmethystBudBehavior.class)
                .vanillaBlock(BlockId.MEDIUM_AMETHYST_BUD)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initMelonBlock() {
        if (BlockTypes.MELON_BLOCK != null) return;
        BlockTypes.MELON_BLOCK = BlockTypeBuilder
                .builder(BlockMelonBlockBehavior.class)
                .vanillaBlock(BlockId.MELON_BLOCK)
                .build();
    }

    public static void initMelonStem() {
        if (BlockTypes.MELON_STEM != null) return;
        BlockTypes.MELON_STEM = BlockTypeBuilder
                .builder(BlockMelonStemBehavior.class)
                .vanillaBlock(BlockId.MELON_STEM)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initMobSpawner() {
        if (BlockTypes.MOB_SPAWNER != null) return;
        BlockTypes.MOB_SPAWNER = BlockTypeBuilder
                .builder(BlockMobSpawnerBehavior.class)
                .vanillaBlock(BlockId.MOB_SPAWNER)
                .build();
    }

    public static void initMossBlock() {
        if (BlockTypes.MOSS_BLOCK != null) return;
        BlockTypes.MOSS_BLOCK = BlockTypeBuilder
                .builder(BlockMossBlockBehavior.class)
                .vanillaBlock(BlockId.MOSS_BLOCK)
                .build();
    }

    public static void initMossCarpet() {
        if (BlockTypes.MOSS_CARPET != null) return;
        BlockTypes.MOSS_CARPET = BlockTypeBuilder
                .builder(BlockMossCarpetBehavior.class)
                .vanillaBlock(BlockId.MOSS_CARPET)
                .build();
    }

    public static void initMossyCobblestone() {
        if (BlockTypes.MOSSY_COBBLESTONE != null) return;
        BlockTypes.MOSSY_COBBLESTONE = BlockTypeBuilder
                .builder(BlockMossyCobblestoneBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE)
                .build();
    }

    public static void initMossyCobblestoneDoubleSlab() {
        if (BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB != null) return;
        BlockTypes.MOSSY_COBBLESTONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockMossyCobblestoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyCobblestoneSlab() {
        if (BlockTypes.MOSSY_COBBLESTONE_SLAB != null) return;
        BlockTypes.MOSSY_COBBLESTONE_SLAB = BlockTypeBuilder
                .builder(BlockMossyCobblestoneSlabBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyCobblestoneStairs() {
        if (BlockTypes.MOSSY_COBBLESTONE_STAIRS != null) return;
        BlockTypes.MOSSY_COBBLESTONE_STAIRS = BlockTypeBuilder
                .builder(BlockMossyCobblestoneStairsBehavior.class)
                .vanillaBlock(BlockId.MOSSY_COBBLESTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMossyStoneBrickDoubleSlab() {
        if (BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.MOSSY_STONE_BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockMossyStoneBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyStoneBrickSlab() {
        if (BlockTypes.MOSSY_STONE_BRICK_SLAB != null) return;
        BlockTypes.MOSSY_STONE_BRICK_SLAB = BlockTypeBuilder
                .builder(BlockMossyStoneBrickSlabBehavior.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMossyStoneBrickStairs() {
        if (BlockTypes.MOSSY_STONE_BRICK_STAIRS != null) return;
        BlockTypes.MOSSY_STONE_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockMossyStoneBrickStairsBehavior.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMossyStoneBricks() {
        if (BlockTypes.MOSSY_STONE_BRICKS != null) return;
        BlockTypes.MOSSY_STONE_BRICKS = BlockTypeBuilder
                .builder(BlockMossyStoneBricksBehavior.class)
                .vanillaBlock(BlockId.MOSSY_STONE_BRICKS)
                .build();
    }

    public static void initMovingBlock() {
        if (BlockTypes.MOVING_BLOCK != null) return;
        BlockTypes.MOVING_BLOCK = BlockTypeBuilder
                .builder(BlockMovingBlockBehavior.class)
                .vanillaBlock(BlockId.MOVING_BLOCK)
                .build();
    }

    public static void initMud() {
        if (BlockTypes.MUD != null) return;
        BlockTypes.MUD = BlockTypeBuilder
                .builder(BlockMudBehavior.class)
                .vanillaBlock(BlockId.MUD)
                .build();
    }

    public static void initMudBrickDoubleSlab() {
        if (BlockTypes.MUD_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.MUD_BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockMudBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMudBrickSlab() {
        if (BlockTypes.MUD_BRICK_SLAB != null) return;
        BlockTypes.MUD_BRICK_SLAB = BlockTypeBuilder
                .builder(BlockMudBrickSlabBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initMudBrickStairs() {
        if (BlockTypes.MUD_BRICK_STAIRS != null) return;
        BlockTypes.MUD_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockMudBrickStairsBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initMudBrickWall() {
        if (BlockTypes.MUD_BRICK_WALL != null) return;
        BlockTypes.MUD_BRICK_WALL = BlockTypeBuilder
                .builder(BlockMudBrickWallBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initMudBricks() {
        if (BlockTypes.MUD_BRICKS != null) return;
        BlockTypes.MUD_BRICKS = BlockTypeBuilder
                .builder(BlockMudBricksBehavior.class)
                .vanillaBlock(BlockId.MUD_BRICKS)
                .build();
    }

    public static void initMuddyMangroveRoots() {
        if (BlockTypes.MUDDY_MANGROVE_ROOTS != null) return;
        BlockTypes.MUDDY_MANGROVE_ROOTS = BlockTypeBuilder
                .builder(BlockMuddyMangroveRootsBehavior.class)
                .vanillaBlock(BlockId.MUDDY_MANGROVE_ROOTS)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initMycelium() {
        if (BlockTypes.MYCELIUM != null) return;
        BlockTypes.MYCELIUM = BlockTypeBuilder
                .builder(BlockMyceliumBehavior.class)
                .vanillaBlock(BlockId.MYCELIUM)
                .build();
    }

    public static void initNetherBrick() {
        if (BlockTypes.NETHER_BRICK != null) return;
        BlockTypes.NETHER_BRICK = BlockTypeBuilder
                .builder(BlockNetherBrickBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK)
                .build();
    }

    public static void initNetherBrickDoubleSlab() {
        if (BlockTypes.NETHER_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.NETHER_BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockNetherBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNetherBrickFence() {
        if (BlockTypes.NETHER_BRICK_FENCE != null) return;
        BlockTypes.NETHER_BRICK_FENCE = BlockTypeBuilder
                .builder(BlockNetherBrickFenceBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK_FENCE)
                .build();
    }

    public static void initNetherBrickSlab() {
        if (BlockTypes.NETHER_BRICK_SLAB != null) return;
        BlockTypes.NETHER_BRICK_SLAB = BlockTypeBuilder
                .builder(BlockNetherBrickSlabBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNetherBrickStairs() {
        if (BlockTypes.NETHER_BRICK_STAIRS != null) return;
        BlockTypes.NETHER_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockNetherBrickStairsBehavior.class)
                .vanillaBlock(BlockId.NETHER_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initNetherGoldOre() {
        if (BlockTypes.NETHER_GOLD_ORE != null) return;
        BlockTypes.NETHER_GOLD_ORE = BlockTypeBuilder
                .builder(BlockNetherGoldOreBehavior.class)
                .vanillaBlock(BlockId.NETHER_GOLD_ORE)
                .build();
    }

    public static void initNetherSprouts() {
        if (BlockTypes.NETHER_SPROUTS != null) return;
        BlockTypes.NETHER_SPROUTS = BlockTypeBuilder
                .builder(BlockNetherSproutsBehavior.class)
                .vanillaBlock(BlockId.NETHER_SPROUTS)
                .build();
    }

    public static void initNetherWart() {
        if (BlockTypes.NETHER_WART != null) return;
        BlockTypes.NETHER_WART = BlockTypeBuilder
                .builder(BlockNetherWartBehavior.class)
                .vanillaBlock(BlockId.NETHER_WART)
                .setProperties(BlockPropertyTypes.AGE_4)
                .build();
    }

    public static void initNetherWartBlock() {
        if (BlockTypes.NETHER_WART_BLOCK != null) return;
        BlockTypes.NETHER_WART_BLOCK = BlockTypeBuilder
                .builder(BlockNetherWartBlockBehavior.class)
                .vanillaBlock(BlockId.NETHER_WART_BLOCK)
                .build();
    }

    public static void initNetheriteBlock() {
        if (BlockTypes.NETHERITE_BLOCK != null) return;
        BlockTypes.NETHERITE_BLOCK = BlockTypeBuilder
                .builder(BlockNetheriteBlockBehavior.class)
                .vanillaBlock(BlockId.NETHERITE_BLOCK)
                .build();
    }

    public static void initNetherrack() {
        if (BlockTypes.NETHERRACK != null) return;
        BlockTypes.NETHERRACK = BlockTypeBuilder
                .builder(BlockNetherrackBehavior.class)
                .vanillaBlock(BlockId.NETHERRACK)
                .build();
    }

    public static void initNetherreactor() {
        if (BlockTypes.NETHERREACTOR != null) return;
        BlockTypes.NETHERREACTOR = BlockTypeBuilder
                .builder(BlockNetherreactorBehavior.class)
                .vanillaBlock(BlockId.NETHERREACTOR)
                .build();
    }

    public static void initNormalStoneDoubleSlab() {
        if (BlockTypes.NORMAL_STONE_DOUBLE_SLAB != null) return;
        BlockTypes.NORMAL_STONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockNormalStoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.NORMAL_STONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNormalStoneSlab() {
        if (BlockTypes.NORMAL_STONE_SLAB != null) return;
        BlockTypes.NORMAL_STONE_SLAB = BlockTypeBuilder
                .builder(BlockNormalStoneSlabBehavior.class)
                .vanillaBlock(BlockId.NORMAL_STONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initNormalStoneStairs() {
        if (BlockTypes.NORMAL_STONE_STAIRS != null) return;
        BlockTypes.NORMAL_STONE_STAIRS = BlockTypeBuilder
                .builder(BlockNormalStoneStairsBehavior.class)
                .vanillaBlock(BlockId.NORMAL_STONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initNoteblock() {
        if (BlockTypes.NOTEBLOCK != null) return;
        BlockTypes.NOTEBLOCK = BlockTypeBuilder
                .builder(BlockNoteblockBehavior.class)
                .vanillaBlock(BlockId.NOTEBLOCK)
                .build();
    }

    public static void initOakDoubleSlab() {
        if (BlockTypes.OAK_DOUBLE_SLAB != null) return;
        BlockTypes.OAK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockOakDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.OAK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initOakFence() {
        if (BlockTypes.OAK_FENCE != null) return;
        BlockTypes.OAK_FENCE = BlockTypeBuilder
                .builder(BlockOakFenceBehavior.class)
                .vanillaBlock(BlockId.OAK_FENCE)
                .build();
    }

    public static void initOakHangingSign() {
        if (BlockTypes.OAK_HANGING_SIGN != null) return;
        BlockTypes.OAK_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockOakHangingSignBehavior.class)
                .vanillaBlock(BlockId.OAK_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initOakLeaves() {
        if (BlockTypes.OAK_LEAVES != null) return;
        BlockTypes.OAK_LEAVES = BlockTypeBuilder
                .builder(BlockOakLeavesBehavior.class)
                .vanillaBlock(BlockId.OAK_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initOakLog() {
        if (BlockTypes.OAK_LOG != null) return;
        BlockTypes.OAK_LOG = BlockTypeBuilder
                .builder(BlockOakLogBehavior.class)
                .vanillaBlock(BlockId.OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initOakPlanks() {
        if (BlockTypes.OAK_PLANKS != null) return;
        BlockTypes.OAK_PLANKS = BlockTypeBuilder
                .builder(BlockOakPlanksBehavior.class)
                .vanillaBlock(BlockId.OAK_PLANKS)
                .build();
    }

    public static void initOakSapling() {
        if (BlockTypes.OAK_SAPLING != null) return;
        BlockTypes.OAK_SAPLING = BlockTypeBuilder
                .builder(BlockOakSaplingBehavior.class)
                .vanillaBlock(BlockId.OAK_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initOakSlab() {
        if (BlockTypes.OAK_SLAB != null) return;
        BlockTypes.OAK_SLAB = BlockTypeBuilder
                .builder(BlockOakSlabBehavior.class)
                .vanillaBlock(BlockId.OAK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initOakStairs() {
        if (BlockTypes.OAK_STAIRS != null) return;
        BlockTypes.OAK_STAIRS = BlockTypeBuilder
                .builder(BlockOakStairsBehavior.class)
                .vanillaBlock(BlockId.OAK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initOakWood() {
        if (BlockTypes.OAK_WOOD != null) return;
        BlockTypes.OAK_WOOD = BlockTypeBuilder
                .builder(BlockOakWoodBehavior.class)
                .vanillaBlock(BlockId.OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initObserver() {
        if (BlockTypes.OBSERVER != null) return;
        BlockTypes.OBSERVER = BlockTypeBuilder
                .builder(BlockObserverBehavior.class)
                .vanillaBlock(BlockId.OBSERVER)
                .setProperties(BlockPropertyTypes.MINECRAFT_FACING_DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initObsidian() {
        if (BlockTypes.OBSIDIAN != null) return;
        BlockTypes.OBSIDIAN = BlockTypeBuilder
                .builder(BlockObsidianBehavior.class)
                .vanillaBlock(BlockId.OBSIDIAN)
                .build();
    }

    public static void initOchreFroglight() {
        if (BlockTypes.OCHRE_FROGLIGHT != null) return;
        BlockTypes.OCHRE_FROGLIGHT = BlockTypeBuilder
                .builder(BlockOchreFroglightBehavior.class)
                .vanillaBlock(BlockId.OCHRE_FROGLIGHT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initOrangeCandle() {
        if (BlockTypes.ORANGE_CANDLE != null) return;
        BlockTypes.ORANGE_CANDLE = BlockTypeBuilder
                .builder(BlockOrangeCandleBehavior.class)
                .vanillaBlock(BlockId.ORANGE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initOrangeCandleCake() {
        if (BlockTypes.ORANGE_CANDLE_CAKE != null) return;
        BlockTypes.ORANGE_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockOrangeCandleCakeBehavior.class)
                .vanillaBlock(BlockId.ORANGE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initOrangeCarpet() {
        if (BlockTypes.ORANGE_CARPET != null) return;
        BlockTypes.ORANGE_CARPET = BlockTypeBuilder
                .builder(BlockOrangeCarpetBehavior.class)
                .vanillaBlock(BlockId.ORANGE_CARPET)
                .build();
    }

    public static void initOrangeConcrete() {
        if (BlockTypes.ORANGE_CONCRETE != null) return;
        BlockTypes.ORANGE_CONCRETE = BlockTypeBuilder
                .builder(BlockOrangeConcreteBehavior.class)
                .vanillaBlock(BlockId.ORANGE_CONCRETE)
                .build();
    }

    public static void initOrangeConcretePowder() {
        if (BlockTypes.ORANGE_CONCRETE_POWDER != null) return;
        BlockTypes.ORANGE_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockOrangeConcretePowderBehavior.class)
                .vanillaBlock(BlockId.ORANGE_CONCRETE_POWDER)
                .build();
    }

    public static void initOrangeGlazedTerracotta() {
        if (BlockTypes.ORANGE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.ORANGE_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockOrangeGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.ORANGE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initOrangeShulkerBox() {
        if (BlockTypes.ORANGE_SHULKER_BOX != null) return;
        BlockTypes.ORANGE_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockOrangeShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.ORANGE_SHULKER_BOX)
                .build();
    }

    public static void initOrangeStainedGlass() {
        if (BlockTypes.ORANGE_STAINED_GLASS != null) return;
        BlockTypes.ORANGE_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockOrangeStainedGlassBehavior.class)
                .vanillaBlock(BlockId.ORANGE_STAINED_GLASS)
                .build();
    }

    public static void initOrangeStainedGlassPane() {
        if (BlockTypes.ORANGE_STAINED_GLASS_PANE != null) return;
        BlockTypes.ORANGE_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockOrangeStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.ORANGE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initOrangeTerracotta() {
        if (BlockTypes.ORANGE_TERRACOTTA != null) return;
        BlockTypes.ORANGE_TERRACOTTA = BlockTypeBuilder
                .builder(BlockOrangeTerracottaBehavior.class)
                .vanillaBlock(BlockId.ORANGE_TERRACOTTA)
                .build();
    }

    public static void initOrangeTulip() {
        if (BlockTypes.ORANGE_TULIP != null) return;
        BlockTypes.ORANGE_TULIP = BlockTypeBuilder
                .builder(BlockOrangeTulipBehavior.class)
                .vanillaBlock(BlockId.ORANGE_TULIP)
                .build();
    }

    public static void initOrangeWool() {
        if (BlockTypes.ORANGE_WOOL != null) return;
        BlockTypes.ORANGE_WOOL = BlockTypeBuilder
                .builder(BlockOrangeWoolBehavior.class)
                .vanillaBlock(BlockId.ORANGE_WOOL)
                .build();
    }

    public static void initOxeyeDaisy() {
        if (BlockTypes.OXEYE_DAISY != null) return;
        BlockTypes.OXEYE_DAISY = BlockTypeBuilder
                .builder(BlockOxeyeDaisyBehavior.class)
                .vanillaBlock(BlockId.OXEYE_DAISY)
                .build();
    }

    public static void initOxidizedChiseledCopper() {
        if (BlockTypes.OXIDIZED_CHISELED_COPPER != null) return;
        BlockTypes.OXIDIZED_CHISELED_COPPER = BlockTypeBuilder
                .builder(BlockOxidizedChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_CHISELED_COPPER)
                .build();
    }

    public static void initOxidizedCopper() {
        if (BlockTypes.OXIDIZED_COPPER != null) return;
        BlockTypes.OXIDIZED_COPPER = BlockTypeBuilder
                .builder(BlockOxidizedCopperBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER)
                .build();
    }

    public static void initOxidizedCopperBulb() {
        if (BlockTypes.OXIDIZED_COPPER_BULB != null) return;
        BlockTypes.OXIDIZED_COPPER_BULB = BlockTypeBuilder
                .builder(BlockOxidizedCopperBulbBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initOxidizedCopperDoor() {
        if (BlockTypes.OXIDIZED_COPPER_DOOR != null) return;
        BlockTypes.OXIDIZED_COPPER_DOOR = BlockTypeBuilder
                .builder(BlockOxidizedCopperDoorBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initOxidizedCopperGrate() {
        if (BlockTypes.OXIDIZED_COPPER_GRATE != null) return;
        BlockTypes.OXIDIZED_COPPER_GRATE = BlockTypeBuilder
                .builder(BlockOxidizedCopperGrateBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_GRATE)
                .build();
    }

    public static void initOxidizedCopperTrapdoor() {
        if (BlockTypes.OXIDIZED_COPPER_TRAPDOOR != null) return;
        BlockTypes.OXIDIZED_COPPER_TRAPDOOR = BlockTypeBuilder
                .builder(BlockOxidizedCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initOxidizedCutCopper() {
        if (BlockTypes.OXIDIZED_CUT_COPPER != null) return;
        BlockTypes.OXIDIZED_CUT_COPPER = BlockTypeBuilder
                .builder(BlockOxidizedCutCopperBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER)
                .build();
    }

    public static void initOxidizedCutCopperSlab() {
        if (BlockTypes.OXIDIZED_CUT_COPPER_SLAB != null) return;
        BlockTypes.OXIDIZED_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockOxidizedCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initOxidizedCutCopperStairs() {
        if (BlockTypes.OXIDIZED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.OXIDIZED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockOxidizedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initOxidizedDoubleCutCopperSlab() {
        if (BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.OXIDIZED_DOUBLE_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockOxidizedDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPackedIce() {
        if (BlockTypes.PACKED_ICE != null) return;
        BlockTypes.PACKED_ICE = BlockTypeBuilder
                .builder(BlockPackedIceBehavior.class)
                .vanillaBlock(BlockId.PACKED_ICE)
                .build();
    }

    public static void initPackedMud() {
        if (BlockTypes.PACKED_MUD != null) return;
        BlockTypes.PACKED_MUD = BlockTypeBuilder
                .builder(BlockPackedMudBehavior.class)
                .vanillaBlock(BlockId.PACKED_MUD)
                .build();
    }

    public static void initPearlescentFroglight() {
        if (BlockTypes.PEARLESCENT_FROGLIGHT != null) return;
        BlockTypes.PEARLESCENT_FROGLIGHT = BlockTypeBuilder
                .builder(BlockPearlescentFroglightBehavior.class)
                .vanillaBlock(BlockId.PEARLESCENT_FROGLIGHT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPeony() {
        if (BlockTypes.PEONY != null) return;
        BlockTypes.PEONY = BlockTypeBuilder
                .builder(BlockPeonyBehavior.class)
                .vanillaBlock(BlockId.PEONY)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initPetrifiedOakDoubleSlab() {
        if (BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB != null) return;
        BlockTypes.PETRIFIED_OAK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPetrifiedOakDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.PETRIFIED_OAK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPetrifiedOakSlab() {
        if (BlockTypes.PETRIFIED_OAK_SLAB != null) return;
        BlockTypes.PETRIFIED_OAK_SLAB = BlockTypeBuilder
                .builder(BlockPetrifiedOakSlabBehavior.class)
                .vanillaBlock(BlockId.PETRIFIED_OAK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPinkCandle() {
        if (BlockTypes.PINK_CANDLE != null) return;
        BlockTypes.PINK_CANDLE = BlockTypeBuilder
                .builder(BlockPinkCandleBehavior.class)
                .vanillaBlock(BlockId.PINK_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPinkCandleCake() {
        if (BlockTypes.PINK_CANDLE_CAKE != null) return;
        BlockTypes.PINK_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockPinkCandleCakeBehavior.class)
                .vanillaBlock(BlockId.PINK_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPinkCarpet() {
        if (BlockTypes.PINK_CARPET != null) return;
        BlockTypes.PINK_CARPET = BlockTypeBuilder
                .builder(BlockPinkCarpetBehavior.class)
                .vanillaBlock(BlockId.PINK_CARPET)
                .build();
    }

    public static void initPinkConcrete() {
        if (BlockTypes.PINK_CONCRETE != null) return;
        BlockTypes.PINK_CONCRETE = BlockTypeBuilder
                .builder(BlockPinkConcreteBehavior.class)
                .vanillaBlock(BlockId.PINK_CONCRETE)
                .build();
    }

    public static void initPinkConcretePowder() {
        if (BlockTypes.PINK_CONCRETE_POWDER != null) return;
        BlockTypes.PINK_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockPinkConcretePowderBehavior.class)
                .vanillaBlock(BlockId.PINK_CONCRETE_POWDER)
                .build();
    }

    public static void initPinkGlazedTerracotta() {
        if (BlockTypes.PINK_GLAZED_TERRACOTTA != null) return;
        BlockTypes.PINK_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockPinkGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.PINK_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPinkPetals() {
        if (BlockTypes.PINK_PETALS != null) return;
        BlockTypes.PINK_PETALS = BlockTypeBuilder
                .builder(BlockPinkPetalsBehavior.class)
                .vanillaBlock(BlockId.PINK_PETALS)
                .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initPinkShulkerBox() {
        if (BlockTypes.PINK_SHULKER_BOX != null) return;
        BlockTypes.PINK_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockPinkShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.PINK_SHULKER_BOX)
                .build();
    }

    public static void initPinkStainedGlass() {
        if (BlockTypes.PINK_STAINED_GLASS != null) return;
        BlockTypes.PINK_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockPinkStainedGlassBehavior.class)
                .vanillaBlock(BlockId.PINK_STAINED_GLASS)
                .build();
    }

    public static void initPinkStainedGlassPane() {
        if (BlockTypes.PINK_STAINED_GLASS_PANE != null) return;
        BlockTypes.PINK_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockPinkStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.PINK_STAINED_GLASS_PANE)
                .build();
    }

    public static void initPinkTerracotta() {
        if (BlockTypes.PINK_TERRACOTTA != null) return;
        BlockTypes.PINK_TERRACOTTA = BlockTypeBuilder
                .builder(BlockPinkTerracottaBehavior.class)
                .vanillaBlock(BlockId.PINK_TERRACOTTA)
                .build();
    }

    public static void initPinkTulip() {
        if (BlockTypes.PINK_TULIP != null) return;
        BlockTypes.PINK_TULIP = BlockTypeBuilder
                .builder(BlockPinkTulipBehavior.class)
                .vanillaBlock(BlockId.PINK_TULIP)
                .build();
    }

    public static void initPinkWool() {
        if (BlockTypes.PINK_WOOL != null) return;
        BlockTypes.PINK_WOOL = BlockTypeBuilder
                .builder(BlockPinkWoolBehavior.class)
                .vanillaBlock(BlockId.PINK_WOOL)
                .build();
    }

    public static void initPiston() {
        if (BlockTypes.PISTON != null) return;
        BlockTypes.PISTON = BlockTypeBuilder
                .builder(BlockPistonBehavior.class)
                .vanillaBlock(BlockId.PISTON)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPistonArmCollision() {
        if (BlockTypes.PISTON_ARM_COLLISION != null) return;
        BlockTypes.PISTON_ARM_COLLISION = BlockTypeBuilder
                .builder(BlockPistonArmCollisionBehavior.class)
                .vanillaBlock(BlockId.PISTON_ARM_COLLISION)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPitcherCrop() {
        if (BlockTypes.PITCHER_CROP != null) return;
        BlockTypes.PITCHER_CROP = BlockTypeBuilder
                .builder(BlockPitcherCropBehavior.class)
                .vanillaBlock(BlockId.PITCHER_CROP)
                .setProperties(BlockPropertyTypes.GROWTH, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initPitcherPlant() {
        if (BlockTypes.PITCHER_PLANT != null) return;
        BlockTypes.PITCHER_PLANT = BlockTypeBuilder
                .builder(BlockPitcherPlantBehavior.class)
                .vanillaBlock(BlockId.PITCHER_PLANT)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initPodzol() {
        if (BlockTypes.PODZOL != null) return;
        BlockTypes.PODZOL = BlockTypeBuilder
                .builder(BlockPodzolBehavior.class)
                .vanillaBlock(BlockId.PODZOL)
                .build();
    }

    public static void initPointedDripstone() {
        if (BlockTypes.POINTED_DRIPSTONE != null) return;
        BlockTypes.POINTED_DRIPSTONE = BlockTypeBuilder
                .builder(BlockPointedDripstoneBehavior.class)
                .vanillaBlock(BlockId.POINTED_DRIPSTONE)
                .setProperties(BlockPropertyTypes.DRIPSTONE_THICKNESS, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initPolishedAndesite() {
        if (BlockTypes.POLISHED_ANDESITE != null) return;
        BlockTypes.POLISHED_ANDESITE = BlockTypeBuilder
                .builder(BlockPolishedAndesiteBehavior.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE)
                .build();
    }

    public static void initPolishedAndesiteDoubleSlab() {
        if (BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_ANDESITE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedAndesiteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedAndesiteSlab() {
        if (BlockTypes.POLISHED_ANDESITE_SLAB != null) return;
        BlockTypes.POLISHED_ANDESITE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedAndesiteSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedAndesiteStairs() {
        if (BlockTypes.POLISHED_ANDESITE_STAIRS != null) return;
        BlockTypes.POLISHED_ANDESITE_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedAndesiteStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_ANDESITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedBasalt() {
        if (BlockTypes.POLISHED_BASALT != null) return;
        BlockTypes.POLISHED_BASALT = BlockTypeBuilder
                .builder(BlockPolishedBasaltBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BASALT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPolishedBlackstone() {
        if (BlockTypes.POLISHED_BLACKSTONE != null) return;
        BlockTypes.POLISHED_BLACKSTONE = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE)
                .build();
    }

    public static void initPolishedBlackstoneBrickDoubleSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstoneBrickSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneBrickSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstoneBrickStairs() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneBrickStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedBlackstoneBrickWall() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneBrickWallBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPolishedBlackstoneBricks() {
        if (BlockTypes.POLISHED_BLACKSTONE_BRICKS != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BRICKS = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneBricksBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BRICKS)
                .build();
    }

    public static void initPolishedBlackstoneButton() {
        if (BlockTypes.POLISHED_BLACKSTONE_BUTTON != null) return;
        BlockTypes.POLISHED_BLACKSTONE_BUTTON = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneButtonBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPolishedBlackstoneDoubleSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstonePressurePlate() {
        if (BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE != null) return;
        BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockPolishedBlackstonePressurePlateBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initPolishedBlackstoneSlab() {
        if (BlockTypes.POLISHED_BLACKSTONE_SLAB != null) return;
        BlockTypes.POLISHED_BLACKSTONE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedBlackstoneStairs() {
        if (BlockTypes.POLISHED_BLACKSTONE_STAIRS != null) return;
        BlockTypes.POLISHED_BLACKSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedBlackstoneWall() {
        if (BlockTypes.POLISHED_BLACKSTONE_WALL != null) return;
        BlockTypes.POLISHED_BLACKSTONE_WALL = BlockTypeBuilder
                .builder(BlockPolishedBlackstoneWallBehavior.class)
                .vanillaBlock(BlockId.POLISHED_BLACKSTONE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPolishedDeepslate() {
        if (BlockTypes.POLISHED_DEEPSLATE != null) return;
        BlockTypes.POLISHED_DEEPSLATE = BlockTypeBuilder
                .builder(BlockPolishedDeepslateBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE)
                .build();
    }

    public static void initPolishedDeepslateDoubleSlab() {
        if (BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_DEEPSLATE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedDeepslateDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDeepslateSlab() {
        if (BlockTypes.POLISHED_DEEPSLATE_SLAB != null) return;
        BlockTypes.POLISHED_DEEPSLATE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedDeepslateSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDeepslateStairs() {
        if (BlockTypes.POLISHED_DEEPSLATE_STAIRS != null) return;
        BlockTypes.POLISHED_DEEPSLATE_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedDeepslateStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedDeepslateWall() {
        if (BlockTypes.POLISHED_DEEPSLATE_WALL != null) return;
        BlockTypes.POLISHED_DEEPSLATE_WALL = BlockTypeBuilder
                .builder(BlockPolishedDeepslateWallBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DEEPSLATE_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPolishedDiorite() {
        if (BlockTypes.POLISHED_DIORITE != null) return;
        BlockTypes.POLISHED_DIORITE = BlockTypeBuilder
                .builder(BlockPolishedDioriteBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE)
                .build();
    }

    public static void initPolishedDioriteDoubleSlab() {
        if (BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_DIORITE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedDioriteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDioriteSlab() {
        if (BlockTypes.POLISHED_DIORITE_SLAB != null) return;
        BlockTypes.POLISHED_DIORITE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedDioriteSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedDioriteStairs() {
        if (BlockTypes.POLISHED_DIORITE_STAIRS != null) return;
        BlockTypes.POLISHED_DIORITE_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedDioriteStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_DIORITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedGranite() {
        if (BlockTypes.POLISHED_GRANITE != null) return;
        BlockTypes.POLISHED_GRANITE = BlockTypeBuilder
                .builder(BlockPolishedGraniteBehavior.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE)
                .build();
    }

    public static void initPolishedGraniteDoubleSlab() {
        if (BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_GRANITE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedGraniteDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedGraniteSlab() {
        if (BlockTypes.POLISHED_GRANITE_SLAB != null) return;
        BlockTypes.POLISHED_GRANITE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedGraniteSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedGraniteStairs() {
        if (BlockTypes.POLISHED_GRANITE_STAIRS != null) return;
        BlockTypes.POLISHED_GRANITE_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedGraniteStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_GRANITE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedTuff() {
        if (BlockTypes.POLISHED_TUFF != null) return;
        BlockTypes.POLISHED_TUFF = BlockTypeBuilder
                .builder(BlockPolishedTuffBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF)
                .build();
    }

    public static void initPolishedTuffDoubleSlab() {
        if (BlockTypes.POLISHED_TUFF_DOUBLE_SLAB != null) return;
        BlockTypes.POLISHED_TUFF_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPolishedTuffDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedTuffSlab() {
        if (BlockTypes.POLISHED_TUFF_SLAB != null) return;
        BlockTypes.POLISHED_TUFF_SLAB = BlockTypeBuilder
                .builder(BlockPolishedTuffSlabBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPolishedTuffStairs() {
        if (BlockTypes.POLISHED_TUFF_STAIRS != null) return;
        BlockTypes.POLISHED_TUFF_STAIRS = BlockTypeBuilder
                .builder(BlockPolishedTuffStairsBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPolishedTuffWall() {
        if (BlockTypes.POLISHED_TUFF_WALL != null) return;
        BlockTypes.POLISHED_TUFF_WALL = BlockTypeBuilder
                .builder(BlockPolishedTuffWallBehavior.class)
                .vanillaBlock(BlockId.POLISHED_TUFF_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initPoppy() {
        if (BlockTypes.POPPY != null) return;
        BlockTypes.POPPY = BlockTypeBuilder
                .builder(BlockPoppyBehavior.class)
                .vanillaBlock(BlockId.POPPY)
                .build();
    }

    public static void initPortal() {
        if (BlockTypes.PORTAL != null) return;
        BlockTypes.PORTAL = BlockTypeBuilder
                .builder(BlockPortalBehavior.class)
                .vanillaBlock(BlockId.PORTAL)
                .setProperties(BlockPropertyTypes.PORTAL_AXIS)
                .build();
    }

    public static void initPotatoes() {
        if (BlockTypes.POTATOES != null) return;
        BlockTypes.POTATOES = BlockTypeBuilder
                .builder(BlockPotatoesBehavior.class)
                .vanillaBlock(BlockId.POTATOES)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initPowderSnow() {
        if (BlockTypes.POWDER_SNOW != null) return;
        BlockTypes.POWDER_SNOW = BlockTypeBuilder
                .builder(BlockPowderSnowBehavior.class)
                .vanillaBlock(BlockId.POWDER_SNOW)
                .build();
    }

    public static void initPoweredComparator() {
        if (BlockTypes.POWERED_COMPARATOR != null) return;
        BlockTypes.POWERED_COMPARATOR = BlockTypeBuilder
                .builder(BlockPoweredComparatorBehavior.class)
                .vanillaBlock(BlockId.POWERED_COMPARATOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                .build();
    }

    public static void initPoweredRepeater() {
        if (BlockTypes.POWERED_REPEATER != null) return;
        BlockTypes.POWERED_REPEATER = BlockTypeBuilder
                .builder(BlockPoweredRepeaterBehavior.class)
                .vanillaBlock(BlockId.POWERED_REPEATER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                .build();
    }

    public static void initPrismarine() {
        if (BlockTypes.PRISMARINE != null) return;
        BlockTypes.PRISMARINE = BlockTypeBuilder
                .builder(BlockPrismarineBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE)
                .build();
    }

    public static void initPrismarineBrickDoubleSlab() {
        if (BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.PRISMARINE_BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPrismarineBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineBrickSlab() {
        if (BlockTypes.PRISMARINE_BRICK_SLAB != null) return;
        BlockTypes.PRISMARINE_BRICK_SLAB = BlockTypeBuilder
                .builder(BlockPrismarineBrickSlabBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineBricks() {
        if (BlockTypes.PRISMARINE_BRICKS != null) return;
        BlockTypes.PRISMARINE_BRICKS = BlockTypeBuilder
                .builder(BlockPrismarineBricksBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICKS)
                .build();
    }

    public static void initPrismarineBricksStairs() {
        if (BlockTypes.PRISMARINE_BRICKS_STAIRS != null) return;
        BlockTypes.PRISMARINE_BRICKS_STAIRS = BlockTypeBuilder
                .builder(BlockPrismarineBricksStairsBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_BRICKS_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPrismarineDoubleSlab() {
        if (BlockTypes.PRISMARINE_DOUBLE_SLAB != null) return;
        BlockTypes.PRISMARINE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPrismarineDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineSlab() {
        if (BlockTypes.PRISMARINE_SLAB != null) return;
        BlockTypes.PRISMARINE_SLAB = BlockTypeBuilder
                .builder(BlockPrismarineSlabBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPrismarineStairs() {
        if (BlockTypes.PRISMARINE_STAIRS != null) return;
        BlockTypes.PRISMARINE_STAIRS = BlockTypeBuilder
                .builder(BlockPrismarineStairsBehavior.class)
                .vanillaBlock(BlockId.PRISMARINE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initPumpkin() {
        if (BlockTypes.PUMPKIN != null) return;
        BlockTypes.PUMPKIN = BlockTypeBuilder
                .builder(BlockPumpkinBehavior.class)
                .vanillaBlock(BlockId.PUMPKIN)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initPumpkinStem() {
        if (BlockTypes.PUMPKIN_STEM != null) return;
        BlockTypes.PUMPKIN_STEM = BlockTypeBuilder
                .builder(BlockPumpkinStemBehavior.class)
                .vanillaBlock(BlockId.PUMPKIN_STEM)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initPurpleCandle() {
        if (BlockTypes.PURPLE_CANDLE != null) return;
        BlockTypes.PURPLE_CANDLE = BlockTypeBuilder
                .builder(BlockPurpleCandleBehavior.class)
                .vanillaBlock(BlockId.PURPLE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPurpleCandleCake() {
        if (BlockTypes.PURPLE_CANDLE_CAKE != null) return;
        BlockTypes.PURPLE_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockPurpleCandleCakeBehavior.class)
                .vanillaBlock(BlockId.PURPLE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initPurpleCarpet() {
        if (BlockTypes.PURPLE_CARPET != null) return;
        BlockTypes.PURPLE_CARPET = BlockTypeBuilder
                .builder(BlockPurpleCarpetBehavior.class)
                .vanillaBlock(BlockId.PURPLE_CARPET)
                .build();
    }

    public static void initPurpleConcrete() {
        if (BlockTypes.PURPLE_CONCRETE != null) return;
        BlockTypes.PURPLE_CONCRETE = BlockTypeBuilder
                .builder(BlockPurpleConcreteBehavior.class)
                .vanillaBlock(BlockId.PURPLE_CONCRETE)
                .build();
    }

    public static void initPurpleConcretePowder() {
        if (BlockTypes.PURPLE_CONCRETE_POWDER != null) return;
        BlockTypes.PURPLE_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockPurpleConcretePowderBehavior.class)
                .vanillaBlock(BlockId.PURPLE_CONCRETE_POWDER)
                .build();
    }

    public static void initPurpleGlazedTerracotta() {
        if (BlockTypes.PURPLE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.PURPLE_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockPurpleGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.PURPLE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initPurpleShulkerBox() {
        if (BlockTypes.PURPLE_SHULKER_BOX != null) return;
        BlockTypes.PURPLE_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockPurpleShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.PURPLE_SHULKER_BOX)
                .build();
    }

    public static void initPurpleStainedGlass() {
        if (BlockTypes.PURPLE_STAINED_GLASS != null) return;
        BlockTypes.PURPLE_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockPurpleStainedGlassBehavior.class)
                .vanillaBlock(BlockId.PURPLE_STAINED_GLASS)
                .build();
    }

    public static void initPurpleStainedGlassPane() {
        if (BlockTypes.PURPLE_STAINED_GLASS_PANE != null) return;
        BlockTypes.PURPLE_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockPurpleStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.PURPLE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initPurpleTerracotta() {
        if (BlockTypes.PURPLE_TERRACOTTA != null) return;
        BlockTypes.PURPLE_TERRACOTTA = BlockTypeBuilder
                .builder(BlockPurpleTerracottaBehavior.class)
                .vanillaBlock(BlockId.PURPLE_TERRACOTTA)
                .build();
    }

    public static void initPurpleWool() {
        if (BlockTypes.PURPLE_WOOL != null) return;
        BlockTypes.PURPLE_WOOL = BlockTypeBuilder
                .builder(BlockPurpleWoolBehavior.class)
                .vanillaBlock(BlockId.PURPLE_WOOL)
                .build();
    }

    public static void initPurpurBlock() {
        if (BlockTypes.PURPUR_BLOCK != null) return;
        BlockTypes.PURPUR_BLOCK = BlockTypeBuilder
                .builder(BlockPurpurBlockBehavior.class)
                .vanillaBlock(BlockId.PURPUR_BLOCK)
                .setProperties(BlockPropertyTypes.CHISEL_TYPE, BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initPurpurDoubleSlab() {
        if (BlockTypes.PURPUR_DOUBLE_SLAB != null) return;
        BlockTypes.PURPUR_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockPurpurDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.PURPUR_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPurpurSlab() {
        if (BlockTypes.PURPUR_SLAB != null) return;
        BlockTypes.PURPUR_SLAB = BlockTypeBuilder
                .builder(BlockPurpurSlabBehavior.class)
                .vanillaBlock(BlockId.PURPUR_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initPurpurStairs() {
        if (BlockTypes.PURPUR_STAIRS != null) return;
        BlockTypes.PURPUR_STAIRS = BlockTypeBuilder
                .builder(BlockPurpurStairsBehavior.class)
                .vanillaBlock(BlockId.PURPUR_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initQuartzBlock() {
        if (BlockTypes.QUARTZ_BLOCK != null) return;
        BlockTypes.QUARTZ_BLOCK = BlockTypeBuilder
                .builder(BlockQuartzBlockBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initQuartzBricks() {
        if (BlockTypes.QUARTZ_BRICKS != null) return;
        BlockTypes.QUARTZ_BRICKS = BlockTypeBuilder
                .builder(BlockQuartzBricksBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_BRICKS)
                .build();
    }

    public static void initQuartzDoubleSlab() {
        if (BlockTypes.QUARTZ_DOUBLE_SLAB != null) return;
        BlockTypes.QUARTZ_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockQuartzDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initQuartzOre() {
        if (BlockTypes.QUARTZ_ORE != null) return;
        BlockTypes.QUARTZ_ORE = BlockTypeBuilder
                .builder(BlockQuartzOreBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_ORE)
                .build();
    }

    public static void initQuartzPillar() {
        if (BlockTypes.QUARTZ_PILLAR != null) return;
        BlockTypes.QUARTZ_PILLAR = BlockTypeBuilder
                .builder(BlockQuartzPillarBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_PILLAR)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initQuartzSlab() {
        if (BlockTypes.QUARTZ_SLAB != null) return;
        BlockTypes.QUARTZ_SLAB = BlockTypeBuilder
                .builder(BlockQuartzSlabBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initQuartzStairs() {
        if (BlockTypes.QUARTZ_STAIRS != null) return;
        BlockTypes.QUARTZ_STAIRS = BlockTypeBuilder
                .builder(BlockQuartzStairsBehavior.class)
                .vanillaBlock(BlockId.QUARTZ_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initRail() {
        if (BlockTypes.RAIL != null) return;
        BlockTypes.RAIL = BlockTypeBuilder
                .builder(BlockRailBehavior.class)
                .vanillaBlock(BlockId.RAIL)
                .setProperties(BlockPropertyTypes.RAIL_DIRECTION_10)
                .build();
    }

    public static void initRawCopperBlock() {
        if (BlockTypes.RAW_COPPER_BLOCK != null) return;
        BlockTypes.RAW_COPPER_BLOCK = BlockTypeBuilder
                .builder(BlockRawCopperBlockBehavior.class)
                .vanillaBlock(BlockId.RAW_COPPER_BLOCK)
                .build();
    }

    public static void initRawGoldBlock() {
        if (BlockTypes.RAW_GOLD_BLOCK != null) return;
        BlockTypes.RAW_GOLD_BLOCK = BlockTypeBuilder
                .builder(BlockRawGoldBlockBehavior.class)
                .vanillaBlock(BlockId.RAW_GOLD_BLOCK)
                .build();
    }

    public static void initRawIronBlock() {
        if (BlockTypes.RAW_IRON_BLOCK != null) return;
        BlockTypes.RAW_IRON_BLOCK = BlockTypeBuilder
                .builder(BlockRawIronBlockBehavior.class)
                .vanillaBlock(BlockId.RAW_IRON_BLOCK)
                .build();
    }

    public static void initRedCandle() {
        if (BlockTypes.RED_CANDLE != null) return;
        BlockTypes.RED_CANDLE = BlockTypeBuilder
                .builder(BlockRedCandleBehavior.class)
                .vanillaBlock(BlockId.RED_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initRedCandleCake() {
        if (BlockTypes.RED_CANDLE_CAKE != null) return;
        BlockTypes.RED_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockRedCandleCakeBehavior.class)
                .vanillaBlock(BlockId.RED_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initRedCarpet() {
        if (BlockTypes.RED_CARPET != null) return;
        BlockTypes.RED_CARPET = BlockTypeBuilder
                .builder(BlockRedCarpetBehavior.class)
                .vanillaBlock(BlockId.RED_CARPET)
                .build();
    }

    public static void initRedConcrete() {
        if (BlockTypes.RED_CONCRETE != null) return;
        BlockTypes.RED_CONCRETE = BlockTypeBuilder
                .builder(BlockRedConcreteBehavior.class)
                .vanillaBlock(BlockId.RED_CONCRETE)
                .build();
    }

    public static void initRedConcretePowder() {
        if (BlockTypes.RED_CONCRETE_POWDER != null) return;
        BlockTypes.RED_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockRedConcretePowderBehavior.class)
                .vanillaBlock(BlockId.RED_CONCRETE_POWDER)
                .build();
    }

    public static void initRedGlazedTerracotta() {
        if (BlockTypes.RED_GLAZED_TERRACOTTA != null) return;
        BlockTypes.RED_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockRedGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.RED_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initRedMushroom() {
        if (BlockTypes.RED_MUSHROOM != null) return;
        BlockTypes.RED_MUSHROOM = BlockTypeBuilder
                .builder(BlockRedMushroomBehavior.class)
                .vanillaBlock(BlockId.RED_MUSHROOM)
                .build();
    }

    public static void initRedMushroomBlock() {
        if (BlockTypes.RED_MUSHROOM_BLOCK != null) return;
        BlockTypes.RED_MUSHROOM_BLOCK = BlockTypeBuilder
                .builder(BlockRedMushroomBlockBehavior.class)
                .vanillaBlock(BlockId.RED_MUSHROOM_BLOCK)
                .setProperties(BlockPropertyTypes.HUGE_MUSHROOM_BITS)
                .build();
    }

    public static void initRedNetherBrick() {
        if (BlockTypes.RED_NETHER_BRICK != null) return;
        BlockTypes.RED_NETHER_BRICK = BlockTypeBuilder
                .builder(BlockRedNetherBrickBehavior.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK)
                .build();
    }

    public static void initRedNetherBrickDoubleSlab() {
        if (BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.RED_NETHER_BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockRedNetherBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedNetherBrickSlab() {
        if (BlockTypes.RED_NETHER_BRICK_SLAB != null) return;
        BlockTypes.RED_NETHER_BRICK_SLAB = BlockTypeBuilder
                .builder(BlockRedNetherBrickSlabBehavior.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedNetherBrickStairs() {
        if (BlockTypes.RED_NETHER_BRICK_STAIRS != null) return;
        BlockTypes.RED_NETHER_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockRedNetherBrickStairsBehavior.class)
                .vanillaBlock(BlockId.RED_NETHER_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initRedSand() {
        if (BlockTypes.RED_SAND != null) return;
        BlockTypes.RED_SAND = BlockTypeBuilder
                .builder(BlockRedSandBehavior.class)
                .vanillaBlock(BlockId.RED_SAND)
                .build();
    }

    public static void initRedSandstone() {
        if (BlockTypes.RED_SANDSTONE != null) return;
        BlockTypes.RED_SANDSTONE = BlockTypeBuilder
                .builder(BlockRedSandstoneBehavior.class)
                .vanillaBlock(BlockId.RED_SANDSTONE)
                .build();
    }

    public static void initRedSandstoneDoubleSlab() {
        if (BlockTypes.RED_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.RED_SANDSTONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockRedSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedSandstoneSlab() {
        if (BlockTypes.RED_SANDSTONE_SLAB != null) return;
        BlockTypes.RED_SANDSTONE_SLAB = BlockTypeBuilder
                .builder(BlockRedSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initRedSandstoneStairs() {
        if (BlockTypes.RED_SANDSTONE_STAIRS != null) return;
        BlockTypes.RED_SANDSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockRedSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.RED_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initRedShulkerBox() {
        if (BlockTypes.RED_SHULKER_BOX != null) return;
        BlockTypes.RED_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockRedShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.RED_SHULKER_BOX)
                .build();
    }

    public static void initRedStainedGlass() {
        if (BlockTypes.RED_STAINED_GLASS != null) return;
        BlockTypes.RED_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockRedStainedGlassBehavior.class)
                .vanillaBlock(BlockId.RED_STAINED_GLASS)
                .build();
    }

    public static void initRedStainedGlassPane() {
        if (BlockTypes.RED_STAINED_GLASS_PANE != null) return;
        BlockTypes.RED_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockRedStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.RED_STAINED_GLASS_PANE)
                .build();
    }

    public static void initRedTerracotta() {
        if (BlockTypes.RED_TERRACOTTA != null) return;
        BlockTypes.RED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockRedTerracottaBehavior.class)
                .vanillaBlock(BlockId.RED_TERRACOTTA)
                .build();
    }

    public static void initRedTulip() {
        if (BlockTypes.RED_TULIP != null) return;
        BlockTypes.RED_TULIP = BlockTypeBuilder
                .builder(BlockRedTulipBehavior.class)
                .vanillaBlock(BlockId.RED_TULIP)
                .build();
    }

    public static void initRedWool() {
        if (BlockTypes.RED_WOOL != null) return;
        BlockTypes.RED_WOOL = BlockTypeBuilder
                .builder(BlockRedWoolBehavior.class)
                .vanillaBlock(BlockId.RED_WOOL)
                .build();
    }

    public static void initRedstoneBlock() {
        if (BlockTypes.REDSTONE_BLOCK != null) return;
        BlockTypes.REDSTONE_BLOCK = BlockTypeBuilder
                .builder(BlockRedstoneBlockBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_BLOCK)
                .build();
    }

    public static void initRedstoneLamp() {
        if (BlockTypes.REDSTONE_LAMP != null) return;
        BlockTypes.REDSTONE_LAMP = BlockTypeBuilder
                .builder(BlockRedstoneLampBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_LAMP)
                .build();
    }

    public static void initRedstoneOre() {
        if (BlockTypes.REDSTONE_ORE != null) return;
        BlockTypes.REDSTONE_ORE = BlockTypeBuilder
                .builder(BlockRedstoneOreBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_ORE)
                .build();
    }

    public static void initRedstoneTorch() {
        if (BlockTypes.REDSTONE_TORCH != null) return;
        BlockTypes.REDSTONE_TORCH = BlockTypeBuilder
                .builder(BlockRedstoneTorchBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initRedstoneWire() {
        if (BlockTypes.REDSTONE_WIRE != null) return;
        BlockTypes.REDSTONE_WIRE = BlockTypeBuilder
                .builder(BlockRedstoneWireBehavior.class)
                .vanillaBlock(BlockId.REDSTONE_WIRE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initReeds() {
        if (BlockTypes.REEDS != null) return;
        BlockTypes.REEDS = BlockTypeBuilder
                .builder(BlockReedsBehavior.class)
                .vanillaBlock(BlockId.REEDS)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initReinforcedDeepslate() {
        if (BlockTypes.REINFORCED_DEEPSLATE != null) return;
        BlockTypes.REINFORCED_DEEPSLATE = BlockTypeBuilder
                .builder(BlockReinforcedDeepslateBehavior.class)
                .vanillaBlock(BlockId.REINFORCED_DEEPSLATE)
                .build();
    }

    public static void initRepeatingCommandBlock() {
        if (BlockTypes.REPEATING_COMMAND_BLOCK != null) return;
        BlockTypes.REPEATING_COMMAND_BLOCK = BlockTypeBuilder
                .builder(BlockRepeatingCommandBlockBehavior.class)
                .vanillaBlock(BlockId.REPEATING_COMMAND_BLOCK)
                .setProperties(BlockPropertyTypes.CONDITIONAL_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initReserved6() {
        if (BlockTypes.RESERVED6 != null) return;
        BlockTypes.RESERVED6 = BlockTypeBuilder
                .builder(BlockReserved6Behavior.class)
                .vanillaBlock(BlockId.RESERVED6)
                .build();
    }

    public static void initRespawnAnchor() {
        if (BlockTypes.RESPAWN_ANCHOR != null) return;
        BlockTypes.RESPAWN_ANCHOR = BlockTypeBuilder
                .builder(BlockRespawnAnchorBehavior.class)
                .vanillaBlock(BlockId.RESPAWN_ANCHOR)
                .setProperties(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE)
                .build();
    }

    public static void initRoseBush() {
        if (BlockTypes.ROSE_BUSH != null) return;
        BlockTypes.ROSE_BUSH = BlockTypeBuilder
                .builder(BlockRoseBushBehavior.class)
                .vanillaBlock(BlockId.ROSE_BUSH)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSand() {
        if (BlockTypes.SAND != null) return;
        BlockTypes.SAND = BlockTypeBuilder
                .builder(BlockSandBehavior.class)
                .vanillaBlock(BlockId.SAND)
                .build();
    }

    public static void initSandstone() {
        if (BlockTypes.SANDSTONE != null) return;
        BlockTypes.SANDSTONE = BlockTypeBuilder
                .builder(BlockSandstoneBehavior.class)
                .vanillaBlock(BlockId.SANDSTONE)
                .build();
    }

    public static void initSandstoneDoubleSlab() {
        if (BlockTypes.SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.SANDSTONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSandstoneSlab() {
        if (BlockTypes.SANDSTONE_SLAB != null) return;
        BlockTypes.SANDSTONE_SLAB = BlockTypeBuilder
                .builder(BlockSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSandstoneStairs() {
        if (BlockTypes.SANDSTONE_STAIRS != null) return;
        BlockTypes.SANDSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initScaffolding() {
        if (BlockTypes.SCAFFOLDING != null) return;
        BlockTypes.SCAFFOLDING = BlockTypeBuilder
                .builder(BlockScaffoldingBehavior.class)
                .vanillaBlock(BlockId.SCAFFOLDING)
                .setProperties(BlockPropertyTypes.STABILITY, BlockPropertyTypes.STABILITY_CHECK)
                .build();
    }

    public static void initSculk() {
        if (BlockTypes.SCULK != null) return;
        BlockTypes.SCULK = BlockTypeBuilder
                .builder(BlockSculkBehavior.class)
                .vanillaBlock(BlockId.SCULK)
                .build();
    }

    public static void initSculkCatalyst() {
        if (BlockTypes.SCULK_CATALYST != null) return;
        BlockTypes.SCULK_CATALYST = BlockTypeBuilder
                .builder(BlockSculkCatalystBehavior.class)
                .vanillaBlock(BlockId.SCULK_CATALYST)
                .setProperties(BlockPropertyTypes.BLOOM)
                .build();
    }

    public static void initSculkSensor() {
        if (BlockTypes.SCULK_SENSOR != null) return;
        BlockTypes.SCULK_SENSOR = BlockTypeBuilder
                .builder(BlockSculkSensorBehavior.class)
                .vanillaBlock(BlockId.SCULK_SENSOR)
                .setProperties(BlockPropertyTypes.SCULK_SENSOR_PHASE)
                .build();
    }

    public static void initSculkShrieker() {
        if (BlockTypes.SCULK_SHRIEKER != null) return;
        BlockTypes.SCULK_SHRIEKER = BlockTypeBuilder
                .builder(BlockSculkShriekerBehavior.class)
                .vanillaBlock(BlockId.SCULK_SHRIEKER)
                .setProperties(BlockPropertyTypes.ACTIVE, BlockPropertyTypes.CAN_SUMMON)
                .build();
    }

    public static void initSculkVein() {
        if (BlockTypes.SCULK_VEIN != null) return;
        BlockTypes.SCULK_VEIN = BlockTypeBuilder
                .builder(BlockSculkVeinBehavior.class)
                .vanillaBlock(BlockId.SCULK_VEIN)
                .setProperties(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS)
                .build();
    }

    public static void initSeaLantern() {
        if (BlockTypes.SEA_LANTERN != null) return;
        BlockTypes.SEA_LANTERN = BlockTypeBuilder
                .builder(BlockSeaLanternBehavior.class)
                .vanillaBlock(BlockId.SEA_LANTERN)
                .build();
    }

    public static void initSeaPickle() {
        if (BlockTypes.SEA_PICKLE != null) return;
        BlockTypes.SEA_PICKLE = BlockTypeBuilder
                .builder(BlockSeaPickleBehavior.class)
                .vanillaBlock(BlockId.SEA_PICKLE)
                .setProperties(BlockPropertyTypes.CLUSTER_COUNT, BlockPropertyTypes.DEAD_BIT)
                .build();
    }

    public static void initSeagrass() {
        if (BlockTypes.SEAGRASS != null) return;
        BlockTypes.SEAGRASS = BlockTypeBuilder
                .builder(BlockSeagrassBehavior.class)
                .vanillaBlock(BlockId.SEAGRASS)
                .setProperties(BlockPropertyTypes.SEA_GRASS_TYPE)
                .build();
    }

    public static void initShortGrass() {
        if (BlockTypes.SHORT_GRASS != null) return;
        BlockTypes.SHORT_GRASS = BlockTypeBuilder
                .builder(BlockShortGrassBehavior.class)
                .vanillaBlock(BlockId.SHORT_GRASS)
                .build();
    }

    public static void initShroomlight() {
        if (BlockTypes.SHROOMLIGHT != null) return;
        BlockTypes.SHROOMLIGHT = BlockTypeBuilder
                .builder(BlockShroomlightBehavior.class)
                .vanillaBlock(BlockId.SHROOMLIGHT)
                .build();
    }

    public static void initSilverGlazedTerracotta() {
        if (BlockTypes.SILVER_GLAZED_TERRACOTTA != null) return;
        BlockTypes.SILVER_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockSilverGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.SILVER_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSkull() {
        if (BlockTypes.SKULL != null) return;
        BlockTypes.SKULL = BlockTypeBuilder
                .builder(BlockSkullBehavior.class)
                .vanillaBlock(BlockId.SKULL)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSlime() {
        if (BlockTypes.SLIME != null) return;
        BlockTypes.SLIME = BlockTypeBuilder
                .builder(BlockSlimeBehavior.class)
                .vanillaBlock(BlockId.SLIME)
                .build();
    }

    public static void initSmallAmethystBud() {
        if (BlockTypes.SMALL_AMETHYST_BUD != null) return;
        BlockTypes.SMALL_AMETHYST_BUD = BlockTypeBuilder
                .builder(BlockSmallAmethystBudBehavior.class)
                .vanillaBlock(BlockId.SMALL_AMETHYST_BUD)
                .setProperties(BlockPropertyTypes.MINECRAFT_BLOCK_FACE)
                .build();
    }

    public static void initSmallDripleafBlock() {
        if (BlockTypes.SMALL_DRIPLEAF_BLOCK != null) return;
        BlockTypes.SMALL_DRIPLEAF_BLOCK = BlockTypeBuilder
                .builder(BlockSmallDripleafBlockBehavior.class)
                .vanillaBlock(BlockId.SMALL_DRIPLEAF_BLOCK)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSmithingTable() {
        if (BlockTypes.SMITHING_TABLE != null) return;
        BlockTypes.SMITHING_TABLE = BlockTypeBuilder
                .builder(BlockSmithingTableBehavior.class)
                .vanillaBlock(BlockId.SMITHING_TABLE)
                .build();
    }

    public static void initSmoker() {
        if (BlockTypes.SMOKER != null) return;
        BlockTypes.SMOKER = BlockTypeBuilder
                .builder(BlockSmokerBehavior.class)
                .vanillaBlock(BlockId.SMOKER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initSmoothBasalt() {
        if (BlockTypes.SMOOTH_BASALT != null) return;
        BlockTypes.SMOOTH_BASALT = BlockTypeBuilder
                .builder(BlockSmoothBasaltBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_BASALT)
                .build();
    }

    public static void initSmoothQuartz() {
        if (BlockTypes.SMOOTH_QUARTZ != null) return;
        BlockTypes.SMOOTH_QUARTZ = BlockTypeBuilder
                .builder(BlockSmoothQuartzBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initSmoothQuartzDoubleSlab() {
        if (BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_QUARTZ_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockSmoothQuartzDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothQuartzSlab() {
        if (BlockTypes.SMOOTH_QUARTZ_SLAB != null) return;
        BlockTypes.SMOOTH_QUARTZ_SLAB = BlockTypeBuilder
                .builder(BlockSmoothQuartzSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothQuartzStairs() {
        if (BlockTypes.SMOOTH_QUARTZ_STAIRS != null) return;
        BlockTypes.SMOOTH_QUARTZ_STAIRS = BlockTypeBuilder
                .builder(BlockSmoothQuartzStairsBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_QUARTZ_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSmoothRedSandstone() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE = BlockTypeBuilder
                .builder(BlockSmoothRedSandstoneBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE)
                .build();
    }

    public static void initSmoothRedSandstoneDoubleSlab() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockSmoothRedSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothRedSandstoneSlab() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE_SLAB != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE_SLAB = BlockTypeBuilder
                .builder(BlockSmoothRedSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothRedSandstoneStairs() {
        if (BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS != null) return;
        BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockSmoothRedSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_RED_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSmoothSandstone() {
        if (BlockTypes.SMOOTH_SANDSTONE != null) return;
        BlockTypes.SMOOTH_SANDSTONE = BlockTypeBuilder
                .builder(BlockSmoothSandstoneBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE)
                .build();
    }

    public static void initSmoothSandstoneDoubleSlab() {
        if (BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_SANDSTONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockSmoothSandstoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothSandstoneSlab() {
        if (BlockTypes.SMOOTH_SANDSTONE_SLAB != null) return;
        BlockTypes.SMOOTH_SANDSTONE_SLAB = BlockTypeBuilder
                .builder(BlockSmoothSandstoneSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothSandstoneStairs() {
        if (BlockTypes.SMOOTH_SANDSTONE_STAIRS != null) return;
        BlockTypes.SMOOTH_SANDSTONE_STAIRS = BlockTypeBuilder
                .builder(BlockSmoothSandstoneStairsBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_SANDSTONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSmoothStone() {
        if (BlockTypes.SMOOTH_STONE != null) return;
        BlockTypes.SMOOTH_STONE = BlockTypeBuilder
                .builder(BlockSmoothStoneBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_STONE)
                .build();
    }

    public static void initSmoothStoneDoubleSlab() {
        if (BlockTypes.SMOOTH_STONE_DOUBLE_SLAB != null) return;
        BlockTypes.SMOOTH_STONE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockSmoothStoneDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_STONE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSmoothStoneSlab() {
        if (BlockTypes.SMOOTH_STONE_SLAB != null) return;
        BlockTypes.SMOOTH_STONE_SLAB = BlockTypeBuilder
                .builder(BlockSmoothStoneSlabBehavior.class)
                .vanillaBlock(BlockId.SMOOTH_STONE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSnifferEgg() {
        if (BlockTypes.SNIFFER_EGG != null) return;
        BlockTypes.SNIFFER_EGG = BlockTypeBuilder
                .builder(BlockSnifferEggBehavior.class)
                .vanillaBlock(BlockId.SNIFFER_EGG)
                .setProperties(BlockPropertyTypes.CRACKED_STATE)
                .build();
    }

    public static void initSnow() {
        if (BlockTypes.SNOW != null) return;
        BlockTypes.SNOW = BlockTypeBuilder
                .builder(BlockSnowBehavior.class)
                .vanillaBlock(BlockId.SNOW)
                .build();
    }

    public static void initSnowLayer() {
        if (BlockTypes.SNOW_LAYER != null) return;
        BlockTypes.SNOW_LAYER = BlockTypeBuilder
                .builder(BlockSnowLayerBehavior.class)
                .vanillaBlock(BlockId.SNOW_LAYER)
                .setProperties(BlockPropertyTypes.COVERED_BIT, BlockPropertyTypes.HEIGHT)
                .build();
    }

    public static void initSoulCampfire() {
        if (BlockTypes.SOUL_CAMPFIRE != null) return;
        BlockTypes.SOUL_CAMPFIRE = BlockTypeBuilder
                .builder(BlockSoulCampfireBehavior.class)
                .vanillaBlock(BlockId.SOUL_CAMPFIRE)
                .setProperties(BlockPropertyTypes.EXTINGUISHED, BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initSoulFire() {
        if (BlockTypes.SOUL_FIRE != null) return;
        BlockTypes.SOUL_FIRE = BlockTypeBuilder
                .builder(BlockSoulFireBehavior.class)
                .vanillaBlock(BlockId.SOUL_FIRE)
                .setProperties(BlockPropertyTypes.AGE_16)
                .build();
    }

    public static void initSoulLantern() {
        if (BlockTypes.SOUL_LANTERN != null) return;
        BlockTypes.SOUL_LANTERN = BlockTypeBuilder
                .builder(BlockSoulLanternBehavior.class)
                .vanillaBlock(BlockId.SOUL_LANTERN)
                .setProperties(BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSoulSand() {
        if (BlockTypes.SOUL_SAND != null) return;
        BlockTypes.SOUL_SAND = BlockTypeBuilder
                .builder(BlockSoulSandBehavior.class)
                .vanillaBlock(BlockId.SOUL_SAND)
                .build();
    }

    public static void initSoulSoil() {
        if (BlockTypes.SOUL_SOIL != null) return;
        BlockTypes.SOUL_SOIL = BlockTypeBuilder
                .builder(BlockSoulSoilBehavior.class)
                .vanillaBlock(BlockId.SOUL_SOIL)
                .build();
    }

    public static void initSoulTorch() {
        if (BlockTypes.SOUL_TORCH != null) return;
        BlockTypes.SOUL_TORCH = BlockTypeBuilder
                .builder(BlockSoulTorchBehavior.class)
                .vanillaBlock(BlockId.SOUL_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initSponge() {
        if (BlockTypes.SPONGE != null) return;
        BlockTypes.SPONGE = BlockTypeBuilder
                .builder(BlockSpongeBehavior.class)
                .vanillaBlock(BlockId.SPONGE)
                .setProperties(BlockPropertyTypes.SPONGE_TYPE)
                .build();
    }

    public static void initSporeBlossom() {
        if (BlockTypes.SPORE_BLOSSOM != null) return;
        BlockTypes.SPORE_BLOSSOM = BlockTypeBuilder
                .builder(BlockSporeBlossomBehavior.class)
                .vanillaBlock(BlockId.SPORE_BLOSSOM)
                .build();
    }

    public static void initSpruceButton() {
        if (BlockTypes.SPRUCE_BUTTON != null) return;
        BlockTypes.SPRUCE_BUTTON = BlockTypeBuilder
                .builder(BlockSpruceButtonBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSpruceDoor() {
        if (BlockTypes.SPRUCE_DOOR != null) return;
        BlockTypes.SPRUCE_DOOR = BlockTypeBuilder
                .builder(BlockSpruceDoorBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSpruceDoubleSlab() {
        if (BlockTypes.SPRUCE_DOUBLE_SLAB != null) return;
        BlockTypes.SPRUCE_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockSpruceDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSpruceFence() {
        if (BlockTypes.SPRUCE_FENCE != null) return;
        BlockTypes.SPRUCE_FENCE = BlockTypeBuilder
                .builder(BlockSpruceFenceBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_FENCE)
                .build();
    }

    public static void initSpruceFenceGate() {
        if (BlockTypes.SPRUCE_FENCE_GATE != null) return;
        BlockTypes.SPRUCE_FENCE_GATE = BlockTypeBuilder
                .builder(BlockSpruceFenceGateBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initSpruceHangingSign() {
        if (BlockTypes.SPRUCE_HANGING_SIGN != null) return;
        BlockTypes.SPRUCE_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockSpruceHangingSignBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSpruceLeaves() {
        if (BlockTypes.SPRUCE_LEAVES != null) return;
        BlockTypes.SPRUCE_LEAVES = BlockTypeBuilder
                .builder(BlockSpruceLeavesBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_LEAVES)
                .setProperties(BlockPropertyTypes.PERSISTENT_BIT, BlockPropertyTypes.UPDATE_BIT)
                .build();
    }

    public static void initSpruceLog() {
        if (BlockTypes.SPRUCE_LOG != null) return;
        BlockTypes.SPRUCE_LOG = BlockTypeBuilder
                .builder(BlockSpruceLogBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initSprucePlanks() {
        if (BlockTypes.SPRUCE_PLANKS != null) return;
        BlockTypes.SPRUCE_PLANKS = BlockTypeBuilder
                .builder(BlockSprucePlanksBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_PLANKS)
                .build();
    }

    public static void initSprucePressurePlate() {
        if (BlockTypes.SPRUCE_PRESSURE_PLATE != null) return;
        BlockTypes.SPRUCE_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockSprucePressurePlateBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initSpruceSapling() {
        if (BlockTypes.SPRUCE_SAPLING != null) return;
        BlockTypes.SPRUCE_SAPLING = BlockTypeBuilder
                .builder(BlockSpruceSaplingBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_SAPLING)
                .setProperties(BlockPropertyTypes.AGE_BIT)
                .build();
    }

    public static void initSpruceSlab() {
        if (BlockTypes.SPRUCE_SLAB != null) return;
        BlockTypes.SPRUCE_SLAB = BlockTypeBuilder
                .builder(BlockSpruceSlabBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initSpruceStairs() {
        if (BlockTypes.SPRUCE_STAIRS != null) return;
        BlockTypes.SPRUCE_STAIRS = BlockTypeBuilder
                .builder(BlockSpruceStairsBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initSpruceStandingSign() {
        if (BlockTypes.SPRUCE_STANDING_SIGN != null) return;
        BlockTypes.SPRUCE_STANDING_SIGN = BlockTypeBuilder
                .builder(BlockSpruceStandingSignBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initSpruceTrapdoor() {
        if (BlockTypes.SPRUCE_TRAPDOOR != null) return;
        BlockTypes.SPRUCE_TRAPDOOR = BlockTypeBuilder
                .builder(BlockSpruceTrapdoorBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initSpruceWallSign() {
        if (BlockTypes.SPRUCE_WALL_SIGN != null) return;
        BlockTypes.SPRUCE_WALL_SIGN = BlockTypeBuilder
                .builder(BlockSpruceWallSignBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initSpruceWood() {
        if (BlockTypes.SPRUCE_WOOD != null) return;
        BlockTypes.SPRUCE_WOOD = BlockTypeBuilder
                .builder(BlockSpruceWoodBehavior.class)
                .vanillaBlock(BlockId.SPRUCE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStandingBanner() {
        if (BlockTypes.STANDING_BANNER != null) return;
        BlockTypes.STANDING_BANNER = BlockTypeBuilder
                .builder(BlockStandingBannerBehavior.class)
                .vanillaBlock(BlockId.STANDING_BANNER)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initStandingSign() {
        if (BlockTypes.STANDING_SIGN != null) return;
        BlockTypes.STANDING_SIGN = BlockTypeBuilder
                .builder(BlockStandingSignBehavior.class)
                .vanillaBlock(BlockId.STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initStickyPiston() {
        if (BlockTypes.STICKY_PISTON != null) return;
        BlockTypes.STICKY_PISTON = BlockTypeBuilder
                .builder(BlockStickyPistonBehavior.class)
                .vanillaBlock(BlockId.STICKY_PISTON)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initStickyPistonArmCollision() {
        if (BlockTypes.STICKY_PISTON_ARM_COLLISION != null) return;
        BlockTypes.STICKY_PISTON_ARM_COLLISION = BlockTypeBuilder
                .builder(BlockStickyPistonArmCollisionBehavior.class)
                .vanillaBlock(BlockId.STICKY_PISTON_ARM_COLLISION)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initStone() {
        if (BlockTypes.STONE != null) return;
        BlockTypes.STONE = BlockTypeBuilder
                .builder(BlockStoneBehavior.class)
                .vanillaBlock(BlockId.STONE)
                .build();
    }

    public static void initStoneBrickDoubleSlab() {
        if (BlockTypes.STONE_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.STONE_BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockStoneBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initStoneBrickSlab() {
        if (BlockTypes.STONE_BRICK_SLAB != null) return;
        BlockTypes.STONE_BRICK_SLAB = BlockTypeBuilder
                .builder(BlockStoneBrickSlabBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initStoneBrickStairs() {
        if (BlockTypes.STONE_BRICK_STAIRS != null) return;
        BlockTypes.STONE_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockStoneBrickStairsBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initStoneBricks() {
        if (BlockTypes.STONE_BRICKS != null) return;
        BlockTypes.STONE_BRICKS = BlockTypeBuilder
                .builder(BlockStoneBricksBehavior.class)
                .vanillaBlock(BlockId.STONE_BRICKS)
                .build();
    }

    public static void initStoneButton() {
        if (BlockTypes.STONE_BUTTON != null) return;
        BlockTypes.STONE_BUTTON = BlockTypeBuilder
                .builder(BlockStoneButtonBehavior.class)
                .vanillaBlock(BlockId.STONE_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initStonePressurePlate() {
        if (BlockTypes.STONE_PRESSURE_PLATE != null) return;
        BlockTypes.STONE_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockStonePressurePlateBehavior.class)
                .vanillaBlock(BlockId.STONE_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initStoneStairs() {
        if (BlockTypes.STONE_STAIRS != null) return;
        BlockTypes.STONE_STAIRS = BlockTypeBuilder
                .builder(BlockStoneStairsBehavior.class)
                .vanillaBlock(BlockId.STONE_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initStonecutter() {
        if (BlockTypes.STONECUTTER != null) return;
        BlockTypes.STONECUTTER = BlockTypeBuilder
                .builder(BlockStonecutterBehavior.class)
                .vanillaBlock(BlockId.STONECUTTER)
                .build();
    }

    public static void initStonecutterBlock() {
        if (BlockTypes.STONECUTTER_BLOCK != null) return;
        BlockTypes.STONECUTTER_BLOCK = BlockTypeBuilder
                .builder(BlockStonecutterBlockBehavior.class)
                .vanillaBlock(BlockId.STONECUTTER_BLOCK)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initStrippedAcaciaLog() {
        if (BlockTypes.STRIPPED_ACACIA_LOG != null) return;
        BlockTypes.STRIPPED_ACACIA_LOG = BlockTypeBuilder
                .builder(BlockStrippedAcaciaLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_ACACIA_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedAcaciaWood() {
        if (BlockTypes.STRIPPED_ACACIA_WOOD != null) return;
        BlockTypes.STRIPPED_ACACIA_WOOD = BlockTypeBuilder
                .builder(BlockStrippedAcaciaWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_ACACIA_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedBambooBlock() {
        if (BlockTypes.STRIPPED_BAMBOO_BLOCK != null) return;
        BlockTypes.STRIPPED_BAMBOO_BLOCK = BlockTypeBuilder
                .builder(BlockStrippedBambooBlockBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_BAMBOO_BLOCK)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedBirchLog() {
        if (BlockTypes.STRIPPED_BIRCH_LOG != null) return;
        BlockTypes.STRIPPED_BIRCH_LOG = BlockTypeBuilder
                .builder(BlockStrippedBirchLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_BIRCH_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedBirchWood() {
        if (BlockTypes.STRIPPED_BIRCH_WOOD != null) return;
        BlockTypes.STRIPPED_BIRCH_WOOD = BlockTypeBuilder
                .builder(BlockStrippedBirchWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_BIRCH_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCherryLog() {
        if (BlockTypes.STRIPPED_CHERRY_LOG != null) return;
        BlockTypes.STRIPPED_CHERRY_LOG = BlockTypeBuilder
                .builder(BlockStrippedCherryLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_CHERRY_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCherryWood() {
        if (BlockTypes.STRIPPED_CHERRY_WOOD != null) return;
        BlockTypes.STRIPPED_CHERRY_WOOD = BlockTypeBuilder
                .builder(BlockStrippedCherryWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_CHERRY_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCrimsonHyphae() {
        if (BlockTypes.STRIPPED_CRIMSON_HYPHAE != null) return;
        BlockTypes.STRIPPED_CRIMSON_HYPHAE = BlockTypeBuilder
                .builder(BlockStrippedCrimsonHyphaeBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_CRIMSON_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedCrimsonStem() {
        if (BlockTypes.STRIPPED_CRIMSON_STEM != null) return;
        BlockTypes.STRIPPED_CRIMSON_STEM = BlockTypeBuilder
                .builder(BlockStrippedCrimsonStemBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_CRIMSON_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedDarkOakLog() {
        if (BlockTypes.STRIPPED_DARK_OAK_LOG != null) return;
        BlockTypes.STRIPPED_DARK_OAK_LOG = BlockTypeBuilder
                .builder(BlockStrippedDarkOakLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_DARK_OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedDarkOakWood() {
        if (BlockTypes.STRIPPED_DARK_OAK_WOOD != null) return;
        BlockTypes.STRIPPED_DARK_OAK_WOOD = BlockTypeBuilder
                .builder(BlockStrippedDarkOakWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_DARK_OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedJungleLog() {
        if (BlockTypes.STRIPPED_JUNGLE_LOG != null) return;
        BlockTypes.STRIPPED_JUNGLE_LOG = BlockTypeBuilder
                .builder(BlockStrippedJungleLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_JUNGLE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedJungleWood() {
        if (BlockTypes.STRIPPED_JUNGLE_WOOD != null) return;
        BlockTypes.STRIPPED_JUNGLE_WOOD = BlockTypeBuilder
                .builder(BlockStrippedJungleWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_JUNGLE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedMangroveLog() {
        if (BlockTypes.STRIPPED_MANGROVE_LOG != null) return;
        BlockTypes.STRIPPED_MANGROVE_LOG = BlockTypeBuilder
                .builder(BlockStrippedMangroveLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_MANGROVE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedMangroveWood() {
        if (BlockTypes.STRIPPED_MANGROVE_WOOD != null) return;
        BlockTypes.STRIPPED_MANGROVE_WOOD = BlockTypeBuilder
                .builder(BlockStrippedMangroveWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_MANGROVE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedOakLog() {
        if (BlockTypes.STRIPPED_OAK_LOG != null) return;
        BlockTypes.STRIPPED_OAK_LOG = BlockTypeBuilder
                .builder(BlockStrippedOakLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_OAK_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedOakWood() {
        if (BlockTypes.STRIPPED_OAK_WOOD != null) return;
        BlockTypes.STRIPPED_OAK_WOOD = BlockTypeBuilder
                .builder(BlockStrippedOakWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_OAK_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedSpruceLog() {
        if (BlockTypes.STRIPPED_SPRUCE_LOG != null) return;
        BlockTypes.STRIPPED_SPRUCE_LOG = BlockTypeBuilder
                .builder(BlockStrippedSpruceLogBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_SPRUCE_LOG)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedSpruceWood() {
        if (BlockTypes.STRIPPED_SPRUCE_WOOD != null) return;
        BlockTypes.STRIPPED_SPRUCE_WOOD = BlockTypeBuilder
                .builder(BlockStrippedSpruceWoodBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_SPRUCE_WOOD)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedWarpedHyphae() {
        if (BlockTypes.STRIPPED_WARPED_HYPHAE != null) return;
        BlockTypes.STRIPPED_WARPED_HYPHAE = BlockTypeBuilder
                .builder(BlockStrippedWarpedHyphaeBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_WARPED_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStrippedWarpedStem() {
        if (BlockTypes.STRIPPED_WARPED_STEM != null) return;
        BlockTypes.STRIPPED_WARPED_STEM = BlockTypeBuilder
                .builder(BlockStrippedWarpedStemBehavior.class)
                .vanillaBlock(BlockId.STRIPPED_WARPED_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initStructureBlock() {
        if (BlockTypes.STRUCTURE_BLOCK != null) return;
        BlockTypes.STRUCTURE_BLOCK = BlockTypeBuilder
                .builder(BlockStructureBlockBehavior.class)
                .vanillaBlock(BlockId.STRUCTURE_BLOCK)
                .setProperties(BlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
                .build();
    }

    public static void initStructureVoid() {
        if (BlockTypes.STRUCTURE_VOID != null) return;
        BlockTypes.STRUCTURE_VOID = BlockTypeBuilder
                .builder(BlockStructureVoidBehavior.class)
                .vanillaBlock(BlockId.STRUCTURE_VOID)
                .setProperties(BlockPropertyTypes.STRUCTURE_VOID_TYPE)
                .build();
    }

    public static void initSunflower() {
        if (BlockTypes.SUNFLOWER != null) return;
        BlockTypes.SUNFLOWER = BlockTypeBuilder
                .builder(BlockSunflowerBehavior.class)
                .vanillaBlock(BlockId.SUNFLOWER)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initSuspiciousGravel() {
        if (BlockTypes.SUSPICIOUS_GRAVEL != null) return;
        BlockTypes.SUSPICIOUS_GRAVEL = BlockTypeBuilder
                .builder(BlockSuspiciousGravelBehavior.class)
                .vanillaBlock(BlockId.SUSPICIOUS_GRAVEL)
                .setProperties(BlockPropertyTypes.BRUSHED_PROGRESS, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSuspiciousSand() {
        if (BlockTypes.SUSPICIOUS_SAND != null) return;
        BlockTypes.SUSPICIOUS_SAND = BlockTypeBuilder
                .builder(BlockSuspiciousSandBehavior.class)
                .vanillaBlock(BlockId.SUSPICIOUS_SAND)
                .setProperties(BlockPropertyTypes.BRUSHED_PROGRESS, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initSweetBerryBush() {
        if (BlockTypes.SWEET_BERRY_BUSH != null) return;
        BlockTypes.SWEET_BERRY_BUSH = BlockTypeBuilder
                .builder(BlockSweetBerryBushBehavior.class)
                .vanillaBlock(BlockId.SWEET_BERRY_BUSH)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initTallGrass() {
        if (BlockTypes.TALL_GRASS != null) return;
        BlockTypes.TALL_GRASS = BlockTypeBuilder
                .builder(BlockTallGrassBehavior.class)
                .vanillaBlock(BlockId.TALL_GRASS)
                .setProperties(BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initTarget() {
        if (BlockTypes.TARGET != null) return;
        BlockTypes.TARGET = BlockTypeBuilder
                .builder(BlockTargetBehavior.class)
                .vanillaBlock(BlockId.TARGET)
                .build();
    }

    public static void initTintedGlass() {
        if (BlockTypes.TINTED_GLASS != null) return;
        BlockTypes.TINTED_GLASS = BlockTypeBuilder
                .builder(BlockTintedGlassBehavior.class)
                .vanillaBlock(BlockId.TINTED_GLASS)
                .build();
    }

    public static void initTnt() {
        if (BlockTypes.TNT != null) return;
        BlockTypes.TNT = BlockTypeBuilder
                .builder(BlockTntBehavior.class)
                .vanillaBlock(BlockId.TNT)
                .setProperties(BlockPropertyTypes.ALLOW_UNDERWATER_BIT, BlockPropertyTypes.EXPLODE_BIT)
                .build();
    }

    public static void initTorch() {
        if (BlockTypes.TORCH != null) return;
        BlockTypes.TORCH = BlockTypeBuilder
                .builder(BlockTorchBehavior.class)
                .vanillaBlock(BlockId.TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initTorchflower() {
        if (BlockTypes.TORCHFLOWER != null) return;
        BlockTypes.TORCHFLOWER = BlockTypeBuilder
                .builder(BlockTorchflowerBehavior.class)
                .vanillaBlock(BlockId.TORCHFLOWER)
                .build();
    }

    public static void initTorchflowerCrop() {
        if (BlockTypes.TORCHFLOWER_CROP != null) return;
        BlockTypes.TORCHFLOWER_CROP = BlockTypeBuilder
                .builder(BlockTorchflowerCropBehavior.class)
                .vanillaBlock(BlockId.TORCHFLOWER_CROP)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initTrapdoor() {
        if (BlockTypes.TRAPDOOR != null) return;
        BlockTypes.TRAPDOOR = BlockTypeBuilder
                .builder(BlockTrapdoorBehavior.class)
                .vanillaBlock(BlockId.TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initTrappedChest() {
        if (BlockTypes.TRAPPED_CHEST != null) return;
        BlockTypes.TRAPPED_CHEST = BlockTypeBuilder
                .builder(BlockTrappedChestBehavior.class)
                .vanillaBlock(BlockId.TRAPPED_CHEST)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
                .build();
    }

    public static void initTrialSpawner() {
        if (BlockTypes.TRIAL_SPAWNER != null) return;
        BlockTypes.TRIAL_SPAWNER = BlockTypeBuilder
                .builder(BlockTrialSpawnerBehavior.class)
                .vanillaBlock(BlockId.TRIAL_SPAWNER)
                .setProperties(BlockPropertyTypes.OMINOUS, BlockPropertyTypes.TRIAL_SPAWNER_STATE)
                .build();
    }

    public static void initTripWire() {
        if (BlockTypes.TRIP_WIRE != null) return;
        BlockTypes.TRIP_WIRE = BlockTypeBuilder
                .builder(BlockTripWireBehavior.class)
                .vanillaBlock(BlockId.TRIP_WIRE)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.DISARMED_BIT, BlockPropertyTypes.POWERED_BIT, BlockPropertyTypes.SUSPENDED_BIT)
                .build();
    }

    public static void initTripwireHook() {
        if (BlockTypes.TRIPWIRE_HOOK != null) return;
        BlockTypes.TRIPWIRE_HOOK = BlockTypeBuilder
                .builder(BlockTripwireHookBehavior.class)
                .vanillaBlock(BlockId.TRIPWIRE_HOOK)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.DIRECTION, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initTubeCoral() {
        if (BlockTypes.TUBE_CORAL != null) return;
        BlockTypes.TUBE_CORAL = BlockTypeBuilder
                .builder(BlockTubeCoralBehavior.class)
                .vanillaBlock(BlockId.TUBE_CORAL)
                .build();
    }

    public static void initTubeCoralBlock() {
        if (BlockTypes.TUBE_CORAL_BLOCK != null) return;
        BlockTypes.TUBE_CORAL_BLOCK = BlockTypeBuilder
                .builder(BlockTubeCoralBlockBehavior.class)
                .vanillaBlock(BlockId.TUBE_CORAL_BLOCK)
                .build();
    }

    public static void initTubeCoralFan() {
        if (BlockTypes.TUBE_CORAL_FAN != null) return;
        BlockTypes.TUBE_CORAL_FAN = BlockTypeBuilder
                .builder(BlockTubeCoralFanBehavior.class)
                .vanillaBlock(BlockId.TUBE_CORAL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_FAN_DIRECTION)
                .build();
    }

    public static void initTubeCoralWallFan() {
        if (BlockTypes.TUBE_CORAL_WALL_FAN != null) return;
        BlockTypes.TUBE_CORAL_WALL_FAN = BlockTypeBuilder
                .builder(BlockTubeCoralWallFanBehavior.class)
                .vanillaBlock(BlockId.TUBE_CORAL_WALL_FAN)
                .setProperties(BlockPropertyTypes.CORAL_DIRECTION)
                .build();
    }

    public static void initTuff() {
        if (BlockTypes.TUFF != null) return;
        BlockTypes.TUFF = BlockTypeBuilder
                .builder(BlockTuffBehavior.class)
                .vanillaBlock(BlockId.TUFF)
                .build();
    }

    public static void initTuffBrickDoubleSlab() {
        if (BlockTypes.TUFF_BRICK_DOUBLE_SLAB != null) return;
        BlockTypes.TUFF_BRICK_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockTuffBrickDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICK_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffBrickSlab() {
        if (BlockTypes.TUFF_BRICK_SLAB != null) return;
        BlockTypes.TUFF_BRICK_SLAB = BlockTypeBuilder
                .builder(BlockTuffBrickSlabBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICK_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffBrickStairs() {
        if (BlockTypes.TUFF_BRICK_STAIRS != null) return;
        BlockTypes.TUFF_BRICK_STAIRS = BlockTypeBuilder
                .builder(BlockTuffBrickStairsBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICK_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initTuffBrickWall() {
        if (BlockTypes.TUFF_BRICK_WALL != null) return;
        BlockTypes.TUFF_BRICK_WALL = BlockTypeBuilder
                .builder(BlockTuffBrickWallBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICK_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initTuffBricks() {
        if (BlockTypes.TUFF_BRICKS != null) return;
        BlockTypes.TUFF_BRICKS = BlockTypeBuilder
                .builder(BlockTuffBricksBehavior.class)
                .vanillaBlock(BlockId.TUFF_BRICKS)
                .build();
    }

    public static void initTuffDoubleSlab() {
        if (BlockTypes.TUFF_DOUBLE_SLAB != null) return;
        BlockTypes.TUFF_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockTuffDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.TUFF_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffSlab() {
        if (BlockTypes.TUFF_SLAB != null) return;
        BlockTypes.TUFF_SLAB = BlockTypeBuilder
                .builder(BlockTuffSlabBehavior.class)
                .vanillaBlock(BlockId.TUFF_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initTuffStairs() {
        if (BlockTypes.TUFF_STAIRS != null) return;
        BlockTypes.TUFF_STAIRS = BlockTypeBuilder
                .builder(BlockTuffStairsBehavior.class)
                .vanillaBlock(BlockId.TUFF_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initTuffWall() {
        if (BlockTypes.TUFF_WALL != null) return;
        BlockTypes.TUFF_WALL = BlockTypeBuilder
                .builder(BlockTuffWallBehavior.class)
                .vanillaBlock(BlockId.TUFF_WALL)
                .setProperties(BlockPropertyTypes.WALL_CONNECTION_TYPE_EAST, BlockPropertyTypes.WALL_CONNECTION_TYPE_NORTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_SOUTH, BlockPropertyTypes.WALL_CONNECTION_TYPE_WEST, BlockPropertyTypes.WALL_POST_BIT)
                .build();
    }

    public static void initTurtleEgg() {
        if (BlockTypes.TURTLE_EGG != null) return;
        BlockTypes.TURTLE_EGG = BlockTypeBuilder
                .builder(BlockTurtleEggBehavior.class)
                .vanillaBlock(BlockId.TURTLE_EGG)
                .setProperties(BlockPropertyTypes.CRACKED_STATE, BlockPropertyTypes.TURTLE_EGG_COUNT)
                .build();
    }

    public static void initTwistingVines() {
        if (BlockTypes.TWISTING_VINES != null) return;
        BlockTypes.TWISTING_VINES = BlockTypeBuilder
                .builder(BlockTwistingVinesBehavior.class)
                .vanillaBlock(BlockId.TWISTING_VINES)
                .setProperties(BlockPropertyTypes.TWISTING_VINES_AGE)
                .build();
    }

    public static void initUnderwaterTorch() {
        if (BlockTypes.UNDERWATER_TORCH != null) return;
        BlockTypes.UNDERWATER_TORCH = BlockTypeBuilder
                .builder(BlockUnderwaterTorchBehavior.class)
                .vanillaBlock(BlockId.UNDERWATER_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initUndyedShulkerBox() {
        if (BlockTypes.UNDYED_SHULKER_BOX != null) return;
        BlockTypes.UNDYED_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockUndyedShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.UNDYED_SHULKER_BOX)
                .build();
    }

    public static void initUnknown() {
        if (BlockTypes.UNKNOWN != null) return;
        BlockTypes.UNKNOWN = BlockTypeBuilder
                .builder(BlockUnknownBehavior.class)
                .vanillaBlock(BlockId.UNKNOWN)
                .build();
    }

    public static void initUnlitRedstoneTorch() {
        if (BlockTypes.UNLIT_REDSTONE_TORCH != null) return;
        BlockTypes.UNLIT_REDSTONE_TORCH = BlockTypeBuilder
                .builder(BlockUnlitRedstoneTorchBehavior.class)
                .vanillaBlock(BlockId.UNLIT_REDSTONE_TORCH)
                .setProperties(BlockPropertyTypes.TORCH_FACING_DIRECTION)
                .build();
    }

    public static void initUnpoweredComparator() {
        if (BlockTypes.UNPOWERED_COMPARATOR != null) return;
        BlockTypes.UNPOWERED_COMPARATOR = BlockTypeBuilder
                .builder(BlockUnpoweredComparatorBehavior.class)
                .vanillaBlock(BlockId.UNPOWERED_COMPARATOR)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OUTPUT_LIT_BIT, BlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
                .build();
    }

    public static void initUnpoweredRepeater() {
        if (BlockTypes.UNPOWERED_REPEATER != null) return;
        BlockTypes.UNPOWERED_REPEATER = BlockTypeBuilder
                .builder(BlockUnpoweredRepeaterBehavior.class)
                .vanillaBlock(BlockId.UNPOWERED_REPEATER)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.REPEATER_DELAY)
                .build();
    }

    public static void initVault() {
        if (BlockTypes.VAULT != null) return;
        BlockTypes.VAULT = BlockTypeBuilder
                .builder(BlockVaultBehavior.class)
                .vanillaBlock(BlockId.VAULT)
                .setProperties(BlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, BlockPropertyTypes.OMINOUS, BlockPropertyTypes.VAULT_STATE)
                .build();
    }

    public static void initVerdantFroglight() {
        if (BlockTypes.VERDANT_FROGLIGHT != null) return;
        BlockTypes.VERDANT_FROGLIGHT = BlockTypeBuilder
                .builder(BlockVerdantFroglightBehavior.class)
                .vanillaBlock(BlockId.VERDANT_FROGLIGHT)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initVine() {
        if (BlockTypes.VINE != null) return;
        BlockTypes.VINE = BlockTypeBuilder
                .builder(BlockVineBehavior.class)
                .vanillaBlock(BlockId.VINE)
                .setProperties(BlockPropertyTypes.VINE_DIRECTION_BITS)
                .build();
    }

    public static void initWallBanner() {
        if (BlockTypes.WALL_BANNER != null) return;
        BlockTypes.WALL_BANNER = BlockTypeBuilder
                .builder(BlockWallBannerBehavior.class)
                .vanillaBlock(BlockId.WALL_BANNER)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWallSign() {
        if (BlockTypes.WALL_SIGN != null) return;
        BlockTypes.WALL_SIGN = BlockTypeBuilder
                .builder(BlockWallSignBehavior.class)
                .vanillaBlock(BlockId.WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWarpedButton() {
        if (BlockTypes.WARPED_BUTTON != null) return;
        BlockTypes.WARPED_BUTTON = BlockTypeBuilder
                .builder(BlockWarpedButtonBehavior.class)
                .vanillaBlock(BlockId.WARPED_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWarpedDoor() {
        if (BlockTypes.WARPED_DOOR != null) return;
        BlockTypes.WARPED_DOOR = BlockTypeBuilder
                .builder(BlockWarpedDoorBehavior.class)
                .vanillaBlock(BlockId.WARPED_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWarpedDoubleSlab() {
        if (BlockTypes.WARPED_DOUBLE_SLAB != null) return;
        BlockTypes.WARPED_DOUBLE_SLAB = BlockTypeBuilder
                .builder(BlockWarpedDoubleSlabBehavior.class)
                .vanillaBlock(BlockId.WARPED_DOUBLE_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWarpedFence() {
        if (BlockTypes.WARPED_FENCE != null) return;
        BlockTypes.WARPED_FENCE = BlockTypeBuilder
                .builder(BlockWarpedFenceBehavior.class)
                .vanillaBlock(BlockId.WARPED_FENCE)
                .build();
    }

    public static void initWarpedFenceGate() {
        if (BlockTypes.WARPED_FENCE_GATE != null) return;
        BlockTypes.WARPED_FENCE_GATE = BlockTypeBuilder
                .builder(BlockWarpedFenceGateBehavior.class)
                .vanillaBlock(BlockId.WARPED_FENCE_GATE)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.IN_WALL_BIT, BlockPropertyTypes.OPEN_BIT)
                .build();
    }

    public static void initWarpedFungus() {
        if (BlockTypes.WARPED_FUNGUS != null) return;
        BlockTypes.WARPED_FUNGUS = BlockTypeBuilder
                .builder(BlockWarpedFungusBehavior.class)
                .vanillaBlock(BlockId.WARPED_FUNGUS)
                .build();
    }

    public static void initWarpedHangingSign() {
        if (BlockTypes.WARPED_HANGING_SIGN != null) return;
        BlockTypes.WARPED_HANGING_SIGN = BlockTypeBuilder
                .builder(BlockWarpedHangingSignBehavior.class)
                .vanillaBlock(BlockId.WARPED_HANGING_SIGN)
                .setProperties(BlockPropertyTypes.ATTACHED_BIT, BlockPropertyTypes.FACING_DIRECTION, BlockPropertyTypes.GROUND_SIGN_DIRECTION, BlockPropertyTypes.HANGING)
                .build();
    }

    public static void initWarpedHyphae() {
        if (BlockTypes.WARPED_HYPHAE != null) return;
        BlockTypes.WARPED_HYPHAE = BlockTypeBuilder
                .builder(BlockWarpedHyphaeBehavior.class)
                .vanillaBlock(BlockId.WARPED_HYPHAE)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initWarpedNylium() {
        if (BlockTypes.WARPED_NYLIUM != null) return;
        BlockTypes.WARPED_NYLIUM = BlockTypeBuilder
                .builder(BlockWarpedNyliumBehavior.class)
                .vanillaBlock(BlockId.WARPED_NYLIUM)
                .build();
    }

    public static void initWarpedPlanks() {
        if (BlockTypes.WARPED_PLANKS != null) return;
        BlockTypes.WARPED_PLANKS = BlockTypeBuilder
                .builder(BlockWarpedPlanksBehavior.class)
                .vanillaBlock(BlockId.WARPED_PLANKS)
                .build();
    }

    public static void initWarpedPressurePlate() {
        if (BlockTypes.WARPED_PRESSURE_PLATE != null) return;
        BlockTypes.WARPED_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockWarpedPressurePlateBehavior.class)
                .vanillaBlock(BlockId.WARPED_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initWarpedRoots() {
        if (BlockTypes.WARPED_ROOTS != null) return;
        BlockTypes.WARPED_ROOTS = BlockTypeBuilder
                .builder(BlockWarpedRootsBehavior.class)
                .vanillaBlock(BlockId.WARPED_ROOTS)
                .build();
    }

    public static void initWarpedSlab() {
        if (BlockTypes.WARPED_SLAB != null) return;
        BlockTypes.WARPED_SLAB = BlockTypeBuilder
                .builder(BlockWarpedSlabBehavior.class)
                .vanillaBlock(BlockId.WARPED_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWarpedStairs() {
        if (BlockTypes.WARPED_STAIRS != null) return;
        BlockTypes.WARPED_STAIRS = BlockTypeBuilder
                .builder(BlockWarpedStairsBehavior.class)
                .vanillaBlock(BlockId.WARPED_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWarpedStandingSign() {
        if (BlockTypes.WARPED_STANDING_SIGN != null) return;
        BlockTypes.WARPED_STANDING_SIGN = BlockTypeBuilder
                .builder(BlockWarpedStandingSignBehavior.class)
                .vanillaBlock(BlockId.WARPED_STANDING_SIGN)
                .setProperties(BlockPropertyTypes.GROUND_SIGN_DIRECTION)
                .build();
    }

    public static void initWarpedStem() {
        if (BlockTypes.WARPED_STEM != null) return;
        BlockTypes.WARPED_STEM = BlockTypeBuilder
                .builder(BlockWarpedStemBehavior.class)
                .vanillaBlock(BlockId.WARPED_STEM)
                .setProperties(BlockPropertyTypes.PILLAR_AXIS)
                .build();
    }

    public static void initWarpedTrapdoor() {
        if (BlockTypes.WARPED_TRAPDOOR != null) return;
        BlockTypes.WARPED_TRAPDOOR = BlockTypeBuilder
                .builder(BlockWarpedTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WARPED_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWarpedWallSign() {
        if (BlockTypes.WARPED_WALL_SIGN != null) return;
        BlockTypes.WARPED_WALL_SIGN = BlockTypeBuilder
                .builder(BlockWarpedWallSignBehavior.class)
                .vanillaBlock(BlockId.WARPED_WALL_SIGN)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWarpedWartBlock() {
        if (BlockTypes.WARPED_WART_BLOCK != null) return;
        BlockTypes.WARPED_WART_BLOCK = BlockTypeBuilder
                .builder(BlockWarpedWartBlockBehavior.class)
                .vanillaBlock(BlockId.WARPED_WART_BLOCK)
                .build();
    }

    public static void initWater() {
        if (BlockTypes.WATER != null) return;
        BlockTypes.WATER = BlockTypeBuilder
                .builder(BlockWaterBehavior.class)
                .vanillaBlock(BlockId.WATER)
                .setProperties(BlockPropertyTypes.LIQUID_DEPTH)
                .build();
    }

    public static void initWaterlily() {
        if (BlockTypes.WATERLILY != null) return;
        BlockTypes.WATERLILY = BlockTypeBuilder
                .builder(BlockWaterlilyBehavior.class)
                .vanillaBlock(BlockId.WATERLILY)
                .build();
    }

    public static void initWaxedChiseledCopper() {
        if (BlockTypes.WAXED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_CHISELED_COPPER = BlockTypeBuilder
                .builder(BlockWaxedChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedCopper() {
        if (BlockTypes.WAXED_COPPER != null) return;
        BlockTypes.WAXED_COPPER = BlockTypeBuilder
                .builder(BlockWaxedCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_COPPER)
                .build();
    }

    public static void initWaxedCopperBulb() {
        if (BlockTypes.WAXED_COPPER_BULB != null) return;
        BlockTypes.WAXED_COPPER_BULB = BlockTypeBuilder
                .builder(BlockWaxedCopperBulbBehavior.class)
                .vanillaBlock(BlockId.WAXED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedCopperDoor() {
        if (BlockTypes.WAXED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_COPPER_DOOR = BlockTypeBuilder
                .builder(BlockWaxedCopperDoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedCopperGrate() {
        if (BlockTypes.WAXED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_COPPER_GRATE = BlockTypeBuilder
                .builder(BlockWaxedCopperGrateBehavior.class)
                .vanillaBlock(BlockId.WAXED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedCopperTrapdoor() {
        if (BlockTypes.WAXED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_COPPER_TRAPDOOR = BlockTypeBuilder
                .builder(BlockWaxedCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedCutCopper() {
        if (BlockTypes.WAXED_CUT_COPPER != null) return;
        BlockTypes.WAXED_CUT_COPPER = BlockTypeBuilder
                .builder(BlockWaxedCutCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER)
                .build();
    }

    public static void initWaxedCutCopperSlab() {
        if (BlockTypes.WAXED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockWaxedCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedCutCopperStairs() {
        if (BlockTypes.WAXED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockWaxedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_DOUBLE_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockWaxedDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedExposedChiseledCopper() {
        if (BlockTypes.WAXED_EXPOSED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_EXPOSED_CHISELED_COPPER = BlockTypeBuilder
                .builder(BlockWaxedExposedChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedExposedCopper() {
        if (BlockTypes.WAXED_EXPOSED_COPPER != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER = BlockTypeBuilder
                .builder(BlockWaxedExposedCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER)
                .build();
    }

    public static void initWaxedExposedCopperBulb() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_BULB != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_BULB = BlockTypeBuilder
                .builder(BlockWaxedExposedCopperBulbBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedExposedCopperDoor() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_DOOR = BlockTypeBuilder
                .builder(BlockWaxedExposedCopperDoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedExposedCopperGrate() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_GRATE = BlockTypeBuilder
                .builder(BlockWaxedExposedCopperGrateBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedExposedCopperTrapdoor() {
        if (BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_EXPOSED_COPPER_TRAPDOOR = BlockTypeBuilder
                .builder(BlockWaxedExposedCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedExposedCutCopper() {
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER != null) return;
        BlockTypes.WAXED_EXPOSED_CUT_COPPER = BlockTypeBuilder
                .builder(BlockWaxedExposedCutCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER)
                .build();
    }

    public static void initWaxedExposedCutCopperSlab() {
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockWaxedExposedCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedExposedCutCopperStairs() {
        if (BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_EXPOSED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockWaxedExposedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedExposedDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockWaxedExposedDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedOxidizedChiseledCopper() {
        if (BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_OXIDIZED_CHISELED_COPPER = BlockTypeBuilder
                .builder(BlockWaxedOxidizedChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCopper() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER = BlockTypeBuilder
                .builder(BlockWaxedOxidizedCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCopperBulb() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_BULB != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_BULB = BlockTypeBuilder
                .builder(BlockWaxedOxidizedCopperBulbBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedOxidizedCopperDoor() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_DOOR = BlockTypeBuilder
                .builder(BlockWaxedOxidizedCopperDoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedOxidizedCopperGrate() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_GRATE = BlockTypeBuilder
                .builder(BlockWaxedOxidizedCopperGrateBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedOxidizedCopperTrapdoor() {
        if (BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_OXIDIZED_COPPER_TRAPDOOR = BlockTypeBuilder
                .builder(BlockWaxedOxidizedCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedOxidizedCutCopper() {
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER != null) return;
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER = BlockTypeBuilder
                .builder(BlockWaxedOxidizedCutCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER)
                .build();
    }

    public static void initWaxedOxidizedCutCopperSlab() {
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockWaxedOxidizedCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedOxidizedCutCopperStairs() {
        if (BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockWaxedOxidizedCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedOxidizedDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockWaxedOxidizedDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedWeatheredChiseledCopper() {
        if (BlockTypes.WAXED_WEATHERED_CHISELED_COPPER != null) return;
        BlockTypes.WAXED_WEATHERED_CHISELED_COPPER = BlockTypeBuilder
                .builder(BlockWaxedWeatheredChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CHISELED_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCopper() {
        if (BlockTypes.WAXED_WEATHERED_COPPER != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER = BlockTypeBuilder
                .builder(BlockWaxedWeatheredCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCopperBulb() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_BULB != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_BULB = BlockTypeBuilder
                .builder(BlockWaxedWeatheredCopperBulbBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWaxedWeatheredCopperDoor() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_DOOR != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_DOOR = BlockTypeBuilder
                .builder(BlockWaxedWeatheredCopperDoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWaxedWeatheredCopperGrate() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_GRATE != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_GRATE = BlockTypeBuilder
                .builder(BlockWaxedWeatheredCopperGrateBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_GRATE)
                .build();
    }

    public static void initWaxedWeatheredCopperTrapdoor() {
        if (BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WAXED_WEATHERED_COPPER_TRAPDOOR = BlockTypeBuilder
                .builder(BlockWaxedWeatheredCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWaxedWeatheredCutCopper() {
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER != null) return;
        BlockTypes.WAXED_WEATHERED_CUT_COPPER = BlockTypeBuilder
                .builder(BlockWaxedWeatheredCutCopperBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER)
                .build();
    }

    public static void initWaxedWeatheredCutCopperSlab() {
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockWaxedWeatheredCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWaxedWeatheredCutCopperStairs() {
        if (BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockWaxedWeatheredCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWaxedWeatheredDoubleCutCopperSlab() {
        if (BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockWaxedWeatheredDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWeatheredChiseledCopper() {
        if (BlockTypes.WEATHERED_CHISELED_COPPER != null) return;
        BlockTypes.WEATHERED_CHISELED_COPPER = BlockTypeBuilder
                .builder(BlockWeatheredChiseledCopperBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_CHISELED_COPPER)
                .build();
    }

    public static void initWeatheredCopper() {
        if (BlockTypes.WEATHERED_COPPER != null) return;
        BlockTypes.WEATHERED_COPPER = BlockTypeBuilder
                .builder(BlockWeatheredCopperBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER)
                .build();
    }

    public static void initWeatheredCopperBulb() {
        if (BlockTypes.WEATHERED_COPPER_BULB != null) return;
        BlockTypes.WEATHERED_COPPER_BULB = BlockTypeBuilder
                .builder(BlockWeatheredCopperBulbBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_BULB)
                .setProperties(BlockPropertyTypes.LIT, BlockPropertyTypes.POWERED_BIT)
                .build();
    }

    public static void initWeatheredCopperDoor() {
        if (BlockTypes.WEATHERED_COPPER_DOOR != null) return;
        BlockTypes.WEATHERED_COPPER_DOOR = BlockTypeBuilder
                .builder(BlockWeatheredCopperDoorBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWeatheredCopperGrate() {
        if (BlockTypes.WEATHERED_COPPER_GRATE != null) return;
        BlockTypes.WEATHERED_COPPER_GRATE = BlockTypeBuilder
                .builder(BlockWeatheredCopperGrateBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_GRATE)
                .build();
    }

    public static void initWeatheredCopperTrapdoor() {
        if (BlockTypes.WEATHERED_COPPER_TRAPDOOR != null) return;
        BlockTypes.WEATHERED_COPPER_TRAPDOOR = BlockTypeBuilder
                .builder(BlockWeatheredCopperTrapdoorBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_COPPER_TRAPDOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPSIDE_DOWN_BIT)
                .build();
    }

    public static void initWeatheredCutCopper() {
        if (BlockTypes.WEATHERED_CUT_COPPER != null) return;
        BlockTypes.WEATHERED_CUT_COPPER = BlockTypeBuilder
                .builder(BlockWeatheredCutCopperBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER)
                .build();
    }

    public static void initWeatheredCutCopperSlab() {
        if (BlockTypes.WEATHERED_CUT_COPPER_SLAB != null) return;
        BlockTypes.WEATHERED_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockWeatheredCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWeatheredCutCopperStairs() {
        if (BlockTypes.WEATHERED_CUT_COPPER_STAIRS != null) return;
        BlockTypes.WEATHERED_CUT_COPPER_STAIRS = BlockTypeBuilder
                .builder(BlockWeatheredCutCopperStairsBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_CUT_COPPER_STAIRS)
                .setProperties(BlockPropertyTypes.UPSIDE_DOWN_BIT, BlockPropertyTypes.WEIRDO_DIRECTION)
                .build();
    }

    public static void initWeatheredDoubleCutCopperSlab() {
        if (BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB != null) return;
        BlockTypes.WEATHERED_DOUBLE_CUT_COPPER_SLAB = BlockTypeBuilder
                .builder(BlockWeatheredDoubleCutCopperSlabBehavior.class)
                .vanillaBlock(BlockId.WEATHERED_DOUBLE_CUT_COPPER_SLAB)
                .setProperties(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
                .build();
    }

    public static void initWeb() {
        if (BlockTypes.WEB != null) return;
        BlockTypes.WEB = BlockTypeBuilder
                .builder(BlockWebBehavior.class)
                .vanillaBlock(BlockId.WEB)
                .build();
    }

    public static void initWeepingVines() {
        if (BlockTypes.WEEPING_VINES != null) return;
        BlockTypes.WEEPING_VINES = BlockTypeBuilder
                .builder(BlockWeepingVinesBehavior.class)
                .vanillaBlock(BlockId.WEEPING_VINES)
                .setProperties(BlockPropertyTypes.WEEPING_VINES_AGE)
                .build();
    }

    public static void initWheat() {
        if (BlockTypes.WHEAT != null) return;
        BlockTypes.WHEAT = BlockTypeBuilder
                .builder(BlockWheatBehavior.class)
                .vanillaBlock(BlockId.WHEAT)
                .setProperties(BlockPropertyTypes.GROWTH)
                .build();
    }

    public static void initWhiteCandle() {
        if (BlockTypes.WHITE_CANDLE != null) return;
        BlockTypes.WHITE_CANDLE = BlockTypeBuilder
                .builder(BlockWhiteCandleBehavior.class)
                .vanillaBlock(BlockId.WHITE_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initWhiteCandleCake() {
        if (BlockTypes.WHITE_CANDLE_CAKE != null) return;
        BlockTypes.WHITE_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockWhiteCandleCakeBehavior.class)
                .vanillaBlock(BlockId.WHITE_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initWhiteCarpet() {
        if (BlockTypes.WHITE_CARPET != null) return;
        BlockTypes.WHITE_CARPET = BlockTypeBuilder
                .builder(BlockWhiteCarpetBehavior.class)
                .vanillaBlock(BlockId.WHITE_CARPET)
                .build();
    }

    public static void initWhiteConcrete() {
        if (BlockTypes.WHITE_CONCRETE != null) return;
        BlockTypes.WHITE_CONCRETE = BlockTypeBuilder
                .builder(BlockWhiteConcreteBehavior.class)
                .vanillaBlock(BlockId.WHITE_CONCRETE)
                .build();
    }

    public static void initWhiteConcretePowder() {
        if (BlockTypes.WHITE_CONCRETE_POWDER != null) return;
        BlockTypes.WHITE_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockWhiteConcretePowderBehavior.class)
                .vanillaBlock(BlockId.WHITE_CONCRETE_POWDER)
                .build();
    }

    public static void initWhiteGlazedTerracotta() {
        if (BlockTypes.WHITE_GLAZED_TERRACOTTA != null) return;
        BlockTypes.WHITE_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockWhiteGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.WHITE_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWhiteShulkerBox() {
        if (BlockTypes.WHITE_SHULKER_BOX != null) return;
        BlockTypes.WHITE_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockWhiteShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.WHITE_SHULKER_BOX)
                .build();
    }

    public static void initWhiteStainedGlass() {
        if (BlockTypes.WHITE_STAINED_GLASS != null) return;
        BlockTypes.WHITE_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockWhiteStainedGlassBehavior.class)
                .vanillaBlock(BlockId.WHITE_STAINED_GLASS)
                .build();
    }

    public static void initWhiteStainedGlassPane() {
        if (BlockTypes.WHITE_STAINED_GLASS_PANE != null) return;
        BlockTypes.WHITE_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockWhiteStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.WHITE_STAINED_GLASS_PANE)
                .build();
    }

    public static void initWhiteTerracotta() {
        if (BlockTypes.WHITE_TERRACOTTA != null) return;
        BlockTypes.WHITE_TERRACOTTA = BlockTypeBuilder
                .builder(BlockWhiteTerracottaBehavior.class)
                .vanillaBlock(BlockId.WHITE_TERRACOTTA)
                .build();
    }

    public static void initWhiteTulip() {
        if (BlockTypes.WHITE_TULIP != null) return;
        BlockTypes.WHITE_TULIP = BlockTypeBuilder
                .builder(BlockWhiteTulipBehavior.class)
                .vanillaBlock(BlockId.WHITE_TULIP)
                .build();
    }

    public static void initWhiteWool() {
        if (BlockTypes.WHITE_WOOL != null) return;
        BlockTypes.WHITE_WOOL = BlockTypeBuilder
                .builder(BlockWhiteWoolBehavior.class)
                .vanillaBlock(BlockId.WHITE_WOOL)
                .build();
    }

    public static void initWitherRose() {
        if (BlockTypes.WITHER_ROSE != null) return;
        BlockTypes.WITHER_ROSE = BlockTypeBuilder
                .builder(BlockWitherRoseBehavior.class)
                .vanillaBlock(BlockId.WITHER_ROSE)
                .build();
    }

    public static void initWoodenButton() {
        if (BlockTypes.WOODEN_BUTTON != null) return;
        BlockTypes.WOODEN_BUTTON = BlockTypeBuilder
                .builder(BlockWoodenButtonBehavior.class)
                .vanillaBlock(BlockId.WOODEN_BUTTON)
                .setProperties(BlockPropertyTypes.BUTTON_PRESSED_BIT, BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initWoodenDoor() {
        if (BlockTypes.WOODEN_DOOR != null) return;
        BlockTypes.WOODEN_DOOR = BlockTypeBuilder
                .builder(BlockWoodenDoorBehavior.class)
                .vanillaBlock(BlockId.WOODEN_DOOR)
                .setProperties(BlockPropertyTypes.DIRECTION, BlockPropertyTypes.DOOR_HINGE_BIT, BlockPropertyTypes.OPEN_BIT, BlockPropertyTypes.UPPER_BLOCK_BIT)
                .build();
    }

    public static void initWoodenPressurePlate() {
        if (BlockTypes.WOODEN_PRESSURE_PLATE != null) return;
        BlockTypes.WOODEN_PRESSURE_PLATE = BlockTypeBuilder
                .builder(BlockWoodenPressurePlateBehavior.class)
                .vanillaBlock(BlockId.WOODEN_PRESSURE_PLATE)
                .setProperties(BlockPropertyTypes.REDSTONE_SIGNAL)
                .build();
    }

    public static void initYellowCandle() {
        if (BlockTypes.YELLOW_CANDLE != null) return;
        BlockTypes.YELLOW_CANDLE = BlockTypeBuilder
                .builder(BlockYellowCandleBehavior.class)
                .vanillaBlock(BlockId.YELLOW_CANDLE)
                .setProperties(BlockPropertyTypes.CANDLES, BlockPropertyTypes.LIT)
                .build();
    }

    public static void initYellowCandleCake() {
        if (BlockTypes.YELLOW_CANDLE_CAKE != null) return;
        BlockTypes.YELLOW_CANDLE_CAKE = BlockTypeBuilder
                .builder(BlockYellowCandleCakeBehavior.class)
                .vanillaBlock(BlockId.YELLOW_CANDLE_CAKE)
                .setProperties(BlockPropertyTypes.LIT)
                .build();
    }

    public static void initYellowCarpet() {
        if (BlockTypes.YELLOW_CARPET != null) return;
        BlockTypes.YELLOW_CARPET = BlockTypeBuilder
                .builder(BlockYellowCarpetBehavior.class)
                .vanillaBlock(BlockId.YELLOW_CARPET)
                .build();
    }

    public static void initYellowConcrete() {
        if (BlockTypes.YELLOW_CONCRETE != null) return;
        BlockTypes.YELLOW_CONCRETE = BlockTypeBuilder
                .builder(BlockYellowConcreteBehavior.class)
                .vanillaBlock(BlockId.YELLOW_CONCRETE)
                .build();
    }

    public static void initYellowConcretePowder() {
        if (BlockTypes.YELLOW_CONCRETE_POWDER != null) return;
        BlockTypes.YELLOW_CONCRETE_POWDER = BlockTypeBuilder
                .builder(BlockYellowConcretePowderBehavior.class)
                .vanillaBlock(BlockId.YELLOW_CONCRETE_POWDER)
                .build();
    }

    public static void initYellowGlazedTerracotta() {
        if (BlockTypes.YELLOW_GLAZED_TERRACOTTA != null) return;
        BlockTypes.YELLOW_GLAZED_TERRACOTTA = BlockTypeBuilder
                .builder(BlockYellowGlazedTerracottaBehavior.class)
                .vanillaBlock(BlockId.YELLOW_GLAZED_TERRACOTTA)
                .setProperties(BlockPropertyTypes.FACING_DIRECTION)
                .build();
    }

    public static void initYellowShulkerBox() {
        if (BlockTypes.YELLOW_SHULKER_BOX != null) return;
        BlockTypes.YELLOW_SHULKER_BOX = BlockTypeBuilder
                .builder(BlockYellowShulkerBoxBehavior.class)
                .vanillaBlock(BlockId.YELLOW_SHULKER_BOX)
                .build();
    }

    public static void initYellowStainedGlass() {
        if (BlockTypes.YELLOW_STAINED_GLASS != null) return;
        BlockTypes.YELLOW_STAINED_GLASS = BlockTypeBuilder
                .builder(BlockYellowStainedGlassBehavior.class)
                .vanillaBlock(BlockId.YELLOW_STAINED_GLASS)
                .build();
    }

    public static void initYellowStainedGlassPane() {
        if (BlockTypes.YELLOW_STAINED_GLASS_PANE != null) return;
        BlockTypes.YELLOW_STAINED_GLASS_PANE = BlockTypeBuilder
                .builder(BlockYellowStainedGlassPaneBehavior.class)
                .vanillaBlock(BlockId.YELLOW_STAINED_GLASS_PANE)
                .build();
    }

    public static void initYellowTerracotta() {
        if (BlockTypes.YELLOW_TERRACOTTA != null) return;
        BlockTypes.YELLOW_TERRACOTTA = BlockTypeBuilder
                .builder(BlockYellowTerracottaBehavior.class)
                .vanillaBlock(BlockId.YELLOW_TERRACOTTA)
                .build();
    }

    public static void initYellowWool() {
        if (BlockTypes.YELLOW_WOOL != null) return;
        BlockTypes.YELLOW_WOOL = BlockTypeBuilder
                .builder(BlockYellowWoolBehavior.class)
                .vanillaBlock(BlockId.YELLOW_WOOL)
                .build();
    }
}
