package org.allaymc.server.item.initializer.helmet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.helmet.ItemGoldenHelmetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenHelmetStackInitializer {
  static void init() {
    ItemTypes.GOLDEN_HELMET_TYPE = ItemTypeBuilder
            .builder(ItemGoldenHelmetStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_HELMET)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
