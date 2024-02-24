package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChorusFlowerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChorusFlowerStackInitializer {
  static void init() {
    ItemTypes.CHORUS_FLOWER_TYPE = ItemTypeBuilder
            .builder(ItemChorusFlowerStack.class)
            .vanillaItem(VanillaItemId.CHORUS_FLOWER)
            .build();
  }
}
