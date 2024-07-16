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
        if (EntityTypes.AGENT != null) return;
        EntityTypes.AGENT = EntityTypeBuilder
                .builder(EntityAgent.class)
                .vanillaEntity(VanillaEntityId.AGENT)
                .build();
    }

    public static void initAllay() {
        if (EntityTypes.ALLAY != null) return;
        EntityTypes.ALLAY = EntityTypeBuilder
                .builder(EntityAllay.class)
                .vanillaEntity(VanillaEntityId.ALLAY)
                .build();
    }

    public static void initAreaEffectCloud() {
        if (EntityTypes.AREA_EFFECT_CLOUD != null) return;
        EntityTypes.AREA_EFFECT_CLOUD = EntityTypeBuilder
                .builder(EntityAreaEffectCloud.class)
                .vanillaEntity(VanillaEntityId.AREA_EFFECT_CLOUD)
                .build();
    }

    public static void initArmadillo() {
        if (EntityTypes.ARMADILLO != null) return;
        EntityTypes.ARMADILLO = EntityTypeBuilder
                .builder(EntityArmadillo.class)
                .vanillaEntity(VanillaEntityId.ARMADILLO)
                .build();
    }

    public static void initArmorStand() {
        if (EntityTypes.ARMOR_STAND != null) return;
        EntityTypes.ARMOR_STAND = EntityTypeBuilder
                .builder(EntityArmorStand.class)
                .vanillaEntity(VanillaEntityId.ARMOR_STAND)
                .build();
    }

    public static void initArrow() {
        if (EntityTypes.ARROW != null) return;
        EntityTypes.ARROW = EntityTypeBuilder
                .builder(EntityArrow.class)
                .vanillaEntity(VanillaEntityId.ARROW)
                .build();
    }

    public static void initAxolotl() {
        if (EntityTypes.AXOLOTL != null) return;
        EntityTypes.AXOLOTL = EntityTypeBuilder
                .builder(EntityAxolotl.class)
                .vanillaEntity(VanillaEntityId.AXOLOTL)
                .build();
    }

    public static void initBalloon() {
        if (EntityTypes.BALLOON != null) return;
        EntityTypes.BALLOON = EntityTypeBuilder
                .builder(EntityBalloon.class)
                .vanillaEntity(VanillaEntityId.BALLOON)
                .build();
    }

    public static void initBat() {
        if (EntityTypes.BAT != null) return;
        EntityTypes.BAT = EntityTypeBuilder
                .builder(EntityBat.class)
                .vanillaEntity(VanillaEntityId.BAT)
                .build();
    }

    public static void initBee() {
        if (EntityTypes.BEE != null) return;
        EntityTypes.BEE = EntityTypeBuilder
                .builder(EntityBee.class)
                .vanillaEntity(VanillaEntityId.BEE)
                .build();
    }

    public static void initBlaze() {
        if (EntityTypes.BLAZE != null) return;
        EntityTypes.BLAZE = EntityTypeBuilder
                .builder(EntityBlaze.class)
                .vanillaEntity(VanillaEntityId.BLAZE)
                .build();
    }

    public static void initBoat() {
        if (EntityTypes.BOAT != null) return;
        EntityTypes.BOAT = EntityTypeBuilder
                .builder(EntityBoat.class)
                .vanillaEntity(VanillaEntityId.BOAT)
                .build();
    }

    public static void initBogged() {
        if (EntityTypes.BOGGED != null) return;
        EntityTypes.BOGGED = EntityTypeBuilder
                .builder(EntityBogged.class)
                .vanillaEntity(VanillaEntityId.BOGGED)
                .build();
    }

    public static void initBreeze() {
        if (EntityTypes.BREEZE != null) return;
        EntityTypes.BREEZE = EntityTypeBuilder
                .builder(EntityBreeze.class)
                .vanillaEntity(VanillaEntityId.BREEZE)
                .build();
    }

    public static void initBreezeWindChargeProjectile() {
        if (EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE != null) return;
        EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE = EntityTypeBuilder
                .builder(EntityBreezeWindChargeProjectile.class)
                .vanillaEntity(VanillaEntityId.BREEZE_WIND_CHARGE_PROJECTILE)
                .build();
    }

    public static void initCamel() {
        if (EntityTypes.CAMEL != null) return;
        EntityTypes.CAMEL = EntityTypeBuilder
                .builder(EntityCamel.class)
                .vanillaEntity(VanillaEntityId.CAMEL)
                .build();
    }

    public static void initCat() {
        if (EntityTypes.CAT != null) return;
        EntityTypes.CAT = EntityTypeBuilder
                .builder(EntityCat.class)
                .vanillaEntity(VanillaEntityId.CAT)
                .build();
    }

    public static void initCaveSpider() {
        if (EntityTypes.CAVE_SPIDER != null) return;
        EntityTypes.CAVE_SPIDER = EntityTypeBuilder
                .builder(EntityCaveSpider.class)
                .vanillaEntity(VanillaEntityId.CAVE_SPIDER)
                .build();
    }

    public static void initChestBoat() {
        if (EntityTypes.CHEST_BOAT != null) return;
        EntityTypes.CHEST_BOAT = EntityTypeBuilder
                .builder(EntityChestBoat.class)
                .vanillaEntity(VanillaEntityId.CHEST_BOAT)
                .build();
    }

    public static void initChestMinecart() {
        if (EntityTypes.CHEST_MINECART != null) return;
        EntityTypes.CHEST_MINECART = EntityTypeBuilder
                .builder(EntityChestMinecart.class)
                .vanillaEntity(VanillaEntityId.CHEST_MINECART)
                .build();
    }

    public static void initChicken() {
        if (EntityTypes.CHICKEN != null) return;
        EntityTypes.CHICKEN = EntityTypeBuilder
                .builder(EntityChicken.class)
                .vanillaEntity(VanillaEntityId.CHICKEN)
                .build();
    }

    public static void initCod() {
        if (EntityTypes.COD != null) return;
        EntityTypes.COD = EntityTypeBuilder
                .builder(EntityCod.class)
                .vanillaEntity(VanillaEntityId.COD)
                .build();
    }

    public static void initCommandBlockMinecart() {
        if (EntityTypes.COMMAND_BLOCK_MINECART != null) return;
        EntityTypes.COMMAND_BLOCK_MINECART = EntityTypeBuilder
                .builder(EntityCommandBlockMinecart.class)
                .vanillaEntity(VanillaEntityId.COMMAND_BLOCK_MINECART)
                .build();
    }

    public static void initCow() {
        if (EntityTypes.COW != null) return;
        EntityTypes.COW = EntityTypeBuilder
                .builder(EntityCow.class)
                .vanillaEntity(VanillaEntityId.COW)
                .build();
    }

    public static void initCreeper() {
        if (EntityTypes.CREEPER != null) return;
        EntityTypes.CREEPER = EntityTypeBuilder
                .builder(EntityCreeper.class)
                .vanillaEntity(VanillaEntityId.CREEPER)
                .build();
    }

    public static void initDolphin() {
        if (EntityTypes.DOLPHIN != null) return;
        EntityTypes.DOLPHIN = EntityTypeBuilder
                .builder(EntityDolphin.class)
                .vanillaEntity(VanillaEntityId.DOLPHIN)
                .build();
    }

    public static void initDonkey() {
        if (EntityTypes.DONKEY != null) return;
        EntityTypes.DONKEY = EntityTypeBuilder
                .builder(EntityDonkey.class)
                .vanillaEntity(VanillaEntityId.DONKEY)
                .build();
    }

    public static void initDragonFireball() {
        if (EntityTypes.DRAGON_FIREBALL != null) return;
        EntityTypes.DRAGON_FIREBALL = EntityTypeBuilder
                .builder(EntityDragonFireball.class)
                .vanillaEntity(VanillaEntityId.DRAGON_FIREBALL)
                .build();
    }

    public static void initDrowned() {
        if (EntityTypes.DROWNED != null) return;
        EntityTypes.DROWNED = EntityTypeBuilder
                .builder(EntityDrowned.class)
                .vanillaEntity(VanillaEntityId.DROWNED)
                .build();
    }

    public static void initEgg() {
        if (EntityTypes.EGG != null) return;
        EntityTypes.EGG = EntityTypeBuilder
                .builder(EntityEgg.class)
                .vanillaEntity(VanillaEntityId.EGG)
                .build();
    }

    public static void initElderGuardian() {
        if (EntityTypes.ELDER_GUARDIAN != null) return;
        EntityTypes.ELDER_GUARDIAN = EntityTypeBuilder
                .builder(EntityElderGuardian.class)
                .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN)
                .build();
    }

    public static void initElderGuardianGhost() {
        if (EntityTypes.ELDER_GUARDIAN_GHOST != null) return;
        EntityTypes.ELDER_GUARDIAN_GHOST = EntityTypeBuilder
                .builder(EntityElderGuardianGhost.class)
                .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN_GHOST)
                .build();
    }

    public static void initEnderCrystal() {
        if (EntityTypes.ENDER_CRYSTAL != null) return;
        EntityTypes.ENDER_CRYSTAL = EntityTypeBuilder
                .builder(EntityEnderCrystal.class)
                .vanillaEntity(VanillaEntityId.ENDER_CRYSTAL)
                .build();
    }

    public static void initEnderDragon() {
        if (EntityTypes.ENDER_DRAGON != null) return;
        EntityTypes.ENDER_DRAGON = EntityTypeBuilder
                .builder(EntityEnderDragon.class)
                .vanillaEntity(VanillaEntityId.ENDER_DRAGON)
                .build();
    }

    public static void initEnderPearl() {
        if (EntityTypes.ENDER_PEARL != null) return;
        EntityTypes.ENDER_PEARL = EntityTypeBuilder
                .builder(EntityEnderPearl.class)
                .vanillaEntity(VanillaEntityId.ENDER_PEARL)
                .build();
    }

    public static void initEnderman() {
        if (EntityTypes.ENDERMAN != null) return;
        EntityTypes.ENDERMAN = EntityTypeBuilder
                .builder(EntityEnderman.class)
                .vanillaEntity(VanillaEntityId.ENDERMAN)
                .build();
    }

    public static void initEndermite() {
        if (EntityTypes.ENDERMITE != null) return;
        EntityTypes.ENDERMITE = EntityTypeBuilder
                .builder(EntityEndermite.class)
                .vanillaEntity(VanillaEntityId.ENDERMITE)
                .build();
    }

    public static void initEvocationFang() {
        if (EntityTypes.EVOCATION_FANG != null) return;
        EntityTypes.EVOCATION_FANG = EntityTypeBuilder
                .builder(EntityEvocationFang.class)
                .vanillaEntity(VanillaEntityId.EVOCATION_FANG)
                .build();
    }

    public static void initEvocationIllager() {
        if (EntityTypes.EVOCATION_ILLAGER != null) return;
        EntityTypes.EVOCATION_ILLAGER = EntityTypeBuilder
                .builder(EntityEvocationIllager.class)
                .vanillaEntity(VanillaEntityId.EVOCATION_ILLAGER)
                .build();
    }

    public static void initEyeOfEnderSignal() {
        if (EntityTypes.EYE_OF_ENDER_SIGNAL != null) return;
        EntityTypes.EYE_OF_ENDER_SIGNAL = EntityTypeBuilder
                .builder(EntityEyeOfEnderSignal.class)
                .vanillaEntity(VanillaEntityId.EYE_OF_ENDER_SIGNAL)
                .build();
    }

    public static void initFallingBlock() {
        if (EntityTypes.FALLING_BLOCK != null) return;
        EntityTypes.FALLING_BLOCK = EntityTypeBuilder
                .builder(EntityFallingBlock.class)
                .vanillaEntity(VanillaEntityId.FALLING_BLOCK)
                .build();
    }

    public static void initFireball() {
        if (EntityTypes.FIREBALL != null) return;
        EntityTypes.FIREBALL = EntityTypeBuilder
                .builder(EntityFireball.class)
                .vanillaEntity(VanillaEntityId.FIREBALL)
                .build();
    }

    public static void initFireworksRocket() {
        if (EntityTypes.FIREWORKS_ROCKET != null) return;
        EntityTypes.FIREWORKS_ROCKET = EntityTypeBuilder
                .builder(EntityFireworksRocket.class)
                .vanillaEntity(VanillaEntityId.FIREWORKS_ROCKET)
                .build();
    }

    public static void initFishingHook() {
        if (EntityTypes.FISHING_HOOK != null) return;
        EntityTypes.FISHING_HOOK = EntityTypeBuilder
                .builder(EntityFishingHook.class)
                .vanillaEntity(VanillaEntityId.FISHING_HOOK)
                .build();
    }

    public static void initFox() {
        if (EntityTypes.FOX != null) return;
        EntityTypes.FOX = EntityTypeBuilder
                .builder(EntityFox.class)
                .vanillaEntity(VanillaEntityId.FOX)
                .build();
    }

    public static void initFrog() {
        if (EntityTypes.FROG != null) return;
        EntityTypes.FROG = EntityTypeBuilder
                .builder(EntityFrog.class)
                .vanillaEntity(VanillaEntityId.FROG)
                .build();
    }

    public static void initGhast() {
        if (EntityTypes.GHAST != null) return;
        EntityTypes.GHAST = EntityTypeBuilder
                .builder(EntityGhast.class)
                .vanillaEntity(VanillaEntityId.GHAST)
                .build();
    }

    public static void initGlowSquid() {
        if (EntityTypes.GLOW_SQUID != null) return;
        EntityTypes.GLOW_SQUID = EntityTypeBuilder
                .builder(EntityGlowSquid.class)
                .vanillaEntity(VanillaEntityId.GLOW_SQUID)
                .build();
    }

    public static void initGoat() {
        if (EntityTypes.GOAT != null) return;
        EntityTypes.GOAT = EntityTypeBuilder
                .builder(EntityGoat.class)
                .vanillaEntity(VanillaEntityId.GOAT)
                .build();
    }

    public static void initGuardian() {
        if (EntityTypes.GUARDIAN != null) return;
        EntityTypes.GUARDIAN = EntityTypeBuilder
                .builder(EntityGuardian.class)
                .vanillaEntity(VanillaEntityId.GUARDIAN)
                .build();
    }

    public static void initHoglin() {
        if (EntityTypes.HOGLIN != null) return;
        EntityTypes.HOGLIN = EntityTypeBuilder
                .builder(EntityHoglin.class)
                .vanillaEntity(VanillaEntityId.HOGLIN)
                .build();
    }

    public static void initHopperMinecart() {
        if (EntityTypes.HOPPER_MINECART != null) return;
        EntityTypes.HOPPER_MINECART = EntityTypeBuilder
                .builder(EntityHopperMinecart.class)
                .vanillaEntity(VanillaEntityId.HOPPER_MINECART)
                .build();
    }

    public static void initHorse() {
        if (EntityTypes.HORSE != null) return;
        EntityTypes.HORSE = EntityTypeBuilder
                .builder(EntityHorse.class)
                .vanillaEntity(VanillaEntityId.HORSE)
                .build();
    }

    public static void initHusk() {
        if (EntityTypes.HUSK != null) return;
        EntityTypes.HUSK = EntityTypeBuilder
                .builder(EntityHusk.class)
                .vanillaEntity(VanillaEntityId.HUSK)
                .build();
    }

    public static void initIceBomb() {
        if (EntityTypes.ICE_BOMB != null) return;
        EntityTypes.ICE_BOMB = EntityTypeBuilder
                .builder(EntityIceBomb.class)
                .vanillaEntity(VanillaEntityId.ICE_BOMB)
                .build();
    }

    public static void initIronGolem() {
        if (EntityTypes.IRON_GOLEM != null) return;
        EntityTypes.IRON_GOLEM = EntityTypeBuilder
                .builder(EntityIronGolem.class)
                .vanillaEntity(VanillaEntityId.IRON_GOLEM)
                .build();
    }

    public static void initItem() {
        if (EntityTypes.ITEM != null) return;
        EntityTypes.ITEM = EntityTypeBuilder
                .builder(EntityItem.class)
                .vanillaEntity(VanillaEntityId.ITEM)
                .build();
    }

    public static void initLeashKnot() {
        if (EntityTypes.LEASH_KNOT != null) return;
        EntityTypes.LEASH_KNOT = EntityTypeBuilder
                .builder(EntityLeashKnot.class)
                .vanillaEntity(VanillaEntityId.LEASH_KNOT)
                .build();
    }

    public static void initLightningBolt() {
        if (EntityTypes.LIGHTNING_BOLT != null) return;
        EntityTypes.LIGHTNING_BOLT = EntityTypeBuilder
                .builder(EntityLightningBolt.class)
                .vanillaEntity(VanillaEntityId.LIGHTNING_BOLT)
                .build();
    }

    public static void initLingeringPotion() {
        if (EntityTypes.LINGERING_POTION != null) return;
        EntityTypes.LINGERING_POTION = EntityTypeBuilder
                .builder(EntityLingeringPotion.class)
                .vanillaEntity(VanillaEntityId.LINGERING_POTION)
                .build();
    }

    public static void initLlama() {
        if (EntityTypes.LLAMA != null) return;
        EntityTypes.LLAMA = EntityTypeBuilder
                .builder(EntityLlama.class)
                .vanillaEntity(VanillaEntityId.LLAMA)
                .build();
    }

    public static void initLlamaSpit() {
        if (EntityTypes.LLAMA_SPIT != null) return;
        EntityTypes.LLAMA_SPIT = EntityTypeBuilder
                .builder(EntityLlamaSpit.class)
                .vanillaEntity(VanillaEntityId.LLAMA_SPIT)
                .build();
    }

    public static void initMagmaCube() {
        if (EntityTypes.MAGMA_CUBE != null) return;
        EntityTypes.MAGMA_CUBE = EntityTypeBuilder
                .builder(EntityMagmaCube.class)
                .vanillaEntity(VanillaEntityId.MAGMA_CUBE)
                .build();
    }

    public static void initMinecart() {
        if (EntityTypes.MINECART != null) return;
        EntityTypes.MINECART = EntityTypeBuilder
                .builder(EntityMinecart.class)
                .vanillaEntity(VanillaEntityId.MINECART)
                .build();
    }

    public static void initMooshroom() {
        if (EntityTypes.MOOSHROOM != null) return;
        EntityTypes.MOOSHROOM = EntityTypeBuilder
                .builder(EntityMooshroom.class)
                .vanillaEntity(VanillaEntityId.MOOSHROOM)
                .build();
    }

    public static void initMule() {
        if (EntityTypes.MULE != null) return;
        EntityTypes.MULE = EntityTypeBuilder
                .builder(EntityMule.class)
                .vanillaEntity(VanillaEntityId.MULE)
                .build();
    }

    public static void initNpc() {
        if (EntityTypes.NPC != null) return;
        EntityTypes.NPC = EntityTypeBuilder
                .builder(EntityNpc.class)
                .vanillaEntity(VanillaEntityId.NPC)
                .build();
    }

    public static void initOcelot() {
        if (EntityTypes.OCELOT != null) return;
        EntityTypes.OCELOT = EntityTypeBuilder
                .builder(EntityOcelot.class)
                .vanillaEntity(VanillaEntityId.OCELOT)
                .build();
    }

    public static void initOminousItemSpawner() {
        if (EntityTypes.OMINOUS_ITEM_SPAWNER != null) return;
        EntityTypes.OMINOUS_ITEM_SPAWNER = EntityTypeBuilder
                .builder(EntityOminousItemSpawner.class)
                .vanillaEntity(VanillaEntityId.OMINOUS_ITEM_SPAWNER)
                .build();
    }

    public static void initPainting() {
        if (EntityTypes.PAINTING != null) return;
        EntityTypes.PAINTING = EntityTypeBuilder
                .builder(EntityPainting.class)
                .vanillaEntity(VanillaEntityId.PAINTING)
                .build();
    }

    public static void initPanda() {
        if (EntityTypes.PANDA != null) return;
        EntityTypes.PANDA = EntityTypeBuilder
                .builder(EntityPanda.class)
                .vanillaEntity(VanillaEntityId.PANDA)
                .build();
    }

    public static void initParrot() {
        if (EntityTypes.PARROT != null) return;
        EntityTypes.PARROT = EntityTypeBuilder
                .builder(EntityParrot.class)
                .vanillaEntity(VanillaEntityId.PARROT)
                .build();
    }

    public static void initPhantom() {
        if (EntityTypes.PHANTOM != null) return;
        EntityTypes.PHANTOM = EntityTypeBuilder
                .builder(EntityPhantom.class)
                .vanillaEntity(VanillaEntityId.PHANTOM)
                .build();
    }

    public static void initPig() {
        if (EntityTypes.PIG != null) return;
        EntityTypes.PIG = EntityTypeBuilder
                .builder(EntityPig.class)
                .vanillaEntity(VanillaEntityId.PIG)
                .build();
    }

    public static void initPiglin() {
        if (EntityTypes.PIGLIN != null) return;
        EntityTypes.PIGLIN = EntityTypeBuilder
                .builder(EntityPiglin.class)
                .vanillaEntity(VanillaEntityId.PIGLIN)
                .build();
    }

    public static void initPiglinBrute() {
        if (EntityTypes.PIGLIN_BRUTE != null) return;
        EntityTypes.PIGLIN_BRUTE = EntityTypeBuilder
                .builder(EntityPiglinBrute.class)
                .vanillaEntity(VanillaEntityId.PIGLIN_BRUTE)
                .build();
    }

    public static void initPillager() {
        if (EntityTypes.PILLAGER != null) return;
        EntityTypes.PILLAGER = EntityTypeBuilder
                .builder(EntityPillager.class)
                .vanillaEntity(VanillaEntityId.PILLAGER)
                .build();
    }

    public static void initPlayer() {
        if (EntityTypes.PLAYER != null) return;
        EntityTypes.PLAYER = EntityTypeBuilder
                .builder(EntityPlayer.class)
                .vanillaEntity(VanillaEntityId.PLAYER)
                .build();
    }

    public static void initPolarBear() {
        if (EntityTypes.POLAR_BEAR != null) return;
        EntityTypes.POLAR_BEAR = EntityTypeBuilder
                .builder(EntityPolarBear.class)
                .vanillaEntity(VanillaEntityId.POLAR_BEAR)
                .build();
    }

    public static void initPufferfish() {
        if (EntityTypes.PUFFERFISH != null) return;
        EntityTypes.PUFFERFISH = EntityTypeBuilder
                .builder(EntityPufferfish.class)
                .vanillaEntity(VanillaEntityId.PUFFERFISH)
                .build();
    }

    public static void initRabbit() {
        if (EntityTypes.RABBIT != null) return;
        EntityTypes.RABBIT = EntityTypeBuilder
                .builder(EntityRabbit.class)
                .vanillaEntity(VanillaEntityId.RABBIT)
                .build();
    }

    public static void initRavager() {
        if (EntityTypes.RAVAGER != null) return;
        EntityTypes.RAVAGER = EntityTypeBuilder
                .builder(EntityRavager.class)
                .vanillaEntity(VanillaEntityId.RAVAGER)
                .build();
    }

    public static void initSalmon() {
        if (EntityTypes.SALMON != null) return;
        EntityTypes.SALMON = EntityTypeBuilder
                .builder(EntitySalmon.class)
                .vanillaEntity(VanillaEntityId.SALMON)
                .build();
    }

    public static void initSheep() {
        if (EntityTypes.SHEEP != null) return;
        EntityTypes.SHEEP = EntityTypeBuilder
                .builder(EntitySheep.class)
                .vanillaEntity(VanillaEntityId.SHEEP)
                .build();
    }

    public static void initShulker() {
        if (EntityTypes.SHULKER != null) return;
        EntityTypes.SHULKER = EntityTypeBuilder
                .builder(EntityShulker.class)
                .vanillaEntity(VanillaEntityId.SHULKER)
                .build();
    }

    public static void initShulkerBullet() {
        if (EntityTypes.SHULKER_BULLET != null) return;
        EntityTypes.SHULKER_BULLET = EntityTypeBuilder
                .builder(EntityShulkerBullet.class)
                .vanillaEntity(VanillaEntityId.SHULKER_BULLET)
                .build();
    }

    public static void initSilverfish() {
        if (EntityTypes.SILVERFISH != null) return;
        EntityTypes.SILVERFISH = EntityTypeBuilder
                .builder(EntitySilverfish.class)
                .vanillaEntity(VanillaEntityId.SILVERFISH)
                .build();
    }

    public static void initSkeleton() {
        if (EntityTypes.SKELETON != null) return;
        EntityTypes.SKELETON = EntityTypeBuilder
                .builder(EntitySkeleton.class)
                .vanillaEntity(VanillaEntityId.SKELETON)
                .build();
    }

    public static void initSkeletonHorse() {
        if (EntityTypes.SKELETON_HORSE != null) return;
        EntityTypes.SKELETON_HORSE = EntityTypeBuilder
                .builder(EntitySkeletonHorse.class)
                .vanillaEntity(VanillaEntityId.SKELETON_HORSE)
                .build();
    }

    public static void initSlime() {
        if (EntityTypes.SLIME != null) return;
        EntityTypes.SLIME = EntityTypeBuilder
                .builder(EntitySlime.class)
                .vanillaEntity(VanillaEntityId.SLIME)
                .build();
    }

    public static void initSmallFireball() {
        if (EntityTypes.SMALL_FIREBALL != null) return;
        EntityTypes.SMALL_FIREBALL = EntityTypeBuilder
                .builder(EntitySmallFireball.class)
                .vanillaEntity(VanillaEntityId.SMALL_FIREBALL)
                .build();
    }

    public static void initSniffer() {
        if (EntityTypes.SNIFFER != null) return;
        EntityTypes.SNIFFER = EntityTypeBuilder
                .builder(EntitySniffer.class)
                .vanillaEntity(VanillaEntityId.SNIFFER)
                .build();
    }

    public static void initSnowGolem() {
        if (EntityTypes.SNOW_GOLEM != null) return;
        EntityTypes.SNOW_GOLEM = EntityTypeBuilder
                .builder(EntitySnowGolem.class)
                .vanillaEntity(VanillaEntityId.SNOW_GOLEM)
                .build();
    }

    public static void initSnowball() {
        if (EntityTypes.SNOWBALL != null) return;
        EntityTypes.SNOWBALL = EntityTypeBuilder
                .builder(EntitySnowball.class)
                .vanillaEntity(VanillaEntityId.SNOWBALL)
                .build();
    }

    public static void initSpider() {
        if (EntityTypes.SPIDER != null) return;
        EntityTypes.SPIDER = EntityTypeBuilder
                .builder(EntitySpider.class)
                .vanillaEntity(VanillaEntityId.SPIDER)
                .build();
    }

    public static void initSplashPotion() {
        if (EntityTypes.SPLASH_POTION != null) return;
        EntityTypes.SPLASH_POTION = EntityTypeBuilder
                .builder(EntitySplashPotion.class)
                .vanillaEntity(VanillaEntityId.SPLASH_POTION)
                .build();
    }

    public static void initSquid() {
        if (EntityTypes.SQUID != null) return;
        EntityTypes.SQUID = EntityTypeBuilder
                .builder(EntitySquid.class)
                .vanillaEntity(VanillaEntityId.SQUID)
                .build();
    }

    public static void initStray() {
        if (EntityTypes.STRAY != null) return;
        EntityTypes.STRAY = EntityTypeBuilder
                .builder(EntityStray.class)
                .vanillaEntity(VanillaEntityId.STRAY)
                .build();
    }

    public static void initStrider() {
        if (EntityTypes.STRIDER != null) return;
        EntityTypes.STRIDER = EntityTypeBuilder
                .builder(EntityStrider.class)
                .vanillaEntity(VanillaEntityId.STRIDER)
                .build();
    }

    public static void initTadpole() {
        if (EntityTypes.TADPOLE != null) return;
        EntityTypes.TADPOLE = EntityTypeBuilder
                .builder(EntityTadpole.class)
                .vanillaEntity(VanillaEntityId.TADPOLE)
                .build();
    }

    public static void initThrownTrident() {
        if (EntityTypes.THROWN_TRIDENT != null) return;
        EntityTypes.THROWN_TRIDENT = EntityTypeBuilder
                .builder(EntityThrownTrident.class)
                .vanillaEntity(VanillaEntityId.THROWN_TRIDENT)
                .build();
    }

    public static void initTnt() {
        if (EntityTypes.TNT != null) return;
        EntityTypes.TNT = EntityTypeBuilder
                .builder(EntityTnt.class)
                .vanillaEntity(VanillaEntityId.TNT)
                .build();
    }

    public static void initTntMinecart() {
        if (EntityTypes.TNT_MINECART != null) return;
        EntityTypes.TNT_MINECART = EntityTypeBuilder
                .builder(EntityTntMinecart.class)
                .vanillaEntity(VanillaEntityId.TNT_MINECART)
                .build();
    }

    public static void initTraderLlama() {
        if (EntityTypes.TRADER_LLAMA != null) return;
        EntityTypes.TRADER_LLAMA = EntityTypeBuilder
                .builder(EntityTraderLlama.class)
                .vanillaEntity(VanillaEntityId.TRADER_LLAMA)
                .build();
    }

    public static void initTripodCamera() {
        if (EntityTypes.TRIPOD_CAMERA != null) return;
        EntityTypes.TRIPOD_CAMERA = EntityTypeBuilder
                .builder(EntityTripodCamera.class)
                .vanillaEntity(VanillaEntityId.TRIPOD_CAMERA)
                .build();
    }

    public static void initTropicalfish() {
        if (EntityTypes.TROPICALFISH != null) return;
        EntityTypes.TROPICALFISH = EntityTypeBuilder
                .builder(EntityTropicalfish.class)
                .vanillaEntity(VanillaEntityId.TROPICALFISH)
                .build();
    }

    public static void initTurtle() {
        if (EntityTypes.TURTLE != null) return;
        EntityTypes.TURTLE = EntityTypeBuilder
                .builder(EntityTurtle.class)
                .vanillaEntity(VanillaEntityId.TURTLE)
                .build();
    }

    public static void initVex() {
        if (EntityTypes.VEX != null) return;
        EntityTypes.VEX = EntityTypeBuilder
                .builder(EntityVex.class)
                .vanillaEntity(VanillaEntityId.VEX)
                .build();
    }

    public static void initVillager() {
        if (EntityTypes.VILLAGER != null) return;
        EntityTypes.VILLAGER = EntityTypeBuilder
                .builder(EntityVillager.class)
                .vanillaEntity(VanillaEntityId.VILLAGER)
                .build();
    }

    public static void initVillagerV2() {
        if (EntityTypes.VILLAGER_V2 != null) return;
        EntityTypes.VILLAGER_V2 = EntityTypeBuilder
                .builder(EntityVillagerV2.class)
                .vanillaEntity(VanillaEntityId.VILLAGER_V2)
                .build();
    }

    public static void initVindicator() {
        if (EntityTypes.VINDICATOR != null) return;
        EntityTypes.VINDICATOR = EntityTypeBuilder
                .builder(EntityVindicator.class)
                .vanillaEntity(VanillaEntityId.VINDICATOR)
                .build();
    }

    public static void initWanderingTrader() {
        if (EntityTypes.WANDERING_TRADER != null) return;
        EntityTypes.WANDERING_TRADER = EntityTypeBuilder
                .builder(EntityWanderingTrader.class)
                .vanillaEntity(VanillaEntityId.WANDERING_TRADER)
                .build();
    }

    public static void initWarden() {
        if (EntityTypes.WARDEN != null) return;
        EntityTypes.WARDEN = EntityTypeBuilder
                .builder(EntityWarden.class)
                .vanillaEntity(VanillaEntityId.WARDEN)
                .build();
    }

    public static void initWindChargeProjectile() {
        if (EntityTypes.WIND_CHARGE_PROJECTILE != null) return;
        EntityTypes.WIND_CHARGE_PROJECTILE = EntityTypeBuilder
                .builder(EntityWindChargeProjectile.class)
                .vanillaEntity(VanillaEntityId.WIND_CHARGE_PROJECTILE)
                .build();
    }

    public static void initWitch() {
        if (EntityTypes.WITCH != null) return;
        EntityTypes.WITCH = EntityTypeBuilder
                .builder(EntityWitch.class)
                .vanillaEntity(VanillaEntityId.WITCH)
                .build();
    }

    public static void initWither() {
        if (EntityTypes.WITHER != null) return;
        EntityTypes.WITHER = EntityTypeBuilder
                .builder(EntityWither.class)
                .vanillaEntity(VanillaEntityId.WITHER)
                .build();
    }

    public static void initWitherSkeleton() {
        if (EntityTypes.WITHER_SKELETON != null) return;
        EntityTypes.WITHER_SKELETON = EntityTypeBuilder
                .builder(EntityWitherSkeleton.class)
                .vanillaEntity(VanillaEntityId.WITHER_SKELETON)
                .build();
    }

    public static void initWitherSkull() {
        if (EntityTypes.WITHER_SKULL != null) return;
        EntityTypes.WITHER_SKULL = EntityTypeBuilder
                .builder(EntityWitherSkull.class)
                .vanillaEntity(VanillaEntityId.WITHER_SKULL)
                .build();
    }

    public static void initWitherSkullDangerous() {
        if (EntityTypes.WITHER_SKULL_DANGEROUS != null) return;
        EntityTypes.WITHER_SKULL_DANGEROUS = EntityTypeBuilder
                .builder(EntityWitherSkullDangerous.class)
                .vanillaEntity(VanillaEntityId.WITHER_SKULL_DANGEROUS)
                .build();
    }

    public static void initWolf() {
        if (EntityTypes.WOLF != null) return;
        EntityTypes.WOLF = EntityTypeBuilder
                .builder(EntityWolf.class)
                .vanillaEntity(VanillaEntityId.WOLF)
                .build();
    }

    public static void initXpBottle() {
        if (EntityTypes.XP_BOTTLE != null) return;
        EntityTypes.XP_BOTTLE = EntityTypeBuilder
                .builder(EntityXpBottle.class)
                .vanillaEntity(VanillaEntityId.XP_BOTTLE)
                .build();
    }

    public static void initXpOrb() {
        if (EntityTypes.XP_ORB != null) return;
        EntityTypes.XP_ORB = EntityTypeBuilder
                .builder(EntityXpOrb.class)
                .vanillaEntity(VanillaEntityId.XP_ORB)
                .build();
    }

    public static void initZoglin() {
        if (EntityTypes.ZOGLIN != null) return;
        EntityTypes.ZOGLIN = EntityTypeBuilder
                .builder(EntityZoglin.class)
                .vanillaEntity(VanillaEntityId.ZOGLIN)
                .build();
    }

    public static void initZombie() {
        if (EntityTypes.ZOMBIE != null) return;
        EntityTypes.ZOMBIE = EntityTypeBuilder
                .builder(EntityZombie.class)
                .vanillaEntity(VanillaEntityId.ZOMBIE)
                .build();
    }

    public static void initZombieHorse() {
        if (EntityTypes.ZOMBIE_HORSE != null) return;
        EntityTypes.ZOMBIE_HORSE = EntityTypeBuilder
                .builder(EntityZombieHorse.class)
                .vanillaEntity(VanillaEntityId.ZOMBIE_HORSE)
                .build();
    }

    public static void initZombiePigman() {
        if (EntityTypes.ZOMBIE_PIGMAN != null) return;
        EntityTypes.ZOMBIE_PIGMAN = EntityTypeBuilder
                .builder(EntityZombiePigman.class)
                .vanillaEntity(VanillaEntityId.ZOMBIE_PIGMAN)
                .build();
    }

    public static void initZombieVillager() {
        if (EntityTypes.ZOMBIE_VILLAGER != null) return;
        EntityTypes.ZOMBIE_VILLAGER = EntityTypeBuilder
                .builder(EntityZombieVillager.class)
                .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER)
                .build();
    }

    public static void initZombieVillagerV2() {
        if (EntityTypes.ZOMBIE_VILLAGER_V2 != null) return;
        EntityTypes.ZOMBIE_VILLAGER_V2 = EntityTypeBuilder
                .builder(EntityZombieVillagerV2.class)
                .vanillaEntity(VanillaEntityId.ZOMBIE_VILLAGER_V2)
                .build();
    }
}
