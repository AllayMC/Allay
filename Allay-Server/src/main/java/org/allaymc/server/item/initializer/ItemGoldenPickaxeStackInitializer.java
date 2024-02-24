package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemGoldenPickaxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenPickaxeStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_PICKAXE_TYPE = ItemTypeBuilder
            .builder(ItemGoldenPickaxeStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_PICKAXE)
            .build();
  }
}
