package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemBambooPlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooPlanksStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemBambooPlanksStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_PLANKS)
            .build();
  }
}
