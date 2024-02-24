package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEnderEyeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEnderEyeStackInitializer {
  static void init() {
    ItemTypes.ENDER_EYE_TYPE = ItemTypeBuilder
            .builder(ItemEnderEyeStack.class)
            .vanillaItem(VanillaItemId.ENDER_EYE)
            .build();
  }
}
