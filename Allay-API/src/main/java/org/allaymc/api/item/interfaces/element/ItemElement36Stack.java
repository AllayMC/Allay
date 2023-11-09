package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement36Stack extends ItemStack {
  ItemType<ItemElement36Stack> ELEMENT_36_TYPE = ItemTypeBuilder
          .builder(ItemElement36Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_36)
          .build();
}
