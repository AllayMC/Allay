package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemPlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPlanksStackInitializer {
  static void init() {
    ItemTypes.PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemPlanksStack.class)
            .vanillaItem(VanillaItemId.PLANKS)
            .build();
  }
}
