package org.allaymc.server.item.component;

import lombok.AllArgsConstructor;
import org.allaymc.api.item.component.ItemRepairableComponent;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.data.ItemId;

/**
 * @author IWareQ
 */
@AllArgsConstructor
public class ItemRepairableComponentImpl implements ItemRepairableComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:item_repairable_component");

    private final ItemId itemId;

    @Override
    public boolean canBeRepairedBy(ItemType<?> itemType) {
        return this.itemId.getItemType() == itemType;
    }
}
