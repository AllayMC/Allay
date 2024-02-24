package org.allaymc.server.item.initializer.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stainedglass.ItemHardOrangeStainedGlassStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardOrangeStainedGlassStackInitializer {
  static void init() {
    ItemTypes.HARD_ORANGE_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemHardOrangeStainedGlassStack.class)
            .vanillaItem(VanillaItemId.HARD_ORANGE_STAINED_GLASS)
            .build();
  }
}
