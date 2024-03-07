package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.air.ItemAirBaseComponentImpl;

/**
 * Allay Project 07/03/2024
 *
 * @author IWareQ
 */
public interface ItemAirStackInitializer {

    static void init() {
        ItemTypes.AIR_TYPE = ItemTypeBuilder
                .builder(ItemAirStack.class)
                .vanillaItem(VanillaItemId.AIR)
                .addComponent(ItemAirBaseComponentImpl::new, ItemAirBaseComponentImpl.class)
                .build();
    }
}
