package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDiamondPickaxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondPickaxeStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_PICKAXE_TYPE = ItemTypeBuilder
            .builder(ItemDiamondPickaxeStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_PICKAXE)
            .build();
  }
}
