package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardWhiteStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardWhiteStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_WHITE_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardWhiteStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_WHITE_STAINED_GLASS)
            .build();
  }
}
