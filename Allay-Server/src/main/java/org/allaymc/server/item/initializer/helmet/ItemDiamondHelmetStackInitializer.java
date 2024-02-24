package org.allaymc.server.item.initializer.helmet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.helmet.ItemDiamondHelmetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondHelmetStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_HELMET_TYPE = ItemTypeBuilder
            .builder(ItemDiamondHelmetStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_HELMET)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
