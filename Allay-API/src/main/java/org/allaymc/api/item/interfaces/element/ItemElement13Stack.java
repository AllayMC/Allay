package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement13Stack extends ItemStack {
  ItemType<ItemElement13Stack> ELEMENT_13_TYPE = ItemTypeBuilder
          .builder(ItemElement13Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_13)
          .build();
}
