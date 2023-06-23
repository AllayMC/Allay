package cn.allay.api.entity.type;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.impl.EntityAllay;
import cn.allay.api.entity.impl.EntityArmorStand;
import cn.allay.api.entity.impl.EntityArrow;
import cn.allay.api.entity.impl.EntityAxolotl;
import cn.allay.api.entity.impl.EntityBat;
import cn.allay.api.entity.impl.EntityBee;
import cn.allay.api.entity.impl.EntityBlaze;
import cn.allay.api.entity.impl.EntityBoat;
import cn.allay.api.entity.impl.EntityCamel;
import cn.allay.api.entity.impl.EntityCat;
import cn.allay.api.entity.impl.EntityCaveSpider;
import cn.allay.api.entity.impl.EntityChestBoat;
import cn.allay.api.entity.impl.EntityChestMinecart;
import cn.allay.api.entity.impl.EntityChicken;
import cn.allay.api.entity.impl.EntityCod;
import cn.allay.api.entity.impl.EntityCommandBlockMinecart;
import cn.allay.api.entity.impl.EntityCow;
import cn.allay.api.entity.impl.EntityCreeper;
import cn.allay.api.entity.impl.EntityDolphin;
import cn.allay.api.entity.impl.EntityDonkey;
import cn.allay.api.entity.impl.EntityDrowned;
import cn.allay.api.entity.impl.EntityEgg;
import cn.allay.api.entity.impl.EntityElderGuardian;
import cn.allay.api.entity.impl.EntityElderGuardianGhost;
import cn.allay.api.entity.impl.EntityEnderCrystal;
import cn.allay.api.entity.impl.EntityEnderDragon;
import cn.allay.api.entity.impl.EntityEnderman;
import cn.allay.api.entity.impl.EntityEndermite;
import cn.allay.api.entity.impl.EntityEvocationFang;
import cn.allay.api.entity.impl.EntityEvocationIllager;
import cn.allay.api.entity.impl.EntityFireworksRocket;
import cn.allay.api.entity.impl.EntityFox;
import cn.allay.api.entity.impl.EntityFrog;
import cn.allay.api.entity.impl.EntityGhast;
import cn.allay.api.entity.impl.EntityGlowSquid;
import cn.allay.api.entity.impl.EntityGoat;
import cn.allay.api.entity.impl.EntityGuardian;
import cn.allay.api.entity.impl.EntityHoglin;
import cn.allay.api.entity.impl.EntityHopperMinecart;
import cn.allay.api.entity.impl.EntityHorse;
import cn.allay.api.entity.impl.EntityHusk;
import cn.allay.api.entity.impl.EntityIronGolem;
import cn.allay.api.entity.impl.EntityLeashKnot;
import cn.allay.api.entity.impl.EntityLightningBolt;
import cn.allay.api.entity.impl.EntityLlama;
import cn.allay.api.entity.impl.EntityMagmaCube;
import cn.allay.api.entity.impl.EntityMinecart;
import cn.allay.api.entity.impl.EntityMooshroom;
import cn.allay.api.entity.impl.EntityMule;
import cn.allay.api.entity.impl.EntityNpc;
import cn.allay.api.entity.impl.EntityOcelot;
import cn.allay.api.entity.impl.EntityPanda;
import cn.allay.api.entity.impl.EntityParrot;
import cn.allay.api.entity.impl.EntityPhantom;
import cn.allay.api.entity.impl.EntityPig;
import cn.allay.api.entity.impl.EntityPiglin;
import cn.allay.api.entity.impl.EntityPiglinBrute;
import cn.allay.api.entity.impl.EntityPillager;
import cn.allay.api.entity.impl.EntityPolarBear;
import cn.allay.api.entity.impl.EntityPufferfish;
import cn.allay.api.entity.impl.EntityRabbit;
import cn.allay.api.entity.impl.EntityRavager;
import cn.allay.api.entity.impl.EntitySalmon;
import cn.allay.api.entity.impl.EntitySheep;
import cn.allay.api.entity.impl.EntityShulker;
import cn.allay.api.entity.impl.EntitySilverfish;
import cn.allay.api.entity.impl.EntitySkeleton;
import cn.allay.api.entity.impl.EntitySkeletonHorse;
import cn.allay.api.entity.impl.EntitySlime;
import cn.allay.api.entity.impl.EntitySniffer;
import cn.allay.api.entity.impl.EntitySnowGolem;
import cn.allay.api.entity.impl.EntitySnowball;
import cn.allay.api.entity.impl.EntitySpider;
import cn.allay.api.entity.impl.EntitySplashPotion;
import cn.allay.api.entity.impl.EntitySquid;
import cn.allay.api.entity.impl.EntityStray;
import cn.allay.api.entity.impl.EntityStrider;
import cn.allay.api.entity.impl.EntityTadpole;
import cn.allay.api.entity.impl.EntityTnt;
import cn.allay.api.entity.impl.EntityTntMinecart;
import cn.allay.api.entity.impl.EntityTraderLlama;
import cn.allay.api.entity.impl.EntityTropicalfish;
import cn.allay.api.entity.impl.EntityTurtle;
import cn.allay.api.entity.impl.EntityVex;
import cn.allay.api.entity.impl.EntityVillager;
import cn.allay.api.entity.impl.EntityVindicator;
import cn.allay.api.entity.impl.EntityWanderingTrader;
import cn.allay.api.entity.impl.EntityWarden;
import cn.allay.api.entity.impl.EntityWitch;
import cn.allay.api.entity.impl.EntityWither;
import cn.allay.api.entity.impl.EntityWitherSkeleton;
import cn.allay.api.entity.impl.EntityWolf;
import cn.allay.api.entity.impl.EntityXpBottle;
import cn.allay.api.entity.impl.EntityXpOrb;
import cn.allay.api.entity.impl.EntityZoglin;
import cn.allay.api.entity.impl.EntityZombie;
import cn.allay.api.entity.impl.EntityZombieHorse;
import cn.allay.api.entity.impl.EntityZombiePigman;
import cn.allay.api.entity.impl.EntityZombieVillager;

/**
 * @author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface VanillaEntityTypes {
  EntityType<EntityAllay> ALLAY_TYPE = EntityTypeBuilder
          .builder(EntityAllay.class)
          .vanillaEntity(VanillaEntityId.ALLAY)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityArmorStand> ARMOR_STAND_TYPE = EntityTypeBuilder
          .builder(EntityArmorStand.class)
          .vanillaEntity(VanillaEntityId.ARMOR_STAND)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityArrow> ARROW_TYPE = EntityTypeBuilder
          .builder(EntityArrow.class)
          .vanillaEntity(VanillaEntityId.ARROW)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityAxolotl> AXOLOTL_TYPE = EntityTypeBuilder
          .builder(EntityAxolotl.class)
          .vanillaEntity(VanillaEntityId.AXOLOTL)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityBat> BAT_TYPE = EntityTypeBuilder
          .builder(EntityBat.class)
          .vanillaEntity(VanillaEntityId.BAT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityBee> BEE_TYPE = EntityTypeBuilder
          .builder(EntityBee.class)
          .vanillaEntity(VanillaEntityId.BEE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityBlaze> BLAZE_TYPE = EntityTypeBuilder
          .builder(EntityBlaze.class)
          .vanillaEntity(VanillaEntityId.BLAZE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityBoat> BOAT_TYPE = EntityTypeBuilder
          .builder(EntityBoat.class)
          .vanillaEntity(VanillaEntityId.BOAT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityCamel> CAMEL_TYPE = EntityTypeBuilder
          .builder(EntityCamel.class)
          .vanillaEntity(VanillaEntityId.CAMEL)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityCat> CAT_TYPE = EntityTypeBuilder
          .builder(EntityCat.class)
          .vanillaEntity(VanillaEntityId.CAT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityCaveSpider> CAVE_SPIDER_TYPE = EntityTypeBuilder
          .builder(EntityCaveSpider.class)
          .vanillaEntity(VanillaEntityId.CAVE_SPIDER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityChestBoat> CHEST_BOAT_TYPE = EntityTypeBuilder
          .builder(EntityChestBoat.class)
          .vanillaEntity(VanillaEntityId.CHEST_BOAT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityChestMinecart> CHEST_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityChestMinecart.class)
          .vanillaEntity(VanillaEntityId.CHEST_MINECART)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityChicken> CHICKEN_TYPE = EntityTypeBuilder
          .builder(EntityChicken.class)
          .vanillaEntity(VanillaEntityId.CHICKEN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityCod> COD_TYPE = EntityTypeBuilder
          .builder(EntityCod.class)
          .vanillaEntity(VanillaEntityId.COD)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityCommandBlockMinecart> COMMAND_BLOCK_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityCommandBlockMinecart.class)
          .vanillaEntity(VanillaEntityId.COMMAND_BLOCK_MINECART)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityCow> COW_TYPE = EntityTypeBuilder
          .builder(EntityCow.class)
          .vanillaEntity(VanillaEntityId.COW)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityCreeper> CREEPER_TYPE = EntityTypeBuilder
          .builder(EntityCreeper.class)
          .vanillaEntity(VanillaEntityId.CREEPER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityDolphin> DOLPHIN_TYPE = EntityTypeBuilder
          .builder(EntityDolphin.class)
          .vanillaEntity(VanillaEntityId.DOLPHIN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityDonkey> DONKEY_TYPE = EntityTypeBuilder
          .builder(EntityDonkey.class)
          .vanillaEntity(VanillaEntityId.DONKEY)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityDrowned> DROWNED_TYPE = EntityTypeBuilder
          .builder(EntityDrowned.class)
          .vanillaEntity(VanillaEntityId.DROWNED)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityEgg> EGG_TYPE = EntityTypeBuilder
          .builder(EntityEgg.class)
          .vanillaEntity(VanillaEntityId.EGG)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityElderGuardian> ELDER_GUARDIAN_TYPE = EntityTypeBuilder
          .builder(EntityElderGuardian.class)
          .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityElderGuardianGhost> ELDER_GUARDIAN_GHOST_TYPE = EntityTypeBuilder
          .builder(EntityElderGuardianGhost.class)
          .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN_GHOST)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityEnderCrystal> ENDER_CRYSTAL_TYPE = EntityTypeBuilder
          .builder(EntityEnderCrystal.class)
          .vanillaEntity(VanillaEntityId.ENDER_CRYSTAL)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityEnderDragon> ENDER_DRAGON_TYPE = EntityTypeBuilder
          .builder(EntityEnderDragon.class)
          .vanillaEntity(VanillaEntityId.ENDER_DRAGON)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityEnderman> ENDERMAN_TYPE = EntityTypeBuilder
          .builder(EntityEnderman.class)
          .vanillaEntity(VanillaEntityId.ENDERMAN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityEndermite> ENDERMITE_TYPE = EntityTypeBuilder
          .builder(EntityEndermite.class)
          .vanillaEntity(VanillaEntityId.ENDERMITE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityEvocationFang> EVOCATION_FANG_TYPE = EntityTypeBuilder
          .builder(EntityEvocationFang.class)
          .vanillaEntity(VanillaEntityId.EVOCATION_FANG)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityEvocationIllager> EVOCATION_ILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityEvocationIllager.class)
          .vanillaEntity(VanillaEntityId.EVOCATION_ILLAGER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityFireworksRocket> FIREWORKS_ROCKET_TYPE = EntityTypeBuilder
          .builder(EntityFireworksRocket.class)
          .vanillaEntity(VanillaEntityId.FIREWORKS_ROCKET)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityFox> FOX_TYPE = EntityTypeBuilder
          .builder(EntityFox.class)
          .vanillaEntity(VanillaEntityId.FOX)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityFrog> FROG_TYPE = EntityTypeBuilder
          .builder(EntityFrog.class)
          .vanillaEntity(VanillaEntityId.FROG)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityGhast> GHAST_TYPE = EntityTypeBuilder
          .builder(EntityGhast.class)
          .vanillaEntity(VanillaEntityId.GHAST)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityGlowSquid> GLOW_SQUID_TYPE = EntityTypeBuilder
          .builder(EntityGlowSquid.class)
          .vanillaEntity(VanillaEntityId.GLOW_SQUID)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityGoat> GOAT_TYPE = EntityTypeBuilder
          .builder(EntityGoat.class)
          .vanillaEntity(VanillaEntityId.GOAT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityGuardian> GUARDIAN_TYPE = EntityTypeBuilder
          .builder(EntityGuardian.class)
          .vanillaEntity(VanillaEntityId.GUARDIAN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityHoglin> HOGLIN_TYPE = EntityTypeBuilder
          .builder(EntityHoglin.class)
          .vanillaEntity(VanillaEntityId.HOGLIN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityHopperMinecart> HOPPER_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityHopperMinecart.class)
          .vanillaEntity(VanillaEntityId.HOPPER_MINECART)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityHorse> HORSE_TYPE = EntityTypeBuilder
          .builder(EntityHorse.class)
          .vanillaEntity(VanillaEntityId.HORSE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityHusk> HUSK_TYPE = EntityTypeBuilder
          .builder(EntityHusk.class)
          .vanillaEntity(VanillaEntityId.HUSK)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityIronGolem> IRON_GOLEM_TYPE = EntityTypeBuilder
          .builder(EntityIronGolem.class)
          .vanillaEntity(VanillaEntityId.IRON_GOLEM)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityLeashKnot> LEASH_KNOT_TYPE = EntityTypeBuilder
          .builder(EntityLeashKnot.class)
          .vanillaEntity(VanillaEntityId.LEASH_KNOT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityLightningBolt> LIGHTNING_BOLT_TYPE = EntityTypeBuilder
          .builder(EntityLightningBolt.class)
          .vanillaEntity(VanillaEntityId.LIGHTNING_BOLT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityLlama> LLAMA_TYPE = EntityTypeBuilder
          .builder(EntityLlama.class)
          .vanillaEntity(VanillaEntityId.LLAMA)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityMagmaCube> MAGMA_CUBE_TYPE = EntityTypeBuilder
          .builder(EntityMagmaCube.class)
          .vanillaEntity(VanillaEntityId.MAGMA_CUBE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityMinecart> MINECART_TYPE = EntityTypeBuilder
          .builder(EntityMinecart.class)
          .vanillaEntity(VanillaEntityId.MINECART)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityMooshroom> MOOSHROOM_TYPE = EntityTypeBuilder
          .builder(EntityMooshroom.class)
          .vanillaEntity(VanillaEntityId.MOOSHROOM)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityMule> MULE_TYPE = EntityTypeBuilder
          .builder(EntityMule.class)
          .vanillaEntity(VanillaEntityId.MULE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityNpc> NPC_TYPE = EntityTypeBuilder
          .builder(EntityNpc.class)
          .vanillaEntity(VanillaEntityId.NPC)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityOcelot> OCELOT_TYPE = EntityTypeBuilder
          .builder(EntityOcelot.class)
          .vanillaEntity(VanillaEntityId.OCELOT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityPanda> PANDA_TYPE = EntityTypeBuilder
          .builder(EntityPanda.class)
          .vanillaEntity(VanillaEntityId.PANDA)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityParrot> PARROT_TYPE = EntityTypeBuilder
          .builder(EntityParrot.class)
          .vanillaEntity(VanillaEntityId.PARROT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityPhantom> PHANTOM_TYPE = EntityTypeBuilder
          .builder(EntityPhantom.class)
          .vanillaEntity(VanillaEntityId.PHANTOM)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityPig> PIG_TYPE = EntityTypeBuilder
          .builder(EntityPig.class)
          .vanillaEntity(VanillaEntityId.PIG)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityPiglin> PIGLIN_TYPE = EntityTypeBuilder
          .builder(EntityPiglin.class)
          .vanillaEntity(VanillaEntityId.PIGLIN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityPiglinBrute> PIGLIN_BRUTE_TYPE = EntityTypeBuilder
          .builder(EntityPiglinBrute.class)
          .vanillaEntity(VanillaEntityId.PIGLIN_BRUTE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityPillager> PILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityPillager.class)
          .vanillaEntity(VanillaEntityId.PILLAGER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityPolarBear> POLAR_BEAR_TYPE = EntityTypeBuilder
          .builder(EntityPolarBear.class)
          .vanillaEntity(VanillaEntityId.POLAR_BEAR)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityPufferfish> PUFFERFISH_TYPE = EntityTypeBuilder
          .builder(EntityPufferfish.class)
          .vanillaEntity(VanillaEntityId.PUFFERFISH)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityRabbit> RABBIT_TYPE = EntityTypeBuilder
          .builder(EntityRabbit.class)
          .vanillaEntity(VanillaEntityId.RABBIT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityRavager> RAVAGER_TYPE = EntityTypeBuilder
          .builder(EntityRavager.class)
          .vanillaEntity(VanillaEntityId.RAVAGER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySalmon> SALMON_TYPE = EntityTypeBuilder
          .builder(EntitySalmon.class)
          .vanillaEntity(VanillaEntityId.SALMON)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySheep> SHEEP_TYPE = EntityTypeBuilder
          .builder(EntitySheep.class)
          .vanillaEntity(VanillaEntityId.SHEEP)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityShulker> SHULKER_TYPE = EntityTypeBuilder
          .builder(EntityShulker.class)
          .vanillaEntity(VanillaEntityId.SHULKER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySilverfish> SILVERFISH_TYPE = EntityTypeBuilder
          .builder(EntitySilverfish.class)
          .vanillaEntity(VanillaEntityId.SILVERFISH)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySkeleton> SKELETON_TYPE = EntityTypeBuilder
          .builder(EntitySkeleton.class)
          .vanillaEntity(VanillaEntityId.SKELETON)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySkeletonHorse> SKELETON_HORSE_TYPE = EntityTypeBuilder
          .builder(EntitySkeletonHorse.class)
          .vanillaEntity(VanillaEntityId.SKELETON_HORSE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySlime> SLIME_TYPE = EntityTypeBuilder
          .builder(EntitySlime.class)
          .vanillaEntity(VanillaEntityId.SLIME)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySniffer> SNIFFER_TYPE = EntityTypeBuilder
          .builder(EntitySniffer.class)
          .vanillaEntity(VanillaEntityId.SNIFFER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySnowGolem> SNOW_GOLEM_TYPE = EntityTypeBuilder
          .builder(EntitySnowGolem.class)
          .vanillaEntity(VanillaEntityId.SNOW_GOLEM)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySnowball> SNOWBALL_TYPE = EntityTypeBuilder
          .builder(EntitySnowball.class)
          .vanillaEntity(VanillaEntityId.SNOWBALL)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySpider> SPIDER_TYPE = EntityTypeBuilder
          .builder(EntitySpider.class)
          .vanillaEntity(VanillaEntityId.SPIDER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySplashPotion> SPLASH_POTION_TYPE = EntityTypeBuilder
          .builder(EntitySplashPotion.class)
          .vanillaEntity(VanillaEntityId.SPLASH_POTION)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntitySquid> SQUID_TYPE = EntityTypeBuilder
          .builder(EntitySquid.class)
          .vanillaEntity(VanillaEntityId.SQUID)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityStray> STRAY_TYPE = EntityTypeBuilder
          .builder(EntityStray.class)
          .vanillaEntity(VanillaEntityId.STRAY)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityStrider> STRIDER_TYPE = EntityTypeBuilder
          .builder(EntityStrider.class)
          .vanillaEntity(VanillaEntityId.STRIDER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityTadpole> TADPOLE_TYPE = EntityTypeBuilder
          .builder(EntityTadpole.class)
          .vanillaEntity(VanillaEntityId.TADPOLE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityTnt> TNT_TYPE = EntityTypeBuilder
          .builder(EntityTnt.class)
          .vanillaEntity(VanillaEntityId.TNT)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityTntMinecart> TNT_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityTntMinecart.class)
          .vanillaEntity(VanillaEntityId.TNT_MINECART)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityTraderLlama> TRADER_LLAMA_TYPE = EntityTypeBuilder
          .builder(EntityTraderLlama.class)
          .vanillaEntity(VanillaEntityId.TRADER_LLAMA)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityTropicalfish> TROPICALFISH_TYPE = EntityTypeBuilder
          .builder(EntityTropicalfish.class)
          .vanillaEntity(VanillaEntityId.TROPICALFISH)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityTurtle> TURTLE_TYPE = EntityTypeBuilder
          .builder(EntityTurtle.class)
          .vanillaEntity(VanillaEntityId.TURTLE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityVex> VEX_TYPE = EntityTypeBuilder
          .builder(EntityVex.class)
          .vanillaEntity(VanillaEntityId.VEX)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityVillager> VILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityVillager.class)
          .vanillaEntity(VanillaEntityId.VILLAGER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityVindicator> VINDICATOR_TYPE = EntityTypeBuilder
          .builder(EntityVindicator.class)
          .vanillaEntity(VanillaEntityId.VINDICATOR)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityWanderingTrader> WANDERING_TRADER_TYPE = EntityTypeBuilder
          .builder(EntityWanderingTrader.class)
          .vanillaEntity(VanillaEntityId.WANDERING_TRADER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityWarden> WARDEN_TYPE = EntityTypeBuilder
          .builder(EntityWarden.class)
          .vanillaEntity(VanillaEntityId.WARDEN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityWitch> WITCH_TYPE = EntityTypeBuilder
          .builder(EntityWitch.class)
          .vanillaEntity(VanillaEntityId.WITCH)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityWither> WITHER_TYPE = EntityTypeBuilder
          .builder(EntityWither.class)
          .vanillaEntity(VanillaEntityId.WITHER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityWitherSkeleton> WITHER_SKELETON_TYPE = EntityTypeBuilder
          .builder(EntityWitherSkeleton.class)
          .vanillaEntity(VanillaEntityId.WITHER_SKELETON)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityWolf> WOLF_TYPE = EntityTypeBuilder
          .builder(EntityWolf.class)
          .vanillaEntity(VanillaEntityId.WOLF)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityXpBottle> XP_BOTTLE_TYPE = EntityTypeBuilder
          .builder(EntityXpBottle.class)
          .vanillaEntity(VanillaEntityId.XP_BOTTLE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityXpOrb> XP_ORB_TYPE = EntityTypeBuilder
          .builder(EntityXpOrb.class)
          .vanillaEntity(VanillaEntityId.XP_ORB)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityZoglin> ZOGLIN_TYPE = EntityTypeBuilder
          .builder(EntityZoglin.class)
          .vanillaEntity(VanillaEntityId.ZOGLIN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityZombie> ZOMBIE_TYPE = EntityTypeBuilder
          .builder(EntityZombie.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityZombieHorse> ZOMBIE_HORSE_TYPE = EntityTypeBuilder
          .builder(EntityZombieHorse.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_HORSE)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityZombiePigman> ZOMBIE_PIGMAN_TYPE = EntityTypeBuilder
          .builder(EntityZombiePigman.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_PIGMAN)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());

  EntityType<EntityZombieVillager> ZOMBIE_VILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityZombieVillager.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER)
          .addBasicComponents()
          .build()        .register(EntityTypeRegistry.getRegistry());
}
