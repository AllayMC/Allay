package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemBirchPlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchPlanksStackInitializer {
  static void init() {
    ItemTypes.BIRCH_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemBirchPlanksStack.class)
            .vanillaItem(VanillaItemId.BIRCH_PLANKS)
            .build();
  }
}
