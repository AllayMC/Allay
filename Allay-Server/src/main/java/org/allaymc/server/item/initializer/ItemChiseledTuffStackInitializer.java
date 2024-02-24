package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChiseledTuffStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledTuffStackInitializer {
  static void init() {
    ItemTypes.CHISELED_TUFF_TYPE = ItemTypeBuilder
            .builder(ItemChiseledTuffStack.class)
            .vanillaItem(VanillaItemId.CHISELED_TUFF)
            .build();
  }
}
