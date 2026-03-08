package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityPotionComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;

/**
 * @author daoge_cmd
 */
public class EntityPotionComponentImpl implements EntityPotionComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_potion_component");

    protected static final String TAG_POTION_ID = "PotionId";

    @Dependency
    protected EntityBaseComponent baseComponent;

    @Getter
    protected PotionType potionType;

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForShort(TAG_POTION_ID, id -> this.potionType = PotionType.fromId(id));
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        if (potionType != null) {
            event.getNbt().putShort(TAG_POTION_ID, (short) potionType.ordinal());
        }
    }

    @Override
    public void setPotionType(PotionType potionType) {
        this.potionType = potionType;
        this.baseComponent.broadcastState();
    }
}
