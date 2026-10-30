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
 * {@code EntityBabyComponentImpl} stores an entity's baby flag and applies
 * explicit form changes to its scale and viewer state. NBT loading restores the
 * flag directly, while {@code setBaby} also sets half or full scale and
 * broadcasts the entity's updated state.
 *
 * @author daoge_cmd
 */
public class EntityBabyComponentImpl implements EntityBabyComponent {

    /**
     * {@code IDENTIFIER} selects this baby-state implementation when entity
     * type definitions compose their components under the namespaced component
     * key.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_baby_component");

    /**
     * {@code TAG_IS_BABY} names the boolean NBT entry shared by the load and
     * save handlers for the entity's baby-state flag.
     */
    protected static final String TAG_IS_BABY = "IsBaby";

    /**
     * Entity receiving this baby-state component.
     */
    @ComponentObject
    protected Entity thisEntity;
    /**
     * Base component applying scale and state broadcasts.
     */
    @Dependency
    protected EntityBaseComponent baseComponent;

    /**
     * {@code baby} records the current baby form and starts as {@code false}.
     * Loading assigns this flag directly, whereas {@code setBaby} also updates
     * scale and broadcasts state.
     */
    @Getter
    protected boolean baby;

    /**
     * Sets baby state, selects half or full scale, and broadcasts state. The
     * supplied value always triggers the scale update and broadcast, even when
     * the stored flag already matches the requested form.
     */
    @Override
    public void setBaby(boolean baby) {
        this.baby = baby;
        baseComponent.setScale(baby ? 0.5 : 1.0);
        baseComponent.broadcastState();
    }

    /**
     * Writes the current baby flag as a boolean NBT entry.
     */
    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().putBoolean(TAG_IS_BABY, baby);
    }

    /**
     * Restores a present boolean baby flag without changing scale or
     * broadcasting. The listener leaves the current flag unchanged when the
     * entry is absent or has another NBT type. This handler does not reconcile
     * the loaded flag with scale.
     */
    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForBoolean(TAG_IS_BABY, v -> this.baby = v);
    }
}
