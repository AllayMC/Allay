package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardPinkStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardPinkStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_PINK_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardPinkStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_PINK_STAINED_GLASS)
            .build();
  }
}
