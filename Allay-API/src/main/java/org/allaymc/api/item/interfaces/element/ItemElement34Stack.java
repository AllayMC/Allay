package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement34Stack extends ItemStack {
  ItemType<ItemElement34Stack> ELEMENT_34_TYPE = ItemTypeBuilder
          .builder(ItemElement34Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_34)
          .build();
}
