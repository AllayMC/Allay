package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSpyglassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpyglassStackInitializer {
  static void init() {
    ItemTypes.SPYGLASS_TYPE = ItemTypeBuilder
            .builder(ItemSpyglassStack.class)
            .vanillaItem(VanillaItemId.SPYGLASS)
            .build();
  }
}
