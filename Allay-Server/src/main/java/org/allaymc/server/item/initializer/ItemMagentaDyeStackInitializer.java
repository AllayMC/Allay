package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemMagentaDyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaDyeStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_DYE_TYPE = ItemTypeBuilder
            .builder(ItemMagentaDyeStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_DYE)
            .build();
  }
}
