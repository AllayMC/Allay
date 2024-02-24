package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChiseledPolishedBlackstoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledPolishedBlackstoneStackInitializer {
  static void init() {
    ItemTypes.CHISELED_POLISHED_BLACKSTONE_TYPE = ItemTypeBuilder
            .builder(ItemChiseledPolishedBlackstoneStack.class)
            .vanillaItem(VanillaItemId.CHISELED_POLISHED_BLACKSTONE)
            .build();
  }
}
