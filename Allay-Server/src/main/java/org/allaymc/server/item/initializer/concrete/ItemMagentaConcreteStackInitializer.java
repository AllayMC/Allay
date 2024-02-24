package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemMagentaConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaConcreteStackInitializer {
  static void init() {
    ItemTypes.MAGENTA_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemMagentaConcreteStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_CONCRETE)
            .build();
  }
}
