package org.allaymc.api.item.data;

import org.allaymc.api.annotation.MinecraftVersionSensitive;

import java.util.HashMap;
import java.util.Map;

@MinecraftVersionSensitive
public interface ItemTags {
    Map<String, ItemTag> NAME_TO_TAG = new HashMap<>();

    ItemTag IS_TOOL = create("minecraft:is_tool");

    ItemTag STONE_TOOL_MATERIALS = create("minecraft:stone_tool_materials");

    ItemTag CRIMSON_STEMS = create("minecraft:crimson_stems");

    ItemTag IS_PICKAXE = create("minecraft:is_pickaxe");

    ItemTag TRIMMABLE_ARMORS = create("minecraft:trimmable_armors");

    ItemTag IS_HOE = create("minecraft:is_hoe");

    ItemTag BOOKSHELF_BOOKS = create("minecraft:bookshelf_books");

    ItemTag HARNESS = create("minecraft:harness");

    ItemTag PLANKS = create("minecraft:planks");

    ItemTag ARROW = create("minecraft:arrow");

    ItemTag BOAT = create("minecraft:boat");

    ItemTag IS_SWORD = create("minecraft:is_sword");

    ItemTag TRANSFORMABLE_ITEMS = create("minecraft:transformable_items");

    ItemTag STONE_BRICKS = create("minecraft:stone_bricks");

    ItemTag DOOR = create("minecraft:door");

    ItemTag COALS = create("minecraft:coals");

    ItemTag WOODEN_SLABS = create("minecraft:wooden_slabs");

    ItemTag BANNER = create("minecraft:banner");

    ItemTag IS_COOKED = create("minecraft:is_cooked");

    ItemTag SPAWN_EGG = create("minecraft:spawn_egg");

    ItemTag LOGS_THAT_BURN = create("minecraft:logs_that_burn");

    ItemTag IS_MEAT = create("minecraft:is_meat");

    ItemTag SAND = create("minecraft:sand");

    ItemTag DIAMOND_TIER = create("minecraft:diamond_tier");

    ItemTag STONE_CRAFTING_MATERIALS = create("minecraft:stone_crafting_materials");

    ItemTag TRIM_TEMPLATES = create("minecraft:trim_templates");

    ItemTag BEACON_PAYMENT = create("minecraft:beacon_payment");

    ItemTag IS_FOOD = create("minecraft:is_food");

    ItemTag GOLDEN_TIER = create("minecraft:golden_tier");

    ItemTag IS_FISH = create("minecraft:is_fish");

    ItemTag SOUL_FIRE_BASE_BLOCKS = create("minecraft:soul_fire_base_blocks");

    ItemTag IS_SHOVEL = create("minecraft:is_shovel");

    ItemTag TRIM_MATERIALS = create("minecraft:trim_materials");

    ItemTag TRANSFORM_MATERIALS = create("minecraft:transform_materials");

    ItemTag DECORATED_POT_SHERDS = create("minecraft:decorated_pot_sherds");

    ItemTag DYE = create("minecraft:dye");

    ItemTag NAUTILUS_ARMOR = create("minecraft:nautilus_armor");

    ItemTag VIBRATION_DAMPER = create("minecraft:vibration_damper");

    ItemTag LEATHER_TIER = create("minecraft:leather_tier");

    ItemTag IS_SHEARS = create("minecraft:is_shears");

    ItemTag BOATS = create("minecraft:boats");

    ItemTag MANGROVE_LOGS = create("minecraft:mangrove_logs");

    ItemTag WOODEN_TIER = create("minecraft:wooden_tier");

    ItemTag EGG = create("minecraft:egg");

    ItemTag TRANSFORM_TEMPLATES = create("minecraft:transform_templates");

    ItemTag IS_ARMOR = create("minecraft:is_armor");

    ItemTag LOGS = create("minecraft:logs");

    ItemTag HANGING_SIGN = create("minecraft:hanging_sign");

    ItemTag LECTERN_BOOKS = create("minecraft:lectern_books");

    ItemTag IRON_TIER = create("minecraft:iron_tier");

    ItemTag IS_TRIDENT = create("minecraft:is_trident");

    ItemTag CHAINMAIL_TIER = create("minecraft:chainmail_tier");

    ItemTag HANGING_ACTOR = create("minecraft:hanging_actor");

    ItemTag IS_MINECART = create("minecraft:is_minecart");

    ItemTag WOOL = create("minecraft:wool");

    ItemTag SIGN = create("minecraft:sign");

    ItemTag COPPER_TIER = create("minecraft:copper_tier");

    ItemTag STONE_TIER = create("minecraft:stone_tier");

    ItemTag WARPED_STEMS = create("minecraft:warped_stems");

    ItemTag HEAD = create("minecraft:head");

    ItemTag DIGGER = create("minecraft:digger");

    ItemTag NETHERITE_TIER = create("minecraft:netherite_tier");

    ItemTag MUSIC_DISC = create("minecraft:music_disc");

    ItemTag HORSE_ARMOR = create("minecraft:horse_armor");

    ItemTag IS_SPEAR = create("minecraft:is_spear");

    ItemTag IS_AXE = create("minecraft:is_axe");

    static ItemTag create(String name) {
        var tag = new ItemTag(name);
        NAME_TO_TAG.put(name, tag);
        return tag;
    }

    static ItemTag getTagByName(String name) {
        return NAME_TO_TAG.get(name);
    }
}
