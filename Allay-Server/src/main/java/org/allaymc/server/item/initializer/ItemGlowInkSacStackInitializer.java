package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGlowInkSacStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowInkSacStackInitializer {
  static void init() {
    ItemTypes.GLOW_INK_SAC_TYPE = ItemTypeBuilder
            .builder(ItemGlowInkSacStack.class)
            .vanillaItem(VanillaItemId.GLOW_INK_SAC)
            .build();
  }
}
