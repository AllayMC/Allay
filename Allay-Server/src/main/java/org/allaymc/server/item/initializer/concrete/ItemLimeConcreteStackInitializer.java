package org.allaymc.server.item.initializer.concrete;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.concrete.ItemLimeConcreteStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeConcreteStackInitializer {
  static void init() {
    ItemTypes.LIME_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemLimeConcreteStack.class)
            .vanillaItem(VanillaItemId.LIME_CONCRETE)
            .build();
  }
}
