package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPoweredComparatorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPoweredComparatorStackInitializer {
  static void init() {
    ItemTypes.POWERED_COMPARATOR_TYPE = ItemTypeBuilder
            .builder(ItemPoweredComparatorStack.class)
            .vanillaItem(VanillaItemId.POWERED_COMPARATOR)
            .build();
  }
}
