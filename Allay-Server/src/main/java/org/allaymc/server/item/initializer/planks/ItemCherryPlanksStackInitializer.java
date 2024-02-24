package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemCherryPlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryPlanksStackInitializer {
  static void init() {
    ItemTypes.CHERRY_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemCherryPlanksStack.class)
            .vanillaItem(VanillaItemId.CHERRY_PLANKS)
            .build();
  }
}
