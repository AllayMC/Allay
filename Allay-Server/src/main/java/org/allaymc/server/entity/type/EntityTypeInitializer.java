package org.allaymc.server.entity.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.interfaces.*;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.server.entity.component.EntityFallingBlockBaseComponentImpl;
import org.allaymc.server.entity.component.EntityItemBaseComponentImpl;
import org.allaymc.server.entity.component.EntityXpOrbBaseComponentImpl;
import org.allaymc.server.entity.component.common.EntityAttributeComponentImpl;
import org.allaymc.server.entity.component.common.EntityDamageComponentImpl;
import org.allaymc.server.entity.component.player.*;

import static org.allaymc.api.entity.component.common.EntityAttributeComponent.basicEntityAttributes;

/**
 * Allay Project 2024/3/9
 *
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class EntityTypeInitializer {
    public static void initFallingBlock() {
        EntityTypes.FALLING_BLOCK = EntityTypeBuilder
                .builder(EntityFallingBlock.class)
                .vanillaEntity(VanillaEntityId.FALLING_BLOCK)
                .addComponent(EntityFallingBlockBaseComponentImpl::new, EntityFallingBlockBaseComponentImpl.class)
                .build();
    }

    public static void initItem() {
        EntityTypes.ITEM = EntityTypeBuilder
                .builder(EntityItem.class)
                .vanillaEntity(VanillaEntityId.ITEM)
                .addComponent(EntityItemBaseComponentImpl::new, EntityItemBaseComponentImpl.class)
                .addComponent(EntityDamageComponentImpl::new, EntityDamageComponentImpl.class)
                .addComponent(
                        () -> new EntityAttributeComponentImpl(
                                AttributeType.HEALTH.newAttributeInstance().setMaxValue(5).setCurrentValue(5)
                        ),
                        EntityAttributeComponentImpl.class
                )
                .build();
    }

    public static void initPlayer() {
        EntityTypes.PLAYER = EntityTypeBuilder
                .builder(EntityPlayer.class)
                .vanillaEntity(VanillaEntityId.PLAYER)
                .addComponent(EntityPlayerBaseComponentImpl::new, EntityPlayerBaseComponentImpl.class)
                .addComponent(EntityPlayerNetworkComponentImpl::new, EntityPlayerNetworkComponentImpl.class)
                .addComponent(() -> new EntityPlayerAttributeComponentImpl(EntityPlayerAttributeComponent.basicPlayerAttributes()), EntityPlayerAttributeComponentImpl.class)
                .addComponent(EntityPlayerContainerHolderComponentImpl::new, EntityPlayerContainerHolderComponentImpl.class)
                .addComponent(EntityPlayerContainerViewerComponentImpl::new, EntityPlayerContainerViewerComponentImpl.class)
                .addComponent(EntityPlayerDamageComponentImpl::new, EntityPlayerDamageComponentImpl.class)
                .build();
    }

    public static void initVillagerV2() {
        EntityTypes.VILLAGER_V2 = EntityTypeBuilder
                .builder(EntityVillagerV2.class)
                .vanillaEntity(VanillaEntityId.VILLAGER_V2)
                .addComponent(() -> new EntityAttributeComponentImpl(basicEntityAttributes()), EntityAttributeComponentImpl.class)
                .addComponent(EntityDamageComponentImpl::new, EntityDamageComponentImpl.class)
                .build();
    }

    public static void initXBOrb() {
        EntityTypes.XP_ORB = EntityTypeBuilder
                .builder(EntityXpOrb.class)
                .vanillaEntity(VanillaEntityId.XP_ORB)
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
