package org.allaymc.server.item.initializer.sapling;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sapling.ItemBambooSaplingStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooSaplingStackInitializer {
  static void init() {
    ItemTypes.BAMBOO_SAPLING_TYPE = ItemTypeBuilder
            .builder(ItemBambooSaplingStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_SAPLING)
            .build();
  }
}
