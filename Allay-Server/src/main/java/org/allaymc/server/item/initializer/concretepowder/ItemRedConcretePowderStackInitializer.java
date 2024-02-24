package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemRedConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedConcretePowderStackInitializer {
  static void init() {
    ItemTypes.RED_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemRedConcretePowderStack.class)
            .vanillaItem(VanillaItemId.RED_CONCRETE_POWDER)
            .build();
  }
}
