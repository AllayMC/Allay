package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardLightGrayStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardLightGrayStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_LIGHT_GRAY_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardLightGrayStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_LIGHT_GRAY_STAINED_GLASS_PANE)
            .build();
  }
}
