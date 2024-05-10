package org.allaymc.api.item.type;

import org.allaymc.api.item.interfaces.*;
import org.allaymc.api.item.interfaces.boat.*;
import org.allaymc.api.item.interfaces.boots.*;
import org.allaymc.api.item.interfaces.bricks.*;
import org.allaymc.api.item.interfaces.bucket.*;
import org.allaymc.api.item.interfaces.button.*;
import org.allaymc.api.item.interfaces.candle.*;
import org.allaymc.api.item.interfaces.candlecake.*;
import org.allaymc.api.item.interfaces.carpet.*;
import org.allaymc.api.item.interfaces.chestplate.*;
import org.allaymc.api.item.interfaces.concrete.*;
import org.allaymc.api.item.interfaces.concretepowder.ItemConcretePowderStack;
import org.allaymc.api.item.interfaces.concretepowder.*;
import org.allaymc.api.item.interfaces.copper.*;
import org.allaymc.api.item.interfaces.coral.*;
import org.allaymc.api.item.interfaces.coralfan.*;
import org.allaymc.api.item.interfaces.door.*;
import org.allaymc.api.item.interfaces.egg.*;
import org.allaymc.api.item.interfaces.element.*;
import org.allaymc.api.item.interfaces.fence.*;
import org.allaymc.api.item.interfaces.fencegate.*;
import org.allaymc.api.item.interfaces.glass.ItemGlassStack;
import org.allaymc.api.item.interfaces.glass.ItemHardGlassStack;
import org.allaymc.api.item.interfaces.glass.ItemTintedGlassStack;
import org.allaymc.api.item.interfaces.glasspane.ItemGlassPaneStack;
import org.allaymc.api.item.interfaces.glasspane.ItemHardGlassPaneStack;
import org.allaymc.api.item.interfaces.hangingsign.*;
import org.allaymc.api.item.interfaces.helmet.*;
import org.allaymc.api.item.interfaces.leaves.*;
import org.allaymc.api.item.interfaces.leggings.*;
import org.allaymc.api.item.interfaces.liquid.ItemFlowingLavaStack;
import org.allaymc.api.item.interfaces.liquid.ItemFlowingWaterStack;
import org.allaymc.api.item.interfaces.liquid.ItemLavaStack;
import org.allaymc.api.item.interfaces.liquid.ItemWaterStack;
import org.allaymc.api.item.interfaces.log.*;
import org.allaymc.api.item.interfaces.minecart.*;
import org.allaymc.api.item.interfaces.musicdisc.*;
import org.allaymc.api.item.interfaces.piston.ItemPistonArmCollisionStack;
import org.allaymc.api.item.interfaces.piston.ItemPistonStack;
import org.allaymc.api.item.interfaces.piston.ItemStickyPistonArmCollisionStack;
import org.allaymc.api.item.interfaces.piston.ItemStickyPistonStack;
import org.allaymc.api.item.interfaces.planks.*;
import org.allaymc.api.item.interfaces.sapling.*;
import org.allaymc.api.item.interfaces.shulkerbox.*;
import org.allaymc.api.item.interfaces.sign.*;
import org.allaymc.api.item.interfaces.slab.*;
import org.allaymc.api.item.interfaces.stainedglass.*;
import org.allaymc.api.item.interfaces.stainedglasspane.*;
import org.allaymc.api.item.interfaces.stairs.*;
import org.allaymc.api.item.interfaces.standingsign.*;
import org.allaymc.api.item.interfaces.terracotta.*;
import org.allaymc.api.item.interfaces.trapdoor.*;
import org.allaymc.api.item.interfaces.wall.*;
import org.allaymc.api.item.interfaces.wallsign.*;
import org.allaymc.api.item.interfaces.wood.*;
import org.allaymc.api.item.interfaces.wool.*;

public final class ItemTypes {
    public static ItemType<ItemAcaciaBoatStack> ACACIA_BOAT_TYPE;

    public static ItemType<ItemAcaciaButtonStack> ACACIA_BUTTON_TYPE;

    public static ItemType<ItemAcaciaChestBoatStack> ACACIA_CHEST_BOAT_TYPE;

    public static ItemType<ItemAcaciaDoorStack> ACACIA_DOOR_TYPE;

    public static ItemType<ItemAcaciaDoubleSlabStack> ACACIA_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemAcaciaFenceStack> ACACIA_FENCE_TYPE;

    public static ItemType<ItemAcaciaFenceGateStack> ACACIA_FENCE_GATE_TYPE;

    public static ItemType<ItemAcaciaHangingSignStack> ACACIA_HANGING_SIGN_TYPE;

    public static ItemType<ItemAcaciaLeavesStack> ACACIA_LEAVES_TYPE;

    public static ItemType<ItemAcaciaLogStack> ACACIA_LOG_TYPE;

    public static ItemType<ItemAcaciaPlanksStack> ACACIA_PLANKS_TYPE;

    public static ItemType<ItemAcaciaPressurePlateStack> ACACIA_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemAcaciaSaplingStack> ACACIA_SAPLING_TYPE;

    public static ItemType<ItemAcaciaSignStack> ACACIA_SIGN_TYPE;

    public static ItemType<ItemAcaciaSlabStack> ACACIA_SLAB_TYPE;

    public static ItemType<ItemAcaciaStairsStack> ACACIA_STAIRS_TYPE;

    public static ItemType<ItemAcaciaStandingSignStack> ACACIA_STANDING_SIGN_TYPE;

    public static ItemType<ItemAcaciaTrapdoorStack> ACACIA_TRAPDOOR_TYPE;

    public static ItemType<ItemAcaciaWallSignStack> ACACIA_WALL_SIGN_TYPE;

    public static ItemType<ItemAcaciaWoodStack> ACACIA_WOOD_TYPE;

    public static ItemType<ItemActivatorRailStack> ACTIVATOR_RAIL_TYPE;

    public static ItemType<ItemAgentSpawnEggStack> AGENT_SPAWN_EGG_TYPE;

    public static ItemType<ItemAirStack> AIR_TYPE;

    public static ItemType<ItemAllaySpawnEggStack> ALLAY_SPAWN_EGG_TYPE;

    public static ItemType<ItemAlliumStack> ALLIUM_TYPE;

    public static ItemType<ItemAllowStack> ALLOW_TYPE;

    public static ItemType<ItemAmethystBlockStack> AMETHYST_BLOCK_TYPE;

    public static ItemType<ItemAmethystClusterStack> AMETHYST_CLUSTER_TYPE;

    public static ItemType<ItemAmethystShardStack> AMETHYST_SHARD_TYPE;

    public static ItemType<ItemAncientDebrisStack> ANCIENT_DEBRIS_TYPE;

    public static ItemType<ItemAndesiteStack> ANDESITE_TYPE;

    public static ItemType<ItemAndesiteStairsStack> ANDESITE_STAIRS_TYPE;

    public static ItemType<ItemAnglerPotterySherdStack> ANGLER_POTTERY_SHERD_TYPE;

    public static ItemType<ItemAnvilStack> ANVIL_TYPE;

    public static ItemType<ItemAppleStack> APPLE_TYPE;

    public static ItemType<ItemArcherPotterySherdStack> ARCHER_POTTERY_SHERD_TYPE;

    public static ItemType<ItemArmadilloScuteStack> ARMADILLO_SCUTE_TYPE;

    public static ItemType<ItemArmadilloSpawnEggStack> ARMADILLO_SPAWN_EGG_TYPE;

    public static ItemType<ItemArmorStandStack> ARMOR_STAND_TYPE;

    public static ItemType<ItemArmsUpPotterySherdStack> ARMS_UP_POTTERY_SHERD_TYPE;

    public static ItemType<ItemArrowStack> ARROW_TYPE;

    public static ItemType<ItemAxolotlBucketStack> AXOLOTL_BUCKET_TYPE;

    public static ItemType<ItemAxolotlSpawnEggStack> AXOLOTL_SPAWN_EGG_TYPE;

    public static ItemType<ItemAzaleaStack> AZALEA_TYPE;

    public static ItemType<ItemAzaleaLeavesStack> AZALEA_LEAVES_TYPE;

    public static ItemType<ItemAzaleaLeavesFloweredStack> AZALEA_LEAVES_FLOWERED_TYPE;

    public static ItemType<ItemAzureBluetStack> AZURE_BLUET_TYPE;

    public static ItemType<ItemBakedPotatoStack> BAKED_POTATO_TYPE;

    public static ItemType<ItemBalloonStack> BALLOON_TYPE;

    public static ItemType<ItemBambooStack> BAMBOO_TYPE;

    public static ItemType<ItemBambooBlockStack> BAMBOO_BLOCK_TYPE;

    public static ItemType<ItemBambooButtonStack> BAMBOO_BUTTON_TYPE;

    public static ItemType<ItemBambooChestRaftStack> BAMBOO_CHEST_RAFT_TYPE;

    public static ItemType<ItemBambooDoorStack> BAMBOO_DOOR_TYPE;

    public static ItemType<ItemBambooDoubleSlabStack> BAMBOO_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemBambooFenceStack> BAMBOO_FENCE_TYPE;

    public static ItemType<ItemBambooFenceGateStack> BAMBOO_FENCE_GATE_TYPE;

    public static ItemType<ItemBambooHangingSignStack> BAMBOO_HANGING_SIGN_TYPE;

    public static ItemType<ItemBambooMosaicStack> BAMBOO_MOSAIC_TYPE;

    public static ItemType<ItemBambooMosaicDoubleSlabStack> BAMBOO_MOSAIC_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemBambooMosaicSlabStack> BAMBOO_MOSAIC_SLAB_TYPE;

    public static ItemType<ItemBambooMosaicStairsStack> BAMBOO_MOSAIC_STAIRS_TYPE;

    public static ItemType<ItemBambooPlanksStack> BAMBOO_PLANKS_TYPE;

    public static ItemType<ItemBambooPressurePlateStack> BAMBOO_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemBambooRaftStack> BAMBOO_RAFT_TYPE;

    public static ItemType<ItemBambooSaplingStack> BAMBOO_SAPLING_TYPE;

    public static ItemType<ItemBambooSignStack> BAMBOO_SIGN_TYPE;

    public static ItemType<ItemBambooSlabStack> BAMBOO_SLAB_TYPE;

    public static ItemType<ItemBambooStairsStack> BAMBOO_STAIRS_TYPE;

    public static ItemType<ItemBambooStandingSignStack> BAMBOO_STANDING_SIGN_TYPE;

    public static ItemType<ItemBambooTrapdoorStack> BAMBOO_TRAPDOOR_TYPE;

    public static ItemType<ItemBambooWallSignStack> BAMBOO_WALL_SIGN_TYPE;

    public static ItemType<ItemBannerStack> BANNER_TYPE;

    public static ItemType<ItemBannerPatternStack> BANNER_PATTERN_TYPE;

    public static ItemType<ItemBarrelStack> BARREL_TYPE;

    public static ItemType<ItemBarrierStack> BARRIER_TYPE;

    public static ItemType<ItemBasaltStack> BASALT_TYPE;

    public static ItemType<ItemBatSpawnEggStack> BAT_SPAWN_EGG_TYPE;

    public static ItemType<ItemBeaconStack> BEACON_TYPE;

    public static ItemType<ItemBedStack> BED_TYPE;

    public static ItemType<ItemBedrockStack> BEDROCK_TYPE;

    public static ItemType<ItemBeeNestStack> BEE_NEST_TYPE;

    public static ItemType<ItemBeeSpawnEggStack> BEE_SPAWN_EGG_TYPE;

    public static ItemType<ItemBeefStack> BEEF_TYPE;

    public static ItemType<ItemBeehiveStack> BEEHIVE_TYPE;

    public static ItemType<ItemBeetrootStack> BEETROOT_TYPE;

    public static ItemType<ItemBeetrootSeedsStack> BEETROOT_SEEDS_TYPE;

    public static ItemType<ItemBeetrootSoupStack> BEETROOT_SOUP_TYPE;

    public static ItemType<ItemBellStack> BELL_TYPE;

    public static ItemType<ItemBigDripleafStack> BIG_DRIPLEAF_TYPE;

    public static ItemType<ItemBirchBoatStack> BIRCH_BOAT_TYPE;

    public static ItemType<ItemBirchButtonStack> BIRCH_BUTTON_TYPE;

    public static ItemType<ItemBirchChestBoatStack> BIRCH_CHEST_BOAT_TYPE;

    public static ItemType<ItemBirchDoorStack> BIRCH_DOOR_TYPE;

    public static ItemType<ItemBirchDoubleSlabStack> BIRCH_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemBirchFenceStack> BIRCH_FENCE_TYPE;

    public static ItemType<ItemBirchFenceGateStack> BIRCH_FENCE_GATE_TYPE;

    public static ItemType<ItemBirchHangingSignStack> BIRCH_HANGING_SIGN_TYPE;

    public static ItemType<ItemBirchLeavesStack> BIRCH_LEAVES_TYPE;

    public static ItemType<ItemBirchLogStack> BIRCH_LOG_TYPE;

    public static ItemType<ItemBirchPlanksStack> BIRCH_PLANKS_TYPE;

    public static ItemType<ItemBirchPressurePlateStack> BIRCH_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemBirchSaplingStack> BIRCH_SAPLING_TYPE;

    public static ItemType<ItemBirchSignStack> BIRCH_SIGN_TYPE;

    public static ItemType<ItemBirchSlabStack> BIRCH_SLAB_TYPE;

    public static ItemType<ItemBirchStairsStack> BIRCH_STAIRS_TYPE;

    public static ItemType<ItemBirchStandingSignStack> BIRCH_STANDING_SIGN_TYPE;

    public static ItemType<ItemBirchTrapdoorStack> BIRCH_TRAPDOOR_TYPE;

    public static ItemType<ItemBirchWallSignStack> BIRCH_WALL_SIGN_TYPE;

    public static ItemType<ItemBirchWoodStack> BIRCH_WOOD_TYPE;

    public static ItemType<ItemBlackCandleStack> BLACK_CANDLE_TYPE;

    public static ItemType<ItemBlackCandleCakeStack> BLACK_CANDLE_CAKE_TYPE;

    public static ItemType<ItemBlackCarpetStack> BLACK_CARPET_TYPE;

    public static ItemType<ItemBlackConcreteStack> BLACK_CONCRETE_TYPE;

    public static ItemType<ItemBlackConcretePowderStack> BLACK_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemBlackDyeStack> BLACK_DYE_TYPE;

    public static ItemType<ItemBlackGlazedTerracottaStack> BLACK_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemBlackShulkerBoxStack> BLACK_SHULKER_BOX_TYPE;

    public static ItemType<ItemBlackStainedGlassStack> BLACK_STAINED_GLASS_TYPE;

    public static ItemType<ItemBlackStainedGlassPaneStack> BLACK_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemBlackTerracottaStack> BLACK_TERRACOTTA_TYPE;

    public static ItemType<ItemBlackWoolStack> BLACK_WOOL_TYPE;

    public static ItemType<ItemBlackstoneStack> BLACKSTONE_TYPE;

    public static ItemType<ItemBlackstoneDoubleSlabStack> BLACKSTONE_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemBlackstoneSlabStack> BLACKSTONE_SLAB_TYPE;

    public static ItemType<ItemBlackstoneStairsStack> BLACKSTONE_STAIRS_TYPE;

    public static ItemType<ItemBlackstoneWallStack> BLACKSTONE_WALL_TYPE;

    public static ItemType<ItemBladePotterySherdStack> BLADE_POTTERY_SHERD_TYPE;

    public static ItemType<ItemBlastFurnaceStack> BLAST_FURNACE_TYPE;

    public static ItemType<ItemBlazePowderStack> BLAZE_POWDER_TYPE;

    public static ItemType<ItemBlazeRodStack> BLAZE_ROD_TYPE;

    public static ItemType<ItemBlazeSpawnEggStack> BLAZE_SPAWN_EGG_TYPE;

    public static ItemType<ItemBleachStack> BLEACH_TYPE;

    public static ItemType<ItemBlueCandleStack> BLUE_CANDLE_TYPE;

    public static ItemType<ItemBlueCandleCakeStack> BLUE_CANDLE_CAKE_TYPE;

    public static ItemType<ItemBlueCarpetStack> BLUE_CARPET_TYPE;

    public static ItemType<ItemBlueConcreteStack> BLUE_CONCRETE_TYPE;

    public static ItemType<ItemBlueConcretePowderStack> BLUE_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemBlueDyeStack> BLUE_DYE_TYPE;

    public static ItemType<ItemBlueGlazedTerracottaStack> BLUE_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemBlueIceStack> BLUE_ICE_TYPE;

    public static ItemType<ItemBlueOrchidStack> BLUE_ORCHID_TYPE;

    public static ItemType<ItemBlueShulkerBoxStack> BLUE_SHULKER_BOX_TYPE;

    public static ItemType<ItemBlueStainedGlassStack> BLUE_STAINED_GLASS_TYPE;

    public static ItemType<ItemBlueStainedGlassPaneStack> BLUE_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemBlueTerracottaStack> BLUE_TERRACOTTA_TYPE;

    public static ItemType<ItemBlueWoolStack> BLUE_WOOL_TYPE;

    public static ItemType<ItemBoatStack> BOAT_TYPE;

    public static ItemType<ItemBoggedSpawnEggStack> BOGGED_SPAWN_EGG_TYPE;

    public static ItemType<ItemBoltArmorTrimSmithingTemplateStack> BOLT_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemBoneStack> BONE_TYPE;

    public static ItemType<ItemBoneBlockStack> BONE_BLOCK_TYPE;

    public static ItemType<ItemBoneMealStack> BONE_MEAL_TYPE;

    public static ItemType<ItemBookStack> BOOK_TYPE;

    public static ItemType<ItemBookshelfStack> BOOKSHELF_TYPE;

    public static ItemType<ItemBorderBlockStack> BORDER_BLOCK_TYPE;

    public static ItemType<ItemBordureIndentedBannerPatternStack> BORDURE_INDENTED_BANNER_PATTERN_TYPE;

    public static ItemType<ItemBowStack> BOW_TYPE;

    public static ItemType<ItemBowlStack> BOWL_TYPE;

    public static ItemType<ItemBrainCoralStack> BRAIN_CORAL_TYPE;

    public static ItemType<ItemBrainCoralFanStack> BRAIN_CORAL_FAN_TYPE;

    public static ItemType<ItemBreadStack> BREAD_TYPE;

    public static ItemType<ItemBreezeRodStack> BREEZE_ROD_TYPE;

    public static ItemType<ItemBreezeSpawnEggStack> BREEZE_SPAWN_EGG_TYPE;

    public static ItemType<ItemBrewerPotterySherdStack> BREWER_POTTERY_SHERD_TYPE;

    public static ItemType<ItemBrewingStandStack> BREWING_STAND_TYPE;

    public static ItemType<ItemBrickStack> BRICK_TYPE;

    public static ItemType<ItemBrickBlockStack> BRICK_BLOCK_TYPE;

    public static ItemType<ItemBrickStairsStack> BRICK_STAIRS_TYPE;

    public static ItemType<ItemBrownCandleStack> BROWN_CANDLE_TYPE;

    public static ItemType<ItemBrownCandleCakeStack> BROWN_CANDLE_CAKE_TYPE;

    public static ItemType<ItemBrownCarpetStack> BROWN_CARPET_TYPE;

    public static ItemType<ItemBrownConcreteStack> BROWN_CONCRETE_TYPE;

    public static ItemType<ItemBrownConcretePowderStack> BROWN_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemBrownDyeStack> BROWN_DYE_TYPE;

    public static ItemType<ItemBrownGlazedTerracottaStack> BROWN_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemBrownMushroomStack> BROWN_MUSHROOM_TYPE;

    public static ItemType<ItemBrownMushroomBlockStack> BROWN_MUSHROOM_BLOCK_TYPE;

    public static ItemType<ItemBrownShulkerBoxStack> BROWN_SHULKER_BOX_TYPE;

    public static ItemType<ItemBrownStainedGlassStack> BROWN_STAINED_GLASS_TYPE;

    public static ItemType<ItemBrownStainedGlassPaneStack> BROWN_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemBrownTerracottaStack> BROWN_TERRACOTTA_TYPE;

    public static ItemType<ItemBrownWoolStack> BROWN_WOOL_TYPE;

    public static ItemType<ItemBrushStack> BRUSH_TYPE;

    public static ItemType<ItemBubbleColumnStack> BUBBLE_COLUMN_TYPE;

    public static ItemType<ItemBubbleCoralStack> BUBBLE_CORAL_TYPE;

    public static ItemType<ItemBubbleCoralFanStack> BUBBLE_CORAL_FAN_TYPE;

    public static ItemType<ItemBucketStack> BUCKET_TYPE;

    public static ItemType<ItemBuddingAmethystStack> BUDDING_AMETHYST_TYPE;

    public static ItemType<ItemBurnPotterySherdStack> BURN_POTTERY_SHERD_TYPE;

    public static ItemType<ItemCactusStack> CACTUS_TYPE;

    public static ItemType<ItemCakeStack> CAKE_TYPE;

    public static ItemType<ItemCalciteStack> CALCITE_TYPE;

    public static ItemType<ItemCalibratedSculkSensorStack> CALIBRATED_SCULK_SENSOR_TYPE;

    public static ItemType<ItemCamelSpawnEggStack> CAMEL_SPAWN_EGG_TYPE;

    public static ItemType<ItemCameraStack> CAMERA_TYPE;

    public static ItemType<ItemCampfireStack> CAMPFIRE_TYPE;

    public static ItemType<ItemCandleStack> CANDLE_TYPE;

    public static ItemType<ItemCandleCakeStack> CANDLE_CAKE_TYPE;

    public static ItemType<ItemCarpetStack> CARPET_TYPE;

    public static ItemType<ItemCarrotStack> CARROT_TYPE;

    public static ItemType<ItemCarrotOnAStickStack> CARROT_ON_A_STICK_TYPE;

    public static ItemType<ItemCarrotsStack> CARROTS_TYPE;

    public static ItemType<ItemCartographyTableStack> CARTOGRAPHY_TABLE_TYPE;

    public static ItemType<ItemCarvedPumpkinStack> CARVED_PUMPKIN_TYPE;

    public static ItemType<ItemCatSpawnEggStack> CAT_SPAWN_EGG_TYPE;

    public static ItemType<ItemCauldronStack> CAULDRON_TYPE;

    public static ItemType<ItemCaveSpiderSpawnEggStack> CAVE_SPIDER_SPAWN_EGG_TYPE;

    public static ItemType<ItemCaveVinesStack> CAVE_VINES_TYPE;

    public static ItemType<ItemCaveVinesBodyWithBerriesStack> CAVE_VINES_BODY_WITH_BERRIES_TYPE;

    public static ItemType<ItemCaveVinesHeadWithBerriesStack> CAVE_VINES_HEAD_WITH_BERRIES_TYPE;

    public static ItemType<ItemChainStack> CHAIN_TYPE;

    public static ItemType<ItemChainCommandBlockStack> CHAIN_COMMAND_BLOCK_TYPE;

    public static ItemType<ItemChainmailBootsStack> CHAINMAIL_BOOTS_TYPE;

    public static ItemType<ItemChainmailChestplateStack> CHAINMAIL_CHESTPLATE_TYPE;

    public static ItemType<ItemChainmailHelmetStack> CHAINMAIL_HELMET_TYPE;

    public static ItemType<ItemChainmailLeggingsStack> CHAINMAIL_LEGGINGS_TYPE;

    public static ItemType<ItemCharcoalStack> CHARCOAL_TYPE;

    public static ItemType<ItemChemicalHeatStack> CHEMICAL_HEAT_TYPE;

    public static ItemType<ItemChemistryTableStack> CHEMISTRY_TABLE_TYPE;

    public static ItemType<ItemCherryBoatStack> CHERRY_BOAT_TYPE;

    public static ItemType<ItemCherryButtonStack> CHERRY_BUTTON_TYPE;

    public static ItemType<ItemCherryChestBoatStack> CHERRY_CHEST_BOAT_TYPE;

    public static ItemType<ItemCherryDoorStack> CHERRY_DOOR_TYPE;

    public static ItemType<ItemCherryDoubleSlabStack> CHERRY_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemCherryFenceStack> CHERRY_FENCE_TYPE;

    public static ItemType<ItemCherryFenceGateStack> CHERRY_FENCE_GATE_TYPE;

    public static ItemType<ItemCherryHangingSignStack> CHERRY_HANGING_SIGN_TYPE;

    public static ItemType<ItemCherryLeavesStack> CHERRY_LEAVES_TYPE;

    public static ItemType<ItemCherryLogStack> CHERRY_LOG_TYPE;

    public static ItemType<ItemCherryPlanksStack> CHERRY_PLANKS_TYPE;

    public static ItemType<ItemCherryPressurePlateStack> CHERRY_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemCherrySaplingStack> CHERRY_SAPLING_TYPE;

    public static ItemType<ItemCherrySignStack> CHERRY_SIGN_TYPE;

    public static ItemType<ItemCherrySlabStack> CHERRY_SLAB_TYPE;

    public static ItemType<ItemCherryStairsStack> CHERRY_STAIRS_TYPE;

    public static ItemType<ItemCherryStandingSignStack> CHERRY_STANDING_SIGN_TYPE;

    public static ItemType<ItemCherryTrapdoorStack> CHERRY_TRAPDOOR_TYPE;

    public static ItemType<ItemCherryWallSignStack> CHERRY_WALL_SIGN_TYPE;

    public static ItemType<ItemCherryWoodStack> CHERRY_WOOD_TYPE;

    public static ItemType<ItemChestStack> CHEST_TYPE;

    public static ItemType<ItemChestBoatStack> CHEST_BOAT_TYPE;

    public static ItemType<ItemChestMinecartStack> CHEST_MINECART_TYPE;

    public static ItemType<ItemChickenStack> CHICKEN_TYPE;

    public static ItemType<ItemChickenSpawnEggStack> CHICKEN_SPAWN_EGG_TYPE;

    public static ItemType<ItemChiseledBookshelfStack> CHISELED_BOOKSHELF_TYPE;

    public static ItemType<ItemChiseledCopperStack> CHISELED_COPPER_TYPE;

    public static ItemType<ItemChiseledDeepslateStack> CHISELED_DEEPSLATE_TYPE;

    public static ItemType<ItemChiseledNetherBricksStack> CHISELED_NETHER_BRICKS_TYPE;

    public static ItemType<ItemChiseledPolishedBlackstoneStack> CHISELED_POLISHED_BLACKSTONE_TYPE;

    public static ItemType<ItemChiseledTuffStack> CHISELED_TUFF_TYPE;

    public static ItemType<ItemChiseledTuffBricksStack> CHISELED_TUFF_BRICKS_TYPE;

    public static ItemType<ItemChorusFlowerStack> CHORUS_FLOWER_TYPE;

    public static ItemType<ItemChorusFruitStack> CHORUS_FRUIT_TYPE;

    public static ItemType<ItemChorusPlantStack> CHORUS_PLANT_TYPE;

    public static ItemType<ItemClayStack> CLAY_TYPE;

    public static ItemType<ItemClayBallStack> CLAY_BALL_TYPE;

    public static ItemType<ItemClientRequestPlaceholderBlockStack> CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE;

    public static ItemType<ItemClockStack> CLOCK_TYPE;

    public static ItemType<ItemCoalStack> COAL_TYPE;

    public static ItemType<ItemCoalBlockStack> COAL_BLOCK_TYPE;

    public static ItemType<ItemCoalOreStack> COAL_ORE_TYPE;

    public static ItemType<ItemCoastArmorTrimSmithingTemplateStack> COAST_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemCobbledDeepslateStack> COBBLED_DEEPSLATE_TYPE;

    public static ItemType<ItemCobbledDeepslateDoubleSlabStack> COBBLED_DEEPSLATE_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemCobbledDeepslateSlabStack> COBBLED_DEEPSLATE_SLAB_TYPE;

    public static ItemType<ItemCobbledDeepslateStairsStack> COBBLED_DEEPSLATE_STAIRS_TYPE;

    public static ItemType<ItemCobbledDeepslateWallStack> COBBLED_DEEPSLATE_WALL_TYPE;

    public static ItemType<ItemCobblestoneStack> COBBLESTONE_TYPE;

    public static ItemType<ItemCobblestoneWallStack> COBBLESTONE_WALL_TYPE;

    public static ItemType<ItemCocoaStack> COCOA_TYPE;

    public static ItemType<ItemCocoaBeansStack> COCOA_BEANS_TYPE;

    public static ItemType<ItemCodStack> COD_TYPE;

    public static ItemType<ItemCodBucketStack> COD_BUCKET_TYPE;

    public static ItemType<ItemCodSpawnEggStack> COD_SPAWN_EGG_TYPE;

    public static ItemType<ItemColoredTorchBpStack> COLORED_TORCH_BP_TYPE;

    public static ItemType<ItemColoredTorchRgStack> COLORED_TORCH_RG_TYPE;

    public static ItemType<ItemCommandBlockStack> COMMAND_BLOCK_TYPE;

    public static ItemType<ItemCommandBlockMinecartStack> COMMAND_BLOCK_MINECART_TYPE;

    public static ItemType<ItemComparatorStack> COMPARATOR_TYPE;

    public static ItemType<ItemCompassStack> COMPASS_TYPE;

    public static ItemType<ItemComposterStack> COMPOSTER_TYPE;

    public static ItemType<ItemCompoundStack> COMPOUND_TYPE;

    public static ItemType<ItemConcreteStack> CONCRETE_TYPE;

    public static ItemType<ItemConcretePowderStack> CONCRETE_POWDER_TYPE;

    public static ItemType<ItemConduitStack> CONDUIT_TYPE;

    public static ItemType<ItemCookedBeefStack> COOKED_BEEF_TYPE;

    public static ItemType<ItemCookedChickenStack> COOKED_CHICKEN_TYPE;

    public static ItemType<ItemCookedCodStack> COOKED_COD_TYPE;

    public static ItemType<ItemCookedMuttonStack> COOKED_MUTTON_TYPE;

    public static ItemType<ItemCookedPorkchopStack> COOKED_PORKCHOP_TYPE;

    public static ItemType<ItemCookedRabbitStack> COOKED_RABBIT_TYPE;

    public static ItemType<ItemCookedSalmonStack> COOKED_SALMON_TYPE;

    public static ItemType<ItemCookieStack> COOKIE_TYPE;

    public static ItemType<ItemCopperBlockStack> COPPER_BLOCK_TYPE;

    public static ItemType<ItemCopperBulbStack> COPPER_BULB_TYPE;

    public static ItemType<ItemCopperDoorStack> COPPER_DOOR_TYPE;

    public static ItemType<ItemCopperGrateStack> COPPER_GRATE_TYPE;

    public static ItemType<ItemCopperIngotStack> COPPER_INGOT_TYPE;

    public static ItemType<ItemCopperOreStack> COPPER_ORE_TYPE;

    public static ItemType<ItemCopperTrapdoorStack> COPPER_TRAPDOOR_TYPE;

    public static ItemType<ItemCoralStack> CORAL_TYPE;

    public static ItemType<ItemCoralBlockStack> CORAL_BLOCK_TYPE;

    public static ItemType<ItemCoralFanStack> CORAL_FAN_TYPE;

    public static ItemType<ItemCoralFanDeadStack> CORAL_FAN_DEAD_TYPE;

    public static ItemType<ItemCoralFanHangStack> CORAL_FAN_HANG_TYPE;

    public static ItemType<ItemCoralFanHang2Stack> CORAL_FAN_HANG2_TYPE;

    public static ItemType<ItemCoralFanHang3Stack> CORAL_FAN_HANG3_TYPE;

    public static ItemType<ItemCornflowerStack> CORNFLOWER_TYPE;

    public static ItemType<ItemCowSpawnEggStack> COW_SPAWN_EGG_TYPE;

    public static ItemType<ItemCrackedDeepslateBricksStack> CRACKED_DEEPSLATE_BRICKS_TYPE;

    public static ItemType<ItemCrackedDeepslateTilesStack> CRACKED_DEEPSLATE_TILES_TYPE;

    public static ItemType<ItemCrackedNetherBricksStack> CRACKED_NETHER_BRICKS_TYPE;

    public static ItemType<ItemCrackedPolishedBlackstoneBricksStack> CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE;

    public static ItemType<ItemCrafterStack> CRAFTER_TYPE;

    public static ItemType<ItemCraftingTableStack> CRAFTING_TABLE_TYPE;

    public static ItemType<ItemCreeperBannerPatternStack> CREEPER_BANNER_PATTERN_TYPE;

    public static ItemType<ItemCreeperSpawnEggStack> CREEPER_SPAWN_EGG_TYPE;

    public static ItemType<ItemCrimsonButtonStack> CRIMSON_BUTTON_TYPE;

    public static ItemType<ItemCrimsonDoorStack> CRIMSON_DOOR_TYPE;

    public static ItemType<ItemCrimsonDoubleSlabStack> CRIMSON_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemCrimsonFenceStack> CRIMSON_FENCE_TYPE;

    public static ItemType<ItemCrimsonFenceGateStack> CRIMSON_FENCE_GATE_TYPE;

    public static ItemType<ItemCrimsonFungusStack> CRIMSON_FUNGUS_TYPE;

    public static ItemType<ItemCrimsonHangingSignStack> CRIMSON_HANGING_SIGN_TYPE;

    public static ItemType<ItemCrimsonHyphaeStack> CRIMSON_HYPHAE_TYPE;

    public static ItemType<ItemCrimsonNyliumStack> CRIMSON_NYLIUM_TYPE;

    public static ItemType<ItemCrimsonPlanksStack> CRIMSON_PLANKS_TYPE;

    public static ItemType<ItemCrimsonPressurePlateStack> CRIMSON_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemCrimsonRootsStack> CRIMSON_ROOTS_TYPE;

    public static ItemType<ItemCrimsonSignStack> CRIMSON_SIGN_TYPE;

    public static ItemType<ItemCrimsonSlabStack> CRIMSON_SLAB_TYPE;

    public static ItemType<ItemCrimsonStairsStack> CRIMSON_STAIRS_TYPE;

    public static ItemType<ItemCrimsonStandingSignStack> CRIMSON_STANDING_SIGN_TYPE;

    public static ItemType<ItemCrimsonStemStack> CRIMSON_STEM_TYPE;

    public static ItemType<ItemCrimsonTrapdoorStack> CRIMSON_TRAPDOOR_TYPE;

    public static ItemType<ItemCrimsonWallSignStack> CRIMSON_WALL_SIGN_TYPE;

    public static ItemType<ItemCrossbowStack> CROSSBOW_TYPE;

    public static ItemType<ItemCryingObsidianStack> CRYING_OBSIDIAN_TYPE;

    public static ItemType<ItemCutCopperStack> CUT_COPPER_TYPE;

    public static ItemType<ItemCutCopperSlabStack> CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemCutCopperStairsStack> CUT_COPPER_STAIRS_TYPE;

    public static ItemType<ItemCyanCandleStack> CYAN_CANDLE_TYPE;

    public static ItemType<ItemCyanCandleCakeStack> CYAN_CANDLE_CAKE_TYPE;

    public static ItemType<ItemCyanCarpetStack> CYAN_CARPET_TYPE;

    public static ItemType<ItemCyanConcreteStack> CYAN_CONCRETE_TYPE;

    public static ItemType<ItemCyanConcretePowderStack> CYAN_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemCyanDyeStack> CYAN_DYE_TYPE;

    public static ItemType<ItemCyanGlazedTerracottaStack> CYAN_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemCyanShulkerBoxStack> CYAN_SHULKER_BOX_TYPE;

    public static ItemType<ItemCyanStainedGlassStack> CYAN_STAINED_GLASS_TYPE;

    public static ItemType<ItemCyanStainedGlassPaneStack> CYAN_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemCyanTerracottaStack> CYAN_TERRACOTTA_TYPE;

    public static ItemType<ItemCyanWoolStack> CYAN_WOOL_TYPE;

    public static ItemType<ItemDangerPotterySherdStack> DANGER_POTTERY_SHERD_TYPE;

    public static ItemType<ItemDarkOakBoatStack> DARK_OAK_BOAT_TYPE;

    public static ItemType<ItemDarkOakButtonStack> DARK_OAK_BUTTON_TYPE;

    public static ItemType<ItemDarkOakChestBoatStack> DARK_OAK_CHEST_BOAT_TYPE;

    public static ItemType<ItemDarkOakDoorStack> DARK_OAK_DOOR_TYPE;

    public static ItemType<ItemDarkOakDoubleSlabStack> DARK_OAK_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemDarkOakFenceStack> DARK_OAK_FENCE_TYPE;

    public static ItemType<ItemDarkOakFenceGateStack> DARK_OAK_FENCE_GATE_TYPE;

    public static ItemType<ItemDarkOakHangingSignStack> DARK_OAK_HANGING_SIGN_TYPE;

    public static ItemType<ItemDarkOakLeavesStack> DARK_OAK_LEAVES_TYPE;

    public static ItemType<ItemDarkOakLogStack> DARK_OAK_LOG_TYPE;

    public static ItemType<ItemDarkOakPlanksStack> DARK_OAK_PLANKS_TYPE;

    public static ItemType<ItemDarkOakPressurePlateStack> DARK_OAK_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemDarkOakSaplingStack> DARK_OAK_SAPLING_TYPE;

    public static ItemType<ItemDarkOakSignStack> DARK_OAK_SIGN_TYPE;

    public static ItemType<ItemDarkOakSlabStack> DARK_OAK_SLAB_TYPE;

    public static ItemType<ItemDarkOakStairsStack> DARK_OAK_STAIRS_TYPE;

    public static ItemType<ItemDarkOakTrapdoorStack> DARK_OAK_TRAPDOOR_TYPE;

    public static ItemType<ItemDarkOakWoodStack> DARK_OAK_WOOD_TYPE;

    public static ItemType<ItemDarkPrismarineStairsStack> DARK_PRISMARINE_STAIRS_TYPE;

    public static ItemType<ItemDarkoakStandingSignStack> DARKOAK_STANDING_SIGN_TYPE;

    public static ItemType<ItemDarkoakWallSignStack> DARKOAK_WALL_SIGN_TYPE;

    public static ItemType<ItemDaylightDetectorStack> DAYLIGHT_DETECTOR_TYPE;

    public static ItemType<ItemDaylightDetectorInvertedStack> DAYLIGHT_DETECTOR_INVERTED_TYPE;

    public static ItemType<ItemDeadBrainCoralStack> DEAD_BRAIN_CORAL_TYPE;

    public static ItemType<ItemDeadBrainCoralFanStack> DEAD_BRAIN_CORAL_FAN_TYPE;

    public static ItemType<ItemDeadBubbleCoralStack> DEAD_BUBBLE_CORAL_TYPE;

    public static ItemType<ItemDeadBubbleCoralFanStack> DEAD_BUBBLE_CORAL_FAN_TYPE;

    public static ItemType<ItemDeadFireCoralStack> DEAD_FIRE_CORAL_TYPE;

    public static ItemType<ItemDeadFireCoralFanStack> DEAD_FIRE_CORAL_FAN_TYPE;

    public static ItemType<ItemDeadHornCoralStack> DEAD_HORN_CORAL_TYPE;

    public static ItemType<ItemDeadHornCoralFanStack> DEAD_HORN_CORAL_FAN_TYPE;

    public static ItemType<ItemDeadTubeCoralStack> DEAD_TUBE_CORAL_TYPE;

    public static ItemType<ItemDeadTubeCoralFanStack> DEAD_TUBE_CORAL_FAN_TYPE;

    public static ItemType<ItemDeadbushStack> DEADBUSH_TYPE;

    public static ItemType<ItemDecoratedPotStack> DECORATED_POT_TYPE;

    public static ItemType<ItemDeepslateStack> DEEPSLATE_TYPE;

    public static ItemType<ItemDeepslateBrickDoubleSlabStack> DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemDeepslateBrickSlabStack> DEEPSLATE_BRICK_SLAB_TYPE;

    public static ItemType<ItemDeepslateBrickStairsStack> DEEPSLATE_BRICK_STAIRS_TYPE;

    public static ItemType<ItemDeepslateBrickWallStack> DEEPSLATE_BRICK_WALL_TYPE;

    public static ItemType<ItemDeepslateBricksStack> DEEPSLATE_BRICKS_TYPE;

    public static ItemType<ItemDeepslateCoalOreStack> DEEPSLATE_COAL_ORE_TYPE;

    public static ItemType<ItemDeepslateCopperOreStack> DEEPSLATE_COPPER_ORE_TYPE;

    public static ItemType<ItemDeepslateDiamondOreStack> DEEPSLATE_DIAMOND_ORE_TYPE;

    public static ItemType<ItemDeepslateEmeraldOreStack> DEEPSLATE_EMERALD_ORE_TYPE;

    public static ItemType<ItemDeepslateGoldOreStack> DEEPSLATE_GOLD_ORE_TYPE;

    public static ItemType<ItemDeepslateIronOreStack> DEEPSLATE_IRON_ORE_TYPE;

    public static ItemType<ItemDeepslateLapisOreStack> DEEPSLATE_LAPIS_ORE_TYPE;

    public static ItemType<ItemDeepslateRedstoneOreStack> DEEPSLATE_REDSTONE_ORE_TYPE;

    public static ItemType<ItemDeepslateTileDoubleSlabStack> DEEPSLATE_TILE_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemDeepslateTileSlabStack> DEEPSLATE_TILE_SLAB_TYPE;

    public static ItemType<ItemDeepslateTileStairsStack> DEEPSLATE_TILE_STAIRS_TYPE;

    public static ItemType<ItemDeepslateTileWallStack> DEEPSLATE_TILE_WALL_TYPE;

    public static ItemType<ItemDeepslateTilesStack> DEEPSLATE_TILES_TYPE;

    public static ItemType<ItemDenyStack> DENY_TYPE;

    public static ItemType<ItemDetectorRailStack> DETECTOR_RAIL_TYPE;

    public static ItemType<ItemDiamondStack> DIAMOND_TYPE;

    public static ItemType<ItemDiamondAxeStack> DIAMOND_AXE_TYPE;

    public static ItemType<ItemDiamondBlockStack> DIAMOND_BLOCK_TYPE;

    public static ItemType<ItemDiamondBootsStack> DIAMOND_BOOTS_TYPE;

    public static ItemType<ItemDiamondChestplateStack> DIAMOND_CHESTPLATE_TYPE;

    public static ItemType<ItemDiamondHelmetStack> DIAMOND_HELMET_TYPE;

    public static ItemType<ItemDiamondHoeStack> DIAMOND_HOE_TYPE;

    public static ItemType<ItemDiamondHorseArmorStack> DIAMOND_HORSE_ARMOR_TYPE;

    public static ItemType<ItemDiamondLeggingsStack> DIAMOND_LEGGINGS_TYPE;

    public static ItemType<ItemDiamondOreStack> DIAMOND_ORE_TYPE;

    public static ItemType<ItemDiamondPickaxeStack> DIAMOND_PICKAXE_TYPE;

    public static ItemType<ItemDiamondShovelStack> DIAMOND_SHOVEL_TYPE;

    public static ItemType<ItemDiamondSwordStack> DIAMOND_SWORD_TYPE;

    public static ItemType<ItemDioriteStack> DIORITE_TYPE;

    public static ItemType<ItemDioriteStairsStack> DIORITE_STAIRS_TYPE;

    public static ItemType<ItemDirtStack> DIRT_TYPE;

    public static ItemType<ItemDirtWithRootsStack> DIRT_WITH_ROOTS_TYPE;

    public static ItemType<ItemDiscFragment5Stack> DISC_FRAGMENT_5_TYPE;

    public static ItemType<ItemDispenserStack> DISPENSER_TYPE;

    public static ItemType<ItemDolphinSpawnEggStack> DOLPHIN_SPAWN_EGG_TYPE;

    public static ItemType<ItemDonkeySpawnEggStack> DONKEY_SPAWN_EGG_TYPE;

    public static ItemType<ItemDoubleCutCopperSlabStack> DOUBLE_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemDoublePlantStack> DOUBLE_PLANT_TYPE;

    public static ItemType<ItemDoubleStoneBlockSlabStack> DOUBLE_STONE_BLOCK_SLAB_TYPE;

    public static ItemType<ItemDoubleStoneBlockSlab2Stack> DOUBLE_STONE_BLOCK_SLAB2_TYPE;

    public static ItemType<ItemDoubleStoneBlockSlab3Stack> DOUBLE_STONE_BLOCK_SLAB3_TYPE;

    public static ItemType<ItemDoubleStoneBlockSlab4Stack> DOUBLE_STONE_BLOCK_SLAB4_TYPE;

    public static ItemType<ItemDragonBreathStack> DRAGON_BREATH_TYPE;

    public static ItemType<ItemDragonEggStack> DRAGON_EGG_TYPE;

    public static ItemType<ItemDriedKelpStack> DRIED_KELP_TYPE;

    public static ItemType<ItemDriedKelpBlockStack> DRIED_KELP_BLOCK_TYPE;

    public static ItemType<ItemDripstoneBlockStack> DRIPSTONE_BLOCK_TYPE;

    public static ItemType<ItemDropperStack> DROPPER_TYPE;

    public static ItemType<ItemDrownedSpawnEggStack> DROWNED_SPAWN_EGG_TYPE;

    public static ItemType<ItemDuneArmorTrimSmithingTemplateStack> DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemDyeStack> DYE_TYPE;

    public static ItemType<ItemEchoShardStack> ECHO_SHARD_TYPE;

    public static ItemType<ItemEggStack> EGG_TYPE;

    public static ItemType<ItemElderGuardianSpawnEggStack> ELDER_GUARDIAN_SPAWN_EGG_TYPE;

    public static ItemType<ItemElement0Stack> ELEMENT_0_TYPE;

    public static ItemType<ItemElement1Stack> ELEMENT_1_TYPE;

    public static ItemType<ItemElement10Stack> ELEMENT_10_TYPE;

    public static ItemType<ItemElement100Stack> ELEMENT_100_TYPE;

    public static ItemType<ItemElement101Stack> ELEMENT_101_TYPE;

    public static ItemType<ItemElement102Stack> ELEMENT_102_TYPE;

    public static ItemType<ItemElement103Stack> ELEMENT_103_TYPE;

    public static ItemType<ItemElement104Stack> ELEMENT_104_TYPE;

    public static ItemType<ItemElement105Stack> ELEMENT_105_TYPE;

    public static ItemType<ItemElement106Stack> ELEMENT_106_TYPE;

    public static ItemType<ItemElement107Stack> ELEMENT_107_TYPE;

    public static ItemType<ItemElement108Stack> ELEMENT_108_TYPE;

    public static ItemType<ItemElement109Stack> ELEMENT_109_TYPE;

    public static ItemType<ItemElement11Stack> ELEMENT_11_TYPE;

    public static ItemType<ItemElement110Stack> ELEMENT_110_TYPE;

    public static ItemType<ItemElement111Stack> ELEMENT_111_TYPE;

    public static ItemType<ItemElement112Stack> ELEMENT_112_TYPE;

    public static ItemType<ItemElement113Stack> ELEMENT_113_TYPE;

    public static ItemType<ItemElement114Stack> ELEMENT_114_TYPE;

    public static ItemType<ItemElement115Stack> ELEMENT_115_TYPE;

    public static ItemType<ItemElement116Stack> ELEMENT_116_TYPE;

    public static ItemType<ItemElement117Stack> ELEMENT_117_TYPE;

    public static ItemType<ItemElement118Stack> ELEMENT_118_TYPE;

    public static ItemType<ItemElement12Stack> ELEMENT_12_TYPE;

    public static ItemType<ItemElement13Stack> ELEMENT_13_TYPE;

    public static ItemType<ItemElement14Stack> ELEMENT_14_TYPE;

    public static ItemType<ItemElement15Stack> ELEMENT_15_TYPE;

    public static ItemType<ItemElement16Stack> ELEMENT_16_TYPE;

    public static ItemType<ItemElement17Stack> ELEMENT_17_TYPE;

    public static ItemType<ItemElement18Stack> ELEMENT_18_TYPE;

    public static ItemType<ItemElement19Stack> ELEMENT_19_TYPE;

    public static ItemType<ItemElement2Stack> ELEMENT_2_TYPE;

    public static ItemType<ItemElement20Stack> ELEMENT_20_TYPE;

    public static ItemType<ItemElement21Stack> ELEMENT_21_TYPE;

    public static ItemType<ItemElement22Stack> ELEMENT_22_TYPE;

    public static ItemType<ItemElement23Stack> ELEMENT_23_TYPE;

    public static ItemType<ItemElement24Stack> ELEMENT_24_TYPE;

    public static ItemType<ItemElement25Stack> ELEMENT_25_TYPE;

    public static ItemType<ItemElement26Stack> ELEMENT_26_TYPE;

    public static ItemType<ItemElement27Stack> ELEMENT_27_TYPE;

    public static ItemType<ItemElement28Stack> ELEMENT_28_TYPE;

    public static ItemType<ItemElement29Stack> ELEMENT_29_TYPE;

    public static ItemType<ItemElement3Stack> ELEMENT_3_TYPE;

    public static ItemType<ItemElement30Stack> ELEMENT_30_TYPE;

    public static ItemType<ItemElement31Stack> ELEMENT_31_TYPE;

    public static ItemType<ItemElement32Stack> ELEMENT_32_TYPE;

    public static ItemType<ItemElement33Stack> ELEMENT_33_TYPE;

    public static ItemType<ItemElement34Stack> ELEMENT_34_TYPE;

    public static ItemType<ItemElement35Stack> ELEMENT_35_TYPE;

    public static ItemType<ItemElement36Stack> ELEMENT_36_TYPE;

    public static ItemType<ItemElement37Stack> ELEMENT_37_TYPE;

    public static ItemType<ItemElement38Stack> ELEMENT_38_TYPE;

    public static ItemType<ItemElement39Stack> ELEMENT_39_TYPE;

    public static ItemType<ItemElement4Stack> ELEMENT_4_TYPE;

    public static ItemType<ItemElement40Stack> ELEMENT_40_TYPE;

    public static ItemType<ItemElement41Stack> ELEMENT_41_TYPE;

    public static ItemType<ItemElement42Stack> ELEMENT_42_TYPE;

    public static ItemType<ItemElement43Stack> ELEMENT_43_TYPE;

    public static ItemType<ItemElement44Stack> ELEMENT_44_TYPE;

    public static ItemType<ItemElement45Stack> ELEMENT_45_TYPE;

    public static ItemType<ItemElement46Stack> ELEMENT_46_TYPE;

    public static ItemType<ItemElement47Stack> ELEMENT_47_TYPE;

    public static ItemType<ItemElement48Stack> ELEMENT_48_TYPE;

    public static ItemType<ItemElement49Stack> ELEMENT_49_TYPE;

    public static ItemType<ItemElement5Stack> ELEMENT_5_TYPE;

    public static ItemType<ItemElement50Stack> ELEMENT_50_TYPE;

    public static ItemType<ItemElement51Stack> ELEMENT_51_TYPE;

    public static ItemType<ItemElement52Stack> ELEMENT_52_TYPE;

    public static ItemType<ItemElement53Stack> ELEMENT_53_TYPE;

    public static ItemType<ItemElement54Stack> ELEMENT_54_TYPE;

    public static ItemType<ItemElement55Stack> ELEMENT_55_TYPE;

    public static ItemType<ItemElement56Stack> ELEMENT_56_TYPE;

    public static ItemType<ItemElement57Stack> ELEMENT_57_TYPE;

    public static ItemType<ItemElement58Stack> ELEMENT_58_TYPE;

    public static ItemType<ItemElement59Stack> ELEMENT_59_TYPE;

    public static ItemType<ItemElement6Stack> ELEMENT_6_TYPE;

    public static ItemType<ItemElement60Stack> ELEMENT_60_TYPE;

    public static ItemType<ItemElement61Stack> ELEMENT_61_TYPE;

    public static ItemType<ItemElement62Stack> ELEMENT_62_TYPE;

    public static ItemType<ItemElement63Stack> ELEMENT_63_TYPE;

    public static ItemType<ItemElement64Stack> ELEMENT_64_TYPE;

    public static ItemType<ItemElement65Stack> ELEMENT_65_TYPE;

    public static ItemType<ItemElement66Stack> ELEMENT_66_TYPE;

    public static ItemType<ItemElement67Stack> ELEMENT_67_TYPE;

    public static ItemType<ItemElement68Stack> ELEMENT_68_TYPE;

    public static ItemType<ItemElement69Stack> ELEMENT_69_TYPE;

    public static ItemType<ItemElement7Stack> ELEMENT_7_TYPE;

    public static ItemType<ItemElement70Stack> ELEMENT_70_TYPE;

    public static ItemType<ItemElement71Stack> ELEMENT_71_TYPE;

    public static ItemType<ItemElement72Stack> ELEMENT_72_TYPE;

    public static ItemType<ItemElement73Stack> ELEMENT_73_TYPE;

    public static ItemType<ItemElement74Stack> ELEMENT_74_TYPE;

    public static ItemType<ItemElement75Stack> ELEMENT_75_TYPE;

    public static ItemType<ItemElement76Stack> ELEMENT_76_TYPE;

    public static ItemType<ItemElement77Stack> ELEMENT_77_TYPE;

    public static ItemType<ItemElement78Stack> ELEMENT_78_TYPE;

    public static ItemType<ItemElement79Stack> ELEMENT_79_TYPE;

    public static ItemType<ItemElement8Stack> ELEMENT_8_TYPE;

    public static ItemType<ItemElement80Stack> ELEMENT_80_TYPE;

    public static ItemType<ItemElement81Stack> ELEMENT_81_TYPE;

    public static ItemType<ItemElement82Stack> ELEMENT_82_TYPE;

    public static ItemType<ItemElement83Stack> ELEMENT_83_TYPE;

    public static ItemType<ItemElement84Stack> ELEMENT_84_TYPE;

    public static ItemType<ItemElement85Stack> ELEMENT_85_TYPE;

    public static ItemType<ItemElement86Stack> ELEMENT_86_TYPE;

    public static ItemType<ItemElement87Stack> ELEMENT_87_TYPE;

    public static ItemType<ItemElement88Stack> ELEMENT_88_TYPE;

    public static ItemType<ItemElement89Stack> ELEMENT_89_TYPE;

    public static ItemType<ItemElement9Stack> ELEMENT_9_TYPE;

    public static ItemType<ItemElement90Stack> ELEMENT_90_TYPE;

    public static ItemType<ItemElement91Stack> ELEMENT_91_TYPE;

    public static ItemType<ItemElement92Stack> ELEMENT_92_TYPE;

    public static ItemType<ItemElement93Stack> ELEMENT_93_TYPE;

    public static ItemType<ItemElement94Stack> ELEMENT_94_TYPE;

    public static ItemType<ItemElement95Stack> ELEMENT_95_TYPE;

    public static ItemType<ItemElement96Stack> ELEMENT_96_TYPE;

    public static ItemType<ItemElement97Stack> ELEMENT_97_TYPE;

    public static ItemType<ItemElement98Stack> ELEMENT_98_TYPE;

    public static ItemType<ItemElement99Stack> ELEMENT_99_TYPE;

    public static ItemType<ItemElytraStack> ELYTRA_TYPE;

    public static ItemType<ItemEmeraldStack> EMERALD_TYPE;

    public static ItemType<ItemEmeraldBlockStack> EMERALD_BLOCK_TYPE;

    public static ItemType<ItemEmeraldOreStack> EMERALD_ORE_TYPE;

    public static ItemType<ItemEmptyMapStack> EMPTY_MAP_TYPE;

    public static ItemType<ItemEnchantedBookStack> ENCHANTED_BOOK_TYPE;

    public static ItemType<ItemEnchantedGoldenAppleStack> ENCHANTED_GOLDEN_APPLE_TYPE;

    public static ItemType<ItemEnchantingTableStack> ENCHANTING_TABLE_TYPE;

    public static ItemType<ItemEndBrickStairsStack> END_BRICK_STAIRS_TYPE;

    public static ItemType<ItemEndBricksStack> END_BRICKS_TYPE;

    public static ItemType<ItemEndCrystalStack> END_CRYSTAL_TYPE;

    public static ItemType<ItemEndGatewayStack> END_GATEWAY_TYPE;

    public static ItemType<ItemEndPortalStack> END_PORTAL_TYPE;

    public static ItemType<ItemEndPortalFrameStack> END_PORTAL_FRAME_TYPE;

    public static ItemType<ItemEndRodStack> END_ROD_TYPE;

    public static ItemType<ItemEndStoneStack> END_STONE_TYPE;

    public static ItemType<ItemEnderChestStack> ENDER_CHEST_TYPE;

    public static ItemType<ItemEnderDragonSpawnEggStack> ENDER_DRAGON_SPAWN_EGG_TYPE;

    public static ItemType<ItemEnderEyeStack> ENDER_EYE_TYPE;

    public static ItemType<ItemEnderPearlStack> ENDER_PEARL_TYPE;

    public static ItemType<ItemEndermanSpawnEggStack> ENDERMAN_SPAWN_EGG_TYPE;

    public static ItemType<ItemEndermiteSpawnEggStack> ENDERMITE_SPAWN_EGG_TYPE;

    public static ItemType<ItemEvokerSpawnEggStack> EVOKER_SPAWN_EGG_TYPE;

    public static ItemType<ItemExperienceBottleStack> EXPERIENCE_BOTTLE_TYPE;

    public static ItemType<ItemExplorerPotterySherdStack> EXPLORER_POTTERY_SHERD_TYPE;

    public static ItemType<ItemExposedChiseledCopperStack> EXPOSED_CHISELED_COPPER_TYPE;

    public static ItemType<ItemExposedCopperStack> EXPOSED_COPPER_TYPE;

    public static ItemType<ItemExposedCopperBulbStack> EXPOSED_COPPER_BULB_TYPE;

    public static ItemType<ItemExposedCopperDoorStack> EXPOSED_COPPER_DOOR_TYPE;

    public static ItemType<ItemExposedCopperGrateStack> EXPOSED_COPPER_GRATE_TYPE;

    public static ItemType<ItemExposedCopperTrapdoorStack> EXPOSED_COPPER_TRAPDOOR_TYPE;

    public static ItemType<ItemExposedCutCopperStack> EXPOSED_CUT_COPPER_TYPE;

    public static ItemType<ItemExposedCutCopperSlabStack> EXPOSED_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemExposedCutCopperStairsStack> EXPOSED_CUT_COPPER_STAIRS_TYPE;

    public static ItemType<ItemExposedDoubleCutCopperSlabStack> EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemEyeArmorTrimSmithingTemplateStack> EYE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemFarmlandStack> FARMLAND_TYPE;

    public static ItemType<ItemFeatherStack> FEATHER_TYPE;

    public static ItemType<ItemFenceStack> FENCE_TYPE;

    public static ItemType<ItemFenceGateStack> FENCE_GATE_TYPE;

    public static ItemType<ItemFermentedSpiderEyeStack> FERMENTED_SPIDER_EYE_TYPE;

    public static ItemType<ItemFieldMasonedBannerPatternStack> FIELD_MASONED_BANNER_PATTERN_TYPE;

    public static ItemType<ItemFilledMapStack> FILLED_MAP_TYPE;

    public static ItemType<ItemFireStack> FIRE_TYPE;

    public static ItemType<ItemFireChargeStack> FIRE_CHARGE_TYPE;

    public static ItemType<ItemFireCoralStack> FIRE_CORAL_TYPE;

    public static ItemType<ItemFireCoralFanStack> FIRE_CORAL_FAN_TYPE;

    public static ItemType<ItemFireworkRocketStack> FIREWORK_ROCKET_TYPE;

    public static ItemType<ItemFireworkStarStack> FIREWORK_STAR_TYPE;

    public static ItemType<ItemFishingRodStack> FISHING_ROD_TYPE;

    public static ItemType<ItemFletchingTableStack> FLETCHING_TABLE_TYPE;

    public static ItemType<ItemFlintStack> FLINT_TYPE;

    public static ItemType<ItemFlintAndSteelStack> FLINT_AND_STEEL_TYPE;

    public static ItemType<ItemFlowArmorTrimSmithingTemplateStack> FLOW_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemFlowBannerPatternStack> FLOW_BANNER_PATTERN_TYPE;

    public static ItemType<ItemFlowPotterySherdStack> FLOW_POTTERY_SHERD_TYPE;

    public static ItemType<ItemFlowerBannerPatternStack> FLOWER_BANNER_PATTERN_TYPE;

    public static ItemType<ItemFlowerPotStack> FLOWER_POT_TYPE;

    public static ItemType<ItemFloweringAzaleaStack> FLOWERING_AZALEA_TYPE;

    public static ItemType<ItemFlowingLavaStack> FLOWING_LAVA_TYPE;

    public static ItemType<ItemFlowingWaterStack> FLOWING_WATER_TYPE;

    public static ItemType<ItemFoxSpawnEggStack> FOX_SPAWN_EGG_TYPE;

    public static ItemType<ItemFrameStack> FRAME_TYPE;

    public static ItemType<ItemFriendPotterySherdStack> FRIEND_POTTERY_SHERD_TYPE;

    public static ItemType<ItemFrogSpawnStack> FROG_SPAWN_TYPE;

    public static ItemType<ItemFrogSpawnEggStack> FROG_SPAWN_EGG_TYPE;

    public static ItemType<ItemFrostedIceStack> FROSTED_ICE_TYPE;

    public static ItemType<ItemFurnaceStack> FURNACE_TYPE;

    public static ItemType<ItemGhastSpawnEggStack> GHAST_SPAWN_EGG_TYPE;

    public static ItemType<ItemGhastTearStack> GHAST_TEAR_TYPE;

    public static ItemType<ItemGildedBlackstoneStack> GILDED_BLACKSTONE_TYPE;

    public static ItemType<ItemGlassStack> GLASS_TYPE;

    public static ItemType<ItemGlassBottleStack> GLASS_BOTTLE_TYPE;

    public static ItemType<ItemGlassPaneStack> GLASS_PANE_TYPE;

    public static ItemType<ItemGlisteringMelonSliceStack> GLISTERING_MELON_SLICE_TYPE;

    public static ItemType<ItemGlobeBannerPatternStack> GLOBE_BANNER_PATTERN_TYPE;

    public static ItemType<ItemGlowBerriesStack> GLOW_BERRIES_TYPE;

    public static ItemType<ItemGlowFrameStack> GLOW_FRAME_TYPE;

    public static ItemType<ItemGlowInkSacStack> GLOW_INK_SAC_TYPE;

    public static ItemType<ItemGlowLichenStack> GLOW_LICHEN_TYPE;

    public static ItemType<ItemGlowSquidSpawnEggStack> GLOW_SQUID_SPAWN_EGG_TYPE;

    public static ItemType<ItemGlowStickStack> GLOW_STICK_TYPE;

    public static ItemType<ItemGlowingobsidianStack> GLOWINGOBSIDIAN_TYPE;

    public static ItemType<ItemGlowstoneStack> GLOWSTONE_TYPE;

    public static ItemType<ItemGlowstoneDustStack> GLOWSTONE_DUST_TYPE;

    public static ItemType<ItemGoatHornStack> GOAT_HORN_TYPE;

    public static ItemType<ItemGoatSpawnEggStack> GOAT_SPAWN_EGG_TYPE;

    public static ItemType<ItemGoldBlockStack> GOLD_BLOCK_TYPE;

    public static ItemType<ItemGoldIngotStack> GOLD_INGOT_TYPE;

    public static ItemType<ItemGoldNuggetStack> GOLD_NUGGET_TYPE;

    public static ItemType<ItemGoldOreStack> GOLD_ORE_TYPE;

    public static ItemType<ItemGoldenAppleStack> GOLDEN_APPLE_TYPE;

    public static ItemType<ItemGoldenAxeStack> GOLDEN_AXE_TYPE;

    public static ItemType<ItemGoldenBootsStack> GOLDEN_BOOTS_TYPE;

    public static ItemType<ItemGoldenCarrotStack> GOLDEN_CARROT_TYPE;

    public static ItemType<ItemGoldenChestplateStack> GOLDEN_CHESTPLATE_TYPE;

    public static ItemType<ItemGoldenHelmetStack> GOLDEN_HELMET_TYPE;

    public static ItemType<ItemGoldenHoeStack> GOLDEN_HOE_TYPE;

    public static ItemType<ItemGoldenHorseArmorStack> GOLDEN_HORSE_ARMOR_TYPE;

    public static ItemType<ItemGoldenLeggingsStack> GOLDEN_LEGGINGS_TYPE;

    public static ItemType<ItemGoldenPickaxeStack> GOLDEN_PICKAXE_TYPE;

    public static ItemType<ItemGoldenRailStack> GOLDEN_RAIL_TYPE;

    public static ItemType<ItemGoldenShovelStack> GOLDEN_SHOVEL_TYPE;

    public static ItemType<ItemGoldenSwordStack> GOLDEN_SWORD_TYPE;

    public static ItemType<ItemGraniteStack> GRANITE_TYPE;

    public static ItemType<ItemGraniteStairsStack> GRANITE_STAIRS_TYPE;

    public static ItemType<ItemGrassBlockStack> GRASS_BLOCK_TYPE;

    public static ItemType<ItemGrassPathStack> GRASS_PATH_TYPE;

    public static ItemType<ItemGravelStack> GRAVEL_TYPE;

    public static ItemType<ItemGrayCandleStack> GRAY_CANDLE_TYPE;

    public static ItemType<ItemGrayCandleCakeStack> GRAY_CANDLE_CAKE_TYPE;

    public static ItemType<ItemGrayCarpetStack> GRAY_CARPET_TYPE;

    public static ItemType<ItemGrayConcreteStack> GRAY_CONCRETE_TYPE;

    public static ItemType<ItemGrayConcretePowderStack> GRAY_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemGrayDyeStack> GRAY_DYE_TYPE;

    public static ItemType<ItemGrayGlazedTerracottaStack> GRAY_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemGrayShulkerBoxStack> GRAY_SHULKER_BOX_TYPE;

    public static ItemType<ItemGrayStainedGlassStack> GRAY_STAINED_GLASS_TYPE;

    public static ItemType<ItemGrayStainedGlassPaneStack> GRAY_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemGrayTerracottaStack> GRAY_TERRACOTTA_TYPE;

    public static ItemType<ItemGrayWoolStack> GRAY_WOOL_TYPE;

    public static ItemType<ItemGreenCandleStack> GREEN_CANDLE_TYPE;

    public static ItemType<ItemGreenCandleCakeStack> GREEN_CANDLE_CAKE_TYPE;

    public static ItemType<ItemGreenCarpetStack> GREEN_CARPET_TYPE;

    public static ItemType<ItemGreenConcreteStack> GREEN_CONCRETE_TYPE;

    public static ItemType<ItemGreenConcretePowderStack> GREEN_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemGreenDyeStack> GREEN_DYE_TYPE;

    public static ItemType<ItemGreenGlazedTerracottaStack> GREEN_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemGreenShulkerBoxStack> GREEN_SHULKER_BOX_TYPE;

    public static ItemType<ItemGreenStainedGlassStack> GREEN_STAINED_GLASS_TYPE;

    public static ItemType<ItemGreenStainedGlassPaneStack> GREEN_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemGreenTerracottaStack> GREEN_TERRACOTTA_TYPE;

    public static ItemType<ItemGreenWoolStack> GREEN_WOOL_TYPE;

    public static ItemType<ItemGrindstoneStack> GRINDSTONE_TYPE;

    public static ItemType<ItemGuardianSpawnEggStack> GUARDIAN_SPAWN_EGG_TYPE;

    public static ItemType<ItemGunpowderStack> GUNPOWDER_TYPE;

    public static ItemType<ItemGusterBannerPatternStack> GUSTER_BANNER_PATTERN_TYPE;

    public static ItemType<ItemGusterPotterySherdStack> GUSTER_POTTERY_SHERD_TYPE;

    public static ItemType<ItemHangingRootsStack> HANGING_ROOTS_TYPE;

    public static ItemType<ItemHardBlackStainedGlassStack> HARD_BLACK_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardBlackStainedGlassPaneStack> HARD_BLACK_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardBlueStainedGlassStack> HARD_BLUE_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardBlueStainedGlassPaneStack> HARD_BLUE_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardBrownStainedGlassStack> HARD_BROWN_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardBrownStainedGlassPaneStack> HARD_BROWN_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardCyanStainedGlassStack> HARD_CYAN_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardCyanStainedGlassPaneStack> HARD_CYAN_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardGlassStack> HARD_GLASS_TYPE;

    public static ItemType<ItemHardGlassPaneStack> HARD_GLASS_PANE_TYPE;

    public static ItemType<ItemHardGrayStainedGlassStack> HARD_GRAY_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardGrayStainedGlassPaneStack> HARD_GRAY_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardGreenStainedGlassStack> HARD_GREEN_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardGreenStainedGlassPaneStack> HARD_GREEN_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardLightBlueStainedGlassStack> HARD_LIGHT_BLUE_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardLightBlueStainedGlassPaneStack> HARD_LIGHT_BLUE_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardLightGrayStainedGlassStack> HARD_LIGHT_GRAY_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardLightGrayStainedGlassPaneStack> HARD_LIGHT_GRAY_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardLimeStainedGlassStack> HARD_LIME_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardLimeStainedGlassPaneStack> HARD_LIME_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardMagentaStainedGlassStack> HARD_MAGENTA_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardMagentaStainedGlassPaneStack> HARD_MAGENTA_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardOrangeStainedGlassStack> HARD_ORANGE_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardOrangeStainedGlassPaneStack> HARD_ORANGE_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardPinkStainedGlassStack> HARD_PINK_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardPinkStainedGlassPaneStack> HARD_PINK_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardPurpleStainedGlassStack> HARD_PURPLE_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardPurpleStainedGlassPaneStack> HARD_PURPLE_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardRedStainedGlassStack> HARD_RED_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardRedStainedGlassPaneStack> HARD_RED_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardStainedGlassStack> HARD_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardStainedGlassPaneStack> HARD_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardWhiteStainedGlassStack> HARD_WHITE_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardWhiteStainedGlassPaneStack> HARD_WHITE_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardYellowStainedGlassStack> HARD_YELLOW_STAINED_GLASS_TYPE;

    public static ItemType<ItemHardYellowStainedGlassPaneStack> HARD_YELLOW_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemHardenedClayStack> HARDENED_CLAY_TYPE;

    public static ItemType<ItemHayBlockStack> HAY_BLOCK_TYPE;

    public static ItemType<ItemHeartOfTheSeaStack> HEART_OF_THE_SEA_TYPE;

    public static ItemType<ItemHeartPotterySherdStack> HEART_POTTERY_SHERD_TYPE;

    public static ItemType<ItemHeartbreakPotterySherdStack> HEARTBREAK_POTTERY_SHERD_TYPE;

    public static ItemType<ItemHeavyCoreStack> HEAVY_CORE_TYPE;

    public static ItemType<ItemHeavyWeightedPressurePlateStack> HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemHoglinSpawnEggStack> HOGLIN_SPAWN_EGG_TYPE;

    public static ItemType<ItemHoneyBlockStack> HONEY_BLOCK_TYPE;

    public static ItemType<ItemHoneyBottleStack> HONEY_BOTTLE_TYPE;

    public static ItemType<ItemHoneycombStack> HONEYCOMB_TYPE;

    public static ItemType<ItemHoneycombBlockStack> HONEYCOMB_BLOCK_TYPE;

    public static ItemType<ItemHopperStack> HOPPER_TYPE;

    public static ItemType<ItemHopperMinecartStack> HOPPER_MINECART_TYPE;

    public static ItemType<ItemHornCoralStack> HORN_CORAL_TYPE;

    public static ItemType<ItemHornCoralFanStack> HORN_CORAL_FAN_TYPE;

    public static ItemType<ItemHorseSpawnEggStack> HORSE_SPAWN_EGG_TYPE;

    public static ItemType<ItemHostArmorTrimSmithingTemplateStack> HOST_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemHowlPotterySherdStack> HOWL_POTTERY_SHERD_TYPE;

    public static ItemType<ItemHuskSpawnEggStack> HUSK_SPAWN_EGG_TYPE;

    public static ItemType<ItemIceStack> ICE_TYPE;

    public static ItemType<ItemIceBombStack> ICE_BOMB_TYPE;

    public static ItemType<ItemInfestedDeepslateStack> INFESTED_DEEPSLATE_TYPE;

    public static ItemType<ItemInfoUpdateStack> INFO_UPDATE_TYPE;

    public static ItemType<ItemInfoUpdate2Stack> INFO_UPDATE2_TYPE;

    public static ItemType<ItemInkSacStack> INK_SAC_TYPE;

    public static ItemType<ItemInvisibleBedrockStack> INVISIBLE_BEDROCK_TYPE;

    public static ItemType<ItemIronAxeStack> IRON_AXE_TYPE;

    public static ItemType<ItemIronBarsStack> IRON_BARS_TYPE;

    public static ItemType<ItemIronBlockStack> IRON_BLOCK_TYPE;

    public static ItemType<ItemIronBootsStack> IRON_BOOTS_TYPE;

    public static ItemType<ItemIronChestplateStack> IRON_CHESTPLATE_TYPE;

    public static ItemType<ItemIronDoorStack> IRON_DOOR_TYPE;

    public static ItemType<ItemIronGolemSpawnEggStack> IRON_GOLEM_SPAWN_EGG_TYPE;

    public static ItemType<ItemIronHelmetStack> IRON_HELMET_TYPE;

    public static ItemType<ItemIronHoeStack> IRON_HOE_TYPE;

    public static ItemType<ItemIronHorseArmorStack> IRON_HORSE_ARMOR_TYPE;

    public static ItemType<ItemIronIngotStack> IRON_INGOT_TYPE;

    public static ItemType<ItemIronLeggingsStack> IRON_LEGGINGS_TYPE;

    public static ItemType<ItemIronNuggetStack> IRON_NUGGET_TYPE;

    public static ItemType<ItemIronOreStack> IRON_ORE_TYPE;

    public static ItemType<ItemIronPickaxeStack> IRON_PICKAXE_TYPE;

    public static ItemType<ItemIronShovelStack> IRON_SHOVEL_TYPE;

    public static ItemType<ItemIronSwordStack> IRON_SWORD_TYPE;

    public static ItemType<ItemIronTrapdoorStack> IRON_TRAPDOOR_TYPE;

    public static ItemType<ItemItemAcaciaDoorStack> ITEM_ACACIA_DOOR_TYPE;

    public static ItemType<ItemItemBedStack> ITEM_BED_TYPE;

    public static ItemType<ItemItemBeetrootStack> ITEM_BEETROOT_TYPE;

    public static ItemType<ItemItemBirchDoorStack> ITEM_BIRCH_DOOR_TYPE;

    public static ItemType<ItemItemBrewingStandStack> ITEM_BREWING_STAND_TYPE;

    public static ItemType<ItemItemCakeStack> ITEM_CAKE_TYPE;

    public static ItemType<ItemItemCameraStack> ITEM_CAMERA_TYPE;

    public static ItemType<ItemItemCampfireStack> ITEM_CAMPFIRE_TYPE;

    public static ItemType<ItemItemCauldronStack> ITEM_CAULDRON_TYPE;

    public static ItemType<ItemItemChainStack> ITEM_CHAIN_TYPE;

    public static ItemType<ItemItemCrimsonDoorStack> ITEM_CRIMSON_DOOR_TYPE;

    public static ItemType<ItemItemDarkOakDoorStack> ITEM_DARK_OAK_DOOR_TYPE;

    public static ItemType<ItemItemFlowerPotStack> ITEM_FLOWER_POT_TYPE;

    public static ItemType<ItemItemFrameStack> ITEM_FRAME_TYPE;

    public static ItemType<ItemItemGlowFrameStack> ITEM_GLOW_FRAME_TYPE;

    public static ItemType<ItemItemHopperStack> ITEM_HOPPER_TYPE;

    public static ItemType<ItemItemIronDoorStack> ITEM_IRON_DOOR_TYPE;

    public static ItemType<ItemItemJungleDoorStack> ITEM_JUNGLE_DOOR_TYPE;

    public static ItemType<ItemItemKelpStack> ITEM_KELP_TYPE;

    public static ItemType<ItemItemMangroveDoorStack> ITEM_MANGROVE_DOOR_TYPE;

    public static ItemType<ItemItemNetherSproutsStack> ITEM_NETHER_SPROUTS_TYPE;

    public static ItemType<ItemItemNetherWartStack> ITEM_NETHER_WART_TYPE;

    public static ItemType<ItemItemReedsStack> ITEM_REEDS_TYPE;

    public static ItemType<ItemItemSkullStack> ITEM_SKULL_TYPE;

    public static ItemType<ItemItemSoulCampfireStack> ITEM_SOUL_CAMPFIRE_TYPE;

    public static ItemType<ItemItemSpruceDoorStack> ITEM_SPRUCE_DOOR_TYPE;

    public static ItemType<ItemItemWarpedDoorStack> ITEM_WARPED_DOOR_TYPE;

    public static ItemType<ItemItemWheatStack> ITEM_WHEAT_TYPE;

    public static ItemType<ItemItemWoodenDoorStack> ITEM_WOODEN_DOOR_TYPE;

    public static ItemType<ItemJigsawStack> JIGSAW_TYPE;

    public static ItemType<ItemJukeboxStack> JUKEBOX_TYPE;

    public static ItemType<ItemJungleBoatStack> JUNGLE_BOAT_TYPE;

    public static ItemType<ItemJungleButtonStack> JUNGLE_BUTTON_TYPE;

    public static ItemType<ItemJungleChestBoatStack> JUNGLE_CHEST_BOAT_TYPE;

    public static ItemType<ItemJungleDoorStack> JUNGLE_DOOR_TYPE;

    public static ItemType<ItemJungleDoubleSlabStack> JUNGLE_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemJungleFenceStack> JUNGLE_FENCE_TYPE;

    public static ItemType<ItemJungleFenceGateStack> JUNGLE_FENCE_GATE_TYPE;

    public static ItemType<ItemJungleHangingSignStack> JUNGLE_HANGING_SIGN_TYPE;

    public static ItemType<ItemJungleLeavesStack> JUNGLE_LEAVES_TYPE;

    public static ItemType<ItemJungleLogStack> JUNGLE_LOG_TYPE;

    public static ItemType<ItemJunglePlanksStack> JUNGLE_PLANKS_TYPE;

    public static ItemType<ItemJunglePressurePlateStack> JUNGLE_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemJungleSaplingStack> JUNGLE_SAPLING_TYPE;

    public static ItemType<ItemJungleSignStack> JUNGLE_SIGN_TYPE;

    public static ItemType<ItemJungleSlabStack> JUNGLE_SLAB_TYPE;

    public static ItemType<ItemJungleStairsStack> JUNGLE_STAIRS_TYPE;

    public static ItemType<ItemJungleStandingSignStack> JUNGLE_STANDING_SIGN_TYPE;

    public static ItemType<ItemJungleTrapdoorStack> JUNGLE_TRAPDOOR_TYPE;

    public static ItemType<ItemJungleWallSignStack> JUNGLE_WALL_SIGN_TYPE;

    public static ItemType<ItemJungleWoodStack> JUNGLE_WOOD_TYPE;

    public static ItemType<ItemKelpStack> KELP_TYPE;

    public static ItemType<ItemLadderStack> LADDER_TYPE;

    public static ItemType<ItemLanternStack> LANTERN_TYPE;

    public static ItemType<ItemLapisBlockStack> LAPIS_BLOCK_TYPE;

    public static ItemType<ItemLapisLazuliStack> LAPIS_LAZULI_TYPE;

    public static ItemType<ItemLapisOreStack> LAPIS_ORE_TYPE;

    public static ItemType<ItemLargeAmethystBudStack> LARGE_AMETHYST_BUD_TYPE;

    public static ItemType<ItemLavaStack> LAVA_TYPE;

    public static ItemType<ItemLavaBucketStack> LAVA_BUCKET_TYPE;

    public static ItemType<ItemLeadStack> LEAD_TYPE;

    public static ItemType<ItemLeatherStack> LEATHER_TYPE;

    public static ItemType<ItemLeatherBootsStack> LEATHER_BOOTS_TYPE;

    public static ItemType<ItemLeatherChestplateStack> LEATHER_CHESTPLATE_TYPE;

    public static ItemType<ItemLeatherHelmetStack> LEATHER_HELMET_TYPE;

    public static ItemType<ItemLeatherHorseArmorStack> LEATHER_HORSE_ARMOR_TYPE;

    public static ItemType<ItemLeatherLeggingsStack> LEATHER_LEGGINGS_TYPE;

    public static ItemType<ItemLeavesStack> LEAVES_TYPE;

    public static ItemType<ItemLeaves2Stack> LEAVES2_TYPE;

    public static ItemType<ItemLecternStack> LECTERN_TYPE;

    public static ItemType<ItemLeverStack> LEVER_TYPE;

    public static ItemType<ItemLightBlockStack> LIGHT_BLOCK_TYPE;

    public static ItemType<ItemLightBlueCandleStack> LIGHT_BLUE_CANDLE_TYPE;

    public static ItemType<ItemLightBlueCandleCakeStack> LIGHT_BLUE_CANDLE_CAKE_TYPE;

    public static ItemType<ItemLightBlueCarpetStack> LIGHT_BLUE_CARPET_TYPE;

    public static ItemType<ItemLightBlueConcreteStack> LIGHT_BLUE_CONCRETE_TYPE;

    public static ItemType<ItemLightBlueConcretePowderStack> LIGHT_BLUE_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemLightBlueDyeStack> LIGHT_BLUE_DYE_TYPE;

    public static ItemType<ItemLightBlueGlazedTerracottaStack> LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemLightBlueShulkerBoxStack> LIGHT_BLUE_SHULKER_BOX_TYPE;

    public static ItemType<ItemLightBlueStainedGlassStack> LIGHT_BLUE_STAINED_GLASS_TYPE;

    public static ItemType<ItemLightBlueStainedGlassPaneStack> LIGHT_BLUE_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemLightBlueTerracottaStack> LIGHT_BLUE_TERRACOTTA_TYPE;

    public static ItemType<ItemLightBlueWoolStack> LIGHT_BLUE_WOOL_TYPE;

    public static ItemType<ItemLightGrayCandleStack> LIGHT_GRAY_CANDLE_TYPE;

    public static ItemType<ItemLightGrayCandleCakeStack> LIGHT_GRAY_CANDLE_CAKE_TYPE;

    public static ItemType<ItemLightGrayCarpetStack> LIGHT_GRAY_CARPET_TYPE;

    public static ItemType<ItemLightGrayConcreteStack> LIGHT_GRAY_CONCRETE_TYPE;

    public static ItemType<ItemLightGrayConcretePowderStack> LIGHT_GRAY_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemLightGrayDyeStack> LIGHT_GRAY_DYE_TYPE;

    public static ItemType<ItemLightGrayShulkerBoxStack> LIGHT_GRAY_SHULKER_BOX_TYPE;

    public static ItemType<ItemLightGrayStainedGlassStack> LIGHT_GRAY_STAINED_GLASS_TYPE;

    public static ItemType<ItemLightGrayStainedGlassPaneStack> LIGHT_GRAY_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemLightGrayTerracottaStack> LIGHT_GRAY_TERRACOTTA_TYPE;

    public static ItemType<ItemLightGrayWoolStack> LIGHT_GRAY_WOOL_TYPE;

    public static ItemType<ItemLightWeightedPressurePlateStack> LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemLightningRodStack> LIGHTNING_ROD_TYPE;

    public static ItemType<ItemLilyOfTheValleyStack> LILY_OF_THE_VALLEY_TYPE;

    public static ItemType<ItemLimeCandleStack> LIME_CANDLE_TYPE;

    public static ItemType<ItemLimeCandleCakeStack> LIME_CANDLE_CAKE_TYPE;

    public static ItemType<ItemLimeCarpetStack> LIME_CARPET_TYPE;

    public static ItemType<ItemLimeConcreteStack> LIME_CONCRETE_TYPE;

    public static ItemType<ItemLimeConcretePowderStack> LIME_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemLimeDyeStack> LIME_DYE_TYPE;

    public static ItemType<ItemLimeGlazedTerracottaStack> LIME_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemLimeShulkerBoxStack> LIME_SHULKER_BOX_TYPE;

    public static ItemType<ItemLimeStainedGlassStack> LIME_STAINED_GLASS_TYPE;

    public static ItemType<ItemLimeStainedGlassPaneStack> LIME_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemLimeTerracottaStack> LIME_TERRACOTTA_TYPE;

    public static ItemType<ItemLimeWoolStack> LIME_WOOL_TYPE;

    public static ItemType<ItemLingeringPotionStack> LINGERING_POTION_TYPE;

    public static ItemType<ItemLitBlastFurnaceStack> LIT_BLAST_FURNACE_TYPE;

    public static ItemType<ItemLitDeepslateRedstoneOreStack> LIT_DEEPSLATE_REDSTONE_ORE_TYPE;

    public static ItemType<ItemLitFurnaceStack> LIT_FURNACE_TYPE;

    public static ItemType<ItemLitPumpkinStack> LIT_PUMPKIN_TYPE;

    public static ItemType<ItemLitRedstoneLampStack> LIT_REDSTONE_LAMP_TYPE;

    public static ItemType<ItemLitRedstoneOreStack> LIT_REDSTONE_ORE_TYPE;

    public static ItemType<ItemLitSmokerStack> LIT_SMOKER_TYPE;

    public static ItemType<ItemLlamaSpawnEggStack> LLAMA_SPAWN_EGG_TYPE;

    public static ItemType<ItemLodestoneStack> LODESTONE_TYPE;

    public static ItemType<ItemLodestoneCompassStack> LODESTONE_COMPASS_TYPE;

    public static ItemType<ItemLogStack> LOG_TYPE;

    public static ItemType<ItemLog2Stack> LOG2_TYPE;

    public static ItemType<ItemLoomStack> LOOM_TYPE;

    public static ItemType<ItemMaceStack> MACE_TYPE;

    public static ItemType<ItemMagentaCandleStack> MAGENTA_CANDLE_TYPE;

    public static ItemType<ItemMagentaCandleCakeStack> MAGENTA_CANDLE_CAKE_TYPE;

    public static ItemType<ItemMagentaCarpetStack> MAGENTA_CARPET_TYPE;

    public static ItemType<ItemMagentaConcreteStack> MAGENTA_CONCRETE_TYPE;

    public static ItemType<ItemMagentaConcretePowderStack> MAGENTA_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemMagentaDyeStack> MAGENTA_DYE_TYPE;

    public static ItemType<ItemMagentaGlazedTerracottaStack> MAGENTA_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemMagentaShulkerBoxStack> MAGENTA_SHULKER_BOX_TYPE;

    public static ItemType<ItemMagentaStainedGlassStack> MAGENTA_STAINED_GLASS_TYPE;

    public static ItemType<ItemMagentaStainedGlassPaneStack> MAGENTA_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemMagentaTerracottaStack> MAGENTA_TERRACOTTA_TYPE;

    public static ItemType<ItemMagentaWoolStack> MAGENTA_WOOL_TYPE;

    public static ItemType<ItemMagmaStack> MAGMA_TYPE;

    public static ItemType<ItemMagmaCreamStack> MAGMA_CREAM_TYPE;

    public static ItemType<ItemMagmaCubeSpawnEggStack> MAGMA_CUBE_SPAWN_EGG_TYPE;

    public static ItemType<ItemMangroveBoatStack> MANGROVE_BOAT_TYPE;

    public static ItemType<ItemMangroveButtonStack> MANGROVE_BUTTON_TYPE;

    public static ItemType<ItemMangroveChestBoatStack> MANGROVE_CHEST_BOAT_TYPE;

    public static ItemType<ItemMangroveDoorStack> MANGROVE_DOOR_TYPE;

    public static ItemType<ItemMangroveDoubleSlabStack> MANGROVE_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemMangroveFenceStack> MANGROVE_FENCE_TYPE;

    public static ItemType<ItemMangroveFenceGateStack> MANGROVE_FENCE_GATE_TYPE;

    public static ItemType<ItemMangroveHangingSignStack> MANGROVE_HANGING_SIGN_TYPE;

    public static ItemType<ItemMangroveLeavesStack> MANGROVE_LEAVES_TYPE;

    public static ItemType<ItemMangroveLogStack> MANGROVE_LOG_TYPE;

    public static ItemType<ItemMangrovePlanksStack> MANGROVE_PLANKS_TYPE;

    public static ItemType<ItemMangrovePressurePlateStack> MANGROVE_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemMangrovePropaguleStack> MANGROVE_PROPAGULE_TYPE;

    public static ItemType<ItemMangroveRootsStack> MANGROVE_ROOTS_TYPE;

    public static ItemType<ItemMangroveSignStack> MANGROVE_SIGN_TYPE;

    public static ItemType<ItemMangroveSlabStack> MANGROVE_SLAB_TYPE;

    public static ItemType<ItemMangroveStairsStack> MANGROVE_STAIRS_TYPE;

    public static ItemType<ItemMangroveStandingSignStack> MANGROVE_STANDING_SIGN_TYPE;

    public static ItemType<ItemMangroveTrapdoorStack> MANGROVE_TRAPDOOR_TYPE;

    public static ItemType<ItemMangroveWallSignStack> MANGROVE_WALL_SIGN_TYPE;

    public static ItemType<ItemMangroveWoodStack> MANGROVE_WOOD_TYPE;

    public static ItemType<ItemMedicineStack> MEDICINE_TYPE;

    public static ItemType<ItemMediumAmethystBudStack> MEDIUM_AMETHYST_BUD_TYPE;

    public static ItemType<ItemMelonBlockStack> MELON_BLOCK_TYPE;

    public static ItemType<ItemMelonSeedsStack> MELON_SEEDS_TYPE;

    public static ItemType<ItemMelonSliceStack> MELON_SLICE_TYPE;

    public static ItemType<ItemMelonStemStack> MELON_STEM_TYPE;

    public static ItemType<ItemMilkBucketStack> MILK_BUCKET_TYPE;

    public static ItemType<ItemMinecartStack> MINECART_TYPE;

    public static ItemType<ItemMinerPotterySherdStack> MINER_POTTERY_SHERD_TYPE;

    public static ItemType<ItemMobSpawnerStack> MOB_SPAWNER_TYPE;

    public static ItemType<ItemMojangBannerPatternStack> MOJANG_BANNER_PATTERN_TYPE;

    public static ItemType<ItemMonsterEggStack> MONSTER_EGG_TYPE;

    public static ItemType<ItemMooshroomSpawnEggStack> MOOSHROOM_SPAWN_EGG_TYPE;

    public static ItemType<ItemMossBlockStack> MOSS_BLOCK_TYPE;

    public static ItemType<ItemMossCarpetStack> MOSS_CARPET_TYPE;

    public static ItemType<ItemMossyCobblestoneStack> MOSSY_COBBLESTONE_TYPE;

    public static ItemType<ItemMossyCobblestoneStairsStack> MOSSY_COBBLESTONE_STAIRS_TYPE;

    public static ItemType<ItemMossyStoneBrickStairsStack> MOSSY_STONE_BRICK_STAIRS_TYPE;

    public static ItemType<ItemMournerPotterySherdStack> MOURNER_POTTERY_SHERD_TYPE;

    public static ItemType<ItemMovingBlockStack> MOVING_BLOCK_TYPE;

    public static ItemType<ItemMudStack> MUD_TYPE;

    public static ItemType<ItemMudBrickDoubleSlabStack> MUD_BRICK_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemMudBrickSlabStack> MUD_BRICK_SLAB_TYPE;

    public static ItemType<ItemMudBrickStairsStack> MUD_BRICK_STAIRS_TYPE;

    public static ItemType<ItemMudBrickWallStack> MUD_BRICK_WALL_TYPE;

    public static ItemType<ItemMudBricksStack> MUD_BRICKS_TYPE;

    public static ItemType<ItemMuddyMangroveRootsStack> MUDDY_MANGROVE_ROOTS_TYPE;

    public static ItemType<ItemMuleSpawnEggStack> MULE_SPAWN_EGG_TYPE;

    public static ItemType<ItemMushroomStewStack> MUSHROOM_STEW_TYPE;

    public static ItemType<ItemMusicDisc11Stack> MUSIC_DISC_11_TYPE;

    public static ItemType<ItemMusicDisc13Stack> MUSIC_DISC_13_TYPE;

    public static ItemType<ItemMusicDisc5Stack> MUSIC_DISC_5_TYPE;

    public static ItemType<ItemMusicDiscBlocksStack> MUSIC_DISC_BLOCKS_TYPE;

    public static ItemType<ItemMusicDiscCatStack> MUSIC_DISC_CAT_TYPE;

    public static ItemType<ItemMusicDiscChirpStack> MUSIC_DISC_CHIRP_TYPE;

    public static ItemType<ItemMusicDiscFarStack> MUSIC_DISC_FAR_TYPE;

    public static ItemType<ItemMusicDiscMallStack> MUSIC_DISC_MALL_TYPE;

    public static ItemType<ItemMusicDiscMellohiStack> MUSIC_DISC_MELLOHI_TYPE;

    public static ItemType<ItemMusicDiscOthersideStack> MUSIC_DISC_OTHERSIDE_TYPE;

    public static ItemType<ItemMusicDiscPigstepStack> MUSIC_DISC_PIGSTEP_TYPE;

    public static ItemType<ItemMusicDiscRelicStack> MUSIC_DISC_RELIC_TYPE;

    public static ItemType<ItemMusicDiscStalStack> MUSIC_DISC_STAL_TYPE;

    public static ItemType<ItemMusicDiscStradStack> MUSIC_DISC_STRAD_TYPE;

    public static ItemType<ItemMusicDiscWaitStack> MUSIC_DISC_WAIT_TYPE;

    public static ItemType<ItemMusicDiscWardStack> MUSIC_DISC_WARD_TYPE;

    public static ItemType<ItemMuttonStack> MUTTON_TYPE;

    public static ItemType<ItemMyceliumStack> MYCELIUM_TYPE;

    public static ItemType<ItemNameTagStack> NAME_TAG_TYPE;

    public static ItemType<ItemNautilusShellStack> NAUTILUS_SHELL_TYPE;

    public static ItemType<ItemNetherBrickStack> NETHER_BRICK_TYPE;

    public static ItemType<ItemNetherBrickFenceStack> NETHER_BRICK_FENCE_TYPE;

    public static ItemType<ItemNetherBrickStairsStack> NETHER_BRICK_STAIRS_TYPE;

    public static ItemType<ItemNetherGoldOreStack> NETHER_GOLD_ORE_TYPE;

    public static ItemType<ItemNetherSproutsStack> NETHER_SPROUTS_TYPE;

    public static ItemType<ItemNetherStarStack> NETHER_STAR_TYPE;

    public static ItemType<ItemNetherWartStack> NETHER_WART_TYPE;

    public static ItemType<ItemNetherWartBlockStack> NETHER_WART_BLOCK_TYPE;

    public static ItemType<ItemNetherbrick0Stack> NETHERBRICK_TYPE;

    public static ItemType<ItemNetheriteAxeStack> NETHERITE_AXE_TYPE;

    public static ItemType<ItemNetheriteBlockStack> NETHERITE_BLOCK_TYPE;

    public static ItemType<ItemNetheriteBootsStack> NETHERITE_BOOTS_TYPE;

    public static ItemType<ItemNetheriteChestplateStack> NETHERITE_CHESTPLATE_TYPE;

    public static ItemType<ItemNetheriteHelmetStack> NETHERITE_HELMET_TYPE;

    public static ItemType<ItemNetheriteHoeStack> NETHERITE_HOE_TYPE;

    public static ItemType<ItemNetheriteIngotStack> NETHERITE_INGOT_TYPE;

    public static ItemType<ItemNetheriteLeggingsStack> NETHERITE_LEGGINGS_TYPE;

    public static ItemType<ItemNetheritePickaxeStack> NETHERITE_PICKAXE_TYPE;

    public static ItemType<ItemNetheriteScrapStack> NETHERITE_SCRAP_TYPE;

    public static ItemType<ItemNetheriteShovelStack> NETHERITE_SHOVEL_TYPE;

    public static ItemType<ItemNetheriteSwordStack> NETHERITE_SWORD_TYPE;

    public static ItemType<ItemNetheriteUpgradeSmithingTemplateStack> NETHERITE_UPGRADE_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemNetherrackStack> NETHERRACK_TYPE;

    public static ItemType<ItemNetherreactorStack> NETHERREACTOR_TYPE;

    public static ItemType<ItemNormalStoneStairsStack> NORMAL_STONE_STAIRS_TYPE;

    public static ItemType<ItemNoteblockStack> NOTEBLOCK_TYPE;

    public static ItemType<ItemNpcSpawnEggStack> NPC_SPAWN_EGG_TYPE;

    public static ItemType<ItemOakBoatStack> OAK_BOAT_TYPE;

    public static ItemType<ItemOakChestBoatStack> OAK_CHEST_BOAT_TYPE;

    public static ItemType<ItemOakDoubleSlabStack> OAK_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemOakFenceStack> OAK_FENCE_TYPE;

    public static ItemType<ItemOakHangingSignStack> OAK_HANGING_SIGN_TYPE;

    public static ItemType<ItemOakLeavesStack> OAK_LEAVES_TYPE;

    public static ItemType<ItemOakLogStack> OAK_LOG_TYPE;

    public static ItemType<ItemOakPlanksStack> OAK_PLANKS_TYPE;

    public static ItemType<ItemOakSaplingStack> OAK_SAPLING_TYPE;

    public static ItemType<ItemOakSignStack> OAK_SIGN_TYPE;

    public static ItemType<ItemOakSlabStack> OAK_SLAB_TYPE;

    public static ItemType<ItemOakStairsStack> OAK_STAIRS_TYPE;

    public static ItemType<ItemOakWoodStack> OAK_WOOD_TYPE;

    public static ItemType<ItemObserverStack> OBSERVER_TYPE;

    public static ItemType<ItemObsidianStack> OBSIDIAN_TYPE;

    public static ItemType<ItemOcelotSpawnEggStack> OCELOT_SPAWN_EGG_TYPE;

    public static ItemType<ItemOchreFroglightStack> OCHRE_FROGLIGHT_TYPE;

    public static ItemType<ItemOrangeCandleStack> ORANGE_CANDLE_TYPE;

    public static ItemType<ItemOrangeCandleCakeStack> ORANGE_CANDLE_CAKE_TYPE;

    public static ItemType<ItemOrangeCarpetStack> ORANGE_CARPET_TYPE;

    public static ItemType<ItemOrangeConcreteStack> ORANGE_CONCRETE_TYPE;

    public static ItemType<ItemOrangeConcretePowderStack> ORANGE_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemOrangeDyeStack> ORANGE_DYE_TYPE;

    public static ItemType<ItemOrangeGlazedTerracottaStack> ORANGE_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemOrangeShulkerBoxStack> ORANGE_SHULKER_BOX_TYPE;

    public static ItemType<ItemOrangeStainedGlassStack> ORANGE_STAINED_GLASS_TYPE;

    public static ItemType<ItemOrangeStainedGlassPaneStack> ORANGE_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemOrangeTerracottaStack> ORANGE_TERRACOTTA_TYPE;

    public static ItemType<ItemOrangeTulipStack> ORANGE_TULIP_TYPE;

    public static ItemType<ItemOrangeWoolStack> ORANGE_WOOL_TYPE;

    public static ItemType<ItemOxeyeDaisyStack> OXEYE_DAISY_TYPE;

    public static ItemType<ItemOxidizedChiseledCopperStack> OXIDIZED_CHISELED_COPPER_TYPE;

    public static ItemType<ItemOxidizedCopperStack> OXIDIZED_COPPER_TYPE;

    public static ItemType<ItemOxidizedCopperBulbStack> OXIDIZED_COPPER_BULB_TYPE;

    public static ItemType<ItemOxidizedCopperDoorStack> OXIDIZED_COPPER_DOOR_TYPE;

    public static ItemType<ItemOxidizedCopperGrateStack> OXIDIZED_COPPER_GRATE_TYPE;

    public static ItemType<ItemOxidizedCopperTrapdoorStack> OXIDIZED_COPPER_TRAPDOOR_TYPE;

    public static ItemType<ItemOxidizedCutCopperStack> OXIDIZED_CUT_COPPER_TYPE;

    public static ItemType<ItemOxidizedCutCopperSlabStack> OXIDIZED_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemOxidizedCutCopperStairsStack> OXIDIZED_CUT_COPPER_STAIRS_TYPE;

    public static ItemType<ItemOxidizedDoubleCutCopperSlabStack> OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemPackedIceStack> PACKED_ICE_TYPE;

    public static ItemType<ItemPackedMudStack> PACKED_MUD_TYPE;

    public static ItemType<ItemPaintingStack> PAINTING_TYPE;

    public static ItemType<ItemPandaSpawnEggStack> PANDA_SPAWN_EGG_TYPE;

    public static ItemType<ItemPaperStack> PAPER_TYPE;

    public static ItemType<ItemParrotSpawnEggStack> PARROT_SPAWN_EGG_TYPE;

    public static ItemType<ItemPearlescentFroglightStack> PEARLESCENT_FROGLIGHT_TYPE;

    public static ItemType<ItemPhantomMembraneStack> PHANTOM_MEMBRANE_TYPE;

    public static ItemType<ItemPhantomSpawnEggStack> PHANTOM_SPAWN_EGG_TYPE;

    public static ItemType<ItemPigSpawnEggStack> PIG_SPAWN_EGG_TYPE;

    public static ItemType<ItemPiglinBannerPatternStack> PIGLIN_BANNER_PATTERN_TYPE;

    public static ItemType<ItemPiglinBruteSpawnEggStack> PIGLIN_BRUTE_SPAWN_EGG_TYPE;

    public static ItemType<ItemPiglinSpawnEggStack> PIGLIN_SPAWN_EGG_TYPE;

    public static ItemType<ItemPillagerSpawnEggStack> PILLAGER_SPAWN_EGG_TYPE;

    public static ItemType<ItemPinkCandleStack> PINK_CANDLE_TYPE;

    public static ItemType<ItemPinkCandleCakeStack> PINK_CANDLE_CAKE_TYPE;

    public static ItemType<ItemPinkCarpetStack> PINK_CARPET_TYPE;

    public static ItemType<ItemPinkConcreteStack> PINK_CONCRETE_TYPE;

    public static ItemType<ItemPinkConcretePowderStack> PINK_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemPinkDyeStack> PINK_DYE_TYPE;

    public static ItemType<ItemPinkGlazedTerracottaStack> PINK_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemPinkPetalsStack> PINK_PETALS_TYPE;

    public static ItemType<ItemPinkShulkerBoxStack> PINK_SHULKER_BOX_TYPE;

    public static ItemType<ItemPinkStainedGlassStack> PINK_STAINED_GLASS_TYPE;

    public static ItemType<ItemPinkStainedGlassPaneStack> PINK_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemPinkTerracottaStack> PINK_TERRACOTTA_TYPE;

    public static ItemType<ItemPinkTulipStack> PINK_TULIP_TYPE;

    public static ItemType<ItemPinkWoolStack> PINK_WOOL_TYPE;

    public static ItemType<ItemPistonStack> PISTON_TYPE;

    public static ItemType<ItemPistonArmCollisionStack> PISTON_ARM_COLLISION_TYPE;

    public static ItemType<ItemPitcherCropStack> PITCHER_CROP_TYPE;

    public static ItemType<ItemPitcherPlantStack> PITCHER_PLANT_TYPE;

    public static ItemType<ItemPitcherPodStack> PITCHER_POD_TYPE;

    public static ItemType<ItemPlanksStack> PLANKS_TYPE;

    public static ItemType<ItemPlentyPotterySherdStack> PLENTY_POTTERY_SHERD_TYPE;

    public static ItemType<ItemPodzolStack> PODZOL_TYPE;

    public static ItemType<ItemPointedDripstoneStack> POINTED_DRIPSTONE_TYPE;

    public static ItemType<ItemPoisonousPotatoStack> POISONOUS_POTATO_TYPE;

    public static ItemType<ItemPolarBearSpawnEggStack> POLAR_BEAR_SPAWN_EGG_TYPE;

    public static ItemType<ItemPolishedAndesiteStack> POLISHED_ANDESITE_TYPE;

    public static ItemType<ItemPolishedAndesiteStairsStack> POLISHED_ANDESITE_STAIRS_TYPE;

    public static ItemType<ItemPolishedBasaltStack> POLISHED_BASALT_TYPE;

    public static ItemType<ItemPolishedBlackstoneStack> POLISHED_BLACKSTONE_TYPE;

    public static ItemType<ItemPolishedBlackstoneBrickDoubleSlabStack> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemPolishedBlackstoneBrickSlabStack> POLISHED_BLACKSTONE_BRICK_SLAB_TYPE;

    public static ItemType<ItemPolishedBlackstoneBrickStairsStack> POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE;

    public static ItemType<ItemPolishedBlackstoneBrickWallStack> POLISHED_BLACKSTONE_BRICK_WALL_TYPE;

    public static ItemType<ItemPolishedBlackstoneBricksStack> POLISHED_BLACKSTONE_BRICKS_TYPE;

    public static ItemType<ItemPolishedBlackstoneButtonStack> POLISHED_BLACKSTONE_BUTTON_TYPE;

    public static ItemType<ItemPolishedBlackstoneDoubleSlabStack> POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemPolishedBlackstonePressurePlateStack> POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemPolishedBlackstoneSlabStack> POLISHED_BLACKSTONE_SLAB_TYPE;

    public static ItemType<ItemPolishedBlackstoneStairsStack> POLISHED_BLACKSTONE_STAIRS_TYPE;

    public static ItemType<ItemPolishedBlackstoneWallStack> POLISHED_BLACKSTONE_WALL_TYPE;

    public static ItemType<ItemPolishedDeepslateStack> POLISHED_DEEPSLATE_TYPE;

    public static ItemType<ItemPolishedDeepslateDoubleSlabStack> POLISHED_DEEPSLATE_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemPolishedDeepslateSlabStack> POLISHED_DEEPSLATE_SLAB_TYPE;

    public static ItemType<ItemPolishedDeepslateStairsStack> POLISHED_DEEPSLATE_STAIRS_TYPE;

    public static ItemType<ItemPolishedDeepslateWallStack> POLISHED_DEEPSLATE_WALL_TYPE;

    public static ItemType<ItemPolishedDioriteStack> POLISHED_DIORITE_TYPE;

    public static ItemType<ItemPolishedDioriteStairsStack> POLISHED_DIORITE_STAIRS_TYPE;

    public static ItemType<ItemPolishedGraniteStack> POLISHED_GRANITE_TYPE;

    public static ItemType<ItemPolishedGraniteStairsStack> POLISHED_GRANITE_STAIRS_TYPE;

    public static ItemType<ItemPolishedTuffStack> POLISHED_TUFF_TYPE;

    public static ItemType<ItemPolishedTuffDoubleSlabStack> POLISHED_TUFF_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemPolishedTuffSlabStack> POLISHED_TUFF_SLAB_TYPE;

    public static ItemType<ItemPolishedTuffStairsStack> POLISHED_TUFF_STAIRS_TYPE;

    public static ItemType<ItemPolishedTuffWallStack> POLISHED_TUFF_WALL_TYPE;

    public static ItemType<ItemPoppedChorusFruitStack> POPPED_CHORUS_FRUIT_TYPE;

    public static ItemType<ItemPoppyStack> POPPY_TYPE;

    public static ItemType<ItemPorkchopStack> PORKCHOP_TYPE;

    public static ItemType<ItemPortalStack> PORTAL_TYPE;

    public static ItemType<ItemPotatoStack> POTATO_TYPE;

    public static ItemType<ItemPotatoesStack> POTATOES_TYPE;

    public static ItemType<ItemPotionStack> POTION_TYPE;

    public static ItemType<ItemPowderSnowStack> POWDER_SNOW_TYPE;

    public static ItemType<ItemPowderSnowBucketStack> POWDER_SNOW_BUCKET_TYPE;

    public static ItemType<ItemPoweredComparatorStack> POWERED_COMPARATOR_TYPE;

    public static ItemType<ItemPoweredRepeaterStack> POWERED_REPEATER_TYPE;

    public static ItemType<ItemPrismarineStack> PRISMARINE_TYPE;

    public static ItemType<ItemPrismarineBricksStairsStack> PRISMARINE_BRICKS_STAIRS_TYPE;

    public static ItemType<ItemPrismarineCrystalsStack> PRISMARINE_CRYSTALS_TYPE;

    public static ItemType<ItemPrismarineShardStack> PRISMARINE_SHARD_TYPE;

    public static ItemType<ItemPrismarineStairsStack> PRISMARINE_STAIRS_TYPE;

    public static ItemType<ItemPrizePotterySherdStack> PRIZE_POTTERY_SHERD_TYPE;

    public static ItemType<ItemPufferfishStack> PUFFERFISH_TYPE;

    public static ItemType<ItemPufferfishBucketStack> PUFFERFISH_BUCKET_TYPE;

    public static ItemType<ItemPufferfishSpawnEggStack> PUFFERFISH_SPAWN_EGG_TYPE;

    public static ItemType<ItemPumpkinStack> PUMPKIN_TYPE;

    public static ItemType<ItemPumpkinPieStack> PUMPKIN_PIE_TYPE;

    public static ItemType<ItemPumpkinSeedsStack> PUMPKIN_SEEDS_TYPE;

    public static ItemType<ItemPumpkinStemStack> PUMPKIN_STEM_TYPE;

    public static ItemType<ItemPurpleCandleStack> PURPLE_CANDLE_TYPE;

    public static ItemType<ItemPurpleCandleCakeStack> PURPLE_CANDLE_CAKE_TYPE;

    public static ItemType<ItemPurpleCarpetStack> PURPLE_CARPET_TYPE;

    public static ItemType<ItemPurpleConcreteStack> PURPLE_CONCRETE_TYPE;

    public static ItemType<ItemPurpleConcretePowderStack> PURPLE_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemPurpleDyeStack> PURPLE_DYE_TYPE;

    public static ItemType<ItemPurpleGlazedTerracottaStack> PURPLE_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemPurpleShulkerBoxStack> PURPLE_SHULKER_BOX_TYPE;

    public static ItemType<ItemPurpleStainedGlassStack> PURPLE_STAINED_GLASS_TYPE;

    public static ItemType<ItemPurpleStainedGlassPaneStack> PURPLE_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemPurpleTerracottaStack> PURPLE_TERRACOTTA_TYPE;

    public static ItemType<ItemPurpleWoolStack> PURPLE_WOOL_TYPE;

    public static ItemType<ItemPurpurBlockStack> PURPUR_BLOCK_TYPE;

    public static ItemType<ItemPurpurStairsStack> PURPUR_STAIRS_TYPE;

    public static ItemType<ItemQuartzStack> QUARTZ_TYPE;

    public static ItemType<ItemQuartzBlockStack> QUARTZ_BLOCK_TYPE;

    public static ItemType<ItemQuartzBricksStack> QUARTZ_BRICKS_TYPE;

    public static ItemType<ItemQuartzOreStack> QUARTZ_ORE_TYPE;

    public static ItemType<ItemQuartzStairsStack> QUARTZ_STAIRS_TYPE;

    public static ItemType<ItemRabbitStack> RABBIT_TYPE;

    public static ItemType<ItemRabbitFootStack> RABBIT_FOOT_TYPE;

    public static ItemType<ItemRabbitHideStack> RABBIT_HIDE_TYPE;

    public static ItemType<ItemRabbitSpawnEggStack> RABBIT_SPAWN_EGG_TYPE;

    public static ItemType<ItemRabbitStewStack> RABBIT_STEW_TYPE;

    public static ItemType<ItemRailStack> RAIL_TYPE;

    public static ItemType<ItemRaiserArmorTrimSmithingTemplateStack> RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemRapidFertilizerStack> RAPID_FERTILIZER_TYPE;

    public static ItemType<ItemRavagerSpawnEggStack> RAVAGER_SPAWN_EGG_TYPE;

    public static ItemType<ItemRawCopperStack> RAW_COPPER_TYPE;

    public static ItemType<ItemRawCopperBlockStack> RAW_COPPER_BLOCK_TYPE;

    public static ItemType<ItemRawGoldStack> RAW_GOLD_TYPE;

    public static ItemType<ItemRawGoldBlockStack> RAW_GOLD_BLOCK_TYPE;

    public static ItemType<ItemRawIronStack> RAW_IRON_TYPE;

    public static ItemType<ItemRawIronBlockStack> RAW_IRON_BLOCK_TYPE;

    public static ItemType<ItemRecoveryCompassStack> RECOVERY_COMPASS_TYPE;

    public static ItemType<ItemRedCandleStack> RED_CANDLE_TYPE;

    public static ItemType<ItemRedCandleCakeStack> RED_CANDLE_CAKE_TYPE;

    public static ItemType<ItemRedCarpetStack> RED_CARPET_TYPE;

    public static ItemType<ItemRedConcreteStack> RED_CONCRETE_TYPE;

    public static ItemType<ItemRedConcretePowderStack> RED_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemRedDyeStack> RED_DYE_TYPE;

    public static ItemType<ItemRedFlowerStack> RED_FLOWER_TYPE;

    public static ItemType<ItemRedGlazedTerracottaStack> RED_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemRedMushroomStack> RED_MUSHROOM_TYPE;

    public static ItemType<ItemRedMushroomBlockStack> RED_MUSHROOM_BLOCK_TYPE;

    public static ItemType<ItemRedNetherBrickStack> RED_NETHER_BRICK_TYPE;

    public static ItemType<ItemRedNetherBrickStairsStack> RED_NETHER_BRICK_STAIRS_TYPE;

    public static ItemType<ItemRedSandstoneStack> RED_SANDSTONE_TYPE;

    public static ItemType<ItemRedSandstoneStairsStack> RED_SANDSTONE_STAIRS_TYPE;

    public static ItemType<ItemRedShulkerBoxStack> RED_SHULKER_BOX_TYPE;

    public static ItemType<ItemRedStainedGlassStack> RED_STAINED_GLASS_TYPE;

    public static ItemType<ItemRedStainedGlassPaneStack> RED_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemRedTerracottaStack> RED_TERRACOTTA_TYPE;

    public static ItemType<ItemRedTulipStack> RED_TULIP_TYPE;

    public static ItemType<ItemRedWoolStack> RED_WOOL_TYPE;

    public static ItemType<ItemRedstoneStack> REDSTONE_TYPE;

    public static ItemType<ItemRedstoneBlockStack> REDSTONE_BLOCK_TYPE;

    public static ItemType<ItemRedstoneLampStack> REDSTONE_LAMP_TYPE;

    public static ItemType<ItemRedstoneOreStack> REDSTONE_ORE_TYPE;

    public static ItemType<ItemRedstoneTorchStack> REDSTONE_TORCH_TYPE;

    public static ItemType<ItemRedstoneWireStack> REDSTONE_WIRE_TYPE;

    public static ItemType<ItemReinforcedDeepslateStack> REINFORCED_DEEPSLATE_TYPE;

    public static ItemType<ItemRepeaterStack> REPEATER_TYPE;

    public static ItemType<ItemRepeatingCommandBlockStack> REPEATING_COMMAND_BLOCK_TYPE;

    public static ItemType<ItemReserved6Stack> RESERVED6_TYPE;

    public static ItemType<ItemRespawnAnchorStack> RESPAWN_ANCHOR_TYPE;

    public static ItemType<ItemRibArmorTrimSmithingTemplateStack> RIB_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemRottenFleshStack> ROTTEN_FLESH_TYPE;

    public static ItemType<ItemSaddleStack> SADDLE_TYPE;

    public static ItemType<ItemSalmonStack> SALMON_TYPE;

    public static ItemType<ItemSalmonBucketStack> SALMON_BUCKET_TYPE;

    public static ItemType<ItemSalmonSpawnEggStack> SALMON_SPAWN_EGG_TYPE;

    public static ItemType<ItemSandStack> SAND_TYPE;

    public static ItemType<ItemSandstoneStack> SANDSTONE_TYPE;

    public static ItemType<ItemSandstoneStairsStack> SANDSTONE_STAIRS_TYPE;

    public static ItemType<ItemSaplingStack> SAPLING_TYPE;

    public static ItemType<ItemScaffoldingStack> SCAFFOLDING_TYPE;

    public static ItemType<ItemScrapePotterySherdStack> SCRAPE_POTTERY_SHERD_TYPE;

    public static ItemType<ItemSculkStack> SCULK_TYPE;

    public static ItemType<ItemSculkCatalystStack> SCULK_CATALYST_TYPE;

    public static ItemType<ItemSculkSensorStack> SCULK_SENSOR_TYPE;

    public static ItemType<ItemSculkShriekerStack> SCULK_SHRIEKER_TYPE;

    public static ItemType<ItemSculkVeinStack> SCULK_VEIN_TYPE;

    public static ItemType<ItemSeaLanternStack> SEA_LANTERN_TYPE;

    public static ItemType<ItemSeaPickleStack> SEA_PICKLE_TYPE;

    public static ItemType<ItemSeagrassStack> SEAGRASS_TYPE;

    public static ItemType<ItemSentryArmorTrimSmithingTemplateStack> SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemShaperArmorTrimSmithingTemplateStack> SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemSheafPotterySherdStack> SHEAF_POTTERY_SHERD_TYPE;

    public static ItemType<ItemShearsStack> SHEARS_TYPE;

    public static ItemType<ItemSheepSpawnEggStack> SHEEP_SPAWN_EGG_TYPE;

    public static ItemType<ItemShelterPotterySherdStack> SHELTER_POTTERY_SHERD_TYPE;

    public static ItemType<ItemShieldStack> SHIELD_TYPE;

    public static ItemType<ItemShroomlightStack> SHROOMLIGHT_TYPE;

    public static ItemType<ItemShulkerBoxStack> SHULKER_BOX_TYPE;

    public static ItemType<ItemShulkerShellStack> SHULKER_SHELL_TYPE;

    public static ItemType<ItemShulkerSpawnEggStack> SHULKER_SPAWN_EGG_TYPE;

    public static ItemType<ItemSilenceArmorTrimSmithingTemplateStack> SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemSilverGlazedTerracottaStack> SILVER_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemSilverfishSpawnEggStack> SILVERFISH_SPAWN_EGG_TYPE;

    public static ItemType<ItemSkeletonHorseSpawnEggStack> SKELETON_HORSE_SPAWN_EGG_TYPE;

    public static ItemType<ItemSkeletonSpawnEggStack> SKELETON_SPAWN_EGG_TYPE;

    public static ItemType<ItemSkullStack> SKULL_TYPE;

    public static ItemType<ItemSkullBannerPatternStack> SKULL_BANNER_PATTERN_TYPE;

    public static ItemType<ItemSkullPotterySherdStack> SKULL_POTTERY_SHERD_TYPE;

    public static ItemType<ItemSlimeStack> SLIME_TYPE;

    public static ItemType<ItemSlimeBallStack> SLIME_BALL_TYPE;

    public static ItemType<ItemSlimeSpawnEggStack> SLIME_SPAWN_EGG_TYPE;

    public static ItemType<ItemSmallAmethystBudStack> SMALL_AMETHYST_BUD_TYPE;

    public static ItemType<ItemSmallDripleafBlockStack> SMALL_DRIPLEAF_BLOCK_TYPE;

    public static ItemType<ItemSmithingTableStack> SMITHING_TABLE_TYPE;

    public static ItemType<ItemSmokerStack> SMOKER_TYPE;

    public static ItemType<ItemSmoothBasaltStack> SMOOTH_BASALT_TYPE;

    public static ItemType<ItemSmoothQuartzStairsStack> SMOOTH_QUARTZ_STAIRS_TYPE;

    public static ItemType<ItemSmoothRedSandstoneStairsStack> SMOOTH_RED_SANDSTONE_STAIRS_TYPE;

    public static ItemType<ItemSmoothSandstoneStairsStack> SMOOTH_SANDSTONE_STAIRS_TYPE;

    public static ItemType<ItemSmoothStoneStack> SMOOTH_STONE_TYPE;

    public static ItemType<ItemSnifferEggStack> SNIFFER_EGG_TYPE;

    public static ItemType<ItemSnifferSpawnEggStack> SNIFFER_SPAWN_EGG_TYPE;

    public static ItemType<ItemSnortPotterySherdStack> SNORT_POTTERY_SHERD_TYPE;

    public static ItemType<ItemSnoutArmorTrimSmithingTemplateStack> SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemSnowStack> SNOW_TYPE;

    public static ItemType<ItemSnowGolemSpawnEggStack> SNOW_GOLEM_SPAWN_EGG_TYPE;

    public static ItemType<ItemSnowLayerStack> SNOW_LAYER_TYPE;

    public static ItemType<ItemSnowballStack> SNOWBALL_TYPE;

    public static ItemType<ItemSoulCampfireStack> SOUL_CAMPFIRE_TYPE;

    public static ItemType<ItemSoulFireStack> SOUL_FIRE_TYPE;

    public static ItemType<ItemSoulLanternStack> SOUL_LANTERN_TYPE;

    public static ItemType<ItemSoulSandStack> SOUL_SAND_TYPE;

    public static ItemType<ItemSoulSoilStack> SOUL_SOIL_TYPE;

    public static ItemType<ItemSoulTorchStack> SOUL_TORCH_TYPE;

    public static ItemType<ItemSparklerStack> SPARKLER_TYPE;

    public static ItemType<ItemSpawnEggStack> SPAWN_EGG_TYPE;

    public static ItemType<ItemSpiderEyeStack> SPIDER_EYE_TYPE;

    public static ItemType<ItemSpiderSpawnEggStack> SPIDER_SPAWN_EGG_TYPE;

    public static ItemType<ItemSpireArmorTrimSmithingTemplateStack> SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemSplashPotionStack> SPLASH_POTION_TYPE;

    public static ItemType<ItemSpongeStack> SPONGE_TYPE;

    public static ItemType<ItemSporeBlossomStack> SPORE_BLOSSOM_TYPE;

    public static ItemType<ItemSpruceBoatStack> SPRUCE_BOAT_TYPE;

    public static ItemType<ItemSpruceButtonStack> SPRUCE_BUTTON_TYPE;

    public static ItemType<ItemSpruceChestBoatStack> SPRUCE_CHEST_BOAT_TYPE;

    public static ItemType<ItemSpruceDoorStack> SPRUCE_DOOR_TYPE;

    public static ItemType<ItemSpruceDoubleSlabStack> SPRUCE_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemSpruceFenceStack> SPRUCE_FENCE_TYPE;

    public static ItemType<ItemSpruceFenceGateStack> SPRUCE_FENCE_GATE_TYPE;

    public static ItemType<ItemSpruceHangingSignStack> SPRUCE_HANGING_SIGN_TYPE;

    public static ItemType<ItemSpruceLeavesStack> SPRUCE_LEAVES_TYPE;

    public static ItemType<ItemSpruceLogStack> SPRUCE_LOG_TYPE;

    public static ItemType<ItemSprucePlanksStack> SPRUCE_PLANKS_TYPE;

    public static ItemType<ItemSprucePressurePlateStack> SPRUCE_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemSpruceSaplingStack> SPRUCE_SAPLING_TYPE;

    public static ItemType<ItemSpruceSignStack> SPRUCE_SIGN_TYPE;

    public static ItemType<ItemSpruceSlabStack> SPRUCE_SLAB_TYPE;

    public static ItemType<ItemSpruceStairsStack> SPRUCE_STAIRS_TYPE;

    public static ItemType<ItemSpruceStandingSignStack> SPRUCE_STANDING_SIGN_TYPE;

    public static ItemType<ItemSpruceTrapdoorStack> SPRUCE_TRAPDOOR_TYPE;

    public static ItemType<ItemSpruceWallSignStack> SPRUCE_WALL_SIGN_TYPE;

    public static ItemType<ItemSpruceWoodStack> SPRUCE_WOOD_TYPE;

    public static ItemType<ItemSpyglassStack> SPYGLASS_TYPE;

    public static ItemType<ItemSquidSpawnEggStack> SQUID_SPAWN_EGG_TYPE;

    public static ItemType<ItemStainedGlassStack> STAINED_GLASS_TYPE;

    public static ItemType<ItemStainedGlassPaneStack> STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemStainedHardenedClayStack> STAINED_HARDENED_CLAY_TYPE;

    public static ItemType<ItemStandingBannerStack> STANDING_BANNER_TYPE;

    public static ItemType<ItemStandingSignStack> STANDING_SIGN_TYPE;

    public static ItemType<ItemStickStack> STICK_TYPE;

    public static ItemType<ItemStickyPistonStack> STICKY_PISTON_TYPE;

    public static ItemType<ItemStickyPistonArmCollisionStack> STICKY_PISTON_ARM_COLLISION_TYPE;

    public static ItemType<ItemStoneStack> STONE_TYPE;

    public static ItemType<ItemStoneAxeStack> STONE_AXE_TYPE;

    public static ItemType<ItemStoneBlockSlabStack> STONE_BLOCK_SLAB_TYPE;

    public static ItemType<ItemStoneBlockSlab2Stack> STONE_BLOCK_SLAB2_TYPE;

    public static ItemType<ItemStoneBlockSlab3Stack> STONE_BLOCK_SLAB3_TYPE;

    public static ItemType<ItemStoneBlockSlab4Stack> STONE_BLOCK_SLAB4_TYPE;

    public static ItemType<ItemStoneBrickStairsStack> STONE_BRICK_STAIRS_TYPE;

    public static ItemType<ItemStoneButtonStack> STONE_BUTTON_TYPE;

    public static ItemType<ItemStoneHoeStack> STONE_HOE_TYPE;

    public static ItemType<ItemStonePickaxeStack> STONE_PICKAXE_TYPE;

    public static ItemType<ItemStonePressurePlateStack> STONE_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemStoneShovelStack> STONE_SHOVEL_TYPE;

    public static ItemType<ItemStoneStairsStack> STONE_STAIRS_TYPE;

    public static ItemType<ItemStoneSwordStack> STONE_SWORD_TYPE;

    public static ItemType<ItemStonebrickStack> STONEBRICK_TYPE;

    public static ItemType<ItemStonecutterStack> STONECUTTER_TYPE;

    public static ItemType<ItemStonecutterBlockStack> STONECUTTER_BLOCK_TYPE;

    public static ItemType<ItemStraySpawnEggStack> STRAY_SPAWN_EGG_TYPE;

    public static ItemType<ItemStriderSpawnEggStack> STRIDER_SPAWN_EGG_TYPE;

    public static ItemType<ItemStringStack> STRING_TYPE;

    public static ItemType<ItemStrippedAcaciaLogStack> STRIPPED_ACACIA_LOG_TYPE;

    public static ItemType<ItemStrippedAcaciaWoodStack> STRIPPED_ACACIA_WOOD_TYPE;

    public static ItemType<ItemStrippedBambooBlockStack> STRIPPED_BAMBOO_BLOCK_TYPE;

    public static ItemType<ItemStrippedBirchLogStack> STRIPPED_BIRCH_LOG_TYPE;

    public static ItemType<ItemStrippedBirchWoodStack> STRIPPED_BIRCH_WOOD_TYPE;

    public static ItemType<ItemStrippedCherryLogStack> STRIPPED_CHERRY_LOG_TYPE;

    public static ItemType<ItemStrippedCherryWoodStack> STRIPPED_CHERRY_WOOD_TYPE;

    public static ItemType<ItemStrippedCrimsonHyphaeStack> STRIPPED_CRIMSON_HYPHAE_TYPE;

    public static ItemType<ItemStrippedCrimsonStemStack> STRIPPED_CRIMSON_STEM_TYPE;

    public static ItemType<ItemStrippedDarkOakLogStack> STRIPPED_DARK_OAK_LOG_TYPE;

    public static ItemType<ItemStrippedDarkOakWoodStack> STRIPPED_DARK_OAK_WOOD_TYPE;

    public static ItemType<ItemStrippedJungleLogStack> STRIPPED_JUNGLE_LOG_TYPE;

    public static ItemType<ItemStrippedJungleWoodStack> STRIPPED_JUNGLE_WOOD_TYPE;

    public static ItemType<ItemStrippedMangroveLogStack> STRIPPED_MANGROVE_LOG_TYPE;

    public static ItemType<ItemStrippedMangroveWoodStack> STRIPPED_MANGROVE_WOOD_TYPE;

    public static ItemType<ItemStrippedOakLogStack> STRIPPED_OAK_LOG_TYPE;

    public static ItemType<ItemStrippedOakWoodStack> STRIPPED_OAK_WOOD_TYPE;

    public static ItemType<ItemStrippedSpruceLogStack> STRIPPED_SPRUCE_LOG_TYPE;

    public static ItemType<ItemStrippedSpruceWoodStack> STRIPPED_SPRUCE_WOOD_TYPE;

    public static ItemType<ItemStrippedWarpedHyphaeStack> STRIPPED_WARPED_HYPHAE_TYPE;

    public static ItemType<ItemStrippedWarpedStemStack> STRIPPED_WARPED_STEM_TYPE;

    public static ItemType<ItemStructureBlockStack> STRUCTURE_BLOCK_TYPE;

    public static ItemType<ItemStructureVoidStack> STRUCTURE_VOID_TYPE;

    public static ItemType<ItemSugarStack> SUGAR_TYPE;

    public static ItemType<ItemSugarCaneStack> SUGAR_CANE_TYPE;

    public static ItemType<ItemSuspiciousGravelStack> SUSPICIOUS_GRAVEL_TYPE;

    public static ItemType<ItemSuspiciousSandStack> SUSPICIOUS_SAND_TYPE;

    public static ItemType<ItemSuspiciousStewStack> SUSPICIOUS_STEW_TYPE;

    public static ItemType<ItemSweetBerriesStack> SWEET_BERRIES_TYPE;

    public static ItemType<ItemSweetBerryBushStack> SWEET_BERRY_BUSH_TYPE;

    public static ItemType<ItemTadpoleBucketStack> TADPOLE_BUCKET_TYPE;

    public static ItemType<ItemTadpoleSpawnEggStack> TADPOLE_SPAWN_EGG_TYPE;

    public static ItemType<ItemTallgrassStack> TALLGRASS_TYPE;

    public static ItemType<ItemTargetStack> TARGET_TYPE;

    public static ItemType<ItemTideArmorTrimSmithingTemplateStack> TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemTintedGlassStack> TINTED_GLASS_TYPE;

    public static ItemType<ItemTntStack> TNT_TYPE;

    public static ItemType<ItemTntMinecartStack> TNT_MINECART_TYPE;

    public static ItemType<ItemTorchStack> TORCH_TYPE;

    public static ItemType<ItemTorchflowerStack> TORCHFLOWER_TYPE;

    public static ItemType<ItemTorchflowerCropStack> TORCHFLOWER_CROP_TYPE;

    public static ItemType<ItemTorchflowerSeedsStack> TORCHFLOWER_SEEDS_TYPE;

    public static ItemType<ItemTotemOfUndyingStack> TOTEM_OF_UNDYING_TYPE;

    public static ItemType<ItemTraderLlamaSpawnEggStack> TRADER_LLAMA_SPAWN_EGG_TYPE;

    public static ItemType<ItemTrapdoorStack> TRAPDOOR_TYPE;

    public static ItemType<ItemTrappedChestStack> TRAPPED_CHEST_TYPE;

    public static ItemType<ItemTrialKeyStack> TRIAL_KEY_TYPE;

    public static ItemType<ItemTrialSpawnerStack> TRIAL_SPAWNER_TYPE;

    public static ItemType<ItemTridentStack> TRIDENT_TYPE;

    public static ItemType<ItemTripWireStack> TRIP_WIRE_TYPE;

    public static ItemType<ItemTripwireHookStack> TRIPWIRE_HOOK_TYPE;

    public static ItemType<ItemTropicalFishStack> TROPICAL_FISH_TYPE;

    public static ItemType<ItemTropicalFishBucketStack> TROPICAL_FISH_BUCKET_TYPE;

    public static ItemType<ItemTropicalFishSpawnEggStack> TROPICAL_FISH_SPAWN_EGG_TYPE;

    public static ItemType<ItemTubeCoralStack> TUBE_CORAL_TYPE;

    public static ItemType<ItemTubeCoralFanStack> TUBE_CORAL_FAN_TYPE;

    public static ItemType<ItemTuffStack> TUFF_TYPE;

    public static ItemType<ItemTuffBrickDoubleSlabStack> TUFF_BRICK_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemTuffBrickSlabStack> TUFF_BRICK_SLAB_TYPE;

    public static ItemType<ItemTuffBrickStairsStack> TUFF_BRICK_STAIRS_TYPE;

    public static ItemType<ItemTuffBrickWallStack> TUFF_BRICK_WALL_TYPE;

    public static ItemType<ItemTuffBricksStack> TUFF_BRICKS_TYPE;

    public static ItemType<ItemTuffDoubleSlabStack> TUFF_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemTuffSlabStack> TUFF_SLAB_TYPE;

    public static ItemType<ItemTuffStairsStack> TUFF_STAIRS_TYPE;

    public static ItemType<ItemTuffWallStack> TUFF_WALL_TYPE;

    public static ItemType<ItemTurtleEggStack> TURTLE_EGG_TYPE;

    public static ItemType<ItemTurtleHelmetStack> TURTLE_HELMET_TYPE;

    public static ItemType<ItemTurtleScuteStack> TURTLE_SCUTE_TYPE;

    public static ItemType<ItemTurtleSpawnEggStack> TURTLE_SPAWN_EGG_TYPE;

    public static ItemType<ItemTwistingVinesStack> TWISTING_VINES_TYPE;

    public static ItemType<ItemUnderwaterTorchStack> UNDERWATER_TORCH_TYPE;

    public static ItemType<ItemUndyedShulkerBoxStack> UNDYED_SHULKER_BOX_TYPE;

    public static ItemType<ItemUnknownStack> UNKNOWN_TYPE;

    public static ItemType<ItemUnlitRedstoneTorchStack> UNLIT_REDSTONE_TORCH_TYPE;

    public static ItemType<ItemUnpoweredComparatorStack> UNPOWERED_COMPARATOR_TYPE;

    public static ItemType<ItemUnpoweredRepeaterStack> UNPOWERED_REPEATER_TYPE;

    public static ItemType<ItemVaultStack> VAULT_TYPE;

    public static ItemType<ItemVerdantFroglightStack> VERDANT_FROGLIGHT_TYPE;

    public static ItemType<ItemVexArmorTrimSmithingTemplateStack> VEX_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemVexSpawnEggStack> VEX_SPAWN_EGG_TYPE;

    public static ItemType<ItemVillagerSpawnEggStack> VILLAGER_SPAWN_EGG_TYPE;

    public static ItemType<ItemVindicatorSpawnEggStack> VINDICATOR_SPAWN_EGG_TYPE;

    public static ItemType<ItemVineStack> VINE_TYPE;

    public static ItemType<ItemWallBannerStack> WALL_BANNER_TYPE;

    public static ItemType<ItemWallSignStack> WALL_SIGN_TYPE;

    public static ItemType<ItemWanderingTraderSpawnEggStack> WANDERING_TRADER_SPAWN_EGG_TYPE;

    public static ItemType<ItemWardArmorTrimSmithingTemplateStack> WARD_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemWardenSpawnEggStack> WARDEN_SPAWN_EGG_TYPE;

    public static ItemType<ItemWarpedButtonStack> WARPED_BUTTON_TYPE;

    public static ItemType<ItemWarpedDoorStack> WARPED_DOOR_TYPE;

    public static ItemType<ItemWarpedDoubleSlabStack> WARPED_DOUBLE_SLAB_TYPE;

    public static ItemType<ItemWarpedFenceStack> WARPED_FENCE_TYPE;

    public static ItemType<ItemWarpedFenceGateStack> WARPED_FENCE_GATE_TYPE;

    public static ItemType<ItemWarpedFungusStack> WARPED_FUNGUS_TYPE;

    public static ItemType<ItemWarpedFungusOnAStickStack> WARPED_FUNGUS_ON_A_STICK_TYPE;

    public static ItemType<ItemWarpedHangingSignStack> WARPED_HANGING_SIGN_TYPE;

    public static ItemType<ItemWarpedHyphaeStack> WARPED_HYPHAE_TYPE;

    public static ItemType<ItemWarpedNyliumStack> WARPED_NYLIUM_TYPE;

    public static ItemType<ItemWarpedPlanksStack> WARPED_PLANKS_TYPE;

    public static ItemType<ItemWarpedPressurePlateStack> WARPED_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemWarpedRootsStack> WARPED_ROOTS_TYPE;

    public static ItemType<ItemWarpedSignStack> WARPED_SIGN_TYPE;

    public static ItemType<ItemWarpedSlabStack> WARPED_SLAB_TYPE;

    public static ItemType<ItemWarpedStairsStack> WARPED_STAIRS_TYPE;

    public static ItemType<ItemWarpedStandingSignStack> WARPED_STANDING_SIGN_TYPE;

    public static ItemType<ItemWarpedStemStack> WARPED_STEM_TYPE;

    public static ItemType<ItemWarpedTrapdoorStack> WARPED_TRAPDOOR_TYPE;

    public static ItemType<ItemWarpedWallSignStack> WARPED_WALL_SIGN_TYPE;

    public static ItemType<ItemWarpedWartBlockStack> WARPED_WART_BLOCK_TYPE;

    public static ItemType<ItemWaterStack> WATER_TYPE;

    public static ItemType<ItemWaterBucketStack> WATER_BUCKET_TYPE;

    public static ItemType<ItemWaterlilyStack> WATERLILY_TYPE;

    public static ItemType<ItemWaxedChiseledCopperStack> WAXED_CHISELED_COPPER_TYPE;

    public static ItemType<ItemWaxedCopperStack> WAXED_COPPER_TYPE;

    public static ItemType<ItemWaxedCopperBulbStack> WAXED_COPPER_BULB_TYPE;

    public static ItemType<ItemWaxedCopperDoorStack> WAXED_COPPER_DOOR_TYPE;

    public static ItemType<ItemWaxedCopperGrateStack> WAXED_COPPER_GRATE_TYPE;

    public static ItemType<ItemWaxedCopperTrapdoorStack> WAXED_COPPER_TRAPDOOR_TYPE;

    public static ItemType<ItemWaxedCutCopperStack> WAXED_CUT_COPPER_TYPE;

    public static ItemType<ItemWaxedCutCopperSlabStack> WAXED_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemWaxedCutCopperStairsStack> WAXED_CUT_COPPER_STAIRS_TYPE;

    public static ItemType<ItemWaxedDoubleCutCopperSlabStack> WAXED_DOUBLE_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemWaxedExposedChiseledCopperStack> WAXED_EXPOSED_CHISELED_COPPER_TYPE;

    public static ItemType<ItemWaxedExposedCopperStack> WAXED_EXPOSED_COPPER_TYPE;

    public static ItemType<ItemWaxedExposedCopperBulbStack> WAXED_EXPOSED_COPPER_BULB_TYPE;

    public static ItemType<ItemWaxedExposedCopperDoorStack> WAXED_EXPOSED_COPPER_DOOR_TYPE;

    public static ItemType<ItemWaxedExposedCopperGrateStack> WAXED_EXPOSED_COPPER_GRATE_TYPE;

    public static ItemType<ItemWaxedExposedCopperTrapdoorStack> WAXED_EXPOSED_COPPER_TRAPDOOR_TYPE;

    public static ItemType<ItemWaxedExposedCutCopperStack> WAXED_EXPOSED_CUT_COPPER_TYPE;

    public static ItemType<ItemWaxedExposedCutCopperSlabStack> WAXED_EXPOSED_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemWaxedExposedCutCopperStairsStack> WAXED_EXPOSED_CUT_COPPER_STAIRS_TYPE;

    public static ItemType<ItemWaxedExposedDoubleCutCopperSlabStack> WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemWaxedOxidizedChiseledCopperStack> WAXED_OXIDIZED_CHISELED_COPPER_TYPE;

    public static ItemType<ItemWaxedOxidizedCopperStack> WAXED_OXIDIZED_COPPER_TYPE;

    public static ItemType<ItemWaxedOxidizedCopperBulbStack> WAXED_OXIDIZED_COPPER_BULB_TYPE;

    public static ItemType<ItemWaxedOxidizedCopperDoorStack> WAXED_OXIDIZED_COPPER_DOOR_TYPE;

    public static ItemType<ItemWaxedOxidizedCopperGrateStack> WAXED_OXIDIZED_COPPER_GRATE_TYPE;

    public static ItemType<ItemWaxedOxidizedCopperTrapdoorStack> WAXED_OXIDIZED_COPPER_TRAPDOOR_TYPE;

    public static ItemType<ItemWaxedOxidizedCutCopperStack> WAXED_OXIDIZED_CUT_COPPER_TYPE;

    public static ItemType<ItemWaxedOxidizedCutCopperSlabStack> WAXED_OXIDIZED_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemWaxedOxidizedCutCopperStairsStack> WAXED_OXIDIZED_CUT_COPPER_STAIRS_TYPE;

    public static ItemType<ItemWaxedOxidizedDoubleCutCopperSlabStack> WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemWaxedWeatheredChiseledCopperStack> WAXED_WEATHERED_CHISELED_COPPER_TYPE;

    public static ItemType<ItemWaxedWeatheredCopperStack> WAXED_WEATHERED_COPPER_TYPE;

    public static ItemType<ItemWaxedWeatheredCopperBulbStack> WAXED_WEATHERED_COPPER_BULB_TYPE;

    public static ItemType<ItemWaxedWeatheredCopperDoorStack> WAXED_WEATHERED_COPPER_DOOR_TYPE;

    public static ItemType<ItemWaxedWeatheredCopperGrateStack> WAXED_WEATHERED_COPPER_GRATE_TYPE;

    public static ItemType<ItemWaxedWeatheredCopperTrapdoorStack> WAXED_WEATHERED_COPPER_TRAPDOOR_TYPE;

    public static ItemType<ItemWaxedWeatheredCutCopperStack> WAXED_WEATHERED_CUT_COPPER_TYPE;

    public static ItemType<ItemWaxedWeatheredCutCopperSlabStack> WAXED_WEATHERED_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemWaxedWeatheredCutCopperStairsStack> WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE;

    public static ItemType<ItemWaxedWeatheredDoubleCutCopperSlabStack> WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemWayfinderArmorTrimSmithingTemplateStack> WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemWeatheredChiseledCopperStack> WEATHERED_CHISELED_COPPER_TYPE;

    public static ItemType<ItemWeatheredCopperStack> WEATHERED_COPPER_TYPE;

    public static ItemType<ItemWeatheredCopperBulbStack> WEATHERED_COPPER_BULB_TYPE;

    public static ItemType<ItemWeatheredCopperDoorStack> WEATHERED_COPPER_DOOR_TYPE;

    public static ItemType<ItemWeatheredCopperGrateStack> WEATHERED_COPPER_GRATE_TYPE;

    public static ItemType<ItemWeatheredCopperTrapdoorStack> WEATHERED_COPPER_TRAPDOOR_TYPE;

    public static ItemType<ItemWeatheredCutCopperStack> WEATHERED_CUT_COPPER_TYPE;

    public static ItemType<ItemWeatheredCutCopperSlabStack> WEATHERED_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemWeatheredCutCopperStairsStack> WEATHERED_CUT_COPPER_STAIRS_TYPE;

    public static ItemType<ItemWeatheredDoubleCutCopperSlabStack> WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE;

    public static ItemType<ItemWebStack> WEB_TYPE;

    public static ItemType<ItemWeepingVinesStack> WEEPING_VINES_TYPE;

    public static ItemType<ItemWheatStack> WHEAT_TYPE;

    public static ItemType<ItemWheatSeedsStack> WHEAT_SEEDS_TYPE;

    public static ItemType<ItemWhiteCandleStack> WHITE_CANDLE_TYPE;

    public static ItemType<ItemWhiteCandleCakeStack> WHITE_CANDLE_CAKE_TYPE;

    public static ItemType<ItemWhiteCarpetStack> WHITE_CARPET_TYPE;

    public static ItemType<ItemWhiteConcreteStack> WHITE_CONCRETE_TYPE;

    public static ItemType<ItemWhiteConcretePowderStack> WHITE_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemWhiteDyeStack> WHITE_DYE_TYPE;

    public static ItemType<ItemWhiteGlazedTerracottaStack> WHITE_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemWhiteShulkerBoxStack> WHITE_SHULKER_BOX_TYPE;

    public static ItemType<ItemWhiteStainedGlassStack> WHITE_STAINED_GLASS_TYPE;

    public static ItemType<ItemWhiteStainedGlassPaneStack> WHITE_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemWhiteTerracottaStack> WHITE_TERRACOTTA_TYPE;

    public static ItemType<ItemWhiteTulipStack> WHITE_TULIP_TYPE;

    public static ItemType<ItemWhiteWoolStack> WHITE_WOOL_TYPE;

    public static ItemType<ItemWildArmorTrimSmithingTemplateStack> WILD_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE;

    public static ItemType<ItemWindChargeStack> WIND_CHARGE_TYPE;

    public static ItemType<ItemWitchSpawnEggStack> WITCH_SPAWN_EGG_TYPE;

    public static ItemType<ItemWitherRoseStack> WITHER_ROSE_TYPE;

    public static ItemType<ItemWitherSkeletonSpawnEggStack> WITHER_SKELETON_SPAWN_EGG_TYPE;

    public static ItemType<ItemWitherSpawnEggStack> WITHER_SPAWN_EGG_TYPE;

    public static ItemType<ItemWolfArmorStack> WOLF_ARMOR_TYPE;

    public static ItemType<ItemWolfSpawnEggStack> WOLF_SPAWN_EGG_TYPE;

    public static ItemType<ItemWoodStack> WOOD_TYPE;

    public static ItemType<ItemWoodenAxeStack> WOODEN_AXE_TYPE;

    public static ItemType<ItemWoodenButtonStack> WOODEN_BUTTON_TYPE;

    public static ItemType<ItemWoodenDoorStack> WOODEN_DOOR_TYPE;

    public static ItemType<ItemWoodenHoeStack> WOODEN_HOE_TYPE;

    public static ItemType<ItemWoodenPickaxeStack> WOODEN_PICKAXE_TYPE;

    public static ItemType<ItemWoodenPressurePlateStack> WOODEN_PRESSURE_PLATE_TYPE;

    public static ItemType<ItemWoodenShovelStack> WOODEN_SHOVEL_TYPE;

    public static ItemType<ItemWoodenSlabStack> WOODEN_SLAB_TYPE;

    public static ItemType<ItemWoodenSwordStack> WOODEN_SWORD_TYPE;

    public static ItemType<ItemWoolStack> WOOL_TYPE;

    public static ItemType<ItemWritableBookStack> WRITABLE_BOOK_TYPE;

    public static ItemType<ItemWrittenBookStack> WRITTEN_BOOK_TYPE;

    public static ItemType<ItemYellowCandleStack> YELLOW_CANDLE_TYPE;

    public static ItemType<ItemYellowCandleCakeStack> YELLOW_CANDLE_CAKE_TYPE;

    public static ItemType<ItemYellowCarpetStack> YELLOW_CARPET_TYPE;

    public static ItemType<ItemYellowConcreteStack> YELLOW_CONCRETE_TYPE;

    public static ItemType<ItemYellowConcretePowderStack> YELLOW_CONCRETE_POWDER_TYPE;

    public static ItemType<ItemYellowDyeStack> YELLOW_DYE_TYPE;

    public static ItemType<ItemYellowFlowerStack> YELLOW_FLOWER_TYPE;

    public static ItemType<ItemYellowGlazedTerracottaStack> YELLOW_GLAZED_TERRACOTTA_TYPE;

    public static ItemType<ItemYellowShulkerBoxStack> YELLOW_SHULKER_BOX_TYPE;

    public static ItemType<ItemYellowStainedGlassStack> YELLOW_STAINED_GLASS_TYPE;

    public static ItemType<ItemYellowStainedGlassPaneStack> YELLOW_STAINED_GLASS_PANE_TYPE;

    public static ItemType<ItemYellowTerracottaStack> YELLOW_TERRACOTTA_TYPE;

    public static ItemType<ItemYellowWoolStack> YELLOW_WOOL_TYPE;

    public static ItemType<ItemZoglinSpawnEggStack> ZOGLIN_SPAWN_EGG_TYPE;

    public static ItemType<ItemZombieHorseSpawnEggStack> ZOMBIE_HORSE_SPAWN_EGG_TYPE;

    public static ItemType<ItemZombiePigmanSpawnEggStack> ZOMBIE_PIGMAN_SPAWN_EGG_TYPE;

    public static ItemType<ItemZombieSpawnEggStack> ZOMBIE_SPAWN_EGG_TYPE;

    public static ItemType<ItemZombieVillagerSpawnEggStack> ZOMBIE_VILLAGER_SPAWN_EGG_TYPE;
}
