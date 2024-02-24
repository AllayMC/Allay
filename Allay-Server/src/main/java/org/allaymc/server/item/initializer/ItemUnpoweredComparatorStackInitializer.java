package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemUnpoweredComparatorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnpoweredComparatorStackInitializer {
  static void init() {
    ItemTypes.UNPOWERED_COMPARATOR_TYPE = ItemTypeBuilder
            .builder(ItemUnpoweredComparatorStack.class)
            .vanillaItem(VanillaItemId.UNPOWERED_COMPARATOR)
            .build();
  }
}
