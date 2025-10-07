package org.allaymc.server.item.data;

import lombok.Getter;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.Locale;

public enum ItemId {
    ACACIA_BOAT("minecraft:acacia_boat", 405),

    ACACIA_BUTTON("minecraft:acacia_button", -140),

    ACACIA_CHEST_BOAT("minecraft:acacia_chest_boat", 678),

    ACACIA_DOOR("minecraft:acacia_door", 589),

    ACACIA_DOUBLE_SLAB("minecraft:acacia_double_slab", -812),

    ACACIA_FENCE("minecraft:acacia_fence", -575),

    ACACIA_FENCE_GATE("minecraft:acacia_fence_gate", 187),

    ACACIA_HANGING_SIGN("minecraft:acacia_hanging_sign", -504),

    ACACIA_LEAVES("minecraft:acacia_leaves", 161),

    ACACIA_LOG("minecraft:acacia_log", 162),

    ACACIA_PLANKS("minecraft:acacia_planks", -742),

    ACACIA_PRESSURE_PLATE("minecraft:acacia_pressure_plate", -150),

    ACACIA_SAPLING("minecraft:acacia_sapling", -828),

    ACACIA_SHELF("minecraft:acacia_shelf", -1051),

    ACACIA_SIGN("minecraft:acacia_sign", 612),

    ACACIA_SLAB("minecraft:acacia_slab", -807),

    ACACIA_STAIRS("minecraft:acacia_stairs", 163),

    ACACIA_STANDING_SIGN("minecraft:acacia_standing_sign", -190),

    ACACIA_TRAPDOOR("minecraft:acacia_trapdoor", -145),

    ACACIA_WALL_SIGN("minecraft:acacia_wall_sign", -191),

    ACACIA_WOOD("minecraft:acacia_wood", -817),

    ACTIVATOR_RAIL("minecraft:activator_rail", 126),

    AGENT_SPAWN_EGG("minecraft:agent_spawn_egg", 515),

    AIR("minecraft:air", -158),

    ALLAY_SPAWN_EGG("minecraft:allay_spawn_egg", 667),

    ALLIUM("minecraft:allium", -831),

    ALLOW("minecraft:allow", 210),

    AMETHYST_BLOCK("minecraft:amethyst_block", -327),

    AMETHYST_CLUSTER("minecraft:amethyst_cluster", -329),

    AMETHYST_SHARD("minecraft:amethyst_shard", 660),

    ANCIENT_DEBRIS("minecraft:ancient_debris", -271),

    ANDESITE("minecraft:andesite", -594),

    ANDESITE_DOUBLE_SLAB("minecraft:andesite_double_slab", -920),

    ANDESITE_SLAB("minecraft:andesite_slab", -893),

    ANDESITE_STAIRS("minecraft:andesite_stairs", -171),

    ANDESITE_WALL("minecraft:andesite_wall", -974),

    ANGLER_POTTERY_SHERD("minecraft:angler_pottery_sherd", 692),

    ANVIL("minecraft:anvil", 145),

    APPLE("minecraft:apple", 278),

    ARCHER_POTTERY_SHERD("minecraft:archer_pottery_sherd", 693),

    ARMADILLO_SCUTE("minecraft:armadillo_scute", 739),

    ARMADILLO_SPAWN_EGG("minecraft:armadillo_spawn_egg", 738),

    ARMOR_STAND("minecraft:armor_stand", 585),

    ARMS_UP_POTTERY_SHERD("minecraft:arms_up_pottery_sherd", 694),

    ARROW("minecraft:arrow", 325),

    AXOLOTL_BUCKET("minecraft:axolotl_bucket", 394),

    AXOLOTL_SPAWN_EGG("minecraft:axolotl_spawn_egg", 530),

    AZALEA("minecraft:azalea", -337),

    AZALEA_LEAVES("minecraft:azalea_leaves", -324),

    AZALEA_LEAVES_FLOWERED("minecraft:azalea_leaves_flowered", -325),

    AZURE_BLUET("minecraft:azure_bluet", -832),

    BAKED_POTATO("minecraft:baked_potato", 303),

    BALLOON("minecraft:balloon", 635),

    BAMBOO("minecraft:bamboo", -163),

    BAMBOO_BLOCK("minecraft:bamboo_block", -527),

    BAMBOO_BUTTON("minecraft:bamboo_button", -511),

    BAMBOO_CHEST_RAFT("minecraft:bamboo_chest_raft", 690),

    BAMBOO_DOOR("minecraft:bamboo_door", -517),

    BAMBOO_DOUBLE_SLAB("minecraft:bamboo_double_slab", -521),

    BAMBOO_FENCE("minecraft:bamboo_fence", -515),

    BAMBOO_FENCE_GATE("minecraft:bamboo_fence_gate", -516),

    BAMBOO_HANGING_SIGN("minecraft:bamboo_hanging_sign", -522),

    BAMBOO_MOSAIC("minecraft:bamboo_mosaic", -509),

    BAMBOO_MOSAIC_DOUBLE_SLAB("minecraft:bamboo_mosaic_double_slab", -525),

    BAMBOO_MOSAIC_SLAB("minecraft:bamboo_mosaic_slab", -524),

    BAMBOO_MOSAIC_STAIRS("minecraft:bamboo_mosaic_stairs", -523),

    BAMBOO_PLANKS("minecraft:bamboo_planks", -510),

    BAMBOO_PRESSURE_PLATE("minecraft:bamboo_pressure_plate", -514),

    BAMBOO_RAFT("minecraft:bamboo_raft", 689),

    BAMBOO_SAPLING("minecraft:bamboo_sapling", -164),

    BAMBOO_SHELF("minecraft:bamboo_shelf", -1056),

    BAMBOO_SIGN("minecraft:bamboo_sign", 688),

    BAMBOO_SLAB("minecraft:bamboo_slab", -513),

    BAMBOO_STAIRS("minecraft:bamboo_stairs", -512),

    BAMBOO_STANDING_SIGN("minecraft:bamboo_standing_sign", -518),

    BAMBOO_TRAPDOOR("minecraft:bamboo_trapdoor", -520),

    BAMBOO_WALL_SIGN("minecraft:bamboo_wall_sign", -519),

    BANNER("minecraft:banner", 600),

    BANNER_PATTERN("minecraft:banner_pattern", 825),

    BARREL("minecraft:barrel", -203),

    BARRIER("minecraft:barrier", -161),

    BASALT("minecraft:basalt", -234),

    BAT_SPAWN_EGG("minecraft:bat_spawn_egg", 480),

    BEACON("minecraft:beacon", 138),

    BED("minecraft:bed", 444),

    BEDROCK("minecraft:bedrock", 7),

    BEE_NEST("minecraft:bee_nest", -218),

    BEE_SPAWN_EGG("minecraft:bee_spawn_egg", 522),

    BEEF("minecraft:beef", 295),

    BEEHIVE("minecraft:beehive", -219),

    BEETROOT("minecraft:beetroot", 307),

    BEETROOT_SEEDS("minecraft:beetroot_seeds", 317),

    BEETROOT_SOUP("minecraft:beetroot_soup", 308),

    BELL("minecraft:bell", -206),

    BIG_DRIPLEAF("minecraft:big_dripleaf", -323),

    BIRCH_BOAT("minecraft:birch_boat", 402),

    BIRCH_BUTTON("minecraft:birch_button", -141),

    BIRCH_CHEST_BOAT("minecraft:birch_chest_boat", 675),

    BIRCH_DOOR("minecraft:birch_door", 587),

    BIRCH_DOUBLE_SLAB("minecraft:birch_double_slab", -810),

    BIRCH_FENCE("minecraft:birch_fence", -576),

    BIRCH_FENCE_GATE("minecraft:birch_fence_gate", 184),

    BIRCH_HANGING_SIGN("minecraft:birch_hanging_sign", -502),

    BIRCH_LEAVES("minecraft:birch_leaves", -801),

    BIRCH_LOG("minecraft:birch_log", -570),

    BIRCH_PLANKS("minecraft:birch_planks", -740),

    BIRCH_PRESSURE_PLATE("minecraft:birch_pressure_plate", -151),

    BIRCH_SAPLING("minecraft:birch_sapling", -826),

    BIRCH_SHELF("minecraft:birch_shelf", -1049),

    BIRCH_SIGN("minecraft:birch_sign", 610),

    BIRCH_SLAB("minecraft:birch_slab", -805),

    BIRCH_STAIRS("minecraft:birch_stairs", 135),

    BIRCH_STANDING_SIGN("minecraft:birch_standing_sign", -186),

    BIRCH_TRAPDOOR("minecraft:birch_trapdoor", -146),

    BIRCH_WALL_SIGN("minecraft:birch_wall_sign", -187),

    BIRCH_WOOD("minecraft:birch_wood", -815),

    BLACK_BUNDLE("minecraft:black_bundle", 257),

    BLACK_CANDLE("minecraft:black_candle", -428),

    BLACK_CANDLE_CAKE("minecraft:black_candle_cake", -445),

    BLACK_CARPET("minecraft:black_carpet", -611),

    BLACK_CONCRETE("minecraft:black_concrete", -642),

    BLACK_CONCRETE_POWDER("minecraft:black_concrete_powder", -723),

    BLACK_DYE("minecraft:black_dye", 421),

    BLACK_GLAZED_TERRACOTTA("minecraft:black_glazed_terracotta", 235),

    BLACK_HARNESS("minecraft:black_harness", 751),

    BLACK_SHULKER_BOX("minecraft:black_shulker_box", -627),

    BLACK_STAINED_GLASS("minecraft:black_stained_glass", -687),

    BLACK_STAINED_GLASS_PANE("minecraft:black_stained_glass_pane", -657),

    BLACK_TERRACOTTA("minecraft:black_terracotta", -738),

    BLACK_WOOL("minecraft:black_wool", -554),

    BLACKSTONE("minecraft:blackstone", -273),

    BLACKSTONE_DOUBLE_SLAB("minecraft:blackstone_double_slab", -283),

    BLACKSTONE_SLAB("minecraft:blackstone_slab", -282),

    BLACKSTONE_STAIRS("minecraft:blackstone_stairs", -276),

    BLACKSTONE_WALL("minecraft:blackstone_wall", -277),

    BLADE_POTTERY_SHERD("minecraft:blade_pottery_sherd", 695),

    BLAST_FURNACE("minecraft:blast_furnace", -196),

    BLAZE_POWDER("minecraft:blaze_powder", 456),

    BLAZE_ROD("minecraft:blaze_rod", 449),

    BLAZE_SPAWN_EGG("minecraft:blaze_spawn_egg", 483),

    BLEACH("minecraft:bleach", 633),

    BLUE_BUNDLE("minecraft:blue_bundle", 258),

    BLUE_CANDLE("minecraft:blue_candle", -424),

    BLUE_CANDLE_CAKE("minecraft:blue_candle_cake", -441),

    BLUE_CARPET("minecraft:blue_carpet", -607),

    BLUE_CONCRETE("minecraft:blue_concrete", -638),

    BLUE_CONCRETE_POWDER("minecraft:blue_concrete_powder", -719),

    BLUE_DYE("minecraft:blue_dye", 425),

    BLUE_EGG("minecraft:blue_egg", 748),

    BLUE_GLAZED_TERRACOTTA("minecraft:blue_glazed_terracotta", 231),

    BLUE_HARNESS("minecraft:blue_harness", 752),

    BLUE_ICE("minecraft:blue_ice", -11),

    BLUE_ORCHID("minecraft:blue_orchid", -830),

    BLUE_SHULKER_BOX("minecraft:blue_shulker_box", -623),

    BLUE_STAINED_GLASS("minecraft:blue_stained_glass", -683),

    BLUE_STAINED_GLASS_PANE("minecraft:blue_stained_glass_pane", -653),

    BLUE_TERRACOTTA("minecraft:blue_terracotta", -734),

    BLUE_WOOL("minecraft:blue_wool", -563),

    BOARD("minecraft:board", 629),

    BOAT("minecraft:boat", 823),

    BOGGED_SPAWN_EGG("minecraft:bogged_spawn_egg", 490),

    BOLT_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:bolt_armor_trim_smithing_template", 734),

    BONE("minecraft:bone", 441),

    BONE_BLOCK("minecraft:bone_block", 216),

    BONE_MEAL("minecraft:bone_meal", 437),

    BOOK("minecraft:book", 413),

    BOOKSHELF("minecraft:bookshelf", 47),

    BORDER_BLOCK("minecraft:border_block", 212),

    BORDURE_INDENTED_BANNER_PATTERN("minecraft:bordure_indented_banner_pattern", 619),

    BOW("minecraft:bow", 324),

    BOWL("minecraft:bowl", 346),

    BRAIN_CORAL("minecraft:brain_coral", -581),

    BRAIN_CORAL_BLOCK("minecraft:brain_coral_block", -849),

    BRAIN_CORAL_FAN("minecraft:brain_coral_fan", -840),

    BRAIN_CORAL_WALL_FAN("minecraft:brain_coral_wall_fan", -904),

    BREAD("minecraft:bread", 283),

    BREEZE_ROD("minecraft:breeze_rod", 274),

    BREEZE_SPAWN_EGG("minecraft:breeze_spawn_egg", 529),

    BREWER_POTTERY_SHERD("minecraft:brewer_pottery_sherd", 696),

    BREWING_STAND("minecraft:brewing_stand", 458),

    BRICK("minecraft:brick", 409),

    BRICK_BLOCK("minecraft:brick_block", 45),

    BRICK_DOUBLE_SLAB("minecraft:brick_double_slab", -880),

    BRICK_SLAB("minecraft:brick_slab", -874),

    BRICK_STAIRS("minecraft:brick_stairs", 108),

    BRICK_WALL("minecraft:brick_wall", -976),

    BROWN_BUNDLE("minecraft:brown_bundle", 259),

    BROWN_CANDLE("minecraft:brown_candle", -425),

    BROWN_CANDLE_CAKE("minecraft:brown_candle_cake", -442),

    BROWN_CARPET("minecraft:brown_carpet", -608),

    BROWN_CONCRETE("minecraft:brown_concrete", -639),

    BROWN_CONCRETE_POWDER("minecraft:brown_concrete_powder", -720),

    BROWN_DYE("minecraft:brown_dye", 424),

    BROWN_EGG("minecraft:brown_egg", 749),

    BROWN_GLAZED_TERRACOTTA("minecraft:brown_glazed_terracotta", 232),

    BROWN_HARNESS("minecraft:brown_harness", 753),

    BROWN_MUSHROOM("minecraft:brown_mushroom", 39),

    BROWN_MUSHROOM_BLOCK("minecraft:brown_mushroom_block", 99),

    BROWN_SHULKER_BOX("minecraft:brown_shulker_box", -624),

    BROWN_STAINED_GLASS("minecraft:brown_stained_glass", -684),

    BROWN_STAINED_GLASS_PANE("minecraft:brown_stained_glass_pane", -654),

    BROWN_TERRACOTTA("minecraft:brown_terracotta", -735),

    BROWN_WOOL("minecraft:brown_wool", -555),

    BRUSH("minecraft:brush", 715),

    BUBBLE_COLUMN("minecraft:bubble_column", -160),

    BUBBLE_CORAL("minecraft:bubble_coral", -582),

    BUBBLE_CORAL_BLOCK("minecraft:bubble_coral_block", -850),

    BUBBLE_CORAL_FAN("minecraft:bubble_coral_fan", -841),

    BUBBLE_CORAL_WALL_FAN("minecraft:bubble_coral_wall_fan", -136),

    BUCKET("minecraft:bucket", 385),

    BUDDING_AMETHYST("minecraft:budding_amethyst", -328),

    BUNDLE("minecraft:bundle", 260),

    BURN_POTTERY_SHERD("minecraft:burn_pottery_sherd", 697),

    BUSH("minecraft:bush", -1023),

    CACTUS("minecraft:cactus", 81),

    CACTUS_FLOWER("minecraft:cactus_flower", -1030),

    CAKE("minecraft:cake", 443),

    CALCITE("minecraft:calcite", -326),

    CALIBRATED_SCULK_SENSOR("minecraft:calibrated_sculk_sensor", -580),

    CAMEL_SPAWN_EGG("minecraft:camel_spawn_egg", 691),

    CAMERA("minecraft:camera", 630),

    CAMPFIRE("minecraft:campfire", 624),

    CANDLE("minecraft:candle", -412),

    CANDLE_CAKE("minecraft:candle_cake", -429),

    CARPET("minecraft:carpet", 780),

    CARROT("minecraft:carrot", 301),

    CARROT_ON_A_STICK("minecraft:carrot_on_a_stick", 550),

    CARROTS("minecraft:carrots", 141),

    CARTOGRAPHY_TABLE("minecraft:cartography_table", -200),

    CARVED_PUMPKIN("minecraft:carved_pumpkin", -155),

    CAT_SPAWN_EGG("minecraft:cat_spawn_egg", 516),

    CAULDRON("minecraft:cauldron", 459),

    CAVE_SPIDER_SPAWN_EGG("minecraft:cave_spider_spawn_egg", 484),

    CAVE_VINES("minecraft:cave_vines", -322),

    CAVE_VINES_BODY_WITH_BERRIES("minecraft:cave_vines_body_with_berries", -375),

    CAVE_VINES_HEAD_WITH_BERRIES("minecraft:cave_vines_head_with_berries", -376),

    CHAIN_COMMAND_BLOCK("minecraft:chain_command_block", 189),

    CHAINMAIL_BOOTS("minecraft:chainmail_boots", 367),

    CHAINMAIL_CHESTPLATE("minecraft:chainmail_chestplate", 365),

    CHAINMAIL_HELMET("minecraft:chainmail_helmet", 364),

    CHAINMAIL_LEGGINGS("minecraft:chainmail_leggings", 366),

    CHALKBOARD("minecraft:chalkboard", 230),

    CHARCOAL("minecraft:charcoal", 327),

    CHEMICAL_HEAT("minecraft:chemical_heat", 192),

    CHEMISTRY_TABLE("minecraft:chemistry_table", 817),

    CHERRY_BOAT("minecraft:cherry_boat", 685),

    CHERRY_BUTTON("minecraft:cherry_button", -530),

    CHERRY_CHEST_BOAT("minecraft:cherry_chest_boat", 686),

    CHERRY_DOOR("minecraft:cherry_door", -531),

    CHERRY_DOUBLE_SLAB("minecraft:cherry_double_slab", -540),

    CHERRY_FENCE("minecraft:cherry_fence", -532),

    CHERRY_FENCE_GATE("minecraft:cherry_fence_gate", -533),

    CHERRY_HANGING_SIGN("minecraft:cherry_hanging_sign", -534),

    CHERRY_LEAVES("minecraft:cherry_leaves", -548),

    CHERRY_LOG("minecraft:cherry_log", -536),

    CHERRY_PLANKS("minecraft:cherry_planks", -537),

    CHERRY_PRESSURE_PLATE("minecraft:cherry_pressure_plate", -538),

    CHERRY_SAPLING("minecraft:cherry_sapling", -547),

    CHERRY_SHELF("minecraft:cherry_shelf", -1054),

    CHERRY_SIGN("minecraft:cherry_sign", 687),

    CHERRY_SLAB("minecraft:cherry_slab", -539),

    CHERRY_STAIRS("minecraft:cherry_stairs", -541),

    CHERRY_STANDING_SIGN("minecraft:cherry_standing_sign", -542),

    CHERRY_TRAPDOOR("minecraft:cherry_trapdoor", -543),

    CHERRY_WALL_SIGN("minecraft:cherry_wall_sign", -544),

    CHERRY_WOOD("minecraft:cherry_wood", -546),

    CHEST("minecraft:chest", 54),

    CHEST_BOAT("minecraft:chest_boat", 681),

    CHEST_MINECART("minecraft:chest_minecart", 415),

    CHICKEN("minecraft:chicken", 297),

    CHICKEN_SPAWN_EGG("minecraft:chicken_spawn_egg", 462),

    CHIPPED_ANVIL("minecraft:chipped_anvil", -959),

    CHISELED_BOOKSHELF("minecraft:chiseled_bookshelf", -526),

    CHISELED_COPPER("minecraft:chiseled_copper", -760),

    CHISELED_DEEPSLATE("minecraft:chiseled_deepslate", -395),

    CHISELED_NETHER_BRICKS("minecraft:chiseled_nether_bricks", -302),

    CHISELED_POLISHED_BLACKSTONE("minecraft:chiseled_polished_blackstone", -279),

    CHISELED_QUARTZ_BLOCK("minecraft:chiseled_quartz_block", -953),

    CHISELED_RED_SANDSTONE("minecraft:chiseled_red_sandstone", -956),

    CHISELED_RESIN_BRICKS("minecraft:chiseled_resin_bricks", -1020),

    CHISELED_SANDSTONE("minecraft:chiseled_sandstone", -944),

    CHISELED_STONE_BRICKS("minecraft:chiseled_stone_bricks", -870),

    CHISELED_TUFF("minecraft:chiseled_tuff", -753),

    CHISELED_TUFF_BRICKS("minecraft:chiseled_tuff_bricks", -759),

    CHORUS_FLOWER("minecraft:chorus_flower", 200),

    CHORUS_FRUIT("minecraft:chorus_fruit", 591),

    CHORUS_PLANT("minecraft:chorus_plant", 240),

    CLAY("minecraft:clay", 82),

    CLAY_BALL("minecraft:clay_ball", 410),

    CLIENT_REQUEST_PLACEHOLDER_BLOCK("minecraft:client_request_placeholder_block", -465),

    CLOCK("minecraft:clock", 419),

    CLOSED_EYEBLOSSOM("minecraft:closed_eyeblossom", -1019),

    COAL("minecraft:coal", 326),

    COAL_BLOCK("minecraft:coal_block", 173),

    COAL_ORE("minecraft:coal_ore", 16),

    COARSE_DIRT("minecraft:coarse_dirt", -962),

    COAST_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:coast_armor_trim_smithing_template", 719),

    COBBLED_DEEPSLATE("minecraft:cobbled_deepslate", -379),

    COBBLED_DEEPSLATE_DOUBLE_SLAB("minecraft:cobbled_deepslate_double_slab", -396),

    COBBLED_DEEPSLATE_SLAB("minecraft:cobbled_deepslate_slab", -380),

    COBBLED_DEEPSLATE_STAIRS("minecraft:cobbled_deepslate_stairs", -381),

    COBBLED_DEEPSLATE_WALL("minecraft:cobbled_deepslate_wall", -382),

    COBBLESTONE("minecraft:cobblestone", 4),

    COBBLESTONE_DOUBLE_SLAB("minecraft:cobblestone_double_slab", -879),

    COBBLESTONE_SLAB("minecraft:cobblestone_slab", -873),

    COBBLESTONE_WALL("minecraft:cobblestone_wall", 139),

    COCOA("minecraft:cocoa", 127),

    COCOA_BEANS("minecraft:cocoa_beans", 438),

    COD("minecraft:cod", 286),

    COD_BUCKET("minecraft:cod_bucket", 389),

    COD_SPAWN_EGG("minecraft:cod_spawn_egg", 508),

    COLORED_TORCH_BLUE("minecraft:colored_torch_blue", 204),

    COLORED_TORCH_BP("minecraft:colored_torch_bp", 821),

    COLORED_TORCH_GREEN("minecraft:colored_torch_green", -963),

    COLORED_TORCH_PURPLE("minecraft:colored_torch_purple", -964),

    COLORED_TORCH_RED("minecraft:colored_torch_red", 202),

    COLORED_TORCH_RG("minecraft:colored_torch_rg", 820),

    COMMAND_BLOCK("minecraft:command_block", 137),

    COMMAND_BLOCK_MINECART("minecraft:command_block_minecart", 596),

    COMPARATOR("minecraft:comparator", 555),

    COMPASS("minecraft:compass", 417),

    COMPOSTER("minecraft:composter", -213),

    COMPOUND("minecraft:compound", 631),

    COMPOUND_CREATOR("minecraft:compound_creator", 238),

    CONCRETE("minecraft:concrete", 806),

    CONCRETE_POWDER("minecraft:concrete_powder", 807),

    CONDUIT("minecraft:conduit", -157),

    COOKED_BEEF("minecraft:cooked_beef", 296),

    COOKED_CHICKEN("minecraft:cooked_chicken", 298),

    COOKED_COD("minecraft:cooked_cod", 290),

    COOKED_MUTTON("minecraft:cooked_mutton", 584),

    COOKED_PORKCHOP("minecraft:cooked_porkchop", 285),

    COOKED_RABBIT("minecraft:cooked_rabbit", 311),

    COOKED_SALMON("minecraft:cooked_salmon", 291),

    COOKIE("minecraft:cookie", 293),

    COPPER_AXE("minecraft:copper_axe", 771),

    COPPER_BARS("minecraft:copper_bars", -1066),

    COPPER_BLOCK("minecraft:copper_block", -340),

    COPPER_BOOTS("minecraft:copper_boots", 776),

    COPPER_BULB("minecraft:copper_bulb", -776),

    COPPER_CHAIN("minecraft:copper_chain", -1074),

    COPPER_CHEST("minecraft:copper_chest", -1031),

    COPPER_CHESTPLATE("minecraft:copper_chestplate", 774),

    COPPER_DOOR("minecraft:copper_door", -784),

    COPPER_GOLEM_SPAWN_EGG("minecraft:copper_golem_spawn_egg", 767),

    COPPER_GOLEM_STATUE("minecraft:copper_golem_statue", -1039),

    COPPER_GRATE("minecraft:copper_grate", -768),

    COPPER_HELMET("minecraft:copper_helmet", 773),

    COPPER_HOE("minecraft:copper_hoe", 772),

    COPPER_HORSE_ARMOR("minecraft:copper_horse_armor", 778),

    COPPER_INGOT("minecraft:copper_ingot", 538),

    COPPER_LANTERN("minecraft:copper_lantern", -1083),

    COPPER_LEGGINGS("minecraft:copper_leggings", 775),

    COPPER_NUGGET("minecraft:copper_nugget", 777),

    COPPER_ORE("minecraft:copper_ore", -311),

    COPPER_PICKAXE("minecraft:copper_pickaxe", 770),

    COPPER_SHOVEL("minecraft:copper_shovel", 769),

    COPPER_SWORD("minecraft:copper_sword", 768),

    COPPER_TORCH("minecraft:copper_torch", -1082),

    COPPER_TRAPDOOR("minecraft:copper_trapdoor", -792),

    CORAL("minecraft:coral", 802),

    CORAL_BLOCK("minecraft:coral_block", 784),

    CORAL_FAN("minecraft:coral_fan", 793),

    CORAL_FAN_DEAD("minecraft:coral_fan_dead", 794),

    CORNFLOWER("minecraft:cornflower", -838),

    COW_SPAWN_EGG("minecraft:cow_spawn_egg", 463),

    CRACKED_DEEPSLATE_BRICKS("minecraft:cracked_deepslate_bricks", -410),

    CRACKED_DEEPSLATE_TILES("minecraft:cracked_deepslate_tiles", -409),

    CRACKED_NETHER_BRICKS("minecraft:cracked_nether_bricks", -303),

    CRACKED_POLISHED_BLACKSTONE_BRICKS("minecraft:cracked_polished_blackstone_bricks", -280),

    CRACKED_STONE_BRICKS("minecraft:cracked_stone_bricks", -869),

    CRAFTER("minecraft:crafter", -313),

    CRAFTING_TABLE("minecraft:crafting_table", 58),

    CREAKING_HEART("minecraft:creaking_heart", -1012),

    CREAKING_SPAWN_EGG("minecraft:creaking_spawn_egg", 746),

    CREEPER_BANNER_PATTERN("minecraft:creeper_banner_pattern", 615),

    CREEPER_HEAD("minecraft:creeper_head", -968),

    CREEPER_SPAWN_EGG("minecraft:creeper_spawn_egg", 468),

    CRIMSON_BUTTON("minecraft:crimson_button", -260),

    CRIMSON_DOOR("minecraft:crimson_door", 653),

    CRIMSON_DOUBLE_SLAB("minecraft:crimson_double_slab", -266),

    CRIMSON_FENCE("minecraft:crimson_fence", -256),

    CRIMSON_FENCE_GATE("minecraft:crimson_fence_gate", -258),

    CRIMSON_FUNGUS("minecraft:crimson_fungus", -228),

    CRIMSON_HANGING_SIGN("minecraft:crimson_hanging_sign", -506),

    CRIMSON_HYPHAE("minecraft:crimson_hyphae", -299),

    CRIMSON_NYLIUM("minecraft:crimson_nylium", -232),

    CRIMSON_PLANKS("minecraft:crimson_planks", -242),

    CRIMSON_PRESSURE_PLATE("minecraft:crimson_pressure_plate", -262),

    CRIMSON_ROOTS("minecraft:crimson_roots", -223),

    CRIMSON_SHELF("minecraft:crimson_shelf", -1057),

    CRIMSON_SIGN("minecraft:crimson_sign", 651),

    CRIMSON_SLAB("minecraft:crimson_slab", -264),

    CRIMSON_STAIRS("minecraft:crimson_stairs", -254),

    CRIMSON_STANDING_SIGN("minecraft:crimson_standing_sign", -250),

    CRIMSON_STEM("minecraft:crimson_stem", -225),

    CRIMSON_TRAPDOOR("minecraft:crimson_trapdoor", -246),

    CRIMSON_WALL_SIGN("minecraft:crimson_wall_sign", -252),

    CROSSBOW("minecraft:crossbow", 608),

    CRYING_OBSIDIAN("minecraft:crying_obsidian", -289),

    CUT_COPPER("minecraft:cut_copper", -347),

    CUT_COPPER_SLAB("minecraft:cut_copper_slab", -361),

    CUT_COPPER_STAIRS("minecraft:cut_copper_stairs", -354),

    CUT_RED_SANDSTONE("minecraft:cut_red_sandstone", -957),

    CUT_RED_SANDSTONE_DOUBLE_SLAB("minecraft:cut_red_sandstone_double_slab", -928),

    CUT_RED_SANDSTONE_SLAB("minecraft:cut_red_sandstone_slab", -901),

    CUT_SANDSTONE("minecraft:cut_sandstone", -945),

    CUT_SANDSTONE_DOUBLE_SLAB("minecraft:cut_sandstone_double_slab", -927),

    CUT_SANDSTONE_SLAB("minecraft:cut_sandstone_slab", -900),

    CYAN_BUNDLE("minecraft:cyan_bundle", 261),

    CYAN_CANDLE("minecraft:cyan_candle", -422),

    CYAN_CANDLE_CAKE("minecraft:cyan_candle_cake", -439),

    CYAN_CARPET("minecraft:cyan_carpet", -605),

    CYAN_CONCRETE("minecraft:cyan_concrete", -636),

    CYAN_CONCRETE_POWDER("minecraft:cyan_concrete_powder", -717),

    CYAN_DYE("minecraft:cyan_dye", 427),

    CYAN_GLAZED_TERRACOTTA("minecraft:cyan_glazed_terracotta", 229),

    CYAN_HARNESS("minecraft:cyan_harness", 754),

    CYAN_SHULKER_BOX("minecraft:cyan_shulker_box", -621),

    CYAN_STAINED_GLASS("minecraft:cyan_stained_glass", -681),

    CYAN_STAINED_GLASS_PANE("minecraft:cyan_stained_glass_pane", -651),

    CYAN_TERRACOTTA("minecraft:cyan_terracotta", -732),

    CYAN_WOOL("minecraft:cyan_wool", -561),

    DAMAGED_ANVIL("minecraft:damaged_anvil", -960),

    DANDELION("minecraft:dandelion", 37),

    DANGER_POTTERY_SHERD("minecraft:danger_pottery_sherd", 698),

    DARK_OAK_BOAT("minecraft:dark_oak_boat", 406),

    DARK_OAK_BUTTON("minecraft:dark_oak_button", -142),

    DARK_OAK_CHEST_BOAT("minecraft:dark_oak_chest_boat", 679),

    DARK_OAK_DOOR("minecraft:dark_oak_door", 590),

    DARK_OAK_DOUBLE_SLAB("minecraft:dark_oak_double_slab", -813),

    DARK_OAK_FENCE("minecraft:dark_oak_fence", -577),

    DARK_OAK_FENCE_GATE("minecraft:dark_oak_fence_gate", 186),

    DARK_OAK_HANGING_SIGN("minecraft:dark_oak_hanging_sign", -505),

    DARK_OAK_LEAVES("minecraft:dark_oak_leaves", -803),

    DARK_OAK_LOG("minecraft:dark_oak_log", -572),

    DARK_OAK_PLANKS("minecraft:dark_oak_planks", -743),

    DARK_OAK_PRESSURE_PLATE("minecraft:dark_oak_pressure_plate", -152),

    DARK_OAK_SAPLING("minecraft:dark_oak_sapling", -829),

    DARK_OAK_SHELF("minecraft:dark_oak_shelf", -1052),

    DARK_OAK_SIGN("minecraft:dark_oak_sign", 613),

    DARK_OAK_SLAB("minecraft:dark_oak_slab", -808),

    DARK_OAK_STAIRS("minecraft:dark_oak_stairs", 164),

    DARK_OAK_TRAPDOOR("minecraft:dark_oak_trapdoor", -147),

    DARK_OAK_WOOD("minecraft:dark_oak_wood", -818),

    DARK_PRISMARINE("minecraft:dark_prismarine", -947),

    DARK_PRISMARINE_DOUBLE_SLAB("minecraft:dark_prismarine_double_slab", -913),

    DARK_PRISMARINE_SLAB("minecraft:dark_prismarine_slab", -886),

    DARK_PRISMARINE_STAIRS("minecraft:dark_prismarine_stairs", -3),

    DARKOAK_STANDING_SIGN("minecraft:darkoak_standing_sign", -192),

    DARKOAK_WALL_SIGN("minecraft:darkoak_wall_sign", -193),

    DAYLIGHT_DETECTOR("minecraft:daylight_detector", 151),

    DAYLIGHT_DETECTOR_INVERTED("minecraft:daylight_detector_inverted", 178),

    DEAD_BRAIN_CORAL("minecraft:dead_brain_coral", -586),

    DEAD_BRAIN_CORAL_BLOCK("minecraft:dead_brain_coral_block", -854),

    DEAD_BRAIN_CORAL_FAN("minecraft:dead_brain_coral_fan", -844),

    DEAD_BRAIN_CORAL_WALL_FAN("minecraft:dead_brain_coral_wall_fan", -906),

    DEAD_BUBBLE_CORAL("minecraft:dead_bubble_coral", -587),

    DEAD_BUBBLE_CORAL_BLOCK("minecraft:dead_bubble_coral_block", -855),

    DEAD_BUBBLE_CORAL_FAN("minecraft:dead_bubble_coral_fan", -845),

    DEAD_BUBBLE_CORAL_WALL_FAN("minecraft:dead_bubble_coral_wall_fan", -908),

    DEAD_FIRE_CORAL("minecraft:dead_fire_coral", -588),

    DEAD_FIRE_CORAL_BLOCK("minecraft:dead_fire_coral_block", -856),

    DEAD_FIRE_CORAL_FAN("minecraft:dead_fire_coral_fan", -846),

    DEAD_FIRE_CORAL_WALL_FAN("minecraft:dead_fire_coral_wall_fan", -909),

    DEAD_HORN_CORAL("minecraft:dead_horn_coral", -589),

    DEAD_HORN_CORAL_BLOCK("minecraft:dead_horn_coral_block", -857),

    DEAD_HORN_CORAL_FAN("minecraft:dead_horn_coral_fan", -847),

    DEAD_HORN_CORAL_WALL_FAN("minecraft:dead_horn_coral_wall_fan", -910),

    DEAD_TUBE_CORAL("minecraft:dead_tube_coral", -585),

    DEAD_TUBE_CORAL_BLOCK("minecraft:dead_tube_coral_block", -853),

    DEAD_TUBE_CORAL_FAN("minecraft:dead_tube_coral_fan", -134),

    DEAD_TUBE_CORAL_WALL_FAN("minecraft:dead_tube_coral_wall_fan", -905),

    DEADBUSH("minecraft:deadbush", 32),

    DECORATED_POT("minecraft:decorated_pot", -551),

    DEEPSLATE("minecraft:deepslate", -378),

    DEEPSLATE_BRICK_DOUBLE_SLAB("minecraft:deepslate_brick_double_slab", -399),

    DEEPSLATE_BRICK_SLAB("minecraft:deepslate_brick_slab", -392),

    DEEPSLATE_BRICK_STAIRS("minecraft:deepslate_brick_stairs", -393),

    DEEPSLATE_BRICK_WALL("minecraft:deepslate_brick_wall", -394),

    DEEPSLATE_BRICKS("minecraft:deepslate_bricks", -391),

    DEEPSLATE_COAL_ORE("minecraft:deepslate_coal_ore", -406),

    DEEPSLATE_COPPER_ORE("minecraft:deepslate_copper_ore", -408),

    DEEPSLATE_DIAMOND_ORE("minecraft:deepslate_diamond_ore", -405),

    DEEPSLATE_EMERALD_ORE("minecraft:deepslate_emerald_ore", -407),

    DEEPSLATE_GOLD_ORE("minecraft:deepslate_gold_ore", -402),

    DEEPSLATE_IRON_ORE("minecraft:deepslate_iron_ore", -401),

    DEEPSLATE_LAPIS_ORE("minecraft:deepslate_lapis_ore", -400),

    DEEPSLATE_REDSTONE_ORE("minecraft:deepslate_redstone_ore", -403),

    DEEPSLATE_TILE_DOUBLE_SLAB("minecraft:deepslate_tile_double_slab", -398),

    DEEPSLATE_TILE_SLAB("minecraft:deepslate_tile_slab", -388),

    DEEPSLATE_TILE_STAIRS("minecraft:deepslate_tile_stairs", -389),

    DEEPSLATE_TILE_WALL("minecraft:deepslate_tile_wall", -390),

    DEEPSLATE_TILES("minecraft:deepslate_tiles", -387),

    DENY("minecraft:deny", 211),

    DEPRECATED_ANVIL("minecraft:deprecated_anvil", -961),

    DEPRECATED_PURPUR_BLOCK_1("minecraft:deprecated_purpur_block_1", -950),

    DEPRECATED_PURPUR_BLOCK_2("minecraft:deprecated_purpur_block_2", -952),

    DETECTOR_RAIL("minecraft:detector_rail", 28),

    DIAMOND("minecraft:diamond", 328),

    DIAMOND_AXE("minecraft:diamond_axe", 343),

    DIAMOND_BLOCK("minecraft:diamond_block", 57),

    DIAMOND_BOOTS("minecraft:diamond_boots", 375),

    DIAMOND_CHESTPLATE("minecraft:diamond_chestplate", 373),

    DIAMOND_HELMET("minecraft:diamond_helmet", 372),

    DIAMOND_HOE("minecraft:diamond_hoe", 357),

    DIAMOND_HORSE_ARMOR("minecraft:diamond_horse_armor", 566),

    DIAMOND_LEGGINGS("minecraft:diamond_leggings", 374),

    DIAMOND_ORE("minecraft:diamond_ore", 56),

    DIAMOND_PICKAXE("minecraft:diamond_pickaxe", 342),

    DIAMOND_SHOVEL("minecraft:diamond_shovel", 341),

    DIAMOND_SWORD("minecraft:diamond_sword", 340),

    DIORITE("minecraft:diorite", -592),

    DIORITE_DOUBLE_SLAB("minecraft:diorite_double_slab", -921),

    DIORITE_SLAB("minecraft:diorite_slab", -894),

    DIORITE_STAIRS("minecraft:diorite_stairs", -170),

    DIORITE_WALL("minecraft:diorite_wall", -973),

    DIRT("minecraft:dirt", 3),

    DIRT_WITH_ROOTS("minecraft:dirt_with_roots", -318),

    DISC_FRAGMENT_5("minecraft:disc_fragment_5", 673),

    DISPENSER("minecraft:dispenser", 23),

    DOLPHIN_SPAWN_EGG("minecraft:dolphin_spawn_egg", 512),

    DONKEY_SPAWN_EGG("minecraft:donkey_spawn_egg", 493),

    DOUBLE_CUT_COPPER_SLAB("minecraft:double_cut_copper_slab", -368),

    DOUBLE_PLANT("minecraft:double_plant", 800),

    DOUBLE_STONE_BLOCK_SLAB("minecraft:double_stone_block_slab", 789),

    DOUBLE_STONE_BLOCK_SLAB2("minecraft:double_stone_block_slab2", 790),

    DOUBLE_STONE_BLOCK_SLAB3("minecraft:double_stone_block_slab3", 791),

    DOUBLE_STONE_BLOCK_SLAB4("minecraft:double_stone_block_slab4", 792),

    DRAGON_BREATH("minecraft:dragon_breath", 593),

    DRAGON_EGG("minecraft:dragon_egg", 122),

    DRAGON_HEAD("minecraft:dragon_head", -969),

    DRIED_GHAST("minecraft:dried_ghast", -1027),

    DRIED_KELP("minecraft:dried_kelp", 292),

    DRIED_KELP_BLOCK("minecraft:dried_kelp_block", -139),

    DRIPSTONE_BLOCK("minecraft:dripstone_block", -317),

    DROPPER("minecraft:dropper", 125),

    DROWNED_SPAWN_EGG("minecraft:drowned_spawn_egg", 511),

    DUNE_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:dune_armor_trim_smithing_template", 718),

    DYE("minecraft:dye", 824),

    ECHO_SHARD("minecraft:echo_shard", 683),

    EGG("minecraft:egg", 416),

    ELDER_GUARDIAN_SPAWN_EGG("minecraft:elder_guardian_spawn_egg", 499),

    ELEMENT_0("minecraft:element_0", 36),

    ELEMENT_1("minecraft:element_1", -12),

    ELEMENT_10("minecraft:element_10", -21),

    ELEMENT_100("minecraft:element_100", -111),

    ELEMENT_101("minecraft:element_101", -112),

    ELEMENT_102("minecraft:element_102", -113),

    ELEMENT_103("minecraft:element_103", -114),

    ELEMENT_104("minecraft:element_104", -115),

    ELEMENT_105("minecraft:element_105", -116),

    ELEMENT_106("minecraft:element_106", -117),

    ELEMENT_107("minecraft:element_107", -118),

    ELEMENT_108("minecraft:element_108", -119),

    ELEMENT_109("minecraft:element_109", -120),

    ELEMENT_11("minecraft:element_11", -22),

    ELEMENT_110("minecraft:element_110", -121),

    ELEMENT_111("minecraft:element_111", -122),

    ELEMENT_112("minecraft:element_112", -123),

    ELEMENT_113("minecraft:element_113", -124),

    ELEMENT_114("minecraft:element_114", -125),

    ELEMENT_115("minecraft:element_115", -126),

    ELEMENT_116("minecraft:element_116", -127),

    ELEMENT_117("minecraft:element_117", -128),

    ELEMENT_118("minecraft:element_118", -129),

    ELEMENT_12("minecraft:element_12", -23),

    ELEMENT_13("minecraft:element_13", -24),

    ELEMENT_14("minecraft:element_14", -25),

    ELEMENT_15("minecraft:element_15", -26),

    ELEMENT_16("minecraft:element_16", -27),

    ELEMENT_17("minecraft:element_17", -28),

    ELEMENT_18("minecraft:element_18", -29),

    ELEMENT_19("minecraft:element_19", -30),

    ELEMENT_2("minecraft:element_2", -13),

    ELEMENT_20("minecraft:element_20", -31),

    ELEMENT_21("minecraft:element_21", -32),

    ELEMENT_22("minecraft:element_22", -33),

    ELEMENT_23("minecraft:element_23", -34),

    ELEMENT_24("minecraft:element_24", -35),

    ELEMENT_25("minecraft:element_25", -36),

    ELEMENT_26("minecraft:element_26", -37),

    ELEMENT_27("minecraft:element_27", -38),

    ELEMENT_28("minecraft:element_28", -39),

    ELEMENT_29("minecraft:element_29", -40),

    ELEMENT_3("minecraft:element_3", -14),

    ELEMENT_30("minecraft:element_30", -41),

    ELEMENT_31("minecraft:element_31", -42),

    ELEMENT_32("minecraft:element_32", -43),

    ELEMENT_33("minecraft:element_33", -44),

    ELEMENT_34("minecraft:element_34", -45),

    ELEMENT_35("minecraft:element_35", -46),

    ELEMENT_36("minecraft:element_36", -47),

    ELEMENT_37("minecraft:element_37", -48),

    ELEMENT_38("minecraft:element_38", -49),

    ELEMENT_39("minecraft:element_39", -50),

    ELEMENT_4("minecraft:element_4", -15),

    ELEMENT_40("minecraft:element_40", -51),

    ELEMENT_41("minecraft:element_41", -52),

    ELEMENT_42("minecraft:element_42", -53),

    ELEMENT_43("minecraft:element_43", -54),

    ELEMENT_44("minecraft:element_44", -55),

    ELEMENT_45("minecraft:element_45", -56),

    ELEMENT_46("minecraft:element_46", -57),

    ELEMENT_47("minecraft:element_47", -58),

    ELEMENT_48("minecraft:element_48", -59),

    ELEMENT_49("minecraft:element_49", -60),

    ELEMENT_5("minecraft:element_5", -16),

    ELEMENT_50("minecraft:element_50", -61),

    ELEMENT_51("minecraft:element_51", -62),

    ELEMENT_52("minecraft:element_52", -63),

    ELEMENT_53("minecraft:element_53", -64),

    ELEMENT_54("minecraft:element_54", -65),

    ELEMENT_55("minecraft:element_55", -66),

    ELEMENT_56("minecraft:element_56", -67),

    ELEMENT_57("minecraft:element_57", -68),

    ELEMENT_58("minecraft:element_58", -69),

    ELEMENT_59("minecraft:element_59", -70),

    ELEMENT_6("minecraft:element_6", -17),

    ELEMENT_60("minecraft:element_60", -71),

    ELEMENT_61("minecraft:element_61", -72),

    ELEMENT_62("minecraft:element_62", -73),

    ELEMENT_63("minecraft:element_63", -74),

    ELEMENT_64("minecraft:element_64", -75),

    ELEMENT_65("minecraft:element_65", -76),

    ELEMENT_66("minecraft:element_66", -77),

    ELEMENT_67("minecraft:element_67", -78),

    ELEMENT_68("minecraft:element_68", -79),

    ELEMENT_69("minecraft:element_69", -80),

    ELEMENT_7("minecraft:element_7", -18),

    ELEMENT_70("minecraft:element_70", -81),

    ELEMENT_71("minecraft:element_71", -82),

    ELEMENT_72("minecraft:element_72", -83),

    ELEMENT_73("minecraft:element_73", -84),

    ELEMENT_74("minecraft:element_74", -85),

    ELEMENT_75("minecraft:element_75", -86),

    ELEMENT_76("minecraft:element_76", -87),

    ELEMENT_77("minecraft:element_77", -88),

    ELEMENT_78("minecraft:element_78", -89),

    ELEMENT_79("minecraft:element_79", -90),

    ELEMENT_8("minecraft:element_8", -19),

    ELEMENT_80("minecraft:element_80", -91),

    ELEMENT_81("minecraft:element_81", -92),

    ELEMENT_82("minecraft:element_82", -93),

    ELEMENT_83("minecraft:element_83", -94),

    ELEMENT_84("minecraft:element_84", -95),

    ELEMENT_85("minecraft:element_85", -96),

    ELEMENT_86("minecraft:element_86", -97),

    ELEMENT_87("minecraft:element_87", -98),

    ELEMENT_88("minecraft:element_88", -99),

    ELEMENT_89("minecraft:element_89", -100),

    ELEMENT_9("minecraft:element_9", -20),

    ELEMENT_90("minecraft:element_90", -101),

    ELEMENT_91("minecraft:element_91", -102),

    ELEMENT_92("minecraft:element_92", -103),

    ELEMENT_93("minecraft:element_93", -104),

    ELEMENT_94("minecraft:element_94", -105),

    ELEMENT_95("minecraft:element_95", -106),

    ELEMENT_96("minecraft:element_96", -107),

    ELEMENT_97("minecraft:element_97", -108),

    ELEMENT_98("minecraft:element_98", -109),

    ELEMENT_99("minecraft:element_99", -110),

    ELEMENT_CONSTRUCTOR("minecraft:element_constructor", -987),

    ELYTRA("minecraft:elytra", 597),

    EMERALD("minecraft:emerald", 546),

    EMERALD_BLOCK("minecraft:emerald_block", 133),

    EMERALD_ORE("minecraft:emerald_ore", 129),

    EMPTY_MAP("minecraft:empty_map", 549),

    ENCHANTED_BOOK("minecraft:enchanted_book", 554),

    ENCHANTED_GOLDEN_APPLE("minecraft:enchanted_golden_apple", 281),

    ENCHANTING_TABLE("minecraft:enchanting_table", 116),

    END_BRICK_STAIRS("minecraft:end_brick_stairs", -178),

    END_BRICKS("minecraft:end_bricks", 206),

    END_CRYSTAL("minecraft:end_crystal", 827),

    END_GATEWAY("minecraft:end_gateway", 209),

    END_PORTAL("minecraft:end_portal", 119),

    END_PORTAL_FRAME("minecraft:end_portal_frame", 120),

    END_ROD("minecraft:end_rod", 208),

    END_STONE("minecraft:end_stone", 121),

    END_STONE_BRICK_DOUBLE_SLAB("minecraft:end_stone_brick_double_slab", -167),

    END_STONE_BRICK_SLAB("minecraft:end_stone_brick_slab", -162),

    END_STONE_BRICK_WALL("minecraft:end_stone_brick_wall", -980),

    ENDER_CHEST("minecraft:ender_chest", 130),

    ENDER_DRAGON_SPAWN_EGG("minecraft:ender_dragon_spawn_egg", 535),

    ENDER_EYE("minecraft:ender_eye", 460),

    ENDER_PEARL("minecraft:ender_pearl", 448),

    ENDERMAN_SPAWN_EGG("minecraft:enderman_spawn_egg", 469),

    ENDERMITE_SPAWN_EGG("minecraft:endermite_spawn_egg", 487),

    EVOKER_SPAWN_EGG("minecraft:evoker_spawn_egg", 503),

    EXPERIENCE_BOTTLE("minecraft:experience_bottle", 542),

    EXPLORER_POTTERY_SHERD("minecraft:explorer_pottery_sherd", 699),

    EXPOSED_CHISELED_COPPER("minecraft:exposed_chiseled_copper", -761),

    EXPOSED_COPPER("minecraft:exposed_copper", -341),

    EXPOSED_COPPER_BARS("minecraft:exposed_copper_bars", -1067),

    EXPOSED_COPPER_BULB("minecraft:exposed_copper_bulb", -777),

    EXPOSED_COPPER_CHAIN("minecraft:exposed_copper_chain", -1075),

    EXPOSED_COPPER_CHEST("minecraft:exposed_copper_chest", -1032),

    EXPOSED_COPPER_DOOR("minecraft:exposed_copper_door", -785),

    EXPOSED_COPPER_GOLEM_STATUE("minecraft:exposed_copper_golem_statue", -1040),

    EXPOSED_COPPER_GRATE("minecraft:exposed_copper_grate", -769),

    EXPOSED_COPPER_LANTERN("minecraft:exposed_copper_lantern", -1084),

    EXPOSED_COPPER_TRAPDOOR("minecraft:exposed_copper_trapdoor", -793),

    EXPOSED_CUT_COPPER("minecraft:exposed_cut_copper", -348),

    EXPOSED_CUT_COPPER_SLAB("minecraft:exposed_cut_copper_slab", -362),

    EXPOSED_CUT_COPPER_STAIRS("minecraft:exposed_cut_copper_stairs", -355),

    EXPOSED_DOUBLE_CUT_COPPER_SLAB("minecraft:exposed_double_cut_copper_slab", -369),

    EXPOSED_LIGHTNING_ROD("minecraft:exposed_lightning_rod", -1059),

    EYE_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:eye_armor_trim_smithing_template", 722),

    FARMLAND("minecraft:farmland", 60),

    FEATHER("minecraft:feather", 352),

    FENCE("minecraft:fence", 782),

    FENCE_GATE("minecraft:fence_gate", 107),

    FERMENTED_SPIDER_EYE("minecraft:fermented_spider_eye", 455),

    FERN("minecraft:fern", -848),

    FIELD_MASONED_BANNER_PATTERN("minecraft:field_masoned_banner_pattern", 618),

    FILLED_MAP("minecraft:filled_map", 446),

    FIRE("minecraft:fire", 51),

    FIRE_CHARGE("minecraft:fire_charge", 543),

    FIRE_CORAL("minecraft:fire_coral", -583),

    FIRE_CORAL_BLOCK("minecraft:fire_coral_block", -851),

    FIRE_CORAL_FAN("minecraft:fire_coral_fan", -842),

    FIRE_CORAL_WALL_FAN("minecraft:fire_coral_wall_fan", -907),

    FIREFLY_BUSH("minecraft:firefly_bush", -1025),

    FIREWORK_ROCKET("minecraft:firework_rocket", 552),

    FIREWORK_STAR("minecraft:firework_star", 553),

    FISHING_ROD("minecraft:fishing_rod", 418),

    FLETCHING_TABLE("minecraft:fletching_table", -201),

    FLINT("minecraft:flint", 381),

    FLINT_AND_STEEL("minecraft:flint_and_steel", 323),

    FLOW_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:flow_armor_trim_smithing_template", 733),

    FLOW_BANNER_PATTERN("minecraft:flow_banner_pattern", 622),

    FLOW_POTTERY_SHERD("minecraft:flow_pottery_sherd", 700),

    FLOWER_BANNER_PATTERN("minecraft:flower_banner_pattern", 614),

    FLOWER_POT("minecraft:flower_pot", 548),

    FLOWERING_AZALEA("minecraft:flowering_azalea", -338),

    FLOWING_LAVA("minecraft:flowing_lava", 10),

    FLOWING_WATER("minecraft:flowing_water", 8),

    FOX_SPAWN_EGG("minecraft:fox_spawn_egg", 518),

    FRAME("minecraft:frame", 547),

    FRIEND_POTTERY_SHERD("minecraft:friend_pottery_sherd", 701),

    FROG_SPAWN("minecraft:frog_spawn", -468),

    FROG_SPAWN_EGG("minecraft:frog_spawn_egg", 664),

    FROSTED_ICE("minecraft:frosted_ice", 207),

    FURNACE("minecraft:furnace", 61),

    GHAST_SPAWN_EGG("minecraft:ghast_spawn_egg", 481),

    GHAST_TEAR("minecraft:ghast_tear", 451),

    GILDED_BLACKSTONE("minecraft:gilded_blackstone", -281),

    GLASS("minecraft:glass", 20),

    GLASS_BOTTLE("minecraft:glass_bottle", 454),

    GLASS_PANE("minecraft:glass_pane", 102),

    GLISTERING_MELON_SLICE("minecraft:glistering_melon_slice", 461),

    GLOBE_BANNER_PATTERN("minecraft:globe_banner_pattern", 621),

    GLOW_BERRIES("minecraft:glow_berries", 828),

    GLOW_FRAME("minecraft:glow_frame", 659),

    GLOW_INK_SAC("minecraft:glow_ink_sac", 537),

    GLOW_LICHEN("minecraft:glow_lichen", -411),

    GLOW_SQUID_SPAWN_EGG("minecraft:glow_squid_spawn_egg", 532),

    GLOW_STICK("minecraft:glow_stick", 638),

    GLOWINGOBSIDIAN("minecraft:glowingobsidian", 246),

    GLOWSTONE("minecraft:glowstone", 89),

    GLOWSTONE_DUST("minecraft:glowstone_dust", 420),

    GOAT_HORN("minecraft:goat_horn", 663),

    GOAT_SPAWN_EGG("minecraft:goat_spawn_egg", 531),

    GOLD_BLOCK("minecraft:gold_block", 41),

    GOLD_INGOT("minecraft:gold_ingot", 330),

    GOLD_NUGGET("minecraft:gold_nugget", 452),

    GOLD_ORE("minecraft:gold_ore", 14),

    GOLDEN_APPLE("minecraft:golden_apple", 280),

    GOLDEN_AXE("minecraft:golden_axe", 350),

    GOLDEN_BOOTS("minecraft:golden_boots", 379),

    GOLDEN_CARROT("minecraft:golden_carrot", 305),

    GOLDEN_CHESTPLATE("minecraft:golden_chestplate", 377),

    GOLDEN_HELMET("minecraft:golden_helmet", 376),

    GOLDEN_HOE("minecraft:golden_hoe", 358),

    GOLDEN_HORSE_ARMOR("minecraft:golden_horse_armor", 565),

    GOLDEN_LEGGINGS("minecraft:golden_leggings", 378),

    GOLDEN_PICKAXE("minecraft:golden_pickaxe", 349),

    GOLDEN_RAIL("minecraft:golden_rail", 27),

    GOLDEN_SHOVEL("minecraft:golden_shovel", 348),

    GOLDEN_SWORD("minecraft:golden_sword", 347),

    GRANITE("minecraft:granite", -590),

    GRANITE_DOUBLE_SLAB("minecraft:granite_double_slab", -923),

    GRANITE_SLAB("minecraft:granite_slab", -896),

    GRANITE_STAIRS("minecraft:granite_stairs", -169),

    GRANITE_WALL("minecraft:granite_wall", -972),

    GRASS_BLOCK("minecraft:grass_block", 2),

    GRASS_PATH("minecraft:grass_path", 198),

    GRAVEL("minecraft:gravel", 13),

    GRAY_BUNDLE("minecraft:gray_bundle", 262),

    GRAY_CANDLE("minecraft:gray_candle", -420),

    GRAY_CANDLE_CAKE("minecraft:gray_candle_cake", -437),

    GRAY_CARPET("minecraft:gray_carpet", -603),

    GRAY_CONCRETE("minecraft:gray_concrete", -634),

    GRAY_CONCRETE_POWDER("minecraft:gray_concrete_powder", -715),

    GRAY_DYE("minecraft:gray_dye", 429),

    GRAY_GLAZED_TERRACOTTA("minecraft:gray_glazed_terracotta", 227),

    GRAY_HARNESS("minecraft:gray_harness", 755),

    GRAY_SHULKER_BOX("minecraft:gray_shulker_box", -619),

    GRAY_STAINED_GLASS("minecraft:gray_stained_glass", -679),

    GRAY_STAINED_GLASS_PANE("minecraft:gray_stained_glass_pane", -649),

    GRAY_TERRACOTTA("minecraft:gray_terracotta", -730),

    GRAY_WOOL("minecraft:gray_wool", -553),

    GREEN_BUNDLE("minecraft:green_bundle", 263),

    GREEN_CANDLE("minecraft:green_candle", -426),

    GREEN_CANDLE_CAKE("minecraft:green_candle_cake", -443),

    GREEN_CARPET("minecraft:green_carpet", -609),

    GREEN_CONCRETE("minecraft:green_concrete", -640),

    GREEN_CONCRETE_POWDER("minecraft:green_concrete_powder", -721),

    GREEN_DYE("minecraft:green_dye", 423),

    GREEN_GLAZED_TERRACOTTA("minecraft:green_glazed_terracotta", 233),

    GREEN_HARNESS("minecraft:green_harness", 756),

    GREEN_SHULKER_BOX("minecraft:green_shulker_box", -625),

    GREEN_STAINED_GLASS("minecraft:green_stained_glass", -685),

    GREEN_STAINED_GLASS_PANE("minecraft:green_stained_glass_pane", -655),

    GREEN_TERRACOTTA("minecraft:green_terracotta", -736),

    GREEN_WOOL("minecraft:green_wool", -560),

    GRINDSTONE("minecraft:grindstone", -195),

    GUARDIAN_SPAWN_EGG("minecraft:guardian_spawn_egg", 488),

    GUNPOWDER("minecraft:gunpowder", 353),

    GUSTER_BANNER_PATTERN("minecraft:guster_banner_pattern", 623),

    GUSTER_POTTERY_SHERD("minecraft:guster_pottery_sherd", 702),

    HANGING_ROOTS("minecraft:hanging_roots", -319),

    HAPPY_GHAST_SPAWN_EGG("minecraft:happy_ghast_spawn_egg", 750),

    HARD_BLACK_STAINED_GLASS("minecraft:hard_black_stained_glass", -702),

    HARD_BLACK_STAINED_GLASS_PANE("minecraft:hard_black_stained_glass_pane", -672),

    HARD_BLUE_STAINED_GLASS("minecraft:hard_blue_stained_glass", -698),

    HARD_BLUE_STAINED_GLASS_PANE("minecraft:hard_blue_stained_glass_pane", -668),

    HARD_BROWN_STAINED_GLASS("minecraft:hard_brown_stained_glass", -699),

    HARD_BROWN_STAINED_GLASS_PANE("minecraft:hard_brown_stained_glass_pane", -669),

    HARD_CYAN_STAINED_GLASS("minecraft:hard_cyan_stained_glass", -696),

    HARD_CYAN_STAINED_GLASS_PANE("minecraft:hard_cyan_stained_glass_pane", -666),

    HARD_GLASS("minecraft:hard_glass", 253),

    HARD_GLASS_PANE("minecraft:hard_glass_pane", 190),

    HARD_GRAY_STAINED_GLASS("minecraft:hard_gray_stained_glass", -694),

    HARD_GRAY_STAINED_GLASS_PANE("minecraft:hard_gray_stained_glass_pane", -664),

    HARD_GREEN_STAINED_GLASS("minecraft:hard_green_stained_glass", -700),

    HARD_GREEN_STAINED_GLASS_PANE("minecraft:hard_green_stained_glass_pane", -670),

    HARD_LIGHT_BLUE_STAINED_GLASS("minecraft:hard_light_blue_stained_glass", -690),

    HARD_LIGHT_BLUE_STAINED_GLASS_PANE("minecraft:hard_light_blue_stained_glass_pane", -660),

    HARD_LIGHT_GRAY_STAINED_GLASS("minecraft:hard_light_gray_stained_glass", -695),

    HARD_LIGHT_GRAY_STAINED_GLASS_PANE("minecraft:hard_light_gray_stained_glass_pane", -665),

    HARD_LIME_STAINED_GLASS("minecraft:hard_lime_stained_glass", -692),

    HARD_LIME_STAINED_GLASS_PANE("minecraft:hard_lime_stained_glass_pane", -662),

    HARD_MAGENTA_STAINED_GLASS("minecraft:hard_magenta_stained_glass", -689),

    HARD_MAGENTA_STAINED_GLASS_PANE("minecraft:hard_magenta_stained_glass_pane", -659),

    HARD_ORANGE_STAINED_GLASS("minecraft:hard_orange_stained_glass", -688),

    HARD_ORANGE_STAINED_GLASS_PANE("minecraft:hard_orange_stained_glass_pane", -658),

    HARD_PINK_STAINED_GLASS("minecraft:hard_pink_stained_glass", -693),

    HARD_PINK_STAINED_GLASS_PANE("minecraft:hard_pink_stained_glass_pane", -663),

    HARD_PURPLE_STAINED_GLASS("minecraft:hard_purple_stained_glass", -697),

    HARD_PURPLE_STAINED_GLASS_PANE("minecraft:hard_purple_stained_glass_pane", -667),

    HARD_RED_STAINED_GLASS("minecraft:hard_red_stained_glass", -701),

    HARD_RED_STAINED_GLASS_PANE("minecraft:hard_red_stained_glass_pane", -671),

    HARD_STAINED_GLASS("minecraft:hard_stained_glass", 818),

    HARD_STAINED_GLASS_PANE("minecraft:hard_stained_glass_pane", 819),

    HARD_WHITE_STAINED_GLASS("minecraft:hard_white_stained_glass", 254),

    HARD_WHITE_STAINED_GLASS_PANE("minecraft:hard_white_stained_glass_pane", 191),

    HARD_YELLOW_STAINED_GLASS("minecraft:hard_yellow_stained_glass", -691),

    HARD_YELLOW_STAINED_GLASS_PANE("minecraft:hard_yellow_stained_glass_pane", -661),

    HARDENED_CLAY("minecraft:hardened_clay", 172),

    HAY_BLOCK("minecraft:hay_block", 170),

    HEART_OF_THE_SEA("minecraft:heart_of_the_sea", 604),

    HEART_POTTERY_SHERD("minecraft:heart_pottery_sherd", 703),

    HEARTBREAK_POTTERY_SHERD("minecraft:heartbreak_pottery_sherd", 704),

    HEAVY_CORE("minecraft:heavy_core", -316),

    HEAVY_WEIGHTED_PRESSURE_PLATE("minecraft:heavy_weighted_pressure_plate", 148),

    HOGLIN_SPAWN_EGG("minecraft:hoglin_spawn_egg", 524),

    HONEY_BLOCK("minecraft:honey_block", -220),

    HONEY_BOTTLE("minecraft:honey_bottle", 627),

    HONEYCOMB("minecraft:honeycomb", 626),

    HONEYCOMB_BLOCK("minecraft:honeycomb_block", -221),

    HOPPER("minecraft:hopper", 560),

    HOPPER_MINECART("minecraft:hopper_minecart", 559),

    HORN_CORAL("minecraft:horn_coral", -584),

    HORN_CORAL_BLOCK("minecraft:horn_coral_block", -852),

    HORN_CORAL_FAN("minecraft:horn_coral_fan", -843),

    HORN_CORAL_WALL_FAN("minecraft:horn_coral_wall_fan", -137),

    HORSE_SPAWN_EGG("minecraft:horse_spawn_egg", 485),

    HOST_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:host_armor_trim_smithing_template", 732),

    HOWL_POTTERY_SHERD("minecraft:howl_pottery_sherd", 705),

    HUSK_SPAWN_EGG("minecraft:husk_spawn_egg", 491),

    ICE("minecraft:ice", 79),

    ICE_BOMB("minecraft:ice_bomb", 632),

    INFESTED_CHISELED_STONE_BRICKS("minecraft:infested_chiseled_stone_bricks", -862),

    INFESTED_COBBLESTONE("minecraft:infested_cobblestone", -858),

    INFESTED_CRACKED_STONE_BRICKS("minecraft:infested_cracked_stone_bricks", -861),

    INFESTED_DEEPSLATE("minecraft:infested_deepslate", -454),

    INFESTED_MOSSY_STONE_BRICKS("minecraft:infested_mossy_stone_bricks", -860),

    INFESTED_STONE("minecraft:infested_stone", 97),

    INFESTED_STONE_BRICKS("minecraft:infested_stone_bricks", -859),

    INFO_UPDATE("minecraft:info_update", 248),

    INFO_UPDATE2("minecraft:info_update2", 249),

    INK_SAC("minecraft:ink_sac", 439),

    INVISIBLE_BEDROCK("minecraft:invisible_bedrock", 95),

    IRON_AXE("minecraft:iron_axe", 322),

    IRON_BARS("minecraft:iron_bars", 101),

    IRON_BLOCK("minecraft:iron_block", 42),

    IRON_BOOTS("minecraft:iron_boots", 371),

    IRON_CHAIN("minecraft:iron_chain", -286),

    IRON_CHESTPLATE("minecraft:iron_chestplate", 369),

    IRON_DOOR("minecraft:iron_door", 397),

    IRON_GOLEM_SPAWN_EGG("minecraft:iron_golem_spawn_egg", 533),

    IRON_HELMET("minecraft:iron_helmet", 368),

    IRON_HOE("minecraft:iron_hoe", 356),

    IRON_HORSE_ARMOR("minecraft:iron_horse_armor", 564),

    IRON_INGOT("minecraft:iron_ingot", 329),

    IRON_LEGGINGS("minecraft:iron_leggings", 370),

    IRON_NUGGET("minecraft:iron_nugget", 602),

    IRON_ORE("minecraft:iron_ore", 15),

    IRON_PICKAXE("minecraft:iron_pickaxe", 321),

    IRON_SHOVEL("minecraft:iron_shovel", 320),

    IRON_SWORD("minecraft:iron_sword", 331),

    IRON_TRAPDOOR("minecraft:iron_trapdoor", 167),

    ITEM_ACACIA_DOOR("minecraft:item.acacia_door", 196),

    ITEM_BED("minecraft:item.bed", 26),

    ITEM_BEETROOT("minecraft:item.beetroot", 244),

    ITEM_BIRCH_DOOR("minecraft:item.birch_door", 194),

    ITEM_BREWING_STAND("minecraft:item.brewing_stand", 117),

    ITEM_CAKE("minecraft:item.cake", 92),

    ITEM_CAMERA("minecraft:item.camera", 242),

    ITEM_CAMPFIRE("minecraft:item.campfire", -209),

    ITEM_CAULDRON("minecraft:item.cauldron", 118),

    ITEM_CRIMSON_DOOR("minecraft:item.crimson_door", -244),

    ITEM_DARK_OAK_DOOR("minecraft:item.dark_oak_door", 197),

    ITEM_FLOWER_POT("minecraft:item.flower_pot", 140),

    ITEM_FRAME("minecraft:item.frame", 199),

    ITEM_GLOW_FRAME("minecraft:item.glow_frame", -339),

    ITEM_HOPPER("minecraft:item.hopper", 154),

    ITEM_IRON_DOOR("minecraft:item.iron_door", 71),

    ITEM_JUNGLE_DOOR("minecraft:item.jungle_door", 195),

    ITEM_KELP("minecraft:item.kelp", -138),

    ITEM_MANGROVE_DOOR("minecraft:item.mangrove_door", -493),

    ITEM_NETHER_SPROUTS("minecraft:item.nether_sprouts", -238),

    ITEM_NETHER_WART("minecraft:item.nether_wart", 115),

    ITEM_REEDS("minecraft:item.reeds", 83),

    ITEM_SOUL_CAMPFIRE("minecraft:item.soul_campfire", -290),

    ITEM_SPRUCE_DOOR("minecraft:item.spruce_door", 193),

    ITEM_WARPED_DOOR("minecraft:item.warped_door", -245),

    ITEM_WHEAT("minecraft:item.wheat", 59),

    ITEM_WOODEN_DOOR("minecraft:item.wooden_door", 64),

    JIGSAW("minecraft:jigsaw", -211),

    JUKEBOX("minecraft:jukebox", 84),

    JUNGLE_BOAT("minecraft:jungle_boat", 403),

    JUNGLE_BUTTON("minecraft:jungle_button", -143),

    JUNGLE_CHEST_BOAT("minecraft:jungle_chest_boat", 676),

    JUNGLE_DOOR("minecraft:jungle_door", 588),

    JUNGLE_DOUBLE_SLAB("minecraft:jungle_double_slab", -811),

    JUNGLE_FENCE("minecraft:jungle_fence", -578),

    JUNGLE_FENCE_GATE("minecraft:jungle_fence_gate", 185),

    JUNGLE_HANGING_SIGN("minecraft:jungle_hanging_sign", -503),

    JUNGLE_LEAVES("minecraft:jungle_leaves", -802),

    JUNGLE_LOG("minecraft:jungle_log", -571),

    JUNGLE_PLANKS("minecraft:jungle_planks", -741),

    JUNGLE_PRESSURE_PLATE("minecraft:jungle_pressure_plate", -153),

    JUNGLE_SAPLING("minecraft:jungle_sapling", -827),

    JUNGLE_SHELF("minecraft:jungle_shelf", -1050),

    JUNGLE_SIGN("minecraft:jungle_sign", 611),

    JUNGLE_SLAB("minecraft:jungle_slab", -806),

    JUNGLE_STAIRS("minecraft:jungle_stairs", 136),

    JUNGLE_STANDING_SIGN("minecraft:jungle_standing_sign", -188),

    JUNGLE_TRAPDOOR("minecraft:jungle_trapdoor", -148),

    JUNGLE_WALL_SIGN("minecraft:jungle_wall_sign", -189),

    JUNGLE_WOOD("minecraft:jungle_wood", -816),

    KELP("minecraft:kelp", 408),

    LAB_TABLE("minecraft:lab_table", -988),

    LADDER("minecraft:ladder", 65),

    LANTERN("minecraft:lantern", -208),

    LAPIS_BLOCK("minecraft:lapis_block", 22),

    LAPIS_LAZULI("minecraft:lapis_lazuli", 440),

    LAPIS_ORE("minecraft:lapis_ore", 21),

    LARGE_AMETHYST_BUD("minecraft:large_amethyst_bud", -330),

    LARGE_FERN("minecraft:large_fern", -865),

    LAVA("minecraft:lava", 11),

    LAVA_BUCKET("minecraft:lava_bucket", 388),

    LEAD("minecraft:lead", 580),

    LEAF_LITTER("minecraft:leaf_litter", -1026),

    LEATHER("minecraft:leather", 407),

    LEATHER_BOOTS("minecraft:leather_boots", 363),

    LEATHER_CHESTPLATE("minecraft:leather_chestplate", 361),

    LEATHER_HELMET("minecraft:leather_helmet", 360),

    LEATHER_HORSE_ARMOR("minecraft:leather_horse_armor", 563),

    LEATHER_LEGGINGS("minecraft:leather_leggings", 362),

    LEAVES("minecraft:leaves", 796),

    LEAVES2("minecraft:leaves2", 797),

    LECTERN("minecraft:lectern", -194),

    LEVER("minecraft:lever", 69),

    LIGHT_BLOCK("minecraft:light_block", 822),

    LIGHT_BLOCK_0("minecraft:light_block_0", -215),

    LIGHT_BLOCK_1("minecraft:light_block_1", -929),

    LIGHT_BLOCK_10("minecraft:light_block_10", -938),

    LIGHT_BLOCK_11("minecraft:light_block_11", -939),

    LIGHT_BLOCK_12("minecraft:light_block_12", -940),

    LIGHT_BLOCK_13("minecraft:light_block_13", -941),

    LIGHT_BLOCK_14("minecraft:light_block_14", -942),

    LIGHT_BLOCK_15("minecraft:light_block_15", -943),

    LIGHT_BLOCK_2("minecraft:light_block_2", -930),

    LIGHT_BLOCK_3("minecraft:light_block_3", -931),

    LIGHT_BLOCK_4("minecraft:light_block_4", -932),

    LIGHT_BLOCK_5("minecraft:light_block_5", -933),

    LIGHT_BLOCK_6("minecraft:light_block_6", -934),

    LIGHT_BLOCK_7("minecraft:light_block_7", -935),

    LIGHT_BLOCK_8("minecraft:light_block_8", -936),

    LIGHT_BLOCK_9("minecraft:light_block_9", -937),

    LIGHT_BLUE_BUNDLE("minecraft:light_blue_bundle", 264),

    LIGHT_BLUE_CANDLE("minecraft:light_blue_candle", -416),

    LIGHT_BLUE_CANDLE_CAKE("minecraft:light_blue_candle_cake", -433),

    LIGHT_BLUE_CARPET("minecraft:light_blue_carpet", -599),

    LIGHT_BLUE_CONCRETE("minecraft:light_blue_concrete", -630),

    LIGHT_BLUE_CONCRETE_POWDER("minecraft:light_blue_concrete_powder", -711),

    LIGHT_BLUE_DYE("minecraft:light_blue_dye", 433),

    LIGHT_BLUE_GLAZED_TERRACOTTA("minecraft:light_blue_glazed_terracotta", 223),

    LIGHT_BLUE_HARNESS("minecraft:light_blue_harness", 757),

    LIGHT_BLUE_SHULKER_BOX("minecraft:light_blue_shulker_box", -615),

    LIGHT_BLUE_STAINED_GLASS("minecraft:light_blue_stained_glass", -675),

    LIGHT_BLUE_STAINED_GLASS_PANE("minecraft:light_blue_stained_glass_pane", -645),

    LIGHT_BLUE_TERRACOTTA("minecraft:light_blue_terracotta", -726),

    LIGHT_BLUE_WOOL("minecraft:light_blue_wool", -562),

    LIGHT_GRAY_BUNDLE("minecraft:light_gray_bundle", 265),

    LIGHT_GRAY_CANDLE("minecraft:light_gray_candle", -421),

    LIGHT_GRAY_CANDLE_CAKE("minecraft:light_gray_candle_cake", -438),

    LIGHT_GRAY_CARPET("minecraft:light_gray_carpet", -604),

    LIGHT_GRAY_CONCRETE("minecraft:light_gray_concrete", -635),

    LIGHT_GRAY_CONCRETE_POWDER("minecraft:light_gray_concrete_powder", -716),

    LIGHT_GRAY_DYE("minecraft:light_gray_dye", 428),

    LIGHT_GRAY_HARNESS("minecraft:light_gray_harness", 758),

    LIGHT_GRAY_SHULKER_BOX("minecraft:light_gray_shulker_box", -620),

    LIGHT_GRAY_STAINED_GLASS("minecraft:light_gray_stained_glass", -680),

    LIGHT_GRAY_STAINED_GLASS_PANE("minecraft:light_gray_stained_glass_pane", -650),

    LIGHT_GRAY_TERRACOTTA("minecraft:light_gray_terracotta", -731),

    LIGHT_GRAY_WOOL("minecraft:light_gray_wool", -552),

    LIGHT_WEIGHTED_PRESSURE_PLATE("minecraft:light_weighted_pressure_plate", 147),

    LIGHTNING_ROD("minecraft:lightning_rod", -312),

    LILAC("minecraft:lilac", -863),

    LILY_OF_THE_VALLEY("minecraft:lily_of_the_valley", -839),

    LIME_BUNDLE("minecraft:lime_bundle", 266),

    LIME_CANDLE("minecraft:lime_candle", -418),

    LIME_CANDLE_CAKE("minecraft:lime_candle_cake", -435),

    LIME_CARPET("minecraft:lime_carpet", -601),

    LIME_CONCRETE("minecraft:lime_concrete", -632),

    LIME_CONCRETE_POWDER("minecraft:lime_concrete_powder", -713),

    LIME_DYE("minecraft:lime_dye", 431),

    LIME_GLAZED_TERRACOTTA("minecraft:lime_glazed_terracotta", 225),

    LIME_HARNESS("minecraft:lime_harness", 759),

    LIME_SHULKER_BOX("minecraft:lime_shulker_box", -617),

    LIME_STAINED_GLASS("minecraft:lime_stained_glass", -677),

    LIME_STAINED_GLASS_PANE("minecraft:lime_stained_glass_pane", -647),

    LIME_TERRACOTTA("minecraft:lime_terracotta", -728),

    LIME_WOOL("minecraft:lime_wool", -559),

    LINGERING_POTION("minecraft:lingering_potion", 595),

    LIT_BLAST_FURNACE("minecraft:lit_blast_furnace", -214),

    LIT_DEEPSLATE_REDSTONE_ORE("minecraft:lit_deepslate_redstone_ore", -404),

    LIT_FURNACE("minecraft:lit_furnace", 62),

    LIT_PUMPKIN("minecraft:lit_pumpkin", 91),

    LIT_REDSTONE_LAMP("minecraft:lit_redstone_lamp", 124),

    LIT_REDSTONE_ORE("minecraft:lit_redstone_ore", 74),

    LIT_SMOKER("minecraft:lit_smoker", -199),

    LLAMA_SPAWN_EGG("minecraft:llama_spawn_egg", 501),

    LODESTONE("minecraft:lodestone", -222),

    LODESTONE_COMPASS("minecraft:lodestone_compass", 639),

    LOG("minecraft:log", 781),

    LOG2("minecraft:log2", 804),

    LOOM("minecraft:loom", -204),

    MACE("minecraft:mace", 344),

    MAGENTA_BUNDLE("minecraft:magenta_bundle", 267),

    MAGENTA_CANDLE("minecraft:magenta_candle", -415),

    MAGENTA_CANDLE_CAKE("minecraft:magenta_candle_cake", -432),

    MAGENTA_CARPET("minecraft:magenta_carpet", -598),

    MAGENTA_CONCRETE("minecraft:magenta_concrete", -629),

    MAGENTA_CONCRETE_POWDER("minecraft:magenta_concrete_powder", -710),

    MAGENTA_DYE("minecraft:magenta_dye", 434),

    MAGENTA_GLAZED_TERRACOTTA("minecraft:magenta_glazed_terracotta", 222),

    MAGENTA_HARNESS("minecraft:magenta_harness", 760),

    MAGENTA_SHULKER_BOX("minecraft:magenta_shulker_box", -614),

    MAGENTA_STAINED_GLASS("minecraft:magenta_stained_glass", -674),

    MAGENTA_STAINED_GLASS_PANE("minecraft:magenta_stained_glass_pane", -644),

    MAGENTA_TERRACOTTA("minecraft:magenta_terracotta", -725),

    MAGENTA_WOOL("minecraft:magenta_wool", -565),

    MAGMA("minecraft:magma", 213),

    MAGMA_CREAM("minecraft:magma_cream", 457),

    MAGMA_CUBE_SPAWN_EGG("minecraft:magma_cube_spawn_egg", 482),

    MANGROVE_BOAT("minecraft:mangrove_boat", 671),

    MANGROVE_BUTTON("minecraft:mangrove_button", -487),

    MANGROVE_CHEST_BOAT("minecraft:mangrove_chest_boat", 680),

    MANGROVE_DOOR("minecraft:mangrove_door", 669),

    MANGROVE_DOUBLE_SLAB("minecraft:mangrove_double_slab", -499),

    MANGROVE_FENCE("minecraft:mangrove_fence", -491),

    MANGROVE_FENCE_GATE("minecraft:mangrove_fence_gate", -492),

    MANGROVE_HANGING_SIGN("minecraft:mangrove_hanging_sign", -508),

    MANGROVE_LEAVES("minecraft:mangrove_leaves", -472),

    MANGROVE_LOG("minecraft:mangrove_log", -484),

    MANGROVE_PLANKS("minecraft:mangrove_planks", -486),

    MANGROVE_PRESSURE_PLATE("minecraft:mangrove_pressure_plate", -490),

    MANGROVE_PROPAGULE("minecraft:mangrove_propagule", -474),

    MANGROVE_ROOTS("minecraft:mangrove_roots", -482),

    MANGROVE_SHELF("minecraft:mangrove_shelf", -1053),

    MANGROVE_SIGN("minecraft:mangrove_sign", 670),

    MANGROVE_SLAB("minecraft:mangrove_slab", -489),

    MANGROVE_STAIRS("minecraft:mangrove_stairs", -488),

    MANGROVE_STANDING_SIGN("minecraft:mangrove_standing_sign", -494),

    MANGROVE_TRAPDOOR("minecraft:mangrove_trapdoor", -496),

    MANGROVE_WALL_SIGN("minecraft:mangrove_wall_sign", -495),

    MANGROVE_WOOD("minecraft:mangrove_wood", -497),

    MATERIAL_REDUCER("minecraft:material_reducer", -986),

    MEDICINE("minecraft:medicine", 636),

    MEDIUM_AMETHYST_BUD("minecraft:medium_amethyst_bud", -331),

    MELON_BLOCK("minecraft:melon_block", 103),

    MELON_SEEDS("minecraft:melon_seeds", 315),

    MELON_SLICE("minecraft:melon_slice", 294),

    MELON_STEM("minecraft:melon_stem", 105),

    MILK_BUCKET("minecraft:milk_bucket", 386),

    MINECART("minecraft:minecart", 395),

    MINER_POTTERY_SHERD("minecraft:miner_pottery_sherd", 706),

    MOB_SPAWNER("minecraft:mob_spawner", 52),

    MOJANG_BANNER_PATTERN("minecraft:mojang_banner_pattern", 617),

    MONSTER_EGG("minecraft:monster_egg", 805),

    MOOSHROOM_SPAWN_EGG("minecraft:mooshroom_spawn_egg", 467),

    MOSS_BLOCK("minecraft:moss_block", -320),

    MOSS_CARPET("minecraft:moss_carpet", -335),

    MOSSY_COBBLESTONE("minecraft:mossy_cobblestone", 48),

    MOSSY_COBBLESTONE_DOUBLE_SLAB("minecraft:mossy_cobblestone_double_slab", -915),

    MOSSY_COBBLESTONE_SLAB("minecraft:mossy_cobblestone_slab", -888),

    MOSSY_COBBLESTONE_STAIRS("minecraft:mossy_cobblestone_stairs", -179),

    MOSSY_COBBLESTONE_WALL("minecraft:mossy_cobblestone_wall", -971),

    MOSSY_STONE_BRICK_DOUBLE_SLAB("minecraft:mossy_stone_brick_double_slab", -168),

    MOSSY_STONE_BRICK_SLAB("minecraft:mossy_stone_brick_slab", -166),

    MOSSY_STONE_BRICK_STAIRS("minecraft:mossy_stone_brick_stairs", -175),

    MOSSY_STONE_BRICK_WALL("minecraft:mossy_stone_brick_wall", -978),

    MOSSY_STONE_BRICKS("minecraft:mossy_stone_bricks", -868),

    MOURNER_POTTERY_SHERD("minecraft:mourner_pottery_sherd", 707),

    MOVING_BLOCK("minecraft:moving_block", 250),

    MUD("minecraft:mud", -473),

    MUD_BRICK_DOUBLE_SLAB("minecraft:mud_brick_double_slab", -479),

    MUD_BRICK_SLAB("minecraft:mud_brick_slab", -478),

    MUD_BRICK_STAIRS("minecraft:mud_brick_stairs", -480),

    MUD_BRICK_WALL("minecraft:mud_brick_wall", -481),

    MUD_BRICKS("minecraft:mud_bricks", -475),

    MUDDY_MANGROVE_ROOTS("minecraft:muddy_mangrove_roots", -483),

    MULE_SPAWN_EGG("minecraft:mule_spawn_egg", 494),

    MUSHROOM_STEM("minecraft:mushroom_stem", -1008),

    MUSHROOM_STEW("minecraft:mushroom_stew", 282),

    MUSIC_DISC_11("minecraft:music_disc_11", 577),

    MUSIC_DISC_13("minecraft:music_disc_13", 567),

    MUSIC_DISC_5("minecraft:music_disc_5", 672),

    MUSIC_DISC_BLOCKS("minecraft:music_disc_blocks", 569),

    MUSIC_DISC_CAT("minecraft:music_disc_cat", 568),

    MUSIC_DISC_CHIRP("minecraft:music_disc_chirp", 570),

    MUSIC_DISC_CREATOR("minecraft:music_disc_creator", 812),

    MUSIC_DISC_CREATOR_MUSIC_BOX("minecraft:music_disc_creator_music_box", 813),

    MUSIC_DISC_FAR("minecraft:music_disc_far", 571),

    MUSIC_DISC_LAVA_CHICKEN("minecraft:music_disc_lava_chicken", 816),

    MUSIC_DISC_MALL("minecraft:music_disc_mall", 572),

    MUSIC_DISC_MELLOHI("minecraft:music_disc_mellohi", 573),

    MUSIC_DISC_OTHERSIDE("minecraft:music_disc_otherside", 662),

    MUSIC_DISC_PIGSTEP("minecraft:music_disc_pigstep", 656),

    MUSIC_DISC_PRECIPICE("minecraft:music_disc_precipice", 814),

    MUSIC_DISC_RELIC("minecraft:music_disc_relic", 735),

    MUSIC_DISC_STAL("minecraft:music_disc_stal", 574),

    MUSIC_DISC_STRAD("minecraft:music_disc_strad", 575),

    MUSIC_DISC_TEARS("minecraft:music_disc_tears", 815),

    MUSIC_DISC_WAIT("minecraft:music_disc_wait", 578),

    MUSIC_DISC_WARD("minecraft:music_disc_ward", 576),

    MUTTON("minecraft:mutton", 583),

    MYCELIUM("minecraft:mycelium", 110),

    NAME_TAG("minecraft:name_tag", 581),

    NAUTILUS_SHELL("minecraft:nautilus_shell", 603),

    NETHER_BRICK("minecraft:nether_brick", 112),

    NETHER_BRICK_DOUBLE_SLAB("minecraft:nether_brick_double_slab", -883),

    NETHER_BRICK_FENCE("minecraft:nether_brick_fence", 113),

    NETHER_BRICK_SLAB("minecraft:nether_brick_slab", -877),

    NETHER_BRICK_STAIRS("minecraft:nether_brick_stairs", 114),

    NETHER_BRICK_WALL("minecraft:nether_brick_wall", -979),

    NETHER_GOLD_ORE("minecraft:nether_gold_ore", -288),

    NETHER_SPROUTS("minecraft:nether_sprouts", 657),

    NETHER_STAR("minecraft:nether_star", 551),

    NETHER_WART("minecraft:nether_wart", 316),

    NETHER_WART_BLOCK("minecraft:nether_wart_block", 214),

    NETHERBRICK("minecraft:netherbrick", 556),

    NETHERITE_AXE("minecraft:netherite_axe", 643),

    NETHERITE_BLOCK("minecraft:netherite_block", -270),

    NETHERITE_BOOTS("minecraft:netherite_boots", 649),

    NETHERITE_CHESTPLATE("minecraft:netherite_chestplate", 647),

    NETHERITE_HELMET("minecraft:netherite_helmet", 646),

    NETHERITE_HOE("minecraft:netherite_hoe", 644),

    NETHERITE_INGOT("minecraft:netherite_ingot", 645),

    NETHERITE_LEGGINGS("minecraft:netherite_leggings", 648),

    NETHERITE_PICKAXE("minecraft:netherite_pickaxe", 642),

    NETHERITE_SCRAP("minecraft:netherite_scrap", 650),

    NETHERITE_SHOVEL("minecraft:netherite_shovel", 641),

    NETHERITE_SWORD("minecraft:netherite_sword", 640),

    NETHERITE_UPGRADE_SMITHING_TEMPLATE("minecraft:netherite_upgrade_smithing_template", 716),

    NETHERRACK("minecraft:netherrack", 87),

    NETHERREACTOR("minecraft:netherreactor", 247),

    NORMAL_STONE_DOUBLE_SLAB("minecraft:normal_stone_double_slab", -926),

    NORMAL_STONE_SLAB("minecraft:normal_stone_slab", -899),

    NORMAL_STONE_STAIRS("minecraft:normal_stone_stairs", -180),

    NOTEBLOCK("minecraft:noteblock", 25),

    NPC_SPAWN_EGG("minecraft:npc_spawn_egg", 498),

    OAK_BOAT("minecraft:oak_boat", 401),

    OAK_CHEST_BOAT("minecraft:oak_chest_boat", 674),

    OAK_DOUBLE_SLAB("minecraft:oak_double_slab", 157),

    OAK_FENCE("minecraft:oak_fence", 85),

    OAK_HANGING_SIGN("minecraft:oak_hanging_sign", -500),

    OAK_LEAVES("minecraft:oak_leaves", 18),

    OAK_LOG("minecraft:oak_log", 17),

    OAK_PLANKS("minecraft:oak_planks", 5),

    OAK_SAPLING("minecraft:oak_sapling", 6),

    OAK_SHELF("minecraft:oak_shelf", -1047),

    OAK_SIGN("minecraft:oak_sign", 383),

    OAK_SLAB("minecraft:oak_slab", 158),

    OAK_STAIRS("minecraft:oak_stairs", 53),

    OAK_WOOD("minecraft:oak_wood", -212),

    OBSERVER("minecraft:observer", 251),

    OBSIDIAN("minecraft:obsidian", 49),

    OCELOT_SPAWN_EGG("minecraft:ocelot_spawn_egg", 478),

    OCHRE_FROGLIGHT("minecraft:ochre_froglight", -471),

    OMINOUS_BOTTLE("minecraft:ominous_bottle", 628),

    OMINOUS_TRIAL_KEY("minecraft:ominous_trial_key", 275),

    OPEN_EYEBLOSSOM("minecraft:open_eyeblossom", -1018),

    ORANGE_BUNDLE("minecraft:orange_bundle", 268),

    ORANGE_CANDLE("minecraft:orange_candle", -414),

    ORANGE_CANDLE_CAKE("minecraft:orange_candle_cake", -431),

    ORANGE_CARPET("minecraft:orange_carpet", -597),

    ORANGE_CONCRETE("minecraft:orange_concrete", -628),

    ORANGE_CONCRETE_POWDER("minecraft:orange_concrete_powder", -709),

    ORANGE_DYE("minecraft:orange_dye", 435),

    ORANGE_GLAZED_TERRACOTTA("minecraft:orange_glazed_terracotta", 221),

    ORANGE_HARNESS("minecraft:orange_harness", 761),

    ORANGE_SHULKER_BOX("minecraft:orange_shulker_box", -613),

    ORANGE_STAINED_GLASS("minecraft:orange_stained_glass", -673),

    ORANGE_STAINED_GLASS_PANE("minecraft:orange_stained_glass_pane", -643),

    ORANGE_TERRACOTTA("minecraft:orange_terracotta", -724),

    ORANGE_TULIP("minecraft:orange_tulip", -834),

    ORANGE_WOOL("minecraft:orange_wool", -557),

    OXEYE_DAISY("minecraft:oxeye_daisy", -837),

    OXIDIZED_CHISELED_COPPER("minecraft:oxidized_chiseled_copper", -763),

    OXIDIZED_COPPER("minecraft:oxidized_copper", -343),

    OXIDIZED_COPPER_BARS("minecraft:oxidized_copper_bars", -1069),

    OXIDIZED_COPPER_BULB("minecraft:oxidized_copper_bulb", -779),

    OXIDIZED_COPPER_CHAIN("minecraft:oxidized_copper_chain", -1077),

    OXIDIZED_COPPER_CHEST("minecraft:oxidized_copper_chest", -1034),

    OXIDIZED_COPPER_DOOR("minecraft:oxidized_copper_door", -787),

    OXIDIZED_COPPER_GOLEM_STATUE("minecraft:oxidized_copper_golem_statue", -1042),

    OXIDIZED_COPPER_GRATE("minecraft:oxidized_copper_grate", -771),

    OXIDIZED_COPPER_LANTERN("minecraft:oxidized_copper_lantern", -1086),

    OXIDIZED_COPPER_TRAPDOOR("minecraft:oxidized_copper_trapdoor", -795),

    OXIDIZED_CUT_COPPER("minecraft:oxidized_cut_copper", -350),

    OXIDIZED_CUT_COPPER_SLAB("minecraft:oxidized_cut_copper_slab", -364),

    OXIDIZED_CUT_COPPER_STAIRS("minecraft:oxidized_cut_copper_stairs", -357),

    OXIDIZED_DOUBLE_CUT_COPPER_SLAB("minecraft:oxidized_double_cut_copper_slab", -371),

    OXIDIZED_LIGHTNING_ROD("minecraft:oxidized_lightning_rod", -1061),

    PACKED_ICE("minecraft:packed_ice", 174),

    PACKED_MUD("minecraft:packed_mud", -477),

    PAINTING("minecraft:painting", 382),

    PALE_HANGING_MOSS("minecraft:pale_hanging_moss", -1011),

    PALE_MOSS_BLOCK("minecraft:pale_moss_block", -1009),

    PALE_MOSS_CARPET("minecraft:pale_moss_carpet", -1010),

    PALE_OAK_BOAT("minecraft:pale_oak_boat", 743),

    PALE_OAK_BUTTON("minecraft:pale_oak_button", -989),

    PALE_OAK_CHEST_BOAT("minecraft:pale_oak_chest_boat", 744),

    PALE_OAK_DOOR("minecraft:pale_oak_door", -990),

    PALE_OAK_DOUBLE_SLAB("minecraft:pale_oak_double_slab", -999),

    PALE_OAK_FENCE("minecraft:pale_oak_fence", -991),

    PALE_OAK_FENCE_GATE("minecraft:pale_oak_fence_gate", -992),

    PALE_OAK_HANGING_SIGN("minecraft:pale_oak_hanging_sign", -993),

    PALE_OAK_LEAVES("minecraft:pale_oak_leaves", -1007),

    PALE_OAK_LOG("minecraft:pale_oak_log", -995),

    PALE_OAK_PLANKS("minecraft:pale_oak_planks", -996),

    PALE_OAK_PRESSURE_PLATE("minecraft:pale_oak_pressure_plate", -997),

    PALE_OAK_SAPLING("minecraft:pale_oak_sapling", -1006),

    PALE_OAK_SHELF("minecraft:pale_oak_shelf", -1055),

    PALE_OAK_SIGN("minecraft:pale_oak_sign", 745),

    PALE_OAK_SLAB("minecraft:pale_oak_slab", -998),

    PALE_OAK_STAIRS("minecraft:pale_oak_stairs", -1000),

    PALE_OAK_STANDING_SIGN("minecraft:pale_oak_standing_sign", -1001),

    PALE_OAK_TRAPDOOR("minecraft:pale_oak_trapdoor", -1002),

    PALE_OAK_WALL_SIGN("minecraft:pale_oak_wall_sign", -1003),

    PALE_OAK_WOOD("minecraft:pale_oak_wood", -1005),

    PANDA_SPAWN_EGG("minecraft:panda_spawn_egg", 517),

    PAPER("minecraft:paper", 412),

    PARROT_SPAWN_EGG("minecraft:parrot_spawn_egg", 506),

    PEARLESCENT_FROGLIGHT("minecraft:pearlescent_froglight", -469),

    PEONY("minecraft:peony", -867),

    PETRIFIED_OAK_DOUBLE_SLAB("minecraft:petrified_oak_double_slab", -903),

    PETRIFIED_OAK_SLAB("minecraft:petrified_oak_slab", -902),

    PHANTOM_MEMBRANE("minecraft:phantom_membrane", 607),

    PHANTOM_SPAWN_EGG("minecraft:phantom_spawn_egg", 514),

    PIG_SPAWN_EGG("minecraft:pig_spawn_egg", 464),

    PIGLIN_BANNER_PATTERN("minecraft:piglin_banner_pattern", 620),

    PIGLIN_BRUTE_SPAWN_EGG("minecraft:piglin_brute_spawn_egg", 527),

    PIGLIN_HEAD("minecraft:piglin_head", -970),

    PIGLIN_SPAWN_EGG("minecraft:piglin_spawn_egg", 525),

    PILLAGER_SPAWN_EGG("minecraft:pillager_spawn_egg", 519),

    PINK_BUNDLE("minecraft:pink_bundle", 269),

    PINK_CANDLE("minecraft:pink_candle", -419),

    PINK_CANDLE_CAKE("minecraft:pink_candle_cake", -436),

    PINK_CARPET("minecraft:pink_carpet", -602),

    PINK_CONCRETE("minecraft:pink_concrete", -633),

    PINK_CONCRETE_POWDER("minecraft:pink_concrete_powder", -714),

    PINK_DYE("minecraft:pink_dye", 430),

    PINK_GLAZED_TERRACOTTA("minecraft:pink_glazed_terracotta", 226),

    PINK_HARNESS("minecraft:pink_harness", 762),

    PINK_PETALS("minecraft:pink_petals", -549),

    PINK_SHULKER_BOX("minecraft:pink_shulker_box", -618),

    PINK_STAINED_GLASS("minecraft:pink_stained_glass", -678),

    PINK_STAINED_GLASS_PANE("minecraft:pink_stained_glass_pane", -648),

    PINK_TERRACOTTA("minecraft:pink_terracotta", -729),

    PINK_TULIP("minecraft:pink_tulip", -836),

    PINK_WOOL("minecraft:pink_wool", -566),

    PISTON("minecraft:piston", 33),

    PISTON_ARM_COLLISION("minecraft:piston_arm_collision", 34),

    PITCHER_CROP("minecraft:pitcher_crop", -574),

    PITCHER_PLANT("minecraft:pitcher_plant", -612),

    PITCHER_POD("minecraft:pitcher_pod", 319),

    PLANKS("minecraft:planks", 801),

    PLAYER_HEAD("minecraft:player_head", -967),

    PLENTY_POTTERY_SHERD("minecraft:plenty_pottery_sherd", 708),

    PODZOL("minecraft:podzol", 243),

    POINTED_DRIPSTONE("minecraft:pointed_dripstone", -308),

    POISONOUS_POTATO("minecraft:poisonous_potato", 304),

    POLAR_BEAR_SPAWN_EGG("minecraft:polar_bear_spawn_egg", 500),

    POLISHED_ANDESITE("minecraft:polished_andesite", -595),

    POLISHED_ANDESITE_DOUBLE_SLAB("minecraft:polished_andesite_double_slab", -919),

    POLISHED_ANDESITE_SLAB("minecraft:polished_andesite_slab", -892),

    POLISHED_ANDESITE_STAIRS("minecraft:polished_andesite_stairs", -174),

    POLISHED_BASALT("minecraft:polished_basalt", -235),

    POLISHED_BLACKSTONE("minecraft:polished_blackstone", -291),

    POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB("minecraft:polished_blackstone_brick_double_slab", -285),

    POLISHED_BLACKSTONE_BRICK_SLAB("minecraft:polished_blackstone_brick_slab", -284),

    POLISHED_BLACKSTONE_BRICK_STAIRS("minecraft:polished_blackstone_brick_stairs", -275),

    POLISHED_BLACKSTONE_BRICK_WALL("minecraft:polished_blackstone_brick_wall", -278),

    POLISHED_BLACKSTONE_BRICKS("minecraft:polished_blackstone_bricks", -274),

    POLISHED_BLACKSTONE_BUTTON("minecraft:polished_blackstone_button", -296),

    POLISHED_BLACKSTONE_DOUBLE_SLAB("minecraft:polished_blackstone_double_slab", -294),

    POLISHED_BLACKSTONE_PRESSURE_PLATE("minecraft:polished_blackstone_pressure_plate", -295),

    POLISHED_BLACKSTONE_SLAB("minecraft:polished_blackstone_slab", -293),

    POLISHED_BLACKSTONE_STAIRS("minecraft:polished_blackstone_stairs", -292),

    POLISHED_BLACKSTONE_WALL("minecraft:polished_blackstone_wall", -297),

    POLISHED_DEEPSLATE("minecraft:polished_deepslate", -383),

    POLISHED_DEEPSLATE_DOUBLE_SLAB("minecraft:polished_deepslate_double_slab", -397),

    POLISHED_DEEPSLATE_SLAB("minecraft:polished_deepslate_slab", -384),

    POLISHED_DEEPSLATE_STAIRS("minecraft:polished_deepslate_stairs", -385),

    POLISHED_DEEPSLATE_WALL("minecraft:polished_deepslate_wall", -386),

    POLISHED_DIORITE("minecraft:polished_diorite", -593),

    POLISHED_DIORITE_DOUBLE_SLAB("minecraft:polished_diorite_double_slab", -922),

    POLISHED_DIORITE_SLAB("minecraft:polished_diorite_slab", -895),

    POLISHED_DIORITE_STAIRS("minecraft:polished_diorite_stairs", -173),

    POLISHED_GRANITE("minecraft:polished_granite", -591),

    POLISHED_GRANITE_DOUBLE_SLAB("minecraft:polished_granite_double_slab", -924),

    POLISHED_GRANITE_SLAB("minecraft:polished_granite_slab", -897),

    POLISHED_GRANITE_STAIRS("minecraft:polished_granite_stairs", -172),

    POLISHED_TUFF("minecraft:polished_tuff", -748),

    POLISHED_TUFF_DOUBLE_SLAB("minecraft:polished_tuff_double_slab", -750),

    POLISHED_TUFF_SLAB("minecraft:polished_tuff_slab", -749),

    POLISHED_TUFF_STAIRS("minecraft:polished_tuff_stairs", -751),

    POLISHED_TUFF_WALL("minecraft:polished_tuff_wall", -752),

    POPPED_CHORUS_FRUIT("minecraft:popped_chorus_fruit", 592),

    POPPY("minecraft:poppy", 38),

    PORKCHOP("minecraft:porkchop", 284),

    PORTAL("minecraft:portal", 90),

    POTATO("minecraft:potato", 302),

    POTATOES("minecraft:potatoes", 142),

    POTION("minecraft:potion", 453),

    POWDER_SNOW("minecraft:powder_snow", -306),

    POWDER_SNOW_BUCKET("minecraft:powder_snow_bucket", 393),

    POWERED_COMPARATOR("minecraft:powered_comparator", 150),

    POWERED_REPEATER("minecraft:powered_repeater", 94),

    PRISMARINE("minecraft:prismarine", 168),

    PRISMARINE_BRICK_DOUBLE_SLAB("minecraft:prismarine_brick_double_slab", -914),

    PRISMARINE_BRICK_SLAB("minecraft:prismarine_brick_slab", -887),

    PRISMARINE_BRICKS("minecraft:prismarine_bricks", -948),

    PRISMARINE_BRICKS_STAIRS("minecraft:prismarine_bricks_stairs", -4),

    PRISMARINE_CRYSTALS("minecraft:prismarine_crystals", 582),

    PRISMARINE_DOUBLE_SLAB("minecraft:prismarine_double_slab", -912),

    PRISMARINE_SHARD("minecraft:prismarine_shard", 598),

    PRISMARINE_SLAB("minecraft:prismarine_slab", -885),

    PRISMARINE_STAIRS("minecraft:prismarine_stairs", -2),

    PRISMARINE_WALL("minecraft:prismarine_wall", -981),

    PRIZE_POTTERY_SHERD("minecraft:prize_pottery_sherd", 709),

    PUFFERFISH("minecraft:pufferfish", 289),

    PUFFERFISH_BUCKET("minecraft:pufferfish_bucket", 392),

    PUFFERFISH_SPAWN_EGG("minecraft:pufferfish_spawn_egg", 509),

    PUMPKIN("minecraft:pumpkin", 86),

    PUMPKIN_PIE("minecraft:pumpkin_pie", 306),

    PUMPKIN_SEEDS("minecraft:pumpkin_seeds", 314),

    PUMPKIN_STEM("minecraft:pumpkin_stem", 104),

    PURPLE_BUNDLE("minecraft:purple_bundle", 270),

    PURPLE_CANDLE("minecraft:purple_candle", -423),

    PURPLE_CANDLE_CAKE("minecraft:purple_candle_cake", -440),

    PURPLE_CARPET("minecraft:purple_carpet", -606),

    PURPLE_CONCRETE("minecraft:purple_concrete", -637),

    PURPLE_CONCRETE_POWDER("minecraft:purple_concrete_powder", -718),

    PURPLE_DYE("minecraft:purple_dye", 426),

    PURPLE_GLAZED_TERRACOTTA("minecraft:purple_glazed_terracotta", 219),

    PURPLE_HARNESS("minecraft:purple_harness", 763),

    PURPLE_SHULKER_BOX("minecraft:purple_shulker_box", -622),

    PURPLE_STAINED_GLASS("minecraft:purple_stained_glass", -682),

    PURPLE_STAINED_GLASS_PANE("minecraft:purple_stained_glass_pane", -652),

    PURPLE_TERRACOTTA("minecraft:purple_terracotta", -733),

    PURPLE_WOOL("minecraft:purple_wool", -564),

    PURPUR_BLOCK("minecraft:purpur_block", 201),

    PURPUR_DOUBLE_SLAB("minecraft:purpur_double_slab", -911),

    PURPUR_PILLAR("minecraft:purpur_pillar", -951),

    PURPUR_SLAB("minecraft:purpur_slab", -884),

    PURPUR_STAIRS("minecraft:purpur_stairs", 203),

    QUARTZ("minecraft:quartz", 557),

    QUARTZ_BLOCK("minecraft:quartz_block", 155),

    QUARTZ_BRICKS("minecraft:quartz_bricks", -304),

    QUARTZ_DOUBLE_SLAB("minecraft:quartz_double_slab", -882),

    QUARTZ_ORE("minecraft:quartz_ore", 153),

    QUARTZ_PILLAR("minecraft:quartz_pillar", -954),

    QUARTZ_SLAB("minecraft:quartz_slab", -876),

    QUARTZ_STAIRS("minecraft:quartz_stairs", 156),

    RABBIT("minecraft:rabbit", 310),

    RABBIT_FOOT("minecraft:rabbit_foot", 561),

    RABBIT_HIDE("minecraft:rabbit_hide", 562),

    RABBIT_SPAWN_EGG("minecraft:rabbit_spawn_egg", 486),

    RABBIT_STEW("minecraft:rabbit_stew", 312),

    RAIL("minecraft:rail", 66),

    RAISER_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:raiser_armor_trim_smithing_template", 730),

    RAPID_FERTILIZER("minecraft:rapid_fertilizer", 634),

    RAVAGER_SPAWN_EGG("minecraft:ravager_spawn_egg", 521),

    RAW_COPPER("minecraft:raw_copper", 541),

    RAW_COPPER_BLOCK("minecraft:raw_copper_block", -452),

    RAW_GOLD("minecraft:raw_gold", 540),

    RAW_GOLD_BLOCK("minecraft:raw_gold_block", -453),

    RAW_IRON("minecraft:raw_iron", 539),

    RAW_IRON_BLOCK("minecraft:raw_iron_block", -451),

    RECOVERY_COMPASS("minecraft:recovery_compass", 682),

    RED_BUNDLE("minecraft:red_bundle", 271),

    RED_CANDLE("minecraft:red_candle", -427),

    RED_CANDLE_CAKE("minecraft:red_candle_cake", -444),

    RED_CARPET("minecraft:red_carpet", -610),

    RED_CONCRETE("minecraft:red_concrete", -641),

    RED_CONCRETE_POWDER("minecraft:red_concrete_powder", -722),

    RED_DYE("minecraft:red_dye", 422),

    RED_FLOWER("minecraft:red_flower", 799),

    RED_GLAZED_TERRACOTTA("minecraft:red_glazed_terracotta", 234),

    RED_HARNESS("minecraft:red_harness", 764),

    RED_MUSHROOM("minecraft:red_mushroom", 40),

    RED_MUSHROOM_BLOCK("minecraft:red_mushroom_block", 100),

    RED_NETHER_BRICK("minecraft:red_nether_brick", 215),

    RED_NETHER_BRICK_DOUBLE_SLAB("minecraft:red_nether_brick_double_slab", -917),

    RED_NETHER_BRICK_SLAB("minecraft:red_nether_brick_slab", -890),

    RED_NETHER_BRICK_STAIRS("minecraft:red_nether_brick_stairs", -184),

    RED_NETHER_BRICK_WALL("minecraft:red_nether_brick_wall", -983),

    RED_SAND("minecraft:red_sand", -949),

    RED_SANDSTONE("minecraft:red_sandstone", 179),

    RED_SANDSTONE_DOUBLE_SLAB("minecraft:red_sandstone_double_slab", 181),

    RED_SANDSTONE_SLAB("minecraft:red_sandstone_slab", 182),

    RED_SANDSTONE_STAIRS("minecraft:red_sandstone_stairs", 180),

    RED_SANDSTONE_WALL("minecraft:red_sandstone_wall", -982),

    RED_SHULKER_BOX("minecraft:red_shulker_box", -626),

    RED_STAINED_GLASS("minecraft:red_stained_glass", -686),

    RED_STAINED_GLASS_PANE("minecraft:red_stained_glass_pane", -656),

    RED_TERRACOTTA("minecraft:red_terracotta", -737),

    RED_TULIP("minecraft:red_tulip", -833),

    RED_WOOL("minecraft:red_wool", -556),

    REDSTONE("minecraft:redstone", 398),

    REDSTONE_BLOCK("minecraft:redstone_block", 152),

    REDSTONE_LAMP("minecraft:redstone_lamp", 123),

    REDSTONE_ORE("minecraft:redstone_ore", 73),

    REDSTONE_TORCH("minecraft:redstone_torch", 76),

    REDSTONE_WIRE("minecraft:redstone_wire", 55),

    REINFORCED_DEEPSLATE("minecraft:reinforced_deepslate", -466),

    REPEATER("minecraft:repeater", 445),

    REPEATING_COMMAND_BLOCK("minecraft:repeating_command_block", 188),

    RESERVED6("minecraft:reserved6", 255),

    RESIN_BLOCK("minecraft:resin_block", -1021),

    RESIN_BRICK("minecraft:resin_brick", 747),

    RESIN_BRICK_DOUBLE_SLAB("minecraft:resin_brick_double_slab", -1015),

    RESIN_BRICK_SLAB("minecraft:resin_brick_slab", -1014),

    RESIN_BRICK_STAIRS("minecraft:resin_brick_stairs", -1016),

    RESIN_BRICK_WALL("minecraft:resin_brick_wall", -1017),

    RESIN_BRICKS("minecraft:resin_bricks", -1013),

    RESIN_CLUMP("minecraft:resin_clump", -1022),

    RESPAWN_ANCHOR("minecraft:respawn_anchor", -272),

    RIB_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:rib_armor_trim_smithing_template", 726),

    ROSE_BUSH("minecraft:rose_bush", -866),

    ROTTEN_FLESH("minecraft:rotten_flesh", 299),

    SADDLE("minecraft:saddle", 396),

    SALMON("minecraft:salmon", 287),

    SALMON_BUCKET("minecraft:salmon_bucket", 390),

    SALMON_SPAWN_EGG("minecraft:salmon_spawn_egg", 510),

    SAND("minecraft:sand", 12),

    SANDSTONE("minecraft:sandstone", 24),

    SANDSTONE_DOUBLE_SLAB("minecraft:sandstone_double_slab", -878),

    SANDSTONE_SLAB("minecraft:sandstone_slab", -872),

    SANDSTONE_STAIRS("minecraft:sandstone_stairs", 128),

    SANDSTONE_WALL("minecraft:sandstone_wall", -975),

    SAPLING("minecraft:sapling", 795),

    SCAFFOLDING("minecraft:scaffolding", -165),

    SCRAPE_POTTERY_SHERD("minecraft:scrape_pottery_sherd", 710),

    SCULK("minecraft:sculk", -458),

    SCULK_CATALYST("minecraft:sculk_catalyst", -460),

    SCULK_SENSOR("minecraft:sculk_sensor", -307),

    SCULK_SHRIEKER("minecraft:sculk_shrieker", -461),

    SCULK_VEIN("minecraft:sculk_vein", -459),

    SEA_LANTERN("minecraft:sea_lantern", 169),

    SEA_PICKLE("minecraft:sea_pickle", -156),

    SEAGRASS("minecraft:seagrass", -130),

    SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:sentry_armor_trim_smithing_template", 717),

    SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:shaper_armor_trim_smithing_template", 731),

    SHEAF_POTTERY_SHERD("minecraft:sheaf_pottery_sherd", 711),

    SHEARS("minecraft:shears", 447),

    SHEEP_SPAWN_EGG("minecraft:sheep_spawn_egg", 465),

    SHELTER_POTTERY_SHERD("minecraft:shelter_pottery_sherd", 712),

    SHIELD("minecraft:shield", 380),

    SHORT_DRY_GRASS("minecraft:short_dry_grass", -1028),

    SHORT_GRASS("minecraft:short_grass", 31),

    SHROOMLIGHT("minecraft:shroomlight", -230),

    SHULKER_BOX("minecraft:shulker_box", 810),

    SHULKER_SHELL("minecraft:shulker_shell", 599),

    SHULKER_SPAWN_EGG("minecraft:shulker_spawn_egg", 497),

    SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:silence_armor_trim_smithing_template", 728),

    SILVER_GLAZED_TERRACOTTA("minecraft:silver_glazed_terracotta", 228),

    SILVERFISH_SPAWN_EGG("minecraft:silverfish_spawn_egg", 470),

    SKELETON_HORSE_SPAWN_EGG("minecraft:skeleton_horse_spawn_egg", 495),

    SKELETON_SKULL("minecraft:skeleton_skull", 144),

    SKELETON_SPAWN_EGG("minecraft:skeleton_spawn_egg", 471),

    SKULL("minecraft:skull", 736),

    SKULL_BANNER_PATTERN("minecraft:skull_banner_pattern", 616),

    SKULL_POTTERY_SHERD("minecraft:skull_pottery_sherd", 713),

    SLIME("minecraft:slime", 165),

    SLIME_BALL("minecraft:slime_ball", 414),

    SLIME_SPAWN_EGG("minecraft:slime_spawn_egg", 472),

    SMALL_AMETHYST_BUD("minecraft:small_amethyst_bud", -332),

    SMALL_DRIPLEAF_BLOCK("minecraft:small_dripleaf_block", -336),

    SMITHING_TABLE("minecraft:smithing_table", -202),

    SMOKER("minecraft:smoker", -198),

    SMOOTH_BASALT("minecraft:smooth_basalt", -377),

    SMOOTH_QUARTZ("minecraft:smooth_quartz", -955),

    SMOOTH_QUARTZ_DOUBLE_SLAB("minecraft:smooth_quartz_double_slab", -925),

    SMOOTH_QUARTZ_SLAB("minecraft:smooth_quartz_slab", -898),

    SMOOTH_QUARTZ_STAIRS("minecraft:smooth_quartz_stairs", -185),

    SMOOTH_RED_SANDSTONE("minecraft:smooth_red_sandstone", -958),

    SMOOTH_RED_SANDSTONE_DOUBLE_SLAB("minecraft:smooth_red_sandstone_double_slab", -918),

    SMOOTH_RED_SANDSTONE_SLAB("minecraft:smooth_red_sandstone_slab", -891),

    SMOOTH_RED_SANDSTONE_STAIRS("minecraft:smooth_red_sandstone_stairs", -176),

    SMOOTH_SANDSTONE("minecraft:smooth_sandstone", -946),

    SMOOTH_SANDSTONE_DOUBLE_SLAB("minecraft:smooth_sandstone_double_slab", -916),

    SMOOTH_SANDSTONE_SLAB("minecraft:smooth_sandstone_slab", -889),

    SMOOTH_SANDSTONE_STAIRS("minecraft:smooth_sandstone_stairs", -177),

    SMOOTH_STONE("minecraft:smooth_stone", -183),

    SMOOTH_STONE_DOUBLE_SLAB("minecraft:smooth_stone_double_slab", 43),

    SMOOTH_STONE_SLAB("minecraft:smooth_stone_slab", 44),

    SNIFFER_EGG("minecraft:sniffer_egg", -596),

    SNIFFER_SPAWN_EGG("minecraft:sniffer_spawn_egg", 528),

    SNORT_POTTERY_SHERD("minecraft:snort_pottery_sherd", 714),

    SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:snout_armor_trim_smithing_template", 725),

    SNOW("minecraft:snow", 80),

    SNOW_GOLEM_SPAWN_EGG("minecraft:snow_golem_spawn_egg", 534),

    SNOW_LAYER("minecraft:snow_layer", 78),

    SNOWBALL("minecraft:snowball", 399),

    SOUL_CAMPFIRE("minecraft:soul_campfire", 658),

    SOUL_FIRE("minecraft:soul_fire", -237),

    SOUL_LANTERN("minecraft:soul_lantern", -269),

    SOUL_SAND("minecraft:soul_sand", 88),

    SOUL_SOIL("minecraft:soul_soil", -236),

    SOUL_TORCH("minecraft:soul_torch", -268),

    SPARKLER("minecraft:sparkler", 637),

    SPAWN_EGG("minecraft:spawn_egg", 826),

    SPIDER_EYE("minecraft:spider_eye", 300),

    SPIDER_SPAWN_EGG("minecraft:spider_spawn_egg", 473),

    SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:spire_armor_trim_smithing_template", 727),

    SPLASH_POTION("minecraft:splash_potion", 594),

    SPONGE("minecraft:sponge", 19),

    SPORE_BLOSSOM("minecraft:spore_blossom", -321),

    SPRUCE_BOAT("minecraft:spruce_boat", 404),

    SPRUCE_BUTTON("minecraft:spruce_button", -144),

    SPRUCE_CHEST_BOAT("minecraft:spruce_chest_boat", 677),

    SPRUCE_DOOR("minecraft:spruce_door", 586),

    SPRUCE_DOUBLE_SLAB("minecraft:spruce_double_slab", -809),

    SPRUCE_FENCE("minecraft:spruce_fence", -579),

    SPRUCE_FENCE_GATE("minecraft:spruce_fence_gate", 183),

    SPRUCE_HANGING_SIGN("minecraft:spruce_hanging_sign", -501),

    SPRUCE_LEAVES("minecraft:spruce_leaves", -800),

    SPRUCE_LOG("minecraft:spruce_log", -569),

    SPRUCE_PLANKS("minecraft:spruce_planks", -739),

    SPRUCE_PRESSURE_PLATE("minecraft:spruce_pressure_plate", -154),

    SPRUCE_SAPLING("minecraft:spruce_sapling", -825),

    SPRUCE_SHELF("minecraft:spruce_shelf", -1048),

    SPRUCE_SIGN("minecraft:spruce_sign", 609),

    SPRUCE_SLAB("minecraft:spruce_slab", -804),

    SPRUCE_STAIRS("minecraft:spruce_stairs", 134),

    SPRUCE_STANDING_SIGN("minecraft:spruce_standing_sign", -181),

    SPRUCE_TRAPDOOR("minecraft:spruce_trapdoor", -149),

    SPRUCE_WALL_SIGN("minecraft:spruce_wall_sign", -182),

    SPRUCE_WOOD("minecraft:spruce_wood", -814),

    SPYGLASS("minecraft:spyglass", 661),

    SQUID_SPAWN_EGG("minecraft:squid_spawn_egg", 477),

    STAINED_GLASS("minecraft:stained_glass", 808),

    STAINED_GLASS_PANE("minecraft:stained_glass_pane", 809),

    STAINED_HARDENED_CLAY("minecraft:stained_hardened_clay", 737),

    STANDING_BANNER("minecraft:standing_banner", 176),

    STANDING_SIGN("minecraft:standing_sign", 63),

    STICK("minecraft:stick", 345),

    STICKY_PISTON("minecraft:sticky_piston", 29),

    STICKY_PISTON_ARM_COLLISION("minecraft:sticky_piston_arm_collision", -217),

    STONE("minecraft:stone", 1),

    STONE_AXE("minecraft:stone_axe", 339),

    STONE_BLOCK_SLAB("minecraft:stone_block_slab", 785),

    STONE_BLOCK_SLAB2("minecraft:stone_block_slab2", 786),

    STONE_BLOCK_SLAB3("minecraft:stone_block_slab3", 787),

    STONE_BLOCK_SLAB4("minecraft:stone_block_slab4", 788),

    STONE_BRICK_DOUBLE_SLAB("minecraft:stone_brick_double_slab", -881),

    STONE_BRICK_SLAB("minecraft:stone_brick_slab", -875),

    STONE_BRICK_STAIRS("minecraft:stone_brick_stairs", 109),

    STONE_BRICK_WALL("minecraft:stone_brick_wall", -977),

    STONE_BRICKS("minecraft:stone_bricks", 98),

    STONE_BUTTON("minecraft:stone_button", 77),

    STONE_HOE("minecraft:stone_hoe", 355),

    STONE_PICKAXE("minecraft:stone_pickaxe", 338),

    STONE_PRESSURE_PLATE("minecraft:stone_pressure_plate", 70),

    STONE_SHOVEL("minecraft:stone_shovel", 337),

    STONE_STAIRS("minecraft:stone_stairs", 67),

    STONE_SWORD("minecraft:stone_sword", 336),

    STONEBRICK("minecraft:stonebrick", 783),

    STONECUTTER("minecraft:stonecutter", 245),

    STONECUTTER_BLOCK("minecraft:stonecutter_block", -197),

    STRAY_SPAWN_EGG("minecraft:stray_spawn_egg", 489),

    STRIDER_SPAWN_EGG("minecraft:strider_spawn_egg", 523),

    STRING("minecraft:string", 351),

    STRIPPED_ACACIA_LOG("minecraft:stripped_acacia_log", -8),

    STRIPPED_ACACIA_WOOD("minecraft:stripped_acacia_wood", -823),

    STRIPPED_BAMBOO_BLOCK("minecraft:stripped_bamboo_block", -528),

    STRIPPED_BIRCH_LOG("minecraft:stripped_birch_log", -6),

    STRIPPED_BIRCH_WOOD("minecraft:stripped_birch_wood", -821),

    STRIPPED_CHERRY_LOG("minecraft:stripped_cherry_log", -535),

    STRIPPED_CHERRY_WOOD("minecraft:stripped_cherry_wood", -545),

    STRIPPED_CRIMSON_HYPHAE("minecraft:stripped_crimson_hyphae", -300),

    STRIPPED_CRIMSON_STEM("minecraft:stripped_crimson_stem", -240),

    STRIPPED_DARK_OAK_LOG("minecraft:stripped_dark_oak_log", -9),

    STRIPPED_DARK_OAK_WOOD("minecraft:stripped_dark_oak_wood", -824),

    STRIPPED_JUNGLE_LOG("minecraft:stripped_jungle_log", -7),

    STRIPPED_JUNGLE_WOOD("minecraft:stripped_jungle_wood", -822),

    STRIPPED_MANGROVE_LOG("minecraft:stripped_mangrove_log", -485),

    STRIPPED_MANGROVE_WOOD("minecraft:stripped_mangrove_wood", -498),

    STRIPPED_OAK_LOG("minecraft:stripped_oak_log", -10),

    STRIPPED_OAK_WOOD("minecraft:stripped_oak_wood", -819),

    STRIPPED_PALE_OAK_LOG("minecraft:stripped_pale_oak_log", -994),

    STRIPPED_PALE_OAK_WOOD("minecraft:stripped_pale_oak_wood", -1004),

    STRIPPED_SPRUCE_LOG("minecraft:stripped_spruce_log", -5),

    STRIPPED_SPRUCE_WOOD("minecraft:stripped_spruce_wood", -820),

    STRIPPED_WARPED_HYPHAE("minecraft:stripped_warped_hyphae", -301),

    STRIPPED_WARPED_STEM("minecraft:stripped_warped_stem", -241),

    STRUCTURE_BLOCK("minecraft:structure_block", 252),

    STRUCTURE_VOID("minecraft:structure_void", 217),

    SUGAR("minecraft:sugar", 442),

    SUGAR_CANE("minecraft:sugar_cane", 411),

    SUNFLOWER("minecraft:sunflower", 175),

    SUSPICIOUS_GRAVEL("minecraft:suspicious_gravel", -573),

    SUSPICIOUS_SAND("minecraft:suspicious_sand", -529),

    SUSPICIOUS_STEW("minecraft:suspicious_stew", 625),

    SWEET_BERRIES("minecraft:sweet_berries", 309),

    SWEET_BERRY_BUSH("minecraft:sweet_berry_bush", -207),

    TADPOLE_BUCKET("minecraft:tadpole_bucket", 666),

    TADPOLE_SPAWN_EGG("minecraft:tadpole_spawn_egg", 665),

    TALL_DRY_GRASS("minecraft:tall_dry_grass", -1029),

    TALL_GRASS("minecraft:tall_grass", -864),

    TALLGRASS("minecraft:tallgrass", 803),

    TARGET("minecraft:target", -239),

    TIDE_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:tide_armor_trim_smithing_template", 724),

    TINTED_GLASS("minecraft:tinted_glass", -334),

    TNT("minecraft:tnt", 46),

    TNT_MINECART("minecraft:tnt_minecart", 558),

    TORCH("minecraft:torch", 50),

    TORCHFLOWER("minecraft:torchflower", -568),

    TORCHFLOWER_CROP("minecraft:torchflower_crop", -567),

    TORCHFLOWER_SEEDS("minecraft:torchflower_seeds", 318),

    TOTEM_OF_UNDYING("minecraft:totem_of_undying", 601),

    TRADER_LLAMA_SPAWN_EGG("minecraft:trader_llama_spawn_egg", 684),

    TRAPDOOR("minecraft:trapdoor", 96),

    TRAPPED_CHEST("minecraft:trapped_chest", 146),

    TRIAL_KEY("minecraft:trial_key", 276),

    TRIAL_SPAWNER("minecraft:trial_spawner", -315),

    TRIDENT("minecraft:trident", 579),

    TRIP_WIRE("minecraft:trip_wire", 132),

    TRIPWIRE_HOOK("minecraft:tripwire_hook", 131),

    TROPICAL_FISH("minecraft:tropical_fish", 288),

    TROPICAL_FISH_BUCKET("minecraft:tropical_fish_bucket", 391),

    TROPICAL_FISH_SPAWN_EGG("minecraft:tropical_fish_spawn_egg", 507),

    TUBE_CORAL("minecraft:tube_coral", -131),

    TUBE_CORAL_BLOCK("minecraft:tube_coral_block", -132),

    TUBE_CORAL_FAN("minecraft:tube_coral_fan", -133),

    TUBE_CORAL_WALL_FAN("minecraft:tube_coral_wall_fan", -135),

    TUFF("minecraft:tuff", -333),

    TUFF_BRICK_DOUBLE_SLAB("minecraft:tuff_brick_double_slab", -756),

    TUFF_BRICK_SLAB("minecraft:tuff_brick_slab", -755),

    TUFF_BRICK_STAIRS("minecraft:tuff_brick_stairs", -757),

    TUFF_BRICK_WALL("minecraft:tuff_brick_wall", -758),

    TUFF_BRICKS("minecraft:tuff_bricks", -754),

    TUFF_DOUBLE_SLAB("minecraft:tuff_double_slab", -745),

    TUFF_SLAB("minecraft:tuff_slab", -744),

    TUFF_STAIRS("minecraft:tuff_stairs", -746),

    TUFF_WALL("minecraft:tuff_wall", -747),

    TURTLE_EGG("minecraft:turtle_egg", -159),

    TURTLE_HELMET("minecraft:turtle_helmet", 606),

    TURTLE_SCUTE("minecraft:turtle_scute", 605),

    TURTLE_SPAWN_EGG("minecraft:turtle_spawn_egg", 513),

    TWISTING_VINES("minecraft:twisting_vines", -287),

    UNDERWATER_TNT("minecraft:underwater_tnt", -985),

    UNDERWATER_TORCH("minecraft:underwater_torch", 239),

    UNDYED_SHULKER_BOX("minecraft:undyed_shulker_box", 205),

    UNKNOWN("minecraft:unknown", -305),

    UNLIT_REDSTONE_TORCH("minecraft:unlit_redstone_torch", 75),

    UNPOWERED_COMPARATOR("minecraft:unpowered_comparator", 149),

    UNPOWERED_REPEATER("minecraft:unpowered_repeater", 93),

    VAULT("minecraft:vault", -314),

    VERDANT_FROGLIGHT("minecraft:verdant_froglight", -470),

    VEX_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:vex_armor_trim_smithing_template", 723),

    VEX_SPAWN_EGG("minecraft:vex_spawn_egg", 504),

    VILLAGER_SPAWN_EGG("minecraft:villager_spawn_egg", 476),

    VINDICATOR_SPAWN_EGG("minecraft:vindicator_spawn_egg", 502),

    VINE("minecraft:vine", 106),

    WALL_BANNER("minecraft:wall_banner", 177),

    WALL_SIGN("minecraft:wall_sign", 68),

    WANDERING_TRADER_SPAWN_EGG("minecraft:wandering_trader_spawn_egg", 520),

    WARD_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:ward_armor_trim_smithing_template", 721),

    WARDEN_SPAWN_EGG("minecraft:warden_spawn_egg", 668),

    WARPED_BUTTON("minecraft:warped_button", -261),

    WARPED_DOOR("minecraft:warped_door", 654),

    WARPED_DOUBLE_SLAB("minecraft:warped_double_slab", -267),

    WARPED_FENCE("minecraft:warped_fence", -257),

    WARPED_FENCE_GATE("minecraft:warped_fence_gate", -259),

    WARPED_FUNGUS("minecraft:warped_fungus", -229),

    WARPED_FUNGUS_ON_A_STICK("minecraft:warped_fungus_on_a_stick", 655),

    WARPED_HANGING_SIGN("minecraft:warped_hanging_sign", -507),

    WARPED_HYPHAE("minecraft:warped_hyphae", -298),

    WARPED_NYLIUM("minecraft:warped_nylium", -233),

    WARPED_PLANKS("minecraft:warped_planks", -243),

    WARPED_PRESSURE_PLATE("minecraft:warped_pressure_plate", -263),

    WARPED_ROOTS("minecraft:warped_roots", -224),

    WARPED_SHELF("minecraft:warped_shelf", -1058),

    WARPED_SIGN("minecraft:warped_sign", 652),

    WARPED_SLAB("minecraft:warped_slab", -265),

    WARPED_STAIRS("minecraft:warped_stairs", -255),

    WARPED_STANDING_SIGN("minecraft:warped_standing_sign", -251),

    WARPED_STEM("minecraft:warped_stem", -226),

    WARPED_TRAPDOOR("minecraft:warped_trapdoor", -247),

    WARPED_WALL_SIGN("minecraft:warped_wall_sign", -253),

    WARPED_WART_BLOCK("minecraft:warped_wart_block", -227),

    WATER("minecraft:water", 9),

    WATER_BUCKET("minecraft:water_bucket", 387),

    WATERLILY("minecraft:waterlily", 111),

    WAXED_CHISELED_COPPER("minecraft:waxed_chiseled_copper", -764),

    WAXED_COPPER("minecraft:waxed_copper", -344),

    WAXED_COPPER_BARS("minecraft:waxed_copper_bars", -1070),

    WAXED_COPPER_BULB("minecraft:waxed_copper_bulb", -780),

    WAXED_COPPER_CHAIN("minecraft:waxed_copper_chain", -1078),

    WAXED_COPPER_CHEST("minecraft:waxed_copper_chest", -1035),

    WAXED_COPPER_DOOR("minecraft:waxed_copper_door", -788),

    WAXED_COPPER_GOLEM_STATUE("minecraft:waxed_copper_golem_statue", -1043),

    WAXED_COPPER_GRATE("minecraft:waxed_copper_grate", -772),

    WAXED_COPPER_LANTERN("minecraft:waxed_copper_lantern", -1087),

    WAXED_COPPER_TRAPDOOR("minecraft:waxed_copper_trapdoor", -796),

    WAXED_CUT_COPPER("minecraft:waxed_cut_copper", -351),

    WAXED_CUT_COPPER_SLAB("minecraft:waxed_cut_copper_slab", -365),

    WAXED_CUT_COPPER_STAIRS("minecraft:waxed_cut_copper_stairs", -358),

    WAXED_DOUBLE_CUT_COPPER_SLAB("minecraft:waxed_double_cut_copper_slab", -372),

    WAXED_EXPOSED_CHISELED_COPPER("minecraft:waxed_exposed_chiseled_copper", -765),

    WAXED_EXPOSED_COPPER("minecraft:waxed_exposed_copper", -345),

    WAXED_EXPOSED_COPPER_BARS("minecraft:waxed_exposed_copper_bars", -1071),

    WAXED_EXPOSED_COPPER_BULB("minecraft:waxed_exposed_copper_bulb", -781),

    WAXED_EXPOSED_COPPER_CHAIN("minecraft:waxed_exposed_copper_chain", -1079),

    WAXED_EXPOSED_COPPER_CHEST("minecraft:waxed_exposed_copper_chest", -1036),

    WAXED_EXPOSED_COPPER_DOOR("minecraft:waxed_exposed_copper_door", -789),

    WAXED_EXPOSED_COPPER_GOLEM_STATUE("minecraft:waxed_exposed_copper_golem_statue", -1044),

    WAXED_EXPOSED_COPPER_GRATE("minecraft:waxed_exposed_copper_grate", -773),

    WAXED_EXPOSED_COPPER_LANTERN("minecraft:waxed_exposed_copper_lantern", -1088),

    WAXED_EXPOSED_COPPER_TRAPDOOR("minecraft:waxed_exposed_copper_trapdoor", -797),

    WAXED_EXPOSED_CUT_COPPER("minecraft:waxed_exposed_cut_copper", -352),

    WAXED_EXPOSED_CUT_COPPER_SLAB("minecraft:waxed_exposed_cut_copper_slab", -366),

    WAXED_EXPOSED_CUT_COPPER_STAIRS("minecraft:waxed_exposed_cut_copper_stairs", -359),

    WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB("minecraft:waxed_exposed_double_cut_copper_slab", -373),

    WAXED_EXPOSED_LIGHTNING_ROD("minecraft:waxed_exposed_lightning_rod", -1063),

    WAXED_LIGHTNING_ROD("minecraft:waxed_lightning_rod", -1062),

    WAXED_OXIDIZED_CHISELED_COPPER("minecraft:waxed_oxidized_chiseled_copper", -766),

    WAXED_OXIDIZED_COPPER("minecraft:waxed_oxidized_copper", -446),

    WAXED_OXIDIZED_COPPER_BARS("minecraft:waxed_oxidized_copper_bars", -1073),

    WAXED_OXIDIZED_COPPER_BULB("minecraft:waxed_oxidized_copper_bulb", -783),

    WAXED_OXIDIZED_COPPER_CHAIN("minecraft:waxed_oxidized_copper_chain", -1081),

    WAXED_OXIDIZED_COPPER_CHEST("minecraft:waxed_oxidized_copper_chest", -1038),

    WAXED_OXIDIZED_COPPER_DOOR("minecraft:waxed_oxidized_copper_door", -791),

    WAXED_OXIDIZED_COPPER_GOLEM_STATUE("minecraft:waxed_oxidized_copper_golem_statue", -1046),

    WAXED_OXIDIZED_COPPER_GRATE("minecraft:waxed_oxidized_copper_grate", -775),

    WAXED_OXIDIZED_COPPER_LANTERN("minecraft:waxed_oxidized_copper_lantern", -1090),

    WAXED_OXIDIZED_COPPER_TRAPDOOR("minecraft:waxed_oxidized_copper_trapdoor", -799),

    WAXED_OXIDIZED_CUT_COPPER("minecraft:waxed_oxidized_cut_copper", -447),

    WAXED_OXIDIZED_CUT_COPPER_SLAB("minecraft:waxed_oxidized_cut_copper_slab", -449),

    WAXED_OXIDIZED_CUT_COPPER_STAIRS("minecraft:waxed_oxidized_cut_copper_stairs", -448),

    WAXED_OXIDIZED_DOUBLE_CUT_COPPER_SLAB("minecraft:waxed_oxidized_double_cut_copper_slab", -450),

    WAXED_OXIDIZED_LIGHTNING_ROD("minecraft:waxed_oxidized_lightning_rod", -1065),

    WAXED_WEATHERED_CHISELED_COPPER("minecraft:waxed_weathered_chiseled_copper", -767),

    WAXED_WEATHERED_COPPER("minecraft:waxed_weathered_copper", -346),

    WAXED_WEATHERED_COPPER_BARS("minecraft:waxed_weathered_copper_bars", -1072),

    WAXED_WEATHERED_COPPER_BULB("minecraft:waxed_weathered_copper_bulb", -782),

    WAXED_WEATHERED_COPPER_CHAIN("minecraft:waxed_weathered_copper_chain", -1080),

    WAXED_WEATHERED_COPPER_CHEST("minecraft:waxed_weathered_copper_chest", -1037),

    WAXED_WEATHERED_COPPER_DOOR("minecraft:waxed_weathered_copper_door", -790),

    WAXED_WEATHERED_COPPER_GOLEM_STATUE("minecraft:waxed_weathered_copper_golem_statue", -1045),

    WAXED_WEATHERED_COPPER_GRATE("minecraft:waxed_weathered_copper_grate", -774),

    WAXED_WEATHERED_COPPER_LANTERN("minecraft:waxed_weathered_copper_lantern", -1089),

    WAXED_WEATHERED_COPPER_TRAPDOOR("minecraft:waxed_weathered_copper_trapdoor", -798),

    WAXED_WEATHERED_CUT_COPPER("minecraft:waxed_weathered_cut_copper", -353),

    WAXED_WEATHERED_CUT_COPPER_SLAB("minecraft:waxed_weathered_cut_copper_slab", -367),

    WAXED_WEATHERED_CUT_COPPER_STAIRS("minecraft:waxed_weathered_cut_copper_stairs", -360),

    WAXED_WEATHERED_DOUBLE_CUT_COPPER_SLAB("minecraft:waxed_weathered_double_cut_copper_slab", -374),

    WAXED_WEATHERED_LIGHTNING_ROD("minecraft:waxed_weathered_lightning_rod", -1064),

    WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:wayfinder_armor_trim_smithing_template", 729),

    WEATHERED_CHISELED_COPPER("minecraft:weathered_chiseled_copper", -762),

    WEATHERED_COPPER("minecraft:weathered_copper", -342),

    WEATHERED_COPPER_BARS("minecraft:weathered_copper_bars", -1068),

    WEATHERED_COPPER_BULB("minecraft:weathered_copper_bulb", -778),

    WEATHERED_COPPER_CHAIN("minecraft:weathered_copper_chain", -1076),

    WEATHERED_COPPER_CHEST("minecraft:weathered_copper_chest", -1033),

    WEATHERED_COPPER_DOOR("minecraft:weathered_copper_door", -786),

    WEATHERED_COPPER_GOLEM_STATUE("minecraft:weathered_copper_golem_statue", -1041),

    WEATHERED_COPPER_GRATE("minecraft:weathered_copper_grate", -770),

    WEATHERED_COPPER_LANTERN("minecraft:weathered_copper_lantern", -1085),

    WEATHERED_COPPER_TRAPDOOR("minecraft:weathered_copper_trapdoor", -794),

    WEATHERED_CUT_COPPER("minecraft:weathered_cut_copper", -349),

    WEATHERED_CUT_COPPER_SLAB("minecraft:weathered_cut_copper_slab", -363),

    WEATHERED_CUT_COPPER_STAIRS("minecraft:weathered_cut_copper_stairs", -356),

    WEATHERED_DOUBLE_CUT_COPPER_SLAB("minecraft:weathered_double_cut_copper_slab", -370),

    WEATHERED_LIGHTNING_ROD("minecraft:weathered_lightning_rod", -1060),

    WEB("minecraft:web", 30),

    WEEPING_VINES("minecraft:weeping_vines", -231),

    WET_SPONGE("minecraft:wet_sponge", -984),

    WHEAT("minecraft:wheat", 359),

    WHEAT_SEEDS("minecraft:wheat_seeds", 313),

    WHITE_BUNDLE("minecraft:white_bundle", 272),

    WHITE_CANDLE("minecraft:white_candle", -413),

    WHITE_CANDLE_CAKE("minecraft:white_candle_cake", -430),

    WHITE_CARPET("minecraft:white_carpet", 171),

    WHITE_CONCRETE("minecraft:white_concrete", 236),

    WHITE_CONCRETE_POWDER("minecraft:white_concrete_powder", 237),

    WHITE_DYE("minecraft:white_dye", 436),

    WHITE_GLAZED_TERRACOTTA("minecraft:white_glazed_terracotta", 220),

    WHITE_HARNESS("minecraft:white_harness", 765),

    WHITE_SHULKER_BOX("minecraft:white_shulker_box", 218),

    WHITE_STAINED_GLASS("minecraft:white_stained_glass", 241),

    WHITE_STAINED_GLASS_PANE("minecraft:white_stained_glass_pane", 160),

    WHITE_TERRACOTTA("minecraft:white_terracotta", 159),

    WHITE_TULIP("minecraft:white_tulip", -835),

    WHITE_WOOL("minecraft:white_wool", 35),

    WILD_ARMOR_TRIM_SMITHING_TEMPLATE("minecraft:wild_armor_trim_smithing_template", 720),

    WILDFLOWERS("minecraft:wildflowers", -1024),

    WIND_CHARGE("minecraft:wind_charge", 277),

    WITCH_SPAWN_EGG("minecraft:witch_spawn_egg", 479),

    WITHER_ROSE("minecraft:wither_rose", -216),

    WITHER_SKELETON_SKULL("minecraft:wither_skeleton_skull", -965),

    WITHER_SKELETON_SPAWN_EGG("minecraft:wither_skeleton_spawn_egg", 492),

    WITHER_SPAWN_EGG("minecraft:wither_spawn_egg", 536),

    WOLF_ARMOR("minecraft:wolf_armor", 740),

    WOLF_SPAWN_EGG("minecraft:wolf_spawn_egg", 466),

    WOOD("minecraft:wood", 811),

    WOODEN_AXE("minecraft:wooden_axe", 335),

    WOODEN_BUTTON("minecraft:wooden_button", 143),

    WOODEN_DOOR("minecraft:wooden_door", 384),

    WOODEN_HOE("minecraft:wooden_hoe", 354),

    WOODEN_PICKAXE("minecraft:wooden_pickaxe", 334),

    WOODEN_PRESSURE_PLATE("minecraft:wooden_pressure_plate", 72),

    WOODEN_SHOVEL("minecraft:wooden_shovel", 333),

    WOODEN_SLAB("minecraft:wooden_slab", 798),

    WOODEN_SWORD("minecraft:wooden_sword", 332),

    WOOL("minecraft:wool", 779),

    WRITABLE_BOOK("minecraft:writable_book", 544),

    WRITTEN_BOOK("minecraft:written_book", 545),

    YELLOW_BUNDLE("minecraft:yellow_bundle", 273),

    YELLOW_CANDLE("minecraft:yellow_candle", -417),

    YELLOW_CANDLE_CAKE("minecraft:yellow_candle_cake", -434),

    YELLOW_CARPET("minecraft:yellow_carpet", -600),

    YELLOW_CONCRETE("minecraft:yellow_concrete", -631),

    YELLOW_CONCRETE_POWDER("minecraft:yellow_concrete_powder", -712),

    YELLOW_DYE("minecraft:yellow_dye", 432),

    YELLOW_GLAZED_TERRACOTTA("minecraft:yellow_glazed_terracotta", 224),

    YELLOW_HARNESS("minecraft:yellow_harness", 766),

    YELLOW_SHULKER_BOX("minecraft:yellow_shulker_box", -616),

    YELLOW_STAINED_GLASS("minecraft:yellow_stained_glass", -676),

    YELLOW_STAINED_GLASS_PANE("minecraft:yellow_stained_glass_pane", -646),

    YELLOW_TERRACOTTA("minecraft:yellow_terracotta", -727),

    YELLOW_WOOL("minecraft:yellow_wool", -558),

    ZOGLIN_SPAWN_EGG("minecraft:zoglin_spawn_egg", 526),

    ZOMBIE_HEAD("minecraft:zombie_head", -966),

    ZOMBIE_HORSE_SPAWN_EGG("minecraft:zombie_horse_spawn_egg", 496),

    ZOMBIE_PIGMAN_SPAWN_EGG("minecraft:zombie_pigman_spawn_egg", 475),

    ZOMBIE_SPAWN_EGG("minecraft:zombie_spawn_egg", 474),

    ZOMBIE_VILLAGER_SPAWN_EGG("minecraft:zombie_villager_spawn_egg", 505);

    @Getter
    private final Identifier identifier;

    @Getter
    private final int runtimeId;

    ItemId(String identifier, int runtimeId) {
        this.identifier = new Identifier(identifier);
        this.runtimeId = runtimeId;
    }

    public static ItemId fromIdentifier(Identifier identifier) {
        try {
            return valueOf(identifier.path().replace(".", "_").toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ignore) {
            return null;
        }
    }

    public ItemType<?> getItemType() {
        return Registries.ITEMS.get(this.getIdentifier());
    }
}
