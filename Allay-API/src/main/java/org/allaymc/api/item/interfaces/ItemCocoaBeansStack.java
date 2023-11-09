package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCocoaBeansStack extends ItemStack {
  ItemType<ItemCocoaBeansStack> COCOA_BEANS_TYPE = ItemTypeBuilder
          .builder(ItemCocoaBeansStack.class)
          .vanillaItem(VanillaItemId.COCOA_BEANS)
          .build();
}
