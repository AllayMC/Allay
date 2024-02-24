package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemBrownConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownConcreteStackInitializer {
  static void init() {
    ItemTypes.BROWN_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemBrownConcreteStack.class)
            .vanillaItem(VanillaItemId.BROWN_CONCRETE)
            .build();
  }
}
