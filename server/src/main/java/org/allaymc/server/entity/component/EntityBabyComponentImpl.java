package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityBabyComponent;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;

/**
 * @author daoge_cmd
 */
public class EntityBabyComponentImpl implements EntityBabyComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_baby_component");

    protected static final String TAG_IS_BABY = "IsBaby";

    @ComponentObject
    protected Entity thisEntity;
    @Dependency
    protected EntityBaseComponent baseComponent;

    @Getter
    protected boolean baby;

    @Override
    public void setBaby(boolean baby) {
        this.baby = baby;
        baseComponent.setScale(baby ? 0.5 : 1.0);
        baseComponent.broadcastState();
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().putBoolean(TAG_IS_BABY, baby);
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForBoolean(TAG_IS_BABY, v -> this.baby = v);
    }
}
