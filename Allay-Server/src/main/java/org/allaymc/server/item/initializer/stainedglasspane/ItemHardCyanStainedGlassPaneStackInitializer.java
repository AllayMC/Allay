package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardCyanStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardCyanStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_CYAN_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardCyanStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_CYAN_STAINED_GLASS_PANE)
            .build();
  }
}
