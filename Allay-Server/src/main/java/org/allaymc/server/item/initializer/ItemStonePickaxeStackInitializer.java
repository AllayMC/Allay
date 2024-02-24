package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStonePickaxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStonePickaxeStackInitializer {
  static void init() {
    ItemTypes.STONE_PICKAXE_TYPE = ItemTypeBuilder
            .builder(ItemStonePickaxeStack.class)
            .vanillaItem(VanillaItemId.STONE_PICKAXE)
            .build();
  }
}
