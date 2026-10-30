package org.allaymc.server.item.component;

import lombok.AllArgsConstructor;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.data.ItemId;

import java.util.function.Supplier;

/**
 * {@code ItemRepairableComponentImpl} accepts one lazily resolved item type as an
 * item's repair material. The generated supplier constructor retains the resolver;
 * the identifier constructor defers item lookup through that resolver. A missing
 * resolved type rejects every repair candidate.
 *
 * @author IWareQ
 */
@AllArgsConstructor
public class ItemRepairableComponentImpl implements ItemRepairableComponent {
    /**
     * {@code IDENTIFIER} names this behavior provider in the item component system,
     * allowing item type builders to select or replace this component by identifier.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_repairable_component");

    /**
     * {@code itemType} resolves the accepted repair material on each query.
     * The generated constructor retains the supplier rather than resolving its value
     * during component construction.
     */
    private final Supplier<ItemType<?>> itemType;

    /**
     * Creates a repair-material resolver from the supplied item identifier mapping.
     */
    public ItemRepairableComponentImpl(ItemId itemId) {
        this(itemId::getItemType);
    }

    /**
     * Returns whether the candidate is the currently resolved repair material.
     * Comparison uses item-type identity, and a resolver result of {@code null}
     * rejects the candidate rather than matching a {@code null} argument.
     */
    @Override
    public boolean canBeRepairedBy(ItemType<?> itemType) {
        var repairItemType = this.itemType.get();
        return repairItemType != null && repairItemType == itemType;
    }
}
