package org.allaymc.server.item.initializer.chestplate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.chestplate.ItemNetheriteChestplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteChestplateStackInitializer {
  static void init() {
    ItemTypes.NETHERITE_CHESTPLATE_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteChestplateStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_CHESTPLATE)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
