package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemOrangeConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeConcretePowderStackInitializer {
  static void init() {
    ItemTypes.ORANGE_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemOrangeConcretePowderStack.class)
            .vanillaItem(VanillaItemId.ORANGE_CONCRETE_POWDER)
            .build();
  }
}
