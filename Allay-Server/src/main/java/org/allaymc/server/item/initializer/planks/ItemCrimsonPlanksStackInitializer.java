package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemCrimsonPlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonPlanksStackInitializer {
  static void init() {
    ItemTypes.CRIMSON_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonPlanksStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_PLANKS)
            .build();
  }
}
