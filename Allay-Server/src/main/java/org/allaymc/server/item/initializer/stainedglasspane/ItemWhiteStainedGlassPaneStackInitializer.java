package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemWhiteStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.WHITE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemWhiteStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.WHITE_STAINED_GLASS_PANE)
            .build();
  }
}
