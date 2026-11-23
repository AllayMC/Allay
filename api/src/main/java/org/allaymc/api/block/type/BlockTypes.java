package org.allaymc.api.block.type;

import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.interfaces.BlockAmethystClusterBehavior;
import org.allaymc.api.block.interfaces.BlockAnvilBehavior;
import org.allaymc.api.block.interfaces.BlockBarrelBehavior;
import org.allaymc.api.block.interfaces.BlockBeaconBehavior;
import org.allaymc.api.block.interfaces.BlockBedBehavior;
import org.allaymc.api.block.interfaces.BlockBigFlower;
import org.allaymc.api.block.interfaces.BlockBlastFurnaceBehavior;
import org.allaymc.api.block.interfaces.BlockBrewingStandBehavior;
import org.allaymc.api.block.interfaces.BlockCandleBehavior;
import org.allaymc.api.block.interfaces.BlockCandleCakeBehavior;
import org.allaymc.api.block.interfaces.BlockCarpetBehavior;
import org.allaymc.api.block.interfaces.BlockCauldronBehavior;
import org.allaymc.api.block.interfaces.BlockChestBehavior;
import org.allaymc.api.block.interfaces.BlockComparatorBehavior;
import org.allaymc.api.block.interfaces.BlockConcretePowderBehavior;
import org.allaymc.api.block.interfaces.BlockCopperBarsBehavior;
import org.allaymc.api.block.interfaces.BlockCopperBehavior;
import org.allaymc.api.block.interfaces.BlockCopperBulbBehavior;
import org.allaymc.api.block.interfaces.BlockCopperChainBehavior;
import org.allaymc.api.block.interfaces.BlockCopperDoorBehavior;
import org.allaymc.api.block.interfaces.BlockCopperGolemStatueBehavior;
import org.allaymc.api.block.interfaces.BlockCopperGrateBehavior;
import org.allaymc.api.block.interfaces.BlockCopperLanternBehavior;
import org.allaymc.api.block.interfaces.BlockCopperSlabBehavior;
import org.allaymc.api.block.interfaces.BlockCopperStairsBehavior;
import org.allaymc.api.block.interfaces.BlockCopperTrapdoorBehavior;
import org.allaymc.api.block.interfaces.BlockDaylightDetectorBehavior;
import org.allaymc.api.block.interfaces.BlockDecoratedPotBehavior;
import org.allaymc.api.block.interfaces.BlockDoubleCopperSlabBehavior;
import org.allaymc.api.block.interfaces.BlockDoubleSlabBehavior;
import org.allaymc.api.block.interfaces.BlockDragonEggBehavior;
import org.allaymc.api.block.interfaces.BlockEnchantingTableBehavior;
import org.allaymc.api.block.interfaces.BlockEnderChestBehavior;
import org.allaymc.api.block.interfaces.BlockFenceGateBehavior;
import org.allaymc.api.block.interfaces.BlockFlowerPotBehavior;
import org.allaymc.api.block.interfaces.BlockFurnaceBehavior;
import org.allaymc.api.block.interfaces.BlockGlassBehavior;
import org.allaymc.api.block.interfaces.BlockGlassPaneBehavior;
import org.allaymc.api.block.interfaces.BlockGlazedTerracottaBehavior;
import org.allaymc.api.block.interfaces.BlockGravelBehavior;
import org.allaymc.api.block.interfaces.BlockHangingSignBehavior;
import org.allaymc.api.block.interfaces.BlockHeadBehavior;
import org.allaymc.api.block.interfaces.BlockInfestedBlockBehavior;
import org.allaymc.api.block.interfaces.BlockIronBarsBehavior;
import org.allaymc.api.block.interfaces.BlockJukeboxBehavior;
import org.allaymc.api.block.interfaces.BlockLanternBehavior;
import org.allaymc.api.block.interfaces.BlockLeavesBehavior;
import org.allaymc.api.block.interfaces.BlockLecternBehavior;
import org.allaymc.api.block.interfaces.BlockLightningRodBehavior;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.interfaces.BlockNoteblockBehavior;
import org.allaymc.api.block.interfaces.BlockOreBehavior;
import org.allaymc.api.block.interfaces.BlockPlantPile;
import org.allaymc.api.block.interfaces.BlockPointedDripstoneBehavior;
import org.allaymc.api.block.interfaces.BlockRedSandBehavior;
import org.allaymc.api.block.interfaces.BlockRedstoneLampBehavior;
import org.allaymc.api.block.interfaces.BlockRepeaterBehavior;
import org.allaymc.api.block.interfaces.BlockRespawnAnchorBehavior;
import org.allaymc.api.block.interfaces.BlockSandBehavior;
import org.allaymc.api.block.interfaces.BlockScaffoldingBehavior;
import org.allaymc.api.block.interfaces.BlockShelfBehavior;
import org.allaymc.api.block.interfaces.BlockShulkerBoxBehavior;
import org.allaymc.api.block.interfaces.BlockSignBehavior;
import org.allaymc.api.block.interfaces.BlockSlabBehavior;
import org.allaymc.api.block.interfaces.BlockSmallFlower;
import org.allaymc.api.block.interfaces.BlockSmokerBehavior;
import org.allaymc.api.block.interfaces.BlockSnowLayerBehavior;
import org.allaymc.api.block.interfaces.BlockStairsBehavior;
import org.allaymc.api.block.interfaces.BlockStandingBannerBehavior;
import org.allaymc.api.block.interfaces.BlockSuspiciousGravelBehavior;
import org.allaymc.api.block.interfaces.BlockSuspiciousSandBehavior;
import org.allaymc.api.block.interfaces.BlockTargetBehavior;
import org.allaymc.api.block.interfaces.BlockTerracottaBehavior;
import org.allaymc.api.block.interfaces.BlockTntBehavior;
import org.allaymc.api.block.interfaces.BlockTorchBehavior;
import org.allaymc.api.block.interfaces.BlockTrapdoorBehavior;
import org.allaymc.api.block.interfaces.BlockTrappedChestBehavior;
import org.allaymc.api.block.interfaces.BlockWallBannerBehavior;
import org.allaymc.api.block.interfaces.BlockWallBehavior;
import org.allaymc.api.block.interfaces.BlockWoodBehavior;
import org.allaymc.api.block.property.type.BlockPropertyTypes;

@MinecraftVersionSensitive
public final class BlockTypes {
    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> ACACIA_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> ACACIA_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> ACACIA_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> ACACIA_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> ACACIA_FENCE_GATE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> ACACIA_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> ACACIA_LEAVES;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> ACACIA_LOG;

    public static BlockType<BlockBehavior> ACACIA_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> ACACIA_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     */
    public static BlockType<BlockBehavior> ACACIA_SAPLING;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> ACACIA_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> ACACIA_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> ACACIA_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> ACACIA_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> ACACIA_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> ACACIA_WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> ACACIA_WOOD;

    /**
     * {@link BlockPropertyTypes#RAIL_DATA_BIT}
     * {@link BlockPropertyTypes#RAIL_DIRECTION_6}
     */
    public static BlockType<BlockBehavior> ACTIVATOR_RAIL;

    public static BlockType<BlockBehavior> AIR;

    public static BlockType<BlockSmallFlower> ALLIUM;

    public static BlockType<BlockBehavior> ALLOW;

    public static BlockType<BlockBehavior> AMETHYST_BLOCK;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_BLOCK_FACE}
     */
    public static BlockType<BlockAmethystClusterBehavior> AMETHYST_CLUSTER;

    public static BlockType<BlockBehavior> ANCIENT_DEBRIS;

    public static BlockType<BlockBehavior> ANDESITE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> ANDESITE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> ANDESITE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> ANDESITE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> ANDESITE_WALL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockAnvilBehavior> ANVIL;

    public static BlockType<BlockBehavior> AZALEA;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> AZALEA_LEAVES;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> AZALEA_LEAVES_FLOWERED;

    public static BlockType<BlockSmallFlower> AZURE_BLUET;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     * {@link BlockPropertyTypes#BAMBOO_LEAF_SIZE}
     * {@link BlockPropertyTypes#BAMBOO_STALK_THICKNESS}
     */
    public static BlockType<BlockBehavior> BAMBOO;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> BAMBOO_BLOCK;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> BAMBOO_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> BAMBOO_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BAMBOO_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> BAMBOO_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> BAMBOO_FENCE_GATE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> BAMBOO_HANGING_SIGN;

    public static BlockType<BlockBehavior> BAMBOO_MOSAIC;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BAMBOO_MOSAIC_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BAMBOO_MOSAIC_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BAMBOO_MOSAIC_STAIRS;

    public static BlockType<BlockBehavior> BAMBOO_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> BAMBOO_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     */
    public static BlockType<BlockBehavior> BAMBOO_SAPLING;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> BAMBOO_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BAMBOO_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BAMBOO_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> BAMBOO_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> BAMBOO_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> BAMBOO_WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockBarrelBehavior> BARREL;

    public static BlockType<BlockBehavior> BARRIER;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> BASALT;

    public static BlockType<BlockBeaconBehavior> BEACON;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#HEAD_PIECE_BIT}
     * {@link BlockPropertyTypes#OCCUPIED_BIT}
     */
    public static BlockType<BlockBedBehavior> BED;

    /**
     * {@link BlockPropertyTypes#INFINIBURN_BIT}
     */
    public static BlockType<BlockBehavior> BEDROCK;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#HONEY_LEVEL}
     */
    public static BlockType<BlockBehavior> BEE_NEST;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#HONEY_LEVEL}
     */
    public static BlockType<BlockBehavior> BEEHIVE;

    /**
     * {@link BlockPropertyTypes#GROWTH_8}
     */
    public static BlockType<BlockBehavior> BEETROOT;

    /**
     * {@link BlockPropertyTypes#ATTACHMENT}
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#TOGGLE_BIT}
     */
    public static BlockType<BlockBehavior> BELL;

    /**
     * {@link BlockPropertyTypes#BIG_DRIPLEAF_HEAD}
     * {@link BlockPropertyTypes#BIG_DRIPLEAF_TILT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> BIG_DRIPLEAF;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> BIRCH_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> BIRCH_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BIRCH_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> BIRCH_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> BIRCH_FENCE_GATE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> BIRCH_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> BIRCH_LEAVES;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> BIRCH_LOG;

    public static BlockType<BlockBehavior> BIRCH_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> BIRCH_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     */
    public static BlockType<BlockBehavior> BIRCH_SAPLING;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> BIRCH_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BIRCH_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BIRCH_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> BIRCH_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> BIRCH_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> BIRCH_WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> BIRCH_WOOD;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> BLACK_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> BLACK_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> BLACK_CARPET;

    public static BlockType<BlockBehavior> BLACK_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BLACK_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> BLACK_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BLACK_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BLACK_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> BLACK_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> BLACK_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> BLACK_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> BLACK_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> BLACK_TERRACOTTA;

    public static BlockType<BlockBehavior> BLACK_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BLACK_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BLACK_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BLACK_WOOL_STAIRS;

    public static BlockType<BlockBehavior> BLACKSTONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BLACKSTONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BLACKSTONE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BLACKSTONE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> BLACKSTONE_WALL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBlastFurnaceBehavior> BLAST_FURNACE;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> BLUE_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> BLUE_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> BLUE_CARPET;

    public static BlockType<BlockBehavior> BLUE_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BLUE_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> BLUE_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BLUE_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BLUE_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> BLUE_GLAZED_TERRACOTTA;

    public static BlockType<BlockBehavior> BLUE_ICE;

    public static BlockType<BlockSmallFlower> BLUE_ORCHID;

    public static BlockType<BlockShulkerBoxBehavior> BLUE_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> BLUE_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> BLUE_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> BLUE_TERRACOTTA;

    public static BlockType<BlockBehavior> BLUE_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BLUE_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BLUE_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BLUE_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#DEPRECATED}
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> BONE_BLOCK;

    public static BlockType<BlockBehavior> BOOKSHELF;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockBehavior> BORDER_BLOCK;

    public static BlockType<BlockBehavior> BRAIN_CORAL;

    public static BlockType<BlockBehavior> BRAIN_CORAL_BLOCK;

    /**
     * {@link BlockPropertyTypes#CORAL_FAN_DIRECTION}
     */
    public static BlockType<BlockBehavior> BRAIN_CORAL_FAN;

    /**
     * {@link BlockPropertyTypes#CORAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> BRAIN_CORAL_WALL_FAN;

    /**
     * {@link BlockPropertyTypes#BREWING_STAND_SLOT_A_BIT}
     * {@link BlockPropertyTypes#BREWING_STAND_SLOT_B_BIT}
     * {@link BlockPropertyTypes#BREWING_STAND_SLOT_C_BIT}
     */
    public static BlockType<BlockBrewingStandBehavior> BREWING_STAND;

    public static BlockType<BlockBehavior> BRICK_BLOCK;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> BRICK_WALL;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> BROWN_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> BROWN_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> BROWN_CARPET;

    public static BlockType<BlockBehavior> BROWN_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BROWN_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> BROWN_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BROWN_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BROWN_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> BROWN_GLAZED_TERRACOTTA;

    public static BlockType<BlockBehavior> BROWN_MUSHROOM;

    /**
     * {@link BlockPropertyTypes#HUGE_MUSHROOM_BITS}
     */
    public static BlockType<BlockBehavior> BROWN_MUSHROOM_BLOCK;

    public static BlockType<BlockShulkerBoxBehavior> BROWN_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> BROWN_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> BROWN_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> BROWN_TERRACOTTA;

    public static BlockType<BlockBehavior> BROWN_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> BROWN_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> BROWN_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> BROWN_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#DRAG_DOWN}
     */
    public static BlockType<BlockBehavior> BUBBLE_COLUMN;

    public static BlockType<BlockBehavior> BUBBLE_CORAL;

    public static BlockType<BlockBehavior> BUBBLE_CORAL_BLOCK;

    /**
     * {@link BlockPropertyTypes#CORAL_FAN_DIRECTION}
     */
    public static BlockType<BlockBehavior> BUBBLE_CORAL_FAN;

    /**
     * {@link BlockPropertyTypes#CORAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> BUBBLE_CORAL_WALL_FAN;

    public static BlockType<BlockBehavior> BUDDING_AMETHYST;

    public static BlockType<BlockBehavior> BUSH;

    /**
     * {@link BlockPropertyTypes#AGE_16}
     */
    public static BlockType<BlockBehavior> CACTUS;

    public static BlockType<BlockBehavior> CACTUS_FLOWER;

    /**
     * {@link BlockPropertyTypes#BITE_COUNTER}
     */
    public static BlockType<BlockBehavior> CAKE;

    public static BlockType<BlockBehavior> CALCITE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#SCULK_SENSOR_PHASE}
     */
    public static BlockType<BlockBehavior> CALIBRATED_SCULK_SENSOR;

    public static BlockType<BlockBehavior> CAMERA;

    /**
     * {@link BlockPropertyTypes#EXTINGUISHED}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> CAMPFIRE;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> CANDLE_CAKE;

    /**
     * {@link BlockPropertyTypes#GROWTH_8}
     */
    public static BlockType<BlockBehavior> CARROTS;

    public static BlockType<BlockBehavior> CARTOGRAPHY_TABLE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> CARVED_PUMPKIN;

    /**
     * {@link BlockPropertyTypes#CAULDRON_LIQUID}
     * {@link BlockPropertyTypes#FILL_LEVEL}
     */
    public static BlockType<BlockCauldronBehavior> CAULDRON;

    /**
     * {@link BlockPropertyTypes#GROWING_PLANT_AGE}
     */
    public static BlockType<BlockBehavior> CAVE_VINES;

    /**
     * {@link BlockPropertyTypes#GROWING_PLANT_AGE}
     */
    public static BlockType<BlockBehavior> CAVE_VINES_BODY_WITH_BERRIES;

    /**
     * {@link BlockPropertyTypes#GROWING_PLANT_AGE}
     */
    public static BlockType<BlockBehavior> CAVE_VINES_HEAD_WITH_BERRIES;

    /**
     * {@link BlockPropertyTypes#CONDITIONAL_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> CHAIN_COMMAND_BLOCK;

    /**
     * {@link BlockPropertyTypes#DIRECTION_16}
     */
    public static BlockType<BlockBehavior> CHALKBOARD;

    public static BlockType<BlockBehavior> CHEMICAL_HEAT;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> CHERRY_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> CHERRY_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> CHERRY_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> CHERRY_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> CHERRY_FENCE_GATE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> CHERRY_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> CHERRY_LEAVES;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> CHERRY_LOG;

    public static BlockType<BlockBehavior> CHERRY_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> CHERRY_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     */
    public static BlockType<BlockBehavior> CHERRY_SAPLING;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> CHERRY_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> CHERRY_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> CHERRY_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> CHERRY_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> CHERRY_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> CHERRY_WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> CHERRY_WOOD;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockChestBehavior> CHEST;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockAnvilBehavior> CHIPPED_ANVIL;

    /**
     * {@link BlockPropertyTypes#BOOKS_STORED}
     * {@link BlockPropertyTypes#DIRECTION_4}
     */
    public static BlockType<BlockBehavior> CHISELED_BOOKSHELF;

    public static BlockType<BlockBehavior> CHISELED_CINNABAR;

    public static BlockType<BlockCopperBehavior> CHISELED_COPPER;

    public static BlockType<BlockBehavior> CHISELED_DEEPSLATE;

    public static BlockType<BlockBehavior> CHISELED_NETHER_BRICKS;

    public static BlockType<BlockBehavior> CHISELED_POLISHED_BLACKSTONE;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> CHISELED_QUARTZ_BLOCK;

    public static BlockType<BlockBehavior> CHISELED_RED_SANDSTONE;

    public static BlockType<BlockBehavior> CHISELED_RESIN_BRICKS;

    public static BlockType<BlockBehavior> CHISELED_SANDSTONE;

    public static BlockType<BlockBehavior> CHISELED_STONE_BRICKS;

    public static BlockType<BlockBehavior> CHISELED_SULFUR;

    public static BlockType<BlockBehavior> CHISELED_TUFF;

    public static BlockType<BlockBehavior> CHISELED_TUFF_BRICKS;

    /**
     * {@link BlockPropertyTypes#AGE_6}
     */
    public static BlockType<BlockBehavior> CHORUS_FLOWER;

    public static BlockType<BlockBehavior> CHORUS_PLANT;

    public static BlockType<BlockBehavior> CINNABAR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> CINNABAR_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> CINNABAR_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> CINNABAR_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> CINNABAR_BRICK_WALL;

    public static BlockType<BlockBehavior> CINNABAR_BRICKS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> CINNABAR_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> CINNABAR_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> CINNABAR_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> CINNABAR_WALL;

    public static BlockType<BlockBehavior> CLAY;

    public static BlockType<BlockBehavior> CLIENT_REQUEST_PLACEHOLDER_BLOCK;

    public static BlockType<BlockSmallFlower> CLOSED_EYEBLOSSOM;

    public static BlockType<BlockBehavior> COAL_BLOCK;

    public static BlockType<BlockOreBehavior> COAL_ORE;

    public static BlockType<BlockBehavior> COARSE_DIRT;

    public static BlockType<BlockBehavior> COBBLED_DEEPSLATE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> COBBLED_DEEPSLATE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> COBBLED_DEEPSLATE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> COBBLED_DEEPSLATE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> COBBLED_DEEPSLATE_WALL;

    public static BlockType<BlockBehavior> COBBLESTONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> COBBLESTONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> COBBLESTONE_SLAB;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> COBBLESTONE_WALL;

    /**
     * {@link BlockPropertyTypes#AGE_3}
     * {@link BlockPropertyTypes#DIRECTION_4}
     */
    public static BlockType<BlockBehavior> COCOA;

    /**
     * {@link BlockPropertyTypes#TORCH_FACING_DIRECTION}
     */
    public static BlockType<BlockTorchBehavior> COLORED_TORCH_BLUE;

    /**
     * {@link BlockPropertyTypes#TORCH_FACING_DIRECTION}
     */
    public static BlockType<BlockTorchBehavior> COLORED_TORCH_GREEN;

    /**
     * {@link BlockPropertyTypes#TORCH_FACING_DIRECTION}
     */
    public static BlockType<BlockTorchBehavior> COLORED_TORCH_PURPLE;

    /**
     * {@link BlockPropertyTypes#TORCH_FACING_DIRECTION}
     */
    public static BlockType<BlockTorchBehavior> COLORED_TORCH_RED;

    /**
     * {@link BlockPropertyTypes#CONDITIONAL_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> COMMAND_BLOCK;

    /**
     * {@link BlockPropertyTypes#COMPOSTER_FILL_LEVEL}
     */
    public static BlockType<BlockBehavior> COMPOSTER;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     */
    public static BlockType<BlockBehavior> COMPOUND_CREATOR;

    public static BlockType<BlockBehavior> CONDUIT;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockCopperBarsBehavior> COPPER_BARS;

    public static BlockType<BlockCopperBehavior> COPPER_BLOCK;

    /**
     * {@link BlockPropertyTypes#LIT}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockCopperBulbBehavior> COPPER_BULB;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockCopperChainBehavior> COPPER_CHAIN;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> COPPER_CHEST;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockCopperDoorBehavior> COPPER_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockCopperGolemStatueBehavior> COPPER_GOLEM_STATUE;

    public static BlockType<BlockCopperGrateBehavior> COPPER_GRATE;

    /**
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockCopperLanternBehavior> COPPER_LANTERN;

    public static BlockType<BlockOreBehavior> COPPER_ORE;

    /**
     * {@link BlockPropertyTypes#TORCH_FACING_DIRECTION}
     */
    public static BlockType<BlockTorchBehavior> COPPER_TORCH;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockCopperTrapdoorBehavior> COPPER_TRAPDOOR;

    public static BlockType<BlockSmallFlower> CORNFLOWER;

    public static BlockType<BlockBehavior> CRACKED_DEEPSLATE_BRICKS;

    public static BlockType<BlockBehavior> CRACKED_DEEPSLATE_TILES;

    public static BlockType<BlockBehavior> CRACKED_NETHER_BRICKS;

    public static BlockType<BlockBehavior> CRACKED_POLISHED_BLACKSTONE_BRICKS;

    public static BlockType<BlockBehavior> CRACKED_STONE_BRICKS;

    /**
     * {@link BlockPropertyTypes#CRAFTING}
     * {@link BlockPropertyTypes#ORIENTATION}
     * {@link BlockPropertyTypes#TRIGGERED_BIT}
     */
    public static BlockType<BlockBehavior> CRAFTER;

    public static BlockType<BlockBehavior> CRAFTING_TABLE;

    /**
     * {@link BlockPropertyTypes#CREAKING_HEART_STATE}
     * {@link BlockPropertyTypes#NATURAL}
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> CREAKING_HEART;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockHeadBehavior> CREEPER_HEAD;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> CRIMSON_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> CRIMSON_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> CRIMSON_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> CRIMSON_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> CRIMSON_FENCE_GATE;

    public static BlockType<BlockBehavior> CRIMSON_FUNGUS;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> CRIMSON_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> CRIMSON_HYPHAE;

    public static BlockType<BlockBehavior> CRIMSON_NYLIUM;

    public static BlockType<BlockBehavior> CRIMSON_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> CRIMSON_PRESSURE_PLATE;

    public static BlockType<BlockBehavior> CRIMSON_ROOTS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> CRIMSON_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> CRIMSON_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> CRIMSON_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> CRIMSON_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> CRIMSON_STEM;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> CRIMSON_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> CRIMSON_WALL_SIGN;

    public static BlockType<BlockBehavior> CRYING_OBSIDIAN;

    public static BlockType<BlockCopperBehavior> CUT_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockCopperSlabBehavior> CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockCopperStairsBehavior> CUT_COPPER_STAIRS;

    public static BlockType<BlockBehavior> CUT_RED_SANDSTONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> CUT_RED_SANDSTONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> CUT_RED_SANDSTONE_SLAB;

    public static BlockType<BlockBehavior> CUT_SANDSTONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> CUT_SANDSTONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> CUT_SANDSTONE_SLAB;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> CYAN_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> CYAN_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> CYAN_CARPET;

    public static BlockType<BlockBehavior> CYAN_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> CYAN_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> CYAN_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> CYAN_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> CYAN_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> CYAN_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> CYAN_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> CYAN_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> CYAN_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> CYAN_TERRACOTTA;

    public static BlockType<BlockBehavior> CYAN_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> CYAN_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> CYAN_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> CYAN_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockAnvilBehavior> DAMAGED_ANVIL;

    public static BlockType<BlockSmallFlower> DANDELION;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> DARK_OAK_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> DARK_OAK_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> DARK_OAK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> DARK_OAK_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> DARK_OAK_FENCE_GATE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> DARK_OAK_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> DARK_OAK_LEAVES;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> DARK_OAK_LOG;

    public static BlockType<BlockBehavior> DARK_OAK_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> DARK_OAK_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     */
    public static BlockType<BlockBehavior> DARK_OAK_SAPLING;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> DARK_OAK_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> DARK_OAK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> DARK_OAK_STAIRS;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> DARK_OAK_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> DARK_OAK_WOOD;

    public static BlockType<BlockBehavior> DARK_PRISMARINE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> DARK_PRISMARINE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> DARK_PRISMARINE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> DARK_PRISMARINE_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> DARKOAK_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> DARKOAK_WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockDaylightDetectorBehavior> DAYLIGHT_DETECTOR;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockDaylightDetectorBehavior> DAYLIGHT_DETECTOR_INVERTED;

    public static BlockType<BlockBehavior> DEAD_BRAIN_CORAL;

    public static BlockType<BlockBehavior> DEAD_BRAIN_CORAL_BLOCK;

    /**
     * {@link BlockPropertyTypes#CORAL_FAN_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEAD_BRAIN_CORAL_FAN;

    /**
     * {@link BlockPropertyTypes#CORAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEAD_BRAIN_CORAL_WALL_FAN;

    public static BlockType<BlockBehavior> DEAD_BUBBLE_CORAL;

    public static BlockType<BlockBehavior> DEAD_BUBBLE_CORAL_BLOCK;

    /**
     * {@link BlockPropertyTypes#CORAL_FAN_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEAD_BUBBLE_CORAL_FAN;

    /**
     * {@link BlockPropertyTypes#CORAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEAD_BUBBLE_CORAL_WALL_FAN;

    public static BlockType<BlockBehavior> DEAD_FIRE_CORAL;

    public static BlockType<BlockBehavior> DEAD_FIRE_CORAL_BLOCK;

    /**
     * {@link BlockPropertyTypes#CORAL_FAN_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEAD_FIRE_CORAL_FAN;

    /**
     * {@link BlockPropertyTypes#CORAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEAD_FIRE_CORAL_WALL_FAN;

    public static BlockType<BlockBehavior> DEAD_HORN_CORAL;

    public static BlockType<BlockBehavior> DEAD_HORN_CORAL_BLOCK;

    /**
     * {@link BlockPropertyTypes#CORAL_FAN_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEAD_HORN_CORAL_FAN;

    /**
     * {@link BlockPropertyTypes#CORAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEAD_HORN_CORAL_WALL_FAN;

    public static BlockType<BlockBehavior> DEAD_TUBE_CORAL;

    public static BlockType<BlockBehavior> DEAD_TUBE_CORAL_BLOCK;

    /**
     * {@link BlockPropertyTypes#CORAL_FAN_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEAD_TUBE_CORAL_FAN;

    /**
     * {@link BlockPropertyTypes#CORAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEAD_TUBE_CORAL_WALL_FAN;

    public static BlockType<BlockBehavior> DEADBUSH;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     */
    public static BlockType<BlockDecoratedPotBehavior> DECORATED_POT;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> DEEPSLATE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> DEEPSLATE_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> DEEPSLATE_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> DEEPSLATE_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> DEEPSLATE_BRICK_WALL;

    public static BlockType<BlockBehavior> DEEPSLATE_BRICKS;

    public static BlockType<BlockOreBehavior> DEEPSLATE_COAL_ORE;

    public static BlockType<BlockOreBehavior> DEEPSLATE_COPPER_ORE;

    public static BlockType<BlockOreBehavior> DEEPSLATE_DIAMOND_ORE;

    public static BlockType<BlockOreBehavior> DEEPSLATE_EMERALD_ORE;

    public static BlockType<BlockOreBehavior> DEEPSLATE_GOLD_ORE;

    public static BlockType<BlockOreBehavior> DEEPSLATE_IRON_ORE;

    public static BlockType<BlockOreBehavior> DEEPSLATE_LAPIS_ORE;

    public static BlockType<BlockOreBehavior> DEEPSLATE_REDSTONE_ORE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> DEEPSLATE_TILE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> DEEPSLATE_TILE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> DEEPSLATE_TILE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> DEEPSLATE_TILE_WALL;

    public static BlockType<BlockBehavior> DEEPSLATE_TILES;

    public static BlockType<BlockBehavior> DENY;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> DEPRECATED_ANVIL;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> DEPRECATED_PURPUR_BLOCK_1;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> DEPRECATED_PURPUR_BLOCK_2;

    /**
     * {@link BlockPropertyTypes#RAIL_DATA_BIT}
     * {@link BlockPropertyTypes#RAIL_DIRECTION_6}
     */
    public static BlockType<BlockBehavior> DETECTOR_RAIL;

    public static BlockType<BlockBehavior> DIAMOND_BLOCK;

    public static BlockType<BlockOreBehavior> DIAMOND_ORE;

    public static BlockType<BlockBehavior> DIORITE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> DIORITE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> DIORITE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> DIORITE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> DIORITE_WALL;

    public static BlockType<BlockBehavior> DIRT;

    public static BlockType<BlockBehavior> DIRT_WITH_ROOTS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#TRIGGERED_BIT}
     */
    public static BlockType<BlockBehavior> DISPENSER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleCopperSlabBehavior> DOUBLE_CUT_COPPER_SLAB;

    public static BlockType<BlockDragonEggBehavior> DRAGON_EGG;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockHeadBehavior> DRAGON_HEAD;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#REHYDRATION_LEVEL}
     */
    public static BlockType<BlockBehavior> DRIED_GHAST;

    public static BlockType<BlockBehavior> DRIED_KELP_BLOCK;

    public static BlockType<BlockBehavior> DRIPSTONE_BLOCK;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#TRIGGERED_BIT}
     */
    public static BlockType<BlockBehavior> DROPPER;

    public static BlockType<BlockBehavior> ELEMENT_0;

    public static BlockType<BlockBehavior> ELEMENT_1;

    public static BlockType<BlockBehavior> ELEMENT_10;

    public static BlockType<BlockBehavior> ELEMENT_100;

    public static BlockType<BlockBehavior> ELEMENT_101;

    public static BlockType<BlockBehavior> ELEMENT_102;

    public static BlockType<BlockBehavior> ELEMENT_103;

    public static BlockType<BlockBehavior> ELEMENT_104;

    public static BlockType<BlockBehavior> ELEMENT_105;

    public static BlockType<BlockBehavior> ELEMENT_106;

    public static BlockType<BlockBehavior> ELEMENT_107;

    public static BlockType<BlockBehavior> ELEMENT_108;

    public static BlockType<BlockBehavior> ELEMENT_109;

    public static BlockType<BlockBehavior> ELEMENT_11;

    public static BlockType<BlockBehavior> ELEMENT_110;

    public static BlockType<BlockBehavior> ELEMENT_111;

    public static BlockType<BlockBehavior> ELEMENT_112;

    public static BlockType<BlockBehavior> ELEMENT_113;

    public static BlockType<BlockBehavior> ELEMENT_114;

    public static BlockType<BlockBehavior> ELEMENT_115;

    public static BlockType<BlockBehavior> ELEMENT_116;

    public static BlockType<BlockBehavior> ELEMENT_117;

    public static BlockType<BlockBehavior> ELEMENT_118;

    public static BlockType<BlockBehavior> ELEMENT_12;

    public static BlockType<BlockBehavior> ELEMENT_13;

    public static BlockType<BlockBehavior> ELEMENT_14;

    public static BlockType<BlockBehavior> ELEMENT_15;

    public static BlockType<BlockBehavior> ELEMENT_16;

    public static BlockType<BlockBehavior> ELEMENT_17;

    public static BlockType<BlockBehavior> ELEMENT_18;

    public static BlockType<BlockBehavior> ELEMENT_19;

    public static BlockType<BlockBehavior> ELEMENT_2;

    public static BlockType<BlockBehavior> ELEMENT_20;

    public static BlockType<BlockBehavior> ELEMENT_21;

    public static BlockType<BlockBehavior> ELEMENT_22;

    public static BlockType<BlockBehavior> ELEMENT_23;

    public static BlockType<BlockBehavior> ELEMENT_24;

    public static BlockType<BlockBehavior> ELEMENT_25;

    public static BlockType<BlockBehavior> ELEMENT_26;

    public static BlockType<BlockBehavior> ELEMENT_27;

    public static BlockType<BlockBehavior> ELEMENT_28;

    public static BlockType<BlockBehavior> ELEMENT_29;

    public static BlockType<BlockBehavior> ELEMENT_3;

    public static BlockType<BlockBehavior> ELEMENT_30;

    public static BlockType<BlockBehavior> ELEMENT_31;

    public static BlockType<BlockBehavior> ELEMENT_32;

    public static BlockType<BlockBehavior> ELEMENT_33;

    public static BlockType<BlockBehavior> ELEMENT_34;

    public static BlockType<BlockBehavior> ELEMENT_35;

    public static BlockType<BlockBehavior> ELEMENT_36;

    public static BlockType<BlockBehavior> ELEMENT_37;

    public static BlockType<BlockBehavior> ELEMENT_38;

    public static BlockType<BlockBehavior> ELEMENT_39;

    public static BlockType<BlockBehavior> ELEMENT_4;

    public static BlockType<BlockBehavior> ELEMENT_40;

    public static BlockType<BlockBehavior> ELEMENT_41;

    public static BlockType<BlockBehavior> ELEMENT_42;

    public static BlockType<BlockBehavior> ELEMENT_43;

    public static BlockType<BlockBehavior> ELEMENT_44;

    public static BlockType<BlockBehavior> ELEMENT_45;

    public static BlockType<BlockBehavior> ELEMENT_46;

    public static BlockType<BlockBehavior> ELEMENT_47;

    public static BlockType<BlockBehavior> ELEMENT_48;

    public static BlockType<BlockBehavior> ELEMENT_49;

    public static BlockType<BlockBehavior> ELEMENT_5;

    public static BlockType<BlockBehavior> ELEMENT_50;

    public static BlockType<BlockBehavior> ELEMENT_51;

    public static BlockType<BlockBehavior> ELEMENT_52;

    public static BlockType<BlockBehavior> ELEMENT_53;

    public static BlockType<BlockBehavior> ELEMENT_54;

    public static BlockType<BlockBehavior> ELEMENT_55;

    public static BlockType<BlockBehavior> ELEMENT_56;

    public static BlockType<BlockBehavior> ELEMENT_57;

    public static BlockType<BlockBehavior> ELEMENT_58;

    public static BlockType<BlockBehavior> ELEMENT_59;

    public static BlockType<BlockBehavior> ELEMENT_6;

    public static BlockType<BlockBehavior> ELEMENT_60;

    public static BlockType<BlockBehavior> ELEMENT_61;

    public static BlockType<BlockBehavior> ELEMENT_62;

    public static BlockType<BlockBehavior> ELEMENT_63;

    public static BlockType<BlockBehavior> ELEMENT_64;

    public static BlockType<BlockBehavior> ELEMENT_65;

    public static BlockType<BlockBehavior> ELEMENT_66;

    public static BlockType<BlockBehavior> ELEMENT_67;

    public static BlockType<BlockBehavior> ELEMENT_68;

    public static BlockType<BlockBehavior> ELEMENT_69;

    public static BlockType<BlockBehavior> ELEMENT_7;

    public static BlockType<BlockBehavior> ELEMENT_70;

    public static BlockType<BlockBehavior> ELEMENT_71;

    public static BlockType<BlockBehavior> ELEMENT_72;

    public static BlockType<BlockBehavior> ELEMENT_73;

    public static BlockType<BlockBehavior> ELEMENT_74;

    public static BlockType<BlockBehavior> ELEMENT_75;

    public static BlockType<BlockBehavior> ELEMENT_76;

    public static BlockType<BlockBehavior> ELEMENT_77;

    public static BlockType<BlockBehavior> ELEMENT_78;

    public static BlockType<BlockBehavior> ELEMENT_79;

    public static BlockType<BlockBehavior> ELEMENT_8;

    public static BlockType<BlockBehavior> ELEMENT_80;

    public static BlockType<BlockBehavior> ELEMENT_81;

    public static BlockType<BlockBehavior> ELEMENT_82;

    public static BlockType<BlockBehavior> ELEMENT_83;

    public static BlockType<BlockBehavior> ELEMENT_84;

    public static BlockType<BlockBehavior> ELEMENT_85;

    public static BlockType<BlockBehavior> ELEMENT_86;

    public static BlockType<BlockBehavior> ELEMENT_87;

    public static BlockType<BlockBehavior> ELEMENT_88;

    public static BlockType<BlockBehavior> ELEMENT_89;

    public static BlockType<BlockBehavior> ELEMENT_9;

    public static BlockType<BlockBehavior> ELEMENT_90;

    public static BlockType<BlockBehavior> ELEMENT_91;

    public static BlockType<BlockBehavior> ELEMENT_92;

    public static BlockType<BlockBehavior> ELEMENT_93;

    public static BlockType<BlockBehavior> ELEMENT_94;

    public static BlockType<BlockBehavior> ELEMENT_95;

    public static BlockType<BlockBehavior> ELEMENT_96;

    public static BlockType<BlockBehavior> ELEMENT_97;

    public static BlockType<BlockBehavior> ELEMENT_98;

    public static BlockType<BlockBehavior> ELEMENT_99;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     */
    public static BlockType<BlockBehavior> ELEMENT_CONSTRUCTOR;

    public static BlockType<BlockBehavior> EMERALD_BLOCK;

    public static BlockType<BlockOreBehavior> EMERALD_ORE;

    public static BlockType<BlockEnchantingTableBehavior> ENCHANTING_TABLE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> END_BRICK_STAIRS;

    public static BlockType<BlockBehavior> END_BRICKS;

    public static BlockType<BlockBehavior> END_GATEWAY;

    public static BlockType<BlockBehavior> END_PORTAL;

    /**
     * {@link BlockPropertyTypes#END_PORTAL_EYE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> END_PORTAL_FRAME;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> END_ROD;

    public static BlockType<BlockBehavior> END_STONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> END_STONE_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> END_STONE_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> END_STONE_BRICK_WALL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockEnderChestBehavior> ENDER_CHEST;

    public static BlockType<BlockCopperBehavior> EXPOSED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> EXPOSED_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockCopperBarsBehavior> EXPOSED_COPPER_BARS;

    /**
     * {@link BlockPropertyTypes#LIT}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockCopperBulbBehavior> EXPOSED_COPPER_BULB;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockCopperChainBehavior> EXPOSED_COPPER_CHAIN;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> EXPOSED_COPPER_CHEST;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockCopperDoorBehavior> EXPOSED_COPPER_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockCopperGolemStatueBehavior> EXPOSED_COPPER_GOLEM_STATUE;

    public static BlockType<BlockCopperGrateBehavior> EXPOSED_COPPER_GRATE;

    /**
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockCopperLanternBehavior> EXPOSED_COPPER_LANTERN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockCopperTrapdoorBehavior> EXPOSED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> EXPOSED_CUT_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockCopperSlabBehavior> EXPOSED_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockCopperStairsBehavior> EXPOSED_CUT_COPPER_STAIRS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleCopperSlabBehavior> EXPOSED_DOUBLE_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockLightningRodBehavior> EXPOSED_LIGHTNING_ROD;

    /**
     * {@link BlockPropertyTypes#MOISTURIZED_AMOUNT}
     */
    public static BlockType<BlockBehavior> FARMLAND;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> FENCE_GATE;

    public static BlockType<BlockBehavior> FERN;

    /**
     * {@link BlockPropertyTypes#AGE_16}
     */
    public static BlockType<BlockBehavior> FIRE;

    public static BlockType<BlockBehavior> FIRE_CORAL;

    public static BlockType<BlockBehavior> FIRE_CORAL_BLOCK;

    /**
     * {@link BlockPropertyTypes#CORAL_FAN_DIRECTION}
     */
    public static BlockType<BlockBehavior> FIRE_CORAL_FAN;

    /**
     * {@link BlockPropertyTypes#CORAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> FIRE_CORAL_WALL_FAN;

    public static BlockType<BlockBehavior> FIREFLY_BUSH;

    public static BlockType<BlockBehavior> FLETCHING_TABLE;

    /**
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockFlowerPotBehavior> FLOWER_POT;

    public static BlockType<BlockBehavior> FLOWERING_AZALEA;

    /**
     * {@link BlockPropertyTypes#LIQUID_DEPTH}
     */
    public static BlockType<BlockLiquidBehavior> FLOWING_LAVA;

    /**
     * {@link BlockPropertyTypes#LIQUID_DEPTH}
     */
    public static BlockType<BlockLiquidBehavior> FLOWING_WATER;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#ITEM_FRAME_MAP_BIT}
     * {@link BlockPropertyTypes#ITEM_FRAME_PHOTO_BIT}
     */
    public static BlockType<BlockBehavior> FRAME;

    public static BlockType<BlockBehavior> FROG_SPAWN;

    /**
     * {@link BlockPropertyTypes#AGE_4}
     */
    public static BlockType<BlockBehavior> FROSTED_ICE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockFurnaceBehavior> FURNACE;

    public static BlockType<BlockBehavior> GILDED_BLACKSTONE;

    public static BlockType<BlockGlassBehavior> GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> GLASS_PANE;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#ITEM_FRAME_MAP_BIT}
     * {@link BlockPropertyTypes#ITEM_FRAME_PHOTO_BIT}
     */
    public static BlockType<BlockBehavior> GLOW_FRAME;

    /**
     * {@link BlockPropertyTypes#MULTI_FACE_DIRECTION_BITS}
     */
    public static BlockType<BlockBehavior> GLOW_LICHEN;

    public static BlockType<BlockBehavior> GLOWINGOBSIDIAN;

    public static BlockType<BlockBehavior> GLOWSTONE;

    public static BlockType<BlockBehavior> GOLD_BLOCK;

    public static BlockType<BlockOreBehavior> GOLD_ORE;

    public static BlockType<BlockSmallFlower> GOLDEN_DANDELION;

    /**
     * {@link BlockPropertyTypes#RAIL_DATA_BIT}
     * {@link BlockPropertyTypes#RAIL_DIRECTION_6}
     */
    public static BlockType<BlockBehavior> GOLDEN_RAIL;

    public static BlockType<BlockBehavior> GRANITE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> GRANITE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> GRANITE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> GRANITE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> GRANITE_WALL;

    public static BlockType<BlockBehavior> GRASS_BLOCK;

    public static BlockType<BlockBehavior> GRASS_PATH;

    public static BlockType<BlockGravelBehavior> GRAVEL;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> GRAY_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> GRAY_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> GRAY_CARPET;

    public static BlockType<BlockBehavior> GRAY_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> GRAY_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> GRAY_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> GRAY_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> GRAY_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> GRAY_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> GRAY_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> GRAY_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> GRAY_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> GRAY_TERRACOTTA;

    public static BlockType<BlockBehavior> GRAY_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> GRAY_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> GRAY_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> GRAY_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> GREEN_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> GREEN_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> GREEN_CARPET;

    public static BlockType<BlockBehavior> GREEN_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> GREEN_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> GREEN_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> GREEN_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> GREEN_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> GREEN_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> GREEN_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> GREEN_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> GREEN_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> GREEN_TERRACOTTA;

    public static BlockType<BlockBehavior> GREEN_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> GREEN_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> GREEN_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> GREEN_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#ATTACHMENT}
     * {@link BlockPropertyTypes#DIRECTION_4}
     */
    public static BlockType<BlockBehavior> GRINDSTONE;

    public static BlockType<BlockBehavior> HANGING_ROOTS;

    public static BlockType<BlockGlassBehavior> HARD_BLACK_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_BLACK_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_BLUE_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_BLUE_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_BROWN_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_BROWN_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_CYAN_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_CYAN_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_GRAY_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_GRAY_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_GREEN_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_GREEN_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_LIGHT_BLUE_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_LIGHT_BLUE_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_LIGHT_GRAY_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_LIGHT_GRAY_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_LIME_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_LIME_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_MAGENTA_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_MAGENTA_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_ORANGE_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_ORANGE_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_PINK_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_PINK_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_PURPLE_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_PURPLE_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_RED_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_RED_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_WHITE_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_WHITE_STAINED_GLASS_PANE;

    public static BlockType<BlockGlassBehavior> HARD_YELLOW_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> HARD_YELLOW_STAINED_GLASS_PANE;

    public static BlockType<BlockBehavior> HARDENED_CLAY;

    /**
     * {@link BlockPropertyTypes#DEPRECATED}
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> HAY_BLOCK;

    public static BlockType<BlockBehavior> HEAVY_CORE;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> HEAVY_WEIGHTED_PRESSURE_PLATE;

    public static BlockType<BlockBehavior> HONEY_BLOCK;

    public static BlockType<BlockBehavior> HONEYCOMB_BLOCK;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#TOGGLE_BIT}
     */
    public static BlockType<BlockBehavior> HOPPER;

    public static BlockType<BlockBehavior> HORN_CORAL;

    public static BlockType<BlockBehavior> HORN_CORAL_BLOCK;

    /**
     * {@link BlockPropertyTypes#CORAL_FAN_DIRECTION}
     */
    public static BlockType<BlockBehavior> HORN_CORAL_FAN;

    /**
     * {@link BlockPropertyTypes#CORAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> HORN_CORAL_WALL_FAN;

    public static BlockType<BlockBehavior> ICE;

    public static BlockType<BlockInfestedBlockBehavior> INFESTED_CHISELED_STONE_BRICKS;

    public static BlockType<BlockInfestedBlockBehavior> INFESTED_COBBLESTONE;

    public static BlockType<BlockInfestedBlockBehavior> INFESTED_CRACKED_STONE_BRICKS;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockInfestedBlockBehavior> INFESTED_DEEPSLATE;

    public static BlockType<BlockInfestedBlockBehavior> INFESTED_MOSSY_STONE_BRICKS;

    public static BlockType<BlockInfestedBlockBehavior> INFESTED_STONE;

    public static BlockType<BlockInfestedBlockBehavior> INFESTED_STONE_BRICKS;

    public static BlockType<BlockBehavior> INFO_UPDATE;

    public static BlockType<BlockBehavior> INFO_UPDATE2;

    public static BlockType<BlockBehavior> INVISIBLE_BEDROCK;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockIronBarsBehavior> IRON_BARS;

    public static BlockType<BlockBehavior> IRON_BLOCK;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> IRON_CHAIN;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> IRON_DOOR;

    public static BlockType<BlockOreBehavior> IRON_ORE;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockBehavior> IRON_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#ROTATION}
     */
    public static BlockType<BlockBehavior> JIGSAW;

    public static BlockType<BlockJukeboxBehavior> JUKEBOX;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> JUNGLE_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> JUNGLE_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> JUNGLE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> JUNGLE_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> JUNGLE_FENCE_GATE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> JUNGLE_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> JUNGLE_LEAVES;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> JUNGLE_LOG;

    public static BlockType<BlockBehavior> JUNGLE_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> JUNGLE_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     */
    public static BlockType<BlockBehavior> JUNGLE_SAPLING;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> JUNGLE_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> JUNGLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> JUNGLE_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> JUNGLE_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> JUNGLE_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> JUNGLE_WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> JUNGLE_WOOD;

    /**
     * {@link BlockPropertyTypes#KELP_AGE}
     */
    public static BlockType<BlockBehavior> KELP;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     */
    public static BlockType<BlockBehavior> LAB_TABLE;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> LADDER;

    /**
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockLanternBehavior> LANTERN;

    public static BlockType<BlockBehavior> LAPIS_BLOCK;

    public static BlockType<BlockOreBehavior> LAPIS_ORE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_BLOCK_FACE}
     */
    public static BlockType<BlockAmethystClusterBehavior> LARGE_AMETHYST_BUD;

    /**
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> LARGE_FERN;

    /**
     * {@link BlockPropertyTypes#LIQUID_DEPTH}
     */
    public static BlockType<BlockLiquidBehavior> LAVA;

    /**
     * {@link BlockPropertyTypes#GROWTH_8}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockPlantPile> LEAF_LITTER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockLecternBehavior> LECTERN;

    /**
     * {@link BlockPropertyTypes#LEVER_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockBehavior> LEVER;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_0;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_1;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_10;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_11;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_12;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_13;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_14;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_15;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_2;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_3;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_4;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_5;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_6;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_7;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_8;

    public static BlockType<BlockBehavior> LIGHT_BLOCK_9;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> LIGHT_BLUE_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> LIGHT_BLUE_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> LIGHT_BLUE_CARPET;

    public static BlockType<BlockBehavior> LIGHT_BLUE_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> LIGHT_BLUE_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> LIGHT_BLUE_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> LIGHT_BLUE_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> LIGHT_BLUE_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> LIGHT_BLUE_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> LIGHT_BLUE_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> LIGHT_BLUE_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> LIGHT_BLUE_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> LIGHT_BLUE_TERRACOTTA;

    public static BlockType<BlockBehavior> LIGHT_BLUE_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> LIGHT_BLUE_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> LIGHT_BLUE_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> LIGHT_BLUE_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> LIGHT_GRAY_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> LIGHT_GRAY_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> LIGHT_GRAY_CARPET;

    public static BlockType<BlockBehavior> LIGHT_GRAY_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> LIGHT_GRAY_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> LIGHT_GRAY_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> LIGHT_GRAY_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> LIGHT_GRAY_CONCRETE_STAIRS;

    public static BlockType<BlockShulkerBoxBehavior> LIGHT_GRAY_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> LIGHT_GRAY_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> LIGHT_GRAY_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> LIGHT_GRAY_TERRACOTTA;

    public static BlockType<BlockBehavior> LIGHT_GRAY_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> LIGHT_GRAY_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> LIGHT_GRAY_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> LIGHT_GRAY_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> LIGHT_WEIGHTED_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockLightningRodBehavior> LIGHTNING_ROD;

    /**
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBigFlower> LILAC;

    public static BlockType<BlockSmallFlower> LILY_OF_THE_VALLEY;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> LIME_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> LIME_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> LIME_CARPET;

    public static BlockType<BlockBehavior> LIME_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> LIME_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> LIME_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> LIME_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> LIME_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> LIME_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> LIME_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> LIME_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> LIME_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> LIME_TERRACOTTA;

    public static BlockType<BlockBehavior> LIME_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> LIME_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> LIME_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> LIME_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBlastFurnaceBehavior> LIT_BLAST_FURNACE;

    public static BlockType<BlockOreBehavior> LIT_DEEPSLATE_REDSTONE_ORE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockFurnaceBehavior> LIT_FURNACE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> LIT_PUMPKIN;

    public static BlockType<BlockRedstoneLampBehavior> LIT_REDSTONE_LAMP;

    public static BlockType<BlockOreBehavior> LIT_REDSTONE_ORE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockSmokerBehavior> LIT_SMOKER;

    public static BlockType<BlockBehavior> LODESTONE;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     */
    public static BlockType<BlockBehavior> LOOM;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> MAGENTA_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> MAGENTA_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> MAGENTA_CARPET;

    public static BlockType<BlockBehavior> MAGENTA_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> MAGENTA_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> MAGENTA_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> MAGENTA_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> MAGENTA_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> MAGENTA_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> MAGENTA_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> MAGENTA_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> MAGENTA_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> MAGENTA_TERRACOTTA;

    public static BlockType<BlockBehavior> MAGENTA_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> MAGENTA_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> MAGENTA_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> MAGENTA_WOOL_STAIRS;

    public static BlockType<BlockBehavior> MAGMA;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> MANGROVE_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> MANGROVE_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> MANGROVE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> MANGROVE_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> MANGROVE_FENCE_GATE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> MANGROVE_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> MANGROVE_LEAVES;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> MANGROVE_LOG;

    public static BlockType<BlockBehavior> MANGROVE_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> MANGROVE_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#HANGING}
     * {@link BlockPropertyTypes#PROPAGULE_STAGE}
     */
    public static BlockType<BlockBehavior> MANGROVE_PROPAGULE;

    public static BlockType<BlockBehavior> MANGROVE_ROOTS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> MANGROVE_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> MANGROVE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> MANGROVE_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> MANGROVE_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> MANGROVE_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> MANGROVE_WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> MANGROVE_WOOD;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     */
    public static BlockType<BlockBehavior> MATERIAL_REDUCER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_BLOCK_FACE}
     */
    public static BlockType<BlockAmethystClusterBehavior> MEDIUM_AMETHYST_BUD;

    public static BlockType<BlockBehavior> MELON_BLOCK;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROWTH_8}
     */
    public static BlockType<BlockBehavior> MELON_STEM;

    public static BlockType<BlockBehavior> MOB_SPAWNER;

    public static BlockType<BlockBehavior> MOSS_BLOCK;

    public static BlockType<BlockCarpetBehavior> MOSS_CARPET;

    public static BlockType<BlockBehavior> MOSSY_COBBLESTONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> MOSSY_COBBLESTONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> MOSSY_COBBLESTONE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> MOSSY_COBBLESTONE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> MOSSY_COBBLESTONE_WALL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> MOSSY_STONE_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> MOSSY_STONE_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> MOSSY_STONE_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> MOSSY_STONE_BRICK_WALL;

    public static BlockType<BlockBehavior> MOSSY_STONE_BRICKS;

    public static BlockType<BlockBehavior> MOVING_BLOCK;

    public static BlockType<BlockBehavior> MUD;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> MUD_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> MUD_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> MUD_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> MUD_BRICK_WALL;

    public static BlockType<BlockBehavior> MUD_BRICKS;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> MUDDY_MANGROVE_ROOTS;

    /**
     * {@link BlockPropertyTypes#HUGE_MUSHROOM_BITS}
     */
    public static BlockType<BlockBehavior> MUSHROOM_STEM;

    public static BlockType<BlockBehavior> MYCELIUM;

    public static BlockType<BlockBehavior> NETHER_BRICK;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> NETHER_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> NETHER_BRICK_FENCE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> NETHER_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> NETHER_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> NETHER_BRICK_WALL;

    public static BlockType<BlockOreBehavior> NETHER_GOLD_ORE;

    public static BlockType<BlockBehavior> NETHER_SPROUTS;

    /**
     * {@link BlockPropertyTypes#AGE_4}
     */
    public static BlockType<BlockBehavior> NETHER_WART;

    public static BlockType<BlockBehavior> NETHER_WART_BLOCK;

    public static BlockType<BlockBehavior> NETHERITE_BLOCK;

    public static BlockType<BlockBehavior> NETHERRACK;

    public static BlockType<BlockBehavior> NETHERREACTOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> NORMAL_STONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> NORMAL_STONE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> NORMAL_STONE_STAIRS;

    public static BlockType<BlockNoteblockBehavior> NOTEBLOCK;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> OAK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> OAK_FENCE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> OAK_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> OAK_LEAVES;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> OAK_LOG;

    public static BlockType<BlockBehavior> OAK_PLANKS;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     */
    public static BlockType<BlockBehavior> OAK_SAPLING;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> OAK_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> OAK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> OAK_STAIRS;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> OAK_WOOD;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_FACING_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockBehavior> OBSERVER;

    public static BlockType<BlockBehavior> OBSIDIAN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> OCHRE_FROGLIGHT;

    public static BlockType<BlockSmallFlower> OPEN_EYEBLOSSOM;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> ORANGE_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> ORANGE_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> ORANGE_CARPET;

    public static BlockType<BlockBehavior> ORANGE_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> ORANGE_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> ORANGE_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> ORANGE_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> ORANGE_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> ORANGE_GLAZED_TERRACOTTA;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> ORANGE_POPLAR_LEAVES;

    public static BlockType<BlockShulkerBoxBehavior> ORANGE_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> ORANGE_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> ORANGE_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> ORANGE_TERRACOTTA;

    public static BlockType<BlockSmallFlower> ORANGE_TULIP;

    public static BlockType<BlockBehavior> ORANGE_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> ORANGE_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> ORANGE_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> ORANGE_WOOL_STAIRS;

    public static BlockType<BlockSmallFlower> OXEYE_DAISY;

    public static BlockType<BlockCopperBehavior> OXIDIZED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> OXIDIZED_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockCopperBarsBehavior> OXIDIZED_COPPER_BARS;

    /**
     * {@link BlockPropertyTypes#LIT}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockCopperBulbBehavior> OXIDIZED_COPPER_BULB;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockCopperChainBehavior> OXIDIZED_COPPER_CHAIN;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> OXIDIZED_COPPER_CHEST;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockCopperDoorBehavior> OXIDIZED_COPPER_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockCopperGolemStatueBehavior> OXIDIZED_COPPER_GOLEM_STATUE;

    public static BlockType<BlockCopperGrateBehavior> OXIDIZED_COPPER_GRATE;

    /**
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockCopperLanternBehavior> OXIDIZED_COPPER_LANTERN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockCopperTrapdoorBehavior> OXIDIZED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> OXIDIZED_CUT_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockCopperSlabBehavior> OXIDIZED_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockCopperStairsBehavior> OXIDIZED_CUT_COPPER_STAIRS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleCopperSlabBehavior> OXIDIZED_DOUBLE_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockLightningRodBehavior> OXIDIZED_LIGHTNING_ROD;

    public static BlockType<BlockBehavior> PACKED_ICE;

    public static BlockType<BlockBehavior> PACKED_MUD;

    /**
     * {@link BlockPropertyTypes#TIP}
     */
    public static BlockType<BlockBehavior> PALE_HANGING_MOSS;

    public static BlockType<BlockBehavior> PALE_MOSS_BLOCK;

    /**
     * {@link BlockPropertyTypes#PALE_MOSS_CARPET_SIDE_EAST}
     * {@link BlockPropertyTypes#PALE_MOSS_CARPET_SIDE_NORTH}
     * {@link BlockPropertyTypes#PALE_MOSS_CARPET_SIDE_SOUTH}
     * {@link BlockPropertyTypes#PALE_MOSS_CARPET_SIDE_WEST}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockCarpetBehavior> PALE_MOSS_CARPET;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> PALE_OAK_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> PALE_OAK_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> PALE_OAK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> PALE_OAK_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> PALE_OAK_FENCE_GATE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> PALE_OAK_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> PALE_OAK_LEAVES;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> PALE_OAK_LOG;

    public static BlockType<BlockBehavior> PALE_OAK_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> PALE_OAK_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     */
    public static BlockType<BlockBehavior> PALE_OAK_SAPLING;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> PALE_OAK_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> PALE_OAK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> PALE_OAK_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> PALE_OAK_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> PALE_OAK_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> PALE_OAK_WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> PALE_OAK_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> PEARLESCENT_FROGLIGHT;

    /**
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBigFlower> PEONY;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> PETRIFIED_OAK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> PETRIFIED_OAK_SLAB;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockHeadBehavior> PIGLIN_HEAD;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> PINK_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> PINK_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> PINK_CARPET;

    public static BlockType<BlockBehavior> PINK_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> PINK_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> PINK_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> PINK_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> PINK_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> PINK_GLAZED_TERRACOTTA;

    /**
     * {@link BlockPropertyTypes#GROWTH_8}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockPlantPile> PINK_PETALS;

    public static BlockType<BlockShulkerBoxBehavior> PINK_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> PINK_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> PINK_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> PINK_TERRACOTTA;

    public static BlockType<BlockSmallFlower> PINK_TULIP;

    public static BlockType<BlockBehavior> PINK_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> PINK_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> PINK_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> PINK_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> PISTON;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> PISTON_ARM_COLLISION;

    /**
     * {@link BlockPropertyTypes#GROWTH_8}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> PITCHER_CROP;

    /**
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBigFlower> PITCHER_PLANT;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockHeadBehavior> PLAYER_HEAD;

    public static BlockType<BlockBehavior> PODZOL;

    /**
     * {@link BlockPropertyTypes#DRIPSTONE_THICKNESS}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockPointedDripstoneBehavior> POINTED_DRIPSTONE;

    public static BlockType<BlockBehavior> POLISHED_ANDESITE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> POLISHED_ANDESITE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> POLISHED_ANDESITE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> POLISHED_ANDESITE_STAIRS;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> POLISHED_BASALT;

    public static BlockType<BlockBehavior> POLISHED_BLACKSTONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> POLISHED_BLACKSTONE_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> POLISHED_BLACKSTONE_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> POLISHED_BLACKSTONE_BRICK_WALL;

    public static BlockType<BlockBehavior> POLISHED_BLACKSTONE_BRICKS;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> POLISHED_BLACKSTONE_BUTTON;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> POLISHED_BLACKSTONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> POLISHED_BLACKSTONE_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> POLISHED_BLACKSTONE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> POLISHED_BLACKSTONE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> POLISHED_BLACKSTONE_WALL;

    public static BlockType<BlockBehavior> POLISHED_CINNABAR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> POLISHED_CINNABAR_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> POLISHED_CINNABAR_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> POLISHED_CINNABAR_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> POLISHED_CINNABAR_WALL;

    public static BlockType<BlockBehavior> POLISHED_DEEPSLATE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> POLISHED_DEEPSLATE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> POLISHED_DEEPSLATE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> POLISHED_DEEPSLATE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> POLISHED_DEEPSLATE_WALL;

    public static BlockType<BlockBehavior> POLISHED_DIORITE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> POLISHED_DIORITE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> POLISHED_DIORITE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> POLISHED_DIORITE_STAIRS;

    public static BlockType<BlockBehavior> POLISHED_GRANITE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> POLISHED_GRANITE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> POLISHED_GRANITE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> POLISHED_GRANITE_STAIRS;

    public static BlockType<BlockBehavior> POLISHED_SULFUR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> POLISHED_SULFUR_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> POLISHED_SULFUR_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> POLISHED_SULFUR_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> POLISHED_SULFUR_WALL;

    public static BlockType<BlockBehavior> POLISHED_TUFF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> POLISHED_TUFF_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> POLISHED_TUFF_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> POLISHED_TUFF_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> POLISHED_TUFF_WALL;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> POPLAR_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> POPLAR_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> POPLAR_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> POPLAR_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> POPLAR_FENCE_GATE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> POPLAR_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> POPLAR_LOG;

    public static BlockType<BlockBehavior> POPLAR_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> POPLAR_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     */
    public static BlockType<BlockBehavior> POPLAR_SAPLING;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> POPLAR_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> POPLAR_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> POPLAR_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> POPLAR_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> POPLAR_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> POPLAR_WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> POPLAR_WOOD;

    public static BlockType<BlockSmallFlower> POPPY;

    /**
     * {@link BlockPropertyTypes#PORTAL_AXIS}
     */
    public static BlockType<BlockBehavior> PORTAL;

    /**
     * {@link BlockPropertyTypes#GROWTH_8}
     */
    public static BlockType<BlockBehavior> POTATOES;

    /**
     * {@link BlockPropertyTypes#POTENT_SULFUR_STATE}
     */
    public static BlockType<BlockBehavior> POTENT_SULFUR;

    public static BlockType<BlockBehavior> POWDER_SNOW;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OUTPUT_LIT_BIT}
     * {@link BlockPropertyTypes#OUTPUT_SUBTRACT_BIT}
     */
    public static BlockType<BlockComparatorBehavior> POWERED_COMPARATOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#REPEATER_DELAY}
     */
    public static BlockType<BlockRepeaterBehavior> POWERED_REPEATER;

    public static BlockType<BlockBehavior> PRISMARINE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> PRISMARINE_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> PRISMARINE_BRICK_SLAB;

    public static BlockType<BlockBehavior> PRISMARINE_BRICKS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> PRISMARINE_BRICKS_STAIRS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> PRISMARINE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> PRISMARINE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> PRISMARINE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> PRISMARINE_WALL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> PUMPKIN;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROWTH_8}
     */
    public static BlockType<BlockBehavior> PUMPKIN_STEM;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> PURPLE_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> PURPLE_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> PURPLE_CARPET;

    public static BlockType<BlockBehavior> PURPLE_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> PURPLE_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> PURPLE_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> PURPLE_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> PURPLE_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> PURPLE_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> PURPLE_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> PURPLE_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> PURPLE_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> PURPLE_TERRACOTTA;

    public static BlockType<BlockBehavior> PURPLE_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> PURPLE_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> PURPLE_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> PURPLE_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> PURPUR_BLOCK;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> PURPUR_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> PURPUR_PILLAR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> PURPUR_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> PURPUR_STAIRS;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> QUARTZ_BLOCK;

    public static BlockType<BlockBehavior> QUARTZ_BRICKS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> QUARTZ_DOUBLE_SLAB;

    public static BlockType<BlockOreBehavior> QUARTZ_ORE;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> QUARTZ_PILLAR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> QUARTZ_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> QUARTZ_STAIRS;

    /**
     * {@link BlockPropertyTypes#RAIL_DIRECTION_10}
     */
    public static BlockType<BlockBehavior> RAIL;

    public static BlockType<BlockBehavior> RAW_COPPER_BLOCK;

    public static BlockType<BlockBehavior> RAW_GOLD_BLOCK;

    public static BlockType<BlockBehavior> RAW_IRON_BLOCK;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> RED_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> RED_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> RED_CARPET;

    public static BlockType<BlockBehavior> RED_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> RED_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> RED_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> RED_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> RED_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> RED_GLAZED_TERRACOTTA;

    public static BlockType<BlockBehavior> RED_MUSHROOM;

    /**
     * {@link BlockPropertyTypes#HUGE_MUSHROOM_BITS}
     */
    public static BlockType<BlockBehavior> RED_MUSHROOM_BLOCK;

    public static BlockType<BlockBehavior> RED_NETHER_BRICK;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> RED_NETHER_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> RED_NETHER_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> RED_NETHER_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> RED_NETHER_BRICK_WALL;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> RED_POPLAR_LEAVES;

    public static BlockType<BlockRedSandBehavior> RED_SAND;

    public static BlockType<BlockBehavior> RED_SANDSTONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> RED_SANDSTONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> RED_SANDSTONE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> RED_SANDSTONE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> RED_SANDSTONE_WALL;

    public static BlockType<BlockBehavior> RED_SHRUB;

    public static BlockType<BlockShulkerBoxBehavior> RED_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> RED_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> RED_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> RED_TERRACOTTA;

    public static BlockType<BlockSmallFlower> RED_TULIP;

    public static BlockType<BlockBehavior> RED_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> RED_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> RED_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> RED_WOOL_STAIRS;

    public static BlockType<BlockBehavior> REDSTONE_BLOCK;

    public static BlockType<BlockRedstoneLampBehavior> REDSTONE_LAMP;

    public static BlockType<BlockOreBehavior> REDSTONE_ORE;

    /**
     * {@link BlockPropertyTypes#TORCH_FACING_DIRECTION}
     */
    public static BlockType<BlockTorchBehavior> REDSTONE_TORCH;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> REDSTONE_WIRE;

    /**
     * {@link BlockPropertyTypes#AGE_16}
     */
    public static BlockType<BlockBehavior> REEDS;

    public static BlockType<BlockBehavior> REINFORCED_DEEPSLATE;

    /**
     * {@link BlockPropertyTypes#CONDITIONAL_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> REPEATING_COMMAND_BLOCK;

    public static BlockType<BlockBehavior> RESERVED6;

    public static BlockType<BlockBehavior> RESIN_BLOCK;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> RESIN_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> RESIN_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> RESIN_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> RESIN_BRICK_WALL;

    public static BlockType<BlockBehavior> RESIN_BRICKS;

    /**
     * {@link BlockPropertyTypes#MULTI_FACE_DIRECTION_BITS}
     */
    public static BlockType<BlockBehavior> RESIN_CLUMP;

    /**
     * {@link BlockPropertyTypes#RESPAWN_ANCHOR_CHARGE}
     */
    public static BlockType<BlockRespawnAnchorBehavior> RESPAWN_ANCHOR;

    /**
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBigFlower> ROSE_BUSH;

    public static BlockType<BlockSandBehavior> SAND;

    public static BlockType<BlockBehavior> SANDSTONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> SANDSTONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> SANDSTONE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> SANDSTONE_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> SANDSTONE_WALL;

    /**
     * {@link BlockPropertyTypes#STABILITY}
     * {@link BlockPropertyTypes#STABILITY_CHECK}
     */
    public static BlockType<BlockScaffoldingBehavior> SCAFFOLDING;

    public static BlockType<BlockBehavior> SCULK;

    /**
     * {@link BlockPropertyTypes#BLOOM}
     */
    public static BlockType<BlockBehavior> SCULK_CATALYST;

    /**
     * {@link BlockPropertyTypes#SCULK_SENSOR_PHASE}
     */
    public static BlockType<BlockBehavior> SCULK_SENSOR;

    /**
     * {@link BlockPropertyTypes#ACTIVE}
     * {@link BlockPropertyTypes#CAN_SUMMON}
     */
    public static BlockType<BlockBehavior> SCULK_SHRIEKER;

    /**
     * {@link BlockPropertyTypes#MULTI_FACE_DIRECTION_BITS}
     */
    public static BlockType<BlockBehavior> SCULK_VEIN;

    public static BlockType<BlockBehavior> SEA_LANTERN;

    /**
     * {@link BlockPropertyTypes#CLUSTER_COUNT}
     * {@link BlockPropertyTypes#DEAD_BIT}
     */
    public static BlockType<BlockBehavior> SEA_PICKLE;

    /**
     * {@link BlockPropertyTypes#SEA_GRASS_TYPE}
     */
    public static BlockType<BlockBehavior> SEAGRASS;

    /**
     * {@link BlockPropertyTypes#GROWTH_2}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> SHELF_MUSHROOM;

    public static BlockType<BlockBehavior> SHORT_DRY_GRASS;

    public static BlockType<BlockBehavior> SHORT_GRASS;

    public static BlockType<BlockBehavior> SHROOMLIGHT;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> SILVER_GLAZED_TERRACOTTA;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockHeadBehavior> SKELETON_SKULL;

    public static BlockType<BlockBehavior> SLIME;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_BLOCK_FACE}
     */
    public static BlockType<BlockAmethystClusterBehavior> SMALL_AMETHYST_BUD;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> SMALL_DRIPLEAF_BLOCK;

    public static BlockType<BlockBehavior> SMITHING_TABLE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockSmokerBehavior> SMOKER;

    public static BlockType<BlockBehavior> SMOOTH_BASALT;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> SMOOTH_QUARTZ;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> SMOOTH_QUARTZ_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> SMOOTH_QUARTZ_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> SMOOTH_QUARTZ_STAIRS;

    public static BlockType<BlockBehavior> SMOOTH_RED_SANDSTONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> SMOOTH_RED_SANDSTONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> SMOOTH_RED_SANDSTONE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> SMOOTH_RED_SANDSTONE_STAIRS;

    public static BlockType<BlockBehavior> SMOOTH_SANDSTONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> SMOOTH_SANDSTONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> SMOOTH_SANDSTONE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> SMOOTH_SANDSTONE_STAIRS;

    public static BlockType<BlockBehavior> SMOOTH_STONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> SMOOTH_STONE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> SMOOTH_STONE_SLAB;

    /**
     * {@link BlockPropertyTypes#CRACKED_STATE}
     */
    public static BlockType<BlockBehavior> SNIFFER_EGG;

    public static BlockType<BlockBehavior> SNOW;

    /**
     * {@link BlockPropertyTypes#COVERED_BIT}
     * {@link BlockPropertyTypes#HEIGHT}
     */
    public static BlockType<BlockSnowLayerBehavior> SNOW_LAYER;

    /**
     * {@link BlockPropertyTypes#EXTINGUISHED}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> SOUL_CAMPFIRE;

    /**
     * {@link BlockPropertyTypes#AGE_16}
     */
    public static BlockType<BlockBehavior> SOUL_FIRE;

    /**
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockLanternBehavior> SOUL_LANTERN;

    public static BlockType<BlockBehavior> SOUL_SAND;

    public static BlockType<BlockBehavior> SOUL_SOIL;

    /**
     * {@link BlockPropertyTypes#TORCH_FACING_DIRECTION}
     */
    public static BlockType<BlockTorchBehavior> SOUL_TORCH;

    public static BlockType<BlockBehavior> SPONGE;

    public static BlockType<BlockBehavior> SPORE_BLOSSOM;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> SPRUCE_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> SPRUCE_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> SPRUCE_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> SPRUCE_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> SPRUCE_FENCE_GATE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> SPRUCE_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> SPRUCE_LEAVES;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> SPRUCE_LOG;

    public static BlockType<BlockBehavior> SPRUCE_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> SPRUCE_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#AGE_BIT}
     */
    public static BlockType<BlockBehavior> SPRUCE_SAPLING;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> SPRUCE_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> SPRUCE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> SPRUCE_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> SPRUCE_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> SPRUCE_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> SPRUCE_WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> SPRUCE_WOOD;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockStandingBannerBehavior> STANDING_BANNER;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> STICKY_PISTON;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> STICKY_PISTON_ARM_COLLISION;

    public static BlockType<BlockBehavior> STONE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> STONE_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> STONE_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> STONE_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> STONE_BRICK_WALL;

    public static BlockType<BlockBehavior> STONE_BRICKS;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> STONE_BUTTON;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> STONE_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> STONE_STAIRS;

    public static BlockType<BlockBehavior> STONECUTTER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> STONECUTTER_BLOCK;

    /**
     * {@link BlockPropertyTypes#HEAD_PIECE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OCCUPIED_BIT}
     */
    public static BlockType<BlockBehavior> STRAW_BED;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_ACACIA_LOG;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_ACACIA_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_BAMBOO_BLOCK;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_BIRCH_LOG;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_BIRCH_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_CHERRY_LOG;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_CHERRY_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_CRIMSON_HYPHAE;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_CRIMSON_STEM;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_DARK_OAK_LOG;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_DARK_OAK_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_JUNGLE_LOG;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_JUNGLE_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_MANGROVE_LOG;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_MANGROVE_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_OAK_LOG;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_OAK_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_PALE_OAK_LOG;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_PALE_OAK_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_POPLAR_LOG;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_POPLAR_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_SPRUCE_LOG;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_SPRUCE_WOOD;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_WARPED_HYPHAE;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> STRIPPED_WARPED_STEM;

    /**
     * {@link BlockPropertyTypes#STRUCTURE_BLOCK_TYPE}
     */
    public static BlockType<BlockBehavior> STRUCTURE_BLOCK;

    public static BlockType<BlockBehavior> STRUCTURE_VOID;

    public static BlockType<BlockBehavior> SULFUR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> SULFUR_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> SULFUR_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> SULFUR_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> SULFUR_BRICK_WALL;

    public static BlockType<BlockBehavior> SULFUR_BRICKS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> SULFUR_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> SULFUR_SLAB;

    /**
     * {@link BlockPropertyTypes#DRIPSTONE_THICKNESS}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockBehavior> SULFUR_SPIKE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> SULFUR_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> SULFUR_WALL;

    /**
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBigFlower> SUNFLOWER;

    /**
     * {@link BlockPropertyTypes#BRUSHED_PROGRESS}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockSuspiciousGravelBehavior> SUSPICIOUS_GRAVEL;

    /**
     * {@link BlockPropertyTypes#BRUSHED_PROGRESS}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockSuspiciousSandBehavior> SUSPICIOUS_SAND;

    /**
     * {@link BlockPropertyTypes#GROWTH_8}
     */
    public static BlockType<BlockBehavior> SWEET_BERRY_BUSH;

    public static BlockType<BlockBehavior> TALL_DRY_GRASS;

    /**
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> TALL_GRASS;

    public static BlockType<BlockTargetBehavior> TARGET;

    public static BlockType<BlockGlassBehavior> TINTED_GLASS;

    /**
     * {@link BlockPropertyTypes#EXPLODE_BIT}
     */
    public static BlockType<BlockTntBehavior> TNT;

    /**
     * {@link BlockPropertyTypes#TORCH_FACING_DIRECTION}
     */
    public static BlockType<BlockTorchBehavior> TORCH;

    public static BlockType<BlockSmallFlower> TORCHFLOWER;

    /**
     * {@link BlockPropertyTypes#GROWTH_8}
     */
    public static BlockType<BlockBehavior> TORCHFLOWER_CROP;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockTrappedChestBehavior> TRAPPED_CHEST;

    /**
     * {@link BlockPropertyTypes#OMINOUS}
     * {@link BlockPropertyTypes#TRIAL_SPAWNER_STATE}
     */
    public static BlockType<BlockBehavior> TRIAL_SPAWNER;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#DISARMED_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#SUSPENDED_BIT}
     */
    public static BlockType<BlockBehavior> TRIP_WIRE;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockBehavior> TRIPWIRE_HOOK;

    public static BlockType<BlockBehavior> TUBE_CORAL;

    public static BlockType<BlockBehavior> TUBE_CORAL_BLOCK;

    /**
     * {@link BlockPropertyTypes#CORAL_FAN_DIRECTION}
     */
    public static BlockType<BlockBehavior> TUBE_CORAL_FAN;

    /**
     * {@link BlockPropertyTypes#CORAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> TUBE_CORAL_WALL_FAN;

    public static BlockType<BlockBehavior> TUFF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> TUFF_BRICK_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> TUFF_BRICK_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> TUFF_BRICK_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> TUFF_BRICK_WALL;

    public static BlockType<BlockBehavior> TUFF_BRICKS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> TUFF_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> TUFF_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> TUFF_STAIRS;

    /**
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_EAST}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_NORTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_SOUTH}
     * {@link BlockPropertyTypes#WALL_CONNECTION_TYPE_WEST}
     * {@link BlockPropertyTypes#WALL_POST_BIT}
     */
    public static BlockType<BlockWallBehavior> TUFF_WALL;

    /**
     * {@link BlockPropertyTypes#CRACKED_STATE}
     * {@link BlockPropertyTypes#TURTLE_EGG_COUNT}
     */
    public static BlockType<BlockBehavior> TURTLE_EGG;

    /**
     * {@link BlockPropertyTypes#TWISTING_VINES_AGE}
     */
    public static BlockType<BlockBehavior> TWISTING_VINES;

    /**
     * {@link BlockPropertyTypes#EXPLODE_BIT}
     */
    public static BlockType<BlockBehavior> UNDERWATER_TNT;

    /**
     * {@link BlockPropertyTypes#TORCH_FACING_DIRECTION}
     */
    public static BlockType<BlockTorchBehavior> UNDERWATER_TORCH;

    public static BlockType<BlockShulkerBoxBehavior> UNDYED_SHULKER_BOX;

    public static BlockType<BlockBehavior> UNKNOWN;

    /**
     * {@link BlockPropertyTypes#TORCH_FACING_DIRECTION}
     */
    public static BlockType<BlockTorchBehavior> UNLIT_REDSTONE_TORCH;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OUTPUT_LIT_BIT}
     * {@link BlockPropertyTypes#OUTPUT_SUBTRACT_BIT}
     */
    public static BlockType<BlockComparatorBehavior> UNPOWERED_COMPARATOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#REPEATER_DELAY}
     */
    public static BlockType<BlockRepeaterBehavior> UNPOWERED_REPEATER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OMINOUS}
     * {@link BlockPropertyTypes#VAULT_STATE}
     */
    public static BlockType<BlockBehavior> VAULT;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockBehavior> VERDANT_FROGLIGHT;

    /**
     * {@link BlockPropertyTypes#VINE_DIRECTION_BITS}
     */
    public static BlockType<BlockBehavior> VINE;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockWallBannerBehavior> WALL_BANNER;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> WALL_SIGN;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> WARPED_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> WARPED_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> WARPED_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockBehavior> WARPED_FENCE;

    /**
     * {@link BlockPropertyTypes#IN_WALL_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     */
    public static BlockType<BlockFenceGateBehavior> WARPED_FENCE_GATE;

    public static BlockType<BlockBehavior> WARPED_FUNGUS;

    /**
     * {@link BlockPropertyTypes#ATTACHED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockHangingSignBehavior> WARPED_HANGING_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> WARPED_HYPHAE;

    public static BlockType<BlockBehavior> WARPED_NYLIUM;

    public static BlockType<BlockBehavior> WARPED_PLANKS;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> WARPED_PRESSURE_PLATE;

    public static BlockType<BlockBehavior> WARPED_ROOTS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     * {@link BlockPropertyTypes#POWERED_SHELF_TYPE}
     */
    public static BlockType<BlockShelfBehavior> WARPED_SHELF;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> WARPED_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> WARPED_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROUND_SIGN_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> WARPED_STANDING_SIGN;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockWoodBehavior> WARPED_STEM;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockTrapdoorBehavior> WARPED_TRAPDOOR;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockSignBehavior> WARPED_WALL_SIGN;

    public static BlockType<BlockBehavior> WARPED_WART_BLOCK;

    /**
     * {@link BlockPropertyTypes#LIQUID_DEPTH}
     */
    public static BlockType<BlockLiquidBehavior> WATER;

    public static BlockType<BlockBehavior> WATERLILY;

    public static BlockType<BlockCopperBehavior> WAXED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> WAXED_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockCopperBarsBehavior> WAXED_COPPER_BARS;

    /**
     * {@link BlockPropertyTypes#LIT}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockCopperBulbBehavior> WAXED_COPPER_BULB;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockCopperChainBehavior> WAXED_COPPER_CHAIN;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> WAXED_COPPER_CHEST;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockCopperDoorBehavior> WAXED_COPPER_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockCopperGolemStatueBehavior> WAXED_COPPER_GOLEM_STATUE;

    public static BlockType<BlockCopperGrateBehavior> WAXED_COPPER_GRATE;

    /**
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockCopperLanternBehavior> WAXED_COPPER_LANTERN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockCopperTrapdoorBehavior> WAXED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> WAXED_CUT_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockCopperSlabBehavior> WAXED_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockCopperStairsBehavior> WAXED_CUT_COPPER_STAIRS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleCopperSlabBehavior> WAXED_DOUBLE_CUT_COPPER_SLAB;

    public static BlockType<BlockCopperBehavior> WAXED_EXPOSED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> WAXED_EXPOSED_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockCopperBarsBehavior> WAXED_EXPOSED_COPPER_BARS;

    /**
     * {@link BlockPropertyTypes#LIT}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockCopperBulbBehavior> WAXED_EXPOSED_COPPER_BULB;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockCopperChainBehavior> WAXED_EXPOSED_COPPER_CHAIN;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> WAXED_EXPOSED_COPPER_CHEST;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockCopperDoorBehavior> WAXED_EXPOSED_COPPER_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockCopperGolemStatueBehavior> WAXED_EXPOSED_COPPER_GOLEM_STATUE;

    public static BlockType<BlockCopperGrateBehavior> WAXED_EXPOSED_COPPER_GRATE;

    /**
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockCopperLanternBehavior> WAXED_EXPOSED_COPPER_LANTERN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockCopperTrapdoorBehavior> WAXED_EXPOSED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> WAXED_EXPOSED_CUT_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockCopperSlabBehavior> WAXED_EXPOSED_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockCopperStairsBehavior> WAXED_EXPOSED_CUT_COPPER_STAIRS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleCopperSlabBehavior> WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockLightningRodBehavior> WAXED_EXPOSED_LIGHTNING_ROD;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockLightningRodBehavior> WAXED_LIGHTNING_ROD;

    public static BlockType<BlockCopperBehavior> WAXED_OXIDIZED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> WAXED_OXIDIZED_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockCopperBarsBehavior> WAXED_OXIDIZED_COPPER_BARS;

    /**
     * {@link BlockPropertyTypes#LIT}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockCopperBulbBehavior> WAXED_OXIDIZED_COPPER_BULB;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockCopperChainBehavior> WAXED_OXIDIZED_COPPER_CHAIN;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> WAXED_OXIDIZED_COPPER_CHEST;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockCopperDoorBehavior> WAXED_OXIDIZED_COPPER_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockCopperGolemStatueBehavior> WAXED_OXIDIZED_COPPER_GOLEM_STATUE;

    public static BlockType<BlockCopperGrateBehavior> WAXED_OXIDIZED_COPPER_GRATE;

    /**
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockCopperLanternBehavior> WAXED_OXIDIZED_COPPER_LANTERN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockCopperTrapdoorBehavior> WAXED_OXIDIZED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> WAXED_OXIDIZED_CUT_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockCopperSlabBehavior> WAXED_OXIDIZED_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockCopperStairsBehavior> WAXED_OXIDIZED_CUT_COPPER_STAIRS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleCopperSlabBehavior> WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockLightningRodBehavior> WAXED_OXIDIZED_LIGHTNING_ROD;

    public static BlockType<BlockCopperBehavior> WAXED_WEATHERED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> WAXED_WEATHERED_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockCopperBarsBehavior> WAXED_WEATHERED_COPPER_BARS;

    /**
     * {@link BlockPropertyTypes#LIT}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockCopperBulbBehavior> WAXED_WEATHERED_COPPER_BULB;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockCopperChainBehavior> WAXED_WEATHERED_COPPER_CHAIN;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> WAXED_WEATHERED_COPPER_CHEST;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockCopperDoorBehavior> WAXED_WEATHERED_COPPER_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockCopperGolemStatueBehavior> WAXED_WEATHERED_COPPER_GOLEM_STATUE;

    public static BlockType<BlockCopperGrateBehavior> WAXED_WEATHERED_COPPER_GRATE;

    /**
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockCopperLanternBehavior> WAXED_WEATHERED_COPPER_LANTERN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockCopperTrapdoorBehavior> WAXED_WEATHERED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> WAXED_WEATHERED_CUT_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockCopperSlabBehavior> WAXED_WEATHERED_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockCopperStairsBehavior> WAXED_WEATHERED_CUT_COPPER_STAIRS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleCopperSlabBehavior> WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockLightningRodBehavior> WAXED_WEATHERED_LIGHTNING_ROD;

    public static BlockType<BlockCopperBehavior> WEATHERED_CHISELED_COPPER;

    public static BlockType<BlockCopperBehavior> WEATHERED_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockCopperBarsBehavior> WEATHERED_COPPER_BARS;

    /**
     * {@link BlockPropertyTypes#LIT}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockCopperBulbBehavior> WEATHERED_COPPER_BULB;

    /**
     * {@link BlockPropertyTypes#PILLAR_AXIS}
     */
    public static BlockType<BlockCopperChainBehavior> WEATHERED_COPPER_CHAIN;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockBehavior> WEATHERED_COPPER_CHEST;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockCopperDoorBehavior> WEATHERED_COPPER_DOOR;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockCopperGolemStatueBehavior> WEATHERED_COPPER_GOLEM_STATUE;

    public static BlockType<BlockCopperGrateBehavior> WEATHERED_COPPER_GRATE;

    /**
     * {@link BlockPropertyTypes#HANGING}
     */
    public static BlockType<BlockCopperLanternBehavior> WEATHERED_COPPER_LANTERN;

    /**
     * {@link BlockPropertyTypes#DIRECTION_4}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     */
    public static BlockType<BlockCopperTrapdoorBehavior> WEATHERED_COPPER_TRAPDOOR;

    public static BlockType<BlockCopperBehavior> WEATHERED_CUT_COPPER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockCopperSlabBehavior> WEATHERED_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockCopperStairsBehavior> WEATHERED_CUT_COPPER_STAIRS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleCopperSlabBehavior> WEATHERED_DOUBLE_CUT_COPPER_SLAB;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     * {@link BlockPropertyTypes#POWERED_BIT}
     */
    public static BlockType<BlockLightningRodBehavior> WEATHERED_LIGHTNING_ROD;

    public static BlockType<BlockBehavior> WEB;

    /**
     * {@link BlockPropertyTypes#WEEPING_VINES_AGE}
     */
    public static BlockType<BlockBehavior> WEEPING_VINES;

    public static BlockType<BlockBehavior> WET_SPONGE;

    /**
     * {@link BlockPropertyTypes#GROWTH_8}
     */
    public static BlockType<BlockBehavior> WHEAT;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> WHITE_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> WHITE_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> WHITE_CARPET;

    public static BlockType<BlockBehavior> WHITE_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> WHITE_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> WHITE_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> WHITE_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> WHITE_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> WHITE_GLAZED_TERRACOTTA;

    public static BlockType<BlockShulkerBoxBehavior> WHITE_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> WHITE_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> WHITE_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> WHITE_TERRACOTTA;

    public static BlockType<BlockSmallFlower> WHITE_TULIP;

    public static BlockType<BlockBehavior> WHITE_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> WHITE_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> WHITE_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> WHITE_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#GROWTH_8}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     */
    public static BlockType<BlockPlantPile> WILDFLOWERS;

    public static BlockType<BlockSmallFlower> WITHER_ROSE;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockHeadBehavior> WITHER_SKELETON_SKULL;

    /**
     * {@link BlockPropertyTypes#BUTTON_PRESSED_BIT}
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockBehavior> WOODEN_BUTTON;

    /**
     * {@link BlockPropertyTypes#DOOR_HINGE_BIT}
     * {@link BlockPropertyTypes#MINECRAFT_CARDINAL_DIRECTION}
     * {@link BlockPropertyTypes#OPEN_BIT}
     * {@link BlockPropertyTypes#UPPER_BLOCK_BIT}
     */
    public static BlockType<BlockBehavior> WOODEN_DOOR;

    /**
     * {@link BlockPropertyTypes#REDSTONE_SIGNAL}
     */
    public static BlockType<BlockBehavior> WOODEN_PRESSURE_PLATE;

    /**
     * {@link BlockPropertyTypes#CANDLES}
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleBehavior> YELLOW_CANDLE;

    /**
     * {@link BlockPropertyTypes#LIT}
     */
    public static BlockType<BlockCandleCakeBehavior> YELLOW_CANDLE_CAKE;

    public static BlockType<BlockCarpetBehavior> YELLOW_CARPET;

    public static BlockType<BlockBehavior> YELLOW_CONCRETE;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> YELLOW_CONCRETE_DOUBLE_SLAB;

    public static BlockType<BlockConcretePowderBehavior> YELLOW_CONCRETE_POWDER;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> YELLOW_CONCRETE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> YELLOW_CONCRETE_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockGlazedTerracottaBehavior> YELLOW_GLAZED_TERRACOTTA;

    /**
     * {@link BlockPropertyTypes#PERSISTENT_BIT}
     * {@link BlockPropertyTypes#UPDATE_BIT}
     */
    public static BlockType<BlockLeavesBehavior> YELLOW_POPLAR_LEAVES;

    public static BlockType<BlockShulkerBoxBehavior> YELLOW_SHULKER_BOX;

    public static BlockType<BlockGlassBehavior> YELLOW_STAINED_GLASS;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_EAST}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_NORTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_SOUTH}
     * {@link BlockPropertyTypes#MINECRAFT_CONNECTION_WEST}
     */
    public static BlockType<BlockGlassPaneBehavior> YELLOW_STAINED_GLASS_PANE;

    public static BlockType<BlockTerracottaBehavior> YELLOW_TERRACOTTA;

    public static BlockType<BlockBehavior> YELLOW_WOOL;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockDoubleSlabBehavior> YELLOW_WOOL_DOUBLE_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_VERTICAL_HALF}
     */
    public static BlockType<BlockSlabBehavior> YELLOW_WOOL_SLAB;

    /**
     * {@link BlockPropertyTypes#MINECRAFT_CORNER}
     * {@link BlockPropertyTypes#UPSIDE_DOWN_BIT}
     * {@link BlockPropertyTypes#WEIRDO_DIRECTION}
     */
    public static BlockType<BlockStairsBehavior> YELLOW_WOOL_STAIRS;

    /**
     * {@link BlockPropertyTypes#FACING_DIRECTION}
     */
    public static BlockType<BlockHeadBehavior> ZOMBIE_HEAD;
}
