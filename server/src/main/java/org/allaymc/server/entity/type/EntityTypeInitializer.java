package org.allaymc.server.entity.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.entity.interfaces.EntityEnderDragon;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.type.ItemTypes;

import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.entity.ai.behavior.BehaviorImpl;
import org.allaymc.server.entity.ai.behaviorgroup.BehaviorGroupImpl;
import org.allaymc.server.entity.ai.controller.FluctuateController;
import org.allaymc.server.entity.ai.controller.LookController;
import org.allaymc.server.entity.ai.controller.WalkController;
import org.allaymc.server.entity.ai.evaluator.MemoryCheckNotEmptyEvaluator;
import org.allaymc.server.entity.ai.evaluator.PassByTimeEvaluator;
import org.allaymc.server.entity.ai.executor.*;
import org.allaymc.server.entity.ai.route.finder.FlatAStarRouteFinder;
import org.allaymc.server.entity.ai.route.posevaluator.WalkingPosEvaluator;
import org.allaymc.server.entity.ai.sensor.NearestFeedingPlayerSensor;
import org.allaymc.server.entity.ai.sensor.NearestPlayerSensor;
import org.allaymc.server.entity.component.*;
import org.allaymc.server.entity.component.armorstand.EntityArmorStandBaseComponentImpl;
import org.allaymc.server.entity.component.armorstand.EntityArmorStandContainerHolderComponentImpl;
import org.allaymc.server.entity.component.armorstand.EntityArmorStandLivingComponentImpl;
import org.allaymc.server.entity.component.fireworks.EntityFireworksRocketBaseComponentImpl;
import org.allaymc.server.entity.component.fireworks.EntityFireworksRocketPhysicsComponentImpl;
import org.allaymc.server.entity.component.fishinghook.EntityFishingHookBaseComponentImpl;
import org.allaymc.server.entity.component.fishinghook.EntityFishingHookPhysicsComponentImpl;
import org.allaymc.server.entity.component.EntityItemBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerContainerHolderComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerLivingComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerPhysicsComponentImpl;
import org.allaymc.server.entity.component.projectile.*;
import org.allaymc.server.entity.component.tnt.EntityTntBaseComponentImpl;
import org.allaymc.server.entity.component.tnt.EntityTntPhysicsComponentImpl;
import org.allaymc.server.entity.component.sheep.EntitySheepBaseComponentImpl;
import org.allaymc.server.entity.data.EntityId;
import org.allaymc.server.entity.impl.*;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class EntityTypeInitializer {
    public static void initFallingBlock() {
        EntityTypes.FALLING_BLOCK = AllayEntityType
                .builder(EntityFallingBlockImpl.class)
                .vanillaEntity(EntityId.FALLING_BLOCK)
                .addComponent(EntityFallingBlockBaseComponentImpl::new, EntityFallingBlockBaseComponentImpl.class)
                .addComponent(() -> new EntityPhysicsComponentImpl() {
                    {
                        // The initial onGround state for falling block is false
                        // And it will be either turned into block or item based
                        // on the block which the falling block fell on
                        this.onGround = false;
                    }

                    @Override
                    public double getGravity() {
                        return 0.04;
                    }

                    @Override
                    public boolean computeLiquidPhysics() {
                        // Falling blocks have no special water physics in vanilla
                        return false;
                    }
                }, EntityPhysicsComponentImpl.class)
                .build();
    }

    public static void initItem() {
        EntityTypes.ITEM = AllayEntityType
                .builder(EntityItemImpl.class)
                .vanillaEntity(EntityId.ITEM)
                .addComponent(EntityItemBaseComponentImpl::new, EntityItemBaseComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl() {
                        @Override
                        public boolean canBeAttacked(DamageContainer damage) {
                            if (!super.canBeAttacked(damage)) {
                                return false;
                            }

                            var damageType = damage.getDamageType();
                            if (damageType == DamageType.FALLING_BLOCK ||
                                damageType == DamageType.STALACTITE) {
                                return false;
                            }

                            return true;
                        }

                        @Override
                        public boolean hasFallDamage() {
                            return false;
                        }

                        @Override
                        public boolean hasDrowningDamage() {
                            return false;
                        }

                        @Override
                        protected boolean hasDeadTimer() {
                            return false;
                        }
                    };
                    component.setMaxHealth(5);
                    return component;
                }, EntityLivingComponentImpl.class)
                .addComponent(() -> new EntityPhysicsComponentImpl() {
                    @Override
                    public double getGravity() {
                        return 0.04;
                    }

                    @Override
                    public double getWaterBuoyancy() {
                        return 0.0405;
                    }

                    @Override
                    public double getWaterDragFactor() {
                        return 0.02;
                    }

                    @Override
                    public double getLavaBuoyancy() {
                        return 0.0405;
                    }

                    @Override
                    public double getLavaDragFactor() {
                        return 0.05;
                    }
                }, EntityPhysicsComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
    }

    public static void initPlayer() {
        EntityTypes.PLAYER = AllayEntityType
                .builder(EntityPlayerImpl.class)
                .vanillaEntity(EntityId.PLAYER)
                .addComponent(EntityPlayerBaseComponentImpl::new, EntityPlayerBaseComponentImpl.class)
                .addComponent(EntityPlayerContainerHolderComponentImpl::new, EntityPlayerContainerHolderComponentImpl.class)
                .addComponent(EntityPlayerLivingComponentImpl::new, EntityPlayerLivingComponentImpl.class)
                .addComponent(EntityPlayerPhysicsComponentImpl::new, EntityPlayerPhysicsComponentImpl.class)
                .addComponent(EntitySleepableComponentImpl::new, EntitySleepableComponentImpl.class)
                .build();
    }

    public static void initVillagerV2() {
        EntityTypes.VILLAGER_V2 = AllayEntityType
                .builder(EntityVillagerV2Impl.class)
                .vanillaEntity(EntityId.VILLAGER_V2)
                .addComponent(EntityLivingComponentImpl::new, EntityLivingComponentImpl.class)
                .addComponent(EntityHumanPhysicsComponentImpl::new, EntityHumanPhysicsComponentImpl.class)
                .build();
    }

    public static void initXBOrb() {
        EntityTypes.XP_ORB = AllayEntityType
                .builder(EntityXpOrbImpl.class)
                .vanillaEntity(EntityId.XP_ORB)
                .addComponent(EntityXpOrbBaseComponentImpl::new, EntityXpOrbBaseComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl() {
                        @Override
                        public boolean hasFallDamage() {
                            return false;
                        }
                    };
                    component.setMaxHealth(5);
                    return component;
                }, EntityLivingComponentImpl.class)
                .addComponent(() -> new EntityPhysicsComponentImpl() {
                    @Override
                    public double getGravity() {
                        return 0.03;
                    }

                    @Override
                    public double getWaterBuoyancy() {
                        return 0.0405;
                    }

                    @Override
                    public double getWaterDragFactor() {
                        return 0.02;
                    }

                    @Override
                    public double getLavaBuoyancy() {
                        return 0.0405;
                    }

                    @Override
                    public double getLavaDragFactor() {
                        return 0.05;
                    }
                }, EntityPhysicsComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
    }

    public static void initTnt() {
        EntityTypes.TNT = AllayEntityType
                .builder(EntityTntImpl.class)
                .vanillaEntity(EntityId.TNT)
                .addComponent(EntityTntBaseComponentImpl::new, EntityTntBaseComponentImpl.class)
                .addComponent(EntityTntPhysicsComponentImpl::new, EntityTntPhysicsComponentImpl.class)
                .build();
    }

    public static void initLightningBolt() {
        EntityTypes.LIGHTNING_BOLT = AllayEntityType
                .builder(EntityLightningBoltImpl.class)
                .vanillaEntity(EntityId.LIGHTNING_BOLT)
                .addComponent(EntityLightningBoltBaseComponentImpl::new, EntityLightningBoltBaseComponentImpl.class)
                .build();
    }

    public static void initEnderCrystal() {
        EntityTypes.ENDER_CRYSTAL = AllayEntityType
                .builder(EntityEnderCrystalImpl.class)
                .vanillaEntity(EntityId.ENDER_CRYSTAL)
                .addComponent(EntityEnderCrystalBaseComponentImpl::new, EntityEnderCrystalBaseComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl() {
                        @Override
                        public boolean canBeAttacked(DamageContainer damage) {
                            if (damage.getAttacker() instanceof EntityEnderDragon) {
                                return false;
                            }

                            return super.canBeAttacked(damage);
                        }

                        @Override
                        public boolean hasFireDamage() {
                            return false;
                        }

                        @Override
                        public boolean hasDrowningDamage() {
                            return false;
                        }

                        @Override
                        public boolean isFireproof() {
                            return true;
                        }

                        @Override
                        protected boolean hasDeadTimer() {
                            return false;
                        }
                    };
                    component.setMaxHealth(5);
                    return component;
                }, EntityLivingComponentImpl.class)
                .build();
    }

    public static void initProjectile() {
        EntityTypes.SNOWBALL = AllayEntityType
                .builder(EntitySnowballImpl.class)
                .vanillaEntity(EntityId.SNOWBALL)
                .addComponent(EntityProjectileBaseComponentImpl::new, EntityProjectileBaseComponentImpl.class)
                .addComponent(EntitySnowballPhysicsComponentImpl::new, EntitySnowballPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
        EntityTypes.SPLASH_POTION = AllayEntityType
                .builder(EntitySplashPotionImpl.class)
                .vanillaEntity(EntityId.SPLASH_POTION)
                .addComponent(EntityProjectileBaseComponentImpl::new, EntityProjectileBaseComponentImpl.class)
                .addComponent(EntitySplashPotionPhysicsComponentImpl::new, EntitySplashPotionPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(EntityPotionComponentImpl::new, EntityPotionComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
        EntityTypes.LINGERING_POTION = AllayEntityType
                .builder(EntityLingeringPotionImpl.class)
                .vanillaEntity(EntityId.LINGERING_POTION)
                .addComponent(EntityProjectileBaseComponentImpl::new, EntityProjectileBaseComponentImpl.class)
                .addComponent(EntityLingeringPotionPhysicsComponentImpl::new, EntityLingeringPotionPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(EntityPotionComponentImpl::new, EntityPotionComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
        EntityTypes.ENDER_PEARL = AllayEntityType
                .builder(EntityEnderPearlImpl.class)
                .vanillaEntity(EntityId.ENDER_PEARL)
                .addComponent(EntityProjectileBaseComponentImpl::new, EntityProjectileBaseComponentImpl.class)
                .addComponent(EntityEnderPearlPhysicsComponentImpl::new, EntityEnderPearlPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
        EntityTypes.XP_BOTTLE = AllayEntityType
                .builder(EntityXpBottleImpl.class)
                .vanillaEntity(EntityId.XP_BOTTLE)
                .addComponent(EntityProjectileBaseComponentImpl::new, EntityProjectileBaseComponentImpl.class)
                .addComponent(EntityXpBottlePhysicsComponentImpl::new, EntityXpBottlePhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
        EntityTypes.ARROW = AllayEntityType
                .builder(EntityArrowImpl.class)
                .vanillaEntity(EntityId.ARROW)
                .addComponent(EntityArrowBaseComponentImpl::new, EntityArrowBaseComponentImpl.class)
                .addComponent(EntityArrowPhysicsComponentImpl::new, EntityArrowPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(EntityPotionComponentImpl::new, EntityPotionComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl() {
                        @Override
                        public boolean canBeAttacked(DamageContainer damage) {
                            return damage.getDamageType() == DamageType.API;
                        }

                        @Override
                        public boolean hasFallDamage() {
                            return false;
                        }

                        @Override
                        public boolean hasFireDamage() {
                            return false;
                        }

                        @Override
                        public boolean hasDrowningDamage() {
                            return false;
                        }

                        @Override
                        protected boolean hasDeadTimer() {
                            return false;
                        }
                    };
                    component.setMaxHealth(5);
                    return component;
                }, EntityLivingComponentImpl.class)
                .build();
    }

    public static void initPainting() {
        EntityTypes.PAINTING = AllayEntityType
                .builder(EntityPaintingImpl.class)
                .vanillaEntity(EntityId.PAINTING)
                .addComponent(EntityPaintingBaseComponentImpl::new, EntityPaintingBaseComponentImpl.class)
                .addComponent(EntityLivingComponentImpl::new, EntityLivingComponentImpl.class)
                .build();
    }

    public static void initFireworkRocket() {
        EntityTypes.FIREWORKS_ROCKET = AllayEntityType
                .builder(EntityFireworksRocketImpl.class)
                .vanillaEntity(EntityId.FIREWORKS_ROCKET)
                .addComponent(EntityFireworksRocketPhysicsComponentImpl::new, EntityFireworksRocketPhysicsComponentImpl.class)
                .addComponent(EntityFireworksRocketBaseComponentImpl::new, EntityFireworksRocketBaseComponentImpl.class)
                .build();
    }

    public static void initAreaEffectCloud() {
        EntityTypes.AREA_EFFECT_CLOUD = AllayEntityType
                .builder(EntityAreaEffectCloudImpl.class)
                .vanillaEntity(EntityId.AREA_EFFECT_CLOUD)
                .addComponent(EntityAreaEffectCloudBaseComponentImpl::new, EntityAreaEffectCloudBaseComponentImpl.class)
                .addComponent(EntityPotionComponentImpl::new, EntityPotionComponentImpl.class)
                .build();
    }

    public static void initWindCharge() {
        EntityTypes.WIND_CHARGE_PROJECTILE = AllayEntityType
                .builder(EntityWindChargeProjectileImpl.class)
                .vanillaEntity(EntityId.WIND_CHARGE_PROJECTILE)
                .addComponent(EntityProjectileBaseComponentImpl::new, EntityProjectileBaseComponentImpl.class)
                .addComponent(EntityWindChargePhysicsComponentImpl::new, EntityWindChargePhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();

        EntityTypes.BREEZE_WIND_CHARGE_PROJECTILE = AllayEntityType
                .builder(EntityBreezeWindChargeProjectileImpl.class)
                .vanillaEntity(EntityId.BREEZE_WIND_CHARGE_PROJECTILE)
                .addComponent(EntityProjectileBaseComponentImpl::new, EntityProjectileBaseComponentImpl.class)
                .addComponent(EntityBreezeWindChargePhysicsComponentImpl::new, EntityBreezeWindChargePhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
    }

    public static void initFireball() {
        EntityTypes.SMALL_FIREBALL = AllayEntityType
                .builder(EntitySmallFireballImpl.class)
                .vanillaEntity(EntityId.SMALL_FIREBALL)
                .addComponent(EntitySmallFireballBaseComponentImpl::new, EntitySmallFireballBaseComponentImpl.class)
                .addComponent(EntitySmallFireballPhysicsComponentImpl::new, EntitySmallFireballPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
        EntityTypes.FIREBALL = AllayEntityType
                .builder(EntityFireballImpl.class)
                .vanillaEntity(EntityId.FIREBALL)
                .addComponent(EntityFireballBaseComponentImpl::new, EntityFireballBaseComponentImpl.class)
                .addComponent(EntityFireballPhysicsComponentImpl::new, EntityFireballPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
    }

    public static void initThrownTrident() {
        EntityTypes.THROWN_TRIDENT = AllayEntityType
                .builder(EntityThrownTridentImpl.class)
                .vanillaEntity(EntityId.THROWN_TRIDENT)
                .addComponent(EntityThrownTridentBaseComponentImpl::new, EntityThrownTridentBaseComponentImpl.class)
                .addComponent(EntityThrownTridentPhysicsComponentImpl::new, EntityThrownTridentPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                // Bedrock Edition tridents never despawn - use max integer age
                .addComponent(() -> new EntityAgeComponentImpl(Integer.MAX_VALUE), EntityAgeComponentImpl.class)
                .build();
    }

    public static void initEgg() {
        EntityTypes.EGG = AllayEntityType
                .builder(EntityEggImpl.class)
                .vanillaEntity(EntityId.EGG)
                .addComponent(EntityProjectileBaseComponentImpl::new, EntityProjectileBaseComponentImpl.class)
                .addComponent(EntityEggPhysicsComponentImpl::new, EntityEggPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
    }

    public static void initFishingHook() {
        EntityTypes.FISHING_HOOK = AllayEntityType
                .builder(EntityFishingHookImpl.class)
                .vanillaEntity(EntityId.FISHING_HOOK)
                .addComponent(EntityFishingHookBaseComponentImpl::new, EntityFishingHookBaseComponentImpl.class)
                .addComponent(EntityFishingHookPhysicsComponentImpl::new, EntityFishingHookPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                // Fishing hook despawns after 60 seconds if not used
                .addComponent(() -> new EntityAgeComponentImpl(1200), EntityAgeComponentImpl.class)
                .build();
    }

    public static void initArmorStand() {
        EntityTypes.ARMOR_STAND = AllayEntityType
                .builder(EntityArmorStandImpl.class)
                .vanillaEntity(EntityId.ARMOR_STAND)
                .addComponent(EntityArmorStandBaseComponentImpl::new, EntityArmorStandBaseComponentImpl.class)
                .addComponent(EntityArmorStandContainerHolderComponentImpl::new, EntityArmorStandContainerHolderComponentImpl.class)
                .addComponent(EntityArmorStandLivingComponentImpl::new, EntityArmorStandLivingComponentImpl.class)
                .addComponent(() -> new EntityHumanPhysicsComponentImpl() {
                    @Override
                    public void onFall(double fallDistance) {
                        super.onFall(fallDistance);
                        if (fallDistance >= 3) {
                            thisEntity.getDimension().addSound(thisEntity.getLocation(), SimpleSound.ARMOR_STAND_LAND);
                        }
                    }
                }, EntityHumanPhysicsComponentImpl.class)
                .build();
    }

    public static void initSheep() {
        EntityTypes.SHEEP = AllayEntityType
                .builder(EntitySheepImpl.class)
                .vanillaEntity(EntityId.SHEEP)
                .addComponent(EntitySheepBaseComponentImpl::new, EntitySheepBaseComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl();
                    component.setMaxHealth(8);
                    return component;
                }, EntityLivingComponentImpl.class)
                .addComponent(EntityAnimalPhysicsComponentImpl::new, EntityAnimalPhysicsComponentImpl.class)
                .addComponent(() -> new EntityAnimalComponentImpl(item -> item.getItemType() == ItemTypes.WHEAT), EntityAnimalComponentImpl.class)
                .addComponent(EntityBabyComponentImpl::new, EntityBabyComponentImpl.class)
                .addComponent(EntityHeadYawComponentImpl::new, EntityHeadYawComponentImpl.class)
                .addComponent(() -> new EntityDyeableComponentImpl(() -> {
                    var rand = ThreadLocalRandom.current();
                    int roll = rand.nextInt(100);
                    if (roll < 82) return DyeColor.WHITE;
                    if (roll < 87) return DyeColor.BLACK;
                    if (roll < 92) return DyeColor.GRAY;
                    if (roll < 97) return DyeColor.LIGHT_GRAY;
                    if (roll < 99) return DyeColor.BROWN;
                    return DyeColor.PINK;
                }), EntityDyeableComponentImpl.class)
                .addComponent(() -> {
                    var behaviorGroup = BehaviorGroupImpl.builder()
                            // Sensors
                            .sensor(new NearestFeedingPlayerSensor(8))
                            .sensor(new NearestPlayerSensor(8, 0, 20))
                            // Core behavior: enter love mode when fed
                            .coreBehavior(BehaviorImpl.builder()
                                    .executor(new InLoveExecutor(400))
                                    .evaluator(entity -> {
                                        if (Boolean.TRUE.equals(entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE))) return false;
                                        // 5-minute cooldown after last love (6000 ticks)
                                        var lastLoveTime = entity.getMemoryStorage().get(MemoryTypes.LAST_IN_LOVE_TIME);
                                        if (lastLoveTime != null && lastLoveTime > 0) {
                                            if (entity.getTick() - lastLoveTime < 6000) return false;
                                        }
                                        return new PassByTimeEvaluator(MemoryTypes.LAST_BE_FEED_TIME, 0, 400).evaluate(entity);
                                    })
                                    .priority(1)
                                    .build())
                            // Priority 1 (highest): flee when attacked
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.2875f, 12, 40, true, 100, true, 10))
                                    .evaluator(new PassByTimeEvaluator(MemoryTypes.LAST_BE_ATTACKED_TIME, 0, 100))
                                    .priority(1)
                                    .build())
                            // Priority 2: breed when in love
                            .behavior(BehaviorImpl.builder()
                                    .executor(new EntityBreedingExecutor(100, 0.23f))
                                    .evaluator(entity -> Boolean.TRUE.equals(entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE)))
                                    .priority(2)
                                    .build())
                            // Priority 3: follow player holding wheat
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FollowEntityExecutor(MemoryTypes.NEAREST_FEEDING_PLAYER, 0.253f, 64, 2.25))
                                    .evaluator(new MemoryCheckNotEmptyEvaluator(MemoryTypes.NEAREST_FEEDING_PLAYER))
                                    .priority(3)
                                    .build())
                            // Priority 4: eat grass
                            .behavior(BehaviorImpl.builder()
                                    .executor(new SheepEatGrassExecutor())
                                    .evaluator(entity -> {
                                        if (entity instanceof EntityAnimal animal && animal.isBaby()) {
                                            if (Math.random() >= 0.86) return false;
                                        } else {
                                            if (Math.random() >= 0.01) return false;
                                        }
                                        var loc = entity.getLocation();
                                        int x = (int) Math.floor(loc.x());
                                        int y = (int) Math.floor(loc.y());
                                        int z = (int) Math.floor(loc.z());
                                        var dim = entity.getDimension();
                                        var feetBlock = dim.getBlockState(x, y, z);
                                        if (feetBlock != null && feetBlock.getBlockType() == BlockTypes.SHORT_GRASS) return true;
                                        var belowBlock = dim.getBlockState(x, y - 1, z);
                                        return belowBlock != null && belowBlock.getBlockType() == BlockTypes.GRASS_BLOCK;
                                    })
                                    .priority(4)
                                    .period(100)
                                    .build())
                            // Priority 5: look at nearest player
                            .behavior(BehaviorImpl.builder()
                                    .executor(new LookAtEntityExecutor(MemoryTypes.NEAREST_PLAYER, 100))
                                    .evaluator(entity -> entity.getMemoryStorage().notEmpty(MemoryTypes.NEAREST_PLAYER) && Math.random() < 0.4)
                                    .priority(5)
                                    .period(100)
                                    .build())
                            // Priority 6 (lowest): random wandering
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.1f, 12, 100, false, -1, true, 10))
                                    .evaluator(entity -> true)
                                    .priority(6)
                                    .build())
                            // Controllers
                            .controller(new WalkController())
                            .controller(new LookController(true, true))
                            .controller(new FluctuateController())
                            // Route finder
                            .routeFinder(new FlatAStarRouteFinder(new WalkingPosEvaluator()))
                            .build();

                    return new EntityAIComponentImpl(behaviorGroup);
                }, EntityAIComponentImpl.class)
                .build();
    }
}
