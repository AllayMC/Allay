package org.allaymc.server.item.initializer.chestplate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.chestplate.ItemChainmailChestplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainmailChestplateStackInitializer {
  static void init() {
    ItemTypes.CHAINMAIL_CHESTPLATE_TYPE = ItemTypeBuilder
            .builder(ItemChainmailChestplateStack.class)
            .vanillaItem(VanillaItemId.CHAINMAIL_CHESTPLATE)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
