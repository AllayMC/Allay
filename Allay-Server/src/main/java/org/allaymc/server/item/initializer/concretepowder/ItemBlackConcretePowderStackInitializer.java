package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemBlackConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackConcretePowderStackInitializer {
  static void init() {
    ItemTypes.BLACK_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemBlackConcretePowderStack.class)
            .vanillaItem(VanillaItemId.BLACK_CONCRETE_POWDER)
            .build();
  }
}
