package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardBrownStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardBrownStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_BROWN_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardBrownStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_BROWN_STAINED_GLASS_PANE)
            .build();
  }
}
