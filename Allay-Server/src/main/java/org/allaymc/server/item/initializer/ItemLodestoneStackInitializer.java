package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLodestoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLodestoneStackInitializer {
  static void init() {
    ItemTypes.LODESTONE_TYPE = ItemTypeBuilder
            .builder(ItemLodestoneStack.class)
            .vanillaItem(VanillaItemId.LODESTONE)
            .build();
  }
}
