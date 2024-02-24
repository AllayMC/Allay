package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardBlackStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardBlackStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_BLACK_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardBlackStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_BLACK_STAINED_GLASS)
            .build();
  }
}
