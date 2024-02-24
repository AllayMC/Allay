package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemPurpleConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleConcretePowderStackInitializer {
  static void init() {
    ItemTypes.PURPLE_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemPurpleConcretePowderStack.class)
            .vanillaItem(VanillaItemId.PURPLE_CONCRETE_POWDER)
            .build();
  }
}
