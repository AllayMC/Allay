package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.server.entity.component.common.EntityAttributeComponentImpl;
import org.allaymc.server.entity.component.common.EntityDamageComponentImpl;
import org.allaymc.server.entity.component.player.*;

import static org.allaymc.api.entity.component.common.EntityAttributeComponent.basicPlayerAttributes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPlayerInitializer {
  static void init() {
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
}
