package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemOakPlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakPlanksStackInitializer {
  static void init() {
    ItemTypes.OAK_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemOakPlanksStack.class)
            .vanillaItem(VanillaItemId.OAK_PLANKS)
            .build();
  }
}
