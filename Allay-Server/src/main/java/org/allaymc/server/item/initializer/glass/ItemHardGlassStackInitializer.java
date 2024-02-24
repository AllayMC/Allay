package org.allaymc.server.item.initializer.glass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.glass.ItemHardGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_GLASS)
            .build();
  }
}
