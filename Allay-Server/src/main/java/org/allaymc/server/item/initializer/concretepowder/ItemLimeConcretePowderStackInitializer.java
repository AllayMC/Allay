package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemLimeConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeConcretePowderStackInitializer {
  static void init() {
    ItemTypes.LIME_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemLimeConcretePowderStack.class)
            .vanillaItem(VanillaItemId.LIME_CONCRETE_POWDER)
            .build();
  }
}
