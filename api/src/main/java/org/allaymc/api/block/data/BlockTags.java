package org.allaymc.api.block.data;

import java.util.HashMap;
import java.util.Map;
import org.allaymc.api.annotation.MinecraftVersionSensitive;

@MinecraftVersionSensitive
public interface BlockTags {
    Map<String, BlockTag> NAME_TO_TAG = new HashMap<>();

    BlockTag FERTILIZE_AREA = create("minecraft:fertilize_area");

    BlockTag WATER = create("minecraft:water");

    BlockTag GRASS = create("minecraft:grass");

    BlockTag DARK_OAK = create("minecraft:dark_oak");

    BlockTag BEACON_BASE = create("minecraft:beacon_base");

    BlockTag LOG = create("minecraft:log");

    BlockTag IS_SWORD_ITEM_DESTRUCTIBLE = create("minecraft:is_sword_item_destructible");

    BlockTag CONCRETE_POWDER = create("minecraft:concrete_powder");

    BlockTag LEAVES_SUPPORTER = create("minecraft:leaves_supporter");

    BlockTag PUMPKIN = create("minecraft:pumpkin");

    BlockTag SPRUCE = create("minecraft:spruce");

    BlockTag SOUL_FIRE_CONVERTER = create("minecraft:soul_fire_converter");

    BlockTag IS_SHOVEL_ITEM_DESTRUCTIBLE = create("minecraft:is_shovel_item_destructible");

    BlockTag STONE = create("minecraft:stone");

    BlockTag SAND = create("minecraft:sand");

    BlockTag STONE_TIER_DESTRUCTIBLE = create("minecraft:stone_tier_destructible");

    BlockTag SNOW = create("minecraft:snow");

    BlockTag IS_PICKAXE_ITEM_DESTRUCTIBLE = create("minecraft:is_pickaxe_item_destructible");

    BlockTag NOT_FEATURE_REPLACEABLE = create("minecraft:not_feature_replaceable");

    BlockTag IS_HOE_ITEM_DESTRUCTIBLE = create("minecraft:is_hoe_item_destructible");

    BlockTag RAIL = create("minecraft:rail");

    BlockTag CROP = create("minecraft:crop");

    BlockTag MOB_SPAWNER = create("minecraft:mob_spawner");

    BlockTag INFINITE_FIRE_SUPPORTER = create("minecraft:infinite_fire_supporter");

    BlockTag BREAK_WHEN_PUSHED = create("minecraft:break_when_pushed");

    BlockTag DIAMOND_TIER_DESTRUCTIBLE = create("minecraft:diamond_tier_destructible");

    BlockTag IS_SHEARS_ITEM_DESTRUCTIBLE = create("minecraft:is_shears_item_destructible");

    BlockTag DIRT = create("minecraft:dirt");

    BlockTag IRON_TIER_DESTRUCTIBLE = create("minecraft:iron_tier_destructible");

    BlockTag DIAMOND_PICK_DIGGABLE = create("minecraft:diamond_pick_diggable");

    BlockTag GRAVEL = create("minecraft:gravel");

    BlockTag ONE_WAY_COLLIDABLE = create("minecraft:one_way_collidable");

    BlockTag BIRCH = create("minecraft:birch");

    BlockTag JUNGLE = create("minecraft:jungle");

    BlockTag ACACIA = create("minecraft:acacia");

    BlockTag WOOD = create("minecraft:wood");

    BlockTag TEXT_SIGN = create("minecraft:text_sign");

    BlockTag IS_AXE_ITEM_DESTRUCTIBLE = create("minecraft:is_axe_item_destructible");

    BlockTag PLANT = create("minecraft:plant");

    BlockTag IRON_PICK_DIGGABLE = create("minecraft:iron_pick_diggable");

    BlockTag OAK = create("minecraft:oak");

    BlockTag WOOL = create("minecraft:wool");

    BlockTag POTTABLE_PLANT = create("minecraft:pottable_plant");

    BlockTag INFESTED = create("minecraft:infested");

    BlockTag POWER_SOURCE = create("minecraft:power_source");

    BlockTag UNPULLABLE = create("minecraft:unpullable");

    BlockTag REDSTONE_WIRE_CONNECT_TO = create("minecraft:redstone_wire_connect_to");

    BlockTag CAN_STICK_BLOCKS = create("minecraft:can_stick_blocks");

    BlockTag LAVA = create("minecraft:lava");

    BlockTag METAL = create("minecraft:metal");

    BlockTag REPLACEABLE = create("minecraft:replaceable");

    BlockTag TRAPDOORS = create("minecraft:trapdoors");

    BlockTag STONE_PICK_DIGGABLE = create("minecraft:stone_pick_diggable");

    BlockTag UNPUSHABLE = create("minecraft:unpushable");

    BlockTag LEAVES = create("minecraft:leaves");

    static BlockTag create(String name) {
        var tag = new BlockTag(name);
        NAME_TO_TAG.put(name, tag);
        return tag;
    }

    static BlockTag getTagByName(String name) {
        return NAME_TO_TAG.get(name);
    }
}
