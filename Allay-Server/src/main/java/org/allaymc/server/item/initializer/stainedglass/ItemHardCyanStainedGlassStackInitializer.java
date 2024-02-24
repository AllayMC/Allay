package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardCyanStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardCyanStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_CYAN_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardCyanStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_CYAN_STAINED_GLASS)
            .build();
  }
}
