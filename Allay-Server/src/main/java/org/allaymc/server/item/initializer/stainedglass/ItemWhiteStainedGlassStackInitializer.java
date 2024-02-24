package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemWhiteStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteStainedGlassStackInitializer {
  static void init() {
    ItemTypes.WHITE_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemWhiteStainedGlassStack.class)
            .vanillaItem(VanillaItemId.WHITE_STAINED_GLASS)
            .build();
  }
}
