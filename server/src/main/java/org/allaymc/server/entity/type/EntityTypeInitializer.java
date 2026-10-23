package org.allaymc.server.entity.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.component.EntityBabyComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.interfaces.*;
import org.allaymc.api.entity.property.type.EntityPropertyTypes;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.entity.ai.behavior.BehaviorImpl;
import org.allaymc.server.entity.ai.behaviorgroup.BehaviorGroupImpl;
import org.allaymc.server.entity.ai.controller.FluctuateController;
import org.allaymc.server.entity.ai.controller.LookController;
import org.allaymc.server.entity.ai.controller.WalkController;
import org.allaymc.server.entity.ai.evaluator.BlockCheckEvaluator;
import org.allaymc.server.entity.ai.evaluator.MemoryCheckNotEmptyEvaluator;
import org.allaymc.server.entity.ai.evaluator.PassByTimeEvaluator;
import org.allaymc.server.entity.ai.evaluator.ProbabilityEvaluator;
import org.allaymc.server.entity.ai.executor.*;
import org.allaymc.server.entity.ai.route.finder.FlatAStarRouteFinder;
import org.allaymc.server.entity.ai.route.posevaluator.WalkingPosEvaluator;
import org.allaymc.server.entity.ai.sensor.NearestFeedingPlayerSensor;
import org.allaymc.server.entity.ai.sensor.NearestPlayerSensor;
import org.allaymc.server.entity.component.*;
import org.allaymc.server.entity.component.animal.*;
import org.allaymc.server.entity.component.humanlike.EntityHumanLikeBaseComponentImpl;
import org.allaymc.server.entity.component.humanlike.EntityHumanLikeContainerHolderComponentImpl;
import org.allaymc.server.entity.component.humanlike.EntityHumanPhysicsComponentImpl;
import org.allaymc.server.entity.component.item.*;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerContainerHolderComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerLivingComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerPhysicsComponentImpl;
import org.allaymc.server.entity.component.projectile.*;
import org.allaymc.server.entity.data.EntityId;
import org.allaymc.server.entity.impl.*;
import org.joml.Vector3i;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.server.entity.ai.evaluator.LogicHelper.all;
import static org.allaymc.server.entity.ai.evaluator.LogicHelper.any;

/**
 * {@code EntityTypeInitializer} defines the built-in entity types and their
 * component configurations. Each initialization method registers its assembled
 * type and assigns the corresponding {@code EntityTypes} reference; component
 * suppliers create per-entity state, while AI configurations delegate behavior
 * selection and route coordination to their behavior groups.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class EntityTypeInitializer {
    /**
     * Registers falling blocks with airborne initial physics and reduced
     * gravity and publishes their built-in type reference. Each builder
     * registers its type before assigning that type's global reference; a
     * later failure leaves preceding registrations and assignments in place.
     */
    public static void initFallingBlock() {
        EntityTypes.FALLING_BLOCK = AllayEntityType
                .builder(EntityFallingBlockImpl.class)
                .vanillaEntity(EntityId.FALLING_BLOCK)
                .addComponent(EntityFallingBlockBaseComponentImpl::new, EntityFallingBlockBaseComponentImpl.class)
                .addComponent(() -> new EntityPhysicsComponentImpl() {
                    {
                        // Start airborne so the first physics update does not treat
                        // a newly created falling block as already resting on support.
                        this.onGround = false;
                    }

                    /**
                     * Returns falling-block gravity of {@code 0.04} blocks per
                     * tick squared.
                     */
                    @Override
                    public double getGravity() {
                        return 0.04;
                    }

                    /**
                     * Returns {@code false} to keep falling blocks on their
                     * ordinary gravity path in liquids. The engine's separate
                     * liquid-flow phase remains available because this
                     * override disables buoyancy and drag rather than all
                     * liquid motion.
                     */
                    @Override
                    public boolean computeLiquidPhysics() {
                        // Keep falling blocks on their ordinary gravity path rather
                        // than applying item-like buoyancy when they enter a liquid.
                        return false;
                    }
                }, EntityPhysicsComponentImpl.class)
                .build();
    }

    /**
     * Registers dropped items with item-specific damage immunity and liquid
     * motion and publishes their built-in type reference. Each builder
     * registers its type before assigning that type's global reference; a
     * later failure leaves preceding registrations and assignments in place.
     */
    public static void initItem() {
        EntityTypes.ITEM = AllayEntityType
                .builder(EntityItemImpl.class)
                .vanillaEntity(EntityId.ITEM)
                .addComponent(EntityItemBaseComponentImpl::new, EntityItemBaseComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl() {
                        /**
                         * Returns whether ordinary living damage eligibility
                         * permits this item and its category is neither
                         * falling-block nor stalactite damage. The parent
                         * filter still checks other supported damage
                         * capabilities before these item-specific exclusions.
                         */
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

                        /**
                         * Returns {@code false} to exclude dropped items from
                         * fall damage handling.
                         */
                        @Override
                        public boolean hasFallDamage() {
                            return false;
                        }

                        /**
                         * Returns {@code false} to exclude dropped items from
                         * drowning damage handling.
                         */
                        @Override
                        public boolean hasDrowningDamage() {
                            return false;
                        }

                        /**
                         * Returns whether base fireproof handling or the
                         * currently carried item's fireproof tag applies. A
                         * missing item stack yields {@code false}, and
                         * replacing the retained stack changes the next tag
                         * lookup.
                         */
                        @Override
                        public boolean isFireproof() {
                            if (super.isFireproof()) {
                                return true;
                            }

                            var itemStack = ((EntityItem) thisEntity).getItemStack();
                            if (itemStack == null) {
                                return false;
                            }

                            var itemType = itemStack.getItemType();
                            return itemType.hasItemTag(ItemTags.FIREPROOF);
                        }

                        /**
                         * Returns {@code false} so dead dropped items request
                         * removal without corpse timing.
                         */
                        @Override
                        protected boolean hasDeadTimer() {
                            return false;
                        }
                    };
                    component.setMaxHealth(5);
                    return component;
                }, EntityLivingComponentImpl.class)
                .addComponent(() -> new EntityPhysicsComponentImpl() {
                    /**
                     * Returns dropped-item gravity of {@code 0.04} blocks per
                     * tick squared.
                     */
                    @Override
                    public double getGravity() {
                        return 0.04;
                    }

                    /**
                     * Returns dropped-item water acceleration of
                     * {@code 0.0405} blocks per tick squared.
                     */
                    @Override
                    public double getWaterBuoyancy() {
                        return 0.0405;
                    }

                    /**
                     * Returns the dropped-item water velocity loss fraction of
                     * {@code 0.02}.
                     */
                    @Override
                    public double getWaterDragFactor() {
                        return 0.02;
                    }

                    /**
                     * Returns dropped-item lava acceleration of {@code 0.0405}
                     * blocks per tick squared.
                     */
                    @Override
                    public double getLavaBuoyancy() {
                        return 0.0405;
                    }

                    /**
                     * Returns the dropped-item lava velocity loss fraction of
                     * {@code 0.05}.
                     */
                    @Override
                    public double getLavaDragFactor() {
                        return 0.05;
                    }
                }, EntityPhysicsComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
    }

    /**
     * Registers players with specialized base, inventory, living, physics, and
     * sleep components and publishes their built-in type reference. Each
     * builder registers its type before assigning that type's global
     * reference; a later failure leaves preceding registrations and
     * assignments in place.
     */
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

    /**
     * Registers villagers with living state and human-sized entity physics and
     * publishes their built-in type reference. Each builder registers its type
     * before assigning that type's global reference; a later failure leaves
     * preceding registrations and assignments in place.
     */
    public static void initVillagerV2() {
        EntityTypes.VILLAGER_V2 = AllayEntityType
                .builder(EntityVillagerV2Impl.class)
                .vanillaEntity(EntityId.VILLAGER_V2)
                .addComponent(EntityLivingComponentImpl::new, EntityLivingComponentImpl.class)
                .addComponent(EntityHumanPhysicsComponentImpl::new, EntityHumanPhysicsComponentImpl.class)
                .build();
    }

    /**
     * Registers zombies with undead combustion, retaliation, and
     * priority-based melee AI and publishes their built-in type reference.
     * Each builder registers its type before assigning that type's global
     * reference; a later failure leaves preceding registrations and
     * assignments in place.
     */
    public static void initZombie() {
        EntityTypes.ZOMBIE = AllayEntityType
                .builder(EntityZombieImpl.class)
                .vanillaEntity(EntityId.ZOMBIE)
                .addComponent(EntityHumanLikeBaseComponentImpl::new, EntityHumanLikeBaseComponentImpl.class)
                .addComponent(EntityHumanLikeContainerHolderComponentImpl::new, EntityHumanLikeContainerHolderComponentImpl.class)
                .addComponent(EntityBabyComponentImpl::new, EntityBabyComponentImpl.class)
                .addComponent(EntityUndeadComponentImpl::new, EntityUndeadComponentImpl.class)
                .addComponent(EntityZombieLivingComponentImpl::new, EntityZombieLivingComponentImpl.class)
                .addComponent(EntityHumanPhysicsComponentImpl::new, EntityHumanPhysicsComponentImpl.class)
                .addComponent(EntityHeadYawComponentImpl::new, EntityHeadYawComponentImpl.class)
                .addComponent(EntityParallelTickComponentImpl::new, EntityParallelTickComponentImpl.class)
                .addComponent(() -> {
                    var behaviorGroup = BehaviorGroupImpl.builder()
                            .sensor(new NearestPlayerSensor(40, 0, 20))
                            .behavior(BehaviorImpl.builder()
                                    .executor(new MeleeAttackExecutor(MemoryTypes.ATTACK_TARGET, 0.1f, 40, true, 30, Math.sqrt(2.5), true))
                                    .evaluator(all(
                                            new MemoryCheckNotEmptyEvaluator(MemoryTypes.ATTACK_TARGET),
                                            entity -> isValidZombieTarget(entity, entity.getMemoryStorage().get(MemoryTypes.ATTACK_TARGET))
                                    ))
                                    .priority(3)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new MeleeAttackExecutor(MemoryTypes.NEAREST_PLAYER, 0.1f, 40, false,30, Math.sqrt(2.5), true))
                                    .evaluator(all(
                                            new MemoryCheckNotEmptyEvaluator(MemoryTypes.NEAREST_PLAYER),
                                            entity -> isValidZombieTarget(entity, entity.getMemoryStorage().get(MemoryTypes.NEAREST_PLAYER))
                                    ))
                                    .priority(2)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.1f, 12, 100, false, -1, true, 10))
                                    .evaluator(entity -> true)
                                    .priority(1)
                                    .build())
                            .controller(new WalkController())
                            .controller(new FluctuateController())
                            .controller(new LookController(true, true))
                            .routeFinder(new FlatAStarRouteFinder(new WalkingPosEvaluator()))
                            .build();

                    return new EntityAIComponentImpl(behaviorGroup);
                }, EntityAIComponentImpl.class)
                .build();
    }

    /**
     * Registers experience orbs with liquid motion and no fall damage and
     * publishes their built-in type reference. Each builder registers its type
     * before assigning that type's global reference; a later failure leaves
     * preceding registrations and assignments in place.
     */
    public static void initXBOrb() {
        EntityTypes.XP_ORB = AllayEntityType
                .builder(EntityXpOrbImpl.class)
                .vanillaEntity(EntityId.XP_ORB)
                .addComponent(EntityXpOrbBaseComponentImpl::new, EntityXpOrbBaseComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl() {
                        /**
                         * Returns {@code false} to exclude experience orbs
                         * from fall damage handling.
                         */
                        @Override
                        public boolean hasFallDamage() {
                            return false;
                        }
                    };
                    component.setMaxHealth(5);
                    return component;
                }, EntityLivingComponentImpl.class)
                .addComponent(() -> new EntityPhysicsComponentImpl() {
                    /**
                     * Returns experience-orb gravity of {@code 0.03} blocks
                     * per tick squared.
                     */
                    @Override
                    public double getGravity() {
                        return 0.03;
                    }

                    /**
                     * Returns experience-orb water acceleration of
                     * {@code 0.0405} blocks per tick squared.
                     */
                    @Override
                    public double getWaterBuoyancy() {
                        return 0.0405;
                    }

                    /**
                     * Returns the experience-orb water velocity loss fraction
                     * of {@code 0.02}.
                     */
                    @Override
                    public double getWaterDragFactor() {
                        return 0.02;
                    }

                    /**
                     * Returns experience-orb lava acceleration of
                     * {@code 0.0405} blocks per tick squared.
                     */
                    @Override
                    public double getLavaBuoyancy() {
                        return 0.0405;
                    }

                    /**
                     * Returns the experience-orb lava velocity loss fraction
                     * of {@code 0.05}.
                     */
                    @Override
                    public double getLavaDragFactor() {
                        return 0.05;
                    }
                }, EntityPhysicsComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
    }

    /**
     * Registers primed TNT with its fuse behavior and specialized physics and
     * publishes their built-in type reference. Each builder registers its type
     * before assigning that type's global reference; a later failure leaves
     * preceding registrations and assignments in place.
     */
    public static void initTnt() {
        EntityTypes.TNT = AllayEntityType
                .builder(EntityTntImpl.class)
                .vanillaEntity(EntityId.TNT)
                .addComponent(EntityTntBaseComponentImpl::new, EntityTntBaseComponentImpl.class)
                .addComponent(EntityTntPhysicsComponentImpl::new, EntityTntPhysicsComponentImpl.class)
                .build();
    }

    /**
     * Registers lightning bolts with transient strike effects and visual
     * lifetime and publishes their built-in type reference. Each builder
     * registers its type before assigning that type's global reference; a
     * later failure leaves preceding registrations and assignments in place.
     */
    public static void initLightningBolt() {
        EntityTypes.LIGHTNING_BOLT = AllayEntityType
                .builder(EntityLightningBoltImpl.class)
                .vanillaEntity(EntityId.LIGHTNING_BOLT)
                .addComponent(EntityLightningBoltBaseComponentImpl::new, EntityLightningBoltBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers ender crystals with crystal behavior and restricted damage
     * handling and publishes their built-in type reference. Each builder
     * registers its type before assigning that type's global reference; a
     * later failure leaves preceding registrations and assignments in place.
     */
    public static void initEnderCrystal() {
        EntityTypes.ENDER_CRYSTAL = AllayEntityType
                .builder(EntityEnderCrystalImpl.class)
                .vanillaEntity(EntityId.ENDER_CRYSTAL)
                .addComponent(EntityEnderCrystalBaseComponentImpl::new, EntityEnderCrystalBaseComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl() {
                        /**
                         * Returns {@code false} for an ender dragon attacker
                         * before checking ordinary living damage eligibility.
                         * Other attackers use the parent category filter,
                         * while the outer attack pipeline still checks
                         * lifecycle and cooldown.
                         */
                        @Override
                        public boolean canBeAttacked(DamageContainer damage) {
                            if (damage.getAttacker() instanceof EntityEnderDragon) {
                                return false;
                            }

                            return super.canBeAttacked(damage);
                        }

                        /**
                         * Returns {@code false} to exclude ender crystals from
                         * ordinary fire damage categories.
                         */
                        @Override
                        public boolean hasFireDamage() {
                            return false;
                        }

                        /**
                         * Returns {@code false} to exclude ender crystals from
                         * drowning damage handling.
                         */
                        @Override
                        public boolean hasDrowningDamage() {
                            return false;
                        }

                        /**
                         * Returns {@code true} so ender crystals reject
                         * fire-duration assignment requests.
                         */
                        @Override
                        public boolean isFireproof() {
                            return true;
                        }

                        /**
                         * Returns {@code false} so dead ender crystals request
                         * removal without corpse timing.
                         */
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

    /**
     * Registers snowballs, potion projectiles, pearls, experience bottles, and
     * arrows and publishes their built-in type reference. Each builder
     * registers its type before assigning that type's global reference; a
     * later failure leaves preceding registrations and assignments in place.
     */
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
                        /**
                         * Returns whether the supplied category is exactly
                         * {@code DamageType.API}, without invoking the parent
                         * category filter. The outer living attack pipeline
                         * still checks alive state, cooldown, and the
                         * cancellable damage event.
                         */
                        @Override
                        public boolean canBeAttacked(DamageContainer damage) {
                            return damage.getDamageType() == DamageType.API;
                        }

                        /**
                         * Returns {@code false} to exclude arrows from
                         * ordinary fall damage handling.
                         */
                        @Override
                        public boolean hasFallDamage() {
                            return false;
                        }

                        /**
                         * Returns {@code false} to exclude arrows from
                         * ordinary fire damage categories.
                         */
                        @Override
                        public boolean hasFireDamage() {
                            return false;
                        }

                        /**
                         * Returns {@code false} to exclude arrows from
                         * ordinary drowning damage handling.
                         */
                        @Override
                        public boolean hasDrowningDamage() {
                            return false;
                        }

                        /**
                         * Returns {@code false} so dead arrows request removal
                         * without corpse timing.
                         */
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

    /**
     * Registers paintings with motif-aware base behavior and living damage
     * handling and publishes their built-in type reference. Each builder
     * registers its type before assigning that type's global reference; a
     * later failure leaves preceding registrations and assignments in place.
     */
    public static void initPainting() {
        EntityTypes.PAINTING = AllayEntityType
                .builder(EntityPaintingImpl.class)
                .vanillaEntity(EntityId.PAINTING)
                .addComponent(EntityPaintingBaseComponentImpl::new, EntityPaintingBaseComponentImpl.class)
                .addComponent(EntityLivingComponentImpl::new, EntityLivingComponentImpl.class)
                .build();
    }

    /**
     * Registers firework rockets with rocket behavior and specialized motion
     * and publishes their built-in type reference. Each builder registers its
     * type before assigning that type's global reference; a later failure
     * leaves preceding registrations and assignments in place.
     */
    public static void initFireworkRocket() {
        EntityTypes.FIREWORKS_ROCKET = AllayEntityType
                .builder(EntityFireworksRocketImpl.class)
                .vanillaEntity(EntityId.FIREWORKS_ROCKET)
                .addComponent(EntityFireworksRocketPhysicsComponentImpl::new, EntityFireworksRocketPhysicsComponentImpl.class)
                .addComponent(EntityFireworksRocketBaseComponentImpl::new, EntityFireworksRocketBaseComponentImpl.class)
                .build();
    }

    /**
     * Registers area effect clouds with radius behavior and potion contents
     * and publishes their built-in type reference. Each builder registers its
     * type before assigning that type's global reference; a later failure
     * leaves preceding registrations and assignments in place.
     */
    public static void initAreaEffectCloud() {
        EntityTypes.AREA_EFFECT_CLOUD = AllayEntityType
                .builder(EntityAreaEffectCloudImpl.class)
                .vanillaEntity(EntityId.AREA_EFFECT_CLOUD)
                .addComponent(EntityAreaEffectCloudBaseComponentImpl::new, EntityAreaEffectCloudBaseComponentImpl.class)
                .addComponent(EntityPotionComponentImpl::new, EntityPotionComponentImpl.class)
                .build();
    }

    /**
     * Registers player and breeze wind charges with their distinct collision
     * handlers and publishes their built-in type reference. Each builder
     * registers its type before assigning that type's global reference; a
     * later failure leaves preceding registrations and assignments in place.
     */
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

    /**
     * Registers small and full fireballs with their specialized impact
     * behavior and publishes their built-in type reference. Each builder
     * registers its type before assigning that type's global reference; a
     * later failure leaves preceding registrations and assignments in place.
     */
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

    /**
     * Registers thrown tridents with an effectively extended age limit and
     * publishes their built-in type reference. Each builder registers its type
     * before assigning that type's global reference; a later failure leaves
     * preceding registrations and assignments in place.
     */
    public static void initThrownTrident() {
        EntityTypes.THROWN_TRIDENT = AllayEntityType
                .builder(EntityThrownTridentImpl.class)
                .vanillaEntity(EntityId.THROWN_TRIDENT)
                .addComponent(EntityThrownTridentBaseComponentImpl::new, EntityThrownTridentBaseComponentImpl.class)
                .addComponent(EntityThrownTridentPhysicsComponentImpl::new, EntityThrownTridentPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                // Extend the age limit to the largest integer tick count; this
                // avoids the ordinary short projectile lifetime without disabling age tracking.
                .addComponent(() -> new EntityAgeComponentImpl(Integer.MAX_VALUE), EntityAgeComponentImpl.class)
                .build();
    }

    /**
     * Registers thrown eggs with the shared climate-variant property and
     * publishes their built-in type reference. Each builder registers its type
     * before assigning that type's global reference; a later failure leaves
     * preceding registrations and assignments in place.
     */
    public static void initEgg() {
        EntityTypes.EGG = AllayEntityType
                .builder(EntityEggImpl.class)
                .vanillaEntity(EntityId.EGG)
                .setProperties(EntityPropertyTypes.CLIMATE_VARIANT)
                .addComponent(EntityProjectileBaseComponentImpl::new, EntityProjectileBaseComponentImpl.class)
                .addComponent(EntityEggPhysicsComponentImpl::new, EntityEggPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .build();
    }

    /**
     * Registers pigs with climate appearance, breeding foods, and animal AI
     * and publishes their built-in type reference. Each builder registers its
     * type before assigning that type's global reference; a later failure
     * leaves preceding registrations and assignments in place.
     */
    public static void initPig() {
        EntityTypes.PIG = AllayEntityType
                .builder(EntityPigImpl.class)
                .vanillaEntity(EntityId.PIG)
                .setProperties(EntityPropertyTypes.CLIMATE_VARIANT)
                .addComponent(EntityPigBaseComponentImpl::new, EntityPigBaseComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl();
                    component.setMaxHealth(10);
                    return component;
                }, EntityLivingComponentImpl.class)
                .addComponent(EntityAnimalPhysicsComponentImpl::new, EntityAnimalPhysicsComponentImpl.class)
                .addComponent(() -> new EntityAnimalComponentImpl(item ->
                        item.getItemType() == ItemTypes.CARROT
                        || item.getItemType() == ItemTypes.POTATO
                        || item.getItemType() == ItemTypes.BEETROOT
                ), EntityAnimalComponentImpl.class)
                .addComponent(EntityBabyComponentImpl::new, EntityBabyComponentImpl.class)
                .addComponent(EntityHeadYawComponentImpl::new, EntityHeadYawComponentImpl.class)
                .addComponent(EntityParallelTickComponentImpl::new, EntityParallelTickComponentImpl.class)
                .addComponent(() -> {
                    var behaviorGroup = BehaviorGroupImpl.builder()
                            .sensor(new NearestFeedingPlayerSensor(8))
                            .sensor(new NearestPlayerSensor(8, 0, 20))
                            .coreBehavior(BehaviorImpl.builder()
                                    .executor(new InLoveExecutor(400))
                                    .evaluator(all(
                                            entity -> !entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE),
                                            entity -> {
                                                var lastLoveTime = entity.getMemoryStorage().get(MemoryTypes.LAST_IN_LOVE_TIME);
                                                // An unobserved love timestamp allows the first breeding cycle;
                                                // later cycles require a five-minute cooldown measured in ticks.
                                                return lastLoveTime == null || lastLoveTime <= 0 || entity.getTick() - lastLoveTime >= 6000;
                                            },
                                            new PassByTimeEvaluator(MemoryTypes.LAST_BE_FEED_TIME, 0, 400)
                                    ))
                                    .priority(1)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.2875f, 12, 40, true, 100, true, 10))
                                    .evaluator(new PassByTimeEvaluator(EntityIntelligent::getLastDamageTime, 0, 100))
                                    .priority(6)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new EntityBreedingExecutor(100, 0.23f))
                                    .evaluator(entity -> entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE))
                                    .priority(5)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FollowEntityExecutor(MemoryTypes.NEAREST_FEEDING_PLAYER, 0.253f, 64, 2.25))
                                    .evaluator(new MemoryCheckNotEmptyEvaluator(MemoryTypes.NEAREST_FEEDING_PLAYER))
                                    .priority(4)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new LookAtEntityExecutor(MemoryTypes.NEAREST_PLAYER, 100))
                                    .evaluator(all(
                                            new MemoryCheckNotEmptyEvaluator(MemoryTypes.NEAREST_PLAYER),
                                            new ProbabilityEvaluator(2, 5)
                                    ))
                                    .priority(2)
                                    .period(100)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.1f, 12, 100, false, -1, true, 10))
                                    .evaluator(entity -> true)
                                    .priority(1)
                                    .build())
                            .controller(new WalkController())
                            .controller(new LookController(true, true))
                            .controller(new FluctuateController())
                            .routeFinder(new FlatAStarRouteFinder(new WalkingPosEvaluator()))
                            .build();

                    return new EntityAIComponentImpl(behaviorGroup);
                }, EntityAIComponentImpl.class)
                .build();
    }

    /**
     * Registers cows with climate appearance, wheat breeding, and animal AI
     * and publishes their built-in type reference. Each builder registers its
     * type before assigning that type's global reference; a later failure
     * leaves preceding registrations and assignments in place.
     */
    public static void initCow() {
        EntityTypes.COW = AllayEntityType
                .builder(EntityCowImpl.class)
                .vanillaEntity(EntityId.COW)
                .setProperties(EntityPropertyTypes.CLIMATE_VARIANT)
                .addComponent(EntityCowBaseComponentImpl::new, EntityCowBaseComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl();
                    component.setMaxHealth(10);
                    return component;
                }, EntityLivingComponentImpl.class)
                .addComponent(EntityAnimalPhysicsComponentImpl::new, EntityAnimalPhysicsComponentImpl.class)
                .addComponent(() -> new EntityAnimalComponentImpl(item -> item.getItemType() == ItemTypes.WHEAT), EntityAnimalComponentImpl.class)
                .addComponent(EntityBabyComponentImpl::new, EntityBabyComponentImpl.class)
                .addComponent(EntityHeadYawComponentImpl::new, EntityHeadYawComponentImpl.class)
                .addComponent(EntityParallelTickComponentImpl::new, EntityParallelTickComponentImpl.class)
                .addComponent(() -> {
                    var behaviorGroup = BehaviorGroupImpl.builder()
                            .sensor(new NearestFeedingPlayerSensor(8))
                            .sensor(new NearestPlayerSensor(8, 0, 20))
                            .coreBehavior(BehaviorImpl.builder()
                                    .executor(new InLoveExecutor(400))
                                    .evaluator(all(
                                            entity -> !entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE),
                                            entity -> {
                                                var lastLoveTime = entity.getMemoryStorage().get(MemoryTypes.LAST_IN_LOVE_TIME);
                                                // An unobserved love timestamp allows the first breeding cycle;
                                                // later cycles require a five-minute cooldown measured in ticks.
                                                return lastLoveTime == null || lastLoveTime <= 0 || entity.getTick() - lastLoveTime >= 6000;
                                            },
                                            new PassByTimeEvaluator(MemoryTypes.LAST_BE_FEED_TIME, 0, 400)
                                    ))
                                    .priority(1)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.2875f, 12, 40, true, 100, true, 10))
                                    .evaluator(new PassByTimeEvaluator(EntityIntelligent::getLastDamageTime, 0, 100))
                                    .priority(6)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new EntityBreedingExecutor(100, 0.23f))
                                    .evaluator(entity -> entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE))
                                    .priority(5)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FollowEntityExecutor(MemoryTypes.NEAREST_FEEDING_PLAYER, 0.253f, 64, 2.25))
                                    .evaluator(new MemoryCheckNotEmptyEvaluator(MemoryTypes.NEAREST_FEEDING_PLAYER))
                                    .priority(4)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new LookAtEntityExecutor(MemoryTypes.NEAREST_PLAYER, 100))
                                    .evaluator(all(
                                            new MemoryCheckNotEmptyEvaluator(MemoryTypes.NEAREST_PLAYER),
                                            new ProbabilityEvaluator(2, 5)
                                    ))
                                    .priority(2)
                                    .period(100)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.1f, 12, 100, false, -1, true, 10))
                                    .evaluator(entity -> true)
                                    .priority(1)
                                    .build())
                            .controller(new WalkController())
                            .controller(new LookController(true, true))
                            .controller(new FluctuateController())
                            .routeFinder(new FlatAStarRouteFinder(new WalkingPosEvaluator()))
                            .build();

                    return new EntityAIComponentImpl(behaviorGroup);
                }, EntityAIComponentImpl.class)
                .build();
    }

    /**
     * Registers chickens with climate appearance, seed breeding, and
     * egg-laying AI and publishes their built-in type reference. Each builder
     * registers its type before assigning that type's global reference; a
     * later failure leaves preceding registrations and assignments in place.
     */
    public static void initChicken() {
        EntityTypes.CHICKEN = AllayEntityType
                .builder(EntityChickenImpl.class)
                .vanillaEntity(EntityId.CHICKEN)
                .setProperties(EntityPropertyTypes.CLIMATE_VARIANT)
                .addComponent(EntityChickenBaseComponentImpl::new, EntityChickenBaseComponentImpl.class)
                .addComponent(() -> {
                    var component = new EntityLivingComponentImpl() {
                        /**
                         * Returns {@code false} to exclude chickens from
                         * ordinary fall damage handling.
                         */
                        @Override
                        public boolean hasFallDamage() {
                            return false;
                        }
                    };
                    component.setMaxHealth(4);
                    return component;
                }, EntityLivingComponentImpl.class)
                .addComponent(EntityChickenPhysicsComponentImpl::new, EntityChickenPhysicsComponentImpl.class)
                .addComponent(() -> new EntityAnimalComponentImpl(item ->
                        item.getItemType() == ItemTypes.WHEAT_SEEDS ||
                        item.getItemType() == ItemTypes.MELON_SEEDS ||
                        item.getItemType() == ItemTypes.PUMPKIN_SEEDS ||
                        item.getItemType() == ItemTypes.BEETROOT_SEEDS ||
                        item.getItemType() == ItemTypes.TORCHFLOWER_SEEDS ||
                        item.getItemType() == ItemTypes.PITCHER_POD
                ), EntityAnimalComponentImpl.class)
                .addComponent(EntityBabyComponentImpl::new, EntityBabyComponentImpl.class)
                .addComponent(EntityHeadYawComponentImpl::new, EntityHeadYawComponentImpl.class)
                .addComponent(EntityParallelTickComponentImpl::new, EntityParallelTickComponentImpl.class)
                .addComponent(() -> {
                    var behaviorGroup = BehaviorGroupImpl.builder()
                            .sensor(new NearestFeedingPlayerSensor(8))
                            .sensor(new NearestPlayerSensor(8, 0, 20))
                            .coreBehavior(BehaviorImpl.builder()
                                    .executor(new InLoveExecutor(400))
                                    .evaluator(all(
                                            entity -> !entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE),
                                            entity -> {
                                                var lastLoveTime = entity.getMemoryStorage().get(MemoryTypes.LAST_IN_LOVE_TIME);
                                                // An unobserved love timestamp allows the first breeding cycle;
                                                // later cycles require a five-minute cooldown measured in ticks.
                                                return lastLoveTime == null || lastLoveTime <= 0 || entity.getTick() - lastLoveTime >= 6000;
                                            },
                                            new PassByTimeEvaluator(MemoryTypes.LAST_BE_FEED_TIME, 0, 400)
                                    ))
                                    .priority(1)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    // From five through ten elapsed minutes, a probability gate may
                                    // permit laying; at ten minutes the upper-window test needs no roll.
                                    .executor(entity -> {
                                        entity.getMemoryStorage().put(MemoryTypes.LAST_EGG_SPAWN_TIME, entity.getTick());
                                        entity.getDimension().dropItem(ItemTypes.EGG.createItemStack(1), entity.getLocation());
                                        entity.getDimension().addSound(entity.getLocation(), SimpleSound.EGG_LAY);
                                        return false;
                                    })
                                    .evaluator(all(
                                            entity -> !(entity instanceof EntityBabyComponent babyComponent && babyComponent.isBaby()),
                                            any(
                                                    all(
                                                            new PassByTimeEvaluator(MemoryTypes.LAST_EGG_SPAWN_TIME, 6000, 12000),
                                                            new ProbabilityEvaluator(20, 100)
                                                    ),
                                                    new PassByTimeEvaluator(MemoryTypes.LAST_EGG_SPAWN_TIME, 12000, Integer.MAX_VALUE)
                                            )
                                    ))
                                    .priority(1)
                                    .period(20)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.2875f, 12, 40, true, 100, true, 10))
                                    .evaluator(new PassByTimeEvaluator(EntityIntelligent::getLastDamageTime, 0, 100))
                                    .priority(6)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new EntityBreedingExecutor(100, 0.15f))
                                    .evaluator(entity -> entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE))
                                    .priority(5)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FollowEntityExecutor(MemoryTypes.NEAREST_FEEDING_PLAYER, 0.253f, 64, 2.25))
                                    .evaluator(new MemoryCheckNotEmptyEvaluator(MemoryTypes.NEAREST_FEEDING_PLAYER))
                                    .priority(4)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new LookAtEntityExecutor(MemoryTypes.NEAREST_PLAYER, 100))
                                    .evaluator(all(
                                            new MemoryCheckNotEmptyEvaluator(MemoryTypes.NEAREST_PLAYER),
                                            new ProbabilityEvaluator(2, 5)
                                    ))
                                    .priority(2)
                                    .period(100)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.11f, 12, 100, false, -1, true, 10))
                                    .evaluator(entity -> true)
                                    .priority(1)
                                    .build())
                            .controller(new WalkController())
                            .controller(new LookController(true, true))
                            .controller(new FluctuateController())
                            .routeFinder(new FlatAStarRouteFinder(new WalkingPosEvaluator()))
                            .build();

                    return new EntityAIComponentImpl(behaviorGroup);
                }, EntityAIComponentImpl.class)
                .build();
    }

    /**
     * Registers fishing hooks with owner-aware projectile behavior and an age
     * limit and publishes their built-in type reference. Each builder
     * registers its type before assigning that type's global reference; a
     * later failure leaves preceding registrations and assignments in place.
     */
    public static void initFishingHook() {
        EntityTypes.FISHING_HOOK = AllayEntityType
                .builder(EntityFishingHookImpl.class)
                .vanillaEntity(EntityId.FISHING_HOOK)
                .addComponent(EntityFishingHookBaseComponentImpl::new, EntityFishingHookBaseComponentImpl.class)
                .addComponent(EntityFishingHookPhysicsComponentImpl::new, EntityFishingHookPhysicsComponentImpl.class)
                .addComponent(EntityProjectileComponentImpl::new, EntityProjectileComponentImpl.class)
                // Limit an unused hook to sixty seconds at twenty ticks per second.
                .addComponent(() -> new EntityAgeComponentImpl(1200), EntityAgeComponentImpl.class)
                .build();
    }

    /**
     * Registers armor stands with humanlike equipment and landing sounds and
     * publishes their built-in type reference. Each builder registers its type
     * before assigning that type's global reference; a later failure leaves
     * preceding registrations and assignments in place.
     */
    public static void initArmorStand() {
        EntityTypes.ARMOR_STAND = AllayEntityType
                .builder(EntityArmorStandImpl.class)
                .vanillaEntity(EntityId.ARMOR_STAND)
                .addComponent(EntityArmorStandBaseComponentImpl::new, EntityArmorStandBaseComponentImpl.class)
                .addComponent(EntityHumanLikeContainerHolderComponentImpl::new, EntityHumanLikeContainerHolderComponentImpl.class)
                .addComponent(EntityArmorStandLivingComponentImpl::new, EntityArmorStandLivingComponentImpl.class)
                .addComponent(() -> new EntityHumanPhysicsComponentImpl() {
                    /**
                     * Runs common landing handling before playing the
                     * armor-stand landing sound for falls of at least three
                     * blocks. A normally returned cancelled fall event still
                     * allows this sound because the wrapper does not inspect a
                     * cancellation result.
                     */
                    @Override
                    public void onFall(double fallDistance) {
                        super.onFall(fallDistance);
                        // Play the landing sound only for falls of at least three blocks.
                        if (fallDistance >= 3) {
                            thisEntity.getDimension().addSound(thisEntity.getLocation(), SimpleSound.ARMOR_STAND_LAND);
                        }
                    }
                }, EntityHumanPhysicsComponentImpl.class)
                .build();
    }

    /**
     * Registers sheep with dye colors, grazing, breeding, and animal AI and
     * publishes their built-in type reference. Each builder registers its type
     * before assigning that type's global reference; a later failure leaves
     * preceding registrations and assignments in place.
     */
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
                .addComponent(EntityParallelTickComponentImpl::new, EntityParallelTickComponentImpl.class)
                .addComponent(() -> new EntityDyeableComponentImpl(() -> {
                    var rand = ThreadLocalRandom.current();
                    int roll = rand.nextInt(100);
                    // Partition one hundred equiprobable rolls into the configured
                    // wool-color frequencies; pink receives the final remaining roll.
                    if (roll < 82) return DyeColor.WHITE;
                    if (roll < 87) return DyeColor.BLACK;
                    if (roll < 92) return DyeColor.GRAY;
                    if (roll < 97) return DyeColor.LIGHT_GRAY;
                    if (roll < 99) return DyeColor.BROWN;
                    return DyeColor.PINK;
                }), EntityDyeableComponentImpl.class)
                .addComponent(() -> {
                    var behaviorGroup = BehaviorGroupImpl.builder()
                            .sensor(new NearestFeedingPlayerSensor(8))
                            .sensor(new NearestPlayerSensor(8, 0, 20))
                            .coreBehavior(BehaviorImpl.builder()
                                    .executor(new InLoveExecutor(400))
                                    .evaluator(all(
                                            entity -> !entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE),
                                            entity -> {
                                                var lastLoveTime = entity.getMemoryStorage().get(MemoryTypes.LAST_IN_LOVE_TIME);
                                                // An unobserved love timestamp allows the first breeding cycle;
                                                // later cycles require a five-minute cooldown measured in ticks.
                                                return lastLoveTime == null || lastLoveTime <= 0 || entity.getTick() - lastLoveTime >= 6000;
                                            },
                                            new PassByTimeEvaluator(MemoryTypes.LAST_BE_FEED_TIME, 0, 400)
                                    ))
                                    .priority(1)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.2875f, 12, 40, true, 100, true, 10))
                                    .evaluator(new PassByTimeEvaluator(EntityIntelligent::getLastDamageTime, 0, 100))
                                    .priority(6)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new EntityBreedingExecutor(100, 0.23f))
                                    .evaluator(entity -> entity.getMemoryStorage().get(MemoryTypes.IS_IN_LOVE))
                                    .priority(5)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FollowEntityExecutor(MemoryTypes.NEAREST_FEEDING_PLAYER, 0.253f, 64, 2.25))
                                    .evaluator(new MemoryCheckNotEmptyEvaluator(MemoryTypes.NEAREST_FEEDING_PLAYER))
                                    .priority(4)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new SheepEatGrassExecutor())
                                    .evaluator(all(
                                            any(
                                                    all(
                                                            entity -> entity instanceof EntityAnimal animal && animal.isBaby(),
                                                            new ProbabilityEvaluator(86, 100)
                                                    ),
                                                    all(
                                                            entity -> !(entity instanceof EntityAnimal animal && animal.isBaby()),
                                                            new ProbabilityEvaluator(1, 100)
                                                    )
                                            ),
                                            any(
                                                    new BlockCheckEvaluator(BlockTypes.SHORT_GRASS, new Vector3i(0, 0, 0)),
                                                    new BlockCheckEvaluator(BlockTypes.GRASS_BLOCK, new Vector3i(0, -1, 0))
                                            )
                                    ))
                                    .priority(3)
                                    .period(100)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new LookAtEntityExecutor(MemoryTypes.NEAREST_PLAYER, 100))
                                    .evaluator(all(
                                            new MemoryCheckNotEmptyEvaluator(MemoryTypes.NEAREST_PLAYER),
                                            new ProbabilityEvaluator(2, 5)
                                    ))
                                    .priority(2)
                                    .period(100)
                                    .build())
                            .behavior(BehaviorImpl.builder()
                                    .executor(new FlatRandomRoamExecutor(0.1f, 12, 100, false, -1, true, 10))
                                    .evaluator(entity -> true)
                                    .priority(1)
                                    .build())
                            .controller(new WalkController())
                            .controller(new LookController(true, true))
                            .controller(new FluctuateController())
                            .routeFinder(new FlatAStarRouteFinder(new WalkingPosEvaluator()))
                            .build();

                    return new EntityAIComponentImpl(behaviorGroup);
                }, EntityAIComponentImpl.class)
                .build();
    }

    /**
     * Returns whether a runtime identifier resolves to an alive, distinct
     * living target in the zombie's dimension. Player targets additionally
     * require survival or adventure mode; creative and spectator players are
     * excluded from zombie target selection.
     */
    private static boolean isValidZombieTarget(EntityIntelligent entity, long targetId) {
        var target = entity.getDimension().getEntityManager().getEntity(targetId);
        if (!(target instanceof EntityLivingComponent) || target == entity || !target.isAlive()) {
            return false;
        }

        if (target instanceof EntityPlayer player) {
            return switch (player.getGameMode()) {
                case SURVIVAL, ADVENTURE -> true;
                case CREATIVE, SPECTATOR -> false;
            };
        }

        return true;
    }
}
