package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemJunglePlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJunglePlanksStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemJunglePlanksStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_PLANKS)
            .build();
  }
}
