package org.allaymc.api.item.type;

import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.interfaces.ItemAppleStack;
import org.allaymc.api.item.interfaces.ItemArrowStack;
import org.allaymc.api.item.interfaces.ItemAxeStack;
import org.allaymc.api.item.interfaces.ItemBakedPotatoStack;
import org.allaymc.api.item.interfaces.ItemBannerStack;
import org.allaymc.api.item.interfaces.ItemBeefStack;
import org.allaymc.api.item.interfaces.ItemBeetrootSoupStack;
import org.allaymc.api.item.interfaces.ItemBeetrootStack;
import org.allaymc.api.item.interfaces.ItemBootsStack;
import org.allaymc.api.item.interfaces.ItemBowStack;
import org.allaymc.api.item.interfaces.ItemBreadStack;
import org.allaymc.api.item.interfaces.ItemBucketStack;
import org.allaymc.api.item.interfaces.ItemCandleStack;
import org.allaymc.api.item.interfaces.ItemCarrotStack;
import org.allaymc.api.item.interfaces.ItemCarvedPumpkinStack;
import org.allaymc.api.item.interfaces.ItemChestplateStack;
import org.allaymc.api.item.interfaces.ItemChickenStack;
import org.allaymc.api.item.interfaces.ItemChorusFruitStack;
import org.allaymc.api.item.interfaces.ItemCodStack;
import org.allaymc.api.item.interfaces.ItemCookedBeefStack;
import org.allaymc.api.item.interfaces.ItemCookedChickenStack;
import org.allaymc.api.item.interfaces.ItemCookedCodStack;
import org.allaymc.api.item.interfaces.ItemCookedMuttonStack;
import org.allaymc.api.item.interfaces.ItemCookedPorkchopStack;
import org.allaymc.api.item.interfaces.ItemCookedRabbitStack;
import org.allaymc.api.item.interfaces.ItemCookedSalmonStack;
import org.allaymc.api.item.interfaces.ItemCookieStack;
import org.allaymc.api.item.interfaces.ItemCrossbowStack;
import org.allaymc.api.item.interfaces.ItemDecoratedPotStack;
import org.allaymc.api.item.interfaces.ItemDiamondStack;
import org.allaymc.api.item.interfaces.ItemDriedKelpStack;
import org.allaymc.api.item.interfaces.ItemDyeStack;
import org.allaymc.api.item.interfaces.ItemEggStack;
import org.allaymc.api.item.interfaces.ItemElytraStack;
import org.allaymc.api.item.interfaces.ItemEnchantedGoldenAppleStack;
import org.allaymc.api.item.interfaces.ItemEnderPearlStack;
import org.allaymc.api.item.interfaces.ItemExperienceBottleStack;
import org.allaymc.api.item.interfaces.ItemFilledMapStack;
import org.allaymc.api.item.interfaces.ItemFireChargeStack;
import org.allaymc.api.item.interfaces.ItemFireworkRocketStack;
import org.allaymc.api.item.interfaces.ItemFireworkStarStack;
import org.allaymc.api.item.interfaces.ItemFishingRodStack;
import org.allaymc.api.item.interfaces.ItemGlowBerriesStack;
import org.allaymc.api.item.interfaces.ItemGoatHornStack;
import org.allaymc.api.item.interfaces.ItemGoldenAppleStack;
import org.allaymc.api.item.interfaces.ItemGoldenCarrotStack;
import org.allaymc.api.item.interfaces.ItemGrassBlockStack;
import org.allaymc.api.item.interfaces.ItemGunpowderStack;
import org.allaymc.api.item.interfaces.ItemHeadStack;
import org.allaymc.api.item.interfaces.ItemHelmetStack;
import org.allaymc.api.item.interfaces.ItemHoeStack;
import org.allaymc.api.item.interfaces.ItemHoneyBottleStack;
import org.allaymc.api.item.interfaces.ItemHorseArmorStack;
import org.allaymc.api.item.interfaces.ItemLeatherBootsStack;
import org.allaymc.api.item.interfaces.ItemLeatherChestplateStack;
import org.allaymc.api.item.interfaces.ItemLeatherHelmetStack;
import org.allaymc.api.item.interfaces.ItemLeatherHorseArmorStack;
import org.allaymc.api.item.interfaces.ItemLeatherLeggingsStack;
import org.allaymc.api.item.interfaces.ItemLeggingsStack;
import org.allaymc.api.item.interfaces.ItemLingeringPotionStack;
import org.allaymc.api.item.interfaces.ItemMaceStack;
import org.allaymc.api.item.interfaces.ItemMelonSliceStack;
import org.allaymc.api.item.interfaces.ItemMilkBucketStack;
import org.allaymc.api.item.interfaces.ItemMushroomStewStack;
import org.allaymc.api.item.interfaces.ItemMusicDiscStack;
import org.allaymc.api.item.interfaces.ItemMuttonStack;
import org.allaymc.api.item.interfaces.ItemPaperStack;
import org.allaymc.api.item.interfaces.ItemPickaxeStack;
import org.allaymc.api.item.interfaces.ItemPoisonousPotatoStack;
import org.allaymc.api.item.interfaces.ItemPorkchopStack;
import org.allaymc.api.item.interfaces.ItemPotatoStack;
import org.allaymc.api.item.interfaces.ItemPotionStack;
import org.allaymc.api.item.interfaces.ItemPufferfishStack;
import org.allaymc.api.item.interfaces.ItemPumpkinPieStack;
import org.allaymc.api.item.interfaces.ItemRabbitStack;
import org.allaymc.api.item.interfaces.ItemRabbitStewStack;
import org.allaymc.api.item.interfaces.ItemRottenFleshStack;
import org.allaymc.api.item.interfaces.ItemSalmonStack;
import org.allaymc.api.item.interfaces.ItemSeaPickleStack;
import org.allaymc.api.item.interfaces.ItemShieldStack;
import org.allaymc.api.item.interfaces.ItemShovelStack;
import org.allaymc.api.item.interfaces.ItemShulkerBoxStack;
import org.allaymc.api.item.interfaces.ItemSignStack;
import org.allaymc.api.item.interfaces.ItemSnowballStack;
import org.allaymc.api.item.interfaces.ItemSpawnEggStack;
import org.allaymc.api.item.interfaces.ItemSpearStack;
import org.allaymc.api.item.interfaces.ItemSpiderEyeStack;
import org.allaymc.api.item.interfaces.ItemSplashPotionStack;
import org.allaymc.api.item.interfaces.ItemSweetBerriesStack;
import org.allaymc.api.item.interfaces.ItemSwordStack;
import org.allaymc.api.item.interfaces.ItemTridentStack;
import org.allaymc.api.item.interfaces.ItemTropicalFishStack;
import org.allaymc.api.item.interfaces.ItemTurtleHelmetStack;
import org.allaymc.api.item.interfaces.ItemWindChargeStack;
import org.allaymc.api.item.interfaces.ItemWritableBookStack;
import org.allaymc.api.item.interfaces.ItemWrittenBookStack;

@MinecraftVersionSensitive
public final class ItemTypes {
    public static ItemType<ItemStack> ACACIA_BOAT;

    public static ItemType<ItemStack> ACACIA_BUTTON;

    public static ItemType<ItemStack> ACACIA_CHEST_BOAT;

    public static ItemType<ItemStack> ACACIA_DOOR;

    public static ItemType<ItemStack> ACACIA_DOUBLE_SLAB;

    public static ItemType<ItemStack> ACACIA_FENCE;

    public static ItemType<ItemStack> ACACIA_FENCE_GATE;

    public static ItemType<ItemStack> ACACIA_HANGING_SIGN;

    public static ItemType<ItemStack> ACACIA_LEAVES;

    public static ItemType<ItemStack> ACACIA_LOG;

    public static ItemType<ItemStack> ACACIA_PLANKS;

    public static ItemType<ItemStack> ACACIA_PRESSURE_PLATE;

    public static ItemType<ItemStack> ACACIA_SAPLING;

    public static ItemType<ItemStack> ACACIA_SHELF;

    public static ItemType<ItemSignStack> ACACIA_SIGN;

    public static ItemType<ItemStack> ACACIA_SLAB;

    public static ItemType<ItemStack> ACACIA_STAIRS;

    public static ItemType<ItemStack> ACACIA_STANDING_SIGN;

    public static ItemType<ItemStack> ACACIA_TRAPDOOR;

    public static ItemType<ItemStack> ACACIA_WALL_SIGN;

    public static ItemType<ItemStack> ACACIA_WOOD;

    public static ItemType<ItemStack> ACTIVATOR_RAIL;

    public static ItemType<ItemSpawnEggStack> AGENT_SPAWN_EGG;

    public static ItemType<ItemAirStack> AIR;

    public static ItemType<ItemSpawnEggStack> ALLAY_SPAWN_EGG;

    public static ItemType<ItemStack> ALLIUM;

    public static ItemType<ItemStack> ALLOW;

    public static ItemType<ItemStack> AMETHYST_BLOCK;

    public static ItemType<ItemStack> AMETHYST_CLUSTER;

    public static ItemType<ItemStack> AMETHYST_SHARD;

    public static ItemType<ItemStack> ANCIENT_DEBRIS;

    public static ItemType<ItemStack> ANDESITE;

    public static ItemType<ItemStack> ANDESITE_DOUBLE_SLAB;

    public static ItemType<ItemStack> ANDESITE_SLAB;

    public static ItemType<ItemStack> ANDESITE_STAIRS;

    public static ItemType<ItemStack> ANDESITE_WALL;

    public static ItemType<ItemStack> ANGLER_POTTERY_SHERD;

    public static ItemType<ItemStack> ANVIL;

    public static ItemType<ItemAppleStack> APPLE;

    public static ItemType<ItemStack> ARCHER_POTTERY_SHERD;

    public static ItemType<ItemStack> ARMADILLO_SCUTE;

    public static ItemType<ItemSpawnEggStack> ARMADILLO_SPAWN_EGG;

    public static ItemType<ItemStack> ARMOR_STAND;

    public static ItemType<ItemStack> ARMS_UP_POTTERY_SHERD;

    public static ItemType<ItemArrowStack> ARROW;

    public static ItemType<ItemBucketStack> AXOLOTL_BUCKET;

    public static ItemType<ItemSpawnEggStack> AXOLOTL_SPAWN_EGG;

    public static ItemType<ItemStack> AZALEA;

    public static ItemType<ItemStack> AZALEA_LEAVES;

    public static ItemType<ItemStack> AZALEA_LEAVES_FLOWERED;

    public static ItemType<ItemStack> AZURE_BLUET;

    public static ItemType<ItemBakedPotatoStack> BAKED_POTATO;

    public static ItemType<ItemStack> BALLOON;

    public static ItemType<ItemStack> BAMBOO;

    public static ItemType<ItemStack> BAMBOO_BLOCK;

    public static ItemType<ItemStack> BAMBOO_BUTTON;

    public static ItemType<ItemStack> BAMBOO_CHEST_RAFT;

    public static ItemType<ItemStack> BAMBOO_DOOR;

    public static ItemType<ItemStack> BAMBOO_DOUBLE_SLAB;

    public static ItemType<ItemStack> BAMBOO_FENCE;

    public static ItemType<ItemStack> BAMBOO_FENCE_GATE;

    public static ItemType<ItemStack> BAMBOO_HANGING_SIGN;

    public static ItemType<ItemStack> BAMBOO_MOSAIC;

    public static ItemType<ItemStack> BAMBOO_MOSAIC_DOUBLE_SLAB;

    public static ItemType<ItemStack> BAMBOO_MOSAIC_SLAB;

    public static ItemType<ItemStack> BAMBOO_MOSAIC_STAIRS;

    public static ItemType<ItemStack> BAMBOO_PLANKS;

    public static ItemType<ItemStack> BAMBOO_PRESSURE_PLATE;

    public static ItemType<ItemStack> BAMBOO_RAFT;

    public static ItemType<ItemStack> BAMBOO_SAPLING;

    public static ItemType<ItemStack> BAMBOO_SHELF;

    public static ItemType<ItemSignStack> BAMBOO_SIGN;

    public static ItemType<ItemStack> BAMBOO_SLAB;

    public static ItemType<ItemStack> BAMBOO_STAIRS;

    public static ItemType<ItemStack> BAMBOO_STANDING_SIGN;

    public static ItemType<ItemStack> BAMBOO_TRAPDOOR;

    public static ItemType<ItemStack> BAMBOO_WALL_SIGN;

    public static ItemType<ItemBannerStack> BANNER;

    public static ItemType<ItemStack> BANNER_PATTERN;

    public static ItemType<ItemStack> BARREL;

    public static ItemType<ItemStack> BARRIER;

    public static ItemType<ItemStack> BASALT;

    public static ItemType<ItemSpawnEggStack> BAT_SPAWN_EGG;

    public static ItemType<ItemStack> BEACON;

    public static ItemType<ItemStack> BED;

    public static ItemType<ItemStack> BEDROCK;

    public static ItemType<ItemStack> BEE_NEST;

    public static ItemType<ItemSpawnEggStack> BEE_SPAWN_EGG;

    public static ItemType<ItemBeefStack> BEEF;

    public static ItemType<ItemStack> BEEHIVE;

    public static ItemType<ItemBeetrootStack> BEETROOT;

    public static ItemType<ItemStack> BEETROOT_SEEDS;

    public static ItemType<ItemBeetrootSoupStack> BEETROOT_SOUP;

    public static ItemType<ItemStack> BELL;

    public static ItemType<ItemStack> BIG_DRIPLEAF;

    public static ItemType<ItemStack> BIRCH_BOAT;

    public static ItemType<ItemStack> BIRCH_BUTTON;

    public static ItemType<ItemStack> BIRCH_CHEST_BOAT;

    public static ItemType<ItemStack> BIRCH_DOOR;

    public static ItemType<ItemStack> BIRCH_DOUBLE_SLAB;

    public static ItemType<ItemStack> BIRCH_FENCE;

    public static ItemType<ItemStack> BIRCH_FENCE_GATE;

    public static ItemType<ItemStack> BIRCH_HANGING_SIGN;

    public static ItemType<ItemStack> BIRCH_LEAVES;

    public static ItemType<ItemStack> BIRCH_LOG;

    public static ItemType<ItemStack> BIRCH_PLANKS;

    public static ItemType<ItemStack> BIRCH_PRESSURE_PLATE;

    public static ItemType<ItemStack> BIRCH_SAPLING;

    public static ItemType<ItemStack> BIRCH_SHELF;

    public static ItemType<ItemSignStack> BIRCH_SIGN;

    public static ItemType<ItemStack> BIRCH_SLAB;

    public static ItemType<ItemStack> BIRCH_STAIRS;

    public static ItemType<ItemStack> BIRCH_STANDING_SIGN;

    public static ItemType<ItemStack> BIRCH_TRAPDOOR;

    public static ItemType<ItemStack> BIRCH_WALL_SIGN;

    public static ItemType<ItemStack> BIRCH_WOOD;

    public static ItemType<ItemStack> BLACK_BUNDLE;

    public static ItemType<ItemCandleStack> BLACK_CANDLE;

    public static ItemType<ItemStack> BLACK_CANDLE_CAKE;

    public static ItemType<ItemStack> BLACK_CARPET;

    public static ItemType<ItemStack> BLACK_CONCRETE;

    public static ItemType<ItemStack> BLACK_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> BLACK_CONCRETE_POWDER;

    public static ItemType<ItemStack> BLACK_CONCRETE_SLAB;

    public static ItemType<ItemStack> BLACK_CONCRETE_STAIRS;

    public static ItemType<ItemStack> BLACK_CUSHION;

    public static ItemType<ItemDyeStack> BLACK_DYE;

    public static ItemType<ItemStack> BLACK_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> BLACK_HARNESS;

    public static ItemType<ItemShulkerBoxStack> BLACK_SHULKER_BOX;

    public static ItemType<ItemStack> BLACK_STAINED_GLASS;

    public static ItemType<ItemStack> BLACK_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> BLACK_TERRACOTTA;

    public static ItemType<ItemStack> BLACK_WOOL;

    public static ItemType<ItemStack> BLACK_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> BLACK_WOOL_SLAB;

    public static ItemType<ItemStack> BLACK_WOOL_STAIRS;

    public static ItemType<ItemStack> BLACKSTONE;

    public static ItemType<ItemStack> BLACKSTONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> BLACKSTONE_SLAB;

    public static ItemType<ItemStack> BLACKSTONE_STAIRS;

    public static ItemType<ItemStack> BLACKSTONE_WALL;

    public static ItemType<ItemStack> BLADE_POTTERY_SHERD;

    public static ItemType<ItemStack> BLAST_FURNACE;

    public static ItemType<ItemStack> BLAZE_POWDER;

    public static ItemType<ItemStack> BLAZE_ROD;

    public static ItemType<ItemSpawnEggStack> BLAZE_SPAWN_EGG;

    public static ItemType<ItemStack> BLEACH;

    public static ItemType<ItemStack> BLUE_BUNDLE;

    public static ItemType<ItemCandleStack> BLUE_CANDLE;

    public static ItemType<ItemStack> BLUE_CANDLE_CAKE;

    public static ItemType<ItemStack> BLUE_CARPET;

    public static ItemType<ItemStack> BLUE_CONCRETE;

    public static ItemType<ItemStack> BLUE_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> BLUE_CONCRETE_POWDER;

    public static ItemType<ItemStack> BLUE_CONCRETE_SLAB;

    public static ItemType<ItemStack> BLUE_CONCRETE_STAIRS;

    public static ItemType<ItemStack> BLUE_CUSHION;

    public static ItemType<ItemDyeStack> BLUE_DYE;

    public static ItemType<ItemEggStack> BLUE_EGG;

    public static ItemType<ItemStack> BLUE_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> BLUE_HARNESS;

    public static ItemType<ItemStack> BLUE_ICE;

    public static ItemType<ItemStack> BLUE_ORCHID;

    public static ItemType<ItemShulkerBoxStack> BLUE_SHULKER_BOX;

    public static ItemType<ItemStack> BLUE_STAINED_GLASS;

    public static ItemType<ItemStack> BLUE_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> BLUE_TERRACOTTA;

    public static ItemType<ItemStack> BLUE_WOOL;

    public static ItemType<ItemStack> BLUE_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> BLUE_WOOL_SLAB;

    public static ItemType<ItemStack> BLUE_WOOL_STAIRS;

    public static ItemType<ItemStack> BOARD;

    public static ItemType<ItemStack> BOAT;

    public static ItemType<ItemSpawnEggStack> BOGGED_SPAWN_EGG;

    public static ItemType<ItemStack> BOLT_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> BONE;

    public static ItemType<ItemStack> BONE_BLOCK;

    public static ItemType<ItemStack> BONE_MEAL;

    public static ItemType<ItemStack> BOOK;

    public static ItemType<ItemStack> BOOKSHELF;

    public static ItemType<ItemStack> BORDER_BLOCK;

    public static ItemType<ItemStack> BORDURE_INDENTED_BANNER_PATTERN;

    public static ItemType<ItemBowStack> BOW;

    public static ItemType<ItemStack> BOWL;

    public static ItemType<ItemStack> BRAIN_CORAL;

    public static ItemType<ItemStack> BRAIN_CORAL_BLOCK;

    public static ItemType<ItemStack> BRAIN_CORAL_FAN;

    public static ItemType<ItemStack> BRAIN_CORAL_WALL_FAN;

    public static ItemType<ItemBreadStack> BREAD;

    public static ItemType<ItemStack> BREEZE_ROD;

    public static ItemType<ItemSpawnEggStack> BREEZE_SPAWN_EGG;

    public static ItemType<ItemStack> BREWER_POTTERY_SHERD;

    public static ItemType<ItemStack> BREWING_STAND;

    public static ItemType<ItemStack> BRICK;

    public static ItemType<ItemStack> BRICK_BLOCK;

    public static ItemType<ItemStack> BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> BRICK_SLAB;

    public static ItemType<ItemStack> BRICK_STAIRS;

    public static ItemType<ItemStack> BRICK_WALL;

    public static ItemType<ItemStack> BROWN_BUNDLE;

    public static ItemType<ItemCandleStack> BROWN_CANDLE;

    public static ItemType<ItemStack> BROWN_CANDLE_CAKE;

    public static ItemType<ItemStack> BROWN_CARPET;

    public static ItemType<ItemStack> BROWN_CONCRETE;

    public static ItemType<ItemStack> BROWN_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> BROWN_CONCRETE_POWDER;

    public static ItemType<ItemStack> BROWN_CONCRETE_SLAB;

    public static ItemType<ItemStack> BROWN_CONCRETE_STAIRS;

    public static ItemType<ItemStack> BROWN_CUSHION;

    public static ItemType<ItemDyeStack> BROWN_DYE;

    public static ItemType<ItemEggStack> BROWN_EGG;

    public static ItemType<ItemStack> BROWN_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> BROWN_HARNESS;

    public static ItemType<ItemStack> BROWN_MUSHROOM;

    public static ItemType<ItemStack> BROWN_MUSHROOM_BLOCK;

    public static ItemType<ItemShulkerBoxStack> BROWN_SHULKER_BOX;

    public static ItemType<ItemStack> BROWN_STAINED_GLASS;

    public static ItemType<ItemStack> BROWN_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> BROWN_TERRACOTTA;

    public static ItemType<ItemStack> BROWN_WOOL;

    public static ItemType<ItemStack> BROWN_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> BROWN_WOOL_SLAB;

    public static ItemType<ItemStack> BROWN_WOOL_STAIRS;

    public static ItemType<ItemStack> BRUSH;

    public static ItemType<ItemStack> BUBBLE_COLUMN;

    public static ItemType<ItemStack> BUBBLE_CORAL;

    public static ItemType<ItemStack> BUBBLE_CORAL_BLOCK;

    public static ItemType<ItemStack> BUBBLE_CORAL_FAN;

    public static ItemType<ItemStack> BUBBLE_CORAL_WALL_FAN;

    public static ItemType<ItemBucketStack> BUCKET;

    public static ItemType<ItemStack> BUDDING_AMETHYST;

    public static ItemType<ItemStack> BUNDLE;

    public static ItemType<ItemStack> BURN_POTTERY_SHERD;

    public static ItemType<ItemStack> BUSH;

    public static ItemType<ItemStack> CACTUS;

    public static ItemType<ItemStack> CACTUS_FLOWER;

    public static ItemType<ItemStack> CAKE;

    public static ItemType<ItemStack> CALCITE;

    public static ItemType<ItemStack> CALIBRATED_SCULK_SENSOR;

    public static ItemType<ItemSpawnEggStack> CAMEL_HUSK_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> CAMEL_SPAWN_EGG;

    public static ItemType<ItemStack> CAMERA;

    public static ItemType<ItemStack> CAMPFIRE;

    public static ItemType<ItemCandleStack> CANDLE;

    public static ItemType<ItemStack> CANDLE_CAKE;

    public static ItemType<ItemStack> CARPET;

    public static ItemType<ItemCarrotStack> CARROT;

    public static ItemType<ItemStack> CARROT_ON_A_STICK;

    public static ItemType<ItemStack> CARROTS;

    public static ItemType<ItemStack> CARTOGRAPHY_TABLE;

    public static ItemType<ItemCarvedPumpkinStack> CARVED_PUMPKIN;

    public static ItemType<ItemSpawnEggStack> CAT_SPAWN_EGG;

    public static ItemType<ItemStack> CAULDRON;

    public static ItemType<ItemSpawnEggStack> CAVE_SPIDER_SPAWN_EGG;

    public static ItemType<ItemStack> CAVE_VINES;

    public static ItemType<ItemStack> CAVE_VINES_BODY_WITH_BERRIES;

    public static ItemType<ItemStack> CAVE_VINES_HEAD_WITH_BERRIES;

    public static ItemType<ItemStack> CHAIN_COMMAND_BLOCK;

    public static ItemType<ItemBootsStack> CHAINMAIL_BOOTS;

    public static ItemType<ItemChestplateStack> CHAINMAIL_CHESTPLATE;

    public static ItemType<ItemHelmetStack> CHAINMAIL_HELMET;

    public static ItemType<ItemLeggingsStack> CHAINMAIL_LEGGINGS;

    public static ItemType<ItemStack> CHALKBOARD;

    public static ItemType<ItemStack> CHARCOAL;

    public static ItemType<ItemStack> CHEMICAL_HEAT;

    public static ItemType<ItemStack> CHEMISTRY_TABLE;

    public static ItemType<ItemStack> CHERRY_BOAT;

    public static ItemType<ItemStack> CHERRY_BUTTON;

    public static ItemType<ItemStack> CHERRY_CHEST_BOAT;

    public static ItemType<ItemStack> CHERRY_DOOR;

    public static ItemType<ItemStack> CHERRY_DOUBLE_SLAB;

    public static ItemType<ItemStack> CHERRY_FENCE;

    public static ItemType<ItemStack> CHERRY_FENCE_GATE;

    public static ItemType<ItemStack> CHERRY_HANGING_SIGN;

    public static ItemType<ItemStack> CHERRY_LEAVES;

    public static ItemType<ItemStack> CHERRY_LOG;

    public static ItemType<ItemStack> CHERRY_PLANKS;

    public static ItemType<ItemStack> CHERRY_PRESSURE_PLATE;

    public static ItemType<ItemStack> CHERRY_SAPLING;

    public static ItemType<ItemStack> CHERRY_SHELF;

    public static ItemType<ItemSignStack> CHERRY_SIGN;

    public static ItemType<ItemStack> CHERRY_SLAB;

    public static ItemType<ItemStack> CHERRY_STAIRS;

    public static ItemType<ItemStack> CHERRY_STANDING_SIGN;

    public static ItemType<ItemStack> CHERRY_TRAPDOOR;

    public static ItemType<ItemStack> CHERRY_WALL_SIGN;

    public static ItemType<ItemStack> CHERRY_WOOD;

    public static ItemType<ItemStack> CHEST;

    public static ItemType<ItemStack> CHEST_BOAT;

    public static ItemType<ItemStack> CHEST_MINECART;

    public static ItemType<ItemChickenStack> CHICKEN;

    public static ItemType<ItemSpawnEggStack> CHICKEN_SPAWN_EGG;

    public static ItemType<ItemStack> CHIPPED_ANVIL;

    public static ItemType<ItemStack> CHISELED_BOOKSHELF;

    public static ItemType<ItemStack> CHISELED_CINNABAR;

    public static ItemType<ItemStack> CHISELED_COPPER;

    public static ItemType<ItemStack> CHISELED_DEEPSLATE;

    public static ItemType<ItemStack> CHISELED_NETHER_BRICKS;

    public static ItemType<ItemStack> CHISELED_POLISHED_BLACKSTONE;

    public static ItemType<ItemStack> CHISELED_QUARTZ_BLOCK;

    public static ItemType<ItemStack> CHISELED_RED_SANDSTONE;

    public static ItemType<ItemStack> CHISELED_RESIN_BRICKS;

    public static ItemType<ItemStack> CHISELED_SANDSTONE;

    public static ItemType<ItemStack> CHISELED_STONE_BRICKS;

    public static ItemType<ItemStack> CHISELED_SULFUR;

    public static ItemType<ItemStack> CHISELED_TUFF;

    public static ItemType<ItemStack> CHISELED_TUFF_BRICKS;

    public static ItemType<ItemStack> CHORUS_FLOWER;

    public static ItemType<ItemChorusFruitStack> CHORUS_FRUIT;

    public static ItemType<ItemStack> CHORUS_PLANT;

    public static ItemType<ItemStack> CINNABAR;

    public static ItemType<ItemStack> CINNABAR_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> CINNABAR_BRICK_SLAB;

    public static ItemType<ItemStack> CINNABAR_BRICK_STAIRS;

    public static ItemType<ItemStack> CINNABAR_BRICK_WALL;

    public static ItemType<ItemStack> CINNABAR_BRICKS;

    public static ItemType<ItemStack> CINNABAR_DOUBLE_SLAB;

    public static ItemType<ItemStack> CINNABAR_SLAB;

    public static ItemType<ItemStack> CINNABAR_STAIRS;

    public static ItemType<ItemStack> CINNABAR_WALL;

    public static ItemType<ItemStack> CLAY;

    public static ItemType<ItemStack> CLAY_BALL;

    public static ItemType<ItemStack> CLIENT_REQUEST_PLACEHOLDER_BLOCK;

    public static ItemType<ItemStack> CLOCK;

    public static ItemType<ItemStack> CLOSED_EYEBLOSSOM;

    public static ItemType<ItemStack> COAL;

    public static ItemType<ItemStack> COAL_BLOCK;

    public static ItemType<ItemStack> COAL_ORE;

    public static ItemType<ItemStack> COARSE_DIRT;

    public static ItemType<ItemStack> COAST_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> COBBLED_DEEPSLATE;

    public static ItemType<ItemStack> COBBLED_DEEPSLATE_DOUBLE_SLAB;

    public static ItemType<ItemStack> COBBLED_DEEPSLATE_SLAB;

    public static ItemType<ItemStack> COBBLED_DEEPSLATE_STAIRS;

    public static ItemType<ItemStack> COBBLED_DEEPSLATE_WALL;

    public static ItemType<ItemStack> COBBLESTONE;

    public static ItemType<ItemStack> COBBLESTONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> COBBLESTONE_SLAB;

    public static ItemType<ItemStack> COBBLESTONE_WALL;

    public static ItemType<ItemStack> COCOA;

    public static ItemType<ItemStack> COCOA_BEANS;

    public static ItemType<ItemCodStack> COD;

    public static ItemType<ItemBucketStack> COD_BUCKET;

    public static ItemType<ItemSpawnEggStack> COD_SPAWN_EGG;

    public static ItemType<ItemStack> COLORED_TORCH_BLUE;

    public static ItemType<ItemStack> COLORED_TORCH_BP;

    public static ItemType<ItemStack> COLORED_TORCH_GREEN;

    public static ItemType<ItemStack> COLORED_TORCH_PURPLE;

    public static ItemType<ItemStack> COLORED_TORCH_RED;

    public static ItemType<ItemStack> COLORED_TORCH_RG;

    public static ItemType<ItemStack> COMMAND_BLOCK;

    public static ItemType<ItemStack> COMMAND_BLOCK_MINECART;

    public static ItemType<ItemStack> COMPARATOR;

    public static ItemType<ItemStack> COMPASS;

    public static ItemType<ItemStack> COMPOSTER;

    public static ItemType<ItemStack> COMPOUND;

    public static ItemType<ItemStack> COMPOUND_CREATOR;

    public static ItemType<ItemStack> CONCRETE;

    public static ItemType<ItemStack> CONCRETE_POWDER;

    public static ItemType<ItemStack> CONDUIT;

    public static ItemType<ItemCookedBeefStack> COOKED_BEEF;

    public static ItemType<ItemCookedChickenStack> COOKED_CHICKEN;

    public static ItemType<ItemCookedCodStack> COOKED_COD;

    public static ItemType<ItemCookedMuttonStack> COOKED_MUTTON;

    public static ItemType<ItemCookedPorkchopStack> COOKED_PORKCHOP;

    public static ItemType<ItemCookedRabbitStack> COOKED_RABBIT;

    public static ItemType<ItemCookedSalmonStack> COOKED_SALMON;

    public static ItemType<ItemCookieStack> COOKIE;

    public static ItemType<ItemAxeStack> COPPER_AXE;

    public static ItemType<ItemStack> COPPER_BARS;

    public static ItemType<ItemStack> COPPER_BLOCK;

    public static ItemType<ItemBootsStack> COPPER_BOOTS;

    public static ItemType<ItemStack> COPPER_BULB;

    public static ItemType<ItemStack> COPPER_CHAIN;

    public static ItemType<ItemStack> COPPER_CHEST;

    public static ItemType<ItemChestplateStack> COPPER_CHESTPLATE;

    public static ItemType<ItemStack> COPPER_DOOR;

    public static ItemType<ItemSpawnEggStack> COPPER_GOLEM_SPAWN_EGG;

    public static ItemType<ItemStack> COPPER_GOLEM_STATUE;

    public static ItemType<ItemStack> COPPER_GRATE;

    public static ItemType<ItemHelmetStack> COPPER_HELMET;

    public static ItemType<ItemHoeStack> COPPER_HOE;

    public static ItemType<ItemHorseArmorStack> COPPER_HORSE_ARMOR;

    public static ItemType<ItemStack> COPPER_INGOT;

    public static ItemType<ItemStack> COPPER_LANTERN;

    public static ItemType<ItemLeggingsStack> COPPER_LEGGINGS;

    public static ItemType<ItemStack> COPPER_NAUTILUS_ARMOR;

    public static ItemType<ItemStack> COPPER_NUGGET;

    public static ItemType<ItemStack> COPPER_ORE;

    public static ItemType<ItemPickaxeStack> COPPER_PICKAXE;

    public static ItemType<ItemShovelStack> COPPER_SHOVEL;

    public static ItemType<ItemSpearStack> COPPER_SPEAR;

    public static ItemType<ItemSwordStack> COPPER_SWORD;

    public static ItemType<ItemStack> COPPER_TORCH;

    public static ItemType<ItemStack> COPPER_TRAPDOOR;

    public static ItemType<ItemStack> CORAL;

    public static ItemType<ItemStack> CORAL_BLOCK;

    public static ItemType<ItemStack> CORAL_FAN;

    public static ItemType<ItemStack> CORAL_FAN_DEAD;

    public static ItemType<ItemStack> CORNFLOWER;

    public static ItemType<ItemSpawnEggStack> COW_SPAWN_EGG;

    public static ItemType<ItemStack> CRACKED_DEEPSLATE_BRICKS;

    public static ItemType<ItemStack> CRACKED_DEEPSLATE_TILES;

    public static ItemType<ItemStack> CRACKED_NETHER_BRICKS;

    public static ItemType<ItemStack> CRACKED_POLISHED_BLACKSTONE_BRICKS;

    public static ItemType<ItemStack> CRACKED_STONE_BRICKS;

    public static ItemType<ItemStack> CRAFTER;

    public static ItemType<ItemStack> CRAFTING_TABLE;

    public static ItemType<ItemStack> CREAKING_HEART;

    public static ItemType<ItemSpawnEggStack> CREAKING_SPAWN_EGG;

    public static ItemType<ItemStack> CREEPER_BANNER_PATTERN;

    public static ItemType<ItemHeadStack> CREEPER_HEAD;

    public static ItemType<ItemSpawnEggStack> CREEPER_SPAWN_EGG;

    public static ItemType<ItemStack> CRIMSON_BUTTON;

    public static ItemType<ItemStack> CRIMSON_DOOR;

    public static ItemType<ItemStack> CRIMSON_DOUBLE_SLAB;

    public static ItemType<ItemStack> CRIMSON_FENCE;

    public static ItemType<ItemStack> CRIMSON_FENCE_GATE;

    public static ItemType<ItemStack> CRIMSON_FUNGUS;

    public static ItemType<ItemStack> CRIMSON_HANGING_SIGN;

    public static ItemType<ItemStack> CRIMSON_HYPHAE;

    public static ItemType<ItemStack> CRIMSON_NYLIUM;

    public static ItemType<ItemStack> CRIMSON_PLANKS;

    public static ItemType<ItemStack> CRIMSON_PRESSURE_PLATE;

    public static ItemType<ItemStack> CRIMSON_ROOTS;

    public static ItemType<ItemStack> CRIMSON_SHELF;

    public static ItemType<ItemSignStack> CRIMSON_SIGN;

    public static ItemType<ItemStack> CRIMSON_SLAB;

    public static ItemType<ItemStack> CRIMSON_STAIRS;

    public static ItemType<ItemStack> CRIMSON_STANDING_SIGN;

    public static ItemType<ItemStack> CRIMSON_STEM;

    public static ItemType<ItemStack> CRIMSON_TRAPDOOR;

    public static ItemType<ItemStack> CRIMSON_WALL_SIGN;

    public static ItemType<ItemCrossbowStack> CROSSBOW;

    public static ItemType<ItemStack> CRYING_OBSIDIAN;

    public static ItemType<ItemStack> CUT_COPPER;

    public static ItemType<ItemStack> CUT_COPPER_SLAB;

    public static ItemType<ItemStack> CUT_COPPER_STAIRS;

    public static ItemType<ItemStack> CUT_RED_SANDSTONE;

    public static ItemType<ItemStack> CUT_RED_SANDSTONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> CUT_RED_SANDSTONE_SLAB;

    public static ItemType<ItemStack> CUT_SANDSTONE;

    public static ItemType<ItemStack> CUT_SANDSTONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> CUT_SANDSTONE_SLAB;

    public static ItemType<ItemStack> CYAN_BUNDLE;

    public static ItemType<ItemCandleStack> CYAN_CANDLE;

    public static ItemType<ItemStack> CYAN_CANDLE_CAKE;

    public static ItemType<ItemStack> CYAN_CARPET;

    public static ItemType<ItemStack> CYAN_CONCRETE;

    public static ItemType<ItemStack> CYAN_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> CYAN_CONCRETE_POWDER;

    public static ItemType<ItemStack> CYAN_CONCRETE_SLAB;

    public static ItemType<ItemStack> CYAN_CONCRETE_STAIRS;

    public static ItemType<ItemStack> CYAN_CUSHION;

    public static ItemType<ItemDyeStack> CYAN_DYE;

    public static ItemType<ItemStack> CYAN_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> CYAN_HARNESS;

    public static ItemType<ItemShulkerBoxStack> CYAN_SHULKER_BOX;

    public static ItemType<ItemStack> CYAN_STAINED_GLASS;

    public static ItemType<ItemStack> CYAN_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> CYAN_TERRACOTTA;

    public static ItemType<ItemStack> CYAN_WOOL;

    public static ItemType<ItemStack> CYAN_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> CYAN_WOOL_SLAB;

    public static ItemType<ItemStack> CYAN_WOOL_STAIRS;

    public static ItemType<ItemStack> DAMAGED_ANVIL;

    public static ItemType<ItemStack> DANDELION;

    public static ItemType<ItemStack> DANGER_POTTERY_SHERD;

    public static ItemType<ItemStack> DARK_OAK_BOAT;

    public static ItemType<ItemStack> DARK_OAK_BUTTON;

    public static ItemType<ItemStack> DARK_OAK_CHEST_BOAT;

    public static ItemType<ItemStack> DARK_OAK_DOOR;

    public static ItemType<ItemStack> DARK_OAK_DOUBLE_SLAB;

    public static ItemType<ItemStack> DARK_OAK_FENCE;

    public static ItemType<ItemStack> DARK_OAK_FENCE_GATE;

    public static ItemType<ItemStack> DARK_OAK_HANGING_SIGN;

    public static ItemType<ItemStack> DARK_OAK_LEAVES;

    public static ItemType<ItemStack> DARK_OAK_LOG;

    public static ItemType<ItemStack> DARK_OAK_PLANKS;

    public static ItemType<ItemStack> DARK_OAK_PRESSURE_PLATE;

    public static ItemType<ItemStack> DARK_OAK_SAPLING;

    public static ItemType<ItemStack> DARK_OAK_SHELF;

    public static ItemType<ItemSignStack> DARK_OAK_SIGN;

    public static ItemType<ItemStack> DARK_OAK_SLAB;

    public static ItemType<ItemStack> DARK_OAK_STAIRS;

    public static ItemType<ItemStack> DARK_OAK_TRAPDOOR;

    public static ItemType<ItemStack> DARK_OAK_WOOD;

    public static ItemType<ItemStack> DARK_PRISMARINE;

    public static ItemType<ItemStack> DARK_PRISMARINE_DOUBLE_SLAB;

    public static ItemType<ItemStack> DARK_PRISMARINE_SLAB;

    public static ItemType<ItemStack> DARK_PRISMARINE_STAIRS;

    public static ItemType<ItemStack> DARKOAK_STANDING_SIGN;

    public static ItemType<ItemStack> DARKOAK_WALL_SIGN;

    public static ItemType<ItemStack> DAYLIGHT_DETECTOR;

    public static ItemType<ItemStack> DAYLIGHT_DETECTOR_INVERTED;

    public static ItemType<ItemStack> DEAD_BRAIN_CORAL;

    public static ItemType<ItemStack> DEAD_BRAIN_CORAL_BLOCK;

    public static ItemType<ItemStack> DEAD_BRAIN_CORAL_FAN;

    public static ItemType<ItemStack> DEAD_BRAIN_CORAL_WALL_FAN;

    public static ItemType<ItemStack> DEAD_BUBBLE_CORAL;

    public static ItemType<ItemStack> DEAD_BUBBLE_CORAL_BLOCK;

    public static ItemType<ItemStack> DEAD_BUBBLE_CORAL_FAN;

    public static ItemType<ItemStack> DEAD_BUBBLE_CORAL_WALL_FAN;

    public static ItemType<ItemStack> DEAD_FIRE_CORAL;

    public static ItemType<ItemStack> DEAD_FIRE_CORAL_BLOCK;

    public static ItemType<ItemStack> DEAD_FIRE_CORAL_FAN;

    public static ItemType<ItemStack> DEAD_FIRE_CORAL_WALL_FAN;

    public static ItemType<ItemStack> DEAD_HORN_CORAL;

    public static ItemType<ItemStack> DEAD_HORN_CORAL_BLOCK;

    public static ItemType<ItemStack> DEAD_HORN_CORAL_FAN;

    public static ItemType<ItemStack> DEAD_HORN_CORAL_WALL_FAN;

    public static ItemType<ItemStack> DEAD_TUBE_CORAL;

    public static ItemType<ItemStack> DEAD_TUBE_CORAL_BLOCK;

    public static ItemType<ItemStack> DEAD_TUBE_CORAL_FAN;

    public static ItemType<ItemStack> DEAD_TUBE_CORAL_WALL_FAN;

    public static ItemType<ItemStack> DEADBUSH;

    public static ItemType<ItemDecoratedPotStack> DECORATED_POT;

    public static ItemType<ItemStack> DEEPSLATE;

    public static ItemType<ItemStack> DEEPSLATE_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> DEEPSLATE_BRICK_SLAB;

    public static ItemType<ItemStack> DEEPSLATE_BRICK_STAIRS;

    public static ItemType<ItemStack> DEEPSLATE_BRICK_WALL;

    public static ItemType<ItemStack> DEEPSLATE_BRICKS;

    public static ItemType<ItemStack> DEEPSLATE_COAL_ORE;

    public static ItemType<ItemStack> DEEPSLATE_COPPER_ORE;

    public static ItemType<ItemStack> DEEPSLATE_DIAMOND_ORE;

    public static ItemType<ItemStack> DEEPSLATE_EMERALD_ORE;

    public static ItemType<ItemStack> DEEPSLATE_GOLD_ORE;

    public static ItemType<ItemStack> DEEPSLATE_IRON_ORE;

    public static ItemType<ItemStack> DEEPSLATE_LAPIS_ORE;

    public static ItemType<ItemStack> DEEPSLATE_REDSTONE_ORE;

    public static ItemType<ItemStack> DEEPSLATE_TILE_DOUBLE_SLAB;

    public static ItemType<ItemStack> DEEPSLATE_TILE_SLAB;

    public static ItemType<ItemStack> DEEPSLATE_TILE_STAIRS;

    public static ItemType<ItemStack> DEEPSLATE_TILE_WALL;

    public static ItemType<ItemStack> DEEPSLATE_TILES;

    public static ItemType<ItemStack> DENY;

    public static ItemType<ItemStack> DEPRECATED_ANVIL;

    public static ItemType<ItemStack> DEPRECATED_PURPUR_BLOCK_1;

    public static ItemType<ItemStack> DEPRECATED_PURPUR_BLOCK_2;

    public static ItemType<ItemStack> DETECTOR_RAIL;

    public static ItemType<ItemDiamondStack> DIAMOND;

    public static ItemType<ItemAxeStack> DIAMOND_AXE;

    public static ItemType<ItemStack> DIAMOND_BLOCK;

    public static ItemType<ItemBootsStack> DIAMOND_BOOTS;

    public static ItemType<ItemChestplateStack> DIAMOND_CHESTPLATE;

    public static ItemType<ItemHelmetStack> DIAMOND_HELMET;

    public static ItemType<ItemHoeStack> DIAMOND_HOE;

    public static ItemType<ItemHorseArmorStack> DIAMOND_HORSE_ARMOR;

    public static ItemType<ItemLeggingsStack> DIAMOND_LEGGINGS;

    public static ItemType<ItemStack> DIAMOND_NAUTILUS_ARMOR;

    public static ItemType<ItemStack> DIAMOND_ORE;

    public static ItemType<ItemPickaxeStack> DIAMOND_PICKAXE;

    public static ItemType<ItemShovelStack> DIAMOND_SHOVEL;

    public static ItemType<ItemSpearStack> DIAMOND_SPEAR;

    public static ItemType<ItemSwordStack> DIAMOND_SWORD;

    public static ItemType<ItemStack> DIORITE;

    public static ItemType<ItemStack> DIORITE_DOUBLE_SLAB;

    public static ItemType<ItemStack> DIORITE_SLAB;

    public static ItemType<ItemStack> DIORITE_STAIRS;

    public static ItemType<ItemStack> DIORITE_WALL;

    public static ItemType<ItemStack> DIRT;

    public static ItemType<ItemStack> DIRT_WITH_ROOTS;

    public static ItemType<ItemStack> DISC_FRAGMENT_5;

    public static ItemType<ItemStack> DISPENSER;

    public static ItemType<ItemSpawnEggStack> DOLPHIN_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> DONKEY_SPAWN_EGG;

    public static ItemType<ItemStack> DOUBLE_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> DOUBLE_PLANT;

    public static ItemType<ItemStack> DOUBLE_STONE_BLOCK_SLAB;

    public static ItemType<ItemStack> DOUBLE_STONE_BLOCK_SLAB2;

    public static ItemType<ItemStack> DOUBLE_STONE_BLOCK_SLAB3;

    public static ItemType<ItemStack> DOUBLE_STONE_BLOCK_SLAB4;

    public static ItemType<ItemStack> DRAGON_BREATH;

    public static ItemType<ItemStack> DRAGON_EGG;

    public static ItemType<ItemHeadStack> DRAGON_HEAD;

    public static ItemType<ItemStack> DRIED_GHAST;

    public static ItemType<ItemDriedKelpStack> DRIED_KELP;

    public static ItemType<ItemStack> DRIED_KELP_BLOCK;

    public static ItemType<ItemStack> DRIPSTONE_BLOCK;

    public static ItemType<ItemStack> DROPPER;

    public static ItemType<ItemSpawnEggStack> DROWNED_SPAWN_EGG;

    public static ItemType<ItemStack> DUNE_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemDyeStack> DYE;

    public static ItemType<ItemStack> ECHO_SHARD;

    public static ItemType<ItemEggStack> EGG;

    public static ItemType<ItemSpawnEggStack> ELDER_GUARDIAN_SPAWN_EGG;

    public static ItemType<ItemStack> ELEMENT_0;

    public static ItemType<ItemStack> ELEMENT_1;

    public static ItemType<ItemStack> ELEMENT_10;

    public static ItemType<ItemStack> ELEMENT_100;

    public static ItemType<ItemStack> ELEMENT_101;

    public static ItemType<ItemStack> ELEMENT_102;

    public static ItemType<ItemStack> ELEMENT_103;

    public static ItemType<ItemStack> ELEMENT_104;

    public static ItemType<ItemStack> ELEMENT_105;

    public static ItemType<ItemStack> ELEMENT_106;

    public static ItemType<ItemStack> ELEMENT_107;

    public static ItemType<ItemStack> ELEMENT_108;

    public static ItemType<ItemStack> ELEMENT_109;

    public static ItemType<ItemStack> ELEMENT_11;

    public static ItemType<ItemStack> ELEMENT_110;

    public static ItemType<ItemStack> ELEMENT_111;

    public static ItemType<ItemStack> ELEMENT_112;

    public static ItemType<ItemStack> ELEMENT_113;

    public static ItemType<ItemStack> ELEMENT_114;

    public static ItemType<ItemStack> ELEMENT_115;

    public static ItemType<ItemStack> ELEMENT_116;

    public static ItemType<ItemStack> ELEMENT_117;

    public static ItemType<ItemStack> ELEMENT_118;

    public static ItemType<ItemStack> ELEMENT_12;

    public static ItemType<ItemStack> ELEMENT_13;

    public static ItemType<ItemStack> ELEMENT_14;

    public static ItemType<ItemStack> ELEMENT_15;

    public static ItemType<ItemStack> ELEMENT_16;

    public static ItemType<ItemStack> ELEMENT_17;

    public static ItemType<ItemStack> ELEMENT_18;

    public static ItemType<ItemStack> ELEMENT_19;

    public static ItemType<ItemStack> ELEMENT_2;

    public static ItemType<ItemStack> ELEMENT_20;

    public static ItemType<ItemStack> ELEMENT_21;

    public static ItemType<ItemStack> ELEMENT_22;

    public static ItemType<ItemStack> ELEMENT_23;

    public static ItemType<ItemStack> ELEMENT_24;

    public static ItemType<ItemStack> ELEMENT_25;

    public static ItemType<ItemStack> ELEMENT_26;

    public static ItemType<ItemStack> ELEMENT_27;

    public static ItemType<ItemStack> ELEMENT_28;

    public static ItemType<ItemStack> ELEMENT_29;

    public static ItemType<ItemStack> ELEMENT_3;

    public static ItemType<ItemStack> ELEMENT_30;

    public static ItemType<ItemStack> ELEMENT_31;

    public static ItemType<ItemStack> ELEMENT_32;

    public static ItemType<ItemStack> ELEMENT_33;

    public static ItemType<ItemStack> ELEMENT_34;

    public static ItemType<ItemStack> ELEMENT_35;

    public static ItemType<ItemStack> ELEMENT_36;

    public static ItemType<ItemStack> ELEMENT_37;

    public static ItemType<ItemStack> ELEMENT_38;

    public static ItemType<ItemStack> ELEMENT_39;

    public static ItemType<ItemStack> ELEMENT_4;

    public static ItemType<ItemStack> ELEMENT_40;

    public static ItemType<ItemStack> ELEMENT_41;

    public static ItemType<ItemStack> ELEMENT_42;

    public static ItemType<ItemStack> ELEMENT_43;

    public static ItemType<ItemStack> ELEMENT_44;

    public static ItemType<ItemStack> ELEMENT_45;

    public static ItemType<ItemStack> ELEMENT_46;

    public static ItemType<ItemStack> ELEMENT_47;

    public static ItemType<ItemStack> ELEMENT_48;

    public static ItemType<ItemStack> ELEMENT_49;

    public static ItemType<ItemStack> ELEMENT_5;

    public static ItemType<ItemStack> ELEMENT_50;

    public static ItemType<ItemStack> ELEMENT_51;

    public static ItemType<ItemStack> ELEMENT_52;

    public static ItemType<ItemStack> ELEMENT_53;

    public static ItemType<ItemStack> ELEMENT_54;

    public static ItemType<ItemStack> ELEMENT_55;

    public static ItemType<ItemStack> ELEMENT_56;

    public static ItemType<ItemStack> ELEMENT_57;

    public static ItemType<ItemStack> ELEMENT_58;

    public static ItemType<ItemStack> ELEMENT_59;

    public static ItemType<ItemStack> ELEMENT_6;

    public static ItemType<ItemStack> ELEMENT_60;

    public static ItemType<ItemStack> ELEMENT_61;

    public static ItemType<ItemStack> ELEMENT_62;

    public static ItemType<ItemStack> ELEMENT_63;

    public static ItemType<ItemStack> ELEMENT_64;

    public static ItemType<ItemStack> ELEMENT_65;

    public static ItemType<ItemStack> ELEMENT_66;

    public static ItemType<ItemStack> ELEMENT_67;

    public static ItemType<ItemStack> ELEMENT_68;

    public static ItemType<ItemStack> ELEMENT_69;

    public static ItemType<ItemStack> ELEMENT_7;

    public static ItemType<ItemStack> ELEMENT_70;

    public static ItemType<ItemStack> ELEMENT_71;

    public static ItemType<ItemStack> ELEMENT_72;

    public static ItemType<ItemStack> ELEMENT_73;

    public static ItemType<ItemStack> ELEMENT_74;

    public static ItemType<ItemStack> ELEMENT_75;

    public static ItemType<ItemStack> ELEMENT_76;

    public static ItemType<ItemStack> ELEMENT_77;

    public static ItemType<ItemStack> ELEMENT_78;

    public static ItemType<ItemStack> ELEMENT_79;

    public static ItemType<ItemStack> ELEMENT_8;

    public static ItemType<ItemStack> ELEMENT_80;

    public static ItemType<ItemStack> ELEMENT_81;

    public static ItemType<ItemStack> ELEMENT_82;

    public static ItemType<ItemStack> ELEMENT_83;

    public static ItemType<ItemStack> ELEMENT_84;

    public static ItemType<ItemStack> ELEMENT_85;

    public static ItemType<ItemStack> ELEMENT_86;

    public static ItemType<ItemStack> ELEMENT_87;

    public static ItemType<ItemStack> ELEMENT_88;

    public static ItemType<ItemStack> ELEMENT_89;

    public static ItemType<ItemStack> ELEMENT_9;

    public static ItemType<ItemStack> ELEMENT_90;

    public static ItemType<ItemStack> ELEMENT_91;

    public static ItemType<ItemStack> ELEMENT_92;

    public static ItemType<ItemStack> ELEMENT_93;

    public static ItemType<ItemStack> ELEMENT_94;

    public static ItemType<ItemStack> ELEMENT_95;

    public static ItemType<ItemStack> ELEMENT_96;

    public static ItemType<ItemStack> ELEMENT_97;

    public static ItemType<ItemStack> ELEMENT_98;

    public static ItemType<ItemStack> ELEMENT_99;

    public static ItemType<ItemStack> ELEMENT_CONSTRUCTOR;

    public static ItemType<ItemElytraStack> ELYTRA;

    public static ItemType<ItemStack> EMERALD;

    public static ItemType<ItemStack> EMERALD_BLOCK;

    public static ItemType<ItemStack> EMERALD_ORE;

    public static ItemType<ItemStack> EMPTY_MAP;

    public static ItemType<ItemStack> ENCHANTED_BOOK;

    public static ItemType<ItemEnchantedGoldenAppleStack> ENCHANTED_GOLDEN_APPLE;

    public static ItemType<ItemStack> ENCHANTING_TABLE;

    public static ItemType<ItemStack> END_BRICK_STAIRS;

    public static ItemType<ItemStack> END_BRICKS;

    public static ItemType<ItemStack> END_CRYSTAL;

    public static ItemType<ItemStack> END_GATEWAY;

    public static ItemType<ItemStack> END_PORTAL;

    public static ItemType<ItemStack> END_PORTAL_FRAME;

    public static ItemType<ItemStack> END_ROD;

    public static ItemType<ItemStack> END_STONE;

    public static ItemType<ItemStack> END_STONE_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> END_STONE_BRICK_SLAB;

    public static ItemType<ItemStack> END_STONE_BRICK_WALL;

    public static ItemType<ItemStack> ENDER_CHEST;

    public static ItemType<ItemSpawnEggStack> ENDER_DRAGON_SPAWN_EGG;

    public static ItemType<ItemStack> ENDER_EYE;

    public static ItemType<ItemEnderPearlStack> ENDER_PEARL;

    public static ItemType<ItemSpawnEggStack> ENDERMAN_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> ENDERMITE_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> EVOKER_SPAWN_EGG;

    public static ItemType<ItemExperienceBottleStack> EXPERIENCE_BOTTLE;

    public static ItemType<ItemStack> EXPLORER_POTTERY_SHERD;

    public static ItemType<ItemStack> EXPOSED_CHISELED_COPPER;

    public static ItemType<ItemStack> EXPOSED_COPPER;

    public static ItemType<ItemStack> EXPOSED_COPPER_BARS;

    public static ItemType<ItemStack> EXPOSED_COPPER_BULB;

    public static ItemType<ItemStack> EXPOSED_COPPER_CHAIN;

    public static ItemType<ItemStack> EXPOSED_COPPER_CHEST;

    public static ItemType<ItemStack> EXPOSED_COPPER_DOOR;

    public static ItemType<ItemStack> EXPOSED_COPPER_GOLEM_STATUE;

    public static ItemType<ItemStack> EXPOSED_COPPER_GRATE;

    public static ItemType<ItemStack> EXPOSED_COPPER_LANTERN;

    public static ItemType<ItemStack> EXPOSED_COPPER_TRAPDOOR;

    public static ItemType<ItemStack> EXPOSED_CUT_COPPER;

    public static ItemType<ItemStack> EXPOSED_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> EXPOSED_CUT_COPPER_STAIRS;

    public static ItemType<ItemStack> EXPOSED_DOUBLE_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> EXPOSED_LIGHTNING_ROD;

    public static ItemType<ItemStack> EYE_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> FARMLAND;

    public static ItemType<ItemStack> FEATHER;

    public static ItemType<ItemStack> FENCE;

    public static ItemType<ItemStack> FENCE_GATE;

    public static ItemType<ItemStack> FERMENTED_SPIDER_EYE;

    public static ItemType<ItemStack> FERN;

    public static ItemType<ItemStack> FIELD_MASONED_BANNER_PATTERN;

    public static ItemType<ItemFilledMapStack> FILLED_MAP;

    public static ItemType<ItemStack> FIRE;

    public static ItemType<ItemFireChargeStack> FIRE_CHARGE;

    public static ItemType<ItemStack> FIRE_CORAL;

    public static ItemType<ItemStack> FIRE_CORAL_BLOCK;

    public static ItemType<ItemStack> FIRE_CORAL_FAN;

    public static ItemType<ItemStack> FIRE_CORAL_WALL_FAN;

    public static ItemType<ItemStack> FIREFLY_BUSH;

    public static ItemType<ItemFireworkRocketStack> FIREWORK_ROCKET;

    public static ItemType<ItemFireworkStarStack> FIREWORK_STAR;

    public static ItemType<ItemFishingRodStack> FISHING_ROD;

    public static ItemType<ItemStack> FLETCHING_TABLE;

    public static ItemType<ItemStack> FLINT;

    public static ItemType<ItemStack> FLINT_AND_STEEL;

    public static ItemType<ItemStack> FLOW_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> FLOW_BANNER_PATTERN;

    public static ItemType<ItemStack> FLOW_POTTERY_SHERD;

    public static ItemType<ItemStack> FLOWER_BANNER_PATTERN;

    public static ItemType<ItemStack> FLOWER_POT;

    public static ItemType<ItemStack> FLOWERING_AZALEA;

    public static ItemType<ItemStack> FLOWING_LAVA;

    public static ItemType<ItemStack> FLOWING_WATER;

    public static ItemType<ItemSpawnEggStack> FOX_SPAWN_EGG;

    public static ItemType<ItemStack> FRAME;

    public static ItemType<ItemStack> FRIEND_POTTERY_SHERD;

    public static ItemType<ItemStack> FROG_SPAWN;

    public static ItemType<ItemSpawnEggStack> FROG_SPAWN_EGG;

    public static ItemType<ItemStack> FROSTED_ICE;

    public static ItemType<ItemStack> FURNACE;

    public static ItemType<ItemSpawnEggStack> GHAST_SPAWN_EGG;

    public static ItemType<ItemStack> GHAST_TEAR;

    public static ItemType<ItemStack> GILDED_BLACKSTONE;

    public static ItemType<ItemStack> GLASS;

    public static ItemType<ItemStack> GLASS_BOTTLE;

    public static ItemType<ItemStack> GLASS_PANE;

    public static ItemType<ItemStack> GLISTERING_MELON_SLICE;

    public static ItemType<ItemStack> GLOBE_BANNER_PATTERN;

    public static ItemType<ItemGlowBerriesStack> GLOW_BERRIES;

    public static ItemType<ItemStack> GLOW_FRAME;

    public static ItemType<ItemStack> GLOW_INK_SAC;

    public static ItemType<ItemStack> GLOW_LICHEN;

    public static ItemType<ItemSpawnEggStack> GLOW_SQUID_SPAWN_EGG;

    public static ItemType<ItemStack> GLOW_STICK;

    public static ItemType<ItemStack> GLOWINGOBSIDIAN;

    public static ItemType<ItemStack> GLOWSTONE;

    public static ItemType<ItemStack> GLOWSTONE_DUST;

    public static ItemType<ItemGoatHornStack> GOAT_HORN;

    public static ItemType<ItemSpawnEggStack> GOAT_SPAWN_EGG;

    public static ItemType<ItemStack> GOLD_BLOCK;

    public static ItemType<ItemStack> GOLD_INGOT;

    public static ItemType<ItemStack> GOLD_NUGGET;

    public static ItemType<ItemStack> GOLD_ORE;

    public static ItemType<ItemGoldenAppleStack> GOLDEN_APPLE;

    public static ItemType<ItemAxeStack> GOLDEN_AXE;

    public static ItemType<ItemBootsStack> GOLDEN_BOOTS;

    public static ItemType<ItemGoldenCarrotStack> GOLDEN_CARROT;

    public static ItemType<ItemChestplateStack> GOLDEN_CHESTPLATE;

    public static ItemType<ItemStack> GOLDEN_DANDELION;

    public static ItemType<ItemHelmetStack> GOLDEN_HELMET;

    public static ItemType<ItemHoeStack> GOLDEN_HOE;

    public static ItemType<ItemHorseArmorStack> GOLDEN_HORSE_ARMOR;

    public static ItemType<ItemLeggingsStack> GOLDEN_LEGGINGS;

    public static ItemType<ItemStack> GOLDEN_NAUTILUS_ARMOR;

    public static ItemType<ItemPickaxeStack> GOLDEN_PICKAXE;

    public static ItemType<ItemStack> GOLDEN_RAIL;

    public static ItemType<ItemShovelStack> GOLDEN_SHOVEL;

    public static ItemType<ItemSpearStack> GOLDEN_SPEAR;

    public static ItemType<ItemSwordStack> GOLDEN_SWORD;

    public static ItemType<ItemStack> GRANITE;

    public static ItemType<ItemStack> GRANITE_DOUBLE_SLAB;

    public static ItemType<ItemStack> GRANITE_SLAB;

    public static ItemType<ItemStack> GRANITE_STAIRS;

    public static ItemType<ItemStack> GRANITE_WALL;

    public static ItemType<ItemGrassBlockStack> GRASS_BLOCK;

    public static ItemType<ItemStack> GRASS_PATH;

    public static ItemType<ItemStack> GRAVEL;

    public static ItemType<ItemStack> GRAY_BUNDLE;

    public static ItemType<ItemCandleStack> GRAY_CANDLE;

    public static ItemType<ItemStack> GRAY_CANDLE_CAKE;

    public static ItemType<ItemStack> GRAY_CARPET;

    public static ItemType<ItemStack> GRAY_CONCRETE;

    public static ItemType<ItemStack> GRAY_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> GRAY_CONCRETE_POWDER;

    public static ItemType<ItemStack> GRAY_CONCRETE_SLAB;

    public static ItemType<ItemStack> GRAY_CONCRETE_STAIRS;

    public static ItemType<ItemStack> GRAY_CUSHION;

    public static ItemType<ItemDyeStack> GRAY_DYE;

    public static ItemType<ItemStack> GRAY_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> GRAY_HARNESS;

    public static ItemType<ItemShulkerBoxStack> GRAY_SHULKER_BOX;

    public static ItemType<ItemStack> GRAY_STAINED_GLASS;

    public static ItemType<ItemStack> GRAY_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> GRAY_TERRACOTTA;

    public static ItemType<ItemStack> GRAY_WOOL;

    public static ItemType<ItemStack> GRAY_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> GRAY_WOOL_SLAB;

    public static ItemType<ItemStack> GRAY_WOOL_STAIRS;

    public static ItemType<ItemStack> GREEN_BUNDLE;

    public static ItemType<ItemCandleStack> GREEN_CANDLE;

    public static ItemType<ItemStack> GREEN_CANDLE_CAKE;

    public static ItemType<ItemStack> GREEN_CARPET;

    public static ItemType<ItemStack> GREEN_CONCRETE;

    public static ItemType<ItemStack> GREEN_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> GREEN_CONCRETE_POWDER;

    public static ItemType<ItemStack> GREEN_CONCRETE_SLAB;

    public static ItemType<ItemStack> GREEN_CONCRETE_STAIRS;

    public static ItemType<ItemStack> GREEN_CUSHION;

    public static ItemType<ItemDyeStack> GREEN_DYE;

    public static ItemType<ItemStack> GREEN_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> GREEN_HARNESS;

    public static ItemType<ItemShulkerBoxStack> GREEN_SHULKER_BOX;

    public static ItemType<ItemStack> GREEN_STAINED_GLASS;

    public static ItemType<ItemStack> GREEN_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> GREEN_TERRACOTTA;

    public static ItemType<ItemStack> GREEN_WOOL;

    public static ItemType<ItemStack> GREEN_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> GREEN_WOOL_SLAB;

    public static ItemType<ItemStack> GREEN_WOOL_STAIRS;

    public static ItemType<ItemStack> GRINDSTONE;

    public static ItemType<ItemSpawnEggStack> GUARDIAN_SPAWN_EGG;

    public static ItemType<ItemGunpowderStack> GUNPOWDER;

    public static ItemType<ItemStack> GUSTER_BANNER_PATTERN;

    public static ItemType<ItemStack> GUSTER_POTTERY_SHERD;

    public static ItemType<ItemStack> HANGING_ROOTS;

    public static ItemType<ItemSpawnEggStack> HAPPY_GHAST_SPAWN_EGG;

    public static ItemType<ItemStack> HARD_BLACK_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_BLACK_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_BLUE_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_BLUE_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_BROWN_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_BROWN_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_CYAN_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_CYAN_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_GLASS;

    public static ItemType<ItemStack> HARD_GLASS_PANE;

    public static ItemType<ItemStack> HARD_GRAY_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_GRAY_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_GREEN_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_GREEN_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_LIGHT_BLUE_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_LIGHT_BLUE_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_LIGHT_GRAY_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_LIGHT_GRAY_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_LIME_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_LIME_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_MAGENTA_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_MAGENTA_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_ORANGE_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_ORANGE_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_PINK_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_PINK_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_PURPLE_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_PURPLE_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_RED_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_RED_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_WHITE_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_WHITE_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARD_YELLOW_STAINED_GLASS;

    public static ItemType<ItemStack> HARD_YELLOW_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> HARDENED_CLAY;

    public static ItemType<ItemStack> HAY_BLOCK;

    public static ItemType<ItemStack> HEART_OF_THE_SEA;

    public static ItemType<ItemStack> HEART_POTTERY_SHERD;

    public static ItemType<ItemStack> HEARTBREAK_POTTERY_SHERD;

    public static ItemType<ItemStack> HEAVY_CORE;

    public static ItemType<ItemStack> HEAVY_WEIGHTED_PRESSURE_PLATE;

    public static ItemType<ItemSpawnEggStack> HOGLIN_SPAWN_EGG;

    public static ItemType<ItemStack> HONEY_BLOCK;

    public static ItemType<ItemHoneyBottleStack> HONEY_BOTTLE;

    public static ItemType<ItemStack> HONEYCOMB;

    public static ItemType<ItemStack> HONEYCOMB_BLOCK;

    public static ItemType<ItemStack> HOPPER;

    public static ItemType<ItemStack> HOPPER_MINECART;

    public static ItemType<ItemStack> HORN_CORAL;

    public static ItemType<ItemStack> HORN_CORAL_BLOCK;

    public static ItemType<ItemStack> HORN_CORAL_FAN;

    public static ItemType<ItemStack> HORN_CORAL_WALL_FAN;

    public static ItemType<ItemSpawnEggStack> HORSE_SPAWN_EGG;

    public static ItemType<ItemStack> HOST_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> HOWL_POTTERY_SHERD;

    public static ItemType<ItemSpawnEggStack> HUSK_SPAWN_EGG;

    public static ItemType<ItemStack> ICE;

    public static ItemType<ItemStack> ICE_BOMB;

    public static ItemType<ItemStack> INFESTED_CHISELED_STONE_BRICKS;

    public static ItemType<ItemStack> INFESTED_COBBLESTONE;

    public static ItemType<ItemStack> INFESTED_CRACKED_STONE_BRICKS;

    public static ItemType<ItemStack> INFESTED_DEEPSLATE;

    public static ItemType<ItemStack> INFESTED_MOSSY_STONE_BRICKS;

    public static ItemType<ItemStack> INFESTED_STONE;

    public static ItemType<ItemStack> INFESTED_STONE_BRICKS;

    public static ItemType<ItemStack> INFO_UPDATE;

    public static ItemType<ItemStack> INFO_UPDATE2;

    public static ItemType<ItemStack> INK_SAC;

    public static ItemType<ItemStack> INVISIBLE_BEDROCK;

    public static ItemType<ItemAxeStack> IRON_AXE;

    public static ItemType<ItemStack> IRON_BARS;

    public static ItemType<ItemStack> IRON_BLOCK;

    public static ItemType<ItemBootsStack> IRON_BOOTS;

    public static ItemType<ItemStack> IRON_CHAIN;

    public static ItemType<ItemChestplateStack> IRON_CHESTPLATE;

    public static ItemType<ItemStack> IRON_DOOR;

    public static ItemType<ItemSpawnEggStack> IRON_GOLEM_SPAWN_EGG;

    public static ItemType<ItemHelmetStack> IRON_HELMET;

    public static ItemType<ItemHoeStack> IRON_HOE;

    public static ItemType<ItemHorseArmorStack> IRON_HORSE_ARMOR;

    public static ItemType<ItemStack> IRON_INGOT;

    public static ItemType<ItemLeggingsStack> IRON_LEGGINGS;

    public static ItemType<ItemStack> IRON_NAUTILUS_ARMOR;

    public static ItemType<ItemStack> IRON_NUGGET;

    public static ItemType<ItemStack> IRON_ORE;

    public static ItemType<ItemPickaxeStack> IRON_PICKAXE;

    public static ItemType<ItemShovelStack> IRON_SHOVEL;

    public static ItemType<ItemSpearStack> IRON_SPEAR;

    public static ItemType<ItemSwordStack> IRON_SWORD;

    public static ItemType<ItemStack> IRON_TRAPDOOR;

    public static ItemType<ItemStack> ITEM_BED;

    public static ItemType<ItemStack> ITEM_BEETROOT;

    public static ItemType<ItemStack> ITEM_BREWING_STAND;

    public static ItemType<ItemStack> ITEM_CAKE;

    public static ItemType<ItemStack> ITEM_CAMERA;

    public static ItemType<ItemStack> ITEM_CAMPFIRE;

    public static ItemType<ItemStack> ITEM_CAULDRON;

    public static ItemType<ItemStack> ITEM_FLOWER_POT;

    public static ItemType<ItemStack> ITEM_FRAME;

    public static ItemType<ItemStack> ITEM_GLOW_FRAME;

    public static ItemType<ItemStack> ITEM_HOPPER;

    public static ItemType<ItemStack> ITEM_KELP;

    public static ItemType<ItemStack> ITEM_NETHER_SPROUTS;

    public static ItemType<ItemStack> ITEM_NETHER_WART;

    public static ItemType<ItemStack> ITEM_REEDS;

    public static ItemType<ItemStack> ITEM_SOUL_CAMPFIRE;

    public static ItemType<ItemStack> ITEM_STRAW_BED;

    public static ItemType<ItemStack> ITEM_WHEAT;

    public static ItemType<ItemStack> JIGSAW;

    public static ItemType<ItemStack> JUKEBOX;

    public static ItemType<ItemStack> JUNGLE_BOAT;

    public static ItemType<ItemStack> JUNGLE_BUTTON;

    public static ItemType<ItemStack> JUNGLE_CHEST_BOAT;

    public static ItemType<ItemStack> JUNGLE_DOOR;

    public static ItemType<ItemStack> JUNGLE_DOUBLE_SLAB;

    public static ItemType<ItemStack> JUNGLE_FENCE;

    public static ItemType<ItemStack> JUNGLE_FENCE_GATE;

    public static ItemType<ItemStack> JUNGLE_HANGING_SIGN;

    public static ItemType<ItemStack> JUNGLE_LEAVES;

    public static ItemType<ItemStack> JUNGLE_LOG;

    public static ItemType<ItemStack> JUNGLE_PLANKS;

    public static ItemType<ItemStack> JUNGLE_PRESSURE_PLATE;

    public static ItemType<ItemStack> JUNGLE_SAPLING;

    public static ItemType<ItemStack> JUNGLE_SHELF;

    public static ItemType<ItemSignStack> JUNGLE_SIGN;

    public static ItemType<ItemStack> JUNGLE_SLAB;

    public static ItemType<ItemStack> JUNGLE_STAIRS;

    public static ItemType<ItemStack> JUNGLE_STANDING_SIGN;

    public static ItemType<ItemStack> JUNGLE_TRAPDOOR;

    public static ItemType<ItemStack> JUNGLE_WALL_SIGN;

    public static ItemType<ItemStack> JUNGLE_WOOD;

    public static ItemType<ItemStack> KELP;

    public static ItemType<ItemStack> LAB_TABLE;

    public static ItemType<ItemStack> LADDER;

    public static ItemType<ItemStack> LANTERN;

    public static ItemType<ItemStack> LAPIS_BLOCK;

    public static ItemType<ItemStack> LAPIS_LAZULI;

    public static ItemType<ItemStack> LAPIS_ORE;

    public static ItemType<ItemStack> LARGE_AMETHYST_BUD;

    public static ItemType<ItemStack> LARGE_FERN;

    public static ItemType<ItemStack> LAVA;

    public static ItemType<ItemBucketStack> LAVA_BUCKET;

    public static ItemType<ItemStack> LEAD;

    public static ItemType<ItemStack> LEAF_LITTER;

    public static ItemType<ItemStack> LEATHER;

    public static ItemType<ItemLeatherBootsStack> LEATHER_BOOTS;

    public static ItemType<ItemLeatherChestplateStack> LEATHER_CHESTPLATE;

    public static ItemType<ItemLeatherHelmetStack> LEATHER_HELMET;

    public static ItemType<ItemLeatherHorseArmorStack> LEATHER_HORSE_ARMOR;

    public static ItemType<ItemLeatherLeggingsStack> LEATHER_LEGGINGS;

    public static ItemType<ItemStack> LEAVES;

    public static ItemType<ItemStack> LEAVES2;

    public static ItemType<ItemStack> LECTERN;

    public static ItemType<ItemStack> LEVER;

    public static ItemType<ItemStack> LIGHT_BLOCK;

    public static ItemType<ItemStack> LIGHT_BLOCK_0;

    public static ItemType<ItemStack> LIGHT_BLOCK_1;

    public static ItemType<ItemStack> LIGHT_BLOCK_10;

    public static ItemType<ItemStack> LIGHT_BLOCK_11;

    public static ItemType<ItemStack> LIGHT_BLOCK_12;

    public static ItemType<ItemStack> LIGHT_BLOCK_13;

    public static ItemType<ItemStack> LIGHT_BLOCK_14;

    public static ItemType<ItemStack> LIGHT_BLOCK_15;

    public static ItemType<ItemStack> LIGHT_BLOCK_2;

    public static ItemType<ItemStack> LIGHT_BLOCK_3;

    public static ItemType<ItemStack> LIGHT_BLOCK_4;

    public static ItemType<ItemStack> LIGHT_BLOCK_5;

    public static ItemType<ItemStack> LIGHT_BLOCK_6;

    public static ItemType<ItemStack> LIGHT_BLOCK_7;

    public static ItemType<ItemStack> LIGHT_BLOCK_8;

    public static ItemType<ItemStack> LIGHT_BLOCK_9;

    public static ItemType<ItemStack> LIGHT_BLUE_BUNDLE;

    public static ItemType<ItemCandleStack> LIGHT_BLUE_CANDLE;

    public static ItemType<ItemStack> LIGHT_BLUE_CANDLE_CAKE;

    public static ItemType<ItemStack> LIGHT_BLUE_CARPET;

    public static ItemType<ItemStack> LIGHT_BLUE_CONCRETE;

    public static ItemType<ItemStack> LIGHT_BLUE_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> LIGHT_BLUE_CONCRETE_POWDER;

    public static ItemType<ItemStack> LIGHT_BLUE_CONCRETE_SLAB;

    public static ItemType<ItemStack> LIGHT_BLUE_CONCRETE_STAIRS;

    public static ItemType<ItemStack> LIGHT_BLUE_CUSHION;

    public static ItemType<ItemDyeStack> LIGHT_BLUE_DYE;

    public static ItemType<ItemStack> LIGHT_BLUE_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> LIGHT_BLUE_HARNESS;

    public static ItemType<ItemShulkerBoxStack> LIGHT_BLUE_SHULKER_BOX;

    public static ItemType<ItemStack> LIGHT_BLUE_STAINED_GLASS;

    public static ItemType<ItemStack> LIGHT_BLUE_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> LIGHT_BLUE_TERRACOTTA;

    public static ItemType<ItemStack> LIGHT_BLUE_WOOL;

    public static ItemType<ItemStack> LIGHT_BLUE_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> LIGHT_BLUE_WOOL_SLAB;

    public static ItemType<ItemStack> LIGHT_BLUE_WOOL_STAIRS;

    public static ItemType<ItemStack> LIGHT_GRAY_BUNDLE;

    public static ItemType<ItemCandleStack> LIGHT_GRAY_CANDLE;

    public static ItemType<ItemStack> LIGHT_GRAY_CANDLE_CAKE;

    public static ItemType<ItemStack> LIGHT_GRAY_CARPET;

    public static ItemType<ItemStack> LIGHT_GRAY_CONCRETE;

    public static ItemType<ItemStack> LIGHT_GRAY_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> LIGHT_GRAY_CONCRETE_POWDER;

    public static ItemType<ItemStack> LIGHT_GRAY_CONCRETE_SLAB;

    public static ItemType<ItemStack> LIGHT_GRAY_CONCRETE_STAIRS;

    public static ItemType<ItemStack> LIGHT_GRAY_CUSHION;

    public static ItemType<ItemDyeStack> LIGHT_GRAY_DYE;

    public static ItemType<ItemStack> LIGHT_GRAY_HARNESS;

    public static ItemType<ItemShulkerBoxStack> LIGHT_GRAY_SHULKER_BOX;

    public static ItemType<ItemStack> LIGHT_GRAY_STAINED_GLASS;

    public static ItemType<ItemStack> LIGHT_GRAY_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> LIGHT_GRAY_TERRACOTTA;

    public static ItemType<ItemStack> LIGHT_GRAY_WOOL;

    public static ItemType<ItemStack> LIGHT_GRAY_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> LIGHT_GRAY_WOOL_SLAB;

    public static ItemType<ItemStack> LIGHT_GRAY_WOOL_STAIRS;

    public static ItemType<ItemStack> LIGHT_WEIGHTED_PRESSURE_PLATE;

    public static ItemType<ItemStack> LIGHTNING_ROD;

    public static ItemType<ItemStack> LILAC;

    public static ItemType<ItemStack> LILY_OF_THE_VALLEY;

    public static ItemType<ItemStack> LIME_BUNDLE;

    public static ItemType<ItemCandleStack> LIME_CANDLE;

    public static ItemType<ItemStack> LIME_CANDLE_CAKE;

    public static ItemType<ItemStack> LIME_CARPET;

    public static ItemType<ItemStack> LIME_CONCRETE;

    public static ItemType<ItemStack> LIME_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> LIME_CONCRETE_POWDER;

    public static ItemType<ItemStack> LIME_CONCRETE_SLAB;

    public static ItemType<ItemStack> LIME_CONCRETE_STAIRS;

    public static ItemType<ItemStack> LIME_CUSHION;

    public static ItemType<ItemDyeStack> LIME_DYE;

    public static ItemType<ItemStack> LIME_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> LIME_HARNESS;

    public static ItemType<ItemShulkerBoxStack> LIME_SHULKER_BOX;

    public static ItemType<ItemStack> LIME_STAINED_GLASS;

    public static ItemType<ItemStack> LIME_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> LIME_TERRACOTTA;

    public static ItemType<ItemStack> LIME_WOOL;

    public static ItemType<ItemStack> LIME_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> LIME_WOOL_SLAB;

    public static ItemType<ItemStack> LIME_WOOL_STAIRS;

    public static ItemType<ItemLingeringPotionStack> LINGERING_POTION;

    public static ItemType<ItemStack> LIT_BLAST_FURNACE;

    public static ItemType<ItemStack> LIT_DEEPSLATE_REDSTONE_ORE;

    public static ItemType<ItemStack> LIT_FURNACE;

    public static ItemType<ItemStack> LIT_PUMPKIN;

    public static ItemType<ItemStack> LIT_REDSTONE_LAMP;

    public static ItemType<ItemStack> LIT_REDSTONE_ORE;

    public static ItemType<ItemStack> LIT_SMOKER;

    public static ItemType<ItemSpawnEggStack> LLAMA_SPAWN_EGG;

    public static ItemType<ItemStack> LODESTONE;

    public static ItemType<ItemStack> LODESTONE_COMPASS;

    public static ItemType<ItemStack> LOG;

    public static ItemType<ItemStack> LOG2;

    public static ItemType<ItemStack> LOOM;

    public static ItemType<ItemMaceStack> MACE;

    public static ItemType<ItemStack> MAGENTA_BUNDLE;

    public static ItemType<ItemCandleStack> MAGENTA_CANDLE;

    public static ItemType<ItemStack> MAGENTA_CANDLE_CAKE;

    public static ItemType<ItemStack> MAGENTA_CARPET;

    public static ItemType<ItemStack> MAGENTA_CONCRETE;

    public static ItemType<ItemStack> MAGENTA_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> MAGENTA_CONCRETE_POWDER;

    public static ItemType<ItemStack> MAGENTA_CONCRETE_SLAB;

    public static ItemType<ItemStack> MAGENTA_CONCRETE_STAIRS;

    public static ItemType<ItemStack> MAGENTA_CUSHION;

    public static ItemType<ItemDyeStack> MAGENTA_DYE;

    public static ItemType<ItemStack> MAGENTA_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> MAGENTA_HARNESS;

    public static ItemType<ItemShulkerBoxStack> MAGENTA_SHULKER_BOX;

    public static ItemType<ItemStack> MAGENTA_STAINED_GLASS;

    public static ItemType<ItemStack> MAGENTA_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> MAGENTA_TERRACOTTA;

    public static ItemType<ItemStack> MAGENTA_WOOL;

    public static ItemType<ItemStack> MAGENTA_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> MAGENTA_WOOL_SLAB;

    public static ItemType<ItemStack> MAGENTA_WOOL_STAIRS;

    public static ItemType<ItemStack> MAGMA;

    public static ItemType<ItemStack> MAGMA_CREAM;

    public static ItemType<ItemSpawnEggStack> MAGMA_CUBE_SPAWN_EGG;

    public static ItemType<ItemStack> MANGROVE_BOAT;

    public static ItemType<ItemStack> MANGROVE_BUTTON;

    public static ItemType<ItemStack> MANGROVE_CHEST_BOAT;

    public static ItemType<ItemStack> MANGROVE_DOOR;

    public static ItemType<ItemStack> MANGROVE_DOUBLE_SLAB;

    public static ItemType<ItemStack> MANGROVE_FENCE;

    public static ItemType<ItemStack> MANGROVE_FENCE_GATE;

    public static ItemType<ItemStack> MANGROVE_HANGING_SIGN;

    public static ItemType<ItemStack> MANGROVE_LEAVES;

    public static ItemType<ItemStack> MANGROVE_LOG;

    public static ItemType<ItemStack> MANGROVE_PLANKS;

    public static ItemType<ItemStack> MANGROVE_PRESSURE_PLATE;

    public static ItemType<ItemStack> MANGROVE_PROPAGULE;

    public static ItemType<ItemStack> MANGROVE_ROOTS;

    public static ItemType<ItemStack> MANGROVE_SHELF;

    public static ItemType<ItemSignStack> MANGROVE_SIGN;

    public static ItemType<ItemStack> MANGROVE_SLAB;

    public static ItemType<ItemStack> MANGROVE_STAIRS;

    public static ItemType<ItemStack> MANGROVE_STANDING_SIGN;

    public static ItemType<ItemStack> MANGROVE_TRAPDOOR;

    public static ItemType<ItemStack> MANGROVE_WALL_SIGN;

    public static ItemType<ItemStack> MANGROVE_WOOD;

    public static ItemType<ItemStack> MATERIAL_REDUCER;

    public static ItemType<ItemStack> MEDICINE;

    public static ItemType<ItemStack> MEDIUM_AMETHYST_BUD;

    public static ItemType<ItemStack> MELON_BLOCK;

    public static ItemType<ItemStack> MELON_SEEDS;

    public static ItemType<ItemMelonSliceStack> MELON_SLICE;

    public static ItemType<ItemStack> MELON_STEM;

    public static ItemType<ItemMilkBucketStack> MILK_BUCKET;

    public static ItemType<ItemStack> MINECART;

    public static ItemType<ItemStack> MINER_POTTERY_SHERD;

    public static ItemType<ItemStack> MOB_SPAWNER;

    public static ItemType<ItemStack> MOJANG_BANNER_PATTERN;

    public static ItemType<ItemStack> MONSTER_EGG;

    public static ItemType<ItemSpawnEggStack> MOOSHROOM_SPAWN_EGG;

    public static ItemType<ItemStack> MOSS_BLOCK;

    public static ItemType<ItemStack> MOSS_CARPET;

    public static ItemType<ItemStack> MOSSY_COBBLESTONE;

    public static ItemType<ItemStack> MOSSY_COBBLESTONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> MOSSY_COBBLESTONE_SLAB;

    public static ItemType<ItemStack> MOSSY_COBBLESTONE_STAIRS;

    public static ItemType<ItemStack> MOSSY_COBBLESTONE_WALL;

    public static ItemType<ItemStack> MOSSY_STONE_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> MOSSY_STONE_BRICK_SLAB;

    public static ItemType<ItemStack> MOSSY_STONE_BRICK_STAIRS;

    public static ItemType<ItemStack> MOSSY_STONE_BRICK_WALL;

    public static ItemType<ItemStack> MOSSY_STONE_BRICKS;

    public static ItemType<ItemStack> MOURNER_POTTERY_SHERD;

    public static ItemType<ItemStack> MOVING_BLOCK;

    public static ItemType<ItemStack> MUD;

    public static ItemType<ItemStack> MUD_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> MUD_BRICK_SLAB;

    public static ItemType<ItemStack> MUD_BRICK_STAIRS;

    public static ItemType<ItemStack> MUD_BRICK_WALL;

    public static ItemType<ItemStack> MUD_BRICKS;

    public static ItemType<ItemStack> MUDDY_MANGROVE_ROOTS;

    public static ItemType<ItemSpawnEggStack> MULE_SPAWN_EGG;

    public static ItemType<ItemStack> MUSHROOM_STEM;

    public static ItemType<ItemMushroomStewStack> MUSHROOM_STEW;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_11;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_13;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_5;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_BLOCKS;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_BOUNCE;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_CAT;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_CHIRP;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_CREATOR;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_CREATOR_MUSIC_BOX;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_FAR;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_LAVA_CHICKEN;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_MALL;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_MELLOHI;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_OTHERSIDE;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_PIGSTEP;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_PRECIPICE;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_RELIC;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_STAL;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_STRAD;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_TEARS;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_WAIT;

    public static ItemType<ItemMusicDiscStack> MUSIC_DISC_WARD;

    public static ItemType<ItemMuttonStack> MUTTON;

    public static ItemType<ItemStack> MYCELIUM;

    public static ItemType<ItemStack> NAME_TAG;

    public static ItemType<ItemStack> NAUTILUS_SHELL;

    public static ItemType<ItemSpawnEggStack> NAUTILUS_SPAWN_EGG;

    public static ItemType<ItemStack> NETHER_BRICK;

    public static ItemType<ItemStack> NETHER_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> NETHER_BRICK_FENCE;

    public static ItemType<ItemStack> NETHER_BRICK_SLAB;

    public static ItemType<ItemStack> NETHER_BRICK_STAIRS;

    public static ItemType<ItemStack> NETHER_BRICK_WALL;

    public static ItemType<ItemStack> NETHER_GOLD_ORE;

    public static ItemType<ItemStack> NETHER_SPROUTS;

    public static ItemType<ItemStack> NETHER_STAR;

    public static ItemType<ItemStack> NETHER_WART;

    public static ItemType<ItemStack> NETHER_WART_BLOCK;

    public static ItemType<ItemStack> NETHERBRICK;

    public static ItemType<ItemAxeStack> NETHERITE_AXE;

    public static ItemType<ItemStack> NETHERITE_BLOCK;

    public static ItemType<ItemBootsStack> NETHERITE_BOOTS;

    public static ItemType<ItemChestplateStack> NETHERITE_CHESTPLATE;

    public static ItemType<ItemHelmetStack> NETHERITE_HELMET;

    public static ItemType<ItemHoeStack> NETHERITE_HOE;

    public static ItemType<ItemHorseArmorStack> NETHERITE_HORSE_ARMOR;

    public static ItemType<ItemStack> NETHERITE_INGOT;

    public static ItemType<ItemLeggingsStack> NETHERITE_LEGGINGS;

    public static ItemType<ItemStack> NETHERITE_NAUTILUS_ARMOR;

    public static ItemType<ItemPickaxeStack> NETHERITE_PICKAXE;

    public static ItemType<ItemStack> NETHERITE_SCRAP;

    public static ItemType<ItemShovelStack> NETHERITE_SHOVEL;

    public static ItemType<ItemSpearStack> NETHERITE_SPEAR;

    public static ItemType<ItemSwordStack> NETHERITE_SWORD;

    public static ItemType<ItemStack> NETHERITE_UPGRADE_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> NETHERRACK;

    public static ItemType<ItemStack> NETHERREACTOR;

    public static ItemType<ItemStack> NORMAL_STONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> NORMAL_STONE_SLAB;

    public static ItemType<ItemStack> NORMAL_STONE_STAIRS;

    public static ItemType<ItemStack> NOTEBLOCK;

    public static ItemType<ItemSpawnEggStack> NPC_SPAWN_EGG;

    public static ItemType<ItemStack> OAK_BOAT;

    public static ItemType<ItemStack> OAK_CHEST_BOAT;

    public static ItemType<ItemStack> OAK_DOUBLE_SLAB;

    public static ItemType<ItemStack> OAK_FENCE;

    public static ItemType<ItemStack> OAK_HANGING_SIGN;

    public static ItemType<ItemStack> OAK_LEAVES;

    public static ItemType<ItemStack> OAK_LOG;

    public static ItemType<ItemStack> OAK_PLANKS;

    public static ItemType<ItemStack> OAK_SAPLING;

    public static ItemType<ItemStack> OAK_SHELF;

    public static ItemType<ItemSignStack> OAK_SIGN;

    public static ItemType<ItemStack> OAK_SLAB;

    public static ItemType<ItemStack> OAK_STAIRS;

    public static ItemType<ItemStack> OAK_WOOD;

    public static ItemType<ItemStack> OBSERVER;

    public static ItemType<ItemStack> OBSIDIAN;

    public static ItemType<ItemSpawnEggStack> OCELOT_SPAWN_EGG;

    public static ItemType<ItemStack> OCHRE_FROGLIGHT;

    public static ItemType<ItemStack> OMINOUS_BOTTLE;

    public static ItemType<ItemStack> OMINOUS_TRIAL_KEY;

    public static ItemType<ItemStack> OPEN_EYEBLOSSOM;

    public static ItemType<ItemStack> ORANGE_BUNDLE;

    public static ItemType<ItemCandleStack> ORANGE_CANDLE;

    public static ItemType<ItemStack> ORANGE_CANDLE_CAKE;

    public static ItemType<ItemStack> ORANGE_CARPET;

    public static ItemType<ItemStack> ORANGE_CONCRETE;

    public static ItemType<ItemStack> ORANGE_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> ORANGE_CONCRETE_POWDER;

    public static ItemType<ItemStack> ORANGE_CONCRETE_SLAB;

    public static ItemType<ItemStack> ORANGE_CONCRETE_STAIRS;

    public static ItemType<ItemStack> ORANGE_CUSHION;

    public static ItemType<ItemDyeStack> ORANGE_DYE;

    public static ItemType<ItemStack> ORANGE_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> ORANGE_HARNESS;

    public static ItemType<ItemStack> ORANGE_POPLAR_LEAVES;

    public static ItemType<ItemShulkerBoxStack> ORANGE_SHULKER_BOX;

    public static ItemType<ItemStack> ORANGE_STAINED_GLASS;

    public static ItemType<ItemStack> ORANGE_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> ORANGE_TERRACOTTA;

    public static ItemType<ItemStack> ORANGE_TULIP;

    public static ItemType<ItemStack> ORANGE_WOOL;

    public static ItemType<ItemStack> ORANGE_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> ORANGE_WOOL_SLAB;

    public static ItemType<ItemStack> ORANGE_WOOL_STAIRS;

    public static ItemType<ItemStack> OXEYE_DAISY;

    public static ItemType<ItemStack> OXIDIZED_CHISELED_COPPER;

    public static ItemType<ItemStack> OXIDIZED_COPPER;

    public static ItemType<ItemStack> OXIDIZED_COPPER_BARS;

    public static ItemType<ItemStack> OXIDIZED_COPPER_BULB;

    public static ItemType<ItemStack> OXIDIZED_COPPER_CHAIN;

    public static ItemType<ItemStack> OXIDIZED_COPPER_CHEST;

    public static ItemType<ItemStack> OXIDIZED_COPPER_DOOR;

    public static ItemType<ItemStack> OXIDIZED_COPPER_GOLEM_STATUE;

    public static ItemType<ItemStack> OXIDIZED_COPPER_GRATE;

    public static ItemType<ItemStack> OXIDIZED_COPPER_LANTERN;

    public static ItemType<ItemStack> OXIDIZED_COPPER_TRAPDOOR;

    public static ItemType<ItemStack> OXIDIZED_CUT_COPPER;

    public static ItemType<ItemStack> OXIDIZED_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> OXIDIZED_CUT_COPPER_STAIRS;

    public static ItemType<ItemStack> OXIDIZED_DOUBLE_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> OXIDIZED_LIGHTNING_ROD;

    public static ItemType<ItemStack> PACKED_ICE;

    public static ItemType<ItemStack> PACKED_MUD;

    public static ItemType<ItemStack> PAINTING;

    public static ItemType<ItemStack> PALE_HANGING_MOSS;

    public static ItemType<ItemStack> PALE_MOSS_BLOCK;

    public static ItemType<ItemStack> PALE_MOSS_CARPET;

    public static ItemType<ItemStack> PALE_OAK_BOAT;

    public static ItemType<ItemStack> PALE_OAK_BUTTON;

    public static ItemType<ItemStack> PALE_OAK_CHEST_BOAT;

    public static ItemType<ItemStack> PALE_OAK_DOOR;

    public static ItemType<ItemStack> PALE_OAK_DOUBLE_SLAB;

    public static ItemType<ItemStack> PALE_OAK_FENCE;

    public static ItemType<ItemStack> PALE_OAK_FENCE_GATE;

    public static ItemType<ItemStack> PALE_OAK_HANGING_SIGN;

    public static ItemType<ItemStack> PALE_OAK_LEAVES;

    public static ItemType<ItemStack> PALE_OAK_LOG;

    public static ItemType<ItemStack> PALE_OAK_PLANKS;

    public static ItemType<ItemStack> PALE_OAK_PRESSURE_PLATE;

    public static ItemType<ItemStack> PALE_OAK_SAPLING;

    public static ItemType<ItemStack> PALE_OAK_SHELF;

    public static ItemType<ItemSignStack> PALE_OAK_SIGN;

    public static ItemType<ItemStack> PALE_OAK_SLAB;

    public static ItemType<ItemStack> PALE_OAK_STAIRS;

    public static ItemType<ItemStack> PALE_OAK_STANDING_SIGN;

    public static ItemType<ItemStack> PALE_OAK_TRAPDOOR;

    public static ItemType<ItemStack> PALE_OAK_WALL_SIGN;

    public static ItemType<ItemStack> PALE_OAK_WOOD;

    public static ItemType<ItemSpawnEggStack> PANDA_SPAWN_EGG;

    public static ItemType<ItemPaperStack> PAPER;

    public static ItemType<ItemSpawnEggStack> PARCHED_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> PARROT_SPAWN_EGG;

    public static ItemType<ItemStack> PEARLESCENT_FROGLIGHT;

    public static ItemType<ItemStack> PEONY;

    public static ItemType<ItemStack> PETRIFIED_OAK_DOUBLE_SLAB;

    public static ItemType<ItemStack> PETRIFIED_OAK_SLAB;

    public static ItemType<ItemStack> PHANTOM_MEMBRANE;

    public static ItemType<ItemSpawnEggStack> PHANTOM_SPAWN_EGG;

    public static ItemType<ItemStack> PHOTO_ITEM;

    public static ItemType<ItemSpawnEggStack> PIG_SPAWN_EGG;

    public static ItemType<ItemStack> PIGLIN_BANNER_PATTERN;

    public static ItemType<ItemSpawnEggStack> PIGLIN_BRUTE_SPAWN_EGG;

    public static ItemType<ItemHeadStack> PIGLIN_HEAD;

    public static ItemType<ItemSpawnEggStack> PIGLIN_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> PILLAGER_SPAWN_EGG;

    public static ItemType<ItemStack> PINK_BUNDLE;

    public static ItemType<ItemCandleStack> PINK_CANDLE;

    public static ItemType<ItemStack> PINK_CANDLE_CAKE;

    public static ItemType<ItemStack> PINK_CARPET;

    public static ItemType<ItemStack> PINK_CONCRETE;

    public static ItemType<ItemStack> PINK_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> PINK_CONCRETE_POWDER;

    public static ItemType<ItemStack> PINK_CONCRETE_SLAB;

    public static ItemType<ItemStack> PINK_CONCRETE_STAIRS;

    public static ItemType<ItemStack> PINK_CUSHION;

    public static ItemType<ItemDyeStack> PINK_DYE;

    public static ItemType<ItemStack> PINK_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> PINK_HARNESS;

    public static ItemType<ItemStack> PINK_PETALS;

    public static ItemType<ItemShulkerBoxStack> PINK_SHULKER_BOX;

    public static ItemType<ItemStack> PINK_STAINED_GLASS;

    public static ItemType<ItemStack> PINK_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> PINK_TERRACOTTA;

    public static ItemType<ItemStack> PINK_TULIP;

    public static ItemType<ItemStack> PINK_WOOL;

    public static ItemType<ItemStack> PINK_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> PINK_WOOL_SLAB;

    public static ItemType<ItemStack> PINK_WOOL_STAIRS;

    public static ItemType<ItemStack> PISTON;

    public static ItemType<ItemStack> PISTON_ARM_COLLISION;

    public static ItemType<ItemStack> PITCHER_CROP;

    public static ItemType<ItemStack> PITCHER_PLANT;

    public static ItemType<ItemStack> PITCHER_POD;

    public static ItemType<ItemStack> PLANKS;

    public static ItemType<ItemHeadStack> PLAYER_HEAD;

    public static ItemType<ItemStack> PLENTY_POTTERY_SHERD;

    public static ItemType<ItemStack> PODZOL;

    public static ItemType<ItemStack> POINTED_DRIPSTONE;

    public static ItemType<ItemPoisonousPotatoStack> POISONOUS_POTATO;

    public static ItemType<ItemSpawnEggStack> POLAR_BEAR_SPAWN_EGG;

    public static ItemType<ItemStack> POLISHED_ANDESITE;

    public static ItemType<ItemStack> POLISHED_ANDESITE_DOUBLE_SLAB;

    public static ItemType<ItemStack> POLISHED_ANDESITE_SLAB;

    public static ItemType<ItemStack> POLISHED_ANDESITE_STAIRS;

    public static ItemType<ItemStack> POLISHED_BASALT;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_BRICK_SLAB;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_BRICK_STAIRS;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_BRICK_WALL;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_BRICKS;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_BUTTON;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_PRESSURE_PLATE;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_SLAB;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_STAIRS;

    public static ItemType<ItemStack> POLISHED_BLACKSTONE_WALL;

    public static ItemType<ItemStack> POLISHED_CINNABAR;

    public static ItemType<ItemStack> POLISHED_CINNABAR_DOUBLE_SLAB;

    public static ItemType<ItemStack> POLISHED_CINNABAR_SLAB;

    public static ItemType<ItemStack> POLISHED_CINNABAR_STAIRS;

    public static ItemType<ItemStack> POLISHED_CINNABAR_WALL;

    public static ItemType<ItemStack> POLISHED_DEEPSLATE;

    public static ItemType<ItemStack> POLISHED_DEEPSLATE_DOUBLE_SLAB;

    public static ItemType<ItemStack> POLISHED_DEEPSLATE_SLAB;

    public static ItemType<ItemStack> POLISHED_DEEPSLATE_STAIRS;

    public static ItemType<ItemStack> POLISHED_DEEPSLATE_WALL;

    public static ItemType<ItemStack> POLISHED_DIORITE;

    public static ItemType<ItemStack> POLISHED_DIORITE_DOUBLE_SLAB;

    public static ItemType<ItemStack> POLISHED_DIORITE_SLAB;

    public static ItemType<ItemStack> POLISHED_DIORITE_STAIRS;

    public static ItemType<ItemStack> POLISHED_GRANITE;

    public static ItemType<ItemStack> POLISHED_GRANITE_DOUBLE_SLAB;

    public static ItemType<ItemStack> POLISHED_GRANITE_SLAB;

    public static ItemType<ItemStack> POLISHED_GRANITE_STAIRS;

    public static ItemType<ItemStack> POLISHED_SULFUR;

    public static ItemType<ItemStack> POLISHED_SULFUR_DOUBLE_SLAB;

    public static ItemType<ItemStack> POLISHED_SULFUR_SLAB;

    public static ItemType<ItemStack> POLISHED_SULFUR_STAIRS;

    public static ItemType<ItemStack> POLISHED_SULFUR_WALL;

    public static ItemType<ItemStack> POLISHED_TUFF;

    public static ItemType<ItemStack> POLISHED_TUFF_DOUBLE_SLAB;

    public static ItemType<ItemStack> POLISHED_TUFF_SLAB;

    public static ItemType<ItemStack> POLISHED_TUFF_STAIRS;

    public static ItemType<ItemStack> POLISHED_TUFF_WALL;

    public static ItemType<ItemStack> POPLAR_BOAT;

    public static ItemType<ItemStack> POPLAR_BUTTON;

    public static ItemType<ItemStack> POPLAR_CHEST_BOAT;

    public static ItemType<ItemStack> POPLAR_DOOR;

    public static ItemType<ItemStack> POPLAR_DOUBLE_SLAB;

    public static ItemType<ItemStack> POPLAR_FENCE;

    public static ItemType<ItemStack> POPLAR_FENCE_GATE;

    public static ItemType<ItemStack> POPLAR_HANGING_SIGN;

    public static ItemType<ItemStack> POPLAR_LOG;

    public static ItemType<ItemStack> POPLAR_PLANKS;

    public static ItemType<ItemStack> POPLAR_PRESSURE_PLATE;

    public static ItemType<ItemStack> POPLAR_SAPLING;

    public static ItemType<ItemStack> POPLAR_SHELF;

    public static ItemType<ItemSignStack> POPLAR_SIGN;

    public static ItemType<ItemStack> POPLAR_SLAB;

    public static ItemType<ItemStack> POPLAR_STAIRS;

    public static ItemType<ItemStack> POPLAR_STANDING_SIGN;

    public static ItemType<ItemStack> POPLAR_TRAPDOOR;

    public static ItemType<ItemStack> POPLAR_WALL_SIGN;

    public static ItemType<ItemStack> POPLAR_WOOD;

    public static ItemType<ItemStack> POPPED_CHORUS_FRUIT;

    public static ItemType<ItemStack> POPPY;

    public static ItemType<ItemPorkchopStack> PORKCHOP;

    public static ItemType<ItemStack> PORTAL;

    public static ItemType<ItemStack> PORTFOLIO;

    public static ItemType<ItemPotatoStack> POTATO;

    public static ItemType<ItemStack> POTATOES;

    public static ItemType<ItemStack> POTENT_SULFUR;

    public static ItemType<ItemPotionStack> POTION;

    public static ItemType<ItemStack> POWDER_SNOW;

    public static ItemType<ItemBucketStack> POWDER_SNOW_BUCKET;

    public static ItemType<ItemStack> POWERED_COMPARATOR;

    public static ItemType<ItemStack> POWERED_REPEATER;

    public static ItemType<ItemStack> PRISMARINE;

    public static ItemType<ItemStack> PRISMARINE_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> PRISMARINE_BRICK_SLAB;

    public static ItemType<ItemStack> PRISMARINE_BRICKS;

    public static ItemType<ItemStack> PRISMARINE_BRICKS_STAIRS;

    public static ItemType<ItemStack> PRISMARINE_CRYSTALS;

    public static ItemType<ItemStack> PRISMARINE_DOUBLE_SLAB;

    public static ItemType<ItemStack> PRISMARINE_SHARD;

    public static ItemType<ItemStack> PRISMARINE_SLAB;

    public static ItemType<ItemStack> PRISMARINE_STAIRS;

    public static ItemType<ItemStack> PRISMARINE_WALL;

    public static ItemType<ItemStack> PRIZE_POTTERY_SHERD;

    public static ItemType<ItemPufferfishStack> PUFFERFISH;

    public static ItemType<ItemBucketStack> PUFFERFISH_BUCKET;

    public static ItemType<ItemSpawnEggStack> PUFFERFISH_SPAWN_EGG;

    public static ItemType<ItemStack> PUMPKIN;

    public static ItemType<ItemPumpkinPieStack> PUMPKIN_PIE;

    public static ItemType<ItemStack> PUMPKIN_SEEDS;

    public static ItemType<ItemStack> PUMPKIN_STEM;

    public static ItemType<ItemStack> PURPLE_BUNDLE;

    public static ItemType<ItemCandleStack> PURPLE_CANDLE;

    public static ItemType<ItemStack> PURPLE_CANDLE_CAKE;

    public static ItemType<ItemStack> PURPLE_CARPET;

    public static ItemType<ItemStack> PURPLE_CONCRETE;

    public static ItemType<ItemStack> PURPLE_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> PURPLE_CONCRETE_POWDER;

    public static ItemType<ItemStack> PURPLE_CONCRETE_SLAB;

    public static ItemType<ItemStack> PURPLE_CONCRETE_STAIRS;

    public static ItemType<ItemStack> PURPLE_CUSHION;

    public static ItemType<ItemDyeStack> PURPLE_DYE;

    public static ItemType<ItemStack> PURPLE_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> PURPLE_HARNESS;

    public static ItemType<ItemShulkerBoxStack> PURPLE_SHULKER_BOX;

    public static ItemType<ItemStack> PURPLE_STAINED_GLASS;

    public static ItemType<ItemStack> PURPLE_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> PURPLE_TERRACOTTA;

    public static ItemType<ItemStack> PURPLE_WOOL;

    public static ItemType<ItemStack> PURPLE_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> PURPLE_WOOL_SLAB;

    public static ItemType<ItemStack> PURPLE_WOOL_STAIRS;

    public static ItemType<ItemStack> PURPUR_BLOCK;

    public static ItemType<ItemStack> PURPUR_DOUBLE_SLAB;

    public static ItemType<ItemStack> PURPUR_PILLAR;

    public static ItemType<ItemStack> PURPUR_SLAB;

    public static ItemType<ItemStack> PURPUR_STAIRS;

    public static ItemType<ItemStack> QUARTZ;

    public static ItemType<ItemStack> QUARTZ_BLOCK;

    public static ItemType<ItemStack> QUARTZ_BRICKS;

    public static ItemType<ItemStack> QUARTZ_DOUBLE_SLAB;

    public static ItemType<ItemStack> QUARTZ_ORE;

    public static ItemType<ItemStack> QUARTZ_PILLAR;

    public static ItemType<ItemStack> QUARTZ_SLAB;

    public static ItemType<ItemStack> QUARTZ_STAIRS;

    public static ItemType<ItemRabbitStack> RABBIT;

    public static ItemType<ItemStack> RABBIT_FOOT;

    public static ItemType<ItemStack> RABBIT_HIDE;

    public static ItemType<ItemSpawnEggStack> RABBIT_SPAWN_EGG;

    public static ItemType<ItemRabbitStewStack> RABBIT_STEW;

    public static ItemType<ItemStack> RAIL;

    public static ItemType<ItemStack> RAISER_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> RAPID_FERTILIZER;

    public static ItemType<ItemSpawnEggStack> RAVAGER_SPAWN_EGG;

    public static ItemType<ItemStack> RAW_COPPER;

    public static ItemType<ItemStack> RAW_COPPER_BLOCK;

    public static ItemType<ItemStack> RAW_GOLD;

    public static ItemType<ItemStack> RAW_GOLD_BLOCK;

    public static ItemType<ItemStack> RAW_IRON;

    public static ItemType<ItemStack> RAW_IRON_BLOCK;

    public static ItemType<ItemStack> RECOVERY_COMPASS;

    public static ItemType<ItemStack> RED_BUNDLE;

    public static ItemType<ItemCandleStack> RED_CANDLE;

    public static ItemType<ItemStack> RED_CANDLE_CAKE;

    public static ItemType<ItemStack> RED_CARPET;

    public static ItemType<ItemStack> RED_CONCRETE;

    public static ItemType<ItemStack> RED_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> RED_CONCRETE_POWDER;

    public static ItemType<ItemStack> RED_CONCRETE_SLAB;

    public static ItemType<ItemStack> RED_CONCRETE_STAIRS;

    public static ItemType<ItemStack> RED_CUSHION;

    public static ItemType<ItemDyeStack> RED_DYE;

    public static ItemType<ItemStack> RED_FLOWER;

    public static ItemType<ItemStack> RED_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> RED_HARNESS;

    public static ItemType<ItemStack> RED_MUSHROOM;

    public static ItemType<ItemStack> RED_MUSHROOM_BLOCK;

    public static ItemType<ItemStack> RED_NETHER_BRICK;

    public static ItemType<ItemStack> RED_NETHER_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> RED_NETHER_BRICK_SLAB;

    public static ItemType<ItemStack> RED_NETHER_BRICK_STAIRS;

    public static ItemType<ItemStack> RED_NETHER_BRICK_WALL;

    public static ItemType<ItemStack> RED_POPLAR_LEAVES;

    public static ItemType<ItemStack> RED_SAND;

    public static ItemType<ItemStack> RED_SANDSTONE;

    public static ItemType<ItemStack> RED_SANDSTONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> RED_SANDSTONE_SLAB;

    public static ItemType<ItemStack> RED_SANDSTONE_STAIRS;

    public static ItemType<ItemStack> RED_SANDSTONE_WALL;

    public static ItemType<ItemStack> RED_SHRUB;

    public static ItemType<ItemShulkerBoxStack> RED_SHULKER_BOX;

    public static ItemType<ItemStack> RED_STAINED_GLASS;

    public static ItemType<ItemStack> RED_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> RED_TERRACOTTA;

    public static ItemType<ItemStack> RED_TULIP;

    public static ItemType<ItemStack> RED_WOOL;

    public static ItemType<ItemStack> RED_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> RED_WOOL_SLAB;

    public static ItemType<ItemStack> RED_WOOL_STAIRS;

    public static ItemType<ItemStack> REDSTONE;

    public static ItemType<ItemStack> REDSTONE_BLOCK;

    public static ItemType<ItemStack> REDSTONE_LAMP;

    public static ItemType<ItemStack> REDSTONE_ORE;

    public static ItemType<ItemStack> REDSTONE_TORCH;

    public static ItemType<ItemStack> REDSTONE_WIRE;

    public static ItemType<ItemStack> REINFORCED_DEEPSLATE;

    public static ItemType<ItemStack> REPEATER;

    public static ItemType<ItemStack> REPEATING_COMMAND_BLOCK;

    public static ItemType<ItemStack> RESERVED6;

    public static ItemType<ItemStack> RESIN_BLOCK;

    public static ItemType<ItemStack> RESIN_BRICK;

    public static ItemType<ItemStack> RESIN_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> RESIN_BRICK_SLAB;

    public static ItemType<ItemStack> RESIN_BRICK_STAIRS;

    public static ItemType<ItemStack> RESIN_BRICK_WALL;

    public static ItemType<ItemStack> RESIN_BRICKS;

    public static ItemType<ItemStack> RESIN_CLUMP;

    public static ItemType<ItemStack> RESPAWN_ANCHOR;

    public static ItemType<ItemStack> RIB_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> ROSE_BUSH;

    public static ItemType<ItemRottenFleshStack> ROTTEN_FLESH;

    public static ItemType<ItemStack> SADDLE;

    public static ItemType<ItemSalmonStack> SALMON;

    public static ItemType<ItemBucketStack> SALMON_BUCKET;

    public static ItemType<ItemSpawnEggStack> SALMON_SPAWN_EGG;

    public static ItemType<ItemStack> SAND;

    public static ItemType<ItemStack> SANDSTONE;

    public static ItemType<ItemStack> SANDSTONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> SANDSTONE_SLAB;

    public static ItemType<ItemStack> SANDSTONE_STAIRS;

    public static ItemType<ItemStack> SANDSTONE_WALL;

    public static ItemType<ItemStack> SAPLING;

    public static ItemType<ItemStack> SCAFFOLDING;

    public static ItemType<ItemStack> SCRAPE_POTTERY_SHERD;

    public static ItemType<ItemStack> SCULK;

    public static ItemType<ItemStack> SCULK_CATALYST;

    public static ItemType<ItemStack> SCULK_SENSOR;

    public static ItemType<ItemStack> SCULK_SHRIEKER;

    public static ItemType<ItemStack> SCULK_VEIN;

    public static ItemType<ItemStack> SEA_LANTERN;

    public static ItemType<ItemSeaPickleStack> SEA_PICKLE;

    public static ItemType<ItemStack> SEAGRASS;

    public static ItemType<ItemStack> SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> SHEAF_POTTERY_SHERD;

    public static ItemType<ItemStack> SHEARS;

    public static ItemType<ItemSpawnEggStack> SHEEP_SPAWN_EGG;

    public static ItemType<ItemStack> SHELF_MUSHROOM;

    public static ItemType<ItemStack> SHELTER_POTTERY_SHERD;

    public static ItemType<ItemShieldStack> SHIELD;

    public static ItemType<ItemStack> SHORT_DRY_GRASS;

    public static ItemType<ItemStack> SHORT_GRASS;

    public static ItemType<ItemStack> SHROOMLIGHT;

    public static ItemType<ItemShulkerBoxStack> SHULKER_BOX;

    public static ItemType<ItemStack> SHULKER_SHELL;

    public static ItemType<ItemSpawnEggStack> SHULKER_SPAWN_EGG;

    public static ItemType<ItemStack> SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> SILVER_GLAZED_TERRACOTTA;

    public static ItemType<ItemSpawnEggStack> SILVERFISH_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> SKELETON_HORSE_SPAWN_EGG;

    public static ItemType<ItemHeadStack> SKELETON_SKULL;

    public static ItemType<ItemSpawnEggStack> SKELETON_SPAWN_EGG;

    public static ItemType<ItemHeadStack> SKULL;

    public static ItemType<ItemStack> SKULL_BANNER_PATTERN;

    public static ItemType<ItemStack> SKULL_POTTERY_SHERD;

    public static ItemType<ItemStack> SLIME;

    public static ItemType<ItemStack> SLIME_BALL;

    public static ItemType<ItemSpawnEggStack> SLIME_SPAWN_EGG;

    public static ItemType<ItemStack> SMALL_AMETHYST_BUD;

    public static ItemType<ItemStack> SMALL_DRIPLEAF_BLOCK;

    public static ItemType<ItemStack> SMITHING_TABLE;

    public static ItemType<ItemStack> SMOKER;

    public static ItemType<ItemStack> SMOOTH_BASALT;

    public static ItemType<ItemStack> SMOOTH_QUARTZ;

    public static ItemType<ItemStack> SMOOTH_QUARTZ_DOUBLE_SLAB;

    public static ItemType<ItemStack> SMOOTH_QUARTZ_SLAB;

    public static ItemType<ItemStack> SMOOTH_QUARTZ_STAIRS;

    public static ItemType<ItemStack> SMOOTH_RED_SANDSTONE;

    public static ItemType<ItemStack> SMOOTH_RED_SANDSTONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> SMOOTH_RED_SANDSTONE_SLAB;

    public static ItemType<ItemStack> SMOOTH_RED_SANDSTONE_STAIRS;

    public static ItemType<ItemStack> SMOOTH_SANDSTONE;

    public static ItemType<ItemStack> SMOOTH_SANDSTONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> SMOOTH_SANDSTONE_SLAB;

    public static ItemType<ItemStack> SMOOTH_SANDSTONE_STAIRS;

    public static ItemType<ItemStack> SMOOTH_STONE;

    public static ItemType<ItemStack> SMOOTH_STONE_DOUBLE_SLAB;

    public static ItemType<ItemStack> SMOOTH_STONE_SLAB;

    public static ItemType<ItemStack> SNIFFER_EGG;

    public static ItemType<ItemSpawnEggStack> SNIFFER_SPAWN_EGG;

    public static ItemType<ItemStack> SNORT_POTTERY_SHERD;

    public static ItemType<ItemStack> SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> SNOW;

    public static ItemType<ItemSpawnEggStack> SNOW_GOLEM_SPAWN_EGG;

    public static ItemType<ItemStack> SNOW_LAYER;

    public static ItemType<ItemSnowballStack> SNOWBALL;

    public static ItemType<ItemStack> SOUL_CAMPFIRE;

    public static ItemType<ItemStack> SOUL_FIRE;

    public static ItemType<ItemStack> SOUL_LANTERN;

    public static ItemType<ItemStack> SOUL_SAND;

    public static ItemType<ItemStack> SOUL_SOIL;

    public static ItemType<ItemStack> SOUL_TORCH;

    public static ItemType<ItemStack> SPARKLER;

    public static ItemType<ItemSpawnEggStack> SPAWN_EGG;

    public static ItemType<ItemSpiderEyeStack> SPIDER_EYE;

    public static ItemType<ItemSpawnEggStack> SPIDER_SPAWN_EGG;

    public static ItemType<ItemStack> SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemSplashPotionStack> SPLASH_POTION;

    public static ItemType<ItemStack> SPONGE;

    public static ItemType<ItemStack> SPORE_BLOSSOM;

    public static ItemType<ItemStack> SPRUCE_BOAT;

    public static ItemType<ItemStack> SPRUCE_BUTTON;

    public static ItemType<ItemStack> SPRUCE_CHEST_BOAT;

    public static ItemType<ItemStack> SPRUCE_DOOR;

    public static ItemType<ItemStack> SPRUCE_DOUBLE_SLAB;

    public static ItemType<ItemStack> SPRUCE_FENCE;

    public static ItemType<ItemStack> SPRUCE_FENCE_GATE;

    public static ItemType<ItemStack> SPRUCE_HANGING_SIGN;

    public static ItemType<ItemStack> SPRUCE_LEAVES;

    public static ItemType<ItemStack> SPRUCE_LOG;

    public static ItemType<ItemStack> SPRUCE_PLANKS;

    public static ItemType<ItemStack> SPRUCE_PRESSURE_PLATE;

    public static ItemType<ItemStack> SPRUCE_SAPLING;

    public static ItemType<ItemStack> SPRUCE_SHELF;

    public static ItemType<ItemSignStack> SPRUCE_SIGN;

    public static ItemType<ItemStack> SPRUCE_SLAB;

    public static ItemType<ItemStack> SPRUCE_STAIRS;

    public static ItemType<ItemStack> SPRUCE_STANDING_SIGN;

    public static ItemType<ItemStack> SPRUCE_TRAPDOOR;

    public static ItemType<ItemStack> SPRUCE_WALL_SIGN;

    public static ItemType<ItemStack> SPRUCE_WOOD;

    public static ItemType<ItemStack> SPYGLASS;

    public static ItemType<ItemSpawnEggStack> SQUID_SPAWN_EGG;

    public static ItemType<ItemStack> STAINED_GLASS;

    public static ItemType<ItemStack> STAINED_GLASS_PANE;

    public static ItemType<ItemStack> STAINED_HARDENED_CLAY;

    public static ItemType<ItemStack> STANDING_BANNER;

    public static ItemType<ItemStack> STANDING_SIGN;

    public static ItemType<ItemStack> STICK;

    public static ItemType<ItemStack> STICKY_PISTON;

    public static ItemType<ItemStack> STICKY_PISTON_ARM_COLLISION;

    public static ItemType<ItemStack> STONE;

    public static ItemType<ItemAxeStack> STONE_AXE;

    public static ItemType<ItemStack> STONE_BLOCK_SLAB;

    public static ItemType<ItemStack> STONE_BLOCK_SLAB2;

    public static ItemType<ItemStack> STONE_BLOCK_SLAB3;

    public static ItemType<ItemStack> STONE_BLOCK_SLAB4;

    public static ItemType<ItemStack> STONE_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> STONE_BRICK_SLAB;

    public static ItemType<ItemStack> STONE_BRICK_STAIRS;

    public static ItemType<ItemStack> STONE_BRICK_WALL;

    public static ItemType<ItemStack> STONE_BRICKS;

    public static ItemType<ItemStack> STONE_BUTTON;

    public static ItemType<ItemHoeStack> STONE_HOE;

    public static ItemType<ItemPickaxeStack> STONE_PICKAXE;

    public static ItemType<ItemStack> STONE_PRESSURE_PLATE;

    public static ItemType<ItemShovelStack> STONE_SHOVEL;

    public static ItemType<ItemSpearStack> STONE_SPEAR;

    public static ItemType<ItemStack> STONE_STAIRS;

    public static ItemType<ItemSwordStack> STONE_SWORD;

    public static ItemType<ItemStack> STONEBRICK;

    public static ItemType<ItemStack> STONECUTTER;

    public static ItemType<ItemStack> STONECUTTER_BLOCK;

    public static ItemType<ItemStack> STRAW_BED;

    public static ItemType<ItemSpawnEggStack> STRAY_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> STRIDER_SPAWN_EGG;

    public static ItemType<ItemStack> STRING;

    public static ItemType<ItemStack> STRIPPED_ACACIA_LOG;

    public static ItemType<ItemStack> STRIPPED_ACACIA_WOOD;

    public static ItemType<ItemStack> STRIPPED_BAMBOO_BLOCK;

    public static ItemType<ItemStack> STRIPPED_BIRCH_LOG;

    public static ItemType<ItemStack> STRIPPED_BIRCH_WOOD;

    public static ItemType<ItemStack> STRIPPED_CHERRY_LOG;

    public static ItemType<ItemStack> STRIPPED_CHERRY_WOOD;

    public static ItemType<ItemStack> STRIPPED_CRIMSON_HYPHAE;

    public static ItemType<ItemStack> STRIPPED_CRIMSON_STEM;

    public static ItemType<ItemStack> STRIPPED_DARK_OAK_LOG;

    public static ItemType<ItemStack> STRIPPED_DARK_OAK_WOOD;

    public static ItemType<ItemStack> STRIPPED_JUNGLE_LOG;

    public static ItemType<ItemStack> STRIPPED_JUNGLE_WOOD;

    public static ItemType<ItemStack> STRIPPED_MANGROVE_LOG;

    public static ItemType<ItemStack> STRIPPED_MANGROVE_WOOD;

    public static ItemType<ItemStack> STRIPPED_OAK_LOG;

    public static ItemType<ItemStack> STRIPPED_OAK_WOOD;

    public static ItemType<ItemStack> STRIPPED_PALE_OAK_LOG;

    public static ItemType<ItemStack> STRIPPED_PALE_OAK_WOOD;

    public static ItemType<ItemStack> STRIPPED_POPLAR_LOG;

    public static ItemType<ItemStack> STRIPPED_POPLAR_WOOD;

    public static ItemType<ItemStack> STRIPPED_SPRUCE_LOG;

    public static ItemType<ItemStack> STRIPPED_SPRUCE_WOOD;

    public static ItemType<ItemStack> STRIPPED_WARPED_HYPHAE;

    public static ItemType<ItemStack> STRIPPED_WARPED_STEM;

    public static ItemType<ItemStack> STRUCTURE_BLOCK;

    public static ItemType<ItemStack> STRUCTURE_VOID;

    public static ItemType<ItemStack> SUGAR;

    public static ItemType<ItemStack> SUGAR_CANE;

    public static ItemType<ItemStack> SULFUR;

    public static ItemType<ItemStack> SULFUR_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> SULFUR_BRICK_SLAB;

    public static ItemType<ItemStack> SULFUR_BRICK_STAIRS;

    public static ItemType<ItemStack> SULFUR_BRICK_WALL;

    public static ItemType<ItemStack> SULFUR_BRICKS;

    public static ItemType<ItemBucketStack> SULFUR_CUBE_BUCKET;

    public static ItemType<ItemSpawnEggStack> SULFUR_CUBE_SPAWN_EGG;

    public static ItemType<ItemStack> SULFUR_DOUBLE_SLAB;

    public static ItemType<ItemStack> SULFUR_SLAB;

    public static ItemType<ItemStack> SULFUR_SPIKE;

    public static ItemType<ItemStack> SULFUR_STAIRS;

    public static ItemType<ItemStack> SULFUR_WALL;

    public static ItemType<ItemStack> SUNFLOWER;

    public static ItemType<ItemStack> SUSPICIOUS_GRAVEL;

    public static ItemType<ItemStack> SUSPICIOUS_SAND;

    public static ItemType<ItemStack> SUSPICIOUS_STEW;

    public static ItemType<ItemSweetBerriesStack> SWEET_BERRIES;

    public static ItemType<ItemStack> SWEET_BERRY_BUSH;

    public static ItemType<ItemBucketStack> TADPOLE_BUCKET;

    public static ItemType<ItemSpawnEggStack> TADPOLE_SPAWN_EGG;

    public static ItemType<ItemStack> TALL_DRY_GRASS;

    public static ItemType<ItemStack> TALL_GRASS;

    public static ItemType<ItemStack> TALLGRASS;

    public static ItemType<ItemStack> TARGET;

    public static ItemType<ItemStack> TIDE_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> TINTED_GLASS;

    public static ItemType<ItemStack> TNT;

    public static ItemType<ItemStack> TNT_MINECART;

    public static ItemType<ItemStack> TORCH;

    public static ItemType<ItemStack> TORCHFLOWER;

    public static ItemType<ItemStack> TORCHFLOWER_CROP;

    public static ItemType<ItemStack> TORCHFLOWER_SEEDS;

    public static ItemType<ItemStack> TOTEM_OF_UNDYING;

    public static ItemType<ItemSpawnEggStack> TRADER_LLAMA_SPAWN_EGG;

    public static ItemType<ItemStack> TRAPDOOR;

    public static ItemType<ItemStack> TRAPPED_CHEST;

    public static ItemType<ItemStack> TRIAL_KEY;

    public static ItemType<ItemStack> TRIAL_SPAWNER;

    public static ItemType<ItemTridentStack> TRIDENT;

    public static ItemType<ItemStack> TRIP_WIRE;

    public static ItemType<ItemStack> TRIPWIRE_HOOK;

    public static ItemType<ItemTropicalFishStack> TROPICAL_FISH;

    public static ItemType<ItemBucketStack> TROPICAL_FISH_BUCKET;

    public static ItemType<ItemSpawnEggStack> TROPICAL_FISH_SPAWN_EGG;

    public static ItemType<ItemStack> TUBE_CORAL;

    public static ItemType<ItemStack> TUBE_CORAL_BLOCK;

    public static ItemType<ItemStack> TUBE_CORAL_FAN;

    public static ItemType<ItemStack> TUBE_CORAL_WALL_FAN;

    public static ItemType<ItemStack> TUFF;

    public static ItemType<ItemStack> TUFF_BRICK_DOUBLE_SLAB;

    public static ItemType<ItemStack> TUFF_BRICK_SLAB;

    public static ItemType<ItemStack> TUFF_BRICK_STAIRS;

    public static ItemType<ItemStack> TUFF_BRICK_WALL;

    public static ItemType<ItemStack> TUFF_BRICKS;

    public static ItemType<ItemStack> TUFF_DOUBLE_SLAB;

    public static ItemType<ItemStack> TUFF_SLAB;

    public static ItemType<ItemStack> TUFF_STAIRS;

    public static ItemType<ItemStack> TUFF_WALL;

    public static ItemType<ItemStack> TURTLE_EGG;

    public static ItemType<ItemTurtleHelmetStack> TURTLE_HELMET;

    public static ItemType<ItemStack> TURTLE_SCUTE;

    public static ItemType<ItemSpawnEggStack> TURTLE_SPAWN_EGG;

    public static ItemType<ItemStack> TWISTING_VINES;

    public static ItemType<ItemStack> UNDERWATER_TNT;

    public static ItemType<ItemStack> UNDERWATER_TORCH;

    public static ItemType<ItemShulkerBoxStack> UNDYED_SHULKER_BOX;

    public static ItemType<ItemStack> UNKNOWN;

    public static ItemType<ItemStack> UNLIT_REDSTONE_TORCH;

    public static ItemType<ItemStack> UNPOWERED_COMPARATOR;

    public static ItemType<ItemStack> UNPOWERED_REPEATER;

    public static ItemType<ItemStack> VAULT;

    public static ItemType<ItemStack> VERDANT_FROGLIGHT;

    public static ItemType<ItemStack> VEX_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemSpawnEggStack> VEX_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> VILLAGER_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> VINDICATOR_SPAWN_EGG;

    public static ItemType<ItemStack> VINE;

    public static ItemType<ItemStack> WALL_BANNER;

    public static ItemType<ItemStack> WALL_SIGN;

    public static ItemType<ItemSpawnEggStack> WANDERING_TRADER_SPAWN_EGG;

    public static ItemType<ItemStack> WARD_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemSpawnEggStack> WARDEN_SPAWN_EGG;

    public static ItemType<ItemStack> WARPED_BUTTON;

    public static ItemType<ItemStack> WARPED_DOOR;

    public static ItemType<ItemStack> WARPED_DOUBLE_SLAB;

    public static ItemType<ItemStack> WARPED_FENCE;

    public static ItemType<ItemStack> WARPED_FENCE_GATE;

    public static ItemType<ItemStack> WARPED_FUNGUS;

    public static ItemType<ItemStack> WARPED_FUNGUS_ON_A_STICK;

    public static ItemType<ItemStack> WARPED_HANGING_SIGN;

    public static ItemType<ItemStack> WARPED_HYPHAE;

    public static ItemType<ItemStack> WARPED_NYLIUM;

    public static ItemType<ItemStack> WARPED_PLANKS;

    public static ItemType<ItemStack> WARPED_PRESSURE_PLATE;

    public static ItemType<ItemStack> WARPED_ROOTS;

    public static ItemType<ItemStack> WARPED_SHELF;

    public static ItemType<ItemSignStack> WARPED_SIGN;

    public static ItemType<ItemStack> WARPED_SLAB;

    public static ItemType<ItemStack> WARPED_STAIRS;

    public static ItemType<ItemStack> WARPED_STANDING_SIGN;

    public static ItemType<ItemStack> WARPED_STEM;

    public static ItemType<ItemStack> WARPED_TRAPDOOR;

    public static ItemType<ItemStack> WARPED_WALL_SIGN;

    public static ItemType<ItemStack> WARPED_WART_BLOCK;

    public static ItemType<ItemStack> WATER;

    public static ItemType<ItemBucketStack> WATER_BUCKET;

    public static ItemType<ItemStack> WATERLILY;

    public static ItemType<ItemStack> WAXED_CHISELED_COPPER;

    public static ItemType<ItemStack> WAXED_COPPER;

    public static ItemType<ItemStack> WAXED_COPPER_BARS;

    public static ItemType<ItemStack> WAXED_COPPER_BULB;

    public static ItemType<ItemStack> WAXED_COPPER_CHAIN;

    public static ItemType<ItemStack> WAXED_COPPER_CHEST;

    public static ItemType<ItemStack> WAXED_COPPER_DOOR;

    public static ItemType<ItemStack> WAXED_COPPER_GOLEM_STATUE;

    public static ItemType<ItemStack> WAXED_COPPER_GRATE;

    public static ItemType<ItemStack> WAXED_COPPER_LANTERN;

    public static ItemType<ItemStack> WAXED_COPPER_TRAPDOOR;

    public static ItemType<ItemStack> WAXED_CUT_COPPER;

    public static ItemType<ItemStack> WAXED_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> WAXED_CUT_COPPER_STAIRS;

    public static ItemType<ItemStack> WAXED_DOUBLE_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> WAXED_EXPOSED_CHISELED_COPPER;

    public static ItemType<ItemStack> WAXED_EXPOSED_COPPER;

    public static ItemType<ItemStack> WAXED_EXPOSED_COPPER_BARS;

    public static ItemType<ItemStack> WAXED_EXPOSED_COPPER_BULB;

    public static ItemType<ItemStack> WAXED_EXPOSED_COPPER_CHAIN;

    public static ItemType<ItemStack> WAXED_EXPOSED_COPPER_CHEST;

    public static ItemType<ItemStack> WAXED_EXPOSED_COPPER_DOOR;

    public static ItemType<ItemStack> WAXED_EXPOSED_COPPER_GOLEM_STATUE;

    public static ItemType<ItemStack> WAXED_EXPOSED_COPPER_GRATE;

    public static ItemType<ItemStack> WAXED_EXPOSED_COPPER_LANTERN;

    public static ItemType<ItemStack> WAXED_EXPOSED_COPPER_TRAPDOOR;

    public static ItemType<ItemStack> WAXED_EXPOSED_CUT_COPPER;

    public static ItemType<ItemStack> WAXED_EXPOSED_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> WAXED_EXPOSED_CUT_COPPER_STAIRS;

    public static ItemType<ItemStack> WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> WAXED_EXPOSED_LIGHTNING_ROD;

    public static ItemType<ItemStack> WAXED_LIGHTNING_ROD;

    public static ItemType<ItemStack> WAXED_OXIDIZED_CHISELED_COPPER;

    public static ItemType<ItemStack> WAXED_OXIDIZED_COPPER;

    public static ItemType<ItemStack> WAXED_OXIDIZED_COPPER_BARS;

    public static ItemType<ItemStack> WAXED_OXIDIZED_COPPER_BULB;

    public static ItemType<ItemStack> WAXED_OXIDIZED_COPPER_CHAIN;

    public static ItemType<ItemStack> WAXED_OXIDIZED_COPPER_CHEST;

    public static ItemType<ItemStack> WAXED_OXIDIZED_COPPER_DOOR;

    public static ItemType<ItemStack> WAXED_OXIDIZED_COPPER_GOLEM_STATUE;

    public static ItemType<ItemStack> WAXED_OXIDIZED_COPPER_GRATE;

    public static ItemType<ItemStack> WAXED_OXIDIZED_COPPER_LANTERN;

    public static ItemType<ItemStack> WAXED_OXIDIZED_COPPER_TRAPDOOR;

    public static ItemType<ItemStack> WAXED_OXIDIZED_CUT_COPPER;

    public static ItemType<ItemStack> WAXED_OXIDIZED_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> WAXED_OXIDIZED_CUT_COPPER_STAIRS;

    public static ItemType<ItemStack> WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> WAXED_OXIDIZED_LIGHTNING_ROD;

    public static ItemType<ItemStack> WAXED_WEATHERED_CHISELED_COPPER;

    public static ItemType<ItemStack> WAXED_WEATHERED_COPPER;

    public static ItemType<ItemStack> WAXED_WEATHERED_COPPER_BARS;

    public static ItemType<ItemStack> WAXED_WEATHERED_COPPER_BULB;

    public static ItemType<ItemStack> WAXED_WEATHERED_COPPER_CHAIN;

    public static ItemType<ItemStack> WAXED_WEATHERED_COPPER_CHEST;

    public static ItemType<ItemStack> WAXED_WEATHERED_COPPER_DOOR;

    public static ItemType<ItemStack> WAXED_WEATHERED_COPPER_GOLEM_STATUE;

    public static ItemType<ItemStack> WAXED_WEATHERED_COPPER_GRATE;

    public static ItemType<ItemStack> WAXED_WEATHERED_COPPER_LANTERN;

    public static ItemType<ItemStack> WAXED_WEATHERED_COPPER_TRAPDOOR;

    public static ItemType<ItemStack> WAXED_WEATHERED_CUT_COPPER;

    public static ItemType<ItemStack> WAXED_WEATHERED_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> WAXED_WEATHERED_CUT_COPPER_STAIRS;

    public static ItemType<ItemStack> WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> WAXED_WEATHERED_LIGHTNING_ROD;

    public static ItemType<ItemStack> WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> WEATHERED_CHISELED_COPPER;

    public static ItemType<ItemStack> WEATHERED_COPPER;

    public static ItemType<ItemStack> WEATHERED_COPPER_BARS;

    public static ItemType<ItemStack> WEATHERED_COPPER_BULB;

    public static ItemType<ItemStack> WEATHERED_COPPER_CHAIN;

    public static ItemType<ItemStack> WEATHERED_COPPER_CHEST;

    public static ItemType<ItemStack> WEATHERED_COPPER_DOOR;

    public static ItemType<ItemStack> WEATHERED_COPPER_GOLEM_STATUE;

    public static ItemType<ItemStack> WEATHERED_COPPER_GRATE;

    public static ItemType<ItemStack> WEATHERED_COPPER_LANTERN;

    public static ItemType<ItemStack> WEATHERED_COPPER_TRAPDOOR;

    public static ItemType<ItemStack> WEATHERED_CUT_COPPER;

    public static ItemType<ItemStack> WEATHERED_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> WEATHERED_CUT_COPPER_STAIRS;

    public static ItemType<ItemStack> WEATHERED_DOUBLE_CUT_COPPER_SLAB;

    public static ItemType<ItemStack> WEATHERED_LIGHTNING_ROD;

    public static ItemType<ItemStack> WEB;

    public static ItemType<ItemStack> WEEPING_VINES;

    public static ItemType<ItemStack> WET_SPONGE;

    public static ItemType<ItemStack> WHEAT;

    public static ItemType<ItemStack> WHEAT_SEEDS;

    public static ItemType<ItemStack> WHITE_BUNDLE;

    public static ItemType<ItemCandleStack> WHITE_CANDLE;

    public static ItemType<ItemStack> WHITE_CANDLE_CAKE;

    public static ItemType<ItemStack> WHITE_CARPET;

    public static ItemType<ItemStack> WHITE_CONCRETE;

    public static ItemType<ItemStack> WHITE_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> WHITE_CONCRETE_POWDER;

    public static ItemType<ItemStack> WHITE_CONCRETE_SLAB;

    public static ItemType<ItemStack> WHITE_CONCRETE_STAIRS;

    public static ItemType<ItemStack> WHITE_CUSHION;

    public static ItemType<ItemDyeStack> WHITE_DYE;

    public static ItemType<ItemStack> WHITE_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> WHITE_HARNESS;

    public static ItemType<ItemShulkerBoxStack> WHITE_SHULKER_BOX;

    public static ItemType<ItemStack> WHITE_STAINED_GLASS;

    public static ItemType<ItemStack> WHITE_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> WHITE_TERRACOTTA;

    public static ItemType<ItemStack> WHITE_TULIP;

    public static ItemType<ItemStack> WHITE_WOOL;

    public static ItemType<ItemStack> WHITE_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> WHITE_WOOL_SLAB;

    public static ItemType<ItemStack> WHITE_WOOL_STAIRS;

    public static ItemType<ItemStack> WILD_ARMOR_TRIM_SMITHING_TEMPLATE;

    public static ItemType<ItemStack> WILDFLOWERS;

    public static ItemType<ItemWindChargeStack> WIND_CHARGE;

    public static ItemType<ItemSpawnEggStack> WITCH_SPAWN_EGG;

    public static ItemType<ItemStack> WITHER_ROSE;

    public static ItemType<ItemHeadStack> WITHER_SKELETON_SKULL;

    public static ItemType<ItemSpawnEggStack> WITHER_SKELETON_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> WITHER_SPAWN_EGG;

    public static ItemType<ItemStack> WOLF_ARMOR;

    public static ItemType<ItemSpawnEggStack> WOLF_SPAWN_EGG;

    public static ItemType<ItemStack> WOOD;

    public static ItemType<ItemAxeStack> WOODEN_AXE;

    public static ItemType<ItemStack> WOODEN_BUTTON;

    public static ItemType<ItemStack> WOODEN_DOOR;

    public static ItemType<ItemHoeStack> WOODEN_HOE;

    public static ItemType<ItemPickaxeStack> WOODEN_PICKAXE;

    public static ItemType<ItemStack> WOODEN_PRESSURE_PLATE;

    public static ItemType<ItemShovelStack> WOODEN_SHOVEL;

    public static ItemType<ItemStack> WOODEN_SLAB;

    public static ItemType<ItemSpearStack> WOODEN_SPEAR;

    public static ItemType<ItemSwordStack> WOODEN_SWORD;

    public static ItemType<ItemStack> WOOL;

    public static ItemType<ItemWritableBookStack> WRITABLE_BOOK;

    public static ItemType<ItemWrittenBookStack> WRITTEN_BOOK;

    public static ItemType<ItemStack> YELLOW_BUNDLE;

    public static ItemType<ItemCandleStack> YELLOW_CANDLE;

    public static ItemType<ItemStack> YELLOW_CANDLE_CAKE;

    public static ItemType<ItemStack> YELLOW_CARPET;

    public static ItemType<ItemStack> YELLOW_CONCRETE;

    public static ItemType<ItemStack> YELLOW_CONCRETE_DOUBLE_SLAB;

    public static ItemType<ItemStack> YELLOW_CONCRETE_POWDER;

    public static ItemType<ItemStack> YELLOW_CONCRETE_SLAB;

    public static ItemType<ItemStack> YELLOW_CONCRETE_STAIRS;

    public static ItemType<ItemStack> YELLOW_CUSHION;

    public static ItemType<ItemDyeStack> YELLOW_DYE;

    public static ItemType<ItemStack> YELLOW_GLAZED_TERRACOTTA;

    public static ItemType<ItemStack> YELLOW_HARNESS;

    public static ItemType<ItemStack> YELLOW_POPLAR_LEAVES;

    public static ItemType<ItemShulkerBoxStack> YELLOW_SHULKER_BOX;

    public static ItemType<ItemStack> YELLOW_STAINED_GLASS;

    public static ItemType<ItemStack> YELLOW_STAINED_GLASS_PANE;

    public static ItemType<ItemStack> YELLOW_TERRACOTTA;

    public static ItemType<ItemStack> YELLOW_WOOL;

    public static ItemType<ItemStack> YELLOW_WOOL_DOUBLE_SLAB;

    public static ItemType<ItemStack> YELLOW_WOOL_SLAB;

    public static ItemType<ItemStack> YELLOW_WOOL_STAIRS;

    public static ItemType<ItemSpawnEggStack> ZOGLIN_SPAWN_EGG;

    public static ItemType<ItemHeadStack> ZOMBIE_HEAD;

    public static ItemType<ItemSpawnEggStack> ZOMBIE_HORSE_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> ZOMBIE_NAUTILUS_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> ZOMBIE_PIGMAN_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> ZOMBIE_SPAWN_EGG;

    public static ItemType<ItemSpawnEggStack> ZOMBIE_VILLAGER_SPAWN_EGG;
}
