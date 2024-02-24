package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemComparatorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemComparatorStackInitializer {
  static void init() {
    ItemTypes.COMPARATOR_TYPE = ItemTypeBuilder
            .builder(ItemComparatorStack.class)
            .vanillaItem(VanillaItemId.COMPARATOR)
            .build();
  }
}
