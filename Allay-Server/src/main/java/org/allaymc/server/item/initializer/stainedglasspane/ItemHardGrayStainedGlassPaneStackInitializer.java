package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardGrayStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardGrayStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_GRAY_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardGrayStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_GRAY_STAINED_GLASS_PANE)
            .build();
  }
}
