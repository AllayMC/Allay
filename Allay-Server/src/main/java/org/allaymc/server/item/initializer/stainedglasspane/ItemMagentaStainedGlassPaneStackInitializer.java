package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemMagentaStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemMagentaStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_STAINED_GLASS_PANE)
            .build();
  }
}
