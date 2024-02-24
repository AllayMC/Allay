package org.allaymc.server.item.initializer.sapling;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.sapling.ItemCherrySaplingStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherrySaplingStackInitializer {
  static void init() {
    ItemTypes.CHERRY_SAPLING_TYPE = ItemTypeBuilder
            .builder(ItemCherrySaplingStack.class)
            .vanillaItem(VanillaItemId.CHERRY_SAPLING)
            .build();
  }
}
