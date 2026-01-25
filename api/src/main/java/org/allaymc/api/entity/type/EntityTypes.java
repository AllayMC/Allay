package org.allaymc.api.entity.type;

import org.allaymc.api.entity.interfaces.EntityAgent;
import org.allaymc.api.entity.interfaces.EntityAllay;
import org.allaymc.api.entity.interfaces.EntityAreaEffectCloud;
import org.allaymc.api.entity.interfaces.EntityArmadillo;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityAxolotl;
import org.allaymc.api.entity.interfaces.EntityBalloon;
import org.allaymc.api.entity.interfaces.EntityBat;
import org.allaymc.api.entity.interfaces.EntityBee;
import org.allaymc.api.entity.interfaces.EntityBlaze;
import org.allaymc.api.entity.interfaces.EntityBoat;
import org.allaymc.api.entity.interfaces.EntityBogged;
import org.allaymc.api.entity.interfaces.EntityBreeze;
import org.allaymc.api.entity.interfaces.EntityBreezeWindChargeProjectile;
import org.allaymc.api.entity.interfaces.EntityCamel;
import org.allaymc.api.entity.interfaces.EntityCamelHusk;
import org.allaymc.api.entity.interfaces.EntityCat;
import org.allaymc.api.entity.interfaces.EntityCaveSpider;
import org.allaymc.api.entity.interfaces.EntityChestBoat;
import org.allaymc.api.entity.interfaces.EntityChestMinecart;
import org.allaymc.api.entity.interfaces.EntityChicken;
import org.allaymc.api.entity.interfaces.EntityCod;
import org.allaymc.api.entity.interfaces.EntityCommandBlockMinecart;
import org.allaymc.api.entity.interfaces.EntityCopperGolem;
import org.allaymc.api.entity.interfaces.EntityCow;
import org.allaymc.api.entity.interfaces.EntityCreaking;
import org.allaymc.api.entity.interfaces.EntityCreeper;
import org.allaymc.api.entity.interfaces.EntityDolphin;
import org.allaymc.api.entity.interfaces.EntityDonkey;
import org.allaymc.api.entity.interfaces.EntityDragonFireball;
import org.allaymc.api.entity.interfaces.EntityDrowned;
import org.allaymc.api.entity.interfaces.EntityEgg;
import org.allaymc.api.entity.interfaces.EntityElderGuardian;
import org.allaymc.api.entity.interfaces.EntityElderGuardianGhost;
import org.allaymc.api.entity.interfaces.EntityEnderCrystal;
import org.allaymc.api.entity.interfaces.EntityEnderDragon;
import org.allaymc.api.entity.interfaces.EntityEnderPearl;
import org.allaymc.api.entity.interfaces.EntityEnderman;
import org.allaymc.api.entity.interfaces.EntityEndermite;
import org.allaymc.api.entity.interfaces.EntityEvocationFang;
import org.allaymc.api.entity.interfaces.EntityEvocationIllager;
import org.allaymc.api.entity.interfaces.EntityEyeOfEnderSignal;
import org.allaymc.api.entity.interfaces.EntityFallingBlock;
import org.allaymc.api.entity.interfaces.EntityFireball;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.api.entity.interfaces.EntityFox;
import org.allaymc.api.entity.interfaces.EntityFrog;
import org.allaymc.api.entity.interfaces.EntityGhast;
import org.allaymc.api.entity.interfaces.EntityGlowSquid;
import org.allaymc.api.entity.interfaces.EntityGoat;
import org.allaymc.api.entity.interfaces.EntityGuardian;
import org.allaymc.api.entity.interfaces.EntityHappyGhast;
import org.allaymc.api.entity.interfaces.EntityHoglin;
import org.allaymc.api.entity.interfaces.EntityHopperMinecart;
import org.allaymc.api.entity.interfaces.EntityHorse;
import org.allaymc.api.entity.interfaces.EntityHusk;
import org.allaymc.api.entity.interfaces.EntityIceBomb;
import org.allaymc.api.entity.interfaces.EntityIronGolem;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityLeashKnot;
import org.allaymc.api.entity.interfaces.EntityLightningBolt;
import org.allaymc.api.entity.interfaces.EntityLingeringPotion;
import org.allaymc.api.entity.interfaces.EntityLlama;
import org.allaymc.api.entity.interfaces.EntityLlamaSpit;
import org.allaymc.api.entity.interfaces.EntityMagmaCube;
import org.allaymc.api.entity.interfaces.EntityMinecart;
import org.allaymc.api.entity.interfaces.EntityMooshroom;
import org.allaymc.api.entity.interfaces.EntityMule;
import org.allaymc.api.entity.interfaces.EntityNautilus;
import org.allaymc.api.entity.interfaces.EntityNpc;
import org.allaymc.api.entity.interfaces.EntityOcelot;
import org.allaymc.api.entity.interfaces.EntityOminousItemSpawner;
import org.allaymc.api.entity.interfaces.EntityPainting;
import org.allaymc.api.entity.interfaces.EntityPanda;
import org.allaymc.api.entity.interfaces.EntityParched;
import org.allaymc.api.entity.interfaces.EntityParrot;
import org.allaymc.api.entity.interfaces.EntityPhantom;
import org.allaymc.api.entity.interfaces.EntityPig;
import org.allaymc.api.entity.interfaces.EntityPiglin;
import org.allaymc.api.entity.interfaces.EntityPiglinBrute;
import org.allaymc.api.entity.interfaces.EntityPillager;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityPolarBear;
import org.allaymc.api.entity.interfaces.EntityPufferfish;
import org.allaymc.api.entity.interfaces.EntityRabbit;
import org.allaymc.api.entity.interfaces.EntityRavager;
import org.allaymc.api.entity.interfaces.EntitySalmon;
import org.allaymc.api.entity.interfaces.EntitySheep;
import org.allaymc.api.entity.interfaces.EntityShulker;
import org.allaymc.api.entity.interfaces.EntityShulkerBullet;
import org.allaymc.api.entity.interfaces.EntitySilverfish;
import org.allaymc.api.entity.interfaces.EntitySkeleton;
import org.allaymc.api.entity.interfaces.EntitySkeletonHorse;
import org.allaymc.api.entity.interfaces.EntitySlime;
import org.allaymc.api.entity.interfaces.EntitySmallFireball;
import org.allaymc.api.entity.interfaces.EntitySniffer;
import org.allaymc.api.entity.interfaces.EntitySnowGolem;
import org.allaymc.api.entity.interfaces.EntitySnowball;
import org.allaymc.api.entity.interfaces.EntitySpider;
import org.allaymc.api.entity.interfaces.EntitySplashPotion;
import org.allaymc.api.entity.interfaces.EntitySquid;
import org.allaymc.api.entity.interfaces.EntityStray;
import org.allaymc.api.entity.interfaces.EntityStrider;
import org.allaymc.api.entity.interfaces.EntityTadpole;
import org.allaymc.api.entity.interfaces.EntityThrownTrident;
import org.allaymc.api.entity.interfaces.EntityTnt;
import org.allaymc.api.entity.interfaces.EntityTntMinecart;
import org.allaymc.api.entity.interfaces.EntityTraderLlama;
import org.allaymc.api.entity.interfaces.EntityTripodCamera;
import org.allaymc.api.entity.interfaces.EntityTropicalfish;
import org.allaymc.api.entity.interfaces.EntityTurtle;
import org.allaymc.api.entity.interfaces.EntityVex;
import org.allaymc.api.entity.interfaces.EntityVillager;
import org.allaymc.api.entity.interfaces.EntityVillagerV2;
import org.allaymc.api.entity.interfaces.EntityVindicator;
import org.allaymc.api.entity.interfaces.EntityWanderingTrader;
import org.allaymc.api.entity.interfaces.EntityWarden;
import org.allaymc.api.entity.interfaces.EntityWindChargeProjectile;
import org.allaymc.api.entity.interfaces.EntityWitch;
import org.allaymc.api.entity.interfaces.EntityWither;
import org.allaymc.api.entity.interfaces.EntityWitherSkeleton;
import org.allaymc.api.entity.interfaces.EntityWitherSkull;
import org.allaymc.api.entity.interfaces.EntityWitherSkullDangerous;
import org.allaymc.api.entity.interfaces.EntityWolf;
import org.allaymc.api.entity.interfaces.EntityXpBottle;
import org.allaymc.api.entity.interfaces.EntityXpOrb;
import org.allaymc.api.entity.interfaces.EntityZoglin;
import org.allaymc.api.entity.interfaces.EntityZombie;
import org.allaymc.api.entity.interfaces.EntityZombieHorse;
import org.allaymc.api.entity.interfaces.EntityZombieNautilus;
import org.allaymc.api.entity.interfaces.EntityZombiePigman;
import org.allaymc.api.entity.interfaces.EntityZombieVillager;
import org.allaymc.api.entity.interfaces.EntityZombieVillagerV2;

public final class EntityTypes {
    public static EntityType<EntityAgent> AGENT;

    public static EntityType<EntityAllay> ALLAY;

    public static EntityType<EntityAreaEffectCloud> AREA_EFFECT_CLOUD;

    public static EntityType<EntityArmadillo> ARMADILLO;

    public static EntityType<EntityArmorStand> ARMOR_STAND;

    public static EntityType<EntityArrow> ARROW;

    public static EntityType<EntityAxolotl> AXOLOTL;

    public static EntityType<EntityBalloon> BALLOON;

    public static EntityType<EntityBat> BAT;

    public static EntityType<EntityBee> BEE;

    public static EntityType<EntityBlaze> BLAZE;

    public static EntityType<EntityBoat> BOAT;

    public static EntityType<EntityBogged> BOGGED;

    public static EntityType<EntityBreeze> BREEZE;

    public static EntityType<EntityBreezeWindChargeProjectile> BREEZE_WIND_CHARGE_PROJECTILE;

    public static EntityType<EntityCamel> CAMEL;

    public static EntityType<EntityCamelHusk> CAMEL_HUSK;

    public static EntityType<EntityCat> CAT;

    public static EntityType<EntityCaveSpider> CAVE_SPIDER;

    public static EntityType<EntityChestBoat> CHEST_BOAT;

    public static EntityType<EntityChestMinecart> CHEST_MINECART;

    public static EntityType<EntityChicken> CHICKEN;

    public static EntityType<EntityCod> COD;

    public static EntityType<EntityCommandBlockMinecart> COMMAND_BLOCK_MINECART;

    public static EntityType<EntityCopperGolem> COPPER_GOLEM;

    public static EntityType<EntityCow> COW;

    public static EntityType<EntityCreaking> CREAKING;

    public static EntityType<EntityCreeper> CREEPER;

    public static EntityType<EntityDolphin> DOLPHIN;

    public static EntityType<EntityDonkey> DONKEY;

    public static EntityType<EntityDragonFireball> DRAGON_FIREBALL;

    public static EntityType<EntityDrowned> DROWNED;

    public static EntityType<EntityEgg> EGG;

    public static EntityType<EntityElderGuardian> ELDER_GUARDIAN;

    public static EntityType<EntityElderGuardianGhost> ELDER_GUARDIAN_GHOST;

    public static EntityType<EntityEnderCrystal> ENDER_CRYSTAL;

    public static EntityType<EntityEnderDragon> ENDER_DRAGON;

    public static EntityType<EntityEnderPearl> ENDER_PEARL;

    public static EntityType<EntityEnderman> ENDERMAN;

    public static EntityType<EntityEndermite> ENDERMITE;

    public static EntityType<EntityEvocationFang> EVOCATION_FANG;

    public static EntityType<EntityEvocationIllager> EVOCATION_ILLAGER;

    public static EntityType<EntityEyeOfEnderSignal> EYE_OF_ENDER_SIGNAL;

    public static EntityType<EntityFallingBlock> FALLING_BLOCK;

    public static EntityType<EntityFireball> FIREBALL;

    public static EntityType<EntityFireworksRocket> FIREWORKS_ROCKET;

    public static EntityType<EntityFishingHook> FISHING_HOOK;

    public static EntityType<EntityFox> FOX;

    public static EntityType<EntityFrog> FROG;

    public static EntityType<EntityGhast> GHAST;

    public static EntityType<EntityGlowSquid> GLOW_SQUID;

    public static EntityType<EntityGoat> GOAT;

    public static EntityType<EntityGuardian> GUARDIAN;

    public static EntityType<EntityHappyGhast> HAPPY_GHAST;

    public static EntityType<EntityHoglin> HOGLIN;

    public static EntityType<EntityHopperMinecart> HOPPER_MINECART;

    public static EntityType<EntityHorse> HORSE;

    public static EntityType<EntityHusk> HUSK;

    public static EntityType<EntityIceBomb> ICE_BOMB;

    public static EntityType<EntityIronGolem> IRON_GOLEM;

    public static EntityType<EntityItem> ITEM;

    public static EntityType<EntityLeashKnot> LEASH_KNOT;

    public static EntityType<EntityLightningBolt> LIGHTNING_BOLT;

    public static EntityType<EntityLingeringPotion> LINGERING_POTION;

    public static EntityType<EntityLlama> LLAMA;

    public static EntityType<EntityLlamaSpit> LLAMA_SPIT;

    public static EntityType<EntityMagmaCube> MAGMA_CUBE;

    public static EntityType<EntityMinecart> MINECART;

    public static EntityType<EntityMooshroom> MOOSHROOM;

    public static EntityType<EntityMule> MULE;

    public static EntityType<EntityNautilus> NAUTILUS;

    public static EntityType<EntityNpc> NPC;

    public static EntityType<EntityOcelot> OCELOT;

    public static EntityType<EntityOminousItemSpawner> OMINOUS_ITEM_SPAWNER;

    public static EntityType<EntityPainting> PAINTING;

    public static EntityType<EntityPanda> PANDA;

    public static EntityType<EntityParched> PARCHED;

    public static EntityType<EntityParrot> PARROT;

    public static EntityType<EntityPhantom> PHANTOM;

    public static EntityType<EntityPig> PIG;

    public static EntityType<EntityPiglin> PIGLIN;

    public static EntityType<EntityPiglinBrute> PIGLIN_BRUTE;

    public static EntityType<EntityPillager> PILLAGER;

    public static EntityType<EntityPlayer> PLAYER;

    public static EntityType<EntityPolarBear> POLAR_BEAR;

    public static EntityType<EntityPufferfish> PUFFERFISH;

    public static EntityType<EntityRabbit> RABBIT;

    public static EntityType<EntityRavager> RAVAGER;

    public static EntityType<EntitySalmon> SALMON;

    public static EntityType<EntitySheep> SHEEP;

    public static EntityType<EntityShulker> SHULKER;

    public static EntityType<EntityShulkerBullet> SHULKER_BULLET;

    public static EntityType<EntitySilverfish> SILVERFISH;

    public static EntityType<EntitySkeleton> SKELETON;

    public static EntityType<EntitySkeletonHorse> SKELETON_HORSE;

    public static EntityType<EntitySlime> SLIME;

    public static EntityType<EntitySmallFireball> SMALL_FIREBALL;

    public static EntityType<EntitySniffer> SNIFFER;

    public static EntityType<EntitySnowGolem> SNOW_GOLEM;

    public static EntityType<EntitySnowball> SNOWBALL;

    public static EntityType<EntitySpider> SPIDER;

    public static EntityType<EntitySplashPotion> SPLASH_POTION;

    public static EntityType<EntitySquid> SQUID;

    public static EntityType<EntityStray> STRAY;

    public static EntityType<EntityStrider> STRIDER;

    public static EntityType<EntityTadpole> TADPOLE;

    public static EntityType<EntityThrownTrident> THROWN_TRIDENT;

    public static EntityType<EntityTnt> TNT;

    public static EntityType<EntityTntMinecart> TNT_MINECART;

    public static EntityType<EntityTraderLlama> TRADER_LLAMA;

    public static EntityType<EntityTripodCamera> TRIPOD_CAMERA;

    public static EntityType<EntityTropicalfish> TROPICALFISH;

    public static EntityType<EntityTurtle> TURTLE;

    public static EntityType<EntityVex> VEX;

    public static EntityType<EntityVillager> VILLAGER;

    public static EntityType<EntityVillagerV2> VILLAGER_V2;

    public static EntityType<EntityVindicator> VINDICATOR;

    public static EntityType<EntityWanderingTrader> WANDERING_TRADER;

    public static EntityType<EntityWarden> WARDEN;

    public static EntityType<EntityWindChargeProjectile> WIND_CHARGE_PROJECTILE;

    public static EntityType<EntityWitch> WITCH;

    public static EntityType<EntityWither> WITHER;

    public static EntityType<EntityWitherSkeleton> WITHER_SKELETON;

    public static EntityType<EntityWitherSkull> WITHER_SKULL;

    public static EntityType<EntityWitherSkullDangerous> WITHER_SKULL_DANGEROUS;

    public static EntityType<EntityWolf> WOLF;

    public static EntityType<EntityXpBottle> XP_BOTTLE;

    public static EntityType<EntityXpOrb> XP_ORB;

    public static EntityType<EntityZoglin> ZOGLIN;

    public static EntityType<EntityZombie> ZOMBIE;

    public static EntityType<EntityZombieHorse> ZOMBIE_HORSE;

    public static EntityType<EntityZombieNautilus> ZOMBIE_NAUTILUS;

    public static EntityType<EntityZombiePigman> ZOMBIE_PIGMAN;

    public static EntityType<EntityZombieVillager> ZOMBIE_VILLAGER;

    public static EntityType<EntityZombieVillagerV2> ZOMBIE_VILLAGER_V2;
}
