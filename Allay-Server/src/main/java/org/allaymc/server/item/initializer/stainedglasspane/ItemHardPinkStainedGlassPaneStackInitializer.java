package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardPinkStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardPinkStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_PINK_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardPinkStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_PINK_STAINED_GLASS_PANE)
            .build();
  }
}
