package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPurpleDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleDyeStackInitializer {
  static void init() {
    ItemTypes.PURPLE_DYE_TYPE = ItemTypeBuilder
            .builder(ItemPurpleDyeStack.class)
            .vanillaItem(VanillaItemId.PURPLE_DYE)
            .build();
  }
}
