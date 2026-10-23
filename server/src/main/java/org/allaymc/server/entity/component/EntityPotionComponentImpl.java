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
 * {@code EntityPotionComponentImpl} retains optional potion contents and
 * connects them to entity persistence and state publication. Component events
 * read and write the numeric potion identifier, while explicit content changes
 * broadcast the owning entity state to viewers.
 *
 * @author daoge_cmd
 */
public class EntityPotionComponentImpl implements EntityPotionComponent {

    /**
     * {@code IDENTIFIER} selects this potion-content component during
     * component registration and dependency lookup by identifier.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_potion_component");

    /**
     * {@code TAG_POTION_ID} names the short-valued potion identifier exchanged
     * by entity load and save events.
     */
    protected static final String TAG_POTION_ID = "PotionId";

    /**
     * Injected owner state publication component.
     */
    @Dependency
    protected EntityBaseComponent baseComponent;

    /**
     * {@code potionType} stores optional potion contents. A {@code null} value
     * omits the potion identifier during saving and remains visible to effect
     * consumers.
     */
    @Getter
    protected PotionType potionType;

    /**
     * Reads a present potion identifier from the component load event. The
     * identifier indexes {@code PotionType} directly, so an out-of-range
     * stored value fails instead of selecting a default potion.
     */
    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForShort(TAG_POTION_ID, id -> this.potionType = PotionType.fromId(id));
    }

    /**
     * Writes the configured potion ordinal into the shared save builder. A
     * {@code null} potion leaves the builder without an added potion tag,
     * allowing entities without configured contents to save.
     */
    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        if (potionType != null) {
            event.getNbt().putShort(TAG_POTION_ID, (short) potionType.ordinal());
        }
    }

    /**
     * Replaces the optional potion contents and broadcasts the owning entity
     * state. Passing {@code null} clears those contents, and the injected
     * {@code baseComponent} must be available before publication.
     */
    @Override
    public void setPotionType(PotionType potionType) {
        this.potionType = potionType;
        this.baseComponent.broadcastState();
    }
}
