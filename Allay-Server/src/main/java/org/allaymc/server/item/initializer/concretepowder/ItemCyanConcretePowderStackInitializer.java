package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemCyanConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanConcretePowderStackInitializer {
  static void init() {
    ItemTypes.CYAN_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemCyanConcretePowderStack.class)
            .vanillaItem(VanillaItemId.CYAN_CONCRETE_POWDER)
            .build();
  }
}
