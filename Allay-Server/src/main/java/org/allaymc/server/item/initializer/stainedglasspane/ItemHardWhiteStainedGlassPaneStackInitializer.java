package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardWhiteStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardWhiteStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_WHITE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardWhiteStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_WHITE_STAINED_GLASS_PANE)
            .build();
  }
}
