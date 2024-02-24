package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardMagentaStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardMagentaStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_MAGENTA_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardMagentaStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_MAGENTA_STAINED_GLASS_PANE)
            .build();
  }
}
