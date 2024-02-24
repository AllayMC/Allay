package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStainedGlassStackInitializer {
  static void init() {
    ItemTypes.STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemStainedGlassStack.class)
            .vanillaItem(VanillaItemId.STAINED_GLASS)
            .build();
  }
}
