package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemLightBlueConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueConcretePowderStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueConcretePowderStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_CONCRETE_POWDER)
            .build();
  }
}
