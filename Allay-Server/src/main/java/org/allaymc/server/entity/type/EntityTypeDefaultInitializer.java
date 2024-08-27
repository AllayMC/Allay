package org.allaymc.server.entity.type;

import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.entity.interfaces.*;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public final class EntityTypeDefaultInitializer {
    public static void initAgent() {
        if (EntityTypes.AGENT != null) return;
        EntityTypes.AGENT = EntityTypeBuilder
                .builder(EntityAgent.class)
                .vanillaEntity(EntityId.AGENT)
                .build();
    }

    public static void initAllay() {
        if (EntityTypes.ALLAY != null) return;
        EntityTypes.ALLAY = EntityTypeBuilder
                .builder(EntityAllay.class)
                .vanillaEntity(EntityId.ALLAY)
                .build();
    }

    public static void initAreaEffectCloud() {
        if (EntityTypes.AREA_EFFECT_CLOUD != null) return;
        EntityTypes.AREA_EFFECT_CLOUD = EntityTypeBuilder
                .builder(EntityAreaEffectCloud.class)
                .vanillaEntity(EntityId.AREA_EFFECT_CLOUD)
                .build();
    }

    public static void initArmadillo() {
        if (EntityTypes.ARMADILLO != null) return;
        EntityTypes.ARMADILLO = EntityTypeBuilder
                .builder(EntityArmadillo.class)
                .vanillaEntity(EntityId.ARMADILLO)
                .build();
    }

    public static void initArmorStand() {
        if (EntityTypes.ARMOR_STAND != null) return;
        EntityTypes.ARMOR_STAND = EntityTypeBuilder
                .builder(EntityArmorStand.class)
                .vanillaEntity(EntityId.ARMOR_STAND)
                .build();
    }

    public static void initArrow() {
        if (EntityTypes.ARROW != null) return;
        EntityTypes.ARROW = EntityTypeBuilder
                .builder(EntityArrow.class)
                .vanillaEntity(EntityId.ARROW)
                .build();
    }

    public static void initAxolotl() {
        if (EntityTypes.AXOLOTL != null) return;
        EntityTypes.AXOLOTL = EntityTypeBuilder
                .builder(EntityAxolotl.class)
                .vanillaEntity(EntityId.AXOLOTL)
                .build();
    }

    public static void initBalloon() {
        if (EntityTypes.BALLOON != null) return;
        EntityTypes.BALLOON = EntityTypeBuilder
                .builder(EntityBalloon.class)
                .vanillaEntity(EntityId.BALLOON)
                .build();
    }

    public static void initBat() {
        if (EntityTypes.BAT != null) return;
        EntityTypes.BAT = EntityTypeBuilder
                .builder(EntityBat.class)
                .vanillaEntity(EntityId.BAT)
                .build();
    }

    public static void initBee() {
        if (EntityTypes.BEE != null) return;
        EntityTypes.BEE = EntityTypeBuilder
                .builder(EntityBee.class)
                .vanillaEntity(EntityId.BEE)
                .build();
    }

    public static void initBlaze() {
        if (EntityTypes.BLAZE != null) return;
        EntityTypes.BLAZE = EntityTypeBuilder
                .builder(EntityBlaze.class)
                .vanillaEntity(EntityId.BLAZE)
                .build();
    }

    public static void initBoat() {
        if (EntityTypes.BOAT != null) return;
        EntityTypes.BOAT = EntityTypeBuilder
                .builder(EntityBoat.class)
                .vanillaEntity(EntityId.BOAT)
                .build();
    }

    public static void initBogged() {
        if (EntityTypes.BOGGED != null) return;
        EntityTypes.BOGGED = EntityTypeBuilder
                .builder(EntityBogged.class)
                .vanillaEntity(EntityId.BOGGED)
                .build();
    }

    public static void initBreeze() {
        if (EntityTypes.BREEZE != null) return;
        EntityTypes.BREEZE = EntityTypeBuilder
                .builder(EntityBreeze.class)
                .vanillaEntity(EntityId.BREEZE)
                .build();
    }

    public static void initBreezeWindChargeProjectile() {
        if (EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE != null) return;
        EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE = EntityTypeBuilder
                .builder(EntityBreezeWindChargeProjectile.class)
                .vanillaEntity(EntityId.BREEZE_WIND_CHARGE_PROJECTILE)
                .build();
    }

    public static void initCamel() {
        if (EntityTypes.CAMEL != null) return;
        EntityTypes.CAMEL = EntityTypeBuilder
                .builder(EntityCamel.class)
                .vanillaEntity(EntityId.CAMEL)
                .build();
    }

    public static void initCat() {
        if (EntityTypes.CAT != null) return;
        EntityTypes.CAT = EntityTypeBuilder
                .builder(EntityCat.class)
                .vanillaEntity(EntityId.CAT)
                .build();
    }

    public static void initCaveSpider() {
        if (EntityTypes.CAVE_SPIDER != null) return;
        EntityTypes.CAVE_SPIDER = EntityTypeBuilder
                .builder(EntityCaveSpider.class)
                .vanillaEntity(EntityId.CAVE_SPIDER)
                .build();
    }

    public static void initChestBoat() {
        if (EntityTypes.CHEST_BOAT != null) return;
        EntityTypes.CHEST_BOAT = EntityTypeBuilder
                .builder(EntityChestBoat.class)
                .vanillaEntity(EntityId.CHEST_BOAT)
                .build();
    }

    public static void initChestMinecart() {
        if (EntityTypes.CHEST_MINECART != null) return;
        EntityTypes.CHEST_MINECART = EntityTypeBuilder
                .builder(EntityChestMinecart.class)
                .vanillaEntity(EntityId.CHEST_MINECART)
                .build();
    }

    public static void initChicken() {
        if (EntityTypes.CHICKEN != null) return;
        EntityTypes.CHICKEN = EntityTypeBuilder
                .builder(EntityChicken.class)
                .vanillaEntity(EntityId.CHICKEN)
                .build();
    }

    public static void initCod() {
        if (EntityTypes.COD != null) return;
        EntityTypes.COD = EntityTypeBuilder
                .builder(EntityCod.class)
                .vanillaEntity(EntityId.COD)
                .build();
    }

    public static void initCommandBlockMinecart() {
        if (EntityTypes.COMMAND_BLOCK_MINECART != null) return;
        EntityTypes.COMMAND_BLOCK_MINECART = EntityTypeBuilder
                .builder(EntityCommandBlockMinecart.class)
                .vanillaEntity(EntityId.COMMAND_BLOCK_MINECART)
                .build();
    }

    public static void initCow() {
        if (EntityTypes.COW != null) return;
        EntityTypes.COW = EntityTypeBuilder
                .builder(EntityCow.class)
                .vanillaEntity(EntityId.COW)
                .build();
    }

    public static void initCreeper() {
        if (EntityTypes.CREEPER != null) return;
        EntityTypes.CREEPER = EntityTypeBuilder
                .builder(EntityCreeper.class)
                .vanillaEntity(EntityId.CREEPER)
                .build();
    }

    public static void initDolphin() {
        if (EntityTypes.DOLPHIN != null) return;
        EntityTypes.DOLPHIN = EntityTypeBuilder
                .builder(EntityDolphin.class)
                .vanillaEntity(EntityId.DOLPHIN)
                .build();
    }

    public static void initDonkey() {
        if (EntityTypes.DONKEY != null) return;
        EntityTypes.DONKEY = EntityTypeBuilder
                .builder(EntityDonkey.class)
                .vanillaEntity(EntityId.DONKEY)
                .build();
    }

    public static void initDragonFireball() {
        if (EntityTypes.DRAGON_FIREBALL != null) return;
        EntityTypes.DRAGON_FIREBALL = EntityTypeBuilder
                .builder(EntityDragonFireball.class)
                .vanillaEntity(EntityId.DRAGON_FIREBALL)
                .build();
    }

    public static void initDrowned() {
        if (EntityTypes.DROWNED != null) return;
        EntityTypes.DROWNED = EntityTypeBuilder
                .builder(EntityDrowned.class)
                .vanillaEntity(EntityId.DROWNED)
                .build();
    }

    public static void initEgg() {
        if (EntityTypes.EGG != null) return;
        EntityTypes.EGG = EntityTypeBuilder
                .builder(EntityEgg.class)
                .vanillaEntity(EntityId.EGG)
                .build();
    }

    public static void initElderGuardian() {
        if (EntityTypes.ELDER_GUARDIAN != null) return;
        EntityTypes.ELDER_GUARDIAN = EntityTypeBuilder
                .builder(EntityElderGuardian.class)
                .vanillaEntity(EntityId.ELDER_GUARDIAN)
                .build();
    }

    public static void initElderGuardianGhost() {
        if (EntityTypes.ELDER_GUARDIAN_GHOST != null) return;
        EntityTypes.ELDER_GUARDIAN_GHOST = EntityTypeBuilder
                .builder(EntityElderGuardianGhost.class)
                .vanillaEntity(EntityId.ELDER_GUARDIAN_GHOST)
                .build();
    }

    public static void initEnderCrystal() {
        if (EntityTypes.ENDER_CRYSTAL != null) return;
        EntityTypes.ENDER_CRYSTAL = EntityTypeBuilder
                .builder(EntityEnderCrystal.class)
                .vanillaEntity(EntityId.ENDER_CRYSTAL)
                .build();
    }

    public static void initEnderDragon() {
        if (EntityTypes.ENDER_DRAGON != null) return;
        EntityTypes.ENDER_DRAGON = EntityTypeBuilder
                .builder(EntityEnderDragon.class)
                .vanillaEntity(EntityId.ENDER_DRAGON)
                .build();
    }

    public static void initEnderPearl() {
        if (EntityTypes.ENDER_PEARL != null) return;
        EntityTypes.ENDER_PEARL = EntityTypeBuilder
                .builder(EntityEnderPearl.class)
                .vanillaEntity(EntityId.ENDER_PEARL)
                .build();
    }

    public static void initEnderman() {
        if (EntityTypes.ENDERMAN != null) return;
        EntityTypes.ENDERMAN = EntityTypeBuilder
                .builder(EntityEnderman.class)
                .vanillaEntity(EntityId.ENDERMAN)
                .build();
    }

    public static void initEndermite() {
        if (EntityTypes.ENDERMITE != null) return;
        EntityTypes.ENDERMITE = EntityTypeBuilder
                .builder(EntityEndermite.class)
                .vanillaEntity(EntityId.ENDERMITE)
                .build();
    }

    public static void initEvocationFang() {
        if (EntityTypes.EVOCATION_FANG != null) return;
        EntityTypes.EVOCATION_FANG = EntityTypeBuilder
                .builder(EntityEvocationFang.class)
                .vanillaEntity(EntityId.EVOCATION_FANG)
                .build();
    }

    public static void initEvocationIllager() {
        if (EntityTypes.EVOCATION_ILLAGER != null) return;
        EntityTypes.EVOCATION_ILLAGER = EntityTypeBuilder
                .builder(EntityEvocationIllager.class)
                .vanillaEntity(EntityId.EVOCATION_ILLAGER)
                .build();
    }

    public static void initEyeOfEnderSignal() {
        if (EntityTypes.EYE_OF_ENDER_SIGNAL != null) return;
        EntityTypes.EYE_OF_ENDER_SIGNAL = EntityTypeBuilder
                .builder(EntityEyeOfEnderSignal.class)
                .vanillaEntity(EntityId.EYE_OF_ENDER_SIGNAL)
                .build();
    }

    public static void initFallingBlock() {
        if (EntityTypes.FALLING_BLOCK != null) return;
        EntityTypes.FALLING_BLOCK = EntityTypeBuilder
                .builder(EntityFallingBlock.class)
                .vanillaEntity(EntityId.FALLING_BLOCK)
                .build();
    }

    public static void initFireball() {
        if (EntityTypes.FIREBALL != null) return;
        EntityTypes.FIREBALL = EntityTypeBuilder
                .builder(EntityFireball.class)
                .vanillaEntity(EntityId.FIREBALL)
                .build();
    }

    public static void initFireworksRocket() {
        if (EntityTypes.FIREWORKS_ROCKET != null) return;
        EntityTypes.FIREWORKS_ROCKET = EntityTypeBuilder
                .builder(EntityFireworksRocket.class)
                .vanillaEntity(EntityId.FIREWORKS_ROCKET)
                .build();
    }

    public static void initFishingHook() {
        if (EntityTypes.FISHING_HOOK != null) return;
        EntityTypes.FISHING_HOOK = EntityTypeBuilder
                .builder(EntityFishingHook.class)
                .vanillaEntity(EntityId.FISHING_HOOK)
                .build();
    }

    public static void initFox() {
        if (EntityTypes.FOX != null) return;
        EntityTypes.FOX = EntityTypeBuilder
                .builder(EntityFox.class)
                .vanillaEntity(EntityId.FOX)
                .build();
    }

    public static void initFrog() {
        if (EntityTypes.FROG != null) return;
        EntityTypes.FROG = EntityTypeBuilder
                .builder(EntityFrog.class)
                .vanillaEntity(EntityId.FROG)
                .build();
    }

    public static void initGhast() {
        if (EntityTypes.GHAST != null) return;
        EntityTypes.GHAST = EntityTypeBuilder
                .builder(EntityGhast.class)
                .vanillaEntity(EntityId.GHAST)
                .build();
    }

    public static void initGlowSquid() {
        if (EntityTypes.GLOW_SQUID != null) return;
        EntityTypes.GLOW_SQUID = EntityTypeBuilder
                .builder(EntityGlowSquid.class)
                .vanillaEntity(EntityId.GLOW_SQUID)
                .build();
    }

    public static void initGoat() {
        if (EntityTypes.GOAT != null) return;
        EntityTypes.GOAT = EntityTypeBuilder
                .builder(EntityGoat.class)
                .vanillaEntity(EntityId.GOAT)
                .build();
    }

    public static void initGuardian() {
        if (EntityTypes.GUARDIAN != null) return;
        EntityTypes.GUARDIAN = EntityTypeBuilder
                .builder(EntityGuardian.class)
                .vanillaEntity(EntityId.GUARDIAN)
                .build();
    }

    public static void initHoglin() {
        if (EntityTypes.HOGLIN != null) return;
        EntityTypes.HOGLIN = EntityTypeBuilder
                .builder(EntityHoglin.class)
                .vanillaEntity(EntityId.HOGLIN)
                .build();
    }

    public static void initHopperMinecart() {
        if (EntityTypes.HOPPER_MINECART != null) return;
        EntityTypes.HOPPER_MINECART = EntityTypeBuilder
                .builder(EntityHopperMinecart.class)
                .vanillaEntity(EntityId.HOPPER_MINECART)
                .build();
    }

    public static void initHorse() {
        if (EntityTypes.HORSE != null) return;
        EntityTypes.HORSE = EntityTypeBuilder
                .builder(EntityHorse.class)
                .vanillaEntity(EntityId.HORSE)
                .build();
    }

    public static void initHusk() {
        if (EntityTypes.HUSK != null) return;
        EntityTypes.HUSK = EntityTypeBuilder
                .builder(EntityHusk.class)
                .vanillaEntity(EntityId.HUSK)
                .build();
    }

    public static void initIceBomb() {
        if (EntityTypes.ICE_BOMB != null) return;
        EntityTypes.ICE_BOMB = EntityTypeBuilder
                .builder(EntityIceBomb.class)
                .vanillaEntity(EntityId.ICE_BOMB)
                .build();
    }

    public static void initIronGolem() {
        if (EntityTypes.IRON_GOLEM != null) return;
        EntityTypes.IRON_GOLEM = EntityTypeBuilder
                .builder(EntityIronGolem.class)
                .vanillaEntity(EntityId.IRON_GOLEM)
                .build();
    }

    public static void initItem() {
        if (EntityTypes.ITEM != null) return;
        EntityTypes.ITEM = EntityTypeBuilder
                .builder(EntityItem.class)
                .vanillaEntity(EntityId.ITEM)
                .build();
    }

    public static void initLeashKnot() {
        if (EntityTypes.LEASH_KNOT != null) return;
        EntityTypes.LEASH_KNOT = EntityTypeBuilder
                .builder(EntityLeashKnot.class)
                .vanillaEntity(EntityId.LEASH_KNOT)
                .build();
    }

    public static void initLightningBolt() {
        if (EntityTypes.LIGHTNING_BOLT != null) return;
        EntityTypes.LIGHTNING_BOLT = EntityTypeBuilder
                .builder(EntityLightningBolt.class)
                .vanillaEntity(EntityId.LIGHTNING_BOLT)
                .build();
    }

    public static void initLingeringPotion() {
        if (EntityTypes.LINGERING_POTION != null) return;
        EntityTypes.LINGERING_POTION = EntityTypeBuilder
                .builder(EntityLingeringPotion.class)
                .vanillaEntity(EntityId.LINGERING_POTION)
                .build();
    }

    public static void initLlama() {
        if (EntityTypes.LLAMA != null) return;
        EntityTypes.LLAMA = EntityTypeBuilder
                .builder(EntityLlama.class)
                .vanillaEntity(EntityId.LLAMA)
                .build();
    }

    public static void initLlamaSpit() {
        if (EntityTypes.LLAMA_SPIT != null) return;
        EntityTypes.LLAMA_SPIT = EntityTypeBuilder
                .builder(EntityLlamaSpit.class)
                .vanillaEntity(EntityId.LLAMA_SPIT)
                .build();
    }

    public static void initMagmaCube() {
        if (EntityTypes.MAGMA_CUBE != null) return;
        EntityTypes.MAGMA_CUBE = EntityTypeBuilder
                .builder(EntityMagmaCube.class)
                .vanillaEntity(EntityId.MAGMA_CUBE)
                .build();
    }

    public static void initMinecart() {
        if (EntityTypes.MINECART != null) return;
        EntityTypes.MINECART = EntityTypeBuilder
                .builder(EntityMinecart.class)
                .vanillaEntity(EntityId.MINECART)
                .build();
    }

    public static void initMooshroom() {
        if (EntityTypes.MOOSHROOM != null) return;
        EntityTypes.MOOSHROOM = EntityTypeBuilder
                .builder(EntityMooshroom.class)
                .vanillaEntity(EntityId.MOOSHROOM)
                .build();
    }

    public static void initMule() {
        if (EntityTypes.MULE != null) return;
        EntityTypes.MULE = EntityTypeBuilder
                .builder(EntityMule.class)
                .vanillaEntity(EntityId.MULE)
                .build();
    }

    public static void initNpc() {
        if (EntityTypes.NPC != null) return;
        EntityTypes.NPC = EntityTypeBuilder
                .builder(EntityNpc.class)
                .vanillaEntity(EntityId.NPC)
                .build();
    }

    public static void initOcelot() {
        if (EntityTypes.OCELOT != null) return;
        EntityTypes.OCELOT = EntityTypeBuilder
                .builder(EntityOcelot.class)
                .vanillaEntity(EntityId.OCELOT)
                .build();
    }

    public static void initOminousItemSpawner() {
        if (EntityTypes.OMINOUS_ITEM_SPAWNER != null) return;
        EntityTypes.OMINOUS_ITEM_SPAWNER = EntityTypeBuilder
                .builder(EntityOminousItemSpawner.class)
                .vanillaEntity(EntityId.OMINOUS_ITEM_SPAWNER)
                .build();
    }

    public static void initPainting() {
        if (EntityTypes.PAINTING != null) return;
        EntityTypes.PAINTING = EntityTypeBuilder
                .builder(EntityPainting.class)
                .vanillaEntity(EntityId.PAINTING)
                .build();
    }

    public static void initPanda() {
        if (EntityTypes.PANDA != null) return;
        EntityTypes.PANDA = EntityTypeBuilder
                .builder(EntityPanda.class)
                .vanillaEntity(EntityId.PANDA)
                .build();
    }

    public static void initParrot() {
        if (EntityTypes.PARROT != null) return;
        EntityTypes.PARROT = EntityTypeBuilder
                .builder(EntityParrot.class)
                .vanillaEntity(EntityId.PARROT)
                .build();
    }

    public static void initPhantom() {
        if (EntityTypes.PHANTOM != null) return;
        EntityTypes.PHANTOM = EntityTypeBuilder
                .builder(EntityPhantom.class)
                .vanillaEntity(EntityId.PHANTOM)
                .build();
    }

    public static void initPig() {
        if (EntityTypes.PIG != null) return;
        EntityTypes.PIG = EntityTypeBuilder
                .builder(EntityPig.class)
                .vanillaEntity(EntityId.PIG)
                .build();
    }

    public static void initPiglin() {
        if (EntityTypes.PIGLIN != null) return;
        EntityTypes.PIGLIN = EntityTypeBuilder
                .builder(EntityPiglin.class)
                .vanillaEntity(EntityId.PIGLIN)
                .build();
    }

    public static void initPiglinBrute() {
        if (EntityTypes.PIGLIN_BRUTE != null) return;
        EntityTypes.PIGLIN_BRUTE = EntityTypeBuilder
                .builder(EntityPiglinBrute.class)
                .vanillaEntity(EntityId.PIGLIN_BRUTE)
                .build();
    }

    public static void initPillager() {
        if (EntityTypes.PILLAGER != null) return;
        EntityTypes.PILLAGER = EntityTypeBuilder
                .builder(EntityPillager.class)
                .vanillaEntity(EntityId.PILLAGER)
                .build();
    }

    public static void initPlayer() {
        if (EntityTypes.PLAYER != null) return;
        EntityTypes.PLAYER = EntityTypeBuilder
                .builder(EntityPlayer.class)
                .vanillaEntity(EntityId.PLAYER)
                .build();
    }

    public static void initPolarBear() {
        if (EntityTypes.POLAR_BEAR != null) return;
        EntityTypes.POLAR_BEAR = EntityTypeBuilder
                .builder(EntityPolarBear.class)
                .vanillaEntity(EntityId.POLAR_BEAR)
                .build();
    }

    public static void initPufferfish() {
        if (EntityTypes.PUFFERFISH != null) return;
        EntityTypes.PUFFERFISH = EntityTypeBuilder
                .builder(EntityPufferfish.class)
                .vanillaEntity(EntityId.PUFFERFISH)
                .build();
    }

    public static void initRabbit() {
        if (EntityTypes.RABBIT != null) return;
        EntityTypes.RABBIT = EntityTypeBuilder
                .builder(EntityRabbit.class)
                .vanillaEntity(EntityId.RABBIT)
                .build();
    }

    public static void initRavager() {
        if (EntityTypes.RAVAGER != null) return;
        EntityTypes.RAVAGER = EntityTypeBuilder
                .builder(EntityRavager.class)
                .vanillaEntity(EntityId.RAVAGER)
                .build();
    }

    public static void initSalmon() {
        if (EntityTypes.SALMON != null) return;
        EntityTypes.SALMON = EntityTypeBuilder
                .builder(EntitySalmon.class)
                .vanillaEntity(EntityId.SALMON)
                .build();
    }

    public static void initSheep() {
        if (EntityTypes.SHEEP != null) return;
        EntityTypes.SHEEP = EntityTypeBuilder
                .builder(EntitySheep.class)
                .vanillaEntity(EntityId.SHEEP)
                .build();
    }

    public static void initShulker() {
        if (EntityTypes.SHULKER != null) return;
        EntityTypes.SHULKER = EntityTypeBuilder
                .builder(EntityShulker.class)
                .vanillaEntity(EntityId.SHULKER)
                .build();
    }

    public static void initShulkerBullet() {
        if (EntityTypes.SHULKER_BULLET != null) return;
        EntityTypes.SHULKER_BULLET = EntityTypeBuilder
                .builder(EntityShulkerBullet.class)
                .vanillaEntity(EntityId.SHULKER_BULLET)
                .build();
    }

    public static void initSilverfish() {
        if (EntityTypes.SILVERFISH != null) return;
        EntityTypes.SILVERFISH = EntityTypeBuilder
                .builder(EntitySilverfish.class)
                .vanillaEntity(EntityId.SILVERFISH)
                .build();
    }

    public static void initSkeleton() {
        if (EntityTypes.SKELETON != null) return;
        EntityTypes.SKELETON = EntityTypeBuilder
                .builder(EntitySkeleton.class)
                .vanillaEntity(EntityId.SKELETON)
                .build();
    }

    public static void initSkeletonHorse() {
        if (EntityTypes.SKELETON_HORSE != null) return;
        EntityTypes.SKELETON_HORSE = EntityTypeBuilder
                .builder(EntitySkeletonHorse.class)
                .vanillaEntity(EntityId.SKELETON_HORSE)
                .build();
    }

    public static void initSlime() {
        if (EntityTypes.SLIME != null) return;
        EntityTypes.SLIME = EntityTypeBuilder
                .builder(EntitySlime.class)
                .vanillaEntity(EntityId.SLIME)
                .build();
    }

    public static void initSmallFireball() {
        if (EntityTypes.SMALL_FIREBALL != null) return;
        EntityTypes.SMALL_FIREBALL = EntityTypeBuilder
                .builder(EntitySmallFireball.class)
                .vanillaEntity(EntityId.SMALL_FIREBALL)
                .build();
    }

    public static void initSniffer() {
        if (EntityTypes.SNIFFER != null) return;
        EntityTypes.SNIFFER = EntityTypeBuilder
                .builder(EntitySniffer.class)
                .vanillaEntity(EntityId.SNIFFER)
                .build();
    }

    public static void initSnowGolem() {
        if (EntityTypes.SNOW_GOLEM != null) return;
        EntityTypes.SNOW_GOLEM = EntityTypeBuilder
                .builder(EntitySnowGolem.class)
                .vanillaEntity(EntityId.SNOW_GOLEM)
                .build();
    }

    public static void initSnowball() {
        if (EntityTypes.SNOWBALL != null) return;
        EntityTypes.SNOWBALL = EntityTypeBuilder
                .builder(EntitySnowball.class)
                .vanillaEntity(EntityId.SNOWBALL)
                .build();
    }

    public static void initSpider() {
        if (EntityTypes.SPIDER != null) return;
        EntityTypes.SPIDER = EntityTypeBuilder
                .builder(EntitySpider.class)
                .vanillaEntity(EntityId.SPIDER)
                .build();
    }

    public static void initSplashPotion() {
        if (EntityTypes.SPLASH_POTION != null) return;
        EntityTypes.SPLASH_POTION = EntityTypeBuilder
                .builder(EntitySplashPotion.class)
                .vanillaEntity(EntityId.SPLASH_POTION)
                .build();
    }

    public static void initSquid() {
        if (EntityTypes.SQUID != null) return;
        EntityTypes.SQUID = EntityTypeBuilder
                .builder(EntitySquid.class)
                .vanillaEntity(EntityId.SQUID)
                .build();
    }

    public static void initStray() {
        if (EntityTypes.STRAY != null) return;
        EntityTypes.STRAY = EntityTypeBuilder
                .builder(EntityStray.class)
                .vanillaEntity(EntityId.STRAY)
                .build();
    }

    public static void initStrider() {
        if (EntityTypes.STRIDER != null) return;
        EntityTypes.STRIDER = EntityTypeBuilder
                .builder(EntityStrider.class)
                .vanillaEntity(EntityId.STRIDER)
                .build();
    }

    public static void initTadpole() {
        if (EntityTypes.TADPOLE != null) return;
        EntityTypes.TADPOLE = EntityTypeBuilder
                .builder(EntityTadpole.class)
                .vanillaEntity(EntityId.TADPOLE)
                .build();
    }

    public static void initThrownTrident() {
        if (EntityTypes.THROWN_TRIDENT != null) return;
        EntityTypes.THROWN_TRIDENT = EntityTypeBuilder
                .builder(EntityThrownTrident.class)
                .vanillaEntity(EntityId.THROWN_TRIDENT)
                .build();
    }

    public static void initTnt() {
        if (EntityTypes.TNT != null) return;
        EntityTypes.TNT = EntityTypeBuilder
                .builder(EntityTnt.class)
                .vanillaEntity(EntityId.TNT)
                .build();
    }

    public static void initTntMinecart() {
        if (EntityTypes.TNT_MINECART != null) return;
        EntityTypes.TNT_MINECART = EntityTypeBuilder
                .builder(EntityTntMinecart.class)
                .vanillaEntity(EntityId.TNT_MINECART)
                .build();
    }

    public static void initTraderLlama() {
        if (EntityTypes.TRADER_LLAMA != null) return;
        EntityTypes.TRADER_LLAMA = EntityTypeBuilder
                .builder(EntityTraderLlama.class)
                .vanillaEntity(EntityId.TRADER_LLAMA)
                .build();
    }

    public static void initTripodCamera() {
        if (EntityTypes.TRIPOD_CAMERA != null) return;
        EntityTypes.TRIPOD_CAMERA = EntityTypeBuilder
                .builder(EntityTripodCamera.class)
                .vanillaEntity(EntityId.TRIPOD_CAMERA)
                .build();
    }

    public static void initTropicalfish() {
        if (EntityTypes.TROPICALFISH != null) return;
        EntityTypes.TROPICALFISH = EntityTypeBuilder
                .builder(EntityTropicalfish.class)
                .vanillaEntity(EntityId.TROPICALFISH)
                .build();
    }

    public static void initTurtle() {
        if (EntityTypes.TURTLE != null) return;
        EntityTypes.TURTLE = EntityTypeBuilder
                .builder(EntityTurtle.class)
                .vanillaEntity(EntityId.TURTLE)
                .build();
    }

    public static void initVex() {
        if (EntityTypes.VEX != null) return;
        EntityTypes.VEX = EntityTypeBuilder
                .builder(EntityVex.class)
                .vanillaEntity(EntityId.VEX)
                .build();
    }

    public static void initVillager() {
        if (EntityTypes.VILLAGER != null) return;
        EntityTypes.VILLAGER = EntityTypeBuilder
                .builder(EntityVillager.class)
                .vanillaEntity(EntityId.VILLAGER)
                .build();
    }

    public static void initVillagerV2() {
        if (EntityTypes.VILLAGER_V2 != null) return;
        EntityTypes.VILLAGER_V2 = EntityTypeBuilder
                .builder(EntityVillagerV2.class)
                .vanillaEntity(EntityId.VILLAGER_V2)
                .build();
    }

    public static void initVindicator() {
        if (EntityTypes.VINDICATOR != null) return;
        EntityTypes.VINDICATOR = EntityTypeBuilder
                .builder(EntityVindicator.class)
                .vanillaEntity(EntityId.VINDICATOR)
                .build();
    }

    public static void initWanderingTrader() {
        if (EntityTypes.WANDERING_TRADER != null) return;
        EntityTypes.WANDERING_TRADER = EntityTypeBuilder
                .builder(EntityWanderingTrader.class)
                .vanillaEntity(EntityId.WANDERING_TRADER)
                .build();
    }

    public static void initWarden() {
        if (EntityTypes.WARDEN != null) return;
        EntityTypes.WARDEN = EntityTypeBuilder
                .builder(EntityWarden.class)
                .vanillaEntity(EntityId.WARDEN)
                .build();
    }

    public static void initWindChargeProjectile() {
        if (EntityTypes.WIND_CHARGE_PROJECTILE != null) return;
        EntityTypes.WIND_CHARGE_PROJECTILE = EntityTypeBuilder
                .builder(EntityWindChargeProjectile.class)
                .vanillaEntity(EntityId.WIND_CHARGE_PROJECTILE)
                .build();
    }

    public static void initWitch() {
        if (EntityTypes.WITCH != null) return;
        EntityTypes.WITCH = EntityTypeBuilder
                .builder(EntityWitch.class)
                .vanillaEntity(EntityId.WITCH)
                .build();
    }

    public static void initWither() {
        if (EntityTypes.WITHER != null) return;
        EntityTypes.WITHER = EntityTypeBuilder
                .builder(EntityWither.class)
                .vanillaEntity(EntityId.WITHER)
                .build();
    }

    public static void initWitherSkeleton() {
        if (EntityTypes.WITHER_SKELETON != null) return;
        EntityTypes.WITHER_SKELETON = EntityTypeBuilder
                .builder(EntityWitherSkeleton.class)
                .vanillaEntity(EntityId.WITHER_SKELETON)
                .build();
    }

    public static void initWitherSkull() {
        if (EntityTypes.WITHER_SKULL != null) return;
        EntityTypes.WITHER_SKULL = EntityTypeBuilder
                .builder(EntityWitherSkull.class)
                .vanillaEntity(EntityId.WITHER_SKULL)
                .build();
    }

    public static void initWitherSkullDangerous() {
        if (EntityTypes.WITHER_SKULL_DANGEROUS != null) return;
        EntityTypes.WITHER_SKULL_DANGEROUS = EntityTypeBuilder
                .builder(EntityWitherSkullDangerous.class)
                .vanillaEntity(EntityId.WITHER_SKULL_DANGEROUS)
                .build();
    }

    public static void initWolf() {
        if (EntityTypes.WOLF != null) return;
        EntityTypes.WOLF = EntityTypeBuilder
                .builder(EntityWolf.class)
                .vanillaEntity(EntityId.WOLF)
                .build();
    }

    public static void initXpBottle() {
        if (EntityTypes.XP_BOTTLE != null) return;
        EntityTypes.XP_BOTTLE = EntityTypeBuilder
                .builder(EntityXpBottle.class)
                .vanillaEntity(EntityId.XP_BOTTLE)
                .build();
    }

    public static void initXpOrb() {
        if (EntityTypes.XP_ORB != null) return;
        EntityTypes.XP_ORB = EntityTypeBuilder
                .builder(EntityXpOrb.class)
                .vanillaEntity(EntityId.XP_ORB)
                .build();
    }

    public static void initZoglin() {
        if (EntityTypes.ZOGLIN != null) return;
        EntityTypes.ZOGLIN = EntityTypeBuilder
                .builder(EntityZoglin.class)
                .vanillaEntity(EntityId.ZOGLIN)
                .build();
    }

    public static void initZombie() {
        if (EntityTypes.ZOMBIE != null) return;
        EntityTypes.ZOMBIE = EntityTypeBuilder
                .builder(EntityZombie.class)
                .vanillaEntity(EntityId.ZOMBIE)
                .build();
    }

    public static void initZombieHorse() {
        if (EntityTypes.ZOMBIE_HORSE != null) return;
        EntityTypes.ZOMBIE_HORSE = EntityTypeBuilder
                .builder(EntityZombieHorse.class)
                .vanillaEntity(EntityId.ZOMBIE_HORSE)
                .build();
    }

    public static void initZombiePigman() {
        if (EntityTypes.ZOMBIE_PIGMAN != null) return;
        EntityTypes.ZOMBIE_PIGMAN = EntityTypeBuilder
                .builder(EntityZombiePigman.class)
                .vanillaEntity(EntityId.ZOMBIE_PIGMAN)
                .build();
    }

    public static void initZombieVillager() {
        if (EntityTypes.ZOMBIE_VILLAGER != null) return;
        EntityTypes.ZOMBIE_VILLAGER = EntityTypeBuilder
                .builder(EntityZombieVillager.class)
                .vanillaEntity(EntityId.ZOMBIE_VILLAGER)
                .build();
    }

    public static void initZombieVillagerV2() {
        if (EntityTypes.ZOMBIE_VILLAGER_V2 != null) return;
        EntityTypes.ZOMBIE_VILLAGER_V2 = EntityTypeBuilder
                .builder(EntityZombieVillagerV2.class)
                .vanillaEntity(EntityId.ZOMBIE_VILLAGER_V2)
                .build();
    }
}
