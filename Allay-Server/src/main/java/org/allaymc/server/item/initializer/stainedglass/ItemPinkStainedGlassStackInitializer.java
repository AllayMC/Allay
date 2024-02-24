package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemPinkStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkStainedGlassStackInitializer {
  static void init() {
    ItemTypes.PINK_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemPinkStainedGlassStack.class)
            .vanillaItem(VanillaItemId.PINK_STAINED_GLASS)
            .build();
  }
}
