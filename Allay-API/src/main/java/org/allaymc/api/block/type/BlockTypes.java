package org.allaymc.api.block.type;

import org.allaymc.api.block.interfaces.*;
import org.allaymc.api.block.interfaces.amethystbud.BlockLargeAmethystBudBehavior;
import org.allaymc.api.block.interfaces.amethystbud.BlockMediumAmethystBudBehavior;
import org.allaymc.api.block.interfaces.amethystbud.BlockSmallAmethystBudBehavior;
import org.allaymc.api.block.interfaces.bricks.*;
import org.allaymc.api.block.interfaces.button.*;
import org.allaymc.api.block.interfaces.candle.*;
import org.allaymc.api.block.interfaces.candlecake.*;
import org.allaymc.api.block.interfaces.carpet.*;
import org.allaymc.api.block.interfaces.concrete.*;
import org.allaymc.api.block.interfaces.concretepowder.*;
import org.allaymc.api.block.interfaces.copper.*;
import org.allaymc.api.block.interfaces.coral.*;
import org.allaymc.api.block.interfaces.coralfan.*;
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
import org.allaymc.api.block.interfaces.sapling.BlockBambooSaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockCherrySaplingBehavior;
import org.allaymc.api.block.interfaces.sapling.BlockSaplingBehavior;
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

public final class BlockTypes {
  public static BlockType<BlockAcaciaButtonBehavior> ACACIA_BUTTON_TYPE;

  public static BlockType<BlockAcaciaDoorBehavior> ACACIA_DOOR_TYPE;

  public static BlockType<BlockAcaciaFenceBehavior> ACACIA_FENCE_TYPE;

  public static BlockType<BlockAcaciaFenceGateBehavior> ACACIA_FENCE_GATE_TYPE;

  public static BlockType<BlockAcaciaHangingSignBehavior> ACACIA_HANGING_SIGN_TYPE;

  public static BlockType<BlockAcaciaLogBehavior> ACACIA_LOG_TYPE;

  public static BlockType<BlockAcaciaPlanksBehavior> ACACIA_PLANKS_TYPE;

  public static BlockType<BlockAcaciaPressurePlateBehavior> ACACIA_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockAcaciaStairsBehavior> ACACIA_STAIRS_TYPE;

  public static BlockType<BlockAcaciaStandingSignBehavior> ACACIA_STANDING_SIGN_TYPE;

  public static BlockType<BlockAcaciaTrapdoorBehavior> ACACIA_TRAPDOOR_TYPE;

  public static BlockType<BlockAcaciaWallSignBehavior> ACACIA_WALL_SIGN_TYPE;

  public static BlockType<BlockActivatorRailBehavior> ACTIVATOR_RAIL_TYPE;

  public static BlockType<BlockAirBehavior> AIR_TYPE;

  public static BlockType<BlockAllowBehavior> ALLOW_TYPE;

  public static BlockType<BlockAmethystBlockBehavior> AMETHYST_BLOCK_TYPE;

  public static BlockType<BlockAmethystClusterBehavior> AMETHYST_CLUSTER_TYPE;

  public static BlockType<BlockAncientDebrisBehavior> ANCIENT_DEBRIS_TYPE;

  public static BlockType<BlockAndesiteBehavior> ANDESITE_TYPE;

  public static BlockType<BlockAndesiteStairsBehavior> ANDESITE_STAIRS_TYPE;

  public static BlockType<BlockAnvilBehavior> ANVIL_TYPE;

  public static BlockType<BlockAzaleaBehavior> AZALEA_TYPE;

  public static BlockType<BlockAzaleaLeavesBehavior> AZALEA_LEAVES_TYPE;

  public static BlockType<BlockAzaleaLeavesFloweredBehavior> AZALEA_LEAVES_FLOWERED_TYPE;

  public static BlockType<BlockBambooBehavior> BAMBOO_TYPE;

  public static BlockType<BlockBambooBlockBehavior> BAMBOO_BLOCK_TYPE;

  public static BlockType<BlockBambooButtonBehavior> BAMBOO_BUTTON_TYPE;

  public static BlockType<BlockBambooDoorBehavior> BAMBOO_DOOR_TYPE;

  public static BlockType<BlockBambooDoubleSlabBehavior> BAMBOO_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockBambooFenceBehavior> BAMBOO_FENCE_TYPE;

  public static BlockType<BlockBambooFenceGateBehavior> BAMBOO_FENCE_GATE_TYPE;

  public static BlockType<BlockBambooHangingSignBehavior> BAMBOO_HANGING_SIGN_TYPE;

  public static BlockType<BlockBambooMosaicBehavior> BAMBOO_MOSAIC_TYPE;

  public static BlockType<BlockBambooMosaicDoubleSlabBehavior> BAMBOO_MOSAIC_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockBambooMosaicSlabBehavior> BAMBOO_MOSAIC_SLAB_TYPE;

  public static BlockType<BlockBambooMosaicStairsBehavior> BAMBOO_MOSAIC_STAIRS_TYPE;

  public static BlockType<BlockBambooPlanksBehavior> BAMBOO_PLANKS_TYPE;

  public static BlockType<BlockBambooPressurePlateBehavior> BAMBOO_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockBambooSaplingBehavior> BAMBOO_SAPLING_TYPE;

  public static BlockType<BlockBambooSlabBehavior> BAMBOO_SLAB_TYPE;

  public static BlockType<BlockBambooStairsBehavior> BAMBOO_STAIRS_TYPE;

  public static BlockType<BlockBambooStandingSignBehavior> BAMBOO_STANDING_SIGN_TYPE;

  public static BlockType<BlockBambooTrapdoorBehavior> BAMBOO_TRAPDOOR_TYPE;

  public static BlockType<BlockBambooWallSignBehavior> BAMBOO_WALL_SIGN_TYPE;

  public static BlockType<BlockBarrelBehavior> BARREL_TYPE;

  public static BlockType<BlockBarrierBehavior> BARRIER_TYPE;

  public static BlockType<BlockBasaltBehavior> BASALT_TYPE;

  public static BlockType<BlockBeaconBehavior> BEACON_TYPE;

  public static BlockType<BlockBedBehavior> BED_TYPE;

  public static BlockType<BlockBedrockBehavior> BEDROCK_TYPE;

  public static BlockType<BlockBeeNestBehavior> BEE_NEST_TYPE;

  public static BlockType<BlockBeehiveBehavior> BEEHIVE_TYPE;

  public static BlockType<BlockBeetrootBehavior> BEETROOT_TYPE;

  public static BlockType<BlockBellBehavior> BELL_TYPE;

  public static BlockType<BlockBigDripleafBehavior> BIG_DRIPLEAF_TYPE;

  public static BlockType<BlockBirchButtonBehavior> BIRCH_BUTTON_TYPE;

  public static BlockType<BlockBirchDoorBehavior> BIRCH_DOOR_TYPE;

  public static BlockType<BlockBirchFenceBehavior> BIRCH_FENCE_TYPE;

  public static BlockType<BlockBirchFenceGateBehavior> BIRCH_FENCE_GATE_TYPE;

  public static BlockType<BlockBirchHangingSignBehavior> BIRCH_HANGING_SIGN_TYPE;

  public static BlockType<BlockBirchLogBehavior> BIRCH_LOG_TYPE;

  public static BlockType<BlockBirchPlanksBehavior> BIRCH_PLANKS_TYPE;

  public static BlockType<BlockBirchPressurePlateBehavior> BIRCH_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockBirchStairsBehavior> BIRCH_STAIRS_TYPE;

  public static BlockType<BlockBirchStandingSignBehavior> BIRCH_STANDING_SIGN_TYPE;

  public static BlockType<BlockBirchTrapdoorBehavior> BIRCH_TRAPDOOR_TYPE;

  public static BlockType<BlockBirchWallSignBehavior> BIRCH_WALL_SIGN_TYPE;

  public static BlockType<BlockBlackCandleBehavior> BLACK_CANDLE_TYPE;

  public static BlockType<BlockBlackCandleCakeBehavior> BLACK_CANDLE_CAKE_TYPE;

  public static BlockType<BlockBlackCarpetBehavior> BLACK_CARPET_TYPE;

  public static BlockType<BlockBlackConcreteBehavior> BLACK_CONCRETE_TYPE;

  public static BlockType<BlockBlackConcretePowderBehavior> BLACK_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockBlackGlazedTerracottaBehavior> BLACK_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockBlackShulkerBoxBehavior> BLACK_SHULKER_BOX_TYPE;

  public static BlockType<BlockBlackStainedGlassBehavior> BLACK_STAINED_GLASS_TYPE;

  public static BlockType<BlockBlackStainedGlassPaneBehavior> BLACK_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockBlackTerracottaBehavior> BLACK_TERRACOTTA_TYPE;

  public static BlockType<BlockBlackWoolBehavior> BLACK_WOOL_TYPE;

  public static BlockType<BlockBlackstoneBehavior> BLACKSTONE_TYPE;

  public static BlockType<BlockBlackstoneDoubleSlabBehavior> BLACKSTONE_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockBlackstoneSlabBehavior> BLACKSTONE_SLAB_TYPE;

  public static BlockType<BlockBlackstoneStairsBehavior> BLACKSTONE_STAIRS_TYPE;

  public static BlockType<BlockBlackstoneWallBehavior> BLACKSTONE_WALL_TYPE;

  public static BlockType<BlockBlastFurnaceBehavior> BLAST_FURNACE_TYPE;

  public static BlockType<BlockBlueCandleBehavior> BLUE_CANDLE_TYPE;

  public static BlockType<BlockBlueCandleCakeBehavior> BLUE_CANDLE_CAKE_TYPE;

  public static BlockType<BlockBlueCarpetBehavior> BLUE_CARPET_TYPE;

  public static BlockType<BlockBlueConcreteBehavior> BLUE_CONCRETE_TYPE;

  public static BlockType<BlockBlueConcretePowderBehavior> BLUE_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockBlueGlazedTerracottaBehavior> BLUE_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockBlueIceBehavior> BLUE_ICE_TYPE;

  public static BlockType<BlockBlueShulkerBoxBehavior> BLUE_SHULKER_BOX_TYPE;

  public static BlockType<BlockBlueStainedGlassBehavior> BLUE_STAINED_GLASS_TYPE;

  public static BlockType<BlockBlueStainedGlassPaneBehavior> BLUE_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockBlueTerracottaBehavior> BLUE_TERRACOTTA_TYPE;

  public static BlockType<BlockBlueWoolBehavior> BLUE_WOOL_TYPE;

  public static BlockType<BlockBoneBlockBehavior> BONE_BLOCK_TYPE;

  public static BlockType<BlockBookshelfBehavior> BOOKSHELF_TYPE;

  public static BlockType<BlockBorderBlockBehavior> BORDER_BLOCK_TYPE;

  public static BlockType<BlockBrainCoralBehavior> BRAIN_CORAL_TYPE;

  public static BlockType<BlockBrewingStandBehavior> BREWING_STAND_TYPE;

  public static BlockType<BlockBrickBlockBehavior> BRICK_BLOCK_TYPE;

  public static BlockType<BlockBrickStairsBehavior> BRICK_STAIRS_TYPE;

  public static BlockType<BlockBrownCandleBehavior> BROWN_CANDLE_TYPE;

  public static BlockType<BlockBrownCandleCakeBehavior> BROWN_CANDLE_CAKE_TYPE;

  public static BlockType<BlockBrownCarpetBehavior> BROWN_CARPET_TYPE;

  public static BlockType<BlockBrownConcreteBehavior> BROWN_CONCRETE_TYPE;

  public static BlockType<BlockBrownConcretePowderBehavior> BROWN_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockBrownGlazedTerracottaBehavior> BROWN_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockBrownMushroomBehavior> BROWN_MUSHROOM_TYPE;

  public static BlockType<BlockBrownMushroomBlockBehavior> BROWN_MUSHROOM_BLOCK_TYPE;

  public static BlockType<BlockBrownShulkerBoxBehavior> BROWN_SHULKER_BOX_TYPE;

  public static BlockType<BlockBrownStainedGlassBehavior> BROWN_STAINED_GLASS_TYPE;

  public static BlockType<BlockBrownStainedGlassPaneBehavior> BROWN_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockBrownTerracottaBehavior> BROWN_TERRACOTTA_TYPE;

  public static BlockType<BlockBrownWoolBehavior> BROWN_WOOL_TYPE;

  public static BlockType<BlockBubbleColumnBehavior> BUBBLE_COLUMN_TYPE;

  public static BlockType<BlockBubbleCoralBehavior> BUBBLE_CORAL_TYPE;

  public static BlockType<BlockBuddingAmethystBehavior> BUDDING_AMETHYST_TYPE;

  public static BlockType<BlockCactusBehavior> CACTUS_TYPE;

  public static BlockType<BlockCakeBehavior> CAKE_TYPE;

  public static BlockType<BlockCalciteBehavior> CALCITE_TYPE;

  public static BlockType<BlockCalibratedSculkSensorBehavior> CALIBRATED_SCULK_SENSOR_TYPE;

  public static BlockType<BlockCameraBehavior> CAMERA_TYPE;

  public static BlockType<BlockCampfireBehavior> CAMPFIRE_TYPE;

  public static BlockType<BlockCandleBehavior> CANDLE_TYPE;

  public static BlockType<BlockCandleCakeBehavior> CANDLE_CAKE_TYPE;

  public static BlockType<BlockCarrotsBehavior> CARROTS_TYPE;

  public static BlockType<BlockCartographyTableBehavior> CARTOGRAPHY_TABLE_TYPE;

  public static BlockType<BlockCarvedPumpkinBehavior> CARVED_PUMPKIN_TYPE;

  public static BlockType<BlockCauldronBehavior> CAULDRON_TYPE;

  public static BlockType<BlockCaveVinesBehavior> CAVE_VINES_TYPE;

  public static BlockType<BlockCaveVinesBodyWithBerriesBehavior> CAVE_VINES_BODY_WITH_BERRIES_TYPE;

  public static BlockType<BlockCaveVinesHeadWithBerriesBehavior> CAVE_VINES_HEAD_WITH_BERRIES_TYPE;

  public static BlockType<BlockChainBehavior> CHAIN_TYPE;

  public static BlockType<BlockChainCommandBlockBehavior> CHAIN_COMMAND_BLOCK_TYPE;

  public static BlockType<BlockChemicalHeatBehavior> CHEMICAL_HEAT_TYPE;

  public static BlockType<BlockChemistryTableBehavior> CHEMISTRY_TABLE_TYPE;

  public static BlockType<BlockCherryButtonBehavior> CHERRY_BUTTON_TYPE;

  public static BlockType<BlockCherryDoorBehavior> CHERRY_DOOR_TYPE;

  public static BlockType<BlockCherryDoubleSlabBehavior> CHERRY_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockCherryFenceBehavior> CHERRY_FENCE_TYPE;

  public static BlockType<BlockCherryFenceGateBehavior> CHERRY_FENCE_GATE_TYPE;

  public static BlockType<BlockCherryHangingSignBehavior> CHERRY_HANGING_SIGN_TYPE;

  public static BlockType<BlockCherryLeavesBehavior> CHERRY_LEAVES_TYPE;

  public static BlockType<BlockCherryLogBehavior> CHERRY_LOG_TYPE;

  public static BlockType<BlockCherryPlanksBehavior> CHERRY_PLANKS_TYPE;

  public static BlockType<BlockCherryPressurePlateBehavior> CHERRY_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockCherrySaplingBehavior> CHERRY_SAPLING_TYPE;

  public static BlockType<BlockCherrySlabBehavior> CHERRY_SLAB_TYPE;

  public static BlockType<BlockCherryStairsBehavior> CHERRY_STAIRS_TYPE;

  public static BlockType<BlockCherryStandingSignBehavior> CHERRY_STANDING_SIGN_TYPE;

  public static BlockType<BlockCherryTrapdoorBehavior> CHERRY_TRAPDOOR_TYPE;

  public static BlockType<BlockCherryWallSignBehavior> CHERRY_WALL_SIGN_TYPE;

  public static BlockType<BlockCherryWoodBehavior> CHERRY_WOOD_TYPE;

  public static BlockType<BlockChestBehavior> CHEST_TYPE;

  public static BlockType<BlockChiseledBookshelfBehavior> CHISELED_BOOKSHELF_TYPE;

  public static BlockType<BlockChiseledCopperBehavior> CHISELED_COPPER_TYPE;

  public static BlockType<BlockChiseledDeepslateBehavior> CHISELED_DEEPSLATE_TYPE;

  public static BlockType<BlockChiseledNetherBricksBehavior> CHISELED_NETHER_BRICKS_TYPE;

  public static BlockType<BlockChiseledPolishedBlackstoneBehavior> CHISELED_POLISHED_BLACKSTONE_TYPE;

  public static BlockType<BlockChiseledTuffBehavior> CHISELED_TUFF_TYPE;

  public static BlockType<BlockChiseledTuffBricksBehavior> CHISELED_TUFF_BRICKS_TYPE;

  public static BlockType<BlockChorusFlowerBehavior> CHORUS_FLOWER_TYPE;

  public static BlockType<BlockChorusPlantBehavior> CHORUS_PLANT_TYPE;

  public static BlockType<BlockClayBehavior> CLAY_TYPE;

  public static BlockType<BlockClientRequestPlaceholderBlockBehavior> CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE;

  public static BlockType<BlockCoalBlockBehavior> COAL_BLOCK_TYPE;

  public static BlockType<BlockCoalOreBehavior> COAL_ORE_TYPE;

  public static BlockType<BlockCobbledDeepslateBehavior> COBBLED_DEEPSLATE_TYPE;

  public static BlockType<BlockCobbledDeepslateDoubleSlabBehavior> COBBLED_DEEPSLATE_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockCobbledDeepslateSlabBehavior> COBBLED_DEEPSLATE_SLAB_TYPE;

  public static BlockType<BlockCobbledDeepslateStairsBehavior> COBBLED_DEEPSLATE_STAIRS_TYPE;

  public static BlockType<BlockCobbledDeepslateWallBehavior> COBBLED_DEEPSLATE_WALL_TYPE;

  public static BlockType<BlockCobblestoneBehavior> COBBLESTONE_TYPE;

  public static BlockType<BlockCobblestoneWallBehavior> COBBLESTONE_WALL_TYPE;

  public static BlockType<BlockCocoaBehavior> COCOA_TYPE;

  public static BlockType<BlockColoredTorchBpBehavior> COLORED_TORCH_BP_TYPE;

  public static BlockType<BlockColoredTorchRgBehavior> COLORED_TORCH_RG_TYPE;

  public static BlockType<BlockCommandBlockBehavior> COMMAND_BLOCK_TYPE;

  public static BlockType<BlockComposterBehavior> COMPOSTER_TYPE;

  public static BlockType<BlockConduitBehavior> CONDUIT_TYPE;

  public static BlockType<BlockCopperBlockBehavior> COPPER_BLOCK_TYPE;

  public static BlockType<BlockCopperBulbBehavior> COPPER_BULB_TYPE;

  public static BlockType<BlockCopperDoorBehavior> COPPER_DOOR_TYPE;

  public static BlockType<BlockCopperGrateBehavior> COPPER_GRATE_TYPE;

  public static BlockType<BlockCopperOreBehavior> COPPER_ORE_TYPE;

  public static BlockType<BlockCopperTrapdoorBehavior> COPPER_TRAPDOOR_TYPE;

  public static BlockType<BlockCoralBlockBehavior> CORAL_BLOCK_TYPE;

  public static BlockType<BlockCoralFanBehavior> CORAL_FAN_TYPE;

  public static BlockType<BlockCoralFanDeadBehavior> CORAL_FAN_DEAD_TYPE;

  public static BlockType<BlockCoralFanHangBehavior> CORAL_FAN_HANG_TYPE;

  public static BlockType<BlockCoralFanHang2Behavior> CORAL_FAN_HANG2_TYPE;

  public static BlockType<BlockCoralFanHang3Behavior> CORAL_FAN_HANG3_TYPE;

  public static BlockType<BlockCrackedDeepslateBricksBehavior> CRACKED_DEEPSLATE_BRICKS_TYPE;

  public static BlockType<BlockCrackedDeepslateTilesBehavior> CRACKED_DEEPSLATE_TILES_TYPE;

  public static BlockType<BlockCrackedNetherBricksBehavior> CRACKED_NETHER_BRICKS_TYPE;

  public static BlockType<BlockCrackedPolishedBlackstoneBricksBehavior> CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE;

  public static BlockType<BlockCrafterBehavior> CRAFTER_TYPE;

  public static BlockType<BlockCraftingTableBehavior> CRAFTING_TABLE_TYPE;

  public static BlockType<BlockCrimsonButtonBehavior> CRIMSON_BUTTON_TYPE;

  public static BlockType<BlockCrimsonDoorBehavior> CRIMSON_DOOR_TYPE;

  public static BlockType<BlockCrimsonDoubleSlabBehavior> CRIMSON_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockCrimsonFenceBehavior> CRIMSON_FENCE_TYPE;

  public static BlockType<BlockCrimsonFenceGateBehavior> CRIMSON_FENCE_GATE_TYPE;

  public static BlockType<BlockCrimsonFungusBehavior> CRIMSON_FUNGUS_TYPE;

  public static BlockType<BlockCrimsonHangingSignBehavior> CRIMSON_HANGING_SIGN_TYPE;

  public static BlockType<BlockCrimsonHyphaeBehavior> CRIMSON_HYPHAE_TYPE;

  public static BlockType<BlockCrimsonNyliumBehavior> CRIMSON_NYLIUM_TYPE;

  public static BlockType<BlockCrimsonPlanksBehavior> CRIMSON_PLANKS_TYPE;

  public static BlockType<BlockCrimsonPressurePlateBehavior> CRIMSON_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockCrimsonRootsBehavior> CRIMSON_ROOTS_TYPE;

  public static BlockType<BlockCrimsonSlabBehavior> CRIMSON_SLAB_TYPE;

  public static BlockType<BlockCrimsonStairsBehavior> CRIMSON_STAIRS_TYPE;

  public static BlockType<BlockCrimsonStandingSignBehavior> CRIMSON_STANDING_SIGN_TYPE;

  public static BlockType<BlockCrimsonStemBehavior> CRIMSON_STEM_TYPE;

  public static BlockType<BlockCrimsonTrapdoorBehavior> CRIMSON_TRAPDOOR_TYPE;

  public static BlockType<BlockCrimsonWallSignBehavior> CRIMSON_WALL_SIGN_TYPE;

  public static BlockType<BlockCryingObsidianBehavior> CRYING_OBSIDIAN_TYPE;

  public static BlockType<BlockCutCopperBehavior> CUT_COPPER_TYPE;

  public static BlockType<BlockCutCopperSlabBehavior> CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockCutCopperStairsBehavior> CUT_COPPER_STAIRS_TYPE;

  public static BlockType<BlockCyanCandleBehavior> CYAN_CANDLE_TYPE;

  public static BlockType<BlockCyanCandleCakeBehavior> CYAN_CANDLE_CAKE_TYPE;

  public static BlockType<BlockCyanCarpetBehavior> CYAN_CARPET_TYPE;

  public static BlockType<BlockCyanConcreteBehavior> CYAN_CONCRETE_TYPE;

  public static BlockType<BlockCyanConcretePowderBehavior> CYAN_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockCyanGlazedTerracottaBehavior> CYAN_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockCyanShulkerBoxBehavior> CYAN_SHULKER_BOX_TYPE;

  public static BlockType<BlockCyanStainedGlassBehavior> CYAN_STAINED_GLASS_TYPE;

  public static BlockType<BlockCyanStainedGlassPaneBehavior> CYAN_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockCyanTerracottaBehavior> CYAN_TERRACOTTA_TYPE;

  public static BlockType<BlockCyanWoolBehavior> CYAN_WOOL_TYPE;

  public static BlockType<BlockDarkOakButtonBehavior> DARK_OAK_BUTTON_TYPE;

  public static BlockType<BlockDarkOakDoorBehavior> DARK_OAK_DOOR_TYPE;

  public static BlockType<BlockDarkOakFenceBehavior> DARK_OAK_FENCE_TYPE;

  public static BlockType<BlockDarkOakFenceGateBehavior> DARK_OAK_FENCE_GATE_TYPE;

  public static BlockType<BlockDarkOakHangingSignBehavior> DARK_OAK_HANGING_SIGN_TYPE;

  public static BlockType<BlockDarkOakLogBehavior> DARK_OAK_LOG_TYPE;

  public static BlockType<BlockDarkOakPlanksBehavior> DARK_OAK_PLANKS_TYPE;

  public static BlockType<BlockDarkOakPressurePlateBehavior> DARK_OAK_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockDarkOakStairsBehavior> DARK_OAK_STAIRS_TYPE;

  public static BlockType<BlockDarkOakTrapdoorBehavior> DARK_OAK_TRAPDOOR_TYPE;

  public static BlockType<BlockDarkPrismarineStairsBehavior> DARK_PRISMARINE_STAIRS_TYPE;

  public static BlockType<BlockDarkoakStandingSignBehavior> DARKOAK_STANDING_SIGN_TYPE;

  public static BlockType<BlockDarkoakWallSignBehavior> DARKOAK_WALL_SIGN_TYPE;

  public static BlockType<BlockDaylightDetectorBehavior> DAYLIGHT_DETECTOR_TYPE;

  public static BlockType<BlockDaylightDetectorInvertedBehavior> DAYLIGHT_DETECTOR_INVERTED_TYPE;

  public static BlockType<BlockDeadBrainCoralBehavior> DEAD_BRAIN_CORAL_TYPE;

  public static BlockType<BlockDeadBubbleCoralBehavior> DEAD_BUBBLE_CORAL_TYPE;

  public static BlockType<BlockDeadFireCoralBehavior> DEAD_FIRE_CORAL_TYPE;

  public static BlockType<BlockDeadHornCoralBehavior> DEAD_HORN_CORAL_TYPE;

  public static BlockType<BlockDeadTubeCoralBehavior> DEAD_TUBE_CORAL_TYPE;

  public static BlockType<BlockDeadbushBehavior> DEADBUSH_TYPE;

  public static BlockType<BlockDecoratedPotBehavior> DECORATED_POT_TYPE;

  public static BlockType<BlockDeepslateBehavior> DEEPSLATE_TYPE;

  public static BlockType<BlockDeepslateBrickDoubleSlabBehavior> DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockDeepslateBrickSlabBehavior> DEEPSLATE_BRICK_SLAB_TYPE;

  public static BlockType<BlockDeepslateBrickStairsBehavior> DEEPSLATE_BRICK_STAIRS_TYPE;

  public static BlockType<BlockDeepslateBrickWallBehavior> DEEPSLATE_BRICK_WALL_TYPE;

  public static BlockType<BlockDeepslateBricksBehavior> DEEPSLATE_BRICKS_TYPE;

  public static BlockType<BlockDeepslateCoalOreBehavior> DEEPSLATE_COAL_ORE_TYPE;

  public static BlockType<BlockDeepslateCopperOreBehavior> DEEPSLATE_COPPER_ORE_TYPE;

  public static BlockType<BlockDeepslateDiamondOreBehavior> DEEPSLATE_DIAMOND_ORE_TYPE;

  public static BlockType<BlockDeepslateEmeraldOreBehavior> DEEPSLATE_EMERALD_ORE_TYPE;

  public static BlockType<BlockDeepslateGoldOreBehavior> DEEPSLATE_GOLD_ORE_TYPE;

  public static BlockType<BlockDeepslateIronOreBehavior> DEEPSLATE_IRON_ORE_TYPE;

  public static BlockType<BlockDeepslateLapisOreBehavior> DEEPSLATE_LAPIS_ORE_TYPE;

  public static BlockType<BlockDeepslateRedstoneOreBehavior> DEEPSLATE_REDSTONE_ORE_TYPE;

  public static BlockType<BlockDeepslateTileDoubleSlabBehavior> DEEPSLATE_TILE_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockDeepslateTileSlabBehavior> DEEPSLATE_TILE_SLAB_TYPE;

  public static BlockType<BlockDeepslateTileStairsBehavior> DEEPSLATE_TILE_STAIRS_TYPE;

  public static BlockType<BlockDeepslateTileWallBehavior> DEEPSLATE_TILE_WALL_TYPE;

  public static BlockType<BlockDeepslateTilesBehavior> DEEPSLATE_TILES_TYPE;

  public static BlockType<BlockDenyBehavior> DENY_TYPE;

  public static BlockType<BlockDetectorRailBehavior> DETECTOR_RAIL_TYPE;

  public static BlockType<BlockDiamondBlockBehavior> DIAMOND_BLOCK_TYPE;

  public static BlockType<BlockDiamondOreBehavior> DIAMOND_ORE_TYPE;

  public static BlockType<BlockDioriteBehavior> DIORITE_TYPE;

  public static BlockType<BlockDioriteStairsBehavior> DIORITE_STAIRS_TYPE;

  public static BlockType<BlockDirtBehavior> DIRT_TYPE;

  public static BlockType<BlockDirtWithRootsBehavior> DIRT_WITH_ROOTS_TYPE;

  public static BlockType<BlockDispenserBehavior> DISPENSER_TYPE;

  public static BlockType<BlockDoubleCutCopperSlabBehavior> DOUBLE_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockDoublePlantBehavior> DOUBLE_PLANT_TYPE;

  public static BlockType<BlockDoubleStoneBlockSlabBehavior> DOUBLE_STONE_BLOCK_SLAB_TYPE;

  public static BlockType<BlockDoubleStoneBlockSlab2Behavior> DOUBLE_STONE_BLOCK_SLAB2_TYPE;

  public static BlockType<BlockDoubleStoneBlockSlab3Behavior> DOUBLE_STONE_BLOCK_SLAB3_TYPE;

  public static BlockType<BlockDoubleStoneBlockSlab4Behavior> DOUBLE_STONE_BLOCK_SLAB4_TYPE;

  public static BlockType<BlockDoubleWoodenSlabBehavior> DOUBLE_WOODEN_SLAB_TYPE;

  public static BlockType<BlockDragonEggBehavior> DRAGON_EGG_TYPE;

  public static BlockType<BlockDriedKelpBlockBehavior> DRIED_KELP_BLOCK_TYPE;

  public static BlockType<BlockDripstoneBlockBehavior> DRIPSTONE_BLOCK_TYPE;

  public static BlockType<BlockDropperBehavior> DROPPER_TYPE;

  public static BlockType<BlockElement0Behavior> ELEMENT_0_TYPE;

  public static BlockType<BlockElement1Behavior> ELEMENT_1_TYPE;

  public static BlockType<BlockElement10Behavior> ELEMENT_10_TYPE;

  public static BlockType<BlockElement100Behavior> ELEMENT_100_TYPE;

  public static BlockType<BlockElement101Behavior> ELEMENT_101_TYPE;

  public static BlockType<BlockElement102Behavior> ELEMENT_102_TYPE;

  public static BlockType<BlockElement103Behavior> ELEMENT_103_TYPE;

  public static BlockType<BlockElement104Behavior> ELEMENT_104_TYPE;

  public static BlockType<BlockElement105Behavior> ELEMENT_105_TYPE;

  public static BlockType<BlockElement106Behavior> ELEMENT_106_TYPE;

  public static BlockType<BlockElement107Behavior> ELEMENT_107_TYPE;

  public static BlockType<BlockElement108Behavior> ELEMENT_108_TYPE;

  public static BlockType<BlockElement109Behavior> ELEMENT_109_TYPE;

  public static BlockType<BlockElement11Behavior> ELEMENT_11_TYPE;

  public static BlockType<BlockElement110Behavior> ELEMENT_110_TYPE;

  public static BlockType<BlockElement111Behavior> ELEMENT_111_TYPE;

  public static BlockType<BlockElement112Behavior> ELEMENT_112_TYPE;

  public static BlockType<BlockElement113Behavior> ELEMENT_113_TYPE;

  public static BlockType<BlockElement114Behavior> ELEMENT_114_TYPE;

  public static BlockType<BlockElement115Behavior> ELEMENT_115_TYPE;

  public static BlockType<BlockElement116Behavior> ELEMENT_116_TYPE;

  public static BlockType<BlockElement117Behavior> ELEMENT_117_TYPE;

  public static BlockType<BlockElement118Behavior> ELEMENT_118_TYPE;

  public static BlockType<BlockElement12Behavior> ELEMENT_12_TYPE;

  public static BlockType<BlockElement13Behavior> ELEMENT_13_TYPE;

  public static BlockType<BlockElement14Behavior> ELEMENT_14_TYPE;

  public static BlockType<BlockElement15Behavior> ELEMENT_15_TYPE;

  public static BlockType<BlockElement16Behavior> ELEMENT_16_TYPE;

  public static BlockType<BlockElement17Behavior> ELEMENT_17_TYPE;

  public static BlockType<BlockElement18Behavior> ELEMENT_18_TYPE;

  public static BlockType<BlockElement19Behavior> ELEMENT_19_TYPE;

  public static BlockType<BlockElement2Behavior> ELEMENT_2_TYPE;

  public static BlockType<BlockElement20Behavior> ELEMENT_20_TYPE;

  public static BlockType<BlockElement21Behavior> ELEMENT_21_TYPE;

  public static BlockType<BlockElement22Behavior> ELEMENT_22_TYPE;

  public static BlockType<BlockElement23Behavior> ELEMENT_23_TYPE;

  public static BlockType<BlockElement24Behavior> ELEMENT_24_TYPE;

  public static BlockType<BlockElement25Behavior> ELEMENT_25_TYPE;

  public static BlockType<BlockElement26Behavior> ELEMENT_26_TYPE;

  public static BlockType<BlockElement27Behavior> ELEMENT_27_TYPE;

  public static BlockType<BlockElement28Behavior> ELEMENT_28_TYPE;

  public static BlockType<BlockElement29Behavior> ELEMENT_29_TYPE;

  public static BlockType<BlockElement3Behavior> ELEMENT_3_TYPE;

  public static BlockType<BlockElement30Behavior> ELEMENT_30_TYPE;

  public static BlockType<BlockElement31Behavior> ELEMENT_31_TYPE;

  public static BlockType<BlockElement32Behavior> ELEMENT_32_TYPE;

  public static BlockType<BlockElement33Behavior> ELEMENT_33_TYPE;

  public static BlockType<BlockElement34Behavior> ELEMENT_34_TYPE;

  public static BlockType<BlockElement35Behavior> ELEMENT_35_TYPE;

  public static BlockType<BlockElement36Behavior> ELEMENT_36_TYPE;

  public static BlockType<BlockElement37Behavior> ELEMENT_37_TYPE;

  public static BlockType<BlockElement38Behavior> ELEMENT_38_TYPE;

  public static BlockType<BlockElement39Behavior> ELEMENT_39_TYPE;

  public static BlockType<BlockElement4Behavior> ELEMENT_4_TYPE;

  public static BlockType<BlockElement40Behavior> ELEMENT_40_TYPE;

  public static BlockType<BlockElement41Behavior> ELEMENT_41_TYPE;

  public static BlockType<BlockElement42Behavior> ELEMENT_42_TYPE;

  public static BlockType<BlockElement43Behavior> ELEMENT_43_TYPE;

  public static BlockType<BlockElement44Behavior> ELEMENT_44_TYPE;

  public static BlockType<BlockElement45Behavior> ELEMENT_45_TYPE;

  public static BlockType<BlockElement46Behavior> ELEMENT_46_TYPE;

  public static BlockType<BlockElement47Behavior> ELEMENT_47_TYPE;

  public static BlockType<BlockElement48Behavior> ELEMENT_48_TYPE;

  public static BlockType<BlockElement49Behavior> ELEMENT_49_TYPE;

  public static BlockType<BlockElement5Behavior> ELEMENT_5_TYPE;

  public static BlockType<BlockElement50Behavior> ELEMENT_50_TYPE;

  public static BlockType<BlockElement51Behavior> ELEMENT_51_TYPE;

  public static BlockType<BlockElement52Behavior> ELEMENT_52_TYPE;

  public static BlockType<BlockElement53Behavior> ELEMENT_53_TYPE;

  public static BlockType<BlockElement54Behavior> ELEMENT_54_TYPE;

  public static BlockType<BlockElement55Behavior> ELEMENT_55_TYPE;

  public static BlockType<BlockElement56Behavior> ELEMENT_56_TYPE;

  public static BlockType<BlockElement57Behavior> ELEMENT_57_TYPE;

  public static BlockType<BlockElement58Behavior> ELEMENT_58_TYPE;

  public static BlockType<BlockElement59Behavior> ELEMENT_59_TYPE;

  public static BlockType<BlockElement6Behavior> ELEMENT_6_TYPE;

  public static BlockType<BlockElement60Behavior> ELEMENT_60_TYPE;

  public static BlockType<BlockElement61Behavior> ELEMENT_61_TYPE;

  public static BlockType<BlockElement62Behavior> ELEMENT_62_TYPE;

  public static BlockType<BlockElement63Behavior> ELEMENT_63_TYPE;

  public static BlockType<BlockElement64Behavior> ELEMENT_64_TYPE;

  public static BlockType<BlockElement65Behavior> ELEMENT_65_TYPE;

  public static BlockType<BlockElement66Behavior> ELEMENT_66_TYPE;

  public static BlockType<BlockElement67Behavior> ELEMENT_67_TYPE;

  public static BlockType<BlockElement68Behavior> ELEMENT_68_TYPE;

  public static BlockType<BlockElement69Behavior> ELEMENT_69_TYPE;

  public static BlockType<BlockElement7Behavior> ELEMENT_7_TYPE;

  public static BlockType<BlockElement70Behavior> ELEMENT_70_TYPE;

  public static BlockType<BlockElement71Behavior> ELEMENT_71_TYPE;

  public static BlockType<BlockElement72Behavior> ELEMENT_72_TYPE;

  public static BlockType<BlockElement73Behavior> ELEMENT_73_TYPE;

  public static BlockType<BlockElement74Behavior> ELEMENT_74_TYPE;

  public static BlockType<BlockElement75Behavior> ELEMENT_75_TYPE;

  public static BlockType<BlockElement76Behavior> ELEMENT_76_TYPE;

  public static BlockType<BlockElement77Behavior> ELEMENT_77_TYPE;

  public static BlockType<BlockElement78Behavior> ELEMENT_78_TYPE;

  public static BlockType<BlockElement79Behavior> ELEMENT_79_TYPE;

  public static BlockType<BlockElement8Behavior> ELEMENT_8_TYPE;

  public static BlockType<BlockElement80Behavior> ELEMENT_80_TYPE;

  public static BlockType<BlockElement81Behavior> ELEMENT_81_TYPE;

  public static BlockType<BlockElement82Behavior> ELEMENT_82_TYPE;

  public static BlockType<BlockElement83Behavior> ELEMENT_83_TYPE;

  public static BlockType<BlockElement84Behavior> ELEMENT_84_TYPE;

  public static BlockType<BlockElement85Behavior> ELEMENT_85_TYPE;

  public static BlockType<BlockElement86Behavior> ELEMENT_86_TYPE;

  public static BlockType<BlockElement87Behavior> ELEMENT_87_TYPE;

  public static BlockType<BlockElement88Behavior> ELEMENT_88_TYPE;

  public static BlockType<BlockElement89Behavior> ELEMENT_89_TYPE;

  public static BlockType<BlockElement9Behavior> ELEMENT_9_TYPE;

  public static BlockType<BlockElement90Behavior> ELEMENT_90_TYPE;

  public static BlockType<BlockElement91Behavior> ELEMENT_91_TYPE;

  public static BlockType<BlockElement92Behavior> ELEMENT_92_TYPE;

  public static BlockType<BlockElement93Behavior> ELEMENT_93_TYPE;

  public static BlockType<BlockElement94Behavior> ELEMENT_94_TYPE;

  public static BlockType<BlockElement95Behavior> ELEMENT_95_TYPE;

  public static BlockType<BlockElement96Behavior> ELEMENT_96_TYPE;

  public static BlockType<BlockElement97Behavior> ELEMENT_97_TYPE;

  public static BlockType<BlockElement98Behavior> ELEMENT_98_TYPE;

  public static BlockType<BlockElement99Behavior> ELEMENT_99_TYPE;

  public static BlockType<BlockEmeraldBlockBehavior> EMERALD_BLOCK_TYPE;

  public static BlockType<BlockEmeraldOreBehavior> EMERALD_ORE_TYPE;

  public static BlockType<BlockEnchantingTableBehavior> ENCHANTING_TABLE_TYPE;

  public static BlockType<BlockEndBrickStairsBehavior> END_BRICK_STAIRS_TYPE;

  public static BlockType<BlockEndBricksBehavior> END_BRICKS_TYPE;

  public static BlockType<BlockEndGatewayBehavior> END_GATEWAY_TYPE;

  public static BlockType<BlockEndPortalBehavior> END_PORTAL_TYPE;

  public static BlockType<BlockEndPortalFrameBehavior> END_PORTAL_FRAME_TYPE;

  public static BlockType<BlockEndRodBehavior> END_ROD_TYPE;

  public static BlockType<BlockEndStoneBehavior> END_STONE_TYPE;

  public static BlockType<BlockEnderChestBehavior> ENDER_CHEST_TYPE;

  public static BlockType<BlockExposedChiseledCopperBehavior> EXPOSED_CHISELED_COPPER_TYPE;

  public static BlockType<BlockExposedCopperBehavior> EXPOSED_COPPER_TYPE;

  public static BlockType<BlockExposedCopperBulbBehavior> EXPOSED_COPPER_BULB_TYPE;

  public static BlockType<BlockExposedCopperDoorBehavior> EXPOSED_COPPER_DOOR_TYPE;

  public static BlockType<BlockExposedCopperGrateBehavior> EXPOSED_COPPER_GRATE_TYPE;

  public static BlockType<BlockExposedCopperTrapdoorBehavior> EXPOSED_COPPER_TRAPDOOR_TYPE;

  public static BlockType<BlockExposedCutCopperBehavior> EXPOSED_CUT_COPPER_TYPE;

  public static BlockType<BlockExposedCutCopperSlabBehavior> EXPOSED_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockExposedCutCopperStairsBehavior> EXPOSED_CUT_COPPER_STAIRS_TYPE;

  public static BlockType<BlockExposedDoubleCutCopperSlabBehavior> EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockFarmlandBehavior> FARMLAND_TYPE;

  public static BlockType<BlockFenceGateBehavior> FENCE_GATE_TYPE;

  public static BlockType<BlockFireBehavior> FIRE_TYPE;

  public static BlockType<BlockFireCoralBehavior> FIRE_CORAL_TYPE;

  public static BlockType<BlockFletchingTableBehavior> FLETCHING_TABLE_TYPE;

  public static BlockType<BlockFlowerPotBehavior> FLOWER_POT_TYPE;

  public static BlockType<BlockFloweringAzaleaBehavior> FLOWERING_AZALEA_TYPE;

  public static BlockType<BlockFlowingLavaBehavior> FLOWING_LAVA_TYPE;

  public static BlockType<BlockFlowingWaterBehavior> FLOWING_WATER_TYPE;

  public static BlockType<BlockFrameBehavior> FRAME_TYPE;

  public static BlockType<BlockFrogSpawnBehavior> FROG_SPAWN_TYPE;

  public static BlockType<BlockFrostedIceBehavior> FROSTED_ICE_TYPE;

  public static BlockType<BlockFurnaceBehavior> FURNACE_TYPE;

  public static BlockType<BlockGildedBlackstoneBehavior> GILDED_BLACKSTONE_TYPE;

  public static BlockType<BlockGlassBehavior> GLASS_TYPE;

  public static BlockType<BlockGlassPaneBehavior> GLASS_PANE_TYPE;

  public static BlockType<BlockGlowFrameBehavior> GLOW_FRAME_TYPE;

  public static BlockType<BlockGlowLichenBehavior> GLOW_LICHEN_TYPE;

  public static BlockType<BlockGlowingobsidianBehavior> GLOWINGOBSIDIAN_TYPE;

  public static BlockType<BlockGlowstoneBehavior> GLOWSTONE_TYPE;

  public static BlockType<BlockGoldBlockBehavior> GOLD_BLOCK_TYPE;

  public static BlockType<BlockGoldOreBehavior> GOLD_ORE_TYPE;

  public static BlockType<BlockGoldenRailBehavior> GOLDEN_RAIL_TYPE;

  public static BlockType<BlockGraniteBehavior> GRANITE_TYPE;

  public static BlockType<BlockGraniteStairsBehavior> GRANITE_STAIRS_TYPE;

  public static BlockType<BlockGrassBehavior> GRASS_TYPE;

  public static BlockType<BlockGrassPathBehavior> GRASS_PATH_TYPE;

  public static BlockType<BlockGravelBehavior> GRAVEL_TYPE;

  public static BlockType<BlockGrayCandleBehavior> GRAY_CANDLE_TYPE;

  public static BlockType<BlockGrayCandleCakeBehavior> GRAY_CANDLE_CAKE_TYPE;

  public static BlockType<BlockGrayCarpetBehavior> GRAY_CARPET_TYPE;

  public static BlockType<BlockGrayConcreteBehavior> GRAY_CONCRETE_TYPE;

  public static BlockType<BlockGrayConcretePowderBehavior> GRAY_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockGrayGlazedTerracottaBehavior> GRAY_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockGrayShulkerBoxBehavior> GRAY_SHULKER_BOX_TYPE;

  public static BlockType<BlockGrayStainedGlassBehavior> GRAY_STAINED_GLASS_TYPE;

  public static BlockType<BlockGrayStainedGlassPaneBehavior> GRAY_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockGrayTerracottaBehavior> GRAY_TERRACOTTA_TYPE;

  public static BlockType<BlockGrayWoolBehavior> GRAY_WOOL_TYPE;

  public static BlockType<BlockGreenCandleBehavior> GREEN_CANDLE_TYPE;

  public static BlockType<BlockGreenCandleCakeBehavior> GREEN_CANDLE_CAKE_TYPE;

  public static BlockType<BlockGreenCarpetBehavior> GREEN_CARPET_TYPE;

  public static BlockType<BlockGreenConcreteBehavior> GREEN_CONCRETE_TYPE;

  public static BlockType<BlockGreenConcretePowderBehavior> GREEN_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockGreenGlazedTerracottaBehavior> GREEN_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockGreenShulkerBoxBehavior> GREEN_SHULKER_BOX_TYPE;

  public static BlockType<BlockGreenStainedGlassBehavior> GREEN_STAINED_GLASS_TYPE;

  public static BlockType<BlockGreenStainedGlassPaneBehavior> GREEN_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockGreenTerracottaBehavior> GREEN_TERRACOTTA_TYPE;

  public static BlockType<BlockGreenWoolBehavior> GREEN_WOOL_TYPE;

  public static BlockType<BlockGrindstoneBehavior> GRINDSTONE_TYPE;

  public static BlockType<BlockHangingRootsBehavior> HANGING_ROOTS_TYPE;

  public static BlockType<BlockHardBlackStainedGlassBehavior> HARD_BLACK_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardBlackStainedGlassPaneBehavior> HARD_BLACK_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardBlueStainedGlassBehavior> HARD_BLUE_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardBlueStainedGlassPaneBehavior> HARD_BLUE_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardBrownStainedGlassBehavior> HARD_BROWN_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardBrownStainedGlassPaneBehavior> HARD_BROWN_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardCyanStainedGlassBehavior> HARD_CYAN_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardCyanStainedGlassPaneBehavior> HARD_CYAN_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardGlassBehavior> HARD_GLASS_TYPE;

  public static BlockType<BlockHardGlassPaneBehavior> HARD_GLASS_PANE_TYPE;

  public static BlockType<BlockHardGrayStainedGlassBehavior> HARD_GRAY_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardGrayStainedGlassPaneBehavior> HARD_GRAY_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardGreenStainedGlassBehavior> HARD_GREEN_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardGreenStainedGlassPaneBehavior> HARD_GREEN_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardLightBlueStainedGlassBehavior> HARD_LIGHT_BLUE_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardLightBlueStainedGlassPaneBehavior> HARD_LIGHT_BLUE_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardLightGrayStainedGlassBehavior> HARD_LIGHT_GRAY_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardLightGrayStainedGlassPaneBehavior> HARD_LIGHT_GRAY_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardLimeStainedGlassBehavior> HARD_LIME_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardLimeStainedGlassPaneBehavior> HARD_LIME_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardMagentaStainedGlassBehavior> HARD_MAGENTA_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardMagentaStainedGlassPaneBehavior> HARD_MAGENTA_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardOrangeStainedGlassBehavior> HARD_ORANGE_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardOrangeStainedGlassPaneBehavior> HARD_ORANGE_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardPinkStainedGlassBehavior> HARD_PINK_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardPinkStainedGlassPaneBehavior> HARD_PINK_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardPurpleStainedGlassBehavior> HARD_PURPLE_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardPurpleStainedGlassPaneBehavior> HARD_PURPLE_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardRedStainedGlassBehavior> HARD_RED_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardRedStainedGlassPaneBehavior> HARD_RED_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardWhiteStainedGlassBehavior> HARD_WHITE_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardWhiteStainedGlassPaneBehavior> HARD_WHITE_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardYellowStainedGlassBehavior> HARD_YELLOW_STAINED_GLASS_TYPE;

  public static BlockType<BlockHardYellowStainedGlassPaneBehavior> HARD_YELLOW_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockHardenedClayBehavior> HARDENED_CLAY_TYPE;

  public static BlockType<BlockHayBlockBehavior> HAY_BLOCK_TYPE;

  public static BlockType<BlockHeavyWeightedPressurePlateBehavior> HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockHoneyBlockBehavior> HONEY_BLOCK_TYPE;

  public static BlockType<BlockHoneycombBlockBehavior> HONEYCOMB_BLOCK_TYPE;

  public static BlockType<BlockHopperBehavior> HOPPER_TYPE;

  public static BlockType<BlockHornCoralBehavior> HORN_CORAL_TYPE;

  public static BlockType<BlockIceBehavior> ICE_TYPE;

  public static BlockType<BlockInfestedDeepslateBehavior> INFESTED_DEEPSLATE_TYPE;

  public static BlockType<BlockInfoUpdateBehavior> INFO_UPDATE_TYPE;

  public static BlockType<BlockInfoUpdate2Behavior> INFO_UPDATE2_TYPE;

  public static BlockType<BlockInvisibleBedrockBehavior> INVISIBLE_BEDROCK_TYPE;

  public static BlockType<BlockIronBarsBehavior> IRON_BARS_TYPE;

  public static BlockType<BlockIronBlockBehavior> IRON_BLOCK_TYPE;

  public static BlockType<BlockIronDoorBehavior> IRON_DOOR_TYPE;

  public static BlockType<BlockIronOreBehavior> IRON_ORE_TYPE;

  public static BlockType<BlockIronTrapdoorBehavior> IRON_TRAPDOOR_TYPE;

  public static BlockType<BlockJigsawBehavior> JIGSAW_TYPE;

  public static BlockType<BlockJukeboxBehavior> JUKEBOX_TYPE;

  public static BlockType<BlockJungleButtonBehavior> JUNGLE_BUTTON_TYPE;

  public static BlockType<BlockJungleDoorBehavior> JUNGLE_DOOR_TYPE;

  public static BlockType<BlockJungleFenceBehavior> JUNGLE_FENCE_TYPE;

  public static BlockType<BlockJungleFenceGateBehavior> JUNGLE_FENCE_GATE_TYPE;

  public static BlockType<BlockJungleHangingSignBehavior> JUNGLE_HANGING_SIGN_TYPE;

  public static BlockType<BlockJungleLogBehavior> JUNGLE_LOG_TYPE;

  public static BlockType<BlockJunglePlanksBehavior> JUNGLE_PLANKS_TYPE;

  public static BlockType<BlockJunglePressurePlateBehavior> JUNGLE_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockJungleStairsBehavior> JUNGLE_STAIRS_TYPE;

  public static BlockType<BlockJungleStandingSignBehavior> JUNGLE_STANDING_SIGN_TYPE;

  public static BlockType<BlockJungleTrapdoorBehavior> JUNGLE_TRAPDOOR_TYPE;

  public static BlockType<BlockJungleWallSignBehavior> JUNGLE_WALL_SIGN_TYPE;

  public static BlockType<BlockKelpBehavior> KELP_TYPE;

  public static BlockType<BlockLadderBehavior> LADDER_TYPE;

  public static BlockType<BlockLanternBehavior> LANTERN_TYPE;

  public static BlockType<BlockLapisBlockBehavior> LAPIS_BLOCK_TYPE;

  public static BlockType<BlockLapisOreBehavior> LAPIS_ORE_TYPE;

  public static BlockType<BlockLargeAmethystBudBehavior> LARGE_AMETHYST_BUD_TYPE;

  public static BlockType<BlockLavaBehavior> LAVA_TYPE;

  public static BlockType<BlockLeavesBehavior> LEAVES_TYPE;

  public static BlockType<BlockLeaves2Behavior> LEAVES2_TYPE;

  public static BlockType<BlockLecternBehavior> LECTERN_TYPE;

  public static BlockType<BlockLeverBehavior> LEVER_TYPE;

  public static BlockType<BlockLightBlockBehavior> LIGHT_BLOCK_TYPE;

  public static BlockType<BlockLightBlueCandleBehavior> LIGHT_BLUE_CANDLE_TYPE;

  public static BlockType<BlockLightBlueCandleCakeBehavior> LIGHT_BLUE_CANDLE_CAKE_TYPE;

  public static BlockType<BlockLightBlueCarpetBehavior> LIGHT_BLUE_CARPET_TYPE;

  public static BlockType<BlockLightBlueConcreteBehavior> LIGHT_BLUE_CONCRETE_TYPE;

  public static BlockType<BlockLightBlueConcretePowderBehavior> LIGHT_BLUE_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockLightBlueGlazedTerracottaBehavior> LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockLightBlueShulkerBoxBehavior> LIGHT_BLUE_SHULKER_BOX_TYPE;

  public static BlockType<BlockLightBlueStainedGlassBehavior> LIGHT_BLUE_STAINED_GLASS_TYPE;

  public static BlockType<BlockLightBlueStainedGlassPaneBehavior> LIGHT_BLUE_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockLightBlueTerracottaBehavior> LIGHT_BLUE_TERRACOTTA_TYPE;

  public static BlockType<BlockLightBlueWoolBehavior> LIGHT_BLUE_WOOL_TYPE;

  public static BlockType<BlockLightGrayCandleBehavior> LIGHT_GRAY_CANDLE_TYPE;

  public static BlockType<BlockLightGrayCandleCakeBehavior> LIGHT_GRAY_CANDLE_CAKE_TYPE;

  public static BlockType<BlockLightGrayCarpetBehavior> LIGHT_GRAY_CARPET_TYPE;

  public static BlockType<BlockLightGrayConcreteBehavior> LIGHT_GRAY_CONCRETE_TYPE;

  public static BlockType<BlockLightGrayConcretePowderBehavior> LIGHT_GRAY_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockLightGrayShulkerBoxBehavior> LIGHT_GRAY_SHULKER_BOX_TYPE;

  public static BlockType<BlockLightGrayStainedGlassBehavior> LIGHT_GRAY_STAINED_GLASS_TYPE;

  public static BlockType<BlockLightGrayStainedGlassPaneBehavior> LIGHT_GRAY_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockLightGrayTerracottaBehavior> LIGHT_GRAY_TERRACOTTA_TYPE;

  public static BlockType<BlockLightGrayWoolBehavior> LIGHT_GRAY_WOOL_TYPE;

  public static BlockType<BlockLightWeightedPressurePlateBehavior> LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockLightningRodBehavior> LIGHTNING_ROD_TYPE;

  public static BlockType<BlockLimeCandleBehavior> LIME_CANDLE_TYPE;

  public static BlockType<BlockLimeCandleCakeBehavior> LIME_CANDLE_CAKE_TYPE;

  public static BlockType<BlockLimeCarpetBehavior> LIME_CARPET_TYPE;

  public static BlockType<BlockLimeConcreteBehavior> LIME_CONCRETE_TYPE;

  public static BlockType<BlockLimeConcretePowderBehavior> LIME_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockLimeGlazedTerracottaBehavior> LIME_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockLimeShulkerBoxBehavior> LIME_SHULKER_BOX_TYPE;

  public static BlockType<BlockLimeStainedGlassBehavior> LIME_STAINED_GLASS_TYPE;

  public static BlockType<BlockLimeStainedGlassPaneBehavior> LIME_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockLimeTerracottaBehavior> LIME_TERRACOTTA_TYPE;

  public static BlockType<BlockLimeWoolBehavior> LIME_WOOL_TYPE;

  public static BlockType<BlockLitBlastFurnaceBehavior> LIT_BLAST_FURNACE_TYPE;

  public static BlockType<BlockLitDeepslateRedstoneOreBehavior> LIT_DEEPSLATE_REDSTONE_ORE_TYPE;

  public static BlockType<BlockLitFurnaceBehavior> LIT_FURNACE_TYPE;

  public static BlockType<BlockLitPumpkinBehavior> LIT_PUMPKIN_TYPE;

  public static BlockType<BlockLitRedstoneLampBehavior> LIT_REDSTONE_LAMP_TYPE;

  public static BlockType<BlockLitRedstoneOreBehavior> LIT_REDSTONE_ORE_TYPE;

  public static BlockType<BlockLitSmokerBehavior> LIT_SMOKER_TYPE;

  public static BlockType<BlockLodestoneBehavior> LODESTONE_TYPE;

  public static BlockType<BlockLoomBehavior> LOOM_TYPE;

  public static BlockType<BlockMagentaCandleBehavior> MAGENTA_CANDLE_TYPE;

  public static BlockType<BlockMagentaCandleCakeBehavior> MAGENTA_CANDLE_CAKE_TYPE;

  public static BlockType<BlockMagentaCarpetBehavior> MAGENTA_CARPET_TYPE;

  public static BlockType<BlockMagentaConcreteBehavior> MAGENTA_CONCRETE_TYPE;

  public static BlockType<BlockMagentaConcretePowderBehavior> MAGENTA_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockMagentaGlazedTerracottaBehavior> MAGENTA_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockMagentaShulkerBoxBehavior> MAGENTA_SHULKER_BOX_TYPE;

  public static BlockType<BlockMagentaStainedGlassBehavior> MAGENTA_STAINED_GLASS_TYPE;

  public static BlockType<BlockMagentaStainedGlassPaneBehavior> MAGENTA_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockMagentaTerracottaBehavior> MAGENTA_TERRACOTTA_TYPE;

  public static BlockType<BlockMagentaWoolBehavior> MAGENTA_WOOL_TYPE;

  public static BlockType<BlockMagmaBehavior> MAGMA_TYPE;

  public static BlockType<BlockMangroveButtonBehavior> MANGROVE_BUTTON_TYPE;

  public static BlockType<BlockMangroveDoorBehavior> MANGROVE_DOOR_TYPE;

  public static BlockType<BlockMangroveDoubleSlabBehavior> MANGROVE_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockMangroveFenceBehavior> MANGROVE_FENCE_TYPE;

  public static BlockType<BlockMangroveFenceGateBehavior> MANGROVE_FENCE_GATE_TYPE;

  public static BlockType<BlockMangroveHangingSignBehavior> MANGROVE_HANGING_SIGN_TYPE;

  public static BlockType<BlockMangroveLeavesBehavior> MANGROVE_LEAVES_TYPE;

  public static BlockType<BlockMangroveLogBehavior> MANGROVE_LOG_TYPE;

  public static BlockType<BlockMangrovePlanksBehavior> MANGROVE_PLANKS_TYPE;

  public static BlockType<BlockMangrovePressurePlateBehavior> MANGROVE_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockMangrovePropaguleBehavior> MANGROVE_PROPAGULE_TYPE;

  public static BlockType<BlockMangroveRootsBehavior> MANGROVE_ROOTS_TYPE;

  public static BlockType<BlockMangroveSlabBehavior> MANGROVE_SLAB_TYPE;

  public static BlockType<BlockMangroveStairsBehavior> MANGROVE_STAIRS_TYPE;

  public static BlockType<BlockMangroveStandingSignBehavior> MANGROVE_STANDING_SIGN_TYPE;

  public static BlockType<BlockMangroveTrapdoorBehavior> MANGROVE_TRAPDOOR_TYPE;

  public static BlockType<BlockMangroveWallSignBehavior> MANGROVE_WALL_SIGN_TYPE;

  public static BlockType<BlockMangroveWoodBehavior> MANGROVE_WOOD_TYPE;

  public static BlockType<BlockMediumAmethystBudBehavior> MEDIUM_AMETHYST_BUD_TYPE;

  public static BlockType<BlockMelonBlockBehavior> MELON_BLOCK_TYPE;

  public static BlockType<BlockMelonStemBehavior> MELON_STEM_TYPE;

  public static BlockType<BlockMobSpawnerBehavior> MOB_SPAWNER_TYPE;

  public static BlockType<BlockMonsterEggBehavior> MONSTER_EGG_TYPE;

  public static BlockType<BlockMossBlockBehavior> MOSS_BLOCK_TYPE;

  public static BlockType<BlockMossCarpetBehavior> MOSS_CARPET_TYPE;

  public static BlockType<BlockMossyCobblestoneBehavior> MOSSY_COBBLESTONE_TYPE;

  public static BlockType<BlockMossyCobblestoneStairsBehavior> MOSSY_COBBLESTONE_STAIRS_TYPE;

  public static BlockType<BlockMossyStoneBrickStairsBehavior> MOSSY_STONE_BRICK_STAIRS_TYPE;

  public static BlockType<BlockMovingBlockBehavior> MOVING_BLOCK_TYPE;

  public static BlockType<BlockMudBehavior> MUD_TYPE;

  public static BlockType<BlockMudBrickDoubleSlabBehavior> MUD_BRICK_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockMudBrickSlabBehavior> MUD_BRICK_SLAB_TYPE;

  public static BlockType<BlockMudBrickStairsBehavior> MUD_BRICK_STAIRS_TYPE;

  public static BlockType<BlockMudBrickWallBehavior> MUD_BRICK_WALL_TYPE;

  public static BlockType<BlockMudBricksBehavior> MUD_BRICKS_TYPE;

  public static BlockType<BlockMuddyMangroveRootsBehavior> MUDDY_MANGROVE_ROOTS_TYPE;

  public static BlockType<BlockMyceliumBehavior> MYCELIUM_TYPE;

  public static BlockType<BlockNetherBrickBehavior> NETHER_BRICK_TYPE;

  public static BlockType<BlockNetherBrickFenceBehavior> NETHER_BRICK_FENCE_TYPE;

  public static BlockType<BlockNetherBrickStairsBehavior> NETHER_BRICK_STAIRS_TYPE;

  public static BlockType<BlockNetherGoldOreBehavior> NETHER_GOLD_ORE_TYPE;

  public static BlockType<BlockNetherSproutsBehavior> NETHER_SPROUTS_TYPE;

  public static BlockType<BlockNetherWartBehavior> NETHER_WART_TYPE;

  public static BlockType<BlockNetherWartBlockBehavior> NETHER_WART_BLOCK_TYPE;

  public static BlockType<BlockNetheriteBlockBehavior> NETHERITE_BLOCK_TYPE;

  public static BlockType<BlockNetherrackBehavior> NETHERRACK_TYPE;

  public static BlockType<BlockNetherreactorBehavior> NETHERREACTOR_TYPE;

  public static BlockType<BlockNormalStoneStairsBehavior> NORMAL_STONE_STAIRS_TYPE;

  public static BlockType<BlockNoteblockBehavior> NOTEBLOCK_TYPE;

  public static BlockType<BlockOakFenceBehavior> OAK_FENCE_TYPE;

  public static BlockType<BlockOakHangingSignBehavior> OAK_HANGING_SIGN_TYPE;

  public static BlockType<BlockOakLogBehavior> OAK_LOG_TYPE;

  public static BlockType<BlockOakPlanksBehavior> OAK_PLANKS_TYPE;

  public static BlockType<BlockOakStairsBehavior> OAK_STAIRS_TYPE;

  public static BlockType<BlockObserverBehavior> OBSERVER_TYPE;

  public static BlockType<BlockObsidianBehavior> OBSIDIAN_TYPE;

  public static BlockType<BlockOchreFroglightBehavior> OCHRE_FROGLIGHT_TYPE;

  public static BlockType<BlockOrangeCandleBehavior> ORANGE_CANDLE_TYPE;

  public static BlockType<BlockOrangeCandleCakeBehavior> ORANGE_CANDLE_CAKE_TYPE;

  public static BlockType<BlockOrangeCarpetBehavior> ORANGE_CARPET_TYPE;

  public static BlockType<BlockOrangeConcreteBehavior> ORANGE_CONCRETE_TYPE;

  public static BlockType<BlockOrangeConcretePowderBehavior> ORANGE_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockOrangeGlazedTerracottaBehavior> ORANGE_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockOrangeShulkerBoxBehavior> ORANGE_SHULKER_BOX_TYPE;

  public static BlockType<BlockOrangeStainedGlassBehavior> ORANGE_STAINED_GLASS_TYPE;

  public static BlockType<BlockOrangeStainedGlassPaneBehavior> ORANGE_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockOrangeTerracottaBehavior> ORANGE_TERRACOTTA_TYPE;

  public static BlockType<BlockOrangeWoolBehavior> ORANGE_WOOL_TYPE;

  public static BlockType<BlockOxidizedChiseledCopperBehavior> OXIDIZED_CHISELED_COPPER_TYPE;

  public static BlockType<BlockOxidizedCopperBehavior> OXIDIZED_COPPER_TYPE;

  public static BlockType<BlockOxidizedCopperBulbBehavior> OXIDIZED_COPPER_BULB_TYPE;

  public static BlockType<BlockOxidizedCopperDoorBehavior> OXIDIZED_COPPER_DOOR_TYPE;

  public static BlockType<BlockOxidizedCopperGrateBehavior> OXIDIZED_COPPER_GRATE_TYPE;

  public static BlockType<BlockOxidizedCopperTrapdoorBehavior> OXIDIZED_COPPER_TRAPDOOR_TYPE;

  public static BlockType<BlockOxidizedCutCopperBehavior> OXIDIZED_CUT_COPPER_TYPE;

  public static BlockType<BlockOxidizedCutCopperSlabBehavior> OXIDIZED_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockOxidizedCutCopperStairsBehavior> OXIDIZED_CUT_COPPER_STAIRS_TYPE;

  public static BlockType<BlockOxidizedDoubleCutCopperSlabBehavior> OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockPackedIceBehavior> PACKED_ICE_TYPE;

  public static BlockType<BlockPackedMudBehavior> PACKED_MUD_TYPE;

  public static BlockType<BlockPearlescentFroglightBehavior> PEARLESCENT_FROGLIGHT_TYPE;

  public static BlockType<BlockPinkCandleBehavior> PINK_CANDLE_TYPE;

  public static BlockType<BlockPinkCandleCakeBehavior> PINK_CANDLE_CAKE_TYPE;

  public static BlockType<BlockPinkCarpetBehavior> PINK_CARPET_TYPE;

  public static BlockType<BlockPinkConcreteBehavior> PINK_CONCRETE_TYPE;

  public static BlockType<BlockPinkConcretePowderBehavior> PINK_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockPinkGlazedTerracottaBehavior> PINK_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockPinkPetalsBehavior> PINK_PETALS_TYPE;

  public static BlockType<BlockPinkShulkerBoxBehavior> PINK_SHULKER_BOX_TYPE;

  public static BlockType<BlockPinkStainedGlassBehavior> PINK_STAINED_GLASS_TYPE;

  public static BlockType<BlockPinkStainedGlassPaneBehavior> PINK_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockPinkTerracottaBehavior> PINK_TERRACOTTA_TYPE;

  public static BlockType<BlockPinkWoolBehavior> PINK_WOOL_TYPE;

  public static BlockType<BlockPistonBehavior> PISTON_TYPE;

  public static BlockType<BlockPistonArmCollisionBehavior> PISTON_ARM_COLLISION_TYPE;

  public static BlockType<BlockPitcherCropBehavior> PITCHER_CROP_TYPE;

  public static BlockType<BlockPitcherPlantBehavior> PITCHER_PLANT_TYPE;

  public static BlockType<BlockPodzolBehavior> PODZOL_TYPE;

  public static BlockType<BlockPointedDripstoneBehavior> POINTED_DRIPSTONE_TYPE;

  public static BlockType<BlockPolishedAndesiteBehavior> POLISHED_ANDESITE_TYPE;

  public static BlockType<BlockPolishedAndesiteStairsBehavior> POLISHED_ANDESITE_STAIRS_TYPE;

  public static BlockType<BlockPolishedBasaltBehavior> POLISHED_BASALT_TYPE;

  public static BlockType<BlockPolishedBlackstoneBehavior> POLISHED_BLACKSTONE_TYPE;

  public static BlockType<BlockPolishedBlackstoneBrickDoubleSlabBehavior> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockPolishedBlackstoneBrickSlabBehavior> POLISHED_BLACKSTONE_BRICK_SLAB_TYPE;

  public static BlockType<BlockPolishedBlackstoneBrickStairsBehavior> POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE;

  public static BlockType<BlockPolishedBlackstoneBrickWallBehavior> POLISHED_BLACKSTONE_BRICK_WALL_TYPE;

  public static BlockType<BlockPolishedBlackstoneBricksBehavior> POLISHED_BLACKSTONE_BRICKS_TYPE;

  public static BlockType<BlockPolishedBlackstoneButtonBehavior> POLISHED_BLACKSTONE_BUTTON_TYPE;

  public static BlockType<BlockPolishedBlackstoneDoubleSlabBehavior> POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockPolishedBlackstonePressurePlateBehavior> POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockPolishedBlackstoneSlabBehavior> POLISHED_BLACKSTONE_SLAB_TYPE;

  public static BlockType<BlockPolishedBlackstoneStairsBehavior> POLISHED_BLACKSTONE_STAIRS_TYPE;

  public static BlockType<BlockPolishedBlackstoneWallBehavior> POLISHED_BLACKSTONE_WALL_TYPE;

  public static BlockType<BlockPolishedDeepslateBehavior> POLISHED_DEEPSLATE_TYPE;

  public static BlockType<BlockPolishedDeepslateDoubleSlabBehavior> POLISHED_DEEPSLATE_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockPolishedDeepslateSlabBehavior> POLISHED_DEEPSLATE_SLAB_TYPE;

  public static BlockType<BlockPolishedDeepslateStairsBehavior> POLISHED_DEEPSLATE_STAIRS_TYPE;

  public static BlockType<BlockPolishedDeepslateWallBehavior> POLISHED_DEEPSLATE_WALL_TYPE;

  public static BlockType<BlockPolishedDioriteBehavior> POLISHED_DIORITE_TYPE;

  public static BlockType<BlockPolishedDioriteStairsBehavior> POLISHED_DIORITE_STAIRS_TYPE;

  public static BlockType<BlockPolishedGraniteBehavior> POLISHED_GRANITE_TYPE;

  public static BlockType<BlockPolishedGraniteStairsBehavior> POLISHED_GRANITE_STAIRS_TYPE;

  public static BlockType<BlockPolishedTuffBehavior> POLISHED_TUFF_TYPE;

  public static BlockType<BlockPolishedTuffDoubleSlabBehavior> POLISHED_TUFF_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockPolishedTuffSlabBehavior> POLISHED_TUFF_SLAB_TYPE;

  public static BlockType<BlockPolishedTuffStairsBehavior> POLISHED_TUFF_STAIRS_TYPE;

  public static BlockType<BlockPolishedTuffWallBehavior> POLISHED_TUFF_WALL_TYPE;

  public static BlockType<BlockPortalBehavior> PORTAL_TYPE;

  public static BlockType<BlockPotatoesBehavior> POTATOES_TYPE;

  public static BlockType<BlockPowderSnowBehavior> POWDER_SNOW_TYPE;

  public static BlockType<BlockPoweredComparatorBehavior> POWERED_COMPARATOR_TYPE;

  public static BlockType<BlockPoweredRepeaterBehavior> POWERED_REPEATER_TYPE;

  public static BlockType<BlockPrismarineBehavior> PRISMARINE_TYPE;

  public static BlockType<BlockPrismarineBricksStairsBehavior> PRISMARINE_BRICKS_STAIRS_TYPE;

  public static BlockType<BlockPrismarineStairsBehavior> PRISMARINE_STAIRS_TYPE;

  public static BlockType<BlockPumpkinBehavior> PUMPKIN_TYPE;

  public static BlockType<BlockPumpkinStemBehavior> PUMPKIN_STEM_TYPE;

  public static BlockType<BlockPurpleCandleBehavior> PURPLE_CANDLE_TYPE;

  public static BlockType<BlockPurpleCandleCakeBehavior> PURPLE_CANDLE_CAKE_TYPE;

  public static BlockType<BlockPurpleCarpetBehavior> PURPLE_CARPET_TYPE;

  public static BlockType<BlockPurpleConcreteBehavior> PURPLE_CONCRETE_TYPE;

  public static BlockType<BlockPurpleConcretePowderBehavior> PURPLE_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockPurpleGlazedTerracottaBehavior> PURPLE_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockPurpleShulkerBoxBehavior> PURPLE_SHULKER_BOX_TYPE;

  public static BlockType<BlockPurpleStainedGlassBehavior> PURPLE_STAINED_GLASS_TYPE;

  public static BlockType<BlockPurpleStainedGlassPaneBehavior> PURPLE_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockPurpleTerracottaBehavior> PURPLE_TERRACOTTA_TYPE;

  public static BlockType<BlockPurpleWoolBehavior> PURPLE_WOOL_TYPE;

  public static BlockType<BlockPurpurBlockBehavior> PURPUR_BLOCK_TYPE;

  public static BlockType<BlockPurpurStairsBehavior> PURPUR_STAIRS_TYPE;

  public static BlockType<BlockQuartzBlockBehavior> QUARTZ_BLOCK_TYPE;

  public static BlockType<BlockQuartzBricksBehavior> QUARTZ_BRICKS_TYPE;

  public static BlockType<BlockQuartzOreBehavior> QUARTZ_ORE_TYPE;

  public static BlockType<BlockQuartzStairsBehavior> QUARTZ_STAIRS_TYPE;

  public static BlockType<BlockRailBehavior> RAIL_TYPE;

  public static BlockType<BlockRawCopperBlockBehavior> RAW_COPPER_BLOCK_TYPE;

  public static BlockType<BlockRawGoldBlockBehavior> RAW_GOLD_BLOCK_TYPE;

  public static BlockType<BlockRawIronBlockBehavior> RAW_IRON_BLOCK_TYPE;

  public static BlockType<BlockRedCandleBehavior> RED_CANDLE_TYPE;

  public static BlockType<BlockRedCandleCakeBehavior> RED_CANDLE_CAKE_TYPE;

  public static BlockType<BlockRedCarpetBehavior> RED_CARPET_TYPE;

  public static BlockType<BlockRedConcreteBehavior> RED_CONCRETE_TYPE;

  public static BlockType<BlockRedConcretePowderBehavior> RED_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockRedFlowerBehavior> RED_FLOWER_TYPE;

  public static BlockType<BlockRedGlazedTerracottaBehavior> RED_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockRedMushroomBehavior> RED_MUSHROOM_TYPE;

  public static BlockType<BlockRedMushroomBlockBehavior> RED_MUSHROOM_BLOCK_TYPE;

  public static BlockType<BlockRedNetherBrickBehavior> RED_NETHER_BRICK_TYPE;

  public static BlockType<BlockRedNetherBrickStairsBehavior> RED_NETHER_BRICK_STAIRS_TYPE;

  public static BlockType<BlockRedSandstoneBehavior> RED_SANDSTONE_TYPE;

  public static BlockType<BlockRedSandstoneStairsBehavior> RED_SANDSTONE_STAIRS_TYPE;

  public static BlockType<BlockRedShulkerBoxBehavior> RED_SHULKER_BOX_TYPE;

  public static BlockType<BlockRedStainedGlassBehavior> RED_STAINED_GLASS_TYPE;

  public static BlockType<BlockRedStainedGlassPaneBehavior> RED_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockRedTerracottaBehavior> RED_TERRACOTTA_TYPE;

  public static BlockType<BlockRedWoolBehavior> RED_WOOL_TYPE;

  public static BlockType<BlockRedstoneBlockBehavior> REDSTONE_BLOCK_TYPE;

  public static BlockType<BlockRedstoneLampBehavior> REDSTONE_LAMP_TYPE;

  public static BlockType<BlockRedstoneOreBehavior> REDSTONE_ORE_TYPE;

  public static BlockType<BlockRedstoneTorchBehavior> REDSTONE_TORCH_TYPE;

  public static BlockType<BlockRedstoneWireBehavior> REDSTONE_WIRE_TYPE;

  public static BlockType<BlockReedsBehavior> REEDS_TYPE;

  public static BlockType<BlockReinforcedDeepslateBehavior> REINFORCED_DEEPSLATE_TYPE;

  public static BlockType<BlockRepeatingCommandBlockBehavior> REPEATING_COMMAND_BLOCK_TYPE;

  public static BlockType<BlockReserved6Behavior> RESERVED6_TYPE;

  public static BlockType<BlockRespawnAnchorBehavior> RESPAWN_ANCHOR_TYPE;

  public static BlockType<BlockSandBehavior> SAND_TYPE;

  public static BlockType<BlockSandstoneBehavior> SANDSTONE_TYPE;

  public static BlockType<BlockSandstoneStairsBehavior> SANDSTONE_STAIRS_TYPE;

  public static BlockType<BlockSaplingBehavior> SAPLING_TYPE;

  public static BlockType<BlockScaffoldingBehavior> SCAFFOLDING_TYPE;

  public static BlockType<BlockSculkBehavior> SCULK_TYPE;

  public static BlockType<BlockSculkCatalystBehavior> SCULK_CATALYST_TYPE;

  public static BlockType<BlockSculkSensorBehavior> SCULK_SENSOR_TYPE;

  public static BlockType<BlockSculkShriekerBehavior> SCULK_SHRIEKER_TYPE;

  public static BlockType<BlockSculkVeinBehavior> SCULK_VEIN_TYPE;

  public static BlockType<BlockSeaLanternBehavior> SEA_LANTERN_TYPE;

  public static BlockType<BlockSeaPickleBehavior> SEA_PICKLE_TYPE;

  public static BlockType<BlockSeagrassBehavior> SEAGRASS_TYPE;

  public static BlockType<BlockShroomlightBehavior> SHROOMLIGHT_TYPE;

  public static BlockType<BlockSilverGlazedTerracottaBehavior> SILVER_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockSkullBehavior> SKULL_TYPE;

  public static BlockType<BlockSlimeBehavior> SLIME_TYPE;

  public static BlockType<BlockSmallAmethystBudBehavior> SMALL_AMETHYST_BUD_TYPE;

  public static BlockType<BlockSmallDripleafBlockBehavior> SMALL_DRIPLEAF_BLOCK_TYPE;

  public static BlockType<BlockSmithingTableBehavior> SMITHING_TABLE_TYPE;

  public static BlockType<BlockSmokerBehavior> SMOKER_TYPE;

  public static BlockType<BlockSmoothBasaltBehavior> SMOOTH_BASALT_TYPE;

  public static BlockType<BlockSmoothQuartzStairsBehavior> SMOOTH_QUARTZ_STAIRS_TYPE;

  public static BlockType<BlockSmoothRedSandstoneStairsBehavior> SMOOTH_RED_SANDSTONE_STAIRS_TYPE;

  public static BlockType<BlockSmoothSandstoneStairsBehavior> SMOOTH_SANDSTONE_STAIRS_TYPE;

  public static BlockType<BlockSmoothStoneBehavior> SMOOTH_STONE_TYPE;

  public static BlockType<BlockSnifferEggBehavior> SNIFFER_EGG_TYPE;

  public static BlockType<BlockSnowBehavior> SNOW_TYPE;

  public static BlockType<BlockSnowLayerBehavior> SNOW_LAYER_TYPE;

  public static BlockType<BlockSoulCampfireBehavior> SOUL_CAMPFIRE_TYPE;

  public static BlockType<BlockSoulFireBehavior> SOUL_FIRE_TYPE;

  public static BlockType<BlockSoulLanternBehavior> SOUL_LANTERN_TYPE;

  public static BlockType<BlockSoulSandBehavior> SOUL_SAND_TYPE;

  public static BlockType<BlockSoulSoilBehavior> SOUL_SOIL_TYPE;

  public static BlockType<BlockSoulTorchBehavior> SOUL_TORCH_TYPE;

  public static BlockType<BlockSpongeBehavior> SPONGE_TYPE;

  public static BlockType<BlockSporeBlossomBehavior> SPORE_BLOSSOM_TYPE;

  public static BlockType<BlockSpruceButtonBehavior> SPRUCE_BUTTON_TYPE;

  public static BlockType<BlockSpruceDoorBehavior> SPRUCE_DOOR_TYPE;

  public static BlockType<BlockSpruceFenceBehavior> SPRUCE_FENCE_TYPE;

  public static BlockType<BlockSpruceFenceGateBehavior> SPRUCE_FENCE_GATE_TYPE;

  public static BlockType<BlockSpruceHangingSignBehavior> SPRUCE_HANGING_SIGN_TYPE;

  public static BlockType<BlockSpruceLogBehavior> SPRUCE_LOG_TYPE;

  public static BlockType<BlockSprucePlanksBehavior> SPRUCE_PLANKS_TYPE;

  public static BlockType<BlockSprucePressurePlateBehavior> SPRUCE_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockSpruceStairsBehavior> SPRUCE_STAIRS_TYPE;

  public static BlockType<BlockSpruceStandingSignBehavior> SPRUCE_STANDING_SIGN_TYPE;

  public static BlockType<BlockSpruceTrapdoorBehavior> SPRUCE_TRAPDOOR_TYPE;

  public static BlockType<BlockSpruceWallSignBehavior> SPRUCE_WALL_SIGN_TYPE;

  public static BlockType<BlockStandingBannerBehavior> STANDING_BANNER_TYPE;

  public static BlockType<BlockStandingSignBehavior> STANDING_SIGN_TYPE;

  public static BlockType<BlockStickyPistonBehavior> STICKY_PISTON_TYPE;

  public static BlockType<BlockStickyPistonArmCollisionBehavior> STICKY_PISTON_ARM_COLLISION_TYPE;

  public static BlockType<BlockStoneBehavior> STONE_TYPE;

  public static BlockType<BlockStoneBlockSlabBehavior> STONE_BLOCK_SLAB_TYPE;

  public static BlockType<BlockStoneBlockSlab2Behavior> STONE_BLOCK_SLAB2_TYPE;

  public static BlockType<BlockStoneBlockSlab3Behavior> STONE_BLOCK_SLAB3_TYPE;

  public static BlockType<BlockStoneBlockSlab4Behavior> STONE_BLOCK_SLAB4_TYPE;

  public static BlockType<BlockStoneBrickStairsBehavior> STONE_BRICK_STAIRS_TYPE;

  public static BlockType<BlockStoneButtonBehavior> STONE_BUTTON_TYPE;

  public static BlockType<BlockStonePressurePlateBehavior> STONE_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockStoneStairsBehavior> STONE_STAIRS_TYPE;

  public static BlockType<BlockStonebrickBehavior> STONEBRICK_TYPE;

  public static BlockType<BlockStonecutterBehavior> STONECUTTER_TYPE;

  public static BlockType<BlockStonecutterBlockBehavior> STONECUTTER_BLOCK_TYPE;

  public static BlockType<BlockStrippedAcaciaLogBehavior> STRIPPED_ACACIA_LOG_TYPE;

  public static BlockType<BlockStrippedBambooBlockBehavior> STRIPPED_BAMBOO_BLOCK_TYPE;

  public static BlockType<BlockStrippedBirchLogBehavior> STRIPPED_BIRCH_LOG_TYPE;

  public static BlockType<BlockStrippedCherryLogBehavior> STRIPPED_CHERRY_LOG_TYPE;

  public static BlockType<BlockStrippedCherryWoodBehavior> STRIPPED_CHERRY_WOOD_TYPE;

  public static BlockType<BlockStrippedCrimsonHyphaeBehavior> STRIPPED_CRIMSON_HYPHAE_TYPE;

  public static BlockType<BlockStrippedCrimsonStemBehavior> STRIPPED_CRIMSON_STEM_TYPE;

  public static BlockType<BlockStrippedDarkOakLogBehavior> STRIPPED_DARK_OAK_LOG_TYPE;

  public static BlockType<BlockStrippedJungleLogBehavior> STRIPPED_JUNGLE_LOG_TYPE;

  public static BlockType<BlockStrippedMangroveLogBehavior> STRIPPED_MANGROVE_LOG_TYPE;

  public static BlockType<BlockStrippedMangroveWoodBehavior> STRIPPED_MANGROVE_WOOD_TYPE;

  public static BlockType<BlockStrippedOakLogBehavior> STRIPPED_OAK_LOG_TYPE;

  public static BlockType<BlockStrippedSpruceLogBehavior> STRIPPED_SPRUCE_LOG_TYPE;

  public static BlockType<BlockStrippedWarpedHyphaeBehavior> STRIPPED_WARPED_HYPHAE_TYPE;

  public static BlockType<BlockStrippedWarpedStemBehavior> STRIPPED_WARPED_STEM_TYPE;

  public static BlockType<BlockStructureBlockBehavior> STRUCTURE_BLOCK_TYPE;

  public static BlockType<BlockStructureVoidBehavior> STRUCTURE_VOID_TYPE;

  public static BlockType<BlockSuspiciousGravelBehavior> SUSPICIOUS_GRAVEL_TYPE;

  public static BlockType<BlockSuspiciousSandBehavior> SUSPICIOUS_SAND_TYPE;

  public static BlockType<BlockSweetBerryBushBehavior> SWEET_BERRY_BUSH_TYPE;

  public static BlockType<BlockTallgrassBehavior> TALLGRASS_TYPE;

  public static BlockType<BlockTargetBehavior> TARGET_TYPE;

  public static BlockType<BlockTintedGlassBehavior> TINTED_GLASS_TYPE;

  public static BlockType<BlockTntBehavior> TNT_TYPE;

  public static BlockType<BlockTorchBehavior> TORCH_TYPE;

  public static BlockType<BlockTorchflowerBehavior> TORCHFLOWER_TYPE;

  public static BlockType<BlockTorchflowerCropBehavior> TORCHFLOWER_CROP_TYPE;

  public static BlockType<BlockTrapdoorBehavior> TRAPDOOR_TYPE;

  public static BlockType<BlockTrappedChestBehavior> TRAPPED_CHEST_TYPE;

  public static BlockType<BlockTrialSpawnerBehavior> TRIAL_SPAWNER_TYPE;

  public static BlockType<BlockTripWireBehavior> TRIP_WIRE_TYPE;

  public static BlockType<BlockTripwireHookBehavior> TRIPWIRE_HOOK_TYPE;

  public static BlockType<BlockTubeCoralBehavior> TUBE_CORAL_TYPE;

  public static BlockType<BlockTuffBehavior> TUFF_TYPE;

  public static BlockType<BlockTuffBrickDoubleSlabBehavior> TUFF_BRICK_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockTuffBrickSlabBehavior> TUFF_BRICK_SLAB_TYPE;

  public static BlockType<BlockTuffBrickStairsBehavior> TUFF_BRICK_STAIRS_TYPE;

  public static BlockType<BlockTuffBrickWallBehavior> TUFF_BRICK_WALL_TYPE;

  public static BlockType<BlockTuffBricksBehavior> TUFF_BRICKS_TYPE;

  public static BlockType<BlockTuffDoubleSlabBehavior> TUFF_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockTuffSlabBehavior> TUFF_SLAB_TYPE;

  public static BlockType<BlockTuffStairsBehavior> TUFF_STAIRS_TYPE;

  public static BlockType<BlockTuffWallBehavior> TUFF_WALL_TYPE;

  public static BlockType<BlockTurtleEggBehavior> TURTLE_EGG_TYPE;

  public static BlockType<BlockTwistingVinesBehavior> TWISTING_VINES_TYPE;

  public static BlockType<BlockUnderwaterTorchBehavior> UNDERWATER_TORCH_TYPE;

  public static BlockType<BlockUndyedShulkerBoxBehavior> UNDYED_SHULKER_BOX_TYPE;

  public static BlockType<BlockUnknownBehavior> UNKNOWN_TYPE;

  public static BlockType<BlockUnlitRedstoneTorchBehavior> UNLIT_REDSTONE_TORCH_TYPE;

  public static BlockType<BlockUnpoweredComparatorBehavior> UNPOWERED_COMPARATOR_TYPE;

  public static BlockType<BlockUnpoweredRepeaterBehavior> UNPOWERED_REPEATER_TYPE;

  public static BlockType<BlockVerdantFroglightBehavior> VERDANT_FROGLIGHT_TYPE;

  public static BlockType<BlockVineBehavior> VINE_TYPE;

  public static BlockType<BlockWallBannerBehavior> WALL_BANNER_TYPE;

  public static BlockType<BlockWallSignBehavior> WALL_SIGN_TYPE;

  public static BlockType<BlockWarpedButtonBehavior> WARPED_BUTTON_TYPE;

  public static BlockType<BlockWarpedDoorBehavior> WARPED_DOOR_TYPE;

  public static BlockType<BlockWarpedDoubleSlabBehavior> WARPED_DOUBLE_SLAB_TYPE;

  public static BlockType<BlockWarpedFenceBehavior> WARPED_FENCE_TYPE;

  public static BlockType<BlockWarpedFenceGateBehavior> WARPED_FENCE_GATE_TYPE;

  public static BlockType<BlockWarpedFungusBehavior> WARPED_FUNGUS_TYPE;

  public static BlockType<BlockWarpedHangingSignBehavior> WARPED_HANGING_SIGN_TYPE;

  public static BlockType<BlockWarpedHyphaeBehavior> WARPED_HYPHAE_TYPE;

  public static BlockType<BlockWarpedNyliumBehavior> WARPED_NYLIUM_TYPE;

  public static BlockType<BlockWarpedPlanksBehavior> WARPED_PLANKS_TYPE;

  public static BlockType<BlockWarpedPressurePlateBehavior> WARPED_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockWarpedRootsBehavior> WARPED_ROOTS_TYPE;

  public static BlockType<BlockWarpedSlabBehavior> WARPED_SLAB_TYPE;

  public static BlockType<BlockWarpedStairsBehavior> WARPED_STAIRS_TYPE;

  public static BlockType<BlockWarpedStandingSignBehavior> WARPED_STANDING_SIGN_TYPE;

  public static BlockType<BlockWarpedStemBehavior> WARPED_STEM_TYPE;

  public static BlockType<BlockWarpedTrapdoorBehavior> WARPED_TRAPDOOR_TYPE;

  public static BlockType<BlockWarpedWallSignBehavior> WARPED_WALL_SIGN_TYPE;

  public static BlockType<BlockWarpedWartBlockBehavior> WARPED_WART_BLOCK_TYPE;

  public static BlockType<BlockWaterBehavior> WATER_TYPE;

  public static BlockType<BlockWaterlilyBehavior> WATERLILY_TYPE;

  public static BlockType<BlockWaxedChiseledCopperBehavior> WAXED_CHISELED_COPPER_TYPE;

  public static BlockType<BlockWaxedCopperBehavior> WAXED_COPPER_TYPE;

  public static BlockType<BlockWaxedCopperBulbBehavior> WAXED_COPPER_BULB_TYPE;

  public static BlockType<BlockWaxedCopperDoorBehavior> WAXED_COPPER_DOOR_TYPE;

  public static BlockType<BlockWaxedCopperGrateBehavior> WAXED_COPPER_GRATE_TYPE;

  public static BlockType<BlockWaxedCopperTrapdoorBehavior> WAXED_COPPER_TRAPDOOR_TYPE;

  public static BlockType<BlockWaxedCutCopperBehavior> WAXED_CUT_COPPER_TYPE;

  public static BlockType<BlockWaxedCutCopperSlabBehavior> WAXED_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockWaxedCutCopperStairsBehavior> WAXED_CUT_COPPER_STAIRS_TYPE;

  public static BlockType<BlockWaxedDoubleCutCopperSlabBehavior> WAXED_DOUBLE_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockWaxedExposedChiseledCopperBehavior> WAXED_EXPOSED_CHISELED_COPPER_TYPE;

  public static BlockType<BlockWaxedExposedCopperBehavior> WAXED_EXPOSED_COPPER_TYPE;

  public static BlockType<BlockWaxedExposedCopperBulbBehavior> WAXED_EXPOSED_COPPER_BULB_TYPE;

  public static BlockType<BlockWaxedExposedCopperDoorBehavior> WAXED_EXPOSED_COPPER_DOOR_TYPE;

  public static BlockType<BlockWaxedExposedCopperGrateBehavior> WAXED_EXPOSED_COPPER_GRATE_TYPE;

  public static BlockType<BlockWaxedExposedCopperTrapdoorBehavior> WAXED_EXPOSED_COPPER_TRAPDOOR_TYPE;

  public static BlockType<BlockWaxedExposedCutCopperBehavior> WAXED_EXPOSED_CUT_COPPER_TYPE;

  public static BlockType<BlockWaxedExposedCutCopperSlabBehavior> WAXED_EXPOSED_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockWaxedExposedCutCopperStairsBehavior> WAXED_EXPOSED_CUT_COPPER_STAIRS_TYPE;

  public static BlockType<BlockWaxedExposedDoubleCutCopperSlabBehavior> WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockWaxedOxidizedChiseledCopperBehavior> WAXED_OXIDIZED_CHISELED_COPPER_TYPE;

  public static BlockType<BlockWaxedOxidizedCopperBehavior> WAXED_OXIDIZED_COPPER_TYPE;

  public static BlockType<BlockWaxedOxidizedCopperBulbBehavior> WAXED_OXIDIZED_COPPER_BULB_TYPE;

  public static BlockType<BlockWaxedOxidizedCopperDoorBehavior> WAXED_OXIDIZED_COPPER_DOOR_TYPE;

  public static BlockType<BlockWaxedOxidizedCopperGrateBehavior> WAXED_OXIDIZED_COPPER_GRATE_TYPE;

  public static BlockType<BlockWaxedOxidizedCopperTrapdoorBehavior> WAXED_OXIDIZED_COPPER_TRAPDOOR_TYPE;

  public static BlockType<BlockWaxedOxidizedCutCopperBehavior> WAXED_OXIDIZED_CUT_COPPER_TYPE;

  public static BlockType<BlockWaxedOxidizedCutCopperSlabBehavior> WAXED_OXIDIZED_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockWaxedOxidizedCutCopperStairsBehavior> WAXED_OXIDIZED_CUT_COPPER_STAIRS_TYPE;

  public static BlockType<BlockWaxedOxidizedDoubleCutCopperSlabBehavior> WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockWaxedWeatheredChiseledCopperBehavior> WAXED_WEATHERED_CHISELED_COPPER_TYPE;

  public static BlockType<BlockWaxedWeatheredCopperBehavior> WAXED_WEATHERED_COPPER_TYPE;

  public static BlockType<BlockWaxedWeatheredCopperBulbBehavior> WAXED_WEATHERED_COPPER_BULB_TYPE;

  public static BlockType<BlockWaxedWeatheredCopperDoorBehavior> WAXED_WEATHERED_COPPER_DOOR_TYPE;

  public static BlockType<BlockWaxedWeatheredCopperGrateBehavior> WAXED_WEATHERED_COPPER_GRATE_TYPE;

  public static BlockType<BlockWaxedWeatheredCopperTrapdoorBehavior> WAXED_WEATHERED_COPPER_TRAPDOOR_TYPE;

  public static BlockType<BlockWaxedWeatheredCutCopperBehavior> WAXED_WEATHERED_CUT_COPPER_TYPE;

  public static BlockType<BlockWaxedWeatheredCutCopperSlabBehavior> WAXED_WEATHERED_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockWaxedWeatheredCutCopperStairsBehavior> WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE;

  public static BlockType<BlockWaxedWeatheredDoubleCutCopperSlabBehavior> WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockWeatheredChiseledCopperBehavior> WEATHERED_CHISELED_COPPER_TYPE;

  public static BlockType<BlockWeatheredCopperBehavior> WEATHERED_COPPER_TYPE;

  public static BlockType<BlockWeatheredCopperBulbBehavior> WEATHERED_COPPER_BULB_TYPE;

  public static BlockType<BlockWeatheredCopperDoorBehavior> WEATHERED_COPPER_DOOR_TYPE;

  public static BlockType<BlockWeatheredCopperGrateBehavior> WEATHERED_COPPER_GRATE_TYPE;

  public static BlockType<BlockWeatheredCopperTrapdoorBehavior> WEATHERED_COPPER_TRAPDOOR_TYPE;

  public static BlockType<BlockWeatheredCutCopperBehavior> WEATHERED_CUT_COPPER_TYPE;

  public static BlockType<BlockWeatheredCutCopperSlabBehavior> WEATHERED_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockWeatheredCutCopperStairsBehavior> WEATHERED_CUT_COPPER_STAIRS_TYPE;

  public static BlockType<BlockWeatheredDoubleCutCopperSlabBehavior> WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE;

  public static BlockType<BlockWebBehavior> WEB_TYPE;

  public static BlockType<BlockWeepingVinesBehavior> WEEPING_VINES_TYPE;

  public static BlockType<BlockWheatBehavior> WHEAT_TYPE;

  public static BlockType<BlockWhiteCandleBehavior> WHITE_CANDLE_TYPE;

  public static BlockType<BlockWhiteCandleCakeBehavior> WHITE_CANDLE_CAKE_TYPE;

  public static BlockType<BlockWhiteCarpetBehavior> WHITE_CARPET_TYPE;

  public static BlockType<BlockWhiteConcreteBehavior> WHITE_CONCRETE_TYPE;

  public static BlockType<BlockWhiteConcretePowderBehavior> WHITE_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockWhiteGlazedTerracottaBehavior> WHITE_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockWhiteShulkerBoxBehavior> WHITE_SHULKER_BOX_TYPE;

  public static BlockType<BlockWhiteStainedGlassBehavior> WHITE_STAINED_GLASS_TYPE;

  public static BlockType<BlockWhiteStainedGlassPaneBehavior> WHITE_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockWhiteTerracottaBehavior> WHITE_TERRACOTTA_TYPE;

  public static BlockType<BlockWhiteWoolBehavior> WHITE_WOOL_TYPE;

  public static BlockType<BlockWitherRoseBehavior> WITHER_ROSE_TYPE;

  public static BlockType<BlockWoodBehavior> WOOD_TYPE;

  public static BlockType<BlockWoodenButtonBehavior> WOODEN_BUTTON_TYPE;

  public static BlockType<BlockWoodenDoorBehavior> WOODEN_DOOR_TYPE;

  public static BlockType<BlockWoodenPressurePlateBehavior> WOODEN_PRESSURE_PLATE_TYPE;

  public static BlockType<BlockWoodenSlabBehavior> WOODEN_SLAB_TYPE;

  public static BlockType<BlockYellowCandleBehavior> YELLOW_CANDLE_TYPE;

  public static BlockType<BlockYellowCandleCakeBehavior> YELLOW_CANDLE_CAKE_TYPE;

  public static BlockType<BlockYellowCarpetBehavior> YELLOW_CARPET_TYPE;

  public static BlockType<BlockYellowConcreteBehavior> YELLOW_CONCRETE_TYPE;

  public static BlockType<BlockYellowConcretePowderBehavior> YELLOW_CONCRETE_POWDER_TYPE;

  public static BlockType<BlockYellowFlowerBehavior> YELLOW_FLOWER_TYPE;

  public static BlockType<BlockYellowGlazedTerracottaBehavior> YELLOW_GLAZED_TERRACOTTA_TYPE;

  public static BlockType<BlockYellowShulkerBoxBehavior> YELLOW_SHULKER_BOX_TYPE;

  public static BlockType<BlockYellowStainedGlassBehavior> YELLOW_STAINED_GLASS_TYPE;

  public static BlockType<BlockYellowStainedGlassPaneBehavior> YELLOW_STAINED_GLASS_PANE_TYPE;

  public static BlockType<BlockYellowTerracottaBehavior> YELLOW_TERRACOTTA_TYPE;

  public static BlockType<BlockYellowWoolBehavior> YELLOW_WOOL_TYPE;
}
