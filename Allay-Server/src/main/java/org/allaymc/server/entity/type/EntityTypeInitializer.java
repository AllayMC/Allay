package org.allaymc.server.entity.type;

import lombok.experimental.UtilityClass;
import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityVillagerV2;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.server.entity.component.common.EntityAttributeComponentImpl;
import org.allaymc.server.entity.component.common.EntityDamageComponentImpl;
import org.allaymc.server.entity.component.item.EntityItemBaseComponentImpl;
import org.allaymc.server.entity.component.player.*;

import static org.allaymc.api.entity.component.common.EntityAttributeComponent.basicEntityAttributes;
import static org.allaymc.api.entity.component.common.EntityAttributeComponent.basicPlayerAttributes;

/**
 * Allay Project 2024/3/9
 *
 * @author daoge_cmd
 */
@SuppressWarnings("unused")
@UtilityClass
public final class EntityTypeInitializer {
    public static void initItem() {
        EntityTypes.ITEM_TYPE = EntityTypeBuilder
                .builder(EntityItem.class)
                .vanillaEntity(VanillaEntityId.ITEM)
                .addComponent(EntityItemBaseComponentImpl::new, EntityItemBaseComponentImpl.class)
                .build();
    }

    public static void initPlayer() {
        EntityTypes.PLAYER_TYPE = EntityTypeBuilder
                .builder(EntityPlayer.class)
                .vanillaEntity(VanillaEntityId.PLAYER)
                .addComponent(EntityPlayerBaseComponentImpl::new, EntityPlayerBaseComponentImpl.class)
                .addComponent(EntityPlayerNetworkComponentImpl::new, EntityPlayerNetworkComponentImpl.class)
                .addComponent(() -> new EntityAttributeComponentImpl(basicPlayerAttributes()), EntityAttributeComponentImpl.class)
                .addComponent(EntityPlayerContainerHolderComponentImpl::new, EntityPlayerContainerHolderComponentImpl.class)
                .addComponent(EntityPlayerContainerViewerComponentImpl::new, EntityPlayerContainerViewerComponentImpl.class)
                .addComponent(EntityPlayerDamageComponentImpl::new, EntityDamageComponentImpl.class)
                .build();
    }

    public static void initVillagerV2() {
        EntityTypes.VILLAGER_V2_TYPE = EntityTypeBuilder
                .builder(EntityVillagerV2.class)
                .vanillaEntity(VanillaEntityId.VILLAGER_V2)
                .addComponent(() -> new EntityAttributeComponentImpl(basicEntityAttributes()), EntityAttributeComponentImpl.class)
                .addComponent(EntityDamageComponentImpl::new, EntityDamageComponentImpl.class)
                .build();
    }
}
