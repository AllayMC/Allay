package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement87Stack extends ItemStack {
  ItemType<ItemElement87Stack> ELEMENT_87_TYPE = ItemTypeBuilder
          .builder(ItemElement87Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_87)
          .build();
}
