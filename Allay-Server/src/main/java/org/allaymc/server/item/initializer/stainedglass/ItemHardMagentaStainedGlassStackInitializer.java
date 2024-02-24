package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardMagentaStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardMagentaStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_MAGENTA_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardMagentaStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_MAGENTA_STAINED_GLASS)
            .build();
  }
}
