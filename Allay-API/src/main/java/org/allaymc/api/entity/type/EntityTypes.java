package org.allaymc.api.entity.type;

import org.allaymc.api.entity.interfaces.EntityAgent;
import org.allaymc.api.entity.interfaces.EntityAllay;
import org.allaymc.api.entity.interfaces.EntityAreaEffectCloud;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityAxolotl;
import org.allaymc.api.entity.interfaces.EntityBalloon;
import org.allaymc.api.entity.interfaces.EntityBat;
import org.allaymc.api.entity.interfaces.EntityBee;
import org.allaymc.api.entity.interfaces.EntityBlaze;
import org.allaymc.api.entity.interfaces.EntityBoat;
import org.allaymc.api.entity.interfaces.EntityCamel;
import org.allaymc.api.entity.interfaces.EntityCat;
import org.allaymc.api.entity.interfaces.EntityCaveSpider;
import org.allaymc.api.entity.interfaces.EntityChestBoat;
import org.allaymc.api.entity.interfaces.EntityChestMinecart;
import org.allaymc.api.entity.interfaces.EntityChicken;
import org.allaymc.api.entity.interfaces.EntityCod;
import org.allaymc.api.entity.interfaces.EntityCommandBlockMinecart;
import org.allaymc.api.entity.interfaces.EntityCow;
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
import org.allaymc.api.entity.interfaces.EntityNpc;
import org.allaymc.api.entity.interfaces.EntityOcelot;
import org.allaymc.api.entity.interfaces.EntityPainting;
import org.allaymc.api.entity.interfaces.EntityPanda;
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
import org.allaymc.api.entity.interfaces.EntityZombiePigman;
import org.allaymc.api.entity.interfaces.EntityZombieVillager;
import org.allaymc.api.entity.interfaces.EntityZombieVillagerV2;

public final class EntityTypes {
  public static EntityType<EntityAgent> AGENT_TYPE;

  public static EntityType<EntityAllay> ALLAY_TYPE;

  public static EntityType<EntityAreaEffectCloud> AREA_EFFECT_CLOUD_TYPE;

  public static EntityType<EntityArmorStand> ARMOR_STAND_TYPE;

  public static EntityType<EntityArrow> ARROW_TYPE;

  public static EntityType<EntityAxolotl> AXOLOTL_TYPE;

  public static EntityType<EntityBalloon> BALLOON_TYPE;

  public static EntityType<EntityBat> BAT_TYPE;

  public static EntityType<EntityBee> BEE_TYPE;

  public static EntityType<EntityBlaze> BLAZE_TYPE;

  public static EntityType<EntityBoat> BOAT_TYPE;

  public static EntityType<EntityCamel> CAMEL_TYPE;

  public static EntityType<EntityCat> CAT_TYPE;

  public static EntityType<EntityCaveSpider> CAVE_SPIDER_TYPE;

  public static EntityType<EntityChestBoat> CHEST_BOAT_TYPE;

  public static EntityType<EntityChestMinecart> CHEST_MINECART_TYPE;

  public static EntityType<EntityChicken> CHICKEN_TYPE;

  public static EntityType<EntityCod> COD_TYPE;

  public static EntityType<EntityCommandBlockMinecart> COMMAND_BLOCK_MINECART_TYPE;

  public static EntityType<EntityCow> COW_TYPE;

  public static EntityType<EntityCreeper> CREEPER_TYPE;

  public static EntityType<EntityDolphin> DOLPHIN_TYPE;

  public static EntityType<EntityDonkey> DONKEY_TYPE;

  public static EntityType<EntityDragonFireball> DRAGON_FIREBALL_TYPE;

  public static EntityType<EntityDrowned> DROWNED_TYPE;

  public static EntityType<EntityEgg> EGG_TYPE;

  public static EntityType<EntityElderGuardian> ELDER_GUARDIAN_TYPE;

  public static EntityType<EntityElderGuardianGhost> ELDER_GUARDIAN_GHOST_TYPE;

  public static EntityType<EntityEnderCrystal> ENDER_CRYSTAL_TYPE;

  public static EntityType<EntityEnderDragon> ENDER_DRAGON_TYPE;

  public static EntityType<EntityEnderPearl> ENDER_PEARL_TYPE;

  public static EntityType<EntityEnderman> ENDERMAN_TYPE;

  public static EntityType<EntityEndermite> ENDERMITE_TYPE;

  public static EntityType<EntityEvocationFang> EVOCATION_FANG_TYPE;

  public static EntityType<EntityEvocationIllager> EVOCATION_ILLAGER_TYPE;

  public static EntityType<EntityEyeOfEnderSignal> EYE_OF_ENDER_SIGNAL_TYPE;

  public static EntityType<EntityFallingBlock> FALLING_BLOCK_TYPE;

  public static EntityType<EntityFireball> FIREBALL_TYPE;

  public static EntityType<EntityFireworksRocket> FIREWORKS_ROCKET_TYPE;

  public static EntityType<EntityFishingHook> FISHING_HOOK_TYPE;

  public static EntityType<EntityFox> FOX_TYPE;

  public static EntityType<EntityFrog> FROG_TYPE;

  public static EntityType<EntityGhast> GHAST_TYPE;

  public static EntityType<EntityGlowSquid> GLOW_SQUID_TYPE;

  public static EntityType<EntityGoat> GOAT_TYPE;

  public static EntityType<EntityGuardian> GUARDIAN_TYPE;

  public static EntityType<EntityHoglin> HOGLIN_TYPE;

  public static EntityType<EntityHopperMinecart> HOPPER_MINECART_TYPE;

  public static EntityType<EntityHorse> HORSE_TYPE;

  public static EntityType<EntityHusk> HUSK_TYPE;

  public static EntityType<EntityIceBomb> ICE_BOMB_TYPE;

  public static EntityType<EntityIronGolem> IRON_GOLEM_TYPE;

  public static EntityType<EntityItem> ITEM_TYPE;

  public static EntityType<EntityLeashKnot> LEASH_KNOT_TYPE;

  public static EntityType<EntityLightningBolt> LIGHTNING_BOLT_TYPE;

  public static EntityType<EntityLingeringPotion> LINGERING_POTION_TYPE;

  public static EntityType<EntityLlama> LLAMA_TYPE;

  public static EntityType<EntityLlamaSpit> LLAMA_SPIT_TYPE;

  public static EntityType<EntityMagmaCube> MAGMA_CUBE_TYPE;

  public static EntityType<EntityMinecart> MINECART_TYPE;

  public static EntityType<EntityMooshroom> MOOSHROOM_TYPE;

  public static EntityType<EntityMule> MULE_TYPE;

  public static EntityType<EntityNpc> NPC_TYPE;

  public static EntityType<EntityOcelot> OCELOT_TYPE;

  public static EntityType<EntityPainting> PAINTING_TYPE;

  public static EntityType<EntityPanda> PANDA_TYPE;

  public static EntityType<EntityParrot> PARROT_TYPE;

  public static EntityType<EntityPhantom> PHANTOM_TYPE;

  public static EntityType<EntityPig> PIG_TYPE;

  public static EntityType<EntityPiglin> PIGLIN_TYPE;

  public static EntityType<EntityPiglinBrute> PIGLIN_BRUTE_TYPE;

  public static EntityType<EntityPillager> PILLAGER_TYPE;

  public static EntityType<EntityPlayer> PLAYER_TYPE;

  public static EntityType<EntityPolarBear> POLAR_BEAR_TYPE;

  public static EntityType<EntityPufferfish> PUFFERFISH_TYPE;

  public static EntityType<EntityRabbit> RABBIT_TYPE;

  public static EntityType<EntityRavager> RAVAGER_TYPE;

  public static EntityType<EntitySalmon> SALMON_TYPE;

  public static EntityType<EntitySheep> SHEEP_TYPE;

  public static EntityType<EntityShulker> SHULKER_TYPE;

  public static EntityType<EntityShulkerBullet> SHULKER_BULLET_TYPE;

  public static EntityType<EntitySilverfish> SILVERFISH_TYPE;

  public static EntityType<EntitySkeleton> SKELETON_TYPE;

  public static EntityType<EntitySkeletonHorse> SKELETON_HORSE_TYPE;

  public static EntityType<EntitySlime> SLIME_TYPE;

  public static EntityType<EntitySmallFireball> SMALL_FIREBALL_TYPE;

  public static EntityType<EntitySniffer> SNIFFER_TYPE;

  public static EntityType<EntitySnowGolem> SNOW_GOLEM_TYPE;

  public static EntityType<EntitySnowball> SNOWBALL_TYPE;

  public static EntityType<EntitySpider> SPIDER_TYPE;

  public static EntityType<EntitySplashPotion> SPLASH_POTION_TYPE;

  public static EntityType<EntitySquid> SQUID_TYPE;

  public static EntityType<EntityStray> STRAY_TYPE;

  public static EntityType<EntityStrider> STRIDER_TYPE;

  public static EntityType<EntityTadpole> TADPOLE_TYPE;

  public static EntityType<EntityThrownTrident> THROWN_TRIDENT_TYPE;

  public static EntityType<EntityTnt> TNT_TYPE;

  public static EntityType<EntityTntMinecart> TNT_MINECART_TYPE;

  public static EntityType<EntityTraderLlama> TRADER_LLAMA_TYPE;

  public static EntityType<EntityTripodCamera> TRIPOD_CAMERA_TYPE;

  public static EntityType<EntityTropicalfish> TROPICALFISH_TYPE;

  public static EntityType<EntityTurtle> TURTLE_TYPE;

  public static EntityType<EntityVex> VEX_TYPE;

  public static EntityType<EntityVillager> VILLAGER_TYPE;

  public static EntityType<EntityVillagerV2> VILLAGER_V2_TYPE;

  public static EntityType<EntityVindicator> VINDICATOR_TYPE;

  public static EntityType<EntityWanderingTrader> WANDERING_TRADER_TYPE;

  public static EntityType<EntityWarden> WARDEN_TYPE;

  public static EntityType<EntityWitch> WITCH_TYPE;

  public static EntityType<EntityWither> WITHER_TYPE;

  public static EntityType<EntityWitherSkeleton> WITHER_SKELETON_TYPE;

  public static EntityType<EntityWitherSkull> WITHER_SKULL_TYPE;

  public static EntityType<EntityWitherSkullDangerous> WITHER_SKULL_DANGEROUS_TYPE;

  public static EntityType<EntityWolf> WOLF_TYPE;

  public static EntityType<EntityXpBottle> XP_BOTTLE_TYPE;

  public static EntityType<EntityXpOrb> XP_ORB_TYPE;

  public static EntityType<EntityZoglin> ZOGLIN_TYPE;

  public static EntityType<EntityZombie> ZOMBIE_TYPE;

  public static EntityType<EntityZombieHorse> ZOMBIE_HORSE_TYPE;

  public static EntityType<EntityZombiePigman> ZOMBIE_PIGMAN_TYPE;

  public static EntityType<EntityZombieVillager> ZOMBIE_VILLAGER_TYPE;

  public static EntityType<EntityZombieVillagerV2> ZOMBIE_VILLAGER_V2_TYPE;
}
