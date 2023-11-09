package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement107Stack extends ItemStack {
  ItemType<ItemElement107Stack> ELEMENT_107_TYPE = ItemTypeBuilder
          .builder(ItemElement107Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_107)
          .build();
}
