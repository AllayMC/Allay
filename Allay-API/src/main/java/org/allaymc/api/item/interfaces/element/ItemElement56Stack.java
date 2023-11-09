package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement56Stack extends ItemStack {
  ItemType<ItemElement56Stack> ELEMENT_56_TYPE = ItemTypeBuilder
          .builder(ItemElement56Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_56)
          .build();
}
