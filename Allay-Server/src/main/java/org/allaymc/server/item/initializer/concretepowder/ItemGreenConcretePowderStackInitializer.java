package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemGreenConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenConcretePowderStackInitializer {
  static void init() {
    ItemTypes.GREEN_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemGreenConcretePowderStack.class)
            .vanillaItem(VanillaItemId.GREEN_CONCRETE_POWDER)
            .build();
  }
}
