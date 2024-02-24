package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemMagentaStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaStainedGlassStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemMagentaStainedGlassStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_STAINED_GLASS)
            .build();
  }
}
