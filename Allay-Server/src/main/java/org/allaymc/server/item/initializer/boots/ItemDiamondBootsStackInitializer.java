package org.allaymc.server.item.initializer.boots;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boots.ItemDiamondBootsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondBootsStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_BOOTS_TYPE = ItemTypeBuilder
            .builder(ItemDiamondBootsStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_BOOTS)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
