package org.allaymc.server.item.initializer.concretepowder;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concretepowder.ItemBlueConcretePowderStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueConcretePowderStackInitializer {
  static void init() {
    ItemTypes.BLUE_CONCRETE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemBlueConcretePowderStack.class)
            .vanillaItem(VanillaItemId.BLUE_CONCRETE_POWDER)
            .build();
  }
}
