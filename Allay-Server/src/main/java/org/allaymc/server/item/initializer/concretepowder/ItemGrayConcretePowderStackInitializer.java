package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemGrayConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayConcretePowderStackInitializer {
  static void init() {
    ItemTypes.GRAY_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemGrayConcretePowderStack.class)
            .vanillaItem(VanillaItemId.GRAY_CONCRETE_POWDER)
            .build();
  }
}
