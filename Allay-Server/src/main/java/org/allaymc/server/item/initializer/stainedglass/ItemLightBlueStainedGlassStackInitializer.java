package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemLightBlueStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueStainedGlassStackInitializer {
  static void init() {
    ItemTypes.LIGHT_BLUE_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueStainedGlassStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_STAINED_GLASS)
            .build();
  }
}
