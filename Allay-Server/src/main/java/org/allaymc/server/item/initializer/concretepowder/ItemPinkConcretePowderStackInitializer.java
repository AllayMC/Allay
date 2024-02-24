package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemPinkConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkConcretePowderStackInitializer {
  static void init() {
    ItemTypes.PINK_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemPinkConcretePowderStack.class)
            .vanillaItem(VanillaItemId.PINK_CONCRETE_POWDER)
            .build();
  }
}
