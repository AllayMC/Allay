package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemGrayStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayStainedGlassStackInitializer {
  static void init() {
    ItemTypes.GRAY_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemGrayStainedGlassStack.class)
            .vanillaItem(VanillaItemId.GRAY_STAINED_GLASS)
            .build();
  }
}
