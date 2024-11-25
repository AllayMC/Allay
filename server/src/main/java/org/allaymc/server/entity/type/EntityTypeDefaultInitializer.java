package org.allaymc.server.entity.type;

import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.server.entity.impl.EntityAgentImpl;
import org.allaymc.server.entity.impl.EntityAllayImpl;
import org.allaymc.server.entity.impl.EntityAreaEffectCloudImpl;
import org.allaymc.server.entity.impl.EntityArmadilloImpl;
import org.allaymc.server.entity.impl.EntityArmorStandImpl;
import org.allaymc.server.entity.impl.EntityArrowImpl;
import org.allaymc.server.entity.impl.EntityAxolotlImpl;
import org.allaymc.server.entity.impl.EntityBalloonImpl;
import org.allaymc.server.entity.impl.EntityBatImpl;
import org.allaymc.server.entity.impl.EntityBeeImpl;
import org.allaymc.server.entity.impl.EntityBlazeImpl;
import org.allaymc.server.entity.impl.EntityBoatImpl;
import org.allaymc.server.entity.impl.EntityBoggedImpl;
import org.allaymc.server.entity.impl.EntityBreezeImpl;
import org.allaymc.server.entity.impl.EntityBreezeWindChargeProjectileImpl;
import org.allaymc.server.entity.impl.EntityCamelImpl;
import org.allaymc.server.entity.impl.EntityCatImpl;
import org.allaymc.server.entity.impl.EntityCaveSpiderImpl;
import org.allaymc.server.entity.impl.EntityChestBoatImpl;
import org.allaymc.server.entity.impl.EntityChestMinecartImpl;
import org.allaymc.server.entity.impl.EntityChickenImpl;
import org.allaymc.server.entity.impl.EntityCodImpl;
import org.allaymc.server.entity.impl.EntityCommandBlockMinecartImpl;
import org.allaymc.server.entity.impl.EntityCowImpl;
import org.allaymc.server.entity.impl.EntityCreeperImpl;
import org.allaymc.server.entity.impl.EntityDolphinImpl;
import org.allaymc.server.entity.impl.EntityDonkeyImpl;
import org.allaymc.server.entity.impl.EntityDragonFireballImpl;
import org.allaymc.server.entity.impl.EntityDrownedImpl;
import org.allaymc.server.entity.impl.EntityEggImpl;
import org.allaymc.server.entity.impl.EntityElderGuardianGhostImpl;
import org.allaymc.server.entity.impl.EntityElderGuardianImpl;
import org.allaymc.server.entity.impl.EntityEnderCrystalImpl;
import org.allaymc.server.entity.impl.EntityEnderDragonImpl;
import org.allaymc.server.entity.impl.EntityEnderPearlImpl;
import org.allaymc.server.entity.impl.EntityEndermanImpl;
import org.allaymc.server.entity.impl.EntityEndermiteImpl;
import org.allaymc.server.entity.impl.EntityEvocationFangImpl;
import org.allaymc.server.entity.impl.EntityEvocationIllagerImpl;
import org.allaymc.server.entity.impl.EntityEyeOfEnderSignalImpl;
import org.allaymc.server.entity.impl.EntityFallingBlockImpl;
import org.allaymc.server.entity.impl.EntityFireballImpl;
import org.allaymc.server.entity.impl.EntityFireworksRocketImpl;
import org.allaymc.server.entity.impl.EntityFishingHookImpl;
import org.allaymc.server.entity.impl.EntityFoxImpl;
import org.allaymc.server.entity.impl.EntityFrogImpl;
import org.allaymc.server.entity.impl.EntityGhastImpl;
import org.allaymc.server.entity.impl.EntityGlowSquidImpl;
import org.allaymc.server.entity.impl.EntityGoatImpl;
import org.allaymc.server.entity.impl.EntityGuardianImpl;
import org.allaymc.server.entity.impl.EntityHoglinImpl;
import org.allaymc.server.entity.impl.EntityHopperMinecartImpl;
import org.allaymc.server.entity.impl.EntityHorseImpl;
import org.allaymc.server.entity.impl.EntityHuskImpl;
import org.allaymc.server.entity.impl.EntityIceBombImpl;
import org.allaymc.server.entity.impl.EntityIronGolemImpl;
import org.allaymc.server.entity.impl.EntityItemImpl;
import org.allaymc.server.entity.impl.EntityLeashKnotImpl;
import org.allaymc.server.entity.impl.EntityLightningBoltImpl;
import org.allaymc.server.entity.impl.EntityLingeringPotionImpl;
import org.allaymc.server.entity.impl.EntityLlamaImpl;
import org.allaymc.server.entity.impl.EntityLlamaSpitImpl;
import org.allaymc.server.entity.impl.EntityMagmaCubeImpl;
import org.allaymc.server.entity.impl.EntityMinecartImpl;
import org.allaymc.server.entity.impl.EntityMooshroomImpl;
import org.allaymc.server.entity.impl.EntityMuleImpl;
import org.allaymc.server.entity.impl.EntityNpcImpl;
import org.allaymc.server.entity.impl.EntityOcelotImpl;
import org.allaymc.server.entity.impl.EntityOminousItemSpawnerImpl;
import org.allaymc.server.entity.impl.EntityPaintingImpl;
import org.allaymc.server.entity.impl.EntityPandaImpl;
import org.allaymc.server.entity.impl.EntityParrotImpl;
import org.allaymc.server.entity.impl.EntityPhantomImpl;
import org.allaymc.server.entity.impl.EntityPigImpl;
import org.allaymc.server.entity.impl.EntityPiglinBruteImpl;
import org.allaymc.server.entity.impl.EntityPiglinImpl;
import org.allaymc.server.entity.impl.EntityPillagerImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.entity.impl.EntityPolarBearImpl;
import org.allaymc.server.entity.impl.EntityPufferfishImpl;
import org.allaymc.server.entity.impl.EntityRabbitImpl;
import org.allaymc.server.entity.impl.EntityRavagerImpl;
import org.allaymc.server.entity.impl.EntitySalmonImpl;
import org.allaymc.server.entity.impl.EntitySheepImpl;
import org.allaymc.server.entity.impl.EntityShulkerBulletImpl;
import org.allaymc.server.entity.impl.EntityShulkerImpl;
import org.allaymc.server.entity.impl.EntitySilverfishImpl;
import org.allaymc.server.entity.impl.EntitySkeletonHorseImpl;
import org.allaymc.server.entity.impl.EntitySkeletonImpl;
import org.allaymc.server.entity.impl.EntitySlimeImpl;
import org.allaymc.server.entity.impl.EntitySmallFireballImpl;
import org.allaymc.server.entity.impl.EntitySnifferImpl;
import org.allaymc.server.entity.impl.EntitySnowGolemImpl;
import org.allaymc.server.entity.impl.EntitySnowballImpl;
import org.allaymc.server.entity.impl.EntitySpiderImpl;
import org.allaymc.server.entity.impl.EntitySplashPotionImpl;
import org.allaymc.server.entity.impl.EntitySquidImpl;
import org.allaymc.server.entity.impl.EntityStrayImpl;
import org.allaymc.server.entity.impl.EntityStriderImpl;
import org.allaymc.server.entity.impl.EntityTadpoleImpl;
import org.allaymc.server.entity.impl.EntityThrownTridentImpl;
import org.allaymc.server.entity.impl.EntityTntImpl;
import org.allaymc.server.entity.impl.EntityTntMinecartImpl;
import org.allaymc.server.entity.impl.EntityTraderLlamaImpl;
import org.allaymc.server.entity.impl.EntityTripodCameraImpl;
import org.allaymc.server.entity.impl.EntityTropicalfishImpl;
import org.allaymc.server.entity.impl.EntityTurtleImpl;
import org.allaymc.server.entity.impl.EntityVexImpl;
import org.allaymc.server.entity.impl.EntityVillagerImpl;
import org.allaymc.server.entity.impl.EntityVillagerV2Impl;
import org.allaymc.server.entity.impl.EntityVindicatorImpl;
import org.allaymc.server.entity.impl.EntityWanderingTraderImpl;
import org.allaymc.server.entity.impl.EntityWardenImpl;
import org.allaymc.server.entity.impl.EntityWindChargeProjectileImpl;
import org.allaymc.server.entity.impl.EntityWitchImpl;
import org.allaymc.server.entity.impl.EntityWitherImpl;
import org.allaymc.server.entity.impl.EntityWitherSkeletonImpl;
import org.allaymc.server.entity.impl.EntityWitherSkullDangerousImpl;
import org.allaymc.server.entity.impl.EntityWitherSkullImpl;
import org.allaymc.server.entity.impl.EntityWolfImpl;
import org.allaymc.server.entity.impl.EntityXpBottleImpl;
import org.allaymc.server.entity.impl.EntityXpOrbImpl;
import org.allaymc.server.entity.impl.EntityZoglinImpl;
import org.allaymc.server.entity.impl.EntityZombieHorseImpl;
import org.allaymc.server.entity.impl.EntityZombieImpl;
import org.allaymc.server.entity.impl.EntityZombiePigmanImpl;
import org.allaymc.server.entity.impl.EntityZombieVillagerImpl;
import org.allaymc.server.entity.impl.EntityZombieVillagerV2Impl;

/**
 * Automatically generated by {@code org.allaymc.codegen.EntityClassGen}
 */
public final class EntityTypeDefaultInitializer {
    public static void initAgent() {
        if (EntityTypes.AGENT != null) return;
        EntityTypes.AGENT = AllayEntityType
                .builder(EntityAgentImpl.class)
                .vanillaEntity(EntityId.AGENT)
                .build();
    }

    public static void initAllay() {
        if (EntityTypes.ALLAY != null) return;
        EntityTypes.ALLAY = AllayEntityType
                .builder(EntityAllayImpl.class)
                .vanillaEntity(EntityId.ALLAY)
                .build();
    }

    public static void initAreaEffectCloud() {
        if (EntityTypes.AREA_EFFECT_CLOUD != null) return;
        EntityTypes.AREA_EFFECT_CLOUD = AllayEntityType
                .builder(EntityAreaEffectCloudImpl.class)
                .vanillaEntity(EntityId.AREA_EFFECT_CLOUD)
                .build();
    }

    public static void initArmadillo() {
        if (EntityTypes.ARMADILLO != null) return;
        EntityTypes.ARMADILLO = AllayEntityType
                .builder(EntityArmadilloImpl.class)
                .vanillaEntity(EntityId.ARMADILLO)
                .build();
    }

    public static void initArmorStand() {
        if (EntityTypes.ARMOR_STAND != null) return;
        EntityTypes.ARMOR_STAND = AllayEntityType
                .builder(EntityArmorStandImpl.class)
                .vanillaEntity(EntityId.ARMOR_STAND)
                .build();
    }

    public static void initArrow() {
        if (EntityTypes.ARROW != null) return;
        EntityTypes.ARROW = AllayEntityType
                .builder(EntityArrowImpl.class)
                .vanillaEntity(EntityId.ARROW)
                .build();
    }

    public static void initAxolotl() {
        if (EntityTypes.AXOLOTL != null) return;
        EntityTypes.AXOLOTL = AllayEntityType
                .builder(EntityAxolotlImpl.class)
                .vanillaEntity(EntityId.AXOLOTL)
                .build();
    }

    public static void initBalloon() {
        if (EntityTypes.BALLOON != null) return;
        EntityTypes.BALLOON = AllayEntityType
                .builder(EntityBalloonImpl.class)
                .vanillaEntity(EntityId.BALLOON)
                .build();
    }

    public static void initBat() {
        if (EntityTypes.BAT != null) return;
        EntityTypes.BAT = AllayEntityType
                .builder(EntityBatImpl.class)
                .vanillaEntity(EntityId.BAT)
                .build();
    }

    public static void initBee() {
        if (EntityTypes.BEE != null) return;
        EntityTypes.BEE = AllayEntityType
                .builder(EntityBeeImpl.class)
                .vanillaEntity(EntityId.BEE)
                .build();
    }

    public static void initBlaze() {
        if (EntityTypes.BLAZE != null) return;
        EntityTypes.BLAZE = AllayEntityType
                .builder(EntityBlazeImpl.class)
                .vanillaEntity(EntityId.BLAZE)
                .build();
    }

    public static void initBoat() {
        if (EntityTypes.BOAT != null) return;
        EntityTypes.BOAT = AllayEntityType
                .builder(EntityBoatImpl.class)
                .vanillaEntity(EntityId.BOAT)
                .build();
    }

    public static void initBogged() {
        if (EntityTypes.BOGGED != null) return;
        EntityTypes.BOGGED = AllayEntityType
                .builder(EntityBoggedImpl.class)
                .vanillaEntity(EntityId.BOGGED)
                .build();
    }

    public static void initBreeze() {
        if (EntityTypes.BREEZE != null) return;
        EntityTypes.BREEZE = AllayEntityType
                .builder(EntityBreezeImpl.class)
                .vanillaEntity(EntityId.BREEZE)
                .build();
    }

    public static void initBreezeWindChargeProjectile() {
        if (EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE != null) return;
        EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE = AllayEntityType
                .builder(EntityBreezeWindChargeProjectileImpl.class)
                .vanillaEntity(EntityId.BREEZE_WIND_CHARGE_PROJECTILE)
                .build();
    }

    public static void initCamel() {
        if (EntityTypes.CAMEL != null) return;
        EntityTypes.CAMEL = AllayEntityType
                .builder(EntityCamelImpl.class)
                .vanillaEntity(EntityId.CAMEL)
                .build();
    }

    public static void initCat() {
        if (EntityTypes.CAT != null) return;
        EntityTypes.CAT = AllayEntityType
                .builder(EntityCatImpl.class)
                .vanillaEntity(EntityId.CAT)
                .build();
    }

    public static void initCaveSpider() {
        if (EntityTypes.CAVE_SPIDER != null) return;
        EntityTypes.CAVE_SPIDER = AllayEntityType
                .builder(EntityCaveSpiderImpl.class)
                .vanillaEntity(EntityId.CAVE_SPIDER)
                .build();
    }

    public static void initChestBoat() {
        if (EntityTypes.CHEST_BOAT != null) return;
        EntityTypes.CHEST_BOAT = AllayEntityType
                .builder(EntityChestBoatImpl.class)
                .vanillaEntity(EntityId.CHEST_BOAT)
                .build();
    }

    public static void initChestMinecart() {
        if (EntityTypes.CHEST_MINECART != null) return;
        EntityTypes.CHEST_MINECART = AllayEntityType
                .builder(EntityChestMinecartImpl.class)
                .vanillaEntity(EntityId.CHEST_MINECART)
                .build();
    }

    public static void initChicken() {
        if (EntityTypes.CHICKEN != null) return;
        EntityTypes.CHICKEN = AllayEntityType
                .builder(EntityChickenImpl.class)
                .vanillaEntity(EntityId.CHICKEN)
                .build();
    }

    public static void initCod() {
        if (EntityTypes.COD != null) return;
        EntityTypes.COD = AllayEntityType
                .builder(EntityCodImpl.class)
                .vanillaEntity(EntityId.COD)
                .build();
    }

    public static void initCommandBlockMinecart() {
        if (EntityTypes.COMMAND_BLOCK_MINECART != null) return;
        EntityTypes.COMMAND_BLOCK_MINECART = AllayEntityType
                .builder(EntityCommandBlockMinecartImpl.class)
                .vanillaEntity(EntityId.COMMAND_BLOCK_MINECART)
                .build();
    }

    public static void initCow() {
        if (EntityTypes.COW != null) return;
        EntityTypes.COW = AllayEntityType
                .builder(EntityCowImpl.class)
                .vanillaEntity(EntityId.COW)
                .build();
    }

    public static void initCreeper() {
        if (EntityTypes.CREEPER != null) return;
        EntityTypes.CREEPER = AllayEntityType
                .builder(EntityCreeperImpl.class)
                .vanillaEntity(EntityId.CREEPER)
                .build();
    }

    public static void initDolphin() {
        if (EntityTypes.DOLPHIN != null) return;
        EntityTypes.DOLPHIN = AllayEntityType
                .builder(EntityDolphinImpl.class)
                .vanillaEntity(EntityId.DOLPHIN)
                .build();
    }

    public static void initDonkey() {
        if (EntityTypes.DONKEY != null) return;
        EntityTypes.DONKEY = AllayEntityType
                .builder(EntityDonkeyImpl.class)
                .vanillaEntity(EntityId.DONKEY)
                .build();
    }

    public static void initDragonFireball() {
        if (EntityTypes.DRAGON_FIREBALL != null) return;
        EntityTypes.DRAGON_FIREBALL = AllayEntityType
                .builder(EntityDragonFireballImpl.class)
                .vanillaEntity(EntityId.DRAGON_FIREBALL)
                .build();
    }

    public static void initDrowned() {
        if (EntityTypes.DROWNED != null) return;
        EntityTypes.DROWNED = AllayEntityType
                .builder(EntityDrownedImpl.class)
                .vanillaEntity(EntityId.DROWNED)
                .build();
    }

    public static void initEgg() {
        if (EntityTypes.EGG != null) return;
        EntityTypes.EGG = AllayEntityType
                .builder(EntityEggImpl.class)
                .vanillaEntity(EntityId.EGG)
                .build();
    }

    public static void initElderGuardian() {
        if (EntityTypes.ELDER_GUARDIAN != null) return;
        EntityTypes.ELDER_GUARDIAN = AllayEntityType
                .builder(EntityElderGuardianImpl.class)
                .vanillaEntity(EntityId.ELDER_GUARDIAN)
                .build();
    }

    public static void initElderGuardianGhost() {
        if (EntityTypes.ELDER_GUARDIAN_GHOST != null) return;
        EntityTypes.ELDER_GUARDIAN_GHOST = AllayEntityType
                .builder(EntityElderGuardianGhostImpl.class)
                .vanillaEntity(EntityId.ELDER_GUARDIAN_GHOST)
                .build();
    }

    public static void initEnderCrystal() {
        if (EntityTypes.ENDER_CRYSTAL != null) return;
        EntityTypes.ENDER_CRYSTAL = AllayEntityType
                .builder(EntityEnderCrystalImpl.class)
                .vanillaEntity(EntityId.ENDER_CRYSTAL)
                .build();
    }

    public static void initEnderDragon() {
        if (EntityTypes.ENDER_DRAGON != null) return;
        EntityTypes.ENDER_DRAGON = AllayEntityType
                .builder(EntityEnderDragonImpl.class)
                .vanillaEntity(EntityId.ENDER_DRAGON)
                .build();
    }

    public static void initEnderPearl() {
        if (EntityTypes.ENDER_PEARL != null) return;
        EntityTypes.ENDER_PEARL = AllayEntityType
                .builder(EntityEnderPearlImpl.class)
                .vanillaEntity(EntityId.ENDER_PEARL)
                .build();
    }

    public static void initEnderman() {
        if (EntityTypes.ENDERMAN != null) return;
        EntityTypes.ENDERMAN = AllayEntityType
                .builder(EntityEndermanImpl.class)
                .vanillaEntity(EntityId.ENDERMAN)
                .build();
    }

    public static void initEndermite() {
        if (EntityTypes.ENDERMITE != null) return;
        EntityTypes.ENDERMITE = AllayEntityType
                .builder(EntityEndermiteImpl.class)
                .vanillaEntity(EntityId.ENDERMITE)
                .build();
    }

    public static void initEvocationFang() {
        if (EntityTypes.EVOCATION_FANG != null) return;
        EntityTypes.EVOCATION_FANG = AllayEntityType
                .builder(EntityEvocationFangImpl.class)
                .vanillaEntity(EntityId.EVOCATION_FANG)
                .build();
    }

    public static void initEvocationIllager() {
        if (EntityTypes.EVOCATION_ILLAGER != null) return;
        EntityTypes.EVOCATION_ILLAGER = AllayEntityType
                .builder(EntityEvocationIllagerImpl.class)
                .vanillaEntity(EntityId.EVOCATION_ILLAGER)
                .build();
    }

    public static void initEyeOfEnderSignal() {
        if (EntityTypes.EYE_OF_ENDER_SIGNAL != null) return;
        EntityTypes.EYE_OF_ENDER_SIGNAL = AllayEntityType
                .builder(EntityEyeOfEnderSignalImpl.class)
                .vanillaEntity(EntityId.EYE_OF_ENDER_SIGNAL)
                .build();
    }

    public static void initFallingBlock() {
        if (EntityTypes.FALLING_BLOCK != null) return;
        EntityTypes.FALLING_BLOCK = AllayEntityType
                .builder(EntityFallingBlockImpl.class)
                .vanillaEntity(EntityId.FALLING_BLOCK)
                .build();
    }

    public static void initFireball() {
        if (EntityTypes.FIREBALL != null) return;
        EntityTypes.FIREBALL = AllayEntityType
                .builder(EntityFireballImpl.class)
                .vanillaEntity(EntityId.FIREBALL)
                .build();
    }

    public static void initFireworksRocket() {
        if (EntityTypes.FIREWORKS_ROCKET != null) return;
        EntityTypes.FIREWORKS_ROCKET = AllayEntityType
                .builder(EntityFireworksRocketImpl.class)
                .vanillaEntity(EntityId.FIREWORKS_ROCKET)
                .build();
    }

    public static void initFishingHook() {
        if (EntityTypes.FISHING_HOOK != null) return;
        EntityTypes.FISHING_HOOK = AllayEntityType
                .builder(EntityFishingHookImpl.class)
                .vanillaEntity(EntityId.FISHING_HOOK)
                .build();
    }

    public static void initFox() {
        if (EntityTypes.FOX != null) return;
        EntityTypes.FOX = AllayEntityType
                .builder(EntityFoxImpl.class)
                .vanillaEntity(EntityId.FOX)
                .build();
    }

    public static void initFrog() {
        if (EntityTypes.FROG != null) return;
        EntityTypes.FROG = AllayEntityType
                .builder(EntityFrogImpl.class)
                .vanillaEntity(EntityId.FROG)
                .build();
    }

    public static void initGhast() {
        if (EntityTypes.GHAST != null) return;
        EntityTypes.GHAST = AllayEntityType
                .builder(EntityGhastImpl.class)
                .vanillaEntity(EntityId.GHAST)
                .build();
    }

    public static void initGlowSquid() {
        if (EntityTypes.GLOW_SQUID != null) return;
        EntityTypes.GLOW_SQUID = AllayEntityType
                .builder(EntityGlowSquidImpl.class)
                .vanillaEntity(EntityId.GLOW_SQUID)
                .build();
    }

    public static void initGoat() {
        if (EntityTypes.GOAT != null) return;
        EntityTypes.GOAT = AllayEntityType
                .builder(EntityGoatImpl.class)
                .vanillaEntity(EntityId.GOAT)
                .build();
    }

    public static void initGuardian() {
        if (EntityTypes.GUARDIAN != null) return;
        EntityTypes.GUARDIAN = AllayEntityType
                .builder(EntityGuardianImpl.class)
                .vanillaEntity(EntityId.GUARDIAN)
                .build();
    }

    public static void initHoglin() {
        if (EntityTypes.HOGLIN != null) return;
        EntityTypes.HOGLIN = AllayEntityType
                .builder(EntityHoglinImpl.class)
                .vanillaEntity(EntityId.HOGLIN)
                .build();
    }

    public static void initHopperMinecart() {
        if (EntityTypes.HOPPER_MINECART != null) return;
        EntityTypes.HOPPER_MINECART = AllayEntityType
                .builder(EntityHopperMinecartImpl.class)
                .vanillaEntity(EntityId.HOPPER_MINECART)
                .build();
    }

    public static void initHorse() {
        if (EntityTypes.HORSE != null) return;
        EntityTypes.HORSE = AllayEntityType
                .builder(EntityHorseImpl.class)
                .vanillaEntity(EntityId.HORSE)
                .build();
    }

    public static void initHusk() {
        if (EntityTypes.HUSK != null) return;
        EntityTypes.HUSK = AllayEntityType
                .builder(EntityHuskImpl.class)
                .vanillaEntity(EntityId.HUSK)
                .build();
    }

    public static void initIceBomb() {
        if (EntityTypes.ICE_BOMB != null) return;
        EntityTypes.ICE_BOMB = AllayEntityType
                .builder(EntityIceBombImpl.class)
                .vanillaEntity(EntityId.ICE_BOMB)
                .build();
    }

    public static void initIronGolem() {
        if (EntityTypes.IRON_GOLEM != null) return;
        EntityTypes.IRON_GOLEM = AllayEntityType
                .builder(EntityIronGolemImpl.class)
                .vanillaEntity(EntityId.IRON_GOLEM)
                .build();
    }

    public static void initItem() {
        if (EntityTypes.ITEM != null) return;
        EntityTypes.ITEM = AllayEntityType
                .builder(EntityItemImpl.class)
                .vanillaEntity(EntityId.ITEM)
                .build();
    }

    public static void initLeashKnot() {
        if (EntityTypes.LEASH_KNOT != null) return;
        EntityTypes.LEASH_KNOT = AllayEntityType
                .builder(EntityLeashKnotImpl.class)
                .vanillaEntity(EntityId.LEASH_KNOT)
                .build();
    }

    public static void initLightningBolt() {
        if (EntityTypes.LIGHTNING_BOLT != null) return;
        EntityTypes.LIGHTNING_BOLT = AllayEntityType
                .builder(EntityLightningBoltImpl.class)
                .vanillaEntity(EntityId.LIGHTNING_BOLT)
                .build();
    }

    public static void initLingeringPotion() {
        if (EntityTypes.LINGERING_POTION != null) return;
        EntityTypes.LINGERING_POTION = AllayEntityType
                .builder(EntityLingeringPotionImpl.class)
                .vanillaEntity(EntityId.LINGERING_POTION)
                .build();
    }

    public static void initLlama() {
        if (EntityTypes.LLAMA != null) return;
        EntityTypes.LLAMA = AllayEntityType
                .builder(EntityLlamaImpl.class)
                .vanillaEntity(EntityId.LLAMA)
                .build();
    }

    public static void initLlamaSpit() {
        if (EntityTypes.LLAMA_SPIT != null) return;
        EntityTypes.LLAMA_SPIT = AllayEntityType
                .builder(EntityLlamaSpitImpl.class)
                .vanillaEntity(EntityId.LLAMA_SPIT)
                .build();
    }

    public static void initMagmaCube() {
        if (EntityTypes.MAGMA_CUBE != null) return;
        EntityTypes.MAGMA_CUBE = AllayEntityType
                .builder(EntityMagmaCubeImpl.class)
                .vanillaEntity(EntityId.MAGMA_CUBE)
                .build();
    }

    public static void initMinecart() {
        if (EntityTypes.MINECART != null) return;
        EntityTypes.MINECART = AllayEntityType
                .builder(EntityMinecartImpl.class)
                .vanillaEntity(EntityId.MINECART)
                .build();
    }

    public static void initMooshroom() {
        if (EntityTypes.MOOSHROOM != null) return;
        EntityTypes.MOOSHROOM = AllayEntityType
                .builder(EntityMooshroomImpl.class)
                .vanillaEntity(EntityId.MOOSHROOM)
                .build();
    }

    public static void initMule() {
        if (EntityTypes.MULE != null) return;
        EntityTypes.MULE = AllayEntityType
                .builder(EntityMuleImpl.class)
                .vanillaEntity(EntityId.MULE)
                .build();
    }

    public static void initNpc() {
        if (EntityTypes.NPC != null) return;
        EntityTypes.NPC = AllayEntityType
                .builder(EntityNpcImpl.class)
                .vanillaEntity(EntityId.NPC)
                .build();
    }

    public static void initOcelot() {
        if (EntityTypes.OCELOT != null) return;
        EntityTypes.OCELOT = AllayEntityType
                .builder(EntityOcelotImpl.class)
                .vanillaEntity(EntityId.OCELOT)
                .build();
    }

    public static void initOminousItemSpawner() {
        if (EntityTypes.OMINOUS_ITEM_SPAWNER != null) return;
        EntityTypes.OMINOUS_ITEM_SPAWNER = AllayEntityType
                .builder(EntityOminousItemSpawnerImpl.class)
                .vanillaEntity(EntityId.OMINOUS_ITEM_SPAWNER)
                .build();
    }

    public static void initPainting() {
        if (EntityTypes.PAINTING != null) return;
        EntityTypes.PAINTING = AllayEntityType
                .builder(EntityPaintingImpl.class)
                .vanillaEntity(EntityId.PAINTING)
                .build();
    }

    public static void initPanda() {
        if (EntityTypes.PANDA != null) return;
        EntityTypes.PANDA = AllayEntityType
                .builder(EntityPandaImpl.class)
                .vanillaEntity(EntityId.PANDA)
                .build();
    }

    public static void initParrot() {
        if (EntityTypes.PARROT != null) return;
        EntityTypes.PARROT = AllayEntityType
                .builder(EntityParrotImpl.class)
                .vanillaEntity(EntityId.PARROT)
                .build();
    }

    public static void initPhantom() {
        if (EntityTypes.PHANTOM != null) return;
        EntityTypes.PHANTOM = AllayEntityType
                .builder(EntityPhantomImpl.class)
                .vanillaEntity(EntityId.PHANTOM)
                .build();
    }

    public static void initPig() {
        if (EntityTypes.PIG != null) return;
        EntityTypes.PIG = AllayEntityType
                .builder(EntityPigImpl.class)
                .vanillaEntity(EntityId.PIG)
                .build();
    }

    public static void initPiglin() {
        if (EntityTypes.PIGLIN != null) return;
        EntityTypes.PIGLIN = AllayEntityType
                .builder(EntityPiglinImpl.class)
                .vanillaEntity(EntityId.PIGLIN)
                .build();
    }

    public static void initPiglinBrute() {
        if (EntityTypes.PIGLIN_BRUTE != null) return;
        EntityTypes.PIGLIN_BRUTE = AllayEntityType
                .builder(EntityPiglinBruteImpl.class)
                .vanillaEntity(EntityId.PIGLIN_BRUTE)
                .build();
    }

    public static void initPillager() {
        if (EntityTypes.PILLAGER != null) return;
        EntityTypes.PILLAGER = AllayEntityType
                .builder(EntityPillagerImpl.class)
                .vanillaEntity(EntityId.PILLAGER)
                .build();
    }

    public static void initPlayer() {
        if (EntityTypes.PLAYER != null) return;
        EntityTypes.PLAYER = AllayEntityType
                .builder(EntityPlayerImpl.class)
                .vanillaEntity(EntityId.PLAYER)
                .build();
    }

    public static void initPolarBear() {
        if (EntityTypes.POLAR_BEAR != null) return;
        EntityTypes.POLAR_BEAR = AllayEntityType
                .builder(EntityPolarBearImpl.class)
                .vanillaEntity(EntityId.POLAR_BEAR)
                .build();
    }

    public static void initPufferfish() {
        if (EntityTypes.PUFFERFISH != null) return;
        EntityTypes.PUFFERFISH = AllayEntityType
                .builder(EntityPufferfishImpl.class)
                .vanillaEntity(EntityId.PUFFERFISH)
                .build();
    }

    public static void initRabbit() {
        if (EntityTypes.RABBIT != null) return;
        EntityTypes.RABBIT = AllayEntityType
                .builder(EntityRabbitImpl.class)
                .vanillaEntity(EntityId.RABBIT)
                .build();
    }

    public static void initRavager() {
        if (EntityTypes.RAVAGER != null) return;
        EntityTypes.RAVAGER = AllayEntityType
                .builder(EntityRavagerImpl.class)
                .vanillaEntity(EntityId.RAVAGER)
                .build();
    }

    public static void initSalmon() {
        if (EntityTypes.SALMON != null) return;
        EntityTypes.SALMON = AllayEntityType
                .builder(EntitySalmonImpl.class)
                .vanillaEntity(EntityId.SALMON)
                .build();
    }

    public static void initSheep() {
        if (EntityTypes.SHEEP != null) return;
        EntityTypes.SHEEP = AllayEntityType
                .builder(EntitySheepImpl.class)
                .vanillaEntity(EntityId.SHEEP)
                .build();
    }

    public static void initShulker() {
        if (EntityTypes.SHULKER != null) return;
        EntityTypes.SHULKER = AllayEntityType
                .builder(EntityShulkerImpl.class)
                .vanillaEntity(EntityId.SHULKER)
                .build();
    }

    public static void initShulkerBullet() {
        if (EntityTypes.SHULKER_BULLET != null) return;
        EntityTypes.SHULKER_BULLET = AllayEntityType
                .builder(EntityShulkerBulletImpl.class)
                .vanillaEntity(EntityId.SHULKER_BULLET)
                .build();
    }

    public static void initSilverfish() {
        if (EntityTypes.SILVERFISH != null) return;
        EntityTypes.SILVERFISH = AllayEntityType
                .builder(EntitySilverfishImpl.class)
                .vanillaEntity(EntityId.SILVERFISH)
                .build();
    }

    public static void initSkeleton() {
        if (EntityTypes.SKELETON != null) return;
        EntityTypes.SKELETON = AllayEntityType
                .builder(EntitySkeletonImpl.class)
                .vanillaEntity(EntityId.SKELETON)
                .build();
    }

    public static void initSkeletonHorse() {
        if (EntityTypes.SKELETON_HORSE != null) return;
        EntityTypes.SKELETON_HORSE = AllayEntityType
                .builder(EntitySkeletonHorseImpl.class)
                .vanillaEntity(EntityId.SKELETON_HORSE)
                .build();
    }

    public static void initSlime() {
        if (EntityTypes.SLIME != null) return;
        EntityTypes.SLIME = AllayEntityType
                .builder(EntitySlimeImpl.class)
                .vanillaEntity(EntityId.SLIME)
                .build();
    }

    public static void initSmallFireball() {
        if (EntityTypes.SMALL_FIREBALL != null) return;
        EntityTypes.SMALL_FIREBALL = AllayEntityType
                .builder(EntitySmallFireballImpl.class)
                .vanillaEntity(EntityId.SMALL_FIREBALL)
                .build();
    }

    public static void initSniffer() {
        if (EntityTypes.SNIFFER != null) return;
        EntityTypes.SNIFFER = AllayEntityType
                .builder(EntitySnifferImpl.class)
                .vanillaEntity(EntityId.SNIFFER)
                .build();
    }

    public static void initSnowGolem() {
        if (EntityTypes.SNOW_GOLEM != null) return;
        EntityTypes.SNOW_GOLEM = AllayEntityType
                .builder(EntitySnowGolemImpl.class)
                .vanillaEntity(EntityId.SNOW_GOLEM)
                .build();
    }

    public static void initSnowball() {
        if (EntityTypes.SNOWBALL != null) return;
        EntityTypes.SNOWBALL = AllayEntityType
                .builder(EntitySnowballImpl.class)
                .vanillaEntity(EntityId.SNOWBALL)
                .build();
    }

    public static void initSpider() {
        if (EntityTypes.SPIDER != null) return;
        EntityTypes.SPIDER = AllayEntityType
                .builder(EntitySpiderImpl.class)
                .vanillaEntity(EntityId.SPIDER)
                .build();
    }

    public static void initSplashPotion() {
        if (EntityTypes.SPLASH_POTION != null) return;
        EntityTypes.SPLASH_POTION = AllayEntityType
                .builder(EntitySplashPotionImpl.class)
                .vanillaEntity(EntityId.SPLASH_POTION)
                .build();
    }

    public static void initSquid() {
        if (EntityTypes.SQUID != null) return;
        EntityTypes.SQUID = AllayEntityType
                .builder(EntitySquidImpl.class)
                .vanillaEntity(EntityId.SQUID)
                .build();
    }

    public static void initStray() {
        if (EntityTypes.STRAY != null) return;
        EntityTypes.STRAY = AllayEntityType
                .builder(EntityStrayImpl.class)
                .vanillaEntity(EntityId.STRAY)
                .build();
    }

    public static void initStrider() {
        if (EntityTypes.STRIDER != null) return;
        EntityTypes.STRIDER = AllayEntityType
                .builder(EntityStriderImpl.class)
                .vanillaEntity(EntityId.STRIDER)
                .build();
    }

    public static void initTadpole() {
        if (EntityTypes.TADPOLE != null) return;
        EntityTypes.TADPOLE = AllayEntityType
                .builder(EntityTadpoleImpl.class)
                .vanillaEntity(EntityId.TADPOLE)
                .build();
    }

    public static void initThrownTrident() {
        if (EntityTypes.THROWN_TRIDENT != null) return;
        EntityTypes.THROWN_TRIDENT = AllayEntityType
                .builder(EntityThrownTridentImpl.class)
                .vanillaEntity(EntityId.THROWN_TRIDENT)
                .build();
    }

    public static void initTnt() {
        if (EntityTypes.TNT != null) return;
        EntityTypes.TNT = AllayEntityType
                .builder(EntityTntImpl.class)
                .vanillaEntity(EntityId.TNT)
                .build();
    }

    public static void initTntMinecart() {
        if (EntityTypes.TNT_MINECART != null) return;
        EntityTypes.TNT_MINECART = AllayEntityType
                .builder(EntityTntMinecartImpl.class)
                .vanillaEntity(EntityId.TNT_MINECART)
                .build();
    }

    public static void initTraderLlama() {
        if (EntityTypes.TRADER_LLAMA != null) return;
        EntityTypes.TRADER_LLAMA = AllayEntityType
                .builder(EntityTraderLlamaImpl.class)
                .vanillaEntity(EntityId.TRADER_LLAMA)
                .build();
    }

    public static void initTripodCamera() {
        if (EntityTypes.TRIPOD_CAMERA != null) return;
        EntityTypes.TRIPOD_CAMERA = AllayEntityType
                .builder(EntityTripodCameraImpl.class)
                .vanillaEntity(EntityId.TRIPOD_CAMERA)
                .build();
    }

    public static void initTropicalfish() {
        if (EntityTypes.TROPICALFISH != null) return;
        EntityTypes.TROPICALFISH = AllayEntityType
                .builder(EntityTropicalfishImpl.class)
                .vanillaEntity(EntityId.TROPICALFISH)
                .build();
    }

    public static void initTurtle() {
        if (EntityTypes.TURTLE != null) return;
        EntityTypes.TURTLE = AllayEntityType
                .builder(EntityTurtleImpl.class)
                .vanillaEntity(EntityId.TURTLE)
                .build();
    }

    public static void initVex() {
        if (EntityTypes.VEX != null) return;
        EntityTypes.VEX = AllayEntityType
                .builder(EntityVexImpl.class)
                .vanillaEntity(EntityId.VEX)
                .build();
    }

    public static void initVillager() {
        if (EntityTypes.VILLAGER != null) return;
        EntityTypes.VILLAGER = AllayEntityType
                .builder(EntityVillagerImpl.class)
                .vanillaEntity(EntityId.VILLAGER)
                .build();
    }

    public static void initVillagerV2() {
        if (EntityTypes.VILLAGER_V2 != null) return;
        EntityTypes.VILLAGER_V2 = AllayEntityType
                .builder(EntityVillagerV2Impl.class)
                .vanillaEntity(EntityId.VILLAGER_V2)
                .build();
    }

    public static void initVindicator() {
        if (EntityTypes.VINDICATOR != null) return;
        EntityTypes.VINDICATOR = AllayEntityType
                .builder(EntityVindicatorImpl.class)
                .vanillaEntity(EntityId.VINDICATOR)
                .build();
    }

    public static void initWanderingTrader() {
        if (EntityTypes.WANDERING_TRADER != null) return;
        EntityTypes.WANDERING_TRADER = AllayEntityType
                .builder(EntityWanderingTraderImpl.class)
                .vanillaEntity(EntityId.WANDERING_TRADER)
                .build();
    }

    public static void initWarden() {
        if (EntityTypes.WARDEN != null) return;
        EntityTypes.WARDEN = AllayEntityType
                .builder(EntityWardenImpl.class)
                .vanillaEntity(EntityId.WARDEN)
                .build();
    }

    public static void initWindChargeProjectile() {
        if (EntityTypes.WIND_CHARGE_PROJECTILE != null) return;
        EntityTypes.WIND_CHARGE_PROJECTILE = AllayEntityType
                .builder(EntityWindChargeProjectileImpl.class)
                .vanillaEntity(EntityId.WIND_CHARGE_PROJECTILE)
                .build();
    }

    public static void initWitch() {
        if (EntityTypes.WITCH != null) return;
        EntityTypes.WITCH = AllayEntityType
                .builder(EntityWitchImpl.class)
                .vanillaEntity(EntityId.WITCH)
                .build();
    }

    public static void initWither() {
        if (EntityTypes.WITHER != null) return;
        EntityTypes.WITHER = AllayEntityType
                .builder(EntityWitherImpl.class)
                .vanillaEntity(EntityId.WITHER)
                .build();
    }

    public static void initWitherSkeleton() {
        if (EntityTypes.WITHER_SKELETON != null) return;
        EntityTypes.WITHER_SKELETON = AllayEntityType
                .builder(EntityWitherSkeletonImpl.class)
                .vanillaEntity(EntityId.WITHER_SKELETON)
                .build();
    }

    public static void initWitherSkull() {
        if (EntityTypes.WITHER_SKULL != null) return;
        EntityTypes.WITHER_SKULL = AllayEntityType
                .builder(EntityWitherSkullImpl.class)
                .vanillaEntity(EntityId.WITHER_SKULL)
                .build();
    }

    public static void initWitherSkullDangerous() {
        if (EntityTypes.WITHER_SKULL_DANGEROUS != null) return;
        EntityTypes.WITHER_SKULL_DANGEROUS = AllayEntityType
                .builder(EntityWitherSkullDangerousImpl.class)
                .vanillaEntity(EntityId.WITHER_SKULL_DANGEROUS)
                .build();
    }

    public static void initWolf() {
        if (EntityTypes.WOLF != null) return;
        EntityTypes.WOLF = AllayEntityType
                .builder(EntityWolfImpl.class)
                .vanillaEntity(EntityId.WOLF)
                .build();
    }

    public static void initXpBottle() {
        if (EntityTypes.XP_BOTTLE != null) return;
        EntityTypes.XP_BOTTLE = AllayEntityType
                .builder(EntityXpBottleImpl.class)
                .vanillaEntity(EntityId.XP_BOTTLE)
                .build();
    }

    public static void initXpOrb() {
        if (EntityTypes.XP_ORB != null) return;
        EntityTypes.XP_ORB = AllayEntityType
                .builder(EntityXpOrbImpl.class)
                .vanillaEntity(EntityId.XP_ORB)
                .build();
    }

    public static void initZoglin() {
        if (EntityTypes.ZOGLIN != null) return;
        EntityTypes.ZOGLIN = AllayEntityType
                .builder(EntityZoglinImpl.class)
                .vanillaEntity(EntityId.ZOGLIN)
                .build();
    }

    public static void initZombie() {
        if (EntityTypes.ZOMBIE != null) return;
        EntityTypes.ZOMBIE = AllayEntityType
                .builder(EntityZombieImpl.class)
                .vanillaEntity(EntityId.ZOMBIE)
                .build();
    }

    public static void initZombieHorse() {
        if (EntityTypes.ZOMBIE_HORSE != null) return;
        EntityTypes.ZOMBIE_HORSE = AllayEntityType
                .builder(EntityZombieHorseImpl.class)
                .vanillaEntity(EntityId.ZOMBIE_HORSE)
                .build();
    }

    public static void initZombiePigman() {
        if (EntityTypes.ZOMBIE_PIGMAN != null) return;
        EntityTypes.ZOMBIE_PIGMAN = AllayEntityType
                .builder(EntityZombiePigmanImpl.class)
                .vanillaEntity(EntityId.ZOMBIE_PIGMAN)
                .build();
    }

    public static void initZombieVillager() {
        if (EntityTypes.ZOMBIE_VILLAGER != null) return;
        EntityTypes.ZOMBIE_VILLAGER = AllayEntityType
                .builder(EntityZombieVillagerImpl.class)
                .vanillaEntity(EntityId.ZOMBIE_VILLAGER)
                .build();
    }

    public static void initZombieVillagerV2() {
        if (EntityTypes.ZOMBIE_VILLAGER_V2 != null) return;
        EntityTypes.ZOMBIE_VILLAGER_V2 = AllayEntityType
                .builder(EntityZombieVillagerV2Impl.class)
                .vanillaEntity(EntityId.ZOMBIE_VILLAGER_V2)
                .build();
    }
}
