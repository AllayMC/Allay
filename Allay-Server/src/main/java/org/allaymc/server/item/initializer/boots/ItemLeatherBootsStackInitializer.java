package org.allaymc.server.item.initializer.boots;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boots.ItemLeatherBootsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherBootsStackInitializer {
  static void init() {
    ItemTypes.LEATHER_BOOTS_TYPE = ItemTypeBuilder
            .builder(ItemLeatherBootsStack.class)
            .vanillaItem(VanillaItemId.LEATHER_BOOTS)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
