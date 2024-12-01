package org.allaymc.server.entity.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.entity.component.attribute.AttributeType;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.server.entity.component.*;
import org.allaymc.server.entity.component.player.*;
import org.allaymc.server.entity.impl.*;

import static org.allaymc.api.entity.component.attribute.EntityAttributeComponent.basicEntityAttributes;

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
                }, EntityDamageComponentImpl.class)
                .addComponent(
                        () -> new EntityAttributeComponentImpl(

                                AttributeType.HEALTH.newAttributeInstance().setMaxValue(5).setCurrentValue(5)
                        ),
                        EntityAttributeComponentImpl.class
                )
                .build();
    }

    public static void initPlayer() {
        EntityTypes.PLAYER = AllayEntityType
                .builder(EntityPlayerImpl.class)
                .vanillaEntity(EntityId.PLAYER)
                .addComponent(EntityPlayerBaseComponentImpl::new, EntityPlayerBaseComponentImpl.class)
                .addComponent(EntityPlayerNetworkComponentImpl::new, EntityPlayerNetworkComponentImpl.class)
                .addComponent(() -> new EntityPlayerAttributeComponentImpl(EntityPlayerAttributeComponent.basicPlayerAttributes()), EntityPlayerAttributeComponentImpl.class)
                .addComponent(EntityPlayerContainerHolderComponentImpl::new, EntityPlayerContainerHolderComponentImpl.class)
                .addComponent(EntityPlayerContainerViewerComponentImpl::new, EntityPlayerContainerViewerComponentImpl.class)
                .addComponent(EntityPlayerDamageComponentImpl::new, EntityPlayerDamageComponentImpl.class)
                .build();
    }

    public static void initVillagerV2() {
        EntityTypes.VILLAGER_V2 = AllayEntityType
                .builder(EntityVillagerV2Impl.class)
                .vanillaEntity(EntityId.VILLAGER_V2)
                .addComponent(() -> new EntityAttributeComponentImpl(basicEntityAttributes()), EntityAttributeComponentImpl.class)
                .addComponent(EntityDamageComponentImpl::new, EntityDamageComponentImpl.class)
                .build();
    }

    public static void initXBOrb() {
        EntityTypes.XP_ORB = AllayEntityType
                .builder(EntityXpOrbImpl.class)
                .vanillaEntity(EntityId.XP_ORB)
                .addComponent(EntityXpOrbBaseComponentImpl::new, EntityXpOrbBaseComponentImpl.class)
                .addComponent(EntityDamageComponentImpl::new, EntityDamageComponentImpl.class)
                .addComponent(
                        () -> new EntityAttributeComponentImpl(
                                AttributeType.HEALTH.newAttributeInstance().setMaxValue(5).setCurrentValue(5)
                        ),
                        EntityAttributeComponentImpl.class
                )
                .build();
    }
}
