package org.allaymc.server.item.initializer.chestplate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.chestplate.ItemDiamondChestplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondChestplateStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_CHESTPLATE_TYPE = ItemTypeBuilder
            .builder(ItemDiamondChestplateStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_CHESTPLATE)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
