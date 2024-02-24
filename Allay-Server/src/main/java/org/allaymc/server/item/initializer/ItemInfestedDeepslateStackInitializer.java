package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemInfestedDeepslateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInfestedDeepslateStackInitializer {
  static void init() {
    ItemTypes.INFESTED_DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemInfestedDeepslateStack.class)
            .vanillaItem(VanillaItemId.INFESTED_DEEPSLATE)
            .build();
  }
}
