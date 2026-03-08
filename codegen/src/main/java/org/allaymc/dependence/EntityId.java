package org.allaymc.dependence;

import lombok.Getter;

public enum EntityId {
    AGENT("minecraft:agent"),

    ALLAY("minecraft:allay"),

    AREA_EFFECT_CLOUD("minecraft:area_effect_cloud"),

    ARMADILLO("minecraft:armadillo"),

    ARMOR_STAND("minecraft:armor_stand"),

    ARROW("minecraft:arrow"),

    AXOLOTL("minecraft:axolotl"),

    BALLOON("minecraft:balloon"),

    BAT("minecraft:bat"),

    BEE("minecraft:bee"),

    BLAZE("minecraft:blaze"),

    BOAT("minecraft:boat"),

    BOGGED("minecraft:bogged"),

    BREEZE("minecraft:breeze"),

    BREEZE_WIND_CHARGE_PROJECTILE("minecraft:breeze_wind_charge_projectile"),

    CAMEL("minecraft:camel"),

    CAMEL_HUSK("minecraft:camel_husk"),

    CAT("minecraft:cat"),

    CAVE_SPIDER("minecraft:cave_spider"),

    CHEST_BOAT("minecraft:chest_boat"),

    CHEST_MINECART("minecraft:chest_minecart"),

    CHICKEN("minecraft:chicken"),

    COD("minecraft:cod"),

    COMMAND_BLOCK_MINECART("minecraft:command_block_minecart"),

    COPPER_GOLEM("minecraft:copper_golem"),

    COW("minecraft:cow"),

    CREAKING("minecraft:creaking"),

    CREEPER("minecraft:creeper"),

    DOLPHIN("minecraft:dolphin"),

    DONKEY("minecraft:donkey"),

    DRAGON_FIREBALL("minecraft:dragon_fireball"),

    DROWNED("minecraft:drowned"),

    EGG("minecraft:egg"),

    ELDER_GUARDIAN("minecraft:elder_guardian"),

    ELDER_GUARDIAN_GHOST("minecraft:elder_guardian_ghost"),

    ENDER_CRYSTAL("minecraft:ender_crystal"),

    ENDER_DRAGON("minecraft:ender_dragon"),

    ENDER_PEARL("minecraft:ender_pearl"),

    ENDERMAN("minecraft:enderman"),

    ENDERMITE("minecraft:endermite"),

    EVOCATION_FANG("minecraft:evocation_fang"),

    EVOCATION_ILLAGER("minecraft:evocation_illager"),

    EYE_OF_ENDER_SIGNAL("minecraft:eye_of_ender_signal"),

    FALLING_BLOCK("minecraft:falling_block"),

    FIREBALL("minecraft:fireball"),

    FIREWORKS_ROCKET("minecraft:fireworks_rocket"),

    FISHING_HOOK("minecraft:fishing_hook"),

    FOX("minecraft:fox"),

    FROG("minecraft:frog"),

    GHAST("minecraft:ghast"),

    GLOW_SQUID("minecraft:glow_squid"),

    GOAT("minecraft:goat"),

    GUARDIAN("minecraft:guardian"),

    HAPPY_GHAST("minecraft:happy_ghast"),

    HOGLIN("minecraft:hoglin"),

    HOPPER_MINECART("minecraft:hopper_minecart"),

    HORSE("minecraft:horse"),

    HUSK("minecraft:husk"),

    ICE_BOMB("minecraft:ice_bomb"),

    IRON_GOLEM("minecraft:iron_golem"),

    ITEM("minecraft:item"),

    LEASH_KNOT("minecraft:leash_knot"),

    LIGHTNING_BOLT("minecraft:lightning_bolt"),

    LINGERING_POTION("minecraft:lingering_potion"),

    LLAMA("minecraft:llama"),

    LLAMA_SPIT("minecraft:llama_spit"),

    MAGMA_CUBE("minecraft:magma_cube"),

    MINECART("minecraft:minecart"),

    MOOSHROOM("minecraft:mooshroom"),

    MULE("minecraft:mule"),

    NAUTILUS("minecraft:nautilus"),

    NPC("minecraft:npc"),

    OCELOT("minecraft:ocelot"),

    OMINOUS_ITEM_SPAWNER("minecraft:ominous_item_spawner"),

    PAINTING("minecraft:painting"),

    PANDA("minecraft:panda"),

    PARCHED("minecraft:parched"),

    PARROT("minecraft:parrot"),

    PHANTOM("minecraft:phantom"),

    PIG("minecraft:pig"),

    PIGLIN("minecraft:piglin"),

    PIGLIN_BRUTE("minecraft:piglin_brute"),

    PILLAGER("minecraft:pillager"),

    PLAYER("minecraft:player"),

    POLAR_BEAR("minecraft:polar_bear"),

    PUFFERFISH("minecraft:pufferfish"),

    RABBIT("minecraft:rabbit"),

    RAVAGER("minecraft:ravager"),

    SALMON("minecraft:salmon"),

    SHEEP("minecraft:sheep"),

    SHULKER("minecraft:shulker"),

    SHULKER_BULLET("minecraft:shulker_bullet"),

    SILVERFISH("minecraft:silverfish"),

    SKELETON("minecraft:skeleton"),

    SKELETON_HORSE("minecraft:skeleton_horse"),

    SLIME("minecraft:slime"),

    SMALL_FIREBALL("minecraft:small_fireball"),

    SNIFFER("minecraft:sniffer"),

    SNOW_GOLEM("minecraft:snow_golem"),

    SNOWBALL("minecraft:snowball"),

    SPIDER("minecraft:spider"),

    SPLASH_POTION("minecraft:splash_potion"),

    SQUID("minecraft:squid"),

    STRAY("minecraft:stray"),

    STRIDER("minecraft:strider"),

    TADPOLE("minecraft:tadpole"),

    THROWN_TRIDENT("minecraft:thrown_trident"),

    TNT("minecraft:tnt"),

    TNT_MINECART("minecraft:tnt_minecart"),

    TRADER_LLAMA("minecraft:trader_llama"),

    TRIPOD_CAMERA("minecraft:tripod_camera"),

    TROPICALFISH("minecraft:tropicalfish"),

    TURTLE("minecraft:turtle"),

    VEX("minecraft:vex"),

    VILLAGER("minecraft:villager"),

    VILLAGER_V2("minecraft:villager_v2"),

    VINDICATOR("minecraft:vindicator"),

    WANDERING_TRADER("minecraft:wandering_trader"),

    WARDEN("minecraft:warden"),

    WIND_CHARGE_PROJECTILE("minecraft:wind_charge_projectile"),

    WITCH("minecraft:witch"),

    WITHER("minecraft:wither"),

    WITHER_SKELETON("minecraft:wither_skeleton"),

    WITHER_SKULL("minecraft:wither_skull"),

    WITHER_SKULL_DANGEROUS("minecraft:wither_skull_dangerous"),

    WOLF("minecraft:wolf"),

    XP_BOTTLE("minecraft:xp_bottle"),

    XP_ORB("minecraft:xp_orb"),

    ZOGLIN("minecraft:zoglin"),

    ZOMBIE("minecraft:zombie"),

    ZOMBIE_HORSE("minecraft:zombie_horse"),

    ZOMBIE_NAUTILUS("minecraft:zombie_nautilus"),

    ZOMBIE_PIGMAN("minecraft:zombie_pigman"),

    ZOMBIE_VILLAGER("minecraft:zombie_villager"),

    ZOMBIE_VILLAGER_V2("minecraft:zombie_villager_v2");

    @Getter
    private final Identifier identifier;

    EntityId(String identifier) {
        this.identifier = new Identifier(identifier);
    }
}
