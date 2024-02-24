package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemDarkOakPlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakPlanksStackInitializer {
  static void init() {
    ItemTypes.DARK_OAK_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakPlanksStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_PLANKS)
            .build();
  }
}
