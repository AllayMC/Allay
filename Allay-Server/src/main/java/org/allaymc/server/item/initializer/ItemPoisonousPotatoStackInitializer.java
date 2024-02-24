package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPoisonousPotatoStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPoisonousPotatoStackInitializer {
  static void init() {
    ItemTypes.POISONOUS_POTATO_TYPE = ItemTypeBuilder
            .builder(ItemPoisonousPotatoStack.class)
            .vanillaItem(VanillaItemId.POISONOUS_POTATO)
            .build();
  }
}
