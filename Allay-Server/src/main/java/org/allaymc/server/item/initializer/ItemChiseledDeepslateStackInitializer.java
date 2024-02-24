package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChiseledDeepslateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledDeepslateStackInitializer {
  static void init() {
    ItemTypes.CHISELED_DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemChiseledDeepslateStack.class)
            .vanillaItem(VanillaItemId.CHISELED_DEEPSLATE)
            .build();
  }
}
