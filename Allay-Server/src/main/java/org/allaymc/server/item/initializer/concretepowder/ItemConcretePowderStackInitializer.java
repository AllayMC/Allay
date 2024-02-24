package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemConcretePowderStackInitializer {
  static void init() {
    ItemTypes.CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemConcretePowderStack.class)
            .vanillaItem(VanillaItemId.CONCRETE_POWDER)
            .build();
  }
}
