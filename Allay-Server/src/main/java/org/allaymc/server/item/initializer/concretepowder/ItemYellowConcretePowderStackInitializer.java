package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemYellowConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowConcretePowderStackInitializer {
  static void init() {
    ItemTypes.YELLOW_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemYellowConcretePowderStack.class)
            .vanillaItem(VanillaItemId.YELLOW_CONCRETE_POWDER)
            .build();
  }
}
