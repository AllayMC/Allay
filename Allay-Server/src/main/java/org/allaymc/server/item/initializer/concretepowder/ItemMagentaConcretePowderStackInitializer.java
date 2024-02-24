package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemMagentaConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaConcretePowderStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemMagentaConcretePowderStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_CONCRETE_POWDER)
            .build();
  }
}
