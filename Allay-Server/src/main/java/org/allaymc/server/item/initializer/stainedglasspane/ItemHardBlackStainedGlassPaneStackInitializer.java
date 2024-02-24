package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardBlackStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardBlackStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_BLACK_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardBlackStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_BLACK_STAINED_GLASS_PANE)
            .build();
  }
}
