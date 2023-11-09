package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement59Stack extends ItemStack {
  ItemType<ItemElement59Stack> ELEMENT_59_TYPE = ItemTypeBuilder
          .builder(ItemElement59Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_59)
          .build();
}
