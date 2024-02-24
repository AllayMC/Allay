package org.allaymc.server.item.initializer.leggings;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leggings.ItemDiamondLeggingsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondLeggingsStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_LEGGINGS_TYPE = ItemTypeBuilder
            .builder(ItemDiamondLeggingsStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_LEGGINGS)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
