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
 * {@code EntityDyeableComponentImpl} stores and persists an entity's selected
 * dye color. The constructor obtains the initial color from its supplier,
 * {@code setColor} broadcasts later changes, and NBT event handlers encode the
 * enum ordinal into the entity's color byte.
 *
 * @author daoge_cmd
 */
public class EntityDyeableComponentImpl implements EntityDyeableComponent {

    /**
     * {@code IDENTIFIER} names this component implementation for provider
     * lookup and replacement when entity types assemble their component sets.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_dyeable_component");

    /**
     * {@code TAG_COLOR} identifies the NBT byte used by load and save handlers
     * to preserve the selected dye-color ordinal.
     */
    protected static final String TAG_COLOR = "Color";

    /**
     * Injected entity owning this dye-color component.
     */
    @ComponentObject
    protected Entity thisEntity;
    /**
     * Base component used to broadcast changed entity appearance.
     */
    @Dependency
    protected EntityBaseComponent baseComponent;

    /**
     * Supplier retained after providing the constructor's initial dye color.
     */
    protected final Supplier<DyeColor> defaultColorSupplier;

    /**
     * Selected dye color used by metadata and NBT persistence.
     */
    @Getter
    protected DyeColor color;

    /**
     * Creates dye state using the supplied supplier once for the initial
     * color. The supplier is retained, and it must return a usable color
     * because persistence later reads the stored color's ordinal.
     */
    public EntityDyeableComponentImpl(Supplier<DyeColor> defaultColorSupplier) {
        this.defaultColorSupplier = defaultColorSupplier;
        this.color = defaultColorSupplier.get();
    }

    /**
     * Stores the selected dye color before broadcasting updated entity
     * appearance. The value must remain usable for later ordinal
     * serialization, and a viewer failure leaves the newly assigned color
     * stored.
     */
    @Override
    public void setColor(DyeColor color) {
        this.color = color;
        baseComponent.broadcastState();
    }

    /**
     * Writes the selected dye color ordinal into the entity NBT byte.
     */
    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        // Dye colors use the enum ordinal as their stored byte, matching the
        // ordinal lookup used by onLoadNBT to recover the selected color.
        event.getNbt().putByte(TAG_COLOR, (byte) color.ordinal());
    }

    /**
     * Loads the dye color when the NBT color byte is present. Missing data
     * preserves the current color, including the supplied constructor default
     * on a new component; an out-of-range ordinal propagates the failure from
     * {@code DyeColor.from}.
     */
    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForByte(TAG_COLOR, b -> this.color = DyeColor.from(b));
    }
}
