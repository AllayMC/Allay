package org.allaymc.server.item.initializer.helmet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.helmet.ItemChainmailHelmetStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.armor.ItemArmorBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainmailHelmetStackInitializer {
  static void init() {
    ItemTypes.CHAINMAIL_HELMET_TYPE = ItemTypeBuilder
            .builder(ItemChainmailHelmetStack.class)
            .vanillaItem(VanillaItemId.CHAINMAIL_HELMET)
            .addComponent(ItemArmorBaseComponentImpl::new, ItemArmorBaseComponentImpl.class)
            .build();
  }
}
