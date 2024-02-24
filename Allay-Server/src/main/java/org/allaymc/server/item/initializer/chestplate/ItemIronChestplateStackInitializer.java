package org.allaymc.server.item.initializer.chestplate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.chestplate.ItemIronChestplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronChestplateStackInitializer {
  static void init() {
    ItemTypes.IRON_CHESTPLATE_TYPE = ItemTypeBuilder
            .builder(ItemIronChestplateStack.class)
            .vanillaItem(VanillaItemId.IRON_CHESTPLATE)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
