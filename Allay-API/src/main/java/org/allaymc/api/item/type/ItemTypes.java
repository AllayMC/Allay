package org.allaymc.api.item.type;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
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
import org.allaymc.api.item.interfaces.sapling.ItemBambooSaplingStack;
import org.allaymc.api.item.interfaces.sapling.ItemCherrySaplingStack;
import org.allaymc.api.item.interfaces.sapling.ItemSaplingStack;
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

    public static ItemType<ItemAcaciaBoatStack> ACACIA_BOAT_TYPE = empty(ItemAcaciaBoatStack.class, VanillaItemId.ACACIA_BOAT);

    public static ItemType<ItemAcaciaButtonStack> ACACIA_BUTTON_TYPE = empty(ItemAcaciaButtonStack.class, VanillaItemId.ACACIA_BUTTON);

    public static ItemType<ItemAcaciaChestBoatStack> ACACIA_CHEST_BOAT_TYPE = empty(ItemAcaciaChestBoatStack.class, VanillaItemId.ACACIA_CHEST_BOAT);

    public static ItemType<ItemAcaciaDoorStack> ACACIA_DOOR_TYPE = empty(ItemAcaciaDoorStack.class, VanillaItemId.ACACIA_DOOR);

    public static ItemType<ItemAcaciaFenceStack> ACACIA_FENCE_TYPE = empty(ItemAcaciaFenceStack.class, VanillaItemId.ACACIA_FENCE);

    public static ItemType<ItemAcaciaFenceGateStack> ACACIA_FENCE_GATE_TYPE = empty(ItemAcaciaFenceGateStack.class, VanillaItemId.ACACIA_FENCE_GATE);

    public static ItemType<ItemAcaciaHangingSignStack> ACACIA_HANGING_SIGN_TYPE = empty(ItemAcaciaHangingSignStack.class, VanillaItemId.ACACIA_HANGING_SIGN);

    public static ItemType<ItemAcaciaLogStack> ACACIA_LOG_TYPE = empty(ItemAcaciaLogStack.class, VanillaItemId.ACACIA_LOG);

    public static ItemType<ItemAcaciaPlanksStack> ACACIA_PLANKS_TYPE = empty(ItemAcaciaPlanksStack.class, VanillaItemId.ACACIA_PLANKS);

    public static ItemType<ItemAcaciaPressurePlateStack> ACACIA_PRESSURE_PLATE_TYPE = empty(ItemAcaciaPressurePlateStack.class, VanillaItemId.ACACIA_PRESSURE_PLATE);

    public static ItemType<ItemAcaciaSignStack> ACACIA_SIGN_TYPE = empty(ItemAcaciaSignStack.class, VanillaItemId.ACACIA_SIGN);

    public static ItemType<ItemAcaciaStairsStack> ACACIA_STAIRS_TYPE = empty(ItemAcaciaStairsStack.class, VanillaItemId.ACACIA_STAIRS);

    public static ItemType<ItemAcaciaStandingSignStack> ACACIA_STANDING_SIGN_TYPE = empty(ItemAcaciaStandingSignStack.class, VanillaItemId.ACACIA_STANDING_SIGN);

    public static ItemType<ItemAcaciaTrapdoorStack> ACACIA_TRAPDOOR_TYPE = empty(ItemAcaciaTrapdoorStack.class, VanillaItemId.ACACIA_TRAPDOOR);

    public static ItemType<ItemAcaciaWallSignStack> ACACIA_WALL_SIGN_TYPE = empty(ItemAcaciaWallSignStack.class, VanillaItemId.ACACIA_WALL_SIGN);

    public static ItemType<ItemActivatorRailStack> ACTIVATOR_RAIL_TYPE = empty(ItemActivatorRailStack.class, VanillaItemId.ACTIVATOR_RAIL);

    public static ItemType<ItemAgentSpawnEggStack> AGENT_SPAWN_EGG_TYPE = empty(ItemAgentSpawnEggStack.class, VanillaItemId.AGENT_SPAWN_EGG);

    public static ItemType<ItemAirStack> AIR_TYPE;

    public static ItemType<ItemAllaySpawnEggStack> ALLAY_SPAWN_EGG_TYPE = empty(ItemAllaySpawnEggStack.class, VanillaItemId.ALLAY_SPAWN_EGG);

    public static ItemType<ItemAllowStack> ALLOW_TYPE = empty(ItemAllowStack.class, VanillaItemId.ALLOW);

    public static ItemType<ItemAmethystBlockStack> AMETHYST_BLOCK_TYPE = empty(ItemAmethystBlockStack.class, VanillaItemId.AMETHYST_BLOCK);

    public static ItemType<ItemAmethystClusterStack> AMETHYST_CLUSTER_TYPE = empty(ItemAmethystClusterStack.class, VanillaItemId.AMETHYST_CLUSTER);

    public static ItemType<ItemAmethystShardStack> AMETHYST_SHARD_TYPE = empty(ItemAmethystShardStack.class, VanillaItemId.AMETHYST_SHARD);

    public static ItemType<ItemAncientDebrisStack> ANCIENT_DEBRIS_TYPE = empty(ItemAncientDebrisStack.class, VanillaItemId.ANCIENT_DEBRIS);

    public static ItemType<ItemAndesiteStack> ANDESITE_TYPE = empty(ItemAndesiteStack.class, VanillaItemId.ANDESITE);

    public static ItemType<ItemAndesiteStairsStack> ANDESITE_STAIRS_TYPE = empty(ItemAndesiteStairsStack.class, VanillaItemId.ANDESITE_STAIRS);

    public static ItemType<ItemAnglerPotterySherdStack> ANGLER_POTTERY_SHERD_TYPE = empty(ItemAnglerPotterySherdStack.class, VanillaItemId.ANGLER_POTTERY_SHERD);

    public static ItemType<ItemAnvilStack> ANVIL_TYPE = empty(ItemAnvilStack.class, VanillaItemId.ANVIL);

    public static ItemType<ItemAppleStack> APPLE_TYPE = empty(ItemAppleStack.class, VanillaItemId.APPLE);

    public static ItemType<ItemArcherPotterySherdStack> ARCHER_POTTERY_SHERD_TYPE = empty(ItemArcherPotterySherdStack.class, VanillaItemId.ARCHER_POTTERY_SHERD);

    public static ItemType<ItemArmadilloScuteStack> ARMADILLO_SCUTE_TYPE = empty(ItemArmadilloScuteStack.class, VanillaItemId.ARMADILLO_SCUTE);

    public static ItemType<ItemArmadilloSpawnEggStack> ARMADILLO_SPAWN_EGG_TYPE = empty(ItemArmadilloSpawnEggStack.class, VanillaItemId.ARMADILLO_SPAWN_EGG);

    public static ItemType<ItemArmorStandStack> ARMOR_STAND_TYPE = empty(ItemArmorStandStack.class, VanillaItemId.ARMOR_STAND);

    public static ItemType<ItemArmsUpPotterySherdStack> ARMS_UP_POTTERY_SHERD_TYPE = empty(ItemArmsUpPotterySherdStack.class, VanillaItemId.ARMS_UP_POTTERY_SHERD);

    public static ItemType<ItemArrowStack> ARROW_TYPE = empty(ItemArrowStack.class, VanillaItemId.ARROW);

    public static ItemType<ItemAxolotlBucketStack> AXOLOTL_BUCKET_TYPE = empty(ItemAxolotlBucketStack.class, VanillaItemId.AXOLOTL_BUCKET);

    public static ItemType<ItemAxolotlSpawnEggStack> AXOLOTL_SPAWN_EGG_TYPE = empty(ItemAxolotlSpawnEggStack.class, VanillaItemId.AXOLOTL_SPAWN_EGG);

    public static ItemType<ItemAzaleaStack> AZALEA_TYPE = empty(ItemAzaleaStack.class, VanillaItemId.AZALEA);

    public static ItemType<ItemAzaleaLeavesStack> AZALEA_LEAVES_TYPE = empty(ItemAzaleaLeavesStack.class, VanillaItemId.AZALEA_LEAVES);

    public static ItemType<ItemAzaleaLeavesFloweredStack> AZALEA_LEAVES_FLOWERED_TYPE = empty(ItemAzaleaLeavesFloweredStack.class, VanillaItemId.AZALEA_LEAVES_FLOWERED);

    public static ItemType<ItemBakedPotatoStack> BAKED_POTATO_TYPE = empty(ItemBakedPotatoStack.class, VanillaItemId.BAKED_POTATO);

    public static ItemType<ItemBalloonStack> BALLOON_TYPE = empty(ItemBalloonStack.class, VanillaItemId.BALLOON);

    public static ItemType<ItemBambooStack> BAMBOO_TYPE = empty(ItemBambooStack.class, VanillaItemId.BAMBOO);

    public static ItemType<ItemBambooBlockStack> BAMBOO_BLOCK_TYPE = empty(ItemBambooBlockStack.class, VanillaItemId.BAMBOO_BLOCK);

    public static ItemType<ItemBambooButtonStack> BAMBOO_BUTTON_TYPE = empty(ItemBambooButtonStack.class, VanillaItemId.BAMBOO_BUTTON);

    public static ItemType<ItemBambooChestRaftStack> BAMBOO_CHEST_RAFT_TYPE = empty(ItemBambooChestRaftStack.class, VanillaItemId.BAMBOO_CHEST_RAFT);

    public static ItemType<ItemBambooDoorStack> BAMBOO_DOOR_TYPE = empty(ItemBambooDoorStack.class, VanillaItemId.BAMBOO_DOOR);

    public static ItemType<ItemBambooDoubleSlabStack> BAMBOO_DOUBLE_SLAB_TYPE = empty(ItemBambooDoubleSlabStack.class, VanillaItemId.BAMBOO_DOUBLE_SLAB);

    public static ItemType<ItemBambooFenceStack> BAMBOO_FENCE_TYPE = empty(ItemBambooFenceStack.class, VanillaItemId.BAMBOO_FENCE);

    public static ItemType<ItemBambooFenceGateStack> BAMBOO_FENCE_GATE_TYPE = empty(ItemBambooFenceGateStack.class, VanillaItemId.BAMBOO_FENCE_GATE);

    public static ItemType<ItemBambooHangingSignStack> BAMBOO_HANGING_SIGN_TYPE = empty(ItemBambooHangingSignStack.class, VanillaItemId.BAMBOO_HANGING_SIGN);

    public static ItemType<ItemBambooMosaicStack> BAMBOO_MOSAIC_TYPE = empty(ItemBambooMosaicStack.class, VanillaItemId.BAMBOO_MOSAIC);

    public static ItemType<ItemBambooMosaicDoubleSlabStack> BAMBOO_MOSAIC_DOUBLE_SLAB_TYPE = empty(ItemBambooMosaicDoubleSlabStack.class, VanillaItemId.BAMBOO_MOSAIC_DOUBLE_SLAB);

    public static ItemType<ItemBambooMosaicSlabStack> BAMBOO_MOSAIC_SLAB_TYPE = empty(ItemBambooMosaicSlabStack.class, VanillaItemId.BAMBOO_MOSAIC_SLAB);

    public static ItemType<ItemBambooMosaicStairsStack> BAMBOO_MOSAIC_STAIRS_TYPE = empty(ItemBambooMosaicStairsStack.class, VanillaItemId.BAMBOO_MOSAIC_STAIRS);

    public static ItemType<ItemBambooPlanksStack> BAMBOO_PLANKS_TYPE = empty(ItemBambooPlanksStack.class, VanillaItemId.BAMBOO_PLANKS);

    public static ItemType<ItemBambooPressurePlateStack> BAMBOO_PRESSURE_PLATE_TYPE = empty(ItemBambooPressurePlateStack.class, VanillaItemId.BAMBOO_PRESSURE_PLATE);

    public static ItemType<ItemBambooRaftStack> BAMBOO_RAFT_TYPE = empty(ItemBambooRaftStack.class, VanillaItemId.BAMBOO_RAFT);

    public static ItemType<ItemBambooSaplingStack> BAMBOO_SAPLING_TYPE = empty(ItemBambooSaplingStack.class, VanillaItemId.BAMBOO_SAPLING);

    public static ItemType<ItemBambooSignStack> BAMBOO_SIGN_TYPE = empty(ItemBambooSignStack.class, VanillaItemId.BAMBOO_SIGN);

    public static ItemType<ItemBambooSlabStack> BAMBOO_SLAB_TYPE = empty(ItemBambooSlabStack.class, VanillaItemId.BAMBOO_SLAB);

    public static ItemType<ItemBambooStairsStack> BAMBOO_STAIRS_TYPE = empty(ItemBambooStairsStack.class, VanillaItemId.BAMBOO_STAIRS);

    public static ItemType<ItemBambooStandingSignStack> BAMBOO_STANDING_SIGN_TYPE = empty(ItemBambooStandingSignStack.class, VanillaItemId.BAMBOO_STANDING_SIGN);

    public static ItemType<ItemBambooTrapdoorStack> BAMBOO_TRAPDOOR_TYPE = empty(ItemBambooTrapdoorStack.class, VanillaItemId.BAMBOO_TRAPDOOR);

    public static ItemType<ItemBambooWallSignStack> BAMBOO_WALL_SIGN_TYPE = empty(ItemBambooWallSignStack.class, VanillaItemId.BAMBOO_WALL_SIGN);

    public static ItemType<ItemBannerStack> BANNER_TYPE = empty(ItemBannerStack.class, VanillaItemId.BANNER);

    public static ItemType<ItemBannerPatternStack> BANNER_PATTERN_TYPE = empty(ItemBannerPatternStack.class, VanillaItemId.BANNER_PATTERN);

    public static ItemType<ItemBarrelStack> BARREL_TYPE = empty(ItemBarrelStack.class, VanillaItemId.BARREL);

    public static ItemType<ItemBarrierStack> BARRIER_TYPE = empty(ItemBarrierStack.class, VanillaItemId.BARRIER);

    public static ItemType<ItemBasaltStack> BASALT_TYPE = empty(ItemBasaltStack.class, VanillaItemId.BASALT);

    public static ItemType<ItemBatSpawnEggStack> BAT_SPAWN_EGG_TYPE = empty(ItemBatSpawnEggStack.class, VanillaItemId.BAT_SPAWN_EGG);

    public static ItemType<ItemBeaconStack> BEACON_TYPE = empty(ItemBeaconStack.class, VanillaItemId.BEACON);

    public static ItemType<ItemBedStack> BED_TYPE = empty(ItemBedStack.class, VanillaItemId.BED);

    public static ItemType<ItemBedrockStack> BEDROCK_TYPE = empty(ItemBedrockStack.class, VanillaItemId.BEDROCK);

    public static ItemType<ItemBeeNestStack> BEE_NEST_TYPE = empty(ItemBeeNestStack.class, VanillaItemId.BEE_NEST);

    public static ItemType<ItemBeeSpawnEggStack> BEE_SPAWN_EGG_TYPE = empty(ItemBeeSpawnEggStack.class, VanillaItemId.BEE_SPAWN_EGG);

    public static ItemType<ItemBeefStack> BEEF_TYPE = empty(ItemBeefStack.class, VanillaItemId.BEEF);

    public static ItemType<ItemBeehiveStack> BEEHIVE_TYPE = empty(ItemBeehiveStack.class, VanillaItemId.BEEHIVE);

    public static ItemType<ItemBeetrootStack> BEETROOT_TYPE = empty(ItemBeetrootStack.class, VanillaItemId.BEETROOT);

    public static ItemType<ItemBeetrootSeedsStack> BEETROOT_SEEDS_TYPE = empty(ItemBeetrootSeedsStack.class, VanillaItemId.BEETROOT_SEEDS);

    public static ItemType<ItemBeetrootSoupStack> BEETROOT_SOUP_TYPE = empty(ItemBeetrootSoupStack.class, VanillaItemId.BEETROOT_SOUP);

    public static ItemType<ItemBellStack> BELL_TYPE = empty(ItemBellStack.class, VanillaItemId.BELL);

    public static ItemType<ItemBigDripleafStack> BIG_DRIPLEAF_TYPE = empty(ItemBigDripleafStack.class, VanillaItemId.BIG_DRIPLEAF);

    public static ItemType<ItemBirchBoatStack> BIRCH_BOAT_TYPE = empty(ItemBirchBoatStack.class, VanillaItemId.BIRCH_BOAT);

    public static ItemType<ItemBirchButtonStack> BIRCH_BUTTON_TYPE = empty(ItemBirchButtonStack.class, VanillaItemId.BIRCH_BUTTON);

    public static ItemType<ItemBirchChestBoatStack> BIRCH_CHEST_BOAT_TYPE = empty(ItemBirchChestBoatStack.class, VanillaItemId.BIRCH_CHEST_BOAT);

    public static ItemType<ItemBirchDoorStack> BIRCH_DOOR_TYPE = empty(ItemBirchDoorStack.class, VanillaItemId.BIRCH_DOOR);

    public static ItemType<ItemBirchFenceStack> BIRCH_FENCE_TYPE = empty(ItemBirchFenceStack.class, VanillaItemId.BIRCH_FENCE);

    public static ItemType<ItemBirchFenceGateStack> BIRCH_FENCE_GATE_TYPE = empty(ItemBirchFenceGateStack.class, VanillaItemId.BIRCH_FENCE_GATE);

    public static ItemType<ItemBirchHangingSignStack> BIRCH_HANGING_SIGN_TYPE = empty(ItemBirchHangingSignStack.class, VanillaItemId.BIRCH_HANGING_SIGN);

    public static ItemType<ItemBirchLogStack> BIRCH_LOG_TYPE = empty(ItemBirchLogStack.class, VanillaItemId.BIRCH_LOG);

    public static ItemType<ItemBirchPlanksStack> BIRCH_PLANKS_TYPE = empty(ItemBirchPlanksStack.class, VanillaItemId.BIRCH_PLANKS);

    public static ItemType<ItemBirchPressurePlateStack> BIRCH_PRESSURE_PLATE_TYPE = empty(ItemBirchPressurePlateStack.class, VanillaItemId.BIRCH_PRESSURE_PLATE);

    public static ItemType<ItemBirchSignStack> BIRCH_SIGN_TYPE = empty(ItemBirchSignStack.class, VanillaItemId.BIRCH_SIGN);

    public static ItemType<ItemBirchStairsStack> BIRCH_STAIRS_TYPE = empty(ItemBirchStairsStack.class, VanillaItemId.BIRCH_STAIRS);

    public static ItemType<ItemBirchStandingSignStack> BIRCH_STANDING_SIGN_TYPE = empty(ItemBirchStandingSignStack.class, VanillaItemId.BIRCH_STANDING_SIGN);

    public static ItemType<ItemBirchTrapdoorStack> BIRCH_TRAPDOOR_TYPE = empty(ItemBirchTrapdoorStack.class, VanillaItemId.BIRCH_TRAPDOOR);

    public static ItemType<ItemBirchWallSignStack> BIRCH_WALL_SIGN_TYPE = empty(ItemBirchWallSignStack.class, VanillaItemId.BIRCH_WALL_SIGN);

    public static ItemType<ItemBlackCandleStack> BLACK_CANDLE_TYPE = empty(ItemBlackCandleStack.class, VanillaItemId.BLACK_CANDLE);

    public static ItemType<ItemBlackCandleCakeStack> BLACK_CANDLE_CAKE_TYPE = empty(ItemBlackCandleCakeStack.class, VanillaItemId.BLACK_CANDLE_CAKE);

    public static ItemType<ItemBlackCarpetStack> BLACK_CARPET_TYPE = empty(ItemBlackCarpetStack.class, VanillaItemId.BLACK_CARPET);

    public static ItemType<ItemBlackConcreteStack> BLACK_CONCRETE_TYPE = empty(ItemBlackConcreteStack.class, VanillaItemId.BLACK_CONCRETE);

    public static ItemType<ItemBlackConcretePowderStack> BLACK_CONCRETE_POWDER_TYPE = empty(ItemBlackConcretePowderStack.class, VanillaItemId.BLACK_CONCRETE_POWDER);

    public static ItemType<ItemBlackDyeStack> BLACK_DYE_TYPE = empty(ItemBlackDyeStack.class, VanillaItemId.BLACK_DYE);

    public static ItemType<ItemBlackGlazedTerracottaStack> BLACK_GLAZED_TERRACOTTA_TYPE = empty(ItemBlackGlazedTerracottaStack.class, VanillaItemId.BLACK_GLAZED_TERRACOTTA);

    public static ItemType<ItemBlackShulkerBoxStack> BLACK_SHULKER_BOX_TYPE = empty(ItemBlackShulkerBoxStack.class, VanillaItemId.BLACK_SHULKER_BOX);

    public static ItemType<ItemBlackStainedGlassStack> BLACK_STAINED_GLASS_TYPE = empty(ItemBlackStainedGlassStack.class, VanillaItemId.BLACK_STAINED_GLASS);

    public static ItemType<ItemBlackStainedGlassPaneStack> BLACK_STAINED_GLASS_PANE_TYPE = empty(ItemBlackStainedGlassPaneStack.class, VanillaItemId.BLACK_STAINED_GLASS_PANE);

    public static ItemType<ItemBlackTerracottaStack> BLACK_TERRACOTTA_TYPE = empty(ItemBlackTerracottaStack.class, VanillaItemId.BLACK_TERRACOTTA);

    public static ItemType<ItemBlackWoolStack> BLACK_WOOL_TYPE = empty(ItemBlackWoolStack.class, VanillaItemId.BLACK_WOOL);

    public static ItemType<ItemBlackstoneStack> BLACKSTONE_TYPE = empty(ItemBlackstoneStack.class, VanillaItemId.BLACKSTONE);

    public static ItemType<ItemBlackstoneDoubleSlabStack> BLACKSTONE_DOUBLE_SLAB_TYPE = empty(ItemBlackstoneDoubleSlabStack.class, VanillaItemId.BLACKSTONE_DOUBLE_SLAB);

    public static ItemType<ItemBlackstoneSlabStack> BLACKSTONE_SLAB_TYPE = empty(ItemBlackstoneSlabStack.class, VanillaItemId.BLACKSTONE_SLAB);

    public static ItemType<ItemBlackstoneStairsStack> BLACKSTONE_STAIRS_TYPE = empty(ItemBlackstoneStairsStack.class, VanillaItemId.BLACKSTONE_STAIRS);

    public static ItemType<ItemBlackstoneWallStack> BLACKSTONE_WALL_TYPE = empty(ItemBlackstoneWallStack.class, VanillaItemId.BLACKSTONE_WALL);

    public static ItemType<ItemBladePotterySherdStack> BLADE_POTTERY_SHERD_TYPE = empty(ItemBladePotterySherdStack.class, VanillaItemId.BLADE_POTTERY_SHERD);

    public static ItemType<ItemBlastFurnaceStack> BLAST_FURNACE_TYPE = empty(ItemBlastFurnaceStack.class, VanillaItemId.BLAST_FURNACE);

    public static ItemType<ItemBlazePowderStack> BLAZE_POWDER_TYPE = empty(ItemBlazePowderStack.class, VanillaItemId.BLAZE_POWDER);

    public static ItemType<ItemBlazeRodStack> BLAZE_ROD_TYPE = empty(ItemBlazeRodStack.class, VanillaItemId.BLAZE_ROD);

    public static ItemType<ItemBlazeSpawnEggStack> BLAZE_SPAWN_EGG_TYPE = empty(ItemBlazeSpawnEggStack.class, VanillaItemId.BLAZE_SPAWN_EGG);

    public static ItemType<ItemBleachStack> BLEACH_TYPE = empty(ItemBleachStack.class, VanillaItemId.BLEACH);

    public static ItemType<ItemBlueCandleStack> BLUE_CANDLE_TYPE = empty(ItemBlueCandleStack.class, VanillaItemId.BLUE_CANDLE);

    public static ItemType<ItemBlueCandleCakeStack> BLUE_CANDLE_CAKE_TYPE = empty(ItemBlueCandleCakeStack.class, VanillaItemId.BLUE_CANDLE_CAKE);

    public static ItemType<ItemBlueCarpetStack> BLUE_CARPET_TYPE = empty(ItemBlueCarpetStack.class, VanillaItemId.BLUE_CARPET);

    public static ItemType<ItemBlueConcreteStack> BLUE_CONCRETE_TYPE = empty(ItemBlueConcreteStack.class, VanillaItemId.BLUE_CONCRETE);

    public static ItemType<ItemBlueConcretePowderStack> BLUE_CONCRETE_POWDER_TYPE = empty(ItemBlueConcretePowderStack.class, VanillaItemId.BLUE_CONCRETE_POWDER);

    public static ItemType<ItemBlueDyeStack> BLUE_DYE_TYPE = empty(ItemBlueDyeStack.class, VanillaItemId.BLUE_DYE);

    public static ItemType<ItemBlueGlazedTerracottaStack> BLUE_GLAZED_TERRACOTTA_TYPE = empty(ItemBlueGlazedTerracottaStack.class, VanillaItemId.BLUE_GLAZED_TERRACOTTA);

    public static ItemType<ItemBlueIceStack> BLUE_ICE_TYPE = empty(ItemBlueIceStack.class, VanillaItemId.BLUE_ICE);

    public static ItemType<ItemBlueShulkerBoxStack> BLUE_SHULKER_BOX_TYPE = empty(ItemBlueShulkerBoxStack.class, VanillaItemId.BLUE_SHULKER_BOX);

    public static ItemType<ItemBlueStainedGlassStack> BLUE_STAINED_GLASS_TYPE = empty(ItemBlueStainedGlassStack.class, VanillaItemId.BLUE_STAINED_GLASS);

    public static ItemType<ItemBlueStainedGlassPaneStack> BLUE_STAINED_GLASS_PANE_TYPE = empty(ItemBlueStainedGlassPaneStack.class, VanillaItemId.BLUE_STAINED_GLASS_PANE);

    public static ItemType<ItemBlueTerracottaStack> BLUE_TERRACOTTA_TYPE = empty(ItemBlueTerracottaStack.class, VanillaItemId.BLUE_TERRACOTTA);

    public static ItemType<ItemBlueWoolStack> BLUE_WOOL_TYPE = empty(ItemBlueWoolStack.class, VanillaItemId.BLUE_WOOL);

    public static ItemType<ItemBoatStack> BOAT_TYPE = empty(ItemBoatStack.class, VanillaItemId.BOAT);

    public static ItemType<ItemBoneStack> BONE_TYPE = empty(ItemBoneStack.class, VanillaItemId.BONE);

    public static ItemType<ItemBoneBlockStack> BONE_BLOCK_TYPE = empty(ItemBoneBlockStack.class, VanillaItemId.BONE_BLOCK);

    public static ItemType<ItemBoneMealStack> BONE_MEAL_TYPE = empty(ItemBoneMealStack.class, VanillaItemId.BONE_MEAL);

    public static ItemType<ItemBookStack> BOOK_TYPE = empty(ItemBookStack.class, VanillaItemId.BOOK);

    public static ItemType<ItemBookshelfStack> BOOKSHELF_TYPE = empty(ItemBookshelfStack.class, VanillaItemId.BOOKSHELF);

    public static ItemType<ItemBorderBlockStack> BORDER_BLOCK_TYPE = empty(ItemBorderBlockStack.class, VanillaItemId.BORDER_BLOCK);

    public static ItemType<ItemBordureIndentedBannerPatternStack> BORDURE_INDENTED_BANNER_PATTERN_TYPE = empty(ItemBordureIndentedBannerPatternStack.class, VanillaItemId.BORDURE_INDENTED_BANNER_PATTERN);

    public static ItemType<ItemBowStack> BOW_TYPE = empty(ItemBowStack.class, VanillaItemId.BOW);

    public static ItemType<ItemBowlStack> BOWL_TYPE = empty(ItemBowlStack.class, VanillaItemId.BOWL);

    public static ItemType<ItemBrainCoralStack> BRAIN_CORAL_TYPE = empty(ItemBrainCoralStack.class, VanillaItemId.BRAIN_CORAL);

    public static ItemType<ItemBreadStack> BREAD_TYPE = empty(ItemBreadStack.class, VanillaItemId.BREAD);

    public static ItemType<ItemBreezeSpawnEggStack> BREEZE_SPAWN_EGG_TYPE = empty(ItemBreezeSpawnEggStack.class, VanillaItemId.BREEZE_SPAWN_EGG);

    public static ItemType<ItemBrewerPotterySherdStack> BREWER_POTTERY_SHERD_TYPE = empty(ItemBrewerPotterySherdStack.class, VanillaItemId.BREWER_POTTERY_SHERD);

    public static ItemType<ItemBrewingStandStack> BREWING_STAND_TYPE = empty(ItemBrewingStandStack.class, VanillaItemId.BREWING_STAND);

    public static ItemType<ItemBrickStack> BRICK_TYPE = empty(ItemBrickStack.class, VanillaItemId.BRICK);

    public static ItemType<ItemBrickBlockStack> BRICK_BLOCK_TYPE = empty(ItemBrickBlockStack.class, VanillaItemId.BRICK_BLOCK);

    public static ItemType<ItemBrickStairsStack> BRICK_STAIRS_TYPE = empty(ItemBrickStairsStack.class, VanillaItemId.BRICK_STAIRS);

    public static ItemType<ItemBrownCandleStack> BROWN_CANDLE_TYPE = empty(ItemBrownCandleStack.class, VanillaItemId.BROWN_CANDLE);

    public static ItemType<ItemBrownCandleCakeStack> BROWN_CANDLE_CAKE_TYPE = empty(ItemBrownCandleCakeStack.class, VanillaItemId.BROWN_CANDLE_CAKE);

    public static ItemType<ItemBrownCarpetStack> BROWN_CARPET_TYPE = empty(ItemBrownCarpetStack.class, VanillaItemId.BROWN_CARPET);

    public static ItemType<ItemBrownConcreteStack> BROWN_CONCRETE_TYPE = empty(ItemBrownConcreteStack.class, VanillaItemId.BROWN_CONCRETE);

    public static ItemType<ItemBrownConcretePowderStack> BROWN_CONCRETE_POWDER_TYPE = empty(ItemBrownConcretePowderStack.class, VanillaItemId.BROWN_CONCRETE_POWDER);

    public static ItemType<ItemBrownDyeStack> BROWN_DYE_TYPE = empty(ItemBrownDyeStack.class, VanillaItemId.BROWN_DYE);

    public static ItemType<ItemBrownGlazedTerracottaStack> BROWN_GLAZED_TERRACOTTA_TYPE = empty(ItemBrownGlazedTerracottaStack.class, VanillaItemId.BROWN_GLAZED_TERRACOTTA);

    public static ItemType<ItemBrownMushroomStack> BROWN_MUSHROOM_TYPE = empty(ItemBrownMushroomStack.class, VanillaItemId.BROWN_MUSHROOM);

    public static ItemType<ItemBrownMushroomBlockStack> BROWN_MUSHROOM_BLOCK_TYPE = empty(ItemBrownMushroomBlockStack.class, VanillaItemId.BROWN_MUSHROOM_BLOCK);

    public static ItemType<ItemBrownShulkerBoxStack> BROWN_SHULKER_BOX_TYPE = empty(ItemBrownShulkerBoxStack.class, VanillaItemId.BROWN_SHULKER_BOX);

    public static ItemType<ItemBrownStainedGlassStack> BROWN_STAINED_GLASS_TYPE = empty(ItemBrownStainedGlassStack.class, VanillaItemId.BROWN_STAINED_GLASS);

    public static ItemType<ItemBrownStainedGlassPaneStack> BROWN_STAINED_GLASS_PANE_TYPE = empty(ItemBrownStainedGlassPaneStack.class, VanillaItemId.BROWN_STAINED_GLASS_PANE);

    public static ItemType<ItemBrownTerracottaStack> BROWN_TERRACOTTA_TYPE = empty(ItemBrownTerracottaStack.class, VanillaItemId.BROWN_TERRACOTTA);

    public static ItemType<ItemBrownWoolStack> BROWN_WOOL_TYPE = empty(ItemBrownWoolStack.class, VanillaItemId.BROWN_WOOL);

    public static ItemType<ItemBrushStack> BRUSH_TYPE = empty(ItemBrushStack.class, VanillaItemId.BRUSH);

    public static ItemType<ItemBubbleColumnStack> BUBBLE_COLUMN_TYPE = empty(ItemBubbleColumnStack.class, VanillaItemId.BUBBLE_COLUMN);

    public static ItemType<ItemBubbleCoralStack> BUBBLE_CORAL_TYPE = empty(ItemBubbleCoralStack.class, VanillaItemId.BUBBLE_CORAL);

    public static ItemType<ItemBucketStack> BUCKET_TYPE = empty(ItemBucketStack.class, VanillaItemId.BUCKET);

    public static ItemType<ItemBuddingAmethystStack> BUDDING_AMETHYST_TYPE = empty(ItemBuddingAmethystStack.class, VanillaItemId.BUDDING_AMETHYST);

    public static ItemType<ItemBurnPotterySherdStack> BURN_POTTERY_SHERD_TYPE = empty(ItemBurnPotterySherdStack.class, VanillaItemId.BURN_POTTERY_SHERD);

    public static ItemType<ItemCactusStack> CACTUS_TYPE = empty(ItemCactusStack.class, VanillaItemId.CACTUS);

    public static ItemType<ItemCakeStack> CAKE_TYPE = empty(ItemCakeStack.class, VanillaItemId.CAKE);

    public static ItemType<ItemCalciteStack> CALCITE_TYPE = empty(ItemCalciteStack.class, VanillaItemId.CALCITE);

    public static ItemType<ItemCalibratedSculkSensorStack> CALIBRATED_SCULK_SENSOR_TYPE = empty(ItemCalibratedSculkSensorStack.class, VanillaItemId.CALIBRATED_SCULK_SENSOR);

    public static ItemType<ItemCamelSpawnEggStack> CAMEL_SPAWN_EGG_TYPE = empty(ItemCamelSpawnEggStack.class, VanillaItemId.CAMEL_SPAWN_EGG);

    public static ItemType<ItemCameraStack> CAMERA_TYPE = empty(ItemCameraStack.class, VanillaItemId.CAMERA);

    public static ItemType<ItemCampfireStack> CAMPFIRE_TYPE = empty(ItemCampfireStack.class, VanillaItemId.CAMPFIRE);

    public static ItemType<ItemCandleStack> CANDLE_TYPE = empty(ItemCandleStack.class, VanillaItemId.CANDLE);

    public static ItemType<ItemCandleCakeStack> CANDLE_CAKE_TYPE = empty(ItemCandleCakeStack.class, VanillaItemId.CANDLE_CAKE);

    public static ItemType<ItemCarpetStack> CARPET_TYPE = empty(ItemCarpetStack.class, VanillaItemId.CARPET);

    public static ItemType<ItemCarrotStack> CARROT_TYPE = empty(ItemCarrotStack.class, VanillaItemId.CARROT);

    public static ItemType<ItemCarrotOnAStickStack> CARROT_ON_A_STICK_TYPE = empty(ItemCarrotOnAStickStack.class, VanillaItemId.CARROT_ON_A_STICK);

    public static ItemType<ItemCarrotsStack> CARROTS_TYPE = empty(ItemCarrotsStack.class, VanillaItemId.CARROTS);

    public static ItemType<ItemCartographyTableStack> CARTOGRAPHY_TABLE_TYPE = empty(ItemCartographyTableStack.class, VanillaItemId.CARTOGRAPHY_TABLE);

    public static ItemType<ItemCarvedPumpkinStack> CARVED_PUMPKIN_TYPE = empty(ItemCarvedPumpkinStack.class, VanillaItemId.CARVED_PUMPKIN);

    public static ItemType<ItemCatSpawnEggStack> CAT_SPAWN_EGG_TYPE = empty(ItemCatSpawnEggStack.class, VanillaItemId.CAT_SPAWN_EGG);

    public static ItemType<ItemCauldronStack> CAULDRON_TYPE = empty(ItemCauldronStack.class, VanillaItemId.CAULDRON);

    public static ItemType<ItemCaveSpiderSpawnEggStack> CAVE_SPIDER_SPAWN_EGG_TYPE = empty(ItemCaveSpiderSpawnEggStack.class, VanillaItemId.CAVE_SPIDER_SPAWN_EGG);

    public static ItemType<ItemCaveVinesStack> CAVE_VINES_TYPE = empty(ItemCaveVinesStack.class, VanillaItemId.CAVE_VINES);

    public static ItemType<ItemCaveVinesBodyWithBerriesStack> CAVE_VINES_BODY_WITH_BERRIES_TYPE = empty(ItemCaveVinesBodyWithBerriesStack.class, VanillaItemId.CAVE_VINES_BODY_WITH_BERRIES);

    public static ItemType<ItemCaveVinesHeadWithBerriesStack> CAVE_VINES_HEAD_WITH_BERRIES_TYPE = empty(ItemCaveVinesHeadWithBerriesStack.class, VanillaItemId.CAVE_VINES_HEAD_WITH_BERRIES);

    public static ItemType<ItemChainStack> CHAIN_TYPE = empty(ItemChainStack.class, VanillaItemId.CHAIN);

    public static ItemType<ItemChainCommandBlockStack> CHAIN_COMMAND_BLOCK_TYPE = empty(ItemChainCommandBlockStack.class, VanillaItemId.CHAIN_COMMAND_BLOCK);

    public static ItemType<ItemChainmailBootsStack> CHAINMAIL_BOOTS_TYPE = empty(ItemChainmailBootsStack.class, VanillaItemId.CHAINMAIL_BOOTS);

    public static ItemType<ItemChainmailChestplateStack> CHAINMAIL_CHESTPLATE_TYPE = empty(ItemChainmailChestplateStack.class, VanillaItemId.CHAINMAIL_CHESTPLATE);

    public static ItemType<ItemChainmailHelmetStack> CHAINMAIL_HELMET_TYPE = empty(ItemChainmailHelmetStack.class, VanillaItemId.CHAINMAIL_HELMET);

    public static ItemType<ItemChainmailLeggingsStack> CHAINMAIL_LEGGINGS_TYPE = empty(ItemChainmailLeggingsStack.class, VanillaItemId.CHAINMAIL_LEGGINGS);

    public static ItemType<ItemCharcoalStack> CHARCOAL_TYPE = empty(ItemCharcoalStack.class, VanillaItemId.CHARCOAL);

    public static ItemType<ItemChemicalHeatStack> CHEMICAL_HEAT_TYPE = empty(ItemChemicalHeatStack.class, VanillaItemId.CHEMICAL_HEAT);

    public static ItemType<ItemChemistryTableStack> CHEMISTRY_TABLE_TYPE = empty(ItemChemistryTableStack.class, VanillaItemId.CHEMISTRY_TABLE);

    public static ItemType<ItemCherryBoatStack> CHERRY_BOAT_TYPE = empty(ItemCherryBoatStack.class, VanillaItemId.CHERRY_BOAT);

    public static ItemType<ItemCherryButtonStack> CHERRY_BUTTON_TYPE = empty(ItemCherryButtonStack.class, VanillaItemId.CHERRY_BUTTON);

    public static ItemType<ItemCherryChestBoatStack> CHERRY_CHEST_BOAT_TYPE = empty(ItemCherryChestBoatStack.class, VanillaItemId.CHERRY_CHEST_BOAT);

    public static ItemType<ItemCherryDoorStack> CHERRY_DOOR_TYPE = empty(ItemCherryDoorStack.class, VanillaItemId.CHERRY_DOOR);

    public static ItemType<ItemCherryDoubleSlabStack> CHERRY_DOUBLE_SLAB_TYPE = empty(ItemCherryDoubleSlabStack.class, VanillaItemId.CHERRY_DOUBLE_SLAB);

    public static ItemType<ItemCherryFenceStack> CHERRY_FENCE_TYPE = empty(ItemCherryFenceStack.class, VanillaItemId.CHERRY_FENCE);

    public static ItemType<ItemCherryFenceGateStack> CHERRY_FENCE_GATE_TYPE = empty(ItemCherryFenceGateStack.class, VanillaItemId.CHERRY_FENCE_GATE);

    public static ItemType<ItemCherryHangingSignStack> CHERRY_HANGING_SIGN_TYPE = empty(ItemCherryHangingSignStack.class, VanillaItemId.CHERRY_HANGING_SIGN);

    public static ItemType<ItemCherryLeavesStack> CHERRY_LEAVES_TYPE = empty(ItemCherryLeavesStack.class, VanillaItemId.CHERRY_LEAVES);

    public static ItemType<ItemCherryLogStack> CHERRY_LOG_TYPE = empty(ItemCherryLogStack.class, VanillaItemId.CHERRY_LOG);

    public static ItemType<ItemCherryPlanksStack> CHERRY_PLANKS_TYPE = empty(ItemCherryPlanksStack.class, VanillaItemId.CHERRY_PLANKS);

    public static ItemType<ItemCherryPressurePlateStack> CHERRY_PRESSURE_PLATE_TYPE = empty(ItemCherryPressurePlateStack.class, VanillaItemId.CHERRY_PRESSURE_PLATE);

    public static ItemType<ItemCherrySaplingStack> CHERRY_SAPLING_TYPE = empty(ItemCherrySaplingStack.class, VanillaItemId.CHERRY_SAPLING);

    public static ItemType<ItemCherrySignStack> CHERRY_SIGN_TYPE = empty(ItemCherrySignStack.class, VanillaItemId.CHERRY_SIGN);

    public static ItemType<ItemCherrySlabStack> CHERRY_SLAB_TYPE = empty(ItemCherrySlabStack.class, VanillaItemId.CHERRY_SLAB);

    public static ItemType<ItemCherryStairsStack> CHERRY_STAIRS_TYPE = empty(ItemCherryStairsStack.class, VanillaItemId.CHERRY_STAIRS);

    public static ItemType<ItemCherryStandingSignStack> CHERRY_STANDING_SIGN_TYPE = empty(ItemCherryStandingSignStack.class, VanillaItemId.CHERRY_STANDING_SIGN);

    public static ItemType<ItemCherryTrapdoorStack> CHERRY_TRAPDOOR_TYPE = empty(ItemCherryTrapdoorStack.class, VanillaItemId.CHERRY_TRAPDOOR);

    public static ItemType<ItemCherryWallSignStack> CHERRY_WALL_SIGN_TYPE = empty(ItemCherryWallSignStack.class, VanillaItemId.CHERRY_WALL_SIGN);

    public static ItemType<ItemCherryWoodStack> CHERRY_WOOD_TYPE = empty(ItemCherryWoodStack.class, VanillaItemId.CHERRY_WOOD);

    public static ItemType<ItemChestStack> CHEST_TYPE = empty(ItemChestStack.class, VanillaItemId.CHEST);

    public static ItemType<ItemChestBoatStack> CHEST_BOAT_TYPE = empty(ItemChestBoatStack.class, VanillaItemId.CHEST_BOAT);

    public static ItemType<ItemChestMinecartStack> CHEST_MINECART_TYPE = empty(ItemChestMinecartStack.class, VanillaItemId.CHEST_MINECART);

    public static ItemType<ItemChickenStack> CHICKEN_TYPE = empty(ItemChickenStack.class, VanillaItemId.CHICKEN);

    public static ItemType<ItemChickenSpawnEggStack> CHICKEN_SPAWN_EGG_TYPE = empty(ItemChickenSpawnEggStack.class, VanillaItemId.CHICKEN_SPAWN_EGG);

    public static ItemType<ItemChiseledBookshelfStack> CHISELED_BOOKSHELF_TYPE = empty(ItemChiseledBookshelfStack.class, VanillaItemId.CHISELED_BOOKSHELF);

    public static ItemType<ItemChiseledCopperStack> CHISELED_COPPER_TYPE = empty(ItemChiseledCopperStack.class, VanillaItemId.CHISELED_COPPER);

    public static ItemType<ItemChiseledDeepslateStack> CHISELED_DEEPSLATE_TYPE = empty(ItemChiseledDeepslateStack.class, VanillaItemId.CHISELED_DEEPSLATE);

    public static ItemType<ItemChiseledNetherBricksStack> CHISELED_NETHER_BRICKS_TYPE = empty(ItemChiseledNetherBricksStack.class, VanillaItemId.CHISELED_NETHER_BRICKS);

    public static ItemType<ItemChiseledPolishedBlackstoneStack> CHISELED_POLISHED_BLACKSTONE_TYPE = empty(ItemChiseledPolishedBlackstoneStack.class, VanillaItemId.CHISELED_POLISHED_BLACKSTONE);

    public static ItemType<ItemChiseledTuffStack> CHISELED_TUFF_TYPE = empty(ItemChiseledTuffStack.class, VanillaItemId.CHISELED_TUFF);

    public static ItemType<ItemChiseledTuffBricksStack> CHISELED_TUFF_BRICKS_TYPE = empty(ItemChiseledTuffBricksStack.class, VanillaItemId.CHISELED_TUFF_BRICKS);

    public static ItemType<ItemChorusFlowerStack> CHORUS_FLOWER_TYPE = empty(ItemChorusFlowerStack.class, VanillaItemId.CHORUS_FLOWER);

    public static ItemType<ItemChorusFruitStack> CHORUS_FRUIT_TYPE = empty(ItemChorusFruitStack.class, VanillaItemId.CHORUS_FRUIT);

    public static ItemType<ItemChorusPlantStack> CHORUS_PLANT_TYPE = empty(ItemChorusPlantStack.class, VanillaItemId.CHORUS_PLANT);

    public static ItemType<ItemClayStack> CLAY_TYPE = empty(ItemClayStack.class, VanillaItemId.CLAY);

    public static ItemType<ItemClayBallStack> CLAY_BALL_TYPE = empty(ItemClayBallStack.class, VanillaItemId.CLAY_BALL);

    public static ItemType<ItemClientRequestPlaceholderBlockStack> CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE = empty(ItemClientRequestPlaceholderBlockStack.class, VanillaItemId.CLIENT_REQUEST_PLACEHOLDER_BLOCK);

    public static ItemType<ItemClockStack> CLOCK_TYPE = empty(ItemClockStack.class, VanillaItemId.CLOCK);

    public static ItemType<ItemCoalStack> COAL_TYPE = empty(ItemCoalStack.class, VanillaItemId.COAL);

    public static ItemType<ItemCoalBlockStack> COAL_BLOCK_TYPE = empty(ItemCoalBlockStack.class, VanillaItemId.COAL_BLOCK);

    public static ItemType<ItemCoalOreStack> COAL_ORE_TYPE = empty(ItemCoalOreStack.class, VanillaItemId.COAL_ORE);

    public static ItemType<ItemCoastArmorTrimSmithingTemplateStack> COAST_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemCoastArmorTrimSmithingTemplateStack.class, VanillaItemId.COAST_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemCobbledDeepslateStack> COBBLED_DEEPSLATE_TYPE = empty(ItemCobbledDeepslateStack.class, VanillaItemId.COBBLED_DEEPSLATE);

    public static ItemType<ItemCobbledDeepslateDoubleSlabStack> COBBLED_DEEPSLATE_DOUBLE_SLAB_TYPE = empty(ItemCobbledDeepslateDoubleSlabStack.class, VanillaItemId.COBBLED_DEEPSLATE_DOUBLE_SLAB);

    public static ItemType<ItemCobbledDeepslateSlabStack> COBBLED_DEEPSLATE_SLAB_TYPE = empty(ItemCobbledDeepslateSlabStack.class, VanillaItemId.COBBLED_DEEPSLATE_SLAB);

    public static ItemType<ItemCobbledDeepslateStairsStack> COBBLED_DEEPSLATE_STAIRS_TYPE = empty(ItemCobbledDeepslateStairsStack.class, VanillaItemId.COBBLED_DEEPSLATE_STAIRS);

    public static ItemType<ItemCobbledDeepslateWallStack> COBBLED_DEEPSLATE_WALL_TYPE = empty(ItemCobbledDeepslateWallStack.class, VanillaItemId.COBBLED_DEEPSLATE_WALL);

    public static ItemType<ItemCobblestoneStack> COBBLESTONE_TYPE = empty(ItemCobblestoneStack.class, VanillaItemId.COBBLESTONE);

    public static ItemType<ItemCobblestoneWallStack> COBBLESTONE_WALL_TYPE = empty(ItemCobblestoneWallStack.class, VanillaItemId.COBBLESTONE_WALL);

    public static ItemType<ItemCocoaStack> COCOA_TYPE = empty(ItemCocoaStack.class, VanillaItemId.COCOA);

    public static ItemType<ItemCocoaBeansStack> COCOA_BEANS_TYPE = empty(ItemCocoaBeansStack.class, VanillaItemId.COCOA_BEANS);

    public static ItemType<ItemCodStack> COD_TYPE = empty(ItemCodStack.class, VanillaItemId.COD);

    public static ItemType<ItemCodBucketStack> COD_BUCKET_TYPE = empty(ItemCodBucketStack.class, VanillaItemId.COD_BUCKET);

    public static ItemType<ItemCodSpawnEggStack> COD_SPAWN_EGG_TYPE = empty(ItemCodSpawnEggStack.class, VanillaItemId.COD_SPAWN_EGG);

    public static ItemType<ItemColoredTorchBpStack> COLORED_TORCH_BP_TYPE = empty(ItemColoredTorchBpStack.class, VanillaItemId.COLORED_TORCH_BP);

    public static ItemType<ItemColoredTorchRgStack> COLORED_TORCH_RG_TYPE = empty(ItemColoredTorchRgStack.class, VanillaItemId.COLORED_TORCH_RG);

    public static ItemType<ItemCommandBlockStack> COMMAND_BLOCK_TYPE = empty(ItemCommandBlockStack.class, VanillaItemId.COMMAND_BLOCK);

    public static ItemType<ItemCommandBlockMinecartStack> COMMAND_BLOCK_MINECART_TYPE = empty(ItemCommandBlockMinecartStack.class, VanillaItemId.COMMAND_BLOCK_MINECART);

    public static ItemType<ItemComparatorStack> COMPARATOR_TYPE = empty(ItemComparatorStack.class, VanillaItemId.COMPARATOR);

    public static ItemType<ItemCompassStack> COMPASS_TYPE = empty(ItemCompassStack.class, VanillaItemId.COMPASS);

    public static ItemType<ItemComposterStack> COMPOSTER_TYPE = empty(ItemComposterStack.class, VanillaItemId.COMPOSTER);

    public static ItemType<ItemCompoundStack> COMPOUND_TYPE = empty(ItemCompoundStack.class, VanillaItemId.COMPOUND);

    public static ItemType<ItemConcreteStack> CONCRETE_TYPE = empty(ItemConcreteStack.class, VanillaItemId.CONCRETE);

    public static ItemType<ItemConcretePowderStack> CONCRETE_POWDER_TYPE = empty(ItemConcretePowderStack.class, VanillaItemId.CONCRETE_POWDER);

    public static ItemType<ItemConduitStack> CONDUIT_TYPE = empty(ItemConduitStack.class, VanillaItemId.CONDUIT);

    public static ItemType<ItemCookedBeefStack> COOKED_BEEF_TYPE = empty(ItemCookedBeefStack.class, VanillaItemId.COOKED_BEEF);

    public static ItemType<ItemCookedChickenStack> COOKED_CHICKEN_TYPE = empty(ItemCookedChickenStack.class, VanillaItemId.COOKED_CHICKEN);

    public static ItemType<ItemCookedCodStack> COOKED_COD_TYPE = empty(ItemCookedCodStack.class, VanillaItemId.COOKED_COD);

    public static ItemType<ItemCookedMuttonStack> COOKED_MUTTON_TYPE = empty(ItemCookedMuttonStack.class, VanillaItemId.COOKED_MUTTON);

    public static ItemType<ItemCookedPorkchopStack> COOKED_PORKCHOP_TYPE = empty(ItemCookedPorkchopStack.class, VanillaItemId.COOKED_PORKCHOP);

    public static ItemType<ItemCookedRabbitStack> COOKED_RABBIT_TYPE = empty(ItemCookedRabbitStack.class, VanillaItemId.COOKED_RABBIT);

    public static ItemType<ItemCookedSalmonStack> COOKED_SALMON_TYPE = empty(ItemCookedSalmonStack.class, VanillaItemId.COOKED_SALMON);

    public static ItemType<ItemCookieStack> COOKIE_TYPE = empty(ItemCookieStack.class, VanillaItemId.COOKIE);

    public static ItemType<ItemCopperBlockStack> COPPER_BLOCK_TYPE = empty(ItemCopperBlockStack.class, VanillaItemId.COPPER_BLOCK);

    public static ItemType<ItemCopperBulbStack> COPPER_BULB_TYPE = empty(ItemCopperBulbStack.class, VanillaItemId.COPPER_BULB);

    public static ItemType<ItemCopperDoorStack> COPPER_DOOR_TYPE = empty(ItemCopperDoorStack.class, VanillaItemId.COPPER_DOOR);

    public static ItemType<ItemCopperGrateStack> COPPER_GRATE_TYPE = empty(ItemCopperGrateStack.class, VanillaItemId.COPPER_GRATE);

    public static ItemType<ItemCopperIngotStack> COPPER_INGOT_TYPE = empty(ItemCopperIngotStack.class, VanillaItemId.COPPER_INGOT);

    public static ItemType<ItemCopperOreStack> COPPER_ORE_TYPE = empty(ItemCopperOreStack.class, VanillaItemId.COPPER_ORE);

    public static ItemType<ItemCopperTrapdoorStack> COPPER_TRAPDOOR_TYPE = empty(ItemCopperTrapdoorStack.class, VanillaItemId.COPPER_TRAPDOOR);

    public static ItemType<ItemCoralStack> CORAL_TYPE = empty(ItemCoralStack.class, VanillaItemId.CORAL);

    public static ItemType<ItemCoralBlockStack> CORAL_BLOCK_TYPE = empty(ItemCoralBlockStack.class, VanillaItemId.CORAL_BLOCK);

    public static ItemType<ItemCoralFanStack> CORAL_FAN_TYPE = empty(ItemCoralFanStack.class, VanillaItemId.CORAL_FAN);

    public static ItemType<ItemCoralFanDeadStack> CORAL_FAN_DEAD_TYPE = empty(ItemCoralFanDeadStack.class, VanillaItemId.CORAL_FAN_DEAD);

    public static ItemType<ItemCoralFanHangStack> CORAL_FAN_HANG_TYPE = empty(ItemCoralFanHangStack.class, VanillaItemId.CORAL_FAN_HANG);

    public static ItemType<ItemCoralFanHang2Stack> CORAL_FAN_HANG2_TYPE = empty(ItemCoralFanHang2Stack.class, VanillaItemId.CORAL_FAN_HANG2);

    public static ItemType<ItemCoralFanHang3Stack> CORAL_FAN_HANG3_TYPE = empty(ItemCoralFanHang3Stack.class, VanillaItemId.CORAL_FAN_HANG3);

    public static ItemType<ItemCowSpawnEggStack> COW_SPAWN_EGG_TYPE = empty(ItemCowSpawnEggStack.class, VanillaItemId.COW_SPAWN_EGG);

    public static ItemType<ItemCrackedDeepslateBricksStack> CRACKED_DEEPSLATE_BRICKS_TYPE = empty(ItemCrackedDeepslateBricksStack.class, VanillaItemId.CRACKED_DEEPSLATE_BRICKS);

    public static ItemType<ItemCrackedDeepslateTilesStack> CRACKED_DEEPSLATE_TILES_TYPE = empty(ItemCrackedDeepslateTilesStack.class, VanillaItemId.CRACKED_DEEPSLATE_TILES);

    public static ItemType<ItemCrackedNetherBricksStack> CRACKED_NETHER_BRICKS_TYPE = empty(ItemCrackedNetherBricksStack.class, VanillaItemId.CRACKED_NETHER_BRICKS);

    public static ItemType<ItemCrackedPolishedBlackstoneBricksStack> CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE = empty(ItemCrackedPolishedBlackstoneBricksStack.class, VanillaItemId.CRACKED_POLISHED_BLACKSTONE_BRICKS);

    public static ItemType<ItemCrafterStack> CRAFTER_TYPE = empty(ItemCrafterStack.class, VanillaItemId.CRAFTER);

    public static ItemType<ItemCraftingTableStack> CRAFTING_TABLE_TYPE = empty(ItemCraftingTableStack.class, VanillaItemId.CRAFTING_TABLE);

    public static ItemType<ItemCreeperBannerPatternStack> CREEPER_BANNER_PATTERN_TYPE = empty(ItemCreeperBannerPatternStack.class, VanillaItemId.CREEPER_BANNER_PATTERN);

    public static ItemType<ItemCreeperSpawnEggStack> CREEPER_SPAWN_EGG_TYPE = empty(ItemCreeperSpawnEggStack.class, VanillaItemId.CREEPER_SPAWN_EGG);

    public static ItemType<ItemCrimsonButtonStack> CRIMSON_BUTTON_TYPE = empty(ItemCrimsonButtonStack.class, VanillaItemId.CRIMSON_BUTTON);

    public static ItemType<ItemCrimsonDoorStack> CRIMSON_DOOR_TYPE = empty(ItemCrimsonDoorStack.class, VanillaItemId.CRIMSON_DOOR);

    public static ItemType<ItemCrimsonDoubleSlabStack> CRIMSON_DOUBLE_SLAB_TYPE = empty(ItemCrimsonDoubleSlabStack.class, VanillaItemId.CRIMSON_DOUBLE_SLAB);

    public static ItemType<ItemCrimsonFenceStack> CRIMSON_FENCE_TYPE = empty(ItemCrimsonFenceStack.class, VanillaItemId.CRIMSON_FENCE);

    public static ItemType<ItemCrimsonFenceGateStack> CRIMSON_FENCE_GATE_TYPE = empty(ItemCrimsonFenceGateStack.class, VanillaItemId.CRIMSON_FENCE_GATE);

    public static ItemType<ItemCrimsonFungusStack> CRIMSON_FUNGUS_TYPE = empty(ItemCrimsonFungusStack.class, VanillaItemId.CRIMSON_FUNGUS);

    public static ItemType<ItemCrimsonHangingSignStack> CRIMSON_HANGING_SIGN_TYPE = empty(ItemCrimsonHangingSignStack.class, VanillaItemId.CRIMSON_HANGING_SIGN);

    public static ItemType<ItemCrimsonHyphaeStack> CRIMSON_HYPHAE_TYPE = empty(ItemCrimsonHyphaeStack.class, VanillaItemId.CRIMSON_HYPHAE);

    public static ItemType<ItemCrimsonNyliumStack> CRIMSON_NYLIUM_TYPE = empty(ItemCrimsonNyliumStack.class, VanillaItemId.CRIMSON_NYLIUM);

    public static ItemType<ItemCrimsonPlanksStack> CRIMSON_PLANKS_TYPE = empty(ItemCrimsonPlanksStack.class, VanillaItemId.CRIMSON_PLANKS);

    public static ItemType<ItemCrimsonPressurePlateStack> CRIMSON_PRESSURE_PLATE_TYPE = empty(ItemCrimsonPressurePlateStack.class, VanillaItemId.CRIMSON_PRESSURE_PLATE);

    public static ItemType<ItemCrimsonRootsStack> CRIMSON_ROOTS_TYPE = empty(ItemCrimsonRootsStack.class, VanillaItemId.CRIMSON_ROOTS);

    public static ItemType<ItemCrimsonSignStack> CRIMSON_SIGN_TYPE = empty(ItemCrimsonSignStack.class, VanillaItemId.CRIMSON_SIGN);

    public static ItemType<ItemCrimsonSlabStack> CRIMSON_SLAB_TYPE = empty(ItemCrimsonSlabStack.class, VanillaItemId.CRIMSON_SLAB);

    public static ItemType<ItemCrimsonStairsStack> CRIMSON_STAIRS_TYPE = empty(ItemCrimsonStairsStack.class, VanillaItemId.CRIMSON_STAIRS);

    public static ItemType<ItemCrimsonStandingSignStack> CRIMSON_STANDING_SIGN_TYPE = empty(ItemCrimsonStandingSignStack.class, VanillaItemId.CRIMSON_STANDING_SIGN);

    public static ItemType<ItemCrimsonStemStack> CRIMSON_STEM_TYPE = empty(ItemCrimsonStemStack.class, VanillaItemId.CRIMSON_STEM);

    public static ItemType<ItemCrimsonTrapdoorStack> CRIMSON_TRAPDOOR_TYPE = empty(ItemCrimsonTrapdoorStack.class, VanillaItemId.CRIMSON_TRAPDOOR);

    public static ItemType<ItemCrimsonWallSignStack> CRIMSON_WALL_SIGN_TYPE = empty(ItemCrimsonWallSignStack.class, VanillaItemId.CRIMSON_WALL_SIGN);

    public static ItemType<ItemCrossbowStack> CROSSBOW_TYPE = empty(ItemCrossbowStack.class, VanillaItemId.CROSSBOW);

    public static ItemType<ItemCryingObsidianStack> CRYING_OBSIDIAN_TYPE = empty(ItemCryingObsidianStack.class, VanillaItemId.CRYING_OBSIDIAN);

    public static ItemType<ItemCutCopperStack> CUT_COPPER_TYPE = empty(ItemCutCopperStack.class, VanillaItemId.CUT_COPPER);

    public static ItemType<ItemCutCopperSlabStack> CUT_COPPER_SLAB_TYPE = empty(ItemCutCopperSlabStack.class, VanillaItemId.CUT_COPPER_SLAB);

    public static ItemType<ItemCutCopperStairsStack> CUT_COPPER_STAIRS_TYPE = empty(ItemCutCopperStairsStack.class, VanillaItemId.CUT_COPPER_STAIRS);

    public static ItemType<ItemCyanCandleStack> CYAN_CANDLE_TYPE = empty(ItemCyanCandleStack.class, VanillaItemId.CYAN_CANDLE);

    public static ItemType<ItemCyanCandleCakeStack> CYAN_CANDLE_CAKE_TYPE = empty(ItemCyanCandleCakeStack.class, VanillaItemId.CYAN_CANDLE_CAKE);

    public static ItemType<ItemCyanCarpetStack> CYAN_CARPET_TYPE = empty(ItemCyanCarpetStack.class, VanillaItemId.CYAN_CARPET);

    public static ItemType<ItemCyanConcreteStack> CYAN_CONCRETE_TYPE = empty(ItemCyanConcreteStack.class, VanillaItemId.CYAN_CONCRETE);

    public static ItemType<ItemCyanConcretePowderStack> CYAN_CONCRETE_POWDER_TYPE = empty(ItemCyanConcretePowderStack.class, VanillaItemId.CYAN_CONCRETE_POWDER);

    public static ItemType<ItemCyanDyeStack> CYAN_DYE_TYPE = empty(ItemCyanDyeStack.class, VanillaItemId.CYAN_DYE);

    public static ItemType<ItemCyanGlazedTerracottaStack> CYAN_GLAZED_TERRACOTTA_TYPE = empty(ItemCyanGlazedTerracottaStack.class, VanillaItemId.CYAN_GLAZED_TERRACOTTA);

    public static ItemType<ItemCyanShulkerBoxStack> CYAN_SHULKER_BOX_TYPE = empty(ItemCyanShulkerBoxStack.class, VanillaItemId.CYAN_SHULKER_BOX);

    public static ItemType<ItemCyanStainedGlassStack> CYAN_STAINED_GLASS_TYPE = empty(ItemCyanStainedGlassStack.class, VanillaItemId.CYAN_STAINED_GLASS);

    public static ItemType<ItemCyanStainedGlassPaneStack> CYAN_STAINED_GLASS_PANE_TYPE = empty(ItemCyanStainedGlassPaneStack.class, VanillaItemId.CYAN_STAINED_GLASS_PANE);

    public static ItemType<ItemCyanTerracottaStack> CYAN_TERRACOTTA_TYPE = empty(ItemCyanTerracottaStack.class, VanillaItemId.CYAN_TERRACOTTA);

    public static ItemType<ItemCyanWoolStack> CYAN_WOOL_TYPE = empty(ItemCyanWoolStack.class, VanillaItemId.CYAN_WOOL);

    public static ItemType<ItemDangerPotterySherdStack> DANGER_POTTERY_SHERD_TYPE = empty(ItemDangerPotterySherdStack.class, VanillaItemId.DANGER_POTTERY_SHERD);

    public static ItemType<ItemDarkOakBoatStack> DARK_OAK_BOAT_TYPE = empty(ItemDarkOakBoatStack.class, VanillaItemId.DARK_OAK_BOAT);

    public static ItemType<ItemDarkOakButtonStack> DARK_OAK_BUTTON_TYPE = empty(ItemDarkOakButtonStack.class, VanillaItemId.DARK_OAK_BUTTON);

    public static ItemType<ItemDarkOakChestBoatStack> DARK_OAK_CHEST_BOAT_TYPE = empty(ItemDarkOakChestBoatStack.class, VanillaItemId.DARK_OAK_CHEST_BOAT);

    public static ItemType<ItemDarkOakDoorStack> DARK_OAK_DOOR_TYPE = empty(ItemDarkOakDoorStack.class, VanillaItemId.DARK_OAK_DOOR);

    public static ItemType<ItemDarkOakFenceStack> DARK_OAK_FENCE_TYPE = empty(ItemDarkOakFenceStack.class, VanillaItemId.DARK_OAK_FENCE);

    public static ItemType<ItemDarkOakFenceGateStack> DARK_OAK_FENCE_GATE_TYPE = empty(ItemDarkOakFenceGateStack.class, VanillaItemId.DARK_OAK_FENCE_GATE);

    public static ItemType<ItemDarkOakHangingSignStack> DARK_OAK_HANGING_SIGN_TYPE = empty(ItemDarkOakHangingSignStack.class, VanillaItemId.DARK_OAK_HANGING_SIGN);

    public static ItemType<ItemDarkOakLogStack> DARK_OAK_LOG_TYPE = empty(ItemDarkOakLogStack.class, VanillaItemId.DARK_OAK_LOG);

    public static ItemType<ItemDarkOakPlanksStack> DARK_OAK_PLANKS_TYPE = empty(ItemDarkOakPlanksStack.class, VanillaItemId.DARK_OAK_PLANKS);

    public static ItemType<ItemDarkOakPressurePlateStack> DARK_OAK_PRESSURE_PLATE_TYPE = empty(ItemDarkOakPressurePlateStack.class, VanillaItemId.DARK_OAK_PRESSURE_PLATE);

    public static ItemType<ItemDarkOakSignStack> DARK_OAK_SIGN_TYPE = empty(ItemDarkOakSignStack.class, VanillaItemId.DARK_OAK_SIGN);

    public static ItemType<ItemDarkOakStairsStack> DARK_OAK_STAIRS_TYPE = empty(ItemDarkOakStairsStack.class, VanillaItemId.DARK_OAK_STAIRS);

    public static ItemType<ItemDarkOakTrapdoorStack> DARK_OAK_TRAPDOOR_TYPE = empty(ItemDarkOakTrapdoorStack.class, VanillaItemId.DARK_OAK_TRAPDOOR);

    public static ItemType<ItemDarkPrismarineStairsStack> DARK_PRISMARINE_STAIRS_TYPE = empty(ItemDarkPrismarineStairsStack.class, VanillaItemId.DARK_PRISMARINE_STAIRS);

    public static ItemType<ItemDarkoakStandingSignStack> DARKOAK_STANDING_SIGN_TYPE = empty(ItemDarkoakStandingSignStack.class, VanillaItemId.DARKOAK_STANDING_SIGN);

    public static ItemType<ItemDarkoakWallSignStack> DARKOAK_WALL_SIGN_TYPE = empty(ItemDarkoakWallSignStack.class, VanillaItemId.DARKOAK_WALL_SIGN);

    public static ItemType<ItemDaylightDetectorStack> DAYLIGHT_DETECTOR_TYPE = empty(ItemDaylightDetectorStack.class, VanillaItemId.DAYLIGHT_DETECTOR);

    public static ItemType<ItemDaylightDetectorInvertedStack> DAYLIGHT_DETECTOR_INVERTED_TYPE = empty(ItemDaylightDetectorInvertedStack.class, VanillaItemId.DAYLIGHT_DETECTOR_INVERTED);

    public static ItemType<ItemDeadBrainCoralStack> DEAD_BRAIN_CORAL_TYPE = empty(ItemDeadBrainCoralStack.class, VanillaItemId.DEAD_BRAIN_CORAL);

    public static ItemType<ItemDeadBubbleCoralStack> DEAD_BUBBLE_CORAL_TYPE = empty(ItemDeadBubbleCoralStack.class, VanillaItemId.DEAD_BUBBLE_CORAL);

    public static ItemType<ItemDeadFireCoralStack> DEAD_FIRE_CORAL_TYPE = empty(ItemDeadFireCoralStack.class, VanillaItemId.DEAD_FIRE_CORAL);

    public static ItemType<ItemDeadHornCoralStack> DEAD_HORN_CORAL_TYPE = empty(ItemDeadHornCoralStack.class, VanillaItemId.DEAD_HORN_CORAL);

    public static ItemType<ItemDeadTubeCoralStack> DEAD_TUBE_CORAL_TYPE = empty(ItemDeadTubeCoralStack.class, VanillaItemId.DEAD_TUBE_CORAL);

    public static ItemType<ItemDeadbushStack> DEADBUSH_TYPE = empty(ItemDeadbushStack.class, VanillaItemId.DEADBUSH);

    public static ItemType<ItemDecoratedPotStack> DECORATED_POT_TYPE = empty(ItemDecoratedPotStack.class, VanillaItemId.DECORATED_POT);

    public static ItemType<ItemDeepslateStack> DEEPSLATE_TYPE = empty(ItemDeepslateStack.class, VanillaItemId.DEEPSLATE);

    public static ItemType<ItemDeepslateBrickDoubleSlabStack> DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE = empty(ItemDeepslateBrickDoubleSlabStack.class, VanillaItemId.DEEPSLATE_BRICK_DOUBLE_SLAB);

    public static ItemType<ItemDeepslateBrickSlabStack> DEEPSLATE_BRICK_SLAB_TYPE = empty(ItemDeepslateBrickSlabStack.class, VanillaItemId.DEEPSLATE_BRICK_SLAB);

    public static ItemType<ItemDeepslateBrickStairsStack> DEEPSLATE_BRICK_STAIRS_TYPE = empty(ItemDeepslateBrickStairsStack.class, VanillaItemId.DEEPSLATE_BRICK_STAIRS);

    public static ItemType<ItemDeepslateBrickWallStack> DEEPSLATE_BRICK_WALL_TYPE = empty(ItemDeepslateBrickWallStack.class, VanillaItemId.DEEPSLATE_BRICK_WALL);

    public static ItemType<ItemDeepslateBricksStack> DEEPSLATE_BRICKS_TYPE = empty(ItemDeepslateBricksStack.class, VanillaItemId.DEEPSLATE_BRICKS);

    public static ItemType<ItemDeepslateCoalOreStack> DEEPSLATE_COAL_ORE_TYPE = empty(ItemDeepslateCoalOreStack.class, VanillaItemId.DEEPSLATE_COAL_ORE);

    public static ItemType<ItemDeepslateCopperOreStack> DEEPSLATE_COPPER_ORE_TYPE = empty(ItemDeepslateCopperOreStack.class, VanillaItemId.DEEPSLATE_COPPER_ORE);

    public static ItemType<ItemDeepslateDiamondOreStack> DEEPSLATE_DIAMOND_ORE_TYPE = empty(ItemDeepslateDiamondOreStack.class, VanillaItemId.DEEPSLATE_DIAMOND_ORE);

    public static ItemType<ItemDeepslateEmeraldOreStack> DEEPSLATE_EMERALD_ORE_TYPE = empty(ItemDeepslateEmeraldOreStack.class, VanillaItemId.DEEPSLATE_EMERALD_ORE);

    public static ItemType<ItemDeepslateGoldOreStack> DEEPSLATE_GOLD_ORE_TYPE = empty(ItemDeepslateGoldOreStack.class, VanillaItemId.DEEPSLATE_GOLD_ORE);

    public static ItemType<ItemDeepslateIronOreStack> DEEPSLATE_IRON_ORE_TYPE = empty(ItemDeepslateIronOreStack.class, VanillaItemId.DEEPSLATE_IRON_ORE);

    public static ItemType<ItemDeepslateLapisOreStack> DEEPSLATE_LAPIS_ORE_TYPE = empty(ItemDeepslateLapisOreStack.class, VanillaItemId.DEEPSLATE_LAPIS_ORE);

    public static ItemType<ItemDeepslateRedstoneOreStack> DEEPSLATE_REDSTONE_ORE_TYPE = empty(ItemDeepslateRedstoneOreStack.class, VanillaItemId.DEEPSLATE_REDSTONE_ORE);

    public static ItemType<ItemDeepslateTileDoubleSlabStack> DEEPSLATE_TILE_DOUBLE_SLAB_TYPE = empty(ItemDeepslateTileDoubleSlabStack.class, VanillaItemId.DEEPSLATE_TILE_DOUBLE_SLAB);

    public static ItemType<ItemDeepslateTileSlabStack> DEEPSLATE_TILE_SLAB_TYPE = empty(ItemDeepslateTileSlabStack.class, VanillaItemId.DEEPSLATE_TILE_SLAB);

    public static ItemType<ItemDeepslateTileStairsStack> DEEPSLATE_TILE_STAIRS_TYPE = empty(ItemDeepslateTileStairsStack.class, VanillaItemId.DEEPSLATE_TILE_STAIRS);

    public static ItemType<ItemDeepslateTileWallStack> DEEPSLATE_TILE_WALL_TYPE = empty(ItemDeepslateTileWallStack.class, VanillaItemId.DEEPSLATE_TILE_WALL);

    public static ItemType<ItemDeepslateTilesStack> DEEPSLATE_TILES_TYPE = empty(ItemDeepslateTilesStack.class, VanillaItemId.DEEPSLATE_TILES);

    public static ItemType<ItemDenyStack> DENY_TYPE = empty(ItemDenyStack.class, VanillaItemId.DENY);

    public static ItemType<ItemDetectorRailStack> DETECTOR_RAIL_TYPE = empty(ItemDetectorRailStack.class, VanillaItemId.DETECTOR_RAIL);

    public static ItemType<ItemDiamondStack> DIAMOND_TYPE = empty(ItemDiamondStack.class, VanillaItemId.DIAMOND);

    public static ItemType<ItemDiamondAxeStack> DIAMOND_AXE_TYPE = empty(ItemDiamondAxeStack.class, VanillaItemId.DIAMOND_AXE);

    public static ItemType<ItemDiamondBlockStack> DIAMOND_BLOCK_TYPE = empty(ItemDiamondBlockStack.class, VanillaItemId.DIAMOND_BLOCK);

    public static ItemType<ItemDiamondBootsStack> DIAMOND_BOOTS_TYPE = empty(ItemDiamondBootsStack.class, VanillaItemId.DIAMOND_BOOTS);

    public static ItemType<ItemDiamondChestplateStack> DIAMOND_CHESTPLATE_TYPE = empty(ItemDiamondChestplateStack.class, VanillaItemId.DIAMOND_CHESTPLATE);

    public static ItemType<ItemDiamondHelmetStack> DIAMOND_HELMET_TYPE = empty(ItemDiamondHelmetStack.class, VanillaItemId.DIAMOND_HELMET);

    public static ItemType<ItemDiamondHoeStack> DIAMOND_HOE_TYPE = empty(ItemDiamondHoeStack.class, VanillaItemId.DIAMOND_HOE);

    public static ItemType<ItemDiamondHorseArmorStack> DIAMOND_HORSE_ARMOR_TYPE = empty(ItemDiamondHorseArmorStack.class, VanillaItemId.DIAMOND_HORSE_ARMOR);

    public static ItemType<ItemDiamondLeggingsStack> DIAMOND_LEGGINGS_TYPE = empty(ItemDiamondLeggingsStack.class, VanillaItemId.DIAMOND_LEGGINGS);

    public static ItemType<ItemDiamondOreStack> DIAMOND_ORE_TYPE = empty(ItemDiamondOreStack.class, VanillaItemId.DIAMOND_ORE);

    public static ItemType<ItemDiamondPickaxeStack> DIAMOND_PICKAXE_TYPE = empty(ItemDiamondPickaxeStack.class, VanillaItemId.DIAMOND_PICKAXE);

    public static ItemType<ItemDiamondShovelStack> DIAMOND_SHOVEL_TYPE = empty(ItemDiamondShovelStack.class, VanillaItemId.DIAMOND_SHOVEL);

    public static ItemType<ItemDiamondSwordStack> DIAMOND_SWORD_TYPE = empty(ItemDiamondSwordStack.class, VanillaItemId.DIAMOND_SWORD);

    public static ItemType<ItemDioriteStack> DIORITE_TYPE = empty(ItemDioriteStack.class, VanillaItemId.DIORITE);

    public static ItemType<ItemDioriteStairsStack> DIORITE_STAIRS_TYPE = empty(ItemDioriteStairsStack.class, VanillaItemId.DIORITE_STAIRS);

    public static ItemType<ItemDirtStack> DIRT_TYPE = empty(ItemDirtStack.class, VanillaItemId.DIRT);

    public static ItemType<ItemDirtWithRootsStack> DIRT_WITH_ROOTS_TYPE = empty(ItemDirtWithRootsStack.class, VanillaItemId.DIRT_WITH_ROOTS);

    public static ItemType<ItemDiscFragment5Stack> DISC_FRAGMENT_5_TYPE = empty(ItemDiscFragment5Stack.class, VanillaItemId.DISC_FRAGMENT_5);

    public static ItemType<ItemDispenserStack> DISPENSER_TYPE = empty(ItemDispenserStack.class, VanillaItemId.DISPENSER);

    public static ItemType<ItemDolphinSpawnEggStack> DOLPHIN_SPAWN_EGG_TYPE = empty(ItemDolphinSpawnEggStack.class, VanillaItemId.DOLPHIN_SPAWN_EGG);

    public static ItemType<ItemDonkeySpawnEggStack> DONKEY_SPAWN_EGG_TYPE = empty(ItemDonkeySpawnEggStack.class, VanillaItemId.DONKEY_SPAWN_EGG);

    public static ItemType<ItemDoubleCutCopperSlabStack> DOUBLE_CUT_COPPER_SLAB_TYPE = empty(ItemDoubleCutCopperSlabStack.class, VanillaItemId.DOUBLE_CUT_COPPER_SLAB);

    public static ItemType<ItemDoublePlantStack> DOUBLE_PLANT_TYPE = empty(ItemDoublePlantStack.class, VanillaItemId.DOUBLE_PLANT);

    public static ItemType<ItemDoubleStoneBlockSlabStack> DOUBLE_STONE_BLOCK_SLAB_TYPE = empty(ItemDoubleStoneBlockSlabStack.class, VanillaItemId.DOUBLE_STONE_BLOCK_SLAB);

    public static ItemType<ItemDoubleStoneBlockSlab2Stack> DOUBLE_STONE_BLOCK_SLAB2_TYPE = empty(ItemDoubleStoneBlockSlab2Stack.class, VanillaItemId.DOUBLE_STONE_BLOCK_SLAB2);

    public static ItemType<ItemDoubleStoneBlockSlab3Stack> DOUBLE_STONE_BLOCK_SLAB3_TYPE = empty(ItemDoubleStoneBlockSlab3Stack.class, VanillaItemId.DOUBLE_STONE_BLOCK_SLAB3);

    public static ItemType<ItemDoubleStoneBlockSlab4Stack> DOUBLE_STONE_BLOCK_SLAB4_TYPE = empty(ItemDoubleStoneBlockSlab4Stack.class, VanillaItemId.DOUBLE_STONE_BLOCK_SLAB4);

    public static ItemType<ItemDoubleWoodenSlabStack> DOUBLE_WOODEN_SLAB_TYPE = empty(ItemDoubleWoodenSlabStack.class, VanillaItemId.DOUBLE_WOODEN_SLAB);

    public static ItemType<ItemDragonBreathStack> DRAGON_BREATH_TYPE = empty(ItemDragonBreathStack.class, VanillaItemId.DRAGON_BREATH);

    public static ItemType<ItemDragonEggStack> DRAGON_EGG_TYPE = empty(ItemDragonEggStack.class, VanillaItemId.DRAGON_EGG);

    public static ItemType<ItemDriedKelpStack> DRIED_KELP_TYPE = empty(ItemDriedKelpStack.class, VanillaItemId.DRIED_KELP);

    public static ItemType<ItemDriedKelpBlockStack> DRIED_KELP_BLOCK_TYPE = empty(ItemDriedKelpBlockStack.class, VanillaItemId.DRIED_KELP_BLOCK);

    public static ItemType<ItemDripstoneBlockStack> DRIPSTONE_BLOCK_TYPE = empty(ItemDripstoneBlockStack.class, VanillaItemId.DRIPSTONE_BLOCK);

    public static ItemType<ItemDropperStack> DROPPER_TYPE = empty(ItemDropperStack.class, VanillaItemId.DROPPER);

    public static ItemType<ItemDrownedSpawnEggStack> DROWNED_SPAWN_EGG_TYPE = empty(ItemDrownedSpawnEggStack.class, VanillaItemId.DROWNED_SPAWN_EGG);

    public static ItemType<ItemDuneArmorTrimSmithingTemplateStack> DUNE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemDuneArmorTrimSmithingTemplateStack.class, VanillaItemId.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemDyeStack> DYE_TYPE = empty(ItemDyeStack.class, VanillaItemId.DYE);

    public static ItemType<ItemEchoShardStack> ECHO_SHARD_TYPE = empty(ItemEchoShardStack.class, VanillaItemId.ECHO_SHARD);

    public static ItemType<ItemEggStack> EGG_TYPE = empty(ItemEggStack.class, VanillaItemId.EGG);

    public static ItemType<ItemElderGuardianSpawnEggStack> ELDER_GUARDIAN_SPAWN_EGG_TYPE = empty(ItemElderGuardianSpawnEggStack.class, VanillaItemId.ELDER_GUARDIAN_SPAWN_EGG);

    public static ItemType<ItemElement0Stack> ELEMENT_0_TYPE = empty(ItemElement0Stack.class, VanillaItemId.ELEMENT_0);

    public static ItemType<ItemElement1Stack> ELEMENT_1_TYPE = empty(ItemElement1Stack.class, VanillaItemId.ELEMENT_1);

    public static ItemType<ItemElement10Stack> ELEMENT_10_TYPE = empty(ItemElement10Stack.class, VanillaItemId.ELEMENT_10);

    public static ItemType<ItemElement100Stack> ELEMENT_100_TYPE = empty(ItemElement100Stack.class, VanillaItemId.ELEMENT_100);

    public static ItemType<ItemElement101Stack> ELEMENT_101_TYPE = empty(ItemElement101Stack.class, VanillaItemId.ELEMENT_101);

    public static ItemType<ItemElement102Stack> ELEMENT_102_TYPE = empty(ItemElement102Stack.class, VanillaItemId.ELEMENT_102);

    public static ItemType<ItemElement103Stack> ELEMENT_103_TYPE = empty(ItemElement103Stack.class, VanillaItemId.ELEMENT_103);

    public static ItemType<ItemElement104Stack> ELEMENT_104_TYPE = empty(ItemElement104Stack.class, VanillaItemId.ELEMENT_104);

    public static ItemType<ItemElement105Stack> ELEMENT_105_TYPE = empty(ItemElement105Stack.class, VanillaItemId.ELEMENT_105);

    public static ItemType<ItemElement106Stack> ELEMENT_106_TYPE = empty(ItemElement106Stack.class, VanillaItemId.ELEMENT_106);

    public static ItemType<ItemElement107Stack> ELEMENT_107_TYPE = empty(ItemElement107Stack.class, VanillaItemId.ELEMENT_107);

    public static ItemType<ItemElement108Stack> ELEMENT_108_TYPE = empty(ItemElement108Stack.class, VanillaItemId.ELEMENT_108);

    public static ItemType<ItemElement109Stack> ELEMENT_109_TYPE = empty(ItemElement109Stack.class, VanillaItemId.ELEMENT_109);

    public static ItemType<ItemElement11Stack> ELEMENT_11_TYPE = empty(ItemElement11Stack.class, VanillaItemId.ELEMENT_11);

    public static ItemType<ItemElement110Stack> ELEMENT_110_TYPE = empty(ItemElement110Stack.class, VanillaItemId.ELEMENT_110);

    public static ItemType<ItemElement111Stack> ELEMENT_111_TYPE = empty(ItemElement111Stack.class, VanillaItemId.ELEMENT_111);

    public static ItemType<ItemElement112Stack> ELEMENT_112_TYPE = empty(ItemElement112Stack.class, VanillaItemId.ELEMENT_112);

    public static ItemType<ItemElement113Stack> ELEMENT_113_TYPE = empty(ItemElement113Stack.class, VanillaItemId.ELEMENT_113);

    public static ItemType<ItemElement114Stack> ELEMENT_114_TYPE = empty(ItemElement114Stack.class, VanillaItemId.ELEMENT_114);

    public static ItemType<ItemElement115Stack> ELEMENT_115_TYPE = empty(ItemElement115Stack.class, VanillaItemId.ELEMENT_115);

    public static ItemType<ItemElement116Stack> ELEMENT_116_TYPE = empty(ItemElement116Stack.class, VanillaItemId.ELEMENT_116);

    public static ItemType<ItemElement117Stack> ELEMENT_117_TYPE = empty(ItemElement117Stack.class, VanillaItemId.ELEMENT_117);

    public static ItemType<ItemElement118Stack> ELEMENT_118_TYPE = empty(ItemElement118Stack.class, VanillaItemId.ELEMENT_118);

    public static ItemType<ItemElement12Stack> ELEMENT_12_TYPE = empty(ItemElement12Stack.class, VanillaItemId.ELEMENT_12);

    public static ItemType<ItemElement13Stack> ELEMENT_13_TYPE = empty(ItemElement13Stack.class, VanillaItemId.ELEMENT_13);

    public static ItemType<ItemElement14Stack> ELEMENT_14_TYPE = empty(ItemElement14Stack.class, VanillaItemId.ELEMENT_14);

    public static ItemType<ItemElement15Stack> ELEMENT_15_TYPE = empty(ItemElement15Stack.class, VanillaItemId.ELEMENT_15);

    public static ItemType<ItemElement16Stack> ELEMENT_16_TYPE = empty(ItemElement16Stack.class, VanillaItemId.ELEMENT_16);

    public static ItemType<ItemElement17Stack> ELEMENT_17_TYPE = empty(ItemElement17Stack.class, VanillaItemId.ELEMENT_17);

    public static ItemType<ItemElement18Stack> ELEMENT_18_TYPE = empty(ItemElement18Stack.class, VanillaItemId.ELEMENT_18);

    public static ItemType<ItemElement19Stack> ELEMENT_19_TYPE = empty(ItemElement19Stack.class, VanillaItemId.ELEMENT_19);

    public static ItemType<ItemElement2Stack> ELEMENT_2_TYPE = empty(ItemElement2Stack.class, VanillaItemId.ELEMENT_2);

    public static ItemType<ItemElement20Stack> ELEMENT_20_TYPE = empty(ItemElement20Stack.class, VanillaItemId.ELEMENT_20);

    public static ItemType<ItemElement21Stack> ELEMENT_21_TYPE = empty(ItemElement21Stack.class, VanillaItemId.ELEMENT_21);

    public static ItemType<ItemElement22Stack> ELEMENT_22_TYPE = empty(ItemElement22Stack.class, VanillaItemId.ELEMENT_22);

    public static ItemType<ItemElement23Stack> ELEMENT_23_TYPE = empty(ItemElement23Stack.class, VanillaItemId.ELEMENT_23);

    public static ItemType<ItemElement24Stack> ELEMENT_24_TYPE = empty(ItemElement24Stack.class, VanillaItemId.ELEMENT_24);

    public static ItemType<ItemElement25Stack> ELEMENT_25_TYPE = empty(ItemElement25Stack.class, VanillaItemId.ELEMENT_25);

    public static ItemType<ItemElement26Stack> ELEMENT_26_TYPE = empty(ItemElement26Stack.class, VanillaItemId.ELEMENT_26);

    public static ItemType<ItemElement27Stack> ELEMENT_27_TYPE = empty(ItemElement27Stack.class, VanillaItemId.ELEMENT_27);

    public static ItemType<ItemElement28Stack> ELEMENT_28_TYPE = empty(ItemElement28Stack.class, VanillaItemId.ELEMENT_28);

    public static ItemType<ItemElement29Stack> ELEMENT_29_TYPE = empty(ItemElement29Stack.class, VanillaItemId.ELEMENT_29);

    public static ItemType<ItemElement3Stack> ELEMENT_3_TYPE = empty(ItemElement3Stack.class, VanillaItemId.ELEMENT_3);

    public static ItemType<ItemElement30Stack> ELEMENT_30_TYPE = empty(ItemElement30Stack.class, VanillaItemId.ELEMENT_30);

    public static ItemType<ItemElement31Stack> ELEMENT_31_TYPE = empty(ItemElement31Stack.class, VanillaItemId.ELEMENT_31);

    public static ItemType<ItemElement32Stack> ELEMENT_32_TYPE = empty(ItemElement32Stack.class, VanillaItemId.ELEMENT_32);

    public static ItemType<ItemElement33Stack> ELEMENT_33_TYPE = empty(ItemElement33Stack.class, VanillaItemId.ELEMENT_33);

    public static ItemType<ItemElement34Stack> ELEMENT_34_TYPE = empty(ItemElement34Stack.class, VanillaItemId.ELEMENT_34);

    public static ItemType<ItemElement35Stack> ELEMENT_35_TYPE = empty(ItemElement35Stack.class, VanillaItemId.ELEMENT_35);

    public static ItemType<ItemElement36Stack> ELEMENT_36_TYPE = empty(ItemElement36Stack.class, VanillaItemId.ELEMENT_36);

    public static ItemType<ItemElement37Stack> ELEMENT_37_TYPE = empty(ItemElement37Stack.class, VanillaItemId.ELEMENT_37);

    public static ItemType<ItemElement38Stack> ELEMENT_38_TYPE = empty(ItemElement38Stack.class, VanillaItemId.ELEMENT_38);

    public static ItemType<ItemElement39Stack> ELEMENT_39_TYPE = empty(ItemElement39Stack.class, VanillaItemId.ELEMENT_39);

    public static ItemType<ItemElement4Stack> ELEMENT_4_TYPE = empty(ItemElement4Stack.class, VanillaItemId.ELEMENT_4);

    public static ItemType<ItemElement40Stack> ELEMENT_40_TYPE = empty(ItemElement40Stack.class, VanillaItemId.ELEMENT_40);

    public static ItemType<ItemElement41Stack> ELEMENT_41_TYPE = empty(ItemElement41Stack.class, VanillaItemId.ELEMENT_41);

    public static ItemType<ItemElement42Stack> ELEMENT_42_TYPE = empty(ItemElement42Stack.class, VanillaItemId.ELEMENT_42);

    public static ItemType<ItemElement43Stack> ELEMENT_43_TYPE = empty(ItemElement43Stack.class, VanillaItemId.ELEMENT_43);

    public static ItemType<ItemElement44Stack> ELEMENT_44_TYPE = empty(ItemElement44Stack.class, VanillaItemId.ELEMENT_44);

    public static ItemType<ItemElement45Stack> ELEMENT_45_TYPE = empty(ItemElement45Stack.class, VanillaItemId.ELEMENT_45);

    public static ItemType<ItemElement46Stack> ELEMENT_46_TYPE = empty(ItemElement46Stack.class, VanillaItemId.ELEMENT_46);

    public static ItemType<ItemElement47Stack> ELEMENT_47_TYPE = empty(ItemElement47Stack.class, VanillaItemId.ELEMENT_47);

    public static ItemType<ItemElement48Stack> ELEMENT_48_TYPE = empty(ItemElement48Stack.class, VanillaItemId.ELEMENT_48);

    public static ItemType<ItemElement49Stack> ELEMENT_49_TYPE = empty(ItemElement49Stack.class, VanillaItemId.ELEMENT_49);

    public static ItemType<ItemElement5Stack> ELEMENT_5_TYPE = empty(ItemElement5Stack.class, VanillaItemId.ELEMENT_5);

    public static ItemType<ItemElement50Stack> ELEMENT_50_TYPE = empty(ItemElement50Stack.class, VanillaItemId.ELEMENT_50);

    public static ItemType<ItemElement51Stack> ELEMENT_51_TYPE = empty(ItemElement51Stack.class, VanillaItemId.ELEMENT_51);

    public static ItemType<ItemElement52Stack> ELEMENT_52_TYPE = empty(ItemElement52Stack.class, VanillaItemId.ELEMENT_52);

    public static ItemType<ItemElement53Stack> ELEMENT_53_TYPE = empty(ItemElement53Stack.class, VanillaItemId.ELEMENT_53);

    public static ItemType<ItemElement54Stack> ELEMENT_54_TYPE = empty(ItemElement54Stack.class, VanillaItemId.ELEMENT_54);

    public static ItemType<ItemElement55Stack> ELEMENT_55_TYPE = empty(ItemElement55Stack.class, VanillaItemId.ELEMENT_55);

    public static ItemType<ItemElement56Stack> ELEMENT_56_TYPE = empty(ItemElement56Stack.class, VanillaItemId.ELEMENT_56);

    public static ItemType<ItemElement57Stack> ELEMENT_57_TYPE = empty(ItemElement57Stack.class, VanillaItemId.ELEMENT_57);

    public static ItemType<ItemElement58Stack> ELEMENT_58_TYPE = empty(ItemElement58Stack.class, VanillaItemId.ELEMENT_58);

    public static ItemType<ItemElement59Stack> ELEMENT_59_TYPE = empty(ItemElement59Stack.class, VanillaItemId.ELEMENT_59);

    public static ItemType<ItemElement6Stack> ELEMENT_6_TYPE = empty(ItemElement6Stack.class, VanillaItemId.ELEMENT_6);

    public static ItemType<ItemElement60Stack> ELEMENT_60_TYPE = empty(ItemElement60Stack.class, VanillaItemId.ELEMENT_60);

    public static ItemType<ItemElement61Stack> ELEMENT_61_TYPE = empty(ItemElement61Stack.class, VanillaItemId.ELEMENT_61);

    public static ItemType<ItemElement62Stack> ELEMENT_62_TYPE = empty(ItemElement62Stack.class, VanillaItemId.ELEMENT_62);

    public static ItemType<ItemElement63Stack> ELEMENT_63_TYPE = empty(ItemElement63Stack.class, VanillaItemId.ELEMENT_63);

    public static ItemType<ItemElement64Stack> ELEMENT_64_TYPE = empty(ItemElement64Stack.class, VanillaItemId.ELEMENT_64);

    public static ItemType<ItemElement65Stack> ELEMENT_65_TYPE = empty(ItemElement65Stack.class, VanillaItemId.ELEMENT_65);

    public static ItemType<ItemElement66Stack> ELEMENT_66_TYPE = empty(ItemElement66Stack.class, VanillaItemId.ELEMENT_66);

    public static ItemType<ItemElement67Stack> ELEMENT_67_TYPE = empty(ItemElement67Stack.class, VanillaItemId.ELEMENT_67);

    public static ItemType<ItemElement68Stack> ELEMENT_68_TYPE = empty(ItemElement68Stack.class, VanillaItemId.ELEMENT_68);

    public static ItemType<ItemElement69Stack> ELEMENT_69_TYPE = empty(ItemElement69Stack.class, VanillaItemId.ELEMENT_69);

    public static ItemType<ItemElement7Stack> ELEMENT_7_TYPE = empty(ItemElement7Stack.class, VanillaItemId.ELEMENT_7);

    public static ItemType<ItemElement70Stack> ELEMENT_70_TYPE = empty(ItemElement70Stack.class, VanillaItemId.ELEMENT_70);

    public static ItemType<ItemElement71Stack> ELEMENT_71_TYPE = empty(ItemElement71Stack.class, VanillaItemId.ELEMENT_71);

    public static ItemType<ItemElement72Stack> ELEMENT_72_TYPE = empty(ItemElement72Stack.class, VanillaItemId.ELEMENT_72);

    public static ItemType<ItemElement73Stack> ELEMENT_73_TYPE = empty(ItemElement73Stack.class, VanillaItemId.ELEMENT_73);

    public static ItemType<ItemElement74Stack> ELEMENT_74_TYPE = empty(ItemElement74Stack.class, VanillaItemId.ELEMENT_74);

    public static ItemType<ItemElement75Stack> ELEMENT_75_TYPE = empty(ItemElement75Stack.class, VanillaItemId.ELEMENT_75);

    public static ItemType<ItemElement76Stack> ELEMENT_76_TYPE = empty(ItemElement76Stack.class, VanillaItemId.ELEMENT_76);

    public static ItemType<ItemElement77Stack> ELEMENT_77_TYPE = empty(ItemElement77Stack.class, VanillaItemId.ELEMENT_77);

    public static ItemType<ItemElement78Stack> ELEMENT_78_TYPE = empty(ItemElement78Stack.class, VanillaItemId.ELEMENT_78);

    public static ItemType<ItemElement79Stack> ELEMENT_79_TYPE = empty(ItemElement79Stack.class, VanillaItemId.ELEMENT_79);

    public static ItemType<ItemElement8Stack> ELEMENT_8_TYPE = empty(ItemElement8Stack.class, VanillaItemId.ELEMENT_8);

    public static ItemType<ItemElement80Stack> ELEMENT_80_TYPE = empty(ItemElement80Stack.class, VanillaItemId.ELEMENT_80);

    public static ItemType<ItemElement81Stack> ELEMENT_81_TYPE = empty(ItemElement81Stack.class, VanillaItemId.ELEMENT_81);

    public static ItemType<ItemElement82Stack> ELEMENT_82_TYPE = empty(ItemElement82Stack.class, VanillaItemId.ELEMENT_82);

    public static ItemType<ItemElement83Stack> ELEMENT_83_TYPE = empty(ItemElement83Stack.class, VanillaItemId.ELEMENT_83);

    public static ItemType<ItemElement84Stack> ELEMENT_84_TYPE = empty(ItemElement84Stack.class, VanillaItemId.ELEMENT_84);

    public static ItemType<ItemElement85Stack> ELEMENT_85_TYPE = empty(ItemElement85Stack.class, VanillaItemId.ELEMENT_85);

    public static ItemType<ItemElement86Stack> ELEMENT_86_TYPE = empty(ItemElement86Stack.class, VanillaItemId.ELEMENT_86);

    public static ItemType<ItemElement87Stack> ELEMENT_87_TYPE = empty(ItemElement87Stack.class, VanillaItemId.ELEMENT_87);

    public static ItemType<ItemElement88Stack> ELEMENT_88_TYPE = empty(ItemElement88Stack.class, VanillaItemId.ELEMENT_88);

    public static ItemType<ItemElement89Stack> ELEMENT_89_TYPE = empty(ItemElement89Stack.class, VanillaItemId.ELEMENT_89);

    public static ItemType<ItemElement9Stack> ELEMENT_9_TYPE = empty(ItemElement9Stack.class, VanillaItemId.ELEMENT_9);

    public static ItemType<ItemElement90Stack> ELEMENT_90_TYPE = empty(ItemElement90Stack.class, VanillaItemId.ELEMENT_90);

    public static ItemType<ItemElement91Stack> ELEMENT_91_TYPE = empty(ItemElement91Stack.class, VanillaItemId.ELEMENT_91);

    public static ItemType<ItemElement92Stack> ELEMENT_92_TYPE = empty(ItemElement92Stack.class, VanillaItemId.ELEMENT_92);

    public static ItemType<ItemElement93Stack> ELEMENT_93_TYPE = empty(ItemElement93Stack.class, VanillaItemId.ELEMENT_93);

    public static ItemType<ItemElement94Stack> ELEMENT_94_TYPE = empty(ItemElement94Stack.class, VanillaItemId.ELEMENT_94);

    public static ItemType<ItemElement95Stack> ELEMENT_95_TYPE = empty(ItemElement95Stack.class, VanillaItemId.ELEMENT_95);

    public static ItemType<ItemElement96Stack> ELEMENT_96_TYPE = empty(ItemElement96Stack.class, VanillaItemId.ELEMENT_96);

    public static ItemType<ItemElement97Stack> ELEMENT_97_TYPE = empty(ItemElement97Stack.class, VanillaItemId.ELEMENT_97);

    public static ItemType<ItemElement98Stack> ELEMENT_98_TYPE = empty(ItemElement98Stack.class, VanillaItemId.ELEMENT_98);

    public static ItemType<ItemElement99Stack> ELEMENT_99_TYPE = empty(ItemElement99Stack.class, VanillaItemId.ELEMENT_99);

    public static ItemType<ItemElytraStack> ELYTRA_TYPE = empty(ItemElytraStack.class, VanillaItemId.ELYTRA);

    public static ItemType<ItemEmeraldStack> EMERALD_TYPE = empty(ItemEmeraldStack.class, VanillaItemId.EMERALD);

    public static ItemType<ItemEmeraldBlockStack> EMERALD_BLOCK_TYPE = empty(ItemEmeraldBlockStack.class, VanillaItemId.EMERALD_BLOCK);

    public static ItemType<ItemEmeraldOreStack> EMERALD_ORE_TYPE = empty(ItemEmeraldOreStack.class, VanillaItemId.EMERALD_ORE);

    public static ItemType<ItemEmptyMapStack> EMPTY_MAP_TYPE = empty(ItemEmptyMapStack.class, VanillaItemId.EMPTY_MAP);

    public static ItemType<ItemEnchantedBookStack> ENCHANTED_BOOK_TYPE = empty(ItemEnchantedBookStack.class, VanillaItemId.ENCHANTED_BOOK);

    public static ItemType<ItemEnchantedGoldenAppleStack> ENCHANTED_GOLDEN_APPLE_TYPE = empty(ItemEnchantedGoldenAppleStack.class, VanillaItemId.ENCHANTED_GOLDEN_APPLE);

    public static ItemType<ItemEnchantingTableStack> ENCHANTING_TABLE_TYPE = empty(ItemEnchantingTableStack.class, VanillaItemId.ENCHANTING_TABLE);

    public static ItemType<ItemEndBrickStairsStack> END_BRICK_STAIRS_TYPE = empty(ItemEndBrickStairsStack.class, VanillaItemId.END_BRICK_STAIRS);

    public static ItemType<ItemEndBricksStack> END_BRICKS_TYPE = empty(ItemEndBricksStack.class, VanillaItemId.END_BRICKS);

    public static ItemType<ItemEndCrystalStack> END_CRYSTAL_TYPE = empty(ItemEndCrystalStack.class, VanillaItemId.END_CRYSTAL);

    public static ItemType<ItemEndGatewayStack> END_GATEWAY_TYPE = empty(ItemEndGatewayStack.class, VanillaItemId.END_GATEWAY);

    public static ItemType<ItemEndPortalStack> END_PORTAL_TYPE = empty(ItemEndPortalStack.class, VanillaItemId.END_PORTAL);

    public static ItemType<ItemEndPortalFrameStack> END_PORTAL_FRAME_TYPE = empty(ItemEndPortalFrameStack.class, VanillaItemId.END_PORTAL_FRAME);

    public static ItemType<ItemEndRodStack> END_ROD_TYPE = empty(ItemEndRodStack.class, VanillaItemId.END_ROD);

    public static ItemType<ItemEndStoneStack> END_STONE_TYPE = empty(ItemEndStoneStack.class, VanillaItemId.END_STONE);

    public static ItemType<ItemEnderChestStack> ENDER_CHEST_TYPE = empty(ItemEnderChestStack.class, VanillaItemId.ENDER_CHEST);

    public static ItemType<ItemEnderDragonSpawnEggStack> ENDER_DRAGON_SPAWN_EGG_TYPE = empty(ItemEnderDragonSpawnEggStack.class, VanillaItemId.ENDER_DRAGON_SPAWN_EGG);

    public static ItemType<ItemEnderEyeStack> ENDER_EYE_TYPE = empty(ItemEnderEyeStack.class, VanillaItemId.ENDER_EYE);

    public static ItemType<ItemEnderPearlStack> ENDER_PEARL_TYPE = empty(ItemEnderPearlStack.class, VanillaItemId.ENDER_PEARL);

    public static ItemType<ItemEndermanSpawnEggStack> ENDERMAN_SPAWN_EGG_TYPE = empty(ItemEndermanSpawnEggStack.class, VanillaItemId.ENDERMAN_SPAWN_EGG);

    public static ItemType<ItemEndermiteSpawnEggStack> ENDERMITE_SPAWN_EGG_TYPE = empty(ItemEndermiteSpawnEggStack.class, VanillaItemId.ENDERMITE_SPAWN_EGG);

    public static ItemType<ItemEvokerSpawnEggStack> EVOKER_SPAWN_EGG_TYPE = empty(ItemEvokerSpawnEggStack.class, VanillaItemId.EVOKER_SPAWN_EGG);

    public static ItemType<ItemExperienceBottleStack> EXPERIENCE_BOTTLE_TYPE = empty(ItemExperienceBottleStack.class, VanillaItemId.EXPERIENCE_BOTTLE);

    public static ItemType<ItemExplorerPotterySherdStack> EXPLORER_POTTERY_SHERD_TYPE = empty(ItemExplorerPotterySherdStack.class, VanillaItemId.EXPLORER_POTTERY_SHERD);

    public static ItemType<ItemExposedChiseledCopperStack> EXPOSED_CHISELED_COPPER_TYPE = empty(ItemExposedChiseledCopperStack.class, VanillaItemId.EXPOSED_CHISELED_COPPER);

    public static ItemType<ItemExposedCopperStack> EXPOSED_COPPER_TYPE = empty(ItemExposedCopperStack.class, VanillaItemId.EXPOSED_COPPER);

    public static ItemType<ItemExposedCopperBulbStack> EXPOSED_COPPER_BULB_TYPE = empty(ItemExposedCopperBulbStack.class, VanillaItemId.EXPOSED_COPPER_BULB);

    public static ItemType<ItemExposedCopperDoorStack> EXPOSED_COPPER_DOOR_TYPE = empty(ItemExposedCopperDoorStack.class, VanillaItemId.EXPOSED_COPPER_DOOR);

    public static ItemType<ItemExposedCopperGrateStack> EXPOSED_COPPER_GRATE_TYPE = empty(ItemExposedCopperGrateStack.class, VanillaItemId.EXPOSED_COPPER_GRATE);

    public static ItemType<ItemExposedCopperTrapdoorStack> EXPOSED_COPPER_TRAPDOOR_TYPE = empty(ItemExposedCopperTrapdoorStack.class, VanillaItemId.EXPOSED_COPPER_TRAPDOOR);

    public static ItemType<ItemExposedCutCopperStack> EXPOSED_CUT_COPPER_TYPE = empty(ItemExposedCutCopperStack.class, VanillaItemId.EXPOSED_CUT_COPPER);

    public static ItemType<ItemExposedCutCopperSlabStack> EXPOSED_CUT_COPPER_SLAB_TYPE = empty(ItemExposedCutCopperSlabStack.class, VanillaItemId.EXPOSED_CUT_COPPER_SLAB);

    public static ItemType<ItemExposedCutCopperStairsStack> EXPOSED_CUT_COPPER_STAIRS_TYPE = empty(ItemExposedCutCopperStairsStack.class, VanillaItemId.EXPOSED_CUT_COPPER_STAIRS);

    public static ItemType<ItemExposedDoubleCutCopperSlabStack> EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = empty(ItemExposedDoubleCutCopperSlabStack.class, VanillaItemId.EXPOSED_DOUBLE_CUT_COPPER_SLAB);

    public static ItemType<ItemEyeArmorTrimSmithingTemplateStack> EYE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemEyeArmorTrimSmithingTemplateStack.class, VanillaItemId.EYE_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemFarmlandStack> FARMLAND_TYPE = empty(ItemFarmlandStack.class, VanillaItemId.FARMLAND);

    public static ItemType<ItemFeatherStack> FEATHER_TYPE = empty(ItemFeatherStack.class, VanillaItemId.FEATHER);

    public static ItemType<ItemFenceStack> FENCE_TYPE = empty(ItemFenceStack.class, VanillaItemId.FENCE);

    public static ItemType<ItemFenceGateStack> FENCE_GATE_TYPE = empty(ItemFenceGateStack.class, VanillaItemId.FENCE_GATE);

    public static ItemType<ItemFermentedSpiderEyeStack> FERMENTED_SPIDER_EYE_TYPE = empty(ItemFermentedSpiderEyeStack.class, VanillaItemId.FERMENTED_SPIDER_EYE);

    public static ItemType<ItemFieldMasonedBannerPatternStack> FIELD_MASONED_BANNER_PATTERN_TYPE = empty(ItemFieldMasonedBannerPatternStack.class, VanillaItemId.FIELD_MASONED_BANNER_PATTERN);

    public static ItemType<ItemFilledMapStack> FILLED_MAP_TYPE = empty(ItemFilledMapStack.class, VanillaItemId.FILLED_MAP);

    public static ItemType<ItemFireStack> FIRE_TYPE = empty(ItemFireStack.class, VanillaItemId.FIRE);

    public static ItemType<ItemFireChargeStack> FIRE_CHARGE_TYPE = empty(ItemFireChargeStack.class, VanillaItemId.FIRE_CHARGE);

    public static ItemType<ItemFireCoralStack> FIRE_CORAL_TYPE = empty(ItemFireCoralStack.class, VanillaItemId.FIRE_CORAL);

    public static ItemType<ItemFireworkRocketStack> FIREWORK_ROCKET_TYPE = empty(ItemFireworkRocketStack.class, VanillaItemId.FIREWORK_ROCKET);

    public static ItemType<ItemFireworkStarStack> FIREWORK_STAR_TYPE = empty(ItemFireworkStarStack.class, VanillaItemId.FIREWORK_STAR);

    public static ItemType<ItemFishingRodStack> FISHING_ROD_TYPE = empty(ItemFishingRodStack.class, VanillaItemId.FISHING_ROD);

    public static ItemType<ItemFletchingTableStack> FLETCHING_TABLE_TYPE = empty(ItemFletchingTableStack.class, VanillaItemId.FLETCHING_TABLE);

    public static ItemType<ItemFlintStack> FLINT_TYPE = empty(ItemFlintStack.class, VanillaItemId.FLINT);

    public static ItemType<ItemFlintAndSteelStack> FLINT_AND_STEEL_TYPE = empty(ItemFlintAndSteelStack.class, VanillaItemId.FLINT_AND_STEEL);

    public static ItemType<ItemFlowerBannerPatternStack> FLOWER_BANNER_PATTERN_TYPE = empty(ItemFlowerBannerPatternStack.class, VanillaItemId.FLOWER_BANNER_PATTERN);

    public static ItemType<ItemFlowerPotStack> FLOWER_POT_TYPE = empty(ItemFlowerPotStack.class, VanillaItemId.FLOWER_POT);

    public static ItemType<ItemFloweringAzaleaStack> FLOWERING_AZALEA_TYPE = empty(ItemFloweringAzaleaStack.class, VanillaItemId.FLOWERING_AZALEA);

    public static ItemType<ItemFlowingLavaStack> FLOWING_LAVA_TYPE = empty(ItemFlowingLavaStack.class, VanillaItemId.FLOWING_LAVA);

    public static ItemType<ItemFlowingWaterStack> FLOWING_WATER_TYPE = empty(ItemFlowingWaterStack.class, VanillaItemId.FLOWING_WATER);

    public static ItemType<ItemFoxSpawnEggStack> FOX_SPAWN_EGG_TYPE = empty(ItemFoxSpawnEggStack.class, VanillaItemId.FOX_SPAWN_EGG);

    public static ItemType<ItemFrameStack> FRAME_TYPE = empty(ItemFrameStack.class, VanillaItemId.FRAME);

    public static ItemType<ItemFriendPotterySherdStack> FRIEND_POTTERY_SHERD_TYPE = empty(ItemFriendPotterySherdStack.class, VanillaItemId.FRIEND_POTTERY_SHERD);

    public static ItemType<ItemFrogSpawnStack> FROG_SPAWN_TYPE = empty(ItemFrogSpawnStack.class, VanillaItemId.FROG_SPAWN);

    public static ItemType<ItemFrogSpawnEggStack> FROG_SPAWN_EGG_TYPE = empty(ItemFrogSpawnEggStack.class, VanillaItemId.FROG_SPAWN_EGG);

    public static ItemType<ItemFrostedIceStack> FROSTED_ICE_TYPE = empty(ItemFrostedIceStack.class, VanillaItemId.FROSTED_ICE);

    public static ItemType<ItemFurnaceStack> FURNACE_TYPE = empty(ItemFurnaceStack.class, VanillaItemId.FURNACE);

    public static ItemType<ItemGhastSpawnEggStack> GHAST_SPAWN_EGG_TYPE = empty(ItemGhastSpawnEggStack.class, VanillaItemId.GHAST_SPAWN_EGG);

    public static ItemType<ItemGhastTearStack> GHAST_TEAR_TYPE = empty(ItemGhastTearStack.class, VanillaItemId.GHAST_TEAR);

    public static ItemType<ItemGildedBlackstoneStack> GILDED_BLACKSTONE_TYPE = empty(ItemGildedBlackstoneStack.class, VanillaItemId.GILDED_BLACKSTONE);

    public static ItemType<ItemGlassStack> GLASS_TYPE = empty(ItemGlassStack.class, VanillaItemId.GLASS);

    public static ItemType<ItemGlassBottleStack> GLASS_BOTTLE_TYPE = empty(ItemGlassBottleStack.class, VanillaItemId.GLASS_BOTTLE);

    public static ItemType<ItemGlassPaneStack> GLASS_PANE_TYPE = empty(ItemGlassPaneStack.class, VanillaItemId.GLASS_PANE);

    public static ItemType<ItemGlisteringMelonSliceStack> GLISTERING_MELON_SLICE_TYPE = empty(ItemGlisteringMelonSliceStack.class, VanillaItemId.GLISTERING_MELON_SLICE);

    public static ItemType<ItemGlobeBannerPatternStack> GLOBE_BANNER_PATTERN_TYPE = empty(ItemGlobeBannerPatternStack.class, VanillaItemId.GLOBE_BANNER_PATTERN);

    public static ItemType<ItemGlowBerriesStack> GLOW_BERRIES_TYPE = empty(ItemGlowBerriesStack.class, VanillaItemId.GLOW_BERRIES);

    public static ItemType<ItemGlowFrameStack> GLOW_FRAME_TYPE = empty(ItemGlowFrameStack.class, VanillaItemId.GLOW_FRAME);

    public static ItemType<ItemGlowInkSacStack> GLOW_INK_SAC_TYPE = empty(ItemGlowInkSacStack.class, VanillaItemId.GLOW_INK_SAC);

    public static ItemType<ItemGlowLichenStack> GLOW_LICHEN_TYPE = empty(ItemGlowLichenStack.class, VanillaItemId.GLOW_LICHEN);

    public static ItemType<ItemGlowSquidSpawnEggStack> GLOW_SQUID_SPAWN_EGG_TYPE = empty(ItemGlowSquidSpawnEggStack.class, VanillaItemId.GLOW_SQUID_SPAWN_EGG);

    public static ItemType<ItemGlowStickStack> GLOW_STICK_TYPE = empty(ItemGlowStickStack.class, VanillaItemId.GLOW_STICK);

    public static ItemType<ItemGlowingobsidianStack> GLOWINGOBSIDIAN_TYPE = empty(ItemGlowingobsidianStack.class, VanillaItemId.GLOWINGOBSIDIAN);

    public static ItemType<ItemGlowstoneStack> GLOWSTONE_TYPE = empty(ItemGlowstoneStack.class, VanillaItemId.GLOWSTONE);

    public static ItemType<ItemGlowstoneDustStack> GLOWSTONE_DUST_TYPE = empty(ItemGlowstoneDustStack.class, VanillaItemId.GLOWSTONE_DUST);

    public static ItemType<ItemGoatHornStack> GOAT_HORN_TYPE = empty(ItemGoatHornStack.class, VanillaItemId.GOAT_HORN);

    public static ItemType<ItemGoatSpawnEggStack> GOAT_SPAWN_EGG_TYPE = empty(ItemGoatSpawnEggStack.class, VanillaItemId.GOAT_SPAWN_EGG);

    public static ItemType<ItemGoldBlockStack> GOLD_BLOCK_TYPE = empty(ItemGoldBlockStack.class, VanillaItemId.GOLD_BLOCK);

    public static ItemType<ItemGoldIngotStack> GOLD_INGOT_TYPE = empty(ItemGoldIngotStack.class, VanillaItemId.GOLD_INGOT);

    public static ItemType<ItemGoldNuggetStack> GOLD_NUGGET_TYPE = empty(ItemGoldNuggetStack.class, VanillaItemId.GOLD_NUGGET);

    public static ItemType<ItemGoldOreStack> GOLD_ORE_TYPE = empty(ItemGoldOreStack.class, VanillaItemId.GOLD_ORE);

    public static ItemType<ItemGoldenAppleStack> GOLDEN_APPLE_TYPE = empty(ItemGoldenAppleStack.class, VanillaItemId.GOLDEN_APPLE);

    public static ItemType<ItemGoldenAxeStack> GOLDEN_AXE_TYPE = empty(ItemGoldenAxeStack.class, VanillaItemId.GOLDEN_AXE);

    public static ItemType<ItemGoldenBootsStack> GOLDEN_BOOTS_TYPE = empty(ItemGoldenBootsStack.class, VanillaItemId.GOLDEN_BOOTS);

    public static ItemType<ItemGoldenCarrotStack> GOLDEN_CARROT_TYPE = empty(ItemGoldenCarrotStack.class, VanillaItemId.GOLDEN_CARROT);

    public static ItemType<ItemGoldenChestplateStack> GOLDEN_CHESTPLATE_TYPE = empty(ItemGoldenChestplateStack.class, VanillaItemId.GOLDEN_CHESTPLATE);

    public static ItemType<ItemGoldenHelmetStack> GOLDEN_HELMET_TYPE = empty(ItemGoldenHelmetStack.class, VanillaItemId.GOLDEN_HELMET);

    public static ItemType<ItemGoldenHoeStack> GOLDEN_HOE_TYPE = empty(ItemGoldenHoeStack.class, VanillaItemId.GOLDEN_HOE);

    public static ItemType<ItemGoldenHorseArmorStack> GOLDEN_HORSE_ARMOR_TYPE = empty(ItemGoldenHorseArmorStack.class, VanillaItemId.GOLDEN_HORSE_ARMOR);

    public static ItemType<ItemGoldenLeggingsStack> GOLDEN_LEGGINGS_TYPE = empty(ItemGoldenLeggingsStack.class, VanillaItemId.GOLDEN_LEGGINGS);

    public static ItemType<ItemGoldenPickaxeStack> GOLDEN_PICKAXE_TYPE = empty(ItemGoldenPickaxeStack.class, VanillaItemId.GOLDEN_PICKAXE);

    public static ItemType<ItemGoldenRailStack> GOLDEN_RAIL_TYPE = empty(ItemGoldenRailStack.class, VanillaItemId.GOLDEN_RAIL);

    public static ItemType<ItemGoldenShovelStack> GOLDEN_SHOVEL_TYPE = empty(ItemGoldenShovelStack.class, VanillaItemId.GOLDEN_SHOVEL);

    public static ItemType<ItemGoldenSwordStack> GOLDEN_SWORD_TYPE = empty(ItemGoldenSwordStack.class, VanillaItemId.GOLDEN_SWORD);

    public static ItemType<ItemGraniteStack> GRANITE_TYPE = empty(ItemGraniteStack.class, VanillaItemId.GRANITE);

    public static ItemType<ItemGraniteStairsStack> GRANITE_STAIRS_TYPE = empty(ItemGraniteStairsStack.class, VanillaItemId.GRANITE_STAIRS);

    public static ItemType<ItemGrassStack> GRASS_TYPE = empty(ItemGrassStack.class, VanillaItemId.GRASS);

    public static ItemType<ItemGrassPathStack> GRASS_PATH_TYPE = empty(ItemGrassPathStack.class, VanillaItemId.GRASS_PATH);

    public static ItemType<ItemGravelStack> GRAVEL_TYPE = empty(ItemGravelStack.class, VanillaItemId.GRAVEL);

    public static ItemType<ItemGrayCandleStack> GRAY_CANDLE_TYPE = empty(ItemGrayCandleStack.class, VanillaItemId.GRAY_CANDLE);

    public static ItemType<ItemGrayCandleCakeStack> GRAY_CANDLE_CAKE_TYPE = empty(ItemGrayCandleCakeStack.class, VanillaItemId.GRAY_CANDLE_CAKE);

    public static ItemType<ItemGrayCarpetStack> GRAY_CARPET_TYPE = empty(ItemGrayCarpetStack.class, VanillaItemId.GRAY_CARPET);

    public static ItemType<ItemGrayConcreteStack> GRAY_CONCRETE_TYPE = empty(ItemGrayConcreteStack.class, VanillaItemId.GRAY_CONCRETE);

    public static ItemType<ItemGrayConcretePowderStack> GRAY_CONCRETE_POWDER_TYPE = empty(ItemGrayConcretePowderStack.class, VanillaItemId.GRAY_CONCRETE_POWDER);

    public static ItemType<ItemGrayDyeStack> GRAY_DYE_TYPE = empty(ItemGrayDyeStack.class, VanillaItemId.GRAY_DYE);

    public static ItemType<ItemGrayGlazedTerracottaStack> GRAY_GLAZED_TERRACOTTA_TYPE = empty(ItemGrayGlazedTerracottaStack.class, VanillaItemId.GRAY_GLAZED_TERRACOTTA);

    public static ItemType<ItemGrayShulkerBoxStack> GRAY_SHULKER_BOX_TYPE = empty(ItemGrayShulkerBoxStack.class, VanillaItemId.GRAY_SHULKER_BOX);

    public static ItemType<ItemGrayStainedGlassStack> GRAY_STAINED_GLASS_TYPE = empty(ItemGrayStainedGlassStack.class, VanillaItemId.GRAY_STAINED_GLASS);

    public static ItemType<ItemGrayStainedGlassPaneStack> GRAY_STAINED_GLASS_PANE_TYPE = empty(ItemGrayStainedGlassPaneStack.class, VanillaItemId.GRAY_STAINED_GLASS_PANE);

    public static ItemType<ItemGrayTerracottaStack> GRAY_TERRACOTTA_TYPE = empty(ItemGrayTerracottaStack.class, VanillaItemId.GRAY_TERRACOTTA);

    public static ItemType<ItemGrayWoolStack> GRAY_WOOL_TYPE = empty(ItemGrayWoolStack.class, VanillaItemId.GRAY_WOOL);

    public static ItemType<ItemGreenCandleStack> GREEN_CANDLE_TYPE = empty(ItemGreenCandleStack.class, VanillaItemId.GREEN_CANDLE);

    public static ItemType<ItemGreenCandleCakeStack> GREEN_CANDLE_CAKE_TYPE = empty(ItemGreenCandleCakeStack.class, VanillaItemId.GREEN_CANDLE_CAKE);

    public static ItemType<ItemGreenCarpetStack> GREEN_CARPET_TYPE = empty(ItemGreenCarpetStack.class, VanillaItemId.GREEN_CARPET);

    public static ItemType<ItemGreenConcreteStack> GREEN_CONCRETE_TYPE = empty(ItemGreenConcreteStack.class, VanillaItemId.GREEN_CONCRETE);

    public static ItemType<ItemGreenConcretePowderStack> GREEN_CONCRETE_POWDER_TYPE = empty(ItemGreenConcretePowderStack.class, VanillaItemId.GREEN_CONCRETE_POWDER);

    public static ItemType<ItemGreenDyeStack> GREEN_DYE_TYPE = empty(ItemGreenDyeStack.class, VanillaItemId.GREEN_DYE);

    public static ItemType<ItemGreenGlazedTerracottaStack> GREEN_GLAZED_TERRACOTTA_TYPE = empty(ItemGreenGlazedTerracottaStack.class, VanillaItemId.GREEN_GLAZED_TERRACOTTA);

    public static ItemType<ItemGreenShulkerBoxStack> GREEN_SHULKER_BOX_TYPE = empty(ItemGreenShulkerBoxStack.class, VanillaItemId.GREEN_SHULKER_BOX);

    public static ItemType<ItemGreenStainedGlassStack> GREEN_STAINED_GLASS_TYPE = empty(ItemGreenStainedGlassStack.class, VanillaItemId.GREEN_STAINED_GLASS);

    public static ItemType<ItemGreenStainedGlassPaneStack> GREEN_STAINED_GLASS_PANE_TYPE = empty(ItemGreenStainedGlassPaneStack.class, VanillaItemId.GREEN_STAINED_GLASS_PANE);

    public static ItemType<ItemGreenTerracottaStack> GREEN_TERRACOTTA_TYPE = empty(ItemGreenTerracottaStack.class, VanillaItemId.GREEN_TERRACOTTA);

    public static ItemType<ItemGreenWoolStack> GREEN_WOOL_TYPE = empty(ItemGreenWoolStack.class, VanillaItemId.GREEN_WOOL);

    public static ItemType<ItemGrindstoneStack> GRINDSTONE_TYPE = empty(ItemGrindstoneStack.class, VanillaItemId.GRINDSTONE);

    public static ItemType<ItemGuardianSpawnEggStack> GUARDIAN_SPAWN_EGG_TYPE = empty(ItemGuardianSpawnEggStack.class, VanillaItemId.GUARDIAN_SPAWN_EGG);

    public static ItemType<ItemGunpowderStack> GUNPOWDER_TYPE = empty(ItemGunpowderStack.class, VanillaItemId.GUNPOWDER);

    public static ItemType<ItemHangingRootsStack> HANGING_ROOTS_TYPE = empty(ItemHangingRootsStack.class, VanillaItemId.HANGING_ROOTS);

    public static ItemType<ItemHardBlackStainedGlassStack> HARD_BLACK_STAINED_GLASS_TYPE = empty(ItemHardBlackStainedGlassStack.class, VanillaItemId.HARD_BLACK_STAINED_GLASS);

    public static ItemType<ItemHardBlackStainedGlassPaneStack> HARD_BLACK_STAINED_GLASS_PANE_TYPE = empty(ItemHardBlackStainedGlassPaneStack.class, VanillaItemId.HARD_BLACK_STAINED_GLASS_PANE);

    public static ItemType<ItemHardBlueStainedGlassStack> HARD_BLUE_STAINED_GLASS_TYPE = empty(ItemHardBlueStainedGlassStack.class, VanillaItemId.HARD_BLUE_STAINED_GLASS);

    public static ItemType<ItemHardBlueStainedGlassPaneStack> HARD_BLUE_STAINED_GLASS_PANE_TYPE = empty(ItemHardBlueStainedGlassPaneStack.class, VanillaItemId.HARD_BLUE_STAINED_GLASS_PANE);

    public static ItemType<ItemHardBrownStainedGlassStack> HARD_BROWN_STAINED_GLASS_TYPE = empty(ItemHardBrownStainedGlassStack.class, VanillaItemId.HARD_BROWN_STAINED_GLASS);

    public static ItemType<ItemHardBrownStainedGlassPaneStack> HARD_BROWN_STAINED_GLASS_PANE_TYPE = empty(ItemHardBrownStainedGlassPaneStack.class, VanillaItemId.HARD_BROWN_STAINED_GLASS_PANE);

    public static ItemType<ItemHardCyanStainedGlassStack> HARD_CYAN_STAINED_GLASS_TYPE = empty(ItemHardCyanStainedGlassStack.class, VanillaItemId.HARD_CYAN_STAINED_GLASS);

    public static ItemType<ItemHardCyanStainedGlassPaneStack> HARD_CYAN_STAINED_GLASS_PANE_TYPE = empty(ItemHardCyanStainedGlassPaneStack.class, VanillaItemId.HARD_CYAN_STAINED_GLASS_PANE);

    public static ItemType<ItemHardGlassStack> HARD_GLASS_TYPE = empty(ItemHardGlassStack.class, VanillaItemId.HARD_GLASS);

    public static ItemType<ItemHardGlassPaneStack> HARD_GLASS_PANE_TYPE = empty(ItemHardGlassPaneStack.class, VanillaItemId.HARD_GLASS_PANE);

    public static ItemType<ItemHardGrayStainedGlassStack> HARD_GRAY_STAINED_GLASS_TYPE = empty(ItemHardGrayStainedGlassStack.class, VanillaItemId.HARD_GRAY_STAINED_GLASS);

    public static ItemType<ItemHardGrayStainedGlassPaneStack> HARD_GRAY_STAINED_GLASS_PANE_TYPE = empty(ItemHardGrayStainedGlassPaneStack.class, VanillaItemId.HARD_GRAY_STAINED_GLASS_PANE);

    public static ItemType<ItemHardGreenStainedGlassStack> HARD_GREEN_STAINED_GLASS_TYPE = empty(ItemHardGreenStainedGlassStack.class, VanillaItemId.HARD_GREEN_STAINED_GLASS);

    public static ItemType<ItemHardGreenStainedGlassPaneStack> HARD_GREEN_STAINED_GLASS_PANE_TYPE = empty(ItemHardGreenStainedGlassPaneStack.class, VanillaItemId.HARD_GREEN_STAINED_GLASS_PANE);

    public static ItemType<ItemHardLightBlueStainedGlassStack> HARD_LIGHT_BLUE_STAINED_GLASS_TYPE = empty(ItemHardLightBlueStainedGlassStack.class, VanillaItemId.HARD_LIGHT_BLUE_STAINED_GLASS);

    public static ItemType<ItemHardLightBlueStainedGlassPaneStack> HARD_LIGHT_BLUE_STAINED_GLASS_PANE_TYPE = empty(ItemHardLightBlueStainedGlassPaneStack.class, VanillaItemId.HARD_LIGHT_BLUE_STAINED_GLASS_PANE);

    public static ItemType<ItemHardLightGrayStainedGlassStack> HARD_LIGHT_GRAY_STAINED_GLASS_TYPE = empty(ItemHardLightGrayStainedGlassStack.class, VanillaItemId.HARD_LIGHT_GRAY_STAINED_GLASS);

    public static ItemType<ItemHardLightGrayStainedGlassPaneStack> HARD_LIGHT_GRAY_STAINED_GLASS_PANE_TYPE = empty(ItemHardLightGrayStainedGlassPaneStack.class, VanillaItemId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE);

    public static ItemType<ItemHardLimeStainedGlassStack> HARD_LIME_STAINED_GLASS_TYPE = empty(ItemHardLimeStainedGlassStack.class, VanillaItemId.HARD_LIME_STAINED_GLASS);

    public static ItemType<ItemHardLimeStainedGlassPaneStack> HARD_LIME_STAINED_GLASS_PANE_TYPE = empty(ItemHardLimeStainedGlassPaneStack.class, VanillaItemId.HARD_LIME_STAINED_GLASS_PANE);

    public static ItemType<ItemHardMagentaStainedGlassStack> HARD_MAGENTA_STAINED_GLASS_TYPE = empty(ItemHardMagentaStainedGlassStack.class, VanillaItemId.HARD_MAGENTA_STAINED_GLASS);

    public static ItemType<ItemHardMagentaStainedGlassPaneStack> HARD_MAGENTA_STAINED_GLASS_PANE_TYPE = empty(ItemHardMagentaStainedGlassPaneStack.class, VanillaItemId.HARD_MAGENTA_STAINED_GLASS_PANE);

    public static ItemType<ItemHardOrangeStainedGlassStack> HARD_ORANGE_STAINED_GLASS_TYPE = empty(ItemHardOrangeStainedGlassStack.class, VanillaItemId.HARD_ORANGE_STAINED_GLASS);

    public static ItemType<ItemHardOrangeStainedGlassPaneStack> HARD_ORANGE_STAINED_GLASS_PANE_TYPE = empty(ItemHardOrangeStainedGlassPaneStack.class, VanillaItemId.HARD_ORANGE_STAINED_GLASS_PANE);

    public static ItemType<ItemHardPinkStainedGlassStack> HARD_PINK_STAINED_GLASS_TYPE = empty(ItemHardPinkStainedGlassStack.class, VanillaItemId.HARD_PINK_STAINED_GLASS);

    public static ItemType<ItemHardPinkStainedGlassPaneStack> HARD_PINK_STAINED_GLASS_PANE_TYPE = empty(ItemHardPinkStainedGlassPaneStack.class, VanillaItemId.HARD_PINK_STAINED_GLASS_PANE);

    public static ItemType<ItemHardPurpleStainedGlassStack> HARD_PURPLE_STAINED_GLASS_TYPE = empty(ItemHardPurpleStainedGlassStack.class, VanillaItemId.HARD_PURPLE_STAINED_GLASS);

    public static ItemType<ItemHardPurpleStainedGlassPaneStack> HARD_PURPLE_STAINED_GLASS_PANE_TYPE = empty(ItemHardPurpleStainedGlassPaneStack.class, VanillaItemId.HARD_PURPLE_STAINED_GLASS_PANE);

    public static ItemType<ItemHardRedStainedGlassStack> HARD_RED_STAINED_GLASS_TYPE = empty(ItemHardRedStainedGlassStack.class, VanillaItemId.HARD_RED_STAINED_GLASS);

    public static ItemType<ItemHardRedStainedGlassPaneStack> HARD_RED_STAINED_GLASS_PANE_TYPE = empty(ItemHardRedStainedGlassPaneStack.class, VanillaItemId.HARD_RED_STAINED_GLASS_PANE);

    public static ItemType<ItemHardStainedGlassStack> HARD_STAINED_GLASS_TYPE = empty(ItemHardStainedGlassStack.class, VanillaItemId.HARD_STAINED_GLASS);

    public static ItemType<ItemHardStainedGlassPaneStack> HARD_STAINED_GLASS_PANE_TYPE = empty(ItemHardStainedGlassPaneStack.class, VanillaItemId.HARD_STAINED_GLASS_PANE);

    public static ItemType<ItemHardWhiteStainedGlassStack> HARD_WHITE_STAINED_GLASS_TYPE = empty(ItemHardWhiteStainedGlassStack.class, VanillaItemId.HARD_WHITE_STAINED_GLASS);

    public static ItemType<ItemHardWhiteStainedGlassPaneStack> HARD_WHITE_STAINED_GLASS_PANE_TYPE = empty(ItemHardWhiteStainedGlassPaneStack.class, VanillaItemId.HARD_WHITE_STAINED_GLASS_PANE);

    public static ItemType<ItemHardYellowStainedGlassStack> HARD_YELLOW_STAINED_GLASS_TYPE = empty(ItemHardYellowStainedGlassStack.class, VanillaItemId.HARD_YELLOW_STAINED_GLASS);

    public static ItemType<ItemHardYellowStainedGlassPaneStack> HARD_YELLOW_STAINED_GLASS_PANE_TYPE = empty(ItemHardYellowStainedGlassPaneStack.class, VanillaItemId.HARD_YELLOW_STAINED_GLASS_PANE);

    public static ItemType<ItemHardenedClayStack> HARDENED_CLAY_TYPE = empty(ItemHardenedClayStack.class, VanillaItemId.HARDENED_CLAY);

    public static ItemType<ItemHayBlockStack> HAY_BLOCK_TYPE = empty(ItemHayBlockStack.class, VanillaItemId.HAY_BLOCK);

    public static ItemType<ItemHeartOfTheSeaStack> HEART_OF_THE_SEA_TYPE = empty(ItemHeartOfTheSeaStack.class, VanillaItemId.HEART_OF_THE_SEA);

    public static ItemType<ItemHeartPotterySherdStack> HEART_POTTERY_SHERD_TYPE = empty(ItemHeartPotterySherdStack.class, VanillaItemId.HEART_POTTERY_SHERD);

    public static ItemType<ItemHeartbreakPotterySherdStack> HEARTBREAK_POTTERY_SHERD_TYPE = empty(ItemHeartbreakPotterySherdStack.class, VanillaItemId.HEARTBREAK_POTTERY_SHERD);

    public static ItemType<ItemHeavyWeightedPressurePlateStack> HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE = empty(ItemHeavyWeightedPressurePlateStack.class, VanillaItemId.HEAVY_WEIGHTED_PRESSURE_PLATE);

    public static ItemType<ItemHoglinSpawnEggStack> HOGLIN_SPAWN_EGG_TYPE = empty(ItemHoglinSpawnEggStack.class, VanillaItemId.HOGLIN_SPAWN_EGG);

    public static ItemType<ItemHoneyBlockStack> HONEY_BLOCK_TYPE = empty(ItemHoneyBlockStack.class, VanillaItemId.HONEY_BLOCK);

    public static ItemType<ItemHoneyBottleStack> HONEY_BOTTLE_TYPE = empty(ItemHoneyBottleStack.class, VanillaItemId.HONEY_BOTTLE);

    public static ItemType<ItemHoneycombStack> HONEYCOMB_TYPE = empty(ItemHoneycombStack.class, VanillaItemId.HONEYCOMB);

    public static ItemType<ItemHoneycombBlockStack> HONEYCOMB_BLOCK_TYPE = empty(ItemHoneycombBlockStack.class, VanillaItemId.HONEYCOMB_BLOCK);

    public static ItemType<ItemHopperStack> HOPPER_TYPE = empty(ItemHopperStack.class, VanillaItemId.HOPPER);

    public static ItemType<ItemHopperMinecartStack> HOPPER_MINECART_TYPE = empty(ItemHopperMinecartStack.class, VanillaItemId.HOPPER_MINECART);

    public static ItemType<ItemHornCoralStack> HORN_CORAL_TYPE = empty(ItemHornCoralStack.class, VanillaItemId.HORN_CORAL);

    public static ItemType<ItemHorseSpawnEggStack> HORSE_SPAWN_EGG_TYPE = empty(ItemHorseSpawnEggStack.class, VanillaItemId.HORSE_SPAWN_EGG);

    public static ItemType<ItemHostArmorTrimSmithingTemplateStack> HOST_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemHostArmorTrimSmithingTemplateStack.class, VanillaItemId.HOST_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemHowlPotterySherdStack> HOWL_POTTERY_SHERD_TYPE = empty(ItemHowlPotterySherdStack.class, VanillaItemId.HOWL_POTTERY_SHERD);

    public static ItemType<ItemHuskSpawnEggStack> HUSK_SPAWN_EGG_TYPE = empty(ItemHuskSpawnEggStack.class, VanillaItemId.HUSK_SPAWN_EGG);

    public static ItemType<ItemIceStack> ICE_TYPE = empty(ItemIceStack.class, VanillaItemId.ICE);

    public static ItemType<ItemIceBombStack> ICE_BOMB_TYPE = empty(ItemIceBombStack.class, VanillaItemId.ICE_BOMB);

    public static ItemType<ItemInfestedDeepslateStack> INFESTED_DEEPSLATE_TYPE = empty(ItemInfestedDeepslateStack.class, VanillaItemId.INFESTED_DEEPSLATE);

    public static ItemType<ItemInfoUpdateStack> INFO_UPDATE_TYPE = empty(ItemInfoUpdateStack.class, VanillaItemId.INFO_UPDATE);

    public static ItemType<ItemInfoUpdate2Stack> INFO_UPDATE2_TYPE = empty(ItemInfoUpdate2Stack.class, VanillaItemId.INFO_UPDATE2);

    public static ItemType<ItemInkSacStack> INK_SAC_TYPE = empty(ItemInkSacStack.class, VanillaItemId.INK_SAC);

    public static ItemType<ItemInvisibleBedrockStack> INVISIBLE_BEDROCK_TYPE = empty(ItemInvisibleBedrockStack.class, VanillaItemId.INVISIBLE_BEDROCK);

    public static ItemType<ItemIronAxeStack> IRON_AXE_TYPE = empty(ItemIronAxeStack.class, VanillaItemId.IRON_AXE);

    public static ItemType<ItemIronBarsStack> IRON_BARS_TYPE = empty(ItemIronBarsStack.class, VanillaItemId.IRON_BARS);

    public static ItemType<ItemIronBlockStack> IRON_BLOCK_TYPE = empty(ItemIronBlockStack.class, VanillaItemId.IRON_BLOCK);

    public static ItemType<ItemIronBootsStack> IRON_BOOTS_TYPE = empty(ItemIronBootsStack.class, VanillaItemId.IRON_BOOTS);

    public static ItemType<ItemIronChestplateStack> IRON_CHESTPLATE_TYPE = empty(ItemIronChestplateStack.class, VanillaItemId.IRON_CHESTPLATE);

    public static ItemType<ItemIronDoorStack> IRON_DOOR_TYPE = empty(ItemIronDoorStack.class, VanillaItemId.IRON_DOOR);

    public static ItemType<ItemIronGolemSpawnEggStack> IRON_GOLEM_SPAWN_EGG_TYPE = empty(ItemIronGolemSpawnEggStack.class, VanillaItemId.IRON_GOLEM_SPAWN_EGG);

    public static ItemType<ItemIronHelmetStack> IRON_HELMET_TYPE = empty(ItemIronHelmetStack.class, VanillaItemId.IRON_HELMET);

    public static ItemType<ItemIronHoeStack> IRON_HOE_TYPE = empty(ItemIronHoeStack.class, VanillaItemId.IRON_HOE);

    public static ItemType<ItemIronHorseArmorStack> IRON_HORSE_ARMOR_TYPE = empty(ItemIronHorseArmorStack.class, VanillaItemId.IRON_HORSE_ARMOR);

    public static ItemType<ItemIronIngotStack> IRON_INGOT_TYPE = empty(ItemIronIngotStack.class, VanillaItemId.IRON_INGOT);

    public static ItemType<ItemIronLeggingsStack> IRON_LEGGINGS_TYPE = empty(ItemIronLeggingsStack.class, VanillaItemId.IRON_LEGGINGS);

    public static ItemType<ItemIronNuggetStack> IRON_NUGGET_TYPE = empty(ItemIronNuggetStack.class, VanillaItemId.IRON_NUGGET);

    public static ItemType<ItemIronOreStack> IRON_ORE_TYPE = empty(ItemIronOreStack.class, VanillaItemId.IRON_ORE);

    public static ItemType<ItemIronPickaxeStack> IRON_PICKAXE_TYPE = empty(ItemIronPickaxeStack.class, VanillaItemId.IRON_PICKAXE);

    public static ItemType<ItemIronShovelStack> IRON_SHOVEL_TYPE = empty(ItemIronShovelStack.class, VanillaItemId.IRON_SHOVEL);

    public static ItemType<ItemIronSwordStack> IRON_SWORD_TYPE = empty(ItemIronSwordStack.class, VanillaItemId.IRON_SWORD);

    public static ItemType<ItemIronTrapdoorStack> IRON_TRAPDOOR_TYPE = empty(ItemIronTrapdoorStack.class, VanillaItemId.IRON_TRAPDOOR);

    public static ItemType<ItemItemAcaciaDoorStack> ITEM_ACACIA_DOOR_TYPE = empty(ItemItemAcaciaDoorStack.class, VanillaItemId.ITEM_ACACIA_DOOR);

    public static ItemType<ItemItemBedStack> ITEM_BED_TYPE = empty(ItemItemBedStack.class, VanillaItemId.ITEM_BED);

    public static ItemType<ItemItemBeetrootStack> ITEM_BEETROOT_TYPE = empty(ItemItemBeetrootStack.class, VanillaItemId.ITEM_BEETROOT);

    public static ItemType<ItemItemBirchDoorStack> ITEM_BIRCH_DOOR_TYPE = empty(ItemItemBirchDoorStack.class, VanillaItemId.ITEM_BIRCH_DOOR);

    public static ItemType<ItemItemBrewingStandStack> ITEM_BREWING_STAND_TYPE = empty(ItemItemBrewingStandStack.class, VanillaItemId.ITEM_BREWING_STAND);

    public static ItemType<ItemItemCakeStack> ITEM_CAKE_TYPE = empty(ItemItemCakeStack.class, VanillaItemId.ITEM_CAKE);

    public static ItemType<ItemItemCameraStack> ITEM_CAMERA_TYPE = empty(ItemItemCameraStack.class, VanillaItemId.ITEM_CAMERA);

    public static ItemType<ItemItemCampfireStack> ITEM_CAMPFIRE_TYPE = empty(ItemItemCampfireStack.class, VanillaItemId.ITEM_CAMPFIRE);

    public static ItemType<ItemItemCauldronStack> ITEM_CAULDRON_TYPE = empty(ItemItemCauldronStack.class, VanillaItemId.ITEM_CAULDRON);

    public static ItemType<ItemItemChainStack> ITEM_CHAIN_TYPE = empty(ItemItemChainStack.class, VanillaItemId.ITEM_CHAIN);

    public static ItemType<ItemItemCrimsonDoorStack> ITEM_CRIMSON_DOOR_TYPE = empty(ItemItemCrimsonDoorStack.class, VanillaItemId.ITEM_CRIMSON_DOOR);

    public static ItemType<ItemItemDarkOakDoorStack> ITEM_DARK_OAK_DOOR_TYPE = empty(ItemItemDarkOakDoorStack.class, VanillaItemId.ITEM_DARK_OAK_DOOR);

    public static ItemType<ItemItemFlowerPotStack> ITEM_FLOWER_POT_TYPE = empty(ItemItemFlowerPotStack.class, VanillaItemId.ITEM_FLOWER_POT);

    public static ItemType<ItemItemFrameStack> ITEM_FRAME_TYPE = empty(ItemItemFrameStack.class, VanillaItemId.ITEM_FRAME);

    public static ItemType<ItemItemGlowFrameStack> ITEM_GLOW_FRAME_TYPE = empty(ItemItemGlowFrameStack.class, VanillaItemId.ITEM_GLOW_FRAME);

    public static ItemType<ItemItemHopperStack> ITEM_HOPPER_TYPE = empty(ItemItemHopperStack.class, VanillaItemId.ITEM_HOPPER);

    public static ItemType<ItemItemIronDoorStack> ITEM_IRON_DOOR_TYPE = empty(ItemItemIronDoorStack.class, VanillaItemId.ITEM_IRON_DOOR);

    public static ItemType<ItemItemJungleDoorStack> ITEM_JUNGLE_DOOR_TYPE = empty(ItemItemJungleDoorStack.class, VanillaItemId.ITEM_JUNGLE_DOOR);

    public static ItemType<ItemItemKelpStack> ITEM_KELP_TYPE = empty(ItemItemKelpStack.class, VanillaItemId.ITEM_KELP);

    public static ItemType<ItemItemMangroveDoorStack> ITEM_MANGROVE_DOOR_TYPE = empty(ItemItemMangroveDoorStack.class, VanillaItemId.ITEM_MANGROVE_DOOR);

    public static ItemType<ItemItemNetherSproutsStack> ITEM_NETHER_SPROUTS_TYPE = empty(ItemItemNetherSproutsStack.class, VanillaItemId.ITEM_NETHER_SPROUTS);

    public static ItemType<ItemItemNetherWartStack> ITEM_NETHER_WART_TYPE = empty(ItemItemNetherWartStack.class, VanillaItemId.ITEM_NETHER_WART);

    public static ItemType<ItemItemReedsStack> ITEM_REEDS_TYPE = empty(ItemItemReedsStack.class, VanillaItemId.ITEM_REEDS);

    public static ItemType<ItemItemSkullStack> ITEM_SKULL_TYPE = empty(ItemItemSkullStack.class, VanillaItemId.ITEM_SKULL);

    public static ItemType<ItemItemSoulCampfireStack> ITEM_SOUL_CAMPFIRE_TYPE = empty(ItemItemSoulCampfireStack.class, VanillaItemId.ITEM_SOUL_CAMPFIRE);

    public static ItemType<ItemItemSpruceDoorStack> ITEM_SPRUCE_DOOR_TYPE = empty(ItemItemSpruceDoorStack.class, VanillaItemId.ITEM_SPRUCE_DOOR);

    public static ItemType<ItemItemWarpedDoorStack> ITEM_WARPED_DOOR_TYPE = empty(ItemItemWarpedDoorStack.class, VanillaItemId.ITEM_WARPED_DOOR);

    public static ItemType<ItemItemWheatStack> ITEM_WHEAT_TYPE = empty(ItemItemWheatStack.class, VanillaItemId.ITEM_WHEAT);

    public static ItemType<ItemItemWoodenDoorStack> ITEM_WOODEN_DOOR_TYPE = empty(ItemItemWoodenDoorStack.class, VanillaItemId.ITEM_WOODEN_DOOR);

    public static ItemType<ItemJigsawStack> JIGSAW_TYPE = empty(ItemJigsawStack.class, VanillaItemId.JIGSAW);

    public static ItemType<ItemJukeboxStack> JUKEBOX_TYPE = empty(ItemJukeboxStack.class, VanillaItemId.JUKEBOX);

    public static ItemType<ItemJungleBoatStack> JUNGLE_BOAT_TYPE = empty(ItemJungleBoatStack.class, VanillaItemId.JUNGLE_BOAT);

    public static ItemType<ItemJungleButtonStack> JUNGLE_BUTTON_TYPE = empty(ItemJungleButtonStack.class, VanillaItemId.JUNGLE_BUTTON);

    public static ItemType<ItemJungleChestBoatStack> JUNGLE_CHEST_BOAT_TYPE = empty(ItemJungleChestBoatStack.class, VanillaItemId.JUNGLE_CHEST_BOAT);

    public static ItemType<ItemJungleDoorStack> JUNGLE_DOOR_TYPE = empty(ItemJungleDoorStack.class, VanillaItemId.JUNGLE_DOOR);

    public static ItemType<ItemJungleFenceStack> JUNGLE_FENCE_TYPE = empty(ItemJungleFenceStack.class, VanillaItemId.JUNGLE_FENCE);

    public static ItemType<ItemJungleFenceGateStack> JUNGLE_FENCE_GATE_TYPE = empty(ItemJungleFenceGateStack.class, VanillaItemId.JUNGLE_FENCE_GATE);

    public static ItemType<ItemJungleHangingSignStack> JUNGLE_HANGING_SIGN_TYPE = empty(ItemJungleHangingSignStack.class, VanillaItemId.JUNGLE_HANGING_SIGN);

    public static ItemType<ItemJungleLogStack> JUNGLE_LOG_TYPE = empty(ItemJungleLogStack.class, VanillaItemId.JUNGLE_LOG);

    public static ItemType<ItemJunglePlanksStack> JUNGLE_PLANKS_TYPE = empty(ItemJunglePlanksStack.class, VanillaItemId.JUNGLE_PLANKS);

    public static ItemType<ItemJunglePressurePlateStack> JUNGLE_PRESSURE_PLATE_TYPE = empty(ItemJunglePressurePlateStack.class, VanillaItemId.JUNGLE_PRESSURE_PLATE);

    public static ItemType<ItemJungleSignStack> JUNGLE_SIGN_TYPE = empty(ItemJungleSignStack.class, VanillaItemId.JUNGLE_SIGN);

    public static ItemType<ItemJungleStairsStack> JUNGLE_STAIRS_TYPE = empty(ItemJungleStairsStack.class, VanillaItemId.JUNGLE_STAIRS);

    public static ItemType<ItemJungleStandingSignStack> JUNGLE_STANDING_SIGN_TYPE = empty(ItemJungleStandingSignStack.class, VanillaItemId.JUNGLE_STANDING_SIGN);

    public static ItemType<ItemJungleTrapdoorStack> JUNGLE_TRAPDOOR_TYPE = empty(ItemJungleTrapdoorStack.class, VanillaItemId.JUNGLE_TRAPDOOR);

    public static ItemType<ItemJungleWallSignStack> JUNGLE_WALL_SIGN_TYPE = empty(ItemJungleWallSignStack.class, VanillaItemId.JUNGLE_WALL_SIGN);

    public static ItemType<ItemKelpStack> KELP_TYPE = empty(ItemKelpStack.class, VanillaItemId.KELP);

    public static ItemType<ItemLadderStack> LADDER_TYPE = empty(ItemLadderStack.class, VanillaItemId.LADDER);

    public static ItemType<ItemLanternStack> LANTERN_TYPE = empty(ItemLanternStack.class, VanillaItemId.LANTERN);

    public static ItemType<ItemLapisBlockStack> LAPIS_BLOCK_TYPE = empty(ItemLapisBlockStack.class, VanillaItemId.LAPIS_BLOCK);

    public static ItemType<ItemLapisLazuliStack> LAPIS_LAZULI_TYPE = empty(ItemLapisLazuliStack.class, VanillaItemId.LAPIS_LAZULI);

    public static ItemType<ItemLapisOreStack> LAPIS_ORE_TYPE = empty(ItemLapisOreStack.class, VanillaItemId.LAPIS_ORE);

    public static ItemType<ItemLargeAmethystBudStack> LARGE_AMETHYST_BUD_TYPE = empty(ItemLargeAmethystBudStack.class, VanillaItemId.LARGE_AMETHYST_BUD);

    public static ItemType<ItemLavaStack> LAVA_TYPE = empty(ItemLavaStack.class, VanillaItemId.LAVA);

    public static ItemType<ItemLavaBucketStack> LAVA_BUCKET_TYPE = empty(ItemLavaBucketStack.class, VanillaItemId.LAVA_BUCKET);

    public static ItemType<ItemLeadStack> LEAD_TYPE = empty(ItemLeadStack.class, VanillaItemId.LEAD);

    public static ItemType<ItemLeatherStack> LEATHER_TYPE = empty(ItemLeatherStack.class, VanillaItemId.LEATHER);

    public static ItemType<ItemLeatherBootsStack> LEATHER_BOOTS_TYPE = empty(ItemLeatherBootsStack.class, VanillaItemId.LEATHER_BOOTS);

    public static ItemType<ItemLeatherChestplateStack> LEATHER_CHESTPLATE_TYPE = empty(ItemLeatherChestplateStack.class, VanillaItemId.LEATHER_CHESTPLATE);

    public static ItemType<ItemLeatherHelmetStack> LEATHER_HELMET_TYPE = empty(ItemLeatherHelmetStack.class, VanillaItemId.LEATHER_HELMET);

    public static ItemType<ItemLeatherHorseArmorStack> LEATHER_HORSE_ARMOR_TYPE = empty(ItemLeatherHorseArmorStack.class, VanillaItemId.LEATHER_HORSE_ARMOR);

    public static ItemType<ItemLeatherLeggingsStack> LEATHER_LEGGINGS_TYPE = empty(ItemLeatherLeggingsStack.class, VanillaItemId.LEATHER_LEGGINGS);

    public static ItemType<ItemLeavesStack> LEAVES_TYPE = empty(ItemLeavesStack.class, VanillaItemId.LEAVES);

    public static ItemType<ItemLeaves2Stack> LEAVES2_TYPE = empty(ItemLeaves2Stack.class, VanillaItemId.LEAVES2);

    public static ItemType<ItemLecternStack> LECTERN_TYPE = empty(ItemLecternStack.class, VanillaItemId.LECTERN);

    public static ItemType<ItemLeverStack> LEVER_TYPE = empty(ItemLeverStack.class, VanillaItemId.LEVER);

    public static ItemType<ItemLightBlockStack> LIGHT_BLOCK_TYPE = empty(ItemLightBlockStack.class, VanillaItemId.LIGHT_BLOCK);

    public static ItemType<ItemLightBlueCandleStack> LIGHT_BLUE_CANDLE_TYPE = empty(ItemLightBlueCandleStack.class, VanillaItemId.LIGHT_BLUE_CANDLE);

    public static ItemType<ItemLightBlueCandleCakeStack> LIGHT_BLUE_CANDLE_CAKE_TYPE = empty(ItemLightBlueCandleCakeStack.class, VanillaItemId.LIGHT_BLUE_CANDLE_CAKE);

    public static ItemType<ItemLightBlueCarpetStack> LIGHT_BLUE_CARPET_TYPE = empty(ItemLightBlueCarpetStack.class, VanillaItemId.LIGHT_BLUE_CARPET);

    public static ItemType<ItemLightBlueConcreteStack> LIGHT_BLUE_CONCRETE_TYPE = empty(ItemLightBlueConcreteStack.class, VanillaItemId.LIGHT_BLUE_CONCRETE);

    public static ItemType<ItemLightBlueConcretePowderStack> LIGHT_BLUE_CONCRETE_POWDER_TYPE = empty(ItemLightBlueConcretePowderStack.class, VanillaItemId.LIGHT_BLUE_CONCRETE_POWDER);

    public static ItemType<ItemLightBlueDyeStack> LIGHT_BLUE_DYE_TYPE = empty(ItemLightBlueDyeStack.class, VanillaItemId.LIGHT_BLUE_DYE);

    public static ItemType<ItemLightBlueGlazedTerracottaStack> LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE = empty(ItemLightBlueGlazedTerracottaStack.class, VanillaItemId.LIGHT_BLUE_GLAZED_TERRACOTTA);

    public static ItemType<ItemLightBlueShulkerBoxStack> LIGHT_BLUE_SHULKER_BOX_TYPE = empty(ItemLightBlueShulkerBoxStack.class, VanillaItemId.LIGHT_BLUE_SHULKER_BOX);

    public static ItemType<ItemLightBlueStainedGlassStack> LIGHT_BLUE_STAINED_GLASS_TYPE = empty(ItemLightBlueStainedGlassStack.class, VanillaItemId.LIGHT_BLUE_STAINED_GLASS);

    public static ItemType<ItemLightBlueStainedGlassPaneStack> LIGHT_BLUE_STAINED_GLASS_PANE_TYPE = empty(ItemLightBlueStainedGlassPaneStack.class, VanillaItemId.LIGHT_BLUE_STAINED_GLASS_PANE);

    public static ItemType<ItemLightBlueTerracottaStack> LIGHT_BLUE_TERRACOTTA_TYPE = empty(ItemLightBlueTerracottaStack.class, VanillaItemId.LIGHT_BLUE_TERRACOTTA);

    public static ItemType<ItemLightBlueWoolStack> LIGHT_BLUE_WOOL_TYPE = empty(ItemLightBlueWoolStack.class, VanillaItemId.LIGHT_BLUE_WOOL);

    public static ItemType<ItemLightGrayCandleStack> LIGHT_GRAY_CANDLE_TYPE = empty(ItemLightGrayCandleStack.class, VanillaItemId.LIGHT_GRAY_CANDLE);

    public static ItemType<ItemLightGrayCandleCakeStack> LIGHT_GRAY_CANDLE_CAKE_TYPE = empty(ItemLightGrayCandleCakeStack.class, VanillaItemId.LIGHT_GRAY_CANDLE_CAKE);

    public static ItemType<ItemLightGrayCarpetStack> LIGHT_GRAY_CARPET_TYPE = empty(ItemLightGrayCarpetStack.class, VanillaItemId.LIGHT_GRAY_CARPET);

    public static ItemType<ItemLightGrayConcreteStack> LIGHT_GRAY_CONCRETE_TYPE = empty(ItemLightGrayConcreteStack.class, VanillaItemId.LIGHT_GRAY_CONCRETE);

    public static ItemType<ItemLightGrayConcretePowderStack> LIGHT_GRAY_CONCRETE_POWDER_TYPE = empty(ItemLightGrayConcretePowderStack.class, VanillaItemId.LIGHT_GRAY_CONCRETE_POWDER);

    public static ItemType<ItemLightGrayDyeStack> LIGHT_GRAY_DYE_TYPE = empty(ItemLightGrayDyeStack.class, VanillaItemId.LIGHT_GRAY_DYE);

    public static ItemType<ItemLightGrayShulkerBoxStack> LIGHT_GRAY_SHULKER_BOX_TYPE = empty(ItemLightGrayShulkerBoxStack.class, VanillaItemId.LIGHT_GRAY_SHULKER_BOX);

    public static ItemType<ItemLightGrayStainedGlassStack> LIGHT_GRAY_STAINED_GLASS_TYPE = empty(ItemLightGrayStainedGlassStack.class, VanillaItemId.LIGHT_GRAY_STAINED_GLASS);

    public static ItemType<ItemLightGrayStainedGlassPaneStack> LIGHT_GRAY_STAINED_GLASS_PANE_TYPE = empty(ItemLightGrayStainedGlassPaneStack.class, VanillaItemId.LIGHT_GRAY_STAINED_GLASS_PANE);

    public static ItemType<ItemLightGrayTerracottaStack> LIGHT_GRAY_TERRACOTTA_TYPE = empty(ItemLightGrayTerracottaStack.class, VanillaItemId.LIGHT_GRAY_TERRACOTTA);

    public static ItemType<ItemLightGrayWoolStack> LIGHT_GRAY_WOOL_TYPE = empty(ItemLightGrayWoolStack.class, VanillaItemId.LIGHT_GRAY_WOOL);

    public static ItemType<ItemLightWeightedPressurePlateStack> LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE = empty(ItemLightWeightedPressurePlateStack.class, VanillaItemId.LIGHT_WEIGHTED_PRESSURE_PLATE);

    public static ItemType<ItemLightningRodStack> LIGHTNING_ROD_TYPE = empty(ItemLightningRodStack.class, VanillaItemId.LIGHTNING_ROD);

    public static ItemType<ItemLimeCandleStack> LIME_CANDLE_TYPE = empty(ItemLimeCandleStack.class, VanillaItemId.LIME_CANDLE);

    public static ItemType<ItemLimeCandleCakeStack> LIME_CANDLE_CAKE_TYPE = empty(ItemLimeCandleCakeStack.class, VanillaItemId.LIME_CANDLE_CAKE);

    public static ItemType<ItemLimeCarpetStack> LIME_CARPET_TYPE = empty(ItemLimeCarpetStack.class, VanillaItemId.LIME_CARPET);

    public static ItemType<ItemLimeConcreteStack> LIME_CONCRETE_TYPE = empty(ItemLimeConcreteStack.class, VanillaItemId.LIME_CONCRETE);

    public static ItemType<ItemLimeConcretePowderStack> LIME_CONCRETE_POWDER_TYPE = empty(ItemLimeConcretePowderStack.class, VanillaItemId.LIME_CONCRETE_POWDER);

    public static ItemType<ItemLimeDyeStack> LIME_DYE_TYPE = empty(ItemLimeDyeStack.class, VanillaItemId.LIME_DYE);

    public static ItemType<ItemLimeGlazedTerracottaStack> LIME_GLAZED_TERRACOTTA_TYPE = empty(ItemLimeGlazedTerracottaStack.class, VanillaItemId.LIME_GLAZED_TERRACOTTA);

    public static ItemType<ItemLimeShulkerBoxStack> LIME_SHULKER_BOX_TYPE = empty(ItemLimeShulkerBoxStack.class, VanillaItemId.LIME_SHULKER_BOX);

    public static ItemType<ItemLimeStainedGlassStack> LIME_STAINED_GLASS_TYPE = empty(ItemLimeStainedGlassStack.class, VanillaItemId.LIME_STAINED_GLASS);

    public static ItemType<ItemLimeStainedGlassPaneStack> LIME_STAINED_GLASS_PANE_TYPE = empty(ItemLimeStainedGlassPaneStack.class, VanillaItemId.LIME_STAINED_GLASS_PANE);

    public static ItemType<ItemLimeTerracottaStack> LIME_TERRACOTTA_TYPE = empty(ItemLimeTerracottaStack.class, VanillaItemId.LIME_TERRACOTTA);

    public static ItemType<ItemLimeWoolStack> LIME_WOOL_TYPE = empty(ItemLimeWoolStack.class, VanillaItemId.LIME_WOOL);

    public static ItemType<ItemLingeringPotionStack> LINGERING_POTION_TYPE = empty(ItemLingeringPotionStack.class, VanillaItemId.LINGERING_POTION);

    public static ItemType<ItemLitBlastFurnaceStack> LIT_BLAST_FURNACE_TYPE = empty(ItemLitBlastFurnaceStack.class, VanillaItemId.LIT_BLAST_FURNACE);

    public static ItemType<ItemLitDeepslateRedstoneOreStack> LIT_DEEPSLATE_REDSTONE_ORE_TYPE = empty(ItemLitDeepslateRedstoneOreStack.class, VanillaItemId.LIT_DEEPSLATE_REDSTONE_ORE);

    public static ItemType<ItemLitFurnaceStack> LIT_FURNACE_TYPE = empty(ItemLitFurnaceStack.class, VanillaItemId.LIT_FURNACE);

    public static ItemType<ItemLitPumpkinStack> LIT_PUMPKIN_TYPE = empty(ItemLitPumpkinStack.class, VanillaItemId.LIT_PUMPKIN);

    public static ItemType<ItemLitRedstoneLampStack> LIT_REDSTONE_LAMP_TYPE = empty(ItemLitRedstoneLampStack.class, VanillaItemId.LIT_REDSTONE_LAMP);

    public static ItemType<ItemLitRedstoneOreStack> LIT_REDSTONE_ORE_TYPE = empty(ItemLitRedstoneOreStack.class, VanillaItemId.LIT_REDSTONE_ORE);

    public static ItemType<ItemLitSmokerStack> LIT_SMOKER_TYPE = empty(ItemLitSmokerStack.class, VanillaItemId.LIT_SMOKER);

    public static ItemType<ItemLlamaSpawnEggStack> LLAMA_SPAWN_EGG_TYPE = empty(ItemLlamaSpawnEggStack.class, VanillaItemId.LLAMA_SPAWN_EGG);

    public static ItemType<ItemLodestoneStack> LODESTONE_TYPE = empty(ItemLodestoneStack.class, VanillaItemId.LODESTONE);

    public static ItemType<ItemLodestoneCompassStack> LODESTONE_COMPASS_TYPE = empty(ItemLodestoneCompassStack.class, VanillaItemId.LODESTONE_COMPASS);

    public static ItemType<ItemLogStack> LOG_TYPE = empty(ItemLogStack.class, VanillaItemId.LOG);

    public static ItemType<ItemLog2Stack> LOG2_TYPE = empty(ItemLog2Stack.class, VanillaItemId.LOG2);

    public static ItemType<ItemLoomStack> LOOM_TYPE = empty(ItemLoomStack.class, VanillaItemId.LOOM);

    public static ItemType<ItemMagentaCandleStack> MAGENTA_CANDLE_TYPE = empty(ItemMagentaCandleStack.class, VanillaItemId.MAGENTA_CANDLE);

    public static ItemType<ItemMagentaCandleCakeStack> MAGENTA_CANDLE_CAKE_TYPE = empty(ItemMagentaCandleCakeStack.class, VanillaItemId.MAGENTA_CANDLE_CAKE);

    public static ItemType<ItemMagentaCarpetStack> MAGENTA_CARPET_TYPE = empty(ItemMagentaCarpetStack.class, VanillaItemId.MAGENTA_CARPET);

    public static ItemType<ItemMagentaConcreteStack> MAGENTA_CONCRETE_TYPE = empty(ItemMagentaConcreteStack.class, VanillaItemId.MAGENTA_CONCRETE);

    public static ItemType<ItemMagentaConcretePowderStack> MAGENTA_CONCRETE_POWDER_TYPE = empty(ItemMagentaConcretePowderStack.class, VanillaItemId.MAGENTA_CONCRETE_POWDER);

    public static ItemType<ItemMagentaDyeStack> MAGENTA_DYE_TYPE = empty(ItemMagentaDyeStack.class, VanillaItemId.MAGENTA_DYE);

    public static ItemType<ItemMagentaGlazedTerracottaStack> MAGENTA_GLAZED_TERRACOTTA_TYPE = empty(ItemMagentaGlazedTerracottaStack.class, VanillaItemId.MAGENTA_GLAZED_TERRACOTTA);

    public static ItemType<ItemMagentaShulkerBoxStack> MAGENTA_SHULKER_BOX_TYPE = empty(ItemMagentaShulkerBoxStack.class, VanillaItemId.MAGENTA_SHULKER_BOX);

    public static ItemType<ItemMagentaStainedGlassStack> MAGENTA_STAINED_GLASS_TYPE = empty(ItemMagentaStainedGlassStack.class, VanillaItemId.MAGENTA_STAINED_GLASS);

    public static ItemType<ItemMagentaStainedGlassPaneStack> MAGENTA_STAINED_GLASS_PANE_TYPE = empty(ItemMagentaStainedGlassPaneStack.class, VanillaItemId.MAGENTA_STAINED_GLASS_PANE);

    public static ItemType<ItemMagentaTerracottaStack> MAGENTA_TERRACOTTA_TYPE = empty(ItemMagentaTerracottaStack.class, VanillaItemId.MAGENTA_TERRACOTTA);

    public static ItemType<ItemMagentaWoolStack> MAGENTA_WOOL_TYPE = empty(ItemMagentaWoolStack.class, VanillaItemId.MAGENTA_WOOL);

    public static ItemType<ItemMagmaStack> MAGMA_TYPE = empty(ItemMagmaStack.class, VanillaItemId.MAGMA);

    public static ItemType<ItemMagmaCreamStack> MAGMA_CREAM_TYPE = empty(ItemMagmaCreamStack.class, VanillaItemId.MAGMA_CREAM);

    public static ItemType<ItemMagmaCubeSpawnEggStack> MAGMA_CUBE_SPAWN_EGG_TYPE = empty(ItemMagmaCubeSpawnEggStack.class, VanillaItemId.MAGMA_CUBE_SPAWN_EGG);

    public static ItemType<ItemMangroveBoatStack> MANGROVE_BOAT_TYPE = empty(ItemMangroveBoatStack.class, VanillaItemId.MANGROVE_BOAT);

    public static ItemType<ItemMangroveButtonStack> MANGROVE_BUTTON_TYPE = empty(ItemMangroveButtonStack.class, VanillaItemId.MANGROVE_BUTTON);

    public static ItemType<ItemMangroveChestBoatStack> MANGROVE_CHEST_BOAT_TYPE = empty(ItemMangroveChestBoatStack.class, VanillaItemId.MANGROVE_CHEST_BOAT);

    public static ItemType<ItemMangroveDoorStack> MANGROVE_DOOR_TYPE = empty(ItemMangroveDoorStack.class, VanillaItemId.MANGROVE_DOOR);

    public static ItemType<ItemMangroveDoubleSlabStack> MANGROVE_DOUBLE_SLAB_TYPE = empty(ItemMangroveDoubleSlabStack.class, VanillaItemId.MANGROVE_DOUBLE_SLAB);

    public static ItemType<ItemMangroveFenceStack> MANGROVE_FENCE_TYPE = empty(ItemMangroveFenceStack.class, VanillaItemId.MANGROVE_FENCE);

    public static ItemType<ItemMangroveFenceGateStack> MANGROVE_FENCE_GATE_TYPE = empty(ItemMangroveFenceGateStack.class, VanillaItemId.MANGROVE_FENCE_GATE);

    public static ItemType<ItemMangroveHangingSignStack> MANGROVE_HANGING_SIGN_TYPE = empty(ItemMangroveHangingSignStack.class, VanillaItemId.MANGROVE_HANGING_SIGN);

    public static ItemType<ItemMangroveLeavesStack> MANGROVE_LEAVES_TYPE = empty(ItemMangroveLeavesStack.class, VanillaItemId.MANGROVE_LEAVES);

    public static ItemType<ItemMangroveLogStack> MANGROVE_LOG_TYPE = empty(ItemMangroveLogStack.class, VanillaItemId.MANGROVE_LOG);

    public static ItemType<ItemMangrovePlanksStack> MANGROVE_PLANKS_TYPE = empty(ItemMangrovePlanksStack.class, VanillaItemId.MANGROVE_PLANKS);

    public static ItemType<ItemMangrovePressurePlateStack> MANGROVE_PRESSURE_PLATE_TYPE = empty(ItemMangrovePressurePlateStack.class, VanillaItemId.MANGROVE_PRESSURE_PLATE);

    public static ItemType<ItemMangrovePropaguleStack> MANGROVE_PROPAGULE_TYPE = empty(ItemMangrovePropaguleStack.class, VanillaItemId.MANGROVE_PROPAGULE);

    public static ItemType<ItemMangroveRootsStack> MANGROVE_ROOTS_TYPE = empty(ItemMangroveRootsStack.class, VanillaItemId.MANGROVE_ROOTS);

    public static ItemType<ItemMangroveSignStack> MANGROVE_SIGN_TYPE = empty(ItemMangroveSignStack.class, VanillaItemId.MANGROVE_SIGN);

    public static ItemType<ItemMangroveSlabStack> MANGROVE_SLAB_TYPE = empty(ItemMangroveSlabStack.class, VanillaItemId.MANGROVE_SLAB);

    public static ItemType<ItemMangroveStairsStack> MANGROVE_STAIRS_TYPE = empty(ItemMangroveStairsStack.class, VanillaItemId.MANGROVE_STAIRS);

    public static ItemType<ItemMangroveStandingSignStack> MANGROVE_STANDING_SIGN_TYPE = empty(ItemMangroveStandingSignStack.class, VanillaItemId.MANGROVE_STANDING_SIGN);

    public static ItemType<ItemMangroveTrapdoorStack> MANGROVE_TRAPDOOR_TYPE = empty(ItemMangroveTrapdoorStack.class, VanillaItemId.MANGROVE_TRAPDOOR);

    public static ItemType<ItemMangroveWallSignStack> MANGROVE_WALL_SIGN_TYPE = empty(ItemMangroveWallSignStack.class, VanillaItemId.MANGROVE_WALL_SIGN);

    public static ItemType<ItemMangroveWoodStack> MANGROVE_WOOD_TYPE = empty(ItemMangroveWoodStack.class, VanillaItemId.MANGROVE_WOOD);

    public static ItemType<ItemMedicineStack> MEDICINE_TYPE = empty(ItemMedicineStack.class, VanillaItemId.MEDICINE);

    public static ItemType<ItemMediumAmethystBudStack> MEDIUM_AMETHYST_BUD_TYPE = empty(ItemMediumAmethystBudStack.class, VanillaItemId.MEDIUM_AMETHYST_BUD);

    public static ItemType<ItemMelonBlockStack> MELON_BLOCK_TYPE = empty(ItemMelonBlockStack.class, VanillaItemId.MELON_BLOCK);

    public static ItemType<ItemMelonSeedsStack> MELON_SEEDS_TYPE = empty(ItemMelonSeedsStack.class, VanillaItemId.MELON_SEEDS);

    public static ItemType<ItemMelonSliceStack> MELON_SLICE_TYPE = empty(ItemMelonSliceStack.class, VanillaItemId.MELON_SLICE);

    public static ItemType<ItemMelonStemStack> MELON_STEM_TYPE = empty(ItemMelonStemStack.class, VanillaItemId.MELON_STEM);

    public static ItemType<ItemMilkBucketStack> MILK_BUCKET_TYPE = empty(ItemMilkBucketStack.class, VanillaItemId.MILK_BUCKET);

    public static ItemType<ItemMinecartStack> MINECART_TYPE = empty(ItemMinecartStack.class, VanillaItemId.MINECART);

    public static ItemType<ItemMinerPotterySherdStack> MINER_POTTERY_SHERD_TYPE = empty(ItemMinerPotterySherdStack.class, VanillaItemId.MINER_POTTERY_SHERD);

    public static ItemType<ItemMobSpawnerStack> MOB_SPAWNER_TYPE = empty(ItemMobSpawnerStack.class, VanillaItemId.MOB_SPAWNER);

    public static ItemType<ItemMojangBannerPatternStack> MOJANG_BANNER_PATTERN_TYPE = empty(ItemMojangBannerPatternStack.class, VanillaItemId.MOJANG_BANNER_PATTERN);

    public static ItemType<ItemMonsterEggStack> MONSTER_EGG_TYPE = empty(ItemMonsterEggStack.class, VanillaItemId.MONSTER_EGG);

    public static ItemType<ItemMooshroomSpawnEggStack> MOOSHROOM_SPAWN_EGG_TYPE = empty(ItemMooshroomSpawnEggStack.class, VanillaItemId.MOOSHROOM_SPAWN_EGG);

    public static ItemType<ItemMossBlockStack> MOSS_BLOCK_TYPE = empty(ItemMossBlockStack.class, VanillaItemId.MOSS_BLOCK);

    public static ItemType<ItemMossCarpetStack> MOSS_CARPET_TYPE = empty(ItemMossCarpetStack.class, VanillaItemId.MOSS_CARPET);

    public static ItemType<ItemMossyCobblestoneStack> MOSSY_COBBLESTONE_TYPE = empty(ItemMossyCobblestoneStack.class, VanillaItemId.MOSSY_COBBLESTONE);

    public static ItemType<ItemMossyCobblestoneStairsStack> MOSSY_COBBLESTONE_STAIRS_TYPE = empty(ItemMossyCobblestoneStairsStack.class, VanillaItemId.MOSSY_COBBLESTONE_STAIRS);

    public static ItemType<ItemMossyStoneBrickStairsStack> MOSSY_STONE_BRICK_STAIRS_TYPE = empty(ItemMossyStoneBrickStairsStack.class, VanillaItemId.MOSSY_STONE_BRICK_STAIRS);

    public static ItemType<ItemMournerPotterySherdStack> MOURNER_POTTERY_SHERD_TYPE = empty(ItemMournerPotterySherdStack.class, VanillaItemId.MOURNER_POTTERY_SHERD);

    public static ItemType<ItemMovingBlockStack> MOVING_BLOCK_TYPE = empty(ItemMovingBlockStack.class, VanillaItemId.MOVING_BLOCK);

    public static ItemType<ItemMudStack> MUD_TYPE = empty(ItemMudStack.class, VanillaItemId.MUD);

    public static ItemType<ItemMudBrickDoubleSlabStack> MUD_BRICK_DOUBLE_SLAB_TYPE = empty(ItemMudBrickDoubleSlabStack.class, VanillaItemId.MUD_BRICK_DOUBLE_SLAB);

    public static ItemType<ItemMudBrickSlabStack> MUD_BRICK_SLAB_TYPE = empty(ItemMudBrickSlabStack.class, VanillaItemId.MUD_BRICK_SLAB);

    public static ItemType<ItemMudBrickStairsStack> MUD_BRICK_STAIRS_TYPE = empty(ItemMudBrickStairsStack.class, VanillaItemId.MUD_BRICK_STAIRS);

    public static ItemType<ItemMudBrickWallStack> MUD_BRICK_WALL_TYPE = empty(ItemMudBrickWallStack.class, VanillaItemId.MUD_BRICK_WALL);

    public static ItemType<ItemMudBricksStack> MUD_BRICKS_TYPE = empty(ItemMudBricksStack.class, VanillaItemId.MUD_BRICKS);

    public static ItemType<ItemMuddyMangroveRootsStack> MUDDY_MANGROVE_ROOTS_TYPE = empty(ItemMuddyMangroveRootsStack.class, VanillaItemId.MUDDY_MANGROVE_ROOTS);

    public static ItemType<ItemMuleSpawnEggStack> MULE_SPAWN_EGG_TYPE = empty(ItemMuleSpawnEggStack.class, VanillaItemId.MULE_SPAWN_EGG);

    public static ItemType<ItemMushroomStewStack> MUSHROOM_STEW_TYPE = empty(ItemMushroomStewStack.class, VanillaItemId.MUSHROOM_STEW);

    public static ItemType<ItemMusicDisc11Stack> MUSIC_DISC_11_TYPE = empty(ItemMusicDisc11Stack.class, VanillaItemId.MUSIC_DISC_11);

    public static ItemType<ItemMusicDisc13Stack> MUSIC_DISC_13_TYPE = empty(ItemMusicDisc13Stack.class, VanillaItemId.MUSIC_DISC_13);

    public static ItemType<ItemMusicDisc5Stack> MUSIC_DISC_5_TYPE = empty(ItemMusicDisc5Stack.class, VanillaItemId.MUSIC_DISC_5);

    public static ItemType<ItemMusicDiscBlocksStack> MUSIC_DISC_BLOCKS_TYPE = empty(ItemMusicDiscBlocksStack.class, VanillaItemId.MUSIC_DISC_BLOCKS);

    public static ItemType<ItemMusicDiscCatStack> MUSIC_DISC_CAT_TYPE = empty(ItemMusicDiscCatStack.class, VanillaItemId.MUSIC_DISC_CAT);

    public static ItemType<ItemMusicDiscChirpStack> MUSIC_DISC_CHIRP_TYPE = empty(ItemMusicDiscChirpStack.class, VanillaItemId.MUSIC_DISC_CHIRP);

    public static ItemType<ItemMusicDiscFarStack> MUSIC_DISC_FAR_TYPE = empty(ItemMusicDiscFarStack.class, VanillaItemId.MUSIC_DISC_FAR);

    public static ItemType<ItemMusicDiscMallStack> MUSIC_DISC_MALL_TYPE = empty(ItemMusicDiscMallStack.class, VanillaItemId.MUSIC_DISC_MALL);

    public static ItemType<ItemMusicDiscMellohiStack> MUSIC_DISC_MELLOHI_TYPE = empty(ItemMusicDiscMellohiStack.class, VanillaItemId.MUSIC_DISC_MELLOHI);

    public static ItemType<ItemMusicDiscOthersideStack> MUSIC_DISC_OTHERSIDE_TYPE = empty(ItemMusicDiscOthersideStack.class, VanillaItemId.MUSIC_DISC_OTHERSIDE);

    public static ItemType<ItemMusicDiscPigstepStack> MUSIC_DISC_PIGSTEP_TYPE = empty(ItemMusicDiscPigstepStack.class, VanillaItemId.MUSIC_DISC_PIGSTEP);

    public static ItemType<ItemMusicDiscRelicStack> MUSIC_DISC_RELIC_TYPE = empty(ItemMusicDiscRelicStack.class, VanillaItemId.MUSIC_DISC_RELIC);

    public static ItemType<ItemMusicDiscStalStack> MUSIC_DISC_STAL_TYPE = empty(ItemMusicDiscStalStack.class, VanillaItemId.MUSIC_DISC_STAL);

    public static ItemType<ItemMusicDiscStradStack> MUSIC_DISC_STRAD_TYPE = empty(ItemMusicDiscStradStack.class, VanillaItemId.MUSIC_DISC_STRAD);

    public static ItemType<ItemMusicDiscWaitStack> MUSIC_DISC_WAIT_TYPE = empty(ItemMusicDiscWaitStack.class, VanillaItemId.MUSIC_DISC_WAIT);

    public static ItemType<ItemMusicDiscWardStack> MUSIC_DISC_WARD_TYPE = empty(ItemMusicDiscWardStack.class, VanillaItemId.MUSIC_DISC_WARD);

    public static ItemType<ItemMuttonStack> MUTTON_TYPE = empty(ItemMuttonStack.class, VanillaItemId.MUTTON);

    public static ItemType<ItemMyceliumStack> MYCELIUM_TYPE = empty(ItemMyceliumStack.class, VanillaItemId.MYCELIUM);

    public static ItemType<ItemNameTagStack> NAME_TAG_TYPE = empty(ItemNameTagStack.class, VanillaItemId.NAME_TAG);

    public static ItemType<ItemNautilusShellStack> NAUTILUS_SHELL_TYPE = empty(ItemNautilusShellStack.class, VanillaItemId.NAUTILUS_SHELL);

    public static ItemType<ItemNetherBrickStack> NETHER_BRICK_TYPE = empty(ItemNetherBrickStack.class, VanillaItemId.NETHER_BRICK);

    public static ItemType<ItemNetherBrickFenceStack> NETHER_BRICK_FENCE_TYPE = empty(ItemNetherBrickFenceStack.class, VanillaItemId.NETHER_BRICK_FENCE);

    public static ItemType<ItemNetherBrickStairsStack> NETHER_BRICK_STAIRS_TYPE = empty(ItemNetherBrickStairsStack.class, VanillaItemId.NETHER_BRICK_STAIRS);

    public static ItemType<ItemNetherGoldOreStack> NETHER_GOLD_ORE_TYPE = empty(ItemNetherGoldOreStack.class, VanillaItemId.NETHER_GOLD_ORE);

    public static ItemType<ItemNetherSproutsStack> NETHER_SPROUTS_TYPE = empty(ItemNetherSproutsStack.class, VanillaItemId.NETHER_SPROUTS);

    public static ItemType<ItemNetherStarStack> NETHER_STAR_TYPE = empty(ItemNetherStarStack.class, VanillaItemId.NETHER_STAR);

    public static ItemType<ItemNetherWartStack> NETHER_WART_TYPE = empty(ItemNetherWartStack.class, VanillaItemId.NETHER_WART);

    public static ItemType<ItemNetherWartBlockStack> NETHER_WART_BLOCK_TYPE = empty(ItemNetherWartBlockStack.class, VanillaItemId.NETHER_WART_BLOCK);

    public static ItemType<ItemNetherbrick0Stack> NETHERBRICK_TYPE = empty(ItemNetherbrick0Stack.class, VanillaItemId.NETHERBRICK);

    public static ItemType<ItemNetheriteAxeStack> NETHERITE_AXE_TYPE = empty(ItemNetheriteAxeStack.class, VanillaItemId.NETHERITE_AXE);

    public static ItemType<ItemNetheriteBlockStack> NETHERITE_BLOCK_TYPE = empty(ItemNetheriteBlockStack.class, VanillaItemId.NETHERITE_BLOCK);

    public static ItemType<ItemNetheriteBootsStack> NETHERITE_BOOTS_TYPE = empty(ItemNetheriteBootsStack.class, VanillaItemId.NETHERITE_BOOTS);

    public static ItemType<ItemNetheriteChestplateStack> NETHERITE_CHESTPLATE_TYPE = empty(ItemNetheriteChestplateStack.class, VanillaItemId.NETHERITE_CHESTPLATE);

    public static ItemType<ItemNetheriteHelmetStack> NETHERITE_HELMET_TYPE = empty(ItemNetheriteHelmetStack.class, VanillaItemId.NETHERITE_HELMET);

    public static ItemType<ItemNetheriteHoeStack> NETHERITE_HOE_TYPE = empty(ItemNetheriteHoeStack.class, VanillaItemId.NETHERITE_HOE);

    public static ItemType<ItemNetheriteIngotStack> NETHERITE_INGOT_TYPE = empty(ItemNetheriteIngotStack.class, VanillaItemId.NETHERITE_INGOT);

    public static ItemType<ItemNetheriteLeggingsStack> NETHERITE_LEGGINGS_TYPE = empty(ItemNetheriteLeggingsStack.class, VanillaItemId.NETHERITE_LEGGINGS);

    public static ItemType<ItemNetheritePickaxeStack> NETHERITE_PICKAXE_TYPE = empty(ItemNetheritePickaxeStack.class, VanillaItemId.NETHERITE_PICKAXE);

    public static ItemType<ItemNetheriteScrapStack> NETHERITE_SCRAP_TYPE = empty(ItemNetheriteScrapStack.class, VanillaItemId.NETHERITE_SCRAP);

    public static ItemType<ItemNetheriteShovelStack> NETHERITE_SHOVEL_TYPE = empty(ItemNetheriteShovelStack.class, VanillaItemId.NETHERITE_SHOVEL);

    public static ItemType<ItemNetheriteSwordStack> NETHERITE_SWORD_TYPE = empty(ItemNetheriteSwordStack.class, VanillaItemId.NETHERITE_SWORD);

    public static ItemType<ItemNetheriteUpgradeSmithingTemplateStack> NETHERITE_UPGRADE_SMITHING_TEMPLATE_TYPE = empty(ItemNetheriteUpgradeSmithingTemplateStack.class, VanillaItemId.NETHERITE_UPGRADE_SMITHING_TEMPLATE);

    public static ItemType<ItemNetherrackStack> NETHERRACK_TYPE = empty(ItemNetherrackStack.class, VanillaItemId.NETHERRACK);

    public static ItemType<ItemNetherreactorStack> NETHERREACTOR_TYPE = empty(ItemNetherreactorStack.class, VanillaItemId.NETHERREACTOR);

    public static ItemType<ItemNormalStoneStairsStack> NORMAL_STONE_STAIRS_TYPE = empty(ItemNormalStoneStairsStack.class, VanillaItemId.NORMAL_STONE_STAIRS);

    public static ItemType<ItemNoteblockStack> NOTEBLOCK_TYPE = empty(ItemNoteblockStack.class, VanillaItemId.NOTEBLOCK);

    public static ItemType<ItemNpcSpawnEggStack> NPC_SPAWN_EGG_TYPE = empty(ItemNpcSpawnEggStack.class, VanillaItemId.NPC_SPAWN_EGG);

    public static ItemType<ItemOakBoatStack> OAK_BOAT_TYPE = empty(ItemOakBoatStack.class, VanillaItemId.OAK_BOAT);

    public static ItemType<ItemOakChestBoatStack> OAK_CHEST_BOAT_TYPE = empty(ItemOakChestBoatStack.class, VanillaItemId.OAK_CHEST_BOAT);

    public static ItemType<ItemOakFenceStack> OAK_FENCE_TYPE = empty(ItemOakFenceStack.class, VanillaItemId.OAK_FENCE);

    public static ItemType<ItemOakHangingSignStack> OAK_HANGING_SIGN_TYPE = empty(ItemOakHangingSignStack.class, VanillaItemId.OAK_HANGING_SIGN);

    public static ItemType<ItemOakLogStack> OAK_LOG_TYPE = empty(ItemOakLogStack.class, VanillaItemId.OAK_LOG);

    public static ItemType<ItemOakPlanksStack> OAK_PLANKS_TYPE = empty(ItemOakPlanksStack.class, VanillaItemId.OAK_PLANKS);

    public static ItemType<ItemOakSignStack> OAK_SIGN_TYPE = empty(ItemOakSignStack.class, VanillaItemId.OAK_SIGN);

    public static ItemType<ItemOakStairsStack> OAK_STAIRS_TYPE = empty(ItemOakStairsStack.class, VanillaItemId.OAK_STAIRS);

    public static ItemType<ItemObserverStack> OBSERVER_TYPE = empty(ItemObserverStack.class, VanillaItemId.OBSERVER);

    public static ItemType<ItemObsidianStack> OBSIDIAN_TYPE = empty(ItemObsidianStack.class, VanillaItemId.OBSIDIAN);

    public static ItemType<ItemOcelotSpawnEggStack> OCELOT_SPAWN_EGG_TYPE = empty(ItemOcelotSpawnEggStack.class, VanillaItemId.OCELOT_SPAWN_EGG);

    public static ItemType<ItemOchreFroglightStack> OCHRE_FROGLIGHT_TYPE = empty(ItemOchreFroglightStack.class, VanillaItemId.OCHRE_FROGLIGHT);

    public static ItemType<ItemOrangeCandleStack> ORANGE_CANDLE_TYPE = empty(ItemOrangeCandleStack.class, VanillaItemId.ORANGE_CANDLE);

    public static ItemType<ItemOrangeCandleCakeStack> ORANGE_CANDLE_CAKE_TYPE = empty(ItemOrangeCandleCakeStack.class, VanillaItemId.ORANGE_CANDLE_CAKE);

    public static ItemType<ItemOrangeCarpetStack> ORANGE_CARPET_TYPE = empty(ItemOrangeCarpetStack.class, VanillaItemId.ORANGE_CARPET);

    public static ItemType<ItemOrangeConcreteStack> ORANGE_CONCRETE_TYPE = empty(ItemOrangeConcreteStack.class, VanillaItemId.ORANGE_CONCRETE);

    public static ItemType<ItemOrangeConcretePowderStack> ORANGE_CONCRETE_POWDER_TYPE = empty(ItemOrangeConcretePowderStack.class, VanillaItemId.ORANGE_CONCRETE_POWDER);

    public static ItemType<ItemOrangeDyeStack> ORANGE_DYE_TYPE = empty(ItemOrangeDyeStack.class, VanillaItemId.ORANGE_DYE);

    public static ItemType<ItemOrangeGlazedTerracottaStack> ORANGE_GLAZED_TERRACOTTA_TYPE = empty(ItemOrangeGlazedTerracottaStack.class, VanillaItemId.ORANGE_GLAZED_TERRACOTTA);

    public static ItemType<ItemOrangeShulkerBoxStack> ORANGE_SHULKER_BOX_TYPE = empty(ItemOrangeShulkerBoxStack.class, VanillaItemId.ORANGE_SHULKER_BOX);

    public static ItemType<ItemOrangeStainedGlassStack> ORANGE_STAINED_GLASS_TYPE = empty(ItemOrangeStainedGlassStack.class, VanillaItemId.ORANGE_STAINED_GLASS);

    public static ItemType<ItemOrangeStainedGlassPaneStack> ORANGE_STAINED_GLASS_PANE_TYPE = empty(ItemOrangeStainedGlassPaneStack.class, VanillaItemId.ORANGE_STAINED_GLASS_PANE);

    public static ItemType<ItemOrangeTerracottaStack> ORANGE_TERRACOTTA_TYPE = empty(ItemOrangeTerracottaStack.class, VanillaItemId.ORANGE_TERRACOTTA);

    public static ItemType<ItemOrangeWoolStack> ORANGE_WOOL_TYPE = empty(ItemOrangeWoolStack.class, VanillaItemId.ORANGE_WOOL);

    public static ItemType<ItemOxidizedChiseledCopperStack> OXIDIZED_CHISELED_COPPER_TYPE = empty(ItemOxidizedChiseledCopperStack.class, VanillaItemId.OXIDIZED_CHISELED_COPPER);

    public static ItemType<ItemOxidizedCopperStack> OXIDIZED_COPPER_TYPE = empty(ItemOxidizedCopperStack.class, VanillaItemId.OXIDIZED_COPPER);

    public static ItemType<ItemOxidizedCopperBulbStack> OXIDIZED_COPPER_BULB_TYPE = empty(ItemOxidizedCopperBulbStack.class, VanillaItemId.OXIDIZED_COPPER_BULB);

    public static ItemType<ItemOxidizedCopperDoorStack> OXIDIZED_COPPER_DOOR_TYPE = empty(ItemOxidizedCopperDoorStack.class, VanillaItemId.OXIDIZED_COPPER_DOOR);

    public static ItemType<ItemOxidizedCopperGrateStack> OXIDIZED_COPPER_GRATE_TYPE = empty(ItemOxidizedCopperGrateStack.class, VanillaItemId.OXIDIZED_COPPER_GRATE);

    public static ItemType<ItemOxidizedCopperTrapdoorStack> OXIDIZED_COPPER_TRAPDOOR_TYPE = empty(ItemOxidizedCopperTrapdoorStack.class, VanillaItemId.OXIDIZED_COPPER_TRAPDOOR);

    public static ItemType<ItemOxidizedCutCopperStack> OXIDIZED_CUT_COPPER_TYPE = empty(ItemOxidizedCutCopperStack.class, VanillaItemId.OXIDIZED_CUT_COPPER);

    public static ItemType<ItemOxidizedCutCopperSlabStack> OXIDIZED_CUT_COPPER_SLAB_TYPE = empty(ItemOxidizedCutCopperSlabStack.class, VanillaItemId.OXIDIZED_CUT_COPPER_SLAB);

    public static ItemType<ItemOxidizedCutCopperStairsStack> OXIDIZED_CUT_COPPER_STAIRS_TYPE = empty(ItemOxidizedCutCopperStairsStack.class, VanillaItemId.OXIDIZED_CUT_COPPER_STAIRS);

    public static ItemType<ItemOxidizedDoubleCutCopperSlabStack> OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = empty(ItemOxidizedDoubleCutCopperSlabStack.class, VanillaItemId.OXIDIZED_DOUBLE_CUT_COPPER_SLAB);

    public static ItemType<ItemPackedIceStack> PACKED_ICE_TYPE = empty(ItemPackedIceStack.class, VanillaItemId.PACKED_ICE);

    public static ItemType<ItemPackedMudStack> PACKED_MUD_TYPE = empty(ItemPackedMudStack.class, VanillaItemId.PACKED_MUD);

    public static ItemType<ItemPaintingStack> PAINTING_TYPE = empty(ItemPaintingStack.class, VanillaItemId.PAINTING);

    public static ItemType<ItemPandaSpawnEggStack> PANDA_SPAWN_EGG_TYPE = empty(ItemPandaSpawnEggStack.class, VanillaItemId.PANDA_SPAWN_EGG);

    public static ItemType<ItemPaperStack> PAPER_TYPE = empty(ItemPaperStack.class, VanillaItemId.PAPER);

    public static ItemType<ItemParrotSpawnEggStack> PARROT_SPAWN_EGG_TYPE = empty(ItemParrotSpawnEggStack.class, VanillaItemId.PARROT_SPAWN_EGG);

    public static ItemType<ItemPearlescentFroglightStack> PEARLESCENT_FROGLIGHT_TYPE = empty(ItemPearlescentFroglightStack.class, VanillaItemId.PEARLESCENT_FROGLIGHT);

    public static ItemType<ItemPhantomMembraneStack> PHANTOM_MEMBRANE_TYPE = empty(ItemPhantomMembraneStack.class, VanillaItemId.PHANTOM_MEMBRANE);

    public static ItemType<ItemPhantomSpawnEggStack> PHANTOM_SPAWN_EGG_TYPE = empty(ItemPhantomSpawnEggStack.class, VanillaItemId.PHANTOM_SPAWN_EGG);

    public static ItemType<ItemPigSpawnEggStack> PIG_SPAWN_EGG_TYPE = empty(ItemPigSpawnEggStack.class, VanillaItemId.PIG_SPAWN_EGG);

    public static ItemType<ItemPiglinBannerPatternStack> PIGLIN_BANNER_PATTERN_TYPE = empty(ItemPiglinBannerPatternStack.class, VanillaItemId.PIGLIN_BANNER_PATTERN);

    public static ItemType<ItemPiglinBruteSpawnEggStack> PIGLIN_BRUTE_SPAWN_EGG_TYPE = empty(ItemPiglinBruteSpawnEggStack.class, VanillaItemId.PIGLIN_BRUTE_SPAWN_EGG);

    public static ItemType<ItemPiglinSpawnEggStack> PIGLIN_SPAWN_EGG_TYPE = empty(ItemPiglinSpawnEggStack.class, VanillaItemId.PIGLIN_SPAWN_EGG);

    public static ItemType<ItemPillagerSpawnEggStack> PILLAGER_SPAWN_EGG_TYPE = empty(ItemPillagerSpawnEggStack.class, VanillaItemId.PILLAGER_SPAWN_EGG);

    public static ItemType<ItemPinkCandleStack> PINK_CANDLE_TYPE = empty(ItemPinkCandleStack.class, VanillaItemId.PINK_CANDLE);

    public static ItemType<ItemPinkCandleCakeStack> PINK_CANDLE_CAKE_TYPE = empty(ItemPinkCandleCakeStack.class, VanillaItemId.PINK_CANDLE_CAKE);

    public static ItemType<ItemPinkCarpetStack> PINK_CARPET_TYPE = empty(ItemPinkCarpetStack.class, VanillaItemId.PINK_CARPET);

    public static ItemType<ItemPinkConcreteStack> PINK_CONCRETE_TYPE = empty(ItemPinkConcreteStack.class, VanillaItemId.PINK_CONCRETE);

    public static ItemType<ItemPinkConcretePowderStack> PINK_CONCRETE_POWDER_TYPE = empty(ItemPinkConcretePowderStack.class, VanillaItemId.PINK_CONCRETE_POWDER);

    public static ItemType<ItemPinkDyeStack> PINK_DYE_TYPE = empty(ItemPinkDyeStack.class, VanillaItemId.PINK_DYE);

    public static ItemType<ItemPinkGlazedTerracottaStack> PINK_GLAZED_TERRACOTTA_TYPE = empty(ItemPinkGlazedTerracottaStack.class, VanillaItemId.PINK_GLAZED_TERRACOTTA);

    public static ItemType<ItemPinkPetalsStack> PINK_PETALS_TYPE = empty(ItemPinkPetalsStack.class, VanillaItemId.PINK_PETALS);

    public static ItemType<ItemPinkShulkerBoxStack> PINK_SHULKER_BOX_TYPE = empty(ItemPinkShulkerBoxStack.class, VanillaItemId.PINK_SHULKER_BOX);

    public static ItemType<ItemPinkStainedGlassStack> PINK_STAINED_GLASS_TYPE = empty(ItemPinkStainedGlassStack.class, VanillaItemId.PINK_STAINED_GLASS);

    public static ItemType<ItemPinkStainedGlassPaneStack> PINK_STAINED_GLASS_PANE_TYPE = empty(ItemPinkStainedGlassPaneStack.class, VanillaItemId.PINK_STAINED_GLASS_PANE);

    public static ItemType<ItemPinkTerracottaStack> PINK_TERRACOTTA_TYPE = empty(ItemPinkTerracottaStack.class, VanillaItemId.PINK_TERRACOTTA);

    public static ItemType<ItemPinkWoolStack> PINK_WOOL_TYPE = empty(ItemPinkWoolStack.class, VanillaItemId.PINK_WOOL);

    public static ItemType<ItemPistonStack> PISTON_TYPE = empty(ItemPistonStack.class, VanillaItemId.PISTON);

    public static ItemType<ItemPistonArmCollisionStack> PISTON_ARM_COLLISION_TYPE = empty(ItemPistonArmCollisionStack.class, VanillaItemId.PISTON_ARM_COLLISION);

    public static ItemType<ItemPitcherCropStack> PITCHER_CROP_TYPE = empty(ItemPitcherCropStack.class, VanillaItemId.PITCHER_CROP);

    public static ItemType<ItemPitcherPlantStack> PITCHER_PLANT_TYPE = empty(ItemPitcherPlantStack.class, VanillaItemId.PITCHER_PLANT);

    public static ItemType<ItemPitcherPodStack> PITCHER_POD_TYPE = empty(ItemPitcherPodStack.class, VanillaItemId.PITCHER_POD);

    public static ItemType<ItemPlanksStack> PLANKS_TYPE = empty(ItemPlanksStack.class, VanillaItemId.PLANKS);

    public static ItemType<ItemPlentyPotterySherdStack> PLENTY_POTTERY_SHERD_TYPE = empty(ItemPlentyPotterySherdStack.class, VanillaItemId.PLENTY_POTTERY_SHERD);

    public static ItemType<ItemPodzolStack> PODZOL_TYPE = empty(ItemPodzolStack.class, VanillaItemId.PODZOL);

    public static ItemType<ItemPointedDripstoneStack> POINTED_DRIPSTONE_TYPE = empty(ItemPointedDripstoneStack.class, VanillaItemId.POINTED_DRIPSTONE);

    public static ItemType<ItemPoisonousPotatoStack> POISONOUS_POTATO_TYPE = empty(ItemPoisonousPotatoStack.class, VanillaItemId.POISONOUS_POTATO);

    public static ItemType<ItemPolarBearSpawnEggStack> POLAR_BEAR_SPAWN_EGG_TYPE = empty(ItemPolarBearSpawnEggStack.class, VanillaItemId.POLAR_BEAR_SPAWN_EGG);

    public static ItemType<ItemPolishedAndesiteStack> POLISHED_ANDESITE_TYPE = empty(ItemPolishedAndesiteStack.class, VanillaItemId.POLISHED_ANDESITE);

    public static ItemType<ItemPolishedAndesiteStairsStack> POLISHED_ANDESITE_STAIRS_TYPE = empty(ItemPolishedAndesiteStairsStack.class, VanillaItemId.POLISHED_ANDESITE_STAIRS);

    public static ItemType<ItemPolishedBasaltStack> POLISHED_BASALT_TYPE = empty(ItemPolishedBasaltStack.class, VanillaItemId.POLISHED_BASALT);

    public static ItemType<ItemPolishedBlackstoneStack> POLISHED_BLACKSTONE_TYPE = empty(ItemPolishedBlackstoneStack.class, VanillaItemId.POLISHED_BLACKSTONE);

    public static ItemType<ItemPolishedBlackstoneBrickDoubleSlabStack> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE = empty(ItemPolishedBlackstoneBrickDoubleSlabStack.class, VanillaItemId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB);

    public static ItemType<ItemPolishedBlackstoneBrickSlabStack> POLISHED_BLACKSTONE_BRICK_SLAB_TYPE = empty(ItemPolishedBlackstoneBrickSlabStack.class, VanillaItemId.POLISHED_BLACKSTONE_BRICK_SLAB);

    public static ItemType<ItemPolishedBlackstoneBrickStairsStack> POLISHED_BLACKSTONE_BRICK_STAIRS_TYPE = empty(ItemPolishedBlackstoneBrickStairsStack.class, VanillaItemId.POLISHED_BLACKSTONE_BRICK_STAIRS);

    public static ItemType<ItemPolishedBlackstoneBrickWallStack> POLISHED_BLACKSTONE_BRICK_WALL_TYPE = empty(ItemPolishedBlackstoneBrickWallStack.class, VanillaItemId.POLISHED_BLACKSTONE_BRICK_WALL);

    public static ItemType<ItemPolishedBlackstoneBricksStack> POLISHED_BLACKSTONE_BRICKS_TYPE = empty(ItemPolishedBlackstoneBricksStack.class, VanillaItemId.POLISHED_BLACKSTONE_BRICKS);

    public static ItemType<ItemPolishedBlackstoneButtonStack> POLISHED_BLACKSTONE_BUTTON_TYPE = empty(ItemPolishedBlackstoneButtonStack.class, VanillaItemId.POLISHED_BLACKSTONE_BUTTON);

    public static ItemType<ItemPolishedBlackstoneDoubleSlabStack> POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE = empty(ItemPolishedBlackstoneDoubleSlabStack.class, VanillaItemId.POLISHED_BLACKSTONE_DOUBLE_SLAB);

    public static ItemType<ItemPolishedBlackstonePressurePlateStack> POLISHED_BLACKSTONE_PRESSURE_PLATE_TYPE = empty(ItemPolishedBlackstonePressurePlateStack.class, VanillaItemId.POLISHED_BLACKSTONE_PRESSURE_PLATE);

    public static ItemType<ItemPolishedBlackstoneSlabStack> POLISHED_BLACKSTONE_SLAB_TYPE = empty(ItemPolishedBlackstoneSlabStack.class, VanillaItemId.POLISHED_BLACKSTONE_SLAB);

    public static ItemType<ItemPolishedBlackstoneStairsStack> POLISHED_BLACKSTONE_STAIRS_TYPE = empty(ItemPolishedBlackstoneStairsStack.class, VanillaItemId.POLISHED_BLACKSTONE_STAIRS);

    public static ItemType<ItemPolishedBlackstoneWallStack> POLISHED_BLACKSTONE_WALL_TYPE = empty(ItemPolishedBlackstoneWallStack.class, VanillaItemId.POLISHED_BLACKSTONE_WALL);

    public static ItemType<ItemPolishedDeepslateStack> POLISHED_DEEPSLATE_TYPE = empty(ItemPolishedDeepslateStack.class, VanillaItemId.POLISHED_DEEPSLATE);

    public static ItemType<ItemPolishedDeepslateDoubleSlabStack> POLISHED_DEEPSLATE_DOUBLE_SLAB_TYPE = empty(ItemPolishedDeepslateDoubleSlabStack.class, VanillaItemId.POLISHED_DEEPSLATE_DOUBLE_SLAB);

    public static ItemType<ItemPolishedDeepslateSlabStack> POLISHED_DEEPSLATE_SLAB_TYPE = empty(ItemPolishedDeepslateSlabStack.class, VanillaItemId.POLISHED_DEEPSLATE_SLAB);

    public static ItemType<ItemPolishedDeepslateStairsStack> POLISHED_DEEPSLATE_STAIRS_TYPE = empty(ItemPolishedDeepslateStairsStack.class, VanillaItemId.POLISHED_DEEPSLATE_STAIRS);

    public static ItemType<ItemPolishedDeepslateWallStack> POLISHED_DEEPSLATE_WALL_TYPE = empty(ItemPolishedDeepslateWallStack.class, VanillaItemId.POLISHED_DEEPSLATE_WALL);

    public static ItemType<ItemPolishedDioriteStack> POLISHED_DIORITE_TYPE = empty(ItemPolishedDioriteStack.class, VanillaItemId.POLISHED_DIORITE);

    public static ItemType<ItemPolishedDioriteStairsStack> POLISHED_DIORITE_STAIRS_TYPE = empty(ItemPolishedDioriteStairsStack.class, VanillaItemId.POLISHED_DIORITE_STAIRS);

    public static ItemType<ItemPolishedGraniteStack> POLISHED_GRANITE_TYPE = empty(ItemPolishedGraniteStack.class, VanillaItemId.POLISHED_GRANITE);

    public static ItemType<ItemPolishedGraniteStairsStack> POLISHED_GRANITE_STAIRS_TYPE = empty(ItemPolishedGraniteStairsStack.class, VanillaItemId.POLISHED_GRANITE_STAIRS);

    public static ItemType<ItemPolishedTuffStack> POLISHED_TUFF_TYPE = empty(ItemPolishedTuffStack.class, VanillaItemId.POLISHED_TUFF);

    public static ItemType<ItemPolishedTuffDoubleSlabStack> POLISHED_TUFF_DOUBLE_SLAB_TYPE = empty(ItemPolishedTuffDoubleSlabStack.class, VanillaItemId.POLISHED_TUFF_DOUBLE_SLAB);

    public static ItemType<ItemPolishedTuffSlabStack> POLISHED_TUFF_SLAB_TYPE = empty(ItemPolishedTuffSlabStack.class, VanillaItemId.POLISHED_TUFF_SLAB);

    public static ItemType<ItemPolishedTuffStairsStack> POLISHED_TUFF_STAIRS_TYPE = empty(ItemPolishedTuffStairsStack.class, VanillaItemId.POLISHED_TUFF_STAIRS);

    public static ItemType<ItemPolishedTuffWallStack> POLISHED_TUFF_WALL_TYPE = empty(ItemPolishedTuffWallStack.class, VanillaItemId.POLISHED_TUFF_WALL);

    public static ItemType<ItemPoppedChorusFruitStack> POPPED_CHORUS_FRUIT_TYPE = empty(ItemPoppedChorusFruitStack.class, VanillaItemId.POPPED_CHORUS_FRUIT);

    public static ItemType<ItemPorkchopStack> PORKCHOP_TYPE = empty(ItemPorkchopStack.class, VanillaItemId.PORKCHOP);

    public static ItemType<ItemPortalStack> PORTAL_TYPE = empty(ItemPortalStack.class, VanillaItemId.PORTAL);

    public static ItemType<ItemPotatoStack> POTATO_TYPE = empty(ItemPotatoStack.class, VanillaItemId.POTATO);

    public static ItemType<ItemPotatoesStack> POTATOES_TYPE = empty(ItemPotatoesStack.class, VanillaItemId.POTATOES);

    public static ItemType<ItemPotionStack> POTION_TYPE = empty(ItemPotionStack.class, VanillaItemId.POTION);

    public static ItemType<ItemPowderSnowStack> POWDER_SNOW_TYPE = empty(ItemPowderSnowStack.class, VanillaItemId.POWDER_SNOW);

    public static ItemType<ItemPowderSnowBucketStack> POWDER_SNOW_BUCKET_TYPE = empty(ItemPowderSnowBucketStack.class, VanillaItemId.POWDER_SNOW_BUCKET);

    public static ItemType<ItemPoweredComparatorStack> POWERED_COMPARATOR_TYPE = empty(ItemPoweredComparatorStack.class, VanillaItemId.POWERED_COMPARATOR);

    public static ItemType<ItemPoweredRepeaterStack> POWERED_REPEATER_TYPE = empty(ItemPoweredRepeaterStack.class, VanillaItemId.POWERED_REPEATER);

    public static ItemType<ItemPrismarineStack> PRISMARINE_TYPE = empty(ItemPrismarineStack.class, VanillaItemId.PRISMARINE);

    public static ItemType<ItemPrismarineBricksStairsStack> PRISMARINE_BRICKS_STAIRS_TYPE = empty(ItemPrismarineBricksStairsStack.class, VanillaItemId.PRISMARINE_BRICKS_STAIRS);

    public static ItemType<ItemPrismarineCrystalsStack> PRISMARINE_CRYSTALS_TYPE = empty(ItemPrismarineCrystalsStack.class, VanillaItemId.PRISMARINE_CRYSTALS);

    public static ItemType<ItemPrismarineShardStack> PRISMARINE_SHARD_TYPE = empty(ItemPrismarineShardStack.class, VanillaItemId.PRISMARINE_SHARD);

    public static ItemType<ItemPrismarineStairsStack> PRISMARINE_STAIRS_TYPE = empty(ItemPrismarineStairsStack.class, VanillaItemId.PRISMARINE_STAIRS);

    public static ItemType<ItemPrizePotterySherdStack> PRIZE_POTTERY_SHERD_TYPE = empty(ItemPrizePotterySherdStack.class, VanillaItemId.PRIZE_POTTERY_SHERD);

    public static ItemType<ItemPufferfishStack> PUFFERFISH_TYPE = empty(ItemPufferfishStack.class, VanillaItemId.PUFFERFISH);

    public static ItemType<ItemPufferfishBucketStack> PUFFERFISH_BUCKET_TYPE = empty(ItemPufferfishBucketStack.class, VanillaItemId.PUFFERFISH_BUCKET);

    public static ItemType<ItemPufferfishSpawnEggStack> PUFFERFISH_SPAWN_EGG_TYPE = empty(ItemPufferfishSpawnEggStack.class, VanillaItemId.PUFFERFISH_SPAWN_EGG);

    public static ItemType<ItemPumpkinStack> PUMPKIN_TYPE = empty(ItemPumpkinStack.class, VanillaItemId.PUMPKIN);

    public static ItemType<ItemPumpkinPieStack> PUMPKIN_PIE_TYPE = empty(ItemPumpkinPieStack.class, VanillaItemId.PUMPKIN_PIE);

    public static ItemType<ItemPumpkinSeedsStack> PUMPKIN_SEEDS_TYPE = empty(ItemPumpkinSeedsStack.class, VanillaItemId.PUMPKIN_SEEDS);

    public static ItemType<ItemPumpkinStemStack> PUMPKIN_STEM_TYPE = empty(ItemPumpkinStemStack.class, VanillaItemId.PUMPKIN_STEM);

    public static ItemType<ItemPurpleCandleStack> PURPLE_CANDLE_TYPE = empty(ItemPurpleCandleStack.class, VanillaItemId.PURPLE_CANDLE);

    public static ItemType<ItemPurpleCandleCakeStack> PURPLE_CANDLE_CAKE_TYPE = empty(ItemPurpleCandleCakeStack.class, VanillaItemId.PURPLE_CANDLE_CAKE);

    public static ItemType<ItemPurpleCarpetStack> PURPLE_CARPET_TYPE = empty(ItemPurpleCarpetStack.class, VanillaItemId.PURPLE_CARPET);

    public static ItemType<ItemPurpleConcreteStack> PURPLE_CONCRETE_TYPE = empty(ItemPurpleConcreteStack.class, VanillaItemId.PURPLE_CONCRETE);

    public static ItemType<ItemPurpleConcretePowderStack> PURPLE_CONCRETE_POWDER_TYPE = empty(ItemPurpleConcretePowderStack.class, VanillaItemId.PURPLE_CONCRETE_POWDER);

    public static ItemType<ItemPurpleDyeStack> PURPLE_DYE_TYPE = empty(ItemPurpleDyeStack.class, VanillaItemId.PURPLE_DYE);

    public static ItemType<ItemPurpleGlazedTerracottaStack> PURPLE_GLAZED_TERRACOTTA_TYPE = empty(ItemPurpleGlazedTerracottaStack.class, VanillaItemId.PURPLE_GLAZED_TERRACOTTA);

    public static ItemType<ItemPurpleShulkerBoxStack> PURPLE_SHULKER_BOX_TYPE = empty(ItemPurpleShulkerBoxStack.class, VanillaItemId.PURPLE_SHULKER_BOX);

    public static ItemType<ItemPurpleStainedGlassStack> PURPLE_STAINED_GLASS_TYPE = empty(ItemPurpleStainedGlassStack.class, VanillaItemId.PURPLE_STAINED_GLASS);

    public static ItemType<ItemPurpleStainedGlassPaneStack> PURPLE_STAINED_GLASS_PANE_TYPE = empty(ItemPurpleStainedGlassPaneStack.class, VanillaItemId.PURPLE_STAINED_GLASS_PANE);

    public static ItemType<ItemPurpleTerracottaStack> PURPLE_TERRACOTTA_TYPE = empty(ItemPurpleTerracottaStack.class, VanillaItemId.PURPLE_TERRACOTTA);

    public static ItemType<ItemPurpleWoolStack> PURPLE_WOOL_TYPE = empty(ItemPurpleWoolStack.class, VanillaItemId.PURPLE_WOOL);

    public static ItemType<ItemPurpurBlockStack> PURPUR_BLOCK_TYPE = empty(ItemPurpurBlockStack.class, VanillaItemId.PURPUR_BLOCK);

    public static ItemType<ItemPurpurStairsStack> PURPUR_STAIRS_TYPE = empty(ItemPurpurStairsStack.class, VanillaItemId.PURPUR_STAIRS);

    public static ItemType<ItemQuartzStack> QUARTZ_TYPE = empty(ItemQuartzStack.class, VanillaItemId.QUARTZ);

    public static ItemType<ItemQuartzBlockStack> QUARTZ_BLOCK_TYPE = empty(ItemQuartzBlockStack.class, VanillaItemId.QUARTZ_BLOCK);

    public static ItemType<ItemQuartzBricksStack> QUARTZ_BRICKS_TYPE = empty(ItemQuartzBricksStack.class, VanillaItemId.QUARTZ_BRICKS);

    public static ItemType<ItemQuartzOreStack> QUARTZ_ORE_TYPE = empty(ItemQuartzOreStack.class, VanillaItemId.QUARTZ_ORE);

    public static ItemType<ItemQuartzStairsStack> QUARTZ_STAIRS_TYPE = empty(ItemQuartzStairsStack.class, VanillaItemId.QUARTZ_STAIRS);

    public static ItemType<ItemRabbitStack> RABBIT_TYPE = empty(ItemRabbitStack.class, VanillaItemId.RABBIT);

    public static ItemType<ItemRabbitFootStack> RABBIT_FOOT_TYPE = empty(ItemRabbitFootStack.class, VanillaItemId.RABBIT_FOOT);

    public static ItemType<ItemRabbitHideStack> RABBIT_HIDE_TYPE = empty(ItemRabbitHideStack.class, VanillaItemId.RABBIT_HIDE);

    public static ItemType<ItemRabbitSpawnEggStack> RABBIT_SPAWN_EGG_TYPE = empty(ItemRabbitSpawnEggStack.class, VanillaItemId.RABBIT_SPAWN_EGG);

    public static ItemType<ItemRabbitStewStack> RABBIT_STEW_TYPE = empty(ItemRabbitStewStack.class, VanillaItemId.RABBIT_STEW);

    public static ItemType<ItemRailStack> RAIL_TYPE = empty(ItemRailStack.class, VanillaItemId.RAIL);

    public static ItemType<ItemRaiserArmorTrimSmithingTemplateStack> RAISER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemRaiserArmorTrimSmithingTemplateStack.class, VanillaItemId.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemRapidFertilizerStack> RAPID_FERTILIZER_TYPE = empty(ItemRapidFertilizerStack.class, VanillaItemId.RAPID_FERTILIZER);

    public static ItemType<ItemRavagerSpawnEggStack> RAVAGER_SPAWN_EGG_TYPE = empty(ItemRavagerSpawnEggStack.class, VanillaItemId.RAVAGER_SPAWN_EGG);

    public static ItemType<ItemRawCopperStack> RAW_COPPER_TYPE = empty(ItemRawCopperStack.class, VanillaItemId.RAW_COPPER);

    public static ItemType<ItemRawCopperBlockStack> RAW_COPPER_BLOCK_TYPE = empty(ItemRawCopperBlockStack.class, VanillaItemId.RAW_COPPER_BLOCK);

    public static ItemType<ItemRawGoldStack> RAW_GOLD_TYPE = empty(ItemRawGoldStack.class, VanillaItemId.RAW_GOLD);

    public static ItemType<ItemRawGoldBlockStack> RAW_GOLD_BLOCK_TYPE = empty(ItemRawGoldBlockStack.class, VanillaItemId.RAW_GOLD_BLOCK);

    public static ItemType<ItemRawIronStack> RAW_IRON_TYPE = empty(ItemRawIronStack.class, VanillaItemId.RAW_IRON);

    public static ItemType<ItemRawIronBlockStack> RAW_IRON_BLOCK_TYPE = empty(ItemRawIronBlockStack.class, VanillaItemId.RAW_IRON_BLOCK);

    public static ItemType<ItemRecoveryCompassStack> RECOVERY_COMPASS_TYPE = empty(ItemRecoveryCompassStack.class, VanillaItemId.RECOVERY_COMPASS);

    public static ItemType<ItemRedCandleStack> RED_CANDLE_TYPE = empty(ItemRedCandleStack.class, VanillaItemId.RED_CANDLE);

    public static ItemType<ItemRedCandleCakeStack> RED_CANDLE_CAKE_TYPE = empty(ItemRedCandleCakeStack.class, VanillaItemId.RED_CANDLE_CAKE);

    public static ItemType<ItemRedCarpetStack> RED_CARPET_TYPE = empty(ItemRedCarpetStack.class, VanillaItemId.RED_CARPET);

    public static ItemType<ItemRedConcreteStack> RED_CONCRETE_TYPE = empty(ItemRedConcreteStack.class, VanillaItemId.RED_CONCRETE);

    public static ItemType<ItemRedConcretePowderStack> RED_CONCRETE_POWDER_TYPE = empty(ItemRedConcretePowderStack.class, VanillaItemId.RED_CONCRETE_POWDER);

    public static ItemType<ItemRedDyeStack> RED_DYE_TYPE = empty(ItemRedDyeStack.class, VanillaItemId.RED_DYE);

    public static ItemType<ItemRedFlowerStack> RED_FLOWER_TYPE = empty(ItemRedFlowerStack.class, VanillaItemId.RED_FLOWER);

    public static ItemType<ItemRedGlazedTerracottaStack> RED_GLAZED_TERRACOTTA_TYPE = empty(ItemRedGlazedTerracottaStack.class, VanillaItemId.RED_GLAZED_TERRACOTTA);

    public static ItemType<ItemRedMushroomStack> RED_MUSHROOM_TYPE = empty(ItemRedMushroomStack.class, VanillaItemId.RED_MUSHROOM);

    public static ItemType<ItemRedMushroomBlockStack> RED_MUSHROOM_BLOCK_TYPE = empty(ItemRedMushroomBlockStack.class, VanillaItemId.RED_MUSHROOM_BLOCK);

    public static ItemType<ItemRedNetherBrickStack> RED_NETHER_BRICK_TYPE = empty(ItemRedNetherBrickStack.class, VanillaItemId.RED_NETHER_BRICK);

    public static ItemType<ItemRedNetherBrickStairsStack> RED_NETHER_BRICK_STAIRS_TYPE = empty(ItemRedNetherBrickStairsStack.class, VanillaItemId.RED_NETHER_BRICK_STAIRS);

    public static ItemType<ItemRedSandstoneStack> RED_SANDSTONE_TYPE = empty(ItemRedSandstoneStack.class, VanillaItemId.RED_SANDSTONE);

    public static ItemType<ItemRedSandstoneStairsStack> RED_SANDSTONE_STAIRS_TYPE = empty(ItemRedSandstoneStairsStack.class, VanillaItemId.RED_SANDSTONE_STAIRS);

    public static ItemType<ItemRedShulkerBoxStack> RED_SHULKER_BOX_TYPE = empty(ItemRedShulkerBoxStack.class, VanillaItemId.RED_SHULKER_BOX);

    public static ItemType<ItemRedStainedGlassStack> RED_STAINED_GLASS_TYPE = empty(ItemRedStainedGlassStack.class, VanillaItemId.RED_STAINED_GLASS);

    public static ItemType<ItemRedStainedGlassPaneStack> RED_STAINED_GLASS_PANE_TYPE = empty(ItemRedStainedGlassPaneStack.class, VanillaItemId.RED_STAINED_GLASS_PANE);

    public static ItemType<ItemRedTerracottaStack> RED_TERRACOTTA_TYPE = empty(ItemRedTerracottaStack.class, VanillaItemId.RED_TERRACOTTA);

    public static ItemType<ItemRedWoolStack> RED_WOOL_TYPE = empty(ItemRedWoolStack.class, VanillaItemId.RED_WOOL);

    public static ItemType<ItemRedstoneStack> REDSTONE_TYPE = empty(ItemRedstoneStack.class, VanillaItemId.REDSTONE);

    public static ItemType<ItemRedstoneBlockStack> REDSTONE_BLOCK_TYPE = empty(ItemRedstoneBlockStack.class, VanillaItemId.REDSTONE_BLOCK);

    public static ItemType<ItemRedstoneLampStack> REDSTONE_LAMP_TYPE = empty(ItemRedstoneLampStack.class, VanillaItemId.REDSTONE_LAMP);

    public static ItemType<ItemRedstoneOreStack> REDSTONE_ORE_TYPE = empty(ItemRedstoneOreStack.class, VanillaItemId.REDSTONE_ORE);

    public static ItemType<ItemRedstoneTorchStack> REDSTONE_TORCH_TYPE = empty(ItemRedstoneTorchStack.class, VanillaItemId.REDSTONE_TORCH);

    public static ItemType<ItemRedstoneWireStack> REDSTONE_WIRE_TYPE = empty(ItemRedstoneWireStack.class, VanillaItemId.REDSTONE_WIRE);

    public static ItemType<ItemReinforcedDeepslateStack> REINFORCED_DEEPSLATE_TYPE = empty(ItemReinforcedDeepslateStack.class, VanillaItemId.REINFORCED_DEEPSLATE);

    public static ItemType<ItemRepeaterStack> REPEATER_TYPE = empty(ItemRepeaterStack.class, VanillaItemId.REPEATER);

    public static ItemType<ItemRepeatingCommandBlockStack> REPEATING_COMMAND_BLOCK_TYPE = empty(ItemRepeatingCommandBlockStack.class, VanillaItemId.REPEATING_COMMAND_BLOCK);

    public static ItemType<ItemReserved6Stack> RESERVED6_TYPE = empty(ItemReserved6Stack.class, VanillaItemId.RESERVED6);

    public static ItemType<ItemRespawnAnchorStack> RESPAWN_ANCHOR_TYPE = empty(ItemRespawnAnchorStack.class, VanillaItemId.RESPAWN_ANCHOR);

    public static ItemType<ItemRibArmorTrimSmithingTemplateStack> RIB_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemRibArmorTrimSmithingTemplateStack.class, VanillaItemId.RIB_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemRottenFleshStack> ROTTEN_FLESH_TYPE = empty(ItemRottenFleshStack.class, VanillaItemId.ROTTEN_FLESH);

    public static ItemType<ItemSaddleStack> SADDLE_TYPE = empty(ItemSaddleStack.class, VanillaItemId.SADDLE);

    public static ItemType<ItemSalmonStack> SALMON_TYPE = empty(ItemSalmonStack.class, VanillaItemId.SALMON);

    public static ItemType<ItemSalmonBucketStack> SALMON_BUCKET_TYPE = empty(ItemSalmonBucketStack.class, VanillaItemId.SALMON_BUCKET);

    public static ItemType<ItemSalmonSpawnEggStack> SALMON_SPAWN_EGG_TYPE = empty(ItemSalmonSpawnEggStack.class, VanillaItemId.SALMON_SPAWN_EGG);

    public static ItemType<ItemSandStack> SAND_TYPE = empty(ItemSandStack.class, VanillaItemId.SAND);

    public static ItemType<ItemSandstoneStack> SANDSTONE_TYPE = empty(ItemSandstoneStack.class, VanillaItemId.SANDSTONE);

    public static ItemType<ItemSandstoneStairsStack> SANDSTONE_STAIRS_TYPE = empty(ItemSandstoneStairsStack.class, VanillaItemId.SANDSTONE_STAIRS);

    public static ItemType<ItemSaplingStack> SAPLING_TYPE = empty(ItemSaplingStack.class, VanillaItemId.SAPLING);

    public static ItemType<ItemScaffoldingStack> SCAFFOLDING_TYPE = empty(ItemScaffoldingStack.class, VanillaItemId.SCAFFOLDING);

    public static ItemType<ItemSculkStack> SCULK_TYPE = empty(ItemSculkStack.class, VanillaItemId.SCULK);

    public static ItemType<ItemSculkCatalystStack> SCULK_CATALYST_TYPE = empty(ItemSculkCatalystStack.class, VanillaItemId.SCULK_CATALYST);

    public static ItemType<ItemSculkSensorStack> SCULK_SENSOR_TYPE = empty(ItemSculkSensorStack.class, VanillaItemId.SCULK_SENSOR);

    public static ItemType<ItemSculkShriekerStack> SCULK_SHRIEKER_TYPE = empty(ItemSculkShriekerStack.class, VanillaItemId.SCULK_SHRIEKER);

    public static ItemType<ItemSculkVeinStack> SCULK_VEIN_TYPE = empty(ItemSculkVeinStack.class, VanillaItemId.SCULK_VEIN);

    public static ItemType<ItemSeaLanternStack> SEA_LANTERN_TYPE = empty(ItemSeaLanternStack.class, VanillaItemId.SEA_LANTERN);

    public static ItemType<ItemSeaPickleStack> SEA_PICKLE_TYPE = empty(ItemSeaPickleStack.class, VanillaItemId.SEA_PICKLE);

    public static ItemType<ItemSeagrassStack> SEAGRASS_TYPE = empty(ItemSeagrassStack.class, VanillaItemId.SEAGRASS);

    public static ItemType<ItemSentryArmorTrimSmithingTemplateStack> SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemSentryArmorTrimSmithingTemplateStack.class, VanillaItemId.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemShaperArmorTrimSmithingTemplateStack> SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemShaperArmorTrimSmithingTemplateStack.class, VanillaItemId.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemSheafPotterySherdStack> SHEAF_POTTERY_SHERD_TYPE = empty(ItemSheafPotterySherdStack.class, VanillaItemId.SHEAF_POTTERY_SHERD);

    public static ItemType<ItemShearsStack> SHEARS_TYPE = empty(ItemShearsStack.class, VanillaItemId.SHEARS);

    public static ItemType<ItemSheepSpawnEggStack> SHEEP_SPAWN_EGG_TYPE = empty(ItemSheepSpawnEggStack.class, VanillaItemId.SHEEP_SPAWN_EGG);

    public static ItemType<ItemShelterPotterySherdStack> SHELTER_POTTERY_SHERD_TYPE = empty(ItemShelterPotterySherdStack.class, VanillaItemId.SHELTER_POTTERY_SHERD);

    public static ItemType<ItemShieldStack> SHIELD_TYPE = empty(ItemShieldStack.class, VanillaItemId.SHIELD);

    public static ItemType<ItemShroomlightStack> SHROOMLIGHT_TYPE = empty(ItemShroomlightStack.class, VanillaItemId.SHROOMLIGHT);

    public static ItemType<ItemShulkerBoxStack> SHULKER_BOX_TYPE = empty(ItemShulkerBoxStack.class, VanillaItemId.SHULKER_BOX);

    public static ItemType<ItemShulkerShellStack> SHULKER_SHELL_TYPE = empty(ItemShulkerShellStack.class, VanillaItemId.SHULKER_SHELL);

    public static ItemType<ItemShulkerSpawnEggStack> SHULKER_SPAWN_EGG_TYPE = empty(ItemShulkerSpawnEggStack.class, VanillaItemId.SHULKER_SPAWN_EGG);

    public static ItemType<ItemSilenceArmorTrimSmithingTemplateStack> SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemSilenceArmorTrimSmithingTemplateStack.class, VanillaItemId.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemSilverGlazedTerracottaStack> SILVER_GLAZED_TERRACOTTA_TYPE = empty(ItemSilverGlazedTerracottaStack.class, VanillaItemId.SILVER_GLAZED_TERRACOTTA);

    public static ItemType<ItemSilverfishSpawnEggStack> SILVERFISH_SPAWN_EGG_TYPE = empty(ItemSilverfishSpawnEggStack.class, VanillaItemId.SILVERFISH_SPAWN_EGG);

    public static ItemType<ItemSkeletonHorseSpawnEggStack> SKELETON_HORSE_SPAWN_EGG_TYPE = empty(ItemSkeletonHorseSpawnEggStack.class, VanillaItemId.SKELETON_HORSE_SPAWN_EGG);

    public static ItemType<ItemSkeletonSpawnEggStack> SKELETON_SPAWN_EGG_TYPE = empty(ItemSkeletonSpawnEggStack.class, VanillaItemId.SKELETON_SPAWN_EGG);

    public static ItemType<ItemSkullStack> SKULL_TYPE = empty(ItemSkullStack.class, VanillaItemId.SKULL);

    public static ItemType<ItemSkullBannerPatternStack> SKULL_BANNER_PATTERN_TYPE = empty(ItemSkullBannerPatternStack.class, VanillaItemId.SKULL_BANNER_PATTERN);

    public static ItemType<ItemSkullPotterySherdStack> SKULL_POTTERY_SHERD_TYPE = empty(ItemSkullPotterySherdStack.class, VanillaItemId.SKULL_POTTERY_SHERD);

    public static ItemType<ItemSlimeStack> SLIME_TYPE = empty(ItemSlimeStack.class, VanillaItemId.SLIME);

    public static ItemType<ItemSlimeBallStack> SLIME_BALL_TYPE = empty(ItemSlimeBallStack.class, VanillaItemId.SLIME_BALL);

    public static ItemType<ItemSlimeSpawnEggStack> SLIME_SPAWN_EGG_TYPE = empty(ItemSlimeSpawnEggStack.class, VanillaItemId.SLIME_SPAWN_EGG);

    public static ItemType<ItemSmallAmethystBudStack> SMALL_AMETHYST_BUD_TYPE = empty(ItemSmallAmethystBudStack.class, VanillaItemId.SMALL_AMETHYST_BUD);

    public static ItemType<ItemSmallDripleafBlockStack> SMALL_DRIPLEAF_BLOCK_TYPE = empty(ItemSmallDripleafBlockStack.class, VanillaItemId.SMALL_DRIPLEAF_BLOCK);

    public static ItemType<ItemSmithingTableStack> SMITHING_TABLE_TYPE = empty(ItemSmithingTableStack.class, VanillaItemId.SMITHING_TABLE);

    public static ItemType<ItemSmokerStack> SMOKER_TYPE = empty(ItemSmokerStack.class, VanillaItemId.SMOKER);

    public static ItemType<ItemSmoothBasaltStack> SMOOTH_BASALT_TYPE = empty(ItemSmoothBasaltStack.class, VanillaItemId.SMOOTH_BASALT);

    public static ItemType<ItemSmoothQuartzStairsStack> SMOOTH_QUARTZ_STAIRS_TYPE = empty(ItemSmoothQuartzStairsStack.class, VanillaItemId.SMOOTH_QUARTZ_STAIRS);

    public static ItemType<ItemSmoothRedSandstoneStairsStack> SMOOTH_RED_SANDSTONE_STAIRS_TYPE = empty(ItemSmoothRedSandstoneStairsStack.class, VanillaItemId.SMOOTH_RED_SANDSTONE_STAIRS);

    public static ItemType<ItemSmoothSandstoneStairsStack> SMOOTH_SANDSTONE_STAIRS_TYPE = empty(ItemSmoothSandstoneStairsStack.class, VanillaItemId.SMOOTH_SANDSTONE_STAIRS);

    public static ItemType<ItemSmoothStoneStack> SMOOTH_STONE_TYPE = empty(ItemSmoothStoneStack.class, VanillaItemId.SMOOTH_STONE);

    public static ItemType<ItemSnifferEggStack> SNIFFER_EGG_TYPE = empty(ItemSnifferEggStack.class, VanillaItemId.SNIFFER_EGG);

    public static ItemType<ItemSnifferSpawnEggStack> SNIFFER_SPAWN_EGG_TYPE = empty(ItemSnifferSpawnEggStack.class, VanillaItemId.SNIFFER_SPAWN_EGG);

    public static ItemType<ItemSnortPotterySherdStack> SNORT_POTTERY_SHERD_TYPE = empty(ItemSnortPotterySherdStack.class, VanillaItemId.SNORT_POTTERY_SHERD);

    public static ItemType<ItemSnoutArmorTrimSmithingTemplateStack> SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemSnoutArmorTrimSmithingTemplateStack.class, VanillaItemId.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemSnowStack> SNOW_TYPE = empty(ItemSnowStack.class, VanillaItemId.SNOW);

    public static ItemType<ItemSnowGolemSpawnEggStack> SNOW_GOLEM_SPAWN_EGG_TYPE = empty(ItemSnowGolemSpawnEggStack.class, VanillaItemId.SNOW_GOLEM_SPAWN_EGG);

    public static ItemType<ItemSnowLayerStack> SNOW_LAYER_TYPE = empty(ItemSnowLayerStack.class, VanillaItemId.SNOW_LAYER);

    public static ItemType<ItemSnowballStack> SNOWBALL_TYPE = empty(ItemSnowballStack.class, VanillaItemId.SNOWBALL);

    public static ItemType<ItemSoulCampfireStack> SOUL_CAMPFIRE_TYPE = empty(ItemSoulCampfireStack.class, VanillaItemId.SOUL_CAMPFIRE);

    public static ItemType<ItemSoulFireStack> SOUL_FIRE_TYPE = empty(ItemSoulFireStack.class, VanillaItemId.SOUL_FIRE);

    public static ItemType<ItemSoulLanternStack> SOUL_LANTERN_TYPE = empty(ItemSoulLanternStack.class, VanillaItemId.SOUL_LANTERN);

    public static ItemType<ItemSoulSandStack> SOUL_SAND_TYPE = empty(ItemSoulSandStack.class, VanillaItemId.SOUL_SAND);

    public static ItemType<ItemSoulSoilStack> SOUL_SOIL_TYPE = empty(ItemSoulSoilStack.class, VanillaItemId.SOUL_SOIL);

    public static ItemType<ItemSoulTorchStack> SOUL_TORCH_TYPE = empty(ItemSoulTorchStack.class, VanillaItemId.SOUL_TORCH);

    public static ItemType<ItemSparklerStack> SPARKLER_TYPE = empty(ItemSparklerStack.class, VanillaItemId.SPARKLER);

    public static ItemType<ItemSpawnEggStack> SPAWN_EGG_TYPE = empty(ItemSpawnEggStack.class, VanillaItemId.SPAWN_EGG);

    public static ItemType<ItemSpiderEyeStack> SPIDER_EYE_TYPE = empty(ItemSpiderEyeStack.class, VanillaItemId.SPIDER_EYE);

    public static ItemType<ItemSpiderSpawnEggStack> SPIDER_SPAWN_EGG_TYPE = empty(ItemSpiderSpawnEggStack.class, VanillaItemId.SPIDER_SPAWN_EGG);

    public static ItemType<ItemSpireArmorTrimSmithingTemplateStack> SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemSpireArmorTrimSmithingTemplateStack.class, VanillaItemId.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemSplashPotionStack> SPLASH_POTION_TYPE = empty(ItemSplashPotionStack.class, VanillaItemId.SPLASH_POTION);

    public static ItemType<ItemSpongeStack> SPONGE_TYPE = empty(ItemSpongeStack.class, VanillaItemId.SPONGE);

    public static ItemType<ItemSporeBlossomStack> SPORE_BLOSSOM_TYPE = empty(ItemSporeBlossomStack.class, VanillaItemId.SPORE_BLOSSOM);

    public static ItemType<ItemSpruceBoatStack> SPRUCE_BOAT_TYPE = empty(ItemSpruceBoatStack.class, VanillaItemId.SPRUCE_BOAT);

    public static ItemType<ItemSpruceButtonStack> SPRUCE_BUTTON_TYPE = empty(ItemSpruceButtonStack.class, VanillaItemId.SPRUCE_BUTTON);

    public static ItemType<ItemSpruceChestBoatStack> SPRUCE_CHEST_BOAT_TYPE = empty(ItemSpruceChestBoatStack.class, VanillaItemId.SPRUCE_CHEST_BOAT);

    public static ItemType<ItemSpruceDoorStack> SPRUCE_DOOR_TYPE = empty(ItemSpruceDoorStack.class, VanillaItemId.SPRUCE_DOOR);

    public static ItemType<ItemSpruceFenceStack> SPRUCE_FENCE_TYPE = empty(ItemSpruceFenceStack.class, VanillaItemId.SPRUCE_FENCE);

    public static ItemType<ItemSpruceFenceGateStack> SPRUCE_FENCE_GATE_TYPE = empty(ItemSpruceFenceGateStack.class, VanillaItemId.SPRUCE_FENCE_GATE);

    public static ItemType<ItemSpruceHangingSignStack> SPRUCE_HANGING_SIGN_TYPE = empty(ItemSpruceHangingSignStack.class, VanillaItemId.SPRUCE_HANGING_SIGN);

    public static ItemType<ItemSpruceLogStack> SPRUCE_LOG_TYPE = empty(ItemSpruceLogStack.class, VanillaItemId.SPRUCE_LOG);

    public static ItemType<ItemSprucePlanksStack> SPRUCE_PLANKS_TYPE = empty(ItemSprucePlanksStack.class, VanillaItemId.SPRUCE_PLANKS);

    public static ItemType<ItemSprucePressurePlateStack> SPRUCE_PRESSURE_PLATE_TYPE = empty(ItemSprucePressurePlateStack.class, VanillaItemId.SPRUCE_PRESSURE_PLATE);

    public static ItemType<ItemSpruceSignStack> SPRUCE_SIGN_TYPE = empty(ItemSpruceSignStack.class, VanillaItemId.SPRUCE_SIGN);

    public static ItemType<ItemSpruceStairsStack> SPRUCE_STAIRS_TYPE = empty(ItemSpruceStairsStack.class, VanillaItemId.SPRUCE_STAIRS);

    public static ItemType<ItemSpruceStandingSignStack> SPRUCE_STANDING_SIGN_TYPE = empty(ItemSpruceStandingSignStack.class, VanillaItemId.SPRUCE_STANDING_SIGN);

    public static ItemType<ItemSpruceTrapdoorStack> SPRUCE_TRAPDOOR_TYPE = empty(ItemSpruceTrapdoorStack.class, VanillaItemId.SPRUCE_TRAPDOOR);

    public static ItemType<ItemSpruceWallSignStack> SPRUCE_WALL_SIGN_TYPE = empty(ItemSpruceWallSignStack.class, VanillaItemId.SPRUCE_WALL_SIGN);

    public static ItemType<ItemSpyglassStack> SPYGLASS_TYPE = empty(ItemSpyglassStack.class, VanillaItemId.SPYGLASS);

    public static ItemType<ItemSquidSpawnEggStack> SQUID_SPAWN_EGG_TYPE = empty(ItemSquidSpawnEggStack.class, VanillaItemId.SQUID_SPAWN_EGG);

    public static ItemType<ItemStainedGlassStack> STAINED_GLASS_TYPE = empty(ItemStainedGlassStack.class, VanillaItemId.STAINED_GLASS);

    public static ItemType<ItemStainedGlassPaneStack> STAINED_GLASS_PANE_TYPE = empty(ItemStainedGlassPaneStack.class, VanillaItemId.STAINED_GLASS_PANE);

    public static ItemType<ItemStainedHardenedClayStack> STAINED_HARDENED_CLAY_TYPE = empty(ItemStainedHardenedClayStack.class, VanillaItemId.STAINED_HARDENED_CLAY);

    public static ItemType<ItemStandingBannerStack> STANDING_BANNER_TYPE = empty(ItemStandingBannerStack.class, VanillaItemId.STANDING_BANNER);

    public static ItemType<ItemStandingSignStack> STANDING_SIGN_TYPE = empty(ItemStandingSignStack.class, VanillaItemId.STANDING_SIGN);

    public static ItemType<ItemStickStack> STICK_TYPE = empty(ItemStickStack.class, VanillaItemId.STICK);

    public static ItemType<ItemStickyPistonStack> STICKY_PISTON_TYPE = empty(ItemStickyPistonStack.class, VanillaItemId.STICKY_PISTON);

    public static ItemType<ItemStickyPistonArmCollisionStack> STICKY_PISTON_ARM_COLLISION_TYPE = empty(ItemStickyPistonArmCollisionStack.class, VanillaItemId.STICKY_PISTON_ARM_COLLISION);

    public static ItemType<ItemStoneStack> STONE_TYPE = empty(ItemStoneStack.class, VanillaItemId.STONE);

    public static ItemType<ItemStoneAxeStack> STONE_AXE_TYPE = empty(ItemStoneAxeStack.class, VanillaItemId.STONE_AXE);

    public static ItemType<ItemStoneBlockSlabStack> STONE_BLOCK_SLAB_TYPE = empty(ItemStoneBlockSlabStack.class, VanillaItemId.STONE_BLOCK_SLAB);

    public static ItemType<ItemStoneBlockSlab2Stack> STONE_BLOCK_SLAB2_TYPE = empty(ItemStoneBlockSlab2Stack.class, VanillaItemId.STONE_BLOCK_SLAB2);

    public static ItemType<ItemStoneBlockSlab3Stack> STONE_BLOCK_SLAB3_TYPE = empty(ItemStoneBlockSlab3Stack.class, VanillaItemId.STONE_BLOCK_SLAB3);

    public static ItemType<ItemStoneBlockSlab4Stack> STONE_BLOCK_SLAB4_TYPE = empty(ItemStoneBlockSlab4Stack.class, VanillaItemId.STONE_BLOCK_SLAB4);

    public static ItemType<ItemStoneBrickStairsStack> STONE_BRICK_STAIRS_TYPE = empty(ItemStoneBrickStairsStack.class, VanillaItemId.STONE_BRICK_STAIRS);

    public static ItemType<ItemStoneButtonStack> STONE_BUTTON_TYPE = empty(ItemStoneButtonStack.class, VanillaItemId.STONE_BUTTON);

    public static ItemType<ItemStoneHoeStack> STONE_HOE_TYPE = empty(ItemStoneHoeStack.class, VanillaItemId.STONE_HOE);

    public static ItemType<ItemStonePickaxeStack> STONE_PICKAXE_TYPE = empty(ItemStonePickaxeStack.class, VanillaItemId.STONE_PICKAXE);

    public static ItemType<ItemStonePressurePlateStack> STONE_PRESSURE_PLATE_TYPE = empty(ItemStonePressurePlateStack.class, VanillaItemId.STONE_PRESSURE_PLATE);

    public static ItemType<ItemStoneShovelStack> STONE_SHOVEL_TYPE = empty(ItemStoneShovelStack.class, VanillaItemId.STONE_SHOVEL);

    public static ItemType<ItemStoneStairsStack> STONE_STAIRS_TYPE = empty(ItemStoneStairsStack.class, VanillaItemId.STONE_STAIRS);

    public static ItemType<ItemStoneSwordStack> STONE_SWORD_TYPE = empty(ItemStoneSwordStack.class, VanillaItemId.STONE_SWORD);

    public static ItemType<ItemStonebrickStack> STONEBRICK_TYPE = empty(ItemStonebrickStack.class, VanillaItemId.STONEBRICK);

    public static ItemType<ItemStonecutterStack> STONECUTTER_TYPE = empty(ItemStonecutterStack.class, VanillaItemId.STONECUTTER);

    public static ItemType<ItemStonecutterBlockStack> STONECUTTER_BLOCK_TYPE = empty(ItemStonecutterBlockStack.class, VanillaItemId.STONECUTTER_BLOCK);

    public static ItemType<ItemStraySpawnEggStack> STRAY_SPAWN_EGG_TYPE = empty(ItemStraySpawnEggStack.class, VanillaItemId.STRAY_SPAWN_EGG);

    public static ItemType<ItemStriderSpawnEggStack> STRIDER_SPAWN_EGG_TYPE = empty(ItemStriderSpawnEggStack.class, VanillaItemId.STRIDER_SPAWN_EGG);

    public static ItemType<ItemStringStack> STRING_TYPE = empty(ItemStringStack.class, VanillaItemId.STRING);

    public static ItemType<ItemStrippedAcaciaLogStack> STRIPPED_ACACIA_LOG_TYPE = empty(ItemStrippedAcaciaLogStack.class, VanillaItemId.STRIPPED_ACACIA_LOG);

    public static ItemType<ItemStrippedBambooBlockStack> STRIPPED_BAMBOO_BLOCK_TYPE = empty(ItemStrippedBambooBlockStack.class, VanillaItemId.STRIPPED_BAMBOO_BLOCK);

    public static ItemType<ItemStrippedBirchLogStack> STRIPPED_BIRCH_LOG_TYPE = empty(ItemStrippedBirchLogStack.class, VanillaItemId.STRIPPED_BIRCH_LOG);

    public static ItemType<ItemStrippedCherryLogStack> STRIPPED_CHERRY_LOG_TYPE = empty(ItemStrippedCherryLogStack.class, VanillaItemId.STRIPPED_CHERRY_LOG);

    public static ItemType<ItemStrippedCherryWoodStack> STRIPPED_CHERRY_WOOD_TYPE = empty(ItemStrippedCherryWoodStack.class, VanillaItemId.STRIPPED_CHERRY_WOOD);

    public static ItemType<ItemStrippedCrimsonHyphaeStack> STRIPPED_CRIMSON_HYPHAE_TYPE = empty(ItemStrippedCrimsonHyphaeStack.class, VanillaItemId.STRIPPED_CRIMSON_HYPHAE);

    public static ItemType<ItemStrippedCrimsonStemStack> STRIPPED_CRIMSON_STEM_TYPE = empty(ItemStrippedCrimsonStemStack.class, VanillaItemId.STRIPPED_CRIMSON_STEM);

    public static ItemType<ItemStrippedDarkOakLogStack> STRIPPED_DARK_OAK_LOG_TYPE = empty(ItemStrippedDarkOakLogStack.class, VanillaItemId.STRIPPED_DARK_OAK_LOG);

    public static ItemType<ItemStrippedJungleLogStack> STRIPPED_JUNGLE_LOG_TYPE = empty(ItemStrippedJungleLogStack.class, VanillaItemId.STRIPPED_JUNGLE_LOG);

    public static ItemType<ItemStrippedMangroveLogStack> STRIPPED_MANGROVE_LOG_TYPE = empty(ItemStrippedMangroveLogStack.class, VanillaItemId.STRIPPED_MANGROVE_LOG);

    public static ItemType<ItemStrippedMangroveWoodStack> STRIPPED_MANGROVE_WOOD_TYPE = empty(ItemStrippedMangroveWoodStack.class, VanillaItemId.STRIPPED_MANGROVE_WOOD);

    public static ItemType<ItemStrippedOakLogStack> STRIPPED_OAK_LOG_TYPE = empty(ItemStrippedOakLogStack.class, VanillaItemId.STRIPPED_OAK_LOG);

    public static ItemType<ItemStrippedSpruceLogStack> STRIPPED_SPRUCE_LOG_TYPE = empty(ItemStrippedSpruceLogStack.class, VanillaItemId.STRIPPED_SPRUCE_LOG);

    public static ItemType<ItemStrippedWarpedHyphaeStack> STRIPPED_WARPED_HYPHAE_TYPE = empty(ItemStrippedWarpedHyphaeStack.class, VanillaItemId.STRIPPED_WARPED_HYPHAE);

    public static ItemType<ItemStrippedWarpedStemStack> STRIPPED_WARPED_STEM_TYPE = empty(ItemStrippedWarpedStemStack.class, VanillaItemId.STRIPPED_WARPED_STEM);

    public static ItemType<ItemStructureBlockStack> STRUCTURE_BLOCK_TYPE = empty(ItemStructureBlockStack.class, VanillaItemId.STRUCTURE_BLOCK);

    public static ItemType<ItemStructureVoidStack> STRUCTURE_VOID_TYPE = empty(ItemStructureVoidStack.class, VanillaItemId.STRUCTURE_VOID);

    public static ItemType<ItemSugarStack> SUGAR_TYPE = empty(ItemSugarStack.class, VanillaItemId.SUGAR);

    public static ItemType<ItemSugarCaneStack> SUGAR_CANE_TYPE = empty(ItemSugarCaneStack.class, VanillaItemId.SUGAR_CANE);

    public static ItemType<ItemSuspiciousGravelStack> SUSPICIOUS_GRAVEL_TYPE = empty(ItemSuspiciousGravelStack.class, VanillaItemId.SUSPICIOUS_GRAVEL);

    public static ItemType<ItemSuspiciousSandStack> SUSPICIOUS_SAND_TYPE = empty(ItemSuspiciousSandStack.class, VanillaItemId.SUSPICIOUS_SAND);

    public static ItemType<ItemSuspiciousStewStack> SUSPICIOUS_STEW_TYPE = empty(ItemSuspiciousStewStack.class, VanillaItemId.SUSPICIOUS_STEW);

    public static ItemType<ItemSweetBerriesStack> SWEET_BERRIES_TYPE = empty(ItemSweetBerriesStack.class, VanillaItemId.SWEET_BERRIES);

    public static ItemType<ItemSweetBerryBushStack> SWEET_BERRY_BUSH_TYPE = empty(ItemSweetBerryBushStack.class, VanillaItemId.SWEET_BERRY_BUSH);

    public static ItemType<ItemTadpoleBucketStack> TADPOLE_BUCKET_TYPE = empty(ItemTadpoleBucketStack.class, VanillaItemId.TADPOLE_BUCKET);

    public static ItemType<ItemTadpoleSpawnEggStack> TADPOLE_SPAWN_EGG_TYPE = empty(ItemTadpoleSpawnEggStack.class, VanillaItemId.TADPOLE_SPAWN_EGG);

    public static ItemType<ItemTallgrassStack> TALLGRASS_TYPE = empty(ItemTallgrassStack.class, VanillaItemId.TALLGRASS);

    public static ItemType<ItemTargetStack> TARGET_TYPE = empty(ItemTargetStack.class, VanillaItemId.TARGET);

    public static ItemType<ItemTideArmorTrimSmithingTemplateStack> TIDE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemTideArmorTrimSmithingTemplateStack.class, VanillaItemId.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemTintedGlassStack> TINTED_GLASS_TYPE = empty(ItemTintedGlassStack.class, VanillaItemId.TINTED_GLASS);

    public static ItemType<ItemTntStack> TNT_TYPE = empty(ItemTntStack.class, VanillaItemId.TNT);

    public static ItemType<ItemTntMinecartStack> TNT_MINECART_TYPE = empty(ItemTntMinecartStack.class, VanillaItemId.TNT_MINECART);

    public static ItemType<ItemTorchStack> TORCH_TYPE = empty(ItemTorchStack.class, VanillaItemId.TORCH);

    public static ItemType<ItemTorchflowerStack> TORCHFLOWER_TYPE = empty(ItemTorchflowerStack.class, VanillaItemId.TORCHFLOWER);

    public static ItemType<ItemTorchflowerCropStack> TORCHFLOWER_CROP_TYPE = empty(ItemTorchflowerCropStack.class, VanillaItemId.TORCHFLOWER_CROP);

    public static ItemType<ItemTorchflowerSeedsStack> TORCHFLOWER_SEEDS_TYPE = empty(ItemTorchflowerSeedsStack.class, VanillaItemId.TORCHFLOWER_SEEDS);

    public static ItemType<ItemTotemOfUndyingStack> TOTEM_OF_UNDYING_TYPE = empty(ItemTotemOfUndyingStack.class, VanillaItemId.TOTEM_OF_UNDYING);

    public static ItemType<ItemTraderLlamaSpawnEggStack> TRADER_LLAMA_SPAWN_EGG_TYPE = empty(ItemTraderLlamaSpawnEggStack.class, VanillaItemId.TRADER_LLAMA_SPAWN_EGG);

    public static ItemType<ItemTrapdoorStack> TRAPDOOR_TYPE = empty(ItemTrapdoorStack.class, VanillaItemId.TRAPDOOR);

    public static ItemType<ItemTrappedChestStack> TRAPPED_CHEST_TYPE = empty(ItemTrappedChestStack.class, VanillaItemId.TRAPPED_CHEST);

    public static ItemType<ItemTrialKeyStack> TRIAL_KEY_TYPE = empty(ItemTrialKeyStack.class, VanillaItemId.TRIAL_KEY);

    public static ItemType<ItemTrialSpawnerStack> TRIAL_SPAWNER_TYPE = empty(ItemTrialSpawnerStack.class, VanillaItemId.TRIAL_SPAWNER);

    public static ItemType<ItemTridentStack> TRIDENT_TYPE = empty(ItemTridentStack.class, VanillaItemId.TRIDENT);

    public static ItemType<ItemTripWireStack> TRIP_WIRE_TYPE = empty(ItemTripWireStack.class, VanillaItemId.TRIP_WIRE);

    public static ItemType<ItemTripwireHookStack> TRIPWIRE_HOOK_TYPE = empty(ItemTripwireHookStack.class, VanillaItemId.TRIPWIRE_HOOK);

    public static ItemType<ItemTropicalFishStack> TROPICAL_FISH_TYPE = empty(ItemTropicalFishStack.class, VanillaItemId.TROPICAL_FISH);

    public static ItemType<ItemTropicalFishBucketStack> TROPICAL_FISH_BUCKET_TYPE = empty(ItemTropicalFishBucketStack.class, VanillaItemId.TROPICAL_FISH_BUCKET);

    public static ItemType<ItemTropicalFishSpawnEggStack> TROPICAL_FISH_SPAWN_EGG_TYPE = empty(ItemTropicalFishSpawnEggStack.class, VanillaItemId.TROPICAL_FISH_SPAWN_EGG);

    public static ItemType<ItemTubeCoralStack> TUBE_CORAL_TYPE = empty(ItemTubeCoralStack.class, VanillaItemId.TUBE_CORAL);

    public static ItemType<ItemTuffStack> TUFF_TYPE = empty(ItemTuffStack.class, VanillaItemId.TUFF);

    public static ItemType<ItemTuffBrickDoubleSlabStack> TUFF_BRICK_DOUBLE_SLAB_TYPE = empty(ItemTuffBrickDoubleSlabStack.class, VanillaItemId.TUFF_BRICK_DOUBLE_SLAB);

    public static ItemType<ItemTuffBrickSlabStack> TUFF_BRICK_SLAB_TYPE = empty(ItemTuffBrickSlabStack.class, VanillaItemId.TUFF_BRICK_SLAB);

    public static ItemType<ItemTuffBrickStairsStack> TUFF_BRICK_STAIRS_TYPE = empty(ItemTuffBrickStairsStack.class, VanillaItemId.TUFF_BRICK_STAIRS);

    public static ItemType<ItemTuffBrickWallStack> TUFF_BRICK_WALL_TYPE = empty(ItemTuffBrickWallStack.class, VanillaItemId.TUFF_BRICK_WALL);

    public static ItemType<ItemTuffBricksStack> TUFF_BRICKS_TYPE = empty(ItemTuffBricksStack.class, VanillaItemId.TUFF_BRICKS);

    public static ItemType<ItemTuffDoubleSlabStack> TUFF_DOUBLE_SLAB_TYPE = empty(ItemTuffDoubleSlabStack.class, VanillaItemId.TUFF_DOUBLE_SLAB);

    public static ItemType<ItemTuffSlabStack> TUFF_SLAB_TYPE = empty(ItemTuffSlabStack.class, VanillaItemId.TUFF_SLAB);

    public static ItemType<ItemTuffStairsStack> TUFF_STAIRS_TYPE = empty(ItemTuffStairsStack.class, VanillaItemId.TUFF_STAIRS);

    public static ItemType<ItemTuffWallStack> TUFF_WALL_TYPE = empty(ItemTuffWallStack.class, VanillaItemId.TUFF_WALL);

    public static ItemType<ItemTurtleEggStack> TURTLE_EGG_TYPE = empty(ItemTurtleEggStack.class, VanillaItemId.TURTLE_EGG);

    public static ItemType<ItemTurtleHelmetStack> TURTLE_HELMET_TYPE = empty(ItemTurtleHelmetStack.class, VanillaItemId.TURTLE_HELMET);

    public static ItemType<ItemTurtleScuteStack> TURTLE_SCUTE_TYPE = empty(ItemTurtleScuteStack.class, VanillaItemId.TURTLE_SCUTE);

    public static ItemType<ItemTurtleSpawnEggStack> TURTLE_SPAWN_EGG_TYPE = empty(ItemTurtleSpawnEggStack.class, VanillaItemId.TURTLE_SPAWN_EGG);

    public static ItemType<ItemTwistingVinesStack> TWISTING_VINES_TYPE = empty(ItemTwistingVinesStack.class, VanillaItemId.TWISTING_VINES);

    public static ItemType<ItemUnderwaterTorchStack> UNDERWATER_TORCH_TYPE = empty(ItemUnderwaterTorchStack.class, VanillaItemId.UNDERWATER_TORCH);

    public static ItemType<ItemUndyedShulkerBoxStack> UNDYED_SHULKER_BOX_TYPE = empty(ItemUndyedShulkerBoxStack.class, VanillaItemId.UNDYED_SHULKER_BOX);

    public static ItemType<ItemUnknownStack> UNKNOWN_TYPE = empty(ItemUnknownStack.class, VanillaItemId.UNKNOWN);

    public static ItemType<ItemUnlitRedstoneTorchStack> UNLIT_REDSTONE_TORCH_TYPE = empty(ItemUnlitRedstoneTorchStack.class, VanillaItemId.UNLIT_REDSTONE_TORCH);

    public static ItemType<ItemUnpoweredComparatorStack> UNPOWERED_COMPARATOR_TYPE = empty(ItemUnpoweredComparatorStack.class, VanillaItemId.UNPOWERED_COMPARATOR);

    public static ItemType<ItemUnpoweredRepeaterStack> UNPOWERED_REPEATER_TYPE = empty(ItemUnpoweredRepeaterStack.class, VanillaItemId.UNPOWERED_REPEATER);

    public static ItemType<ItemVerdantFroglightStack> VERDANT_FROGLIGHT_TYPE = empty(ItemVerdantFroglightStack.class, VanillaItemId.VERDANT_FROGLIGHT);

    public static ItemType<ItemVexArmorTrimSmithingTemplateStack> VEX_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemVexArmorTrimSmithingTemplateStack.class, VanillaItemId.VEX_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemVexSpawnEggStack> VEX_SPAWN_EGG_TYPE = empty(ItemVexSpawnEggStack.class, VanillaItemId.VEX_SPAWN_EGG);

    public static ItemType<ItemVillagerSpawnEggStack> VILLAGER_SPAWN_EGG_TYPE = empty(ItemVillagerSpawnEggStack.class, VanillaItemId.VILLAGER_SPAWN_EGG);

    public static ItemType<ItemVindicatorSpawnEggStack> VINDICATOR_SPAWN_EGG_TYPE = empty(ItemVindicatorSpawnEggStack.class, VanillaItemId.VINDICATOR_SPAWN_EGG);

    public static ItemType<ItemVineStack> VINE_TYPE = empty(ItemVineStack.class, VanillaItemId.VINE);

    public static ItemType<ItemWallBannerStack> WALL_BANNER_TYPE = empty(ItemWallBannerStack.class, VanillaItemId.WALL_BANNER);

    public static ItemType<ItemWallSignStack> WALL_SIGN_TYPE = empty(ItemWallSignStack.class, VanillaItemId.WALL_SIGN);

    public static ItemType<ItemWanderingTraderSpawnEggStack> WANDERING_TRADER_SPAWN_EGG_TYPE = empty(ItemWanderingTraderSpawnEggStack.class, VanillaItemId.WANDERING_TRADER_SPAWN_EGG);

    public static ItemType<ItemWardArmorTrimSmithingTemplateStack> WARD_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemWardArmorTrimSmithingTemplateStack.class, VanillaItemId.WARD_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemWardenSpawnEggStack> WARDEN_SPAWN_EGG_TYPE = empty(ItemWardenSpawnEggStack.class, VanillaItemId.WARDEN_SPAWN_EGG);

    public static ItemType<ItemWarpedButtonStack> WARPED_BUTTON_TYPE = empty(ItemWarpedButtonStack.class, VanillaItemId.WARPED_BUTTON);

    public static ItemType<ItemWarpedDoorStack> WARPED_DOOR_TYPE = empty(ItemWarpedDoorStack.class, VanillaItemId.WARPED_DOOR);

    public static ItemType<ItemWarpedDoubleSlabStack> WARPED_DOUBLE_SLAB_TYPE = empty(ItemWarpedDoubleSlabStack.class, VanillaItemId.WARPED_DOUBLE_SLAB);

    public static ItemType<ItemWarpedFenceStack> WARPED_FENCE_TYPE = empty(ItemWarpedFenceStack.class, VanillaItemId.WARPED_FENCE);

    public static ItemType<ItemWarpedFenceGateStack> WARPED_FENCE_GATE_TYPE = empty(ItemWarpedFenceGateStack.class, VanillaItemId.WARPED_FENCE_GATE);

    public static ItemType<ItemWarpedFungusStack> WARPED_FUNGUS_TYPE = empty(ItemWarpedFungusStack.class, VanillaItemId.WARPED_FUNGUS);

    public static ItemType<ItemWarpedFungusOnAStickStack> WARPED_FUNGUS_ON_A_STICK_TYPE = empty(ItemWarpedFungusOnAStickStack.class, VanillaItemId.WARPED_FUNGUS_ON_A_STICK);

    public static ItemType<ItemWarpedHangingSignStack> WARPED_HANGING_SIGN_TYPE = empty(ItemWarpedHangingSignStack.class, VanillaItemId.WARPED_HANGING_SIGN);

    public static ItemType<ItemWarpedHyphaeStack> WARPED_HYPHAE_TYPE = empty(ItemWarpedHyphaeStack.class, VanillaItemId.WARPED_HYPHAE);

    public static ItemType<ItemWarpedNyliumStack> WARPED_NYLIUM_TYPE = empty(ItemWarpedNyliumStack.class, VanillaItemId.WARPED_NYLIUM);

    public static ItemType<ItemWarpedPlanksStack> WARPED_PLANKS_TYPE = empty(ItemWarpedPlanksStack.class, VanillaItemId.WARPED_PLANKS);

    public static ItemType<ItemWarpedPressurePlateStack> WARPED_PRESSURE_PLATE_TYPE = empty(ItemWarpedPressurePlateStack.class, VanillaItemId.WARPED_PRESSURE_PLATE);

    public static ItemType<ItemWarpedRootsStack> WARPED_ROOTS_TYPE = empty(ItemWarpedRootsStack.class, VanillaItemId.WARPED_ROOTS);

    public static ItemType<ItemWarpedSignStack> WARPED_SIGN_TYPE = empty(ItemWarpedSignStack.class, VanillaItemId.WARPED_SIGN);

    public static ItemType<ItemWarpedSlabStack> WARPED_SLAB_TYPE = empty(ItemWarpedSlabStack.class, VanillaItemId.WARPED_SLAB);

    public static ItemType<ItemWarpedStairsStack> WARPED_STAIRS_TYPE = empty(ItemWarpedStairsStack.class, VanillaItemId.WARPED_STAIRS);

    public static ItemType<ItemWarpedStandingSignStack> WARPED_STANDING_SIGN_TYPE = empty(ItemWarpedStandingSignStack.class, VanillaItemId.WARPED_STANDING_SIGN);

    public static ItemType<ItemWarpedStemStack> WARPED_STEM_TYPE = empty(ItemWarpedStemStack.class, VanillaItemId.WARPED_STEM);

    public static ItemType<ItemWarpedTrapdoorStack> WARPED_TRAPDOOR_TYPE = empty(ItemWarpedTrapdoorStack.class, VanillaItemId.WARPED_TRAPDOOR);

    public static ItemType<ItemWarpedWallSignStack> WARPED_WALL_SIGN_TYPE = empty(ItemWarpedWallSignStack.class, VanillaItemId.WARPED_WALL_SIGN);

    public static ItemType<ItemWarpedWartBlockStack> WARPED_WART_BLOCK_TYPE = empty(ItemWarpedWartBlockStack.class, VanillaItemId.WARPED_WART_BLOCK);

    public static ItemType<ItemWaterStack> WATER_TYPE = empty(ItemWaterStack.class, VanillaItemId.WATER);

    public static ItemType<ItemWaterBucketStack> WATER_BUCKET_TYPE = empty(ItemWaterBucketStack.class, VanillaItemId.WATER_BUCKET);

    public static ItemType<ItemWaterlilyStack> WATERLILY_TYPE = empty(ItemWaterlilyStack.class, VanillaItemId.WATERLILY);

    public static ItemType<ItemWaxedChiseledCopperStack> WAXED_CHISELED_COPPER_TYPE = empty(ItemWaxedChiseledCopperStack.class, VanillaItemId.WAXED_CHISELED_COPPER);

    public static ItemType<ItemWaxedCopperStack> WAXED_COPPER_TYPE = empty(ItemWaxedCopperStack.class, VanillaItemId.WAXED_COPPER);

    public static ItemType<ItemWaxedCopperBulbStack> WAXED_COPPER_BULB_TYPE = empty(ItemWaxedCopperBulbStack.class, VanillaItemId.WAXED_COPPER_BULB);

    public static ItemType<ItemWaxedCopperDoorStack> WAXED_COPPER_DOOR_TYPE = empty(ItemWaxedCopperDoorStack.class, VanillaItemId.WAXED_COPPER_DOOR);

    public static ItemType<ItemWaxedCopperGrateStack> WAXED_COPPER_GRATE_TYPE = empty(ItemWaxedCopperGrateStack.class, VanillaItemId.WAXED_COPPER_GRATE);

    public static ItemType<ItemWaxedCopperTrapdoorStack> WAXED_COPPER_TRAPDOOR_TYPE = empty(ItemWaxedCopperTrapdoorStack.class, VanillaItemId.WAXED_COPPER_TRAPDOOR);

    public static ItemType<ItemWaxedCutCopperStack> WAXED_CUT_COPPER_TYPE = empty(ItemWaxedCutCopperStack.class, VanillaItemId.WAXED_CUT_COPPER);

    public static ItemType<ItemWaxedCutCopperSlabStack> WAXED_CUT_COPPER_SLAB_TYPE = empty(ItemWaxedCutCopperSlabStack.class, VanillaItemId.WAXED_CUT_COPPER_SLAB);

    public static ItemType<ItemWaxedCutCopperStairsStack> WAXED_CUT_COPPER_STAIRS_TYPE = empty(ItemWaxedCutCopperStairsStack.class, VanillaItemId.WAXED_CUT_COPPER_STAIRS);

    public static ItemType<ItemWaxedDoubleCutCopperSlabStack> WAXED_DOUBLE_CUT_COPPER_SLAB_TYPE = empty(ItemWaxedDoubleCutCopperSlabStack.class, VanillaItemId.WAXED_DOUBLE_CUT_COPPER_SLAB);

    public static ItemType<ItemWaxedExposedChiseledCopperStack> WAXED_EXPOSED_CHISELED_COPPER_TYPE = empty(ItemWaxedExposedChiseledCopperStack.class, VanillaItemId.WAXED_EXPOSED_CHISELED_COPPER);

    public static ItemType<ItemWaxedExposedCopperStack> WAXED_EXPOSED_COPPER_TYPE = empty(ItemWaxedExposedCopperStack.class, VanillaItemId.WAXED_EXPOSED_COPPER);

    public static ItemType<ItemWaxedExposedCopperBulbStack> WAXED_EXPOSED_COPPER_BULB_TYPE = empty(ItemWaxedExposedCopperBulbStack.class, VanillaItemId.WAXED_EXPOSED_COPPER_BULB);

    public static ItemType<ItemWaxedExposedCopperDoorStack> WAXED_EXPOSED_COPPER_DOOR_TYPE = empty(ItemWaxedExposedCopperDoorStack.class, VanillaItemId.WAXED_EXPOSED_COPPER_DOOR);

    public static ItemType<ItemWaxedExposedCopperGrateStack> WAXED_EXPOSED_COPPER_GRATE_TYPE = empty(ItemWaxedExposedCopperGrateStack.class, VanillaItemId.WAXED_EXPOSED_COPPER_GRATE);

    public static ItemType<ItemWaxedExposedCopperTrapdoorStack> WAXED_EXPOSED_COPPER_TRAPDOOR_TYPE = empty(ItemWaxedExposedCopperTrapdoorStack.class, VanillaItemId.WAXED_EXPOSED_COPPER_TRAPDOOR);

    public static ItemType<ItemWaxedExposedCutCopperStack> WAXED_EXPOSED_CUT_COPPER_TYPE = empty(ItemWaxedExposedCutCopperStack.class, VanillaItemId.WAXED_EXPOSED_CUT_COPPER);

    public static ItemType<ItemWaxedExposedCutCopperSlabStack> WAXED_EXPOSED_CUT_COPPER_SLAB_TYPE = empty(ItemWaxedExposedCutCopperSlabStack.class, VanillaItemId.WAXED_EXPOSED_CUT_COPPER_SLAB);

    public static ItemType<ItemWaxedExposedCutCopperStairsStack> WAXED_EXPOSED_CUT_COPPER_STAIRS_TYPE = empty(ItemWaxedExposedCutCopperStairsStack.class, VanillaItemId.WAXED_EXPOSED_CUT_COPPER_STAIRS);

    public static ItemType<ItemWaxedExposedDoubleCutCopperSlabStack> WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = empty(ItemWaxedExposedDoubleCutCopperSlabStack.class, VanillaItemId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB);

    public static ItemType<ItemWaxedOxidizedChiseledCopperStack> WAXED_OXIDIZED_CHISELED_COPPER_TYPE = empty(ItemWaxedOxidizedChiseledCopperStack.class, VanillaItemId.WAXED_OXIDIZED_CHISELED_COPPER);

    public static ItemType<ItemWaxedOxidizedCopperStack> WAXED_OXIDIZED_COPPER_TYPE = empty(ItemWaxedOxidizedCopperStack.class, VanillaItemId.WAXED_OXIDIZED_COPPER);

    public static ItemType<ItemWaxedOxidizedCopperBulbStack> WAXED_OXIDIZED_COPPER_BULB_TYPE = empty(ItemWaxedOxidizedCopperBulbStack.class, VanillaItemId.WAXED_OXIDIZED_COPPER_BULB);

    public static ItemType<ItemWaxedOxidizedCopperDoorStack> WAXED_OXIDIZED_COPPER_DOOR_TYPE = empty(ItemWaxedOxidizedCopperDoorStack.class, VanillaItemId.WAXED_OXIDIZED_COPPER_DOOR);

    public static ItemType<ItemWaxedOxidizedCopperGrateStack> WAXED_OXIDIZED_COPPER_GRATE_TYPE = empty(ItemWaxedOxidizedCopperGrateStack.class, VanillaItemId.WAXED_OXIDIZED_COPPER_GRATE);

    public static ItemType<ItemWaxedOxidizedCopperTrapdoorStack> WAXED_OXIDIZED_COPPER_TRAPDOOR_TYPE = empty(ItemWaxedOxidizedCopperTrapdoorStack.class, VanillaItemId.WAXED_OXIDIZED_COPPER_TRAPDOOR);

    public static ItemType<ItemWaxedOxidizedCutCopperStack> WAXED_OXIDIZED_CUT_COPPER_TYPE = empty(ItemWaxedOxidizedCutCopperStack.class, VanillaItemId.WAXED_OXIDIZED_CUT_COPPER);

    public static ItemType<ItemWaxedOxidizedCutCopperSlabStack> WAXED_OXIDIZED_CUT_COPPER_SLAB_TYPE = empty(ItemWaxedOxidizedCutCopperSlabStack.class, VanillaItemId.WAXED_OXIDIZED_CUT_COPPER_SLAB);

    public static ItemType<ItemWaxedOxidizedCutCopperStairsStack> WAXED_OXIDIZED_CUT_COPPER_STAIRS_TYPE = empty(ItemWaxedOxidizedCutCopperStairsStack.class, VanillaItemId.WAXED_OXIDIZED_CUT_COPPER_STAIRS);

    public static ItemType<ItemWaxedOxidizedDoubleCutCopperSlabStack> WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB_TYPE = empty(ItemWaxedOxidizedDoubleCutCopperSlabStack.class, VanillaItemId.WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB);

    public static ItemType<ItemWaxedWeatheredChiseledCopperStack> WAXED_WEATHERED_CHISELED_COPPER_TYPE = empty(ItemWaxedWeatheredChiseledCopperStack.class, VanillaItemId.WAXED_WEATHERED_CHISELED_COPPER);

    public static ItemType<ItemWaxedWeatheredCopperStack> WAXED_WEATHERED_COPPER_TYPE = empty(ItemWaxedWeatheredCopperStack.class, VanillaItemId.WAXED_WEATHERED_COPPER);

    public static ItemType<ItemWaxedWeatheredCopperBulbStack> WAXED_WEATHERED_COPPER_BULB_TYPE = empty(ItemWaxedWeatheredCopperBulbStack.class, VanillaItemId.WAXED_WEATHERED_COPPER_BULB);

    public static ItemType<ItemWaxedWeatheredCopperDoorStack> WAXED_WEATHERED_COPPER_DOOR_TYPE = empty(ItemWaxedWeatheredCopperDoorStack.class, VanillaItemId.WAXED_WEATHERED_COPPER_DOOR);

    public static ItemType<ItemWaxedWeatheredCopperGrateStack> WAXED_WEATHERED_COPPER_GRATE_TYPE = empty(ItemWaxedWeatheredCopperGrateStack.class, VanillaItemId.WAXED_WEATHERED_COPPER_GRATE);

    public static ItemType<ItemWaxedWeatheredCopperTrapdoorStack> WAXED_WEATHERED_COPPER_TRAPDOOR_TYPE = empty(ItemWaxedWeatheredCopperTrapdoorStack.class, VanillaItemId.WAXED_WEATHERED_COPPER_TRAPDOOR);

    public static ItemType<ItemWaxedWeatheredCutCopperStack> WAXED_WEATHERED_CUT_COPPER_TYPE = empty(ItemWaxedWeatheredCutCopperStack.class, VanillaItemId.WAXED_WEATHERED_CUT_COPPER);

    public static ItemType<ItemWaxedWeatheredCutCopperSlabStack> WAXED_WEATHERED_CUT_COPPER_SLAB_TYPE = empty(ItemWaxedWeatheredCutCopperSlabStack.class, VanillaItemId.WAXED_WEATHERED_CUT_COPPER_SLAB);

    public static ItemType<ItemWaxedWeatheredCutCopperStairsStack> WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE = empty(ItemWaxedWeatheredCutCopperStairsStack.class, VanillaItemId.WAXED_WEATHERED_CUT_COPPER_STAIRS);

    public static ItemType<ItemWaxedWeatheredDoubleCutCopperSlabStack> WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = empty(ItemWaxedWeatheredDoubleCutCopperSlabStack.class, VanillaItemId.WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB);

    public static ItemType<ItemWayfinderArmorTrimSmithingTemplateStack> WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemWayfinderArmorTrimSmithingTemplateStack.class, VanillaItemId.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemWeatheredChiseledCopperStack> WEATHERED_CHISELED_COPPER_TYPE = empty(ItemWeatheredChiseledCopperStack.class, VanillaItemId.WEATHERED_CHISELED_COPPER);

    public static ItemType<ItemWeatheredCopperStack> WEATHERED_COPPER_TYPE = empty(ItemWeatheredCopperStack.class, VanillaItemId.WEATHERED_COPPER);

    public static ItemType<ItemWeatheredCopperBulbStack> WEATHERED_COPPER_BULB_TYPE = empty(ItemWeatheredCopperBulbStack.class, VanillaItemId.WEATHERED_COPPER_BULB);

    public static ItemType<ItemWeatheredCopperDoorStack> WEATHERED_COPPER_DOOR_TYPE = empty(ItemWeatheredCopperDoorStack.class, VanillaItemId.WEATHERED_COPPER_DOOR);

    public static ItemType<ItemWeatheredCopperGrateStack> WEATHERED_COPPER_GRATE_TYPE = empty(ItemWeatheredCopperGrateStack.class, VanillaItemId.WEATHERED_COPPER_GRATE);

    public static ItemType<ItemWeatheredCopperTrapdoorStack> WEATHERED_COPPER_TRAPDOOR_TYPE = empty(ItemWeatheredCopperTrapdoorStack.class, VanillaItemId.WEATHERED_COPPER_TRAPDOOR);

    public static ItemType<ItemWeatheredCutCopperStack> WEATHERED_CUT_COPPER_TYPE = empty(ItemWeatheredCutCopperStack.class, VanillaItemId.WEATHERED_CUT_COPPER);

    public static ItemType<ItemWeatheredCutCopperSlabStack> WEATHERED_CUT_COPPER_SLAB_TYPE = empty(ItemWeatheredCutCopperSlabStack.class, VanillaItemId.WEATHERED_CUT_COPPER_SLAB);

    public static ItemType<ItemWeatheredCutCopperStairsStack> WEATHERED_CUT_COPPER_STAIRS_TYPE = empty(ItemWeatheredCutCopperStairsStack.class, VanillaItemId.WEATHERED_CUT_COPPER_STAIRS);

    public static ItemType<ItemWeatheredDoubleCutCopperSlabStack> WEATHERED_DOUBLE_CUT_COPPER_SLAB_TYPE = empty(ItemWeatheredDoubleCutCopperSlabStack.class, VanillaItemId.WEATHERED_DOUBLE_CUT_COPPER_SLAB);

    public static ItemType<ItemWebStack> WEB_TYPE = empty(ItemWebStack.class, VanillaItemId.WEB);

    public static ItemType<ItemWeepingVinesStack> WEEPING_VINES_TYPE = empty(ItemWeepingVinesStack.class, VanillaItemId.WEEPING_VINES);

    public static ItemType<ItemWheatStack> WHEAT_TYPE = empty(ItemWheatStack.class, VanillaItemId.WHEAT);

    public static ItemType<ItemWheatSeedsStack> WHEAT_SEEDS_TYPE = empty(ItemWheatSeedsStack.class, VanillaItemId.WHEAT_SEEDS);

    public static ItemType<ItemWhiteCandleStack> WHITE_CANDLE_TYPE = empty(ItemWhiteCandleStack.class, VanillaItemId.WHITE_CANDLE);

    public static ItemType<ItemWhiteCandleCakeStack> WHITE_CANDLE_CAKE_TYPE = empty(ItemWhiteCandleCakeStack.class, VanillaItemId.WHITE_CANDLE_CAKE);

    public static ItemType<ItemWhiteCarpetStack> WHITE_CARPET_TYPE = empty(ItemWhiteCarpetStack.class, VanillaItemId.WHITE_CARPET);

    public static ItemType<ItemWhiteConcreteStack> WHITE_CONCRETE_TYPE = empty(ItemWhiteConcreteStack.class, VanillaItemId.WHITE_CONCRETE);

    public static ItemType<ItemWhiteConcretePowderStack> WHITE_CONCRETE_POWDER_TYPE = empty(ItemWhiteConcretePowderStack.class, VanillaItemId.WHITE_CONCRETE_POWDER);

    public static ItemType<ItemWhiteDyeStack> WHITE_DYE_TYPE = empty(ItemWhiteDyeStack.class, VanillaItemId.WHITE_DYE);

    public static ItemType<ItemWhiteGlazedTerracottaStack> WHITE_GLAZED_TERRACOTTA_TYPE = empty(ItemWhiteGlazedTerracottaStack.class, VanillaItemId.WHITE_GLAZED_TERRACOTTA);

    public static ItemType<ItemWhiteShulkerBoxStack> WHITE_SHULKER_BOX_TYPE = empty(ItemWhiteShulkerBoxStack.class, VanillaItemId.WHITE_SHULKER_BOX);

    public static ItemType<ItemWhiteStainedGlassStack> WHITE_STAINED_GLASS_TYPE = empty(ItemWhiteStainedGlassStack.class, VanillaItemId.WHITE_STAINED_GLASS);

    public static ItemType<ItemWhiteStainedGlassPaneStack> WHITE_STAINED_GLASS_PANE_TYPE = empty(ItemWhiteStainedGlassPaneStack.class, VanillaItemId.WHITE_STAINED_GLASS_PANE);

    public static ItemType<ItemWhiteTerracottaStack> WHITE_TERRACOTTA_TYPE = empty(ItemWhiteTerracottaStack.class, VanillaItemId.WHITE_TERRACOTTA);

    public static ItemType<ItemWhiteWoolStack> WHITE_WOOL_TYPE = empty(ItemWhiteWoolStack.class, VanillaItemId.WHITE_WOOL);

    public static ItemType<ItemWildArmorTrimSmithingTemplateStack> WILD_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = empty(ItemWildArmorTrimSmithingTemplateStack.class, VanillaItemId.WILD_ARMOR_TRIM_SMITHING_TEMPLATE);

    public static ItemType<ItemWitchSpawnEggStack> WITCH_SPAWN_EGG_TYPE = empty(ItemWitchSpawnEggStack.class, VanillaItemId.WITCH_SPAWN_EGG);

    public static ItemType<ItemWitherRoseStack> WITHER_ROSE_TYPE = empty(ItemWitherRoseStack.class, VanillaItemId.WITHER_ROSE);

    public static ItemType<ItemWitherSkeletonSpawnEggStack> WITHER_SKELETON_SPAWN_EGG_TYPE = empty(ItemWitherSkeletonSpawnEggStack.class, VanillaItemId.WITHER_SKELETON_SPAWN_EGG);

    public static ItemType<ItemWitherSpawnEggStack> WITHER_SPAWN_EGG_TYPE = empty(ItemWitherSpawnEggStack.class, VanillaItemId.WITHER_SPAWN_EGG);

    public static ItemType<ItemWolfArmorStack> WOLF_ARMOR_TYPE = empty(ItemWolfArmorStack.class, VanillaItemId.WOLF_ARMOR);

    public static ItemType<ItemWolfSpawnEggStack> WOLF_SPAWN_EGG_TYPE = empty(ItemWolfSpawnEggStack.class, VanillaItemId.WOLF_SPAWN_EGG);

    public static ItemType<ItemWoodStack> WOOD_TYPE = empty(ItemWoodStack.class, VanillaItemId.WOOD);

    public static ItemType<ItemWoodenAxeStack> WOODEN_AXE_TYPE = empty(ItemWoodenAxeStack.class, VanillaItemId.WOODEN_AXE);

    public static ItemType<ItemWoodenButtonStack> WOODEN_BUTTON_TYPE = empty(ItemWoodenButtonStack.class, VanillaItemId.WOODEN_BUTTON);

    public static ItemType<ItemWoodenDoorStack> WOODEN_DOOR_TYPE = empty(ItemWoodenDoorStack.class, VanillaItemId.WOODEN_DOOR);

    public static ItemType<ItemWoodenHoeStack> WOODEN_HOE_TYPE = empty(ItemWoodenHoeStack.class, VanillaItemId.WOODEN_HOE);

    public static ItemType<ItemWoodenPickaxeStack> WOODEN_PICKAXE_TYPE = empty(ItemWoodenPickaxeStack.class, VanillaItemId.WOODEN_PICKAXE);

    public static ItemType<ItemWoodenPressurePlateStack> WOODEN_PRESSURE_PLATE_TYPE = empty(ItemWoodenPressurePlateStack.class, VanillaItemId.WOODEN_PRESSURE_PLATE);

    public static ItemType<ItemWoodenShovelStack> WOODEN_SHOVEL_TYPE = empty(ItemWoodenShovelStack.class, VanillaItemId.WOODEN_SHOVEL);

    public static ItemType<ItemWoodenSlabStack> WOODEN_SLAB_TYPE = empty(ItemWoodenSlabStack.class, VanillaItemId.WOODEN_SLAB);

    public static ItemType<ItemWoodenSwordStack> WOODEN_SWORD_TYPE = empty(ItemWoodenSwordStack.class, VanillaItemId.WOODEN_SWORD);

    public static ItemType<ItemWoolStack> WOOL_TYPE = empty(ItemWoolStack.class, VanillaItemId.WOOL);

    public static ItemType<ItemWritableBookStack> WRITABLE_BOOK_TYPE = empty(ItemWritableBookStack.class, VanillaItemId.WRITABLE_BOOK);

    public static ItemType<ItemWrittenBookStack> WRITTEN_BOOK_TYPE = empty(ItemWrittenBookStack.class, VanillaItemId.WRITTEN_BOOK);

    public static ItemType<ItemYellowCandleStack> YELLOW_CANDLE_TYPE = empty(ItemYellowCandleStack.class, VanillaItemId.YELLOW_CANDLE);

    public static ItemType<ItemYellowCandleCakeStack> YELLOW_CANDLE_CAKE_TYPE = empty(ItemYellowCandleCakeStack.class, VanillaItemId.YELLOW_CANDLE_CAKE);

    public static ItemType<ItemYellowCarpetStack> YELLOW_CARPET_TYPE = empty(ItemYellowCarpetStack.class, VanillaItemId.YELLOW_CARPET);

    public static ItemType<ItemYellowConcreteStack> YELLOW_CONCRETE_TYPE = empty(ItemYellowConcreteStack.class, VanillaItemId.YELLOW_CONCRETE);

    public static ItemType<ItemYellowConcretePowderStack> YELLOW_CONCRETE_POWDER_TYPE = empty(ItemYellowConcretePowderStack.class, VanillaItemId.YELLOW_CONCRETE_POWDER);

    public static ItemType<ItemYellowDyeStack> YELLOW_DYE_TYPE = empty(ItemYellowDyeStack.class, VanillaItemId.YELLOW_DYE);

    public static ItemType<ItemYellowFlowerStack> YELLOW_FLOWER_TYPE = empty(ItemYellowFlowerStack.class, VanillaItemId.YELLOW_FLOWER);

    public static ItemType<ItemYellowGlazedTerracottaStack> YELLOW_GLAZED_TERRACOTTA_TYPE = empty(ItemYellowGlazedTerracottaStack.class, VanillaItemId.YELLOW_GLAZED_TERRACOTTA);

    public static ItemType<ItemYellowShulkerBoxStack> YELLOW_SHULKER_BOX_TYPE = empty(ItemYellowShulkerBoxStack.class, VanillaItemId.YELLOW_SHULKER_BOX);

    public static ItemType<ItemYellowStainedGlassStack> YELLOW_STAINED_GLASS_TYPE = empty(ItemYellowStainedGlassStack.class, VanillaItemId.YELLOW_STAINED_GLASS);

    public static ItemType<ItemYellowStainedGlassPaneStack> YELLOW_STAINED_GLASS_PANE_TYPE = empty(ItemYellowStainedGlassPaneStack.class, VanillaItemId.YELLOW_STAINED_GLASS_PANE);

    public static ItemType<ItemYellowTerracottaStack> YELLOW_TERRACOTTA_TYPE = empty(ItemYellowTerracottaStack.class, VanillaItemId.YELLOW_TERRACOTTA);

    public static ItemType<ItemYellowWoolStack> YELLOW_WOOL_TYPE = empty(ItemYellowWoolStack.class, VanillaItemId.YELLOW_WOOL);

    public static ItemType<ItemZoglinSpawnEggStack> ZOGLIN_SPAWN_EGG_TYPE = empty(ItemZoglinSpawnEggStack.class, VanillaItemId.ZOGLIN_SPAWN_EGG);

    public static ItemType<ItemZombieHorseSpawnEggStack> ZOMBIE_HORSE_SPAWN_EGG_TYPE = empty(ItemZombieHorseSpawnEggStack.class, VanillaItemId.ZOMBIE_HORSE_SPAWN_EGG);

    public static ItemType<ItemZombiePigmanSpawnEggStack> ZOMBIE_PIGMAN_SPAWN_EGG_TYPE = empty(ItemZombiePigmanSpawnEggStack.class, VanillaItemId.ZOMBIE_PIGMAN_SPAWN_EGG);

    public static ItemType<ItemZombieSpawnEggStack> ZOMBIE_SPAWN_EGG_TYPE = empty(ItemZombieSpawnEggStack.class, VanillaItemId.ZOMBIE_SPAWN_EGG);

    public static ItemType<ItemZombieVillagerSpawnEggStack> ZOMBIE_VILLAGER_SPAWN_EGG_TYPE = empty(ItemZombieVillagerSpawnEggStack.class, VanillaItemId.ZOMBIE_VILLAGER_SPAWN_EGG);

    private static <T extends ItemStack> ItemType<T> empty(Class<T> type, VanillaItemId itemId) {
        return ItemTypeBuilder.builder(type).vanillaItem(itemId).build();
    }
}
