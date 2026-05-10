package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.component.EntityDyeableComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class EntityDyeableComponentImpl implements EntityDyeableComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_dyeable_component");

    protected static final String TAG_COLOR = "Color";

    @ComponentObject
    protected Entity thisEntity;
    @Dependency
    protected EntityBaseComponent baseComponent;

    protected final Supplier<DyeColor> defaultColorSupplier;

    @Getter
    protected DyeColor color;

    public EntityDyeableComponentImpl(Supplier<DyeColor> defaultColorSupplier) {
        this.defaultColorSupplier = defaultColorSupplier;
        this.color = defaultColorSupplier.get();
    }

    @Override
    public void setColor(DyeColor color) {
        this.color = color;
        baseComponent.broadcastState();
    }

    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().putByte(TAG_COLOR, (byte) color.ordinal());
    }

    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForByte(TAG_COLOR, b -> this.color = DyeColor.from(b));
    }
}
