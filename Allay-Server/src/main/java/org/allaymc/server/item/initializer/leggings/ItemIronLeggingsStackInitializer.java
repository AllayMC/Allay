package org.allaymc.server.item.initializer.leggings;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.leggings.ItemIronLeggingsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronLeggingsStackInitializer {
  static void init() {
    ItemTypes.IRON_LEGGINGS_TYPE = ItemTypeBuilder
            .builder(ItemIronLeggingsStack.class)
            .vanillaItem(VanillaItemId.IRON_LEGGINGS)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
