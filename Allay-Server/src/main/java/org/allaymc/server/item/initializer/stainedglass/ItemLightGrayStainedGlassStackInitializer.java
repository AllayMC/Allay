package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemLightGrayStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayStainedGlassStackInitializer {
  static void init() {
    ItemTypes.LIGHT_GRAY_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayStainedGlassStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_STAINED_GLASS)
            .build();
  }
}
