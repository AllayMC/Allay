package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement109Stack extends ItemStack {
  ItemType<ItemElement109Stack> ELEMENT_109_TYPE = ItemTypeBuilder
          .builder(ItemElement109Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_109)
          .build();
}
