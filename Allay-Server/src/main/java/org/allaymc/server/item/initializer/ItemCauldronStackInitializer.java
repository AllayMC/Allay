package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemCauldronStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.cauldron.ItemCauldronBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCauldronStackInitializer {
  static void init() {
    ItemTypes.CAULDRON_TYPE = ItemTypeBuilder
            .builder(ItemCauldronStack.class)
            .vanillaItem(VanillaItemId.CAULDRON)
            .addComponent(ItemCauldronBaseComponentImpl::new, ItemCauldronBaseComponentImpl.class)
            .build();
  }
}
