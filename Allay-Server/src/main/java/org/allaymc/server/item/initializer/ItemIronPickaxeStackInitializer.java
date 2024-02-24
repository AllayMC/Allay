package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronPickaxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronPickaxeStackInitializer {
  static void init() {
    ItemTypes.IRON_PICKAXE_TYPE = ItemTypeBuilder
            .builder(ItemIronPickaxeStack.class)
            .vanillaItem(VanillaItemId.IRON_PICKAXE)
            .build();
  }
}
