package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemLightGrayConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayConcretePowderStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayConcretePowderStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_CONCRETE_POWDER)
            .build();
  }
}
