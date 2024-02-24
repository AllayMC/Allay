package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemBrownConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownConcretePowderStackInitializer {
  static void init() {
    ItemTypes.BROWN_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemBrownConcretePowderStack.class)
            .vanillaItem(VanillaItemId.BROWN_CONCRETE_POWDER)
            .build();
  }
}
