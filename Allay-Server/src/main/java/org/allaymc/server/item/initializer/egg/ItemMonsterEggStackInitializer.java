package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemMonsterEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMonsterEggStackInitializer {
  static void init() {
    ItemTypes.MONSTER_EGG_TYPE = ItemTypeBuilder
            .builder(ItemMonsterEggStack.class)
            .vanillaItem(VanillaItemId.MONSTER_EGG)
            .build();
  }
}
