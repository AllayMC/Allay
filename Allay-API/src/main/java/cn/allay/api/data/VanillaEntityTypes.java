package cn.allay.api.data;

import cn.allay.api.entity.impl.*;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * Allay Project <p>
 *
 * @author daoge_cmd
 */
public interface VanillaEntityTypes {
  EntityType<EntityAgent> AGENT_TYPE = EntityTypeBuilder
          .builder(EntityAgent.class)
          .vanillaEntity(VanillaEntityId.AGENT)
          .addBasicComponents()
          .build();

  EntityType<EntityAllay> ALLAY_TYPE = EntityTypeBuilder
          .builder(EntityAllay.class)
          .vanillaEntity(VanillaEntityId.ALLAY)
          .addBasicComponents()
          .build();

  EntityType<EntityAreaEffectCloud> AREA_EFFECT_CLOUD_TYPE = EntityTypeBuilder
          .builder(EntityAreaEffectCloud.class)
          .vanillaEntity(VanillaEntityId.AREA_EFFECT_CLOUD)
          .addBasicComponents()
          .build();

  EntityType<EntityArmorStand> ARMOR_STAND_TYPE = EntityTypeBuilder
          .builder(EntityArmorStand.class)
          .vanillaEntity(VanillaEntityId.ARMOR_STAND)
          .addBasicComponents()
          .build();

  EntityType<EntityArrow> ARROW_TYPE = EntityTypeBuilder
          .builder(EntityArrow.class)
          .vanillaEntity(VanillaEntityId.ARROW)
          .addBasicComponents()
          .build();

  EntityType<EntityAxolotl> AXOLOTL_TYPE = EntityTypeBuilder
          .builder(EntityAxolotl.class)
          .vanillaEntity(VanillaEntityId.AXOLOTL)
          .addBasicComponents()
          .build();

  EntityType<EntityBalloon> BALLOON_TYPE = EntityTypeBuilder
          .builder(EntityBalloon.class)
          .vanillaEntity(VanillaEntityId.BALLOON)
          .addBasicComponents()
          .build();

  EntityType<EntityBat> BAT_TYPE = EntityTypeBuilder
          .builder(EntityBat.class)
          .vanillaEntity(VanillaEntityId.BAT)
          .addBasicComponents()
          .build();

  EntityType<EntityBee> BEE_TYPE = EntityTypeBuilder
          .builder(EntityBee.class)
          .vanillaEntity(VanillaEntityId.BEE)
          .addBasicComponents()
          .build();

  EntityType<EntityBlaze> BLAZE_TYPE = EntityTypeBuilder
          .builder(EntityBlaze.class)
          .vanillaEntity(VanillaEntityId.BLAZE)
          .addBasicComponents()
          .build();

  EntityType<EntityBoat> BOAT_TYPE = EntityTypeBuilder
          .builder(EntityBoat.class)
          .vanillaEntity(VanillaEntityId.BOAT)
          .addBasicComponents()
          .build();

  EntityType<EntityCamel> CAMEL_TYPE = EntityTypeBuilder
          .builder(EntityCamel.class)
          .vanillaEntity(VanillaEntityId.CAMEL)
          .addBasicComponents()
          .build();

  EntityType<EntityCat> CAT_TYPE = EntityTypeBuilder
          .builder(EntityCat.class)
          .vanillaEntity(VanillaEntityId.CAT)
          .addBasicComponents()
          .build();

  EntityType<EntityCaveSpider> CAVE_SPIDER_TYPE = EntityTypeBuilder
          .builder(EntityCaveSpider.class)
          .vanillaEntity(VanillaEntityId.CAVE_SPIDER)
          .addBasicComponents()
          .build();

  EntityType<EntityChestBoat> CHEST_BOAT_TYPE = EntityTypeBuilder
          .builder(EntityChestBoat.class)
          .vanillaEntity(VanillaEntityId.CHEST_BOAT)
          .addBasicComponents()
          .build();

  EntityType<EntityChestMinecart> CHEST_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityChestMinecart.class)
          .vanillaEntity(VanillaEntityId.CHEST_MINECART)
          .addBasicComponents()
          .build();

  EntityType<EntityChicken> CHICKEN_TYPE = EntityTypeBuilder
          .builder(EntityChicken.class)
          .vanillaEntity(VanillaEntityId.CHICKEN)
          .addBasicComponents()
          .build();

  EntityType<EntityCod> COD_TYPE = EntityTypeBuilder
          .builder(EntityCod.class)
          .vanillaEntity(VanillaEntityId.COD)
          .addBasicComponents()
          .build();

  EntityType<EntityCommandBlockMinecart> COMMAND_BLOCK_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityCommandBlockMinecart.class)
          .vanillaEntity(VanillaEntityId.COMMAND_BLOCK_MINECART)
          .addBasicComponents()
          .build();

  EntityType<EntityCow> COW_TYPE = EntityTypeBuilder
          .builder(EntityCow.class)
          .vanillaEntity(VanillaEntityId.COW)
          .addBasicComponents()
          .build();

  EntityType<EntityCreeper> CREEPER_TYPE = EntityTypeBuilder
          .builder(EntityCreeper.class)
          .vanillaEntity(VanillaEntityId.CREEPER)
          .addBasicComponents()
          .build();

  EntityType<EntityDolphin> DOLPHIN_TYPE = EntityTypeBuilder
          .builder(EntityDolphin.class)
          .vanillaEntity(VanillaEntityId.DOLPHIN)
          .addBasicComponents()
          .build();

  EntityType<EntityDonkey> DONKEY_TYPE = EntityTypeBuilder
          .builder(EntityDonkey.class)
          .vanillaEntity(VanillaEntityId.DONKEY)
          .addBasicComponents()
          .build();

  EntityType<EntityDragonFireball> DRAGON_FIREBALL_TYPE = EntityTypeBuilder
          .builder(EntityDragonFireball.class)
          .vanillaEntity(VanillaEntityId.DRAGON_FIREBALL)
          .addBasicComponents()
          .build();

  EntityType<EntityDrowned> DROWNED_TYPE = EntityTypeBuilder
          .builder(EntityDrowned.class)
          .vanillaEntity(VanillaEntityId.DROWNED)
          .addBasicComponents()
          .build();

  EntityType<EntityEgg> EGG_TYPE = EntityTypeBuilder
          .builder(EntityEgg.class)
          .vanillaEntity(VanillaEntityId.EGG)
          .addBasicComponents()
          .build();

  EntityType<EntityElderGuardian> ELDER_GUARDIAN_TYPE = EntityTypeBuilder
          .builder(EntityElderGuardian.class)
          .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN)
          .addBasicComponents()
          .build();

  EntityType<EntityElderGuardianGhost> ELDER_GUARDIAN_GHOST_TYPE = EntityTypeBuilder
          .builder(EntityElderGuardianGhost.class)
          .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN_GHOST)
          .addBasicComponents()
          .build();

  EntityType<EntityEnderCrystal> ENDER_CRYSTAL_TYPE = EntityTypeBuilder
          .builder(EntityEnderCrystal.class)
          .vanillaEntity(VanillaEntityId.ENDER_CRYSTAL)
          .addBasicComponents()
          .build();

  EntityType<EntityEnderDragon> ENDER_DRAGON_TYPE = EntityTypeBuilder
          .builder(EntityEnderDragon.class)
          .vanillaEntity(VanillaEntityId.ENDER_DRAGON)
          .addBasicComponents()
          .build();

  EntityType<EntityEnderPearl> ENDER_PEARL_TYPE = EntityTypeBuilder
          .builder(EntityEnderPearl.class)
          .vanillaEntity(VanillaEntityId.ENDER_PEARL)
          .addBasicComponents()
          .build();

  EntityType<EntityEnderman> ENDERMAN_TYPE = EntityTypeBuilder
          .builder(EntityEnderman.class)
          .vanillaEntity(VanillaEntityId.ENDERMAN)
          .addBasicComponents()
          .build();

  EntityType<EntityEndermite> ENDERMITE_TYPE = EntityTypeBuilder
          .builder(EntityEndermite.class)
          .vanillaEntity(VanillaEntityId.ENDERMITE)
          .addBasicComponents()
          .build();

  EntityType<EntityEvocationFang> EVOCATION_FANG_TYPE = EntityTypeBuilder
          .builder(EntityEvocationFang.class)
          .vanillaEntity(VanillaEntityId.EVOCATION_FANG)
          .addBasicComponents()
          .build();

  EntityType<EntityEvocationIllager> EVOCATION_ILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityEvocationIllager.class)
          .vanillaEntity(VanillaEntityId.EVOCATION_ILLAGER)
          .addBasicComponents()
          .build();

  EntityType<EntityEyeOfEnderSignal> EYE_OF_ENDER_SIGNAL_TYPE = EntityTypeBuilder
          .builder(EntityEyeOfEnderSignal.class)
          .vanillaEntity(VanillaEntityId.EYE_OF_ENDER_SIGNAL)
          .addBasicComponents()
          .build();

  EntityType<EntityFallingBlock> FALLING_BLOCK_TYPE = EntityTypeBuilder
          .builder(EntityFallingBlock.class)
          .vanillaEntity(VanillaEntityId.FALLING_BLOCK)
          .addBasicComponents()
          .build();

  EntityType<EntityFireball> FIREBALL_TYPE = EntityTypeBuilder
          .builder(EntityFireball.class)
          .vanillaEntity(VanillaEntityId.FIREBALL)
          .addBasicComponents()
          .build();

  EntityType<EntityFireworksRocket> FIREWORKS_ROCKET_TYPE = EntityTypeBuilder
          .builder(EntityFireworksRocket.class)
          .vanillaEntity(VanillaEntityId.FIREWORKS_ROCKET)
          .addBasicComponents()
          .build();

  EntityType<EntityFishingHook> FISHING_HOOK_TYPE = EntityTypeBuilder
          .builder(EntityFishingHook.class)
          .vanillaEntity(VanillaEntityId.FISHING_HOOK)
          .addBasicComponents()
          .build();

  EntityType<EntityFox> FOX_TYPE = EntityTypeBuilder
          .builder(EntityFox.class)
          .vanillaEntity(VanillaEntityId.FOX)
          .addBasicComponents()
          .build();

  EntityType<EntityFrog> FROG_TYPE = EntityTypeBuilder
          .builder(EntityFrog.class)
          .vanillaEntity(VanillaEntityId.FROG)
          .addBasicComponents()
          .build();

  EntityType<EntityGhast> GHAST_TYPE = EntityTypeBuilder
          .builder(EntityGhast.class)
          .vanillaEntity(VanillaEntityId.GHAST)
          .addBasicComponents()
          .build();

  EntityType<EntityGlowSquid> GLOW_SQUID_TYPE = EntityTypeBuilder
          .builder(EntityGlowSquid.class)
          .vanillaEntity(VanillaEntityId.GLOW_SQUID)
          .addBasicComponents()
          .build();

  EntityType<EntityGoat> GOAT_TYPE = EntityTypeBuilder
          .builder(EntityGoat.class)
          .vanillaEntity(VanillaEntityId.GOAT)
          .addBasicComponents()
          .build();

  EntityType<EntityGuardian> GUARDIAN_TYPE = EntityTypeBuilder
          .builder(EntityGuardian.class)
          .vanillaEntity(VanillaEntityId.GUARDIAN)
          .addBasicComponents()
          .build();

  EntityType<EntityHoglin> HOGLIN_TYPE = EntityTypeBuilder
          .builder(EntityHoglin.class)
          .vanillaEntity(VanillaEntityId.HOGLIN)
          .addBasicComponents()
          .build();

  EntityType<EntityHopperMinecart> HOPPER_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityHopperMinecart.class)
          .vanillaEntity(VanillaEntityId.HOPPER_MINECART)
          .addBasicComponents()
          .build();

  EntityType<EntityHorse> HORSE_TYPE = EntityTypeBuilder
          .builder(EntityHorse.class)
          .vanillaEntity(VanillaEntityId.HORSE)
          .addBasicComponents()
          .build();

  EntityType<EntityHusk> HUSK_TYPE = EntityTypeBuilder
          .builder(EntityHusk.class)
          .vanillaEntity(VanillaEntityId.HUSK)
          .addBasicComponents()
          .build();

  EntityType<EntityIceBomb> ICE_BOMB_TYPE = EntityTypeBuilder
          .builder(EntityIceBomb.class)
          .vanillaEntity(VanillaEntityId.ICE_BOMB)
          .addBasicComponents()
          .build();

  EntityType<EntityIronGolem> IRON_GOLEM_TYPE = EntityTypeBuilder
          .builder(EntityIronGolem.class)
          .vanillaEntity(VanillaEntityId.IRON_GOLEM)
          .addBasicComponents()
          .build();

  EntityType<EntityItem> ITEM_TYPE = EntityTypeBuilder
          .builder(EntityItem.class)
          .vanillaEntity(VanillaEntityId.ITEM)
          .addBasicComponents()
          .build();

  EntityType<EntityLeashKnot> LEASH_KNOT_TYPE = EntityTypeBuilder
          .builder(EntityLeashKnot.class)
          .vanillaEntity(VanillaEntityId.LEASH_KNOT)
          .addBasicComponents()
          .build();

  EntityType<EntityLightningBolt> LIGHTNING_BOLT_TYPE = EntityTypeBuilder
          .builder(EntityLightningBolt.class)
          .vanillaEntity(VanillaEntityId.LIGHTNING_BOLT)
          .addBasicComponents()
          .build();

  EntityType<EntityLingeringPotion> LINGERING_POTION_TYPE = EntityTypeBuilder
          .builder(EntityLingeringPotion.class)
          .vanillaEntity(VanillaEntityId.LINGERING_POTION)
          .addBasicComponents()
          .build();

  EntityType<EntityLlama> LLAMA_TYPE = EntityTypeBuilder
          .builder(EntityLlama.class)
          .vanillaEntity(VanillaEntityId.LLAMA)
          .addBasicComponents()
          .build();

  EntityType<EntityLlamaSpit> LLAMA_SPIT_TYPE = EntityTypeBuilder
          .builder(EntityLlamaSpit.class)
          .vanillaEntity(VanillaEntityId.LLAMA_SPIT)
          .addBasicComponents()
          .build();

  EntityType<EntityMagmaCube> MAGMA_CUBE_TYPE = EntityTypeBuilder
          .builder(EntityMagmaCube.class)
          .vanillaEntity(VanillaEntityId.MAGMA_CUBE)
          .addBasicComponents()
          .build();

  EntityType<EntityMinecart> MINECART_TYPE = EntityTypeBuilder
          .builder(EntityMinecart.class)
          .vanillaEntity(VanillaEntityId.MINECART)
          .addBasicComponents()
          .build();

  EntityType<EntityMooshroom> MOOSHROOM_TYPE = EntityTypeBuilder
          .builder(EntityMooshroom.class)
          .vanillaEntity(VanillaEntityId.MOOSHROOM)
          .addBasicComponents()
          .build();

  EntityType<EntityMule> MULE_TYPE = EntityTypeBuilder
          .builder(EntityMule.class)
          .vanillaEntity(VanillaEntityId.MULE)
          .addBasicComponents()
          .build();

  EntityType<EntityNpc> NPC_TYPE = EntityTypeBuilder
          .builder(EntityNpc.class)
          .vanillaEntity(VanillaEntityId.NPC)
          .addBasicComponents()
          .build();

  EntityType<EntityOcelot> OCELOT_TYPE = EntityTypeBuilder
          .builder(EntityOcelot.class)
          .vanillaEntity(VanillaEntityId.OCELOT)
          .addBasicComponents()
          .build();

  EntityType<EntityPainting> PAINTING_TYPE = EntityTypeBuilder
          .builder(EntityPainting.class)
          .vanillaEntity(VanillaEntityId.PAINTING)
          .addBasicComponents()
          .build();

  EntityType<EntityPanda> PANDA_TYPE = EntityTypeBuilder
          .builder(EntityPanda.class)
          .vanillaEntity(VanillaEntityId.PANDA)
          .addBasicComponents()
          .build();

  EntityType<EntityParrot> PARROT_TYPE = EntityTypeBuilder
          .builder(EntityParrot.class)
          .vanillaEntity(VanillaEntityId.PARROT)
          .addBasicComponents()
          .build();

  EntityType<EntityPhantom> PHANTOM_TYPE = EntityTypeBuilder
          .builder(EntityPhantom.class)
          .vanillaEntity(VanillaEntityId.PHANTOM)
          .addBasicComponents()
          .build();

  EntityType<EntityPig> PIG_TYPE = EntityTypeBuilder
          .builder(EntityPig.class)
          .vanillaEntity(VanillaEntityId.PIG)
          .addBasicComponents()
          .build();

  EntityType<EntityPiglin> PIGLIN_TYPE = EntityTypeBuilder
          .builder(EntityPiglin.class)
          .vanillaEntity(VanillaEntityId.PIGLIN)
          .addBasicComponents()
          .build();

  EntityType<EntityPiglinBrute> PIGLIN_BRUTE_TYPE = EntityTypeBuilder
          .builder(EntityPiglinBrute.class)
          .vanillaEntity(VanillaEntityId.PIGLIN_BRUTE)
          .addBasicComponents()
          .build();

  EntityType<EntityPillager> PILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityPillager.class)
          .vanillaEntity(VanillaEntityId.PILLAGER)
          .addBasicComponents()
          .build();

  EntityType<EntityPlayer> PLAYER_TYPE = EntityTypeBuilder
          .builder(EntityPlayer.class)
          .vanillaEntity(VanillaEntityId.PLAYER)
          .addBasicComponents()
          .build();

  EntityType<EntityPolarBear> POLAR_BEAR_TYPE = EntityTypeBuilder
          .builder(EntityPolarBear.class)
          .vanillaEntity(VanillaEntityId.POLAR_BEAR)
          .addBasicComponents()
          .build();

  EntityType<EntityPufferfish> PUFFERFISH_TYPE = EntityTypeBuilder
          .builder(EntityPufferfish.class)
          .vanillaEntity(VanillaEntityId.PUFFERFISH)
          .addBasicComponents()
          .build();

  EntityType<EntityRabbit> RABBIT_TYPE = EntityTypeBuilder
          .builder(EntityRabbit.class)
          .vanillaEntity(VanillaEntityId.RABBIT)
          .addBasicComponents()
          .build();

  EntityType<EntityRavager> RAVAGER_TYPE = EntityTypeBuilder
          .builder(EntityRavager.class)
          .vanillaEntity(VanillaEntityId.RAVAGER)
          .addBasicComponents()
          .build();

  EntityType<EntitySalmon> SALMON_TYPE = EntityTypeBuilder
          .builder(EntitySalmon.class)
          .vanillaEntity(VanillaEntityId.SALMON)
          .addBasicComponents()
          .build();

  EntityType<EntitySheep> SHEEP_TYPE = EntityTypeBuilder
          .builder(EntitySheep.class)
          .vanillaEntity(VanillaEntityId.SHEEP)
          .addBasicComponents()
          .build();

  EntityType<EntityShulker> SHULKER_TYPE = EntityTypeBuilder
          .builder(EntityShulker.class)
          .vanillaEntity(VanillaEntityId.SHULKER)
          .addBasicComponents()
          .build();

  EntityType<EntityShulkerBullet> SHULKER_BULLET_TYPE = EntityTypeBuilder
          .builder(EntityShulkerBullet.class)
          .vanillaEntity(VanillaEntityId.SHULKER_BULLET)
          .addBasicComponents()
          .build();

  EntityType<EntitySilverfish> SILVERFISH_TYPE = EntityTypeBuilder
          .builder(EntitySilverfish.class)
          .vanillaEntity(VanillaEntityId.SILVERFISH)
          .addBasicComponents()
          .build();

  EntityType<EntitySkeleton> SKELETON_TYPE = EntityTypeBuilder
          .builder(EntitySkeleton.class)
          .vanillaEntity(VanillaEntityId.SKELETON)
          .addBasicComponents()
          .build();

  EntityType<EntitySkeletonHorse> SKELETON_HORSE_TYPE = EntityTypeBuilder
          .builder(EntitySkeletonHorse.class)
          .vanillaEntity(VanillaEntityId.SKELETON_HORSE)
          .addBasicComponents()
          .build();

  EntityType<EntitySlime> SLIME_TYPE = EntityTypeBuilder
          .builder(EntitySlime.class)
          .vanillaEntity(VanillaEntityId.SLIME)
          .addBasicComponents()
          .build();

  EntityType<EntitySmallFireball> SMALL_FIREBALL_TYPE = EntityTypeBuilder
          .builder(EntitySmallFireball.class)
          .vanillaEntity(VanillaEntityId.SMALL_FIREBALL)
          .addBasicComponents()
          .build();

  EntityType<EntitySniffer> SNIFFER_TYPE = EntityTypeBuilder
          .builder(EntitySniffer.class)
          .vanillaEntity(VanillaEntityId.SNIFFER)
          .addBasicComponents()
          .build();

  EntityType<EntitySnowGolem> SNOW_GOLEM_TYPE = EntityTypeBuilder
          .builder(EntitySnowGolem.class)
          .vanillaEntity(VanillaEntityId.SNOW_GOLEM)
          .addBasicComponents()
          .build();

  EntityType<EntitySnowball> SNOWBALL_TYPE = EntityTypeBuilder
          .builder(EntitySnowball.class)
          .vanillaEntity(VanillaEntityId.SNOWBALL)
          .addBasicComponents()
          .build();

  EntityType<EntitySpider> SPIDER_TYPE = EntityTypeBuilder
          .builder(EntitySpider.class)
          .vanillaEntity(VanillaEntityId.SPIDER)
          .addBasicComponents()
          .build();

  EntityType<EntitySplashPotion> SPLASH_POTION_TYPE = EntityTypeBuilder
          .builder(EntitySplashPotion.class)
          .vanillaEntity(VanillaEntityId.SPLASH_POTION)
          .addBasicComponents()
          .build();

  EntityType<EntitySquid> SQUID_TYPE = EntityTypeBuilder
          .builder(EntitySquid.class)
          .vanillaEntity(VanillaEntityId.SQUID)
          .addBasicComponents()
          .build();

  EntityType<EntityStray> STRAY_TYPE = EntityTypeBuilder
          .builder(EntityStray.class)
          .vanillaEntity(VanillaEntityId.STRAY)
          .addBasicComponents()
          .build();

  EntityType<EntityStrider> STRIDER_TYPE = EntityTypeBuilder
          .builder(EntityStrider.class)
          .vanillaEntity(VanillaEntityId.STRIDER)
          .addBasicComponents()
          .build();

  EntityType<EntityTadpole> TADPOLE_TYPE = EntityTypeBuilder
          .builder(EntityTadpole.class)
          .vanillaEntity(VanillaEntityId.TADPOLE)
          .addBasicComponents()
          .build();

  EntityType<EntityThrownTrident> THROWN_TRIDENT_TYPE = EntityTypeBuilder
          .builder(EntityThrownTrident.class)
          .vanillaEntity(VanillaEntityId.THROWN_TRIDENT)
          .addBasicComponents()
          .build();

  EntityType<EntityTnt> TNT_TYPE = EntityTypeBuilder
          .builder(EntityTnt.class)
          .vanillaEntity(VanillaEntityId.TNT)
          .addBasicComponents()
          .build();

  EntityType<EntityTntMinecart> TNT_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityTntMinecart.class)
          .vanillaEntity(VanillaEntityId.TNT_MINECART)
          .addBasicComponents()
          .build();

  EntityType<EntityTraderLlama> TRADER_LLAMA_TYPE = EntityTypeBuilder
          .builder(EntityTraderLlama.class)
          .vanillaEntity(VanillaEntityId.TRADER_LLAMA)
          .addBasicComponents()
          .build();

  EntityType<EntityTripodCamera> TRIPOD_CAMERA_TYPE = EntityTypeBuilder
          .builder(EntityTripodCamera.class)
          .vanillaEntity(VanillaEntityId.TRIPOD_CAMERA)
          .addBasicComponents()
          .build();

  EntityType<EntityTropicalfish> TROPICALFISH_TYPE = EntityTypeBuilder
          .builder(EntityTropicalfish.class)
          .vanillaEntity(VanillaEntityId.TROPICALFISH)
          .addBasicComponents()
          .build();

  EntityType<EntityTurtle> TURTLE_TYPE = EntityTypeBuilder
          .builder(EntityTurtle.class)
          .vanillaEntity(VanillaEntityId.TURTLE)
          .addBasicComponents()
          .build();

  EntityType<EntityVex> VEX_TYPE = EntityTypeBuilder
          .builder(EntityVex.class)
          .vanillaEntity(VanillaEntityId.VEX)
          .addBasicComponents()
          .build();

  EntityType<EntityVillager> VILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityVillager.class)
          .vanillaEntity(VanillaEntityId.VILLAGER)
          .addBasicComponents()
          .build();

  EntityType<EntityVillagerV2> VILLAGER_V2_TYPE = EntityTypeBuilder
          .builder(EntityVillagerV2.class)
          .vanillaEntity(VanillaEntityId.VILLAGER_V2)
          .addBasicComponents()
          .build();

  EntityType<EntityVindicator> VINDICATOR_TYPE = EntityTypeBuilder
          .builder(EntityVindicator.class)
          .vanillaEntity(VanillaEntityId.VINDICATOR)
          .addBasicComponents()
          .build();

  EntityType<EntityWanderingTrader> WANDERING_TRADER_TYPE = EntityTypeBuilder
          .builder(EntityWanderingTrader.class)
          .vanillaEntity(VanillaEntityId.WANDERING_TRADER)
          .addBasicComponents()
          .build();

  EntityType<EntityWarden> WARDEN_TYPE = EntityTypeBuilder
          .builder(EntityWarden.class)
          .vanillaEntity(VanillaEntityId.WARDEN)
          .addBasicComponents()
          .build();

  EntityType<EntityWitch> WITCH_TYPE = EntityTypeBuilder
          .builder(EntityWitch.class)
          .vanillaEntity(VanillaEntityId.WITCH)
          .addBasicComponents()
          .build();

  EntityType<EntityWither> WITHER_TYPE = EntityTypeBuilder
          .builder(EntityWither.class)
          .vanillaEntity(VanillaEntityId.WITHER)
          .addBasicComponents()
          .build();

  EntityType<EntityWitherSkeleton> WITHER_SKELETON_TYPE = EntityTypeBuilder
          .builder(EntityWitherSkeleton.class)
          .vanillaEntity(VanillaEntityId.WITHER_SKELETON)
          .addBasicComponents()
          .build();

  EntityType<EntityWitherSkull> WITHER_SKULL_TYPE = EntityTypeBuilder
          .builder(EntityWitherSkull.class)
          .vanillaEntity(VanillaEntityId.WITHER_SKULL)
          .addBasicComponents()
          .build();

  EntityType<EntityWitherSkullDangerous> WITHER_SKULL_DANGEROUS_TYPE = EntityTypeBuilder
          .builder(EntityWitherSkullDangerous.class)
          .vanillaEntity(VanillaEntityId.WITHER_SKULL_DANGEROUS)
          .addBasicComponents()
          .build();

  EntityType<EntityWolf> WOLF_TYPE = EntityTypeBuilder
          .builder(EntityWolf.class)
          .vanillaEntity(VanillaEntityId.WOLF)
          .addBasicComponents()
          .build();

  EntityType<EntityXpBottle> XP_BOTTLE_TYPE = EntityTypeBuilder
          .builder(EntityXpBottle.class)
          .vanillaEntity(VanillaEntityId.XP_BOTTLE)
          .addBasicComponents()
          .build();

  EntityType<EntityXpOrb> XP_ORB_TYPE = EntityTypeBuilder
          .builder(EntityXpOrb.class)
          .vanillaEntity(VanillaEntityId.XP_ORB)
          .addBasicComponents()
          .build();

  EntityType<EntityZoglin> ZOGLIN_TYPE = EntityTypeBuilder
          .builder(EntityZoglin.class)
          .vanillaEntity(VanillaEntityId.ZOGLIN)
          .addBasicComponents()
          .build();

  EntityType<EntityZombie> ZOMBIE_TYPE = EntityTypeBuilder
          .builder(EntityZombie.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE)
          .addBasicComponents()
          .build();

  EntityType<EntityZombieHorse> ZOMBIE_HORSE_TYPE = EntityTypeBuilder
          .builder(EntityZombieHorse.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_HORSE)
          .addBasicComponents()
          .build();

  EntityType<EntityZombiePigman> ZOMBIE_PIGMAN_TYPE = EntityTypeBuilder
          .builder(EntityZombiePigman.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_PIGMAN)
          .addBasicComponents()
          .build();

  EntityType<EntityZombieVillager> ZOMBIE_VILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityZombieVillager.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER)
          .addBasicComponents()
          .build();

  EntityType<EntityZombieVillagerV2> ZOMBIE_VILLAGER_V2_TYPE = EntityTypeBuilder
          .builder(EntityZombieVillagerV2.class)
          .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER_V2)
          .addBasicComponents()
          .build();
}
