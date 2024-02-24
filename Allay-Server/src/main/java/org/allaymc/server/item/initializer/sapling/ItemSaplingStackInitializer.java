package org.allaymc.server.item.initializer.sapling;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sapling.ItemSaplingStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSaplingStackInitializer {
  static void init() {
    ItemTypes.SAPLING_TYPE = ItemTypeBuilder
            .builder(ItemSaplingStack.class)
            .vanillaItem(VanillaItemId.SAPLING)
            .build();
  }
}
