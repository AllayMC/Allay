package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemWarpedPlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedPlanksStackInitializer {
  static void init() {
    ItemTypes.WARPED_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemWarpedPlanksStack.class)
            .vanillaItem(VanillaItemId.WARPED_PLANKS)
            .build();
  }
}
