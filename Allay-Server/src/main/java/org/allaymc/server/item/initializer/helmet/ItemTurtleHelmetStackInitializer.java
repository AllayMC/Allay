package org.allaymc.server.item.initializer.helmet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.helmet.ItemTurtleHelmetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTurtleHelmetStackInitializer {
  static void init() {
    ItemTypes.TURTLE_HELMET_TYPE = ItemTypeBuilder
            .builder(ItemTurtleHelmetStack.class)
            .vanillaItem(VanillaItemId.TURTLE_HELMET)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
