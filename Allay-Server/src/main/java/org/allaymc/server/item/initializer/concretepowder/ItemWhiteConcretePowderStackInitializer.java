package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemWhiteConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteConcretePowderStackInitializer {
  static void init() {
    ItemTypes.WHITE_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemWhiteConcretePowderStack.class)
            .vanillaItem(VanillaItemId.WHITE_CONCRETE_POWDER)
            .build();
  }
}
