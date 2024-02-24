package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetheritePickaxeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheritePickaxeStackInitializer {
  static void init() {
    ItemTypes.NETHERITE_PICKAXE_TYPE = ItemTypeBuilder
            .builder(ItemNetheritePickaxeStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_PICKAXE)
            .build();
  }
}
