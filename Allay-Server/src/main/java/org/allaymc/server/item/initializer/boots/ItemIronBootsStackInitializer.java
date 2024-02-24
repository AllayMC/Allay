package org.allaymc.server.item.initializer.boots;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.boots.ItemIronBootsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronBootsStackInitializer {
  static void init() {
    ItemTypes.IRON_BOOTS_TYPE = ItemTypeBuilder
            .builder(ItemIronBootsStack.class)
            .vanillaItem(VanillaItemId.IRON_BOOTS)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
