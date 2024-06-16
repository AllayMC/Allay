package org.allaymc.server.entity.type;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.*;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public final class EntityTypeDefaultInitializer {
  public static void initAgent() {
    if (EntityTypes.AGENT_TYPE != null) return;
    EntityTypes.AGENT_TYPE = EntityTypeBuilder
            .builder(EntityAgent.class)
            .vanillaEntity(VanillaEntityId.AGENT)
            .build();
  }

  public static void initAllay() {
    if (EntityTypes.ALLAY_TYPE != null) return;
    EntityTypes.ALLAY_TYPE = EntityTypeBuilder
            .builder(EntityAllay.class)
            .vanillaEntity(VanillaEntityId.ALLAY)
            .build();
  }

  public static void initAreaEffectCloud() {
    if (EntityTypes.AREA_EFFECT_CLOUD_TYPE != null) return;
    EntityTypes.AREA_EFFECT_CLOUD_TYPE = EntityTypeBuilder
            .builder(EntityAreaEffectCloud.class)
            .vanillaEntity(VanillaEntityId.AREA_EFFECT_CLOUD)
            .build();
  }

  public static void initArmadillo() {
    if (EntityTypes.ARMADILLO_TYPE != null) return;
    EntityTypes.ARMADILLO_TYPE = EntityTypeBuilder
            .builder(EntityArmadillo.class)
            .vanillaEntity(VanillaEntityId.ARMADILLO)
            .build();
  }

  public static void initArmorStand() {
    if (EntityTypes.ARMOR_STAND_TYPE != null) return;
    EntityTypes.ARMOR_STAND_TYPE = EntityTypeBuilder
            .builder(EntityArmorStand.class)
            .vanillaEntity(VanillaEntityId.ARMOR_STAND)
            .build();
  }

  public static void initArrow() {
    if (EntityTypes.ARROW_TYPE != null) return;
    EntityTypes.ARROW_TYPE = EntityTypeBuilder
            .builder(EntityArrow.class)
            .vanillaEntity(VanillaEntityId.ARROW)
            .build();
  }

  public static void initAxolotl() {
    if (EntityTypes.AXOLOTL_TYPE != null) return;
    EntityTypes.AXOLOTL_TYPE = EntityTypeBuilder
            .builder(EntityAxolotl.class)
            .vanillaEntity(VanillaEntityId.AXOLOTL)
            .build();
  }

  public static void initBalloon() {
    if (EntityTypes.BALLOON_TYPE != null) return;
    EntityTypes.BALLOON_TYPE = EntityTypeBuilder
            .builder(EntityBalloon.class)
            .vanillaEntity(VanillaEntityId.BALLOON)
            .build();
  }

  public static void initBat() {
    if (EntityTypes.BAT_TYPE != null) return;
    EntityTypes.BAT_TYPE = EntityTypeBuilder
            .builder(EntityBat.class)
            .vanillaEntity(VanillaEntityId.BAT)
            .build();
  }

  public static void initBee() {
    if (EntityTypes.BEE_TYPE != null) return;
    EntityTypes.BEE_TYPE = EntityTypeBuilder
            .builder(EntityBee.class)
            .vanillaEntity(VanillaEntityId.BEE)
            .build();
  }

  public static void initBlaze() {
    if (EntityTypes.BLAZE_TYPE != null) return;
    EntityTypes.BLAZE_TYPE = EntityTypeBuilder
            .builder(EntityBlaze.class)
            .vanillaEntity(VanillaEntityId.BLAZE)
            .build();
  }

  public static void initBoat() {
    if (EntityTypes.BOAT_TYPE != null) return;
    EntityTypes.BOAT_TYPE = EntityTypeBuilder
            .builder(EntityBoat.class)
            .vanillaEntity(VanillaEntityId.BOAT)
            .build();
  }

  public static void initBogged() {
    if (EntityTypes.BOGGED_TYPE != null) return;
    EntityTypes.BOGGED_TYPE = EntityTypeBuilder
            .builder(EntityBogged.class)
            .vanillaEntity(VanillaEntityId.BOGGED)
            .build();
  }

  public static void initBreeze() {
    if (EntityTypes.BREEZE_TYPE != null) return;
    EntityTypes.BREEZE_TYPE = EntityTypeBuilder
            .builder(EntityBreeze.class)
            .vanillaEntity(VanillaEntityId.BREEZE)
            .build();
  }

  public static void initBreezeWindChargeProjectile() {
    if (EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE_TYPE != null) return;
    EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE_TYPE = EntityTypeBuilder
            .builder(EntityBreezeWindChargeProjectile.class)
            .vanillaEntity(VanillaEntityId.BREEZE_WIND_CHARGE_PROJECTILE)
            .build();
  }

  public static void initCamel() {
    if (EntityTypes.CAMEL_TYPE != null) return;
    EntityTypes.CAMEL_TYPE = EntityTypeBuilder
            .builder(EntityCamel.class)
            .vanillaEntity(VanillaEntityId.CAMEL)
            .build();
  }

  public static void initCat() {
    if (EntityTypes.CAT_TYPE != null) return;
    EntityTypes.CAT_TYPE = EntityTypeBuilder
            .builder(EntityCat.class)
            .vanillaEntity(VanillaEntityId.CAT)
            .build();
  }

  public static void initCaveSpider() {
    if (EntityTypes.CAVE_SPIDER_TYPE != null) return;
    EntityTypes.CAVE_SPIDER_TYPE = EntityTypeBuilder
            .builder(EntityCaveSpider.class)
            .vanillaEntity(VanillaEntityId.CAVE_SPIDER)
            .build();
  }

  public static void initChestBoat() {
    if (EntityTypes.CHEST_BOAT_TYPE != null) return;
    EntityTypes.CHEST_BOAT_TYPE = EntityTypeBuilder
            .builder(EntityChestBoat.class)
            .vanillaEntity(VanillaEntityId.CHEST_BOAT)
            .build();
  }

  public static void initChestMinecart() {
    if (EntityTypes.CHEST_MINECART_TYPE != null) return;
    EntityTypes.CHEST_MINECART_TYPE = EntityTypeBuilder
            .builder(EntityChestMinecart.class)
            .vanillaEntity(VanillaEntityId.CHEST_MINECART)
            .build();
  }

  public static void initChicken() {
    if (EntityTypes.CHICKEN_TYPE != null) return;
    EntityTypes.CHICKEN_TYPE = EntityTypeBuilder
            .builder(EntityChicken.class)
            .vanillaEntity(VanillaEntityId.CHICKEN)
            .build();
  }

  public static void initCod() {
    if (EntityTypes.COD_TYPE != null) return;
    EntityTypes.COD_TYPE = EntityTypeBuilder
            .builder(EntityCod.class)
            .vanillaEntity(VanillaEntityId.COD)
            .build();
  }

  public static void initCommandBlockMinecart() {
    if (EntityTypes.COMMAND_BLOCK_MINECART_TYPE != null) return;
    EntityTypes.COMMAND_BLOCK_MINECART_TYPE = EntityTypeBuilder
            .builder(EntityCommandBlockMinecart.class)
            .vanillaEntity(VanillaEntityId.COMMAND_BLOCK_MINECART)
            .build();
  }

  public static void initCow() {
    if (EntityTypes.COW_TYPE != null) return;
    EntityTypes.COW_TYPE = EntityTypeBuilder
            .builder(EntityCow.class)
            .vanillaEntity(VanillaEntityId.COW)
            .build();
  }

  public static void initCreeper() {
    if (EntityTypes.CREEPER_TYPE != null) return;
    EntityTypes.CREEPER_TYPE = EntityTypeBuilder
            .builder(EntityCreeper.class)
            .vanillaEntity(VanillaEntityId.CREEPER)
            .build();
  }

  public static void initDolphin() {
    if (EntityTypes.DOLPHIN_TYPE != null) return;
    EntityTypes.DOLPHIN_TYPE = EntityTypeBuilder
            .builder(EntityDolphin.class)
            .vanillaEntity(VanillaEntityId.DOLPHIN)
            .build();
  }

  public static void initDonkey() {
    if (EntityTypes.DONKEY_TYPE != null) return;
    EntityTypes.DONKEY_TYPE = EntityTypeBuilder
            .builder(EntityDonkey.class)
            .vanillaEntity(VanillaEntityId.DONKEY)
            .build();
  }

  public static void initDragonFireball() {
    if (EntityTypes.DRAGON_FIREBALL_TYPE != null) return;
    EntityTypes.DRAGON_FIREBALL_TYPE = EntityTypeBuilder
            .builder(EntityDragonFireball.class)
            .vanillaEntity(VanillaEntityId.DRAGON_FIREBALL)
            .build();
  }

  public static void initDrowned() {
    if (EntityTypes.DROWNED_TYPE != null) return;
    EntityTypes.DROWNED_TYPE = EntityTypeBuilder
            .builder(EntityDrowned.class)
            .vanillaEntity(VanillaEntityId.DROWNED)
            .build();
  }

  public static void initEgg() {
    if (EntityTypes.EGG_TYPE != null) return;
    EntityTypes.EGG_TYPE = EntityTypeBuilder
            .builder(EntityEgg.class)
            .vanillaEntity(VanillaEntityId.EGG)
            .build();
  }

  public static void initElderGuardian() {
    if (EntityTypes.ELDER_GUARDIAN_TYPE != null) return;
    EntityTypes.ELDER_GUARDIAN_TYPE = EntityTypeBuilder
            .builder(EntityElderGuardian.class)
            .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN)
            .build();
  }

  public static void initElderGuardianGhost() {
    if (EntityTypes.ELDER_GUARDIAN_GHOST_TYPE != null) return;
    EntityTypes.ELDER_GUARDIAN_GHOST_TYPE = EntityTypeBuilder
            .builder(EntityElderGuardianGhost.class)
            .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN_GHOST)
            .build();
  }

  public static void initEnderCrystal() {
    if (EntityTypes.ENDER_CRYSTAL_TYPE != null) return;
    EntityTypes.ENDER_CRYSTAL_TYPE = EntityTypeBuilder
            .builder(EntityEnderCrystal.class)
            .vanillaEntity(VanillaEntityId.ENDER_CRYSTAL)
            .build();
  }

  public static void initEnderDragon() {
    if (EntityTypes.ENDER_DRAGON_TYPE != null) return;
    EntityTypes.ENDER_DRAGON_TYPE = EntityTypeBuilder
            .builder(EntityEnderDragon.class)
            .vanillaEntity(VanillaEntityId.ENDER_DRAGON)
            .build();
  }

  public static void initEnderPearl() {
    if (EntityTypes.ENDER_PEARL_TYPE != null) return;
    EntityTypes.ENDER_PEARL_TYPE = EntityTypeBuilder
            .builder(EntityEnderPearl.class)
            .vanillaEntity(VanillaEntityId.ENDER_PEARL)
            .build();
  }

  public static void initEnderman() {
    if (EntityTypes.ENDERMAN_TYPE != null) return;
    EntityTypes.ENDERMAN_TYPE = EntityTypeBuilder
            .builder(EntityEnderman.class)
            .vanillaEntity(VanillaEntityId.ENDERMAN)
            .build();
  }

  public static void initEndermite() {
    if (EntityTypes.ENDERMITE_TYPE != null) return;
    EntityTypes.ENDERMITE_TYPE = EntityTypeBuilder
            .builder(EntityEndermite.class)
            .vanillaEntity(VanillaEntityId.ENDERMITE)
            .build();
  }

  public static void initEvocationFang() {
    if (EntityTypes.EVOCATION_FANG_TYPE != null) return;
    EntityTypes.EVOCATION_FANG_TYPE = EntityTypeBuilder
            .builder(EntityEvocationFang.class)
            .vanillaEntity(VanillaEntityId.EVOCATION_FANG)
            .build();
  }

  public static void initEvocationIllager() {
    if (EntityTypes.EVOCATION_ILLAGER_TYPE != null) return;
    EntityTypes.EVOCATION_ILLAGER_TYPE = EntityTypeBuilder
            .builder(EntityEvocationIllager.class)
            .vanillaEntity(VanillaEntityId.EVOCATION_ILLAGER)
            .build();
  }

  public static void initEyeOfEnderSignal() {
    if (EntityTypes.EYE_OF_ENDER_SIGNAL_TYPE != null) return;
    EntityTypes.EYE_OF_ENDER_SIGNAL_TYPE = EntityTypeBuilder
            .builder(EntityEyeOfEnderSignal.class)
            .vanillaEntity(VanillaEntityId.EYE_OF_ENDER_SIGNAL)
            .build();
  }

  public static void initFallingBlock() {
    if (EntityTypes.FALLING_BLOCK_TYPE != null) return;
    EntityTypes.FALLING_BLOCK_TYPE = EntityTypeBuilder
            .builder(EntityFallingBlock.class)
            .vanillaEntity(VanillaEntityId.FALLING_BLOCK)
            .build();
  }

  public static void initFireball() {
    if (EntityTypes.FIREBALL_TYPE != null) return;
    EntityTypes.FIREBALL_TYPE = EntityTypeBuilder
            .builder(EntityFireball.class)
            .vanillaEntity(VanillaEntityId.FIREBALL)
            .build();
  }

  public static void initFireworksRocket() {
    if (EntityTypes.FIREWORKS_ROCKET_TYPE != null) return;
    EntityTypes.FIREWORKS_ROCKET_TYPE = EntityTypeBuilder
            .builder(EntityFireworksRocket.class)
            .vanillaEntity(VanillaEntityId.FIREWORKS_ROCKET)
            .build();
  }

  public static void initFishingHook() {
    if (EntityTypes.FISHING_HOOK_TYPE != null) return;
    EntityTypes.FISHING_HOOK_TYPE = EntityTypeBuilder
            .builder(EntityFishingHook.class)
            .vanillaEntity(VanillaEntityId.FISHING_HOOK)
            .build();
  }

  public static void initFox() {
    if (EntityTypes.FOX_TYPE != null) return;
    EntityTypes.FOX_TYPE = EntityTypeBuilder
            .builder(EntityFox.class)
            .vanillaEntity(VanillaEntityId.FOX)
            .build();
  }

  public static void initFrog() {
    if (EntityTypes.FROG_TYPE != null) return;
    EntityTypes.FROG_TYPE = EntityTypeBuilder
            .builder(EntityFrog.class)
            .vanillaEntity(VanillaEntityId.FROG)
            .build();
  }

  public static void initGhast() {
    if (EntityTypes.GHAST_TYPE != null) return;
    EntityTypes.GHAST_TYPE = EntityTypeBuilder
            .builder(EntityGhast.class)
            .vanillaEntity(VanillaEntityId.GHAST)
            .build();
  }

  public static void initGlowSquid() {
    if (EntityTypes.GLOW_SQUID_TYPE != null) return;
    EntityTypes.GLOW_SQUID_TYPE = EntityTypeBuilder
            .builder(EntityGlowSquid.class)
            .vanillaEntity(VanillaEntityId.GLOW_SQUID)
            .build();
  }

  public static void initGoat() {
    if (EntityTypes.GOAT_TYPE != null) return;
    EntityTypes.GOAT_TYPE = EntityTypeBuilder
            .builder(EntityGoat.class)
            .vanillaEntity(VanillaEntityId.GOAT)
            .build();
  }

  public static void initGuardian() {
    if (EntityTypes.GUARDIAN_TYPE != null) return;
    EntityTypes.GUARDIAN_TYPE = EntityTypeBuilder
            .builder(EntityGuardian.class)
            .vanillaEntity(VanillaEntityId.GUARDIAN)
            .build();
  }

  public static void initHoglin() {
    if (EntityTypes.HOGLIN_TYPE != null) return;
    EntityTypes.HOGLIN_TYPE = EntityTypeBuilder
            .builder(EntityHoglin.class)
            .vanillaEntity(VanillaEntityId.HOGLIN)
            .build();
  }

  public static void initHopperMinecart() {
    if (EntityTypes.HOPPER_MINECART_TYPE != null) return;
    EntityTypes.HOPPER_MINECART_TYPE = EntityTypeBuilder
            .builder(EntityHopperMinecart.class)
            .vanillaEntity(VanillaEntityId.HOPPER_MINECART)
            .build();
  }

  public static void initHorse() {
    if (EntityTypes.HORSE_TYPE != null) return;
    EntityTypes.HORSE_TYPE = EntityTypeBuilder
            .builder(EntityHorse.class)
            .vanillaEntity(VanillaEntityId.HORSE)
            .build();
  }

  public static void initHusk() {
    if (EntityTypes.HUSK_TYPE != null) return;
    EntityTypes.HUSK_TYPE = EntityTypeBuilder
            .builder(EntityHusk.class)
            .vanillaEntity(VanillaEntityId.HUSK)
            .build();
  }

  public static void initIceBomb() {
    if (EntityTypes.ICE_BOMB_TYPE != null) return;
    EntityTypes.ICE_BOMB_TYPE = EntityTypeBuilder
            .builder(EntityIceBomb.class)
            .vanillaEntity(VanillaEntityId.ICE_BOMB)
            .build();
  }

  public static void initIronGolem() {
    if (EntityTypes.IRON_GOLEM_TYPE != null) return;
    EntityTypes.IRON_GOLEM_TYPE = EntityTypeBuilder
            .builder(EntityIronGolem.class)
            .vanillaEntity(VanillaEntityId.IRON_GOLEM)
            .build();
  }

  public static void initItem() {
    if (EntityTypes.ITEM_TYPE != null) return;
    EntityTypes.ITEM_TYPE = EntityTypeBuilder
            .builder(EntityItem.class)
            .vanillaEntity(VanillaEntityId.ITEM)
            .build();
  }

  public static void initLeashKnot() {
    if (EntityTypes.LEASH_KNOT_TYPE != null) return;
    EntityTypes.LEASH_KNOT_TYPE = EntityTypeBuilder
            .builder(EntityLeashKnot.class)
            .vanillaEntity(VanillaEntityId.LEASH_KNOT)
            .build();
  }

  public static void initLightningBolt() {
    if (EntityTypes.LIGHTNING_BOLT_TYPE != null) return;
    EntityTypes.LIGHTNING_BOLT_TYPE = EntityTypeBuilder
            .builder(EntityLightningBolt.class)
            .vanillaEntity(VanillaEntityId.LIGHTNING_BOLT)
            .build();
  }

  public static void initLingeringPotion() {
    if (EntityTypes.LINGERING_POTION_TYPE != null) return;
    EntityTypes.LINGERING_POTION_TYPE = EntityTypeBuilder
            .builder(EntityLingeringPotion.class)
            .vanillaEntity(VanillaEntityId.LINGERING_POTION)
            .build();
  }

  public static void initLlama() {
    if (EntityTypes.LLAMA_TYPE != null) return;
    EntityTypes.LLAMA_TYPE = EntityTypeBuilder
            .builder(EntityLlama.class)
            .vanillaEntity(VanillaEntityId.LLAMA)
            .build();
  }

  public static void initLlamaSpit() {
    if (EntityTypes.LLAMA_SPIT_TYPE != null) return;
    EntityTypes.LLAMA_SPIT_TYPE = EntityTypeBuilder
            .builder(EntityLlamaSpit.class)
            .vanillaEntity(VanillaEntityId.LLAMA_SPIT)
            .build();
  }

  public static void initMagmaCube() {
    if (EntityTypes.MAGMA_CUBE_TYPE != null) return;
    EntityTypes.MAGMA_CUBE_TYPE = EntityTypeBuilder
            .builder(EntityMagmaCube.class)
            .vanillaEntity(VanillaEntityId.MAGMA_CUBE)
            .build();
  }

  public static void initMinecart() {
    if (EntityTypes.MINECART_TYPE != null) return;
    EntityTypes.MINECART_TYPE = EntityTypeBuilder
            .builder(EntityMinecart.class)
            .vanillaEntity(VanillaEntityId.MINECART)
            .build();
  }

  public static void initMooshroom() {
    if (EntityTypes.MOOSHROOM_TYPE != null) return;
    EntityTypes.MOOSHROOM_TYPE = EntityTypeBuilder
            .builder(EntityMooshroom.class)
            .vanillaEntity(VanillaEntityId.MOOSHROOM)
            .build();
  }

  public static void initMule() {
    if (EntityTypes.MULE_TYPE != null) return;
    EntityTypes.MULE_TYPE = EntityTypeBuilder
            .builder(EntityMule.class)
            .vanillaEntity(VanillaEntityId.MULE)
            .build();
  }

  public static void initNpc() {
    if (EntityTypes.NPC_TYPE != null) return;
    EntityTypes.NPC_TYPE = EntityTypeBuilder
            .builder(EntityNpc.class)
            .vanillaEntity(VanillaEntityId.NPC)
            .build();
  }

  public static void initOcelot() {
    if (EntityTypes.OCELOT_TYPE != null) return;
    EntityTypes.OCELOT_TYPE = EntityTypeBuilder
            .builder(EntityOcelot.class)
            .vanillaEntity(VanillaEntityId.OCELOT)
            .build();
  }

  public static void initOminousItemSpawner() {
    if (EntityTypes.OMINOUS_ITEM_SPAWNER_TYPE != null) return;
    EntityTypes.OMINOUS_ITEM_SPAWNER_TYPE = EntityTypeBuilder
            .builder(EntityOminousItemSpawner.class)
            .vanillaEntity(VanillaEntityId.OMINOUS_ITEM_SPAWNER)
            .build();
  }

  public static void initPainting() {
    if (EntityTypes.PAINTING_TYPE != null) return;
    EntityTypes.PAINTING_TYPE = EntityTypeBuilder
            .builder(EntityPainting.class)
            .vanillaEntity(VanillaEntityId.PAINTING)
            .build();
  }

  public static void initPanda() {
    if (EntityTypes.PANDA_TYPE != null) return;
    EntityTypes.PANDA_TYPE = EntityTypeBuilder
            .builder(EntityPanda.class)
            .vanillaEntity(VanillaEntityId.PANDA)
            .build();
  }

  public static void initParrot() {
    if (EntityTypes.PARROT_TYPE != null) return;
    EntityTypes.PARROT_TYPE = EntityTypeBuilder
            .builder(EntityParrot.class)
            .vanillaEntity(VanillaEntityId.PARROT)
            .build();
  }

  public static void initPhantom() {
    if (EntityTypes.PHANTOM_TYPE != null) return;
    EntityTypes.PHANTOM_TYPE = EntityTypeBuilder
            .builder(EntityPhantom.class)
            .vanillaEntity(VanillaEntityId.PHANTOM)
            .build();
  }

  public static void initPig() {
    if (EntityTypes.PIG_TYPE != null) return;
    EntityTypes.PIG_TYPE = EntityTypeBuilder
            .builder(EntityPig.class)
            .vanillaEntity(VanillaEntityId.PIG)
            .build();
  }

  public static void initPiglin() {
    if (EntityTypes.PIGLIN_TYPE != null) return;
    EntityTypes.PIGLIN_TYPE = EntityTypeBuilder
            .builder(EntityPiglin.class)
            .vanillaEntity(VanillaEntityId.PIGLIN)
            .build();
  }

  public static void initPiglinBrute() {
    if (EntityTypes.PIGLIN_BRUTE_TYPE != null) return;
    EntityTypes.PIGLIN_BRUTE_TYPE = EntityTypeBuilder
            .builder(EntityPiglinBrute.class)
            .vanillaEntity(VanillaEntityId.PIGLIN_BRUTE)
            .build();
  }

  public static void initPillager() {
    if (EntityTypes.PILLAGER_TYPE != null) return;
    EntityTypes.PILLAGER_TYPE = EntityTypeBuilder
            .builder(EntityPillager.class)
            .vanillaEntity(VanillaEntityId.PILLAGER)
            .build();
  }

  public static void initPlayer() {
    if (EntityTypes.PLAYER_TYPE != null) return;
    EntityTypes.PLAYER_TYPE = EntityTypeBuilder
            .builder(EntityPlayer.class)
            .vanillaEntity(VanillaEntityId.PLAYER)
            .build();
  }

  public static void initPolarBear() {
    if (EntityTypes.POLAR_BEAR_TYPE != null) return;
    EntityTypes.POLAR_BEAR_TYPE = EntityTypeBuilder
            .builder(EntityPolarBear.class)
            .vanillaEntity(VanillaEntityId.POLAR_BEAR)
            .build();
  }

  public static void initPufferfish() {
    if (EntityTypes.PUFFERFISH_TYPE != null) return;
    EntityTypes.PUFFERFISH_TYPE = EntityTypeBuilder
            .builder(EntityPufferfish.class)
            .vanillaEntity(VanillaEntityId.PUFFERFISH)
            .build();
  }

  public static void initRabbit() {
    if (EntityTypes.RABBIT_TYPE != null) return;
    EntityTypes.RABBIT_TYPE = EntityTypeBuilder
            .builder(EntityRabbit.class)
            .vanillaEntity(VanillaEntityId.RABBIT)
            .build();
  }

  public static void initRavager() {
    if (EntityTypes.RAVAGER_TYPE != null) return;
    EntityTypes.RAVAGER_TYPE = EntityTypeBuilder
            .builder(EntityRavager.class)
            .vanillaEntity(VanillaEntityId.RAVAGER)
            .build();
  }

  public static void initSalmon() {
    if (EntityTypes.SALMON_TYPE != null) return;
    EntityTypes.SALMON_TYPE = EntityTypeBuilder
            .builder(EntitySalmon.class)
            .vanillaEntity(VanillaEntityId.SALMON)
            .build();
  }

  public static void initSheep() {
    if (EntityTypes.SHEEP_TYPE != null) return;
    EntityTypes.SHEEP_TYPE = EntityTypeBuilder
            .builder(EntitySheep.class)
            .vanillaEntity(VanillaEntityId.SHEEP)
            .build();
  }

  public static void initShulker() {
    if (EntityTypes.SHULKER_TYPE != null) return;
    EntityTypes.SHULKER_TYPE = EntityTypeBuilder
            .builder(EntityShulker.class)
            .vanillaEntity(VanillaEntityId.SHULKER)
            .build();
  }

  public static void initShulkerBullet() {
    if (EntityTypes.SHULKER_BULLET_TYPE != null) return;
    EntityTypes.SHULKER_BULLET_TYPE = EntityTypeBuilder
            .builder(EntityShulkerBullet.class)
            .vanillaEntity(VanillaEntityId.SHULKER_BULLET)
            .build();
  }

  public static void initSilverfish() {
    if (EntityTypes.SILVERFISH_TYPE != null) return;
    EntityTypes.SILVERFISH_TYPE = EntityTypeBuilder
            .builder(EntitySilverfish.class)
            .vanillaEntity(VanillaEntityId.SILVERFISH)
            .build();
  }

  public static void initSkeleton() {
    if (EntityTypes.SKELETON_TYPE != null) return;
    EntityTypes.SKELETON_TYPE = EntityTypeBuilder
            .builder(EntitySkeleton.class)
            .vanillaEntity(VanillaEntityId.SKELETON)
            .build();
  }

  public static void initSkeletonHorse() {
    if (EntityTypes.SKELETON_HORSE_TYPE != null) return;
    EntityTypes.SKELETON_HORSE_TYPE = EntityTypeBuilder
            .builder(EntitySkeletonHorse.class)
            .vanillaEntity(VanillaEntityId.SKELETON_HORSE)
            .build();
  }

  public static void initSlime() {
    if (EntityTypes.SLIME_TYPE != null) return;
    EntityTypes.SLIME_TYPE = EntityTypeBuilder
            .builder(EntitySlime.class)
            .vanillaEntity(VanillaEntityId.SLIME)
            .build();
  }

  public static void initSmallFireball() {
    if (EntityTypes.SMALL_FIREBALL_TYPE != null) return;
    EntityTypes.SMALL_FIREBALL_TYPE = EntityTypeBuilder
            .builder(EntitySmallFireball.class)
            .vanillaEntity(VanillaEntityId.SMALL_FIREBALL)
            .build();
  }

  public static void initSniffer() {
    if (EntityTypes.SNIFFER_TYPE != null) return;
    EntityTypes.SNIFFER_TYPE = EntityTypeBuilder
            .builder(EntitySniffer.class)
            .vanillaEntity(VanillaEntityId.SNIFFER)
            .build();
  }

  public static void initSnowGolem() {
    if (EntityTypes.SNOW_GOLEM_TYPE != null) return;
    EntityTypes.SNOW_GOLEM_TYPE = EntityTypeBuilder
            .builder(EntitySnowGolem.class)
            .vanillaEntity(VanillaEntityId.SNOW_GOLEM)
            .build();
  }

  public static void initSnowball() {
    if (EntityTypes.SNOWBALL_TYPE != null) return;
    EntityTypes.SNOWBALL_TYPE = EntityTypeBuilder
            .builder(EntitySnowball.class)
            .vanillaEntity(VanillaEntityId.SNOWBALL)
            .build();
  }

  public static void initSpider() {
    if (EntityTypes.SPIDER_TYPE != null) return;
    EntityTypes.SPIDER_TYPE = EntityTypeBuilder
            .builder(EntitySpider.class)
            .vanillaEntity(VanillaEntityId.SPIDER)
            .build();
  }

  public static void initSplashPotion() {
    if (EntityTypes.SPLASH_POTION_TYPE != null) return;
    EntityTypes.SPLASH_POTION_TYPE = EntityTypeBuilder
            .builder(EntitySplashPotion.class)
            .vanillaEntity(VanillaEntityId.SPLASH_POTION)
            .build();
  }

  public static void initSquid() {
    if (EntityTypes.SQUID_TYPE != null) return;
    EntityTypes.SQUID_TYPE = EntityTypeBuilder
            .builder(EntitySquid.class)
            .vanillaEntity(VanillaEntityId.SQUID)
            .build();
  }

  public static void initStray() {
    if (EntityTypes.STRAY_TYPE != null) return;
    EntityTypes.STRAY_TYPE = EntityTypeBuilder
            .builder(EntityStray.class)
            .vanillaEntity(VanillaEntityId.STRAY)
            .build();
  }

  public static void initStrider() {
    if (EntityTypes.STRIDER_TYPE != null) return;
    EntityTypes.STRIDER_TYPE = EntityTypeBuilder
            .builder(EntityStrider.class)
            .vanillaEntity(VanillaEntityId.STRIDER)
            .build();
  }

  public static void initTadpole() {
    if (EntityTypes.TADPOLE_TYPE != null) return;
    EntityTypes.TADPOLE_TYPE = EntityTypeBuilder
            .builder(EntityTadpole.class)
            .vanillaEntity(VanillaEntityId.TADPOLE)
            .build();
  }

  public static void initThrownTrident() {
    if (EntityTypes.THROWN_TRIDENT_TYPE != null) return;
    EntityTypes.THROWN_TRIDENT_TYPE = EntityTypeBuilder
            .builder(EntityThrownTrident.class)
            .vanillaEntity(VanillaEntityId.THROWN_TRIDENT)
            .build();
  }

  public static void initTnt() {
    if (EntityTypes.TNT_TYPE != null) return;
    EntityTypes.TNT_TYPE = EntityTypeBuilder
            .builder(EntityTnt.class)
            .vanillaEntity(VanillaEntityId.TNT)
            .build();
  }

  public static void initTntMinecart() {
    if (EntityTypes.TNT_MINECART_TYPE != null) return;
    EntityTypes.TNT_MINECART_TYPE = EntityTypeBuilder
            .builder(EntityTntMinecart.class)
            .vanillaEntity(VanillaEntityId.TNT_MINECART)
            .build();
  }

  public static void initTraderLlama() {
    if (EntityTypes.TRADER_LLAMA_TYPE != null) return;
    EntityTypes.TRADER_LLAMA_TYPE = EntityTypeBuilder
            .builder(EntityTraderLlama.class)
            .vanillaEntity(VanillaEntityId.TRADER_LLAMA)
            .build();
  }

  public static void initTripodCamera() {
    if (EntityTypes.TRIPOD_CAMERA_TYPE != null) return;
    EntityTypes.TRIPOD_CAMERA_TYPE = EntityTypeBuilder
            .builder(EntityTripodCamera.class)
            .vanillaEntity(VanillaEntityId.TRIPOD_CAMERA)
            .build();
  }

  public static void initTropicalfish() {
    if (EntityTypes.TROPICALFISH_TYPE != null) return;
    EntityTypes.TROPICALFISH_TYPE = EntityTypeBuilder
            .builder(EntityTropicalfish.class)
            .vanillaEntity(VanillaEntityId.TROPICALFISH)
            .build();
  }

  public static void initTurtle() {
    if (EntityTypes.TURTLE_TYPE != null) return;
    EntityTypes.TURTLE_TYPE = EntityTypeBuilder
            .builder(EntityTurtle.class)
            .vanillaEntity(VanillaEntityId.TURTLE)
            .build();
  }

  public static void initVex() {
    if (EntityTypes.VEX_TYPE != null) return;
    EntityTypes.VEX_TYPE = EntityTypeBuilder
            .builder(EntityVex.class)
            .vanillaEntity(VanillaEntityId.VEX)
            .build();
  }

  public static void initVillager() {
    if (EntityTypes.VILLAGER_TYPE != null) return;
    EntityTypes.VILLAGER_TYPE = EntityTypeBuilder
            .builder(EntityVillager.class)
            .vanillaEntity(VanillaEntityId.VILLAGER)
            .build();
  }

  public static void initVillagerV2() {
    if (EntityTypes.VILLAGER_V2_TYPE != null) return;
    EntityTypes.VILLAGER_V2_TYPE = EntityTypeBuilder
            .builder(EntityVillagerV2.class)
            .vanillaEntity(VanillaEntityId.VILLAGER_V2)
            .build();
  }

  public static void initVindicator() {
    if (EntityTypes.VINDICATOR_TYPE != null) return;
    EntityTypes.VINDICATOR_TYPE = EntityTypeBuilder
            .builder(EntityVindicator.class)
            .vanillaEntity(VanillaEntityId.VINDICATOR)
            .build();
  }

  public static void initWanderingTrader() {
    if (EntityTypes.WANDERING_TRADER_TYPE != null) return;
    EntityTypes.WANDERING_TRADER_TYPE = EntityTypeBuilder
            .builder(EntityWanderingTrader.class)
            .vanillaEntity(VanillaEntityId.WANDERING_TRADER)
            .build();
  }

  public static void initWarden() {
    if (EntityTypes.WARDEN_TYPE != null) return;
    EntityTypes.WARDEN_TYPE = EntityTypeBuilder
            .builder(EntityWarden.class)
            .vanillaEntity(VanillaEntityId.WARDEN)
            .build();
  }

  public static void initWindChargeProjectile() {
    if (EntityTypes.WIND_CHARGE_PROJECTILE_TYPE != null) return;
    EntityTypes.WIND_CHARGE_PROJECTILE_TYPE = EntityTypeBuilder
            .builder(EntityWindChargeProjectile.class)
            .vanillaEntity(VanillaEntityId.WIND_CHARGE_PROJECTILE)
            .build();
  }

  public static void initWitch() {
    if (EntityTypes.WITCH_TYPE != null) return;
    EntityTypes.WITCH_TYPE = EntityTypeBuilder
            .builder(EntityWitch.class)
            .vanillaEntity(VanillaEntityId.WITCH)
            .build();
  }

  public static void initWither() {
    if (EntityTypes.WITHER_TYPE != null) return;
    EntityTypes.WITHER_TYPE = EntityTypeBuilder
            .builder(EntityWither.class)
            .vanillaEntity(VanillaEntityId.WITHER)
            .build();
  }

  public static void initWitherSkeleton() {
    if (EntityTypes.WITHER_SKELETON_TYPE != null) return;
    EntityTypes.WITHER_SKELETON_TYPE = EntityTypeBuilder
            .builder(EntityWitherSkeleton.class)
            .vanillaEntity(VanillaEntityId.WITHER_SKELETON)
            .build();
  }

  public static void initWitherSkull() {
    if (EntityTypes.WITHER_SKULL_TYPE != null) return;
    EntityTypes.WITHER_SKULL_TYPE = EntityTypeBuilder
            .builder(EntityWitherSkull.class)
            .vanillaEntity(VanillaEntityId.WITHER_SKULL)
            .build();
  }

  public static void initWitherSkullDangerous() {
    if (EntityTypes.WITHER_SKULL_DANGEROUS_TYPE != null) return;
    EntityTypes.WITHER_SKULL_DANGEROUS_TYPE = EntityTypeBuilder
            .builder(EntityWitherSkullDangerous.class)
            .vanillaEntity(VanillaEntityId.WITHER_SKULL_DANGEROUS)
            .build();
  }

  public static void initWolf() {
    if (EntityTypes.WOLF_TYPE != null) return;
    EntityTypes.WOLF_TYPE = EntityTypeBuilder
            .builder(EntityWolf.class)
            .vanillaEntity(VanillaEntityId.WOLF)
            .build();
  }

  public static void initXpBottle() {
    if (EntityTypes.XP_BOTTLE_TYPE != null) return;
    EntityTypes.XP_BOTTLE_TYPE = EntityTypeBuilder
            .builder(EntityXpBottle.class)
            .vanillaEntity(VanillaEntityId.XP_BOTTLE)
            .build();
  }

  public static void initXpOrb() {
    if (EntityTypes.XP_ORB_TYPE != null) return;
    EntityTypes.XP_ORB_TYPE = EntityTypeBuilder
            .builder(EntityXpOrb.class)
            .vanillaEntity(VanillaEntityId.XP_ORB)
            .build();
  }

  public static void initZoglin() {
    if (EntityTypes.ZOGLIN_TYPE != null) return;
    EntityTypes.ZOGLIN_TYPE = EntityTypeBuilder
            .builder(EntityZoglin.class)
            .vanillaEntity(VanillaEntityId.ZOGLIN)
            .build();
  }

  public static void initZombie() {
    if (EntityTypes.ZOMBIE_TYPE != null) return;
    EntityTypes.ZOMBIE_TYPE = EntityTypeBuilder
            .builder(EntityZombie.class)
            .vanillaEntity(VanillaEntityId.ZOMBIE)
            .build();
  }

  public static void initZombieHorse() {
    if (EntityTypes.ZOMBIE_HORSE_TYPE != null) return;
    EntityTypes.ZOMBIE_HORSE_TYPE = EntityTypeBuilder
            .builder(EntityZombieHorse.class)
            .vanillaEntity(VanillaEntityId.ZOMBIE_HORSE)
            .build();
  }

  public static void initZombiePigman() {
    if (EntityTypes.ZOMBIE_PIGMAN_TYPE != null) return;
    EntityTypes.ZOMBIE_PIGMAN_TYPE = EntityTypeBuilder
            .builder(EntityZombiePigman.class)
            .vanillaEntity(VanillaEntityId.ZOMBIE_PIGMAN)
            .build();
  }

  public static void initZombieVillager() {
    if (EntityTypes.ZOMBIE_VILLAGER_TYPE != null) return;
    EntityTypes.ZOMBIE_VILLAGER_TYPE = EntityTypeBuilder
            .builder(EntityZombieVillager.class)
            .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER)
            .build();
  }

  public static void initZombieVillagerV2() {
    if (EntityTypes.ZOMBIE_VILLAGER_V2_TYPE != null) return;
    EntityTypes.ZOMBIE_VILLAGER_V2_TYPE = EntityTypeBuilder
            .builder(EntityZombieVillagerV2.class)
            .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER_V2)
            .build();
  }
}
