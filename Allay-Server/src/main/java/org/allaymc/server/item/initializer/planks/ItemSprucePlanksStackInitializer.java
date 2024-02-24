package org.allaymc.server.item.initializer.planks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.planks.ItemSprucePlanksStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSprucePlanksStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_PLANKS_TYPE = ItemTypeBuilder
            .builder(ItemSprucePlanksStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_PLANKS)
            .build();
  }
}
