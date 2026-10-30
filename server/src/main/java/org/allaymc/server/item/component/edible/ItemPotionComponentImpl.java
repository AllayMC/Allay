package org.allaymc.server.item.component.edible;

import org.allaymc.api.item.component.ItemBaseComponent;
import org.allaymc.api.item.component.ItemPotionComponent;
import org.allaymc.api.item.data.PotionType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.Dependency;

/**
 * {@code ItemPotionComponentImpl} maps potion selections to the owning stack's
 * numeric metadata. The injected base component stores that metadata, while
 * ordinal conversion supplies the potion definition read by drink and
 * projectile behavior without a separate potion field.
 *
 * @author daoge_cmd
 */
public class ItemPotionComponentImpl implements ItemPotionComponent {

    /**
     * {@code IDENTIFIER} names the potion component for provider lookup and
     * dependency injection when item behavior is assembled.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:potion_component");

    /**
     * {@code baseComponent} holds the injected stack metadata owner. Potion
     * reads and writes use this same component after dependency injection
     * completes.
     */
    @Dependency
    protected ItemBaseComponent baseComponent;

    /**
     * Returns the potion selected by the base component's metadata ordinal.
     * Metadata outside {@link PotionType}'s available values causes an index
     * failure rather than selecting a fallback potion.
     */
    @Override
    public PotionType getPotionType() {
        return PotionType.fromId(baseComponent.getMeta());
    }

    /**
     * Writes the supplied potion type's ordinal into the owning stack
     * metadata. {@code potionType} must be non-{@code null}; later reads
     * resolve the stored metadata through the same ordinal mapping.
     */
    @Override
    public void setPotionType(PotionType potionType) {
        baseComponent.setMeta(potionType.ordinal());
    }
}
