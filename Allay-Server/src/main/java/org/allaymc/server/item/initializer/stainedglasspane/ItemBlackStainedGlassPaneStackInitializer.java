package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemBlackStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.BLACK_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemBlackStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.BLACK_STAINED_GLASS_PANE)
            .build();
  }
}
