package org.allaymc.server.item.initializer.chestplate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.chestplate.ItemLeatherChestplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherChestplateStackInitializer {
  static void init() {
    ItemTypes.LEATHER_CHESTPLATE_TYPE = ItemTypeBuilder
            .builder(ItemLeatherChestplateStack.class)
            .vanillaItem(VanillaItemId.LEATHER_CHESTPLATE)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
