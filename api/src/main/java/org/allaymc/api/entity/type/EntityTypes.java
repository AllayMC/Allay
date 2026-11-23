package org.allaymc.api.entity.type;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityAreaEffectCloud;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityBogged;
import org.allaymc.api.entity.interfaces.EntityBreezeWindChargeProjectile;
import org.allaymc.api.entity.interfaces.EntityChicken;
import org.allaymc.api.entity.interfaces.EntityCow;
import org.allaymc.api.entity.interfaces.EntityDrowned;
import org.allaymc.api.entity.interfaces.EntityEgg;
import org.allaymc.api.entity.interfaces.EntityEnderCrystal;
import org.allaymc.api.entity.interfaces.EntityEnderDragon;
import org.allaymc.api.entity.interfaces.EntityEnderPearl;
import org.allaymc.api.entity.interfaces.EntityFallingBlock;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.api.entity.interfaces.EntityHusk;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityLightningBolt;
import org.allaymc.api.entity.interfaces.EntityLingeringPotion;
import org.allaymc.api.entity.interfaces.EntityPainting;
import org.allaymc.api.entity.interfaces.EntityPhantom;
import org.allaymc.api.entity.interfaces.EntityPig;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntitySheep;
import org.allaymc.api.entity.interfaces.EntitySkeleton;
import org.allaymc.api.entity.interfaces.EntitySkeletonHorse;
import org.allaymc.api.entity.interfaces.EntitySmallFireball;
import org.allaymc.api.entity.interfaces.EntitySnowball;
import org.allaymc.api.entity.interfaces.EntitySplashPotion;
import org.allaymc.api.entity.interfaces.EntityStray;
import org.allaymc.api.entity.interfaces.EntityThrownTrident;
import org.allaymc.api.entity.interfaces.EntityTnt;
import org.allaymc.api.entity.interfaces.EntityVillagerV2;
import org.allaymc.api.entity.interfaces.EntityWarden;
import org.allaymc.api.entity.interfaces.EntityWindChargeProjectile;
import org.allaymc.api.entity.interfaces.EntityWither;
import org.allaymc.api.entity.interfaces.EntityWitherSkeleton;
import org.allaymc.api.entity.interfaces.EntityXpBottle;
import org.allaymc.api.entity.interfaces.EntityXpOrb;
import org.allaymc.api.entity.interfaces.EntityZoglin;
import org.allaymc.api.entity.interfaces.EntityZombie;
import org.allaymc.api.entity.interfaces.EntityZombieHorse;
import org.allaymc.api.entity.interfaces.EntityZombiePigman;
import org.allaymc.api.entity.interfaces.EntityZombieVillager;
import org.allaymc.api.entity.interfaces.EntityZombieVillagerV2;

public final class EntityTypes {
    public static EntityType<Entity> AGENT;

    public static EntityType<Entity> ALLAY;

    public static EntityType<EntityAreaEffectCloud> AREA_EFFECT_CLOUD;

    public static EntityType<Entity> ARMADILLO;

    public static EntityType<EntityArmorStand> ARMOR_STAND;

    public static EntityType<EntityArrow> ARROW;

    public static EntityType<Entity> AXOLOTL;

    public static EntityType<Entity> BALLOON;

    public static EntityType<Entity> BAT;

    public static EntityType<Entity> BEE;

    public static EntityType<Entity> BLAZE;

    public static EntityType<Entity> BOAT;

    public static EntityType<EntityBogged> BOGGED;

    public static EntityType<Entity> BREEZE;

    public static EntityType<EntityBreezeWindChargeProjectile> BREEZE_WIND_CHARGE_PROJECTILE;

    public static EntityType<Entity> CAMEL;

    public static EntityType<Entity> CAMEL_HUSK;

    public static EntityType<Entity> CAT;

    public static EntityType<Entity> CAVE_SPIDER;

    public static EntityType<Entity> CHEST_BOAT;

    public static EntityType<Entity> CHEST_MINECART;

    public static EntityType<EntityChicken> CHICKEN;

    public static EntityType<Entity> COD;

    public static EntityType<Entity> COMMAND_BLOCK_MINECART;

    public static EntityType<Entity> COPPER_GOLEM;

    public static EntityType<EntityCow> COW;

    public static EntityType<Entity> CREAKING;

    public static EntityType<Entity> CREEPER;

    public static EntityType<Entity> DOLPHIN;

    public static EntityType<Entity> DONKEY;

    public static EntityType<Entity> DRAGON_FIREBALL;

    public static EntityType<EntityDrowned> DROWNED;

    public static EntityType<EntityEgg> EGG;

    public static EntityType<Entity> ELDER_GUARDIAN;

    public static EntityType<Entity> ELDER_GUARDIAN_GHOST;

    public static EntityType<EntityEnderCrystal> ENDER_CRYSTAL;

    public static EntityType<EntityEnderDragon> ENDER_DRAGON;

    public static EntityType<EntityEnderPearl> ENDER_PEARL;

    public static EntityType<Entity> ENDERMAN;

    public static EntityType<Entity> ENDERMITE;

    public static EntityType<Entity> EVOCATION_FANG;

    public static EntityType<Entity> EVOCATION_ILLAGER;

    public static EntityType<Entity> EYE_OF_ENDER_SIGNAL;

    public static EntityType<EntityFallingBlock> FALLING_BLOCK;

    public static EntityType<Entity> FIREBALL;

    public static EntityType<EntityFireworksRocket> FIREWORKS_ROCKET;

    public static EntityType<EntityFishingHook> FISHING_HOOK;

    public static EntityType<Entity> FOX;

    public static EntityType<Entity> FROG;

    public static EntityType<Entity> GHAST;

    public static EntityType<Entity> GLOW_SQUID;

    public static EntityType<Entity> GOAT;

    public static EntityType<Entity> GUARDIAN;

    public static EntityType<Entity> HAPPY_GHAST;

    public static EntityType<Entity> HOGLIN;

    public static EntityType<Entity> HOPPER_MINECART;

    public static EntityType<Entity> HORSE;

    public static EntityType<EntityHusk> HUSK;

    public static EntityType<Entity> ICE_BOMB;

    public static EntityType<Entity> IRON_GOLEM;

    public static EntityType<EntityItem> ITEM;

    public static EntityType<Entity> LEASH_KNOT;

    public static EntityType<EntityLightningBolt> LIGHTNING_BOLT;

    public static EntityType<EntityLingeringPotion> LINGERING_POTION;

    public static EntityType<Entity> LLAMA;

    public static EntityType<Entity> LLAMA_SPIT;

    public static EntityType<Entity> MAGMA_CUBE;

    public static EntityType<Entity> MINECART;

    public static EntityType<Entity> MOOSHROOM;

    public static EntityType<Entity> MULE;

    public static EntityType<Entity> NAUTILUS;

    public static EntityType<Entity> NPC;

    public static EntityType<Entity> OCELOT;

    public static EntityType<Entity> OMINOUS_ITEM_SPAWNER;

    public static EntityType<EntityPainting> PAINTING;

    public static EntityType<Entity> PANDA;

    public static EntityType<Entity> PARCHED;

    public static EntityType<Entity> PARROT;

    public static EntityType<EntityPhantom> PHANTOM;

    public static EntityType<EntityPig> PIG;

    public static EntityType<Entity> PIGLIN;

    public static EntityType<Entity> PIGLIN_BRUTE;

    public static EntityType<Entity> PILLAGER;

    public static EntityType<EntityPlayer> PLAYER;

    public static EntityType<Entity> POLAR_BEAR;

    public static EntityType<Entity> PUFFERFISH;

    public static EntityType<Entity> RABBIT;

    public static EntityType<Entity> RAVAGER;

    public static EntityType<Entity> SALMON;

    public static EntityType<EntitySheep> SHEEP;

    public static EntityType<Entity> SHULKER;

    public static EntityType<Entity> SHULKER_BULLET;

    public static EntityType<Entity> SILVERFISH;

    public static EntityType<EntitySkeleton> SKELETON;

    public static EntityType<EntitySkeletonHorse> SKELETON_HORSE;

    public static EntityType<Entity> SLIME;

    public static EntityType<EntitySmallFireball> SMALL_FIREBALL;

    public static EntityType<Entity> SNIFFER;

    public static EntityType<Entity> SNOW_GOLEM;

    public static EntityType<EntitySnowball> SNOWBALL;

    public static EntityType<Entity> SPIDER;

    public static EntityType<EntitySplashPotion> SPLASH_POTION;

    public static EntityType<Entity> SQUID;

    public static EntityType<EntityStray> STRAY;

    public static EntityType<Entity> STRIDER;

    public static EntityType<Entity> SULFUR_CUBE;

    public static EntityType<Entity> TADPOLE;

    public static EntityType<EntityThrownTrident> THROWN_TRIDENT;

    public static EntityType<EntityTnt> TNT;

    public static EntityType<Entity> TNT_MINECART;

    public static EntityType<Entity> TRADER_LLAMA;

    public static EntityType<Entity> TRIPOD_CAMERA;

    public static EntityType<Entity> TROPICALFISH;

    public static EntityType<Entity> TURTLE;

    public static EntityType<Entity> VEX;

    public static EntityType<Entity> VILLAGER;

    public static EntityType<EntityVillagerV2> VILLAGER_V2;

    public static EntityType<Entity> VINDICATOR;

    public static EntityType<Entity> WANDERING_TRADER;

    public static EntityType<EntityWarden> WARDEN;

    public static EntityType<EntityWindChargeProjectile> WIND_CHARGE_PROJECTILE;

    public static EntityType<Entity> WITCH;

    public static EntityType<EntityWither> WITHER;

    public static EntityType<EntityWitherSkeleton> WITHER_SKELETON;

    public static EntityType<Entity> WITHER_SKULL;

    public static EntityType<Entity> WITHER_SKULL_DANGEROUS;

    public static EntityType<Entity> WOLF;

    public static EntityType<EntityXpBottle> XP_BOTTLE;

    public static EntityType<EntityXpOrb> XP_ORB;

    public static EntityType<EntityZoglin> ZOGLIN;

    public static EntityType<EntityZombie> ZOMBIE;

    public static EntityType<EntityZombieHorse> ZOMBIE_HORSE;

    public static EntityType<Entity> ZOMBIE_NAUTILUS;

    public static EntityType<EntityZombiePigman> ZOMBIE_PIGMAN;

    public static EntityType<EntityZombieVillager> ZOMBIE_VILLAGER;

    public static EntityType<EntityZombieVillagerV2> ZOMBIE_VILLAGER_V2;
}
