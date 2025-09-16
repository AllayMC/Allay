package org.allaymc.server.entity.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.server.entity.component.*;
import org.allaymc.server.entity.component.player.*;
import org.allaymc.server.entity.component.projectile.*;
import org.allaymc.server.entity.impl.*;

import static org.allaymc.server.entity.component.EntityAttributeComponentImpl.basicEntityAttributes;

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
                }, EntityPhysicsComponentImpl.class)
                .build();
    }

    public static void initItem() {
        EntityTypes.ITEM = AllayEntityType
                .builder(EntityItemImpl.class)
                .vanillaEntity(EntityId.ITEM)
                .addComponent(EntityItemBaseComponentImpl::new, EntityItemBaseComponentImpl.class)
                .addComponent(() -> new EntityDamageComponentImpl() {
                    @Override
                    public boolean hasFallDamage() {
                        return false;
                    }

                    @Override
                    public boolean hasDrowningDamage() {
                        return false;
                    }
                }, EntityDamageComponentImpl.class)
                .addComponent(
                        () -> new EntityAttributeComponentImpl(AttributeType.HEALTH.newAttributeInstance().setMaxValue(5).setCurrentValue(5)),
                        EntityAttributeComponentImpl.class
                )
                .addComponent(() -> new EntityPhysicsComponentImpl() {
                    @Override
                    public double getGravity() {
                        return 0.04;
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
                .addComponent(EntityPlayerNetworkComponentImpl::new, EntityPlayerNetworkComponentImpl.class)
                .addComponent(() -> new EntityPlayerAttributeComponentImpl(EntityPlayerAttributeComponentImpl.basicPlayerAttributes()), EntityPlayerAttributeComponentImpl.class)
                .addComponent(EntityPlayerContainerHolderComponentImpl::new, EntityPlayerContainerHolderComponentImpl.class)
                .addComponent(EntityPlayerContainerViewerComponentImpl::new, EntityPlayerContainerViewerComponentImpl.class)
                .addComponent(EntityPlayerDamageComponentImpl::new, EntityPlayerDamageComponentImpl.class)
                .addComponent(EntityBreatheComponentImpl::new, EntityBreatheComponentImpl.class)
                .addComponent(EntityPlayerPhysicsComponentImpl::new, EntityPlayerPhysicsComponentImpl.class)
                .addComponent(EntityPlayerScoreboardViewerComponentImpl::new, EntityPlayerScoreboardViewerComponentImpl.class)
                .addComponent(EntityPlayerChunkLoaderComponentImpl::new, EntityPlayerChunkLoaderComponentImpl.class)
                .addComponent(EntityPlayerBossBarViewerComponentImpl::new, EntityPlayerBossBarViewerComponentImpl.class)
                .build();
    }

    public static void initVillagerV2() {
        EntityTypes.VILLAGER_V2 = AllayEntityType
                .builder(EntityVillagerV2Impl.class)
                .vanillaEntity(EntityId.VILLAGER_V2)
                .addComponent(() -> new EntityAttributeComponentImpl(basicEntityAttributes()), EntityAttributeComponentImpl.class)
                .addComponent(EntityDamageComponentImpl::new, EntityDamageComponentImpl.class)
                .addComponent(EntityBreatheComponentImpl::new, EntityBreatheComponentImpl.class)
                .addComponent(EntityHumanPhysicsComponentImpl::new, EntityHumanPhysicsComponentImpl.class)
                .build();
    }

    public static void initXBOrb() {
        EntityTypes.XP_ORB = AllayEntityType
                .builder(EntityXpOrbImpl.class)
                .vanillaEntity(EntityId.XP_ORB)
                .addComponent(EntityXpOrbBaseComponentImpl::new, EntityXpOrbBaseComponentImpl.class)
                .addComponent(EntityDamageComponentImpl::new, EntityDamageComponentImpl.class)
                .addComponent(
                        () -> new EntityAttributeComponentImpl(AttributeType.HEALTH.newAttributeInstance().setMaxValue(5).setCurrentValue(5)),
                        EntityAttributeComponentImpl.class
                )
                .addComponent(() -> new EntityPhysicsComponentImpl() {
                    @Override
                    public double getGravity() {
                        return 0.04;
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
                .addComponent(EntitySplashPotionProjectileComponentImpl::new, EntitySplashPotionProjectileComponentImpl.class)
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
                .addComponent(() -> new EntityAgeComponentImpl(), EntityAgeComponentImpl.class)
                .addComponent(EntityArrowDamageComponentImpl::new, EntityArrowDamageComponentImpl.class)
                .addComponent(
                        () -> new EntityAttributeComponentImpl(AttributeType.HEALTH.newAttributeInstance().setMaxValue(5).setCurrentValue(5)),
                        EntityAttributeComponentImpl.class
                )
                .build();
    }
}
