package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement40Stack extends ItemStack {
  ItemType<ItemElement40Stack> ELEMENT_40_TYPE = ItemTypeBuilder
          .builder(ItemElement40Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_40)
          .build();
}
