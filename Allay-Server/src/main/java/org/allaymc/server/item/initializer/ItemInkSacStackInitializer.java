package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemInkSacStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInkSacStackInitializer {
  static void init() {
    ItemTypes.INK_SAC_TYPE = ItemTypeBuilder
            .builder(ItemInkSacStack.class)
            .vanillaItem(VanillaItemId.INK_SAC)
            .build();
  }
}
