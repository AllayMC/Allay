package org.allaymc.server.item.initializer.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglasspane.ItemHardOrangeStainedGlassPaneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardOrangeStainedGlassPaneStackInitializer {
  static void init() {
    ItemTypes.HARD_ORANGE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
            .builder(ItemHardOrangeStainedGlassPaneStack.class)
            .vanillaItem(VanillaItemId.HARD_ORANGE_STAINED_GLASS_PANE)
            .build();
  }
}
