package org.allaymc.server.item.initializer.leggings;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leggings.ItemLeatherLeggingsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherLeggingsStackInitializer {
  static void init() {
    ItemTypes.LEATHER_LEGGINGS_TYPE = ItemTypeBuilder
            .builder(ItemLeatherLeggingsStack.class)
            .vanillaItem(VanillaItemId.LEATHER_LEGGINGS)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
