package org.allaymc.server.item.initializer.helmet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.helmet.ItemNetheriteHelmetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteHelmetStackInitializer {
  static void init() {
    ItemTypes.NETHERITE_HELMET_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteHelmetStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_HELMET)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
