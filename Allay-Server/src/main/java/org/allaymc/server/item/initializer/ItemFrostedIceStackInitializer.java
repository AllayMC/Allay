package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFrostedIceStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFrostedIceStackInitializer {
  static void init() {
    ItemTypes.FROSTED_ICE_TYPE = ItemTypeBuilder
            .builder(ItemFrostedIceStack.class)
            .vanillaItem(VanillaItemId.FROSTED_ICE)
            .build();
  }
}
