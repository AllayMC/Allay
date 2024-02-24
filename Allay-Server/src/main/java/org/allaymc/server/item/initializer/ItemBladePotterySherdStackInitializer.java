package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBladePotterySherdStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBladePotterySherdStackInitializer {
  static void init() {
    ItemTypes.BLADE_POTTERY_SHERD_TYPE = ItemTypeBuilder
            .builder(ItemBladePotterySherdStack.class)
            .vanillaItem(VanillaItemId.BLADE_POTTERY_SHERD)
            .build();
  }
}
