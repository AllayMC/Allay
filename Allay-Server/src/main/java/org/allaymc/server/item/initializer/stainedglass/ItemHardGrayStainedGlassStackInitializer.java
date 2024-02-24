package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardGrayStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardGrayStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_GRAY_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardGrayStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_GRAY_STAINED_GLASS)
            .build();
  }
}
