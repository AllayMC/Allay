package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeepslateLapisOreStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateLapisOreStackInitializer {
  static void init() {
    ItemTypes.DEEPSLATE_LAPIS_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateLapisOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_LAPIS_ORE)
            .build();
  }
}
