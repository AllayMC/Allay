package org.allaymc.server.entity.type;

import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.server.entity.data.EntityId;
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
import org.allaymc.server.entity.impl.EntityCamelHuskImpl;
import org.allaymc.server.entity.impl.EntityCamelImpl;
import org.allaymc.server.entity.impl.EntityCatImpl;
import org.allaymc.server.entity.impl.EntityCaveSpiderImpl;
import org.allaymc.server.entity.impl.EntityChestBoatImpl;
import org.allaymc.server.entity.impl.EntityChestMinecartImpl;
import org.allaymc.server.entity.impl.EntityChickenImpl;
import org.allaymc.server.entity.impl.EntityCodImpl;
import org.allaymc.server.entity.impl.EntityCommandBlockMinecartImpl;
import org.allaymc.server.entity.impl.EntityCopperGolemImpl;
import org.allaymc.server.entity.impl.EntityCowImpl;
import org.allaymc.server.entity.impl.EntityCreakingImpl;
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
import org.allaymc.server.entity.impl.EntityHappyGhastImpl;
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
import org.allaymc.server.entity.impl.EntityNautilusImpl;
import org.allaymc.server.entity.impl.EntityNpcImpl;
import org.allaymc.server.entity.impl.EntityOcelotImpl;
import org.allaymc.server.entity.impl.EntityOminousItemSpawnerImpl;
import org.allaymc.server.entity.impl.EntityPaintingImpl;
import org.allaymc.server.entity.impl.EntityPandaImpl;
import org.allaymc.server.entity.impl.EntityParchedImpl;
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
import org.allaymc.server.entity.impl.EntityZombieNautilusImpl;
import org.allaymc.server.entity.impl.EntityZombiePigmanImpl;
import org.allaymc.server.entity.impl.EntityZombieVillagerImpl;
import org.allaymc.server.entity.impl.EntityZombieVillagerV2Impl;

public final class EntityTypeDefaultInitializer {
    public static void init() {
        if (EntityTypes.AGENT == null) {
            EntityTypes.AGENT = AllayEntityType
                            .builder(EntityAgentImpl.class)
                            .vanillaEntity(EntityId.AGENT)
                            .build();
        }
        if (EntityTypes.ALLAY == null) {
            EntityTypes.ALLAY = AllayEntityType
                            .builder(EntityAllayImpl.class)
                            .vanillaEntity(EntityId.ALLAY)
                            .build();
        }
        if (EntityTypes.AREA_EFFECT_CLOUD == null) {
            EntityTypes.AREA_EFFECT_CLOUD = AllayEntityType
                            .builder(EntityAreaEffectCloudImpl.class)
                            .vanillaEntity(EntityId.AREA_EFFECT_CLOUD)
                            .build();
        }
        if (EntityTypes.ARMADILLO == null) {
            EntityTypes.ARMADILLO = AllayEntityType
                            .builder(EntityArmadilloImpl.class)
                            .vanillaEntity(EntityId.ARMADILLO)
                            .build();
        }
        if (EntityTypes.ARMOR_STAND == null) {
            EntityTypes.ARMOR_STAND = AllayEntityType
                            .builder(EntityArmorStandImpl.class)
                            .vanillaEntity(EntityId.ARMOR_STAND)
                            .build();
        }
        if (EntityTypes.ARROW == null) {
            EntityTypes.ARROW = AllayEntityType
                            .builder(EntityArrowImpl.class)
                            .vanillaEntity(EntityId.ARROW)
                            .build();
        }
        if (EntityTypes.AXOLOTL == null) {
            EntityTypes.AXOLOTL = AllayEntityType
                            .builder(EntityAxolotlImpl.class)
                            .vanillaEntity(EntityId.AXOLOTL)
                            .build();
        }
        if (EntityTypes.BALLOON == null) {
            EntityTypes.BALLOON = AllayEntityType
                            .builder(EntityBalloonImpl.class)
                            .vanillaEntity(EntityId.BALLOON)
                            .build();
        }
        if (EntityTypes.BAT == null) {
            EntityTypes.BAT = AllayEntityType
                            .builder(EntityBatImpl.class)
                            .vanillaEntity(EntityId.BAT)
                            .build();
        }
        if (EntityTypes.BEE == null) {
            EntityTypes.BEE = AllayEntityType
                            .builder(EntityBeeImpl.class)
                            .vanillaEntity(EntityId.BEE)
                            .build();
        }
        if (EntityTypes.BLAZE == null) {
            EntityTypes.BLAZE = AllayEntityType
                            .builder(EntityBlazeImpl.class)
                            .vanillaEntity(EntityId.BLAZE)
                            .build();
        }
        if (EntityTypes.BOAT == null) {
            EntityTypes.BOAT = AllayEntityType
                            .builder(EntityBoatImpl.class)
                            .vanillaEntity(EntityId.BOAT)
                            .build();
        }
        if (EntityTypes.BOGGED == null) {
            EntityTypes.BOGGED = AllayEntityType
                            .builder(EntityBoggedImpl.class)
                            .vanillaEntity(EntityId.BOGGED)
                            .build();
        }
        if (EntityTypes.BREEZE == null) {
            EntityTypes.BREEZE = AllayEntityType
                            .builder(EntityBreezeImpl.class)
                            .vanillaEntity(EntityId.BREEZE)
                            .build();
        }
        if (EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE == null) {
            EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE = AllayEntityType
                            .builder(EntityBreezeWindChargeProjectileImpl.class)
                            .vanillaEntity(EntityId.BREEZE_WIND_CHARGE_PROJECTILE)
                            .build();
        }
        if (EntityTypes.CAMEL == null) {
            EntityTypes.CAMEL = AllayEntityType
                            .builder(EntityCamelImpl.class)
                            .vanillaEntity(EntityId.CAMEL)
                            .build();
        }
        if (EntityTypes.CAMEL_HUSK == null) {
            EntityTypes.CAMEL_HUSK = AllayEntityType
                            .builder(EntityCamelHuskImpl.class)
                            .vanillaEntity(EntityId.CAMEL_HUSK)
                            .build();
        }
        if (EntityTypes.CAT == null) {
            EntityTypes.CAT = AllayEntityType
                            .builder(EntityCatImpl.class)
                            .vanillaEntity(EntityId.CAT)
                            .build();
        }
        if (EntityTypes.CAVE_SPIDER == null) {
            EntityTypes.CAVE_SPIDER = AllayEntityType
                            .builder(EntityCaveSpiderImpl.class)
                            .vanillaEntity(EntityId.CAVE_SPIDER)
                            .build();
        }
        if (EntityTypes.CHEST_BOAT == null) {
            EntityTypes.CHEST_BOAT = AllayEntityType
                            .builder(EntityChestBoatImpl.class)
                            .vanillaEntity(EntityId.CHEST_BOAT)
                            .build();
        }
        if (EntityTypes.CHEST_MINECART == null) {
            EntityTypes.CHEST_MINECART = AllayEntityType
                            .builder(EntityChestMinecartImpl.class)
                            .vanillaEntity(EntityId.CHEST_MINECART)
                            .build();
        }
        if (EntityTypes.CHICKEN == null) {
            EntityTypes.CHICKEN = AllayEntityType
                            .builder(EntityChickenImpl.class)
                            .vanillaEntity(EntityId.CHICKEN)
                            .build();
        }
        if (EntityTypes.COD == null) {
            EntityTypes.COD = AllayEntityType
                            .builder(EntityCodImpl.class)
                            .vanillaEntity(EntityId.COD)
                            .build();
        }
        if (EntityTypes.COMMAND_BLOCK_MINECART == null) {
            EntityTypes.COMMAND_BLOCK_MINECART = AllayEntityType
                            .builder(EntityCommandBlockMinecartImpl.class)
                            .vanillaEntity(EntityId.COMMAND_BLOCK_MINECART)
                            .build();
        }
        if (EntityTypes.COPPER_GOLEM == null) {
            EntityTypes.COPPER_GOLEM = AllayEntityType
                            .builder(EntityCopperGolemImpl.class)
                            .vanillaEntity(EntityId.COPPER_GOLEM)
                            .build();
        }
        if (EntityTypes.COW == null) {
            EntityTypes.COW = AllayEntityType
                            .builder(EntityCowImpl.class)
                            .vanillaEntity(EntityId.COW)
                            .build();
        }
        if (EntityTypes.CREAKING == null) {
            EntityTypes.CREAKING = AllayEntityType
                            .builder(EntityCreakingImpl.class)
                            .vanillaEntity(EntityId.CREAKING)
                            .build();
        }
        if (EntityTypes.CREEPER == null) {
            EntityTypes.CREEPER = AllayEntityType
                            .builder(EntityCreeperImpl.class)
                            .vanillaEntity(EntityId.CREEPER)
                            .build();
        }
        if (EntityTypes.DOLPHIN == null) {
            EntityTypes.DOLPHIN = AllayEntityType
                            .builder(EntityDolphinImpl.class)
                            .vanillaEntity(EntityId.DOLPHIN)
                            .build();
        }
        if (EntityTypes.DONKEY == null) {
            EntityTypes.DONKEY = AllayEntityType
                            .builder(EntityDonkeyImpl.class)
                            .vanillaEntity(EntityId.DONKEY)
                            .build();
        }
        if (EntityTypes.DRAGON_FIREBALL == null) {
            EntityTypes.DRAGON_FIREBALL = AllayEntityType
                            .builder(EntityDragonFireballImpl.class)
                            .vanillaEntity(EntityId.DRAGON_FIREBALL)
                            .build();
        }
        if (EntityTypes.DROWNED == null) {
            EntityTypes.DROWNED = AllayEntityType
                            .builder(EntityDrownedImpl.class)
                            .vanillaEntity(EntityId.DROWNED)
                            .build();
        }
        if (EntityTypes.EGG == null) {
            EntityTypes.EGG = AllayEntityType
                            .builder(EntityEggImpl.class)
                            .vanillaEntity(EntityId.EGG)
                            .build();
        }
        if (EntityTypes.ELDER_GUARDIAN == null) {
            EntityTypes.ELDER_GUARDIAN = AllayEntityType
                            .builder(EntityElderGuardianImpl.class)
                            .vanillaEntity(EntityId.ELDER_GUARDIAN)
                            .build();
        }
        if (EntityTypes.ELDER_GUARDIAN_GHOST == null) {
            EntityTypes.ELDER_GUARDIAN_GHOST = AllayEntityType
                            .builder(EntityElderGuardianGhostImpl.class)
                            .vanillaEntity(EntityId.ELDER_GUARDIAN_GHOST)
                            .build();
        }
        if (EntityTypes.ENDER_CRYSTAL == null) {
            EntityTypes.ENDER_CRYSTAL = AllayEntityType
                            .builder(EntityEnderCrystalImpl.class)
                            .vanillaEntity(EntityId.ENDER_CRYSTAL)
                            .build();
        }
        if (EntityTypes.ENDER_DRAGON == null) {
            EntityTypes.ENDER_DRAGON = AllayEntityType
                            .builder(EntityEnderDragonImpl.class)
                            .vanillaEntity(EntityId.ENDER_DRAGON)
                            .build();
        }
        if (EntityTypes.ENDER_PEARL == null) {
            EntityTypes.ENDER_PEARL = AllayEntityType
                            .builder(EntityEnderPearlImpl.class)
                            .vanillaEntity(EntityId.ENDER_PEARL)
                            .build();
        }
        if (EntityTypes.ENDERMAN == null) {
            EntityTypes.ENDERMAN = AllayEntityType
                            .builder(EntityEndermanImpl.class)
                            .vanillaEntity(EntityId.ENDERMAN)
                            .build();
        }
        if (EntityTypes.ENDERMITE == null) {
            EntityTypes.ENDERMITE = AllayEntityType
                            .builder(EntityEndermiteImpl.class)
                            .vanillaEntity(EntityId.ENDERMITE)
                            .build();
        }
        if (EntityTypes.EVOCATION_FANG == null) {
            EntityTypes.EVOCATION_FANG = AllayEntityType
                            .builder(EntityEvocationFangImpl.class)
                            .vanillaEntity(EntityId.EVOCATION_FANG)
                            .build();
        }
        if (EntityTypes.EVOCATION_ILLAGER == null) {
            EntityTypes.EVOCATION_ILLAGER = AllayEntityType
                            .builder(EntityEvocationIllagerImpl.class)
                            .vanillaEntity(EntityId.EVOCATION_ILLAGER)
                            .build();
        }
        if (EntityTypes.EYE_OF_ENDER_SIGNAL == null) {
            EntityTypes.EYE_OF_ENDER_SIGNAL = AllayEntityType
                            .builder(EntityEyeOfEnderSignalImpl.class)
                            .vanillaEntity(EntityId.EYE_OF_ENDER_SIGNAL)
                            .build();
        }
        if (EntityTypes.FALLING_BLOCK == null) {
            EntityTypes.FALLING_BLOCK = AllayEntityType
                            .builder(EntityFallingBlockImpl.class)
                            .vanillaEntity(EntityId.FALLING_BLOCK)
                            .build();
        }
        if (EntityTypes.FIREBALL == null) {
            EntityTypes.FIREBALL = AllayEntityType
                            .builder(EntityFireballImpl.class)
                            .vanillaEntity(EntityId.FIREBALL)
                            .build();
        }
        if (EntityTypes.FIREWORKS_ROCKET == null) {
            EntityTypes.FIREWORKS_ROCKET = AllayEntityType
                            .builder(EntityFireworksRocketImpl.class)
                            .vanillaEntity(EntityId.FIREWORKS_ROCKET)
                            .build();
        }
        if (EntityTypes.FISHING_HOOK == null) {
            EntityTypes.FISHING_HOOK = AllayEntityType
                            .builder(EntityFishingHookImpl.class)
                            .vanillaEntity(EntityId.FISHING_HOOK)
                            .build();
        }
        if (EntityTypes.FOX == null) {
            EntityTypes.FOX = AllayEntityType
                            .builder(EntityFoxImpl.class)
                            .vanillaEntity(EntityId.FOX)
                            .build();
        }
        if (EntityTypes.FROG == null) {
            EntityTypes.FROG = AllayEntityType
                            .builder(EntityFrogImpl.class)
                            .vanillaEntity(EntityId.FROG)
                            .build();
        }
        if (EntityTypes.GHAST == null) {
            EntityTypes.GHAST = AllayEntityType
                            .builder(EntityGhastImpl.class)
                            .vanillaEntity(EntityId.GHAST)
                            .build();
        }
        if (EntityTypes.GLOW_SQUID == null) {
            EntityTypes.GLOW_SQUID = AllayEntityType
                            .builder(EntityGlowSquidImpl.class)
                            .vanillaEntity(EntityId.GLOW_SQUID)
                            .build();
        }
        if (EntityTypes.GOAT == null) {
            EntityTypes.GOAT = AllayEntityType
                            .builder(EntityGoatImpl.class)
                            .vanillaEntity(EntityId.GOAT)
                            .build();
        }
        if (EntityTypes.GUARDIAN == null) {
            EntityTypes.GUARDIAN = AllayEntityType
                            .builder(EntityGuardianImpl.class)
                            .vanillaEntity(EntityId.GUARDIAN)
                            .build();
        }
        if (EntityTypes.HAPPY_GHAST == null) {
            EntityTypes.HAPPY_GHAST = AllayEntityType
                            .builder(EntityHappyGhastImpl.class)
                            .vanillaEntity(EntityId.HAPPY_GHAST)
                            .build();
        }
        if (EntityTypes.HOGLIN == null) {
            EntityTypes.HOGLIN = AllayEntityType
                            .builder(EntityHoglinImpl.class)
                            .vanillaEntity(EntityId.HOGLIN)
                            .build();
        }
        if (EntityTypes.HOPPER_MINECART == null) {
            EntityTypes.HOPPER_MINECART = AllayEntityType
                            .builder(EntityHopperMinecartImpl.class)
                            .vanillaEntity(EntityId.HOPPER_MINECART)
                            .build();
        }
        if (EntityTypes.HORSE == null) {
            EntityTypes.HORSE = AllayEntityType
                            .builder(EntityHorseImpl.class)
                            .vanillaEntity(EntityId.HORSE)
                            .build();
        }
        if (EntityTypes.HUSK == null) {
            EntityTypes.HUSK = AllayEntityType
                            .builder(EntityHuskImpl.class)
                            .vanillaEntity(EntityId.HUSK)
                            .build();
        }
        if (EntityTypes.ICE_BOMB == null) {
            EntityTypes.ICE_BOMB = AllayEntityType
                            .builder(EntityIceBombImpl.class)
                            .vanillaEntity(EntityId.ICE_BOMB)
                            .build();
        }
        if (EntityTypes.IRON_GOLEM == null) {
            EntityTypes.IRON_GOLEM = AllayEntityType
                            .builder(EntityIronGolemImpl.class)
                            .vanillaEntity(EntityId.IRON_GOLEM)
                            .build();
        }
        if (EntityTypes.ITEM == null) {
            EntityTypes.ITEM = AllayEntityType
                            .builder(EntityItemImpl.class)
                            .vanillaEntity(EntityId.ITEM)
                            .build();
        }
        if (EntityTypes.LEASH_KNOT == null) {
            EntityTypes.LEASH_KNOT = AllayEntityType
                            .builder(EntityLeashKnotImpl.class)
                            .vanillaEntity(EntityId.LEASH_KNOT)
                            .build();
        }
        if (EntityTypes.LIGHTNING_BOLT == null) {
            EntityTypes.LIGHTNING_BOLT = AllayEntityType
                            .builder(EntityLightningBoltImpl.class)
                            .vanillaEntity(EntityId.LIGHTNING_BOLT)
                            .build();
        }
        if (EntityTypes.LINGERING_POTION == null) {
            EntityTypes.LINGERING_POTION = AllayEntityType
                            .builder(EntityLingeringPotionImpl.class)
                            .vanillaEntity(EntityId.LINGERING_POTION)
                            .build();
        }
        if (EntityTypes.LLAMA == null) {
            EntityTypes.LLAMA = AllayEntityType
                            .builder(EntityLlamaImpl.class)
                            .vanillaEntity(EntityId.LLAMA)
                            .build();
        }
        if (EntityTypes.LLAMA_SPIT == null) {
            EntityTypes.LLAMA_SPIT = AllayEntityType
                            .builder(EntityLlamaSpitImpl.class)
                            .vanillaEntity(EntityId.LLAMA_SPIT)
                            .build();
        }
        if (EntityTypes.MAGMA_CUBE == null) {
            EntityTypes.MAGMA_CUBE = AllayEntityType
                            .builder(EntityMagmaCubeImpl.class)
                            .vanillaEntity(EntityId.MAGMA_CUBE)
                            .build();
        }
        if (EntityTypes.MINECART == null) {
            EntityTypes.MINECART = AllayEntityType
                            .builder(EntityMinecartImpl.class)
                            .vanillaEntity(EntityId.MINECART)
                            .build();
        }
        if (EntityTypes.MOOSHROOM == null) {
            EntityTypes.MOOSHROOM = AllayEntityType
                            .builder(EntityMooshroomImpl.class)
                            .vanillaEntity(EntityId.MOOSHROOM)
                            .build();
        }
        if (EntityTypes.MULE == null) {
            EntityTypes.MULE = AllayEntityType
                            .builder(EntityMuleImpl.class)
                            .vanillaEntity(EntityId.MULE)
                            .build();
        }
        if (EntityTypes.NAUTILUS == null) {
            EntityTypes.NAUTILUS = AllayEntityType
                            .builder(EntityNautilusImpl.class)
                            .vanillaEntity(EntityId.NAUTILUS)
                            .build();
        }
        if (EntityTypes.NPC == null) {
            EntityTypes.NPC = AllayEntityType
                            .builder(EntityNpcImpl.class)
                            .vanillaEntity(EntityId.NPC)
                            .build();
        }
        if (EntityTypes.OCELOT == null) {
            EntityTypes.OCELOT = AllayEntityType
                            .builder(EntityOcelotImpl.class)
                            .vanillaEntity(EntityId.OCELOT)
                            .build();
        }
        if (EntityTypes.OMINOUS_ITEM_SPAWNER == null) {
            EntityTypes.OMINOUS_ITEM_SPAWNER = AllayEntityType
                            .builder(EntityOminousItemSpawnerImpl.class)
                            .vanillaEntity(EntityId.OMINOUS_ITEM_SPAWNER)
                            .build();
        }
        if (EntityTypes.PAINTING == null) {
            EntityTypes.PAINTING = AllayEntityType
                            .builder(EntityPaintingImpl.class)
                            .vanillaEntity(EntityId.PAINTING)
                            .build();
        }
        if (EntityTypes.PANDA == null) {
            EntityTypes.PANDA = AllayEntityType
                            .builder(EntityPandaImpl.class)
                            .vanillaEntity(EntityId.PANDA)
                            .build();
        }
        if (EntityTypes.PARCHED == null) {
            EntityTypes.PARCHED = AllayEntityType
                            .builder(EntityParchedImpl.class)
                            .vanillaEntity(EntityId.PARCHED)
                            .build();
        }
        if (EntityTypes.PARROT == null) {
            EntityTypes.PARROT = AllayEntityType
                            .builder(EntityParrotImpl.class)
                            .vanillaEntity(EntityId.PARROT)
                            .build();
        }
        if (EntityTypes.PHANTOM == null) {
            EntityTypes.PHANTOM = AllayEntityType
                            .builder(EntityPhantomImpl.class)
                            .vanillaEntity(EntityId.PHANTOM)
                            .build();
        }
        if (EntityTypes.PIG == null) {
            EntityTypes.PIG = AllayEntityType
                            .builder(EntityPigImpl.class)
                            .vanillaEntity(EntityId.PIG)
                            .build();
        }
        if (EntityTypes.PIGLIN == null) {
            EntityTypes.PIGLIN = AllayEntityType
                            .builder(EntityPiglinImpl.class)
                            .vanillaEntity(EntityId.PIGLIN)
                            .build();
        }
        if (EntityTypes.PIGLIN_BRUTE == null) {
            EntityTypes.PIGLIN_BRUTE = AllayEntityType
                            .builder(EntityPiglinBruteImpl.class)
                            .vanillaEntity(EntityId.PIGLIN_BRUTE)
                            .build();
        }
        if (EntityTypes.PILLAGER == null) {
            EntityTypes.PILLAGER = AllayEntityType
                            .builder(EntityPillagerImpl.class)
                            .vanillaEntity(EntityId.PILLAGER)
                            .build();
        }
        if (EntityTypes.PLAYER == null) {
            EntityTypes.PLAYER = AllayEntityType
                            .builder(EntityPlayerImpl.class)
                            .vanillaEntity(EntityId.PLAYER)
                            .build();
        }
        if (EntityTypes.POLAR_BEAR == null) {
            EntityTypes.POLAR_BEAR = AllayEntityType
                            .builder(EntityPolarBearImpl.class)
                            .vanillaEntity(EntityId.POLAR_BEAR)
                            .build();
        }
        if (EntityTypes.PUFFERFISH == null) {
            EntityTypes.PUFFERFISH = AllayEntityType
                            .builder(EntityPufferfishImpl.class)
                            .vanillaEntity(EntityId.PUFFERFISH)
                            .build();
        }
        if (EntityTypes.RABBIT == null) {
            EntityTypes.RABBIT = AllayEntityType
                            .builder(EntityRabbitImpl.class)
                            .vanillaEntity(EntityId.RABBIT)
                            .build();
        }
        if (EntityTypes.RAVAGER == null) {
            EntityTypes.RAVAGER = AllayEntityType
                            .builder(EntityRavagerImpl.class)
                            .vanillaEntity(EntityId.RAVAGER)
                            .build();
        }
        if (EntityTypes.SALMON == null) {
            EntityTypes.SALMON = AllayEntityType
                            .builder(EntitySalmonImpl.class)
                            .vanillaEntity(EntityId.SALMON)
                            .build();
        }
        if (EntityTypes.SHEEP == null) {
            EntityTypes.SHEEP = AllayEntityType
                            .builder(EntitySheepImpl.class)
                            .vanillaEntity(EntityId.SHEEP)
                            .build();
        }
        if (EntityTypes.SHULKER == null) {
            EntityTypes.SHULKER = AllayEntityType
                            .builder(EntityShulkerImpl.class)
                            .vanillaEntity(EntityId.SHULKER)
                            .build();
        }
        if (EntityTypes.SHULKER_BULLET == null) {
            EntityTypes.SHULKER_BULLET = AllayEntityType
                            .builder(EntityShulkerBulletImpl.class)
                            .vanillaEntity(EntityId.SHULKER_BULLET)
                            .build();
        }
        if (EntityTypes.SILVERFISH == null) {
            EntityTypes.SILVERFISH = AllayEntityType
                            .builder(EntitySilverfishImpl.class)
                            .vanillaEntity(EntityId.SILVERFISH)
                            .build();
        }
        if (EntityTypes.SKELETON == null) {
            EntityTypes.SKELETON = AllayEntityType
                            .builder(EntitySkeletonImpl.class)
                            .vanillaEntity(EntityId.SKELETON)
                            .build();
        }
        if (EntityTypes.SKELETON_HORSE == null) {
            EntityTypes.SKELETON_HORSE = AllayEntityType
                            .builder(EntitySkeletonHorseImpl.class)
                            .vanillaEntity(EntityId.SKELETON_HORSE)
                            .build();
        }
        if (EntityTypes.SLIME == null) {
            EntityTypes.SLIME = AllayEntityType
                            .builder(EntitySlimeImpl.class)
                            .vanillaEntity(EntityId.SLIME)
                            .build();
        }
        if (EntityTypes.SMALL_FIREBALL == null) {
            EntityTypes.SMALL_FIREBALL = AllayEntityType
                            .builder(EntitySmallFireballImpl.class)
                            .vanillaEntity(EntityId.SMALL_FIREBALL)
                            .build();
        }
        if (EntityTypes.SNIFFER == null) {
            EntityTypes.SNIFFER = AllayEntityType
                            .builder(EntitySnifferImpl.class)
                            .vanillaEntity(EntityId.SNIFFER)
                            .build();
        }
        if (EntityTypes.SNOW_GOLEM == null) {
            EntityTypes.SNOW_GOLEM = AllayEntityType
                            .builder(EntitySnowGolemImpl.class)
                            .vanillaEntity(EntityId.SNOW_GOLEM)
                            .build();
        }
        if (EntityTypes.SNOWBALL == null) {
            EntityTypes.SNOWBALL = AllayEntityType
                            .builder(EntitySnowballImpl.class)
                            .vanillaEntity(EntityId.SNOWBALL)
                            .build();
        }
        if (EntityTypes.SPIDER == null) {
            EntityTypes.SPIDER = AllayEntityType
                            .builder(EntitySpiderImpl.class)
                            .vanillaEntity(EntityId.SPIDER)
                            .build();
        }
        if (EntityTypes.SPLASH_POTION == null) {
            EntityTypes.SPLASH_POTION = AllayEntityType
                            .builder(EntitySplashPotionImpl.class)
                            .vanillaEntity(EntityId.SPLASH_POTION)
                            .build();
        }
        if (EntityTypes.SQUID == null) {
            EntityTypes.SQUID = AllayEntityType
                            .builder(EntitySquidImpl.class)
                            .vanillaEntity(EntityId.SQUID)
                            .build();
        }
        if (EntityTypes.STRAY == null) {
            EntityTypes.STRAY = AllayEntityType
                            .builder(EntityStrayImpl.class)
                            .vanillaEntity(EntityId.STRAY)
                            .build();
        }
        if (EntityTypes.STRIDER == null) {
            EntityTypes.STRIDER = AllayEntityType
                            .builder(EntityStriderImpl.class)
                            .vanillaEntity(EntityId.STRIDER)
                            .build();
        }
        if (EntityTypes.TADPOLE == null) {
            EntityTypes.TADPOLE = AllayEntityType
                            .builder(EntityTadpoleImpl.class)
                            .vanillaEntity(EntityId.TADPOLE)
                            .build();
        }
        if (EntityTypes.THROWN_TRIDENT == null) {
            EntityTypes.THROWN_TRIDENT = AllayEntityType
                            .builder(EntityThrownTridentImpl.class)
                            .vanillaEntity(EntityId.THROWN_TRIDENT)
                            .build();
        }
        if (EntityTypes.TNT == null) {
            EntityTypes.TNT = AllayEntityType
                            .builder(EntityTntImpl.class)
                            .vanillaEntity(EntityId.TNT)
                            .build();
        }
        if (EntityTypes.TNT_MINECART == null) {
            EntityTypes.TNT_MINECART = AllayEntityType
                            .builder(EntityTntMinecartImpl.class)
                            .vanillaEntity(EntityId.TNT_MINECART)
                            .build();
        }
        if (EntityTypes.TRADER_LLAMA == null) {
            EntityTypes.TRADER_LLAMA = AllayEntityType
                            .builder(EntityTraderLlamaImpl.class)
                            .vanillaEntity(EntityId.TRADER_LLAMA)
                            .build();
        }
        if (EntityTypes.TRIPOD_CAMERA == null) {
            EntityTypes.TRIPOD_CAMERA = AllayEntityType
                            .builder(EntityTripodCameraImpl.class)
                            .vanillaEntity(EntityId.TRIPOD_CAMERA)
                            .build();
        }
        if (EntityTypes.TROPICALFISH == null) {
            EntityTypes.TROPICALFISH = AllayEntityType
                            .builder(EntityTropicalfishImpl.class)
                            .vanillaEntity(EntityId.TROPICALFISH)
                            .build();
        }
        if (EntityTypes.TURTLE == null) {
            EntityTypes.TURTLE = AllayEntityType
                            .builder(EntityTurtleImpl.class)
                            .vanillaEntity(EntityId.TURTLE)
                            .build();
        }
        if (EntityTypes.VEX == null) {
            EntityTypes.VEX = AllayEntityType
                            .builder(EntityVexImpl.class)
                            .vanillaEntity(EntityId.VEX)
                            .build();
        }
        if (EntityTypes.VILLAGER == null) {
            EntityTypes.VILLAGER = AllayEntityType
                            .builder(EntityVillagerImpl.class)
                            .vanillaEntity(EntityId.VILLAGER)
                            .build();
        }
        if (EntityTypes.VILLAGER_V2 == null) {
            EntityTypes.VILLAGER_V2 = AllayEntityType
                            .builder(EntityVillagerV2Impl.class)
                            .vanillaEntity(EntityId.VILLAGER_V2)
                            .build();
        }
        if (EntityTypes.VINDICATOR == null) {
            EntityTypes.VINDICATOR = AllayEntityType
                            .builder(EntityVindicatorImpl.class)
                            .vanillaEntity(EntityId.VINDICATOR)
                            .build();
        }
        if (EntityTypes.WANDERING_TRADER == null) {
            EntityTypes.WANDERING_TRADER = AllayEntityType
                            .builder(EntityWanderingTraderImpl.class)
                            .vanillaEntity(EntityId.WANDERING_TRADER)
                            .build();
        }
        if (EntityTypes.WARDEN == null) {
            EntityTypes.WARDEN = AllayEntityType
                            .builder(EntityWardenImpl.class)
                            .vanillaEntity(EntityId.WARDEN)
                            .build();
        }
        if (EntityTypes.WIND_CHARGE_PROJECTILE == null) {
            EntityTypes.WIND_CHARGE_PROJECTILE = AllayEntityType
                            .builder(EntityWindChargeProjectileImpl.class)
                            .vanillaEntity(EntityId.WIND_CHARGE_PROJECTILE)
                            .build();
        }
        if (EntityTypes.WITCH == null) {
            EntityTypes.WITCH = AllayEntityType
                            .builder(EntityWitchImpl.class)
                            .vanillaEntity(EntityId.WITCH)
                            .build();
        }
        if (EntityTypes.WITHER == null) {
            EntityTypes.WITHER = AllayEntityType
                            .builder(EntityWitherImpl.class)
                            .vanillaEntity(EntityId.WITHER)
                            .build();
        }
        if (EntityTypes.WITHER_SKELETON == null) {
            EntityTypes.WITHER_SKELETON = AllayEntityType
                            .builder(EntityWitherSkeletonImpl.class)
                            .vanillaEntity(EntityId.WITHER_SKELETON)
                            .build();
        }
        if (EntityTypes.WITHER_SKULL == null) {
            EntityTypes.WITHER_SKULL = AllayEntityType
                            .builder(EntityWitherSkullImpl.class)
                            .vanillaEntity(EntityId.WITHER_SKULL)
                            .build();
        }
        if (EntityTypes.WITHER_SKULL_DANGEROUS == null) {
            EntityTypes.WITHER_SKULL_DANGEROUS = AllayEntityType
                            .builder(EntityWitherSkullDangerousImpl.class)
                            .vanillaEntity(EntityId.WITHER_SKULL_DANGEROUS)
                            .build();
        }
        if (EntityTypes.WOLF == null) {
            EntityTypes.WOLF = AllayEntityType
                            .builder(EntityWolfImpl.class)
                            .vanillaEntity(EntityId.WOLF)
                            .build();
        }
        if (EntityTypes.XP_BOTTLE == null) {
            EntityTypes.XP_BOTTLE = AllayEntityType
                            .builder(EntityXpBottleImpl.class)
                            .vanillaEntity(EntityId.XP_BOTTLE)
                            .build();
        }
        if (EntityTypes.XP_ORB == null) {
            EntityTypes.XP_ORB = AllayEntityType
                            .builder(EntityXpOrbImpl.class)
                            .vanillaEntity(EntityId.XP_ORB)
                            .build();
        }
        if (EntityTypes.ZOGLIN == null) {
            EntityTypes.ZOGLIN = AllayEntityType
                            .builder(EntityZoglinImpl.class)
                            .vanillaEntity(EntityId.ZOGLIN)
                            .build();
        }
        if (EntityTypes.ZOMBIE == null) {
            EntityTypes.ZOMBIE = AllayEntityType
                            .builder(EntityZombieImpl.class)
                            .vanillaEntity(EntityId.ZOMBIE)
                            .build();
        }
        if (EntityTypes.ZOMBIE_HORSE == null) {
            EntityTypes.ZOMBIE_HORSE = AllayEntityType
                            .builder(EntityZombieHorseImpl.class)
                            .vanillaEntity(EntityId.ZOMBIE_HORSE)
                            .build();
        }
        if (EntityTypes.ZOMBIE_NAUTILUS == null) {
            EntityTypes.ZOMBIE_NAUTILUS = AllayEntityType
                            .builder(EntityZombieNautilusImpl.class)
                            .vanillaEntity(EntityId.ZOMBIE_NAUTILUS)
                            .build();
        }
        if (EntityTypes.ZOMBIE_PIGMAN == null) {
            EntityTypes.ZOMBIE_PIGMAN = AllayEntityType
                            .builder(EntityZombiePigmanImpl.class)
                            .vanillaEntity(EntityId.ZOMBIE_PIGMAN)
                            .build();
        }
        if (EntityTypes.ZOMBIE_VILLAGER == null) {
            EntityTypes.ZOMBIE_VILLAGER = AllayEntityType
                            .builder(EntityZombieVillagerImpl.class)
                            .vanillaEntity(EntityId.ZOMBIE_VILLAGER)
                            .build();
        }
        if (EntityTypes.ZOMBIE_VILLAGER_V2 == null) {
            EntityTypes.ZOMBIE_VILLAGER_V2 = AllayEntityType
                            .builder(EntityZombieVillagerV2Impl.class)
                            .vanillaEntity(EntityId.ZOMBIE_VILLAGER_V2)
                            .build();
        }
    }
}
