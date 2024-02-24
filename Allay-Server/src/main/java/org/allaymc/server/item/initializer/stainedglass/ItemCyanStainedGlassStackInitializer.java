package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemCyanStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanStainedGlassStackInitializer {
  static void init() {
    ItemTypes.CYAN_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemCyanStainedGlassStack.class)
            .vanillaItem(VanillaItemId.CYAN_STAINED_GLASS)
            .build();
  }
}
